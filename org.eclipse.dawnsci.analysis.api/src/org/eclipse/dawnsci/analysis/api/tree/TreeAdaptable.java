/*
 * Copyright (c) 2015, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.tree;

/**
 * Interface to allow UI interactions with items in a tree such as a node or attribute
 */
public interface TreeAdaptable {

	/**
	 * @return full path to file
	 */
	public abstract String getFile();

	/**
	 * @return full path to node or node attribute
	 */
	public abstract String getNode();

	/**
	 * @return node link (can be null)
	 */
	public abstract NodeLink getNodeLink();

	/**
	 * @return attribute (can be null)
	 */
	public abstract Attribute getAttribute();
}
