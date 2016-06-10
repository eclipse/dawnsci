/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.remotedataset.test.mock;

import java.io.File;
import java.io.IOException;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.IFileLoader;
import org.eclipse.dawnsci.analysis.api.io.ILazyLoader;
import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.metadata.IMetaLoader;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.Metadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyDataset;

/**
 * A class which can be extended when implementing IFileLoader
 */
public abstract class MockAbstractFileLoader implements IFileLoader, IMetaLoader {
	/** 
	 * Name prefix for an image dataset (should be followed by two digits, starting with 01)
	 */
	public static final String IMAGE_NAME_PREFIX = "image-";

	/** 
	 * Name format for an image dataset
	 */
	public static final String IMAGE_NAME_FORMAT = IMAGE_NAME_PREFIX + "%02d";

	/**
	 * Default name for first image dataset
	 */
	public static final String DEF_IMAGE_NAME = IMAGE_NAME_PREFIX + "01";

	/**
	 * Name for image stack
	 */
	public static final String STACK_NAME = "image-stack";

	/**
	 * String to separate full file path or file name from dataset name
	 */
	public static final String FILEPATH_DATASET_SEPARATOR = ":";

	protected String fileName = "";

	public void setFile(final String fileName) {
		this.fileName = fileName;
		clearMetadata();
	}

	protected boolean loadMetadata = true;
	protected Metadata metadata;
	protected boolean loadLazily = false;

	abstract protected void clearMetadata();

	@Override
	public IMetadata getMetadata() {
		return metadata;
	}

	@Override
	public IDataHolder loadFile(IMonitor mon) throws ScanFileHolderException {
		return loadFile();
	}

	@Override
	public void setLoadMetadata(boolean willLoadMetadata) {
		loadMetadata = willLoadMetadata;
	}

	@Override
	public void setLoadAllLazily(boolean willLoadLazily) {
		loadLazily = willLoadLazily;
	}

	@Override
	public void loadMetadata(IMonitor mon) throws Exception {
		if (metadata != null)
			return;

		boolean oldMeta = loadMetadata;
		boolean oldLazily = loadLazily;
		loadMetadata = true;
		loadLazily = true;
		loadFile(mon);
		loadMetadata = oldMeta;
		loadLazily = oldLazily;
	}

	protected class LazyLoaderStub implements ILazyLoader {
		public static final long serialVersionUID = 5057544213374303912L;
		private IFileLoader loader;
		private String name;

		public LazyLoaderStub() {
			loader = null;
			name = null;
		}

		public LazyLoaderStub(IFileLoader loader) {
			this(loader, null);
		}

		/**
		 * @param loader
		 * @param name dataset name in data holder (can be null to signify first dataset)
		 */
		public LazyLoaderStub(IFileLoader loader, String name) {
			this.loader = loader;
			this.name = name;
			if (loader != null) {
				loader.setLoadAllLazily(false);
				loader.setLoadMetadata(false);
			}
		}

		public IFileLoader getLoader() {
			return loader;
		}

		@Override
		public IDataset getDataset(IMonitor mon, SliceND slice) throws IOException {
			if (loader == null) {
				return null;
			}
			IDataHolder holder;
			try {
				holder = loader.loadFile(mon);
			} catch (ScanFileHolderException e) {
				throw new IOException("Could not load file", e);
			}
			if (holder.getFilePath() == null) {
				holder.setFilePath(fileName);
			}
			if (holder.getLoaderClass() == null) {
				holder.setLoaderClass(loader.getClass());
			}
			IDataset data = name == null ? holder.getDataset(0) : holder.getDataset(name);
			return data.getSliceView(slice);
		}

		@Override
		public boolean isFileReadable() {
			return new File(fileName).canRead();
		}
	}

	protected LazyDataset createLazyDataset(String dName, int dtype, int[] shape, LazyLoaderStub l) {
		return new LazyDataset(dName, dtype, shape, l);
	}

	protected LazyDataset createLazyDataset(String dName, String dhName, int dtype, int[] shape, IFileLoader loader) {
		return new LazyDataset(dName, dtype, shape, new LazyLoaderStub(loader, dhName));
	}

	protected LazyDataset createLazyDataset(String dName, int dtype, int[] shape, IFileLoader loader) {
		return createLazyDataset(dName, dName, dtype, shape, loader);
	}

	/**
	 * @param mon
	 * @return false if cancelled
	 */
	protected static boolean monitorIncrement(IMonitor mon) {
		if (mon != null) {
			mon.worked(1);
			if (mon.isCancelled()) return false;
		}
		return true;
	}
	
	@Override
	public void setAsyncLoad(boolean treeOnTop) {
		throw new RuntimeException("Asynchronous loading is not supported!");
	}
	
	@Override
	public boolean isLoading() {
		throw new RuntimeException("Asynchronous loading is not supported!");
	}

}
