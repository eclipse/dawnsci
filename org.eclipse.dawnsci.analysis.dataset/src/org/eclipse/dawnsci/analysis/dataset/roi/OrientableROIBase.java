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

import org.eclipse.dawnsci.analysis.api.roi.IOrientableROI;

public class OrientableROIBase extends ROIBase implements IOrientableROI, Serializable {
	protected double ang;     // angle in radians
	protected double cang;
	protected double sang;

	@Override
	public OrientableROIBase copy() {
		OrientableROIBase c = new OrientableROIBase();
		c.name = name;
		c.spt = spt.clone();
		c.plot = plot;
		c.ang = ang;
		c.cang = cang;
		c.sang = sang;
		return c;
	}

	/**
	 * @return Returns the angle in degrees
	 */
	@Override
	public double getAngleDegrees() {
		return Math.toDegrees(ang);
	}

	/**
	 * @param angle The angle in degrees to set
	 */
	@Override
	public void setAngleDegrees(double angle) {
		setAngle(Math.toRadians(angle));
	}

	private final static double TWOPI = 2.0 * Math.PI;
	/**
	 * Make sure angle lie in permitted ranges:
	 *  0 <= ang < 2*pi
	 */
	protected void checkAngle() {
		while (ang < 0) {
			ang += TWOPI;
		}
		while (ang >= TWOPI) {
			ang -= TWOPI;
		}
		cang = Math.cos(ang);
		sang = Math.sin(ang);
	}

	/**
	 * @param rx 
	 * @param ry 
	 * @return array with original Cartesian coordinates
	 */
	protected double[] transformToOriginal(double rx, double ry) {
		double[] car = { rx * cang - ry * sang, rx * sang + ry * cang };
		return car;
	}

	/**
	 * @param rx 
	 * @return array with original Cartesian coordinates
	 */
	protected double[] transformXToOriginal(double rx) {
		double[] car = { rx * cang, rx * sang };
		return car;
	}

	/**
	 * @param ox 
	 * @param oy 
	 * @return array with rotated Cartesian coordinates
	 */
	protected double[] transformToRotated(double ox, double oy) {
		double[] car = { ox * cang + oy * sang, -ox * sang + oy * cang };
		return car;
	}

	/**
	 * @param angle
	 * @return angle between 0 and 2pi
	 */
	protected static double sanifyAngle(double angle) {
		while (angle < 0) {
			angle += TWOPI;
		}
		while (angle >= TWOPI) {
			angle -= TWOPI;
		}
		return angle;
	}

	/**
	 * @param angles
	 * @return angles between 0 and 2pi
	 */
	protected static double[] sanifyAngles(double... angles) {
		for (int i = 0; i < angles.length; i++) {
			angles[i] = sanifyAngle(angles[i]);
		}
		return angles;
	}

	/**
	 * @return Returns the angle
	 */
	@Override
	public double getAngle() {
		return ang;
	}

	/**
	 * @param angle The major axis angle to set
	 */
	@Override
	public void setAngle(double angle) {
		ang = angle;
		checkAngle();
		setDirty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(ang);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cang);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sang);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;

		OrientableROIBase other = (OrientableROIBase) obj;
		if (Double.doubleToLongBits(ang) != Double.doubleToLongBits(other.ang))
			return false;
		if (Double.doubleToLongBits(cang) != Double.doubleToLongBits(other.cang))
			return false;
		if (Double.doubleToLongBits(sang) != Double.doubleToLongBits(other.sang))
			return false;
		return true;
	}
}
