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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;

/**
 * {@link TypeIdResolver} implementation which converts between JSON strings and the information necessary to load a class in OSGi, that is,
 * the fully-qualified class name, bundle symbolic name and bundle version.
 * <p>
 * Generic types are currently not handled. It might be possible to do this (perhaps by delegating to ClassNameIdResolver) but all calls made
 * to ClassUtils would need overriding to use correct bundle classloaders.
 * <p>
 * Also, non-static inner types will probably fail with the current implementation, but this has not been tested.
 *
 * @author Colin Palmer
 *
 */
public class BundleAndClassNameIdResolver extends TypeIdResolverBase {

	/**
	 * Interface to allow Jackson ClassUtil to be mocked for testing
	 */
	public interface ClassFinder {
		public Class<?> findClass(String className) throws ClassNotFoundException;
	}

	private static final Logger logger = LoggerFactory.getLogger(BundleAndClassNameIdResolver.class);
	private static final Map<BundleAndClassInfo, Bundle> cachedBundles = new ConcurrentHashMap<BundleAndClassInfo, Bundle>();

	/**
	 * Clear the bundle cache. Intended only for use in testing.
	 */
	public static void clearCache() {
		cachedBundles.clear();
	}

	private final BundleProvider bundleProvider;
	private final ClassNameIdResolver classNameIdResolver;
	private final ClassFinder classFinder;

	public BundleAndClassNameIdResolver(JavaType baseType, TypeFactory typeFactory, BundleProvider bundleProvider) {
		// Create a default ClassFinder to use Jackson ClassUtil
		this(baseType, typeFactory, bundleProvider, className -> ClassUtil.findClass(className));
	}

	/**
	 * Constructor only for use in testing to allow a mock ClassFinder to be passed in.
	 */
	public BundleAndClassNameIdResolver(JavaType baseType, TypeFactory typeFactory, BundleProvider bundleProvider, ClassFinder classFinder) {
		super(baseType, typeFactory);
		this.bundleProvider = bundleProvider;
		this.classFinder = classFinder;

		// Create a ClassNameIdResolver to delegate to when handling class names (i.e. after we have handled the bundle information)
		this.classNameIdResolver = new ClassNameIdResolver(baseType, typeFactory);
	}

	@Override
	public Id getMechanism() { return Id.CUSTOM; }

	@Override
	public String idFromValue(Object value) {
		return idFromValueAndType(value, value.getClass());
	}

	@Override
	public String idFromValueAndType(Object value, Class<?> clazz) {
		Bundle bundle = bundleProvider.getBundle(clazz);
		String className = classNameIdResolver.idFromValueAndType(value, clazz);
		BundleAndClassInfo info = BundleAndClassInfo.from(bundle, className);
		return info.toString();
	}

	@Override
	public JavaType typeFromId(String id) {
		try {
			Class<?> clazz = getClass(id);
			// This probably doesn't handle generics, except for arrays and collections
			// see ClassNameIdResolver#typeFromId() for more on this
			JavaType type = _typeFactory.constructSpecializedType(_baseType, clazz);
			return type;
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Class " + id + " not found", e);
		}
	}

	private Class<?> getClass(String id) throws ClassNotFoundException {
		BundleAndClassInfo info = BundleAndClassInfo.from(id);

		// If there is no bundle name, try loading the class using the standard Jackson utility method
		// See ClassNameIdResolver for complexity regarding generics here - not supported for now
		if (info.getBundleSymbolicName().length() == 0) {
			return classFinder.findClass(info.getClassName());
		}

		Bundle bundleToUse = getBundle(info);
		if (bundleToUse != null) {
			try {
				return bundleToUse.loadClass(info.getClassName());
			} catch (ClassNotFoundException | IllegalStateException ex) {
				// the bundle cannot find the required class, so we log the exception and fall back to just finding the class by name
				logger.warn("Class {} could not be loaded by bundle {}", info.getClassName(), info.getBundleSymbolicName(), ex);
			}
		} else {
			logger.warn("Bundle {} (version {}) not found when trying to load class {}", info.getBundleSymbolicName(), info.getBundleVersion(), info.getClassName());
		}
		// If the bundle is not found, or cannot load the required class, fall back and try to load the class with ClassUtil
		return classFinder.findClass(info.getClassName());
	}

	private Bundle getBundle(BundleAndClassInfo info) {
		Bundle cachedBundle = cachedBundles.get(info);
		if (cachedBundle != null) {
			if (cachedBundle.getState() != Bundle.UNINSTALLED) {
				return cachedBundle;
			} else {
				cachedBundles.remove(info);
			}
		}
		Bundle[] bundles = bundleProvider.getBundles();
		Bundle bundleToUse = null;
		List<Bundle> bundlesWithCorrectName = new ArrayList<>();
		for (Bundle bundle : bundles) {
			if (info.getBundleSymbolicName().equals(bundle.getSymbolicName())) {
				// Correct name. If correct version, select this bundle and exit the loop.
				if (bundle.getVersion().toString().equals(info.getBundleVersion())) {
					bundleToUse = bundle;
					break;
				}
				// Otherwise, keep a reference to it and we will fall back to it later if a better match is not found.
				bundlesWithCorrectName.add(bundle);
			}
		}
		if (bundleToUse == null && !bundlesWithCorrectName.isEmpty()) {
			// We haven't found an exact match, but there are some bundles with the same name.
			// Sort them in decreasing order by version number. Select the last one with a version greater than the
			// target version to try and ensure a close match.
			// (Perhaps we should also try to match major version first? Doesn't seem like a big enough issue to be
			// worth implementing for now.)
			bundlesWithCorrectName.sort(Comparator.comparing(Bundle::getVersion).reversed());
			Version requiredVersion = Version.parseVersion(info.getBundleVersion());
			for (Bundle bundle : bundlesWithCorrectName) {
				if (bundle.getVersion().compareTo(requiredVersion) > 0) {
					bundleToUse = bundle;
				}
			}
			// We have no bundles of higher version than the target. Select the highest version we have.
			if (bundleToUse == null) {
				bundleToUse = bundlesWithCorrectName.get(0);
			}
		}
		if (bundleToUse != null) {
			cachedBundles.put(info, bundleToUse);
		}
		return bundleToUse;
	}
}
