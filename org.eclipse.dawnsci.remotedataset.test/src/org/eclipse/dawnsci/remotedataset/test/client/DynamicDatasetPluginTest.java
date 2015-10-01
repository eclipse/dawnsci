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
package org.eclipse.dawnsci.remotedataset.test.client;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Collection;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace.DownsampleType;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.dyn.DynamicDatasetFactory;
import org.eclipse.dawnsci.remotedataset.client.dyn.IDynamicMonitorDataset;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.dawnsci.remotedataset.test.server.DataServerTest;
import org.eclipse.dawnsci.remotedataset.test.server.TestUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.junit.Test;

/**
 * Runs as Junit Plugin test because runs up user interface with stream. 
 * Start the Data Server before running this test!
 * 
 * Hi All,

A number of people have asked me for an areaDetector simulation, so here you go.

•	From the Launcher, go to Utilities -> GDA AreaDetector Simulation
•	This will launch an EDM screen with a typical areaDetector GUI and a button to start the IOC
•	Clicking this button will start an IOC with the PV prefix set to $(hostname –s)-AD-SIM-01
•	The GUI will have the same PV prefix set
•	This means that you can run the GUI and IOC on the same machine and they will work together and not interfere with anyone else running the simulation
•	The CA server port is 6064, so make sure to export EPICS_CA_SERVER_PORT=6064

Thanks,
Tom Cobb
--

+44 (0)1235 778582

 * 
 */
public class DynamicDatasetPluginTest extends DataServerTest {
	
	/**
	 * Test opens stream in plotting system.
	 * NEED TO Start IOC on ws197 to run this test
	 * @throws Exception
	 */
	//@Test
	public void testDynamicDatasetEPICSGreyScale() throws Exception {
		
		// Requires an EPICS stream to connect to, not for general overnight testing!
		SliceClient<BufferedImage> client = new SliceClient<BufferedImage>(new URL("http://ws157.diamond.ac.uk:8080/ADSIM.mjpg.mjpg"));
		client.setGet(false);
    	client.setFormat(Format.MJPG);
    	client.setImageCache(10); // More than we will send...
    	client.setSleep(80);

    	IWorkbenchPart part = openView();
		
		final IPlottingSystem sys  = (IPlottingSystem)part.getAdapter(IPlottingSystem.class);
		
		final IDynamicMonitorDataset   grey = DynamicDatasetFactory.createGreyScaleImage(client);
		IImageTrace trace = (IImageTrace)sys.createPlot2D(grey, null, null);
		trace.setDownsampleType(DownsampleType.POINT); // Fast!

		grey.start(100); // blocks until 100 images received.
		
		System.out.println("Received images = "+client.getReceivedImageCount());
		System.out.println("Dropped images = "+client.getDroppedImageCount());

	}

	/**
	 * Test opens stream in plotting system.
	 * NEED TO Start IOC on ws197 to run this test
	 * @throws Exception
	 */
	@Test
	public void testDynamicDatasetEPICS() throws Exception {
		
		// Requires an EPICS stream to connect to, not for general overnight testing!
		SliceClient<BufferedImage> client = new SliceClient<BufferedImage>(new URL("http://ws157.diamond.ac.uk:8080/ADSIM.mjpg.mjpg"));
		client.setGet(false);
    	client.setFormat(Format.MJPG);
    	client.setImageCache(10);
    	client.setSleep(80);     
    	
    	IWorkbenchPart part = openView();
		 
		final IPlottingSystem   sys = (IPlottingSystem)part.getAdapter(IPlottingSystem.class);
		final IDynamicMonitorDataset rgb = DynamicDatasetFactory.createRGBImage(client);
		
		IImageTrace trace = (IImageTrace)sys.createPlot2D(rgb, null, null);
		trace.setDownsampleType(DownsampleType.POINT); // Fast!
		trace.setRescaleHistogram(false); // Fast! Comes from RGBData anyway though

		rgb.start(100); // blocks until 100 images received.
		
		System.out.println("Received images = "+client.getReceivedImageCount());
		System.out.println("Dropped images = "+client.getDroppedImageCount());

	}

	/**
	 * Test opens stream in plotting system.
	 * @throws Exception
	 */
	@Test
	public void testDynamicDataset() throws Exception {
		
		SliceClient<BufferedImage> client = new SliceClient<BufferedImage>("localhost", 8080);
    	client.setPath("RANDOM:512x512");
    	client.setFormat(Format.MJPG);
    	client.setHisto("MEAN");
    	client.setImageCache(10); // More than we will send...
    	client.setSleep(100);     // Default anyway is 100ms

    	IWorkbenchPart part = openView();
		 
		final IPlottingSystem sys = (IPlottingSystem)part.getAdapter(IPlottingSystem.class);
		final IDynamicMonitorDataset rgb = DynamicDatasetFactory.createRGBImage(client);
		sys.createPlot2D(rgb, null, null);

		rgb.start(100); // blocks until 100 images received.
	}
	
