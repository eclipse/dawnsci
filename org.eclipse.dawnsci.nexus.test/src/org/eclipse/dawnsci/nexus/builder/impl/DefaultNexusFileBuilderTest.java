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
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
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

public class DefaultNexusFileBuilderTest {

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
		filePath = testScratchDirectoryName + fileName;
		fileInSubDirPath = testScratchDirectoryName +  "subdir/" + filePath; 
	}
	
	@Before
	public void setUp() {
		nexusFileBuilder = new DefaultNexusFileBuilder(filePath);
	}
	
	@Test
	public void testCreateAndOpenFile() throws NexusException {
		ServiceHolder.setNexusFileFactory(new NexusFileFactoryHDF5());
		NexusEntryBuilder nexusEntryBuilder = nexusFileBuilder.newEntry();
		nexusEntryBuilder.getNXentry().setTitleScalar("test");
		
		nexusFileBuilder.createFile();
		
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
		ServiceHolder.setNexusFileFactory(new NexusFileFactoryHDF5());
		NexusFileBuilder nexusSubdirFileBuilder = new DefaultNexusFileBuilder(fileInSubDirPath);
		NexusEntryBuilder nexusEntryBuilder = nexusSubdirFileBuilder.newEntry();

		nexusEntryBuilder.getNXentry().setTitleScalar("test");
		nexusSubdirFileBuilder.createFile();
		
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
	
	@Test
	public void testGetNodeFactory() {
		assertThat(nexusFileBuilder.getNodeFactory(), notNullValue(NexusNodeFactory.class));
	}
	
}
