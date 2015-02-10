/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.plotting.api.expressions;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;


/**
 * An interface used to provide a connection with the other expressions
 * For instance is a given string is a defined variable name in the current 
 * context.
 * 
 * TODO is this similar to a JEXL context? - maybe it is not needed
 */
public interface IVariableManager {
	
	public abstract class Stub implements IVariableManager {

		@Override
		public void deleteExpression() {
			// TODO Auto-generated method stub

		}

		@Override
		public void addExpression() {
			// TODO Auto-generated method stub

		}

		@Override
		public void saveExpressions() {
			// TODO Auto-generated method stub

		}

		@Override
		public void clearExpressionCache(String... variableNames) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * 
	 * @return names of all known data names
	 */
	public List<String> getDataNames();


	/**
	 * 
	 * @return names of all known variables
	 */
	public List<String> getVariableNames();
	
	/**
	 * Test if variable name can be resolved.
	 * @param name
	 * @param monitor
	 * @return
	 */
	public boolean isVariableName(String variableName, IMonitor monitor);
	
	/**
	 * Test if variable name can be resolved.
	 * @param name
	 * @param monitor
	 * @return
	 */
	public boolean isDataName(String variableName, IMonitor monitor);

	/**
	 * The  has been
	 * parsed to be a legal expression variable.
	 * 
	 * @param name
	 * @param monitor
	 * @return
	 */
	public IDataset getVariableValue(String variableName, final IMonitor monitor);

	/**
	 * Tries to get the lazy dataset for the name
	 * @param name
	 * @param monitor
	 * @return
	 */
	public ILazyDataset getLazyValue(String variableName, final IMonitor monitor);

	/**
	 * Tries to get the lazy dataset for the name
	 * @param dataName
	 * @param monitor
	 * @return
	 */
	public ILazyDataset getDataValue(String dataName, final IMonitor monitor);

	/**
	 * Delete selected expression, if any
	 */
	public void deleteExpression();


	/**
	 * Create a plottable dataset from an expression.
	 * Normally is implemented to add an item to a table and make it editable to recieve the expression.
	 */
	public void addExpression();

	
	/**
	 * Saves the current expressions.
	 */
	public void saveExpressions();
	
	/**
	 * Call to remove the values of any cached expressions.
	 */
	public void clearExpressionCache(String... variableNames);

}
