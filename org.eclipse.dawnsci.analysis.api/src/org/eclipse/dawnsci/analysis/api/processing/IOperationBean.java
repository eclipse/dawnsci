/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

import java.util.List;

/**
 * Interface to set up an operation processing run
 */
public interface IOperationBean {

	/**
	 * Path to the node containing the unique keys for live processing
	 * 
	 * @param dataKey
	 */
	public void setDataKey(String dataKey);

	/**
	 * Set the path to the file to be processed
	 * 
	 * @param filePath
	 */
	public void setFilePath(String filePath);
	
	/**
	 * Get the path to the file to be processed
	 * 
	 * @return filePath
	 */
	public String getFilePath();

	/**
	 * Set the absolute path the processed data should be saved as
	 * 
	 * @param outputFilePath
	 */
	public void setOutputFilePath(String outputFilePath);
	
	/**
	 * Get the absolute path the processed data should be saved as
	 * 
	 * @return outputPath
	 */
	public String getOutputFilePath();

	/**
	 * Set the path to the dataset, or NXData node, to be processed
	 * 
	 * @param datasetPath
	 */
	public void setDatasetPath(String datasetPath);

	/**
	 * Set a string representation of the slicing of the dataset
	 * 
	 * @param slicing
	 */
	public void setSlicing(String slicing);

	/**
	 * Set the path to the NeXus file containing the processing chain
	 * 
	 * @param processingPath
	 */
	public void setProcessingPath(String processingPath);

	/**
	 * Set axes names for each dimension of the dataset set
	 * 
	 * @param axesNames
	 */
	public void setAxesNames(List<String>[] axesNames);

	/**
	 * Set whether the processing chain file should be deleted when processing is complete
	 * 
	 * @param deleteProcessingFile
	 */
	public void setDeleteProcessingFile(boolean deleteProcessingFile);

	/**
	 * Set the Xmx value used for the processing runner
	 * 
	 * @param xmx
	 */
	public void setXmx(String xmx);

	/**
	 * Set which dimensions of the dataset are the detector dimensions
	 * 
	 * Not needed if the scan rank is set
	 * 
	 * @param dataDimensions
	 */
	public void setDataDimensions(int[] dataDimensions);
	
	/**
	 * Set the rank of the scan
	 * 
	 * Not needed if the data dimensions are set
	 * 
	 * @param scanRank
	 */
	public void setScanRank(Integer scanRank);
	
	/**
	 * Sets whether the processed file should be readable while being written to
	 * 
	 * @param readable
	 */
	public void setReadable(boolean readable);
	
	/**
	 * Set the name of the processing
	 * 
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Set the run directory for the logs
	 * @param runDirectory
	 */
	public void setRunDirectory(String runDirectory);

	/**
	 * Set the number of core to be used
	 * 
	 * @param numberOfCores
	 */
	public void setNumberOfCores(int numberOfCores);

	/**
	 * Set the timeOut for live processing in ms
	 * 
	 * @param timeOut
	 */
	default void setTimeOut(int timeOut) {};
	
	/**
	 * Set whether a link to the input data should be included in the processed
	 * 
	 * @param linkEntry
	 */
	default void setLinkParentEntry(boolean linkEntry){};

}