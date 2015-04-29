/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.examples.slice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceNDGenerator;
import org.junit.Test;

public class SliceNDGeneratorTest {

	@Test
	public void testIndiciesConstructor() {
		
		int[] shape = {3,4,5,1000,1000};
		int[] dd = {3,4};
		
		Dataset p = DatasetFactory.createFromObject(new int[]{1,3});
		Dataset r = DatasetFactory.createRange(3, Dataset.INT);
		
		SliceNDGenerator gen = new SliceNDGenerator(shape, dd, null,null,null,null,null);
		
		int[] os = gen.getOutputShape();
		assertArrayEquals(shape, os);
		
		List<SliceND> slices = gen.generateDataSlices();
		SliceND sliceND = slices.get(0);
		assertEquals(3*4*5,slices.size());
		assertArrayEquals(new int[]{0,0,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,4,0,0},sliceND.getStart());
		
		gen = new SliceNDGenerator(shape, dd, null,p,r,null,null);
		
		os = gen.getOutputShape();
		assertArrayEquals(new int[]{shape[0],p.getSize(),r.getSize(),shape[3],shape[4]}, os);
		
		slices = gen.generateDataSlices();
		sliceND = slices.get(0);
		assertEquals(3*p.getSize()*r.getSize(),slices.size());
		assertArrayEquals(new int[]{0,1,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,2,0,0},sliceND.getStart());
		
		
		
	}
	
	@Test
	public void testSliceNDConstructorGenerator() {
		
		int[] shape = {3,4,5,1000,1000};
		int[] dd = {3,4};
		
		SliceNDGenerator gen = new SliceNDGenerator(shape, dd, new SliceND(shape));
		
		int[] os = gen.getOutputShape();
		assertArrayEquals(shape, os);
		
		List<SliceND> slices = gen.generateDataSlices();
		SliceND sliceND = slices.get(0);
		assertEquals(3*4*5,slices.size());
		assertArrayEquals(new int[]{0,0,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,4,0,0},sliceND.getStart());
		
		List<SliceND> outSlice = new ArrayList<SliceND>();
		
		slices = gen.generateDataSlices(null,outSlice);
		sliceND = slices.get(0);
		SliceND sliceNDo = outSlice.get(0);
		assertArrayEquals(sliceND.getStart(), sliceNDo.getStart());
		sliceND = slices.get(slices.size()-1);
		sliceNDo = outSlice.get(slices.size()-1);
		assertArrayEquals(sliceND.getStart(), sliceNDo.getStart());

		gen = new SliceNDGenerator(shape, dd, new SliceND(shape, (Slice) null, new Slice(1,4,2), new Slice(0,3), null, null));

		os = gen.getOutputShape();
		assertArrayEquals(new int[]{shape[0],2,3,shape[3],shape[4]}, os);
		
		slices = gen.generateDataSlices();
		sliceND = slices.get(0);
		assertEquals(3*3*2,slices.size());
		assertArrayEquals(new int[]{0,1,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,2,0,0},sliceND.getStart());
		
		outSlice = new ArrayList<SliceND>();
		
		slices = gen.generateDataSlices(null,outSlice);
		sliceND = slices.get(0);
		sliceNDo = outSlice.get(0);
		assertArrayEquals(new int[]{0,0,0,0,0}, sliceNDo.getStart());
		assertArrayEquals(new int[]{0,1,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		sliceNDo = outSlice.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,2,0,0},sliceND.getStart());
		assertArrayEquals(new int[]{2,1,2,0,0},sliceNDo.getStart());
		
	}
	
	@Test
	public void testSorting() {
		
		int[] shape = {3,4,5,1000,1000};
		int[] dd = {3,4};
		
		SliceNDGenerator gen = new SliceNDGenerator(shape, dd, new SliceND(shape));
		
		List<SliceND> slices = gen.generateDataSlices(new int[]{0});
		SliceND sliceND = slices.get(0);
		assertArrayEquals(new int[]{0,0,0,0,0},sliceND.getStart());
		sliceND = slices.get(1);
		assertArrayEquals(new int[]{1,0,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,4,0,0},sliceND.getStart());
		
		slices = gen.generateDataSlices(new int[]{1});
		sliceND = slices.get(0);
		assertArrayEquals(new int[]{0,0,0,0,0},sliceND.getStart());
		sliceND = slices.get(1);
		assertArrayEquals(new int[]{0,1,0,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,4,0,0},sliceND.getStart());
		
		slices = gen.generateDataSlices(new int[]{1,2});
		sliceND = slices.get(0);
		assertArrayEquals(new int[]{0,0,0,0,0},sliceND.getStart());
		sliceND = slices.get(1);
		assertArrayEquals(new int[]{0,1,0,0,0},sliceND.getStart());
		sliceND = slices.get(4);
		assertArrayEquals(new int[]{0,0,1,0,0},sliceND.getStart());
		sliceND = slices.get(5);
		assertArrayEquals(new int[]{0,1,1,0,0},sliceND.getStart());
		sliceND = slices.get(slices.size()-1);
		assertArrayEquals(new int[]{2,3,4,0,0},sliceND.getStart());
		
	}
	
}
