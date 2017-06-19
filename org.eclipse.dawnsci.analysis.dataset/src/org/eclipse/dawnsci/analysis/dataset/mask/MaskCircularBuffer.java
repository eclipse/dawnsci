package org.eclipse.dawnsci.analysis.dataset.mask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;
import org.eclipse.january.dataset.BooleanDataset;
import org.eclipse.january.dataset.Comparisons;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.IntegerDataset;

/**
 * Buffer that allows multiple masking steps to be applied and undone
 * 
 * Optimised for speed and low memory usage
 * 
 */
public class MaskCircularBuffer {

	private IntegerDataset mask;
	private int[] shape;
	int bitMask = START;
	private static final int END = 0x80000000;
	private static final int START = 0x00000001;
	
	public MaskCircularBuffer(int[] shape) {
		mask = DatasetFactory.zeros(IntegerDataset.class, shape);
		this.shape = shape;
	}
	
	public void maskROI(IROI inputROI) {
		IROI roi = inputROI.copy();
		IRectangularROI bounds = roi.getBounds();
		
		int iStart = (int)Math.floor(bounds.getPointY());
		int iStop = (int)Math.ceil(bounds.getPointY()+bounds.getLength(1));
		if (iStop > shape[0]) iStop = shape[0];
		if (iStart < 0) iStart = 0;
		
		for (int i = iStart; i < iStop; i++) {
			double[] hi = roi.findHorizontalIntersections(i);
			if (hi != null) {
				boolean cutsStart = roi.containsPoint(0, i);
				boolean cutsEnd = roi.containsPoint(shape[1]-1d, i);
				
				List<Integer> inters = new ArrayList<>();
				if (cutsStart) inters.add(0);
				for (double d : hi) {
					if (!inters.contains((int)d) && d > 0 && d < shape[1]-1) inters.add((int)d);
				}
				if (cutsEnd && !inters.contains(shape[1]-1)) inters.add(shape[1]-1);
				
				int[] start = new int[]{i,0};
				int[] stop = new int[]{i+1,0};
				int[] step = new int[]{1,1};
				
				while (!inters.isEmpty()) {
					
					if (inters.size() == 1) {
						start[1] = inters.get(0);
						stop[1] = start[1]+1;
						IntegerDataset data = (IntegerDataset)mask.getSliceView(start, stop, step);
						updateArray(data);
						inters.remove(0);
					} else {
						int s = inters.get(0);
						int e = inters.get(1);
						
						if (roi.containsPoint(s+(e-s)/2d, i)) {
							start[1] = s;
							stop[1] = e;
							IntegerDataset data = (IntegerDataset)mask.getSliceView(start, stop, step);
							updateArray(data);
							inters.remove(0);
						} else {
							start[1] = inters.get(0);
							stop[1] = start[1]+1;
							IntegerDataset data = (IntegerDataset)mask.getSliceView(start, stop, step);
							updateArray(data);
							inters.remove(0);
						}
					}
				}
			}
		}

		nextBitMask();
		
	}
	
	public BooleanDataset getMask(){
		return Comparisons.equalTo(mask, 0);
	}
	
	public void undo() {
		if (bitMask == START) {
			return;
		}
		
		bitMask = bitMask >>> 1;
		IndexIterator iterator = mask.getIterator();
		
		while (iterator.hasNext()) {
			int v = (int)mask.getElementLongAbs(iterator.index);
			mask.setAbs(iterator.index, v & ~bitMask);
		}
	}
	
	public boolean isBufferEmpty() {
		return bitMask == 1;
	}
	
	public void clear() {
		bitMask = START;
		mask.fill(0);
	}
	
	private void nextBitMask(){
		if (bitMask == END) {
			freeEndSlot();
		} else {
			bitMask = bitMask << 1;
		}
	}
	
	private void freeEndSlot() {

		IndexIterator iterator = mask.getIterator();
		
		while (iterator.hasNext()) {
			int v = (int)mask.getElementLongAbs(iterator.index);
			
			int pos1 = v & START;
			v = v >> 1;
			v = (v | pos1);
			v = (v & ~END);
			mask.setAbs(iterator.index, v);
		}
	}
	
	public void maskThreshold(Double min, Double max, Dataset data) {
		if (!Arrays.equals(mask.getShape(), data.getShape())) throw new IllegalArgumentException("must have same shape");
		
		
		int count = 0;
		
		IndexIterator iterator = data.getIterator();
		
		while (iterator.hasNext()) {
			double element = data.getElementDoubleAbs(iterator.index);
			
			if (min != null && element < min) {
				long v = mask.getElementLongAbs(count);
				v= v | bitMask;
				mask.setAbs(count, (int)v);
			}
			
			if (max != null && element > max) {
				long v = mask.getElementLongAbs(count);
				v= v | bitMask;
				mask.setAbs(count, (int)v);
			}
			
			count++;
		}
		
		nextBitMask();
	}
	
	private void updateArray(IntegerDataset section) {
		IndexIterator iterator = section.getIterator();
		while (iterator.hasNext()) {
			int v = (int)section.getElementLongAbs(iterator.index);
			section.setAbs(iterator.index, v | bitMask);
		}
	}
	
}
