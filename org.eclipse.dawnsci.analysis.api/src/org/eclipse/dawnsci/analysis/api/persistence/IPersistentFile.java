/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.persistence;

import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.diffraction.IPowderCalibrationInfo;
import org.eclipse.dawnsci.analysis.api.fitting.functions.IFunction;
import org.eclipse.dawnsci.analysis.api.metadata.IDiffractionMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.OriginMetadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.processing.OperationData;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.dawnsci.analysis.api.roi.IROI;

/**
 * An IPersistent File is defined by what can be saved in an HDF5 file.<br>
 * In this case ROIs, masks, and images.<br>
 * <br>
 * The resulting tree structure of the HDF5 file saved will be the following:<br>
 * <pre>
 *     Entry name              |   Class        |    Description          
 * -----------------------------------------------------------------
 * entry                       |   Group        |                         
 * entry/data                  |   Group        |                         
 * entry/data/image            |   Dataset      |  64-bit floating-point  
 * entry/data/xaxis            |   Dataset      |  64-bit floating-point  
 * entry/data/yaxis            |   Dataset      |  64-bit floating-point  
 * entry/mask                  |   Group        |                         
 * entry/mask/mask0            |   Dataset      |  8-bit integer          
 * entry/mask/mask0/name       |   Attribute    |  String                 
 * entry/mask/mask1            |   Dataset      |  8-bit integer          
 * entry/mask/mask1/name       |   Attribute    |  String                 
 * entry/mask/...              |   ...          |  ...                    
 * entry/region                |   Dataset      |  8-bit integer          
 * entry/region/JSON           |   Attribute    |  String                 
 * entry/function              |   Dataset      |  64-bit floating-point  
 * entry/function/JSON         |   Attribute    |  String                 
 * entry/instrument/detector   |   Group        |                         
 * entry/process               |   Group        |                         
 * </pre>
 * The image can contain more than one Dataset with its corresponding axes.<br>
 * The region is an JSON serialized dataset. It can therefore contain more than one region.<br>
 * The entry/instrument/detector is an NX_detector group containing many datasets
 * required to build a IDiffractionMetadata object. <br>
 * 
 * Calling a setXXX(...) will write the data into the persistence file and will overwrite it
 * providing the data is repeated. It might add if the data is different, for instance adding
 * more regions with different names.
 * 
 * <br>
 * After using an IPersistentFile, the method close() needs to be called.
 * <code>
 * //Get file ref
 * try {
 *    // save some things
 * } finally {
 *    fileref.close();
 * }
 * </code>
 * <br>
 * 
 * @author Matthew Gerring
 *
 */
public interface IPersistentFile {

	/**
	 * Method to set a map of masks<br>
	 * This will write the data to entry/mask<br>
	 * If the masks already exist, they will be overwritten.<br>
	 * 
	 * @param masks
	 * @throws Exception 
	 */
	public void setMasks(Map<String, ? extends IDataset> masks) throws Exception;

	/**
	 * Method to add a mask to the current map of masks<br>
	 * If the mask already exist, it will be overwritten.<br>
	 * 
	 * @param name
	 *           the name of the mask (must be unique)
	 * @param mask
	 *           the mask value as a BooleanDataset
	 * @param mon
	 * @throws Exception
	 */
	public void addMask(String name, IDataset mask, IMonitor mon) throws Exception;

	/**
	 * Method to set a dataset: can be an image or a stack of images<br>
	 * This will write the data to entry/data<br>
	 * If the data already exist it will be overwritten.<br>
	 * 
	 * @param data
	 * @throws Exception 
	 */
	public void setData(IDataset data) throws Exception;
	
	/**
	 * Method to set datasets which persist history
	 * 
	 * @param data
	 * @throws Exception 
	 */
	public void setHistory(IDataset... data) throws Exception;

	/**
	 * Method to set datasets which persist history
	 * 
	 * @param mon
	 * @throws Exception 
	 */
	public Map<String, ILazyDataset> getHistory(IMonitor mon) throws Exception;

