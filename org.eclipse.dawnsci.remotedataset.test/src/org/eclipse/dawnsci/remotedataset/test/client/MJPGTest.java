package org.eclipse.dawnsci.remotedataset.test.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dawnsci.plotting.services.ImageService;
import org.dawnsci.plotting.services.PlotImageService;
import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.dataset.function.Downsample;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.junit.Before;
import org.junit.Test;

import uk.ac.diamond.scisoft.analysis.osgi.LoaderServiceImpl;

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
		ServiceHolder.setDownService(new Downsample());
		ServiceHolder.setImageService(new ImageService());
		ServiceHolder.setLoaderService(new LoaderServiceImpl());
		ServiceHolder.setPlotImageService(new PlotImageService());
	}
	
	@Test
	public void testMJPGEPICS() throws Exception {
	
		IRemoteDataset set = service.createMJPGDataset(new URL("http://ws157.diamond.ac.uk:8080/ADSIM.mjpg.mjpg"), 250, 10);
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
