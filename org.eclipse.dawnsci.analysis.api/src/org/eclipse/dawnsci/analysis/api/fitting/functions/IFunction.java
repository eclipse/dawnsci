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

package org.eclipse.dawnsci.analysis.api.fitting.functions;

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;


/**
 * Function interface used for fitting
 */
public interface IFunction extends Serializable {

	public static final long serialVersionUID = -6729243965994162061L;

	/**
	 * Get the name of the function
	 * 
	 * @return The function's name
	 */
	public String getName();

	/**
	 * Set the name parameter
	 * 
	 * @param newName
	 *            The function's new name
	 */
	public void setName(String newName);

	/**
	 * Get the description of the function
	 * 
	 * @return The description of the function
	 */
	public String getDescription();

	/**
	 * Set the description of the function
	 * 
	 * @param newDescription
	 *            The function's new description
	 */
	public void setDescription(String newDescription);

	/**
	 * Get the parameter which is at the index specified
	 * 
	 * @param index
	 *            The position in the array to be obtained
	 * @return The parameter
	 */
	public IParameter getParameter(int index);

	/**
	 * Get all the parameters in the array
	 * 
	 * @return all parameters
	 */
	public IParameter[] getParameters();

	/**
	 * Get the length of the parameter array
	 * 
	 * @return the length as an integer
	 */
	public int getNoOfParameters();

	/**
	 * Get the parameter at a particular index in the function
	 * 
	 * @param index
	 *            The index to retrieve
	 * @return A pointer to the parameter
	 */
	public double getParameterValue(int index);

	/**
	 * Get a double array of all the parameters values
	 * 
	 * @return The double array of all the parameter values
	 */
	public double[] getParameterValues();

	/**
	 * Set a parameter at given index
	 *
	 * @param index
	 * @param parameter
	 */
	public void setParameter(int index, IParameter parameter);

	/**
	 * Set all of the parameter values for the object.
	 * 
	 * @param params
	 *            an array of doubles which needs to be equal in length to the number of parameters in the array.
	 */
	public void setParameterValues(double... params);

	/**
	 * Evaluate the function at particular values
	 * 
	 * @param values
	 *            The function arguments
	 * @return A double which is the result of the evaluation.
	 */
	public double val(double... values);

	/**
	 * Method which calculates the partial derivative
	 * 
	 * @param parameter
	 * @param values
	 * @return the derivative at the point specified with respect to the specified parameter.
	 */
	public double partialDeriv(IParameter parameter, double... values);

	/**
	 * Calculate values from the function
	 * <p>
	 * The function can be evaluated in one of three possible modes. In general, the function has
	 * <tt>m</tt> independent coordinate datasets and the output dataset has <tt>n</tt> dimensions.
	 * <p>
	 * The simplest mode (<tt>m</tt>=1) requires a single dataset which can be compound. The output
	 * shape matches the input shape.
	 * <p>
	 * The next mode requires <tt>m</tt> nD datasets possessing the same shape and where each dataset
	 * specifies one of <tt>m</tt> coordinates. The output shape matches the input shape. An exception
	 * is made for matching 1D shapes where a hypergrid is used. 
	 * <p>
	 * Most general mode has <tt>n = m</tt> and the coordinates evaluated on an nD hypergrid of
	 * flattened input datasets. Its output shape is determined by sizes of the input datasets.
	 * 
	 * @param coords
	 *            The coordinates at which to evaluate the function
	 * @return dataset of the whole function
	 */
	public IDataset calculateValues(IDataset... coords);

	/**
	 * Calculate partial derivative values from the function with respect to the given parameter
	 * 
	 * @param parameter
	 * @param coords see {@link #calculateValues(IDataset...)}
	 * @return the dataset of the partial derivative
	 */
	public IDataset calculatePartialDerivativeValues(IParameter parameter, IDataset... coords);

	/**
	 * Method to evaluate the sum of the deviations of the dataset from the function
	 * when that function is evaluated at the given values and parameters. The allValues flag
	 * dictates whether to use all the values or just a sampled subset. The weight values, if given,
	 * are used to multiply each squared-difference value
	 * 
	 * @param allValues Boolean specifying whether to use sampling or not, currently not implemented so use true
	 * @param data A dataset containing the values for the data to be evaluated
	 * @param weight A dataset containing values to use for weighting, can be null
	 * @param coords a dataset containing the coordinates of the data points
	 * @return residual
	 */
	public double residual(boolean allValues, IDataset data, IDataset weight, IDataset... coords);

	/**
	 * Set internal caching state as needing to be reset if true
	 * @param isDirty
	 */
	public void setDirty(boolean isDirty);

	/**
	 * Set a monitor to check progress
	 * @param monitor
	 */
	public void setMonitor(IMonitor monitor);

	/**
	 * Get monitor
	 * @return monitor, maybe be null
	 */
	public IMonitor getMonitor();

	/**
	 * @return clone
	 * @throws Exception
	 */
	public IFunction copy() throws Exception;

	/**
	 * Check function tree is valid. For example, if an operator has a required
	 * number of children this checks they are all set.
	 *
	 * @return validity of the Function
	 */
	boolean isValid();

	/**
	 * @return parent operator
	 */
	public IOperator getParentOperator();

	/**
	 * Set operator that is its parent
	 * @param parent
	 */
	public void setParentOperator(IOperator parent);
}
