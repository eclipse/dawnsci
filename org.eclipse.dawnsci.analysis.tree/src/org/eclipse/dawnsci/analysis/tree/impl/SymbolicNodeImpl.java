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

import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;

/**
 * Symbolic link to another node
 */
public class SymbolicNodeImpl extends NodeImpl implements SymbolicNode, Serializable {
	protected static final long serialVersionUID = -2348087598312513187L;

	private Tree tree;
	private String path;

	/**
	 * Construct a symbolic link with given object ID, file name and node path
	 * @param oid object ID
	 * @param treeWithNode
	 * @param pathToNode (ends in separator if group, otherwise a dataset)
	 */
	public SymbolicNodeImpl(final long oid, final Tree treeWithNode, final String pathToNode) {
		super(oid);
		tree = treeWithNode;
		path = pathToNode;
	}

	@Override
	public NodeLink getNodeLink() {
		return tree.findNodeLink(path);
	}

	@Override
	public Node getNode() {
		NodeLink l = getNodeLink();
		return l == null ? null : l.getDestination();
	}

	@Override
	public boolean isData() {
		return !path.endsWith(SEPARATOR);
	}

	@Override
	public Tree getTree() {
		return tree;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		String attrs = super.toString();
		out.append(attrs);
		return out.toString();
	}
}
