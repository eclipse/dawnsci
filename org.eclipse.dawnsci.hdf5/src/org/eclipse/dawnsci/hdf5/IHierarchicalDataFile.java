/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5;

import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;


/**
 * Having this big interface here is not ideal but
 * is designed to avoid temptation of talking to objects
 * directly in the {@link HierarchicalDataFile} which 
 * are not thread safe.
 * 
 * @author gerring
 *
 */
public interface IHierarchicalDataFile extends AutoCloseable {

	public static final int NUMBER_ARRAY              = 17061;
	public static final int SCALAR                    = 17063;
	public static final int TEXT                      = 17062;
	/**
	 * Must be called to close the file.
	 * 
     * For historical reasons (close was written before AutoCloseable was
     * added) this method does not follow strongly encouragement not to throw
     * Exception. 
	 */
	public void close() throws Exception;
	
	/**
	 * @return true if the file has been closed in an unfriendly closeAll(...) call.
	 */
	public boolean isClosed();

	/**
	 * Get the root TreeNode
	 * @return
	 */
	public TreeNode getNode();
	
	/**
	 * Return the file path
	 * @return
	 */
	public String getPath();

	/**
	 * Return the root Group
	 * @return
	 */
	public String getRoot();

	/**
	 * Print the full file tree
	 * @throws Exception
	 */
	public void print() throws Exception;

	/**
	 * This call can also be used to get links out of the data by the path in the link file.
	 * @param fullPath
	 * @return an HObject e.g. Dataset or Group located at that path
	 * @throws Exception
	 * @Deprecated This method allows the API user to get ncsa objects directly.
	 */
	@Deprecated
	public Object getData(String fullPath) throws Exception;
	
	/**
	 * Return true if this path references a Dataset object. Other objects are Groups
	 * involved in structure, Datasets hold data.
	 * @param path
	 * @return
	 */
	public boolean isDataset(String path) throws Exception;

	/**
	 * Return true if this path references a Group object. Objects are Groups
	 * which hold in structure and Datasets which hold data.
	 * @param path
	 * @return
	 */
	public boolean isGroup(String path) throws Exception;

	/**
	 * Attempt to delete the HObject at this path and unlink any children.
	 * 
	 * @param fullPath
	 */
	//public void delete(String fullPath) throws Exception;
	
	/**
	 * Rename the path to have the newName and return the new full path
	 * @param path
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public String rename(String path, String newName) throws Exception;
	
	/**
	 * 
	 * @param path
	 * @return the parent of the node at this path
	 * @throws Exception
	 */
	public String getParent(final String path) throws Exception;
	
	/**
	 * The full attribute key is: <node full path>@<attribute name>
	 * e.g. /entry1/data@napimount
	 * @param fullAttributeKey
	 * @return
	 * @throws Exception
	 */
	public String getAttributeValue(String fullAttributeKey) throws Exception;

	/**
	 * Reads all the attributes for the given object and returns a Map of attribute
	 * name to attribute values. NOTE unlike {@link #getAttributeValues()} which is
	 * recursive, this method only gets the attribute for the given path.
	 * 
	 * @return map of attribute names (part after the @) to attribute values or 
	 * <code>null</code> if path does not exist.
	 */
	Map<String, Object> getAttributeValues(String fullPath);
	
	/**
	 * Reads all the attribute Lists from the object and puts it in a map of the full paths
	 * to the attributes using paths of the form: <node full path>@<attribute name>
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getAttributeValues();

	
	/**
	 * Extracts names, sizes and shapes in one pass.
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	public HierarchicalInfo getDatasetInformation(int dataType) throws Exception;


	/**
	 * dataType one of NUMBER_ARRAY or TEXT or one of the Datatype.CLASS_* variables.
	 * @return
	 * @throws Exception
	 */
	public List<String> getDatasetNames(int dataType)  throws Exception;

	/**
	 * @return map of dataset names with their sizes
	 * @throws Exception
	 */
	public Map<String, Integer> getDatasetSizes(int dataType)  throws Exception;

	/**
	 * @return map of dataset names with their shapes
	 * @throws Exception
	 */
	public Map<String, int[]> getDatasetShapes(int dataType)  throws Exception;

	/**
	 * Get a group, creating one if it does not exist.
	 * @param string
	 * @return full path to group
	 * @throws Exception
	 */
	public String group(String path) throws Exception;

	/**
	 * Get a group in this parent, creating one if it does not exist.
	 * @param string
	 * @return full path to group
	 * @throws Exception
	 */
	public String group(String name, final String parent) throws Exception;
	
	/**
	 * Does not set the attribute again if it is already set.
	 * @param object
	 * @param attribute one of the values defined in {@link Nexus}
	 * @throws Exception
	 */
	public void setNexusAttribute(final String objectPath, final String attribute) throws Exception;
	
	/**
	 * Set a string attribute. Creates attribute if necessary
	 * @param object
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public void setAttribute(final String objectPath, final String name, final String value) throws Exception;

	/**
	 * Set a string attribute
	 * @param object
	 * @param name
	 * @param value
	 * @param overwrite if true, write only if not already defined, else if false, leave alone if already defined as equal
	 * @throws Exception
	 */
	public void setAttribute(final String objectPath, final String name, final String value, boolean overwrite) throws Exception;
	
