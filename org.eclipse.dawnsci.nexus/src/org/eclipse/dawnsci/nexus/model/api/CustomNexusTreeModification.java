package org.eclipse.dawnsci.nexus.model.api;

import org.eclipse.dawnsci.nexus.NXentry;

/**
 * A custom modification to the nexus tree.
 *
 */
public interface CustomNexusTreeModification extends NexusTreeModification {
	
	/**
	 * Perform a custom modification to the entry.
	 * @param entry
	 */
	public void modifyEntry(NXentry entry);

}
