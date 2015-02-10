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
package org.eclipse.dawnsci.hdf5.model.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.HierarchicalDataUtils;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataFileModel;
import org.eclipse.dawnsci.hdf5.model.internal.HierarchicalDataFileModel;
import org.eclipse.dawnsci.hdf5.model.internal.IHierarchicalDataFileGetReader;
import org.junit.Test;

public class HierarchicalDataFileModelTest {

	private static final IHierarchicalDataFileGetReader get_i05_4859_Reader = new IHierarchicalDataFileGetReader() {

		@Override
		public IHierarchicalDataFile getReader() throws Exception {
			return HierarchicalDataFactory.getReader(new File("testfiles/i05-4859.nxs").getAbsolutePath());
		}
	};

	/**
	 * Used to debug the file structure to help write the other tests
	 */
	@Test
	public void printDataContents() throws Exception {
		try (IHierarchicalDataFile reader = get_i05_4859_Reader.getReader()) {
			printDataContents(reader);
		}

	}

	private void printDataContents(IHierarchicalDataFile reader)
			throws Exception {
		Map<String, Object> attributeValues = reader.getAttributeValues();
		for (Entry<String, Object> entry : attributeValues.entrySet()) {
			String ln = entry.getKey() + "=" + entry.getValue() + "=="
					+ HierarchicalDataUtils.extractScalar(entry.getValue());
			System.out.println(ln);

		}

		String g = reader.getRoot();
		printGroup(reader, g);
	}

	private void printGroup(IHierarchicalDataFile reader, String g) throws Exception {
		
		List<String> members = reader.memberList(g);

		int n = members.size();
		for (int i = 0; i < n; i++) {
			String childPath = members.get(i);
			System.out.print(childPath);
			if (reader.isDataset(childPath)) {
				ncsa.hdf.object.Dataset dataset = (ncsa.hdf.object.Dataset) reader.getData(childPath);
				Object value = dataset.read();
				System.out.print("=DIMS(");
				System.out.print(Arrays.toString(dataset.getDims()));
				System.out.print(")");
				System.out.print(value);
				System.out.print("=="
						+ HierarchicalDataUtils.extractScalar(value));
				
			} else if (reader.isGroup(childPath)) {
				printGroup(reader, childPath);
			}
			System.out.println();

		}
	}

	@Test
	public void testInvalidFile() {
		IHierarchicalDataFileGetReader getInvalidFileReader = new IHierarchicalDataFileGetReader() {

			@Override
			public IHierarchicalDataFile getReader() throws Exception {
				String absolutePath = "/tmp/non-existent-file.nxs";
				return HierarchicalDataFactory.getReader(absolutePath);
			}
		};
		IHierarchicalDataFileModel invalidFileModel = new HierarchicalDataFileModel(
				getInvalidFileReader);
		assertEquals(null, invalidFileModel.getPath("/@HDF5_Version"));
	}

