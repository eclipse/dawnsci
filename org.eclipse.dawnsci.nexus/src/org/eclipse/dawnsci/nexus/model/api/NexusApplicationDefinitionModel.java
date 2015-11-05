/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.model.api;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXsubentry;

/**
 * A interface for objects that wrap a {@link NXsubentry} for a Nexus application definition.
 */
public interface NexusApplicationDefinitionModel {

	/**
	 * Returns the wrapped {@link NXsubentry}. This can be used to make custom modifications. 
	 * @return the wrapped {@link NXsubentry}
	 */
	public NXsubentry getNXsubentry();

	/**
	 * Adds the default groups to the subentry. This method should be called before
	 * calling {@link #addNexusObject(NexusObjectProvider)}. 
	 */
	public void addDefaultGroups();

	/**
	 * @param nexusAdapter
	 * @return
	 * @throws NexusException
	 */
	public <N extends NXobject> N addNexusObject(NexusObjectProvider<N> nexusAdapter) throws NexusException;

	/**
	 * Creates a new {@link NexusDataModel} child of this application definition. If the application definition defines links from fields within this data node
	 * to the subentry for the application definition, these will be automatically added when this method is invoked.
	 *
	 * @return nexus data model
	 * @throws NexusException
	 */
	public NexusDataModel newData() throws NexusException;

	/**
	 * Returns the data node with the given path relative to the {@link NXsubentry} for
	 * this application definition.
	 * @param relativePath
	 * @return data node
	 * @throws NexusException
	 */
	public DataNode getDataNode(String relativePath) throws NexusException;

	/**
	 * Validates this NeXus application definition model according to the NXDL application definition.
	 * @throws NexusException
	 */
	public void validate() throws NexusException;
	

}
