package org.eclipse.dawnsci.nexus.file;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.TestUtils;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class NexusFileRemoveNodeTest {

	private static String testScratchDirectoryName;
	private static String FILE_NAME;

	/**
	 * Creates an empty directory for use by test code.
	 *
	 * @throws Exception if setup fails
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(NexusFileTest.class.getCanonicalName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
		FILE_NAME = testScratchDirectoryName + "test.nxs";
	}

	private NexusFile setUpFile(boolean swmr) throws NexusException {
		String fileName = swmr ? "swmr_" + FILE_NAME : FILE_NAME;
		new File(fileName).delete();
		NexusFile nf = NexusTestUtils.createNexusFile(fileName, swmr);
		GroupNode g = nf.getGroup("/entry/blah", true);
		nf.createData(g, "heros", DatasetFactory.ones(2,3,4,5));
		if (swmr) {
			nf.activateSwmrMode();
		}
		return nf;
	}

	private void removeNode(boolean swmr) throws Exception {
		NexusFile nf = setUpFile(swmr);

		GroupNode g = nf.getGroup("/entry/blah", false);
		try {
			nf.removeNode(g, "zeros");
			fail("Should have thrown an IAE");
		} catch (IllegalArgumentException e) {
			
		}
		
		nf.removeNode(g, "heros");
		nf.createData(g, "zeros", DatasetFactory.zeros(5,4,3));
		nf.close();

		nf = NexusTestUtils.openNexusFileReadOnly(FILE_NAME);
		DataNode dn = nf.getData("/entry/blah/zeros");
		Dataset d = DatasetUtils.sliceAndConvertLazyDataset(dn.getDataset());
		assertNotNull(d);
		assertArrayEquals(new int[] {5, 4, 3}, d.getShapeRef());
		assertEquals(0, d.getDouble(), 1e-9);
	}

	@Test
	public void testNonSwmr() throws Exception {
		removeNode(false);
	}

	@Test(expected=NexusException.class)
	public void testSwmr() throws Exception {
		removeNode(true);
	}
}
