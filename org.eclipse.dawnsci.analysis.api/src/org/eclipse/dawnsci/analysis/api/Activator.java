/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext staticContext;

	@Override
	public void start(BundleContext context) throws Exception {
		staticContext = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		staticContext = null;
	}

	public static Object getService(final Class<?> serviceClass) {
		
		if (staticContext==null) return null;
		ServiceReference<?> ref = staticContext.getServiceReference(serviceClass);
		if (ref==null) return null;
		return staticContext.getService(ref);
	}
}
