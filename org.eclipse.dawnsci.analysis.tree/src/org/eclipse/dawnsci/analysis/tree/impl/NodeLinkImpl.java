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

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;

public class NodeLinkImpl implements NodeLink, Serializable {
	protected static final long serialVersionUID = -8586668618966201973L;

	private Node from;
	private Node to;
	private String name;

	/**
	 * A node link
	 * @param link name
	 * @param source node which link starts from (can be null)
	 * @param destination node which link points to
	 */
	public NodeLinkImpl(final String link, final Node source, final Node destination) {
		if (link == null || destination == null) {
			throw new IllegalArgumentException("Path name, link name and destination must be defined");
		}

		name = link;
		from = source;
		to   = destination;
	}

	@Override
	public Node getSource() {
		return from;
	}

	@Override
	public Node getDestination() {
		return to;
	}

	@Override
	public boolean isDestinationData() {
		return to instanceof DataNode;
	}

	@Override
	public boolean isDestinationGroup() {
		return to instanceof GroupNode;
	}

	@Override
	public boolean isDestinationSymbolic() {
		return to instanceof SymbolicNode;
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return name + '\n' + to.toString();
	}

	@Override
	public String getFullName() {
		return name;
	}
}
