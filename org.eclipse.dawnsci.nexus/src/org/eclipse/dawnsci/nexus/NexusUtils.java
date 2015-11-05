/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */


package org.eclipse.dawnsci.nexus;


import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyWriteableDataset;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.hdf5.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.hdf5.NexusFileHDF5;

/**
 * Utility methods for dealing with NeXus files.
 */
public class NexusUtils {

	/**
	 * Create a (top-level) NeXus augmented path
	 * @param name
	 * @param nxClass
	 * @return augmented path
	 */
	public static String createAugmentPath(String name, String nxClass) {
		StringBuilder b = new StringBuilder();
		if (!name.startsWith(Tree.ROOT))
			b.append(Tree.ROOT);
		return addToAugmentPath(b, name, nxClass).toString();
	}

	/**
	 * Add to a NeXus augmented path
	 * @param path
	 * @param name
	 * @param nxClass
	 * @return augmented path
	 */
	public static String addToAugmentPath(String path, String name, String nxClass) {
		return addToAugmentPath(new StringBuilder(path), name, nxClass).toString();
	}

	/**
	 * Add to a NeXus augmented path
	 * @param path
	 * @param name
	 * @param nxClass
	 * @return augmented path
	 */
	public static StringBuilder addToAugmentPath(StringBuilder path, String name, String nxClass) {
		if (name == null) {
			throw new IllegalArgumentException("Name must not be null");
		}
		if (path.length() == 0) {
			path.append(Tree.ROOT);
		} else if (path.lastIndexOf(Node.SEPARATOR) != path.length() - 1) {
			path.append(Node.SEPARATOR);
		}
		path.append(name);
		if (nxClass != null) {
			path.append(NexusFile.NXCLASS_SEPARATOR).append(nxClass);
		}
		return path;
	}

	/**
	 * Create a plain path by stripping out NXclasses
	 * @param augmentedPath
	 * @return plain path
	 */
	public static String stripAugmentedPath(String augmentedPath) {
		int i;
		while ((i = augmentedPath.indexOf(NexusFile.NXCLASS_SEPARATOR)) >= 0) {
			int j = augmentedPath.indexOf(Node.SEPARATOR, i);
			augmentedPath = j >= 0 ? augmentedPath.substring(0, i) + augmentedPath.substring(j)
					: augmentedPath.substring(0, i);
		}
		return augmentedPath;
	}

	/**
	 * Get name of last part
	 * @param path
	 * @return name or null if path does not contain any {@value Node#SEPARATOR} or ends in that
	 */
	public static String getName(String path) {
		if (path.endsWith(Node.SEPARATOR) || !path.contains(Node.SEPARATOR))
			return null;
		return path.substring(path.lastIndexOf(Node.SEPARATOR) + 1);
	}

	/**
	 * Create a lazy writeable dataset
	 * @param name
	 * @param dtype
	 * @param shape
	 * @param maxShape
	 * @param chunks
	 * @return lazy writeable dataset
	 */
	public static ILazyWriteableDataset createLazyWriteableDataset(String name, int dtype, int[] shape, int[] maxShape, int[] chunks) {
		return new LazyWriteableDataset(name, dtype, shape, maxShape, chunks, null);
	}

	/**
	 * Write the string into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static DataNode writeString(NexusFile file, GroupNode group, String name, String value) throws NexusException {
		if (name == null || name.isEmpty() || value == null || value.isEmpty())
			return null;
		return write(file, group, name, value);
	}

	/**
	 * Write the integer into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static DataNode writeInteger(NexusFile file, GroupNode group, String name, int value) throws NexusException {
		return write(file, group, name, value);
	}

	/**
	 * Write the integer array into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static DataNode writeIntegerArray(NexusFile file, GroupNode group, String name, int[] value) throws NexusException {
		return write(file, group, name, value);
	}

	/**
	 * Write the double into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static DataNode writeDouble(NexusFile file, GroupNode group, String name, double value) throws NexusException {
		return write(file, group, name, value);
	}

	/**
	 * Write the double into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static DataNode writeDoubleArray(NexusFile file, GroupNode group, String name, double[] value) throws NexusException {
		return write(file, group, name, value);
	}

	/**
	 * Write the double into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static DataNode writeDoubleArray(NexusFile file, GroupNode group, String name, Double[] value) throws NexusException {
		return write(file, group, name, value);
	}

	/**
	 * Write the double into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @param units
	 * @throws NexusException
	 */
	public static DataNode writeDouble(NexusFile file, GroupNode group, String name, double value, String units) throws NexusException {
		DataNode node = write(file, group, name, value);
		if (units != null) {
			writeStringAttribute(file, node, "units", units);
		}
		return node;
	}

	/**
	 * Write the object into a field called 'name' at the group in the NeXus file.
	 *
	 * @param file
	 * @param group
	 * @param name
	 * @param value
	 * @return data node
	 * @throws NexusException
	 */
	public static DataNode write(NexusFile file, GroupNode group, String name, Object value) throws NexusException {
		if (value == null || name == null || name.isEmpty())
			return null;

		Dataset a = DatasetFactory.createFromObject(value);
		a.setName(name);

		DataNode d = null;
		try {
			d = file.createData(group, a);
		} catch (NexusException e) {
			d = file.getData(group, name);
			ILazyWriteableDataset wd = d.getWriteableDataset();
			try {
				wd.setSlice(null, a, null);
			} catch (Exception ex) {
				throw new NexusException("Could not set slice", ex);
			}
		}

		return d;
	}

