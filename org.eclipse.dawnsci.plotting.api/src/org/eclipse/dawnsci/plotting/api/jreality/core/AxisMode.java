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

package org.eclipse.dawnsci.plotting.api.jreality.core;

/**
 * Defines the different axis mode
 */
public enum AxisMode {
	/**
	 * Linear mode so a 1:1 mapping between data point and its x axis
	 */
	LINEAR(1), 
	/**
	 *  Just like linear mode but with an initial offset
	 */
	LINEAR_WITH_OFFSET(2), 
	
	/**
	 * Completely custom each data point mode might have a specific x value
	 */
	CUSTOM(3);

	private int code;
	
	private AxisMode(int code) {
		this.code = code;
	}
	
	/**
	 * Used for saving enum to file.
	 * @return int code
	 */
	public int asInt() {
		return code;
	}
	
	/**
	 * Used for saving enum to file
	 * @param code
	 * @return AxisMode
	 */
	public static AxisMode asEnum(int code) {
		switch (code) {
			case 1: return LINEAR;
			case 2: return LINEAR_WITH_OFFSET;
			case 3: return CUSTOM;
		}
		return null;
	}
}
