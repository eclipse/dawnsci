package org.eclipse.dawnsci.nexus.validation;

import org.eclipse.dawnsci.nexus.NXroot;

public interface NXApplicationValidator {
	
	public void validate(final NXroot root) throws NexusValidationException, Exception;

}
