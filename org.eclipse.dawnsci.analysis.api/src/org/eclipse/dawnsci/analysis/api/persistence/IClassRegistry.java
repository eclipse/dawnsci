/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.persistence;

import java.util.Map;

/**
 * A ClassRegistry is used to provide a class id for use when marshalling objects.
 * 
 * This is typically necessary when dealing with dynamically defined types in the bean
 * definition, or to allow e.g. a subscriber to determine the received bean class type.
 * 
 * Provide an IClassRegistry to the extension point:
 * org.eclipse.dawnsci.analysis.classregistry
 * to enable proper marshalling for the given objects.
 * 
 * @author Martin Gaughran
 */
public interface IClassRegistry {

	/**
	 * Return the Map<String, Class<?>> that stores the id string
	 * to class information.
	 * 
	 * @return the Map that links id strings to classes.
	 */
	public Map<String, Class<?>> getIdToClassMap();
	
}
