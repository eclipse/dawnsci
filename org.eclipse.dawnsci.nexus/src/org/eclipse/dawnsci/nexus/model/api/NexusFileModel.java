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

package org.eclipse.dawnsci.nexus.model.api;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXroot;

/**
 * A model of a NeXus file.
 */
public interface NexusFileModel {

	/**
	 * Saves the nexus file
	 * @throws NexusException 
	 */
	public void saveFile() throws NexusException;

	public TreeFile getNexusTree();

	public NXroot getNxRoot();

	public NexusEntryModel newEntry(); // uses defaultName: entry1, entry2, etc.

	public NexusEntryModel newEntry(String entryName);

}
