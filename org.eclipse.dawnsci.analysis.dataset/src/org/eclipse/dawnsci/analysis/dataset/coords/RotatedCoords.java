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

package org.eclipse.dawnsci.analysis.dataset.coords;

/**
 * Class to hold and utilise a rotation
 */
public class RotatedCoords {
	double cang, sang;

	/**
	 * @param angle in degrees
	 */
	public RotatedCoords(double angle) {
		cang = Math.cos(Math.toRadians(angle));
		sang = Math.sin(Math.toRadians(angle));
	}

	/**
	 * @param angle
	 * @param isDegrees 
	 */
	public RotatedCoords(double angle, boolean isDegrees) {
		if (isDegrees) {
			cang = Math.cos(Math.toRadians(angle));
			sang = Math.sin(Math.toRadians(angle));
		} else {
			cang = Math.cos(angle);
			sang = Math.sin(angle);
		}
	}

	/**
	 * @param rx 
	 * @param ry 
	 * @return array with original Cartesian coordinates
	 */
	public double[] transformToOriginal(double rx, double ry) {
		double[] car = { rx * cang - ry * sang, rx * sang + ry * cang };
		return car;
	}

	/**
	 * @param ox 
	 * @param oy 
	 * @return array with rotated Cartesian coordinates
	 */
	public double[] transformToRotated(double ox, double oy) {
		double[] car = { ox * cang + oy * sang, -ox * sang + oy * cang };
		return car;
	}
}
