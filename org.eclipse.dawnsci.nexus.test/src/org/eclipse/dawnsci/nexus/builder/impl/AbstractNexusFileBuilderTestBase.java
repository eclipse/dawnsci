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

import static org.eclipse.dawnsci.nexus.test.util.NexusAssert.assertNexusTreesEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileFactoryHDF5;
import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.ServiceHolder;
import org.eclipse.dawnsci.nexus.TestUtils;
import org.eclipse.dawnsci.nexus.builder.NexusBuilderFactory;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryModification;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusScanFile;
import org.eclipse.dawnsci.nexus.builder.impl.DefaultNexusBuilderFactory;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.eclipse.january.dataset.ILazyDataset;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractNexusFileBuilderTestBase {
	
	public static final String TEST_FILE_FOLDER = "testfiles/dawnsci/data/nexus/";

	private static String testScratchDirectoryName;
	
	private String filePath;
	
	private String comparisonFilePath;
	
	private boolean pluginTest = false;
	
	private NexusBuilderFactory nexusBuilderFactory = null;
	
	@Before
	public void setUp() throws Exception {
		nexusBuilderFactory = getNexusBuilderFactory();
		final String testClassName = getTestClassName();
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(testClassName);
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
		filePath = testScratchDirectoryName + getFilename();
		comparisonFilePath = TEST_FILE_FOLDER + getFilename();
		ServiceHolder.setNexusFileFactory(new NexusFileFactoryHDF5());
	}
	
	protected NexusBuilderFactory getNexusBuilderFactory() {
		return new DefaultNexusBuilderFactory(); // overridden by plugin tests
	}
	
	protected INexusFileFactory getNexusFileFactory() {
		return new NexusFileFactoryHDF5(); // overridden by plugin tests
	}

	protected abstract String getTestClassName();
	
	public void setPluginTest(boolean pluginTest) {
		this.pluginTest = pluginTest;
	}
	
	public boolean isPluginTest() {
		return pluginTest;
	}
	
	protected abstract String getFilename();
	
	protected void checkNexusBuilderFactory(NexusBuilderFactory nexusBuilderFactory) {
		// do nothing, subclasses may override
	}
	
	@Test
	public void testBuildNexusFile() throws Exception {
		checkNexusBuilderFactory(nexusBuilderFactory);
		final NexusFileBuilder fileBuilder = nexusBuilderFactory.newNexusFileBuilder(filePath);
		final NexusEntryBuilder entryBuilder = fileBuilder.newEntry();
		entryBuilder.addDefaultGroups();
		List<NexusEntryModification> treeModifications = getNexusTreeModifications();
		entryBuilder.modifyEntry(treeModifications);
		configureEntryModel(entryBuilder);
		
		addDataBuilder(entryBuilder);
		addApplicationDefinitions(entryBuilder);
		
		// save the nexus file
		NexusScanFile scanFile = fileBuilder.createFile();
		scanFile.openToWrite();
		
		// compare with file in repository
		final TreeFile actualNexusTree = fileBuilder.getNexusTree();
		TreeFile expectedNexusTree = NexusTestUtils.loadNexusFile(comparisonFilePath, true);
		assertNexusTreesEqual(expectedNexusTree, actualNexusTree);
		
		scanFile.close();
	}
	
	protected void configureEntryModel(NexusEntryBuilder nexusEntryModel) throws NexusException {
		// do nothing, subclasses may override
	}
	
	protected void addApplicationDefinitions(NexusEntryBuilder nexusEntryModel) throws NexusException {
		// do nothing, subclasses may override
	}
	
	protected abstract List<NexusEntryModification> getNexusTreeModifications();
	
	protected abstract void addDataBuilder(NexusEntryBuilder entryBuilder) throws NexusException;
	
	protected void assertNumChildNodes(NXobject parentNode, int numGroupNodes, int numDataNodes) {
		assertEquals(numGroupNodes, parentNode.getNumberOfGroupNodes());
		assertEquals(numDataNodes, parentNode.getNumberOfDataNodes());
	}

	protected ILazyDataset getDataset(NXobject group, String name,
			boolean loadedFromDisk) {
		if (loadedFromDisk) {
			return group.getDataset(name);
		}
		
		return group.getLazyWritableDataset(name);
	}
	
	protected void checkWriteableDataset(NXobject group, String name,
			int expectedRank, Class<?> expectedElementClass, boolean loadedFromDisk) {
		ILazyDataset dataset = getDataset(group, name, loadedFromDisk);
		assertNotNull(dataset);
		assertEquals(expectedRank, dataset.getRank());
		assertEquals(expectedElementClass, dataset.getElementClass());
	}

}
