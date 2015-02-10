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

package org.eclipse.dawnsci.analysis.api.roi;

/**
 * A ROI whose boundary can be parameterised
 */
public interface IParametricROI extends IOrientableROI {

	@Override
	public IParametricROI copy();

	/**
	 * Get point on boundary of ROI for given parameter
	 *
	 * @param parameter
	 * @return point coordinates
	 */
	public double[] getPoint(double parameter);

	/**
	 * Calculate parameter values at which ROI may intersect horizontal line of given y
	 *
	 * @param y
	 * @return parameters (can be null for non-intersection, or contain one [NaN in degenerate case] or more values)
	 */
	public double[] getHorizontalIntersectionParameters(double y);

	/**
	 * Calculate parameter values at which ROI may intersect vertical line of given x
	 *
	 * @param x
	 * @return parameters (can be null for non-intersection or degenerate case,
	 * or contain one or more values)
	 */
	public double[] getVerticalIntersectionParameters(double x);

	/**
	 * @param d
	 * @return start parameter at distance from centre or focus
	 */
	public double getStartParameter(double d);

	/**
	 * @param d
	 * @return end parameter at distance from centre or focus
	 */
	public double getEndParameter(double d);
}
