/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5.nexus;

import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NexusFile;

/**
 * HDF5 implementation of {@link INexusFileFactory}.
 */
public class NexusFileFactoryHDF5 implements INexusFileFactory {

	@Override
	public NexusFile newNexusFile(String path) {
		return new NexusFileHDF5(path);
	}

	@Override
	public NexusFile newNexusFile(String path, boolean enableSWMR) {
		return new NexusFileHDF5(path, enableSWMR);
	}

}
