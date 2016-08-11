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

import org.eclipse.january.dataset.ILazyDataset;

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
	 * @throws IllegalArgumentException if node link of same name already exists but is of different type 
	 */
	public void addNodeLink(NodeLink link);

	/**
	 * Add given node with given path and name
	 * @param name
	 * @param node
	 * @throws IllegalArgumentException if node of same name already exists but is of different type 
	 */
	public void addNode(String name, Node node);
	
	/**
	 * Returns the child node of the given name, or <code>null</code> if no such node exists.
	 * @param name name of child node within this group node
	 * @return child node with given name if it exists, otherwise <code>null</code>
	 */
	public Node getNode(String name);

	/**
	 * Returns whether this group contains a child node with the given name
	 * @param name name
	 * @return <code>true</code> if this node contains a child node with the given name,
	 *    <code>false</code> otherwise
	 */
	boolean containsNode(String name);

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
	 * Get (child) group node of given name. A {@link SymbolicNode} with the
	 * given name is resolved to its destination node.
	 * @param name
	 * @return group, or <code>null</code> if no such group exists
	 * @throws IllegalArgumentException if a node exists with the given name but is not a group node
	 */
	public GroupNode getGroupNode(String name);

	/**
	 * Add (child) group node with given path and name 
	 * @param name
	 * @param g group
	 * @throws IllegalArgumentException if a node of same name already exists that is not a group node
	 */
	public void addGroupNode(String name, GroupNode g);

	/**
	 * Remove group node of given name
	 * @param name
	 * @throws IllegalArgumentException if named node does not exist or is not a group node
	 */
	public void removeGroupNode(String name);

	/**
	 * Remove given group node
	 * @param g group node
	 * @throws IllegalArgumentException if no group node exists with the given name
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
	 * Get data node of given name. A {@link SymbolicNode} with the given name is resolved
	 * to its destination node.
	 * @param name
	 * @return datanode, or <code>null</code> if no such data node exists
	 * @throws IllegalArgumentException if a node with the given name exists that is not a data node
	 */
	public DataNode getDataNode(String name);

	/**
	 * Add given data node with given path and name 
	 * @param name
	 * @param d dataset
	 * @throws IllegalArgumentException if a node of same name already exists that is not a data node
	 */
	public void addDataNode(String name, DataNode d);

	/**
	 * Remove the data node of given name.
	 * @param name
	 * @throws IllegalArgumentException if named node does not exist or is not a data node
	 */
	public void removeDataNode(String name);

	/**
	 * Remove the given data node from this group.
	 * @param d data node
	 * @throws IllegalArgumentException if node is not in group
	 */
	public void removeDataNode(DataNode d);

	/**
	 * Add linked node with given path and name
	 * @param name
	 * @param s symbolic link
	 * @throws IllegalArgumentException if a node of same name already exists that is not a symbolic node
	 */
	public void addSymbolicNode(String name, SymbolicNode s);
	
	/**
	 * Returns whether this group node contains a symbolic node of the given name.
	 * @param name
	 * @return <code>true</code> if group contains a symbolic node of given name,
	 * <code>false</code> otherwise
	 */
	public boolean containsSymbolicNode(String name);
	
	/**
	 * Removes the symbolic node with the given name.
	 * @param name name of symbolic node to remove
	 * @throws IllegalArgumentException if named node does not exist or is not a symbolic node
	 */
	public void removeSymbolicNode(String name);
	
	/**
	 * Removes the given symbolic node from this tree.
	 * @param s symbolic node
	 * @throws IllegalArgumentException if node is not in group
	 */
	public void removeSymbolicNode(SymbolicNode s);
	
	/**
	 * Get (child) symbolic node of given name. 
	 * @param name
	 * @return symbolic node, or <code>null</code> if no such node exists
	 * @throws IllegalArgumentException if a node exists with the given name that is not a
	 * 		symbolic node
	 */
	public SymbolicNode getSymbolicNode(String name);
	
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
