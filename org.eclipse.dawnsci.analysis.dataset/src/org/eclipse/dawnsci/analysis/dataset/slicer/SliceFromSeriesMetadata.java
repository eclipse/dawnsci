/*-
 * Copyright 2014 Diamond Light Source Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.metadata.OriginMetadata;

public class SliceFromSeriesMetadata implements OriginMetadata {
	
	private SourceInformation sourceInfo;
	private SliceInformation sliceInfo;
	
	public SliceFromSeriesMetadata(SourceInformation source, SliceInformation slice) {
		this.sourceInfo = source;
		this.sliceInfo = slice;
	}
	
	public SliceFromSeriesMetadata(SliceInformation slice) {
		this.sliceInfo = slice;
	}
	
	public SliceFromSeriesMetadata(SourceInformation source) {
		this.sourceInfo = source;
	}

	public SourceInformation getSourceInfo() {
		return sourceInfo;
	}

	public SliceInformation getSliceInfo() {
		return sliceInfo;
	}
	
	@Override
	public SliceFromSeriesMetadata clone() {
		SourceInformation soi = sourceInfo != null ? sourceInfo.clone() : null;
		SliceInformation sli = sliceInfo != null ? sliceInfo.clone() : null;
		
		return new SliceFromSeriesMetadata(soi, sli);
		}

	@Override
	public int[] getDataDimensions() {
		return sliceInfo != null ? sliceInfo.getDataDimensions()  : null;
	}

	@Override
	public ILazyDataset getParent() {
		return sourceInfo != null ? sourceInfo.getParent()  : null;
	}

	@Override
	public Slice[] getSliceInOutput() {
		return sliceInfo != null ? sliceInfo.getSliceInOutput()  : null;
	}

	@Override
	public String getDatasetName() {
		return sourceInfo != null ? sourceInfo.getDatasetName()  : null;
	}

	@Override
	public String getFilePath() {
		return sourceInfo != null ? sourceInfo.getFilePath()  : null;
	}

	@Override
	public Slice[] getSliceFromInput() {
		return sliceInfo != null ? sliceInfo.getSliceFromInput()  : null;
	}
	
	public int[] getSubSampledShape() {
		return sliceInfo != null ? sliceInfo.getSubSampledShape() : null;
	}
	
	public int getTotalSlices() {
		return sliceInfo != null ? sliceInfo.getTotalSlices() : -1;
	}
	
	public boolean isDataDimension(int dim) {
		return sliceInfo != null ? sliceInfo.isDataDimension(dim) : false;
	}
	
	public void reducedDimensionToSingular(int dim) {
		if (sliceInfo.isDataDimension(dim)) throw new IllegalArgumentException("Cannot reduce data dimension!");
		sliceInfo.reducedDimensionToSingular(dim);
		
	}
	/**
	 * For when external data is a different series of the same data shape.
	 * 
	 * Returns the dimensions of the input shape compatible with the series parent,
	 * null if no suitable dimensions found.
	 * 
	 * i.e. if parent is [p,q,x,y] and shape is [v,x,y] returns [1,2]
	 * 
	 * @param shape
	 * @return datadims
	 */
	public int[] getCompatibleDataDimensions(int[] shape) {
		
		int[] datadims = getDataDimensions();
		int[] oShape = getParent().getShape();
		
		//same rank, dims must be same shape
		if (shape.length == oShape.length) {
			for (int i : datadims) {
				if (shape[i] != oShape[i]) return null;
			}
			
			return datadims.clone();
		}
		
		boolean suitable = true;
		
		for (int i : datadims) {
			if (i >= shape.length) {
				suitable = false;
				break;
			}
			if (shape[i] != oShape[i]){
				suitable = false;
				break;
			}
		}
		
		if (suitable) return datadims.clone();
		
		suitable = true;
		
		int[] shift = datadims.clone();
		int rankdif = oShape.length-shape.length;
		for (int i = 0; i < shift.length; i++) shift[i] -=rankdif;
		
		for (int i = 0; i < shift.length; i++) {
			if (shift[i] >= shape.length || shift[i] < 0) {
				suitable = false;
				break;
			}
			if (shape[shift[i]] != oShape[datadims[i]]){
				suitable = false;
				break;
			}
		}
		
		if (suitable) return shift;
			
		return null;
	}
	
	/**
	 * Returns the slice corresponding to matching slice of parent, currently assumes
	 * rank of input <= rank of parent
	 * 
	 * returns null if ds not suitable shape
	 * 
	 * i.e. if current slice is [3,4,x,y] and ds has shape [p,q,2] returns [3,4,:] 
	 * 
	 * @param ds
	 * @return slice
	 */
	public IDataset getMatchingSlice(ILazyDataset ds) {
		
		int[] oShape = getParent().getShape();
		int[] shape = ds.getShape();
		int[] datadims = getDataDimensions();
		
		if (Arrays.equals(oShape, shape)){
			return ds.getSlice(getSliceFromInput());
		}
		
		Slice[] oSlices = getSliceFromInput();
		Slice[] slices = new Slice[shape.length];
		
		for (int i = 0; i < slices.length; i++) {
			if (ArrayUtils.contains(datadims, i)) {
				slices[i] = null;
			} else {
				if (shape[i] != oShape[i]) return null;
				slices[i] = oSlices[i];
			}
		}
		
		return ds.getSlice(slices);
	}

	@Override
	public int[] getDataMaxDimensions() {
		return null;
	}

	@Override
	public int[] getDataChunkDimensions() {
		return null;
	}
}
