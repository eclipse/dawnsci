/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi.handler;

import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;

/**
 * Wrapper class for polygon that adds handles for each point
 */
public class PolygonalROIHandler extends ROIHandler<PolygonalROI> {

	/**
	 * Handler for ROIBase
	 * @param roi
	 */
	public PolygonalROIHandler(PolygonalROI roi) {
		super();
		if (roi != null) {
			for (int h = 0, hmax = roi.getSides(); h < hmax; h++) {
				add(-1);
			}
			this.roi = roi;
		}
	}

	/**
	 * @return Returns the roi.
	 */
	@Override
	public PolygonalROI getROI() {
		return roi;
	}

	@Override
	public int getCentreHandle() {
		return -1;
	}

	@Override
	public void setROI(PolygonalROI roi) {
		int n = roi.getSides();
		if (n > size()) {
			for (int h = size(); h < n; h++) {
				add(-1);
			}
		}
		this.roi = roi;
	}

	@Override
	public double[] getHandlePoint(int handle, int size) {
		double[] pt = getAnchorPoint(handle, size);
		
		if (pt != null) {
			pt[0] -= size/2;
			pt[1] -= size/2;
		}
		return pt;
	}

	@Override
	public double[] getAnchorPoint(int handle, int size) {
		double[] pt = null;

		int sides = roi.getSides();
		if (handle < sides) {
			pt = roi.getPoint(handle).getPoint();
		}

		return pt;
	}

	@Override
	public PolygonalROI interpretMouseDragging(double[] spt, double[] ept) {
		// TODO Auto-generated method stub
		return null;
	}

}
