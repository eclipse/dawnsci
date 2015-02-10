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
package org.eclipse.dawnsci.hdf5.model;

/**
 * A Model of an individual Hierarchical Data File (e.g. Nexus/HDF5)
 *
 * @author Tracy Miranda
 */
public interface IHierarchicalDataFileModel {

	/**
	 * Convenience method that return true if the given path (node + optional
	 * attribute) exists.
	 *
	 * @param path
	 *            Path to dataset or group + optional attribute (separated by @)
	 * @return whether file has specified path
	 */
	boolean hasPath(String path);

	/**
	 * Convenience method that allows passing a path with an optional @ followed
	 * by the attribute name.
	 *
	 * @param path
	 *            path containing optional @attribute
	 * @return Scalar value, attribute value or <code>null</code> if path did
	 *         not exists or did not contain scalar value.
	 */
	Object getPath(String path);
}
