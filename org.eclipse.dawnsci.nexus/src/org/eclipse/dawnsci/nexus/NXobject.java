/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;

/**
 * Base interface of all Nexus group nodes
 */
public interface NXobject extends GroupNode {

	/**
	 * Java {@link Class} object of the interface for this base class, e.g. {@link NXsample}.class. 
	 * @return name of Nexus class
	 */
	public Class<? extends NXobject> getNXclass();
	
	/**
	 * Enum constant from {@link NexusBaseClass} for this base class, e.g. {@link NexusBaseClass#NX_SAMPLE}.
	 * @return {@link NexusBaseClass} enum constant for this class
	 */
	public NexusBaseClass getNexusBaseClass();
	
	/**
	 * Returns a set containing the {@link NexusBaseClass} constants for the permitted child group types
	 * of this base class.
	 * @return {@link NexusBaseClass} constants for permitted child groups
	 */
	public Set<NexusBaseClass> getPermittedChildGroupClasses();
	
	/**
	 * Returns whether the given NeXus group object can be added as a child group
	 * to this base class instance according to the NXDL definition for this base class.
	 * @param nexusObject potential child nexus group object
	 * @return <code>true</code> if the given group object can be added as a child of this base
	 *    class instance, <code>false</code> otherwise
	 */
	public boolean canAddChild(NXobject nexusObject);
	
	/**
	 * Returns the child of this node of the given type with the given name.
	 * @param name of child
	 * @param nxClass class of child
	 * @return named child NXobject of given NeXus class or <code>null</code> if none
	 */
	public <N extends NXobject> N getChild(String name, Class<N> nxClass);
	
	/**
	 * Returns a map containing all the children of this node of the given class. The keys of the
	 * map are the names of the child nodes.
	 * @param nxClass class of children.
	 * @return map of children, key is child node's name
	 */
	public <N extends NXobject> Map<String, N> getChildren(Class<N> nxClass);

	/**
	 * Sets the dataset for the field with the given name
	 * @param name
	 * @param value
	 * @return the new data node, for convenience
	 */
	public DataNode setDataset(String name, IDataset value);

	/**
	 * Gets the dataset for the field with the given name, if it exists, otherwise <code>null</code>.
	 * Note that this method should only be used for small datasets, i.e. those set before the scan.
	 * To get the dataset for larger datasets (i.e. data acquired during the scan) use
	 * <code>getDataNode(name).getDataset()</code> which returns an {@link ILazyDataset}.
	 * @param name dataset name
	 * @return the dataset for the field with the given name, or <code>null</code> if the no such dataset exists
	 */
	public IDataset getDataset(String name);
	
	/**
	 * Creates and adds a new {@link ILazyWriteableDataset} to this group for the given field name,
	 * with the given rank (dimensionality) and of the given element class
	 * @param name field name
	 * @param rank rank
	 * @param clazz dataset element class
	 * @return new lazy writable dataset
	 */
	public ILazyWriteableDataset initializeLazyDataset(String name, int rank, Class<?> clazz);
	
	/**
	 * Creates and adds a new {@link ILazyWriteableDataset} to this group for the given field
	 * name with the given fixed shape and of the given element class
	 * @param name field name
	 * @param size the shape
	 * @param clazz dataset element class
	 * @return new lazy writable dataset
	 */
	public ILazyWriteableDataset initializeFixedSizeLazyDataset(String name, int[] shape, Class<?> clazz);

	/**
	 * Creates and adds a new {@link ILazyWriteableDataset} to this group for the given field name,
	 * with the given maximum shape and of the given element class
	 * @param name field name
	 * @param maxShape the maximum shape 
	 * @param clazz dataset element class
	 * @return new lazy writable dataset
	 */
	public ILazyWriteableDataset initializeLazyDataset(String name, int[] maxShape, Class<?> clazz);
	
	/**
	 * Creates and adds a new datanode to this group for the given field name and
	 * with the given dataset as its value. The given dataset may be either a
	 * {@link ILazyWriteableDataset} or an {@link IDataset}.
	 * @param name field name
	 * @param dataset dataset
	 * @return new data node
	 */
	public DataNode createDataNode(String name, ILazyDataset dataset);
	
	/**
	 * Adds an external link within the given name within this node to the node
	 * with the given path within the file with the given name.
	 * The external file need not exist at the time this method is invoked.
	 * @param name name of link within this group
	 * @param externalFileName name of external file to link to
	 * @param pathToNode path of node within external file to link to
	 */
	public void addExternalLink(String name, String externalFileName, String pathToNode);

