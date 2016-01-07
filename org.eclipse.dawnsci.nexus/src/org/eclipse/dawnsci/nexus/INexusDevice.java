package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.nexus.builder.AbstractNexusProvider;
import org.eclipse.dawnsci.nexus.builder.DelegateNexusProvider;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;

/**
 * Any device which can write NeXus should implement this method.
 * 
 * This can be done easily by using {@link AbstractNexusProvider} or
 * {@link DelegateNexusProvider}
 * 
 * @author Matthew Gerring
 *
 */
public interface INexusDevice {

	/**
	 * The object provider required for writing correct nexus files.
	 * @return
	 * @throws Exception
	 */
	<N extends NXobject> NexusObjectProvider<N> getNexusProvider() throws Exception;
	
	/**
	 * 
	 * @param nodeFactory
	 * @return
	 */
	<N extends NXobject> N createNexusObject(NexusNodeFactory nodeFactory);

}
