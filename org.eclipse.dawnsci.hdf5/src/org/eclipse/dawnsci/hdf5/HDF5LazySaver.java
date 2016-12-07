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
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.SliceND;
import org.eclipse.january.io.ILazyAsyncSaver;

/**
 * Lazy saver for HDF5 files
 */
public class HDF5LazySaver extends HDF5LazyLoader implements ILazyAsyncSaver, Serializable {

	private static final long serialVersionUID = -5244067010482825423L;

	boolean isWriteable;
	protected String parentPath; // path of group containing dataset
	private int[] maxShape;
	private int[] chunks;
	private Object fill;
	protected boolean create = false; // create on first slice setting
	protected boolean init = false;   // has been initialized?

	private ILazyWriteableDataset writeableDataset;

	protected String dataPath;

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
		dataPath = HDF5Utils.absolutePathToData(parentPath, name);
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

	/**
	 * Set flag to indicate the dataset has already been created on file
	 */
	public void setAlreadyCreated() {
		create = true;
		init = true;
	}

	@Override
	public boolean isFileWriteable() {
		if (!isWriteable && (!init || create || isFileReadable())) {
			if (file == null) {
				file = new File(filePath);
			}
			isWriteable = file.exists() ? file.canWrite() : file.getParentFile().canWrite();
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

		// higher level API does not cope with differing data types
		data = DatasetUtils.cast(data, dtype);
		try {
			if (!create) { // ensure create on first use
				HDF5Utils.setDatasetSlice(filePath, parentPath, name, slice, data);
				create = true;
			} else {
				HDF5File fid = HDF5FileFactory.acquireFile(filePath, true);
				try {
					HDF5Utils.writeDatasetSlice(fid, dataPath, slice, data);
				} catch (NexusException e) {
					throw new ScanFileHolderException("Problem writing slice to dataset", e);
				} finally {
					fid.decrementCount();
				}
			}
			expandShape(slice);
		} catch (ScanFileHolderException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Set to asynchronously write if lazy writeable dataset is not null
	 * <p>
	 * The dataset must correspond to that held by this saver otherwise mismatches
	 * can occur between the shape
	 * @param dataset
	 */
	public void setAsyncWriteableDataset(ILazyWriteableDataset dataset) {
		if (!Arrays.equals(dataset.getChunking(), chunks)) {
			throw new IllegalArgumentException("Chunk size of given dataset must match saver's");
		}
		if (!Arrays.equals(dataset.getMaxShape(), maxShape)) {
			throw new IllegalArgumentException("Max shape of given dataset must match saver's");
		}
		Object ofill = dataset.getFillValue();
		if (fill != ofill || (fill != null && !fill.equals(ofill))) {
			throw new IllegalArgumentException("Fill value of given dataset must match saver's");
		}
		this.writeableDataset = dataset;
	}

	@Override
	public void setSliceAsync(IMonitor mon, IDataset data, SliceND slice) throws IOException {
		try {
			HDF5File fid = HDF5FileFactory.acquireFile(filePath, true);
			synchronized (fid) {
				fid.addWriteJob(writeableDataset, data, slice);
				fid.decrementCount();
			}
			expandShape(slice);
		} catch (ScanFileHolderException e) {
			logger.error("Problem setting slice of dataset in file: {}", filePath, e);
			throw new IOException("Problem setting slice of dataset in file: " + filePath, e);
		}
	}

	protected boolean expandShape(SliceND slice) {
		int[] eShape = slice.getSourceShape();
		if (eShape.length != trueShape.length) {
			throw new IllegalArgumentException("Slice shape must match this saver's shape");
		}
		boolean expand = false;
		for (int i = 0; i < trueShape.length; i++) {
			int l = eShape[i];
			if (l > trueShape[i]) {
				trueShape[i] = l; 
				expand = true;
			}
		}
		return expand;
	}

	@Override
	public int[] refreshShape() {
		return trueShape.clone();
	}

	@Override
	public String toString() {
		return dataPath;
	}
}
