package org.eclipse.dawnsci.nexus;

/**
 * Defines an interface for creating {@link NexusFile}s.
 *
 */
public interface INexusFileFactory {

	/**
	 * Create a new {@link NexusFile} with the given path
	 * (overwriting any existing one).
	 * @param path path
	 * @return new {@link NexusFile}
	 */
	public NexusFile newNexusFile(String path);
	
	/**
	 * Create a new {@link NexusFile} with the given path
	 * (overwriting any existing one), optionally
	 * enabling SWMR (Single Write Multiple Read).
	 * @param path path
	 * @param enableSWMR <code>true</code> to enable SWMR, <code>false</code> otherwise
	 * @return new {@link NexusFile}
	 */
	public NexusFile newNexusFile(String path, boolean enableSWMR);
	
}
