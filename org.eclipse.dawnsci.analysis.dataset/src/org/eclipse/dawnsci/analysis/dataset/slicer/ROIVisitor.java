/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;
import org.eclipse.january.dataset.IDataset;

public class ROIVisitor {

	public static void visitHorizontalSections(IDataset data, IROI inputROI, SliceVisitor visitor) throws Exception {
		
		IROI roi = inputROI.copy();
		
		IRectangularROI bounds = roi.getBounds();
		int[] shape = data.getShape();
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
						IDataset section = data.getSliceView(start, stop, step);
						visitor.visit(section);
						inters.remove(0);
					} else {
						int s = inters.get(0);
						int e = inters.get(1);
						
						if (roi.containsPoint(s+(e-s)/2d, i)) {
							start[1] = s;
							stop[1] = e;
							IDataset section = data.getSliceView(start, stop, step);
							visitor.visit(section);
							inters.remove(0);
						} else {
							start[1] = inters.get(0);
							stop[1] = start[1]+1;
							IDataset section = data.getSliceView(start, stop, step);
							visitor.visit(section);
							inters.remove(0);
						}
					}
				}
			}
		}
	}
	
}
