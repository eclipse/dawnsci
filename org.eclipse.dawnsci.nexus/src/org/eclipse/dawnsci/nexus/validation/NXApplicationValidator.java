package org.eclipse.dawnsci.nexus.validation;

import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;

/**
 * Defines the interface for NeXus application definition validators.
 * A class that implements this interface knows how to validate a tree of
 * nexus objects according to a particular application definition.
 */
public interface NXApplicationValidator {
	
	/**
	 * Validate the given nexus tree.
	 * @param root
	 * @throws NexusValidationException
	 */
	public void validate(final NXroot root) throws NexusValidationException;
	
	/**
	 * Validate the given nexus entry.
	 * @param entry
	 * @throws NexusValidationException
	 */
	public void validate(final NXentry entry) throws NexusValidationException;
	
	/**
	 * Validate the given nexus subentry.
	 * @param subentry
	 * @throws NexusValidationException
	 */
	public void validate(final NXsubentry subentry) throws NexusValidationException;

}
