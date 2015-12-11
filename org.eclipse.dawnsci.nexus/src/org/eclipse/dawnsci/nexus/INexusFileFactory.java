package org.eclipse.dawnsci.nexus;

public interface INexusFileFactory {

	public NexusFile createNexusFile(String path);

	public NexusFile createNexusFile(String path, boolean enableSWMR);
}
