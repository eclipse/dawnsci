/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

public abstract class HDF5BaseResource implements HDF5Resource {

	protected final long resource;

	public HDF5BaseResource(long resource) {
		this.resource = resource;
	}

	@Override
	public long getResource() {
		if (resource < 0) {
			throw new IllegalStateException("No resource acquired");
		}
		return resource;
	}
}
