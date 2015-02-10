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
package org.eclipse.dawnsci.slicing.api.system;

import java.util.EventObject;

public class AxisChoiceEvent extends EventObject {

	private int    dimension;
	private String axisName;

	public AxisChoiceEvent(Object source, int dimension, String axisName) {
		super(source);
		this.dimension = dimension;
		this.axisName = axisName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5436650856986750316L;

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((axisName == null) ? 0 : axisName.hashCode());
		result = prime * result + dimension;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AxisChoiceEvent other = (AxisChoiceEvent) obj;
		if (axisName == null) {
			if (other.axisName != null)
				return false;
		} else if (!axisName.equals(other.axisName))
			return false;
		if (dimension != other.dimension)
			return false;
		return true;
	}

}
