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
import org.osgi.framework.FrameworkUtil;

/**
 * The default implementation of BundleProvider for use in an OSGi environment.
 * <p>
 * It should also be safe to use this implementation without OSGi running. The methods will return <code>null</code> or
 * empty objects but should not throw exceptions.
 *
 * @author Colin Palmer
 *
 */
public class OSGiBundleProvider implements BundleProvider {

	private static final Bundle[] NO_BUNDLES = new Bundle[0];

	@Override
	public Bundle getBundle(Class<?> clazz) {
		return FrameworkUtil.getBundle(clazz);
	}

	@Override
	public Bundle[] getBundles() {
		if (Activator.getContext() != null) {
			return Activator.getContext().getBundles();
		}
		return NO_BUNDLES;
	}
}
