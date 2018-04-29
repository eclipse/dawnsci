/*-
 * Copyright 2015, 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import java.util.Arrays;

import org.eclipse.dawnsci.hdf5.HDF5File;
import org.eclipse.dawnsci.hdf5.HDF5FileFactory;
import org.eclipse.dawnsci.hdf5.HDF5Utils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.PositionIterator;
import org.eclipse.january.dataset.ShapeUtils;
import org.junit.Test;

public class ReadThreadTest extends AbstractThreadTestBase {
	String file = "test-scratch/readthread.h5";
	String group = "/entry/group1/group2";
	int[] shape = new int[] {512, 128, 1024};
	int[] chunk = new int[] {1, 128, 1024};

	@Test
	public void testSingleThreadRead() throws Throwable {
		prepareForTest(file, 1);

		doTestOfDataSet(0);
	}

	@Test
	public void testTwoThreadsRead() throws Throwable {
		prepareForTest(file, 2);

		testWithNThreads(2);
	}

	@Test
	public void testEightThreadsRead() throws Throwable {
		prepareForTest(file, 8);

		testWithNThreads(8);
	}

	@Override
	protected void doTestOfDataSet(int index) throws Throwable {
		String dataName = "data" + index;

		PositionIterator iter = new PositionIterator(shape, 1, 2);
		int[] start = iter.getPos();
		int[] stop = shape.clone();
		int[] step = new int[shape.length];
		Arrays.fill(step, 1);
		int[] nshape = chunk.clone();
		long now;

		iter.reset();
		now = -System.nanoTime();
		while (iter.hasNext()) {
			stop[0] = start[0] + 1;
			HDF5Utils.loadDataset(file, dataName, start, nshape, step, Dataset.FLOAT64, 1, false);
		}
		HDF5FileFactory.releaseFile(file);
		now += System.nanoTime();

		System.err.println("Thd for " + dataName + " took " + now*1e-9 + "s");
	}

	private void prepareForTest(String file, int nthreads) throws Exception {
		HDF5FileFactory.deleteFile(file);

		HDF5File f = HDF5FileFactory.acquireFileAsNew(file);
		int size = ShapeUtils.calcSize(shape);
		Dataset data = DatasetFactory.createRange(size);
		data.setShape(shape);

		for (int i = 0; i < nthreads; i++) {
			HDF5Utils.writeDataset(f, "data" + i, data);
		}
		HDF5FileFactory.releaseFile(file);
	}
}
