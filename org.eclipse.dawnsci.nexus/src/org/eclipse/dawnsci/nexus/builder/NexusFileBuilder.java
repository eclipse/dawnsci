/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.impl.NXrootImpl;
import org.eclipse.dawnsci.nexus.impl.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

/**
 * A builder for building a NeXus file. Wraps a {@link TreeFile} object.
 */
public interface NexusFileBuilder {
	
	/**
	 * Returns the {@link NexusNodeFactory} that can be used to create nodes. This
	 * factory object should be used to create new nodes so that each node has a sequential oid.
	 * @return wrapped node factory
	 */
	public NexusNodeFactory getNodeFactory();
	
	/**
	 * Returns the wrapped {@link TreeFile}.
	 * @return nexus tree
	 */
	public TreeFile getNexusTree();

	/**
	 * Returns the {@link NXrootImpl} object at the root of the NeXus object tree.
	 * @return the {@link NXrootImpl}
	 */
	public NXrootImpl getNXroot();

	/**
	 * Creates a new {@link NexusEntryBuilder} with the default name, "entry".
	 * @return new {@link NexusEntryBuilder}
	 * @throws NexusException if the entry could not be created for any reason
	 */
	public NexusEntryBuilder newEntry() throws NexusException;

	/**
	 * Creates a new {@link NexusEntryBuilder} with the given name.
	 * @param entryName entry name
	 * @return new {@link NexusEntryBuilder}
	 * @throws NexusException if the entry could not be created for any reason
	 */
	public NexusEntryBuilder newEntry(String entryName) throws NexusException;
	
	/**
	 * Validates all entries within the NeXus file.
	 * @throws NexusValidationException if one or more of the entries in the nexus file
	 *   are invalid.
	 */
	public void validate() throws NexusValidationException;

	/**
	 * Saves the nexus file.
	 * @throws NexusException if the nexus file could not be saved for any reason 
	 */
	public void saveFile() throws NexusException;

}
