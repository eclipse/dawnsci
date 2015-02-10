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

/**
 * If the slice range is an expanded value from a variable
 * like ${x} where x equals the string "all" or "700:710" or
 * 10;100;1 for instance, the substituer replaces the value.
 * 
 * @author Matthew Gerring
 *
 */
public interface ISliceRangeSubstituter {

	/**
	 * If the slice range is an expanded value from a variable
	 * like ${x} where x equals the string "all" or "700:710" or
	 * 10;100;1 for instance, the substituter replaces the value.
	 */
	String substitute(String value);
}
