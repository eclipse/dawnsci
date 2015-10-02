/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import ncsa.hdf.object.FileFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should be used to access HDF5 files from Java as long as the limitations like those
 * in HDF 2.7 are in place. This class gives a facade which is designed to ensure no more that
 * one file handle to a hdf5 file is active at one time - however multiple threads may access
 * the file. It is better than alternative ways of doing this as the level of synchronization is
 * lower. Use HierarchicalDataFactory as much as possible to avoid thread problems with HDF5.
 * 
 * 
 * <usage><code>
 * HierarchicalDataFile file = null;
 * try {
 *     file = HierarchicalDataFactory.getXXX();
 *    
 *     ... use file
 *     
 * } finally {
 *     file.close();
 * }
 * </code></usage>
 * 
 * @author Matthew Gerring
 *
 */
public class HierarchicalDataFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(HierarchicalDataFactory.class);
	
	/**
	 * Call this method to get a reference to a HierarchicalDataFile
	 * opened for reading use.
	 * 
	 * @param absolutePath
	 * @return
	 * @throws Exception
	 */
	public static IHierarchicalDataFile getReader(final String absolutePath) throws Exception {
		return  HierarchicalDataFactory.getReader(absolutePath, false);
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
	 * Get the reader, optionally waiting if a low level API call is blocking reading the file.
	 * @param path absolute path
	 * @param waitForLowLevel
	 * @return
	 * @throws Exception
	 */
	public static IHierarchicalDataFile getReader(final String path, boolean waitForLowLevel) throws Exception {
		String absolutePath = canonicalisePath(path);

		if (lowLevelLocks.containsKey(absolutePath)) {
			if (!waitForLowLevel) {
				throw new Exception("The low level API is currently reading from "+absolutePath);
			} else {
				final ReentrantLock lock = lowLevelLocks.get(absolutePath);
				if (lock!=null) {
					try {
					    lock.lock(); // waits
						return HierarchicalDataFile.open(absolutePath, FileFormat.READ);
					} finally {
						lock.unlock();
						lowLevelLocks.remove(absolutePath);
					}
				}
			}
		}
		return HierarchicalDataFile.open(absolutePath, FileFormat.READ);
	}
	
	/**
	 * Expert use only. This will close all writers and reader references for a give
	 * file path. Use with Caution because other references to the file may exist,
	 * which will go dead.
	 */
	public static void closeReaders(final String absolutePath) throws Exception { 
		HierarchicalDataFile.closeReaders(absolutePath);
	}
	
	
	/**
	 * Call this method to get a reference to a HierarchicalDataFile
	 * opened for writing use.
	 * 
	 * @param absolutePath
	 * @return
	 * @throws Exception
	 */
	public static IHierarchicalDataFile getWriter(final String absolutePath) throws Exception {
		return getWriter(absolutePath, false);
	}

	/**
	 * Call this method to get a reference to a HierarchicalDataFile
	 * opened for writing use.
	 * 
	 * @param path absolute path
	 * @param waitForAvailability if false and in use, exception thrown, if true will wait for lock in high level API. If a lock of the low level API is active, throws exception regardless.
	 * @return
	 * @throws Exception
	 */
	public static IHierarchicalDataFile getWriter(final String path, boolean waitForAvailability) throws Exception {
		String absolutePath = canonicalisePath(path);

		if (!(new File(absolutePath)).exists()) {
			create(absolutePath);
		}
		if (lowLevelLocks.containsKey(absolutePath)) throw new Exception("The low level API is currently reading from "+absolutePath);
		return HierarchicalDataFile.open(absolutePath, FileFormat.WRITE, waitForAvailability);
	}
	
	/**
	 * Call this method to get a reference to a *new* HierarchicalDataFile.
	 * 
	 * Do 
	 * 
	 * @param absolutePath
	 * @return
	 * @throws Exception
	 */
	public static void create(final String absolutePath) throws Exception {
		IHierarchicalDataFile file=null;
		try { // Try finally not really necessary but sets good example.
		    file = HierarchicalDataFile.open(absolutePath, FileFormat.CREATE);
		} finally {
		    if (file!=null) file.close();
		} 
	}


	public static boolean isHDF5(final String absolutePath) {
		if (HierarchicalDataFile.isWriting(absolutePath)) return true;
		if (HierarchicalDataFile.isReading(absolutePath)) return true;
		
		// We guess based on extension
		final String lowPath = absolutePath.toLowerCase();
		for (String ext : EXT) {
			if (lowPath.endsWith(ext)) return true;
		}
		return false;
	}
	
	private final static List<String> EXT;
	static {
		EXT = new ArrayList<String>(7);
		EXT.add(".h5");
		EXT.add(".nxs");
		EXT.add(".hd5");
		EXT.add(".hdf5");
		EXT.add(".hdf");
		EXT.add(".nexus");
	}	
	
	/**
	 * These locks can be used for requesting a lock for accessing a file path.
	 */
	private static Map<String, ReentrantLock> lowLevelLocks = new Hashtable<String, ReentrantLock>();

	private static ReentrantLock accessLock = new ReentrantLock();

	/**
	 * Ask to acquire a lock on a given file path. This call will block until
	 * other loaders have finished accessing the file.
	 * 
	 * This lock effectively forces the code asking for the lock into a single threaded mode.
	 * Only one thread may hold the lock at a time. This is useful when using the low level
	 * API which will not like multiple threads holding the same file handle and will very
	 * likely crash if this happens.
	 * 
	 * If the high level API holds a lock (its concept of locking is different and not single threaded) then 
	 * we will attempt to close this lock before getting a lock on the low level API.
	 * 
	 * Expert use only. acquireLowLevelReadingAccess and releaseLowLevelReadingAccess
	 * must be used in a try{} finally{} block.
	 * 
	 * @param path absolute path
	 */
	public static void acquireLowLevelReadingAccess(final String path) throws Exception {
		String absolutePath = canonicalisePath(path);

		accessLock.lock();
		ReentrantLock l;
		try {
			// If the high level has the lock, we attempt to close it
			// which hopefully should avoid a crash.
			if (HierarchicalDataFile.isReading(absolutePath)) {
				closeReaders(absolutePath);
			}

			l = lowLevelLocks.get(absolutePath);
			
			// Important to remove our low level lock at this point or deadlocks occur.
			if (HierarchicalDataFile.isReading(absolutePath)) {
				if (l!=null) try {
					l.unlock();
				} catch (Exception ignored) {
					
				}
				lowLevelLocks.remove(absolutePath);
				throw new Exception("The file path "+absolutePath+" is already being used by the high level API!");
			}
			
			logger.trace(String.format("Get lock for %s (thd %x)", absolutePath, Thread.currentThread().getId()));
			if (l == null) {
				l = new ReentrantLock();
				logger.trace(" Lock created for {}", absolutePath);
				lowLevelLocks.put(absolutePath, l);
			} else {
				logger.trace(String.format(" Lock exists for %s (%b)", absolutePath, l.isLocked()));
			}
		} finally {
			accessLock.unlock();
		}

		if (l.tryLock()) {
			logger.trace(String.format(" Lock free for %s (or held by current thd %x)", absolutePath, Thread.currentThread().getId()));
		} else {
			logger.trace("  Wait for held lock for {}", absolutePath);
			l.lock();
			logger.trace(String.format("  Hold lock for %s (thd %x)", absolutePath, Thread.currentThread().getId()));
		}
	}

	/**
	 * Ask to release a lock on a given file path. If this thread already 
	 * holds the lock and has a count greater than 1 the thread will keep the lock.
	 * 
	 * Expert use only. acquireLowLevelReadingAccess and releaseLowLevelReadingAccess
	 * must be used in a try{} finally{} block.
	 *
	 * @param path absolute path
	 */
	public static void releaseLowLevelReadingAccess(final String path) {
		String absolutePath;
		try {
			absolutePath = canonicalisePath(path);
		} catch (IOException e) {
			logger.error("Ignoring exception whilst releasing read lock", e);
			return;
		}
		
		accessLock.lock();
		try {
			ReentrantLock l = lowLevelLocks.get(absolutePath);
			if (l != null) {
				if (l.isHeldByCurrentThread()) {
					l.unlock();
					logger.trace(String.format("Release lock for %s (thd %x, %d)", absolutePath, Thread.currentThread().getId(), l.getHoldCount()));
				} else {
					logger.trace("Somehow the lock for {} was released (thd {})!", absolutePath, Thread.currentThread().getId());
				}
				if (!l.hasQueuedThreads()) {
					lowLevelLocks.remove(absolutePath);
				}
				l = null;
			}
		} finally {
			accessLock.unlock();
		}
	}
}
