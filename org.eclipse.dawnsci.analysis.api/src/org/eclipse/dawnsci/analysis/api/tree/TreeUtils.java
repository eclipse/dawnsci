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
	 * Get the path
	 * @param tree
	 * @param node
	 * @return path to node, return null if not found
	 */
	public static String getPath(Tree tree, Node node) {
		GroupNode g = tree.getGroupNode();
		if (g == node) {
			return tree.getNodeLink().getFullName();
		}
		return getPathDepthFirst(tree.getGroupNode(), node);
	}

	private static String getPathDepthFirst(final GroupNode group, final Node node) {
		for (NodeLink l : group) {
			if (l.getDestination() == node) {
				return l.getFullName();
			} else if (l.isDestinationGroup()) {
				String p = getPathDepthFirst((GroupNode) l.getDestination(), node);
				if (p != null)
					return p;
			}
		}
		return null;
	}
}
