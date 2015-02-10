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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Represent an attribute of a node
 */
public interface Attribute {

	/**
	 * @return name of attribute
	 */
	public String getName();

	/**
	 * Get full name of attribute including node and attribute name
	 * @return name
	 */
	public String getFullName();

	/**
	 * @return true if attribute contains strings
	 */
	public boolean isString();

	/**
	 * @return name of attribute type
	 */
	public String getTypeName();

	/**
	 * Set name of attribute type
	 * @param name
	 */
	public void setTypeName(String name);

	/**
	 * @return rank of attribute dataset
	 */
	public int getRank();

	/**
	 * @return shape of attribute dataset
	 */
	public int[] getShape();

	/**
	 * Get number of items in attribute dataset
	 * @return size
	 */
	public int getSize();

	/**
	 * @return first element as string
	 */
	public String getFirstElement();

	/**
	 * Get dataset holding value(s) of attribute
	 * @return dataset
	 */
	public IDataset getValue();

	/**
	 * Set value of attribute from given object
	 * @param obj
	 */
	public void setValue(Object obj);

	/**
	 * Set value of attribute from given object
	 * @param obj
	 * @param isUnsigned
	 *            if true, interpret integer values as unsigned by increasing element bit width
	 */
	public void setValue(Object obj, boolean isUnsigned);

	/**
	 * Get node name
	 * @return name
	 */
	public String getNodeName();

	/**
	 * Get tree
	 * @return tree
	 */
	public Tree getTree();
}
