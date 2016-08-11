/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.editor;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import hdf.object.HObject;

class H5Filter extends ViewerFilter {

	private String    searchString;

	public void setSearchText(String s) {
		if (s==null) s="";
		this.searchString = ".*" + s.toLowerCase() + ".*";
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}

		final TreeNode node   = (TreeNode)element;
		final HObject  object = (HObject)((DefaultMutableTreeNode)node).getUserObject();
		final String name     = object.getName();
		
		if (name==null || "".equals(name)) return true;

		if (name.matches(searchString)) {
			return true;
		}
		if (name.matches(searchString)) {
			return true;
		}


		return false;
	}

}
