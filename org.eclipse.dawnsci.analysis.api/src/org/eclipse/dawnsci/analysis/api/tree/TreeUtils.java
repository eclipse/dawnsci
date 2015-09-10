/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.tree;

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
		String p = getPathDepthFirst(Tree.ROOT, tree.getGroupNode(), node);
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
}
