/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.remotedataset.test.mock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.IFileLoader;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.hdf5.HDF5FileFactory;
import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.IMetadataProvider;
import org.eclipse.january.dataset.MetadataException;
import org.eclipse.january.metadata.IMetadata;
import org.eclipse.january.metadata.Metadata;
import org.eclipse.january.metadata.MetadataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is to marshal all the data for the purpose of loading from or saving to a file
 * directly or via a ScanFileHolder.
 * <p>
 * This is designed to take in any dataset obeying the IDataset interface but output an
 * object that is a subclass of Dataset - the dataset will be converted if necessary.
 * 
 * This implementation does not permit duplicated names.
 */
public class MockDataHolder implements IMetadataProvider, IDataHolder, Serializable {
	
	protected static final Logger logger = LoggerFactory.getLogger(MockDataHolder.class);

	/**
	 * List containing all the name and data pairs (to be) loaded.
	 */
	private LinkedHashMap<String, ILazyDataset> nameDataMappings;

	/**
	 * List containing metadata
	 */
	private IMetadata metadata;

	/**
	 * Loader class string
	 */
	private String loaderClass;
	
	/**
	 * The path to the original file loaded (if any)
	 */
	private String filePath;

	private Tree tree;

	private INexusFileFactory factory;

	/**
	 * This must create the three objects which will be put into the ScanFileHolder
	 */
	public MockDataHolder(INexusFileFactory factory, String filePath) {
		nameDataMappings = new LinkedHashMap<String, ILazyDataset>();
		metadata = new Metadata();
		this.factory = factory;
		this.filePath = filePath;
	}

	/**
	 * Used to hold metadata
	 * @param meta
	 */
	public MockDataHolder(IMetadata meta) {
		nameDataMappings = new LinkedHashMap<String, ILazyDataset>();
		metadata = meta;
	}

	/**
	 * The current data as a map of lazy datasets.
	 * @return map of lazy datasets with keys from their corresponding names
	 */
	@Override
	public Map<String, ILazyDataset> toLazyMap() {
		return new LinkedHashMap<String, ILazyDataset>(nameDataMappings);
	}

	/**
	 * Does not clone the meta data.
	 * @return shallow copy of DataHolder
	 */
	@Override
	public IDataHolder clone() {
		MockDataHolder ret = new MockDataHolder(factory, filePath);
		ret.nameDataMappings.putAll(nameDataMappings);
		ret.metadata    = metadata;
		ret.filePath    = filePath;
		ret.loaderClass = loaderClass;
		return ret;
	}

	/**
	 * Adds a dataset and its name into the two vectors of the Object.
	 * 
	 * Replaces any datasets of the same name already existing.
	 *  
	 * @param name
	 *            the name of the dataset which is to be added
	 * @param dataset
	 *            the actual data of the dataset
	 */
	@Override
	public boolean addDataset(String name, ILazyDataset dataset) {
		boolean ret = nameDataMappings.containsKey(name);
		nameDataMappings.put(name, dataset);
		return ret;
	}

	/**
	 * Adds a dataset, metadata and its name. This is for Diffraction data
	 * 
	 * Replaces any datasets of the same name already existing.
	 * 
	 * @param name
	 *            the name of the dataset which is to be added
	 * @param dataset
	 *            the actual data of the dataset.
	 * @param metadata
	 *            the metadata that is associated with the dataset
	 */
	public boolean addDataset(String name, ILazyDataset dataset, IMetadata metadata) {
		boolean ret = addDataset(name, dataset);
		this.metadata = metadata;
		return ret;
	}
	
