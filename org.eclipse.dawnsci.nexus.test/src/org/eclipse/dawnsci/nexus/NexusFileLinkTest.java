/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;

import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class NexusFileLinkTest {
	static String testScratchDirectoryName;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(NexusFileLinkTest.class.getCanonicalName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
	}

	@Test
	public void testAuthority() throws Exception {
		// explore how URI is decomposed
		printURI(new URI("nxfile://top/file#/node"));
		printURI(new URI("nxfile:///top/file#/node"));
		printURI(new URI("nxfile://./top/file#/node"));
		printURI(new URI("nxfile:///./top/file#/node"));
	}

	void printURI(URI uri) {
		System.out.println("URI      : " + uri);
		System.out.println("Authority: " + uri.getAuthority());
		System.out.println("Path     : " + uri.getPath());
		String a = uri.getAuthority();
		String p = uri.getPath();
		if (a != null) {
			p = a + p;
		}
		System.out.println("Combined : " + p);
		System.out.println("Fragment : " + uri.getFragment());
		System.out.println("");
	}

	@Test
	public void testExternalLinks() throws Exception {
		Dataset a = DatasetFactory.createRange(10, Dataset.FLOAT32).reshape(2, 5);
		a.setName("value");

		String ename;
		String tname;
		NexusFile nf;
		GroupNode g;

		// create file where /e/f/g -> a so /e/f/g/b/c is available
		ename = createExternalFile(testScratchDirectoryName + "external1.nxs", a);
		tname = testScratchDirectoryName + "test1.nxs";
		nf = NexusTestUtils.createNexusFile(tname);
		nf.linkExternal(new URI("nxfile://./" + ename + "#/a"), "/e/f/g", true);
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(tname);
		g = nf.getGroup("/e/f/g", false);
		assertNotNull(g);
		assertTrue(g.containsAttribute("top"));
		assertEquals("world", g.getAttribute("top").getFirstElement());
		g = nf.getGroup(g, "b", null, false);
		g = nf.getGroup("/e/f/g/b", false);
		assertNotNull(g);
		g = nf.getGroup("/e/f/g/b/c", false);
		assertNotNull(g);
		assertTrue(g.containsDataNode("value"));
		assertEquals(a, g.getDataNode("value").getDataset().getSlice());
		nf.close();

		// create file where /e/f/g/a -> a so /e/f/g/a/b/c is available
		ename = createExternalFile(testScratchDirectoryName + "external2.nxs", a);
		tname = testScratchDirectoryName + "test2.nxs";
		nf = NexusTestUtils.createNexusFile(tname);
		nf.linkExternal(new URI("nxfile://./" + ename + "#/a"), "/e/f/g/", true);
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(tname);
		g = nf.getGroup("/e/f/g", false);
		assertNotNull(g);
		assertFalse(g.containsAttribute("top"));
		g = nf.getGroup(g, "a", null, false);
		assertNotNull(g);
		assertTrue(g.containsAttribute("top"));
		assertEquals("world", g.getAttribute("top").getFirstElement());
		g = nf.getGroup(g, "b", null, false);
		assertNotNull(g);

		g = nf.getGroup("/e/f/g/a/b", false);
		assertNotNull(g);
		g = nf.getGroup("/e/f/g/a/b/c", false);
		assertNotNull(g);
		assertTrue(g.containsDataNode("value"));
		assertEquals(a, g.getDataNode("value").getDataset().getSlice());
		nf.close();

		// create file where /e/f/d -> value so /e/f/d is available
		ename = createExternalFile(testScratchDirectoryName + "external3.nxs", a);
		createExternalFile(ename, a);
		tname = testScratchDirectoryName + "test3.nxs";
		nf = NexusTestUtils.createNexusFile(tname);
		nf.linkExternal(new URI("nxfile://./" + ename + "#/a/b/c/value"), "/e/f/d", false);
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(tname);
		g = nf.getGroup("/e/f", false);
		assertNotNull(g);
		assertTrue(g.containsDataNode("d"));
		assertEquals(a, g.getDataNode("d").getDataset().getSlice());
		assertEquals(a, nf.getData(g, "d").getDataset().getSlice());
		assertEquals(a, nf.getData("/e/f/d").getDataset().getSlice());
		nf.close();

		// create file where /e/f/g -> c so /e/f/g/value is available
		ename = createExternalFile(testScratchDirectoryName + "external4.nxs", a);
		createExternalFile(ename, a);
		tname = testScratchDirectoryName + "test4.nxs";
		nf = NexusTestUtils.createNexusFile(tname);
		nf.linkExternal(new URI("nxfile://./" + ename + "#/a/b/c/value"), "/e/f/g/", false);
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(tname);
		g = nf.getGroup("/e/f/g", false);
		assertNotNull(g);
		assertTrue(g.containsDataNode("value"));
		assertEquals(a, g.getDataNode("value").getDataset().getSlice());
		assertEquals(a, nf.getData(g, "value").getDataset().getSlice());
		assertEquals(a, nf.getData("/e/f/g/value").getDataset().getSlice());
		nf.close();
	}

	// need to create a new external file as NAPI does not seem to cope well if an external file
	// is used in several files
	static boolean WORKAROUND_NAPI = true;
	private String createExternalFile(String ename, Dataset d) throws NexusException {
		if (!WORKAROUND_NAPI) {
			ename = "test-scratch/external.nxs";
			if (new File(ename).exists())
				return ename;
		}

		NexusFile nf = NexusTestUtils.createNexusFile(ename);
		GroupNode g = nf.getGroup("/a", true);
		Dataset a = DatasetFactory.createFromObject("world");
		a.setName("top");
		nf.addAttribute(g, nf.createAttribute(a));
		g = nf.getGroup("/a/b/c", true);
		a = DatasetFactory.createFromObject("world");
		a.setName("bottom");
		nf.addAttribute(g, nf.createAttribute(a));
		nf.createData(g, d);
		nf.close();

		return ename;
	}
}
