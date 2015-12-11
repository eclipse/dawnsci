package org.eclipse.dawnsci.nexus.validation;

import org.eclipse.dawnsci.nexus.NexusException;

public class NexusValidationException extends NexusException {

	private static final long serialVersionUID = 1L;

	public NexusValidationException(String message) {
		super(message);
	}
	
	public NexusValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
