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