	/**
	 * Method to set the axes<br>
	 * This will write the data to entry/data<br>
	 * If the axes already exist, they will be overwritten.<br>
	 * 
	 * @param axes
	 * @throws Exception 
	 */
	public void setAxes(List<? extends IDataset> axes) throws Exception;

	/**
	 * Method to set a map of ROIs<br>
	 * This will write the data to entry/region<br>
	 * If the ROIs already exist, they will be overwritten.<br>
	 * 
	 * @param rois
	 * @throws Exception 
	 */
	public void setROIs(Map<String, IROI> rois) throws Exception;

	/**
	 * Method to add a ROI to the current map of ROIs<br>
	 * Not implemented yet.
	 * @param name
	 * @param roi
	 */
	public void addROI(String name, IROI roi) throws Exception ;

	/**
	 * Method to set the site
	 * @param site
	 * @throws Exception
	 */
	public void setSite(String site) throws Exception;

	/**
	 * Method that reads a ROI from entry/region<br>
	 * 
	 * @param roiName
	 * @return ROIBase
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public IROI getROI(String roiName) throws Exception;

	/**
	 * Method that reads a map of ROIs from entry/region<br>
	 * 
	 * @param mon
	 * @return Map
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public Map<String, IROI> getROIs(IMonitor mon) throws Exception;

	/**
	 * Method that returns an ILazyDataset. Could be an image or a stack of images.<br>
	 * This method reads from entry/data.<br>
	 * 
	 * @param dataName
	 * @param mon
	 * @return ILazyDataset
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public ILazyDataset getData(String dataName, IMonitor mon) throws Exception;

	/**
	 * Method that reads a List of axes from entry/data.<br>
	 * 
	 * @param xAxisName
	 * @param yAxisName
	 * @param mon
	 * @return List<ILazyDataset>
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public List<ILazyDataset> getAxes(String xAxisName, String yAxisName, IMonitor mon) throws Exception;

	/**
	 * Method that reads a map of all available masks from entry/mask.<br>
	 * 
	 * @param mon
	 * @return Map
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public Map<String, IDataset> getMasks(IMonitor mon) throws Exception;

	/**
	 * Method that reads a mask from entry/mask.<br>
	 * 
	 * @param maskName
	 * @param mon
	 * @return BooleanDataset
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public IDataset getMask(String maskName, IMonitor mon) throws Exception;

	/**
	 * Close the Hierarchical file<br>
	 * This method needs to be called after the saving / writing of the file is done.
	 */
	public void close();

	/**
	 * Method that returns the list of data names saved in the file.<br>
	 * Reads from entry/data<br>
	 * 
	 * @param mon
	 * @return List<String>
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public List<String> getDataNames(IMonitor mon) throws Exception;

	/**
	 * Method that returns the list of mask names saved in the file.<br>
	 * Reads from entry/mask<br>
	 * 
	 * @param  mon - may be null.
	 * @return List<String>
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public List<String> getMaskNames(IMonitor mon) throws Exception;

	/**
	 * Method that returns the list of roi names saved in the file.<br>
	 * Reads from entry/region.<br>
	 * 
	 * @param mon
	 * @return List<String>
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public List<String> getROINames(IMonitor mon) throws Exception;

	/**
	 * A method to test if a given ROIBase can be persisted using JSON
	 * @param roi
	 * @return true 
	 *            if this region can be saved in the persistence file.
	 * 
	 */
	public boolean isRegionSupported(IROI roi);
	
	/**
	 * Method to set a map of functions<br>
	 * This will write the data to entry/region<br>
	 * If the functions already exist, they will be overwritten.<br>
	 * 
	 * @param functions
	 * @throws Exception 
	 */
	public void setFunctions(Map<String, IFunction> functions) throws Exception;

	/**
	 * Method to add a function to the current map of functions<br>
	 * @param name
	 * @param function
	 */
	public void addFunction(String name, IFunction function) throws Exception ;

