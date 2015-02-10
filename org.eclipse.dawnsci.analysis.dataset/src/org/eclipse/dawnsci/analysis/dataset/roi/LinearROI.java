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

package org.eclipse.dawnsci.analysis.dataset.roi;

import java.io.Serializable;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.roi.IParametricROI;


/**
 * Class for linear regions of interest 
 */
public class LinearROI extends OrientableROIBase implements IParametricROI, Serializable {
	private double len;    // length
	private boolean crossHair; // enable secondary linear ROI that bisects at 90 degrees

	private transient double[] ept;

	/**
	 * Default new line
	 */
	public LinearROI() {
		this(1.);
	}

	/**
	 * @param len
	 */
	public LinearROI(double len) {
		this(len, 0.0);
	}

	/**
	 * @param len
	 * @param ang in radians
	 */
	public LinearROI(double len, double ang) {
		this.spt = new double[] {0, 0};
		this.len = len;
		this.ang = ang;
		checkAngle();
		crossHair = false;
	}

	/**
	 * New line from origin to point
	 * @param pt 
	 */
	public LinearROI(double[] pt) {
		this(new double[] {0, 0}, pt);
	}

	/**
	 * New line given by points
	 * @param spt
	 * @param ept
	 */
	public LinearROI(double[] spt, double[] ept) {
		this.spt = spt.clone();
		this.ept = ept.clone();
		double x = ept[0] - spt[0];
		double y = ept[1] - spt[1];
		len = Math.hypot(x, y);
		ang = Math.atan2(y, x);
		checkAngle();
		crossHair = false;
	}

	@Override
	protected void setDirty() {
		super.setDirty();
		ept = null;
	}

	/**
	 * Set start point whilst keeping end point
	 * @param pt
	 */
	public void setPointKeepEndPoint(int[] pt) {
		setPointKeepEndPoint(ROIUtils.convertToDoubleArray(pt));
	}

	/**
	 * Set start point whilst keeping end point
	 * @param pt
	 */
	public void setPointKeepEndPoint(double[] pt) {
		double[] ept = getEndPoint();
		spt[0] = pt[0];
		spt[1] = pt[1];
		double x = ept[0] - pt[0];
		double y = ept[1] - pt[1];
		len = Math.hypot(x, y);
		ang = Math.atan2(y, x);
		bounds = null;
		checkAngle();
	}

	/**
	 * @param f 
	 * @return point from normalized length along line
	 */
	@Override
	public double[] getPoint(double f) {
		return new double[] { spt[0] + f*len*cang, 
				spt[1] + f*len*sang };
	}

	/**
	 * @return end point
	 */
	public double[] getEndPoint() {
		if (ept == null)
			ept = getPoint(1);

		return ept;
	}

	/**
	 * @param f 
	 * @return point from normalized length along line
	 */
	public int[] getIntPoint(double f) {
		double[] pt = getPoint(f);
		return new int[] { (int) pt[0], (int) pt[1] };
	}

	/**
	 * @return end point
	 */
	public int[] getIntEndPoint() {
		double[] pt = getPoint(1);
		return new int[] { (int) pt[0], (int) pt[1] };
	}

	/**
	 * Change line to have specified end point
	 * @param eptx 
	 * @param epty 
	 */
	public void setEndPoint(double eptx, double epty) {
		if (ept == null) {
			ept = new double[2];
		}
		ept[0] = eptx;
		ept[1] = epty;
		double x = eptx - spt[0];
		double y = epty - spt[1];
		len = Math.hypot(x, y);
		ang = Math.atan2(y, x);
		bounds = null;
		checkAngle();
	}

	/**
	 * Change line to have specified end point
	 * @param ept
	 */
	public void setEndPoint(double[] ept) {
		setEndPoint(ept[0], ept[1]);
	}

	/**
	 * @param ept
	 */
	public void setEndPoint(int[] ept) {
		setEndPoint(ept[0], ept[1]);
	}

	/**
	 * @return mid point
	 */
	public double[] getMidPoint() {
		return getPoint(0.5);
	}

	/**
	 * Change line to have specified mid point
	 * @param mpt
	 */
	public void setMidPoint(double[] mpt) {
		spt[0] = mpt[0] - 0.5*len*cang;
		spt[1] = mpt[1] - 0.5*len*sang;
		setDirty();
	}

	/**
	 * For Jython
	 * @param angle The angle in degrees to set
	 */
	public void setAngledegrees(double angle) {
		setAngleDegrees(angle);
	}

	/**
	 * Change line to have specified length
	 * @param len
	 */
	public void setLength(double len) {
		this.len = len;
		setDirty();
	}

	/**
	 * @return length
	 */
	public double getLength() {
		return len;
	}

	/**
	 * @param pt
	 * @return angle as measured from midpoint to given point
	 */
	public double getAngleRelativeToMidPoint(int[] pt) {
		return getAngleRelativeToMidPoint(ROIUtils.convertToDoubleArray(pt));
	}
	
