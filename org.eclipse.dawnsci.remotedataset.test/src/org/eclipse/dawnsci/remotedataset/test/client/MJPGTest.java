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
package org.eclipse.dawnsci.remotedataset.test.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.dataset.function.Downsample;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.dawnsci.remotedataset.test.mock.ImageServiceMock;
import org.eclipse.dawnsci.remotedataset.test.mock.LoaderServiceMock;
import org.eclipse.dawnsci.remotedataset.test.mock.PlotImageServiceMock;
import org.eclipse.january.dataset.DataEvent;
import org.eclipse.january.dataset.IDataListener;
import org.eclipse.january.dataset.IDatasetConnector;
import org.junit.Before;
import org.junit.Test;

/**
 * Test using MJPG datasets how they are supposed to be used.
 * 
 * @author Matthew Gerring
 *
 */
public class MJPGTest {

	private IRemoteDatasetService service;
	
	@Before
	public void createService() throws Exception {
		
		service = new RemoteDatasetServiceImpl(); // Normally this would come from OSGi
		
		// Sorry but the concrete classes for these services are not part of an eclipse project.
		// To get these concrete services go to dawnsci.org and follow the instructions for
		// setting up dawnsci to run in your application.
		new ServiceHolder().setDownService(new Downsample());
		new ServiceHolder().setImageService(new ImageServiceMock());
		new ServiceHolder().setLoaderService(new LoaderServiceMock()); // TODO Implement the mock to get the test working again.
		new ServiceHolder().setPlotImageService(new PlotImageServiceMock());
	}
	
	@Test
	public void testMJPGEPICS() throws Exception {
	
		IDatasetConnector set = service.createMJPGDataset(new URL("http://ws157.diamond.ac.uk:8080/ADSIM.mjpg.mjpg"), 250, 10);
		set.connect();
		
		try {
			final List<Integer> count = new ArrayList<>(1);
			count.add(0);
			
			set.addDataListener(new IDataListener() {	
				@Override
				public void dataChangePerformed(DataEvent evt) {
					System.out.println(count.get(0)+" images recevied!");
					count.set(0, count.get(0).intValue()+1);
				}
			});
			
			
			Thread.sleep(5000);
			
			if (count.get(0)<10) throw new Exception("Less images than expected from stream! "+count.get(0));
		
		} finally {
		    set.disconnect();
		}

	}
}