	/**
	 * Add a ImetaData object
	 * @param metadata which is an object implementing IMetadata
	 */
	@Override
	public void setMetadata(IMetadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return an object implementing IMetadata
	 */
	@Override
	public IMetadata getMetadata() {
		return metadata;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends MetadataType> List<T> getMetadata(Class<T> clazz) throws MetadataException {
		if (IMetadata.class.isAssignableFrom(clazz)) {
			List<T> result = new ArrayList<T>();
			result.add((T) getMetadata());
			return result;
		}
		throw new MetadataException("getMetadata(clazz) does not currently support anything other than IMetadata");
		// If it should only support this, simply return null here, otherwise implement the method fully
	}
	
	@Override
	public <T extends MetadataType> T getFirstMetadata(Class<T> clazz) {
		try {
			List<T> ml = getMetadata(clazz);
			if (ml == null) return null;
			return ml.isEmpty() ? null : ml.get(0);
		} catch (Exception e) {
			logger.error("Get metadata failed!",e);
		}

		return null;
	}

	/**
	 * @return List of datasets
	 */
	@Override
	public List<ILazyDataset> getList() {
		return new ArrayList<ILazyDataset>(nameDataMappings.values());
	}

	/**
	 * This pulls out the dataset which could be lazy, maintaining its laziness.
	 * @param index
	 * @return Generic dataset with given index in holder or null if it does not exist
	 */
	@Override
	public ILazyDataset getLazyDataset(int index) {
		final String key = getName(index);
		if (key == null ) return null;
 		return nameDataMappings.get(key);
	}

	/**
	 * This pulls out the dataset which could be lazy, maintaining its laziness.
	 * @param name
	 * @return Generic dataset with given name or null if it does not exist
	 */
	@Override
	public ILazyDataset getLazyDataset(String name) {
		if (nameDataMappings.containsKey(name)) return nameDataMappings.get(name);
		
		try {
			NexusFile file = factory.newNexusFile(filePath);
			try {
				file.openToRead();
				final DataNode node = file.getData(name);
				ILazyDataset ds = node.getDataset();
				return ds;
			} finally {
				file.close();
			}
		} catch (Exception ne) {
			throw new RuntimeException(ne);
		}

	}

	/**
	 * Set a generic dataset with given name
	 * @param name
	 * @param dataset
	 */
	public void setDataset(String name, ILazyDataset dataset) {
		nameDataMappings.put(name, dataset);
	}

	/**
	 * This does not retrieve lazy datasets.
	 * @param index
	 * @return Generic dataset with given index in holder or null if it is lazy or does not exist
	 */
	@Override
	public Dataset getDataset(int index) {
		ILazyDataset lazy = getLazyDataset(index);
		return lazy instanceof IDataset ? DatasetUtils.convertToDataset((IDataset) lazy) : null;
	}

	/**
	 * This does not retrieve lazy datasets.
	 * @param name
	 * @return Generic dataset with given name or null if it is lazy or does not exist
	 */
	@Override
	public Dataset getDataset(String name) {
		ILazyDataset lazy = getLazyDataset(name);
		return lazy instanceof IDataset ? DatasetUtils.convertToDataset((IDataset) lazy) : null;
	}

	/**
	 * @param name
	 * @return true if data holder contains name 
	 * @see java.util.List#contains(Object)
	 */
	@Override
	public boolean contains(String name) {
		return nameDataMappings.containsKey(name);
	}

	/**
	 * @param name
	 * @return index of dataset with given name
	 * @see java.util.List#indexOf(Object)
	 */
	public int indexOf(String name) {
		List<String> keys = new ArrayList<String>(nameDataMappings.keySet());
		return keys.indexOf(name);
	}

	/**
	 * @return Array of dataset names
	 */
	@Override
	public String[] getNames() {
		return nameDataMappings.keySet().toArray(new String[nameDataMappings.size()]);
	}

	/**
	 * @param index
	 * @return Dataset name at given index
	 */
	@Override
	public String getName(final int index) {
		try {
			List<String> keys = new ArrayList<String>(nameDataMappings.keySet());
			return keys.get(index);
		} catch( IndexOutOfBoundsException e ) {
			return null;
		}
	}

	/**
	 * @return Number of datasets
	 */
	@Override
	public int size() {
		return nameDataMappings.size();
	}

	/**
	 * @return Number of unique dataset names
	 */
	@Override
	public int namesSize() {
		return size();
	}

	/**
	 * Clear list of names and datasets
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		nameDataMappings.clear();
		metadata = null;
		tree = null;
	}

	/**
	 * Remove name and dataset at index
	 * @param index
	 * @see java.util.List#remove(int)
	 */
	public void remove(int index) {
		final String key = getName(index);
		if (key != null)
			nameDataMappings.remove(key);
	}

	/**
	 * @return class
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends IFileLoader> getLoaderClass() {
		return null;
	}

	@Override
	public void setLoaderClass(Class<? extends IFileLoader> clazz) {
		loaderClass = clazz.getName();
	}

	@Override
	public String getFilePath() {
		return filePath;
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void setTree(Tree tree) {
		this.tree = tree;
	}

	private static final long DEFAULT_OBJECT_ID = -1;

	@Override
	public Tree getTree() {
		if (tree==null) {
			try {
				try {
					long fid = HDF5FileFactory.acquireFile(getFilePath(), false);
	
					final long oid = getFilePath().hashCode(); // include file name in ID
					TreeFile f = TreeFactory.createTreeFile(oid, getFilePath());
				    tree = f;
				} finally {
					HDF5FileFactory.releaseFile(getFilePath());
				}
			} catch (Exception ne) {
				throw new RuntimeException(ne);
			}
		}
		
		return tree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((loaderClass == null) ? 0 : loaderClass.hashCode());
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		result = prime * result + ((nameDataMappings == null) ? 0 : nameDataMappings.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MockDataHolder other = (MockDataHolder) obj;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (loaderClass == null) {
			if (other.loaderClass != null)
				return false;
		} else if (!loaderClass.equals(other.loaderClass))
			return false;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		} else if (!metadata.equals(other.metadata))
			return false;
		if (nameDataMappings == null) {
			if (other.nameDataMappings != null)
				return false;
		} else if (!nameDataMappings.equals(other.nameDataMappings))
			return false;
		return true;
	}

}
