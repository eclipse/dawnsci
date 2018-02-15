/*
 * Copyright (c) 2014, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;

public class SliceInformation {

	private SliceND currentSlice;
	private SliceND output;
	private SliceND sampling;
	private int[] dataDimensions;
	private final int number;
	private final int totalSlices;
	
	/**
	 * Object to store the information about where a slice is from in a lazy dataset
	 * 
	 * @param current - slice describing where this data comes from in the original dataset 
	 * @param output - slice describing where this data goes in the output dataset
	 * @param sampling - slice describing how original data is subsampled for iteration 
	 * @param dataDimensions - dimensions which correspond to the data (as opposed to the stack or scan)
	 * @param total - total number of slices that can be taken (can be negative to indicate total is unknown)
	 * @param number - which number this slice corresponds to (must be less than total, if total is non-negative)
	 */
	public SliceInformation(SliceND current, SliceND output, SliceND sampling, int[] dataDimensions, int total, int number) {
		this.currentSlice = current;
		this.output = output;
		this.sampling = sampling;
		this.dataDimensions = dataDimensions;
		if (total >= 0 && number >= total) {
			throw new IllegalArgumentException("Given number must be less than given total");
		}
		this.totalSlices = total;
		this.number = number;
	}

	public Slice[] getSliceFromInput() {
		return currentSlice.convertToSlice();
	}
	
	public Slice[] getSliceInOutput() {
		return output.convertToSlice();
	}

	public int getSliceNumber() {
		return number;
	}

	public boolean isFirstSlice() {
		return number == 0;
	}

	public boolean isLastSlice() {
		return number == totalSlices - 1;
	}

	public int[] getSubSampledShape() {
		return sampling.getShape();
	}
	
	public Slice[] getSubSampling() {
		return sampling.convertToSlice();
	}

	@Override
	public SliceInformation clone() {
		return new SliceInformation(currentSlice.clone(), output.clone(), sampling.clone(),
									dataDimensions.clone(), totalSlices, number);
	}

	/**
	 * @return total number of slices. Can be negative to indicate this is not known
	 */
	public int getTotalSlices() {
		return totalSlices;
	}

	public int[] getDataDimensions() {
		return dataDimensions;
	}
	
	public boolean isDataDimension(int dim) {
		return ArrayUtils.contains(dataDimensions, dim);
	}
	
	public void reducedDimensionToSingular(int dim) {
		
		Slice[] cs = output.convertToSlice();
		int[] sss = sampling.getShape();
		Slice[] ss = sampling.convertToSlice();
		
		sss[dim] = 1;
		cs[dim].setStart(0);
		cs[dim].setStop(1);
		cs[dim].setStep(1);
		
		ss[dim].setStart(0);
		ss[dim].setStop(1);
		ss[dim].setStep(1);
		
		output = new SliceND(sss, cs);
		sampling = new SliceND(sss,ss);
	}
	
	public void convertSliceDimensionToFull(int dim) {
		
		Slice[] cs = output.convertToSlice();
		int[] sss = sampling.getShape();
		Slice[] ss = sampling.convertToSlice();
		
//		sss[dim] = 1;
		cs[dim].setStart(0);
		cs[dim].setStop(sss[dim]);
		cs[dim].setStep(1);
		
		ss[dim].setStart(0);
		ss[dim].setStop(sss[dim]);
		ss[dim].setStep(1);
		
		int[] dd = new int[dataDimensions.length +1];
		System.arraycopy(dataDimensions, 0, dd, 1, dataDimensions.length);
		dd[0] = dim;
		dataDimensions = dd;
		output = new SliceND(sss, cs);
		sampling = new SliceND(sss,ss);
	}
	
	public void reducedDimension(int dim, int size, int current) {
		
		Slice[] cs = output.convertToSlice();
		int[] sss = sampling.getShape();
		Slice[] ss = sampling.convertToSlice();
		
		sss[dim] = size;
		cs[dim].setStart(current);
		cs[dim].setStop(current+1);
		cs[dim].setStep(1);
		
		ss[dim].setStart(0);
		ss[dim].setStop(size);
		ss[dim].setStep(1);
		
		output = new SliceND(sss, cs);
		sampling = new SliceND(sss,ss);
	}
	
	public SliceND getInputSliceWithoutDataDimensions() {
		return removeDimensionsFromInputSlice(dataDimensions);
	}
	
	public SliceND removeDimensionsFromInputSlice(int[] dims) {
		int newRank = currentSlice.getShape().length-dims.length;
		Slice[] oSlice = currentSlice.convertToSlice();
		int[] oShape = sampling.getShape().clone();
		
		Slice[] newSlice = new Slice[newRank];
		int[] newShape = new int[newRank];
		
		int index = 0;
		for (int i = 0; i < oSlice.length; i++) {
			if (!ArrayUtils.contains(dims, i)) {
				newSlice[i] = oSlice[index];
				newShape[i] = oShape[index++];
			}
		}
		
		return new SliceND(newShape, newSlice);
	}
	
	public int calculateFastestDimension() {
		int[] dd = dataDimensions.clone();
		Arrays.sort(dd);
		
		int[] shape = getSubSampledShape();
		
		for (int i = shape.length-1; i > -1; i--) {
			int key = Arrays.binarySearch(dd, i);
			if (key < 0) return i;
		}
		
		return -1;	
	}
}
