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

import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationFactory;

/**
 * Factory class for application definition subentries.
 *
 */
public class DefaultApplicationFactory implements NexusApplicationFactory {
	
	private static final DefaultApplicationFactory INSTANCE = new DefaultApplicationFactory();

	private DefaultApplicationFactory() {
		// private constructor to prevent external instantiation
	}
	
	/**
	 * Returns the singleton instance of this class.
	 * @return singleton instance of this class
	 */
	public static DefaultApplicationFactory getApplicationDefinitionFactory() {
		return INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationFactory#newApplicationDefinitionModel(org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder, org.eclipse.dawnsci.nexus.NexusApplicationDefinition)
	 */
	@Override
	public NexusApplicationBuilder newApplicationDefinitionModel(NexusEntryBuilder entryModel,
			NexusApplicationDefinition appDef, String subentryName) throws NexusException {
		NexusNodeFactory nodeFactory = entryModel.getNodeFactory();
		NXsubentry nxSubentry = nodeFactory.createNXsubentry();
		NexusApplicationBuilder appDefModel = null;
		switch (appDef) {
		case NX_TOMO:
			appDefModel = new TomoApplicationBuilder(entryModel, nxSubentry);
			break;
		default:
			throw new NexusException("Unsupported application definition: " + appDef);
		}

		final NXentry nxEntry = entryModel.getNXentry();
		if (nxEntry.containsGroupNode(subentryName)) {
			throw new NexusException("A subentry with the name " + subentryName + " already exists in this entry.");
		}
		
		nxEntry.setSubentry(subentryName, nxSubentry);
		
		return appDefModel;
	}

}
