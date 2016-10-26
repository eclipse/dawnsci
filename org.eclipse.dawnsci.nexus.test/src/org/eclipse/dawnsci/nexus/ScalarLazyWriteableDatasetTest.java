package org.eclipse.dawnsci.nexus;

import static org.eclipse.dawnsci.nexus.test.util.NexusTestUtils.loadNexusFile;
import static org.eclipse.dawnsci.nexus.test.util.NexusTestUtils.saveNexusFile;
import static org.junit.Assert.assertEquals;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.SliceND;
import org.junit.Before;
import org.junit.Test;

public class ScalarLazyWriteableDatasetTest {
	
	private static final String FILE_NAME = "scalarlazywritabletest.nxs";
	
	protected static String testScratchDirectoryName;
	
	private ILazyWriteableDataset lazyScalarDataset;
	
	@Before
	public void setUp() throws Exception {
		testScratchDirectoryName = TestUtils.generateDirectorynameFromClassname(getClass().getCanonicalName());
		TestUtils.makeScratchDirectory(testScratchDirectoryName);
	}

	@Test
	public void testScalarLazyWritableDataset() throws Exception {
		// create the tree
		TreeFile createdNexusTree = createNexusTree();
		saveNexusFile(createdNexusTree);
		
		// write to the dataset
		double value = 123.456;
		writeToDataset(value);
		
		// load the nexus tree and check it has been written correctly
		TreeFile loadedTree = loadNexusFile(testScratchDirectoryName + FILE_NAME, true);
		checkTree(loadedTree, value);
	}
	
	protected TreeFile createNexusTree() {
		final TreeFileImpl treeFile = NexusNodeFactory.createTreeFile(testScratchDirectoryName + FILE_NAME);

		NXroot root = NexusNodeFactory.createNXroot();
		// add scalar attribute
		root.addAttribute(TreeFactory.createAttribute("sAttr", 1.5));
		DataNode d = TreeFactory.createDataNode(12345l);
		d.setDataset(DatasetFactory.createFromObject(-1.5));
		root.addDataNode("sData", d);
		treeFile.setGroupNode(root);
		NXentry entry = NexusNodeFactory.createNXentry();
		root.setEntry(entry);
		
		NXdata data = NexusNodeFactory.createNXdata();
		lazyScalarDataset = data.initializeLazyDataset(NXdata.NX_DATA, 0, Double.class);
		entry.setData(data);
		return treeFile;
	}
	
	private void writeToDataset(double value) throws Exception {
		SliceND slice = new SliceND(lazyScalarDataset.getShape(), lazyScalarDataset.getMaxShape(),
				new int[0], new int[0], null);
		lazyScalarDataset.setSlice(null, DatasetFactory.createFromObject(value), slice);
	}
	
	private void checkTree(TreeFile loadedTree, double expectedValue) throws Exception {
		NXroot root = (NXroot) loadedTree.getGroupNode();
		NXentry entry = root.getEntry();
		NXdata dataGroup = entry.getData();
		DataNode dataNode = dataGroup.getDataNode(NXdata.NX_DATA);
		ILazyDataset lazyDataset = dataNode.getDataset();
		IDataset slice = lazyDataset.getSlice();
		double actualValue = slice.getDouble();
		
		assertEquals(expectedValue, actualValue, 1e-15);
	}
}
