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

package org.eclipse.dawnsci.analysis.api.dataset;

import java.io.Serializable;

/**
 * This interface will be used with the plotting system to mark
 * a given set of data to plot as having error. It needs to be adapted
 * to support non-symmetric error bounds. 
 * 
 * The plotting system will attempt to plot any data which is also IErrorDataset
 * including error bars unless setErrorBarEnabled(false) is explicitly called. This
 * is a detail of the plotting system not the analysis algorithms.
 */
public interface IErrorDataset extends IDataset {

	/**
	 * If error information is set, returns true.
	 * Faster to call than getError() which constructs a
	 * new dataset.
	 * 
	 * @return true if there is error data.
	 */
	public boolean hasErrors();

	/**
	 * 
	 * @return the error dataset, constructing one if necessary
	 */
	@Override
	public IDataset getError();

	/**
	 * Get the error for a given position.
	 * @param pos
	 * @return error value (symmetric)
	 */
	public double getError(int... pos);

	/**
	 * Get the error values for a single point in the dataset
	 * @param pos of the point to be referenced 
	 * @return the values of the error at this point (can be null when no error defined)
	 */
	public double[] getErrorArray(int... pos);

	/**
	 * Set the error, may be a single double, a double array or a whole dataset
	 * that can broadcast to the dataset
	 * @param error (null clears any errors)
	 */
	@Override
	public void setError(Serializable error);
}
