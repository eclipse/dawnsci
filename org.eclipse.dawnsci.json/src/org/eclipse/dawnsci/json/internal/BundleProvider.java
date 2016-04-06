/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Colin Palmer - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.json.internal;

import org.osgi.framework.Bundle;

/**
 * A simple interface to allow OSGi framework API calls to be replaced with mock implementations for testing.
 *
 * @see OSGiBundleProvider
 *
 * @author Colin Palmer
 *
 */
public interface BundleProvider {

	/**
	 * Get the bundle which loaded the specified class, or <code>null</code> if the class was not loaded by a bundle classloader.
	 *
	 * @param cls class to find the bundle for
	 * @return the bundle which loaded the specified class, or <code>null</code>
	 */
	Bundle getBundle(Class<?> cls);

	/**
	 * Get an array of all installed bundles. The array might be empty but should never be <code>null</code>, even if called
	 * outside an OSGi environment.
	 *
	 * @return an array of all installed bundles
	 */
	Bundle[] getBundles();
}
