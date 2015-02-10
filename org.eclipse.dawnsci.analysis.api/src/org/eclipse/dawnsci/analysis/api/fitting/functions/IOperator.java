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
 * A function that is an operation on other functions
 */
public interface IOperator extends IFunction {

	/**
	 * Test if operator is extendible
	 * @return true, if it can be extended
	 */
	public boolean isExtendible();

	/**
	 * Get the number of functions in the (composite) function.
	 * 
	 * @return number of functions
	 */
	public int getNoOfFunctions();

	/**
	 * @return number of required functions, can return -1 for unlimited number
	 */
	public int getRequiredFunctions();

	/**
	 * Get function at given index
	 * 
	 * @param index
	 * @return function
	 */
	public IFunction getFunction(int index);

	/**
	 * Get all function
	 * 
	 * @return functions
	 */
	public IFunction[] getFunctions();

	/**
	 * Add function to operator
	 * @param function
	 */
	public void addFunction(IFunction function);

	/**
	 * Removes a function at given index from operator
	 * @param index
	 */
	public void removeFunction(int index);

	/**
	 * Set function at given index
	 * @param index
	 * @param function
	 */
	public void setFunction(int index, IFunction function);

	/**
	 * Update parameters
	 */
	public void updateParameters();
}
