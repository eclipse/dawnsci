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

package org.eclipse.dawnsci.analysis.api.processing.model;

import java.util.Collection;


/**
 * A model to use with operations. Do not put JSON stuff in here.
 * 
 * This model is designed to be the POJO and should be separate
 * from the serialization methods such as XML or JSON. It should not contain
 * mathematical methods or non-data operations.
 * 
 * OperationModel objects must be POJOs! Do not put unexpected methods
 * not involved with setting and getting the data. Put only model things
 * here or the reflection risks doing the wrong thing or perhaps unintended 
 * dependencies will be created.
 * 
 */
public interface IOperationModel {

	/**
	 * Get a field by name using reflection.
	 * @param name
	 * @return field value
	 */
	public Object get(String name) throws Exception;
	
	/**
	 * Set a field by name using reflection.
	 * @param name
	 * @return fields old value, or null
	 */
	public Object set(String name, Object value) throws Exception;

	/**
	 * 
	 * @param name
	 * @return true if the given field in in the model and does not have an annotation making it invisible.
	 */
	public boolean isModelField(String name) throws Exception;


}
