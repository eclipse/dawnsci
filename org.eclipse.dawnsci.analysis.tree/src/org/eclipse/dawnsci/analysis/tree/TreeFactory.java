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
package org.eclipse.dawnsci.analysis.tree;

import java.net.URI;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.impl.AttributeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.DataNodeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.GroupNodeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.NodeLinkImpl;
import org.eclipse.dawnsci.analysis.tree.impl.SymbolicNodeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeImpl;

public class TreeFactory {

	/**
	 * Create an attribute with node, name
	 * @param attrName
	 */
	public static Attribute createAttribute(final String attrName) {
		return new AttributeImpl(attrName);
	}


	/**
	 * Create an attribute with node, name, value and sign
	 * @param attrName
	 * @param attrValue (usually, this is a Java array)
	 * @param isUnsigned true if items are unsigned but held in signed primitives
	 */
	public static Attribute createAttribute(final String attrName, final Object attrValue, final boolean isUnsigned) {
		return new AttributeImpl(attrName, attrValue, isUnsigned);
	}

	/**
	 * Create a node link
	 * @param path to source
	 * @param link name
	 * @param source node which link starts from (can be null)
	 * @param destination node which link points to
	 */
	public static NodeLink createNodeLink(final Tree tree, final String path, final String link, final Node source, final Node destination) {
		return new NodeLinkImpl(tree, path, link, source, destination);
	}

	/**
	 * Create a symbolic link with given object ID, file name and node path
	 * @param oid object ID
	 * @param treeWithNode
	 * @param pathToNode (ends in separator if group, otherwise a dataset)
	 */
	public static SymbolicNode createSymbolicNode(final long oid, final Tree treeWithNode, final String pathToNode) {
		return new SymbolicNodeImpl(oid, treeWithNode, pathToNode);
	}

	/**
	 * Create a data node with given object ID
	 * @param oid object ID
	 */
	public static DataNode createDataNode(long oid) {
		return new DataNodeImpl(oid);
	}

	/**
	 * Create a group node with given object ID
	 * @param oid object ID
	 */
	public static GroupNode createGroupNode(long oid) {
		return new GroupNodeImpl(oid);
	}

	/**
	 * Create a tree with given object ID and URI 
	 * @param oid object ID
	 * @param uri (can be null)
	 */
	public static Tree createTree(final long oid, URI uri) {
		return new TreeImpl(oid, uri);
	}

	/**
	 * Create a tree file with given object ID and URI 
	 * @param oid object ID
	 * @param uri
	 */
	public static TreeFile createTreeFile(final long oid, URI uri) {
		return new TreeFileImpl(oid, uri);
	}

	/**
	 * Create a tree file with given object ID and file name 
	 * @param oid object ID
	 * @param fileName
	 */
	public static TreeFile createTreeFile(final long oid, final String fileName) {
		return new TreeFileImpl(oid, fileName);
	}
}
