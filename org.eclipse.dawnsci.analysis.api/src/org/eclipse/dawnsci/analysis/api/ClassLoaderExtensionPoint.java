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

package org.eclipse.dawnsci.analysis.api;

/**
 * This only holds constants for the extension point
 */
public class ClassLoaderExtensionPoint {

	/**
	 * ID of extension point for class loading
	 */
	public static final String ID = "org.eclipse.dawnsci.analysis.classloader";

	/**
	 * attribute name in extension point
	 */
	public static final String ATTR = "class";

}
