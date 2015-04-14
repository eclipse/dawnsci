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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.hdf5lib.HDFNativeData;
import ncsa.hdf.hdf5lib.exceptions.HDF5Exception;
import ncsa.hdf.hdf5lib.structs.H5O_info_t;
import ncsa.hdf.object.Datatype;
import ncsa.hdf.object.h5.H5Datatype;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.dataset.impl.ByteDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.FloatDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.IntegerDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.LongDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.PositionIterator;
import org.eclipse.dawnsci.analysis.dataset.impl.ShortDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDF5Utils {
	protected static final Logger logger = LoggerFactory.getLogger(HDF5Utils.class);

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
		Dataset ds = null;
		switch (dtype) {
		case Dataset.FLOAT32:
			float[] fData = (float[]) data;
			ds = new FloatDataset(fData, shape);
			break;
		case Dataset.FLOAT64:
			double[] dData = (double[]) data;
			ds = new DoubleDataset(dData, shape);
			break;
		case Dataset.INT8:
			byte[] bData = (byte[]) data;
			ds = new ByteDataset(bData, shape);
			break;
		case Dataset.INT16:
			short[] sData = (short[]) data;
			ds = new ShortDataset(sData, shape);
			break;
		case Dataset.INT32:
			int[] iData = (int[]) data;
			ds = new IntegerDataset(iData, shape);
			break;
		case Dataset.INT64:
			long[] lData = (long[]) data;
			ds = new LongDataset(lData, shape);
			break;
		case Dataset.STRING:
			String[] strData = (String[]) data;
			ds = new StringDataset(strData, shape);
			break;
		default:
			throw new IllegalArgumentException("Unknown or unsupported dataset type");
		}
	
		if (extend) {
			switch (dtype) {
			case Dataset.INT32:
				ds = new LongDataset(ds);
				DatasetUtils.unwrapUnsigned(ds, 32);
				break;
			case Dataset.INT16:
				ds = new IntegerDataset(ds);
				DatasetUtils.unwrapUnsigned(ds, 16);
				break;
			case Dataset.INT8:
				ds = new ShortDataset(ds);
				DatasetUtils.unwrapUnsigned(ds, 8);
				break;
			}
		}
		return ds;
	}

	/**
	 * Translate between data type and dataset type
	 * @param dclass data type class
	 * @param dsize data type element size in bytes
	 * @return dataset type
	 */
	public static int getDtype(final int dclass, final int dsize) {
		return getDtype(dclass, dsize, 1, false);
	}

	/**
	 * Translate between data type and dataset type
	 * @param dclass data type class
	 * @param dsize data type element size in bytes
	 * @param isize number of items
	 * @param isComplex
	 * @return dataset type
	 */
	public static int getDtype(final int dclass, final int dsize, final int isize, final boolean isComplex) {
		switch (dclass) {
		case Datatype.CLASS_STRING:
			return Dataset.STRING;
		case Datatype.CLASS_CHAR:
		case Datatype.CLASS_INTEGER:
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
			break;
		case Datatype.CLASS_BITFIELD:
			switch (dsize) {
			case 1:
				return isize == 1 ? Dataset.INT8 : Dataset.ARRAYINT8;
			case 2:
				return isize == 1 ? Dataset.INT16 : Dataset.ARRAYINT16;
			case 4:
				return isize == 1 ? Dataset.INT32 : Dataset.ARRAYINT32;
			case 8:
				return isize == 1 ? Dataset.INT64 : Dataset.ARRAYINT64;
			}
			break;
		case Datatype.CLASS_FLOAT:
			if (isComplex) {
				switch (dsize) {
				case 4:
					return Dataset.COMPLEX64;
				case 8:
					return Dataset.COMPLEX128;
				}
			}
			switch (dsize) {
			case 4:
				return Dataset.FLOAT32;
			case 8:
				return Dataset.FLOAT64;
			}
			break;
		}
		return -1;
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
				throws Exception {
		Dataset data = null;

		final String cPath;
		try {
			cPath = new File(fileName).getCanonicalPath();
		} catch (IOException e) {
			logger.error("Could not get canonical path", e);
			throw new ScanFileHolderException("Could not get canonical path", e);
		}
		int fid = -1;
		try {
			HierarchicalDataFactory.acquireLowLevelReadingAccess(cPath);
			fid = H5.H5Fopen(fileName, HDF5Constants.H5F_ACC_RDONLY, HDF5Constants.H5P_DEFAULT);

			try {
				H5O_info_t info = H5.H5Oget_info_by_name(fid, node, HDF5Constants.H5P_DEFAULT);
				int t = info.type;
				if (t != HDF5Constants.H5O_TYPE_DATASET) {
					logger.error("Node {} was not a dataset", node);
					return data;
				}
			} catch (HDF5Exception ex) {
				logger.error("Could not find info about object {}" + node);
				return data;
			}

			int did = -1, tid = -1, tclass = -1;
			try {
				did = H5.H5Dopen(fid, node, HDF5Constants.H5P_DEFAULT);
				tid = H5.H5Dget_type(did);

				tclass = H5.H5Tget_class(tid);
				if (tclass == HDF5Constants.H5T_ARRAY || tclass == HDF5Constants.H5T_VLEN) {
					// for ARRAY, the type is determined by the base type
					int btid = H5.H5Tget_super(tid);
					tclass = H5.H5Tget_class(btid);
					try {
						H5.H5Tclose(btid);
					} catch (HDF5Exception ex) {
					}
				}
				int sid = -1, pid = -1;
				int rank;
				boolean isText, isVLEN; //, isUnsigned = false;
//				boolean isEnum, isRegRef, isNativeDatatype;
				long[] dims;

				// create a new scalar dataset

				try {
					pid = H5.H5Dget_create_plist(did);

					sid = H5.H5Dget_space(did);

					rank = H5.H5Sget_simple_extent_ndims(sid);

					isText = tclass == HDF5Constants.H5T_STRING;
					isVLEN = tclass == HDF5Constants.H5T_VLEN || H5.H5Tis_variable_str(tid);

					final int ldtype;
					if (dtype >= 0) {
						ldtype = dtype;
					} else {
						Datatype type = new H5Datatype(tid);
						ldtype = getDtype(type.getDatatypeClass(), type.getDatatypeSize());
					}

					if (rank == 0) {
						// a single data point
						rank = 1;
						dims = new long[1];
						dims[0] = 1;
					} else {
						dims = new long[rank];
						H5.H5Sget_simple_extent_dims(sid, dims, null);
					}

					long[] schunk = null; // source chunking

					try {
						if (H5.H5Pget_layout(pid) == HDF5Constants.H5D_CHUNKED) {
							schunk = new long[rank];
							H5.H5Pget_chunk(pid, rank, schunk);
						}
					} catch (HDF5Exception ex) {
						logger.error("Could not get chunk size");
						return data;
					}

					final long[] sstart = new long[rank]; // source start
					final long[] sstride = new long[rank]; // source steps
					final long[] dsize = new long[rank]; // destination size

					for (int i = 0; i < rank; i++) {
						sstart[i] = start[i];
						sstride[i] = step[i];
						dsize[i] = count[i];
					}

					boolean all = false;
					if (schunk == null) {
						all = true;
					} else {
						if (Arrays.equals(dims, schunk)) {
							all = true;
						} else {
							int j = rank - 1; // find last chunked dimension that is sliced across
							while (j >= 0) {
								if (schunk[j] > 1 && dsize[j] <= 1)
									break;
								j--;
							}
							all = j < 0;
						}
					}
					if (schunk == null || all) {
						H5.H5Sselect_hyperslab(sid, HDF5Constants.H5S_SELECT_SET, sstart, sstride, dsize, null);
						int length = 1;
						for (int i = 0; i < rank; i++)
							length *= count[i];

						int msid = H5.H5Screate_simple(1, new long[] {length}, null);
						H5.H5Sselect_all(msid);
						data = DatasetFactory.zeros(isize, count, ldtype);
						Object odata = data.getBuffer();

						boolean isREF = H5.H5Tequal(tid, HDF5Constants.H5T_STD_REF_OBJ);
						if (isVLEN) {
							H5.H5DreadVL(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, (Object[]) odata);
						} else {
							H5.H5Dread(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, odata);

							if (odata instanceof byte[] && ldtype != Dataset.INT8) {
								// TODO check if this is actually used
								Object idata = null;
								byte[] bdata = (byte[]) odata;
								if (isText) {
									idata = ncsa.hdf.object.Dataset.byteToString(bdata, H5.H5Tget_size(tid));
								} else if (isREF) {
									idata = HDFNativeData.byteToLong(bdata);
								}

								if (idata != null) {
									data = createDataset(idata, count, ldtype, false); // extend later, if necessary
								}
							}
						}
					} else {
						// read in many split chunks
						final boolean[] isSplit = new boolean[rank];
						final long[] send = new long[rank];
						int length = 1;
						for (int i = 0; i < rank; i++) {
							send[i] = sstart[i] + count[i] * step[i];
							isSplit[i] = schunk[i] <= 1 && dsize[i] > 1;
							if (isSplit[i]) {
								dsize[i] = 1;
							} else {
								length *= dsize[i];
							}
						}
						if (length == 1) { // if just single point then bulk up request
							for (int i = rank - 1; i >= 0; i--) {
								int l = count[i];
								if (l > 1) {
									dsize[i] = l;
									length = l;
									isSplit[i] = false;
									break;
								}
							}
						}
						final List<Integer> notSplit = new ArrayList<Integer>();
						for (int i = 0; i < rank; i++) {
							if (!isSplit[i])
								notSplit.add(i);
						}
						final int[] axes = new int[notSplit.size()];
						for (int i = 0; i < axes.length; i++) {
							axes[i] = notSplit.get(i);
						}
						data = DatasetFactory.zeros(count, ldtype);
						Object odata;
						try {
							odata = H5Datatype.allocateArray(tid, length);
						} catch (OutOfMemoryError err) {
							throw new ScanFileHolderException("Out Of Memory", err);
						}
						int msid = H5.H5Screate_simple(1, new long[] {length}, null);
						H5.H5Sselect_all(msid);

						PositionIterator it = data.getPositionIterator(axes);
						final int[] pos = it.getPos();
						final boolean[] hit = it.getOmit();
						while (it.hasNext()) {
							H5.H5Sselect_hyperslab(sid, HDF5Constants.H5S_SELECT_SET, sstart, sstride, dsize, null);
							boolean isREF = H5.H5Tequal(tid, HDF5Constants.H5T_STD_REF_OBJ);
							Object idata;
							if (isVLEN) {
								H5.H5DreadVL(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, (Object[]) odata);
								idata = odata;
							} else {
								H5.H5Dread(did, tid, msid, sid, HDF5Constants.H5P_DEFAULT, odata);

								if (odata instanceof byte[] && ldtype != Dataset.INT8) {
									// TODO check if this is actually used
									byte[] bdata = (byte[]) odata;
									if (isText) {
										idata = ncsa.hdf.object.Dataset.byteToString(bdata, H5.H5Tget_size(tid));
									} else if (isREF) {
										idata = HDFNativeData.byteToLong(bdata);
									} else {
										idata = odata;
									}
								} else {
									idata = odata;
								}
							}

							data.setItemsOnAxes(pos, hit, idata);
							int j = rank - 1;
							for (; j >= 0; j--) {
								if (isSplit[j]) {
									sstart[j] += sstride[j];
									if (sstart[j] >= send[j]) {
										sstart[j] = start[j];
									} else {
										break;
									}
								}
							}
							if (j == -1)
								break;
						}
					}
					if (extend) {
						switch (ldtype) {
						case Dataset.INT32:
							data = new LongDataset(data);
							DatasetUtils.unwrapUnsigned(data, 32);
							break;
						case Dataset.INT16:
							data = new IntegerDataset(data);
							DatasetUtils.unwrapUnsigned(data, 16);
							break;
						case Dataset.INT8:
							data = new ShortDataset(data);
							DatasetUtils.unwrapUnsigned(data, 8);
							break;
						}
					}
				} catch (HDF5Exception ex) {
					logger.error("Could not get data space information", ex);
					return data;
				} finally {
					if (sid >= 0) {
						try {
							H5.H5Sclose(sid);
						} catch (HDF5Exception ex2) {
						}
					}
					if (pid >= 0) {
						try {
							H5.H5Pclose(pid);
						} catch (HDF5Exception ex) {
						}
					}
				}

			} catch (HDF5Exception ex) {
				logger.error("Could not open dataset", ex);
			} finally {
				if (tid >= 0) {
					try {
						H5.H5Tclose(tid);
					} catch (HDF5Exception ex) {
					}
				}
				if (did >= 0) {
					try {
						H5.H5Dclose(did);
					} catch (HDF5Exception ex) {
					}
				}
			}

		} catch (Throwable le) {
			throw new ScanFileHolderException("Problem loading file: " + fileName, le);
		} finally {
			try {
				H5.H5Fclose(fid);
			} catch (Throwable e) {
			}
			HierarchicalDataFactory.releaseLowLevelReadingAccess(cPath);
		}

		return data;
	}
}
