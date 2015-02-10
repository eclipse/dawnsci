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

package org.eclipse.dawnsci.analysis.api.tree;

import java.util.Iterator;

/**
 * Fundamental unit of tree which can possess attributes
 */
public interface Node {
	/**
	 * String used as separator between a group and its children
	 */
	public static final String SEPARATOR = "/";

	/**
	 * String used as separator between a node and its attributes
	 */
	public static final String ATTRIBUTE = "@";

	/**
	 * @return ID
	 */
	public long getID();

	/**
	 * @return number of attributes on node
	 */
	public int getNumberOfAttributes();

	/**
	 * @param name
	 * @return true if node contains attribute of given name
	 */
	public boolean containsAttribute(String name);

	/**
	 * Get attribute of given name from node
	 * @param name
	 * @return attribute (can be null if there is no attribute of given name)
	 */
	public Attribute getAttribute(String name);

	/**
	 * Add given attribute to node
	 * @param a
	 */
	public void addAttribute(Attribute a);

	/**
	 * @return iterator over attribute names in node
	 */
	public Iterator<String> getAttributeNameIterator();

	/**
	 * @return iterator over attribute names in node
	 */
	public Iterator<? extends Attribute> getAttributeIterator();
}
