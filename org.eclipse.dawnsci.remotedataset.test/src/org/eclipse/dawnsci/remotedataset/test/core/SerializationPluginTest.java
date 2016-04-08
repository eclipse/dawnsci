package org.eclipse.dawnsci.remotedataset.test.core;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
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
		
		// TODO Ticket 
		// final TreeBean btree = createTreeBean(...)
		//final String json = marshaller.marshal(btree);
		//final Tree  ntree = marshaller.unmarshal(json, TreeBean.class);
		//assertEquals(btree, ntree);
	}
}
