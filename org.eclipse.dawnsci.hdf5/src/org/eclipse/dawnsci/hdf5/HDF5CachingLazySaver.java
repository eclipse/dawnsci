/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import java.io.IOException;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.SliceND;

import hdf.hdf5lib.H5;
import hdf.hdf5lib.exceptions.HDF5Exception;

public class HDF5CachingLazySaver extends HDF5LazySaver {
	
	private static final long serialVersionUID = 1L;
	
	long[] datasetID = new long[]{-1,-1};

	public HDF5CachingLazySaver(String hostname, String filename, String node, String name, int[] trueShape, int isize,
			int dtype, boolean extendUnsigned, int[] maxShape, int[] chunks, Object fill) {
		super(hostname, filename, node, name, trueShape, isize, dtype, extendUnsigned, maxShape, chunks, fill);
	}

	
	@Override
	public void setSlice(IMonitor mon, IDataset data, SliceND slice) throws IOException {
		if (!init) {
			boolean zeroes = false;
			for (int i : trueShape) {
				if (i == 0) {
					zeroes = true;
					break;
				}
			}
			if (zeroes) {
				trueShape = slice.getSourceShape().clone();
			}
			initialize();
		}
		if (data.getRank() == 0) {
			data = data.getSliceView();
			data.setShape(slice.getShape());
		}

		// higher level API does not cope with differing data types
		data = DatasetUtils.cast(data, dtype);
		
		
		
		try {
			if (!create) { // ensure create on first use
				HDF5Utils.setDatasetSlice(filePath, parentPath, name, slice, data);
				create = true;
			} else {
				if (datasetID[0] == -1) datasetID[0] = HDF5FileFactory.acquireFile(filePath, true).getID();
				try {
					HDF5Utils.writeDatasetSliceWithID(datasetID, dataPath, slice, data);
				} catch (NexusException e) {
					throw new ScanFileHolderException("Problem writing slice to dataset", e);
				} 
			}
			expandShape(slice);
		} catch (ScanFileHolderException e) {
			throw new IOException(e);
		}
	}
	
	public void closeDataset(){
		if (datasetID[1] != -1) {
			try {
				H5.H5Dflush(datasetID[1]);
			} catch (HDF5Exception ex) {
				logger.error("Could not flush data", ex);
			}
			try {
				H5.H5Dclose(datasetID[1]);
			} catch (HDF5Exception ex) {
				logger.error("Could not close data", ex);
			}
		}
	}
	
	public void flushDataset(){
		if (datasetID[1] != -1) {
			try {
				H5.H5Dflush(datasetID[1]);
			} catch (HDF5Exception ex) {
				logger.error("Could not flush data", ex);
			}
		}
	}
	
	
}
