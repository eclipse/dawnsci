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

package org.eclipse.dawnsci.nexus.builder.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.ServiceHolder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusScanFile;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

/**
 * Default implementation of {@link NexusFileBuilder}.
 */
public class DefaultNexusFileBuilder implements NexusFileBuilder {

	private final NexusNodeFactory nexusNodeFactory = new NexusNodeFactory();

	private final TreeFile treeFile;

	private final NXroot nxRoot;
	
	private Map<String, NexusEntryBuilder> entries = new HashMap<>();
	
	private boolean fileCreated = false;

	/**
	 * Creates a new {@link DefaultNexusFileBuilder}.
	 * @param filePath
	 */
	public DefaultNexusFileBuilder(final String filePath) {
		treeFile = nexusNodeFactory.createTreeFile(filePath);
		nxRoot = nexusNodeFactory.createNXroot();
		
		// TODO: do we need to set any attributes on root?
		nxRoot.setAttributeFile_name(filePath);
		treeFile.setGroupNode(nxRoot);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#getNexusTree()
	 */
	@Override
	public TreeFile getNexusTree() {
		return treeFile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#getNodeFactory()
	 */
	@Override
	public NexusNodeFactory getNodeFactory() {
		return nexusNodeFactory;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#getNxRoot()
	 */
	@Override
	public NXroot getNXroot() {
		return nxRoot;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#newEntry()
	 */
	@Override
	public DefaultNexusEntryBuilder newEntry() throws NexusException {
		return newEntry("entry");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#newEntry(java.lang.String)
	 */
	@Override
	public DefaultNexusEntryBuilder newEntry(String entryName) throws NexusException {
		if (entries.containsKey(entryName)) {
			throw new NexusException("An entry with the name " + entryName + " already exists");
		}
		
		final NXentry entry = nexusNodeFactory.createNXentry();
		nxRoot.setEntry(entryName, entry);

		DefaultNexusEntryBuilder entryModel = new DefaultNexusEntryBuilder(nexusNodeFactory, entryName, entry);
		entries.put(entryName, entryModel);
		
		return entryModel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#validate()
	 */
	@Override
	public void validate() throws NexusValidationException {
		for (NexusEntryBuilder entry : entries.values()) {
			entry.validate();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.NexusFileBuilder#createFile()
	 */
	@Override
	public NexusScanFile createFile() throws NexusException {
		if (fileCreated) {
			throw new IllegalStateException("The Nexus file has already been created");
		}
		
		final String filename = treeFile.getFilename();
		
		// create the parent dir if it doesn't exist
		File parentDir = new File(filename).getParentFile();
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		
		// create and open the nexus file
		final INexusFileFactory nexusFileFactory = ServiceHolder.getNexusFileFactory();
		try (NexusFile nexusFile = nexusFileFactory.newNexusFile(filename, true)) {
			nexusFile.createAndOpenToWrite();
			// save the content of the TreeFile into the nexus file
			nexusFile.addNode("/", treeFile.getGroupNode());
			nexusFile.flush();
			fileCreated = true;
			return new DefaultNexusScanFile(filename);
		} // NexusFile is auto-closed
	}

}
