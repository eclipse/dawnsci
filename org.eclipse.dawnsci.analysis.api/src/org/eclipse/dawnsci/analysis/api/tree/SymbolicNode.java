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

/**
 * Symbolic node that uses a name to link to another node
 */
public interface SymbolicNode extends Node {

	/**
	 * Get node link referenced by symbolic link
	 * @return node
	 */
	public NodeLink getNodeLink();

	/**
	 * Get node referenced by symbolic link
	 * @return node
	 */
	public Node getNode();

	/**
	 * @return true if linked node is data
	 */
	public boolean isData();

	/**
	 * @return tree containing linked node
	 */
	public Tree getTree();

	/**
	 * @return path to linked node
	 */
	public String getPath();
}
