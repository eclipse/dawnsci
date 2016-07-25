/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.StringDataset;

public class TreeToMapUtils {
	
	private static final String DATANODEKEY = "org.eclipse.dawnsci.analysis.api.tree.DataNode";
	private static final String MAXSHAPE = "org.eclipse.dawnsci.analysis.api.tree.DataNode.maxShape";

	public static Map<String, Object> treeToMap(Tree tree) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		NodeLink nl = tree.getNodeLink();
		
		String name = nl.getName();
		map.put(name, parseNodeLink(nl.getDestination()));
		
		return map;
		
	}
	
	public static Tree mapToTree(Map<String, Object> map, String fileName) {
		
		TreeFile tf = TreeFactory.createTreeFile(0, fileName);
		
		String root = map.keySet().iterator().next();
		
		Object o = map.get(root);
		
		if (o instanceof Map) {
			Node n = parseMap((Map<String, Object>)o);
			if (n instanceof GroupNode) {
				tf.setGroupNode((GroupNode)n);
				return tf;
			}
			
			
		}
		
		return null;
		
	}
	
	private static Node parseMap(Map<String, Object> map) {
		
		Node parent = null;
		
		if (map.containsKey(DATANODEKEY)) {
			parent = TreeFactory.createDataNode(0);
			Object shape = map.remove(MAXSHAPE);
			if (shape instanceof long[]) ((DataNode)parent).setMaxShape((long[])shape);
			
		} else {
			parent = TreeFactory.createGroupNode(0);
		}
		
		
		
		for (String key : map.keySet()) {
			
			Object o = map.get(key);
			
			if (o instanceof Map) {
				Node gn = parseMap((Map<String, Object>) o);
				if (parent instanceof GroupNode) ((GroupNode)parent).addNode(key, gn);
			}
			
			if (o instanceof String || o instanceof String[]) {
				parent.addAttribute(TreeFactory.createAttribute(key,o));
			}
			
		}
		
		return parent;
		
		
		
	}
	

	private static Object parseNodeLink(Node destination) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		Iterator<? extends Attribute> it = destination.getAttributeIterator();
		
		while (it.hasNext()) {
			Attribute next = it.next();
			if (next.getSize() == 1) {
				map.put(next.getName(), next.getFirstElement());
			} else {
				
				IDataset value = next.getValue();
				
				if (value instanceof StringDataset) {
					map.put(next.getName(), ((StringDataset)value).getData());
				}
				
			}
			
			
		}
		
		if (destination instanceof GroupNode) {
			GroupNode gn = (GroupNode)destination;
			Iterator<String> nnit = gn.getNodeNameIterator();
			while (nnit.hasNext()){
				String next = nnit.next();
				Node node = gn.getNode(next);
				map.put(next, parseNodeLink(node));
			}
		}
		
		if (destination instanceof DataNode) {
			map.put(DATANODEKEY, true);
			DataNode dn = (DataNode)destination;
			map.put("org.eclipse.dawnsci.analysis.api.tree.DataNode.maxShape", dn.getMaxShape());
		}
		
		return map;
	}
	
}
