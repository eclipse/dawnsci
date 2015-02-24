/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.IndexIterator;
import org.eclipse.dawnsci.analysis.dataset.impl.IntegersIterator;
import org.eclipse.dawnsci.analysis.dataset.impl.PositionIterator;

public class SliceNDGenerator {

	private IndexIterator iter;
	private int[] datadimensions;
	private int[] inputShape;
	
	//TODO use other iterators as well...
	
	public SliceNDGenerator(final int[] shape, int[] dataDimensions, final Object... index) {
		
		this.datadimensions = dataDimensions;
		this.inputShape = shape;
		
		for (int i : dataDimensions) {
			index[i] = new Slice(1);
		}
		
		iter = new IntegersIterator(true, shape, index);
		
	}
	
	public SliceNDGenerator(final int[] shape, int[] dataDimensions, SliceND slice) {
		
		this.datadimensions = dataDimensions;
		this.inputShape = shape;
		
		int[] start = slice.getStart().clone();
		int[] stop = slice.getStop().clone();
		int[] step = slice.getStep().clone();
		
		
		for (int i : dataDimensions) {
			start[i] = 0;
			stop[i] = 1;
			step[i] = 1;
		}
		
		iter = new PositionIterator(slice, dataDimensions);
	}
	
	public int[] getOutputShape() {
		int[] shape = iter.getShape().clone();
		for (int i : datadimensions) shape[i] = inputShape[i];
		return shape;
	}
	
	public int[] getInputShape() {
		return inputShape.clone();
	}
	
	public List<SliceND> generateDataSlices() {
		return generateDataSlices(null,null);
	}
	
	public List<SliceND> generateDataSlices(int[] dimensionOrder) {
		return generateDataSlices(dimensionOrder, null);
	}
	
	public List<SliceND> generateDataSlices(List<SliceND> positionInOutput) {
		return generateDataSlices(null, positionInOutput);
	}
	
	public List<SliceND> generateDataSlices(int[] dimensionOrder, List<SliceND> positionInOutput) {
		
		iter.reset();
		
		PositionIterator piter = new PositionIterator(iter.getShape(),datadimensions);
		Map<SliceND,SliceND> map = new HashMap<SliceND, SliceND>();
		
		List<SliceND> output = new ArrayList<SliceND>();
		
		int[] st = new int[inputShape.length];
		for (int i = 0; i < st.length;i++) st[i] = 1;
		
		while (iter.hasNext()) {
			int[] pos = iter.getPos();
			int[] end = getEndFromPos(pos);
			SliceND main = new SliceND(inputShape, pos, end, st);
			output.add(main);
			
			if (positionInOutput != null) {
				piter.hasNext();
				pos = piter.getPos();
				end = getEndFromPos(pos);
				SliceND outer = new SliceND(getOutputShape(), pos, end, st);
				map.put(main, outer);
			}
		}
		
		if (dimensionOrder != null) {
			Comparator<SliceND> c = SliceNDGenerator.getStartComparator(inputShape.length,dimensionOrder);
			Collections.sort(output,c);
		}
		
		if (positionInOutput != null) for (SliceND s : output) positionInOutput.add(map.get(s));
		
		return output;
	}

	private int[] getEndFromPos(int[] pos) {
		
		int[] end = pos.clone();
		for (int i = 0; i<pos.length;i++) {
			end[i]++;
		}

		for (int i = 0; i < datadimensions.length; i++){
			end[datadimensions[i]] = inputShape[datadimensions[i]];
		}
		
		return end;
	}
	
	public static Comparator<SliceND> getStartComparator(final int rank, final int[] incrementOrder) {
		
		final int[] full = getDimensionSortingArray(rank,incrementOrder);
		
		return new Comparator<SliceND>() {

			@Override
			public int compare(SliceND o1, SliceND o2) {
				
				int[] s1 = o1.getStart();
				int[] s2 = o2.getStart();
				
				for (int i : full) {
					int val = Integer.compare(s1[i], s2[i]);
					if (val != 0) return val;
				}
				
				return 0;
			}
		};
	}
	
	private static int[] getDimensionSortingArray(final int rank, final int[] incrementOrder) {
		final int[] full = new int[rank];
		int[] rev = incrementOrder.clone();
		ArrayUtils.reverse(rev);
		
		for (int i = rank-1,j = 0; i > 0; i--) {
			if (!ArrayUtils.contains(incrementOrder, i)) {
				full[j++] = i;
			}
		}
		
		for (int i = rank - incrementOrder.length; i < rank; i++) {
			full[i] = rev[incrementOrder.length+i-rank];
		}
		
		return full;
	}
}
