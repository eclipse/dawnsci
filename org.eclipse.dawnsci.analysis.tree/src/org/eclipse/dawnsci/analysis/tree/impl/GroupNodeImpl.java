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

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.january.dataset.ILazyDataset;

public class GroupNodeImpl extends NodeImpl implements GroupNode, Serializable {
	protected static final long serialVersionUID = 8830337783420707862L;

	private Map<Long, Node> pool;
	private int dataNodes;
	private int groups;
	private final Map<String, NodeLink> nodes;
	private boolean populated = false;

	@Override
	public void setGlobalPool(Map<Long, Node> globalPool) {
		pool = globalPool;
	}

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
		dataNodes = 0;
		groups = 0;
		nodes = new LinkedHashMap<String, NodeLink>();
	}

	@Override
	public boolean isPopulated() {
		return populated;
	}

	@Override
	public int getNumberOfNodelinks() {
		return nodes.size();
	}

	@Override
	public NodeLink getNodeLink(String name) {
		return nodes.get(name);
	}

	@Override
	public void addNodeLink(final NodeLink link) {
		synchronized (nodes) {
			final String name = link.getName();
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (link.isDestinationData() && !(n instanceof DataNode)) {
					throw new IllegalArgumentException("Cannot add a data node as there is an existing non data node of same name: " + name);
				}
				if (link.isDestinationGroup() && !(n instanceof GroupNode)) {
					throw new IllegalArgumentException("Cannot add a group node as there is an existing non group node of same name: " + name);
				}
				if (link.isDestinationSymbolic() && !(n instanceof SymbolicNode)) {
					throw new IllegalArgumentException("Cannot add a symbolic node as there is an existing non symbolic node of same name: " + name);
				}
			}
			Node n = link.getDestination();
			if (n instanceof GroupNode) {
				groups++;
			} else {
				dataNodes++;
			}
			nodes.put(name, link);
			populated = true;
		}
	}

	@Override
	public void addNode(final String name, final Node node) {
		if (node == null)
			return;
	
		if (node instanceof SymbolicNode) {
			addSymbolicNode(name, (SymbolicNode) node);
		} else if (node instanceof DataNode) {
			addDataNode(name, (DataNode) node);
		} else if (node instanceof GroupNode) {
			addGroupNode(name, (GroupNode) node);
		}
	}

	@Override
	public int getNumberOfGroupNodes() {
		return groups;
	}
	
	@Override
	public boolean containsNode(final String name) {
		return nodes.containsKey(name);
	}

	@Override
	public boolean containsGroupNode(final String name) {
		return nodes.containsKey(name) && nodes.get(name).isDestinationGroup();
	}

	@Override
	public GroupNode getGroupNode(final String name) {
		if (nodes.containsKey(name)) {
			Node n = nodes.get(name).getDestination();
			if (n instanceof SymbolicNode) {
				n = ((SymbolicNode) n).getNode();
				if (n == null) {
					throw new NullPointerException("A symbolic node exists with the given name which cannot be resolved to a group node: " + name);
				}
			}
			if (!(n instanceof GroupNode)) {
				throw new IllegalArgumentException("Existing node with given name is not a group node: " + name);
			}

			return (GroupNode) n;
		}

		return null;
	}

	public void addGroupNode(final String name, final GroupNode g) {
		synchronized (nodes) {
			// check that there is not an existing data node with the same name
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (!(n instanceof GroupNode)) {
					throw new IllegalArgumentException("Cannot add node as group contains node with same name that is not a group node: " + name);
				}
			} else {
				groups++;
			}
			// add the new node
			nodes.put(name, createNodeLink(name, g));
			populated = true;
		}
	}

	@Override
	public void removeGroupNode(final String name) {
		if (!nodes.containsKey(name)) {
			throw new IllegalArgumentException("No node exists in this group with the name: " + name);
		}

		Node n = nodes.get(name).getDestination();
		if (n instanceof SymbolicNode) {
			n = ((SymbolicNode) n).getNode();
			if (n == null) {
				throw new NullPointerException("A symbolic node exists with the given name which cannot be resolved to a group node: " + name);
			}
		}
		if (n instanceof DataNode) {
			throw new IllegalArgumentException("Group of given name does not exist in this group: " + name);
		}

		nodes.remove(name);
		groups--;
	}

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

	@Override
	public int getNumberOfDataNodes() {
		return dataNodes;
	}

	@Override
	public boolean containsDataNode(final String name) {
		return nodes.containsKey(name) && nodes.get(name).isDestinationData();
	}

	@Override
	public DataNode getDataNode(final String name) {
		if (nodes.containsKey(name)) {
			Node n = nodes.get(name).getDestination();
			if (n instanceof SymbolicNode) {
				n = ((SymbolicNode) n).getNode();
				if (n == null) {
					throw new NullPointerException("A symbolic node exists with the given name which cannot be resolved to a data node: " + name);
				}
			}
			if (!(n instanceof DataNode)) {
				throw new IllegalArgumentException("Existing node with given name is not a data node: " + name);
			}

			return (DataNode) n;
		}

		return null;
	}
	
	public Node getNode(final String name) {
		if (nodes.containsKey(name)) {
			return nodes.get(name).getDestination();
		}
		
		return null;
	}

	@Override
	public void addDataNode(final String name, final DataNode d) {
		synchronized (nodes) {
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (!(n instanceof DataNode)) {
					throw new IllegalArgumentException("Cannot add node as group contains node with same name that is not a data node: " + name);
				}
			} else {
				dataNodes++;
			}
			nodes.put(name, createNodeLink(name, d));
			populated = true;
		}
	}

	protected NodeLink createNodeLink(final String name, final Node n) {
		return new NodeLinkImpl(name, this, n);
	}

	@Override
	public void removeDataNode(final String name) {
		if (!nodes.containsKey(name)) {
			throw new IllegalArgumentException("No name exists in this group: " + name);
		}

		Node n = nodes.get(name).getDestination();
		if (n instanceof SymbolicNode) {
			n = ((SymbolicNode) n).getNode();
			if (n == null) {
				throw new NullPointerException("A symbolic node exists with the given name which cannot be resolved to a data node: " + name);
			}
		}
		if (n instanceof GroupNode) {
			throw new IllegalArgumentException("Dataset of given name does not exist in this group: " + name);
		}

		nodes.remove(name);
		dataNodes--;
	}

	@Override
	public void removeDataNode(final DataNode d) {
		for (String n : nodes.keySet()) {
			NodeLink l = nodes.get(n);
			if (l.getDestination().equals(d)) {
				nodes.remove(n);
				dataNodes--;
				return;
			}
		}
		throw new IllegalArgumentException("Given dataset does not exist in this group");
	}

	@Override
	public void addSymbolicNode(final String name, final SymbolicNode s) {
		synchronized (nodes) {
			if (nodes.containsKey(name)) {
				Node n = nodes.get(name).getDestination();
				if (!(n instanceof SymbolicNode)) {
					throw new IllegalArgumentException("Cannot add node as group contains node with same name that is not a symbolic node: " + name);
				}
			} else {
				if (name.endsWith(Node.SEPARATOR)) {
					groups++;
				} else {
					dataNodes++;
				}
			}
			nodes.put(name, createNodeLink(name, s));
		}
	}

	@Override
	public boolean containsSymbolicNode(String name) {
		return nodes.containsKey(name) && nodes.get(name).isDestinationSymbolic();
	}

	@Override
	public SymbolicNode getSymbolicNode(String name) {
		if (nodes.containsKey(name)) {
			Node n = nodes.get(name).getDestination();
			if (!(n instanceof SymbolicNode)) {
				throw new IllegalArgumentException("Existing node with given name is not a symbolic node: " + name);
			}
			
			return (SymbolicNode) n;
		}
		
		return null;
	}

	@Override
	public void removeSymbolicNode(String name) {
		if (!nodes.containsKey(name)) {
			throw new IllegalArgumentException("No node exists in this group with the name: " + name);
		}
		
		Node n = nodes.get(name).getDestination();
		if (!(n instanceof SymbolicNode)) {
			throw new IllegalArgumentException("The node with the given name is not a symbolic node: " + name);
		}
		
		nodes.remove(name);
	}

	@Override
	public void removeSymbolicNode(SymbolicNode s) {
		for (String n : nodes.keySet()) {
			NodeLink l = nodes.get(n);
			if (l.getDestination().equals(s)) {
				nodes.remove(n);
				return;
			}
		}
		
		throw new IllegalArgumentException("Given symbolic node does not exist in this group");
	}
	
	@Override
	public boolean isGroupNode() {
		return true;
	}

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
			appendNodeString(s, n);
		}

		return s.toString();
	}

	protected void appendNodeString(StringBuilder s, String n) {
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
	

	@Override
	public Iterator<String> getNodeNameIterator() {
		return nodes.keySet().iterator();
	}

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
				if (dataset != null && !list.contains(dataset)) {
					list.add(dataset);
				}
			}
		}
	}

	@Override
	public NodeLink findNodeLink(String pathname) {
		int i = pathname.indexOf(SEPARATOR);
	
		if (i == 0) {
			pathname = pathname.substring(1);
			i = pathname.indexOf(SEPARATOR);
		}

		String link = i < 0 ? pathname : pathname.substring(0, i);

		if (nodes.containsKey(link)) {
			NodeLink node = nodes.get(link);
			if (i < 0) {
				return node;
			}
			String path = pathname.substring(i+1);
			if (node.isDestinationSymbolic()) {
				node = ((SymbolicNode) node.getDestination()).getNodeLink();
			}
			if (node.isDestinationGroup()) {
				return ((GroupNode) node.getDestination()).findNodeLink(path);
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
