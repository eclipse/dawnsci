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

/**
 * Class for ring region of interest
 */
public class RingROI extends ROIBase implements Serializable {
	protected double rad[]; // radii

	protected boolean clippingCompensation; // compensate for clipping
	private boolean averageArea = true;
	protected double dpp; // Sampling rate used for profile calculations in dots per pixel

	/**
	 * 
	 */
	public RingROI() {
		this(30., 120.);
	}

	/**
	 * Create an annulus centred on origin
	 * @param sr 
	 * @param er
	 */
	public RingROI(double sr, double er) {
		this(0, 0, sr, er);
	}

	/**
	 * Create an annulus
	 * @param ptx
	 * @param pty
	 * @param sr
	 * @param er
	 */
	public RingROI(double ptx, double pty, double sr, double er) {
		this(ptx, pty, sr, er, 1.0, true);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param sr
	 * @param er
	 * @param dpp
	 * @param clip 
	 */
	public RingROI(double ptx, double pty, double sr, double er, double dpp, boolean clip) {
		spt = new double[] {ptx, pty};
		rad = new double[] {sr, er};
		this.dpp = dpp;
		clippingCompensation = clip;
		checkRadii();
	}

	/**
	 * @return Returns reference to the radii
	 */
	public double[] getRadii() {
		return rad;
	}

	/**
	 * @param index 
	 * @return Returns the radius
	 */
	public double getRadius(int index) {
		return rad[index];
	}

	/**
	 * @return Returns the radii
	 */
	public int[] getIntRadii() {
		return new int[] { (int) rad[0], (int) rad[1] };
	}

	/**
	 * @param index 
	 * @return Returns the radius
	 */
	public int getIntRadius(int index) {
		return (int) rad[index];
	}

	/**
	 * @param radius The radii to set
	 */
	public void setRadii(double radius[]) {
		setRadii(radius[0], radius[1]);
	}

	/**
	 * @param startRadius 
	 * @param endRadius 
	 */
	public void setRadii(double startRadius, double endRadius) {
		rad[0] = startRadius;
		rad[1] = endRadius;
		checkRadii();
		setDirty();
	}

	/**
	 * Add an offset to radii
	 * @param radius 
	 */
	public void addRadii(double radius) {
		if (rad[0] + radius < 0)
			radius = -rad[0];
		if (rad[1] + radius < 0)
			radius = -rad[1];
		rad[0] += radius;
		rad[1] += radius;
		setDirty();
	}

	/**
	 * Add an offset to a radius
	 * @param index 
	 * @param radius 
	 */
	public void addRadius(int index, double radius) {
		rad[index] += radius;
		checkRadii();
		setDirty();
	}

	/**
	 * Make sure radii lie in permitted range:
	 *  0 <= rad0, rad1
	 *  rad0 <= rad1
	 */
	private void checkRadii() {
		if (rad[0] < 0)
			rad[0] = 0;
		if (rad[1] < 0)
			rad[1] = 0;
		if (rad[0] > rad[1]) {
			rad[0] = rad[1];
		}
	}

	/**
	 * @param clippingCompensation The clippingCompensation to set.
	 */
	public void setClippingCompensation(boolean clippingCompensation) {
		this.clippingCompensation = clippingCompensation;
	}

	/**
	 * @return Returns the clippingCompensation.
	 */
	public boolean isClippingCompensation() {
		return clippingCompensation;
	}

	/**
	 * Return sampling rate used in profile calculations
	 * 
	 * @return
	 * 			sampling rate in dots per pixel
	 */
	public double getDpp() {
		return dpp;
	}

	/**
	 * Set sampling rate used in profile calculations  
	 * 
	 * @param dpp
	 *			sampling rate in dots per pixel; 
	 */
	public void setDpp(double dpp) {
		this.dpp = dpp;
	}

	/**
	 * Whether the pixel average value shall be calculated instead of integrating
	 * @return averageArea
	 */
	public boolean isAverageArea() {
		return averageArea;
	}

	/**
	 * Set true to not strictly integrate but average over the pixels
	 * @param averageArea
	 */
	public void setAverageArea(boolean averageArea) {
		this.averageArea = averageArea;
	}

	@Override
	public RectangularROI getBounds() {
		if (bounds == null) {
			bounds = new RectangularROI();
			bounds.setPoint(spt[0] - rad[1], spt[1] - rad[1]);
			bounds.setLengths(2*rad[1], 2*rad[1]);
		}
		return bounds;
	}

	@Override
	public void downsample(double subFactor) {
		super.downsample(subFactor);
		rad[0] /= subFactor;
		rad[1] /= subFactor;
		setDirty();
	}

	@Override
	public RingROI copy() {
		RingROI c = new RingROI(spt[0], spt[1], rad[0], rad[1], dpp, clippingCompensation);
		c.setAverageArea(averageArea);
		c.name = name;
		c.plot = plot;
		return c;
	}

	@Override
	public boolean containsPoint(double x, double y) {
		x -= spt[0];
		y -= spt[1];

		double r = Math.hypot(x, y);
		return r >= rad[0] && r <= rad[1];
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		x -= spt[0];
		y -= spt[1];

		double r = Math.hypot(x, y);
		return Math.abs(r - rad[0]) <= distance || Math.abs(r - rad[1]) <= distance;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Centre %s Radii %s", Arrays.toString(spt), Arrays.toString(rad));
	}

	@Override
	public double[] findHorizontalIntersections(double y) {
		y -= spt[1];
		if (y < -rad[1] || y > rad[1]) {
			return null;
		}

		double x = spt[0];
		if (y == -rad[1] || y == rad[1]) { // outer touching case
			return new double[]{x};
		}

		double xo = Math.sqrt(rad[1]*rad[1] - y*y);
		if (y < -rad[0] || y > rad[0]) {
			 return new double[] {x - xo, x + xo};
		}

		if (y == -rad[0] || y == rad[0]) { // inner touching case
			return new double[]{x - xo, x, x + xo};
		}

		double xi = Math.sqrt(rad[0]*rad[0] - y*y);
		return new double[]{x - xo, x - xi, x + xi, x + xo};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (averageArea ? 1231 : 1237);
		result = prime * result + (clippingCompensation ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(dpp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(rad);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		RingROI other = (RingROI) obj;
		if (averageArea != other.averageArea)
			return false;
		if (clippingCompensation != other.clippingCompensation)
			return false;
		if (Double.doubleToLongBits(dpp) != Double.doubleToLongBits(other.dpp))
			return false;
		if (!Arrays.equals(rad, other.rad))
			return false;
		return true;
	}
}
