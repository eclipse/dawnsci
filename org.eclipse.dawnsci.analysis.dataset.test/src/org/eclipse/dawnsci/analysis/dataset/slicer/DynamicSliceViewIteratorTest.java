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
import org.eclipse.january.dataset.IDynamicDataset;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.Slice;
import org.junit.Test;

public class DynamicSliceViewIteratorTest {

	@Test
	public void testFail() {
		
		int[] fastest = {1,2,3,4,5,6};
		
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
		
		int[] fastest = {1,2,3,4,5,6};
		
		MockDynamicLazyDataset mock = getData(fastest);
		MockDynamicLazyDataset mockkey = getKey(fastest);
		MockDynamicLazyDataset mockfinished = getFinished();
		
		mockkey.getParent().iadd(1);
		mock.setAllowIncrement(true);
		mockkey.setAllowIncrement(true);
		
		DynamicSliceViewIterator iterator = new DynamicSliceViewIterator(mock, new IDynamicDataset[]{mockkey}, mockfinished, 2);
		iterator.setMaxTimeout(1000);
		
		for (int i = 0 ; i < fastest.length; i++) {
			boolean hasNext = iterator.hasNext();
			assertTrue(hasNext);
			Dataset slice = DatasetUtils.convertToDataset(iterator.next().getSlice());
			assertEquals(i*10*11, (double)slice.sum(),0.000001);
		}
	}
	
	@Test
	public void testPassPartial() throws DatasetException {
		
		int[] fastest = {1,2,3,4,5,6};
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
			assertEquals(i*10*11, (double)slice.sum(),0.000001);
		}
		
		boolean hasNext = iterator.hasNext();
		assertFalse(hasNext);
	}
	
	private MockDynamicLazyDataset getData(int[] fastest) {
		DoubleDataset d = DatasetFactory.createRange(DoubleDataset.class,fastest.length);
		d.setShape(new int[]{fastest.length,1,1});
		d = (DoubleDataset)DatasetUtils.tile(d, new int[]{1, 10, 11});
		
		int[][] shapes = new int[fastest.length][];
		
		for (int i = 0; i < fastest.length; i++) {
			shapes[i] = new int[] {fastest[i],10,11};
		}
		
		return new MockDynamicLazyDataset(shapes, d);
	}
	
	private MockDynamicLazyDataset getKey(int[] fastest) {
		IntegerDataset key = DatasetFactory.zeros(IntegerDataset.class, new int[]{6});
		
		int[][] shapes = new int[fastest.length][];
		
		for (int i = 0; i < fastest.length; i++) {
			shapes[i] = new int[] {fastest[i]};
		}
		
		return new MockDynamicLazyDataset(shapes, key);
	}
	
	private MockDynamicLazyDataset getFinished() {
		IntegerDataset finished = DatasetFactory.zeros(IntegerDataset.class, new int[]{1});
		return new MockDynamicLazyDataset(new int[][]{new int[]{1}}, finished);
	}
	
	
	

}
