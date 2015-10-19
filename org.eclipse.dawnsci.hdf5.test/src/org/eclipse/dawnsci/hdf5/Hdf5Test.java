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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Map;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.HierarchicalDataUtils;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.dawnsci.hdf5.Nexus;
import org.junit.BeforeClass;
import org.junit.Test;


public class Hdf5Test {

	private static String FILENAME = "dset.h5";
	private static String DATASETNAME = "dset";
	private static final int DIM_X = 4;
	private static final int DIM_Y = 6;
	private static String TestFileAbsolutePath;

	@BeforeClass
	static public void setUpClass() {
		TestFileAbsolutePath = new File("testfiles/FeKedge_1_15.nxs").getAbsolutePath();
	}

	@Test
	public void writeH5Example() throws Exception {

		IHierarchicalDataFile file = null;
		try {

			final File tmp = File.createTempFile("Test", "h5");
			tmp.deleteOnExit();
			tmp.createNewFile();

			// Throws exception if already writing to this file
			// on another thread.
			// writeH5Example is synchronized to here to avoid this write
			// (but not others!) interfering.
			file = HierarchicalDataFactory.getWriter(tmp.getAbsolutePath());

			final String myDataGroup = file.group("myData");

			final double[] random = new double[2048*2048];
			for (int i = 0; i < 2048*2048; i++) {
				random[i] = Math.random();
			}
			String s = file.createDataset("data",
											Dataset.FLOAT64,
											new long[]{2048,2048},
											random,
											myDataGroup);
			file.setNexusAttribute(s, Nexus.SDS);
			file.close();

			file = HierarchicalDataFactory.getReader(tmp.getAbsolutePath());
			final ncsa.hdf.object.Dataset set = (ncsa.hdf.object.Dataset)file.getData("myData/data");

			set.getMetadata();

			final long[] size = set.getDims();
			if (size[0]!=2048 || size[1]!=2048) throw new Exception("Unexpected read data set size!");

			final double[] data = (double[])set.getData();

			for (int i = 0; i < data.length; i++) {
				if (data[i]!=random[i]) throw new Exception("Unexpected data value!");
			}

		} finally {
			if (file!=null) file.close();
		}
	}

	@Test
	public void testOpenFile() throws Exception {


		long id = -1;
		try {
			id = HDF5Utils.H5Fopen(TestFileAbsolutePath,
				            HDF5Constants.H5F_ACC_RDONLY,
					        HDF5Constants.H5P_DEFAULT);


			if (id<0) throw new Exception("Cannot open hdf5 file!");
		} finally {
			if (id>-1) H5.H5Fclose(id);
		}


	}

	@Test
	public void testData() throws Exception {
		getData(TestFileAbsolutePath);
	}

	private void getData(String path) throws Exception {

		IHierarchicalDataFile testFile = null;

		try {
			// open the file and retrieve the file structure
			testFile = HierarchicalDataFactory.getReader(path);
			final ncsa.hdf.object.Dataset set = (ncsa.hdf.object.Dataset)testFile.getData("/entry1/counterTimer01/lnI0It");
			final Object  dat = set.read();
			final double[] da = (double[])dat;
			if (da.length<100) throw new Exception("Did not get data from node lnI0It");
		} finally {
			// close file resource
			if (testFile!=null) testFile.close();
		}
	}

	@Test
	public void testShowStructure() throws Exception {
		printStructure(TestFileAbsolutePath);
		//printStructure(/buffer/linpickard1/results/examples/DCT_201006-good.h5");
		//printStructure("/buffer/linpickard1/results/large test files/rhodo_f2_datanorm_.h5");
	}

	public void printStructure(final String path) throws Exception {

		IHierarchicalDataFile testFile = null;

		try {
			// open the file and retrieve the file structure
			testFile = HierarchicalDataFactory.getReader(path);
			final String root = testFile.getRoot();
			if (root == null) throw new Exception("Did not get root!");

			testFile.print();

		} finally {
			// close file resource
			if (testFile!=null) testFile.close();
		}

	}


	//@Test
	public void testReadWriteDataset() {

		long file_id = -1;
		long dataset_id = -1;
		int[][] dset_data = new int[DIM_X][DIM_Y];

		// Initialize the dataset.
		for (int indx = 0; indx < DIM_X; indx++)
			for (int jndx = 0; jndx < DIM_Y; jndx++)
				dset_data[indx][jndx] = indx * 6 + jndx + 1;

		// Open an existing file.
		try {
			file_id = HDF5Utils.H5Fopen(FILENAME, HDF5Constants.H5F_ACC_RDWR,
					HDF5Constants.H5P_DEFAULT);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Open an existing dataset.
		try {
			if (file_id >= 0)
				dataset_id = H5.H5Dopen(file_id, "/" + DATASETNAME, HDF5Constants.H5P_DEFAULT);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Write the dataset.
		try {
			if (dataset_id >= 0)
				H5.H5Dwrite(dataset_id, HDF5Constants.H5T_NATIVE_INT,
						HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL,
						HDF5Constants.H5P_DEFAULT, dset_data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (dataset_id >= 0)
				H5.H5Dread(dataset_id, HDF5Constants.H5T_NATIVE_INT,
						HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL,
						HDF5Constants.H5P_DEFAULT, dset_data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Close the dataset.
		try {
			if (dataset_id >= 0)
				H5.H5Dclose(dataset_id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Close the file.
		try {
			if (file_id >= 0)
				H5.H5Fclose(file_id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAutoCloseable() throws Exception {
		// this is really a compile test to make sure that IHierarchicalDataFile
		// can be used with a try-with-resources statement (i.e. is
		// AutoCloseable)
		try (IHierarchicalDataFile reader = HierarchicalDataFactory
				.getReader(TestFileAbsolutePath)) {
		}
	}

	@Test
	public void testGetAttributeValues_String() throws Exception {
		try (IHierarchicalDataFile reader = HierarchicalDataFactory
				.getReader(TestFileAbsolutePath)) {
			Map<String, Object> attrs;

			attrs = reader.getAttributeValues("/");
			assertEquals(4, attrs.size());
			assertEquals("1.6.10", HierarchicalDataUtils.extractScalar(attrs
					.get("HDF5_Version")));
			assertEquals("4.2.0", HierarchicalDataUtils.extractScalar(attrs
					.get("NeXus_version")));
			assertEquals(
					"/scratch/users/data/2010/cm1903-4/Experiment_1/nexus/FeKedge_1_15.nxs",
					HierarchicalDataUtils.extractScalar(attrs.get("file_name")));
			assertEquals("2010-07-19T13:54:15+00:00",
					HierarchicalDataUtils.extractScalar(attrs.get("file_time")));

			attrs = reader.getAttributeValues("/entry1/xspress2system/Time");
			assertEquals(2, attrs.size());
			assertEquals(1,
					HierarchicalDataUtils.extractScalar(attrs.get("axis")));
			assertEquals("/entry1/instrument/xas_scannable/Time",
					HierarchicalDataUtils.extractScalar(attrs.get("target")));

			attrs = reader
					.getAttributeValues("/entry1/instrument/xspress2system/data");
			assertEquals(3, attrs.size());
			assertEquals(1,
					HierarchicalDataUtils.extractScalar(attrs.get("signal")));
			assertEquals("/entry1/instrument/xspress2system/data",
					HierarchicalDataUtils.extractScalar(attrs.get("target")));
			assertEquals("counts",
					HierarchicalDataUtils.extractScalar(attrs.get("units")));

			assertEquals(null, reader.getAttributeValues("/nonexistent"));
		}

	}

}