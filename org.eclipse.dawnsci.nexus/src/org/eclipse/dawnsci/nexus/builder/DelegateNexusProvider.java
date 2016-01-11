package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.INexusDevice;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusScanInfo;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;


/**
 * Use this concrete class to make a given class implement INexusDevice properly.
 * 
 * @author Matthew Gerring
 *
 * @param <N>
 */
public class DelegateNexusProvider<N extends NXobject> extends AbstractNexusProvider<N> {
	
	private INexusDevice creator;
	private NexusScanInfo info;

	public DelegateNexusProvider(NexusBaseClass nexusBaseClass, NexusScanInfo info, INexusDevice creator) {
		super(nexusBaseClass);
		this.info    = info;
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, String dataNodeName, NexusBaseClass category, NexusScanInfo info, INexusDevice creator) {
		super(name, nexusBaseClass, dataNodeName, category);
		this.info    = info;
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, String dataNodeName, NexusScanInfo info, INexusDevice creator) {
		super(name, nexusBaseClass, dataNodeName);
		this.info    = info;
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, NexusScanInfo info, INexusDevice creator) {
		super(name, nexusBaseClass);
		this.info    = info;
		this.creator = creator;
	}

	@Override
	protected N doCreateNexusObject(NexusNodeFactory nodeFactory) {
		return creator.createNexusObject(nodeFactory, info);
	}

}
