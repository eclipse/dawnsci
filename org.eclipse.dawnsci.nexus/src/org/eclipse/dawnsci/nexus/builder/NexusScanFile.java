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
package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;

/**
 * Represents a NeXus file built using this framework. The structure
 * of the file has been created and cannot be further modified.
 * Writable datasets can to be written to the file.
 */
public interface NexusScanFile extends AutoCloseable {
	
	/**
	 * Opens the file to write. SWMR mode is also enabled.
	 * @throws NexusException if the file could not be opened for any reason
	 */
	public void openToWrite() throws NexusException;
	
	/**
	 * Flushes the underlying {@link NexusFile}.
	 * @throws NexusException if the nexus file could not be flushed for any reason
	 */
	public int flush() throws NexusException;
	
	/**
	 * Flushes and closes the underlying {@link NexusFile}.
	 * @throws NexusException if the nexus file could not be closed for an reason
	 */
	@Override
	public void close() throws NexusException;
	
}