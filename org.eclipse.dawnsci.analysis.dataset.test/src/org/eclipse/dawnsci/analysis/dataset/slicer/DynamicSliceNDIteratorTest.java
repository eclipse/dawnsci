/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.dawnsci.analysis.dataset.slicer.DynamicSliceNDIterator;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.SliceND;
import org.junit.Test;

public class DynamicSliceNDIteratorTest {

	@Test
	public void test() {
		
		int[] shape = new int[]{1,1,100,100};
		Dataset key = DatasetFactory.zeros(IntegerDataset.class,new int[]{1, 1});
		
		DynamicSliceNDIterator gsndi = new DynamicSliceNDIterator(shape, key, key.getRank());
		
		assertFalse(gsndi.hasNext());
		
		shape = new int[]{1,1,100,100};
		key = DatasetFactory.ones(IntegerDataset.class,new int[]{1, 1});
		gsndi.updateShape(shape, key);
		assertTrue(gsndi.peekHasNext());
		assertTrue(gsndi.hasNext());
		SliceND currentSlice = gsndi.getCurrentSlice();
		
		assertArrayEquals(new int[]{0, 0,0,0},currentSlice.getStart());
		assertArrayEquals(new int[]{1, 1,100,100},currentSlice.getStop());
		assertEquals(1, gsndi.getCount());
		
		assertFalse(gsndi.peekHasNext());
		assertFalse(gsndi.hasNext());
		
		shape = new int[]{1,10,100,100};
		key = DatasetFactory.zeros(IntegerDataset.class, new int[]{1, 10});
		key.setObjectAbs(0, 1);
		key.setObjectAbs(1, 1);
		key.setObjectAbs(2, 1);
		gsndi.updateShape(shape, key);
		assertTrue(gsndi.peekHasNext());
		assertTrue(gsndi.hasNext());
		assertTrue(gsndi.hasNext());
		assertEquals(3, gsndi.getCount());
		currentSlice = gsndi.getCurrentSlice();
		assertFalse(gsndi.hasNext());
		assertEquals(3, gsndi.getCount());
		shape = new int[]{1,50,100,100};
		key = DatasetFactory.ones(IntegerDataset.class,new int[]{1, 50});
		gsndi.updateShape(shape, key);
		int count = 0;
		while (gsndi.hasNext()) {
			gsndi.getCurrentSlice();
			assertEquals(count+4, gsndi.getCount());
			count++;
		}
		
		assertEquals(47, count);
		
		count = 0;
		shape = new int[]{3,50,100,100};
		key = DatasetFactory.ones(IntegerDataset.class, new int[]{3, 50});
		gsndi.updateShape(shape, key);
		while (gsndi.hasNext()) {
			gsndi.getCurrentSlice();
			assertEquals(count+51, gsndi.getCount());
			count++;
		}
		
		assertEquals(100, count);

	}
	
	@Test
	public void testRepeating() {
		
		int[] shape = new int[]{1,100,100};
		Dataset key = DatasetFactory.zeros(IntegerDataset.class,new int[]{1});
		
		DynamicSliceNDIterator gsndi = new DynamicSliceNDIterator(shape, key, key.getRank());
		gsndi.enableRepeating();
		assertFalse(gsndi.peekHasNext());
		assertFalse(gsndi.hasNext());
		assertEquals(0, gsndi.getCount());
		shape = new int[]{1,100,100};
		key = DatasetFactory.ones(IntegerDataset.class,new int[]{1});
		gsndi.updateShape(shape, key);
		assertTrue(gsndi.peekHasNext());
		assertTrue(gsndi.hasNext());
		SliceND currentSlice = gsndi.getCurrentSlice();
		assertEquals(1, gsndi.getCount());
		assertArrayEquals(new int[]{0,0,0},currentSlice.getStart());
		assertArrayEquals(new int[]{1,100,100},currentSlice.getStop());
		assertFalse(gsndi.peekHasNext());
		assertFalse(gsndi.hasNext());
		assertEquals(1, gsndi.getCount());
		key.set(2, 0);
		gsndi.updateShape(shape, key);
		assertTrue(gsndi.peekHasNext());
		assertTrue(gsndi.hasNext());
		assertEquals(1, gsndi.getCount());
		currentSlice = gsndi.getCurrentSlice();
		
		assertArrayEquals(new int[]{0,0,0},currentSlice.getStart());
		assertArrayEquals(new int[]{1,100,100},currentSlice.getStop());
		
		assertFalse(gsndi.peekHasNext());
		assertFalse(gsndi.hasNext());
		
		assertEquals(1, gsndi.getCount());
		
		key = DatasetFactory.ones(IntegerDataset.class,new int[]{2});
		key.set(3,0);
		shape = new int[]{3,100,100};
		gsndi.updateShape(shape, key);
		assertTrue(gsndi.peekHasNext());
		assertTrue(gsndi.hasNext());
		assertEquals(1, gsndi.getCount());
		currentSlice = gsndi.getCurrentSlice();
		
		assertArrayEquals(new int[]{0,0,0},currentSlice.getStart());
		assertArrayEquals(new int[]{1,100,100},currentSlice.getStop());
		assertTrue(gsndi.peekHasNext());
		assertTrue(gsndi.hasNext());
		currentSlice = gsndi.getCurrentSlice();
		assertEquals(2, gsndi.getCount());
		assertArrayEquals(new int[]{1,0,0},currentSlice.getStart());
		assertArrayEquals(new int[]{2,100,100},currentSlice.getStop());

	}

}
