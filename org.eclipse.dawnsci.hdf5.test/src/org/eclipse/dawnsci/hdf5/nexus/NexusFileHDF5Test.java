/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5.nexus;

import static org.junit.Assert.assertFalse;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyWriteableDataset;
import org.eclipse.dawnsci.hdf5.HDF5DatasetResource;
import org.eclipse.dawnsci.hdf5.HDF5DatatypeResource;
import org.eclipse.dawnsci.hdf5.HDF5FileResource;
import org.eclipse.dawnsci.hdf5.HDF5Resource;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileHDF5;
import org.junit.Test;

public class NexusFileHDF5Test {

	private final static String FILE_NAME = "test-scratch/test.nxs";

	@Test
	public void testStringsSwmrFixedLength() throws Exception {
		IDataset ods = DatasetFactory.createFromObject(new String[] {"String 1", "String 2", "String 3"});
		try (NexusFileHDF5 nf = new NexusFileHDF5(FILE_NAME, true)) {
			nf.createAndOpenToWrite();
			ILazyWriteableDataset lds = new LazyWriteableDataset("data",
					Dataset.STRING,
					new int[] {0},
					new int[] {ILazyWriteableDataset.UNLIMITED},
					new int[] {3},
					null);
			nf.createData("/test", lds, true);
			nf.flush();
		}
		try (HDF5Resource fRes = new HDF5FileResource(H5.H5Fopen(FILE_NAME,
					HDF5Constants.H5F_ACC_RDONLY,
					HDF5Constants.H5P_DEFAULT));
				HDF5Resource dRes = new HDF5DatasetResource(H5.H5Dopen(fRes.getResource(),
						"/test/data",
						HDF5Constants.H5P_DEFAULT));
				HDF5Resource tRes = new HDF5DatatypeResource(H5.H5Dget_type(dRes.getResource()))) {
			assertFalse(H5.H5Tis_variable_str(tRes.getResource()));
		}
		try (NexusFileHDF5 nf = new NexusFileHDF5(FILE_NAME, true)) {
			nf.openToWrite(true);
			ILazyWriteableDataset lds = nf.getData("/test/data").getWriteableDataset();
			lds.setSlice(null, ods, new int[] {0}, new int[] {3}, null);
		}
	}
}