	/**
	 * Method that reads a function from entry/region<br>
	 * 
	 * @param functionName
	 * @return AFunction
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public IFunction getFunction(String functionName) throws Exception;

	/**
	 * Method that reads a map of functions from entry/region<br>
	 * 
	 * @param mon
	 * @return Map
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public Map<String, IFunction> getFunctions(IMonitor mon) throws Exception;
	
	/**
	 * Method that returns the list of function names saved in the file.<br>
	 * Reads from entry/data<br>
	 * 
	 * @param mon
	 * @return List<String>
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public List<String> getFunctionNames(IMonitor mon) throws Exception;

	/**
	 * Method to set diffraction metadata<br>
	 * This will write the data to entry/metadata<br>
	 * If the metadata already exists, they will be overwritten.<br>
	 * 
	 * @param metadata
	 * @throws Exception 
	 */
	public void setDiffractionMetadata(IDiffractionMetadata metadata) throws Exception;

	/**
	 * Method that returns Diffraction metadata.<br>
	 * This method reads from entry/diffraction_metadata.<br>
	 * 
	 * @param mon
	 * @return IDiffractionMetadata
	 * @throws Exception
	 *              is thrown if no correct entry is found in the file
	 */
	public IDiffractionMetadata getDiffractionMetadata(IMonitor mon) throws Exception;

	/**
	 * Method that returns the version the persistent file.<br>
	 * Saved as an attribute in the HDF5 file.
	 * @return String
	 * @throws Exception
	 */
	public String getVersion() throws Exception;

	/**
	 * Method that returns the site where the persistence API is being called.<br>
	 * Saved as an attribute in the persistence file.
	 * @return String
	 * @throws Exception
	 */
	public String getSite() throws Exception;

	/**
	 * Set attribute on region
	 * @param regionName - the region
	 * @param attributeName - JSON not allowed as name.
	 * @param value
	 */
	public void setRegionAttribute(String regionName, String attributeName, String value) throws Exception;

	
	/**
	 * Get attribute of a region
	 * @param regionName
	 * @param attributeName
	 * @return attribute
	 * @throws Exception
	 */
	public String getRegionAttribute(String regionName, String attributeName) throws Exception;

	/**
	 * Method that returns true if the HDF5 file contains a image entry. False otherwise.<br>
	 * @return a boolean
	 */
	public boolean containsData();
	
	/**
	 * Method that returns true if the HDF5 file contains a mask entry. False otherwise.<br>
	 * @return a boolean
	 */
	public boolean containsMask();
	
	/**
	 * Method that returns true if the HDF5 file contains a region entry. False otherwise.<br>
	 * @return a boolean
	 */
	public boolean containsRegion();
	
	/**
	 * Method that returns true if the HDF5 file contains a diffraction meta entry. False otherwise.<br>
	 * @return a boolean
	 */
	public boolean containsDiffractionMetadata();
	
	/**
	 * Method that returns true if the HDF5 file contains a function entry. False otherwise.<br>
	 * @return a boolean
	 */
	public boolean containsFunction();
	
	/**
	 * Method to store a powder calibration run in a NeXus file
	 * @param calibrationImage
	 * @param metadata
	 * @param info
	 * @throws Exception
	 */
	public void setPowderCalibrationInformation(IDataset calibrationImage, IDiffractionMetadata metadata, IPowderCalibrationInfo info) throws Exception;
	
	/**
	 * Method to store a series of operations in a NeXus file
	 * 
	 * Writes the operations when called.
	 * 
	 * @param operations - An array of operations
	 * @throws Exception
	 */
	public void setOperations(IOperation<? extends IOperationModel, ? extends OperationData>... operations) throws Exception;
	
	/**
	 * Method to read a series of operations from a Nexus file
	 * @return array of operations
	 * @throws Exception
	 */
	public IOperation<? extends IOperationModel, ? extends OperationData>[] getOperations() throws Exception;
	
	/**
	 * 
	 * @param origin
	 * @throws Exception
	 */
	public void setOperationDataOrigin(OriginMetadata origin) throws Exception;
	
	/**
	 * 
	 * @return data of data origin
	 * @throws Exception
	 */
	public OriginMetadata getOperationDataOrigin() throws Exception;

}
