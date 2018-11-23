package org.eclipse.dawnsci.analysis.dataset.slicer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.dawnsci.analysis.dataset.MockDynamicLazyDataset;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IDynamicDataset;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.Slice;
import org.junit.Test;

public class DynamicSliceViewIteratorTest {

	@Test
	public void testFail() {
		
		int fastest = 6;
		
		MockDynamicLazyDataset mock = getData(fastest);
		MockDynamicLazyDataset mockkey = getKey(fastest);
		MockDynamicLazyDataset mockfinished = getFinished();
		
		DynamicSliceViewIterator iterator = new DynamicSliceViewIterator(mock, new IDynamicDataset[]{mockkey}, mockfinished, 2);
		iterator.setMaxTimeout(1000);
		
		boolean hasNext = iterator.hasNext();
		
		assertFalse(hasNext);
	}
	
	@Test
	public void testPass() throws DatasetException {
		
		int fastest = 6;
		
		MockDynamicLazyDataset mock = getData(fastest);
		MockDynamicLazyDataset mockkey = getKey(fastest);
		MockDynamicLazyDataset mockfinished = getFinished();
		
		mockkey.getParent().iadd(1);
		mock.setAllowIncrement(true);
		mockkey.setAllowIncrement(true);
		
		DynamicSliceViewIterator iterator = new DynamicSliceViewIterator(mock, new IDynamicDataset[]{mockkey}, mockfinished, 2);
		iterator.setMaxTimeout(1000);
		
		for (int i = 0 ; i < fastest-1; i++) {
			boolean hasNext = iterator.hasNext();
			assertTrue(hasNext);
			Dataset slice = DatasetUtils.convertToDataset(iterator.next().getSlice());
			assertEquals(i*10*11, ((Number) slice.sum()).doubleValue(),0.000001);
		}
	}
	
	@Test
	public void testPassPartial() throws DatasetException {
		
		int fastest = 6;
		int end = 3;
		
		MockDynamicLazyDataset mock = getData(fastest);
		MockDynamicLazyDataset mockkey = getKey(fastest);
		MockDynamicLazyDataset mockfinished = getFinished();
		
		mockkey.getParent().getSliceView(new Slice[]{new Slice(0,end,1)}).iadd(1);
		mock.setAllowIncrement(true);
		mockkey.setAllowIncrement(true);
		
		DynamicSliceViewIterator iterator = new DynamicSliceViewIterator(mock, new IDynamicDataset[]{mockkey}, mockfinished, 2);
		iterator.setMaxTimeout(1000);
		
		for (int i = 0 ; i < end; i++) {
			boolean hasNext = iterator.hasNext();
			assertTrue(hasNext);
			Dataset slice = DatasetUtils.convertToDataset(iterator.next().getSlice());
			assertEquals(i*10*11, ((Number) slice.sum()).doubleValue(),0.000001);
		}
		
		boolean hasNext = iterator.hasNext();
		assertFalse(hasNext);
	}
	
	@Test
	public void testPassRepeating() throws DatasetException {
		
		int fastest = 5;
		
		MockDynamicLazyDataset mock = getData(fastest);
		MockDynamicLazyDataset mockkey = getKey(fastest);
		MockDynamicLazyDataset mockfinished = getFinished();
		
		DynamicSliceViewIterator iterator = new DynamicSliceViewIterator(mock, new IDynamicDataset[]{mockkey}, mockfinished, 2, true);
		iterator.setMaxTimeout(2000);
		mock.setAllowIncrement(true);
		mockkey.setAllowIncrement(true);
		
		for (int i = 0; i < fastest; i++) {
			mock.refreshShape();
			mockkey.refreshShape();
		}
		
		mock.setAllowIncrement(false);
		mockkey.setAllowIncrement(false);
		
		mockkey.getParent().set(1, 0);
		
		iterator.reset();
		
		mockkey.getParent().set(2, 0);
		
		assertTrue(iterator.hasNext());
		IDataset next = iterator.next().getSlice();
		assertEquals(0, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(3, 0);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(0, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(4, 0);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(0, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(5, 0);
		mockkey.getParent().set(6, 1);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(0, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(6, 1);
		
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(0, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(7, 1);
		mockkey.getParent().set(8, 2);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(1, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(9, 2);
		mockkey.getParent().set(10, 3);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(1, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(11, 3);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(2, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(12, 3);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(2, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(13, 3);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(3, next.getDouble(0,0,0), 0.00000001);
		
		mockkey.getParent().set(14, 4);
		mockfinished.getParent().set(1, 0);
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(3, next.getDouble(0,0,0), 0.00000001);
		
		assertTrue(iterator.hasNext());
		next = iterator.next().getSlice();
		assertEquals(4, next.getDouble(0,0,0), 0.00000001);
		
		assertFalse(iterator.hasNext());

		
	}
	
	
	private MockDynamicLazyDataset getData(int max) {
		DoubleDataset d = DatasetFactory.createRange(DoubleDataset.class,max);
		d.setShape(new int[]{max,1,1});
		d = (DoubleDataset)DatasetUtils.tile(d, new int[]{1, 10, 11});
		
		int[][] shapes = new int[max][];
		
		for (int i = 0; i < max; i++) {
			shapes[i] = new int[] {i+1,10,11};
		}
		
		return new MockDynamicLazyDataset(shapes, d);
	}
	
	private MockDynamicLazyDataset getKey(int max) {
		IntegerDataset key = DatasetFactory.zeros(IntegerDataset.class, new int[]{max});
		
		int[][] shapes = new int[max][];
		
		for (int i = 0; i < max; i++) {
			shapes[i] = new int[] {i+1};
		}
		
		return new MockDynamicLazyDataset(shapes, key);
	}
	
	private MockDynamicLazyDataset getFinished() {
		IntegerDataset finished = DatasetFactory.zeros(IntegerDataset.class, new int[]{1});
		return new MockDynamicLazyDataset(new int[][]{new int[]{1}}, finished);
	}
	
	
	

}
