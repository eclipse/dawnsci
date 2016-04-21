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
package org.eclipse.dawnsci.json.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.dawnsci.json.internal.BundleProvider;
import org.osgi.framework.Bundle;

/**
 * A BundleProvider implementation for use in unit tests.
 * <p>
 * Class objects can be registered along with a Bundle (which could well be a mock). When asked for the bundle which
 * loaded a class, the registered bundle is returned. The list of all registered bundles can also be returned.
 *
 * @author Colin Palmer
 *
 */
public class TestBundleProvider implements BundleProvider {

	private static final Bundle[] EMPTY_BUNDLE_ARRAY = new Bundle[0];

	private Map<Class<?>, Bundle> classBundleMap = new HashMap<>();
	private Set<Bundle> bundles = new HashSet<>();
	private boolean getBundlesWasCalled = false;

	public void registerBundleForClass(Class<?> clazz, Bundle bundle) {
		classBundleMap.put(clazz, bundle);
		addBundle(bundle);
	}

	public void addBundle(Bundle bundle) {
		bundles.add(bundle);
	}

	@Override
	public Bundle getBundle(Class<?> clazz) {
		return classBundleMap.get(clazz);
	}

	@Override
	public Bundle[] getBundles() {
		getBundlesWasCalled = true;
		return bundles.toArray(EMPTY_BUNDLE_ARRAY);
	}

	boolean wasGetBundlesCalled() {
		boolean result = getBundlesWasCalled;
		getBundlesWasCalled = false;
		return result;
	}
}
