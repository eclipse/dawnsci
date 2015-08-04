package org.eclipse.dawnsci.nexus.validation;

import org.eclipse.dawnsci.nexus.NXentry;

public interface NXApplicationValidator {
	
	public void validate(final NXentry rootEntry) throws NexusValidationException;

}
