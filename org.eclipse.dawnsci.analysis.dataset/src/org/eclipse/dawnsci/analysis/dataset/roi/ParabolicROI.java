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
 * A parabolic region of interest with the start point as the focus. In the rotated frame,
 * it can be represented as x-p = 4 a y^2 where p = 2 a
 */
public class ParabolicROI extends OrientableROIBase implements IParametricROI, Serializable {
	private double tp;   // twice focal parameter (or latus rectum) 

	/**
	 * No argument constructor need for serialization
	 */
	public ParabolicROI() {
		this(2, 0, 0, 0);
	}

	/**
	 * Create a parabolic ROI
	 * @param focal length
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public ParabolicROI(double focal, double ptx, double pty) {
		this(focal, 0, ptx, pty);
	}

	/**
	 * Create a parabolic ROI
	 * @param focal length
	 * @param angle major axis angle
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public ParabolicROI(double focal, double angle, double ptx, double pty) {
		spt = new double[] { ptx, pty };
		setFocalParameter(focal);
		ang = angle;
		checkAngle();
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		tp /= subFactor;
	}

	@Override
	public ParabolicROI copy() {
		ParabolicROI c = new ParabolicROI(0.5*tp, ang, spt[0], spt[1]);
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * @return Returns focal parameter
	 */
	public double getFocalParameter() {
		return 0.5*tp;
	}

	/**
	 * Set focal parameter
	 * @param focal
	 */
	public void setFocalParameter(double focal) {
		tp = 2*focal;
		setDirty();
	}

	/**
	 * Get point on parabola at given angle
	 * @param angle in radians
	 * @return point 
	 */
	@Override
	public double[] getPoint(double angle) {
		double[] pt = getRelativePoint(angle);
		pt[0] += spt[0];
		pt[1] += spt[1];
		return pt;
	}

	/**
	 * Get point on parabola at given angle relative to focus
	 * @param angle in radians
	 * @return point 
	 */
	public double[] getRelativePoint(double angle) {
		double cb = Math.cos(angle);
		if (cb == 1) {
			double[] pt = transformToOriginal(1, 0);
			if (pt[0] != 0)
				pt[0] *= Double.POSITIVE_INFINITY;
			if (pt[1] != 0)
				pt[1] *= Double.POSITIVE_INFINITY;
			return pt;
		}
		double sb = Math.sin(angle);
		double r = tp / (1 - cb);

		return transformToOriginal(r * cb, r * sb);
	}

	/**
	 * Get point on parabola at given angle
	 * @param angle in degrees
	 * @return point 
	 */
	public double[] getPointDegrees(double angle) {
		return getPoint(Math.toRadians(angle));
	}

	/**
	 * Get distance from focus to point on parabola at given angle
	 * @param angle in radians
	 * @return distance
	 */
	public double getDistance(double angle) {
		double[] p = getRelativePoint(angle);
		return Math.hypot(p[0], p[1]);
	}

	/**
	 * <emph>Important:</emph> this returns null as a parabola is unbounded
	 */
	@Override
	public RectangularROI getBounds() {
		return null;
	}

	/**
	 * Determine if point is on or inside ellipse
	 * @param x
	 * @param y
	 * @return true if ellipse contains point
	 */
	@Override
	public boolean containsPoint(double x, double y) {
		return false;
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		x -= spt[0];
		y -= spt[1];

		double[] pt = transformToRotated(x, y);
		return Math.abs(pt[0] * pt[0] - tp * pt[1]) <= distance;
	}

	/**
	 * Calculate values for angle at which parabola will intersect vertical line of given x
	 * @param x
	 * @return possible angles
	 */
	@Override
	public double[] getVerticalIntersectionParameters(double x) {
		double[] pt = transformXToOriginal(tp);
		x -= spt[0];

		pt[0] += x;
		x /= Math.hypot(pt[0], pt[1]);
		if (x < -1 || x > 1) {
			return null;
		}
		double t = Math.atan2(pt[1], pt[0]);
		if (x == -1 || x == 1) { // touching case
			return sanifyAngles(Math.acos(x) - t);
		}
		x = Math.acos(x);
		return sanifyAngles(x - t, 2 * Math.PI - x - t);
	}

	/**
	 * Calculate values for angle at which parabola will intersect horizontal line of given y
	 * @param y
	 * @return possible angles
	 */
	@Override
	public double[] getHorizontalIntersectionParameters(double y) {
		double[] pt = transformXToOriginal(tp);
		y -= spt[1];

		pt[1] += y;
		y /= Math.hypot(pt[0], pt[1]);
		if (y < -1 || y > 1) {
			return null;
		}
		double t = Math.atan2(pt[1], pt[0]);
		if (y == -1 || y == 1) { // touching case
			return sanifyAngles(Math.acos(y) - t);
		}
		y = Math.asin(y);
		return sanifyAngles(y - t, Math.PI - y - t);
	}

	/**
	 * @param d
	 * @return start angle at distance from focus
	 */
	@Override
	public double getStartParameter(double d) {
		return Math.acos(1 - tp/d);
	}

	/**
	 * @param d
	 * @return end angle at distance from focus
	 */
	@Override
	public double getEndParameter(double d) {
		return Math.PI * 2 - getStartParameter(d);
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, focal=%g, angle=%g", Arrays.toString(spt),
				getFocalParameter(), getAngleDegrees());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(tp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		ParabolicROI other = (ParabolicROI) obj;
		if (Double.doubleToLongBits(tp) != Double.doubleToLongBits(other.tp))
			return false;
		return true;
	}
}
