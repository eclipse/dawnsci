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

package org.eclipse.dawnsci.analysis.dataset.roi.fitting;

import java.io.Serializable;

import org.apache.commons.math3.analysis.UnivariateFunction;


/**
 * This class represents the derivative with respect to angle of the geometric
 * distance function from a given point to an ellipse. It is used by an optimizer
 * to find the closest point on an ellipse to a given point.
 * <p>
 * The ellipse is centred on the origin.
 */
public class AngleDerivativeFunction implements UnivariateFunction, Serializable {
	double ra, rb; // major and minor semi-axes
	double alpha;  // orientation angle of major axis
	double A, B, C;

	/**
	 * Set major and minor semi-axes of ellipse
	 */
	public void setRadii(final double a, final double b) {
		ra = a;
		rb = b;
		A = ra*ra - rb*rb;
	}

	/**
	 * Set angle of major axis of ellipse
	 * @param angle
	 */
	public void setAngle(final double angle) {
		alpha = angle;
	}

	/**
	 * Set coordinates of given point
	 * @param Xc
	 * @param Yc
	 */
	public void setCoordinate(final double Xc, final double Yc) {
		final double c = Math.cos(alpha);
		final double s = Math.sin(alpha);
		B = -ra*(Xc*c + Yc*s);
		C = -rb*(Xc*s - Yc*c);
	}

	/**
	 * @return value proportional to the derivative of the distance function wrt. angle
	 */
	@Override
	public double value(final double angle) {
		final double c = Math.cos(angle);
		final double s = Math.sin(angle);

		return (A*c + B)*s + C*c; // -ve and 1/2 value of squared distance derivative
	}
}