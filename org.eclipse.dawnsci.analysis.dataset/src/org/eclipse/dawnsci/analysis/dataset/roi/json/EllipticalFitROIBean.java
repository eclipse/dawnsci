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

public class EllipticalFitROIBean extends ROIBean {

	public static final String TYPE = "EllipticalFitROI";
	private double[] semiAxes; // semi-axes
	private double angle; // angles in radians
	private List<double[]> points;

	public EllipticalFitROIBean() {
		this.type = TYPE;
	}

	public EllipticalFitROIBean(String name) {
		this.type = name;
	}

	/**
	 * @return Returns reference to the semi-axes
	 */
	public double[] getSemiAxes() {
		return semiAxes;
	}

	/**
	 * Set semi-axis values
	 * @param semiAxes
	 */
	public void setSemiAxes(double[] semiAxes) {
		this.semiAxes = semiAxes;
	}

	/**
	 * @return Returns the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle The major axis angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Set points which are then used to fit ellipse
	 * @param points
	 */
	public void setPoints(List<double[]> points) {
		this.points = points;
	}

	/**
	 * @return points in polygon for fitting
	 */
	public List<double[]> getPoints() {
		return points;
	}


}
