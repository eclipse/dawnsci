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
package org.eclipse.dawnsci.analysis.examples;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	public static final String PLUGIN_ID = "org.eclipse.dawnsci.analysis.examples";

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}	
	
	/**
	 * @param bundleName
	 * @return file this can return null if bundle is not found
	 * @throws IOException
	 */
	public static File getBundleLocation(final String bundleName) throws IOException {
		final Bundle bundle = find(bundleName);
		if (bundle == null) {
			return null;
		}
		
		String loc = bundle.getLocation();
		if (loc==null) return null;
		
		if (loc.startsWith("reference:file:"))    loc = loc.substring("reference:file:".length());
		if (isWindowsOS() && loc.startsWith("/")) loc = loc.substring(1);
		
		return new File(loc);
	}
	
	static public boolean isWindowsOS() {
		return (System.getProperty("os.name").indexOf("Windows") == 0);
	}

	
	/**
	 * Find the first bundle with a given name.
	 * @param bundleName
	 * @return
	 */
	private static Bundle find(String bundleName) {
		for (Bundle bundle : context.getBundles()) {
			if (bundle.getSymbolicName().equals(bundleName)) return bundle;
 		}
		return null;
	}

	/**
	 * Get the bundle path using eclipse.home.location not loading the bundle.
	 * @param bundleName
	 * @return
	 */
	public static File getBundlePathNoLoading(String bundleName) {
		return new File(new File(getEclipseHome(), "plugins"), bundleName);
	}
	
	/**
	 * Gets eclipse home in debug and in deployed application mode.
	 * @return
	 */
	public static String getEclipseHome() {
		File hDirectory;
		try {
			URI u = new URI(System.getProperty("eclipse.home.location"));
			hDirectory = new File(u);
		} catch (URISyntaxException e) {
			return null;
		}

		String path = hDirectory.getName();
		if (path.equals("plugins") || path.equals("bundles")) {
			path = hDirectory.getParentFile().getParentFile().getAbsolutePath();
		} else{
			path = hDirectory.getAbsolutePath();
		}
        return path;
	}

}

