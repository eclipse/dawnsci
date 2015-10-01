package org.eclipse.dawnsci.remotedataset.client;

import java.awt.image.BufferedImage;
import java.net.URL;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.dawnsci.remotedataset.client.dyn.DynamicDatasetFactory;
import org.eclipse.dawnsci.remotedataset.client.dyn.IDynamicMonitorDataset;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;

public class RemoteDatasetServiceImpl implements IRemoteDatasetService {

	static {
		System.out.println("Starting remote dataset service.");
	}
	@Override
	public IRemoteDataset createRemoteDataset(String serverName, int port) {
    	return new RemoteDataset(serverName, port);
	}
	
	@Override
	public IDataset createMJPGDataset(URL url, long sleepTime, int cacheSize) throws Exception {
		
		SliceClient<BufferedImage> client = new SliceClient<BufferedImage>(url);
		client.setGet(false);
    	client.setFormat(Format.MJPG);
    	client.setImageCache(cacheSize); // More than we will send...
    	client.setSleep(sleepTime);     
    	
		final IDynamicMonitorDataset rgb = DynamicDatasetFactory.createRGBImage(client);
		
		// TODO Do we need a way of stopping this thread?
		final Thread imageMonitor = new Thread(new Runnable() {
			public void run() {
				try {
					rgb.start(); // Just keep going until we are interrupted...
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		imageMonitor.setName("Monitor "+url);
		imageMonitor.setDaemon(true);
		imageMonitor.setPriority(Thread.MIN_PRIORITY); // TODO Is that right?
		imageMonitor.start();
 
    	return rgb;
	}

}
