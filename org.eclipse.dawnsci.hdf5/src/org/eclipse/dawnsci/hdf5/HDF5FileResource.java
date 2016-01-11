/*-
 * Copyright © 2015 Diamond Light Source Ltd.
 *
 * This file is part of GDA.
 *
 * GDA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License version 3 as published by the Free
 * Software Foundation.
 *
 * GDA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with GDA. If not, see <http://www.gnu.org/licenses/>.
 */

package org.eclipse.dawnsci.hdf5;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.exceptions.HDF5LibraryException;

import org.eclipse.dawnsci.hdf5.HDF5BaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
