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

}
