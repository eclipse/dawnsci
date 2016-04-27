package org.eclipse.dawnsci.remotedataset.test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
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
		
		URL loc = getClass().getResource("163245_Cu_formate_1_processed_150604_145331.nxs");
		String file = BundleUtils.getBundleLocation("org.eclipse.dawnsci.remotedataset.test")+"/src"+loc.getFile();
		IDataHolder dh = lservice.getData(file, null);
		
		final Tree   tree = dh.getTree();
		
		Map<String, NodeLink> bfs = TreeUtils.treeBreadthFirstSearch(tree.getGroupNode(), getFinder(), true, null);
		String key = bfs.keySet().iterator().next();
		
		
		final String json = marshaller.marshal(TreeToMapUtils.treeToMap(tree));
		final Map  ntree = marshaller.unmarshal(json, Map.class);
		Tree myTree = TreeToMapUtils.mapToTree(ntree, "163245_Cu_formate_1_processed_150604_145331.nxs");
		
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
