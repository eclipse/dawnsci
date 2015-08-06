package org.eclipse.dawnsci.hdf5.swmr;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.hdf5lib.exceptions.HDF5Exception;

public class TestSWMR {

	private final static String filename = "test-scratch/testSWMR.h5";

	@Before
	public void setup() throws NullPointerException, HDF5Exception {
		File parent = new File(filename).getParentFile();
		if (!parent.exists()) {
			parent.mkdir();
		}
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
		long fileID = H5.H5Fcreate(filename, HDF5Constants.H5F_ACC_TRUNC, HDF5Constants.H5P_DEFAULT, fapl);
		long dsID = H5.H5Screate_simple(1, new long[] {0}, new long[] {-1});
		long dcpl = H5.H5Pcreate(HDF5Constants.H5P_DATASET_CREATE);
		H5.H5Pset_chunk(dcpl, 1, new long[] {2});
		long dID = H5.H5Dcreate(fileID, "/data",
								HDF5Constants.H5T_IEEE_F32LE, dsID,
								HDF5Constants.H5P_DEFAULT, dcpl, HDF5Constants.H5P_DEFAULT);
		H5.H5Dflush(dID);
		H5.H5Drefresh(dID);
		H5.H5Dclose(dID);
		H5.H5Pclose(dcpl);
		H5.H5Sclose(dsID);
		H5.H5Fclose(fileID);
		H5.H5Pclose(fapl);
	}

	@Test
	public void testRead() throws HDF5Exception, NullPointerException {
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
		long fileID = H5.H5Fopen("testfiles/testSWMR.h5", HDF5Constants.H5F_ACC_RDONLY | HDF5Constants.H5F_ACC_SWMR_READ, fapl);
		long dID = H5.H5Dopen(fileID, "/data", HDF5Constants.H5P_DEFAULT);

		long fsID = H5.H5Dget_space(dID);
		int rank = (int) H5.H5Sget_simple_extent_ndims(fsID);
		long[] shape = new long[rank];
		long[] maxShape = new long[rank];
		H5.H5Sget_simple_extent_dims(fsID, shape, maxShape);

		Assert.assertEquals(1, rank);
		Assert.assertArrayEquals(new long[] {4}, shape);
		Assert.assertArrayEquals(new long[] {HDF5Constants.H5S_UNLIMITED}, maxShape);
		float[] buf = new float[4];
		H5.H5Dread_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buf);
		Assert.assertArrayEquals(new float[] {0, 1, 2, 3}, buf, 1f-5);
	}

	@Test
	public void testWrite() throws HDF5Exception, NullPointerException {
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
		long fileID = H5.H5Fopen(filename, HDF5Constants.H5F_ACC_RDWR, fapl);
		float[] data = new float[] {0, 1, 2, 3};
		H5.H5Fstart_swmr_write(fileID);
		long dID = H5.H5Dopen(fileID, "/data", HDF5Constants.H5P_DEFAULT);
		H5.H5Dset_extent(dID, new long[] {4});
		H5.H5Dwrite_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, data);
		H5.H5Dflush(dID);
		H5.H5Drefresh(dID);

		float[] buf = new float[4];
		H5.H5Dread_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buf);
		H5.H5Dclose(dID);
		H5.H5Fclose(fileID);
		H5.H5Pclose(fapl);

		Assert.assertArrayEquals(data, buf, 1f-6);
	}

	@Test
	public void testMultipleWrite() throws HDF5Exception, NullPointerException {
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
		long fileID = H5.H5Fopen(filename, HDF5Constants.H5F_ACC_RDWR, fapl);
		float[] data = new float[] {0, 1, 2, 3};
		H5.H5Fstart_swmr_write(fileID);
		long dID = H5.H5Dopen(fileID, "/data", HDF5Constants.H5P_DEFAULT);
		H5.H5Dset_extent(dID, new long[] {4});
		H5.H5Dwrite_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, data);
		H5.H5Dflush(dID);
		H5.H5Drefresh(dID);

		float[] buf = new float[4];
		H5.H5Dread_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buf);

		Assert.assertArrayEquals(data, buf, 1f-6);

		data = new float[] {0, 1, 2, 3, 0, 1, 2, 3};
		H5.H5Dset_extent(dID, new long[] {8});
		H5.H5Dwrite_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, data);
		H5.H5Dflush(dID);
		H5.H5Drefresh(dID);

		buf = new float[8];
		H5.H5Dread_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buf);

		Assert.assertArrayEquals(data, buf, 1f-6);

		H5.H5Dclose(dID);
		
		H5.H5Fclose(fileID);
		H5.H5Pclose(fapl);
	}

	public void testAWrite() {
		
	}
}
