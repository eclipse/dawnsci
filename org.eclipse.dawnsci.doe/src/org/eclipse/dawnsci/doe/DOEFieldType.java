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

public enum DOEFieldType {
	
	SINGLE_VALUE,
	LIST,
	RANGE;

	/**
	 * Decodes a value to determine the DOEFieldType, if it cannot be decoded,
	 * SINGLE_VALUE is returned.
	 * 
	 * @param value
	 * @return DOEFieldType
	 */
	public static DOEFieldType getRangeType(String value) {
		
		if (value.indexOf(";")>-1 && value.split(";").length==3) {
			return RANGE;
		} else if (value.indexOf(",")>-1) {
			return LIST;
		}
		return SINGLE_VALUE;
	}
}
