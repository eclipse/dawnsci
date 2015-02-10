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

import java.io.File;

import ncsa.hdf.object.Dataset;

import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFile;
import org.eclipse.dawnsci.hdf5.HierarchicalDataUtils;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.junit.After;
import org.junit.Test;

public class Hdf5ThreadTest extends AbstractThreadTestBase {

	private boolean useFileCopy;

	@Test
	public void testSeparateFiles10Threads() throws Throwable {
		useFileCopy = true;
		super.testWithNThreads(10);
	}

	@Test
	public void testSameFile10Threads() throws Throwable {
		useFileCopy = false;
		super.testWithNThreads(10);
	}

	@Test
	public void testSeparateFiles100Threads() throws Throwable {
		useFileCopy = true;
		super.testWithNThreads(100);
	}

	@Test
	public void testSameFile100Threads() throws Throwable {
		useFileCopy = false;
		super.testWithNThreads(100);
	}

	@After
	public void clear() throws Exception {
		// Ensures that there are no open files left
		HierarchicalDataFile.clear();
		// Not really necessary
	}

	/**
	 *
	 * @param index
	 * @param useFileCopy
	 * @throws Throwable
	 */
	@Override
	protected void doTestOfDataSet(int index) throws Throwable {

		IHierarchicalDataFile testFile = null;

		try {
			final File test;
			if (useFileCopy) {
				test = File.createTempFile("test", ".h5");
				test.deleteOnExit();
				HierarchicalDataUtils.copy(new File("testfiles/FeKedge_1_15.nxs"), test);
			} else {
				test = new File("testfiles/FeKedge_1_15.nxs");
			}


			// open the file and retrieve the file structure
			testFile = HierarchicalDataFactory.getReader(test.getAbsolutePath());
			final String root = testFile.getRoot();

			if (root == null) throw new Exception("Did not get root!");
			System.out.println("Testing with thread "+index);
			testFile.print();

			final Dataset set = (Dataset)testFile.getData("/entry1/counterTimer01/lnI0It");
			final Object  dat = set.read();
			final double[] da = (double[])dat;
			if (da.length<100) throw new Exception("Did not get data from node lnI0It, length was "+da.length);

			System.out.println("===========================");

		} finally {
			// close file resource
			if (testFile!=null) testFile.close();
		}

	}

}
