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
 * A ClassRegister is used to provide a string to identify classes when marshalling objects.
 * 
 * This is only necessary when dealing with dynamic instantiation in the bean definition,
 * or to allow e.g. a subscriber to determine the received bean class type.
 * 
 * Provide an IClassRegister to the extension point:
 * to enable proper marshalling with that object.
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
	 * Return true if the register includes the identification string given,
	 * false otherwise.
	 * 
	 * @param id
	 *            the identification string being searched for.
	 * @return true if register contains id, false otherwise.
	 * 
	 */
	
	public Boolean hasId(String id);
	
	/**
	 * Return true if the register includes the class given, false otherwise.
	 * 
	 * @param clazz
	 *            the Class being searched for.
	 * @return true if register contains clazz, false otherwise.
	 * 
	 */
	public Boolean hasClass(Class<?> clazz);
	
}
