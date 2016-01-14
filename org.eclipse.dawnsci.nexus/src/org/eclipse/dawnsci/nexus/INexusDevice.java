package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.nexus.builder.AbstractNexusProvider;
import org.eclipse.dawnsci.nexus.builder.DelegateNexusProvider;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;

/**
 * Any device which can write NeXus should implement this method.
 * 
 * This can be done easily by using {@link AbstractNexusProvider} or
 * {@link DelegateNexusProvider}
 * 
 * @author Matthew Gerring
 *
 */
public interface INexusDevice<N extends NXobject> {

	/**
	 * The object provider required for writing correct nexus files.
	 * @return
	 * @throws Exception
	 */
	NexusObjectProvider<N> getNexusProvider(NexusScanInfo info) ;
	
	/**
	 * 
	 * @param nodeFactory
	 * @return
	 */
	N createNexusObject(NexusNodeFactory nodeFactory, NexusScanInfo info);

}
