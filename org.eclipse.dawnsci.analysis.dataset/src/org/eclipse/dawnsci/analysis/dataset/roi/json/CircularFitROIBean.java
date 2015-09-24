/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.roi.json;

import java.util.List;

public class CircularFitROIBean extends ROIBean {

	public static final String TYPE = "CircularFitROI";
	private List<double[]> points;
	private double radius;

	public CircularFitROIBean() {
		this.type = TYPE;
	}

	public CircularFitROIBean(String name) {
		this.type = name;
	}

	/**
	 * Returns the radius
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Set radius
	 * @param radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Set points which are then used to fit circle
	 * @param points
	 */
	public void setPoints(List<double[]> points) {
		this.points = points;
	}

	/**
	 * Set points which are then used to fit circle
	 * @return points
	 */
	public List<double[]> getPoints() {
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
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
		CircularFitROIBean other = (CircularFitROIBean) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

}
