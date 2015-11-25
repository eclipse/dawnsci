/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.SliceNDIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iteration over views of a (subsampled) ILazyDataset
 * 
 * Used for iterating of images or XY data in a multidimensional dataset
 * 
 * Views will contain SliceFromSeriesMetadata describing there location in the original ILazyDataset,
 * as well as in the subsampled view.
 * 
 * If the input ILazyDataset contains SliceFromSeriesMetadata with a SourceInformation object,
 * the SourceInformation will be transfered to each view
 * 
 * Wrapper for a SliceNDIterator, but iterating ILazyDatasets rather than slices,
 * also adds extra metadata.
 */
public class SliceViewIterator implements ISliceViewIterator{
	
	private static final Logger logger = LoggerFactory.getLogger(SliceViewIterator.class);
	
	private ILazyDataset lazyDataset;
	private SliceNDIterator iterator;
	private SliceND	sampling;
	private SourceInformation source;
	private int[] axes;
	private int count;
	private int total;
	private boolean next = false;

	/**
	 * Construct a Slice View Iterator
	 * 
	 * @param lazyDataset - the full dataset
	 * @param sampling - the specific part to iterate over
	 * @param axes - the dimensions the correspond to data axes (i.e. length 1 for XY and 2 for an image)
	 */
	public SliceViewIterator(ILazyDataset lazyDataset, SliceND sampling, int... axes) {
		this.lazyDataset = lazyDataset;
		this.sampling = sampling != null ? sampling : new SliceND(lazyDataset.getShape());
		sampling = sampling == null ? new SliceND(lazyDataset.getShape()) : sampling;
		this.iterator = new SliceNDIterator(sampling, axes);
		this.axes = axes;
		count = 0;
		total = calculateTotal(sampling, axes);
		
		next = iterator.hasNext();
		
		List<SliceFromSeriesMetadata> sl;
		try {
			sl = lazyDataset.getMetadata(SliceFromSeriesMetadata.class);
			if (sl != null && !sl.isEmpty() && sl.get(0) != null) {
				SliceFromSeriesMetadata ss = sl.get(0);
				if (ss.getSourceInfo() != null) source = ss.getSourceInfo();
			}
		} catch (Exception e) {
			logger.warn("Lazy dataset contains no source information", e);
		}
	}
	
	/**
	 * Check to see if there is another view
	 * 
	 * @return if there is another view
	 */
	@Override
	public boolean hasNext(){
		count++;
		return next;
	}
	
	/**
	 * Resets the iterator
	 */
	public void reset() {
		count = 0;
		iterator.reset();
		next = iterator.hasNext();
	}
	
	/**
	 * Get the current view on the ILazyDataset
	 * 
	 * @return lazyDataset
	 */
	@Override
	public ILazyDataset next() {
		SliceND current = iterator.getCurrentSlice().clone();
		ILazyDataset view = lazyDataset.getSliceView(current);
		view.clearMetadata(SliceFromSeriesMetadata.class);
		SliceInformation sl = new SliceInformation(current,
				iterator.getOutputSlice().clone(), sampling,
				axes, total, count);
		
		SliceFromSeriesMetadata m = new SliceFromSeriesMetadata(source, sl);
		
		view.setMetadata(m);
		
		next = iterator.hasNext();
		
		return view;
	}
	
	/**
	 * Get the total number of views to be iterated over
	 * 
	 * @return total;
	 */
	public int getTotal(){
		return total;
	}
	
	/**
	 * Get the number of the current ILazyDataset
	 * 
	 * @return current
	 */
	public int getCurrent(){
		return count;
	}
	
	/**
	 * Get the SliceND that describes the current views position in the subsampled data
	 * 
	 * @return output
	 */
	@Override
	public SliceND getSliceND(){
		return iterator.getOutputSlice();
	}
	
	/**
	 * Get the shape of the subsampled view
	 * 
	 * @return shape
	 */
	@Override
	public int[] getShape(){
		return sampling.getShape().clone();
	}
	
	private int calculateTotal(SliceND slice, int[] axes) {
		int[] nShape = slice.getShape();

		int[] dd = axes.clone();
		Arrays.sort(dd);
		
		 int n = 1;
		 for (int i = 0; i < nShape.length; i++) {
			 if (Arrays.binarySearch(dd, i) < 0) n *= nShape[i];
		 }
		return n;
	}
	
	@Override
	public void remove() {
		//TODO throw something?
	}
	
}
