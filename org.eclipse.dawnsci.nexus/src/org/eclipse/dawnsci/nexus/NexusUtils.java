/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */


package org.eclipse.dawnsci.nexus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;

/**
 * Utility methods for dealing with NeXus files.
 */
public class NexusUtils {

	final static int CHUNK_TARGET_SIZE = 1024 * 1024; // 1 MB

	final static ChunkingStrategy DEFAULT_CHUNK_STRATEGY = ChunkingStrategy.SKEW_LAST;

	/**
	 * Possible strategies for estimating chunking
	 */
	public enum ChunkingStrategy {
		/**
		 * Approximately balance chunking across all dimensions
		 * Better for slicing across multiple dimensions when processing.
		 */
		BALANCE,

		/**
		 * Skew chunking toward later dimensions - maximally reduce earlier dimensions first
		 * Good for writing large detector images frame by frame.
		 */
		SKEW_LAST
	}

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
	 * Loads the entire nexus tree structure into memory. Note that this does not
	 * necessarily load the contents of every dataset within the nexus file into memory,
	 * as some may be {@link ILazyDataset}s.
	 * @param filePath file path
	 * @return TreeFile tree file representing nexus tree
	 * @throws NexusException
	 */
	public static TreeFile loadNexusTree(NexusFile nexusFile) throws NexusException {
		// TODO FIXME mattd 2016-02-11 should it be possible to do this without
		// a NexusFile? or perhaps this method should even be on NexusFile itself
		final String filePath = nexusFile.getFilePath();
		final TreeFile treeFile = TreeFactory.createTreeFile(filePath.hashCode(), filePath);
		final GroupNode rootNode = nexusFile.getGroup("/", false);
		treeFile.setGroupNode(rootNode);
		recursivelyLoadNexusTree(nexusFile, rootNode);

		return treeFile;
	}

	private static void recursivelyLoadNexusTree(NexusFile nexusFile, GroupNode group) throws NexusException {
		final Iterator<String> nodeNames = group.getNodeNameIterator();
		while (nodeNames.hasNext()) {
			String nodeName = nodeNames.next();
			if (group.containsGroupNode(nodeName)) {
				// nexusFile.getGroup() causes that group to be populated
				GroupNode childGroup = nexusFile.getGroup(group, nodeName, null, false);
				recursivelyLoadNexusTree(nexusFile, childGroup);
			}
		}
	}

	private static int[] estimateChunkingBalanced(int[] expectedMaxShape,
			int dataByteSize,
			int[] fixedChunkDimensions,
			int targetSize) {
		if (expectedMaxShape == null) {
			throw new NullPointerException("Must provide an expected shape");
		}
		if (fixedChunkDimensions != null && (expectedMaxShape.length != fixedChunkDimensions.length)) {
			throw new IllegalArgumentException("Shape estimation and provided chunk information have different dimensions");
		}
		for (int d : expectedMaxShape) {
			if (d <= 0) {
				throw new IllegalArgumentException("Shape estimation must have dimensions greater than zero");
			}
		}
		int[] chunks = Arrays.copyOf(expectedMaxShape, expectedMaxShape.length);
		int[] fixed;
		if (fixedChunkDimensions != null) {
			fixed = fixedChunkDimensions;
		} else {
			fixed = new int[chunks.length];
			Arrays.fill(fixed, -1);
		}
		for (int i = 0; i < chunks.length; i++) {
			if (fixed[i] > 0) {
				chunks[i] = fixed[i];
			}
		}
		long currentSize = dataByteSize;
		for (int i : chunks) {
			currentSize *= (long) i;
		}
		int idx = 0;
		boolean minimal = false;
		while (currentSize > targetSize && !minimal) {
			//check that our chunk estimation can continue being reduced
			for (int i = 0; i < fixed.length; i++) {
				minimal = true;
				if (fixed[i] <= 0 && chunks[i] > 1) {
					minimal = false;
					break;
				}
			}
			if (fixed[idx] <= 0) {
				chunks[idx] = (int) Math.round(chunks[idx] / 2.0);
			}
			idx++;
			idx %= chunks.length;
			currentSize = dataByteSize;
			for (int i : chunks) {
				currentSize *= (long) i;
			}
		}
		return chunks;
	}

