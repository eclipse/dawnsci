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
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * An object which can resolve data maths and can be used
 * as data in Viewer models in jface.
 */
public interface IExpressionObject {

	/**
	 * The name of the expression, e.g. "Q"
	 * @return
	 */
	public String getExpressionName();
	
	/**
	 * Method to set the expression string. For instance when the user
	 * types in a new string.
	 * 
	 * @param expression
	 */
	public void setExpressionName(String name);


	/**
	 * The string expression, e.g. "x*y"
	 * @return
	 */
	public String getExpressionString();
	
	/**
	 * Method to set the expression string. For instance when the user
	 * types in a new string.
	 * 
	 * @param expression
	 */
	public void setExpressionString(String expression);

	/**
	 * If the expression value is cached, the
	 * clear method will nullify this cache.
	 */
	public void clear();

	/**
	 * Evaluates and caches the expression if necessary.
	 * @param suggestedName may be null
	 * @param monitor
	 * @return the evaluated value of the expression.
	 * @throws Exception
	 */
	public IDataset getDataSet(String suggestedName, IMonitor monitor) throws Exception;

	/**
	 * Guesses the data without evaluating the expression, instead looks for
	 * a reference to a concrete data set and uses the attributes (shape etc)
	 * of this.
	 * 
	 * *WARNING* Is educated guess at lazy dataset, will not always work.
	 * 
	 * @param suggestedName should not be null, should be the data name or the variable name for expressions.
	 * @param monitor
	 * @return null if the guess cannot be made or there was any kind of error.
	 */
	public ILazyDataset getLazyDataSet(String suggestedName, IMonitor monitor);

	/**
	 * If the expression has been evaluated once, this will give the cached result. Otherwise
	 * it will return null.
	 * 
	 * @return
	 */
	public ILazyDataset getCachedLazyDataSet();

	/**
	 * 
	 * @param monitor
	 * @return true if expression contained in the object has legal syntax.
	 */
	public boolean isValid(IMonitor monitor);
	
	/**
	 * A string which maye be null to provide feedback as to the problem with the expression.
	 * @return
	 */
	public String getInvalidReason();

	/**
	 * Used to give a nicer error message when an expression is bad.
	 * @param monitor
	 * @return
	 */
	public List<String> getInvalidVariables(IMonitor monitor);
	
	/**
	 * Get all functions currently in the expression engine
	 * 
	 * @returns functions
	 */
	public Map<String,Object> getFunctions();

	/**
	 * 
	 * @param variableNames
	 * @return true if one or more of variableNames is referenced by this expression
	 */
	public boolean containsVariable(String... variableNames);

	/**
	 * The manager of all available expressions.
	 * @return
	 */
	public IVariableManager getVariableManager();

}
