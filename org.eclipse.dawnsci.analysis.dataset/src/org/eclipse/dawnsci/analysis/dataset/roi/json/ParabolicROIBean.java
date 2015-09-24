/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.dataset.roi.json;

import java.util.Arrays;

/**
 * A parabolic region of interest with the start point as the focus. In the rotated frame,
 * it can be represented as x-p = 4 a y^2 where p = 2 a
 */
public class ParabolicROIBean extends ROIBean {
	public static final String TYPE = "ParabolicROI";

	private double focalParameter;
	private double angle;

	public ParabolicROIBean() {
	}

	/**
	 * @return Returns focal parameter
	 */
	public double getFocalParameter() {
		return focalParameter;
	}

	/**
	 * Set focal parameter
	 * @param focalParameter
	 */
	public void setFocalParameter(double focalParameter) {
		this.focalParameter = focalParameter;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, focal=%g, angle=%g", Arrays.toString(startPoint),
				getFocalParameter(), getAngle());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(focalParameter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParabolicROIBean other = (ParabolicROIBean) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		if (Double.doubleToLongBits(focalParameter) != Double.doubleToLongBits(other.focalParameter))
			return false;
		return true;
	}

}