	private static int[] estimateChunkingSkewed(int[] expectedMaxShape,
			int dataByteSize,
			int[] fixedChunkDimensions,
			int targetSize) {
		if (expectedMaxShape == null) {
			throw new NullPointerException("Must provide an expected shape");
		}
		if (fixedChunkDimensions != null && (expectedMaxShape.length != fixedChunkDimensions.length)) {
			throw new IllegalArgumentException("Shape estimation and provided chunk information have different dimensions");
		}
		for (int d : expectedMaxShape) {
			if (d <= 0) {
				throw new IllegalArgumentException("Shape estimation must have dimensions greater than zero");
			}
		}

		int[] chunk = Arrays.copyOf(expectedMaxShape, expectedMaxShape.length);
		int[] fixed = fixedChunkDimensions;
		if (fixed == null) {
			fixed = new int[chunk.length];
			Arrays.fill(fixed, -1);
		}
		for (int i = 0; i < chunk.length; i++) {
			if (fixed[i] > 0) {
				chunk[i] = fixed[i];
			}
		}
		long currentSize = dataByteSize;
		for (int i : chunk) {
			currentSize *= (long) i;
		}
		ArrayList<Integer> toReduce = new ArrayList<Integer>();
		for (int i = 0; i < fixed.length; i++) {
			if (fixed[i] < 1) toReduce.add(i);
		}
		outer_loop:
		for (int idx : toReduce) {
			while (chunk[idx] > 1) {
				if (currentSize > targetSize) {
					// round up to avoid needing an extra chunk to hold a tiny amount of data
					chunk[idx] = (int) Math.ceil(chunk[idx] / 2.0);

					currentSize = dataByteSize;
					for (int c : chunk) {
						currentSize *= (long) c;
					}
				} else {
					// finished reducing chunk
					break outer_loop;
				}
			}
		}
		return chunk;
	}

	/**
	 * Estimate suitable chunk parameters based on the expected final size of a dataset
	 *
	 * @param expectedMaxShape
	 *            expected final size of the dataset
	 * @param dataByteSize
	 *            size of each element in bytes
	 * @param fixedChunkDimensions
	 *            provided dimensions in a chunk to be kept constant (-1 for no provided chunk)
	 * @param strategy
	 *            strategy to use for estimating
	 * @return chunk estimate
	 */
	public static int[] estimateChunking(int[] expectedMaxShape,
			int dataByteSize,
			int[] fixedChunkDimensions,
			ChunkingStrategy strategy) {
		switch (strategy) {
		case BALANCE:
			return estimateChunkingBalanced(expectedMaxShape, dataByteSize, fixedChunkDimensions, CHUNK_TARGET_SIZE);
		case SKEW_LAST:
		default:
			return estimateChunkingSkewed(expectedMaxShape, dataByteSize, fixedChunkDimensions, CHUNK_TARGET_SIZE);
		}
	}

	/**
	 * Estimate suitable chunk parameters based on the expected final size of a dataset
	 * @param expectedMaxShape
	 *            expected final size of the dataset
	 * @param dataByteSize
	 *            size of each element in bytes
	 * @param strategy
	 *            strategy to use for estimating
	 * @return chunk estimate
	 */
	public static int[] estimateChunking(int[] expectedMaxShape, int dataByteSize, ChunkingStrategy strategy) {
		return estimateChunking(expectedMaxShape, dataByteSize, null, strategy);
	}

	/**
	 * Estimate suitable chunk parameters based on the expected final size of a dataset
	 *
	 * @param expectedMaxShape
	 *            expected final size of the dataset
	 * @param dataByteSize
	 *            size of each element in bytes
	 * @param fixedChunkDimensions
	 *            provided dimensions in a chunk to be kept constant (-1 for no provided chunk)
	 * @return chunk estimate
	 */
	public static int[] estimateChunking(int[] expectedMaxShape, int dataByteSize, int[] fixedChunkDimensions) {
		return estimateChunking(expectedMaxShape, dataByteSize, fixedChunkDimensions, DEFAULT_CHUNK_STRATEGY);
	}

	/**
	 * Estimate suitable chunk parameters based on the expected final size of a dataset
	 *
	 * @param expectedMaxShape
	 *            expected final size of the dataset
	 * @param dataByteSize
	 *            size of each element in bytes
	 * @return chunk estimate
	 */
	public static int[] estimateChunking(int[] expectedMaxShape, int dataByteSize) {
		return estimateChunking(expectedMaxShape, dataByteSize, null, DEFAULT_CHUNK_STRATEGY);
	}
}
