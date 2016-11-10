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

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.dawnsci.remotedataset.test.mock.LoaderServiceMock;
import org.eclipse.january.dataset.IDataset;
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
public class SliceClientTest extends DataServerTest {
	
	
	@Before
	@Override
	public void setLoader() {
		ServiceHolder.setLoaderService(new LoaderServiceMock(factory));
	}

	@Test
	public void testFullData() throws Exception {
		System.out.println("> testFullData start");
		System.out.flush();
		final SliceClient<IDataset> client = new SliceClient<IDataset>("localhost", port);
		client.setPath(testDir+"/export.h5");
		client.setDataset("/entry/edf/data");
		client.setSlice("[0,:2048,:2048]");

		final IDataset data = client.get();
		if (!Arrays.equals(data.getShape(), new int[]{2048, 2048})) {
			throw new Exception("Unexpected shape "+Arrays.toString(data.getShape()));
		}
		System.out.println("> testFullData ok");
	}

	@Test
	public void testDownsampledData() throws Exception {
		System.out.println("> testDownsampledData start");
		System.out.flush();
		final SliceClient<IDataset> client = new SliceClient<IDataset>("localhost", port);
		client.setPath(testDir+"/export.h5");
		client.setDataset("/entry/edf/data");
		client.setSlice("[0,:2048,:2048]");
		client.setBin("MEAN:4x4");
		
		final IDataset data = client.get();
		if (!Arrays.equals(data.getShape(), new int[]{512, 512})) {
			throw new Exception("Unexpected shape "+Arrays.toString(data.getShape()));
		}
		System.out.println("> testDownsampledData ok");
	}
	
	@Test
	public void testDownsampledJPG() throws Exception {
		System.out.println("> testDownsampledJPG start");
		System.out.flush();
		final SliceClient<BufferedImage> client =  new SliceClient<BufferedImage>("localhost", port);
		client.setPath(testDir+"/export.h5");
		client.setDataset("/entry/edf/data");
		client.setSlice("[0,:2048,:2048]");
		client.setBin("MEAN:4x4");
		client.setFormat(Format.JPG);
		client.setHisto("MEAN");
		
		final BufferedImage image = client.get();
		if (image.getHeight()!=512) throw new Exception("Unexpected image height '"+image.getHeight()+"'");
		if (image.getWidth()!=512)  throw new Exception("Unexpected image height '"+image.getWidth()+"'");
		
		System.out.println("> testDownsampledJPG ok");
	}

	
	@Test
	public void testDownsampledMJPG() throws Exception {
		System.out.println("> testDownsampledMJPG start");
		System.out.flush();
		final SliceClient<BufferedImage> client =  new SliceClient<BufferedImage>("localhost", port);
		client.setPath(testDir+"/export.h5");
		client.setDataset("/entry/edf/data");
		client.setSlice("[0,:2048,:2048]");
		client.setBin("MEAN:4x4");
		client.setFormat(Format.MJPG);
		client.setHisto("MEAN");
		client.setSleep(100); // Default anyway is 100ms
		
		
		int i = 0;
		while(!client.isFinished()) {
			
			final BufferedImage image = client.take();
			if (image ==null) break; // Last image in stream is null.
			if (image.getHeight()!=512) throw new Exception("Unexpected image height '"+image.getHeight()+"'");
			if (image.getWidth()!=512)  throw new Exception("Unexpected image height '"+image.getWidth()+"'");
			++i;
			System.out.println("Image "+i+" found");
		}
	
		if (i != 4) throw new Exception("4 images were not found! "+i+" were!");
		System.out.println("> testDownsampledMJPG ok");
	}
	
	@Test
	public void testFastMJPG() throws Exception {
		System.out.println("> testFastMJPG start");
		System.out.flush();
		final SliceClient<BufferedImage> client =  new SliceClient<BufferedImage>("localhost", port);
		client.setPath("RANDOM:512x512");
		client.setFormat(Format.MJPG);
		client.setHisto("MEAN");
		client.setSleep(10); // 100Hz - she's a fast one!
		
		int i = 0;
		while(!client.isFinished()) {
			
			final BufferedImage image = client.take();
			if (image ==null) break; // Last image in stream is null.
			if (image.getHeight()!=512) throw new Exception("Unexpected image height '"+image.getHeight()+"'");
			if (image.getWidth()!=512)  throw new Exception("Unexpected image height '"+image.getWidth()+"'");
			++i;
			if (i>100) {
				client.setFinished(true);
				break; // That's enough of that
			}
			
			Thread.sleep(80);// Evil sleep means that take() is not as fast as send and there will be drops.
		}
	
		// We say
		System.out.println("Received images = "+i);
		System.out.println("Dropped images = "+client.getDroppedImageCount());
		System.out.println("> testFastMJPG ok");
	}

	@Test
	public void testFastMDATA() throws Exception {
		System.out.println("> testFastMDATA start");
		System.out.flush();
		final SliceClient<IDataset> client = new SliceClient<IDataset>("localhost", port);
		client.setPath("RANDOM:512x512");
		client.setFormat(Format.MDATA);
		client.setHisto("MEAN");
		client.setSleep(10); // 100Hz - she's a fast one!
		
		int i = 0;
		while(!client.isFinished()) {
			
			final IDataset image = client.take();
			if (image ==null) break; // Last image in stream is null.
			if (image.getShape()[0]!=512) throw new Exception("Unexpected image height '"+image.getShape()[0]+"'");
			if (image.getShape()[1]!=512)  throw new Exception("Unexpected image height '"+image.getShape()[1]+"'");
			++i;
			if (i>100) {
				client.setFinished(true);
				break; // That's enough of that
			}
			
			Thread.sleep(80);// Evil sleep means that take() is not as fast as send and there will be drops.
		}
	
		// We say
		System.out.println("Received images = "+i);
		System.out.println("Dropped images = "+client.getDroppedImageCount());
		System.out.println("> testFastMDATA ok");
	}
}
