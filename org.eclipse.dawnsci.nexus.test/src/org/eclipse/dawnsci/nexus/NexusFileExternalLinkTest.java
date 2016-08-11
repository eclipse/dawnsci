package org.eclipse.dawnsci.nexus;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.nexus.test.util.NexusTestUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.junit.After;
import org.junit.Before;

public class NexusFileExternalLinkTest extends AbstractNexusFileTestBase {

	private static final String FILE_NAME = "externalLink.nxs";
	
	private static final String EXTERNAL_FILE_NAME = "target.nxs";
	
	private IDataset linkedDataset;
	
	@Before
	public void setup() throws Exception {
		super.setUp();
		NexusTestUtils.deleteFile(EXTERNAL_FILE_NAME);
	}
	
	@After
	public void tearDown() throws Exception {
		NexusTestUtils.deleteFile(EXTERNAL_FILE_NAME);
		linkedDataset = null;
	}
	
	@Override
	protected String getFilename() {
		return FILE_NAME;
	}
	
	@Override
	protected NXroot createNXroot() {
		NXroot root = NexusNodeFactory.createNXroot();
		
		NXentry entry = NexusNodeFactory.createNXentry();
		root.setEntry(entry);
		
		NXinstrument instrument = NexusNodeFactory.createNXinstrument();
		entry.setInstrument(instrument);
		
		NXdetector detector = NexusNodeFactory.createNXdetector();
		detector.addExternalLink(NXdetector.NX_DATA, EXTERNAL_FILE_NAME, "/entry/data/data");
		instrument.setDetector(detector);
		
		return root;
	}
	
	@Override
	protected void checkNexusFile(TreeFile actualNexusTree, TreeFile loadedNexusTree) throws Exception {
//		super.checkNexusFile(actualNexusTree, loadedNexusTree);
	
		// check the constructed (i.e. pre-save) nexus tree
		NXroot root = (NXroot) actualNexusTree.getGroupNode();
		NXdetector detector = root.getEntry().getInstrument().getDetector();
		NodeLink nodeLink = detector.getNodeLink(NXdetector.NX_DATA);
		assertNotNull(nodeLink);
		SymbolicNode dest = (SymbolicNode) nodeLink.getDestination();
		// try to follow the link in the original file
		Node linkedNode = dest.getNode();
		assertNull(linkedNode); // cannot follow link to external file
		
		// check the loaded nexus tree
		root = (NXroot) loadedNexusTree.getGroupNode();
		detector = root.getEntry().getInstrument().getDetector();
		
		nodeLink = detector.getNodeLink(NXdetector.NX_DATA);
		assertNull(nodeLink);
		
		// cannot open external data node when external file does not exist
		DataNode dataNode = detector.getDataNode(NXdetector.NX_DATA);
		assertNull(dataNode); // the data node doesn't exist yet
		
		createExternalNexusFile();
		
		// data node still doesn't exist on this tree as it was created before
		// the external file was created
		dataNode = detector.getDataNode(NXdetector.NX_DATA);
		assertNull(dataNode);
		
		// reload the tree now that the external link exists
		TreeFile reloadedNexusTree = NexusTestUtils.loadNexusFile(filePath, true);
		NXroot root2 = (NXroot) reloadedNexusTree.getGroupNode();
		NXdetector detector2 = root2.getEntry().getInstrument().getDetector();
		DataNode dataNode2 = detector2.getDataNode(NXdetector.NX_DATA);
		assertNotNull(dataNode2);
		ILazyDataset dataset = dataNode2.getDataset();
		assertNotNull(dataset);
		assertEquals(dataset.getSize(), 10);
		assertEquals(dataset.getRank(), 2);
		assertArrayEquals(dataset.getShape(), linkedDataset.getShape());
		IDataset slice = dataset.getSlice();
		assertEquals(slice, linkedDataset);
	}

	private void createExternalNexusFile() throws NexusException {
		// create the external file
		TreeFile externalFileTree = NexusNodeFactory.createTreeFile(EXTERNAL_FILE_NAME);
		NXroot extRoot = NexusNodeFactory.createNXroot();
		externalFileTree.setGroupNode(extRoot);
		NXentry extEntry = NexusNodeFactory.createNXentry();
		extRoot.setEntry(extEntry);
		NXdata extData = NexusNodeFactory.createNXdata();
		extEntry.setData(extData);
		
		linkedDataset = DatasetFactory.createRange(10.0, Dataset.FLOAT64).reshape(2, 5);
		extData.setData(linkedDataset);
		
		NexusTestUtils.saveNexusFile(externalFileTree);
	}
	
}
