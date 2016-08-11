/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus.file;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.tree.impl.DataNodeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.GroupNodeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.SymbolicNodeImpl;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.NexusUtils;
import org.eclipse.dawnsci.nexus.TestUtils;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;
import org.eclipse.january.dataset.Random;
import org.eclipse.january.dataset.SliceND;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NexusFileTest {

	private static String testScratchDirectoryName;
	private static String FILE_NAME;
	private static String FILE2_NAME;

	private NexusFile nf;

	/**
	 * Creates an empty directory for use by test code.
	 *
	 * @throws Exception if setup fails
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(NexusFileTest.class.getCanonicalName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
		(new File(testScratchDirectoryName + "origin/")).mkdirs();
		(new File(testScratchDirectoryName + "linked/")).mkdirs();
		FILE_NAME = testScratchDirectoryName + "test.nxs";
		FILE2_NAME = testScratchDirectoryName + "ext-test.nxs";
	}

	@Before
	public void setUp() throws Exception {
		nf = NexusTestUtils.createNexusFile(FILE_NAME);
	}

	@After
	public void tearDown() throws Exception {
		nf.close();
		nf = null;
	}

	@Test
	public void testOpenToRead() throws Exception {
		// create a group and close the file
		nf.getGroup("/a/b/c", true);
		nf.close();

		// open the file to read and test that the created group is present
		nf.openToRead();
		assertNotNull(nf.getGroup("/a/b/c", false));
	}

	@Test(expected = NexusException.class)
	public void testOpenToReadTryWrite() throws Exception {
		// create a group and close the file
		nf.getGroup("/a/b/c", true);
		nf.close();

		// open the file to read only, and try writing to it, exception should be thrown
		nf.openToRead();
		nf.getGroup("/e/f/g", true);
	}

	@Test
	public void testOpenToWrite() throws Exception {
		// create a group and close the file
		nf.getGroup("/a/b/c", true);
		nf.close();

		// open the file to write and check the previously written group is still there
		nf.openToWrite(false);
		assertNotNull(nf.getGroup("/a/b/c", false));

		// write a new group (would throw an exception if file wasn't writable)
		nf.getGroup("/e/f/g", true);
	}

	@Test
	public void testCreateAndOpenToWrite() throws Exception {
		// create a group and close the file
		nf.getGroup("/a/b/c", true);
		nf.close();

		// create and open the file to write - overwrites the old file
		nf.createAndOpenToWrite();
		try {
			// try getting the previously created group, should not exist
			// as createAndOpenToWrite overwrites the old file
			nf.getGroup("/a/b/c", false);
			fail("Group should not exist as old file should have been overwritten");
		} catch (NexusException e) {
			// fall through
		}

		// try writing a new group
		nf.getGroup("/e/f/g", true);
		assertNotNull(nf.getGroup("/e/f/g", false));
	}

	@Test
	public void testGetPath() throws Exception {
		final String path = "/a/b/c/";
		GroupNode groupNode = nf.getGroup(path, true);
		assertEquals(path, nf.getPath(groupNode));
	}

	@Test
	public void testGetGroup() throws Exception {
		// create a new group
		GroupNode group = nf.getGroup("/a/b/c", true);
		assertNotNull(group);

		// test that the group is a child of its expected parent group
		GroupNode childGroup = nf.getGroup("/a/b/c", false);
		GroupNode parentGroup = nf.getGroup("/a/b", false);
		assertEquals(1, parentGroup.getNames().size());
		assertTrue(parentGroup.getNames().contains("c"));
		assertSame(childGroup, parentGroup.getGroupNode("c"));
	}

	@Test(expected = NexusException.class)
	public void testGetGroupNoCreate() throws Exception {
		nf.getGroup("/a/b/c/d", false);
	}

	@Test
	public void testGetGroupOfClass() throws Exception {
		// create a new group of class Nxtext
		final String className = "NXtext";
		GroupNode parentGroup = nf.getGroup("/a/b", true);
		GroupNode group = nf.getGroup(parentGroup, "c", className, true);

		assertEquals(className, group.getAttribute("NX_class").getFirstElement());
		assertEquals(1, parentGroup.getNames().size());
		assertTrue(parentGroup.getNames().contains("c"));
	}

	@Test
	public void testGetRootNode() throws Exception {
		nf.getGroup("/a/b", true);
		GroupNode root = nf.getGroup("/", false);
		assertNotNull(root);
		assertTrue(root.containsGroupNode("a"));
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		root =  nf.getGroup("/", false);
		assertNotNull(root);
		assertTrue(root.containsGroupNode("a"));
	}

	@Test
	public void testGroupProperties() throws Exception {
		//Test that the properties on a retrieved node match expectations without having traversed to children
		nf.getGroup("/a/b/c", true);
		nf.getGroup("/a/b/x", true);
		IDataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("d");
		nf.createData("/a/b", dataset, false);
		IDataset attr = DatasetFactory.createFromObject(new int[] {1, 2, 3});
		IDataset attr2 = DatasetFactory.createFromObject(new int[] {4, 5});
		attr.setName("SomeAttribute");
		attr2.setName("Another Attribute");
		nf.addAttribute("/a/b", nf.createAttribute(attr));
		nf.addAttribute("/a/b/c", nf.createAttribute(attr2));
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		GroupNode group = nf.getGroup("/a/b", false);
		assertTrue(group.containsGroupNode("c"));
		assertTrue(group.containsGroupNode("x"));
		assertTrue(group.containsDataNode("d"));
		assertEquals(group.getDataNode("d").getDataset().getSlice(), dataset);
		assertTrue(group.containsAttribute("SomeAttribute"));
		assertEquals(attr, group.getAttribute("SomeAttribute").getValue());
		assertTrue(group.getGroupNode("c").containsAttribute("Another Attribute"));
		assertEquals(attr2, group.getGroupNode("c").getAttribute("Another Attribute").getValue());

		//Make sure group has no other children
		String[] childNames = group.getNames().toArray(new String[] {});
		//we could use assertTrue(childNames.contains(...)) but this gives a much better diagnostic on failure
		Arrays.sort(childNames);
		assertArrayEquals(childNames, new String[] {"c", "d", "x"});
	}

	@Test
	public void testDataNodeProperties() throws Exception {
		IDataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("d");
		nf.createData("/a/b", dataset, true);
		IDataset attr = DatasetFactory.createFromObject(new int[] {12, 3});
		attr.setName("SomeAttribute");
		nf.addAttribute("/a/b/d", nf.createAttribute(attr));
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode dataNode = nf.getData("/a/b/d");
		assertNotNull(dataNode);
		assertEquals(dataset, dataNode.getDataset().getSlice());
		Attribute foundAttr = dataNode.getAttribute("SomeAttribute");
		assertNotNull(foundAttr);
		assertEquals(attr, foundAttr.getValue());
	}

	@Test
	public void testCreateDataPathLazyDataset() throws Exception {
		int[] shape = { 5, 5 };
		ILazyWriteableDataset dataset = new LazyWriteableDataset("data", Dataset.INT32, shape, shape, null, null);
		DataNode dataNode = nf.createData("/a/b/c", dataset, true);
		assertNotNull(dataNode);
		assertSame(dataset, dataNode.getDataset());

		GroupNode parentGroup = nf.getGroup("/a/b/c", false);
		assertSame(dataNode, parentGroup.getDataNode("data"));
	}

	@Test
	public void testCreateDataGroupNodeLazyDataset() throws Exception {
		GroupNode parentGroup = nf.getGroup("/a/b/c", true);

		int[] shape = { 5, 5 };
		ILazyWriteableDataset dataset = new LazyWriteableDataset("data", Dataset.INT32, shape, shape, null, null);
		DataNode dataNode = nf.createData(parentGroup, dataset);
		assertNotNull(dataNode);
		assertSame(dataset, dataNode.getDataset());

		assertSame(dataNode, parentGroup.getDataNode("data"));
	}

	@Test
	public void testCreateDataPathDataset() throws Exception {
		Dataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("data");
		DataNode dataNode = nf.createData("/a/b/c", dataset, true);
		assertNotNull(dataNode);
		assertSame(dataset, dataNode.getDataset());

		GroupNode parentGroup = nf.getGroup("/a/b/c", false);
		assertSame(dataNode, parentGroup.getDataNode("data"));
	}

	@Test
	public void testCreateDataGroupNodeDataset() throws Exception {
		GroupNode parentGroup = nf.getGroup("/a/b/c", true);

		Dataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("data");
		DataNode dataNode = nf.createData(parentGroup, dataset);
		assertNotNull(dataNode);
		assertSame(dataset, dataNode.getDataset());

		assertSame(dataNode, parentGroup.getDataNode("data"));
	}

	@Test
	public void testGetDataWithAugmentedPath() throws Exception {
		GroupNode parentNode = nf.getGroup("/entry1:NXentry/instrument:NXinstrument/detector:NXdetector", true);
		IDataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("data");
		nf.createData(parentNode, dataset);
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		GroupNode readNode = nf.getGroup("/entry1/instrument/detector", false);
		assertTrue(readNode.containsDataNode("data"));
		DataNode readDataNode = nf.getData("/entry1:NXentry/instrument:NXinstrument/detector:NXdetector/data");
		assertSame(readNode.getDataNode("data"), readDataNode);
		IDataset readData = readDataNode.getDataset().getSlice();
		assertEquals(dataset, readData);
	}

	@Test
	public void testAddAttributeNode() throws Exception {
		Dataset attribDataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		attribDataset.setName("testAttribute");

		GroupNode node = nf.getGroup("/a/b/c", true);
		Attribute attribute = nf.createAttribute(attribDataset);
		assertNotNull(attribute);
		nf.addAttribute(node, attribute);

		assertNotNull(node.getAttribute("testAttribute"));
		assertSame(attribute, node.getAttribute("testAttribute"));
	}

	@Test
	public void testAddAttributePath() throws Exception {
		Dataset attribDataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		attribDataset.setName("testAttribute");
		Dataset attrib2Dataset = DatasetFactory.createRange(6, Dataset.INT64).reshape(3, 2);
		attrib2Dataset.setName("testAttribute2");

		GroupNode node = nf.getGroup("/a/b/c", true);
		Attribute attribute = nf.createAttribute(attribDataset);
		assertNotNull(attribute);
		nf.addAttribute(node, attribute);
		Attribute attribute2 = nf.createAttribute(attrib2Dataset);
		assertNotNull(attribute2);
		nf.addAttribute(node, attribute2);
		assertNotNull(node.getAttribute("testAttribute"));
		assertSame(attribute, node.getAttribute("testAttribute"));
		assertNotNull(node.getAttribute("testAttribute2"));
		assertSame(attribute2, node.getAttribute("testAttribute2"));
	}

	@Test
	public void testReadbackDoubleArrayAttribute() throws Exception {
		GroupNode group = nf.getGroup("/a/b", true);
		IDataset attrData = DatasetFactory.createRange(12.0, Dataset.FLOAT64).reshape(3, 4);
		attrData.setName("attribute");
		nf.addAttribute(group, nf.createAttribute(attrData));
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		group = nf.getGroup("/a/b", false);
		Attribute attr = group.getAttribute("attribute");
		assertNotNull(attr);
		IDataset readData = attr.getValue();
		assertNotNull(readData);
		assertEquals(attrData, readData);
	}

	@Test
	public void testReadbackStringAttribute() throws Exception {
		GroupNode group = nf.getGroup("/a/b", true);
		IDataset attrString = DatasetFactory.createFromObject("SomeÅttributeString");
		attrString.setName("test");
		nf.addAttribute(group, nf.createAttribute(attrString));
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		group = nf.getGroup("/a/b", false);
		Attribute attr = group.getAttribute("test");
		assertNotNull(attr);
		if (attrString.getRank() == 0) {
			//Hack around the current ambiguity between scalar datasets (rank 0) and single element arrays
			attrString.resize(new int[] {1});
		}
		assertEquals(attrString, attr.getValue());
	}

	@Test
	public void testReadbackStringArrayAttribute() throws Exception {
		GroupNode group = nf.getGroup("/a/b", true);
		IDataset attrString = DatasetFactory.createFromObject(new String[] {"A String", "String1", "SomeÅttributeString", "String2"}).reshape(2,2);
		attrString.setName("test");
		nf.addAttribute(group, nf.createAttribute(attrString));
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		group = nf.getGroup("/a/b", false);
		Attribute attr = group.getAttribute("test");
		assertNotNull(attr);
		assertEquals(attrString, attr.getValue());
	}

	@Test
	public void testOverwritingAttribute() throws Exception {
		GroupNode group = nf.getGroup("/a", true);
		IDataset attr = DatasetFactory.createFromObject(new int[] {1, 2});
		attr.setName("test");
		nf.addAttribute(group, nf.createAttribute(attr));
		IDataset attr2 = DatasetFactory.createFromObject(new int[] {3, 4, 5});
		attr2.setName("test");
		nf.addAttribute(group, nf.createAttribute(attr2));
		nf.close();

		nf = NexusTestUtils.openNexusFile(FILE_NAME);
		group = nf.getGroup("/a", true);
		assertTrue(group.containsAttribute("test"));
		assertEquals(attr2, group.getAttribute("test").getValue());

		IDataset attr3 = DatasetFactory.createFromObject(new String[] {"test attribute"});
		attr3.setName("test");
		nf.addAttribute(group, nf.createAttribute(attr3));
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		group = nf.getGroup("/a", true);
		assertTrue(group.containsAttribute("test"));
		assertEquals(attr3, group.getAttribute("test").getValue());
	}

	@Test
	public void testLink() throws Exception {
		nf.getGroup("/a/b/c/d", true);
		nf.link("/a/b/c", "/f/g");

		GroupNode linkedGroup = nf.getGroup("/f/g", false);
		assertNotNull(linkedGroup);
		assertNotNull(linkedGroup.getGroupNode("d"));
	}

	@Test
	public void testHardLinkDataset() throws Exception {
		IDataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("data");
		nf.createData("/a/", dataset, true);
		nf.link("/a/data", "/x/data");
		DataNode dataNode = nf.getData("/a/data");
		DataNode linkedNode = nf.getData("/x/data");
		assertSame(linkedNode, nf.getData("/a/data"));
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		dataNode = nf.getData("/a/data");
		linkedNode = nf.getData("/x/data");
		assertNotNull(linkedNode);
		assertNotNull(linkedNode.getDataset().getSlice());
		assertSame(dataNode, linkedNode);
	}

	@Test
	public void testTargetAttributeAfterLink() throws Exception {
		nf.getGroup("/a/", true);
		nf.getGroup("/x/y", true);
		nf.link("/x/y", "/a/b");
		GroupNode b = nf.getGroup("/a/b", false);
		assertTrue(b.containsAttribute("target"));
		IDataset attrData = b.getAttribute("target").getValue();
		IDataset expected = DatasetFactory.createFromObject("/x/y");
		expected.resize(new int[] {1});
		expected.setName("target");
		attrData.resize(new int[] {1});
		assertEquals(expected, attrData);
	}

	@Test
	public void testLinkExternal() throws Exception {
		try (NexusFile extFile = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			extFile.getGroup("/d/e/f/g", true);
		}
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#/d/e"), "/a/b/c", true);
		GroupNode groupC = nf.getGroup("/a/b/c", false);
		GroupNode groupF = nf.getGroup(groupC, "f", null, false);
		GroupNode groupG = nf.getGroup(groupF, "g", null, false);
		assertNotNull(groupG);
		assertSame(groupG, nf.getGroup("/a/b/c/f/g", false));
	}

	@Test
	public void testLinkExternalNonExistent() throws Exception {
		nf.linkExternal(new URI("nxfile://NOT_REAL.nxs#/d/e"), "/a/b/c", true);
		nf.getGroup("/a/b", false);
	}

	@Test
	public void testLinkExternalUnderRoot() throws Exception {
		try (NexusFile extFile = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			extFile.getGroup("/d", true);
		}
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#/d"), "/a", true);
		assertNotNull(nf.getGroup("/a", false));
	}

	@Test
	public void testLinkExternalUseSourceName() throws Exception {
		try (NexusFile extFile = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			extFile.getGroup("/e/f/g", true);
		}
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#e"), "/a/b/c/d/", true);
		GroupNode groupD = nf.getGroup("/a/b/c/d", false);
		GroupNode groupE = nf.getGroup(groupD, "e", null, false);
		assertNotNull(groupE);
	}

	@Test
	public void testLinkExternalDatasetUseGivenName() throws Exception {
		IDataset externalData = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		externalData.setName("data");
		try (NexusFile ef = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			ef.createData("/a/b/c", externalData, true);
		}
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#a/b/c/data"), "/x/y/linkedData", false);
		DataNode dataNode = nf.getData("/x/y/linkedData");
		IDataset linkedData = dataNode.getDataset().getSlice();
		assertNotNull(linkedData);
		assertEquals(externalData, linkedData);
	}

	@Test
	public void testLinkExternalDatasetUseSourceName() throws Exception {
		IDataset externalData = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		externalData.setName("data");
		try (NexusFile ef = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			ef.createData("/a/b/c", externalData, true);
		}
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#a/b/c/data"), "/x/y/", false);
		DataNode dataNode = nf.getData("/x/y/data");
		IDataset linkedData = dataNode.getDataset().getSlice();
		assertNotNull(linkedData);
		assertEquals(externalData, linkedData);
	}

	@Test
	public void testLinkExternalSameFile() throws Exception {
		//"soft link" implementation
		nf.getGroup("/a/b/c", true);
		nf.linkExternal(new URI("#/a/b"), "/x", true);
		GroupNode groupXC = nf.getGroup("/x/c", false);
		GroupNode groupABC = nf.getGroup("/a/b/c", false);
		assertNotNull(groupXC);
		assertSame(groupXC, groupABC);
	}

	@Test
	public void testSoftLinkDataset() throws Exception {
		IDataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("data");
		nf.createData("/a/", dataset, true);
		nf.linkExternal(new URI("#/a/data"), "/x/", false);
		DataNode dataNode = nf.getData("/a/data");
		DataNode linkedDataNode = nf.getData("/x/data");
		assertSame(dataNode, linkedDataNode);
	}

	@Test
	public void testRelativeExternalLink() throws Exception {
		IDataset externalData = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		externalData.setName("data");
		try (NexusFile ef = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			ef.createData("/a/b/c", externalData, true);
		}
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME.replaceFirst(testScratchDirectoryName, "") + "#a/b/c/data"), "/x/y/", false);
		DataNode dataNode = nf.getData("/x/y/data");
		IDataset linkedData = dataNode.getDataset().getSlice();
		assertNotNull(linkedData);
		assertEquals(externalData, linkedData);
	}

	@Test
	public void testIsPathValid() throws Exception {
		nf.getGroup("/a/b/c", true);
		assertTrue(nf.isPathValid("/a/b/c"));
		assertFalse(nf.isPathValid("/a/b/c/d"));
	}

	@Test
	public void testWritingSimpleStringArray() throws Exception {
		GroupNode g = nf.getGroup("/entry1:NXentry", true);
		NexusUtils.write(nf, g, "stringarray", new String[] {"String", "String Å"});
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode d = nf.getData("/entry1/stringarray");
		IDataset ds = d.getDataset().getSlice();
		int[] shape = ds.getShape();
		assertArrayEquals(new int[] {2}, shape);
	}

	@Test
	public void testWriteSimpleString() throws Exception {
		GroupNode g = nf.getGroup("/note:NXnote", true);
		NexusUtils.write(nf, g, "somestring", "MyString");
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode d = nf.getData("/note/somestring");
		IDataset ds = d.getDataset().getSlice();
		int[] shape = ds.getShape();
		assertArrayEquals(new int[] {1}, shape);
		assertEquals("MyString", ds.getString(0));
	}

	@Test
	public void testLazyWriteStringArray() throws Exception {
		int nPoints = 10;

		GroupNode g = nf.getGroup("/test:NXnote", true);
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("stringarray", Dataset.STRING,
				new int[] {ILazyWriteableDataset.UNLIMITED}, null, null);
		nf.createData(g, lazy);
		nf.close();

		for (int i = 0; i < nPoints; i++) {
			lazy.setSlice(null, DatasetFactory.createFromObject("file" + i),
					SliceND.createSlice(lazy, new int[] {i}, new int[] {i+1}));
		}

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode d = nf.getData("/test/stringarray");
		IDataset ds = d.getDataset().getSlice();
		int[] shape = ds.getShape();
		assertArrayEquals(new int[] {nPoints}, shape);
		for (int i = 0; i < nPoints; i++) {
			assertEquals("file" + i, ds.getString(i));
		}
	}

	@Test
	public void testLazyWrite2DInt32Array() throws Exception {
		GroupNode g = nf.getGroup("/test:NXnote", true);
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("intarray", Dataset.INT32,
				new int[] {ILazyWriteableDataset.UNLIMITED, 10}, null, null);
		nf.createData(g, lazy);
		lazy.setSlice(null, DatasetFactory.createFromObject(new int[] {-1, -1, -1, -1}).reshape(2, 2), new int[] {0, 0}, new int[] {2, 2}, null);
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode node = nf.getData("/test/intarray");
		Dataset data = DatasetUtils.convertToDataset(node.getDataset().getSlice(new int[] {0, 0}, new int[] {2, 2}, new int[] {1, 1}));
		assertArrayEquals(new int[] {-1, -1, -1, -1}, (int[]) data.getBuffer());
	}

	@Test
	public void testLazyWrite2DDoubleArray() throws Exception {
		GroupNode g = nf.getGroup("/test:NXnote", true);
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("doublearray", Dataset.FLOAT64,
				new int[] {ILazyWriteableDataset.UNLIMITED, 10}, null, null);
		nf.createData(g, lazy);
		lazy.setSlice(null, DatasetFactory.createFromObject(new double[] {1, 2, 3, 4}).reshape(2, 2), new int[] {0, 0}, new int[] {2, 2}, null);
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode node = nf.getData("/test/doublearray");
		Dataset data = DatasetUtils.convertToDataset(node.getDataset().getSlice(new int[] {0, 0}, new int[] {2, 2}, new int[] {1, 1}));
		assertArrayEquals(new double[] {1, 2, 3, 4}, (double[]) data.getBuffer(), 1e-12);
	}
	
	@Test
	public void testLazyRelativeWriteSpeed() throws Exception {
		
		ILazyWriteableDataset lazy3 = createLazyDouble(3);
		ILazyWriteableDataset lazy4 = createLazyDouble(4);
		ILazyWriteableDataset lazy5 = createLazyDouble(5);
		ILazyWriteableDataset lazy8 = createLazyDouble(8);
		nf.close();
			
		IDataset image = Random.rand(1024,1024);
		
		long diff3 = checkWrite(lazy3, image);
		long diff4 = checkWrite(lazy4, image);
		long diff5 = checkWrite(lazy5, image);
		long diff8 = checkWrite(lazy8, image);

		long perf = Math.max(20,diff3*2);
		assertTrue(diff4<perf); // Might not be with default chunking.
		assertTrue(diff5<perf); // Might not be with default chunking.
		assertTrue(diff8<perf); // Might not be with default chunking.

	}


	private long checkWrite(ILazyWriteableDataset lazy, IDataset image) throws Exception {
		
		int size = lazy.getShape().length;
		int[] start = new int[size];
		int[] end   = new int[size];
		for (int i = 0; i < size; i++) {
			start[i]=0;
			end[i]=1;
		}
		end[end.length-2] = 1024;
		end[end.length-1] = 1024;
		long before = System.currentTimeMillis();
		lazy.setSlice(null, image, start, end, null);
		long after = System.currentTimeMillis();
		long diff = after-before;
		System.out.println("Writing 1 image in "+size+"D stack took: "+diff+" ms");
        return diff;
	}

	private ILazyWriteableDataset createLazyDouble(int size) throws NexusException {
		
		GroupNode group = nf.getGroup("/test_"+size+"D:NXnote", true);
		
		int[] max = new int[size];
		int[]chunk = new int[size];
		for (int i = 0; i < size-2; i++) {
			max[i]=ILazyWriteableDataset.UNLIMITED;
			chunk[i]=1;
		}
		max[max.length-2] = 1024;
		max[max.length-1] = 1024;
		chunk[max.length-2] = 1024;
		chunk[max.length-1] = 1024;
		
		final ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("doublearray", Dataset.FLOAT64, max, null, chunk);
		nf.createData(group, lazy);
		return lazy;
	}

	@Test
	public void testLazyWrite2DStringArray() throws Exception {
		GroupNode g = nf.getGroup("/test:NXnote", true);
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("stringarray", Dataset.STRING,
				new int[] {2, 10}, new int[] {ILazyWriteableDataset.UNLIMITED, 10}, null);
		nf.createData(g, lazy);
		lazy.setSlice(null, DatasetFactory.createFromObject(new String[] {"Value1", "Value2"}).reshape(2, 1), new int[] {2, 0}, new int[] {4, 1}, null);
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode node = nf.getData("/test/stringarray");
		IDataset data = node.getDataset().getSlice();
		assertEquals("Value1", data.getString(2, 0));
	}

	@Test
	public void testLazyDatasetMaxShape() throws Exception {
		int[] maxShape = new int[] {ILazyWriteableDataset.UNLIMITED, 10, 100};
		ILazyWriteableDataset lazyData = NexusUtils.createLazyWriteableDataset("data", Dataset.INT32,
				new int[] {10, 10, 10}, maxShape, null);
		nf.createData("/a/", lazyData, true);
		nf.close();

		nf = NexusTestUtils.openNexusFile(FILE_NAME);
		ILazyWriteableDataset readData = nf.getData("/a/data").getWriteableDataset();
		assertNotNull(readData);
		assertArrayEquals(maxShape, readData.getMaxShape());
	}

	@Test
	public void testWriteAfterHardLink() throws Exception {
		//Have had bugs where writing created a "new" dataset, leaving previously
		//hard linked nodes un-updated
		ILazyWriteableDataset lazyData = NexusUtils.createLazyWriteableDataset("c", Dataset.INT32,
				new int[] {ILazyWriteableDataset.UNLIMITED}, null, null);
		GroupNode g = nf.getGroup("/a/b", true);
		nf.createData(g, lazyData);
		nf.link("/a/b/c", "/x/");
		lazyData.setSlice(null, DatasetFactory.createFromObject(1), new int[] {0}, new int[] {1}, null);
		assertSame(nf.getData("/a/b/c").getDataset(), nf.getData("/x/c").getDataset());
		nf.flush();
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		assertEquals(nf.getData("/a/b/c").getDataset(), nf.getData("/x/c").getDataset());
	}

	@Test
	public void testNxClassGroup() throws Exception {
		GroupNode g = nf.getGroup("/entry1:NXentry/note:NXnote", true);
		GroupNode e = nf.getGroup("/entry1", false);
		Attribute gAttr = g.getAttribute("NX_class");
		Attribute eAttr = e.getAttribute("NX_class");
		assertNotNull(gAttr);
		assertArrayEquals(gAttr.getValue().getShape(), new int[] {1});
		assertEquals(gAttr.getValue().getString(0), "NXnote");
		assertNotNull(eAttr);
		assertArrayEquals(eAttr.getValue().getShape(), new int[] {1});
		assertEquals(eAttr.getValue().getString(0), "NXentry");

		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		g = nf.getGroup("/entry1:NXentry/note:NXnote", true);
		e = nf.getGroup("/entry1", false);
		gAttr = g.getAttribute("NX_class");
		eAttr = e.getAttribute("NX_class");
		assertNotNull(gAttr);
		assertArrayEquals(gAttr.getValue().getShape(), new int[] {1});
		assertEquals(gAttr.getValue().getString(0), "NXnote");
		assertNotNull(eAttr);
		assertArrayEquals(eAttr.getValue().getShape(), new int[] {1});
		assertEquals(eAttr.getValue().getString(0), "NXentry");
	}

	@Test
	public void testRelativeNapiMountToGroup() throws Exception {
		GroupNode g = nf.getGroup("/e/g", true);
		Dataset mountData = DatasetFactory.createFromObject(new String[] {"nxfile://" + FILE2_NAME.replaceFirst(testScratchDirectoryName, "") + "#x/y"});
		mountData.setName("napimount");
		nf.addAttribute( g, nf.createAttribute(mountData) );
		nf.close();
		nf = NexusTestUtils.createNexusFile(FILE2_NAME);
		nf.getGroup("/x/y/z", true);
		nf.close();
		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		g = nf.getGroup("/e/g", false);
		assertTrue(g.containsGroupNode("z"));
		assertNotNull(nf.getGroup("/e/g/z/", false));
		nf.close();
	}

	@Test
	public void testRelativeNapiMountToDataset() throws Exception {
		GroupNode g = nf.getGroup("/e/g", true);
		Dataset mountData = DatasetFactory.createFromObject(new String[] {"nxfile://" + FILE2_NAME.replaceFirst(testScratchDirectoryName, "") + "#x/y/z"});
		mountData.setName("napimount");
		nf.addAttribute( g, nf.createAttribute(mountData) );
		nf.close();
		Dataset dummyData = DatasetFactory.createFromObject(new int[] {0, 1, 2});
		dummyData.setName("z");
		try (NexusFile extFile = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			extFile.createData(extFile.getGroup("/x/y", true), dummyData);
		}
		try (NexusFile oFile = NexusTestUtils.openNexusFileReadOnly(FILE_NAME)) {
			g = oFile.getGroup("/e", false);
			assertFalse(g.containsDataNode("g")); // NAPI mount from group to dataset not supported
			oFile.getData("/e/g");
			fail("NAPI mount from group to dataset not supported");
		} catch (IllegalArgumentException e) { // throws this
		}
	}

	@Test
	public void testAbsoluteNapiMountToGroup() throws Exception {
		try (NexusFile origin = NexusTestUtils.createNexusFile(testScratchDirectoryName + "origin/test.nxs");
				NexusFile linked = NexusTestUtils.createNexusFile(testScratchDirectoryName + "linked/linked.nxs")) {
			GroupNode g = origin.getGroup("/a/b", true);
			linked.getGroup("/x/y/z", true);
			Dataset mountData = DatasetFactory.createFromObject(new String[] {"nxfile://" + testScratchDirectoryName + "linked/linked.nxs#x/y/"});
			mountData.setName("napimount");
			origin.addAttribute( g, nf.createAttribute(mountData) );
		}
		try (NexusFile origin = NexusTestUtils.openNexusFileReadOnly(testScratchDirectoryName + "origin/test.nxs")) {
			GroupNode g = origin.getGroup("/a/b", false);
			GroupNode g2 = origin.getGroup("/a/b/z", false);
			assertNotNull(g2);
			assertNotNull(g);
			assertTrue(g.containsGroupNode("z"));
			assertSame(g.getGroupNode("z"), g2);
		}
	}

	@Test
	public void testAbsoluteNapiMountToDataset() throws Exception {
		Dataset dummyData = DatasetFactory.createFromObject(new int[] {0, 1, 2});
		dummyData.setName("z");
		try (NexusFile origin = NexusTestUtils.createNexusFile(testScratchDirectoryName + "origin/test.nxs");
				NexusFile linked = NexusTestUtils.createNexusFile(testScratchDirectoryName + "linked/linked.nxs")) {
			GroupNode g = origin.getGroup("/a/b/d", true);
			Dataset mountData = DatasetFactory.createFromObject(new String[] {"nxfile://" + testScratchDirectoryName + "linked/linked.nxs#x/y/z"});
			mountData.setName("napimount");
			origin.addAttribute(g, origin.createAttribute(mountData));
			GroupNode l = linked.getGroup("/x/y/", true);
			linked.createData(l, dummyData);
		}
		try (NexusFile origin = NexusTestUtils.openNexusFileReadOnly(testScratchDirectoryName + "origin/test.nxs")) {
			GroupNode group = origin.getGroup("/a/b", false);
			assertFalse(group.containsDataNode("d"));
			origin.getData(group, "d");
			fail("NAPI mount from group to dataset not supported");
		} catch (IllegalArgumentException e) { // throws this
		}
	}

	@Test
	public void testNapiMountToGroupThenDataset() throws Exception {
		Dataset dummyData = DatasetFactory.createFromObject(new int[] {0, 1, 2});
		dummyData.setName("z");
		try (NexusFile origin = NexusTestUtils.createNexusFile(testScratchDirectoryName + "origin/test.nxs");
				NexusFile linked = NexusTestUtils.createNexusFile(testScratchDirectoryName + "linked/linked.nxs")) {
			GroupNode g = origin.getGroup("/a/b/d", true);
			Dataset mountData = DatasetFactory.createFromObject(new String[] {"nxfile://" + testScratchDirectoryName + "linked/linked.nxs#w/x"});
			mountData.setName("napimount");
			origin.addAttribute(g, origin.createAttribute(mountData));
			GroupNode l = linked.getGroup("/w/x/y/", true);
			linked.createData(l, dummyData);
		}
		try (NexusFile origin = NexusTestUtils.openNexusFileReadOnly(testScratchDirectoryName + "origin/test.nxs")) {
			DataNode dataNode = origin.getData("/a/b/d/y/z");
			assertNotNull(dataNode);
			GroupNode groupNode = origin.getGroup("/a/b/d/y", false);
			assertNotNull(groupNode);
			assertTrue(groupNode.containsDataNode("z"));
			assertSame(groupNode.getDataNode("z"), dataNode);
			IDataset readData = dataNode.getDataset().getSlice();
			assertNotNull(readData);
			assertEquals(dummyData, readData);
		}
	}

	@Test
	public void testCreateCompressedDataDeflate() throws Exception {
		//Not a very elegant test
		int[] chunking = new int[] {200, 200};
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("d", Dataset.FLOAT64,
				new int[] {ILazyWriteableDataset.UNLIMITED, 1000}, null, null);
		lazy.setChunking(chunking);
		nf.createData("/a", lazy, true);
		lazy.setSlice(null, DatasetFactory.createRange(1, 1000000, Dataset.FLOAT64).reshape(1000, 1000),
				new int[] {0, 0}, new int[] {1000, 1000}, null);
		nf.close();
		try (NexusFile cf = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			lazy = NexusUtils.createLazyWriteableDataset("d", Dataset.FLOAT64,
					new int[] {ILazyWriteableDataset.UNLIMITED, 1000}, null, null);
			lazy.setChunking(chunking);
			cf.createData("/a", lazy, NexusFile.COMPRESSION_LZW_L1, true);
			lazy.setSlice(null, DatasetFactory.createRange(1, 1000000, Dataset.FLOAT64).reshape(1000, 1000),
					new int[] {0, 0}, new int[] {1000, 1000}, null);
		}
		File uncompressed = new File(FILE_NAME);
		File compressed = new File(FILE2_NAME);
		assertTrue(compressed.length() < uncompressed.length());
	}

	@Test
	public void testChunking() throws Exception {
		int[] chunking = new int[] {1, 256, 256};
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("data", Dataset.FLOAT64,
				new int[] {1 ,100, 100}, new int[] {ILazyWriteableDataset.UNLIMITED, 1024, 1024}, null);
		lazy.setChunking(chunking);
		nf.createData("/a/", lazy, true);
		assertArrayEquals(chunking, lazy.getChunking());
		lazy.setSlice(null, DatasetFactory.createRange(1, 1024 * 1024 * 8, Dataset.FLOAT64).reshape(8, 1024, 1024),
				new int[] {0, 0, 0}, new int[] {8, 1024, 1024}, null);
		nf.close();

		nf = NexusTestUtils.openNexusFile(FILE_NAME);
		ILazyWriteableDataset readData = nf.getData("/a/data").getWriteableDataset();
		assertNotNull(readData);
		assertArrayEquals(chunking, readData.getChunking());
	}

	@Test
	public void testDefaultChunking() throws Exception {
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("data", Dataset.FLOAT64,
				new int[] {1 ,100, 100}, new int[] {ILazyWriteableDataset.UNLIMITED, 1000, 1000}, null);
		nf.createData("/a", lazy, true);
		int[] newChunking = lazy.getChunking();
		assertNotNull(newChunking);
		assertTrue(newChunking[0] >= 1);
		assertTrue(newChunking[1] >= 32);
		assertTrue(newChunking[2] >= 32);
		//our chunks should be at *least* 16kb on this sort of dataset
		assertTrue(newChunking[0] * newChunking[1] * newChunking[2] * 8 >= 16 * 1024);
		//we should not exceed 1MB either
		assertTrue(newChunking[0] * newChunking[1] * newChunking[2] * 8 <= 1024 * 1024);
		//should favour the last axis
		assertTrue(newChunking[2] >= newChunking[1] && newChunking[2] >= newChunking[0]);
	}

	@Test
	public void testSensibleChunkingOverride() throws Exception {
		int[] originalChunking = new int[] {1, 1, 1};
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("data", Dataset.FLOAT64,
				new int[] {1 ,100, 100}, new int[] {ILazyWriteableDataset.UNLIMITED, 1000, 1000}, null);
		lazy.setChunking(originalChunking);
		nf.createData("/a", lazy, true);
		int[] newChunking = lazy.getChunking();
		assertFalse(Arrays.equals(originalChunking, newChunking));
		assertTrue(newChunking[0] >= 1);
		assertTrue(newChunking[1] >= 32);
		assertTrue(newChunking[2] >= 32);
		assertTrue(newChunking[0] * newChunking[1] * newChunking[2] * 8 >= 16 * 1024);
		assertTrue(newChunking[0] * newChunking[1] * newChunking[2] * 8 <= 1024 * 1024);
		assertTrue(newChunking[2] >= newChunking[1] && newChunking[2] >= newChunking[0]);
	}

	@Test
	public void testAddGroupNode() throws Exception {
		GroupNode groupNode = new GroupNodeImpl("/base/g".hashCode());
		DataNode dataNode = new DataNodeImpl("/base/g/d".hashCode());
		Dataset dataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		dataset.setName("d");
		dataNode.setDataset(dataset);
		groupNode.addDataNode(dataset.getName(), dataNode);
		GroupNode base = nf.getGroup("/base", true);
		nf.addNode(base, "g", groupNode);

		GroupNode readG = nf.getGroup("/base/g", false);
		assertNotNull(readG);
		assertTrue(readG.containsDataNode("d"));
		DataNode readDataNode = readG.getDataNode("d");
		assertNotNull(readDataNode);
		assertSame(readDataNode, nf.getData("/base/g/d"));
		IDataset readData = readDataNode.getDataset().getSlice();
		assertEquals(dataset, readData);
		assertFalse(dataset == readData); // we probably shouldn't be referencing the same object
	}

	@Test
	public void testUpdateNodes() throws Exception {
		nf.getGroup("/a/b/c", true);
		Dataset attrData = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		attrData.setName("atr");
		Attribute attr = nf.createAttribute(attrData);
		GroupNode base = nf.getGroup("/a", false);
		GroupNode b = new GroupNodeImpl("/a/b".hashCode());
		b.addAttribute(attr);
		GroupNode x = new GroupNodeImpl("/a/b/x".hashCode());
		b.addGroupNode("x", x);
		nf.addNode(base, "b", b);

		assertNotNull(nf.getGroup("/a/b/x", false));
		GroupNode readB = nf.getGroup("/a/b", false);
		assertTrue(readB.containsAttribute("atr"));
		Attribute readAttr = readB.getAttribute("atr");
		assertEquals(attrData, readAttr.getValue());
	}

	@Test
	public void testAddLazyDataNode() throws Exception {
		GroupNode g = new GroupNodeImpl("/base/g".hashCode());
		DataNode lazyDataNode = new DataNodeImpl("/base/g/intarray".hashCode());
		ILazyWriteableDataset lazy = NexusUtils.createLazyWriteableDataset("intarray", Dataset.INT32,
				new int[] { ILazyWriteableDataset.UNLIMITED, 10 }, null, null);
		lazyDataNode.setDataset(lazy);
		g.addDataNode("intarray", lazyDataNode);
		GroupNode base = nf.getGroup("/base", true);
		nf.addNode(base, "g", g);

		DataNode readNode = nf.getData("/base/g/intarray");
		assertNotNull(readNode);
		assertSame(lazy, readNode.getDataset());
		nf.close();
		nf = null;

		lazy.setSlice(null, DatasetFactory.createFromObject(new int[] { -1, -1, -1, -1 }).reshape(2, 2),
				new int[] { 0, 0 }, new int[] { 2, 2 }, null);

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode node = nf.getData("/base/g/intarray");
		Dataset data = DatasetUtils.convertToDataset(node.getDataset().getSlice(new int[] { 0, 0 }, new int[] { 2, 2 }, new int[] { 1, 1 }));
		assertArrayEquals(new int[] { -1, -1, -1, -1 }, (int[]) data.getBuffer());
	}

	@Test
	public void testAddNodeWithHardlinks() throws Exception {
		GroupNode g = new GroupNodeImpl("/base/g".hashCode());
		GroupNode h = new GroupNodeImpl("/base/g/h".hashCode());
		g.addGroupNode("h", h);
		// /base/g/i = hardlink to /base/g/h
		g.addGroupNode("i", h);
		GroupNode j = new GroupNodeImpl("/base/g/j".hashCode());
		g.addGroupNode("j", j);
		GroupNode k = new GroupNodeImpl("/base/g/h/k".hashCode());
		h.addGroupNode("k", k);
		// /base/g/h/k/l hardlink to /base/g/j
		k.addGroupNode("l", j);

		GroupNode base = nf.getGroup("/base", true);
		nf.addNode(base, "g", g);

		GroupNode readH = nf.getGroup("/base/g/h", false);
		GroupNode readI = nf.getGroup("/base/g/i", false);
		assertNotNull(readH);
		assertSame(readH, readI);

		GroupNode readL = nf.getGroup("/base/g/h/k/l", false);
		GroupNode readJ = nf.getGroup("/base/g/j", false);
		assertNotNull(readL);
		assertSame(readJ, readL);

		assertFalse(readH == readL);

		nf.close();
		nf = NexusTestUtils.openNexusFile(FILE_NAME);

		readH = nf.getGroup("/base/g/h", false);
		readI = nf.getGroup("/base/g/i", false);
		assertNotNull(readH);
		assertSame(readH, readI);

		readL = nf.getGroup("/base/g/h/k/l", false);
		readJ = nf.getGroup("/base/g/j", false);
		assertNotNull(readL);
		assertSame(readJ, readL);

		assertFalse(readH == readL);
	}

	@Test
	public void testLazyWriteFromReadbackNode() throws Exception {
		nf.close();
		IDataset ods = DatasetFactory.createFromObject(new int[] {10, 20, 30});
		try (NexusFile nf2 = NexusTestUtils.createNexusFile(FILE_NAME)) {
			ILazyWriteableDataset lds = new LazyWriteableDataset("data",
					Dataset.INT32,
					new int[] {0},
					new int[] {ILazyWriteableDataset.UNLIMITED},
					new int[] {3},
					null);
			nf2.createData("/test/", lds, true);
			nf2.flush();
		}
		try (NexusFile nf = NexusTestUtils.openNexusFile((FILE_NAME))) {
			ILazyWriteableDataset lds = nf.getData("/test/data").getWriteableDataset();
			lds.setSlice(null, ods, new int[] {0}, new int[] {3}, null);
		}
	}

	@Test
	public void testExternalLinkToDelayedFile() throws Exception {
		File f2 = new File(FILE2_NAME);
		if (f2.exists()) {
			if (!f2.delete()) {
				throw new Exception("Could not delete previous external file");
			}
		}
		IDataset data = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		data.setName("d");
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#a/"), "/x", true);
		nf.linkExternal(new URI("nxfile://" + FILE2_NAME + "#g/d"), "/l/d", false);
		assertTrue(nf.isPathValid("/x"));
		assertTrue(nf.isPathValid("/l/d"));
		try {
			nf.getGroup("/x", false);
			fail("Should not be able to traverse external link node when external file does not exist");
		} catch (Exception e) {
		}
		try {
			nf.getData("/l/d");
			fail("Should not be able to open external data node when external file does not exist");
		} catch (Exception e) {
		}
		try (NexusFile ef = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			ef.getGroup("/a/b", true);
			ef.getGroup("/a/c", true);
			ef.createData("/g", data, true);
		}
		GroupNode x = nf.getGroup("/x", false);
		assertNotNull(x);
		assertTrue(x.containsGroupNode("b"));
		assertTrue(x.containsGroupNode("c"));
		DataNode d = nf.getData("/l/d");
		assertNotNull(d);
		assertEquals(data, d.getDataset().getSlice());
	}

	@Test
	public void testAddExternalLinkNode() throws Exception {
		IDataset data = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		data.setName("data");
		try (NexusFile nf2 = NexusTestUtils.createNexusFile(FILE2_NAME)) {
			nf2.getGroup("/ext/group/a", true);
			nf2.createData("/ext", data, true);
		}
		GroupNode g = new GroupNodeImpl("/g".hashCode());
		SymbolicNode ed = new SymbolicNodeImpl("/g/ed".hashCode(), new URI(FILE2_NAME), null, "/ext/data");
		SymbolicNode eg = new SymbolicNodeImpl("/g/eg".hashCode(), new URI(FILE2_NAME), null, "/ext/group/");
		g.addNode("ed", ed);
		g.addNode("eg", eg);
		nf.addNode("/g", g);
		nf.close();
		nf.openToRead();
		GroupNode readG = nf.getGroup("/g", false);
		assertTrue(readG.containsGroupNode("eg"));
		assertTrue(readG.containsDataNode("ed"));
		GroupNode readEg = nf.getGroup("/g/eg", false);;
		assertTrue(readEg.containsGroupNode("a"));
		assertEquals(data, nf.getData(readG, "ed").getDataset().getSlice());
	}

	@Test
	public void testHardLinkWithExistingTargetAttribute() throws Exception {
		// test that creating a hard link does not overwrite an already set "target" attribute
		IDataset target = DatasetFactory.createFromObject("/g/d");
		target.setName("target");
		GroupNode h = nf.getGroup("/g/h/", true);
		Attribute t = nf.createAttribute(target);
		nf.addAttribute(h, t);
		nf.link("/g/h", "/g/d"); // would normally set target to /g/h
		GroupNode d = nf.getGroup("/g/d", false);
		IDataset readBack = d.getAttribute("target").getValue();
		if (readBack.getRank() == 0) {
			readBack.resize(new int[] {1});
		}
		assertEquals(target, readBack.getSlice());
		nf.close();
		nf.openToRead();
		d = nf.getGroup("/g/d", false);
		readBack = d.getAttribute("target").getValue();
		if (readBack.getRank() == 0) {
			readBack.resize(new int[] {1});
		}
		assertEquals(target, readBack.getSlice());
	}
}
