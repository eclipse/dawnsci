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

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.metadata.AxesMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.IDiffractionMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * A service which loads data using any loader it can, depending on the 
 * service implementation and returns it as an IDataset or IDataHolder
 * 
 * The implementor of this service contributes using an eclipse extension
 * point and then later any plugin may ask for an implementation of the service.
 * 
 * @author Matthew Gerring
 */
public interface ILoaderService {

	/**
	 * Reads a file and returns it as a data holder
	 * @param filePath
	 * @param monitor
	 * @return IDataHolder
	 * @throws Exception
	 */
    public IDataHolder getData(String filePath, final IMonitor monitor) throws Exception;

	/**
	 * Reads a file and returns it as a data holder
	 * @param filePath
	 * @param lazily if true, <b>all</b> datasets in the data holder will be lazy otherwise the holder
	 * may contain non-lazy datasets
	 * @param monitor
	 * @return IDataHolder
	 * @throws Exception
	 */
    public IDataHolder getData(String filePath, boolean lazily, final IMonitor monitor) throws Exception;

	/**
	 * Reads a single dataset from a file and returns it as a Dataset, with progress
	 * Use for image files.
	 * @param filePath
	 * @param monitor
	 * @return IDataHolder
	 * @throws Exception
	 */
    public IDataset getDataset(String filePath, final IMonitor monitor) throws Exception;
    
	/**
	 * Reads a dataset and returns it as a Dataset, with progress
	 * 
	 * NOTE LazyDatasets will be loaded into memory by this method. To avoid this use:
	 * <code>
	 * IDataHolder holder = LoaderFactory.getData(...)
	 * ILazyDataset lz    = holder.getLazyDataset(...)
	 * <code>
	 * 
	 * Now the ILazyDataset is available rather than loading all into memory.
	 * If you use this method all the data of the dataset will be loaded to memory.
     *
	 * 
	 * @param filePath
	 * @param datasetPath for instance nexus path, may be null.
	 * @param monitor
	 * @return IDataHolder
	 * @throws Exception
	 */
    public IDataset getDataset(String filePath, final String datasetPath, final IMonitor monitor) throws Exception;

    /**
     * This method can be used to load meta data. It will use Fabio if
     * LoaderFactory does not work.
     * 
     * @param filePath
     * @param monitor
     * @return metadata
     * @throws Exception
     */
    public IMetadata getMetadata(final String filePath, final IMonitor monitor) throws Exception;

    /**
     * The locked diffraction data if any. Usually this will be null unless someone
     * has specifically decided to override the meta data.
     * 
     * @return IDataHolder
     */
    public IDiffractionMetadata getLockedDiffractionMetaData();

    /**
     * Call to set the locked data, this will mean that the real diffraction data is
     * ignored in some cases.
     * 
     * @param diffMetaData
     * @return the old one if any.
     */
    public IDiffractionMetadata setLockedDiffractionMetaData(IDiffractionMetadata diffMetaData);

    /**
     * 
     * @return list of supported file extensions.
     */
	public Collection<String> getSupportedExtensions();

    /**
     * Clears the cache of soft references which the loader service keeps.
     * This can help reduce working memory if a class is created that 
     * swamps available memory with the soft reference cache.
     */
	public void clearSoftReferenceCache();
	
	/**
	 * The matcher for the expression string for parsing directories of 
	 * similarly names files into ILazyDatasets.
	 * 
	 * Returns null if no matcher can be assigned for this file name.
	 * 
	 * Returns a matcher where groups
	 * 0 = whole string
	 * 1 = repeated stub
	 * 2 = index in dataset (not necessarily a contiguous series of integers)
	 * 3 = the file extension.
	 * 
	 * @return regular expression
	 */
	public Matcher getStackMatcher(String fileName);
	
	/**
	 * Creates a AxesMetadata object containing lazy datasets for the axes specified in the axesNames <Dimension,DatasetName> map
	 * 
	 * @param parent - the lazy dataset the metadata relates to
	 * @param path - the path to the file
	 * @param axesNames - a map of the dimension (Integer) against the axes dataset name (String)
	 * @return axesMetadata
	 * @throws Exception
	 */
	public AxesMetadata getAxesMetadata(ILazyDataset parent, String path, Map<Integer, String> axesNames) throws Exception;

	/**
	 * Get class that can load files of given extension
	 * 
	 * @param extension
	 * @return loader class
	 */
	public Class<? extends IFileLoader> getLoaderClass(String extension);
}
