/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5.nexus;

import java.io.IOException;
import java.net.URI;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;

/**
 * Replacement for NexusFileInterface
 * 
 * All elements of a path can be augmented with an optional NXclass string appended on
 * with a {@value #NXCLASS_SEPARATOR}. If the NXclass does not match then an {@link IllegalArgumentException}
 * will be thrown.
 */
public interface NexusFile extends AutoCloseable {

	public static final String NXCLASS_SEPARATOR = ":";

	/*
	 * use augmented paths (with NX_class attribute)
	 * use absolute and relative paths with lazy moves and create if necessary
	 * get info on dataset
	 * get/set data as whole or slab and compressed
	 * get/set attributes
	 * 
	 * cope with arbitrary serializable data including nested arrays and boxed types
	 * add instrumentation for debugging (instead of wrapper)
	 * make links
	 */

	/**
	 * Open file to only read
	 * @throws IOException 
	 */
	public void openToRead() throws IOException;

	/**
	 * Open file to write
	 * @param createIfNecessary if true, then create file if it does not exist
	 * @throws IOException 
	 */
	public void openToWrite(boolean createIfNecessary) throws IOException;

	/**
	 * Create file and open to write
	 * @throws IOException
	 */
	public void createAndOpenToWrite() throws IOException;

	/**
	 * Get group with given path and create path if necessary
	 * @param path
	 * @param createPathIfNecessary
	 * @return node or null if group does not exist at specified path
	 */
	public GroupNode getGroup(String path, boolean createPathIfNecessary);

	/**
	 * Get data node with given path
	 * @param path
	 * @return node or null if data does not exist at specified path
	 */
	public DataNode getData(String path);

	/**
	 * Create data node with given path and create path if necessary
	 * @param path
	 * @param data
	 * @param createPathIfNecessary
	 * @return node or null if data does not exist at specified path
	 */
	public DataNode createData(String path, ILazyWriteableDataset data, boolean createPathIfNecessary);

	/**
	 * Create data node in given group
	 * @param name
	 * @param data
	 * @return node
	 */
	public DataNode createData(GroupNode group, ILazyWriteableDataset data);

	/**
	 * Create attribute
	 * @param node
	 * @param attr
	 * @return attribute
	 */
	public Attribute createAttribute(String node, IDataset attr);

	/**
	 * Add (and write) attribute(s) to given node
	 * @param node
	 * @param attribute
	 */
	public void addAttribute(Node node, Attribute... attribute);

	/**
	 * Link source to a destination. If the destination ends in {@value Node#SEPARATOR}
	 * then the source name is added to the destination.
	 * @param source
	 * @param destination
	 */
	public void link(String source, String destination);

	/**
	 * Link source in another file to a destination. If the destination ends in {@value Node#SEPARATOR}
	 * then the source name is added to the destination.
	 * @param source
	 * @param destination
	 * @param isGroup
	 */
	public void linkExternal(URI source, String destination, boolean isGroup);

	/**
	 * Flush data to filesystem
	 */
	public void flush();
}
