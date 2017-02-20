/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hdf.hdf5lib.H5;
import hdf.hdf5lib.HDF5Constants;
import hdf.hdf5lib.exceptions.HDF5LibraryException;

/**
 * This contains method to access low level HDF5 file IDs and allows them to
 * be held open for a set period (default of 5s) before being closed.
 */
public class HDF5FileFactory {
	private static final Logger logger = LoggerFactory.getLogger(HDF5FileFactory.class);

	private static long heldPeriod = 5000; // 5 seconds
	private static long finishPeriod = heldPeriod / 5; // 1 seconds
	private static boolean verbose = false;

	/**
	 * @param verbose if true, print to standard error when opening, closing, creating or deleting files
	 */
	public static void setVerbose(boolean verbose) {
		HDF5FileFactory.verbose = verbose;
	}

	private final static HDF5FileFactory INSTANCE;

	static {
		INSTANCE = new HDF5FileFactory();
	}

	private ConcurrentMap<String, HDF5File> map;

	// Need singleton to add finalizer
	private HDF5FileFactory() {
		map = new ConcurrentHashMap<>();
	}

	@Override
	protected void finalize() throws Throwable {
		synchronized (map) {
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String f = iter.next();
				HDF5File a = map.remove(f);
				a.flushWrites();
				a.finish(finishPeriod);
				try {
					H5.H5Fclose(a.getID());
				} catch (HDF5LibraryException e) {
					logger.error("Could not close file: {}", f);
				}
			}
		}
		super.finalize();
	}

	/**
	 * Canonicalise path so that we can use it as a standard key
	 * @param absolutePath
	 * @return
	 * @throws IOException
	 */
	public static String canonicalisePath(String absolutePath) throws IOException {
		try {
			return new File(absolutePath).getCanonicalPath();
		} catch (IOException e) {
			logger.error("Could not get canonical path: {}", absolutePath);
			throw e;
		}
	}

	/**
	 * Set period of time a file ID is held open for. The period specified must be greater
	 * than or equal to 100 ms.
	 * @param heldPeriod in milliseconds
	 */
	public static void setHeldPeriod(long heldPeriod) {
		if (heldPeriod < 100) {
			throw new IllegalArgumentException();
		}
		finishPeriod = heldPeriod/10l;
		HDF5FileFactory.heldPeriod = heldPeriod;
	}

	/**
	 * Get period of time a file ID is held open for.
	 * @return period held in milliseconds
	 */
	public static long getHeldPeriod() {
		return heldPeriod;
	}

	private static void closeFile(HDF5File f) throws HDF5LibraryException {
		f.flushWrites();
		f.finish(finishPeriod);
		long fid = f.getID();
		long openObjects = H5.H5Fget_obj_count(fid,
				HDF5Constants.H5F_OBJ_LOCAL |
				HDF5Constants.H5F_OBJ_DATASET |
				HDF5Constants.H5F_OBJ_DATATYPE |
				HDF5Constants.H5F_OBJ_GROUP |
				HDF5Constants.H5F_OBJ_ATTR);
		if (openObjects > 0) {
			logger.error("There are " + openObjects + " hdf5 objects left open");
		}
		H5.H5Fclose(fid);
	}

	static {
		new Thread(new Runnable() {
			@Override
			public void run() {
				long waitPeriod = heldPeriod;
				while (true) {
					if (waitPeriod >= 0) {
						try {
							Thread.sleep(waitPeriod);
						} catch (InterruptedException e) {
						}
					}

					long now = System.currentTimeMillis();
					long next = now + heldPeriod;
					synchronized (INSTANCE.map) {
						Iterator<String> iter = INSTANCE.map.keySet().iterator();
						while (iter.hasNext()) {
							String f = iter.next();
							HDF5File a = INSTANCE.map.get(f);
							if (a.getCount() <= 0) {
								if (a.getTime() <= now) {
									try {
										closeFile(a);
										INSTANCE.map.remove(f);
// FIXME for CustomTomoConverter, etc 
//										HierarchicalDataFactory.releaseLowLevelReadingAccess(f);
									} catch (HDF5LibraryException e) {
										logger.error("Could not close file {}", f, e);
									}
								} else if (a.getTime() < next) {
									next = a.getTime(); // reduce waiting to next earliest between now and next
								}
							}
						}
					}
					waitPeriod = next - System.currentTimeMillis();
				}
			}
		}, "File ID releaser").start();

		// make sure all files are closed on shutdown
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					INSTANCE.finalize();
				} catch (Throwable e) {
				}
			}
		}, "File ID closer"));
	}

	/**
	 * Acquire file ID
	 * @param fileName
	 * @param writeable
	 * @param asNew
	 * @param withLatestVersion if true, use latest object format version for writing
	 * @return file ID
	 * @throws ScanFileHolderException
	 */
	private synchronized static HDF5File acquireFile(String fileName, boolean writeable, boolean asNew, boolean withLatestVersion) throws ScanFileHolderException {
		final String cPath;
		try {
			cPath = canonicalisePath(fileName);
		} catch (IOException e) {
			logger.error("Problem canonicalising path", e);
			throw new ScanFileHolderException("Problem canonicalising path", e);
		}

		HDF5File access = null;
		long fid = -1;
		long fapl = -1;
		boolean canSWMR = false;

		synchronized (INSTANCE.map) {
			try {
				if (INSTANCE.map.containsKey(cPath)) {
					access = INSTANCE.map.get(cPath);
					if (asNew) {
						// we should be able to create if nobody is actually using the old file handle,
						// even though it hasn't been disposed yet
						if (access.getCount() > 0) {
							logger.error("File already open and will need to closed: {}", cPath);
							throw new ScanFileHolderException("File already open and will need to closed");
						} else {
							// close and allow fall through to file creation below
							closeFile(access);
							INSTANCE.map.remove(cPath);
						}
					} else {
						if (writeable && !access.isWriteable()) {
							logger.error("Cannot get file {} in writeable state as it has been opened read-only", cPath);
							throw new ScanFileHolderException("Cannot get file in writeable state as it has been opened read-only");
						}
						access.incrementCount();
						return access;
					}
				}
// FIXME for CustomTomoConverter, etc 
//				HierarchicalDataFactory.acquireLowLevelReadingAccess(cPath);
				try {
					fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
					if (writeable && withLatestVersion) {
						canSWMR = true;
						H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
					}
					if (asNew) {
						if (verbose) {
							System.err.println("Creating " + cPath + " with latest " + withLatestVersion);
						}
						fid = H5.H5Fcreate(cPath, HDF5Constants.H5F_ACC_TRUNC, HDF5Constants.H5P_DEFAULT, fapl);
					} else {
						if (new File(cPath).exists()) {
							if (verbose) {
								System.err.println("Opening " + cPath + " with writeable " + writeable + " with latest " + withLatestVersion);
							}
							if (!writeable) {
								// attempt to read with SWMR access first
								int a = HDF5Constants.H5F_ACC_RDONLY | HDF5Constants.H5F_ACC_SWMR_READ;
								try {
									fid = H5.H5Fopen(cPath, a, fapl);
								} catch (HDF5LibraryException e) {
									// this can happen when someone else has already
									// opened the file without SWMR
									// i.e. high-level API access (e.g. its use in PersistentFileImpl)
									a = HDF5Constants.H5F_ACC_RDONLY;
									fid = H5.H5Fopen(cPath, a, fapl);
								}
							} else {
								int a = HDF5Constants.H5F_ACC_RDWR;
								fid = H5.H5Fopen(cPath, a, fapl);
							}
						} else if (!writeable) {
							logger.error("File {} does not exist!", cPath);
							throw new FileNotFoundException("File does not exist!");
						} else {
							if (verbose) {
								System.err.println("Creating " + cPath + " with latest " + withLatestVersion);
							}
							fid = H5.H5Fcreate(cPath, HDF5Constants.H5F_ACC_EXCL, HDF5Constants.H5P_DEFAULT, fapl);
						}
					}
				} finally {
					if (fapl != -1) {
						H5.H5Pclose(fapl);
					}
				}
				access = new HDF5File(cPath, fid, asNew || writeable, canSWMR);
				INSTANCE.map.put(cPath, access);
				return access;
			} catch (Throwable le) {
// FIXME for CustomTomoConverter, etc 
//				if (!IDS.containsKey(cPath)) {
//					HierarchicalDataFactory.releaseLowLevelReadingAccess(cPath);
//				}
				logger.error("Could not acquire access to file: {}", cPath, le);
				throw new ScanFileHolderException("Could not acquire access to file: " + cPath, le);
			}
		}
	}

	/**
	 * Acquire file
	 * @param fileName
	 * @param writeable
	 * @return file
	 * @throws ScanFileHolderException
	 */
	public static HDF5File acquireFile(String fileName, boolean writeable) throws ScanFileHolderException {
		return acquireFile(fileName, writeable, false, false);
	}

	/**
	 * Acquire file
	 * @param fileName
	 * @param writeable
	 * @param withLatestVersion if true, use latest object format version for writing
	 * @return file
	 * @throws ScanFileHolderException
	 */
	public static HDF5File acquireFile(String fileName, boolean writeable, boolean withLatestVersion) throws ScanFileHolderException {
		return acquireFile(fileName, writeable, false, withLatestVersion);
	}

	/**
	 * Acquire file as a newly created one. This deletes extant files
	 * @param fileName
	 * @return file
	 * @throws ScanFileHolderException
	 */
	public static HDF5File acquireFileAsNew(String fileName) throws ScanFileHolderException {
		return acquireFile(fileName, true, true, false);
	}

	/**
	 * Acquire file as a newly created one. This deletes extant files
	 * @param fileName
	 * @param withLatestVersion if true, use latest object format version for writing
	 * @return file
	 * @throws ScanFileHolderException
	 */
	public static HDF5File acquireFileAsNew(String fileName, boolean withLatestVersion) throws ScanFileHolderException {
		return acquireFile(fileName, true, true, withLatestVersion);
	}

	/**
	 * Delete file
	 * @param fileName
	 * @throws ScanFileHolderException
	 */
	public static void deleteFile(String fileName) throws ScanFileHolderException {
		final String cPath;
		try {
			cPath = canonicalisePath(fileName);
		} catch (IOException e) {
			logger.error("Problem canonicalising path", e);
			throw new ScanFileHolderException("Problem canonicalising path", e);
		}

		synchronized (INSTANCE.map) {
			if (INSTANCE.map.containsKey(cPath)) {
				try {
					HDF5File access = INSTANCE.map.get(cPath);
					if (access.getCount() <= 0) {
						try {
							access.finish(finishPeriod);
							if (verbose) {
								System.err.println("Closing and deleting " + cPath);
							}
							H5.H5Fclose(access.getID());
							INSTANCE.map.remove(cPath);
// FIXME for CustomTomoConverter, etc 
//							HierarchicalDataFactory.releaseLowLevelReadingAccess(cPath); 
						} catch (HDF5LibraryException e) {
							logger.error("Could not close file", e);
							throw e;
						}
					} else {
						if (verbose) {
							System.err.println("Cannot close as file being used " + cPath);
						}
						logger.error("File is currently being used: {}", cPath);
						throw new ScanFileHolderException("File is currently being used: " + cPath);
					}
				} catch (Throwable le) {
					logger.error("Problem releasing access to file: {}", cPath, le);
					throw new ScanFileHolderException("Problem releasing access to file: " + cPath, le);
				}
			}
		}

		File f = new File(cPath);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * Release file
	 * @param fileName
	 * @throws ScanFileHolderException
	 */
	public static void releaseFile(String fileName) throws ScanFileHolderException {
		releaseFile(fileName, false);
	}

	/**
	 * Release file associated with file name
	 * @param fileName
	 * @param close if true then close it too
	 * @throws ScanFileHolderException
	 */
	public static void releaseFile(String fileName, boolean close) throws ScanFileHolderException {
		final String cPath;
		try {
			cPath = canonicalisePath(fileName);
		} catch (IOException e) {
			logger.error("Problem canonicalising path", e);
			throw new ScanFileHolderException("Problem canonicalising path", e);
		}

		synchronized (INSTANCE.map) {
			if (!INSTANCE.map.containsKey(cPath)) {
				logger.debug("File not known - has it already been released?");
				return;
			}
		
			try {
				HDF5File access = INSTANCE.map.get(cPath);
				if (access.decrementCount() <= 0) {
					if (close) {
						try {
							if (verbose) {
								System.err.println("Finishing writes for " + cPath);
							}
							access.finish(access.getID());
							tryToCloseOpenObjects(access.getID());
							if (verbose) {
								System.err.println("Closing " + cPath);
							}
							H5.H5Fclose(access.getID());
							INSTANCE.map.remove(cPath);
// FIXME for CustomTomoConverter, etc 
//							HierarchicalDataFactory.releaseLowLevelReadingAccess(cPath); 
						} catch (HDF5LibraryException e) {
							logger.error("Could not close file", e);
							throw e;
						}
					} else {
						access.setTime(System.currentTimeMillis() + heldPeriod); // update release time
					}
				}
			} catch (Throwable le) {
				logger.error("Problem releasing access to file: {}", cPath, le);
				throw new ScanFileHolderException("Problem releasing access to file: " + cPath, le);
			}
		}
	}
	
	private static void tryToCloseOpenObjects(long fileId) throws ScanFileHolderException {
		try {
			//try datasets, datatypes and groups (things that can be closed with H5Oclose)
			int typeIdentifier = HDF5Constants.H5F_OBJ_DATASET |
					HDF5Constants.H5F_OBJ_DATATYPE |
					HDF5Constants.H5F_OBJ_GROUP |
					HDF5Constants.H5F_OBJ_LOCAL;
			int openObjectCount = (int) H5.H5Fget_obj_count(fileId, typeIdentifier);
			if (openObjectCount > 0) {
				logger.debug("Trying to close hdf5 file with open objects");
				long[] openIds = new long[openObjectCount];
				H5.H5Fget_obj_ids(fileId, typeIdentifier, openObjectCount, openIds);
				for (int i = 0; i < openObjectCount; i++) {
					long id = openIds[i];
					try {
						H5.H5Oclose(id);
						openIds[i] = -1;
					} catch (HDF5LibraryException e) {
						logger.error("Error closing hdf5 file - could not close open object");
					}
				}
			}
			//try attributes
			typeIdentifier = HDF5Constants.H5F_OBJ_ATTR | HDF5Constants.H5F_OBJ_LOCAL;
			openObjectCount = (int) H5.H5Fget_obj_count(fileId, typeIdentifier);
			if (openObjectCount > 0) {
				logger.debug("Trying to close hdf5 file with open attributes");
				long[] attrIds = new long[openObjectCount];
				H5.H5Fget_obj_ids(fileId, typeIdentifier, openObjectCount, attrIds);
				for (int i = 0; i < openObjectCount; i++) {
					long id = attrIds[i];
					try {
						H5.H5Aclose(id);
						attrIds[i] = -1;
					} catch (HDF5LibraryException e) {
						logger.error("Error closing hdf5 file - could not close open attribute");
					}
				}
			}
		} catch (HDF5LibraryException e) {
			throw new ScanFileHolderException("Could not query for open objects", e);
		}
	}


	/**
	 * Flush writes to file associated with file name
	 * @param fileName
	 * @throws ScanFileHolderException
	 */
	public static void flushWrites(String fileName) throws ScanFileHolderException {
		final String cPath;
		try {
			cPath = canonicalisePath(fileName);
		} catch (IOException e) {
			logger.error("Problem canonicalising path", e);
			throw new ScanFileHolderException("Problem canonicalising path", e);
		}

		HDF5File access = null;
		synchronized (INSTANCE.map) {
			if (!INSTANCE.map.containsKey(cPath)) {
//				logger.debug("File not known - has it already been released?");
				return;
			}
			access = INSTANCE.map.get(cPath);
		}

		synchronized(access) {
			try {
				if (verbose) {
					System.err.println("Flushing writes for " + cPath);
				}
				access.flushWrites();
				int status = H5.H5Fflush(access.getID(), HDF5Constants.H5F_SCOPE_GLOBAL);
				if (status < 0) {
					throw new HDF5LibraryException("H5Fflush returned an error value: " + status);
				}
			} catch (Throwable le) {
				logger.error("Problem flushing file: {}", cPath, le);
				throw new ScanFileHolderException("Problem flushing file: " + cPath, le);
			}
		}
	}
}
