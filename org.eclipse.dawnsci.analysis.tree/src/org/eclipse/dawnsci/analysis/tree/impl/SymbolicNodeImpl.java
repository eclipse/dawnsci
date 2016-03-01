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
import java.net.URI;
import java.util.Objects;

import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeUtils;

/**
 * Symbolic link to another node
 */
public class SymbolicNodeImpl extends NodeImpl implements SymbolicNode, Serializable {
	protected static final long serialVersionUID = -2348087598312513187L;

	private URI uri;
	private Tree tree;
	private GroupNode group;
	private String path;

	/**
	 * Construct a symbolic link with given object ID, from tree, group and node path
	 * @param oid object ID
	 * @param tree
	 * @param groupWithNode (can be null if path is absolute)
	 * @param pathToNode (ends in separator if group, otherwise a dataset)
	 */
	public SymbolicNodeImpl(final long oid, final Tree tree, final GroupNode groupWithNode, final String pathToNode) {
		this(oid, tree == null ? null : tree.getSourceURI(), groupWithNode, pathToNode);
		this.tree = tree;
	}

	/**
	 * Construct a symbolic link with given object ID, from URI to tree, group and node path
	 * @param oid object ID
	 * @param uri
	 * @param groupWithNode (can be null if path is absolute)
	 * @param pathToNode (ends in separator if group, otherwise a dataset)
	 */
	public SymbolicNodeImpl(final long oid, final URI uri, final GroupNode groupWithNode, final String pathToNode) {
		super(oid);
		this.uri = uri;
		group = groupWithNode;
		path = TreeImpl.canonicalizePath(pathToNode);
		if (!path.startsWith(Tree.ROOT) && group == null) {
			throw new IllegalArgumentException("A group node must be given when creating a symbolic node with a relative path");
		}
	}

	@Override
	public NodeLink getNodeLink() {
		if (group == null || tree == null) {
			return null;
		}

		if (path.startsWith(Tree.ROOT)) {
			return tree.findNodeLink(path);
		}
		return tree.findNodeLink(TreeImpl.canonicalizePath(TreeUtils.getPath(tree, group) + path));
	}

	@Override
	public Node getNode() {
		NodeLink l = getNodeLink();
		return l == null ? null : l.getDestination();
	}

	@Override
	public boolean isData() {
		NodeLink l = getNodeLink();
		return l == null ? !path.endsWith(SEPARATOR) : l.isDestinationData();
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public URI getSourceURI() {
		return uri;
	}
	
	@Override
	public boolean isSymbolicNode() {
		return true;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		String attrs = super.toString();
		out.append(attrs);
		return out.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof SymbolicNodeImpl)) return false;
		
		SymbolicNodeImpl other = (SymbolicNodeImpl) obj;
		if (!Objects.equals(uri, other.uri)) return false;
		if (!Objects.equals(tree, other.tree)) return false;
		if (!Objects.equals(group, other.group)) return false;
		if (!Objects.equals(path, other.path)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uri, tree, group, path);
	}
	
}
