package org.eclipse.dawnsci.nexus.builder.impl;

import org.eclipse.dawnsci.nexus.builder.NexusBuilderFactory;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;

public class DefaultNexusBuilderFactory implements NexusBuilderFactory {

	@Override
	public NexusFileBuilder newNexusFileBuilder(String filePath) {
		return new DefaultNexusFileBuilder(filePath);
	}

}
