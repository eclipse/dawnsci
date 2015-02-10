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
 * Scaling types
 */
public enum ScaleType {
	/**
	 * Linear scaling
	 */
	LINEAR, 
	/**
	 *  logarithmic base 2 scaling
	 */
	LOG2, 
	/**
	 * logarithmic base 10 scaling 
	 */
	LOG10,
	/**
	 * logarithmic base e scaling
	 */
	LN
}
