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

/**
 * Returns objects implementing IExpressionObject which can be used
 * as items in the workbench for evaluating expressions in tables. Usually
 * the IExpressionObject is added to the object used for the table data
 * as member data.
 */
public interface IExpressionObjectService {
	
	/**
	 * Validate the expression name and return a cleaned up one if you can.
	 * @param manager
	 * @param variableName
	 * @return 
	 * @throws Exception if name not ok with message about why it is not ok
	 */
	public String validate(IVariableManager manager, String variableName) throws Exception;

	/**
	 * Create an IExpressionObject
	 * @param manager
	 * @param expressionName, may be null.
	 * @param expression,     may be null.
	 * @return
	 */
	public IExpressionObject createExpressionObject(IVariableManager manager, String expressionName, String expression);

	/**
	 * Creates a safe variable name from the suggested name, may return the 
	 * same name back again, if it is legal or may return empty string if all characters
	 * are illegal for variable names. Replaces space with _
	 * @param name
	 * @return
	 */
	public String getSafeName(String name);
	
	/**
	 * Returns the active expressions for a given file path. 
	 * @param sourcePath
	 * @return
	 * @throws Exception
	 */
	public List<IExpressionObject> getActiveExpressions(String sourcePath) throws Exception;

} 
