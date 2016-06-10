/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.eclipse.dawnsci.analysis.api.dataset.DatasetException;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.metadata.AxesMetadata;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.analysis.dataset.metadata.DynamicMetadataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicSliceViewIterator implements ISliceViewIterator {

	private static final Logger logger = LoggerFactory.getLogger(DynamicSliceViewIterator.class);
	
	private DynamicSliceNDIterator iterator = null;
	int count;
	
	private IDynamicDataset lazy;
	private IDynamicDataset[] keys;
	private IDynamicDataset finished;
	private int[] axes;
	private SourceInformation source;
	private boolean next = false;
	
	boolean last = false;
	
	private int maxTimeout = 60000;
	private int timeout = 1000;

	
	public DynamicSliceViewIterator(IDynamicDataset lazy, IDynamicDataset[] keys, IDynamicDataset finished) {
		try {
			iterator = new DynamicSliceNDIterator(lazy.getShape(), mergeKeys(keys), keys[0].getRank());
		} catch (DatasetException e) {
			logger.error("Could not get data from lazy dataset", e);
		}
		this.lazy = lazy;
		this.keys = keys;
		this.finished = finished;
		
		int lr = lazy.getRank();
		int dataSize = lazy.getRank() - keys[0].getRank();
		
		this.axes = new int[dataSize];
		
		for (int i = 0; i < axes.length; i++) {
			axes[i] = lr - 1 -i;
		}
		updateShape();
		next = iterator.hasNext();
		
		Object ssm = lazy.getFirstMetadata(SliceFromSeriesMetadata.class);
		if (ssm != null && ssm instanceof SliceFromSeriesMetadata && ((SliceFromSeriesMetadata)ssm).getSourceInfo() != null) source = ((SliceFromSeriesMetadata)ssm).getSourceInfo();
		else logger.warn("Lazy dataset contains no source information");

	}

	public void updateShape() {
		try {
			lazy.refreshShape();
			for (IDynamicDataset k : keys) k.refreshShape();
			int[] s = DynamicMetadataUtils.refreshDynamicAxesMetadata(lazy.getMetadata(AxesMetadata.class), lazy.getShape());
			lazy.resize(s);
			iterator.updateShape(lazy.getShape(), mergeKeys(keys));
		} catch (Exception e) {
			logger.error("Error refreshing axes",e);
		}
		
	}
	
	@Override
	public boolean hasNext() {
		
		boolean hasNext = next;
		count++;
		double time = 0;
		
		while (time < maxTimeout && !iterator.peekHasNext()  && !last) {
			try {
				Thread.sleep(timeout);
				updateShape();
				last = finished.getSlice().getInt(0) == 1;
				time += timeout;
			} catch (InterruptedException e) {
				break;
			} catch (DatasetException e) {
				logger.error("Could not get data from lazy dataset", e);
			}
		}
		
		if (time >= maxTimeout) last = true;
		
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
		ILazyDataset view;
		try {
			view = lazy.getSlice(current);
		} catch (DatasetException e) {
			logger.error("Could not get data from lazy dataset", e);
			return null;
		}
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
	
	private IDataset mergeKeys(IDynamicDataset[] keys) throws DatasetException {
		if (keys.length == 1) return keys[0].getSlice();
		Dataset[] dk = new Dataset[keys.length];
		int[] maxShape = new int[keys[0].getRank()];
		int minSize = Integer.MAX_VALUE;
		for (int i = 0; i < keys.length; i++) {
			dk[i] = DatasetUtils.convertToDataset(keys[i].getSlice());
			int[] shape = dk[i].getShape();
			for (int j = 0 ; j < shape.length; j++) if (maxShape[j] < shape[j]) maxShape[j] = shape[j];
			if (dk[i].getSize() < minSize) minSize = dk[i].getSize();
		}
		
		Dataset key = DatasetFactory.zeros(new int[]{minSize}, Dataset.INT64);
		
		for (int i = 0; i < minSize ; i++) {
			for (Dataset k : dk) {
				if (i > k.getSize()) return key;
				if (k.getElementLongAbs(i) == 0) return key;
			}
			
			key.set(i+1, i);
		}
		
		return key;
	}
	
}
