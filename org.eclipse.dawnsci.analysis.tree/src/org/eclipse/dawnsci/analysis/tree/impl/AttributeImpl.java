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

package org.eclipse.dawnsci.analysis.tree.impl;

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;

public class AttributeImpl implements Attribute, Serializable {
	protected static final long serialVersionUID = -5046142834233727039L;

	private String name;
	private String type;
	private Dataset value;

	/**
	 * Create an attribute with node, name
	 * @param attrName
	 */
	public AttributeImpl(final String attrName) {
		name = attrName;
	}
	
	/**
	 * Create an attribute with node, name. The value is treated as a signed for applicable value types.
	 * @param attrName attribute name
	 * @param attrValue attribute value
	 */
	public AttributeImpl(final String attrName, final Object attrValue) {
		name = attrName;
		setValue(attrValue, false);
	}

	/**
	 * Create an attribute with node, name, value and a flag indicating whether value is unsigned
	 * @param attrName
	 * @param attrValue (usually, this is a Java array)
	 * @param isUnsigned <code>true</code> if items are unsigned but held in signed primitives,
	 *   <code>false</code> otherwise
	 */
	public AttributeImpl(final String attrName, final Object attrValue, final boolean isUnsigned) {
		name = attrName;
		setValue(attrValue, isUnsigned);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isString() {
		return value != null && (value instanceof StringDataset);
	}

	@Override
	public String getTypeName() {
		return type;
	}

	@Override
	public void setTypeName(String name) {
		type = name;
	}

	@Override
	public int getRank() {
		return value.getRank();
	}

	@Override
	public int[] getShape() {
		return value.getShape();
	}

	@Override
	public int getSize() {
		return value.getSize();
	}

	@Override
	public String getFirstElement() {
		return value.getString(0);
	}

	@Override
	public String toString() {
		return value.toString(true);
	}

	@Override
	public Dataset getValue() {
		return value;
	}

	@Override
	public void setValue(Object obj) {
		setValue(obj, false);
	}

	@Override
	public void setValue(Object obj, boolean isUnsigned) {
		value = DatasetFactory.createFromObject(obj, isUnsigned);
		if (value.getRank() == 0) {
			value.setShape(1);
		}
	}
}
