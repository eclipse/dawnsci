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

import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.roi.IParametricROI;

/**
 * An elliptical region of interest with the start point as the centre
 */
public class EllipticalROI extends OrientableROIBase implements IParametricROI {
	private double[] saxis; // semi-axes

	/**
	 * No argument constructor need for serialization
	 */
	public EllipticalROI() {
		this(1, 1, 0, 0, 0);
	}

	/**
	 * Create a circular ROI
	 * @param croi
	 */
	public EllipticalROI(CircularROI croi) {
		this(croi.getRadius(), croi.getRadius(), 0, croi.getPointX()	, croi.getPointY());
	}

	/**
	 * Create a circular ROI
	 * @param radius
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public EllipticalROI(double radius, double ptx, double pty) {
		this(radius, radius, 0, ptx, pty);
	}

	/**
	 * Create an elliptical ROI
	 * @param major semi-axis
	 * @param minor semi-axis
	 * @param angle major axis angle
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public EllipticalROI(double major, double minor, double angle, double ptx, double pty) {
		spt = new double[] { ptx, pty };
		saxis = new double[] { major, minor };
		ang = angle;
		checkAngle();
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		saxis[0] /= subFactor;
		saxis[1] /= subFactor;
		setDirty();
	}

	@Override
	public EllipticalROI copy() {
		EllipticalROI c = new EllipticalROI(saxis[0], saxis[1], ang, spt[0], spt[1]);
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * @return Returns reference to the semi-axes
	 */
	public double[] getSemiAxes() {
		return saxis;
	}

	/**
	 * @param index (should be 0 or 1 for major or minor axis)
	 * @return Returns the semi-axis value
	 */
	public double getSemiAxis(int index) {
		if (index < 0 || index > 1)
			throw new IllegalArgumentException("Index should be 0 or 1");
		return saxis[index];
	}

	/**
	 * Set semi-axis values
	 * @param semiaxis
	 */
	public void setSemiAxes(double[] semiaxis) {
		if (saxis.length < 2)
			throw new IllegalArgumentException("Need at least two semi-axis values");
		saxis[0] = semiaxis[0];
		saxis[1] = semiaxis[1];
		setDirty();
	}

	/**
	 * Set semi-axis values
	 * @param semiaxis
	 */
	public void setSemiaxes(double[] semiaxis) {
		setSemiAxes(semiaxis);
	}

