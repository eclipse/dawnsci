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

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;

public class SliceInformation {

	private SliceND currentSlice;
	private int number;
	private SliceND output;
	private SliceND sampling;
	private int[] dataDimensions;
	private int totalSlices;
	private int[] shape;
	
	public SliceInformation(SliceND current, SliceND output, SliceND sampling,int[] shape, int[] dataDimensions, int total, int number) {
		this.currentSlice = current;
		this.number = number;
		this.output = output;
		this.dataDimensions = dataDimensions;
		this.totalSlices = total;
		this.sampling = sampling;
		this.shape = shape;
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
		return new SliceInformation(currentSlice.clone(), output.clone(), sampling.clone(), shape.clone(),
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
