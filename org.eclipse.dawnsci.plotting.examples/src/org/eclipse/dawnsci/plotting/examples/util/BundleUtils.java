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
package org.eclipse.dawnsci.plotting.examples.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 *   BundleUtils
 *   
 *   Assumes that this class can be used before the Logger is loaded, therefore do not put Logging in here.
 *
 *   @author gerring
 *   @date Aug 2, 2010
 *   @project org.dawb.common.util
 **/
public class BundleUtils {
	
	/**
	 * @param bundleName
	 * @return file this can return null if bundle is not found
	 * @throws IOException
	 */
	public static File getBundleLocation(final String bundleName) throws IOException {
		final Bundle bundle = Platform.getBundle(bundleName);
		if (bundle == null) {
			return null;
		}
		return FileLocator.getBundleFile(bundle);
	}
	
	/**
	 * Get the java.io.File location of a bundle.
	 * @param bundleName
	 * @return
	 * @throws Exception 
	 */
	public static File getBundleLocation(final Bundle bundle) throws IOException {
		return FileLocator.getBundleFile(bundle);
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
	
	private static Pattern FEATURE_MATCH = Pattern.compile("org.dawnsci.base.product.feature_(.+)");
	/**
	 * Looks at installed features, gets newest uk.ac.diamond.dawn.product.feature
	 * and returns that version.
	 * 
	 * @return null if cannot find a dawn feature (might happen in debug mode)
	 */
	public static String getDawnVersion() {
		
		final File   dir = new File(getEclipseHome(), "features");
		if (!dir.exists()) return null;
		final File[] fa  = dir.listFiles();
		
		long date = -1;
		String version = null;
		for (File sd : fa) {
			if (!sd.isDirectory()) continue;
			Matcher matcher = FEATURE_MATCH.matcher(sd.getName());
			if (matcher.matches()) {
				if (date<sd.lastModified()) {
					date    = sd.lastModified();
					version = matcher.group(1); 
				}
			}
		}
		return version;
	}
}
