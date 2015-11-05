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

import java.util.Map;
import java.util.Set;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

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
	 */
	public abstract void setDataset(String name, IDataset value);

	/**
	 * Gets the dataset for the field with the given name, if it exists, otherwise <code>null</code>
	 * @param name
	 * @return the dataset for the field with the given name, or <code>null</code> if the no such dataset exists
	 */
	public abstract IDataset getDataset(String name);
	
	/**
	 * Creates and adds a new {@link ILazyWriteableDataset} to this group for the given field name,
	 * with the given rank (dimensionality) and of the given type (a constant from {@link Dataset}).
	 * @param name field name
	 * @param rank rank
	 * @param dtype data type constant (from {@link Dataset})
	 * @return new lazy writable dataset
	 */
	public ILazyWriteableDataset initializeLazyDataset(String name, int rank, int dtype);

	/**
	 * @param name
	 * @return
	 */
	public ILazyWriteableDataset getLazyWritableDataset(String name);

}
