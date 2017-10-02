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
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.january.dataset.DTypeUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;
import org.eclipse.january.dataset.SliceND;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hdf.hdf5lib.H5;
import hdf.hdf5lib.HDF5Constants;
import hdf.hdf5lib.exceptions.HDF5Exception;
import hdf.hdf5lib.exceptions.HDF5LibraryException;
import hdf.hdf5lib.structs.H5O_info_t;

public class HDF5Utils {
	private static final Logger logger = LoggerFactory.getLogger(HDF5Utils.class);

	private static String host;

	/**
	 * Gracefully gets the local host name (if there is a mis-configuration or any other issue, "localhost" is returned) 
	 * @return local host name
	 */
	public static String getLocalHostName() {
		if (host == null) {
			ExecutorService ex = Executors.newSingleThreadExecutor();
			Future<String> fu = ex.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					try { // this can block for a long while
						return InetAddress.getLocalHost().getHostName();
					} catch (UnknownHostException e) {
						logger.error("Could not find host name", e);
					}
					return null;
				}
			});
	
			try { // time-out after 5 seconds
				host = fu.get(5, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				logger.error("Timed out finding host name", e);
			} catch (InterruptedException e) {
				logger.error("Thread interrupted on finding host name", e);
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				logger.error("Task aborted on finding host name", e);
			}
			if (host == null) {
				host = "localhost";
			}
		}
		return host;
	}


	/**
	 * Create a dataset from the given data object
	 * @param data
	 * @param shape
	 * @param dtype
	 * @param extend true dataset for unsigned types 
	 * @return dataset
	 */
	public static Dataset createDataset(final Object data, final int[] shape, final int dtype,
			final boolean extend) {
		Dataset ds = DatasetFactory.createFromObject(dtype, data);
	
		if (extend) {
			ds = DatasetUtils.makeUnsigned(ds);
		}
		ds.setShape(shape);
		return ds;
	}

	/**
	 * Translate between data type and dataset type
	 * @param dclass data type class
	 * @param dsize data type element size in bytes
	 * @return dataset type
	 */
	public static int getDType(final int dclass, final int dsize) {
		if (dclass == HDF5Constants.H5T_STRING) {
			return Dataset.STRING;
		} else if (dclass == HDF5Constants.H5T_INTEGER) {
			switch (dsize) {
			case 1:
				return Dataset.INT8;
			case 2:
				return Dataset.INT16;
			case 4:
				return Dataset.INT32;
			case 8:
				return Dataset.INT64;
			}
		} else if (dclass == HDF5Constants.H5T_BITFIELD) {
			switch (dsize) {
			case 1:
				return Dataset.INT8;
			case 2:
				return Dataset.INT16;
			case 4:
				return Dataset.INT32;
			case 8:
				return Dataset.INT64;
			}
		} else if (dclass == HDF5Constants.H5T_FLOAT) {
			switch (dsize) {
			case 4:
				return Dataset.FLOAT32;
			case 8:
				return Dataset.FLOAT64;
			}
		}
		return -1;
	}

	/**
	 * Get HDF5 data type constants for boxed primitives
	 * @param clazz
	 * @return
	 */
	public static long getHDF5type(Class<?> clazz) {
		if (clazz.equals(String.class)) {
			return HDF5Constants.H5T_C_S1;
		} else if (clazz.equals(Boolean.class)) {
			return HDF5Constants.H5T_NATIVE_INT8;
		} else if (clazz.equals(Byte.class)) {
			return HDF5Constants.H5T_NATIVE_INT8;
		} else if (clazz.equals(Short.class)) {
			return HDF5Constants.H5T_NATIVE_INT16;
		} else if (clazz.equals(Integer.class)) {
			return HDF5Constants.H5T_NATIVE_INT32;
		} else if (clazz.equals(Long.class)) {
			return HDF5Constants.H5T_NATIVE_INT64;
		} else if (clazz.equals(Float.class)) {
			return HDF5Constants.H5T_NATIVE_FLOAT;
		} else if (clazz.equals(Double.class)) {
			return HDF5Constants.H5T_NATIVE_DOUBLE;
		}
		throw new IllegalArgumentException("Invalid datatype requested");
	}

	/**
	 * Get HDF5 data type constants for dataset types
	 * @param dtype
	 * @return
	 */
	public static long getHDF5type(int dtype) {
		switch (dtype) {
		case Dataset.STRING:
			return HDF5Constants.H5T_C_S1;
		case Dataset.BOOL:
		case Dataset.INT8:
		case Dataset.ARRAYINT8:
			return HDF5Constants.H5T_NATIVE_INT8;
		case Dataset.INT16:
		case Dataset.ARRAYINT16:
			return HDF5Constants.H5T_NATIVE_INT16;
		case Dataset.INT32:
		case Dataset.ARRAYINT32:
			return HDF5Constants.H5T_NATIVE_INT32;
		case Dataset.INT64:
		case Dataset.ARRAYINT64:
			return HDF5Constants.H5T_NATIVE_INT64;
		case Dataset.FLOAT32:
		case Dataset.ARRAYFLOAT32:
		case Dataset.COMPLEX64:
			return HDF5Constants.H5T_NATIVE_FLOAT;
		case Dataset.FLOAT64:
		case Dataset.ARRAYFLOAT64:
		case Dataset.COMPLEX128:
			return HDF5Constants.H5T_NATIVE_DOUBLE;
		default:
			throw new IllegalArgumentException("Invalid datatype requested");
		}
	}

	/**
	 * Load dataset from given file
	 * @param fileName
	 * @param node
	 * @param start
	 * @param count
	 * @param step
	 * @param dtype
	 * @param isize
	 * @param extend
	 * @return dataset
	 * @throws Exception
	 */
	public static Dataset loadDatasetWithClose(final String fileName, final String node,
				final int[] start, final int[] count, final int[] step,
				final int dtype, final int isize, final boolean extend)
				throws ScanFileHolderException {

		return loadDataset(fileName, node, start, count, step, dtype, isize, extend, true);
	}

	/**
	 * Load entire dataset from given file
	 * @param fileName
	 * @param node
	 * @return dataset
	 * @throws Exception
	 */
	public static Dataset loadDataset(final String fileName, final String node)
				throws ScanFileHolderException {

		try {
			HDF5File fid = HDF5FileFactory.acquireFile(fileName, false);

			return loadDataset(fid, node);
		} finally {
			HDF5FileFactory.releaseFile(fileName);
		}
	}

	/**
	 * Load entire dataset from given file
	 * @param fileName
	 * @param node
	 * @return dataset
	 * @throws Exception
	 */
	public static Dataset loadDataset(final HDF5File fid, final String node)
				throws ScanFileHolderException {

		Dataset data = null;
		try {
			int[][] shapes = readDatasetShape(fid, node);
			int[] shape = shapes[0];
			int[] start = new int[shape.length];
			int[] step = new int[shape.length];
			Arrays.fill(step, 1);
			data = readDataset(fid, node, start, shape, step, -1, -1, false);
		} catch (Throwable le) {
			logger.error("Problem loading dataset in file: {}", fid, le);
			throw new ScanFileHolderException("Problem loading file: " + fid, le);
		}

		return data;
	}

	/**
	 * Load dataset from given file
	 * @param fileName
	 * @param node
	 * @param start
	 * @param count
	 * @param step
	 * @param dtype
	 * @param isize
	 * @param extend
	 * @return dataset
	 * @throws Exception
	 */
	public static Dataset loadDataset(final String fileName, final String node,
				final int[] start, final int[] count, final int[] step,
				final int dtype, final int isize, final boolean extend)
				throws ScanFileHolderException {

		return loadDataset(fileName, node, start, count, step, dtype, isize, extend, false);
	}

	/**
	 * Load dataset from given file
	 * @param fileName
	 * @param node
	 * @param start
	 * @param count
	 * @param step
	 * @param dtype
	 * @param isize
	 * @param extend
	 * @return dataset
	 * @throws Exception
	 */
	private static Dataset loadDataset(final String fileName, final String node,
				final int[] start, final int[] count, final int[] step,
				final int dtype, final int isize, final boolean extend, final boolean close)
				throws ScanFileHolderException {

		Dataset data = null;
		try {
			HDF5File fid = HDF5FileFactory.acquireFile(fileName, false);

			data = readDataset(fid, node, start, count, step, dtype, isize, extend);
		} catch (Throwable le) {
			logger.error("Problem loading dataset {} in file: {}", node, fileName, le);
			throw new ScanFileHolderException("Problem loading file: " + fileName, le);
		} finally {
			HDF5FileFactory.releaseFile(fileName, close);
		}

		return data;
	}

	/**
	 * Get dataset shape information from given file
	 * @param fileName
	 * @param dataPath
	 * @return null for when there's no data; two empty arrays for a zero-rank dataset;
	 *  shape, max shape otherwise
	 * @throws ScanFileHolderException
	 */
	public static int[][] getDatasetShape(final String fileName, final String node)
			throws ScanFileHolderException {

		try {
			HDF5File fid = HDF5FileFactory.acquireFile(fileName, false);

			return readDatasetShape(fid, node);
		} catch (Throwable le) {
			logger.error("Problem loading dataset shape in file: {}", fileName, le);
			throw new ScanFileHolderException("Problem loading file: " + fileName, le);
		} finally {
			HDF5FileFactory.releaseFile(fileName);
		}
	}

	/**
	 * Read shape information from a dataset
	 * @param f
	 * @param dataPath
	 * @return null for when there's no data; two empty arrays for a zero-rank dataset;
	 *  shape, max shape otherwise
	 * @throws NexusException
	 */
	public static int[][] readDatasetShape(HDF5File f, String dataPath) throws NexusException {
		long hdfDatasetId = -1;
		try {
			try {
				hdfDatasetId = H5.H5Dopen(f.getID(), dataPath, HDF5Constants.H5P_DEFAULT);
	
				long hdfDataspaceId = -1;
				try {
					H5.H5Drefresh(hdfDatasetId);
					hdfDataspaceId = H5.H5Dget_space(hdfDatasetId);
					int type = H5.H5Sget_simple_extent_type(hdfDataspaceId);
					if (type == HDF5Constants.H5S_NULL) {
						return null;
					} else if (type == HDF5Constants.H5S_SCALAR) {
						return new int[][] {new int[0], new int[0]};
					}
					int rank = H5.H5Sget_simple_extent_ndims(hdfDataspaceId);
					long[] dims = new long[rank];
					long[] mdims = new long[rank];
					H5.H5Sget_simple_extent_dims(hdfDataspaceId, dims, mdims);
	
					int[] shape = new int[rank];
					int[] mshape = new int[rank];
					for (int i = 0; i < rank; i++) {
						shape[i] = (int) dims[i];
						mshape[i] = (int) mdims[i];
					}
					return new int[][] { shape, mshape}; 
				} finally {
					if (hdfDataspaceId != -1) {
						try {
							H5.H5Sclose(hdfDataspaceId);
						} catch (HDF5Exception ex) {
						}
					}
				}
			} finally {
				if (hdfDatasetId != -1) {
					try {
						H5.H5Dclose(hdfDatasetId);
					} catch (HDF5Exception ex) {
					}
				}
			}
		} catch (HDF5Exception e) {
			logger.error("Could not read dataset shape", e);
			throw new NexusException("Could not read dataset shape", e);
		}
	}

	/**
	 * Read dataset from given file ID
	 * @param f
	 * @param node
	 * @param start
	 * @param count
	 * @param step
	 * @param dtype (can be -1 for dataset type from file)
	 * @param isize (can be -1 for item size from file)
	 * @param extend
	 * @return dataset
	 * @throws NexusException
	 */
	public static Dataset readDataset(HDF5File f, final String node, final int[] start, final int[] count,
			final int[] step, final int dtype, final int isize, final boolean extend)
					throws NexusException {
		Dataset data = null;

		try {
			H5O_info_t info = H5.H5Oget_info_by_name(f.getID(), node, HDF5Constants.H5P_DEFAULT);
			int t = info.type;
			if (t != HDF5Constants.H5O_TYPE_DATASET) {
				logger.error("Node {} was not a dataset", node);
				return data;
			}
		} catch (HDF5Exception ex) {
			logger.error("Could not find info about object {}" + node);
			return data;
		}

		long did = -1;
		long tid = -1;
		long ntid = -1;
		try {
			did = H5.H5Dopen(f.getID(), node, HDF5Constants.H5P_DEFAULT);
			tid = H5.H5Dget_type(did);
			if (H5.H5Tequal(tid, HDF5Constants.H5T_STD_REF_OBJ)) {
				logger.error("Could not handle reference object data");
				throw new NexusException("Could not handle reference object data");
			}

			ntid = H5.H5Tget_native_type(tid);
			DatasetType type = getDatasetType(tid, ntid);
			long sid = -1;
			long msid = -1;
			int rank;

			// create a new scalar dataset
			try {
				sid = H5.H5Dget_space(did);
				rank = H5.H5Sget_simple_extent_ndims(sid);

				final long[] sstart = new long[rank]; // source start
				final long[] sstride = new long[rank]; // source steps
				final long[] dsize = new long[rank]; // destination size

				for (int i = 0; i < rank; i++) {
					sstart[i] = start[i];
					sstride[i] = step[i];
					dsize[i] = count[i];
				}

				if (rank == 0) {
					msid = H5.H5Screate(HDF5Constants.H5S_SCALAR);
				} else {
					H5.H5Sselect_hyperslab(sid, HDF5Constants.H5S_SELECT_SET, sstart, sstride, dsize, null);
					msid = H5.H5Screate_simple(rank, dsize, null);
					H5.H5Sselect_all(msid);
				}
				final int ldtype = dtype >= 0 ? dtype : type.dtype;
				final int lisize = isize >= 0 ? isize : type.isize;
				data = DatasetFactory.zeros(lisize, count, ldtype);
				Object odata = data.getBuffer();

				try {
					if (type.isVariableLength) {
						H5.H5Dread_VLStrings(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, (Object[]) odata);
					} else if (type.dtype == Dataset.STRING) {
						H5.H5Dread_string(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, (String[]) odata);
					} else {
						H5.H5Dread(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, odata);
					}
					if (extend) {
						data = DatasetUtils.makeUnsigned(data);
					}
				} catch (HDF5LibraryException e) {
					logger.error("Could not read data", e);
					throw new NexusException("Could not read data", e);
				}
			} catch (HDF5Exception ex) {
				logger.error("Could not get data space information", ex);
				throw new NexusException("Could not get data space information", ex);
			} finally {
				if (msid != -1) {
					try {
						H5.H5Sclose(msid);
					} catch (HDF5Exception ex2) {
					}
				}
				if (sid != -1) {
					try {
						H5.H5Sclose(sid);
					} catch (HDF5Exception ex2) {
					}
				}
			}
		} catch (HDF5Exception ex) {
			logger.error("Could not open dataset", ex);
			throw new NexusException("Could not open dataset", ex);
		} finally {
			if (ntid != -1) {
				try {
					H5.H5Tclose(ntid);
				} catch (HDF5Exception ex) {
				}
			}
			if (tid != -1) {
				try {
					H5.H5Tclose(tid);
				} catch (HDF5Exception ex) {
				}
			}
			if (did != -1) {
				try {
					H5.H5Dclose(did);
				} catch (HDF5Exception ex) {
				}
			}
		}

		return data;
	}

	/**
	 * @return the absolute path to data
	 */
	public static String absolutePathToData(String parentPath, String name) {
		if (parentPath == null || parentPath.isEmpty()) {
			parentPath = Tree.ROOT;
		} else if (!parentPath.startsWith(Tree.ROOT)) {
			parentPath = Tree.ROOT.concat(parentPath);
		}

		if (!parentPath.endsWith(Node.SEPARATOR)) {
			parentPath = parentPath.concat(Node.SEPARATOR);
		}
		return parentPath.concat(name);
	}

	/**
	 * Create a dataset in HDF5 file. Create the file if necessary
	 * @param fileName
	 * @param parentPath path to group containing dataset
	 * @param name name of dataset
	 * @param initialShape
	 * @param maxShape
	 * @param chunking
	 * @param dtype dataset type
	 * @param fill
	 * @param asUnsigned
	 * @throws ScanFileHolderException
	 */
	public static void createDataset(final String fileName, final String parentPath, final String name, final int[] initialShape, final int[] maxShape, final int[] chunking, final int dtype, final Object fill, final boolean asUnsigned) throws ScanFileHolderException {
		createDataset(fileName, parentPath, name, initialShape, maxShape, chunking, dtype, fill, asUnsigned, false);
	}

	/**
	 * Create a dataset in HDF5 file. Create the file if necessary
	 * @param fileName
	 * @param parentPath path to group containing dataset
	 * @param name name of dataset
	 * @param initialShape
	 * @param maxShape
	 * @param chunking
	 * @param dtype dataset type
	 * @param fill
	 * @param asUnsigned
	 * @param close
	 * @throws ScanFileHolderException
	 */
	private static void createDataset(final String fileName, final String parentPath, final String name, final int[] initialShape, final int[] maxShape, final int[] chunking, final int dtype, final Object fill, final boolean asUnsigned, final boolean close) throws ScanFileHolderException {

		try {
			HDF5File fid = HDF5FileFactory.acquireFile(fileName, true);

			requireDestination(fid, parentPath);
			String dataPath = absolutePathToData(parentPath, name);
			createDataset(fid, NexusFile.COMPRESSION_NONE, dataPath, dtype, initialShape, maxShape, chunking, fill);
		} catch (Throwable le) {
			logger.error("Problem creating dataset in file: {}", fileName, le);
			throw new ScanFileHolderException("Problem creating dataset in file: " + fileName, le);
		} finally {
			HDF5FileFactory.releaseFile(fileName, close);
		}
	}

	/**
	 * Create a lazy dataset in HDF5 file
	 * @param fileName
	 * @param parentPath
	 * @param name
	 * @param initialShape
	 * @param maxShape
	 * @param chunking
	 * @param dtype
	 * @param fill
	 * @param asUnsigned
	 * @return
	 */
	public static LazyWriteableDataset createLazyDataset(final String fileName, final String parentPath, final String name, final int[] initialShape, final int[] maxShape, final int[] chunking, final int dtype, final Object fill, final boolean asUnsigned) {
		HDF5LazySaver saver = new HDF5LazySaver(null, fileName,
				parentPath + Node.SEPARATOR + name, name, initialShape, 1, dtype, asUnsigned, maxShape, chunking, fill);
		saver.setCreateOnInitialization(true);
		LazyWriteableDataset lazy = new LazyWriteableDataset(name, dtype, initialShape, maxShape, chunking, saver);
		lazy.setFillValue(fill);
		return lazy;
	}

	/**
	 * Create a dataset in HDF5 file. Create the file if necessary
	 * @param fileName
	 * @param parentPath path to group containing dataset
	 * @param name name of dataset
	 * @param initialShape
	 * @param maxShape
	 * @param chunking
	 * @param dtype dataset type
	 * @param fill
	 * @param asUnsigned
	 * @throws ScanFileHolderException
	 */
	static void createDatasetWithClose(final String fileName, final String parentPath, final String name, final int[] initialShape, final int[] maxShape, final int[] chunking, final int dtype, final Object fill, final boolean asUnsigned) throws ScanFileHolderException {
		createDataset(fileName, parentPath, name, initialShape, maxShape, chunking, dtype, fill, asUnsigned, true);
	}

	private static void requireDestination(HDF5File fid, String group) throws HDF5Exception {
		boolean exists = false;
		long gid = -1;
		try {
			try {
				gid = H5.H5Gopen(fid.getID(), group, HDF5Constants.H5P_DEFAULT);
				exists = true;
			} catch (Exception e) {
			} finally {
				if (!exists) {
					gid = createDestination(fid.getID(), group);
				}
			}
		} finally {
			if (gid != -1) {
				try {
					H5.H5Gclose(gid);
				} catch (HDF5Exception ex) {
				}
			}
		}
	}

	private static long createDestination(long fileID, String group) throws HDF5Exception {
		long gcpid = -1;
		long gid = -1;

		try {
			gcpid = H5.H5Pcreate(HDF5Constants.H5P_LINK_CREATE);
			H5.H5Pset_create_intermediate_group(gcpid, true);
			gid = H5.H5Gcreate(fileID, group, gcpid, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);
		} catch (HDF5Exception e) {
			logger.error("Could not create destination", e);
			throw e;
		} finally {
			if (gcpid != -1) {
				try {
					H5.H5Pclose(gcpid);
				} catch (HDF5Exception ex) {
				}
			}
		}

		return gid;
	}

	/**
	 * Create a dataset in given file
	 * @param f
	 * @param compression
	 * @param dataPath
	 * @param dtype
	 * @param iShape
	 * @param iMaxShape
	 * @param iChunks
	 * @param fillValue
	 * @throws NexusException
	 */
	public static void createDataset(HDF5File f, int compression, String dataPath, int dtype, int[] iShape, int[] iMaxShape, int[] iChunks,
			Object fillValue) throws NexusException {
		long[] shape = toLongArray(iShape);
		long[] maxShape = toLongArray(iMaxShape);
		long[] chunks = toLongArray(iChunks);
		boolean stringDataset = dtype == Dataset.STRING;
		long hdfType = getHDF5type(dtype);
		try {
			long hdfDatatypeId = -1;
			long hdfDataspaceId = -1;
			long hdfPropertiesId = -1;
			try {
				hdfDatatypeId = H5.H5Tcopy(hdfType);
				hdfDataspaceId = H5.H5Screate_simple(shape.length, shape, maxShape);
				hdfPropertiesId = H5.H5Pcreate(HDF5Constants.H5P_DATASET_CREATE);

				if (stringDataset) {
					H5.H5Tset_cset(hdfDatatypeId, HDF5Constants.H5T_CSET_UTF8);
					H5.H5Tset_size(hdfDatatypeId, HDF5Constants.H5T_VARIABLE);
				} else if (fillValue != null) {
					// Strings must not have a fill value set
					H5.H5Pset_fill_value(hdfPropertiesId, hdfDatatypeId, fillValue);
				}
				if (chunks != null) {
					// these have to be set in this order
					H5.H5Pset_layout(hdfPropertiesId, HDF5Constants.H5D_CHUNKED);
					H5.H5Pset_chunk(hdfPropertiesId, chunks.length, chunks);
				}
				int deflateLevel = 0;
				switch (compression) {
				case NexusFile.COMPRESSION_LZW_L1:
					deflateLevel = 1;
					break;
				default:
					compression = NexusFile.COMPRESSION_NONE;
					break;
				}
				if (compression != NexusFile.COMPRESSION_NONE) {
					H5.H5Pset_deflate(hdfPropertiesId, deflateLevel);
				}
				long hdfDatasetId = -1;
				try {
					hdfDatasetId = H5.H5Dcreate(f.getID(), dataPath, hdfDatatypeId, hdfDataspaceId,
						HDF5Constants.H5P_DEFAULT, hdfPropertiesId, HDF5Constants.H5P_DEFAULT);
				} finally {
					if (hdfDatasetId != -1) {
						try {
							H5.H5Dclose(hdfDatasetId);
						} catch (HDF5Exception ex) {
						}
					}
				}
			} finally {
				if (hdfPropertiesId != -1) {
					try {
						H5.H5Pclose(hdfPropertiesId);
					} catch (HDF5Exception ex) {
					}
				}
				if (hdfDataspaceId != -1) {
					try {
						H5.H5Sclose(hdfDataspaceId);
					} catch (HDF5Exception ex) {
					}
				}
				if (hdfDatatypeId != -1) {
					try {
						H5.H5Tclose(hdfDatatypeId);
					} catch (HDF5Exception ex) {
					}
				}
			}
		} catch (HDF5Exception e) {
			logger.error("Could not create dataset", e);
			throw new NexusException("Could not create dataset", e);
		}
	}

	/**
	 * write a dataset in HDF5 file. Create the file if necessary
	 * @param fileName
	 * @param parentPath path to group containing dataset
	 * @param data
	 * @throws ScanFileHolderException 
	 */
	public static void writeDataset(String fileName, String parentPath, IDataset data) throws ScanFileHolderException {
		HDF5File fid = HDF5FileFactory.acquireFile(fileName, true);

		try {
			requireDestination(fid, parentPath);
			String dataPath = absolutePathToData(parentPath, data.getName());
			writeDataset(fid, dataPath, data);
		} catch (Throwable le) {
			throw new ScanFileHolderException("Problem loading file: " + fileName, le);
		} finally {
			HDF5FileFactory.releaseFile(fileName);
		}
	}

	/**
	 * Write a dataset in given file
	 * @param f
	 * @param dataPath
	 * @param data
	 * @throws NexusException
	 */
	public static void writeDataset(HDF5File f, String dataPath, IDataset data) throws NexusException {
		Dataset dataset = DatasetUtils.convertToDataset(data);

		long[] shape = toLongArray(dataset.getShapeRef());

		int dtype = dataset.getDType();
		boolean stringDataset = dtype == Dataset.STRING;
		long hdfType = getHDF5type(dtype);

		try {
			long hdfDatatypeId = -1;
			long hdfDataspaceId = -1;
			long hdfPropertiesId = -1;

			try {
				hdfDatatypeId = H5.H5Tcopy(hdfType);
				hdfDataspaceId = shape.length == 0 ? H5.H5Screate(HDF5Constants.H5S_SCALAR) : H5.H5Screate_simple(shape.length, shape, null);
				hdfPropertiesId = H5.H5Pcreate(HDF5Constants.H5P_DATASET_CREATE);

				if (stringDataset) {
					H5.H5Tset_cset(hdfDatatypeId, HDF5Constants.H5T_CSET_UTF8);
					H5.H5Tset_size(hdfDatatypeId, HDF5Constants.H5T_VARIABLE);
				}
				long hdfDatasetId = -1;
				try {
					hdfDatasetId = H5.H5Dcreate(f.getID(), dataPath, hdfDatatypeId, hdfDataspaceId,
						HDF5Constants.H5P_DEFAULT, hdfPropertiesId, HDF5Constants.H5P_DEFAULT);
					if (stringDataset) {
						String[] strings = (String[])DatasetUtils.serializeDataset(data);
						H5.H5Dwrite_VLStrings(hdfDatasetId, hdfDatatypeId, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, strings);
					} else {
						Serializable buffer = DatasetUtils.serializeDataset(data);
						H5.H5Dwrite(hdfDatasetId, hdfDatatypeId, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buffer);
					}
				} finally {
					if (hdfDatasetId != -1) {
						try {
							H5.H5Dclose(hdfDatasetId);
						} catch (HDF5Exception ex) {
						}
					}
				}
			} finally {
				if (hdfPropertiesId != -1) {
					try {
						H5.H5Pclose(hdfPropertiesId);
					} catch (HDF5Exception ex) {
					}
				}
				if (hdfDataspaceId != -1) {
					try {
						H5.H5Sclose(hdfDataspaceId);
					} catch (HDF5Exception ex) {
					}
				}
				if (hdfDatatypeId != -1) {
					try {
						H5.H5Tclose(hdfDatatypeId);
					} catch (HDF5Exception ex) {
					}
				}
			}
		} catch (HDF5Exception e) {
			logger.error("Could not write dataset", e);
			throw new NexusException("Could not write dataset", e);
		}
	}

	private static final Charset UTF8 = Charset.forName("UTF-8");

	/**
	 * Write attributes to a group or dataset in given file
	 * @param fileName
	 * @param path
	 * @param attributes
	 * @throws ScanFileHolderException 
	 */
	public static void writeAttributes(String fileName, String path, IDataset... attributes) throws ScanFileHolderException {
		HDF5File fid = HDF5FileFactory.acquireFile(fileName, true);

		try {
			writeAttributes(fid, path, attributes);
		} catch (Throwable le) {
			throw new ScanFileHolderException("Problem loading file: " + fileName, le);
		} finally {
			HDF5FileFactory.releaseFile(fileName);
		}
	}

	/**
	 * Write attributes to a group or dataset in given file
	 * @param f
	 * @param path
	 * @param attributes
	 * @throws NexusException
	 */
	public static void writeAttributes(HDF5File f, String path, IDataset... attributes) throws NexusException {
		for (IDataset attr : attributes) {
			String attrName = attr.getName();
			if (attrName == null || attrName.isEmpty()) {
				throw new NullPointerException("Attribute must have a name");
			}

			long fileID = f.getID();
			try {
				// if an attribute with the same name already exists, we delete it to be consistent with NAPI
				if (H5.H5Aexists_by_name(fileID, path, attrName, HDF5Constants.H5P_DEFAULT)) {
					try {
						H5.H5Adelete_by_name(fileID, path, attrName, HDF5Constants.H5P_DEFAULT);
					} catch (HDF5Exception e) {
						throw new NexusException("Could not delete existing attribute", e);
					}
				}
			} catch (HDF5Exception e) {
				throw new NexusException("Error inspecting existing attributes", e);
			}
			Dataset attrData = DatasetUtils.convertToDataset(attr);
			long baseHdf5Type = getHDF5type(attrData.getDType());

			final boolean isScalar = attrData.getRank() == 0;
			final long[] shape = toLongArray(attrData.getShapeRef());
			long datatypeID = -1;
			long dataspaceID = -1;
			try {
				datatypeID = H5.H5Tcopy(baseHdf5Type);
				dataspaceID = isScalar ? H5.H5Screate(HDF5Constants.H5S_SCALAR) : H5.H5Screate_simple(shape.length, shape, shape);
				boolean stringDataset = attrData.getDType() == Dataset.STRING;
				Serializable buffer = DatasetUtils.serializeDataset(attrData);
				if (stringDataset) {
					String[] strings = (String[]) buffer;
					int strCount = strings.length;
					int maxLength = 0;
					byte[][] stringbuffers = new byte[strCount][];
					int i = 0;
					for (String str : strings) {
						stringbuffers[i] = str.getBytes(UTF8);
						int l = stringbuffers[i].length;
						if (l > maxLength) maxLength = l;
						i++;
					}
					maxLength++; //we require null terminators
					buffer = new byte[maxLength * strCount];
					int offset = 0;
					for (byte[] str: stringbuffers) {
						System.arraycopy(str, 0, buffer, offset, str.length);
						offset += maxLength;
					}

					H5.H5Tset_cset(datatypeID, HDF5Constants.H5T_CSET_ASCII);
					H5.H5Tset_size(datatypeID, maxLength);
				}
				long attrID = -1;
				try {
					attrID = H5.H5Acreate_by_name(fileID, path, attrName, datatypeID, dataspaceID,
								HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);

					H5.H5Awrite(attrID, datatypeID, buffer);
				} catch (HDF5Exception e) {
					throw new NexusException("Could not create attribute", e);
				} finally {
					if (attrID != -1) {
						try {
							H5.H5Aclose(attrID);
						} catch (HDF5Exception e) {
						}
					}
				}
			} catch (HDF5Exception e) {
				throw new NexusException("Could not make data type or space", e);
			} finally {
				if (dataspaceID != -1) {
					try {
						H5.H5Sclose(dataspaceID);
					} catch (HDF5Exception e) {
					}
				}
				if (datatypeID != -1) {
					try {
						H5.H5Tclose(datatypeID);
					} catch (HDF5Exception e) {
					}
				}
			}
		}
	}

	/**
	 * Set slice of dataset in HDF5 file. Create file if necessary
	 * @param fileName
	 * @param parentPath
	 * @param name
	 * @param slice
	 * @param value
	 * @throws ScanFileHolderException
	 */
	static void setDatasetSliceWithClose(final String fileName, final String parentPath, final String name, final SliceND slice, final IDataset value) throws ScanFileHolderException {
		setDatasetSlice(fileName, parentPath, name, slice, value, true, false);
	}

	/**
	 * Set slice of dataset in HDF5 file. Create file if necessary
	 * @param fileName
	 * @param parentPath
	 * @param name
	 * @param slice
	 * @param value
	 * @throws ScanFileHolderException
	 */
	public static void setDatasetSlice(final String fileName, final String parentPath, final String name, final SliceND slice, final IDataset value) throws ScanFileHolderException {
		setDatasetSlice(fileName, parentPath, name, slice, value, false, false);
	}

	/**
	 * Set slice of dataset in HDF5 file. Create file if necessary
	 * @param fileName
	 * @param parentPath
	 * @param name
	 * @param slice
	 * @param value
	 * @throws ScanFileHolderException
	 */
	public static void setExistingDatasetSlice(final String fileName, final String parentPath, final String name, final SliceND slice, final IDataset value) throws ScanFileHolderException {
		setDatasetSlice(fileName, parentPath, name, slice, value, false, true);
	}

	/**
	 * Set slice of dataset in HDF5 file. Create file if necessary
	 * @param fileName
	 * @param parentPath
	 * @param name
	 * @param slice
	 * @param value
	 * @param close
	 * @param exists 
	 * @throws ScanFileHolderException
	 */
	private static void setDatasetSlice(final String fileName, final String parentPath, final String name, final SliceND slice, final IDataset value, final boolean close, boolean exists) throws ScanFileHolderException {
		try {
			if (!exists) {
				prepareFile(fileName, parentPath, name, slice, value);
			}
			HDF5File fid = HDF5FileFactory.acquireFile(fileName, true);

			String dataPath = absolutePathToData(parentPath, name);
			writeDatasetSlice(fid, dataPath, slice, value);
		} catch (Throwable le) {
			logger.error("Problem setting slice of dataset in file: {}", fileName, le);
			throw new ScanFileHolderException("Problem setting slice of dataset in file: " + fileName, le);
		} finally {
			HDF5FileFactory.releaseFile(fileName, close);
		}
	}

	private static void prepareFile(final String fileName, final String parentPath, final String name, final SliceND slice, final IDataset value) throws Exception {
		if (!new File(fileName).exists()) {
			int[] mshape = slice.getMaxShape();
			if (mshape == null) {
				mshape = slice.getShape();
			}
			createDataset(fileName, parentPath, name, slice.getStart(), mshape, slice.getShape(),
					DTypeUtils.getDType(value), null, false);
		}
	}

	/**
	 * 
	 * @param f
	 * @param dataPath
	 * @return
	 * @throws NexusException
	 */
	public static long[] openDataset(HDF5File f, String dataPath) throws NexusException {
		long hdfDatasetId = -1;
		long hdfDataspaceId = -1;
		try {
			hdfDatasetId = H5.H5Dopen(f.getID(), dataPath, HDF5Constants.H5P_DEFAULT);

			hdfDataspaceId = H5.H5Dget_space(hdfDatasetId);
		} catch (HDF5Exception e) {
			logger.error("Could not open dataset", e);
			throw new NexusException("Could not open dataset", e);
		}

		return new long[] {hdfDatasetId, hdfDataspaceId};
	}

	/**
	 * 
	 * @param ids
	 * @throws NexusException
	 */
	public static void flushDataset(long[] ids) throws NexusException {
		long id = ids[0];
		if (id != -1) {
			try {
				H5.H5Dflush(id);
			} catch (HDF5Exception ex) {
				logger.error("Could not flush data", ex);
				throw new NexusException("Could not flush data", ex);
			}
		}
	}

	/**
	 * 
	 * @param ids
	 * @throws NexusException
	 */
	public static void closeDataset(long[] ids) throws NexusException {
		long id = ids[1];
		if (id != -1) {
			ids[1] = -1;
			try {
				H5.H5Sclose(id);
			} catch (HDF5Exception ex) {
				logger.error("Could not close file space", ex);
			}
		}
		try {
			flushDataset(ids);
		} finally {
			id = ids[0];
			if (id != -1) {
				ids[0] = -1;
				try {
					H5.H5Dclose(id);
				} catch (HDF5Exception ex) {
					logger.error("Could not close data", ex);
					throw new NexusException("Could not close data", ex);
				}
			}
		}
	}

	/**
	 * Write to a slice in a dataset
	 * @param f
	 * @param dataPath
	 * @param slice
	 * @param value
	 * @throws NexusException
	 */
	public static void writeDatasetSlice(HDF5File f, String dataPath, SliceND slice, IDataset value) throws NexusException {
		long[] ids = null;
		try {
			ids = f.openDataset(dataPath);
			long hdfDatasetId = ids[0];
			long hdfDataspaceId = ids[1];

			long hdfMemspaceId = -1;
			long hdfDatatypeId = -1;
			try {
				int rank = H5.H5Sget_simple_extent_ndims(hdfDataspaceId);
				long[] dims = new long[rank];
				long[] mdims = new long[rank];
				H5.H5Sget_simple_extent_dims(hdfDataspaceId, dims, mdims);
				long[] start = toLongArray(slice.getStart());
				long[] stride = toLongArray(slice.getStep());
				long[] shape = toLongArray(slice.getShape());

				long[] newShape = null;
				if (slice.isExpanded()) {
					newShape = toLongArray(slice.getSourceShape());
				} else {
					long[] mShape = toLongArray(slice.getStop());
					if (expandToGreatestShape(mShape, dims)) {
						newShape = mShape;
					}
				}
				if (newShape != null) {
					H5.H5Dset_extent(hdfDatasetId, newShape);
					try {
						H5.H5Sclose(hdfDataspaceId);
					} catch (HDF5Exception ex) {
					}
					hdfDataspaceId = H5.H5Screate_simple(rank, newShape, mdims);
					ids[1] = hdfDataspaceId;
				}
				if (rank != 0) { // not a scalar (or null) dataspace
					H5.H5Sselect_hyperslab(hdfDataspaceId, HDF5Constants.H5S_SELECT_SET, start, stride, shape, null);
				}

				Dataset data = DatasetUtils.convertToDataset(value);
				int dtype = data.getDType();
				long memtype = getHDF5type(dtype);
				Serializable buffer = DatasetUtils.serializeDataset(data);

				// convert boolean[] data to byte[]
				// the HDF5 library cannot handle boolean[]
				if (data.getElementClass().equals(Boolean.class)) {
					boolean[] original = (boolean[]) buffer;
					int length = original.length;
					byte[] converted = new byte[length];
					for (int i = 0; i < length; i++) {
						converted[i] = (byte) (original[i] ? 1 : 0);
					}
					buffer = converted;
				}

				hdfMemspaceId = H5.H5Screate_simple(rank, HDF5Utils.toLongArray(data.getShape()), null);
				if (dtype == Dataset.STRING) {
					boolean vlenString = false;
					hdfDatatypeId = H5.H5Dget_type(hdfDatasetId);
					int typeSize = -1;
					try {
						typeSize = (int) H5.H5Tget_size(hdfDatatypeId);
						vlenString = H5.H5Tis_variable_str(hdfDatatypeId);
					} finally {
						H5.H5Tclose(hdfDatatypeId);
						hdfDatatypeId = -1;
					}
					hdfDatatypeId = H5.H5Tcopy(memtype);
					H5.H5Tset_cset(hdfDatatypeId, HDF5Constants.H5T_CSET_UTF8);
					H5.H5Tset_size(hdfDatatypeId, vlenString ? HDF5Constants.H5T_VARIABLE : typeSize);
					if (vlenString) {
						H5.H5Dwrite_VLStrings(hdfDatasetId, hdfDatatypeId, hdfMemspaceId, hdfDataspaceId, HDF5Constants.H5P_DEFAULT, (String[]) buffer);
					} else {
						String[] strings = (String[]) buffer;
						byte[] strBuffer = new byte[typeSize * strings.length];
						int idx = 0;
						for (String str : (String[]) strings) {
							//typesize - 1 since we always want to leave room for \0 at the end of the string
							if (str.length() > typeSize - 1) {
								logger.warn("String does not fit into space allocated in HDF5 file in " + dataPath + " - string will be truncated");
							}
							byte[] src = str.getBytes(UTF8);
							int length = Math.min(typeSize - 1, src.length);
							System.arraycopy(src, 0, strBuffer, idx, length);
							idx += typeSize;
						}
						H5.H5Dwrite(hdfDatasetId, hdfDatatypeId, hdfMemspaceId, hdfDataspaceId, HDF5Constants.H5P_DEFAULT, strBuffer);
					}
				} else {
					H5.H5Dwrite(hdfDatasetId, memtype, hdfMemspaceId, hdfDataspaceId, HDF5Constants.H5P_DEFAULT, buffer);
				}
			} finally {
				if (hdfDatatypeId != -1) {
					try {
						H5.H5Tclose(hdfDatatypeId);
					} catch (HDF5Exception ex) {
						logger.error("Could not close datatype", ex);
						throw ex;
					}
				}
				if (hdfMemspaceId != -1) {
					try {
						H5.H5Sclose(hdfMemspaceId);
					} catch (HDF5Exception ex) {
						logger.error("Could not close memory space", ex);
						throw ex;
					}
				}
			}
		} catch (HDF5Exception e) {
			logger.error("Could not write dataset slice", e);
			throw new NexusException("Could not write dataset slice", e);
		} finally {
			if (!f.containsDataset(dataPath)) {
				closeDataset(ids);
			}
		}
	}

	private static boolean expandToGreatestShape(long[] a, long[] b) {
		int rank = a.length;
		boolean isExpanded = false;
		for (int i = 0; i < rank; i++) {
			if (a[i] > b[i]) {
				isExpanded = true;
			} else { // ensure shape is maximal
				a[i] = b[i];
			}
		}

		return isExpanded;
	}

	/**
	 * Wrapper to fix super block status flag issue
	 * @param filePath
	 * @param flags
	 * @param fapl
	 * @return file ID
	 * @throws HDF5LibraryException
	 * @throws NullPointerException
	 * @deprecated Use {@link H5#H5Fopen(String,int,long)} directly
	 */
	public static long H5Fopen(String filePath, int flags, long fapl) throws HDF5LibraryException, NullPointerException {
		return H5.H5Fopen(filePath, flags, fapl);
	}

	private static final Map<Long, Integer> HDF_TYPES_TO_DATASET_TYPES;
	private static final Map<Integer, Long> DATASET_TYPES_TO_HDF_TYPES;
	private static final Map<Long, Long> ENUM_SIZE_TO_HDF_TYPES;
	private static final Set<Long> UNSIGNED_HDF_TYPES;

	private static long getTypeRepresentation(long nativeHdfTypeId) throws NexusException {
		try {
			for (long type : HDF_TYPES_TO_DATASET_TYPES.keySet()) {
				if (H5.H5Tequal(nativeHdfTypeId, type)) {
					return type;
				}
			}
		} catch (HDF5LibraryException e) {
			throw new NexusException("Could not compare types", e);
		}

		return -1;
	}

	static {
		HDF_TYPES_TO_DATASET_TYPES = new HashMap<Long, Integer>();
		DATASET_TYPES_TO_HDF_TYPES = new HashMap<Integer, Long>();
		ENUM_SIZE_TO_HDF_TYPES = new HashMap<Long, Long>();
		UNSIGNED_HDF_TYPES = new HashSet<Long>();

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_INT8, Dataset.INT8);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.INT8, HDF5Constants.H5T_NATIVE_INT8);
		ENUM_SIZE_TO_HDF_TYPES.put(1l, HDF5Constants.H5T_NATIVE_INT8);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_INT16, Dataset.INT16);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.INT16, HDF5Constants.H5T_NATIVE_INT16);
		ENUM_SIZE_TO_HDF_TYPES.put(2l, HDF5Constants.H5T_NATIVE_INT16);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_INT32, Dataset.INT32);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.INT32, HDF5Constants.H5T_NATIVE_INT32);
		ENUM_SIZE_TO_HDF_TYPES.put(4l, HDF5Constants.H5T_NATIVE_INT32);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B8, Dataset.INT8);
		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B16, Dataset.INT16);
		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B32, Dataset.INT32);
		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B64, Dataset.INT8);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_INT64, Dataset.INT64);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.INT64, HDF5Constants.H5T_NATIVE_INT64);
		ENUM_SIZE_TO_HDF_TYPES.put(8l, HDF5Constants.H5T_NATIVE_INT64);


		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_UINT8, Dataset.INT8);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_UINT8);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_UINT16, Dataset.INT16);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_UINT16);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_UINT32, Dataset.INT32);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_UINT32);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_UINT64, Dataset.INT64);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_UINT64);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_FLOAT, Dataset.FLOAT32);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.FLOAT32, HDF5Constants.H5T_NATIVE_FLOAT);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_DOUBLE, Dataset.FLOAT64);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.FLOAT64, HDF5Constants.H5T_NATIVE_DOUBLE);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_C_S1, Dataset.STRING);
		DATASET_TYPES_TO_HDF_TYPES.put(Dataset.STRING, HDF5Constants.H5T_C_S1);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B8, Dataset.INT8);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_B8);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B16, Dataset.INT16);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_B16);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B32, Dataset.INT32);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_B32);

		HDF_TYPES_TO_DATASET_TYPES.put(HDF5Constants.H5T_NATIVE_B64, Dataset.INT64);
		UNSIGNED_HDF_TYPES.add(HDF5Constants.H5T_NATIVE_B64);
	}

	public static class DatasetType {
		public int dtype = -1; // dataset type number 
		public int isize = 1; // number of elements per item
		public long size; // size of string in bytes
		public int bits = -1; // max number of bits for bit-fields (-1 for other types)
		public int nEnum; // number of enumerations
		public String name;
		public boolean isVariableLength; // is variable length
		public boolean isComplex = false;
		public boolean unsigned; // is unsigned
	}

	public static Dataset[] readAttributes(long oid) throws NexusException {
		return readAttributes(oid, HERE);
	}

	private static final String HERE = ".";

	public static Dataset[] readAttributes(long oid, String path) throws NexusException {
	    H5O_info_t info = null;
	    try {
	        info = H5.H5Oget_info(oid);
	    } catch (HDF5LibraryException e) {
	    	logger.error("Could not get info from object", e);
	    	throw new NexusException("Could not get info from object", e);
	    }
	
	    int n = (int) info.num_attrs;
	    if (n <= 0) {
	        return new Dataset[0];
	    }

	    Dataset[] attrs = new Dataset[n];
	    for (int i = 0; i < n; i++) {
	    	attrs[i] = getAttrDataset(oid, path, i);
	    }
	
		return attrs;
	}

	/**
	 * 
	 * @param tid
	 * @return
	 * @throws HDF5LibraryException
	 * @throws NexusException 
	 */
	public static DatasetType findClassesInComposite(long tid) throws HDF5LibraryException, NexusException {
		List<String> names = new ArrayList<String>();
		List<Integer> classes = new ArrayList<Integer>();
		List<Integer> dtypes = new ArrayList<Integer>();
		List<Integer> widths = new ArrayList<Integer>();
		List<Boolean> signs = new ArrayList<Boolean>();
		flattenCompositeDatasetType(tid, "", names, classes, dtypes, widths, signs);
		DatasetType comp = new DatasetType();
		comp.isize = classes.size();
		if (comp.isize > 0) {
			int tclass = classes.get(0);
			for (int i = 1; i < comp.isize; i++) {
				if (tclass != classes.get(i)) {
					logger.error("Could not load inhomogeneous compound datatype");
					return null;
				}
			}
			comp.dtype = dtypes.get(0);
			if (comp.isize == 2 && tclass == HDF5Constants.H5T_FLOAT) {
				if (getLastComponent(names.get(0)).toLowerCase().startsWith("r") && getLastComponent(names.get(1)).toLowerCase().startsWith("i")) {
					comp.isComplex = true;
					comp.dtype = comp.dtype == Dataset.FLOAT32 ? Dataset.COMPLEX64 : Dataset.COMPLEX128;
				}
			}
			if (!comp.isComplex) {
				comp.dtype *= Dataset.ARRAYMUL;
			}

			StringBuilder name = new StringBuilder(comp.isComplex ? "Complex = {" : "Composite of {");
			for (int i = 0; i < comp.isize; i++) {
				name.append(names.get(i));
				name.append(constructType(classes.get(i), widths.get(i), signs.get(i)));
				name.append(", ");
			}
			name.delete(name.length() - 2, name.length());
			name.append("}");
			comp.name = name.toString();
			Collections.sort(widths);
			comp.bits = widths.get(widths.size() - 1);
		}
		return comp;
	}

	private static final String COLON = ":";

	private static String getLastComponent(String n) {
		String[] bits = n.split(COLON);
		int l = bits.length - 1;
		while (bits[l].trim().length() == 0) {
			l--;
		}
		return bits[l];
	}

	private static String constructType(int c, int w, boolean s) {
		StringBuilder n = new StringBuilder(":");
		if (!s) {
			n.append("U");
		}
		if (c == HDF5Constants.H5T_BITFIELD) {
			n.append("INT");
			n.append(w);
		} else if (c == HDF5Constants.H5T_INTEGER) {
			n.append("INT");
			n.append(-w*8);
		} else if (c == HDF5Constants.H5T_FLOAT) {
			n.append("FLOAT");
			n.append(-w*8);
		}
		return n.toString();
	}

	/**
	 * This flattens compound and array
	 * @param tid
	 * @param prefix
	 * @param names
	 * @param classes
	 * @param dtypes
	 * @param widths bits (positive) or bytes (negative)
	 * @param signs
	 * @throws HDF5LibraryException
	 * @throws NexusException 
	 */
	private static void flattenCompositeDatasetType(long tid, String prefix, List<String> names, List<Integer> classes, List<Integer> dtypes, List<Integer> widths, List<Boolean> signs) throws HDF5LibraryException, NexusException {
		int tclass = H5.H5Tget_class(tid);
		if (tclass == HDF5Constants.H5T_ARRAY) {
			long btid = -1;
			try {
				btid = H5.H5Tget_super(tid);
				tclass = H5.H5Tget_class(btid);
				// deal with array of composite
				if (tclass == HDF5Constants.H5T_COMPOUND || tclass == HDF5Constants.H5T_ARRAY) {
					flattenCompositeDatasetType(btid, prefix, names, classes, dtypes, widths, signs);
					return;
				}
				int r = H5.H5Tget_array_ndims(tid);
				long[] shape = new long[r];
				H5.H5Tget_array_dims(tid, shape);
				long size = calcLongSize(shape);
				for (long i = 0; i < size; i++) {
					names.add(prefix + i);
					classes.add(tclass);
				}
			} catch (HDF5Exception ex) {
			} finally {
				if (btid != -1) {
					try {
						H5.H5Tclose(btid);
					} catch (HDF5LibraryException e) {
					}
				}
			}
		} else {
			int n = H5.H5Tget_nmembers(tid);
			if (n <= 0)
				return;
	
			int mclass = 0;
			long tmptid = -1;
			for (int i = 0; i < n; i++) {
				long mtype = -1;
				try {
					mtype = H5.H5Tget_member_type(tid, i);
	
					try {
						tmptid = mtype;
						mtype = H5.H5Tget_native_type(tmptid);
					} catch (HDF5Exception ex) {
						continue;
					} finally {
						if (tmptid != -1) {
							try {
								H5.H5Tclose(tmptid);
							} catch (HDF5LibraryException e) {
							}
						}
					}
		
					try {
						mclass = H5.H5Tget_class(mtype);
					} catch (HDF5Exception ex) {
						continue;
					}
		
					
					String mname = prefix;
					if (prefix.length() > 0) {
						mname += COLON;
					}
					mname += H5.H5Tget_member_name(tid, i);
					if (mclass == HDF5Constants.H5T_COMPOUND || mclass == HDF5Constants.H5T_ARRAY) {
						// deal with composite
						flattenCompositeDatasetType(mtype, mname, names, classes, dtypes, widths, signs);
					} else if (mclass == HDF5Constants.H5T_VLEN) {
						continue;
					} else {
						names.add(mname);
						classes.add(mclass);
						if (mclass == HDF5Constants.H5T_BITFIELD) {
							int p = -1;
							try {
								p = H5.H5Tget_precision(mtype);
							} catch (HDF5Exception ex) {
								continue;
							} finally {
								widths.add(p);
							}
							signs.add(false);
						} else {
							int w = 1;
							try {
								w = (int) H5.H5Tget_size(mtype);
							} catch (HDF5Exception ex) {
								continue;
							} finally {
								widths.add(-w);
							}
							if (mclass == HDF5Constants.H5T_INTEGER) {
								boolean s = true;
								try {
									s = H5.H5Tget_sign(mtype) == HDF5Constants.H5T_SGN_2;
								} catch (HDF5Exception ex) {
									continue;
								} finally {
									signs.add(s);
								}
							} else {
								signs.add(true);
							}
						}
						dtypes.add(HDF_TYPES_TO_DATASET_TYPES.get(getTypeRepresentation(mtype)));
					}
				} finally {
					if (mtype != -1) {
						try {
							H5.H5Tclose(mtype);
						} catch (HDF5LibraryException e) {
						}
					}
				}
			}
		}
	}

	public static DatasetType getDatasetType(long typeId, long nativeTypeId) throws NexusException, HDF5LibraryException {
		DatasetType type = new DatasetType();
		int tclass = H5.H5Tget_class(nativeTypeId);

		if (tclass == HDF5Constants.H5T_ARRAY || tclass == HDF5Constants.H5T_COMPOUND) {
			type = findClassesInComposite(typeId);
		} else {
			type.size = H5.H5Tget_size(nativeTypeId);
	
			long typeRepresentation;
			if (tclass == HDF5Constants.H5T_STRING) {
				type.isVariableLength = H5.H5Tis_variable_str(nativeTypeId);
				typeRepresentation = HDF5Constants.H5T_C_S1;
			} else if (tclass == HDF5Constants.H5T_ENUM) {
				// TODO maybe convert to string dataset eventually
				type.nEnum = H5.H5Tget_nmembers(nativeTypeId);
				typeRepresentation = ENUM_SIZE_TO_HDF_TYPES.get(type.size);
			} else {
				type.isVariableLength = tclass == HDF5Constants.H5T_VLEN;
				typeRepresentation = getTypeRepresentation(nativeTypeId);
			}
			type.dtype = HDF_TYPES_TO_DATASET_TYPES.get(typeRepresentation);
			type.unsigned = UNSIGNED_HDF_TYPES.contains(typeRepresentation);
			type.name = DTypeUtils.getDTypeName(type.dtype, type.isize);
			if (type.unsigned) {
				type.name = "U" + type.name;
			}
		}
//		isRegRef = H5.H5Tequal(tid, HDF5Constants.H5T_STD_REF_DSETREG);
		return type;
	}

	public static Dataset getAttrDataset(long locId, String path, long i) throws NexusException {
		Dataset dataset = null;
		String name = null;
		try {
			try (HDF5Resource attrResource = new HDF5AttributeResource(
					H5.H5Aopen_by_idx(locId, path, HDF5Constants.H5_INDEX_NAME, HDF5Constants.H5_ITER_INC, i,
							HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT))) {
				long[] shape = null;
				long[] maxShape = null;
				long attrId = attrResource.getResource();
				name = H5.H5Aget_name(attrId);
				try (HDF5Resource spaceResource = new HDF5DataspaceResource(H5.H5Aget_space(attrId));
						HDF5Resource typeResource = new HDF5DatatypeResource(H5.H5Aget_type(attrId));
						HDF5Resource nativeTypeResource = new HDF5DatatypeResource(H5.H5Tget_native_type(typeResource.getResource()))) {
					final long spaceId = spaceResource.getResource();
					final long nativeTypeId = nativeTypeResource.getResource();
					DatasetType type = getDatasetType(typeResource.getResource(), nativeTypeId);
					if (type == null) {
						throw new NexusException("Unknown data type");
					}
					final int nDims = H5.H5Sget_simple_extent_ndims(spaceId);
					shape = new long[nDims];
					maxShape = new long[nDims];
					H5.H5Sget_simple_extent_dims(spaceId, shape, maxShape);
					final int[] iShape = toIntArray(shape);
					int strCount = 1;
					for (int d : iShape) {
						strCount *= d;
					}
					if (type.dtype == Dataset.STRING) {
						if (type.isVariableLength) {
							String[] buffer = new String[strCount];
							H5.H5AreadVL(attrId, nativeTypeId, buffer);
							dataset = DatasetFactory.createFromObject(buffer).reshape(iShape);
						} else {
							byte[] buffer = new byte[(int) (strCount * type.size)];
							H5.H5Aread(attrId, nativeTypeId, buffer);
							String[] strings = new String[strCount];
							int strIndex = 0;
							for (int j = 0; j < buffer.length; j += type.size) {
								int strLength = 0;
								//Java doesn't strip null bytes during string construction
								for (int k = j; k < j + type.size && buffer[k] != '\0'; k++) strLength++;
								strings[strIndex++] = new String(buffer, j, strLength, UTF8);
							}
							dataset = DatasetFactory.createFromObject(strings).reshape(iShape);
						}
					} else {
						dataset = DatasetFactory.zeros(iShape, type.dtype);
						Serializable buffer = dataset.getBuffer();
						H5.H5Aread(attrId, nativeTypeId, buffer);
					}
					dataset.setName(name);
				}
			}
		} catch (HDF5Exception e) {
			logger.error("Could not retrieve attribute: {} in {}", name, path, e);
			throw new NexusException("Could not retrieve attribute: " + name + " in " + path, e);
		}
		return dataset;
	}

	public static long calcLongSize(final long[] shape) {
		double dsize = 1.0;
		for (int i = 0; i < shape.length; i++) {
			// make sure the indexes isn't zero or negative
			if (shape[i] == 0) {
				return 0;
			} else if (shape[i] < 0) {
				throw new IllegalArgumentException(String.format(
						"The %d-th is %d which is an illegal argument as it is negative", i, shape[i]));
			}
	
			dsize *= shape[i];
		}
	
		// check to see if the size is larger than an integer, i.e. we can't allocate it
		if (dsize > Long.MAX_VALUE) {
			throw new IllegalArgumentException("Size of the dataset is too large to allocate");
		}
		return (long) dsize;
	}

	/**
	 * Convert integer array to long array
	 * @param in
	 * @return long array
	 */
	public static final long[] toLongArray(final int[] in) {
		if (in == null)
			return null;
	
		long[] out = new long[in.length];
		for (int i = 0; i < in.length; i++) {
			out[i] = in[i];
		}
		return out;
	}

	/**
	 * Convert long array to integer array
	 * @param in
	 * @return integer array
	 */
	public static int[] toIntArray(long[] in) {
		int[] out = new int[in.length];
		for (int i = 0; i < out.length; i++) {
			long value = in[i];
			if (value == Long.MAX_VALUE) {
				value = -1; // stopgap fix for incorrectly written maximum shape
			}
			if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
				throw new IllegalArgumentException("Cannot convert to int array without data loss");
			}
			out[i] = (int)value;
		}
		return out;
	}
}
