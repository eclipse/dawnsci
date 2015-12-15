package org.eclipse.dawnsci.nexus.test.util;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileFactoryHDF5;
import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.NexusUtils;

public class NexusTestUtils {

	private static INexusFileFactory nexusFileFactory = new NexusFileFactoryHDF5();
	
	private static INexusFileFactory getNexusFileFactory() {
		return nexusFileFactory;
	}
	
	/**
	 * Create a new Nexus file (overwriting any existing one)
	 * @param path
	 * @return Nexus file
	 * @throws NexusException
	 */
	public static NexusFile createNexusFile(String path) throws NexusException {
		return createNexusFile(path, false);
	}

	/**
	 * Create a new Nexus file (overwriting any existing one)
	 * @param path
	 * @param enableSWMR
	 * @return Nexus file
	 * @throws NexusException
	 */
	public static NexusFile createNexusFile(String path, boolean enableSWMR) throws NexusException {
		NexusFile file = getNexusFileFactory().newNexusFile(path, enableSWMR);
		file.createAndOpenToWrite();
		return file;
	}
	
	/**
	 * Save the NeXus tree with the given root node to the given path.
	 * <p>
	 * Use this method when the NeXus tree has been created using the NeXus base classes (subinterfaces of {@link NXobject}), rather than using
	 * {@link NexusFile} to create the nodes.
	 * @param filePath
	 *            file path
	 * @param rootNode
	 *            root node
	 *
	 * @throws NexusException
	 */
	public static void saveNexusFile(String filePath, NXroot rootNode) throws NexusException {
		final TreeFileImpl treeFile = new TreeFileImpl(filePath.hashCode(), filePath);
		treeFile.setGroupNode(rootNode);
		saveNexusFile(treeFile);
	}

	/**
	 * Save the given NeXus file tree.
	 * <p>
	 * Use this method when the NeXus tree has been created using the NeXus base classes (subinterfaces of {@link NXobject}), rather than using
	 * {@link NexusFile} to create the nodes.
	 *
	 * @param nexusTree
	 *            nexus tree
	 * @throws NexusException
	 */
	public static void saveNexusFile(TreeFile nexusTree) throws NexusException {
		try (NexusFile nexusFile = createNexusFile(nexusTree.getFilename())) {
			nexusFile.addNode("/", nexusTree.getGroupNode());
			nexusFile.flush();
		}
	}
	
	/**
	 * Open an existing Nexus file to modify
	 * @param path
	 * @return Nexus file
	 * @throws NexusException
	 */
	public static NexusFile openNexusFile(String path) throws NexusException {
		NexusFile file = getNexusFileFactory().newNexusFile(path);
		file.openToWrite(false);
		return file;
	}

	/**
	 * Open an existing Nexus file to read only
	 * @param path
	 * @return Nexus file
	 * @throws NexusException
	 */
	public static NexusFile openNexusFileReadOnly(String path) throws NexusException {
		NexusFile file = getNexusFileFactory().newNexusFile(path);
		file.openToRead();
		return file;
	}

	/**
	 * Loads the nexus file with the given path.
	 * @param filePath
	 * @param readOnly
	 * @return
	 * @throws NexusException
	 */
	public static TreeFile loadNexusFile(String filePath, boolean readOnly) throws NexusException {
		try (NexusFile nexusFile = readOnly ? openNexusFileReadOnly(filePath) : openNexusFile(filePath)) {
			return NexusUtils.loadNexusTree(nexusFile);
		}
	}

}
