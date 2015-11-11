/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.exceptions.HDF5LibraryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDF5DatatypeResource extends HDF5BaseResource {

	private static final Logger logger = LoggerFactory.getLogger(HDF5DatatypeResource.class);

	/**
	 * Wrap the specified datatype resource identifier
	 * @param resource datatype identifier to wrap
	 */
	public HDF5DatatypeResource(long resource) {
		super(resource);
	}

	@Override
	public void close() {
		try {
			H5.H5Tclose(resource);
		} catch (HDF5LibraryException e) {
			logger.error("Could not close HDF5 datatype", e);
		}
	}

}