	@Test
	public void testGetPath() {
		IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
				get_i05_4859_Reader);
		checkGetPathOnAllCases(model);
	}

	@Test
	public void testHasPath() {
		IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
				get_i05_4859_Reader);
		checkHasPathOnAllCases(model);
	}

	private void checkGetPathOnAllCases(IHierarchicalDataFileModel model) {
		// global attributes
		assertEquals("1.8.7", model.getPath("/@HDF5_Version"));
		// path with scalar dataset
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
		// scalar dataset attribute
		assertEquals("Hz",
				model.getPath("/entry1/instrument/analyser/cps@units"));
		// group
		assertEquals(null, model.getPath("/entry1"));
		// attrib on group
		assertEquals("NXentry", model.getPath("/entry1@NX_class"));
		// path with non 1-size dataset
		assertEquals(null,
				model.getPath("/entry1/instrument/analyser/energies"));
		// attrib on non 1-size dataset, string
		assertEquals("3",
				model.getPath("/entry1/instrument/analyser/energies@axis"));
		// attrib on non 1-size dataset, integer
		assertEquals(1,
				model.getPath("/entry1/instrument/analyser/energies@primary"));
		// non-existent root attrib
		assertEquals(null, model.getPath("/@NonExistent"));
		// non-existent attrib on valid path
		assertEquals(null,
				model.getPath("/entry1/instrument/analyser/cps@NonExistent"));
		// non-existent path
		assertEquals(null,
				model.getPath("/entry1/instrument/analyser/NonExistent"));
		// non-existent attrib on group
		assertEquals(null, model.getPath("/entry1@NonExistent"));
	}

	private void checkHasPathOnAllCases(IHierarchicalDataFileModel model) {
		// root attrib
		assertTrue(model.hasPath("/@HDF5_Version"));
		// path with scalar dataset
		assertTrue(model.hasPath("/entry1/instrument/analyser/cps"));
		// attrib scalar dataset
		assertTrue(model.hasPath("/entry1/instrument/analyser/cps@units"));
		// group
		assertTrue(model.hasPath("/entry1"));
		// attrib on group
		assertTrue(model.hasPath("/entry1@NX_class"));
		// path with non 1-size dataset
		assertTrue(model.hasPath("/entry1/instrument/analyser/energies"));
		// attrib on non 1-size dataset, string
		assertTrue(model.hasPath("/entry1/instrument/analyser/energies@axis"));
		// attrib on non 1-size dataset, integer
		assertTrue(model
				.hasPath("/entry1/instrument/analyser/energies@primary"));
		// non-existent root attrib
		assertTrue(!model.hasPath("/@NonExistent"));
		// non-existent attrib on valid path
		assertTrue(!model
				.hasPath("/entry1/instrument/analyser/cps@NonExistent"));
		// non-existent path
		assertTrue(!model.hasPath("/entry1/instrument/analyser/NonExistent"));
		// non-existent attrib on group
		assertTrue(!model.hasPath("/entry1@NonExistent"));
	}

	/**
	 * This test makes sure that re-reading the same attribute does not cause a
	 * new file access.
	 * <p>
	 * This depends on the overall contract of the Model not being violated in
	 * the code, that is that the reader is not left open between calls to the
	 * model.
	 * <p>
	 * Additionally, this test may fail if more data is cached on the initial
	 * read of the Nexus file. For example, at one point each attribute was
	 * fetched individually from the underlying reader. However on discovering
	 * that reading any one attribute loaded all the attributes on that node a
	 * new method was added
	 * {@link IHierarchicalDataFile#getAttributeValues(String)} to get all the
	 * attributes in one go.
	 */
	@Test
	public void hitsCache() {
		final int[] count = new int[] { 0 };
		IHierarchicalDataFileGetReader getCountingReader = new IHierarchicalDataFileGetReader() {
			@Override
			public IHierarchicalDataFile getReader() throws Exception {
				count[0] += 1;
				return get_i05_4859_Reader.getReader();
			}
		};
		IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
				getCountingReader);

		// Make sure re-reading attribute (either has or value) does not
		// increase file access count
		assertTrue(model.hasPath("/@HDF5_Version"));
		assertEquals(1, count[0]);
		assertTrue(model.hasPath("/@HDF5_Version"));
		assertEquals(1, count[0]);
		assertEquals("1.8.7", model.getPath("/@HDF5_Version"));
		assertEquals(1, count[0]);

		// Make sure reading additional attributes on same node does not
		// increase file access count
		assertTrue(model.hasPath("/@NeXus_version"));
		assertEquals(1, count[0]);
		assertEquals("4.3.1", model.getPath("/@NeXus_version"));
		assertEquals(1, count[0]);

		// Make sure reading additional non-existent attributes on same
		// node does not increase file access count
		assertTrue(!model.hasPath("/@NonExistentAttribute"));
		assertEquals(1, count[0]);
		assertEquals(null, model.getPath("/@NonExistentAttribute"));
		assertEquals(1, count[0]);

		// Make sure re-reading dataset (either has or value) does not
		// increase file access count
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
		assertEquals(2, count[0]);
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
		assertEquals(2, count[0]);
		assertTrue(model.hasPath("/entry1/instrument/analyser/cps"));
		assertEquals(2, count[0]);

		// Make sure oscillating between attribs and dataset on same
		// path does not increase counts
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
		assertEquals(2, count[0]);
		assertEquals("Hz",
				model.getPath("/entry1/instrument/analyser/cps@units"));
		assertEquals(3, count[0]);
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
		assertEquals(3, count[0]);
		assertEquals("Hz",
				model.getPath("/entry1/instrument/analyser/cps@units"));
		assertEquals(3, count[0]);

		// Make sure reading additional non-existent dataset on same
		// node does not increase file access count
		assertTrue(!model.hasPath("/Non/Existent/Path"));
		assertEquals(4, count[0]);
		assertEquals(null, model.getPath("/Non/Existent/Path"));
		assertEquals(4, count[0]);

		// Make sure reading additional non-existent path's attributes
		// on same node does not increase file access count
		assertTrue(!model.hasPath("/Another/Non/Existent/Path@Attrib1"));
		assertEquals(5, count[0]);
		assertEquals(null, model.getPath("/Another/Non/Existent/Path@Attrib1"));
		assertEquals(5, count[0]);
		assertTrue(!model.hasPath("/Another/Non/Existent/Path@Attrib2"));
		assertEquals(5, count[0]);
		assertEquals(null, model.getPath("/Another/Non/Existent/Path@Attrib2"));
		assertEquals(5, count[0]);

		// Re-use check of all cases to make sure re-reading each/all of them
		// does not increase access count
		checkGetPathOnAllCases(model);
		checkHasPathOnAllCases(model);
		final int expectedCount = count[0];
		checkGetPathOnAllCases(model);
		checkHasPathOnAllCases(model);
		assertEquals(expectedCount, count[0]);
	}

	@Test
	public void testReadAttribFirst() {
		IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
				get_i05_4859_Reader);
		assertEquals("Hz",
				model.getPath("/entry1/instrument/analyser/cps@units"));
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
	}

	@Test
	public void testReadDatasetFirst() {
		IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
				get_i05_4859_Reader);
		assertEquals(1754416.0,
				model.getPath("/entry1/instrument/analyser/cps"));
		assertEquals("Hz",
				model.getPath("/entry1/instrument/analyser/cps@units"));
	}

	private Object createNexusFile(final int dtype, final long[] shape, final Object buffer) throws Exception {
		
		String PATH = "kichwa1";
		final File file = File.createTempFile("HierarchicalDataFileModelTest",
				".nxs");
		file.delete();
		try {

			// Create a file and verify it
			IHierarchicalDataFile writer = HierarchicalDataFactory.getWriter(file.getAbsolutePath());
			writer.createDataset(PATH, dtype, shape, buffer, writer.getRoot());
			writer.close();
			try (IHierarchicalDataFile reader = HierarchicalDataFactory
					.getReader(file.getAbsolutePath());) {
				printDataContents(reader);
			}

			IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
					new IHierarchicalDataFileGetReader() {

						@Override
						public IHierarchicalDataFile getReader()
								throws Exception {
							String absolutePath = file.getAbsolutePath();
							return HierarchicalDataFactory
									.getReader(absolutePath);
						}
					});
			return model.getPath(PATH);
		} finally {
			file.delete();
		}
	}

	/**
	 * This test was created to test support Datasets that have > 1 dimension,
	 * but the sizes of all the dimensions are 1. e.g. /entry1/sample/name is
	 * stored as [ZrTe3_X60] with dims [1,1] and previously was not supported
	 *
	 * @throws Exception
	 */
	@Test
	public void testMultiDimArrayString() throws Exception {
		final String GOLD = "Gold";
		assertEquals(GOLD, createNexusFile(Dataset.STRING, new long[] { 1 }, new String[] { GOLD }));
		assertEquals(
				GOLD, createNexusFile(Dataset.STRING, new long[] { 1, 1 },
						new String[] { GOLD }));
		assertEquals(
				GOLD, createNexusFile(Dataset.STRING, new long[] { 1, 1, 1 },
						new String[] { GOLD }));
		assertEquals(
				GOLD, createNexusFile(Dataset.STRING, new long[] { 1, 1, 1, 1 },
						new String[] { GOLD }));
	}

	@Test
	public void testMultiDimArrayFloat() throws Exception {
		assertEquals(1.0,
				createNexusFile(Dataset.FLOAT64, new long[] { 1 }, new double[] { 1.0 }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new long[] { 1, 1 },
						new double[][] { { 1.0 } }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new long[] { 1, 1, 1 },
						new double[][][] { { { 1.0 } } }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new long[] { 1, 1 },
						new double[] { 1.0 }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new long[] { 1, 1, 1 },
						new double[] { 1.0 }));

	}

}
