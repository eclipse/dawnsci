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
package org.eclipse.dawnsci.nexus.builder.impl;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileFactoryHDF5;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.ServiceHolder;
import org.eclipse.dawnsci.nexus.TestUtils;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusFileBuilder;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DefaultNexusFileBuilderTest {
	@Parameters
	public static Object[] data() {
		return new Object[] {true, false};
	}

	@Parameter
	public boolean async;

	private static final String fileName = "testFile.nx5";
	
	private static String testScratchDirectoryName;
	
	private static String filePath;
	
	private static String fileInSubDirPath;
	
	private NexusFileBuilder nexusFileBuilder;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(
				DefaultNexusFileBuilderTest.class.getSimpleName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
	}
	
	@Before
	public void setUp() {
		String s = async ? "Async" : "Sync";
		filePath = testScratchDirectoryName + s + fileName;
		fileInSubDirPath = testScratchDirectoryName +  "subdir/" + filePath; 
		nexusFileBuilder = new DefaultNexusFileBuilder(filePath);
	}
	
	@Test
	public void testCreateAndOpenFile() throws NexusException {
		new ServiceHolder().setNexusFileFactory(new NexusFileFactoryHDF5());
		NexusEntryBuilder nexusEntryBuilder = nexusFileBuilder.newEntry();
		nexusEntryBuilder.getNXentry().setTitleScalar("test");
		
		nexusFileBuilder.createFile(async);
		
		TreeFile nexusFile = NexusTestUtils.loadNexusFile(filePath, true);
		assertThat(nexusFile, notNullValue());
		NXroot root = (NXroot) nexusFile.getGroupNode();
		assertThat(nexusFile.getGroupNode(), notNullValue());
		NXentry entry = root.getEntry();
		assertThat(entry, notNullValue());
		assertThat(entry.getTitleScalar(), equalTo("test"));
	}
	
	@Test
	public void testCreateAndOpenFileInSubDir() throws NexusException {
		new ServiceHolder().setNexusFileFactory(new NexusFileFactoryHDF5());
		NexusFileBuilder nexusSubdirFileBuilder = new DefaultNexusFileBuilder(fileInSubDirPath);
		NexusEntryBuilder nexusEntryBuilder = nexusSubdirFileBuilder.newEntry();

		nexusEntryBuilder.getNXentry().setTitleScalar("test");
		nexusSubdirFileBuilder.createFile(async);
		
		TreeFile nexusFile = NexusTestUtils.loadNexusFile(fileInSubDirPath, true);
		assertThat(nexusFile, notNullValue());
		NXroot root = (NXroot) nexusFile.getGroupNode();
		assertThat(nexusFile.getGroupNode(), notNullValue());
		NXentry entry = root.getEntry();
		assertThat(entry, notNullValue());
		assertThat(entry.getTitleScalar(), equalTo("test"));
	}
	
	@Test
	public void testGetNexusTree() {
		TreeFile nexusFile = nexusFileBuilder.getNexusTree();
		assertThat(nexusFile, notNullValue());
		String expectedFilePath = System.getProperty("user.dir") + "/" + filePath;
		assertThat(nexusFile.getFilename(), equalTo(expectedFilePath));
	}
	
	@Test
	public void testGetNXroot() {
		assertThat(nexusFileBuilder.getNXroot(), Matchers.notNullValue(NXroot.class));
	}
	
	@Test
	public void testGetNewEntry() throws Exception {
		NXroot nxRoot = nexusFileBuilder.getNXroot();
		assertThat(nxRoot.getChildren(NXentry.class).keySet(), empty());

		NexusEntryBuilder entryBuilder = nexusFileBuilder.newEntry();
		assertThat(nxRoot.getChildren(NXentry.class).keySet(), hasSize(1));
		assertThat(entryBuilder.getNXentry(), sameInstance(nxRoot.getEntry()));
	}
	
	@Test
	public void testGetNewEntry_name() throws Exception {
		NXroot nxRoot = nexusFileBuilder.getNXroot();
		assertThat(nxRoot.getChildren(NXentry.class).keySet(), empty());

		NexusEntryBuilder entryBuilder = nexusFileBuilder.newEntry("myentry");
		assertThat(nxRoot.getChildren(NXentry.class).keySet(), hasSize(1));
		assertThat(entryBuilder.getNXentry(), notNullValue(NXentry.class));
		assertThat(entryBuilder.getNXentry(), sameInstance(nxRoot.getEntry("myentry")));
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidate() throws Exception {
		// verify that calling validate() on the NexusFileBuilder invokes
		// validate() on each of the child NexusEntryBuilder entities
		// Note: this method used to use Mockito to validate that each entry was validated,
		// however the plugin dependency on mockito appears to prevent plugin tests from running

		NexusEntryBuilder nexusEntryBuilder = nexusFileBuilder.newEntry("entry1");
		nexusEntryBuilder.newApplication(NexusApplicationDefinition.NX_TOMO);
//		nexusFileBuilder.newEntry("entry2");
		
		nexusFileBuilder.validate();
//		verify(entry1).validate();
//		verify(entry2).validate();
//		verifyNoMoreInteractions(entry1, entry2);
	}
}