	/**
	 * @param pt
	 * @return angle as measured from midpoint to given point
	 */
	public double getAngleRelativeToMidPoint(double[] pt) {
		double[] mpt = getMidPoint();
		mpt[0] = pt[0] - mpt[0];
		mpt[1] = pt[1] - mpt[1];
		return Math.atan2(mpt[1], mpt[0]);
	}
	
	@Override
	public LinearROI copy() {
		LinearROI roi = new LinearROI();
		roi.setName(name);
		roi.setPoint(spt.clone());
		roi.setPlot(plot);
		roi.setAngle(ang);
		roi.setLength(len);
		roi.setCrossHair(crossHair);
		return roi;
	}

	/**
	 * Subtract an offset to starting point
	 * @param pt
	 */
	public void subPoint(int[] pt) {
		spt[0] -= pt[0];
		spt[1] -= pt[1];
		setDirty();
	}

	/**
	 * Subtract an offset to starting point
	 * @param pt
	 */
	public void subPoint(double[] pt) {
		spt[0] -= pt[0];
		spt[1] -= pt[1];	
		setDirty();
	}

	/**
	 * Move line along its length
	 * @param distance
	 */
	public void translateAlongLength(double distance) {
		spt[0] += distance*cang;
		spt[1] += distance*sang;
		setDirty();
	}

	/**
	 * @param f
	 * @return point given by normalised length on perpendicular bisector
	 */
	public int[] getPerpendicularBisectorIntPoint(double f) {
		double[] mpt = getMidPoint();
		double offset = (f-0.5)*len;
		double bangle = ang + Math.PI * 0.5; // bisector angle

		return new int[] { (int) (mpt[0] + offset*Math.cos(bangle)),
				(int) (mpt[1] + offset*Math.sin(bangle)) };
	}

	/**
	 * @param f
	 * @return point given by normalised length on perpendicular bisector
	 */
	public double[] getPerpendicularBisectorPoint(double f) {
		double[] mpt = getMidPoint();
		double offset = (f-0.5)*len;
		double bangle = ang + Math.PI * 0.5; // bisector angle

		return new double[] { mpt[0] + offset*Math.cos(bangle),
				mpt[1] + offset*Math.sin(bangle) };
	}

	/**
	 * @param crossHair The crossHair to set.
	 */
	public void setCrossHair(boolean crossHair) {
		this.crossHair = crossHair;
	}

	/**
	 * @return Returns the crossHair.
	 */
	public boolean isCrossHair() {
		return crossHair;
	}

	@Override
	public RectangularROI getBounds() {
		if (bounds == null) {
			bounds = new RectangularROI(1, 0);
			double[] ept = getEndPoint();
			double pax = Math.min(spt[0], ept[0]);
			double pbx = Math.max(spt[0], ept[0]);
			double pay = Math.min(spt[1], ept[1]);
			double pby = Math.max(spt[1], ept[1]);
			bounds.setPoint(pax, pay);
			bounds.setLengths(pbx - pax, pby - pay);
		}
		return bounds;
	}

	@Override
	public boolean containsPoint(double x, double y) {
		return isNearOutline(x, y, Math.max(Math.ulp(x), Math.ulp(y)));
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		if (!super.isNearOutline(x, y, distance))
			return false;

		return ROIUtils.isNearSegment(cang, sang, len, x - spt[0], y - spt[1], distance);
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, length=%g, angle=%g", Arrays.toString(spt), len, getAngleDegrees());
	}

	@Override
	public double[] getVerticalIntersectionParameters(double x) {
		if (Math.abs(cang) <= Math.ulp(1d)) {
			return x == spt[0] ? new double[] { Double.NaN } : null;
		}
		x -= spt[0];
		double f = x / (len * cang);
		if (f < 0 || f > 1)
			return null;
		return new double[] { f };
	}

	@Override
	public double[] getHorizontalIntersectionParameters(double y) {
		if (sang == 0) {
			return y == spt[1] ? new double[] { Double.NaN } : null;
		}

		y -= spt[1];
		double f = y / (len * sang);
		if (f < 0 || f > 1)
			return null;
		return new double[] { f };
	}

	/**
	 * @return 0
	 */
	@Override
	public double getStartParameter(double d) {
		return 0;
	}

	/**
	 * @return 1
	 */
	@Override
	public double getEndParameter(double d) {
		return 1;
	}

	@Override
	public double[] findHorizontalIntersections(double y) {
		double[] xi = null;
		if (sang == 0) {
			if (y == spt[1]) {
				xi = new double[] {spt[0], getEndPoint()[0]};
				Arrays.sort(xi);
			}
		} else {
			y -= spt[1];
			double f = y / sang;
			if (f >= 0 && f <= len) {
				xi = new double[] {spt[0] + f*cang};
			}
		}
		return xi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (crossHair ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(ept);
		long temp;
		temp = Double.doubleToLongBits(len);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		LinearROI other = (LinearROI) obj;
		if (crossHair != other.crossHair)
			return false;
		if (!Arrays.equals(ept, other.ept))
			return false;
		if (Double.doubleToLongBits(len) != Double.doubleToLongBits(other.len))
			return false;
		return true;
	}
}
