/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyWriteableDataset;
import org.junit.BeforeClass;
import org.junit.Test;

public class NexusFileIntegrationTest {
	static String testScratchDirectoryName;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(NexusFileIntegrationTest.class.getCanonicalName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
		TestUtils.populateNexusFileFactory();
	}

	@Test
	public void testNexusFile() throws Exception {
		String name = testScratchDirectoryName + "test.nxs";
		NexusFile nf = NexusUtils.createNexusFile(name);

		GroupNode g = nf.getGroup("/e/a/b", true);
		Dataset a = DatasetFactory.createFromObject("world");
		a.setName("hello");

		nf.addAttribute(g, nf.createAttribute(a));

		int[] shape = new int[] {2, 34};
		int[] mshape = new int[] {ILazyWriteableDataset.UNLIMITED, 34};
		LazyWriteableDataset d = new LazyWriteableDataset("d", Dataset.INT16, shape, mshape, null, null);
		nf.createData(g, d);

		LazyWriteableDataset e = new LazyWriteableDataset("e", Dataset.FLOAT64, shape, mshape, null, null);
		nf.createData(g, e);

		a = DatasetFactory.createFromObject(-1.5);
		a.setName("value");
		nf.addAttribute(g.getDataNode(d.getName()), nf.createAttribute(a));

		LazyWriteableDataset t = new LazyWriteableDataset("t", Dataset.STRING, shape, mshape, null, null);
		nf.createData(g, t);

		a = DatasetFactory.createRange(10, Dataset.FLOAT32).reshape(2, 5);
		a.setName("value");
		nf.createData("/entry", a, true);
		a.iadd(1);
		try {
			nf.createData("/entry", a, false);
			fail("Must not be able to create a dataset on top of an existing one");
		} catch (NexusException ex) {
			// do nothing
		}

		nf.close();

		SliceND slice = new SliceND(shape, new Slice(2), new Slice(10, 11));
		d.setSlice(DatasetFactory.zeros(slice.getShape(), Dataset.INT16).fill(-5), slice);

		SliceND eSlice = SliceND.createSlice(e, new int[] {2, 3}, new int[] {4, 34});
		e.setSlice(DatasetFactory.zeros(eSlice.getShape(), Dataset.INT16).fill(-9), eSlice);

		t.setSlice(DatasetFactory.createFromObject(new String[] {"Hello", "World"}).reshape(2, 1),
				SliceND.createSlice(t, new int[] {2, 3}, new int[] {4, 4}));

		nf.openToRead();
		g = nf.getGroup("/e/a/b", false);
		checkGroup(g);

		DataNode n = nf.getData("/e/a/b/d");
		checkData(n, shape);

		n = nf.getData("/e/a/b/e");
		checkEData(n, new int[] {4, 34});

		n = nf.getData("/e/a/b/t");
		checkTextData(n, new int[] {4, 34});

		nf.close();

		nf.openToWrite(false);
		nf.link("/e/a/b", "/f/c");

		nf.linkExternal(new URI("nxfile:///./"+name+"#/e/a/b/d"), "/g", false);
		nf.close();

		nf.openToRead();
		g = nf.getGroup("/f/c", false);
		checkGroup(g);

		n = g.getDataNode("d");
		checkData(n, shape);

		n = g.getDataNode("t");
		checkTextData(n, new int[] {4, 34});

		n = nf.getData("/g");
		checkData(n, shape);

		n = nf.getData("/entry/value");
		IDataset b = n.getDataset().getSlice();
		assertArrayEquals(new int[] {2, 5}, b.getShape());
		assertEquals(Float.class, b.elementClass());
		assertEquals(0.0, b.getDouble(0, 0), 1e-15);
		assertEquals(9.0, b.getDouble(1, 4), 1e-15);
		nf.close();
	}

	private void checkGroup(GroupNode g) {
		assertTrue(g.containsAttribute("hello"));
		assertEquals("world", g.getAttribute("hello").getValue().getString());
		assertTrue(g.isPopulated() && g.containsDataNode("d"));
	}

	private void checkData(DataNode n, int[] shape) {
		assertTrue(n.containsAttribute("value"));
		assertEquals(-1.5, n.getAttribute("value").getValue().getDouble(), 1e-15);
		ILazyDataset b = n.getDataset();
		assertTrue(b.elementClass().equals(Short.class));
		assertArrayEquals(shape, b.getShape());
		IDataset bs = b.getSlice();
		assertEquals(0, bs.getLong(0, 0));
		assertEquals(-5, bs.getLong(1, 10));
	}

	private void checkEData(DataNode n, int[] shape) {
		ILazyDataset b = n.getDataset();
		assertTrue(b.elementClass().equals(Double.class));
		assertArrayEquals(shape, b.getShape());
		IDataset bs = b.getSlice();
		assertEquals(Double.NaN, bs.getDouble(0, 0), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(0, 2), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(0, 10), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(1, 0), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(1, 2), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(1, 10), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(2, 0), 1e-12);
		assertEquals(Double.NaN, bs.getDouble(2, 2), 1e-12);
		assertEquals(-9, bs.getDouble(2, 10), 1e-12);
	}

	private void checkTextData(DataNode n, int[] shape) {
		ILazyDataset b = n.getDataset();
		assertTrue(b.elementClass().equals(String.class));
		// NAPI is broken wrt strings so skip for time being
		assertArrayEquals(shape, b.getShape());
		IDataset bs = b.getSlice();
		assertEquals("null", bs.getString(0, 0));
		assertEquals("Hello", bs.getString(2, 3));
		assertEquals("World", bs.getString(3, 3));
	}

	@Test
	public void testLinked() throws NexusException {
		String d = "testfiles/dawnsci/data/nexus/";
		String n = "testlinks.nxs";

		NexusFile f = NexusUtils.openNexusFileReadOnly(d + n);

		// original
		int[] shape;
		IDataset ds;
		shape = new int[] {25, 3};
		ds = f.getData("/entry1/to/this/level/d1").getDataset().getSlice();
		assertArrayEquals(shape, ds.getShape());
		assertEquals(1, ds.getInt(0, 1));
		assertEquals(5, ds.getInt(1, 2));
		assertEquals(37, ds.getInt(12, 1));

		shape = new int[] {2, 5};
		ds = f.getData("/d_el").getDataset().getSlice();
		assertArrayEquals(shape, ds.getShape());
		assertEquals(1., ds.getDouble(0, 1), 1e-8);
		assertEquals(9., ds.getDouble(1, 4), 1e-8);

		//NAPI mounts to datasets are not valid, so skip
		/*
		ds = f.getData("/entry1/to/this/level/extdst").getDataset().getSlice();
		assertArrayEquals(shape, ds.getShape());
		assertEquals(1., ds.getDouble(0, 1), 1e-8);
		assertEquals(9., ds.getDouble(1, 4), 1e-8);
		*/

		// cannot get string attributes written by h5py(!!!)
		GroupNode g = f.getGroup("/g_el/lies", false);
		assertEquals("ballyho", g.getAttribute("a1").getFirstElement());
	}
}