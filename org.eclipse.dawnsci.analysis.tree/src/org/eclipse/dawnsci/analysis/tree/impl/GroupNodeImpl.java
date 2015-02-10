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

package org.eclipse.dawnsci.analysis.tree.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;

public class GroupNodeImpl extends NodeImpl implements GroupNode, Serializable {
	protected static final long serialVersionUID = 8830337783420707862L;

	private Map<Long, Node> pool;
	private int datasets;
	private int groups;
	private final Map<String, NodeLink> nodes;

	/**
	 * Set a reference to the global pool of nodes
	 * @param globalPool
	 */
	@Override
	public void setGlobalPool(Map<Long, Node> globalPool) {
		pool = globalPool;
	}

	/**
	 * @return global pool of cached nodes
	 */
	@Override
	public Map<Long, Node> getGlobalPool() {
		return pool;
	}

	/**
	 * Construct a group node with given object ID
	 * @param oid object ID
	 */
	public GroupNodeImpl(final long oid) {
		super(oid);
		datasets = 0;
		groups = 0;
		nodes = new LinkedHashMap<String, NodeLink>();
	}

	/**
	 * @return number of node links held in group
	 */
	@Override
	public int getNumberOfNodelinks() {
		return nodes.size();
	}

	/**
	 * @param name
	 * @return node link to child node of given name
	 */
	@Override
	public NodeLink getNodeLink(String name) {
		return nodes.get(name);
	}

