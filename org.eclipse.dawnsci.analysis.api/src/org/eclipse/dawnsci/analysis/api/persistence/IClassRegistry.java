/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.persistence;

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
	 * Return the Class that corresponds to the given object.
	 * 
	 * @param id
	 *            the identification string to be decoded.
	 * @return the Class corresponding to the id.
	 */
	public Class<?> getClassFromId(String id);
	
	/**
	 * Return the identification string corresponding to the given class.
	 * 
	 * @param clazz
	 *            the Class to be encoded.
	 * @return the identification string corresponding to clazz.
	 */
	public String getIdFromClass(Class<?> clazz);

	/**
	 * Return true if the registry includes the identification string given,
	 * false otherwise.
	 * 
	 * @param id
	 *            the identification string being searched for.
	 * @return true if registry contains id, false otherwise.
	 * 
	 */
	
	public boolean isId(String id);
	
	/**
	 * Return true if the registry includes the class given, false otherwise.
	 * 
	 * @param clazz
	 *            the Class being searched for.
	 * @return true if registry contains clazz, false otherwise.
	 * 
	 */
	public boolean isClass(Class<?> clazz);
	
}
