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

import java.io.File;
import java.io.Serializable;
import java.net.URI;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;

/**
 * Top level node for tree-based file
 */
public class TreeFileImpl extends TreeImpl implements TreeFile, Serializable {
	protected static final long serialVersionUID = -1101384820336767759L;

	private final String path;
	private String prefix;

	/**
	 * Construct a tree file with given object ID and URI 
	 * @param oid object ID
	 * @param uri
	 */
	public TreeFileImpl(final long oid, URI uri) {
		super(oid, uri);

		File f = new File(source);
		
		path = f.getAbsolutePath();
		prefix = f.getParentFile().getAbsolutePath();
	}

	/**
	 * Construct a tree file with given object ID and file name 
	 * @param oid object ID
	 * @param fileName
	 */
	public TreeFileImpl(final long oid, final String fileName) {
		this(oid, new File(fileName).toURI());
	}

	@Override
	public String getFilename() {
		return path;
	}

	@Override
	public String getParentDirectory() {
		return prefix;
	}

	@Override
	public String toString() {
		return (host != null ? (host + HOST_SEPARATOR + FILE_STARTER) : "") + path;
	}
}
