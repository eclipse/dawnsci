/*
 * Copyright (c) 2014, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;

public class SliceInformation {

	private SliceND currentSlice;
	private int number;
	private SliceND output;
	private SliceND sampling;
	private int[] dataDimensions;
	private int totalSlices;
	
	/**
	 * Object to store the information about where a slice is from in a lazy dataset
	 * 
	 * @param current - slice describing where this data comes from in the original dataset 
	 * @param output - slice describing where this data goes in the output dataset
	 * @param sampling - slice describing how original data is subsampled for iteration 
	 * @param dataDimensions - dimensions which correspond to the data (as apposed to the stack or scan)
	 * @param total - total number of slices that can be taken
	 * @param number - which number this slice corresponds to
	 */
	public SliceInformation(SliceND current, SliceND output, SliceND sampling, int[] dataDimensions, int total, int number) {
		this.currentSlice = current;
		this.number = number;
		this.output = output;
		this.dataDimensions = dataDimensions;
		this.totalSlices = total;
		this.sampling = sampling;
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
}
