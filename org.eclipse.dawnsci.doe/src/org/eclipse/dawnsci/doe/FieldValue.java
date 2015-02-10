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
package org.eclipse.dawnsci.doe;

import java.io.Serializable;

public class FieldValue implements Serializable {

	private String name,value;
    private Object originalObject;
	public FieldValue() {
		
	}
	
	public FieldValue(final Object originalObject, String name, String value) {
		super();
		setOriginalObject(originalObject);
		setName(name);
		setValue(value);
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * NOTE: Intentionally not using value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((originalObject == null) ? 0 : originalObject.hashCode());
		return result;
	}

	/**
	 * NOTE: Intentionally not using value
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldValue other = (FieldValue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (originalObject == null) {
			if (other.originalObject != null)
				return false;
		} else if (!originalObject.equals(other.originalObject))
			return false;
		return true;
	}

	/**
	 * @return Returns the originalObject.
	 */
	public Object getOriginalObject() {
		return originalObject;
	}

	/**
	 * @param originalObject The originalObject to set.
	 */
	public void setOriginalObject(Object originalObject) {
		this.originalObject = originalObject;
	}
}
