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
 * 
 * Usual definition of 2D polar coordinates with respect to 2D Cartesian coordinate system
 *
 */
public class SectorCoords {
	double x, y;
	double r, p;

	/**
	 * @param ca
	 * @param cb (in degrees, if not Cartesian)
	 * @param isCartesian
	 */
	public SectorCoords(double ca, double cb, boolean isCartesian) {
		if (isCartesian) {
			x = ca;
			y = cb;
			double[] pol = convertFromCartesianToPolarRadians(x, y);
			r = pol[0];
			p = pol[1];
		} else {
			r = ca;
			p = Math.toRadians(cb);
			double[] car = convertFromPolarRadians(r, p);
			x = car[0];
			y = car[1];
		}
	}

	/**
	 * @param spt 
	 * @param pt
	 */
	public SectorCoords(double[] spt, double[] pt) {
		this(pt[0] - spt[0], pt[1] - spt[1], true);
	}

	public static double[] convertFromPolarRadians(double r, double p) {
		return new double[] {r*Math.cos(p), r*Math.sin(p)};
	}

	public static double[] convertFromPolarDegrees(double r, double p) {
		return convertFromPolarRadians(r, Math.toRadians(p));
	}

	public static double[] convertFromCartesianToPolarRadians(double x, double y) {
		double r = Math.hypot(x, y);
		double p = Math.atan2(y, x);
		if (p < 0) p += 2*Math.PI;
		return new double[] {r, p};
	}

	/**
	 * 
	 * @return array with Cartesian coordinates
	 */
	public double[] getCartesian() {
		double[] car = { x, y };
		return car;
	}

	/**
	 * 
	 * @return array with Polar coordinates (in degrees)
	 */
	public double[] getPolar() {
		double[] pol = { r, Math.toDegrees(p) };
		return pol;
	}

	/**
	 * 
	 * @return array with Polar coordinates (in radians)
	 */
	public double[] getPolarRadians() {
		double[] pol = { r, p };
		return pol;
	}

	/**
	 * Add an amount to the azimuthal (polar) angle in degrees
	 * 
	 * @param dp change in phi
	 */
	public void addPhi(double dp) {
		p += Math.toRadians(dp);
		x = r*Math.cos(p);
		y = r*Math.sin(p);
	}
}
