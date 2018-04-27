/*-
 * Copyright 2015, 2017 Diamond Light Source Ltd.
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;

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
			} else if (group.containsDataNode(nodeName)) {
				nexusFile.getData(group, nodeName);
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

	/**
	 * Returns names of axes in group at same level as name passed in.
	 * 
	 * This opens and safely closes a nexus file if one is not already open for this
	 * location.
	 * 
	 * @param filePath
	 * @param nexusPath
	 *            - path to signal dataset
	 * @param dimension,
	 *            the dimension we want the axis for starting with 1
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAxisNames(String filePath, String nexusPath, int dimension) throws Exception {

		if (filePath == null || nexusPath == null)
			return null;
		if (dimension < 1)
			return null;
		try (NexusFile file = ServiceHolder.getNexusFileFactory().newNexusFile(filePath)) {
			file.openToRead();
			DataNode signal = file.getData(nexusPath);

			final List<String> axesTmp = new ArrayList<String>(3);
			final Map<Integer, String> axesMap = new TreeMap<Integer, String>();

			int[] shape = signal.getDataset().getShape();
			if (dimension > shape.length)
				return null;
			final long size = shape[dimension - 1];

			final String parentPath = file.getPath(signal).substring(0, file.getPath(signal).lastIndexOf(Node.SEPARATOR));

			final GroupNode parent = file.getGroup(parentPath, false);

			int fakePosValue = Integer.MAX_VALUE;

			for (NodeLink child : parent) {
				Node node = child.getDestination();
				if (node instanceof DataNode) {
					DataNode dataNode = (DataNode) node;
					final Iterator<? extends Attribute> att = dataNode.getAttributeIterator();
					if (!(child.isDestinationData()))
						continue;
					if (child.getName().equals(signal.getDataset().getName()))
						continue;

					String axis = null;
					int pos = -1;
					boolean isSignal = false;
					while (att.hasNext()) {
						Attribute attribute = att.next();
						if ("axis".equals(attribute.getName())) {
							int iaxis = getAttributeIntValue(attribute);
							if (iaxis < 0) { // We look for comma separated string
								final int[] axesDims = attribute.getShape();

								final int[] shapeAxes = dataNode.getDataset().getShape();
								final int[] shapeData = shape;
								if (axesDims != null && axesCompatible(axesDims, shapeAxes, shapeData)) {
									for (int dim : axesDims) {
										if (dim == dimension) {
											axis = parentPath + Node.SEPARATOR + dataNode.getDataset().getName() + ":" + dimension;
											break;
										}
									}
								}

							}
							if (iaxis == dimension) {

								final int[] dims = dataNode.getDataset().getShape();

								if ((dims.length == 1 && dims[0] == size) || dims.length != 1) {
									axis = parentPath + Node.SEPARATOR + dataNode.getDataset().getName();
								}

							}

						} else if ("primary".equals(attribute.getName())) {
							if (pos != 0)
								pos = getAttributeIntValue(attribute);

						} else if ("label".equals(attribute.getName())) {
							int labelAxis = getAttributeIntValue(attribute);
							if (labelAxis == dimension)
								pos = 0;

						} else if ("signal".equals(attribute.getName())) {
							isSignal = true;
							axis = null;
							pos = -1;
							break;
						}
					}

					// prioritise datasets that specify an axes (even with no primary attribute)
					// over other datasets
					if (axis != null && pos == -1) {
						pos = fakePosValue--;
					}

					// Add any the same shape as this dimension
					// providing that they are not signals
					// Some nexus files set axis wrong
					if (axis == null && !isSignal) {
						final int[] dims = dataNode.getDataset().getShape();
						if (dims[0] == size && dims.length == 1) {
							axis = parentPath + Node.SEPARATOR + dataNode.getDataset().getName();
						}
					}

					if (axis != null) {
						if (pos < 0) {
							axesTmp.add(axis);
						} else {
							axesMap.put(pos, axis);
						}
					}
				}
			}

			final List<String> axes = new ArrayList<String>(3);
			if (!axesMap.isEmpty()) {
				for (Integer pos : axesMap.keySet()) {
					axes.add(axesMap.get(pos));
				}
			}
			axes.addAll(axesTmp);

			return axes;
		}
	}

	/**
	 * Gets the int value or returns -1 (Can only be used for values which are not
	 * allowed to be -1!)
	 * 
	 * @param attribute
	 * @return
	 */
	private static int getAttributeIntValue(Attribute attribute) {
		if (attribute.isString()) {
			return Integer.parseInt(attribute.getFirstElement());
		}
		final Dataset ob = DatasetUtils.convertToDataset(attribute.getValue());
		return ob.getInt();
	}

	private static boolean axesCompatible(int[] axesDims, int[] shapeAxes, int[] shapeData) {
		if (axesDims == null)
			return false;
		if (Arrays.equals(shapeAxes, shapeData))
			return true;

		for (int i = 0; i < axesDims.length; i++) {
			if (shapeAxes[i] != shapeData[axesDims[i] - 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compare object a as a scalar to String b by first converting b to a type
	 * that matches a.
	 *
	 * @see Comparable#compareTo(Object)
	 * @throws NumberFormatException
	 *             if b can not be converted to the same type as a
	 */
	public static int compareScalarToString(Object a, String b)
			throws NumberFormatException {
		if (a instanceof Short) {
			return ((Short) a).compareTo(Short.parseShort(b));
		}
		if (a instanceof Integer) {
			return ((Integer) a).compareTo(Integer.parseInt(b));
		}
		if (a instanceof Long) {
			return ((Long) a).compareTo(Long.parseLong(b));
		}
		if (a instanceof Character) {
			return ((Character) a).toString().compareTo(b);
		}
		if (a instanceof Float) {
			return ((Float) a).compareTo(Float.parseFloat(b));
		}
		if (a instanceof Double) {
			return ((Double) a).compareTo(Double.parseDouble(b));
		}
		if (a instanceof Boolean) {
			return ((Boolean) a).compareTo(Boolean.parseBoolean(b));
		}
		if (a instanceof Byte) {
			return ((Byte) a).compareTo(Byte.valueOf(b));
		}
		if (a instanceof String) {
			return ((String) a).compareTo(b);
		}
		String name;
		if (a == null) {
			name = "null";
		} else {
			name = "a.getClass().getName()";
		}
		throw new NumberFormatException("a has unknown type for conversion: "
				+ name);
	}

	/**
	 * Compares the two scalar objects if they are the same type and Comparable
	 * using their compareTo method, else compares the toString value.
	 *
	 * @see Comparable#compareTo(Object)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int compareScalars(Object a, Object b) {
		if (a instanceof Comparable && b instanceof Comparable) {
			Comparable ca = (Comparable) a;
			Comparable cb = (Comparable) b;
			if (a.getClass() == b.getClass())
				return ca.compareTo(cb);
		}
		return a.toString().compareTo(b.toString());
	}

	/**
	 * Convert the size == 1 array to a scalar. Works on January data types Dataset
	 *
	 * @param value
	 *         a January Dataset is expected
	 * @return the scalar value, or <code>null</code> if no scalar can be
	 *         extracted
	 */
	public static Object extractScalarFromDataset(Object value) {
		if (value instanceof Dataset) {
			Dataset d = (Dataset) value;
			return d.getSize() == 1 ? d.getObject() : null;
		}
		return null;
	}

	/**
	 * Append data to a dataset in group node. If data node does not exist then create one. This assumes
	 * that data is of a consistent rank R and will be appended to a shape with rank R+1 along the first
	 * dimension 
	 * @param f Nexus file
	 * @param g group node
	 * @param a dataset
	 * @return data node
	 * @throws NexusException
	 * @throws DatasetException 
	 */
	public static DataNode appendData(NexusFile f, GroupNode g, Dataset a) throws NexusException, DatasetException {
		int r = a.getRank();
		int[] ns = new int[r + 1];
		ns[0] = 1;
		System.arraycopy(a.getShapeRef(), 0, ns, 1, r);
		String name = a.getName();
		a = a.reshape(ns);
		DataNode d = g.getDataNode(name);
		if (d == null) {
			int[] ms = ns.clone();
			ms[0] = -1;
			LazyWriteableDataset w = new LazyWriteableDataset(name, a.getElementClass(), ns, ms, null, null);
			return f.createData(g, w);
		}
	
		ILazyWriteableDataset w = d.getWriteableDataset();
		if (r != w.getRank() - 1) {
			throw new IllegalArgumentException("Rank of input dataset must be " + (w.getRank() - 1) + " not " + r);
		}
		int[] cs = w.getShape();
		SliceND slice = new SliceND(cs, w.getMaxShape(), new Slice(cs[0], cs[0] + 1));
		w.setSlice(null, a, slice);
		return d;
	}

	/**
	 * Load group and its subgroups to given maximum depth
	 * @param file
	 * @param path
	 * @param maxDepth
	 * @return group
	 * @throws NexusException
	 */
	public static GroupNode loadGroupFully(NexusFile file, String path, int maxDepth) throws NexusException {
		// populate tree
		GroupNode g = file.getGroup(path, false);
		loadGroup(file, g, maxDepth);
		return g;
	}

	private static void loadGroup(NexusFile file, GroupNode g, int maxDepth) throws NexusException {
		if (maxDepth-- > 0) {
			for (String n : g.getNames()) {
				if (g.containsGroupNode(n)) {
					loadGroup(file, file.getGroup(g, n, null, false), maxDepth);
				}
			}
		}
	}
}
