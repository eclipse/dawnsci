/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;

public class DynamicSliceNDIterator {

	private int[] scanShape;
	private Dataset key;
	private int absCurrentPosition = -1;
	private int currentMax = 0;
	private SliceND currentSlice;
	
	public DynamicSliceNDIterator(int[] initialShape, IDataset key) {
		scanShape = new int[key.getRank()];
		this.key = DatasetUtils.convertToDataset(key);
		updateScanShape(initialShape);
		buildInitialSlice(initialShape,initialShape.length - key.getRank());
	}
	
	private void buildInitialSlice(int[] initialShape, int dataRank) {
		int[] maxShape = initialShape.clone();
		int[] shape = initialShape.clone();
		for (int i = 0; i < maxShape.length - dataRank; i++){
			maxShape[i] = -1;
			shape[i] = 1;
		}
		currentSlice = new SliceND(shape, maxShape);
		for (int i = 0; i < maxShape.length - dataRank; i++) currentSlice.setSlice(0, 0, 1, 1);
		
		
	}
	
	private void updateScanShape(int[] initialShape){
		System.arraycopy(initialShape, 0, scanShape, 0, scanShape.length);
		currentMax = scanShape[0];
		for (int i = 1; i < scanShape.length; i++){
			currentMax *= scanShape[i];
		}
		currentMax--;
	}
	
	public void updateShape(int[] shape, IDataset key) {
		updateScanShape(shape);
		this.key = DatasetUtils.convertToDataset(key);
	}
	
	private void updateCurrentSlice() {
		int[] pos = AbstractDataset.getNDPositionFromShape(absCurrentPosition,scanShape);
		for (int i = 0; i < pos.length; i++) currentSlice.setSlice(i, pos[i], pos[i]+1, 1);
	}
	
	public SliceND getCurrentSlice() {
		return absCurrentPosition == -1 ? null : currentSlice;
	}
	
	public boolean hasNext() {
		
		if (absCurrentPosition == currentMax) return false;
		
		absCurrentPosition++;
		if ( key.getSize() <= absCurrentPosition || key.getElementDoubleAbs(absCurrentPosition) == 0) {
			absCurrentPosition--;
			return false;
		}
		
		updateCurrentSlice();
		
		return true;
	}
	
	public boolean peekHasNext() {
		if (absCurrentPosition == currentMax) return false;
		
		if (key.getSize() <= absCurrentPosition +1 ) return false;
		
		return !(key.getElementDoubleAbs(absCurrentPosition + 1) == 0);

	}
	
	public void reset() {
		absCurrentPosition = -1;
	}
	
}
