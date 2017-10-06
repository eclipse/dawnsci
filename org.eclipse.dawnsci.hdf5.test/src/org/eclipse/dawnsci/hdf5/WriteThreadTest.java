/*-
 * Copyright 2015, 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.hdf.object.AbstractThreadTestBase;
import org.eclipse.dawnsci.hdf5.HDF5FileFactory;
import org.eclipse.dawnsci.hdf5.HDF5Utils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.PositionIterator;
import org.eclipse.january.dataset.ShapeUtils;
import org.eclipse.january.dataset.SliceND;
import org.junit.Test;

public class WriteThreadTest extends AbstractThreadTestBase {
	String file = "test-scratch/writethread.h5";
	String group = "/entry/group1/group2";
	int[] mshape = new int[] {-1, 128, 1024};
	int[] chunk = new int[] {1, 128, 1024};

	@Test
	public void testSingleThreadWrite() throws Throwable {
		prepareForTest(file, 1);

		doTestOfDataSet(0);
	}

	@Test
	public void testTwoThreadsWrite() throws Throwable {
		prepareForTest(file, 2);

		testWithNThreads(2);
	}

	@Test
	public void testEightThreadsWrite() throws Throwable {
		prepareForTest(file, 8);

		testWithNThreads(8);
	}

	@Override
	protected void doTestOfDataSet(int index) throws Throwable {
		String dataName = "data" + index;

		int[] shape = chunk.clone();
		int size = ShapeUtils.calcSize(shape);
		Dataset data1 = DatasetFactory.createRange(size, Dataset.FLOAT64);
		data1.setShape(shape);

		shape[0] = 128;
		PositionIterator iter = new PositionIterator(shape, 1, 2);
		int[] pos = iter.getPos();
		long now;

		int[] nshape = shape.clone();
		nshape[0] = 1;
		SliceND slice = new SliceND(nshape);
		int[] start = slice.getStart();
		int[] stop = slice.getStop();

		now = -System.nanoTime();
		while (iter.hasNext()) {
			for (int i = 0; i < 3; i++) {
				start[i] = pos[i];
			}
			stop[0] = start[0] + 1;
//			System.err.println("Writing to " + dataName + " @ " + start[0]);
			HDF5Utils.setDatasetSlice(file, group, dataName, slice, data1);
		}
		now += System.nanoTime();
		HDF5FileFactory.releaseFile(file);
		System.err.println("Thd for " + dataName + " took " + now*1e-9 + "s");
	}

	private void prepareForTest(String file, int nthreads) throws ScanFileHolderException {
		int[] mshape = new int[] {-1, 128, 1024};

		HDF5FileFactory.deleteFile(file);

		for (int i = 0; i < nthreads; i++) {
			HDF5Utils.createDataset(file, group, "data" + i, chunk, mshape, chunk, Dataset.FLOAT64, null, false);
		}
	}
}
