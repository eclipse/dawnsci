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

import java.util.Collection;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;

/**
 * Wraps an {@link NXentry} base class instance and provides methods to
 *
 */
public interface NexusEntryModel {

	public NXentry getNxEntry();
	
	public NexusNodeFactory getNodeFactory();
	
	/**
	 * Add the default groups to the entry. This method should be called before
	 * calling {@link #addNexusObject(NexusObjectProvider)}. 
	 */
	public void addDefaultGroups();
	
	public <N extends NXobject> N addNexusObject(NexusObjectProvider<N> nexusObjectProvider) throws NexusException;
	
	public List<NXobject> addNexusObjects(Collection<NexusObjectProvider<?>> nexusObjectProvider) throws NexusException;
	
	public void modifyTree(CustomNexusTreeModification modification) throws NexusException;
	
	public void addMetadata(NexusMetadataProvider metadataProvider) throws NexusException;
	
	public void addNexusTreeModification(NexusTreeModification modification) throws NexusException;
	
	public void addNexusTreeModifications(Collection<NexusTreeModification> modifications) throws NexusException;
	
	public NexusDataModel createDefaultData() throws NexusException;
	
	public NexusDataModel newData(String name) throws NexusException;
	
	public void setInstrumentName(String instrumentName);
	
	/**
	 * Make a custom modification to the nexus entry.
	 * @param modification
	 */
	public void modifyEntry(CustomNexusTreeModification modification) throws NexusException;

	public NexusApplicationDefinitionModel newApplicationDefinition(
			NexusApplicationDefinition appDefName) throws NexusException;
	
	/**
	 * Returns the data node with the given path relative to this 
	 * @param relativePath
	 * @return
	 * @throws NexusException
	 */
	public DataNode getDataNode(String relativePath) throws NexusException;
	
}
