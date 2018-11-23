/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.roi;

import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ROISliceUtils;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.Slice;
import org.junit.Assert;
import org.junit.Test;

public class ROISliceTest {
	
	@Test
	public void testNDSlices() throws Exception {
		Dataset input = DatasetFactory.ones(new int[] {10,20,30,40}, Dataset.FLOAT32);
		
		RectangularROI roi = new RectangularROI(10, 10, 5, 5, 0);
		
		Slice[] slices = new Slice[input.getRank()];
		
		slices[0] = new Slice(0, 1);
		
		int[] order = new int[] {3,2,1,0};
		
		Dataset output = ROISliceUtils.getDataset(input,roi, slices, new int[]{order[0],order[1]}, 1, null);
		
		output.squeeze();

		int[] shape = output.getShape();
		
		Assert.assertArrayEquals(new int[]{20, 5,5}, shape);
		
		output = ROISliceUtils.getDataset(input,roi, slices, new int[]{order[0],order[1]}, 1, null);
		
		Dataset mean;
		
		if (order[0] > order[1]) mean = output.mean(order[0]).mean(order[1]);
		else mean = output.mean(order[1]).mean(order[0]);
		
		mean.squeeze();
		shape = mean.getShape();
		Assert.assertEquals(20, shape[0]);
		
	}
	@Test
	public void getAxisDatasetTrapzSumTestNDSlices() throws Exception {
		//TODO test different dimensions
		//Create ND array and axes
		Dataset input = DatasetFactory.ones(new int[] {10,20,30,40}, Dataset.FLOAT32);
//		Dataset axis0 = DatasetFactory.createLinearSpace(0, 9, 10, Dataset.FLOAT32);
//		Dataset axis1 = DatasetFactory.createLinearSpace(0, 19, 20, Dataset.FLOAT32);
//		Dataset axis2 = DatasetFactory.createLinearSpace(0, 29, 30, Dataset.FLOAT32);
		Dataset axis3 = DatasetFactory.createLinearSpace(0, 39, 40, Dataset.FLOAT32);
		
		//Create ROI and slices
		RectangularROI roi = new RectangularROI(10, 10, 5, 5, 0);
		Slice[] slices = new Slice[input.getRank()];
		
		//Test basic slice from 0-1
		slices[0] = new Slice(0, 1);
		int[] order = new int[] {3,2,1,0};
		
		Dataset out0 = ROISliceUtils.getAxisDatasetTrapzSum(input, axis3,roi, slices, order[0], 1, null);
		
		//Dataset out0 = (Dataset)ROISliceUtils.getAxisDatasetTrapzSum(axis0, input, roi, 0);
		Assert.assertArrayEquals(new int[]{20, 30},out0.getShape());
		Assert.assertEquals(5.0, out0.getDouble(0, 0),0);
		
		//Test basic slice from 3-4
		slices[0] = new Slice(3, 4);
		out0 = ROISliceUtils.getAxisDatasetTrapzSum(input, axis3,roi, slices, order[0], 1, null);
		
		Assert.assertArrayEquals(new int[]{20, 30},out0.getShape());
		Assert.assertEquals(5.0, out0.getDouble(0, 0),0);
		
		
		//Test basic slice from 3-4, reduced range
		slices[0] = new Slice(3, 4);
		slices[2] = new Slice(5, 15);
		out0 = ROISliceUtils.getAxisDatasetTrapzSum(input, axis3,roi, slices, order[0], 1, null);

		Assert.assertArrayEquals(new int[]{20, 10},out0.getShape());
		Assert.assertEquals(5.0, out0.getDouble(0, 0),0);
		
	}
	
	@Test
	public void getDatasetLineROINDSlices() throws Exception {
		Dataset input = DatasetFactory.ones(new int[] {10,20,30,40}, Dataset.FLOAT32);
//		Dataset axis0 = DatasetFactory.createLinearSpace(0, 9, 10, Dataset.FLOAT32);
//		Dataset axis1 = DatasetFactory.createLinearSpace(0, 19, 20, Dataset.FLOAT32);
//		Dataset axis2 = DatasetFactory.createLinearSpace(0, 29, 30, Dataset.FLOAT32);
//		Dataset axis3 = DatasetFactory.createLinearSpace(0, 39, 40, Dataset.FLOAT32);
		
		LinearROI roi = new LinearROI(new double[]{2.0,2.0}, new double[]{15.0,20.0});
		
		Slice[] slices = new Slice[input.getRank()];
		slices[0] = new Slice(0, 1);
		int[] order = new int[] {3,2,1,0};

		Dataset output = (Dataset) ROISliceUtils.getDataset(input, roi,slices, new int[]{order[0],order[1]},1, null);
		int[] shape = output.getShape();
		Assert.assertEquals(23,shape[0],0);
		Assert.assertEquals(20,shape[1],0);
		
		output = (Dataset) ROISliceUtils.getDataset(input, roi,slices, new int[]{order[1],order[0]},1, null);
		shape = output.getShape();
		Assert.assertEquals(23,shape[0],0);
		Assert.assertEquals(20,shape[1],0);
		
		output = (Dataset) ROISliceUtils.getDataset(input, roi,slices, new int[]{order[0],order[2]},1, null);
		shape = output.getShape();
		Assert.assertEquals(23,shape[0],0);
		Assert.assertEquals(30,shape[1],0);
	}
	
	

}
