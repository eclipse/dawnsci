/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.examples.slice;

import static org.junit.Assert.*;

import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceInformation;
import org.junit.Test;

public class SliceInformationTest {

	
	@Test
	public void testReduceDimension() {
		
		int dim = 1;
		
		SliceInformation si = getSliceInfo();
		
		int[] osss = si.getSubSampledShape();
		Slice[] os = si.getSliceInOutput();
		
		si.reducedDimensionToSingular(dim);
		
		int[] sss = si.getSubSampledShape();
		Slice[] so = si.getSliceInOutput();
		
		for (int i = 0; i< osss.length; i++) {
			if (i == dim) {
				assertEquals(dim,sss[i]);
				assertEquals(0, so[i].getStart().intValue());
			} else{
				assertEquals(osss[i],sss[i]);
				assertEquals(os[i].getStart().intValue(), so[i].getStart().intValue());
			}
		}
		
		
	}
	
	@Test
	public void testgetReducedSlice() {
		
		SliceInformation si = getSliceInfo();
		
		SliceND ss = si.getInputSliceWithoutDataDimensions();
		
		assertArrayEquals(new int[]{2,3},ss.getStart());
		assertArrayEquals(new int[]{3,4},ss.getStop());
		
		
	}
	
	private SliceInformation getSliceInfo(){
		
		int[] shape = new int[]{4,5,6};
		
		int[] start = new int[]{2,3,0};
		int[] stop = new int[]{3,4,5};
		int[]step = new int[]{1,1,1};
		
		SliceND c = new SliceND(shape,start.clone(),stop.clone(),step.clone());
		SliceND o = new SliceND(shape,start.clone(),stop.clone(),step.clone());
		SliceND s = new SliceND(shape);
		
		return new SliceInformation(c, o, s, new int[]{2}, 20, 18);
	}
	
}
