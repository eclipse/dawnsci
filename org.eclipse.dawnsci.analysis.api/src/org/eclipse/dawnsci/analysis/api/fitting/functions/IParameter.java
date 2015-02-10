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

/**
 * Parameter interface used for manipulation of fitting parameters
 */
public interface IParameter {

	/**
	 * Get the name of the parameter
	 * 
	 * @return The name of the parameter
	 */
	public String getName();

	/**
	 * Get the value of the parameter
	 * 
	 * @return The value of the parameter
	 */
	public double getValue();

	/**
	 * Get the upper limit of the Parameter
	 * 
	 * @return The upper limit
	 */
	public double getUpperLimit();

	/**
	 * Get the lower limit of the parameter
	 * 
	 * @return The lower limit
	 */
	public double getLowerLimit();

	/**
	 * Check whether the parameter is set as fixed or not
	 * 
	 * @return true if fixed
	 */
	public boolean isFixed();

	/**
	 * Set the parameter as fixed or not, the default is false
	 * 
	 * @param fixed
	 *            true for setting the parameter as fixed
	 */
	public void setFixed(boolean fixed);

	/**
	 * Set both limits of the parameter. For when both limits are being
	 * changed at the same time and you wish to avoid the logic testing
	 * when setting the limits separately.
	 * 
	 * @param newLowerLimit
	 * @param newUpperLimit
	 */
	public void setLimits(double newLowerLimit, double newUpperLimit);

	/**
	 * Set the lower limit of the parameter, the default is Double.MIN_VALUE
	 * 
	 * @param lowerLimit
	 *            The new double value which is the lower limit
	 */
	public void setLowerLimit(double lowerLimit);

	/**
	 * Set the upper limit of the parameter, the default is Double.MAX_VALUE
	 * 
	 * @param upperLimit
	 *            The new double value which is the upper limit
	 */
	public void setUpperLimit(double upperLimit);

	
	/**
	 * Set the name of the parameter
	 * 
	 * @param name
	 *            The new String name of the parameter
	 */
	public void setName(String name);

	/**
	 * Set the value of the parameter
	 * 
	 * @param value
	 *            The new double Value of the parameter
	 */
	public void setValue(double value);
}
