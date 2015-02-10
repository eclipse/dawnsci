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
 * A hyperbolic region of interest with the start point as the focus. In the rotated frame,
 * it can be represented as (x+l/e)^2 / a^2 - y^2 / b^2 = 1, where l = b^2/a and e = sqrt(1 + b^2/a^2)
 */
public class HyperbolicROI extends OrientableROIBase implements IParametricROI, Serializable {
	private double l;   // semi-latus rectum
	private double e;   // eccentricity

	/**
	 * No argument constructor need for serialization
	 */
	public HyperbolicROI() {
		this(1, Math.sqrt(2), 0, 0);
	}

	/**
	 * Create a hyperbolic ROI
	 * @param semi semi-latus rectum (half length of chord parallel to directrix, passing through focus)
	 * @param eccentricity measure of non-circularity (>1)
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public HyperbolicROI(double semi, double eccentricity, double ptx, double pty) {
		this(semi, eccentricity, 0, ptx, pty);
	}

	/**
	 * Create a hyperbolic ROI
	 * @param semi semi-latus rectum (half length of chord parallel to directrix, passing through focus)
	 * @param eccentricity measure of non-circularity (>1)
	 * @param angle major axis angle
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public HyperbolicROI(double semi, double eccentricity, double angle, double ptx, double pty) {
		spt = new double[] { ptx, pty };
		l = semi;
		e = eccentricity;
		ang = angle;
		checkAngle();
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		l /= subFactor;
	}

	@Override
	public HyperbolicROI copy() {
		HyperbolicROI c = new HyperbolicROI(l, e, ang, spt[0], spt[1]);
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * @return Returns semi-latus rectum
	 */
	public double getSemilatusRectum() {
		return l;
	}

	/**
	 * Set semi-latus rectum
	 * @param semi
	 */
	public void setSemilatusRectum(double semi) {
		l = semi;
		setDirty();
	}

	/**
	 * @return eccentricity
	 */
	public double getEccentricity() {
		return e;
	}

	/**
	 * Set eccentricity
	 * @param eccentricity
	 */
	public void setEccentricity(double eccentricity) {
		e = eccentricity;
		setDirty();
	}

	/**
	 * Get point on hyperbola at given angle
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
	 * Get point on hyperbola at given angle relative to focus
	 * @param angle in radians
	 * @return point 
	 */
	public double[] getRelativePoint(double angle) {
		double cb = Math.cos(angle);
		double denom = 1 - e * cb;
		// NB when this is negative, the point is on other nappe of double cone
		if (denom == 0) {
			double[] pt = transformToOriginal(1, 0);
			if (pt[0] != 0)
				pt[0] *= Double.POSITIVE_INFINITY;
			if (pt[1] != 0)
				pt[1] *= Double.POSITIVE_INFINITY;
			return pt;
//		} else if (denom < 0) {
//			return new double[] {Double.NaN, Double.NaN};
		}
		double sb = Math.sin(angle);
		double r = l / denom;

		return transformToOriginal(r * cb, r * sb);
	}

	/**
	 * @return angle of asymptote (which acts as a minimum for angle)
	 */
	public double getAsymptoteAngle() {
		return Math.acos(1 / e);
	}

	/**
	 * Get point on hyperbolic at given angle
	 * @param angle in degrees
	 * @return point 
	 */
	public double[] getPointDegrees(double angle) {
		return getPoint(Math.toRadians(angle));
	}

	/**
	 * Get distance from focus to point on hyperbolic at given angle
	 * @param angle in radians
	 * @return distance
	 */
	public double getDistance(double angle) {
		double[] p = getRelativePoint(angle);
		return Math.hypot(p[0], p[1]);
	}

	/**
	 * <emph>Important:</emph> this returns null as a hyperbolic is unbounded
	 */
	@Override
	public RectangularROI getBounds() {
		return null;
	}

	/**
	 * Determine if point is on or inside hyperbolic
	 * @param x
	 * @param y
	 * @return true if hyperbolic contains point
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
		double a = Math.atan2(pt[1], pt[0]);
		double[] pr = getRelativePoint(a);
		return Math.hypot(pt[0] - pr[0], pt[1] - pr[1]) <= distance;
	}

	/**
	 * Calculate values for angle at which hyperbola will intersect vertical line of given x
	 * @param x
	 * @return possible angles
	 */
	@Override
	public double[] getVerticalIntersectionParameters(double x) {
		double[] pt = transformXToOriginal(l);
		x -= spt[0];

		pt[0] += x*e;
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
	 * Calculate values for angle at which hyperbola will intersect horizontal line of given y
	 * @param y
	 * @return possible angles
	 */
	@Override
	public double[] getHorizontalIntersectionParameters(double y) {
		double[] pt = transformXToOriginal(l);
		y -= spt[1];

		pt[1] += y*e;
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
	 * @return start angle of positive branch at distance from focus
	 */
	@Override
	public double getStartParameter(double d) {
		return Math.acos((1 - l / d) / e);
	}

	/**
	 * @param d
	 * @return end angle of positive branch at distance from focus
	 */
	@Override
	public double getEndParameter(double d) {
		return Math.PI * 2 - getStartParameter(d);
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, semi-latus=%g, eccentricity=%g, angle=%g",
				Arrays.toString(spt), l, e, getAngleDegrees());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(e);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(l);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		HyperbolicROI other = (HyperbolicROI) obj;
		if (Double.doubleToLongBits(e) != Double.doubleToLongBits(other.e))
			return false;
		if (Double.doubleToLongBits(l) != Double.doubleToLongBits(other.l))
			return false;
		return true;
	}
}
