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
 * A tree consists of nodes linked together by groups
 */
public interface NodeLink {

	/**
	 * @return source node
	 */
	public Node getSource();

	/**
	 * @return destination node
	 */
	public Node getDestination();

	/**
	 * @return true if destination is a data node
	 */
	public boolean isDestinationData();

	/**
	 * @return true if destination is a group node
	 */
	public boolean isDestinationGroup();

	/**
	 * @return true if destination is a symbolic node
	 */
	public boolean isDestinationSymbolic();

	/**
	 * @return name of node link
	 */
	public String getName();

	/**
	 * @return path of node link
	 */
	public String getPath();

	/**
	 * @return full name 
	 */
	public String getFullName();

	/**
	 * @return top node
	 */
	public Tree getTree();
}
