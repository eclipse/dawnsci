/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hdf.hdf5lib.H5;
import hdf.hdf5lib.exceptions.HDF5LibraryException;

public class HDF5FileResource extends HDF5BaseResource {

	private static final Logger logger = LoggerFactory.getLogger(HDF5FileResource.class);

	/**
	 * Wrap the specified file resource identifier
	 * @param resource file identifier to wrap
	 */
	public HDF5FileResource(long resource) {
		super(resource);
	}

	@Override
	public void close() {
		try {
			H5.H5Fclose(resource);
		} catch (HDF5LibraryException e) {
			logger.error("Could not close HDF5 file", e);
		}
	}

}