	/**
	 * Add node link
	 * @param link
	 */
	@Override
	public void addNodeLink(final NodeLink link) {
		synchronized (nodes) {
			final String name = link.getName();
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (n instanceof SymbolicNode)
					n = ((SymbolicNode) n).getNode();
				if (link.isDestinationData() && !(n instanceof DataNode)) {
					throw new IllegalArgumentException("Cannot add a group as there is a non-group of same name: " + name);
				}
				if (link.isDestinationGroup() && !(n instanceof GroupNode)) {
					throw new IllegalArgumentException("Cannot add a group as there is a non-dataset of same name: " + name);
				}
			}
			Node n = link.getDestination();
			if (n instanceof SymbolicNode)
				n = ((SymbolicNode) n).getNode();
			if (n instanceof GroupNode) {
				groups++;
			} else {
				datasets++;
			}
			nodes.put(name, link);
		}
	}

	/**
	 * Add given node with given path and name
	 * @param file
	 * @param path
	 * @param name
	 * @param node
	 */
	public void addNode(final Tree file, final String path, final String name, final Node node) {
		if (node == null)
			return;
	
		if (node instanceof SymbolicNode) {
			addSymbolicNode(file, path, name, (SymbolicNode) node);
		} else if (node instanceof DataNode) {
			addDataNode(file, path, name, (DataNode) node);
		} else if (node instanceof GroupNode) {
			addGroupNode(file, path, name, (GroupNode) node);
		}
	}

	/**
	 * @return number of child groups in group
	 */
	@Override
	public int getNumberOfGroupNodes() {
		return groups;
	}

	/**
	 * @param name
	 * @return true if group contains child group of given name
	 */
	@Override
	public boolean containsGroupNode(final String name) {
		return nodes.containsKey(name) && nodes.get(name).isDestinationGroup();
	}

	/**
	 * Get (child) group of given name 
	 * @param name
	 * @return group
	 */
	@Override
	public GroupNode getGroupNode(final String name) {
		if (nodes.containsKey(name)) {
			Node n = nodes.get(name).getDestination();
			if (n instanceof SymbolicNode)
				n = ((SymbolicNode) n).getNode();
			if (n instanceof GroupNode)
				return (GroupNode) n;
		}
	
		throw new IllegalArgumentException("No such group of given name: " + name);
	}

	/**
	 * Add (child) group with given path and name 
	 * @param file
	 * @param path
	 * @param name
	 * @param g group
	 */
	public void addGroupNode(final Tree file, final String path, final String name, final GroupNode g) {
		synchronized (nodes) {
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (n instanceof SymbolicNode)
					n = ((SymbolicNode) n).getNode();
				if (n instanceof DataNode) {
					throw new IllegalArgumentException("Cannot add a group as there is a dataset of same name: " + name);
				}
			} else {
				groups++;
			}
			nodes.put(name, createNodeLink(file, path, name, g));
		}
	}

	/**
	 * Remove group of given name
	 * @param name
	 */
	@Override
	public void removeGroupNode(final String name) {
		if (!nodes.containsKey(name))
			throw new IllegalArgumentException("No name exists in this group: " + name);
	
		Node n = nodes.get(name).getDestination();
		if (n instanceof SymbolicNode)
			n = ((SymbolicNode) n).getNode();
		if (n instanceof DataNode)
			throw new IllegalArgumentException("Group of given name does not exist in this group: " + name);
	
		nodes.remove(name);
		groups--;
	}

	/**
	 * Remove given group
	 * @param g group
	 */
	@Override
	public void removeGroupNode(final GroupNode g) {
		for (String n : nodes.keySet()) {
			NodeLink l = nodes.get(n);
			if (l.getDestination().equals(g)) {
				nodes.remove(n);
				groups--;
				return;
			}
		}
		throw new IllegalArgumentException("Given group does not exist in this group");
	}

	/**
	 * @return number of datasets held in group
	 */
	@Override
	public int getNumberOfDataNodes() {
		return datasets;
	}

	/**
	 * @param name
	 * @return true if group contains dataset of given name
	 */
	@Override
	public boolean containsDataNode(final String name) {
		return nodes.containsKey(name) && nodes.get(name).isDestinationData();
	}

	/**
	 * Get dataset of given name
	 * @param name
	 * @return dataset
	 */
	public DataNodeImpl getDataNode(final String name) {
		if (nodes.containsKey(name)) {
			Node n = nodes.get(name).getDestination();
			if (n instanceof SymbolicNode)
				n = ((SymbolicNode) n).getNode();
			if (n instanceof DataNodeImpl)
				return (DataNodeImpl) n;
		}
	
		throw new IllegalArgumentException("No such dataset of given name: " + name);
	}

	/**
	 * Add given dataset with given path and name 
	 * @param file
	 * @param path
	 * @param name
	 * @param d dataset
	 */
	@Override
	public void addDataNode(final Tree file, final String path, final String name, final DataNode d) {
		synchronized (nodes) {
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (n instanceof SymbolicNode)
					n = ((SymbolicNode) n).getNode();
				if (n instanceof GroupNode) {
					throw new IllegalArgumentException("Cannot add a dataset as there is a group of same name: " + name);
				}
			} else {
				datasets++;
			}
			nodes.put(name, createNodeLink(file, path, name, d));
		}
	}

	protected NodeLink createNodeLink(final Tree file, final String path, final String name, final Node n) {
		return new NodeLinkImpl(file, path, name, this, n);
	}

	/**
	 * Remove dataset of given name
	 * @param name
	 */
	@Override
	public void removeDataNode(final String name) {
		if (!nodes.containsKey(name))
			throw new IllegalArgumentException("No name exists in this group: " + name);
	
		Node n = nodes.get(name).getDestination();
		if (n instanceof SymbolicNode)
			n = ((SymbolicNode) n).getNode();
		if (n instanceof GroupNode)
			throw new IllegalArgumentException("Dataset of given name does not exist in this group: " + name);
	
		nodes.remove(name);
		datasets--;
	}

	/**
	 * Remove given dataset
	 * @param d dataset
	 */
	public void removeDataNode(final DataNode d) {
		for (String n : nodes.keySet()) {
			NodeLink l = nodes.get(n);
			if (l.getDestination().equals(d)) {
				nodes.remove(n);
				datasets--;
				return;
			}
		}
		throw new IllegalArgumentException("Given dataset does not exist in this group");
	}

	/**
	 * Add linked node with given path and name
	 * @param file
	 * @param path
	 * @param name
	 * @param s symbolic node
	 */
	public void addSymbolicNode(final Tree file, final String path, final String name, final SymbolicNode s) {
		synchronized (nodes) {
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (n instanceof SymbolicNode)
					n = ((SymbolicNode) n).getNode();
				if (n instanceof DataNode) {
					throw new IllegalArgumentException("Cannot add a group as there is a dataset of same name: " + name);
				}
			} else {
				if (name.endsWith(Node.SEPARATOR)) {
					groups++;
				}
			}
			nodes.put(name, createNodeLink(file, path, name, s));
		}
	}

	/**
	 * Find name of node linked to this group
	 * @param node
	 * @return name (or null, if node is not in group)
	 */
	@Override
	public String findLinkedNodeName(Node node) {
		for (Entry<String, NodeLink> e : nodes.entrySet()) {
			if (e.getValue().getDestination() == node) {
				return e.getKey();
			}
		}
		return null;
	}

	@Override
	public String toString() {
			StringBuilder s = new StringBuilder(super.toString());
			for (String n : nodes.keySet()) {
				s.append(INDENT);
				s.append(n);
				Node node = nodes.get(n).getDestination();
				if (node instanceof SymbolicNode)
					s.append('@');
				else if (node instanceof GroupNode)
					s.append('/');
	//			else
	//				s.append(String.format("(%d)", node.getID()));
				s.append('\n');
			}
	
			return s.toString();
		}

	/**
	 * @return iterator over child names in group
	 */
	@Override
	public Iterator<String> getNodeNameIterator() {
		return nodes.keySet().iterator();
	}

	/**
	 * Recursively find datasets of given name
	 * @param name
	 * @return list of (unique) datasets
	 */
	@Override
	public List<ILazyDataset> getDatasets(final String name) {
		final ArrayList<ILazyDataset> list = new ArrayList<ILazyDataset>();

		for (NodeLink l : this) {
			findDatasets(name, list, l);
		}
		return list;
	}

	private void findDatasets(final String name, final List<ILazyDataset> list, final NodeLink link) {
		Node n = null;
		if (link.isDestinationSymbolic()) {
			SymbolicNode slink = (SymbolicNode) link.getDestination();
			if (slink.isData())
				n = slink.getNode();
		} else {
			n = link.getDestination();
		}
	
		if (n == null)
			return;
	
		if (n instanceof GroupNode) {
			for (NodeLink l : (GroupNode) n) {
				findDatasets(name, list, l);
			}
		} else if (n instanceof DataNode) {
			if (link.getName().equals(name)) {
				ILazyDataset dataset = ((DataNode) n).getDataset();
				if (!list.contains(dataset))
					list.add(dataset);
			}
		}
	}

	/**
	 * Recursively find link to node given by path name 
	 * @param pathname
	 * @return node or null if not found
	 */
	@Override
	public NodeLink findNodeLink(String pathname) {
		int i = pathname.indexOf(SEPARATOR);
	
		if (i == 0) {
			pathname = pathname.substring(1);
			i = pathname.indexOf(SEPARATOR);
		}
	
		String link = i < 0 ? pathname: pathname.substring(0, i);
	
		if (nodes.containsKey(link)) {
			NodeLink node = nodes.get(link);
			if (i < 0) {
				return node;
			}
			String path = pathname.substring(i+1);
			if (node.isDestinationGroup()) {
				return ((GroupNodeImpl) node.getDestination()).findNodeLink(path);
			}
		} else { // is attribute?
			i = link.indexOf(ATTRIBUTE);
			if (i > 0) {
				link = pathname.substring(0, i);
				String attr = pathname.substring(i+1);
				if (nodes.containsKey(link)) {
					NodeLink node = nodes.get(link);
					if (node.getDestination().containsAttribute(attr)) {
						return node;
					}
				}
			}
		}
		return null;
	}

	@Override
	public Iterator<NodeLink> iterator() {
		return (Iterator<NodeLink>)  nodes.values().iterator();
	}

	@Override
	public Collection<String> getNames() {
		synchronized (nodes) {
			return new ArrayList<String>(nodes.keySet());
		}
	}
}
