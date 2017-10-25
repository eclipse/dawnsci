package org.eclipse.dawnsci.analysis.dataset.slicer;

import static org.junit.Assert.*;

import org.eclipse.dawnsci.analysis.dataset.MockDynamicLazyDataset;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.SliceND;
import org.junit.Test;

public class MockDynamicLazyDatasetTest {

	@Test
	public void test() throws DatasetException {
		
		DoubleDataset d = DatasetFactory.createRange(DoubleDataset.class,100);
		d.setShape(new int[]{100,1,1});
		d = (DoubleDataset)DatasetUtils.tile(d, new int[]{1, 10, 11});
		
		int[] s0 = {1,10,11};
		int[] s1 = {3,10,11};
		int[] s2 = {4,10,11};
		int[] s3 = {5,10,11};
		int[] s4 = {20,10,11};
		int[] s5 = {30,10,11};
		int[] s6 = {41,10,11};
		int[] s7 = {61,10,11};
		int[] s8 = {85,10,11};
		int[] s9 = {100,10,11};
		int[][] shapes = {s0,s1,s2,s3,s4,s5,s6,s7,s8,s9};
		
		MockDynamicLazyDataset mock = new MockDynamicLazyDataset(shapes, d);
		
		Dataset slice = mock.getSlice();
		assertArrayEquals(s0, slice.getShape());
		assertEquals(((Number) slice.sum()).doubleValue(), 0,0.00001);
		slice.toString();
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s0, slice.getShape());
		mock.setAllowIncrement(true);
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s1, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s2, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s3, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s4, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s5, slice.getShape());
		
		SliceND s = new SliceND(mock.getShape());
		s.setSlice(0, mock.getShape()[0]-1,mock.getShape()[0],1);
		Dataset slice2 = mock.getSlice(s);
		assertEquals(((Number) slice2.sum()).doubleValue(), 29*10*11,0.00001);
		
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s6, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s7, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s8, slice.getShape());
		mock.refreshShape();
		slice = mock.getSlice();
		assertArrayEquals(s9, slice.getShape());
		

	}

}
