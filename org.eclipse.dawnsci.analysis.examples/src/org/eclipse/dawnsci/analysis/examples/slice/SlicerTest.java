/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.examples.slice;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.dataset.slicer.SliceViewIterator;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceVisitor;
import org.eclipse.dawnsci.analysis.dataset.slicer.Slicer;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.Random;
import org.eclipse.january.dataset.SliceND;
import org.junit.Test;

public class SlicerTest {

	/**
	 * Slice visitor
	 */
	@Test
	public void slicerImages() throws Exception {
		
		final ILazyDataset         lz   = Random.lazyRand(64, 100, 100);
		final Map<Integer, String> dims = new HashMap<Integer, String>();
		dims.put(0, "all");
		SliceND slice = Slicer.getSliceNDFromSliceDimensions(dims, lz.getShape());
		int[] axes = Slicer.getDataDimensions(lz.getShape(), dims);
		final SliceViewIterator it = new SliceViewIterator(lz, slice, axes);
		Slicer.visit(it, new SliceVisitor() {
			
			private int count = 0;
			@Override
			public void visit(IDataset data) throws Exception {
				
                IDataset image = data.squeeze(); // We squeeze the slice to get the image.
                ++count;
                System.out.println("Image "+count+" "+image);
			}
			@Override
			public boolean isCancelled() {
				return false;
			}
		});
	}
	
}
