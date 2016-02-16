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
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.builder.DataDevice;
import org.eclipse.dawnsci.nexus.builder.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder;
import org.eclipse.dawnsci.nexus.builder.impl.AbstractNexusDataBuilder;

/**
 * A data builder, wrapping an {@link NXdata} base class instance, within an application definition where
 * the application definition has predetermined links for the fields within the NxData base class instance
 * to locations within the main {@link NXsubentry} base class instance.
 * <p>
 * This class should only be used where by a class that implements {@link NexusApplicationBuilder}
 * for an application definition where the NeXus application definition specifies links for the
 * locations. NXtomo is an example of this.
 * when its {@link NexusApplicationBuilder#newData()} method is invoked. It should then add
 * the appropriate links using the {@link #addLink(String, String)} method of this class.
 */
public class PredeterminedLinksApplicationDataBuilder extends AbstractNexusDataBuilder implements NexusDataBuilder {

	/**
	 * Creates a new {@link PredeterminedLinksApplicationDataBuilder} wrapping the given
	 * {@link NXdata}.
	 * @param nxData {@link NXdata} to wrap.
	 */
	public PredeterminedLinksApplicationDataBuilder(NXdata nxData) {
		super(null, nxData);
	}

	/**
	 * Adds a field to the wrapped {@link NXdata} linking to the given data node.
	 * @param name name of field with in the NXdata
	 * @param dataNode data node to link to
	 * @throws NexusException
	 */
	protected void addLink(final String name, final DataNode dataNode) throws NexusException {
		nxData.addDataNode(name, dataNode);
	}

	@Override
	public void setPrimaryDevice(DataDevice<?> primaryDeviceModel)
			throws NexusException {
		// this data model already has all the information it needs to be fully populated
		throw new UnsupportedOperationException("No additional objects are required for this data model");
	}

	@Override
	public void addDataDevice(NexusObjectProvider<?> dataDevice,
			Integer defaultAxisDimension, int... dimensionMappings)
			throws NexusException {
		// this data model already has all the information it needs to be fully populated
		throw new UnsupportedOperationException("No additional objects are required for this data model");
	}

	@Override
	public void addDataDevice(DataDevice<?> dataDeviceModel)
			throws NexusException {
		// this data model already has all the information it needs to be fully populated
		throw new UnsupportedOperationException("No additional objects are required for this data model");
	}

}
