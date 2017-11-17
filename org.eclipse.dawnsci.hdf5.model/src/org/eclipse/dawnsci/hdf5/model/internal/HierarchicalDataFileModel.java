/*-
 *******************************************************************************
 * Copyright (c) 2011, 2017 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.hdf5.model.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataFileModel;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.nexus.NexusUtils;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.Slice;

public class HierarchicalDataFileModel implements IHierarchicalDataFileModel {
	private static class Node {
		/**
		 * Map of attribute Names to attribute Values. If attributes == null
		 * then it hasn't been loaded yet
		 */
		Map<String, Object> attributes;

		/**
		 * True if the scalar value is valid.
		 */
		boolean scalarValid;

		/**
		 * Scalar value (as extracted from a dataset) or null if node's value
		 * cannot be converted to a scalar. Valid iff scalarValis is True.
		 */
		Object scalar;
	}

	/**
	 * Map of paths to nodes. If node is null that means path does not exist in
	 * file.
	 */
	private Map<String, Node> cache = Collections
			.synchronizedMap(new HashMap<String, Node>());
	private boolean isValidFile = true;
	private IHierarchicalDataFileGetReader getReader;

	public HierarchicalDataFileModel(IHierarchicalDataFileGetReader getReader) {
		this.getReader = getReader;
	}

	@Override
	public Object getPath(String path) {
		if (!isValidFile)
			return null;
		if (path.contains("@")) {
			String[] split = path.split("@", 2);
			return getAttribute(split[0], split[1]);
		} else {
			return getScalar(path);
		}
	}

	/**
	 * Get an attribute from the file.
	 * 
	 * @param path
	 *            Path to dataset or group
	 * @param attribute
	 *            attribute of dataset or group to return
	 * @return The attribute value, or <code>null</code> if the path, or
	 *         attribute at that path, does not exist.
	 */
	private Object getAttribute(String path, String attribute) {
		synchronized (cache) {
			if (cache.containsKey(path)) {
				Node node = cache.get(path);
				if (node == null) {
					// Path is known not to exist already
					return null;
				}
				if (node.attributes != null) {
					Attribute attr = (Attribute) node.attributes.get(attribute);
					// attribute does not exist
					if (attr == null) {
						return null;
					}
					Object attValue = attr.getValue();
					return NexusUtils.extractScalarFromDataset(attValue);
				}
			}

			// attributes have not been loaded
			try {
				NexusFile reader = null;
				//test if file exists
				try {
					reader = getReader.getReader();
				} catch(NexusException ne) {
					if (ne.getMessage().startsWith("File not found"))
						// exit
						return null;
				}
				if (!reader.isPathValid(path)) {
					// if path does not exist
					cache.put(path, null);
					return null;
				}
				org.eclipse.dawnsci.analysis.api.tree.Node myNode = reader.getNode(path);
				Iterator<? extends Attribute> attIterator = myNode.getAttributeIterator();
				if (!attIterator.hasNext()) {
					cache.put(path, null);
					return null;
				} else {
					// Extract scalars for all entries
					Node node = cache.get(path);
					if (node == null) {
						node = new Node();
						cache.put(path, node);
					}
					if (node.attributes == null) {
						node.attributes = new HashMap<>();
					}

					while (attIterator.hasNext()) {
						Attribute att = attIterator.next();
						node.attributes.put(att.getName(), att);
					}
					Attribute theAttribute = (Attribute) node.attributes.get(attribute);
					return NexusUtils.extractScalarFromDataset(theAttribute.getValue());
				}
			} catch (Exception e) {
				// file is bad, return now
				isValidFile = false;
				cache.clear();
				return null;
			}
		}
	}

	/**
	 * Get a Scalar value from the file.
	 * <p>
	 * If the path is to a size-1 dataset, then this function method returns the
	 * entry 0 of the dataset. Otherwise <code>null</code> is returned.
	 * 
	 * @param path
	 *            path to dataset to load
	 * @return Scalar value or <code>null</code> if path did not exists or did
	 *         not contain scalar value.
	 */
	private Object getScalar(String path) {
		synchronized (cache) {
			if (cache.containsKey(path)) {
				Node node = cache.get(path);
				if (node == null) {
					// Path is known not to exist already
					return null;
				}
				if (node.scalarValid) {
					return node.scalar;
				}
			}

			// scalar has not been loaded
			try {
				NexusFile reader = null;
				// test if file exist
				try {
					reader = getReader.getReader();
				} catch (NexusException ne) {
					if (ne.getMessage().startsWith("File not found"))
						return null;
				}
				if (!reader.isPathValid(path)) {
					// path does not exist in file
					cache.put(path, null);
					return null;
				}
				org.eclipse.dawnsci.analysis.api.tree.Node myNode = reader.getNode(path);
				if (myNode == null) {
					// path does not exist in file
					cache.put(path, null);
					return null;
				}
				Node node = cache.get(path);
				if (node == null) {
					node = new Node();
					cache.put(path, node);
				}
				node.scalarValid = true;
				node.scalar = null;

				if (myNode instanceof DataNode) {
					DataNode data = (DataNode) myNode;

					ILazyDataset dataset = data.getDataset();

					int[] dims = dataset.getShape();
					boolean allSizeOneDim = true;
					for (int dim : dims) {
						if (dim != 1) {
							allSizeOneDim = false;
						}
					}
					// Only read datasets which have one element
					// as datasets with > 1 element cannot be converted
					// with extractScalar, loading them in the first place
					// uses unnecessary procession time.
					if (allSizeOneDim) {
						int size = dataset.getSize();
						Object value = dataset.getSlice(new Slice(size));
						node.scalar = NexusUtils.extractScalarFromDataset(value);
					}
					return node.scalar;
				}
				return null;
			} catch (Exception e) {
				// file is bad, return now
				isValidFile = false;
				cache.clear();
				return null;
			}
		}
	}

	/**
	 * Return true if the given path exists, else false.
	 * 
	 * @param path
	 *            Path to dataset or group
	 * @return whether file has specified path
	 */
	private boolean hasNode(String path) {
		// load and test the path, we can't simply
		// check the result of getScalar == null
		// because the node may be a valid path
		// but not be a scalar
		getScalar(path);
		return cache.get(path) != null;
	}

	/**
	 * Return true if the given attribute exists at the given path, else false.
	 * Returns false if hasNode(path) returns false.
	 * 
	 * @param path
	 *            Path to dataset or group
	 * @param attribute
	 *            attribute of dataset or group to test
	 * @return whether file has specified attribute
	 */
	private boolean hasAttribute(String path, String attribute) {
		return getAttribute(path, attribute) != null;
	}

	@Override
	public boolean hasPath(String path) {
		if (!isValidFile)
			return false;
		if (path.contains("@")) {
			String[] split = path.split("@", 2);
			return hasAttribute(split[0], split[1]);
		} else {
			return hasNode(path);
		}
	}

	@Override
	public String toString() {
		return "HierarchicalDataFileModel for " + getReader.toString();
	}
}
