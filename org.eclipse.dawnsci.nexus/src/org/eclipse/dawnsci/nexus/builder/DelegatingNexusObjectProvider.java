package org.eclipse.dawnsci.nexus.builder;

import java.util.function.Supplier;

import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusBaseClass;

public class DelegatingNexusObjectProvider<N extends NXobject> extends AbstractNexusObjectProvider<N> {

	private final Supplier<N> supplier; 
	
	public DelegatingNexusObjectProvider(String name, NexusBaseClass nexusBaseClass,
			Supplier<N> nexusObjectSupplier) {
		super(name, nexusBaseClass);
		this.supplier = nexusObjectSupplier;
	}

	@Override
	protected N createNexusObject() {
		return supplier.get();
	}

}
