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

import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;

/**
 * Wrapper class for a RectangularROI that adds handles
 */
public class RectangularROIHandler extends ROIHandler<RectangularROI> {
	/**
	 * Number of handle areas
	 */
	private final static int NHANDLE = 9;
	
	/**
	 * Handler for RectangularROI
	 * @param roi
	 */
	public RectangularROIHandler(RectangularROI roi) {
		super();
		for (int h = 0; h < NHANDLE; h++) {
			add(-1);
		}
		this.roi = roi;
	}

	@Override
	public int getCentreHandle() {
		return 4;
	}

	@Override
	public double[] getHandlePoint(int handle, int size) {
		double[] pt = null;

		switch (handle) {
		case 0:
			pt = roi.getPoint();
			break;
		case 1:
			pt = roi.getPoint(0.5, 0);
			pt[0] -= size/2;
			break;
		case 2:
			pt = roi.getPoint(1.0, 0);
			pt[0] -= size;
			break;
		case 3:
			pt = roi.getPoint(0.0, 0.5);
			pt[1] -= size/2;
			break;
		case 4:
			pt = roi.getPoint(0.5, 0.5);
			pt[0] -= size/2;
			pt[1] -= size/2;
			break;
		case 5:
			pt = roi.getPoint(1.0, 0.5);
			pt[0] -= size;
			pt[1] -= size/2;
			break;
		case 6:
			pt = roi.getPoint(0.0, 1.0);
			pt[1] -= size;
			break;
		case 7:
			pt = roi.getPoint(0.5, 1.0);
			pt[0] -= size/2;
			pt[1] -= size;
			break;
		case 8:
			pt = roi.getPoint(1.0, 1.0);
			pt[0] -= size;
			pt[1] -= size;
			break;
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
			pt = roi.getPoint(0.5, 0);
			break;
		case 2:
			pt = roi.getPoint(1.0, 0);
			break;
		case 3:
			pt = roi.getPoint(0.0, 0.5);
			break;
		case 4:
			pt = roi.getPoint(0.5, 0.5);
			break;
		case 5:
			pt = roi.getPoint(1.0, 0.5);
			break;
		case 6:
			pt = roi.getPoint(0.0, 1.0);
			break;
		case 7:
			pt = roi.getPoint(0.5, 1.0);
			break;
		case 8:
			pt = roi.getPoint(1.0, 1.0);
			break;
		}
		return pt;
	}

	/**
	 * @param spt starting point 
	 * @param pt 
	 * @return resized ROI
	 */
	public RectangularROI resize(double[] spt, double[] pt) {
		RectangularROI rroi = null;
		double[] ept;

		if (handle == 4)
			return rroi;

		rroi = roi.copy();
		ept = rroi.getEndPoint();

		switch (handle) {
		case -1: // new definition
			rroi.setPoint(spt);
			rroi.setEndPoint(pt);
			break;
		case 0:
			pt[0] -= spt[0];
			pt[1] -= spt[1];
			rroi.setPointKeepEndPoint(pt, true, true);
			break;
		case 1:
			pt[0] -= spt[0];
			pt[1] -= spt[1];
			rroi.setPointKeepEndPoint(pt, false, true);
			break;
		case 2:
			rroi.adjustKeepDiagonalPoint(spt, ept, pt, true);
			break;
		case 3:
			pt[0] -= spt[0];
			pt[1] -= spt[1];
			rroi.setPointKeepEndPoint(pt, true, false);
			break;
		case 5:
			pt[0] += ept[0] - spt[0];
			pt[1] += ept[1] - spt[1];
			rroi.setEndPoint(pt, true, false);
			break;
		case 6:
			rroi.adjustKeepDiagonalPoint(spt, ept, pt, false);
			break;
		case 7:
			pt[0] += ept[0] - spt[0];
			pt[1] += ept[1] - spt[1];
			rroi.setEndPoint(pt, false, true);
			break;
		case 8:
			pt[0] += ept[0] - spt[0];
			pt[1] += ept[1] - spt[1];
			rroi.setEndPoint(pt, true, true);
			break;
		default:
			break;
		}

		return rroi;
	}

	/**
	 * @param pt
	 * @return reoriented ROI
	 */
	public RectangularROI reorient(double[] pt) {
		RectangularROI rroi = null;

		if (handle == 4 || handle % 2 != 0)
			return rroi;

		rroi = roi.copy();
		double nang, oang;

		switch (handle) {
		case 0: // keep end point
			oang = roi.getAngleRelativeToPoint(1.0, 1.0, roi.getPoint());
			nang = roi.getAngleRelativeToPoint(1.0, 1.0, pt);
			rroi.addAngle(nang-oang);
			rroi.setEndPointKeepLengths(roi.getEndPoint());
			break;
		case 2:
			oang = roi.getAngleRelativeToPoint(0.0, 1.0, roi.getPoint(1.0, 0.0));
			nang = roi.getAngleRelativeToPoint(0.0, 1.0, pt);
			rroi.translate(0.0, 1.0);
			rroi.addAngle(nang-oang);
			rroi.translate(0.0, -1.0);
			break;
		case 6:
			oang = roi.getAngleRelativeToPoint(1.0, 0.0, roi.getPoint(0.0, 1.0));
			nang = roi.getAngleRelativeToPoint(1.0, 0.0, pt);
			rroi.translate(1.0, 0.0);
			rroi.addAngle(nang-oang);
			rroi.translate(-1.0, 0.0);
			break;
		case 8: // keep start point
			oang = roi.getAngleRelativeToPoint(0, 0, roi.getPoint(1.0, 1.0));
			nang = roi.getAngleRelativeToPoint(0, 0, pt);
			rroi.addAngle(nang-oang);
			break;
		}
		return rroi;
	}

	@Override
	public RectangularROI interpretMouseDragging(double[] spt, double[] ept) {
		RectangularROI croi = null; // return null if not a valid event

		switch (status) {
		case RMOVE:
			croi = roi.copy();
			ept[0] -= spt[0];
			ept[1] -= spt[1];
			croi.addPoint(ept);
			break;
		case NONE:
			croi = roi.copy();
			croi.setEndPoint(ept);
			break;
		case REORIENT:
			croi = reorient(ept);
			break;
		case RESIZE:
			croi = resize(spt, ept);
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
