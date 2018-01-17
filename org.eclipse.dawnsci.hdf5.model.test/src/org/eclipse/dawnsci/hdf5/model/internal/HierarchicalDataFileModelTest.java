/*-
 *******************************************************************************
 * Copyright (c) 2011, 2017 Diamond Light Source Ltd.
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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataFileModel;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileHDF5;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.NexusUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.junit.Test;

public class HierarchicalDataFileModelTest {

	private static final IHierarchicalDataFileGetReader get_i05_4859_Reader = new IHierarchicalDataFileGetReader() {

		@Override
		public NexusFile getReader() throws Exception {
			return NexusFileHDF5.openNexusFile(new File("testfiles/i05-4859.nxs").getAbsolutePath());
		}
	};

	/**
	 * Used to debug the file structure to help write the other tests
	 */
	@Test
	public void printDataContents() throws Exception {
		try (NexusFile reader = get_i05_4859_Reader.getReader()) {
			printDataContents(reader);
		}

	}

	private void printDataContents(NexusFile reader) throws Exception {
		String root = reader.getRoot();
		GroupNode rootGroup = reader.getGroup(root, true);
		Iterator<? extends Attribute> attrIter = rootGroup.getAttributeIterator();
		while (attrIter.hasNext()) {
			Attribute attribute = attrIter.next();
			String ln = attribute.getName() + "=" + attribute.getValue() + "=="
					+ NexusUtils.extractScalarFromDataset(attribute.getValue());
			System.out.println(ln);
		}
		printGroup(reader, root);
	}

	private void printGroup(NexusFile reader, String g) throws Exception {
		GroupNode rootGroup = reader.getGroup(g, true);
		List<String> members = (List<String>) rootGroup.getNames();

		int n = members.size();
		for (int i = 0; i < n; i++) {
			String childPath = members.get(i);
			childPath = Node.SEPARATOR + childPath;
			System.out.print(childPath);
			GroupNode group = reader.getGroup(childPath, false);
			if (group.isDataNode()) {
				DataNode dataset = reader.getData(childPath);
				Dataset value = DatasetUtils.sliceAndConvertLazyDataset(dataset.getDataset());
				System.out.print("=DIMS(");
				System.out.print(Arrays.toString(value.getShape()));
				System.out.print(")");
				System.out.print(value.toString(true));
				System.out.print("=="
						+ NexusUtils.extractScalarFromDataset(value));
				
			} else if (group.isGroupNode()) {
				Collection<String> names = group.getNames();
				for (Iterator<String> iterator = names.iterator(); iterator.hasNext();) {
					String name = iterator.next();
					System.out.println("node: " + name);
				}
			}
			System.out.println();

		}
	}

	@Test
	public void testInvalidFile() {
		IHierarchicalDataFileGetReader getInvalidFileReader = new IHierarchicalDataFileGetReader() {

			@Override
			public NexusFile getReader() throws Exception {
				String absolutePath = "/tmp/non-existent-file.nxs";
				NexusFile nexusFile = NexusFileHDF5.openNexusFile(absolutePath);
				nexusFile.openToRead();
				return nexusFile;
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
			public NexusFile getReader() throws Exception {
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

	private Object createNexusFile(final int dtype, final int[] shape, final Object buffer) throws Exception {
		
		String PATH = Node.SEPARATOR + "kichwa1";
		String dataPath = PATH + Node.SEPARATOR + "Gold";
		final File file = File.createTempFile("HierarchicalDataFileModelTest",
				".nxs");
		file.delete();
		try {

			// Create a file and verify it
			NexusFile writer = NexusFileHDF5.createNexusFile(file.getAbsolutePath());
			Dataset data = DatasetFactory.createFromObject(dtype, buffer, shape);
			data.setName("Gold");
			writer.createData(PATH, data, true);
			writer.close();
			try (NexusFile reader = NexusFileHDF5.openNexusFile(file.getAbsolutePath())) {
				printDataContents(reader);
				reader.close();
			}

			IHierarchicalDataFileModel model = new HierarchicalDataFileModel(
					new IHierarchicalDataFileGetReader() {

						@Override
						public NexusFile getReader()
								throws Exception {
							String absolutePath = file.getAbsolutePath();
							NexusFile nexusFile = NexusFileHDF5.openNexusFile(absolutePath);
							return nexusFile;
						}
					});
			return model.getPath(dataPath);
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
		assertEquals(GOLD, createNexusFile(Dataset.STRING, new int[] { 1 }, new String[] { GOLD }));
		assertEquals(
				GOLD, createNexusFile(Dataset.STRING, new int[] { 1, 1 },
						new String[] { GOLD }));
		assertEquals(
				GOLD, createNexusFile(Dataset.STRING, new int[] { 1, 1, 1 },
						new String[] { GOLD }));
		assertEquals(
				GOLD, createNexusFile(Dataset.STRING, new int[] { 1, 1, 1, 1 },
						new String[] { GOLD }));
	}

	@Test
	public void testMultiDimArrayFloat() throws Exception {
		assertEquals(1.0,
				createNexusFile(Dataset.FLOAT64, new int[] { 1 }, new double[] { 1.0 }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new int[] { 1, 1 },
						new double[][] { { 1.0 } }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new int[] { 1, 1, 1 },
						new double[][][] { { { 1.0 } } }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new int[] { 1, 1 },
						new double[] { 1.0 }));
		assertEquals(
				1.0,
				createNexusFile(Dataset.FLOAT64, new int[] { 1, 1, 1 },
						new double[] { 1.0 }));

	}

}
