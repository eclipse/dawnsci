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
 * Replacement for old Nexus file interface
 * 
 * All elements of a path can be augmented with an optional NX_class string appended on
 * with a {@value #NXCLASS_SEPARATOR}. If the NX_class attribute does not match then an
 * {@link IllegalArgumentException} will be thrown.
 */
public interface NexusFile extends AutoCloseable {

	public static final String NXCLASS = "NX_class";

	public static final String NXCLASS_SEPARATOR = ":";

	/**
	 * No compression
	 */
	public static final int COMPRESSION_NONE = 0;

	/**
	 * Compression using LZW level 1
	 */
	public static final int COMPRESSION_LZW_L1 = 1;

	/**
	 * Scheme for NeXus URI used to refer to external files
	 */
	public static final String NX_URL_SCHEME = "nxfile";

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
	public void openToRead() throws NexusException;

	/**
	 * Open file to write
	 * @param createIfNecessary if true, then create file if it does not exist
	 * @throws IOException 
	 */
	public void openToWrite(boolean createIfNecessary) throws NexusException;

	/**
	 * Create file and open to write
	 * @throws IOException
	 */
	public void createAndOpenToWrite() throws NexusException;

	/**
	 * Set to instrument some method calls via logging at the DEBUG level
	 * @param debug
	 */
	public void setDebug(boolean debug);

	/**
	 * Get path of node from internal tree
	 * @param node
	 * @return (depth-first) path of node
	 */
	public String getPath(Node node);

	/**
	 * Get group with given path and create path if necessary
	 * @param path
	 * @param createPathIfNecessary
	 * @return node or null if group does not exist at specified path
	 */
	public GroupNode getGroup(String path, boolean createPathIfNecessary) throws NexusException;

	/**
	 * Get group in group with given name and create path if necessary
	 * @param group
	 * @param name
	 * @param nxClass
	 * @param createPathIfNecessary
	 * @return node or null if group does not exist at specified path
	 */
	public GroupNode getGroup(GroupNode group, String name, String nxClass, boolean createPathIfNecessary) throws NexusException;

	/**
	 * Get data node with given path
	 * @param path
	 * @return node or null if data does not exist at specified path
	 */
	public DataNode getData(String path) throws NexusException;

	/**
	 * Get data node in group with given name
	 * @param group
	 * @param name
	 * @return node or null if data does not exist in group
	 */
	public DataNode getData(GroupNode group, String name) throws NexusException;

	/**
	 * Create data node with given path to its group and create path if necessary
	 * @param path to group
	 * @param data
	 * @param createPathIfNecessary
	 * @return node or null if data does not exist at specified path
	 * @throws NexusException when node already exists
	 */
	public DataNode createData(String path, ILazyWriteableDataset data, boolean createPathIfNecessary) throws NexusException;

	/**
	 * Create data node with given path to its group and create path if necessary
	 * @param path to group
	 * @param data
	 * @param compression
	 * @param createPathIfNecessary
	 * @return node or null if data does not exist at specified path
	 * @throws NexusException when node already exists
	 */
	public DataNode createData(String path, ILazyWriteableDataset data, int compression, boolean createPathIfNecessary) throws NexusException;

	/**
	 * Create data node with given path to its group and create path if necessary
	 * @param path to group
	 * @param data
	 * @param createPathIfNecessary
	 * @return node or null if data does not exist at specified path
	 * @throws NexusException when node already exists
	 */
	public DataNode createData(String path, IDataset data, boolean createPathIfNecessary) throws NexusException;

	/**
	 * Create data node in given group
	 * @param group
	 * @param data
	 * @return node
	 * @throws NexusException when node already exists
	 */
	public DataNode createData(GroupNode group, ILazyWriteableDataset data) throws NexusException;

	/**
	 * Create data node in given group
	 * @param group
	 * @param data
	 * @param compression
	 * @return node
	 * @throws NexusException when node already exists
	 */
	public DataNode createData(GroupNode group, ILazyWriteableDataset data, int compression) throws NexusException;

	/**
	 * Create data node in given group
	 * @param group
	 * @param data
	 * @return node
	 * @throws NexusException when node already exists
	 */
	public DataNode createData(GroupNode group, IDataset data) throws NexusException;

	/**
	 * Create attribute
	 * @param attr
	 * @return attribute
	 */
	public Attribute createAttribute(IDataset attr);

	/**
	 * Add (and write) attribute(s) to given node
	 * @param node
	 * @param attribute
	 */
	public void addAttribute(Node node, Attribute... attribute) throws NexusException;

	/**
	 * Add (and write) attribute(s) to given path
	 * @param path
	 * @param attribute
	 */
	public void addAttribute(String path, Attribute... attribute) throws NexusException;

	/**
	 * Link source to a destination. If the destination ends in {@value Node#SEPARATOR}
	 * then the source name is added to the destination.
	 * @param source
	 * @param destination
	 */
	public void link(String source, String destination) throws NexusException;

	/**
	 * Link source in another file to a destination. If the destination ends in {@value Node#SEPARATOR}
	 * then the source name is added to the destination.
	 * @param source
	 * @param destination
	 * @param isGroup
	 */
	public void linkExternal(URI source, String destination, boolean isGroup) throws NexusException;

	/**
	 * Flush data to filesystem
	 */
	public void flush() throws NexusException;

	/**
	 * Close file
	 */
	@Override
	public void close() throws NexusException;

	/**
	 * Checks if a path within the file exists
	 * @param path
	 * @return true if path exists
	 */
	public boolean isPathValid(String path);
}
