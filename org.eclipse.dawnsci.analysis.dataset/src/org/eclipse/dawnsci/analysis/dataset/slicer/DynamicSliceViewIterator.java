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

import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IDynamicDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.LongDataset;
import org.eclipse.january.dataset.SliceND;
import org.eclipse.january.metadata.AxesMetadata;
import org.eclipse.january.metadata.DynamicMetadataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicSliceViewIterator implements ISliceViewIterator {

	private static final Logger logger = LoggerFactory.getLogger(DynamicSliceViewIterator.class);
	
	private DynamicSliceNDIterator iterator = null;
	
	private IDynamicDataset lazy;
	private IDynamicDataset[] keys;
	private IDynamicDataset finished;
	private int[] axes;
	private SourceInformation source;
	private boolean next = false;
	
	private boolean last = false;
	
	private int maxTimeout = 60000;
	private int timeout = 1000;
	
	public DynamicSliceViewIterator(IDynamicDataset lazy, IDynamicDataset[] keys, IDynamicDataset finished, int dataSize) {
		this(lazy, keys, finished, dataSize, false);
	}
	
	public DynamicSliceViewIterator(IDynamicDataset lazy, IDynamicDataset[] keys, IDynamicDataset finished, int dataSize, boolean repeating) {
		try {
			iterator = new DynamicSliceNDIterator(lazy.getShape(), mergeKeys(keys), lazy.getRank()-dataSize);
			if (repeating) iterator.enableRepeating();
		} catch (DatasetException e) {
			logger.error("Could not get data from lazy dataset", e);
		}
		this.lazy = lazy;
		this.keys = keys;
		this.finished = finished;
		
		int lr = lazy.getRank();
		
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
	
	public void setMaxTimeout(int maxTimeout) {
		this.maxTimeout = maxTimeout;
		timeout = maxTimeout/50;
	}

	public void updateShape() {
		try {
			lazy.refreshShape();
			int[] current = lazy.getShape();
			for (IDynamicDataset k : keys) k.refreshShape();
			int[] s = DynamicMetadataUtils.refreshDynamicAxesMetadata(lazy.getMetadata(AxesMetadata.class), lazy.getShape());
			for (int i = 0; i < axes.length;i++) {
				s[axes[i]] = current[axes[i]];
			}
			lazy.resize(s);
			iterator.updateShape(lazy.getShape(), mergeKeys(keys));
		} catch (Exception e) {
			logger.error("Error refreshing axes",e);
		}
		
	}
	
	@Override
	public boolean hasNext() {
		
		boolean hasNext = next;
		double time = 0;
		
		while (time < maxTimeout && !iterator.peekHasNext()  && !last) {
			try {
				Thread.sleep(timeout);
				updateShape();
				finished.refreshShape();
				last = finished.getSlice().getInt(0) == 1;
				time += timeout;
			} catch (InterruptedException e) {
				break;
			} catch (DatasetException e) {
				logger.error("Could not get data from lazy dataset", e);
			}
		}
		
		if (time >= maxTimeout) {
			last = true;
			logger.error("Dynamic slice view iterator has timed-out");
		}
		
		return hasNext;
	}
	
	/**
	 * Resets the iterator
	 */
	@Override
	public void reset() {
		iterator.reset();
		last = false;
		updateShape();
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
		ILazyDataset view;
		try {
			view = lazy.getSlice(current);
		} catch (DatasetException e) {
			logger.error("Could not get data from lazy dataset", e);
			return null;
		}
		view.clearMetadata(SliceFromSeriesMetadata.class);
		
		int count = iterator.getCount();
		
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
		
		try {
			
			for (IDynamicDataset key : keys) {
				String name = key.getName();
				if (name == null) name = "unknown";
				logger.info("merge " + name + " with shape " + Arrays.toString(key.getShape()));
			}
			
			//For testing on I18
		} catch (Exception e) {
			logger.info("Exception testing keys",e);
		}
		
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
		
		Dataset key = DatasetFactory.zeros(LongDataset.class,new int[]{minSize});
		
		for (int i = 0; i < minSize ; i++) {
			long sum = 0;
			for (Dataset k : dk) {
				if (i > k.getSize()) return key;
				long l = k.getElementLongAbs(i);
				if (k.getElementLongAbs(i) == 0) return key;
				sum+=l;
				
			}
			
			key.set(sum, i);
		}
		
		return key;
	}
	
}
