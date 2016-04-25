package org.eclipse.dawnsci.nexus;

/**
 * Defines an interface for creating {@link NexusFile}s.
 *
 */
public interface INexusFileFactory {

	/**
	 * Create a new {@link NexusFile} with the given path. This can be used to read
	 * an existing NexusFile or to write a new file (overwriting any existing file).
	 * 
	 * @param path path
	 * @return new {@link NexusFile}
	 */
	public NexusFile newNexusFile(String path);
	
	/**
	 * Create a new {@link NexusFile} with the given path, optionally enabling SWMR
	 * (Single Write Multiple Read). This can be used to read
	 * an existing NexusFile or to write a new file (overwriting any existing file). 
	 * 
	 * @param path path
	 * @param enableSWMR <code>true</code> to enable SWMR, <code>false</code> otherwise
	 * @return new {@link NexusFile}
	 */
	public NexusFile newNexusFile(String path, boolean enableSWMR);
	
}
