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

import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;

/**
 * Wrapper class for a LinearROI that adds handles
 */
public class LinearROIHandler extends ROIHandler<LinearROI> {

	/**
	 * Number of handle areas
	 */
	private final static int NHANDLE = 3;
	
	/**
	 * Handler for LinearROI
	 * @param roi
	 */
	public LinearROIHandler(LinearROI roi) {
		super();
		for (int h = 0; h < NHANDLE; h++) {
			add(-1);
		}
		this.roi = roi;
	}

	@Override
	public int getCentreHandle() {
		return 1;
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

		switch (handle) {
		case 0:
			pt = roi.getPoint();
			break;
		case 1:
			pt = roi.getMidPoint();
			break;
		case 2:
			pt = roi.getEndPoint();
			break;
		}

		return pt;
	}

	/**
	 * @param pt
	 * @return reoriented ROI
	 */
	public LinearROI reorient(double[] pt) {
		LinearROI croi = null;
		double len;

		switch (handle) {
		case 0:
			croi = roi.copy();
			len = croi.getLength();
			croi.setPointKeepEndPoint(pt);
			croi.translateAlongLength(croi.getLength()-len);
			croi.setLength(len);
			break;
		case 2:
			croi = roi.copy();
			len = croi.getLength();
			croi.setEndPoint(pt);
			croi.setLength(len);
			break;
		}
		return croi;
	}

	/**
	 * @param pt
	 * @return resized ROI
	 */
	public LinearROI resize(double[] pt) {
		LinearROI croi = null;

		switch (handle) {
		case 0:
			croi = roi.copy();
			croi.setPointKeepEndPoint(pt);
			break;
		case 2:
			croi = roi.copy();
			croi.setEndPoint(pt);
			break;
		}
		return croi;
	}

	@Override
	public LinearROI interpretMouseDragging(double[] spt, double[] ept) {
		LinearROI croi = null; // return null if not a valid event

		switch (status) {
		case RMOVE:
			croi = roi.copy();
			croi.addPoint(ept);
			croi.subPoint(spt);
			break;
		case NONE:
			croi = roi.copy();
			croi.setEndPoint(ept);
			break;
		case REORIENT:
			croi = reorient(ept);
			break;
		case RESIZE:
			croi = resize(ept);
			break;
		case ROTATE:
			croi = roi.copy();
			double ang = croi.getAngleRelativeToMidPoint(ept);
			double[] mpt = croi.getMidPoint();
			croi.setAngle(ang);
			croi.setMidPoint(mpt);
			break;
		case CMOVE:
			break;
		case CRMOVE:
			break;
		}

		return croi;
	}
}
