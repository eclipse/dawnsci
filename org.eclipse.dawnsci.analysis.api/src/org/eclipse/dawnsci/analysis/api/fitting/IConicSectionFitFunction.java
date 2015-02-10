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

package org.eclipse.dawnsci.analysis.api.fitting;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optim.nonlinear.vector.Target;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;


/**
 * Conic section fit function which returns the coordinates (interleaved) for the points
 * specified by the geometric parameters and an array of angles
 */
public interface IConicSectionFitFunction extends MultivariateVectorFunction {

	/**
	 * Set points used in fit
	 * @param x
	 * @param y
	 */
	public void setPoints(IDataset x, IDataset y);

	/**
	 * @return array of interleaved coordinates
	 */
	public Target getTarget();

	/**
	 * @return default weights of 1
	 */
	public Weight getWeight();

	/**
	 * Calculate distance squared to nearest point of conic section
	 * @param parameters
	 * @return squared distances
	 * @throws IllegalArgumentException
	 */
	public IDataset calcDistanceSquared(double[] parameters) throws IllegalArgumentException;

	/**
	 * @return a matrix function that calculates the Jacobian
	 */
	public MultivariateMatrixFunction jacobian();
}
