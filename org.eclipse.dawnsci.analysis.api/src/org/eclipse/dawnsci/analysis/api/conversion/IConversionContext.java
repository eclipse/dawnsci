/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.conversion;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.Slice;

/**
 * The conversion context which will drive what we are going to convert.
 */
public interface IConversionContext {

	/**
	 * 
	 * @return the monitor
	 */
	public IMonitor getMonitor();
	
	/**
	 * 
	 * @param monitor
	 */
	public void setMonitor(IMonitor monitor);
	
	/**
	 * Get the current conversion.
	 * @return the scheme
	 */
	public IConversionScheme getConversionScheme();
	
	/**
	 * Set the way in which we will convert
	 * @param cs
	 */
	public void setConversionScheme(IConversionScheme cs);
	
	/**
	 * Call to override the conversion scheme. If this 
	 */
	public void setConversionVisitor(IConversionVisitor visitor);

	/**
	 * Call to run the conversion. Process all files matching the filePathRegEx
	 */
	public IConversionVisitor getConversionVisitor();

	/**
	 * The file that we are converting
	 * @return list of file paths
	 */
	public List<String> getFilePaths();
	
	/**
	 * The dataset(s) we will process. Allows regular expressions inside
	 * each set.
	 * 
	 * @return regexp of data set name (using / as the path separator)
	 */
	public List<String> getDatasetNames();
	
	/**
	 * The dataset(s) we will process. Allows regular expressions inside
	 * each set.
	 */
	public void setDatasetNames(List<String> sets);
	
	/**
	 * The dataset(s) we will process. Allows regular expressions inside
	 * each set.
	 */
	public void setDatasetNames(String...    names);
	
	/**
	 * Instead of specifying a dataset name(s) to loop over
	 * one can specify an ILazyDataset to use instead. In this
	 * case the datasetName(s) will be ignored.
	 * 
	 * @return ILazyDataset
	 */
	public ILazyDataset getLazyDataset();
	
	/**
	 * Instead of specifying a dataset name(s) to loop over
	 * one can specify an ILazyDataset to use instead. In this
	 * case the datasetName(s) will be ignored.
	 * 
	 */
	public void setLazyDataset(ILazyDataset lazy);


	/**
	 * The dataset(s) we will process. Allows regular expressions.
	 * Sets the dataset names to a string list of size one.
	 *  
	 * @param datasetRegExp all Datasets (H5 definition) whose full path 
	 * matches this reg exp will be converted.
	 */
	public void setDatasetName(String datasetRegExp);
	
	/**
	 * 
	 * @return path export will process to.
	 */
	public String getOutputPath();
	
	/**
	 * Set the file path to output to.
	 * @param fileOrfolderPath
	 */
	public void setOutputPath(String fileOrfolderPath);
	
	/**
	 * 
	 * @param dim
	 * @param sliceString either an integer to hold the dimension constant or
	 * a range of the form "start:end" where start is the start index and end is
	 * the end index or the string "all" to use the size of the dataset (start=0,
	 * end-length dimension).
	 * 
	 * There should be only one range set in the slicing, one set to "all" or one
	 * set to "start:end" where start is the start index. Only one range can be
	 * processed for a given convert, other dimensions may be constant however.
	 */
	public void addSliceDimension(int dim, String sliceString);

	/**
	 * Set the dimensions in one go, overriding any previous addSliceDimension(...) calls
	 */
	public void setSliceDimensions(Map<Integer, String> dims);

	/**
	 * 
	 * @return the dimensions to slice in.
	 */
	public Map<Integer, String> getSliceDimensions();
	
	/**
	 * Get custom data which may be needed for a certain ConversionScheme
	 * @return the user object or null if not set.
	 */
	public Object getUserObject();
	
	/**
	 * Set custom data which may be needed for a certain ConversionScheme
	 */
	public void setUserObject(Object object);
	
	/**
	 * Attempts to create a new user object from a json string by deserializing it
	 * using the user object class for this context then asssigning the userObject field to the
	 * deserialization result.
	 * @param className - the className of the object type to be deserialised.
	 * @param json - the json string itself.
	 * @throws Exception if the json string cannot be deserialized.
	 */
	public void createUserObject(String className, String json) throws Exception;

	/**
	 * During converting many files the current active file being converted may be returned here.
	 * @return current conversion file, may be null.
	 */
	public File getSelectedConversionFile();
	
	/**
	 * Set the current active file to convert from. For instance when the conversion API loops over
	 * a directory of images, this path will be the current image file we are packing.
	 * 
	 * @param selectedConversionFile current conversion file, may be null.
	 */
	public void setSelectedConversionFile(File selectedConversionFile);
	
	/**
	 * During converting many data sets, the current dataset path being converted 
	 * may be retrieved here.
	 * @return current path being converted
	 */
	public String getSelectedH5Path();
	
	/**
	 * During converting many data sets, the current dataset path being converted 
	 * may be set here.
	 * @param h5Path
	 */
	public void setSelectedH5Path(String h5Path);
	
	/**
	 * get the dataset name to be used for an axis when saving 2d data as text
	 * @return.
	 */
	public String getAxisDatasetName();
	
	/**
	 * set the dataset name to be used for an axis when saving 2d data as text
	 * @param axisDatasetName
	 */
	public void setAxisDatasetName(String axisDatasetName);

	/**
	 * Amount of work to be done which will be 
	 * progressed if the task is run in a wizard.
	 * @return size of work intended
	 */
	public int getWorkSize();
	
	
	/**
	 * Amount of work to be done which will be 
	 * progressed if the task is run in a wizard.
	 */
	public void setWorkSize(int workSize);

	/**
	 * Gets whether the converted ILazyDataset is based on the result of an expression.
	 * @return true if data source is expression
	 */
	public boolean isExpression();
	
	
	/**
	 * Sets whether the converted ILazyDataset is based on the result of an expression.
	 */
	public void setExpression(boolean expression) ;
	
	/**
	 * Gets an array of slices describing where the current slice sits in the view of the dataset 
	 * @return array of slices
	 */
	public Slice[] getSelectedSlice();
	
	/**
	 * Set the current array of slices describing where the slice sits in the view of the dataset 
	 */
	public void setSelectedSlice(Slice[] slice);

	/**
	 * Gets the shape of the subset of the original dataset being analysed
	 * @return shape of selected data
	 */
	public int[] getSelectedShape();
	
	/**
	 * Set the shape of the current subset of the original dataset being analysed
	 */
	public void setSelectedShape(int[] shape);
	
	/**
	 * The names (nexus path to axis normally) of the axis by dimension
	 * number.
	 * 
	 * @return map of axes.
	 */
	public Map<Integer, String> getAxesNames();
	
	/**
	 *  Set the names (nexus path to axis normally) of the axis by dimension
	 * number.
	 */
	public void setAxesNames(Map<Integer, String> axesNames);

	/**
	 * Expert use: override the file paths before running the conversion.
	 * @param paths
	 */
	public void setFilePaths(String... paths)  throws Exception;
	
	/**
	 * Define if this context will send macro commands so that the
	 * user can copy what we are doing.
	 */
	public boolean isEchoMacro();
	
	/**
	 * Define if this context will send macro commands so that the
	 * user can copy what we are doing.
	 * @param echoMacro
	 */
	public void setEchoMacro(boolean echoMacro);

}