	/**
	 * @param file
	 * @param node
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static void writeStringAttribute(NexusFile file, Node node, String name, String value) throws NexusException {
		writeAttribute(file, node, name, value);
	}

	/**
	 * @param file
	 * @param node
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static void writeIntegerAttribute(NexusFile file, Node node, String name, int... value) throws NexusException {
		writeAttribute(file, node, name, value);
	}

	/**
	 * @param file
	 * @param node
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static void writeDoubleAttribute(NexusFile file, Node node, String name, double... value) throws NexusException {
		writeAttribute(file, node, name, value);
	}

	/**
	 * @param file
	 * @param node
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static void writeDoubleAttribute(NexusFile file, Node node, String name, Double... value) throws NexusException {
		writeAttribute(file, node, name, value);
	}

	/**
	 * @param file
	 * @param node
	 * @param name
	 * @param value
	 * @throws NexusException
	 */
	public static void writeAttribute(NexusFile file, Node node, String name, Object value) throws NexusException {
		if (value == null || name == null || name.isEmpty())
			return;

		Dataset a = DatasetFactory.createFromObject(value);
		a.setName(name);
		Attribute attr = file.createAttribute(a);
		file.addAttribute(node, attr);
	}

	/**
	 * Create a new Nexus file (overwriting any existing one)
	 * @param path
	 * @return Nexus file
	 * @throws NexusException
	 */
	public static NexusFile createNexusFile(String path) throws NexusException {
		NexusFile file = new NexusFileHDF5(path);
		file.createAndOpenToWrite();
		return file;
	}

	/**
	 * Open an existing Nexus file to modify
	 * @param path
	 * @return Nexus file
	 * @throws NexusException
	 */
	public static NexusFile openNexusFile(String path) throws NexusException {
		NexusFile file = new NexusFileHDF5(path);
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
		NexusFile file = new NexusFileHDF5(path);
		file.openToRead();
		return file;
	}

	/**
	 * Save the NeXus tree with the given root node to the given path.
	 * <p>
	 * Use this method when the NeXus tree has been created using the NeXus base classes (subinterfaces of {@link NXobject}), rather than using
	 * {@link NexusFile} to create the nodes.
	 *
	 * @param rootNode
	 *            root node
	 * @param filePath
	 *            file path
	 * @throws NexusException
	 */
	public static void saveNexusFile(NXroot rootNode, String filePath) throws NexusException {
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

	public static TreeFile loadNexusFile(String filePath, boolean readOnly) throws NexusException {
		try (NexusFile nexusFile = readOnly ? openNexusFileReadOnly(filePath) : openNexusFile(filePath)) {
			final GroupNode rootNode = nexusFile.getGroup("/", false);
			final TreeFile treeFile = TreeFactory.createTreeFile(filePath.hashCode(), filePath);
			treeFile.setGroupNode(rootNode);
			recursivelyLoadNexusTree(nexusFile, rootNode);

			return treeFile;
		}
	}

	private static void recursivelyLoadNexusTree(NexusFile nexusFile, GroupNode group) throws NexusException {
		final Iterator<String> nodeNames = group.getNodeNameIterator();
		while (nodeNames.hasNext()) {
			String nodeName = nodeNames.next();
			if (group.containsGroupNode(nodeName)) {
				// nexusFile.getGroup causes that group to be populated
				GroupNode childGroup = nexusFile.getGroup(group, nodeName, null, false);
				recursivelyLoadNexusTree(nexusFile, childGroup);
			}
		}
	}

	/**
	 * Estimate suitable chunking paremeters based on the expected final size of a dataset
	 *
	 * @param expectedMaxShape
	 *            expected final size of the dataset
	 * @param dataByteSize
	 *            size of each element in bytes
	 * @return chunking estimate
	 */
	public static int[] estimateChunking(int[] expectedMaxShape, int dataByteSize) {
		// aim for at most a 1MB chunk
		final int targetSize = 1024 * 1024;
		if (expectedMaxShape == null) {
			throw new NullPointerException("Must provide an expected shape");
		}
		for (int d : expectedMaxShape) {
			if (d <= 0) {
				throw new IllegalArgumentException("Shape estimation must have dimensions greater than zero");
			}
		}
		int[] chunks = Arrays.copyOf(expectedMaxShape, expectedMaxShape.length);
		int currentSize = dataByteSize;
		for (int i : chunks) {
			currentSize *= i;
		}
		int index = 0;
		while (currentSize > targetSize) {
			chunks[index] = (int) (Math.round((chunks[index]) / 2.0) + 0.5);
			index++;
			index %= chunks.length;
			currentSize = dataByteSize;
			for (int i : chunks) {
				currentSize *= i;
			}
		}
		return chunks;
	}
}
