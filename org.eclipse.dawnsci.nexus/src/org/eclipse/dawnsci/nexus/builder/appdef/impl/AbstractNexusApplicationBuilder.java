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

package org.eclipse.dawnsci.nexus.builder.appdef.impl;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder;

/**
 * Abstract superclass of NeXus application builders.
 */
public abstract class AbstractNexusApplicationBuilder implements
		NexusApplicationBuilder {
	
	protected final NexusEntryBuilder nexusEntryModel;
	
	protected final NXsubentry subentry;
	
	/**
	 * Creates a new nexus application definition.
	 * @param appDef
	 * @param nexusEntryModel
	 * @param subentry
	 */
	public AbstractNexusApplicationBuilder(final NexusApplicationDefinition appDef, 
			final NexusEntryBuilder nexusEntryModel, final NXsubentry subentry) {
		this.nexusEntryModel = nexusEntryModel;
		this.subentry = subentry;

		subentry.setDefinitionScalar(getApplicationDefinitionName(appDef));
	}
	
	/**
	 * Returns the application definition name
	 * @param applicationDefinition
	 * @return
	 */
	protected String getApplicationDefinitionName(NexusApplicationDefinition applicationDefinition) {
		final String appDefEnumName = applicationDefinition.name();
		if (!appDefEnumName.startsWith("NX_")) {
			// sanity check that app def begins with 'NX_' - highly unlikely
			throw new RuntimeException("Application definition name doesn't begin with NX_");
		}
		
		return appDefEnumName.substring(0, 2) + appDefEnumName.substring(3);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder#getNXsubentry()
	 */
	@Override
	public NXsubentry getNXsubentry() {
		return subentry;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.model.api.NexusApplicationDefinitionModel#getDataNode(java.lang.String)
	 */
	@Override
	public DataNode getDataNode(String relativePath) throws NexusException {
		NodeLink nodeLink = subentry.findNodeLink(relativePath);
		if (nodeLink == null) {
			throw new NexusException("Cannot find expected data node within the subentry with relative path: " + relativePath);
		}
		if (!nodeLink.isDestinationData()) {
			throw new NexusException("Node found was not a data node, relative path within the subentry: " + relativePath);
		}

		return (DataNode) nodeLink.getDestination();
	}
	
}
