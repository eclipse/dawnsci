/*-
 *******************************************************************************
 * Copyright (c) 2017 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.hdf5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.FloatDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.junit.Test;

import hdf.hdf5lib.H5;
import hdf.hdf5lib.HDF5Constants;
import hdf.hdf5lib.exceptions.HDF5Exception;

/**
 * Test various filter plugins that are built into the hdf.hdf5lib
 * plugin
 */
public class TestPlugins {

	private final static String DATA_PATH = "/data";
	private final static int DIM_0 = 10;
	private final static int DIM_1 = 256;
	private final static int DIM_2 = 512;
	private final static long[] DATA_SHAPE = new long[] {DIM_0, DIM_1, DIM_2};
	private final static long[] DATA_MAX_SHAPE = new long[] {HDF5Constants.H5S_UNLIMITED, DIM_1, DIM_2};
	private final static long[] DATA_CHUNK = new long[] {1, DIM_1, DIM_2};

	@Test
	public void testZlibFilter() throws NullPointerException, HDF5Exception {
		testFilter("test-scratch/testFilterZlib.h5", HDF5Constants.H5Z_FILTER_DEFLATE, 1);
	}

	@Test
	public void testLZFFilter() throws NullPointerException, HDF5Exception {
		testFilter("test-scratch/testFilterLZF.h5", 32000);
	}

	@Test
	public void testLZ4Filter() throws NullPointerException, HDF5Exception {
		testFilter("test-scratch/testFilterLZ4.h5", 32004, 1);
	}

	@Test
	public void testBSFilter() throws NullPointerException, HDF5Exception {
		testFilter("test-scratch/testFilterBS.h5", 32008, 0, 2);
	}

	public void testFilter(String filename, int filterNumber, int... filterParams) throws NullPointerException, HDF5Exception {
		createFilteredFile(filename, filterNumber, filterParams);
		checkFilteredFile(filename);
	}

	private void createFilteredFile(String filename, int filterNumber, int... filterParams) throws NullPointerException, HDF5Exception {
		File parent = new File(filename).getParentFile();
		if (!parent.exists()) {
			parent.mkdir();
		}
		long fileID = H5.H5Fcreate(filename, HDF5Constants.H5F_ACC_TRUNC, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);
		long dsID = H5.H5Screate_simple(DATA_SHAPE.length, DATA_SHAPE, DATA_MAX_SHAPE);
		long dcpl = H5.H5Pcreate(HDF5Constants.H5P_DATASET_CREATE);
		H5.H5Pset_chunk(dcpl, DATA_CHUNK.length, DATA_CHUNK);

		H5.H5Pset_filter(dcpl, filterNumber, HDF5Constants.H5Z_FLAG_MANDATORY, filterParams.length, filterParams);

		long dID = H5.H5Dcreate(fileID, DATA_PATH,
								HDF5Constants.H5T_NATIVE_FLOAT, dsID,
								HDF5Constants.H5P_DEFAULT, dcpl, HDF5Constants.H5P_DEFAULT);

		Dataset data = DatasetFactory.createRange(DIM_0 * DIM_1 * DIM_2).reshape(DIM_0, DIM_1, DIM_2);
		H5.H5Dwrite(dID, HDF5Constants.H5T_NATIVE_DOUBLE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, DatasetUtils.serializeDataset(data));

		H5.H5Dclose(dID);
		H5.H5Pclose(dcpl);
		H5.H5Sclose(dsID);
		H5.H5Fclose(fileID);
	}

	private void checkFilteredFile(String filename) throws NullPointerException, HDF5Exception {
		long fileID = H5.H5Fopen(filename, HDF5Constants.H5F_ACC_RDONLY, HDF5Constants.H5P_DEFAULT);
		long dID = H5.H5Dopen(fileID, DATA_PATH, HDF5Constants.H5P_DEFAULT);
		long sID = H5.H5Dget_space(dID);
		int rank = H5.H5Sget_simple_extent_ndims(sID);
		assertEquals(DATA_SHAPE.length, rank);
		long[] dims = new long[rank];
		long[] mdims = new long[rank];
		H5.H5Sget_simple_extent_dims(sID, dims, mdims);
		H5.H5Sclose(sID);
		
		assertArrayEquals(DATA_SHAPE, dims);
		assertArrayEquals(DATA_MAX_SHAPE, mdims);

		int[] shape = new int[rank];
		for (int i = 0; i < rank; i++) {
			shape[i] = (int) dims[i];
		}

		Dataset data = DatasetFactory.zeros(FloatDataset.class, shape);

		H5.H5Dread(dID, HDF5Constants.H5T_NATIVE_FLOAT, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, data.getBuffer());

		H5.H5Dclose(dID);
		H5.H5Fclose(fileID);

		IndexIterator it = data.getIterator();
		while (it.hasNext()) {
			assertEquals(it.index, data.getElementLongAbs(it.index));
		}
	}
}
