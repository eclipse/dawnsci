/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;


public class TreeUtils {

	/**
	 * Get the path which ends with {@value Node#SEPARATOR} if it refers to a group
	 * @param tree
	 * @param node
	 * @return path to node, return null if not found
	 */
	public static String getPath(Tree tree, Node node) {
		GroupNode g = tree.getGroupNode();
		if (g == node) {
			return Tree.ROOT;
		}
		String p = getPathDepthFirst(Tree.ROOT, g, node);
		if (node instanceof GroupNode && !p.endsWith(Node.SEPARATOR)) {
			p = p + Node.SEPARATOR;
		}
		return p;
	}

	private static String getPathDepthFirst(final String parent, final GroupNode group, final Node node) {
		for (NodeLink l : group) {
			String current = parent + l.getName();
			if (l.getDestination() == node) {
				return current;
			} else if (l.isDestinationGroup()) {
				String p = getPathDepthFirst(current + Node.SEPARATOR, (GroupNode) l.getDestination(), node);
				if (p != null)
					return p;
			}
		}
		return null;
	}
	
	/**
	 * Breadth first search of nexus tree using visitor to determine whether a node link matches
	 * those searched for
	 * @param file
	 * @param finder
	 * @param findFirst
	 * @param mon
	 * @return map with full path as key and NodeLink as value
	 */
	public static Map<String, NodeLink> treeBreadthFirstSearch(GroupNode file, IFindInTree finder, boolean findFirst, IMonitor mon) {
		
		Map<String, NodeLink> map = new HashMap<String, NodeLink>();
		List<NamedNodeLink> out = new ArrayList<NamedNodeLink>();
		
		Queue<NamedNodeLink> queue = new LinkedList<NamedNodeLink>();
		for (NodeLink nxObject : file) {
			Node node = nxObject.getDestination();
			if (finder.found(nxObject)) {
				
				if (findFirst) {
					map.put(nxObject.getName(),nxObject);
					return map;
				}
				out.add(new NamedNodeLink(nxObject.getName(), nxObject));
			}
			
			if(node instanceof GroupNode) {
				queue.add(new NamedNodeLink(nxObject.getName(), nxObject));
			}
		}
		
		while (queue.size() != 0) {
			NamedNodeLink group = queue.poll();
			GroupNode dest = (GroupNode)group.link.getDestination();
			for (NodeLink nxObject: dest) {
				Node node = nxObject.getDestination();
				if (finder.found(nxObject)) {
					if (findFirst) {
						map.put(group.name + Node.SEPARATOR + nxObject.getName(),nxObject);
						return map;
					}
					out.add(new NamedNodeLink(group.name + Node.SEPARATOR + nxObject.getName(), nxObject));
				}
				
				if(node instanceof GroupNode) {
					queue.add(new NamedNodeLink(group.name + Node.SEPARATOR + nxObject.getName(), nxObject));
				}
			}
			
			if (mon != null && mon.isCancelled()) return null;
		}
		
		for (NamedNodeLink nnl : out) map.put(nnl.name, nnl.link);
		
		return map;
	}
	
	/**
	 * Get a map of all the unique data nodes in a tree
	 * Map keys are the data nodes, map values are the shortest path to each node
	 * 
	 * @param node
	 * @return map
	 */
	public static Map<DataNode,String> getUniqueDataNodes(GroupNode node) {
		HashMap<DataNode, String> nodes = new HashMap<>();
		
		addNodes(node,nodes,"");
		
		return nodes;
		
		
	}
	
	private static void addNodes(GroupNode node, Map<DataNode,String> map, String name) {
		Iterator<String> it = node.getNodeNameIterator();
		while (it.hasNext()) {
			String next = it.next();
			Node n = node.getNode(next);
			if (n instanceof DataNode && !map.containsKey(n)) map.put((DataNode)n, name + Node.SEPARATOR + next);
			else if (n instanceof GroupNode) addNodes((GroupNode)n, map, name + Node.SEPARATOR + next);
		}
		
	}
	
	private static class NamedNodeLink {
		public String name;
		public NodeLink link;
		
		public NamedNodeLink(String name, NodeLink link) {
			this.name = name;
			this.link = link;
		}
		
	}
}
