package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeImpl;
import org.eclipse.dawnsci.nexus.impl.NXobjectFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractNexusFileTest {
	
	protected NXobjectFactory nxObjectFactory;
	
	@Before
	public void setUp() {
		nxObjectFactory = new NXobjectFactory();
	}
	
	@After
	public void tearDown() {
		nxObjectFactory = null;
	}
	
	protected abstract String getFilename();
	
	protected abstract NXroot createNXroot();
	
	private TreeFile createNexusTree() {
		final TreeFileImpl treeFile = nxObjectFactory.createTreeFile(getFilename());
		final NXroot root = createNXroot(); 
		treeFile.setGroupNode(root);
		
		return treeFile;
	}
	
	@Test
	public void testNexusFile() {
		TreeFile nexusTree = createNexusTree();
		
		saveFile(nexusTree);
		TreeFile loadedTree = loadFile();
		
		assertNexusTreesEqual(nexusTree, loadedTree);
	}
	
	private void saveFile(TreeFile nexusTree) {
		// TODO save file
	}
	
	private TreeFile loadFile() {
		final String filename = getFilename();
		// TODO load file
		return null;
	}
	
	private void assertNexusTreesEqual(TreeFile tree1, TreeFile tree2) {
		// TODO assert nexus trees equal
	}

}
