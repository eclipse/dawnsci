package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.INexusDevice;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.NexusScanInfo;

/**
 * Use this concrete class to make a given class implement INexusDevice
 * properly.
 * 
 * @author Matthew Gerring
 *
 * @param <N>
 */
public class DelegateNexusProvider<N extends NXobject> extends AbstractNexusObjectProvider<N> {

	private INexusDevice<N> creator;
	private NexusScanInfo info;

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, NexusScanInfo info,
			INexusDevice<N> creator) {
		super(name, nexusBaseClass);
		this.info = info;
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass,
			String defaultDataFieldName, NexusScanInfo info, INexusDevice<N> creator) {
		super(name, nexusBaseClass, defaultDataFieldName);
		this.info = info;
		this.creator = creator;
	}

	@Override
	protected N doCreateNexusObject(NexusNodeFactory nodeFactory) throws NexusException {
		return creator.createNexusObject(nodeFactory, info);
	}

}
