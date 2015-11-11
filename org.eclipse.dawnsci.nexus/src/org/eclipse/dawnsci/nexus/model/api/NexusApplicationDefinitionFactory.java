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

import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;

/**
 * Factory interface for creating {@link NexusApplicationDefinitionModel}s, encapsulating
 * {@link NXsubentry} base class instances.
 */
public interface NexusApplicationDefinitionFactory {
	
	/**
	 * Creates and returns a new application definition model for the given
	 * {@link NexusApplicationDefinition} enum constant.
	 * @param parentEntryModel
	 * @return new {@link NexusApplicationDefinitionModel}
	 */
	public NexusApplicationDefinitionModel newApplicationDefinitionModel(
			NexusEntryModel parentEntryModel, NexusApplicationDefinition appDef);

}
