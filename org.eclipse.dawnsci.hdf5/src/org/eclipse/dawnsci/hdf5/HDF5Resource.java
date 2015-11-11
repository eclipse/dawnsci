/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

/**
 * Interface to allow wrapping of raw HDF5 resources into something that
 * can be used with try-with-resource.
 */
public interface HDF5Resource extends AutoCloseable {
	/**
	 * @return raw resource identifier
	 */
	public long getResource();

	@Override
	public void close();
}
