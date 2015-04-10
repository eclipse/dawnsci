/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;

/**
 * Node to group other nodes using node links
 */
public interface GroupNode extends Node, Iterable<NodeLink> {

	/**
	 * @return true if it has been populated with any nodes
	 */
	public boolean isPopulated();

	/**
	 * Set a reference to the global pool of nodes
	 * @param globalPool
	 */
	public void setGlobalPool(Map<Long, Node> globalPool);

	/**
	 * @return global pool of cached nodes
	 */
	public Map<Long, Node> getGlobalPool();

	/**
	 * @return number of node-links held in group
	 */
	public int getNumberOfNodelinks();

	/**
	 * @param name
	 * @return node link to child node of given name
	 */
	public NodeLink getNodeLink(String name);

	/**
	 * Add node link
	 * @param link
	 */
	public void addNodeLink(NodeLink link);

	/**
	 * Add given node with given path and name
	 * @param tree
	 * @param path
	 * @param name
	 * @param node
	 */
	public void addNode(Tree tree, String path, String name, Node node);

	/**
	 * @return number of child groups in group
	 */
	public int getNumberOfGroupNodes();

	/**
	 * @param name
	 * @return true if group contains child group of given name
	 */
	public boolean containsGroupNode(String name);

	/**
	 * Get (child) group node of given name 
	 * @param name
	 * @return group
	 */
	public GroupNode getGroupNode(String name);

	/**
	 * Add (child) group node with given path and name 
	 * @param tree
	 * @param path
	 * @param name
	 * @param g group
	 */
	public void addGroupNode(Tree tree, String path, String name, GroupNode g);

	/**
	 * Remove group node of given name
	 * @param name
	 */
	public void removeGroupNode(String name);

	/**
	 * Remove given group node
	 * @param g group node
	 */
	public void removeGroupNode(GroupNode g);

	/**
	 * @return number of data nodes held in group
	 */
	public int getNumberOfDataNodes();

	/**
	 * @param name
	 * @return true if group contains data node of given name
	 */
	public boolean containsDataNode(String name);

	/**
	 * Get data node of given name
	 * @param name
	 * @return dataset
	 */
	public DataNode getDataNode(String name);

	/**
	 * Add given data node with given path and name 
	 * @param tree
	 * @param path
	 * @param name
	 * @param d dataset
	 */
	public void addDataNode(Tree tree, String path, String name, DataNode d);

	/**
	 * Remove dataset of given name
	 * @param name
	 */
	public void removeDataNode(String name);

	/**
	 * Remove given dataset
	 * @param d dataset
	 */
	public void removeDataNode(DataNode d);

	/**
	 * Add linked node with given path and name
	 * @param tree
	 * @param path
	 * @param name
	 * @param s symbolic link
	 */
	public void addSymbolicNode(Tree tree, String path, String name, SymbolicNode s);

	/**
	 * Find name of node linked to this group
	 * @param node
	 * @return name (or null, if node is not in group)
	 */
	public String findLinkedNodeName(Node node);

	/**
	 * @return iterator over child names in group
	 */
	public Iterator<String> getNodeNameIterator();

	/**
	 * Recursively find datasets of given name
	 * @param name
	 * @return list of (unique) datasets
	 */
	public List<ILazyDataset> getDatasets(String name);

	/**
	 * Recursively find link to node given by path name 
	 * @param pathname
	 * @return node or null if not found
	 */
	public NodeLink findNodeLink(String pathname);

	/**
	 * @return iterator over links to children in group
	 */
	@Override
	public Iterator<NodeLink> iterator();

	/**
	 * @return names of nodes
	 */
	public Collection<String> getNames();
}
