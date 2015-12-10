/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyDynamicDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicSliceViewIterator implements ISliceViewIterator {

	private static final Logger logger = LoggerFactory.getLogger(DynamicSliceViewIterator.class);
	
	private DynamicSliceNDIterator iterator = null;
	int count;
	
	private LazyDynamicDataset lazy;
	private LazyDynamicDataset key;
	private int[] axes;
	private SourceInformation source;
	private boolean next = false;
	
	boolean last = false;
	
	private int timeout = 5;
	
	public DynamicSliceViewIterator(LazyDynamicDataset lazy, LazyDynamicDataset key) {
		iterator = new DynamicSliceNDIterator(lazy.getShape(), key.getSlice());
		this.lazy = lazy;
		this.key = key;
		
		int lr = lazy.getRank();
		int dataSize = lazy.getRank() - key.getRank();
		
		this.axes = new int[dataSize];
		
		for (int i = 0; i < axes.length; i++) {
			axes[i] = lr - 1 -i;
		}
		
		next = iterator.hasNext();
		
		Object ssm = lazy.getFirstMetadata(SliceFromSeriesMetadata.class);
		if (ssm != null && ssm instanceof SliceFromSeriesMetadata && ((SliceFromSeriesMetadata)ssm).getSourceInfo() != null) source = ((SliceFromSeriesMetadata)ssm).getSourceInfo();
		else logger.warn("Lazy dataset contains no source information");

	}
	
	public void updateShape() {
		lazy.refreshShape();
		key.refreshShape();
		iterator.updateShape(lazy.getShape(), key.getSlice());
	}
	
	@Override
	public boolean hasNext() {
		
		boolean hasNext = next;
		count++;
		double time = 0;
		
		while (time < timeout && !iterator.peekHasNext()  && ! last) {
			try {
				Thread.sleep((timeout*1000)/100);
				updateShape();
				time += (timeout)/100.;
			} catch (InterruptedException e) {
				break;
			}
		}
		
		if (time >= timeout) last = true;
		
		return hasNext;
	}
	
	/**
	 * Resets the iterator
	 */
	public void reset() {
		count = 0;
		iterator.reset();
	}
	
	/**
	 * Get the current view on the ILazyDataset
	 * 
	 * @return lazyDataset
	 */
	@Override
	public ILazyDataset next() {
		SliceND current = iterator.getCurrentSlice().clone();
		ILazyDataset view = lazy.getSlice(current);
		view.clearMetadata(SliceFromSeriesMetadata.class);
		
		SliceInformation sl = new SliceInformation(current,
				current.clone(), new SliceND(lazy.getShape()),
				axes, last ? count : -1, count);
		
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
		return -1;
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
		return iterator.getCurrentSlice();
	}
	
	/**
	 * Get the shape of the subsampled view
	 * 
	 * @return shape
	 */
	@Override
	public int[] getShape(){
		return lazy.getShape().clone();
	}
	
	@Override
	public void remove() {
		//TODO throw something?
	}
	
}