	/**
	 * Set an integer attribute on an HObject, useful for nexus signal and axis calls.
	 * Does not overwrite the value if it is already set.
	 * 
	 * @param entry
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public void setIntAttribute(final String entryPath, final String name, final int value) throws Exception;

	/**
	 * This method returns the dataset axes for a given signal node. The nexus path must be the path
	 * to the signal
	 * 
	 * @param signalPath
	 * @param dimension
	 * @return list of strings which represent the path to the dataset
	 */
	public List<String> getNexusAxesNames(String signalPath, int dimension) throws Exception;
	
	/**
	 * Creates a new String dataset with the given name and parent, the string passed in is the value.
	 * If it already exists then an integer will be appended to the name and it will still be written.
	 * 
	 * @param name
	 * @param value
	 * @param parentPath
	 * @return full path including dataset name
	 */
	public String createStringDataset(final String name, final String value, final String parentPath) throws Exception;

	/**
	 * Creates a new String dataset with the given name and parent, the string passed in is the value.
	 * If it already exists then an integer will be appended to the name and it will still be written.
	 * 
	 * @param name
	 * @param value
	 * @param parentPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String createStringDataset(final String name, final int size, final String parentPath) throws Exception;

	/**
	 * Creates and returns a new dataset with the given name and parent
	 * If it already exists then an integer will be appended to the name and it will still be written.
     *
	 * @param name
	 * @param shape
	 * @param buffer
	 * @param dataGroupPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String createDataset(final String name, IDataset data, final String dataGroupPath) throws Exception;

	/**
	 * Creates and returns a new dataset with the given name and parent
	 * If it already exists then an integer will be appended to the name and it will still be written.
     *
 	 * @param name
	 * @param dType
	 * @param dims
	 * @param buffer
	 * @param dataGroupPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String createDataset(final String name, final int dType, final long[] dims, final Object buffer, final String dataGroupPath) throws Exception;

	
	/**
	 * Creates and returns a new dataset with the given name and parent
	 * If it already exists then an integer will be appended to the name and it will still be written.
     *
	 * @param name
	 * @param data
	 * @param overwrite
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String createDataset(final String name, IDataset data, final String dataGroupPath, final boolean overwrite) throws Exception;

	/**
	 * Creates and returns a new String dataset with the given name and parent and the string value passed in.
	 * If it already exists then the dataset is overwritten
	 * 
	 * @param name
	 * @param value
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String replaceStringDataset(final String name, final String value, final String parent) throws Exception;

	/**
	 * Creates and returns a new dataset with the given name and parent
	 * If it already exists then the dataset is overwritten
     *
	 * @param name
	 * @param data
	 * @param dataGroupPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String replaceDataset(final String name, final IDataset data, final String dataGroupPath) throws Exception;

	/**
	 * Creates and returns a new dataset with the given name and parent
	 * If it already exists then the dataset is overwritten
	 * @param name
	 * @param dType
	 * @param dims
	 * @param buffer
	 * @param dataGroupPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String replaceDataset(final String name, final int dType, final long[] dims, final Object buffer, final String dataGroupPath) throws Exception;
	
	/**
	 * Method finds the given data set in the group and adds buffer to the end of the stack.
	 * 
	 * If the data set does not exist it is created with dimensions [bufferShape]
	 * 
	 * If the data set exists the first dimension is created and increased by one to accommodate it, for instance
	 * the second image in the stack would resize the data shape to [2, bufferShape...] and
	 * so forth.
	 * 
	 * A more efficient algorithm could be used than increasing by 1 if this proves slow.
	 * 
	 * @param datasetName
	 * @param data
	 * @param dataGroupPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String appendDataset(String datasetName, IDataset data, String dataGroupPath)  throws Exception;

	/**
	 * Method finds the given data set in the group and adds buffer to the end of the stack.
	 * 
	 * If the data set does not exist it is created with dimensions [bufferShape]
	 * 
	 * If the data set exists the first dimension is created and increased by one to accommodate it, for instance
	 * the second image in the stack would resize the data shape to [2, bufferShape...] and
	 * so forth.
	 * 
	 * A more efficient algorithm could be used than increasing by 1 if this proves slow.
	 * 
	 * @param datasetName
	 * @param dType
	 * @param shape
	 * @param buffer
	 * @param dataGroupPath
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String appendDataset(String datasetName, int dType, long[] shape, Object buffer, String dataGroupPath)  throws Exception;
	
	/**
	 * Creates a link to an existing object
	 * 
	 * @param targetGroup
	 * @param linkName
	 * @param sourceFullPath
	 * @return The link object, or null if sourcePath doesn't exist
	 * @throws Exception
	 */
	public String createLink(String targetGroup, String linkName, String sourceFullPath) throws Exception;

	/**
	 * Gets the size of a dimension of the dataset.
	 * @param datasetName
	 * @param dimension
	 * @return the size of a dimension of the dataset or -1 if a problem occurred
	 * @throws Exception
	 */
	public long getDimensionSize(String datasetName, int dimension) throws Exception;
	
	/**
	 * Method finds the given data set in the group and set a slice of data into it.
	 * 
	 * If the data set does not exist, it is created with dimensions [totalShape], and
	 * the data [buffer] is inserted in the positions designated in startStopStep
	 * 
	 * 
	 * @param name
	 * @param dtype
	 * @param buffer
	 * @param parent
	 * @param startStopStep
	 * @param totalShape
	 * @return full path including dataset name
	 * @throws Exception
	 */
	public String insertSlice(String name,  
					          final IDataset data,
					          final String   parent,
					          final long[][] startStopStep,
					          final long[] totalShape) throws Exception;


	/**
	 * List of members from a group.
	 * @param group
	 * @return
	 * @throws Exception
	 */
	public List<String> memberList(String group) throws Exception;

}
