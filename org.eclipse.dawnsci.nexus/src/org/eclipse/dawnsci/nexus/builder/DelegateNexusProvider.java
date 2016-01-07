package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.INexusDevice;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
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

	public DelegateNexusProvider(NexusBaseClass nexusBaseClass, INexusDevice creator) {
		super(nexusBaseClass);
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, String dataNodeName, NexusBaseClass category, INexusDevice creator) {
		super(name, nexusBaseClass, dataNodeName, category);
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, String dataNodeName, INexusDevice creator) {
		super(name, nexusBaseClass, dataNodeName);
		this.creator = creator;
	}

	public DelegateNexusProvider(String name, NexusBaseClass nexusBaseClass, INexusDevice creator) {
		super(name, nexusBaseClass);
		this.creator = creator;
	}

	@Override
	protected N doCreateNexusObject(NexusNodeFactory nodeFactory) {
		return creator.createNexusObject(nodeFactory);
	}

}
