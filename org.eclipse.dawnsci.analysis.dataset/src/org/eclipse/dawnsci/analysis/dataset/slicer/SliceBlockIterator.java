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
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.DatasetException;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.SliceNDIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SliceBlockIterator implements ISliceViewIterator{

private static final Logger logger = LoggerFactory.getLogger(SliceBlockIterator.class);
	
	private ILazyDataset lazyDataset;
	private SliceNDIterator iterator;
	private SliceND	sampling;
	private SourceInformation source;
	private int[] axes;
	private int count;
	private int total;
	private int blocks = 10;
	private int fastest;
	private int subCount;
	private IDataset subSet;
	private boolean next;

	/**
	 * Construct a Slice View Iterator
	 * 
	 * @param lazyDataset - the full dataset
	 * @param sampling - the specific part to iterate over
	 * @param axes - the dimensions the correspond to data axes (i.e. length 1 for XY and 2 for an image)
	 */
	public SliceBlockIterator(ILazyDataset lazyDataset, SliceND sampling, int... axes) {
		if (sampling == null) sampling = new SliceND(lazyDataset.getShape());
		this.lazyDataset = lazyDataset.getSliceView(sampling);
//		this.sampling = sampling != null ? sampling : new SliceND(lazyDataset.getShape());
		this.iterator = new SliceNDIterator(new SliceND(lazyDataset.getShape()), axes);
		this.axes = axes;
		this.sampling = sampling;
		count = 0;
		total = calculateTotal(sampling, axes);
		Arrays.sort(axes);
		for (int i = 0; i < lazyDataset.getRank(); i++){
			if (Arrays.binarySearch(axes, i) < 0) fastest = i;
		}
		subCount = 0;
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
		
		IDataset out = null;
		SliceND current = iterator.getCurrentSlice().clone();
		if (subCount == 0) {
			SliceND c = current.clone();
			int max = lazyDataset.getShape()[fastest];
			int stop = current.getStop()[fastest];
			int newStop = stop + blocks-1;
			newStop = newStop > max ? max : newStop;
			int start = c.getStart()[fastest];
			c.setSlice(fastest, start, newStop, 1);
			try {
				subSet = lazyDataset.getSlice(c);
			} catch (DatasetException e) {
				logger.error("Could not get data from lazy dataset", e);
				return out;
			}
			SliceND s = new SliceND(subSet.getShape());
			s.setSlice(fastest, 0, 1, 1);
			out = subSet.getSliceView(s);
			if (subSet.getShape()[fastest] != 1) subCount++;
		} else {
			
			SliceND s = new SliceND(subSet.getShape());
			s.setSlice(fastest, subCount, subCount+1, 1);
			out = subSet.getSliceView(s);
			if (subCount == subSet.getShape()[fastest]-1) subCount = 0;
			else subCount++;
		}
		
		out.clearMetadata(SliceFromSeriesMetadata.class);
		SliceInformation sl = new SliceInformation(current,
				iterator.getOutputSlice().clone(), sampling,
				axes, total, count);
		
		SliceFromSeriesMetadata m = new SliceFromSeriesMetadata(source, sl);
		
		out.setMetadata(m);
		
		next = iterator.hasNext();
		
		return out;
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
