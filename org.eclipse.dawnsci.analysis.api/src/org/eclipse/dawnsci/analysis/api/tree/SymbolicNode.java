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

import java.net.URI;

/**
 * Symbolic node that uses a name to link to another node. Note, the destination does not have to exist.
 */
public interface SymbolicNode extends Node {

	/**
	 * Get node link referenced by symbolic link
	 * @return node or null if tree or destination does not exist
	 */
	public NodeLink getNodeLink();

	/**
	 * Get node referenced by symbolic link
	 * @return node or null if it does not exist
	 */
	public Node getNode();

	/**
	 * @return true if linked node is specified as data (even if it does not exist)
	 */
	public boolean isData();

	/**
	 * @return path to linked node
	 */
	public String getPath();

	/**
	 * @return URI to source of tree
	 */
	public URI getSourceURI();
}