	/**
	 * Test opens stream in plotting system.
	 * @throws Exception
	 */
	@Test
	public void testHDF5Stream() throws Exception {
		
		IWorkbenchPart part = openView();
 
		final IPlottingSystem sys = (IPlottingSystem)part.getAdapter(IPlottingSystem.class);
		sys.createPlot2D(Random.rand(new int[]{1024, 1024}), null, null);
		
   		final Collection<ITrace>   traces= sys.getTraces(IImageTrace.class);
		final IImageTrace          imt = (IImageTrace)traces.iterator().next();
    	imt.setDownsampleType(DownsampleType.POINT); // Fast!
    	imt.setRescaleHistogram(false); // Fast!
    	     		
    	final SliceClient<BufferedImage> client = new SliceClient<BufferedImage>("localhost", 8080);
    	client.setPath("c:/Work/results/TomographyDataSet.hdf5");
    	client.setDataset("/entry/exchange/data");
    	client.setSlice("[700,:1024,:1024]");
    	client.setBin("MEAN:2x2");
    	client.setFormat(Format.MJPG);
    	client.setHisto("MEDIAN");
    	client.setImageCache(25); // More than we will send...
    	client.setSleep(100); // Default anyway is 100ms


    	try {
    		
    		int i = 0;
	    	while(!client.isFinished()) {
	
	    		final BufferedImage image = client.take();
	    		if (image==null) break;
	    		
	    		final IDataset set = ServiceHolder.getPlotImageService().createDataset(image);
	    		
	    		Display.getDefault().syncExec(new Runnable() {
	    			public void run() {
	    	    		imt.setData(set, null, false);
	    	    		sys.repaint();
	    			}
	    		});
	    		System.out.println("Slice "+i+" plotted");
	    		++i;
	    		TestUtils.delay(100);
	    	}
    	} catch (Exception ne) {
    		client.setFinished(true);
    		throw ne;
    	}

 		
	}
	
	
	/**
	 * Test opens stream in plotting system.
	 * @throws Exception
	 */
	@Test
	public void testStreamSpeed() throws Exception {
		
		IWorkbenchPart part = openView();
 
		final IPlottingSystem sys = (IPlottingSystem)part.getAdapter(IPlottingSystem.class);
		sys.createPlot2D(Random.rand(new int[]{1024, 1024}), null, null);
		
   		final Collection<ITrace>   traces= sys.getTraces(IImageTrace.class);
		final IImageTrace          imt = (IImageTrace)traces.iterator().next();
    	imt.setDownsampleType(DownsampleType.POINT); // Fast!
    	imt.setRescaleHistogram(false); // Fast!
    	   		
    	final SliceClient<BufferedImage> client = new SliceClient<BufferedImage>("localhost", 8080);
    	client.setPath("RANDOM:1024x1024");
    	client.setFormat(Format.MJPG);
    	client.setHisto("MEAN");
    	client.setImageCache(10); // More than we will send...
    	client.setSleep(15); // Default anyway is 100ms

    	try {
    		
    		int i = 0;
	    	while(!client.isFinished()) {
	
	    		final BufferedImage image = client.take();
	    		if (image==null) break;
	    		
	    		final IDataset set = ServiceHolder.getPlotImageService().createDataset(image);
	    		
	    		Display.getDefault().syncExec(new Runnable() {
	    			public void run() {
	    	    		imt.setData(set, null, false);
	    	    		sys.repaint();
	    			}
	    		});
	    		System.out.println("Slice "+i+" plotted");
	    		++i;
				if (i>100) {
					client.setFinished(true);
					break; // That's enough of that
				}
				TestUtils.delay(15);

	    	}
	    	
			System.out.println("Received images = "+i);
			System.out.println("Dropped images = "+client.getDroppedImageCount());

    	} catch (Exception ne) {
    		client.setFinished(true);
    		throw ne;
    	}

 		
	}


	private IWorkbenchPart openView() throws PartInitException {
		final IWorkbenchPage     page = TestUtils.getPage();		
		IViewPart part = page.showView("org.dawnsci.processing.ui.view.vanillaPlottingSystemView", null, IWorkbenchPage.VIEW_ACTIVATE);		
 		page.activate(part);
 		page.setPartState(page.getActivePartReference(), IWorkbenchPage.STATE_MAXIMIZED);
 		return part;
	}


}
