/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.test.core;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.tree.IFindInTree;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeUtils;
import org.eclipse.dawnsci.analysis.tree.TreeToMapUtils;
import org.eclipse.dawnsci.remotedataset.test.ServiceHolder;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * Not intended for unit suites.
 * 
 * @author Matthew Gerring
 *
 */
public class SerializationPluginTest {
	
	private IMarshallerService marshaller;
	private ILoaderService     lservice;
	
	@Before
	public void before() throws Exception {
		marshaller = ServiceHolder.getMarshallerService();
		lservice   = ServiceHolder.getLoaderservice();
	}

	@Test
	public void testTreeSerialize() throws Exception {
		
		String file = BundleUtils.getBundleLocation("org.eclipse.dawnsci.remotedataset.test")+"/testfiles/38323_processed.nxs";
		IDataHolder dh = lservice.getData(file, null);
		
		final Tree   tree = dh.getTree();
		
		Map<String, NodeLink> bfs = TreeUtils.treeBreadthFirstSearch(tree.getGroupNode(), getFinder(), true, null);
		String key = bfs.keySet().iterator().next();
		
		final Map<String, Object> map = TreeToMapUtils.treeToMap(tree);
		final String json = marshaller.marshal(map, false);
		final Map<String, Object>  ntree = marshaller.unmarshal(json, Map.class);
		
		assertTrue(map.keySet().containsAll(ntree.keySet()));
		
		Tree myTree = TreeToMapUtils.mapToTree(ntree, "38323_processed.nxs");
		
		Map<String, NodeLink> bfst = TreeUtils.treeBreadthFirstSearch(myTree.getGroupNode(), getFinder(), true, null);
		String keyt = bfst.keySet().iterator().next();
		
		assertTrue(key.equals(keyt));
	}
	
	private IFindInTree getFinder(){
		return new IFindInTree() {
			
			@Override
			public boolean found(NodeLink node) {
				Node n = node.getDestination();
				if (n.containsAttribute("signal")) {
					return true;
				}

				return false;
			}
		};
	}
}
