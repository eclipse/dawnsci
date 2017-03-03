/*-
 *******************************************************************************
 * Copyright (c) 2011, 2017 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *    Baha El-Kassaby - Removal of HObject usage
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.editor;

import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

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
		if (element instanceof NodeLink) {
			NodeLink link = (NodeLink) element;
			final String name = link.getName();

			if (name == null || "".equals(name))
				return true;

			if (name.matches(searchString)) {
				return true;
			}
			if (name.matches(searchString)) {
				return true;
			}

		}
		return false;
	}

}
