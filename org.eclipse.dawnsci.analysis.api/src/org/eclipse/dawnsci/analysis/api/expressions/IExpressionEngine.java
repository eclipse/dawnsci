/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.expressions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * This service can be called to save and/or load data from an hdf5 file.
 *
 */
public interface IExpressionEngine {
	
	/**
	 * Method to create an expression in the engine, throws an exception if the expression cannot be parsed
	 *  
	 * @param expression
	 * @throws Exception
	 */
	public void createExpression(String expression) throws Exception;
	
	/**
	 * Evaluates the expression set in the engine, throws exception if cannot evaluate, returns answer as object
	 *  
	 * @return Object
	 * @throws Exception
	 */
	public Object evaluate() throws Exception;
	
	/**
	 * Adds a listener for expression evaluated with event (calculation performed in separate thread)
	 * 
	 * @param listener
	 */
	public void addExpressionEngineListener(IExpressionEngineListener listener);
	
	/**
	 * Removes a listener for expression evaluated with event (calculation performed in separate thread)
	 * 
	 * @param listener
	 */
	public void removeExpressionEngineListener(IExpressionEngineListener listener);
	
	/**
	 * Evaluate the expression and fire an event on completion (for long calculations, or expressions on UI)
	 * Can be cancelled using the monitor
	 * 
	 * @param monitor
	 */
	public void evaluateWithEvent(IMonitor monitor);
	
	/**
	 * Get all functions currently in the expression engine
	 * 
	 * @returns functions
	 */
	public Map<String,Object> getFunctions();
	
	/**
	 * Set all functions currently in the expression engine
	 * 
	 * @param functions
	 */
	public void setFunctions(Map<String,Object> functions);
	
	/**
	 * Set all variables currently in the expression engine
	 * 
	 * @param variables
	 */
	public void setLoadedVariables(Map<String,Object> variables);
	
	/**
	 * get named variable from engine
	 * 
	 * @param name
	 * @returns Object
	 */
	public Object getLoadedVariable(String name);
	
	/**
	 * Adds to variables currently in the expression engine
	 * 
	 * @param variables
	 */
	public void addLoadedVariables(Map<String,Object> variables);
	
	/**
	 * Adds single variable to variables currently in the expression engine
	 * 
	 * @param name
	 * @param value
	 */
	public void addLoadedVariable(String name, Object value);
	
	/**
	 * Gets names of *all* variables from the expression.
	 * <p>
	 * The returned collection attempts to maintain the order of the
	 * variable names as they were first encountered in the expression.
	 *
	 * @returns names
	 */
	public Collection<String> getVariableNamesFromExpression();
	
	/**
	 * Gets names of variables from the expression which can be
	 * provided as lazy datasets. The expression will then do the 
	 * relavent slicing during evaluation.
	 * 
	 * @returns names
	 */
	public Collection<String> getLazyVariableNamesFromExpression();

}
