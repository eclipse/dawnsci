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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * The conversion context which will drive what we are going to convert.
 */
public interface IConversionContext {

	/**
	 * Schemes to be edited as required, current list is a guess
	 * of the conversions we have spoken about before.
	 */
	public enum ConversionScheme {

		ASCII_FROM_1D(" ascii from 1D data",   true,  1), 
		ASCII_FROM_2D(" ascii from 2D data",   false, 2), 
		TIFF_FROM_3D(" image files from image stack", true, false, 2,3,4,5),
		AVI_FROM_3D(" video from image stack", true, 2,3,4,5),
		STITCHED_FROM_IMAGEDIR(" stitched image from directory of images", true, false, 1, 2),
		ALIGNED_FROM_3D(" align stack of images", true, false, 2, 3, 4, 5),
		H5_FROM_IMAGEDIR(" nexus stack from directory of images", true, false, 2),
		H5_FROM_1D(" nexus from 1D data",   true, false,  1),
		CUSTOM_NCD(" ascii from NCD data",     true, 1,2,3,4,5,6),
		CUSTOM_TOMO(" tiff from tomography nexus file(s) [nxtomo]",    true, 3),
		COMPARE(" compare data",    true,  false, 0,1,2,3,4,5),
		PROCESS(" process data", false, true, 1,2,3,4,5);
		
		private final String  uiLabel;
		private final int[]   preferredRanks;
		private final boolean userVisible;
		private final boolean nexusOnly;

		ConversionScheme(String uiLabel, boolean userVisible, int... preferredRanks) {
			this(uiLabel, userVisible, true, preferredRanks);
		}
		ConversionScheme(String uiLabel, boolean userVisible, boolean nexusOnly, int... preferredRanks) {
			this.uiLabel        = uiLabel;
			this.userVisible    = userVisible;
			this.nexusOnly      = nexusOnly;
			this.preferredRanks = preferredRanks;
		}

		public String getUiLabel() {
			return uiLabel;
		}
		
		public static ConversionScheme fromLabel(String uiLabel) {
			for (ConversionScheme cs : values()) {
				if (cs.getUiLabel().equals(uiLabel)) return cs;
			}
			return null;
		}

		/**
		 * The labels of the active user interface schemes.
		 * @return all labels
		 */
		public static String[] getLabels() {
			final List<String> labels = new ArrayList<String>(3);
			for (int i = 0; i < values().length; i++)  {
				if (values()[i].isUserVisible()) labels.add(values()[i].getUiLabel());
			}
			return labels.toArray(new String[labels.size()]);
		}

		/**
		 * The preferred dimensions of data sets likely to be chosen
		 * by this wizard, if null, there is no preference.
		 * @return preferred ranks
		 */
		public int[] getPreferredRanks() {
			return preferredRanks;
		}

		public boolean isRankSupported(int rank) {
			if (preferredRanks==null) return false;
			for (int i = 0; i < preferredRanks.length; i++) {
				if (preferredRanks[i]==rank) return true;
			}
			return false;
		}

		/**
		 * 
		 * @return true if scheme should appear in UI choices such as the
		 * conversion wizard.
		 */
		public boolean isUserVisible() {
			return userVisible;
		}
		public boolean isNexusOnly() {
			return nexusOnly;
		}
		
		public String getDescription() {
			final StringBuilder buf = new StringBuilder();
			buf.append("Conversion Name:\t");
			buf.append(uiLabel);
			buf.append("\n\n");
			
			buf.append("Data Source:\t");
			buf.append(isNexusOnly()?"HDF5 or Nexus files only":"Any loadable data of correct rank");
			buf.append("\n\n");
			
			buf.append("Supported Data Ranks:\t");
			buf.append(Arrays.toString(preferredRanks));
			buf.append("\n\n");
			
			return buf.toString();
		}
		
	}
	
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
	public ConversionScheme getConversionScheme();
	
	/**
	 * Set the way in which we will convert
	 * @param cs
	 */
	public void setConversionScheme(ConversionScheme cs);
	
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
