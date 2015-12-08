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

import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;

/**
 * Factory interface for creating {@link NexusApplicationBuilder}s, encapsulating
 * {@link NXsubentry} base class instances.
 * Implementations of {@link NexusEntryBuilder#newApplication(NexusApplicationDefinition)}
 * should use a factory implementing this interface to create the
 * nexus application definition.
 */
public interface NexusApplicationFactory {
	
	/**
	 * Creates and returns a new application definition model for the given
	 * {@link NexusApplicationDefinition} enum constant.
	 * @param parentEntryModel
	 * @param subentryName name of the subentry
	 * @return new {@link NexusApplicationBuilder}
	 * @throws NexusException if the application definition cannot be created for any reason
	 */
	public NexusApplicationBuilder newApplicationDefinitionModel(
			NexusEntryBuilder parentEntryModel, NexusApplicationDefinition appDef, String subentryName) throws NexusException;

}