	/**
	 * Returns the {@link ILazyWriteableDataset} for the field within this object with the given name,
	 * or <code>null</code> if no such field exists, or the dataset for this field is not a
	 * {@link ILazyWriteableDataset}
	 * @param name field name
	 * @return the {@link ILazyWriteableDataset} for the given field if it exists, otherwise <code>null</code>
	 */
	public ILazyWriteableDataset getLazyWritableDataset(String name);

	/**
	 * Sets the field with the given name to the given value.
	 * @param name field name
	 * @param value field value
	 * @return the newly created {@link DataNode}.
	 */
	public DataNode setField(String name, Object value);
	
	/**
	 * Set the value of the given attribute. If the first argument is not <code>null</code>
	 * then the attribute is set on the field or child group with this name
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @param attrValue attribute value
	 */
	public void setAttribute(String name, String attrName, Object attrValue);

	/**
	 * Gets the value of the given field as a date.
	 * @param name name of field
	 * @return the value of the given field as a date, <code>null</code> if
	 *   there is no field with the given name, or the value cannot be parsed as a date
	 * @throws IllegalArgumentException if the node with the given name is not a {@link DataNode}.
	 */
	public Date getDate(String name);

	/**
	 * Gets the value of the given field as a number.
	 * @param name name of field
	 * @return the value of the given field as a number, <code>null</code> if
	 *   there is no field with the given name
	 * @throws IllegalArgumentException if the node with the given name is not a {@link DataNode}.
	 */
	public Number getNumber(String name);

	/**
	 * Gets the value of the given field as a double.
	 * @param name name of field
	 * @return the value of the given field as a double, <code>null</code> if
	 *   there is no field with the given name
	 * @throws IllegalArgumentException if the node with the given name is not a {@link DataNode}.
	 */
	public double getDouble(String name);

	/**
	 * Gets the value of the given field as a long.
	 * @param name name of field
	 * @return the value of the given field as a long, <code>null</code> if
	 *   there is no field with the given name
	 * @throws IllegalArgumentException if the node with the given name is not a {@link DataNode}.
	 */
	public long getLong(String name);

	/**
	 * Gets the value of the given field as a boolean.
	 * @param name name of field
	 * @return the value of the given field as a boolean, <code>null</code> if
	 *   there is no field with the given name
	 * @throws IllegalArgumentException if the node with the given name is not a {@link DataNode}.
	 */
	public boolean getBoolean(String name);

	/**
	 * Gets the value of the given field as a string.
	 * @param name name of field
	 * @return the value of the given field as a string, <code>null</code> if
	 *   there is no field with the given name
	 * @throws IllegalArgumentException if the node with the given name is not a {@link DataNode}.
	 */
	public String getString(String name);

	/**
	 * Get the value of the given attribute as a date. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a date, or <code>null</code> if
	 *   no such attribute or value cannot be parsed as a date
	 */
	public Date getAttrDate(String name, String attrName);

	/**
	 * Get the value of the given attribute as a number. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a number
	 */
	public Number getAttrNumber(String name, String attrName);

	/**
	 * Get the value of the given attribute as a double. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a double
	 */
	public double getAttrDouble(String name, String attrName);

	/**
	 * Get the value of the given attribute as a long. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a long
	 */
	public long getAttrLong(String name, String attrName);

	/**
	 * Get the value of the given attribute as a boolean. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a long
	 */
	public boolean getAttrBoolean(String name, String attrName);

	/**
	 * Get the value of the given attribute as a string. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a long
	 */
	public String getAttrString(String name, String attrName);

	/**
	 * Get the value of the given attribute. If the first argument is
	 * not <code>null</code> then returns the value of attribute of the field
	 * or child group with that name.
	 * @param name name of node (if <code>null</code> then current group)
	 * @param attrName attribute name
	 * @return value of attribute as a long
	 */
	public Dataset getAttr(String name, String attrName);

	/**
	 * Add a child node with the given name. This method should be used with caution
	 * as it allows a child group to be added that may not be permitted by the NXDL
	 * base class definition for this base class. In preference, the relevant
	 * set method on the base class specific sub-interface of this interface
	 * should be used.
	 * @param name name of child group
	 * @param child child group
	 */
	public <N extends NXobject> void putChild(String name, N child);

	/**
	 * Adds the child nodes with the given names.
	 * This method should be used with caution as it allows a child group
	 * to be added that may not be permitted by the NXDL
	 * base class definition for this base class. In preference, the relevant
	 * set method on the base class specific sub-interface of this interface
	 * should be used.
	 * @param map map from names to child nodes to add
	 */
	public <N extends NXobject> void setChildren(Map<String, N> map);

	/**
	 * Returns
	 * @return
	 */
	public Map<String, Dataset> getAllDatasets();

}
