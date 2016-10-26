/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.january.asserts.TestUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.junit.Test;

public class ScalarDatasetTest {
	private static final String file = "test-scratch/scalar.h5";

	@Test
	public void testScalar() throws ScanFileHolderException, NexusException {
		HDF5File f = HDF5FileFactory.acquireFileAsNew(file);
		Dataset scalar = DatasetFactory.createFromObject(1.0);
		scalar.setName("scalar");
		HDF5Utils.writeDataset(f, "/" + scalar.getName(), scalar);

		Dataset data = DatasetFactory.createFromObject(new double[] {1.5});
		data.setName("data1d");
		HDF5Utils.writeDataset(f, "/" + data.getName(), data);

		HDF5FileFactory.releaseFile(file, true);

		f = HDF5FileFactory.acquireFile(file, false);
		Dataset s = HDF5Utils.loadDataset(f, "/scalar");
		TestUtils.assertDatasetEquals(scalar, s);

		Dataset d = HDF5Utils.loadDataset(f, "/data1d");
		TestUtils.assertDatasetEquals(data, d);

		HDF5FileFactory.releaseFile(file, true);
	}

}

