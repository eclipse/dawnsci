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
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.Node;

public class NodeImpl implements Node, Serializable {
	protected static final long serialVersionUID = -662872819341035983L;

	protected LinkedHashMap<String, Attribute> attributes;
	protected static final String INDENT = "    ";
	protected final long id;

	/**
	 * Construct a node with given object ID
	 * @param oid object ID
	 */
	public NodeImpl(final long oid) {
		attributes = new LinkedHashMap<String, Attribute>();
		id = oid;
	}

	@Override
	public long getID() {
		return id;
	}

	@Override
	public int getNumberOfAttributes() {
		return attributes.size();
	}

	@Override
	public boolean containsAttribute(final String name) {
		return attributes.containsKey(name);
	}

	@Override
	public Attribute getAttribute(final String name) {
		return attributes.get(name);
	}

	@Override
	public void addAttribute(final Attribute a) {
		attributes.put(a.getName(), a);
	}

	@Override
	public Iterator<String> getAttributeNameIterator() {
		return attributes.keySet().iterator();
	}

	@Override
	public Iterator<? extends Attribute> getAttributeIterator() {
		return attributes.values().iterator();
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
	
		for (String a : attributes.keySet()) {
			out.append(INDENT);
			out.append(ATTRIBUTE);
			out.append(a);
			out.append(" = ");
			out.append(attributes.get(a));
			out.append('\n');
		}
	
		return out.toString();
	}
}
