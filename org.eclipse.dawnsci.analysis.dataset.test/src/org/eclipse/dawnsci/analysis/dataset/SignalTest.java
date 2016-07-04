/*-
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.asserts.TestUtils;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Signal;
import org.junit.Test;

public class SignalTest {

	/**
	 * Comes from the Ticket SCI#1275
	 */
	@Test
	public void testSimpleConvolveFilters() {
		Dataset f = DatasetFactory.zeros(new int[] {10,10}, Dataset.ARRAYFLOAT64);
		Dataset g = DatasetFactory.zeros(new int[] {3,3}, Dataset.ARRAYFLOAT64);
		
		
		// test against a null filter
		f.iadd(2);
		g.set(1, 1, 1);
		
		Dataset result = Signal.convolve(f, g, null);
		
		assertEquals("Arrays are not equal area",  (Double) f.sum(), (Double) result.sum(), 0.1); 
		
		// set against a zeroed filter
		g.set(0, 1, 1);
		
		result = Signal.convolve(f, g, null);
		
		assertEquals("Array not zero size",  0.0, (Double) result.sum(), 0.1); 	
		
		// set against a doubling filter
		g.set(2, 1, 1);
		
		result = Signal.convolve(f, g, null);
		
		assertEquals("convolved array not twice the size",  (Double)f.sum()*2.0, (Double) result.sum(), 0.1); 
	}
	
	/**
	 * Comes from the Ticket SCI#1275
	 */
	@Test
	public void testConvolveFilters() {
		Dataset f = DatasetFactory.zeros(new int[] {10,10}, Dataset.ARRAYFLOAT64);
		Dataset g = DatasetFactory.zeros(new int[] {3,3}, Dataset.ARRAYFLOAT64);
		
		// test against a null filter
		f.iadd(1);
		g.set(1, 1, 1);
		g.set(1, 0, 1);
		g.set(1, 2, 1);
		g.set(1, 1, 0);
		g.set(1, 1, 2);
		
		Dataset result = Signal.convolve(f, g, null);
		
		assertEquals("Element (0,0) is not correct",  0, result.getDouble(0,0), 0.1); 
		assertEquals("Element (1,1) is not correct",  3, result.getDouble(1,1), 0.1); 
		assertEquals("Element (2,2) is not correct",  5, result.getDouble(2,2), 0.1); 
		assertEquals("Element (3,3) is not correct",  5, result.getDouble(3,3), 0.1); 
		assertEquals("Element (6,6) is not correct",  5, result.getDouble(6,6), 0.1); 
		assertEquals("Element (9,9) is not correct",  5, result.getDouble(9,9), 0.1); 
		assertEquals("Element (10,10) is not correct",  3, result.getDouble(10,10), 0.1); 
		assertEquals("Element (11,11) is not correct",  0, result.getDouble(11,11), 0.1); 
	}

	@Test
	public void testConvolutionFilter() {
		Dataset ds = DatasetFactory.createRange(DoubleDataset.class, 1000);
		Dataset kernel = DatasetFactory.ones(DoubleDataset.class, 27);
		Dataset result = Signal.convolveToSameShape(ds, kernel, null);
		assertEquals(120, result.getDouble(2), 0.001);
		
		ds = ds.reshape(new int[] {10,100});
		kernel = kernel.reshape(3,9);
		result = Signal.convolveToSameShape(ds, kernel, null);
		assertEquals(ds.getDouble(5,5)*27, result.getDouble(5,5), 0.001);
		
		ds = ds.reshape(new int[] {10,10,10});
		kernel = kernel.reshape(3,3,3);
		result = Signal.convolveToSameShape(ds, kernel, null);
		assertEquals(ds.getDouble(5,5,5)*27, result.getDouble(5,5,5), 0.001);
	}

	/**
	 * Comes from the Ticket SCI#1275
	 */
	@Test
	public void testConvolve() {
		Dataset one_d = DatasetFactory.zeros(new int[] {10}, Dataset.ARRAYFLOAT64);
		Dataset two_d = DatasetFactory.zeros(new int[] {10,10}, Dataset.ARRAYFLOAT64);
		Dataset three_d = DatasetFactory.zeros(new int[] {10,10,10}, Dataset.ARRAYFLOAT64);
		
		@SuppressWarnings("unused")
		Dataset result;
		
		try {
			result = Signal.convolve(one_d, one_d, null);
		} catch (Exception e) {
			fail("Should be able to convolve 2 1D arrays");
		}
		
		try {
			result = Signal.convolve(three_d, three_d, null);
		} catch (Exception e) {
			fail("Should be able to convolve 2 3D arrays");
		}
		
		try {
			result = Signal.convolve(one_d, two_d, null);
		} catch (IllegalArgumentException e) {
			// this is the correct exception in this case
		} catch (Exception e) {
			e.printStackTrace();
			fail("convolving 2 differnt shaped arrays should raise an IllegalArgumentException");
		}
		
		try {
			result = Signal.convolve(one_d, three_d, null);
		} catch (IllegalArgumentException e) {
			// this is the correct exception in this case
		} catch (Exception e) {
			e.printStackTrace();
			fail("convolving 2 differnt shaped arrays should raise an IllegalArgumentException");
		}
		
		try {
			result = Signal.convolve(two_d, three_d, null);
		} catch (IllegalArgumentException e) {
			// this is the correct exception in this case
		} catch (Exception e) {
			e.printStackTrace();
			fail("convolving 2 differnt shaped arrays should raise an IllegalArgumentException");
		}
		
		try {
			result = Signal.convolve(three_d, two_d, null);
		} catch (IllegalArgumentException e) {
			// this is the correct exception in this case
		} catch (Exception e) {
			e.printStackTrace();
			fail("convolving 2 differnt shaped arrays should raise an IllegalArgumentException");
		}
		
		try {
			result = Signal.convolve(three_d, one_d, null);
		} catch (IllegalArgumentException e) {
			// this is the correct exception in this case
		} catch (Exception e) {
			e.printStackTrace();
			fail("convolving 2 differnt shaped arrays should raise an IllegalArgumentException");
		}
	}

	@Test
	public void testConvolveAll() {
		Dataset d = DatasetFactory.createRange(20, Dataset.FLOAT64);
		Dataset k = DatasetFactory.ones(new int[] {5}, Dataset.FLOAT64);
		Dataset c;

		Dataset e = DatasetFactory.createFromObject(new double[] {0, 1, 3, 6, 10, 15, 20,
				25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 70, 54, 37, 19});

		c = Signal.convolve(d, k, null);
		TestUtils.assertDatasetEquals(e, c, 1e-7, 1e-9);
		c = Signal.convolve(k, d, null);
		TestUtils.assertDatasetEquals(e, c, 1e-7, 1e-9);

		c = Signal.convolveToSameShape(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(2, 22)), c, 1e-7, 1e-9);
		c = Signal.convolveToSameShape(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(2, 22)), c, 1e-7, 1e-9);

		c = Signal.convolveForOverlap(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(4, 20)), c, 1e-7, 1e-9);
		c = Signal.convolveForOverlap(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(4, 20)), c, 1e-7, 1e-9);

		d = DatasetFactory.createRange(19, Dataset.FLOAT64);
		e = DatasetFactory.createFromObject(new double[] {0, 1, 3, 6, 10, 15, 20,
				25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 66, 51, 35, 18});

		c = Signal.convolve(d, k, null);
		TestUtils.assertDatasetEquals(e, c, 1e-7, 1e-9);
		c = Signal.convolve(k, d, null);
		TestUtils.assertDatasetEquals(e, c, 1e-7, 1e-9);

		c = Signal.convolveToSameShape(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(2, 21)), c, 1e-7, 1e-9);
		c = Signal.convolveToSameShape(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(2, 21)), c, 1e-7, 1e-9);

		c = Signal.convolveForOverlap(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(4, 19)), c, 1e-7, 1e-9);
		c = Signal.convolveForOverlap(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(4, 19)), c, 1e-7, 1e-9);
	}

	@Test
	public void testCorrelateAll() {
		Dataset d = DatasetFactory.createRange(20, Dataset.FLOAT64);
		Dataset k = DatasetFactory.ones(new int[] {5}, Dataset.FLOAT64);
		Dataset c;

		Dataset e = DatasetFactory.createFromObject(new double[] {0, 1, 3, 6, 10, 15, 20, 25,
				30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 70, 54, 37, 19});

		c = Signal.correlate(d, k, null);
		TestUtils.assertDatasetEquals(e, c, 1e-7, 1e-9);

		c = Signal.correlateToSameShape(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(2, 22)), c, 1e-7, 1e-9);

		c = Signal.correlateForOverlap(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(4, 20)), c, 1e-7, 1e-9);

		c = Signal.correlate(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(null, null, new int[] {-1}), c, 1e-7, 1e-9);

		c = Signal.correlateToSameShape(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(21, 1, -1)), c, 1e-7, 1e-9);

		c = Signal.correlateForOverlap(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(19, 3, -1)), c, 1e-7, 1e-9);
		
		d = DatasetFactory.createRange(19, Dataset.FLOAT64);
		e = DatasetFactory.createFromObject(new double[] {0, 1, 3, 6, 10, 15, 20,
				25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 66, 51, 35, 18});

		c = Signal.correlate(d, k, null);
		TestUtils.assertDatasetEquals(e, c, 1e-7, 1e-9);

		c = Signal.correlateToSameShape(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(2, 21)), c, 1e-7, 1e-9);

		c = Signal.correlateForOverlap(d, k, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(4, 19)), c, 1e-7, 1e-9);

		c = Signal.correlate(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(null, null, new int[] {-1}), c, 1e-7, 1e-9);

		c = Signal.correlateToSameShape(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(20, 1, -1)), c, 1e-7, 1e-9);

		c = Signal.correlateForOverlap(k, d, null);
		TestUtils.assertDatasetEquals(e.getSlice(new Slice(18, 3, -1)), c, 1e-7, 1e-9);
	}

	@Test
	public void testWindows() {
		Dataset w;
		w = Signal.hammingWindow(10);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] { 0.080000, 0.187620, 0.460122, 0.770000,
				0.972259, 0.972259, 0.770000, 0.460122, 0.187620, 0.080000 }), w, 1e-5, 1e-6);

		w = Signal.hammingWindow(11);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] { 0.08, 0.16785218, 0.39785218, 0.68214782,
				0.91214782, 1., 0.91214782, 0.68214782, 0.39785218, 0.16785218, 0.08 }), w, 1e-5, 1e-6);
	}
}
