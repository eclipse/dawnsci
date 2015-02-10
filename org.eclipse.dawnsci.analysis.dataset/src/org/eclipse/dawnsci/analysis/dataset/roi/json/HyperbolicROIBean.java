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
 * A hyperbolic region of interest with the start point as the focus. In the rotated frame,
 * it can be represented as (x+l/e)^2 / a^2 - y^2 / b^2 = 1, where l = b^2/a and e = sqrt(1 + b^2/a^2)
 */
public class HyperbolicROIBean extends ROIBean {

	public static final String TYPE = "HyperbolicROI";

	private double semi;   // semi-latus rectum
	private double eccentricity;   // eccentricity
	private double angle; // major axis angle

	public HyperbolicROIBean() {
	}

	public void setSemi(double semi) {
		this.semi = semi;
	}
	
	public double getSemi() {
		return semi;
	}

	public void setEccentricity(double eccentricity) {
		this.eccentricity = eccentricity;
	}

	public double getEccentricity() {
		return eccentricity;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, semi-latus=%g, eccentricity=%g, angle=%g",
				Arrays.toString(startPoint), semi, eccentricity, angle);
	}

}
