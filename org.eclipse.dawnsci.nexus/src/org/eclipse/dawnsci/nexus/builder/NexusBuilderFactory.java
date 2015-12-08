package org.eclipse.dawnsci.nexus.builder;

/**
 * Factory for creating {@link NexusFileBuilder}s.
 */
public interface NexusBuilderFactory {
	
	/**
	 * Creates and returns a new {@link NexusFileBuilder} for building a NeXus file
	 * with the given path.
	 * @param filePath path of NeXus file
	 * @return new {@link NexusFileBuilder}
	 */
	public NexusFileBuilder newNexusFileBuilder(String filePath); 

}