	/**
	 * Set semi-axis value
	 * @param index (should be 0 or 1 for major or minor axis)
	 * @param semiaxis
	 */
	public void setSemiAxis(int index, double semiaxis) {
		if (index < 0 || index > 1)
			throw new IllegalArgumentException("Index should be 0 or 1");
		saxis[index] = semiaxis;
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
	 * @return true if ellipse is circular (i.e. its axes have the same length)
	 */
	public boolean isCircular() {
		return saxis[0] == saxis[1];
	}

	/**
	 * @return aspect ratio, i.e. ratio of major to minor axes
	 */
	public double getAspectRatio() {
		return saxis[0] / saxis[1];
	}

	/**
	 * Get point on ellipse at given angle
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
	 * Get point on ellipse at given angle relative to centre
	 * @param angle in radians
	 * @return point 
	 */
	public double[] getRelativePoint(double angle) {
		double cb = Math.cos(angle);
		double sb = Math.sin(angle);

		return new double[] { saxis[0] * cang * cb - saxis[1] * sang * sb,
				saxis[0] * sang * cb + saxis[1] * cang * sb };
	}

	/**
	 * Get point on ellipse at given angle
	 * @param angle in degrees
	 * @return point 
	 */
	public double[] getPointDegrees(double angle) {
		return getPoint(Math.toRadians(angle));
	}

	/**
	 * Get distance from centre to point on ellipse at given angle
	 * @param angle in radians
	 * @return distance
	 */
	public double getDistance(double angle) {
		double[] p = getRelativePoint(angle);
		return Math.hypot(p[0], p[1]);
	}

	@Override
	public RectangularROI getBounds() {
		if (bounds == null) {
			// angles which produce stationary points in x and y
			double[] angles = new double[] { Math.atan2(-saxis[1] * sang, saxis[0] * cang),
					Math.atan2(saxis[1] * cang, saxis[0] * sang) };

			double[] max = getRelativePoint(angles[0]);
			double[] min = max.clone();

			ROIUtils.updateMaxMin(max, min, getRelativePoint(angles[0] + Math.PI));

			ROIUtils.updateMaxMin(max, min, getRelativePoint(angles[1]));

			ROIUtils.updateMaxMin(max, min, getRelativePoint(angles[1] + Math.PI));

			bounds = new RectangularROI();
			bounds.setLengths(max[0] - min[0], max[1] - min[1]);
			bounds.setPoint(spt[0] + min[0], spt[1] + min[1]);
		}
		return bounds;
	}

	protected double getAngleRelative(double x, double y) {
		return Math.atan2(saxis[0]*(cang*y - sang*x), saxis[1]*(cang*x + sang*y));
	}

	@Override
	public boolean containsPoint(double x, double y) {
		x -= spt[0];
		y -= spt[1];
		double a = getAngleRelative(x, y);
		return Math.hypot(x, y) <= getDistance(a);
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		x -= spt[0];
		y -= spt[1];
		double a = getAngleRelative(x, y);
		return Math.abs(getDistance(a) - Math.hypot(x, y)) <= distance;
	}

	/**
	 * Determine if ellipse is within an axis-aligned rectangle
	 * @param rect
	 * @return true if ellipse lies wholly within rectangle
	 */
	public boolean isContainedBy(RectangularROI rect) {
		double as = saxis[0]*saxis[0];
		double bs = saxis[1]*saxis[1];
		double dx = Math.sqrt(as*cang*cang + bs*sang*sang);
		double dy = Math.sqrt(as*sang*sang + bs*cang*cang);
		double ang = Math.abs(rect.getAngle());
		double[] a = rect.getPointRef();
		double[] b = rect.getEndPoint();
		if (ang == 0 || ang == Math.PI) {
			// do nothing
		} else if (ang == 0.5*Math.PI || ang == 1.5*Math.PI) {
			double t = dx;
			dx = dy;
			dy = t;
		} else {
			throw new UnsupportedOperationException("Non-axis-aligned rectangles are not implemented yet");
		}

		double x = a[0] - spt[0];
		if (x > -dx)
			return false;
		x = b[0] - spt[0];
		if (x < dx)
			return false;

		double y = a[1] - spt[1];
		if (y > -dy)
			return false;
		y = b[1] - spt[1];
		if (y < dy)
			return false;

		return true;
	}

	@Override
	protected void setDirty() {
		super.setDirty();
		qhB = Double.NaN;
	}

	/**
	 * Calculate values for angle at which ellipse will intersect vertical line of given x
	 * @param x
	 * @return possible angles
	 */
	@Override
	public double[] getVerticalIntersectionParameters(double x) {
		double tx = saxis[0]*cang;
		double ty = saxis[1]*sang;
		double r = Math.hypot(tx, ty); 

		x -= spt[0];
		if (x < -r || x > r) {
			return null;
		}
		x /= r;
		double t = Math.atan2(ty, tx);
		if (x == -1 || x == 1) { // touching case
			return sanifyAngles(Math.acos(x) - t);
		}
		x = Math.acos(x);
		return sanifyAngles(x - t, 2 * Math.PI - x - t);
	}

	/**
	 * Calculate values for angle at which ellipse will intersect horizontal line of given y
	 * @param y
	 * @return possible angles
	 */
	@Override
	public double[] getHorizontalIntersectionParameters(double y) {
		double tx = saxis[0] * sang;
		double ty = saxis[1] * cang;
		double r = Math.hypot(tx, ty); 

		y -= spt[1];
		if (y < -r || y > r) {
			return null;
		}
		y /= r;
		double t = Math.atan2(tx, ty);
		if (y == -1 || y == 1) { // touching case
			return sanifyAngles(Math.asin(y) - t);
		}
		y = Math.asin(y);
		return sanifyAngles(y - t, Math.PI - y - t);
	}

	@Override
	public double getStartParameter(double d) {
		return 0;
	}

	@Override
	public double getEndParameter(double d) {
		return Math.PI * 2;
	}

	@Override
	public String toString() {
		if (isCircular()) {
			return super.toString() + String.format("point=%s, radius=%g, angle=%g", Arrays.toString(spt), saxis[0], getAngleDegrees());
		}
		return super.toString() + String.format("point=%s, semiaxes=%s, angle=%g", Arrays.toString(spt), Arrays.toString(saxis), getAngleDegrees());
	}

	private transient double qhB = Double.NaN;  // coefficients of quadratic equation of ellipse
	private transient double qBC = -1;
	private transient double qC2 = -1;

	private void updateQValues() {
		double a = saxis[0];
		double b = saxis[1];
		double a2 = a * a;
		double b2 = b * b;
		double f = a2 * sang * sang + b2 * cang * cang;
		double asbs = (a2 + b2) / f;
		qhB = - asbs * sang * cang / 2;
		qBC = qhB * qhB - asbs + 1;
		qC2 = - a2 * b2 / f;
	}

	@Override
	public double[] findHorizontalIntersections(double y) {
		if (Double.isNaN(qhB)) {
			updateQValues();
		}
		double disc = qBC * y * y - qC2;
		double[] xi = null;
		double hb = qhB * y;
		if (Math.abs(disc) < Math.ulp(hb*hb + 1e-15)) {
			xi = new double[] { -hb };
		} else if (disc > 0) {
			disc = Math.sqrt(disc);
			xi = new double[] { -hb - disc, -hb + disc };
		}
		return xi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(saxis);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		EllipticalROI other = (EllipticalROI) obj;
		if (!Arrays.equals(saxis, other.saxis))
			return false;
		return true;
	}
}
