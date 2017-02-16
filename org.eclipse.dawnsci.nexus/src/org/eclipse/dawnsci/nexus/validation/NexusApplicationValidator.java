/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.nexus.validation;

import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsubentry;

/**
 * Defines the interface for NeXus application definition validators.
 * A class that implements this interface knows how to validate a tree of
 * nexus objects according to a particular application definition.
 */
public interface NexusApplicationValidator {
	
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
