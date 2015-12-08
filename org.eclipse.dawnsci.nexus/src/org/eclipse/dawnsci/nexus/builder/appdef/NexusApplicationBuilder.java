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

package org.eclipse.dawnsci.nexus.builder.appdef;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.builder.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.impl.NXsubentryImpl;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

/**
 * A interface for building {@link NXsubentry} for a NeXus application.
 */
public interface NexusApplicationBuilder {

	/**
	 * Returns the wrapped {@link NXsubentry}. This can be used to make custom modifications. 
	 * @return the wrapped {@link NXsubentry}
	 */
	public NXsubentryImpl getNXsubentry();

	/**
	 * Adds the default groups to the subentry. This method should be called before
	 * any nexus objects are added to the entry
	 * 
	 * @throws NexusException if the default groups cannot be added for any reason
	 */
	public void addDefaultGroups() throws NexusException;

	/**
	 * Adds the nexus object provided by the given {@link NexusObjectProvider}.
	 * @param nexusObjectProvider
	 * @return the added nexus object
	 * @throws NexusException if the nexus objects cannot be added for any reason 
	 */
	public <N extends NXobject> N add(NexusObjectProvider<N> nexusObjectProvider) throws NexusException;

	/**
	 * Creates a new {@link NexusDataBuilder} child of this application. If the application definition defines links from fields within this data node
	 * to the subentry for the application definition, these will be automatically added when this method is invoked.
	 *
	 * @return nexus data model
	 * @throws NexusException if the new data cannot be created for any reason
	 */
	public NexusDataBuilder newData() throws NexusException;

	/**
	 * Returns the data node with the given path relative to the {@link NXsubentry} for
	 * this application definition.
	 * @param relativePath
	 * @return data node
	 * @throws NexusException
	 */
	public DataNode getDataNode(String relativePath) throws NexusException;

	/**
	 * Validates this NeXus application according to the NXDL application definition.
	 * @throws NexusValidationException if the application is invalid 
	 */
	public void validate() throws NexusValidationException;
	

}
