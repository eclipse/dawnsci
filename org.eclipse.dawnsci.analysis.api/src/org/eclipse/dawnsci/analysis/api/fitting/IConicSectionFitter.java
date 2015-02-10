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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;


/**
 * Conic section fitter interface
 */
public interface IConicSectionFitter {

	/**
	 * @return array of parameters
	 */
	public double[] getParameters();

	/**
	 * @return root mean square of residuals
	 */
	public double getRMS();

	/**
	 * Fit points given by x, y datasets to a conic section. If no initial parameters are
	 * given, then an algebraic fit is performed then a non-linear least squares fitting routine
	 * is used to provide the best geometric fit.
	 * @param x
	 * @param y
	 * @param init parameters (can be null)
	 */
	public void geometricFit(IDataset x, IDataset y, double[] init);

	/**
	 * Fit points given by x, y datasets to a conic section using an algebraic fit.
	 * @param x
	 * @param y
	 */
	public void algebraicFit(IDataset x, IDataset y);

	/**
	 * Create a function that is used by optimizer with optional
	 * x, y datasets. The datasets can be null if the fitter has already been used.
	 * @param x can be null
	 * @param y can be null
	 * @return function
	 */
	public IConicSectionFitFunction getFitFunction(IDataset x, IDataset y);
}
