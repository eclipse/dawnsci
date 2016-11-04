package org.eclipse.dawnsci.nexus;

import java.util.List;

import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;

/**
 * Any device which can provide multiple NeXus objects to be added to a NeXus tree.
 */
public interface IMultipleNexusDevice {
	
	/**
	 * Returns the object providers required for writing correct NeXus files.
	 * 
	 * @see INexusDevice#getNexusProvider(NexusScanInfo)
	 * 
	 * @param info information about the scan which can be useful when creating datasets,
	 * 		e.g. the scan rank, <code>info.getRank()</code>
	 * @return a list of nexus providers for the given scan role
	 * @throws NexusException if the nexus objects could not be created for any reason
	 */
	public List<NexusObjectProvider<?>> getNexusProviders(NexusScanInfo info) throws NexusException;
	
}
