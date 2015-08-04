package org.eclipse.dawnsci.nexus.validation;

import org.eclipse.dawnsci.hdf5.nexus.NexusException;

public class NexusValidationException extends NexusException {

	public NexusValidationException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
