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
 * A ROI whose boundary is parametrised from a fit of a polyline
 */
public interface IFitROI extends IParametricROI {

	/**
	 * @return a polyline
	 */
	public IPolylineROI getPoints();

	/**
	 * Set points which are then used to fit ROI
	 * @param points
	 */
	public void setPoints(IPolylineROI points);

	/**
	 * @return root mean squared of residuals
	 */
	public double getRMS();

	@Override
	public IFitROI copy();
}
