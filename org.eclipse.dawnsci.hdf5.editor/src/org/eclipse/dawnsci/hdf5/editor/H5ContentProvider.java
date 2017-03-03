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
 *    Baha El-Kassaby - Removal of IHierchicalDataFile and HObject usage
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public class H5ContentProvider implements ILazyTreeContentProvider {

	private TreeViewer treeViewer;

	public H5ContentProvider() {

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		treeViewer = (TreeViewer) viewer;
		treeViewer.refresh();
	}

	@Override
	public void updateElement(Object parent, int index) {
		if (parent instanceof NodeLink) {
			NodeLink node = (NodeLink) parent;
			int size = 0;
			String name = "";
			GroupNode groupnode = null;
			List<String> names = null;
			NodeLink link = null;
			if(node.getDestination() instanceof DataNode) {
				DataNode datanode = (DataNode) node.getDestination();
				size = datanode.getNumberOfAttributes();
				Iterator<String> attributesNameIt = datanode.getAttributeNameIterator();
				names = new ArrayList<String>(datanode.getNumberOfAttributes());
				while (attributesNameIt.hasNext()) {
					String attribute = (String) attributesNameIt.next();
					names.add(attribute);
				}
				Iterator<? extends Attribute> attributesIt = datanode.getAttributeIterator();
				List<Attribute> attributes = new ArrayList<Attribute>(datanode.getNumberOfAttributes());
				while (attributesIt.hasNext()) {
					Attribute attribute = (Attribute) attributesIt.next();
					attributes.add(attribute);
				}
				Object att = attributes.get(index);
				if (att instanceof NodeLink) {
					link = (NodeLink) attributes.get(index);
					if (treeViewer != null && index < size) {
						name = names.get(index);
						treeViewer.replace(parent, index, link);
						updateChildCount(link, -1);
					}
				} else if (att instanceof Attribute) {
					Attribute attribute = (Attribute)att;
					if (treeViewer != null && index < size) {
						name = names.get(index);
						treeViewer.replace(parent, index, attribute);
						updateChildCount(link, -1);
					}
				}
			}
			if (node.getDestination() instanceof GroupNode) {
				groupnode = (GroupNode) node.getDestination();
				size = groupnode.getNumberOfNodelinks();
				names = new ArrayList<String>(groupnode.getNames());
				if (treeViewer != null && index < size) {
					name = names.get(index);
					link = groupnode.findNodeLink(name);
					treeViewer.replace(parent, index, link);
					updateChildCount(link, -1);
				}
			}
		}
	}

	@Override
	public void updateChildCount(Object element, int currentChildCount) {
		NodeLink link = (NodeLink) element;
		int size = 0;
		Node destination = link != null ? link.getDestination() : null;
		if (destination != null) {
			if (destination instanceof DataNode) {
				DataNode datanode = (DataNode) link.getDestination();
				size = datanode.getNumberOfAttributes();
			}
			if (destination instanceof GroupNode) {
				GroupNode groupnode = (GroupNode) link.getDestination();
				size = groupnode.getNumberOfNodelinks();
			}
		}
		if (treeViewer != null && element != null)
			treeViewer.setChildCount(element, size);
	}

	@Override
	public Object getParent(Object element) {
		if (element==null || !(element instanceof TreeNode)) {
			return null;
		}
		final TreeNode node = ((TreeNode) element);
		return node.getParent();
	}


}
