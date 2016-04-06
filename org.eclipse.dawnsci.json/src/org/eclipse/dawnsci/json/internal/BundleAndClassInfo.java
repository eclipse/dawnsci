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
 * Simple class to hold necessary information about a bundle and class, and convert it to and from string form.
 * <p>
 * This is an immutable class with equals() and hashCode() implemented, allowing instances to be used as map keys.
 */
class BundleAndClassInfo {

	private static final String FIELD_DELIMITER = "&"; // cannot be any of ;,.-_ due to collisions with possible parts of bundle or class names
	private static final String EQUALS = "=";
	private static final String BUNDLE_FIELD_NAME = "bundle";
	private static final String VERSION_FIELD_NAME = "version";
	private static final String CLASS_FIELD_NAME = "class";

	private final String bundleSymbolicName;
	private final String bundleVersion;
	private final String className;

	private BundleAndClassInfo(String bundleSymbolicName, String bundleVersion, String className) {
		this.bundleSymbolicName = bundleSymbolicName == null ? "" : bundleSymbolicName;
		this.bundleVersion = bundleVersion == null ? "" : bundleVersion;
		this.className = className == null ? "" : className;
	}

	public static BundleAndClassInfo from(Bundle bundle, String className) {
		String bundleSymbolicName = null;
		String bundleVersion = null;
		if (bundle != null) {
			bundleSymbolicName = bundle.getSymbolicName();
			bundleVersion = bundle.getVersion().toString();
		}
		return new BundleAndClassInfo(bundleSymbolicName, bundleVersion, className);
	}

	public static BundleAndClassInfo from(String id) {
		String bundleSymbolicName = null;
		String bundleVersion = null;
		String className = null;
		for (String idPart : id.split(FIELD_DELIMITER)) {
			if (idPart.startsWith(BUNDLE_FIELD_NAME)) {
				bundleSymbolicName = idPart.substring(idPart.indexOf(EQUALS) + 1);
			} else if (idPart.startsWith(VERSION_FIELD_NAME)) {
				bundleVersion = idPart.substring(idPart.indexOf(EQUALS) + 1);
			} else if (idPart.startsWith(CLASS_FIELD_NAME)) {
				className = idPart.substring(idPart.indexOf(EQUALS) + 1);
			}
		}
		return new BundleAndClassInfo(bundleSymbolicName, bundleVersion, className);
	}

	public String getBundleSymbolicName() {
		return bundleSymbolicName;
	}

	public String getBundleVersion() {
		return bundleVersion;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return BUNDLE_FIELD_NAME + EQUALS + bundleSymbolicName + FIELD_DELIMITER
				+ VERSION_FIELD_NAME + EQUALS + bundleVersion + FIELD_DELIMITER
				+ CLASS_FIELD_NAME + EQUALS + className;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((bundleSymbolicName == null) ? 0 : bundleSymbolicName
						.hashCode());
		result = prime * result
				+ ((bundleVersion == null) ? 0 : bundleVersion.hashCode());
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BundleAndClassInfo other = (BundleAndClassInfo) obj;
		if (bundleSymbolicName == null) {
			if (other.bundleSymbolicName != null)
				return false;
		} else if (!bundleSymbolicName.equals(other.bundleSymbolicName))
			return false;
		if (bundleVersion == null) {
			if (other.bundleVersion != null)
				return false;
		} else if (!bundleVersion.equals(other.bundleVersion))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		return true;
	}
}