/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.io;

import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.tree.Tree;

public interface IDataHolder extends IDataAnalysisObject {
	
	/**
	 * Does a shallow copy of the data which is useful for 
	 * the case where additional ILazyDatasets may be registered with
	 * the holder.
	 * 
	 * @return a shallow copy
	 */
	@Override
	public IDataHolder clone();

	/**
	 * @return an object implementing IMetadata
	 */
	public IMetadata getMetadata();
	
	/**
	 * Set the metadata
	 */
	public void setMetadata(IMetadata md);

	/**
	 * This does not retrieve lazy datasets.
	 * @param index
	 * @return Generic dataset with given index in holder
	 */
	public IDataset getDataset(int index);

	/**
	 * This does not retrieve lazy datasets.
	 * @param name
	 * @return Generic dataset with given name (first one if name not unique)
	 */
	public IDataset getDataset(String name);

	/**
	 * This pulls out the dataset which could be lazy, maintaining its laziness.
	 * @param index
	 * @return Generic dataset with given index in holder
	 */
	public ILazyDataset getLazyDataset(int index);

	/**
	 * This pulls out the dataset which could be lazy, maintaining its laziness.
	 * @param name
	 * @return Generic dataset with given name (first one if name not unique)
	 */
	public ILazyDataset getLazyDataset(String name);

	/**
	 * @param name
	 * @return true if data holder contains name 
	 * @see java.util.List#contains(Object)
	 */
	public boolean contains(String name);

	/**
	 * @return Array of dataset names
	 */
	public String[] getNames();

	/**
	 * @param index
	 * @return Dataset name at given index
	 */
	public String getName(int index);

	/**
	 * @return Number of datasets
	 */
	public int size();

	/**
	 * @return Number of unique dataset names
	 */
	public int namesSize();
	
	/**
	 * The file path, if any of the data loaded.
	 * @return path to data
	 */ 
	public String getFilePath();
	
	/**
	 * The file path, if any of the data loaded.
	 * @param path
	 */ 
	public void setFilePath(String path);

	
	/**
	 * Allows a dataset to be added to the holder.
	 * WARNING expert use only! If you add data to a holder without
	 * cloning it, you can change the cached LoaderFactory version.
	 * 
	 * This can result in all users getting any extra data added to the holder.
	 * 
	 * The name will be replaced as duplicates are not allowed.
	 * 
	 * @param name
	 * @param dataset
	 * @return true if replaced old name, false normally.
	 */
	public boolean addDataset(String name, ILazyDataset dataset);

	/**
	 * The current data as a map
	 * @return map of lazy data
	 */
	public Map<String, ILazyDataset> toLazyMap();

	/**
	 * Expert/internal use only.
	 * @param clazz
	 */
	public void setLoaderClass(Class<? extends IFileLoader> clazz);

	/**
	 * @return the loader class, if any used to load this data.
	 */
	public Class<? extends IFileLoader> getLoaderClass();

	/**
	 * Clears the current data maps - use only when you are sure it is ok
	 * because this data holder can be cached in the LoaderFactory!
	 * 
	 * Expert/internal use only.
	 */
	public void clear();

	/**
	 * @return  The list of data in the holder (lazy)
	 */
	public List<ILazyDataset> getList();

	/**
	 * @return tree (can be null)
	 */
	public Tree getTree();
	
	/**
	 * Set the tree available in the holder
	 * @param tree
	 */
	public void setTree(Tree tree);
}
