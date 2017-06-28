package org.eclipse.dawnsci.analysis.dataset.mask;

import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.slicer.ROIVisitor;
import org.eclipse.dawnsci.analysis.dataset.slicer.SliceVisitor;
import org.eclipse.january.dataset.BooleanDataset;
import org.eclipse.january.dataset.Comparisons;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.IntegerDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Buffer that allows multiple masking steps to be applied and undone
 * 
 * Optimised for speed and low memory usage
 * 
 */
public class MaskCircularBuffer {

	private IntegerDataset mask;
	int bitMask = START;
	private static final int END = 0x80000000;
	private static final int START = 0x00000001;
	
	private final static Logger logger = LoggerFactory.getLogger(MaskCircularBuffer.class);
	
	public MaskCircularBuffer(int[] shape) {
		mask = DatasetFactory.zeros(IntegerDataset.class, shape);
	}
	
	public void maskROI(IROI inputROI) {
		
		try {
			ROIVisitor.visitHorizontalSections(mask, inputROI, new SliceVisitor() {
				
				@Override
				public void visit(IDataset data) throws Exception {
					updateArray((IntegerDataset)data);
					
				}
				
				@Override
				public boolean isCancelled() {
					return false;
				}
			});
		} catch (Exception e) {
			logger.error("Error during ROI masking!", e);
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
		return bitMask == START;
	}
	
	public void invert(){
		
		IndexIterator iterator = mask.getIterator();
		
		while (iterator.hasNext()) {
			int v = (int)mask.getElementLongAbs(iterator.index);
			
			mask.setAbs(iterator.index, v == 0 ? 1 : 0);
		}
		
		bitMask = START;
		nextBitMask();
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
	
	public void merge(Dataset m) {
		if (!Arrays.equals(mask.getShape(), m.getShape())) throw new IllegalArgumentException("must have same shape");
		
		int count = 0;
		
		IndexIterator iterator = m.getIterator();
		
		while (iterator.hasNext()) {
			double element = m.getElementDoubleAbs(iterator.index);
			
			if (element == 0) {
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
