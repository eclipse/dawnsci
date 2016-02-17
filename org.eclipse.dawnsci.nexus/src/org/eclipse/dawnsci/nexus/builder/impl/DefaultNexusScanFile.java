package org.eclipse.dawnsci.nexus.builder.impl;

import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.ServiceHolder;
import org.eclipse.dawnsci.nexus.builder.NexusScanFile;

class DefaultNexusScanFile implements NexusScanFile {

	private final String filePath;
	
	private NexusFile nexusFile = null;
	
	protected DefaultNexusScanFile(final String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void openToWrite() throws NexusException {
		if (nexusFile != null) {
			throw new IllegalStateException("NexusFile is already open.");
		}
		final INexusFileFactory nexusFileFactory = ServiceHolder.getNexusFileFactory();
		nexusFile = nexusFileFactory.newNexusFile(filePath, true);
		nexusFile.openToWrite(false); // file must already exist on disk
		nexusFile.activateSwmrMode();
	}

	@Override
	public void flush() throws NexusException {
		if (nexusFile == null) {
			throw new IllegalStateException("NexusFile has not been opened.");
		}
		
		nexusFile.flush();
	}

	@Override
	public void close() throws NexusException {
		if (nexusFile == null) {
			throw new IllegalStateException("NexusFile has not been opened.");
		}
		nexusFile.flush();
		nexusFile.close();
		nexusFile = null;
	}

}
