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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;

/**
 * Base interface of all Nexus group nodes
 */
public interface NXobject extends GroupNode {

	/**
	 * @return name of Nexus class
	 */
	public Class<? extends NXobject> getNXclass();

	/**
	 * Returns the child of this node of the given type with the given name.
	 * @param name of child
	 * @param nxClass class of child
	 * @return named child NXobject of given Nexus class or <code>null</code> if none
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
	 * @param name
	 * @param rank
	 * @param dtype
	 * @return
	 */
	public ILazyWriteableDataset initializeLazyDataset(String name, int rank, int dtype);

	/**
	 * @param name
	 * @return
	 */
	public ILazyWriteableDataset getLazyWritableDataset(String name);

}
