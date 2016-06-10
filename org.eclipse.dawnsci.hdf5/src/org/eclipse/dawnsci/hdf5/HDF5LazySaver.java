/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.ILazySaver;
import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;

/**
 * Lazy saver for HDF5 files
 */
public class HDF5LazySaver extends HDF5LazyLoader implements ILazySaver, Serializable {

	private static final long serialVersionUID = -5244067010482825423L;

	boolean isWriteable;
	private String parentPath; // path of group containing dataset
	private int[] maxShape;
	private int[] chunks;
	private Object fill;
	private boolean create = false; // create on first slice setting
	private boolean init = false;   // has been initialized?

	/**
	 * 
	 * @param hostname
	 * @param filename
	 * @param node
	 * @param name
	 * @param trueShape
	 * @param isize
	 * @param dtype
	 * @param extendUnsigned
	 * @param maxShape
	 * @param chunks
	 * @param fill null, a String or single value array
	 */
	public HDF5LazySaver(String hostname, String filename, String node, String name, int[] trueShape, int isize,
			int dtype, boolean extendUnsigned, int[] maxShape, int[] chunks, Object fill) {
		super(hostname, filename, node, name, trueShape, isize, dtype, extendUnsigned);
		int i = node.lastIndexOf(Node.SEPARATOR);
		if (i > 0) {
			parentPath = node.substring(0, i);
		} else {
			parentPath = Tree.ROOT;
		}
		this.maxShape = maxShape == null ? trueShape.clone() : maxShape.clone();
		this.chunks = chunks == null ? null : chunks.clone();
		this.fill = fill;
		isWriteable = false;
	}

	/**
	 * Set flag to create the dataset on file when the first slice is set
	 * @param create this is false by default (i.e. no dataset is created)
	 */
	public void setCreateOnInitialization(boolean create) {
		if (init) {
			throw new UnsupportedOperationException("It is too late for this flag to have any effect as the first slice has already been set");
		}
		this.create = create;
	}

	@Override
	public boolean isFileWriteable() {
		if (!isWriteable && (!init || create || isFileReadable())) {
			File f = new File(filePath);
			isWriteable = f.exists() ? f.canWrite() : f.getParentFile().canWrite();
		}

		return isWriteable;
	}

	@Override
	public void initialize() throws IOException {
		if (!init) {
			init = true;
			if (create) {
				try {
					HDF5Utils.createDataset(filePath, parentPath, name, trueShape, maxShape, chunks, dtype, fill, false);
				} catch (ScanFileHolderException e) {
					throw new IOException(e);
				}
			}
		}
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

		//higher level API does not cope with differing data types
		data = DatasetUtils.cast(data, dtype);
		try {
			if (!create) { // ensure create on first use
				HDF5Utils.setDatasetSlice(filePath, parentPath, name, slice, data);
				create = true;
			} else {
				HDF5Utils.setExistingDatasetSlice(filePath, parentPath, name, slice, data);
			}
		} catch (ScanFileHolderException e) {
			throw new IOException(e);
		}
	}
}
