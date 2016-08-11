/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.test.server;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.tree.TreeToMapUtils;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.dawnsci.remotedataset.test.core.BundleUtils;
import org.eclipse.dawnsci.remotedataset.test.mock.LoaderServiceMock;
import org.eclipse.january.dataset.IRemoteData;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs as standard junit test.
 * Start the Data Server before running this test!
 * 
 * Test tests require that the DataServer is going and that the
 * data is at the pre-supposed locations.
 * 
 * TODO make this into a replicable unit test.
 * 
 * @author Matthew Gerring
 *
 */ 
public class RemoteDataTest extends DataServerTest {
	
	private IRemoteDatasetService service;
	
	@Before
	public void before() {
		service = new RemoteDatasetServiceImpl(); // Instead of OSGi - do not copy!
		((RemoteDatasetServiceImpl)service).setMarshallerService(new MarshallerService());
		ServiceHolder.setLoaderService(new LoaderServiceMock(factory));
		ServiceHolder.setMarshallerService(new MarshallerService());
	}

	@Test
	public void testEmptyTree() throws Exception {

		final IRemoteData data = service.createRemoteData("localhost", 8080);
		
		String path = "./testfiles/38323_processed.nxs"; // The mock loader gives a non-null empty tree for this file.
		data.setPath(path);
		
		Map<String, Object> tmap1 = data.getTree();
		
		IDataHolder dh = ServiceHolder.getLoaderService().getData(path, null);
		
		final Map<String, Object> tmap2 = TreeToMapUtils.treeToMap(dh.getTree());

		assertTrue(tmap1.keySet().containsAll(tmap2.keySet()));
		System.out.println("Test Empty Tree Passed!");
	}

}
