package org.eclipse.dawnsci.analysis.dataset;

import static org.junit.Assert.*;

import org.eclipse.dawnsci.analysis.dataset.mask.MaskCircularBuffer;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.january.dataset.BooleanDataset;
import org.eclipse.january.dataset.Comparisons;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.SliceND;
import org.junit.Test;

public class MaskCircularBufferTest {

	@Test
	public void testInitialised() {
		MaskCircularBuffer buffer = new MaskCircularBuffer(new int[]{10,20});
		BooleanDataset mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
	}
	
	@Test
	public void testROI() {
		MaskCircularBuffer buffer = new MaskCircularBuffer(new int[]{10,20});
		BooleanDataset mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		
		RectangularROI roi = new RectangularROI(new double[]{1, 1}, new double[]{2.1,2.1});
		
		buffer.maskROI(roi);
		
		BooleanDataset m = buffer.getMask();
		assertEquals((10*20-4), (double)m.sum(),0.00000001);
		
		SliceND s = new SliceND(m.getShape());
		s.setSlice(0, 1,3,1);
		s.setSlice(1, 1,3,1);
		
		Dataset slice = m.getSlice(s);
		assertEquals(0, (double)slice.sum(),0.00000001);
		
		buffer.undo();
		
		m = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		slice = m.getSlice(s);
		assertTrue(Comparisons.allTrue(slice));
		
	}
	
	@Test
	public void testThreshold() {
		
		int[] shape = new int[]{10,20};
		
		MaskCircularBuffer buffer = new MaskCircularBuffer(shape);
		BooleanDataset mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		
		Dataset data = DatasetFactory.zeros(IntegerDataset.class,shape);
		
		
		SliceND s = new SliceND(shape);
		s.setSlice(0, 1,3,1);
		s.setSlice(1, 1,3,1);
		
		data.getSliceView(s).iadd(1);
		
		buffer.maskThreshold(null, 0.9, data);
		mask = buffer.getMask();
		assertEquals((10*20-4), (double)mask.sum(),0.00000001);
		
		Dataset slice = mask.getSlice(s);
		assertEquals(0, (double)slice.sum(),0.00000001);
		
		buffer.undo();
		
		mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		
		data.getSliceView(s).isubtract(2);
		
		buffer.maskThreshold(-0.5, null, data);
		mask = buffer.getMask();
		assertEquals((10*20-4), (double)mask.sum(),0.00000001);
		
		slice = mask.getSlice(s);
		assertEquals(0, (double)slice.sum(),0.00000001);
		
		buffer.undo();
		mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		
		buffer.maskThreshold(-0.5, -0.1, data);
		mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(Comparisons.logicalNot(mask)));
		
		buffer.clear();
		mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
	}
	@Test
	public void testIsEmpty() {
		MaskCircularBuffer buffer = new MaskCircularBuffer(new int[]{100,20});
		assertTrue(buffer.isBufferEmpty());
		
		RectangularROI roi = new RectangularROI(new double[]{1, 1}, new double[]{2.1,2.1});
		buffer.maskROI(roi);
		assertFalse(buffer.isBufferEmpty());
		
		buffer.clear();
		assertTrue(buffer.isBufferEmpty());
		
		BooleanDataset mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		
	}
	
	@Test
	public void testRollover() {
		MaskCircularBuffer buffer = new MaskCircularBuffer(new int[]{100,20});
		BooleanDataset mask = buffer.getMask();
		assertTrue(Comparisons.allTrue(mask));
		
		RectangularROI roi = new RectangularROI(new double[]{1, 1}, new double[]{2.1,2.1});
		
		for (int i = 0; i < 40; i++) {
			buffer.maskROI(roi);
			mask = buffer.getMask();
			assertFalse(mask.get(i+1,1));
			assertTrue(mask.get(i+3,1));
			double[] point = roi.getPoint();
			point[1]++;
			roi.setPoint(point);
		}
		
		for (int i = 38; i >= 8; i--) {
			mask = buffer.getMask();
			assertFalse(mask.get(i+3,1));
			buffer.undo();
			mask = buffer.getMask();
			assertTrue(mask.get(i+3,1));
		}
		
	}

}
