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
 * A circular region of interest
 */
public class CircularROI extends ROIBase implements IParametricROI, Serializable {
	private double rad;

	/**
	 * Create a unit circle centred on origin
	 */
	public CircularROI() {
		this(1, 0, 0);
	}

	/**
	 * Create circle of given radius centred on origin
	 * @param radius
	 */
	public CircularROI(double radius) {
		this(radius, 0, 0);
	}

	/**
	 * Create circle of given radius with specified centre
	 * @param radius
	 * @param ptx centre point x value
	 * @param pty centre point y value
	 */
	public CircularROI(double radius, double ptx, double pty) {
		spt = new double[] { ptx, pty };
		rad = Math.abs(radius);
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		rad /= subFactor;
		setDirty();
	}

	@Override
	public CircularROI copy() {
		CircularROI c = new CircularROI(rad, spt[0], spt[1]);
		c.name = name;
		c.plot = plot;
		return c;
	}

	/**
	 * @return radius
	 */
	public double getRadius() {
		return rad;
	}

	/**
	 * Set radius
	 * @param radius
	 */
	public void setRadius(double radius) {
		rad = radius;
		setDirty();
	}

	/**
	 * Get point on circle at given angle
	 * @param angle in radians
	 * @return point 
	 */
	@Override
	public double[] getPoint(double angle) {
		return new double[] { spt[0] + rad*Math.cos(angle), 
				spt[1] + rad*Math.sin(angle) };
	}

	/**
	 * Get point on circle at given angle
	 * @param angle in degrees
	 * @return point 
	 */
	public double[] getPointDegrees(double angle) {
		return getPoint(Math.toRadians(angle));
	}

	/**
	 * Get centre point
	 * @return x and y coordinates in double array
	 */
	public double[] getCentre() {
		return getPoint();
	}

	/**
	 * Set centre point
	 * @param centre
	 */
	public void setCentre(double... centre) {
		setPoint(centre);
	}

	@Override
	public RectangularROI getBounds() {
		if (bounds == null) {
			bounds = new RectangularROI();
			bounds.setPoint(spt[0] - rad, spt[1] - rad);
			bounds.setLengths(2 * rad, 2 * rad);
		}
		return bounds;
	}

	@Override
	public boolean containsPoint(double x, double y) {
		return Math.hypot(x - spt[0], y - spt[1]) <= rad;
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		double r = Math.hypot(x - spt[0], y - spt[1]);

		return Math.abs(r - rad) <= distance;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, radius=%g", Arrays.toString(spt), rad);
	}
	
	/**
	 * Calculate values for angle at which circle will intersect vertical line of given x
	 * @param x
	 * @return possible angles
	 */
	@Override
	public double[] getVerticalIntersectionParameters(double x) {

		x -= spt[0];
		if (x < -rad || x > rad) {
			return null;
		}

		if (x == -rad || x == rad) { // touching case
			return new double[]{x < 0 ? Math.PI : 0};
		}
		
		double ang = Math.acos(x/rad);
		
		return new double[] {ang, (Math.PI*2)-ang};
	}

	/**
	 * Calculate values for angle at which circle will intersect horizontal line of given y
	 * @param y
	 * @return possible angles
	 */
	@Override
	public double[] getHorizontalIntersectionParameters(double y) {

		y -= spt[1];
		if (y < -rad || y > rad) {
			return null;
		}

		if (y == -rad || y == rad) { // touching case
			return new double[]{y < 0 ? Math.PI : 0};
		}

		double ang = Math.acos(y/rad);

		return new double[] {ang, (Math.PI*2)-ang};
	}

	/**
	 * Calculate x values at which circle will intersect horizontal line of given y
	 * @param y
	 * @return x values
	 */
	@Override
	public double[] findHorizontalIntersections(double y) {
		y -= spt[1];
		if (y < -rad || y > rad) {
			return null;
		}

		if (y == -rad || y == rad) { // touching case
			return new double[]{spt[0]};
		}

		double x = Math.sqrt(rad*rad - y*y);
		return new double[] {spt[0] - x, spt[0] + x};
	}

	@Override
	public double getAngleDegrees() {
		return 0;
	}

	@Override
	public void setAngleDegrees(double degrees) {
	}

	@Override
	public double getAngle() {
		return 0;
	}

	@Override
	public void setAngle(double angle) {
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(rad);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		CircularROI other = (CircularROI) obj;
		if (Double.doubleToLongBits(rad) != Double.doubleToLongBits(other.rad))
			return false;
		return true;
	}
}
