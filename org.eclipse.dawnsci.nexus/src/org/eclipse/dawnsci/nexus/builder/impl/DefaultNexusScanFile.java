package org.eclipse.dawnsci.nexus.builder.impl;

import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.builder.NexusScanFile;

class DefaultNexusScanFile implements NexusScanFile {

	private final NexusFile nexusFile;
	
	protected DefaultNexusScanFile(final NexusFile nexusFile) {
		this.nexusFile = nexusFile;
	}
	
	@Override
	public void openToWrite() throws NexusException {
		nexusFile.openToWrite(false); // must already have been created
		nexusFile.activateSwmrMode();
	}

	@Override
	public void flush() throws NexusException {
		nexusFile.flush();
	}

	@Override
	public void close() throws NexusException {
		nexusFile.flush();
		nexusFile.close();
	}

}
