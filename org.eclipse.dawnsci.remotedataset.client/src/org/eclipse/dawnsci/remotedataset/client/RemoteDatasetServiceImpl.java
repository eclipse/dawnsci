package org.eclipse.dawnsci.remotedataset.client;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.dawnsci.remotedataset.client.dyn.DynamicDatasetFactory;
import org.eclipse.dawnsci.remotedataset.client.dyn.IDynamicMonitorDatasetHolder;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.january.dataset.IRemoteData;
import org.eclipse.january.dataset.IDatasetConnector;

public class RemoteDatasetServiceImpl implements IRemoteDatasetService {
	
	static {
		System.out.println("Starting remote dataset service.");
	}
	@Override
	public IDatasetConnector createRemoteDataset(String serverName, int port) {
    	return new RemoteDataset(serverName, port, getExecutor());
	}
	
	@Override
	public IDatasetConnector createMJPGDataset(URL url, long sleepTime, int cacheSize) throws Exception {
		SliceClient<BufferedImage> client = getSlice(url, sleepTime, cacheSize);
		final IDynamicMonitorDatasetHolder rgb = DynamicDatasetFactory.createRGBImage(client);
    	return rgb;
	}

	@Override
	public IDatasetConnector createGrayScaleMJPGDataset(URL url, long sleepTime, int cacheSize) throws Exception {
		SliceClient<BufferedImage> client = getSlice(url, sleepTime, cacheSize);
		final IDynamicMonitorDatasetHolder rgb = DynamicDatasetFactory.createGreyScaleImage(client);
    	return rgb;
	}

	private SliceClient<BufferedImage> getSlice(URL url, long sleepTime, int cacheSize) {
		SliceClient<BufferedImage> client = new SliceClient<BufferedImage>(url);
		client.setGet(false);
    	client.setFormat(Format.MJPG);
    	client.setImageCache(cacheSize); // More than we will send...
    	client.setSleep(sleepTime);
		return client;
	}

	private static Executor executor;
	
	@Override
	public void setExecutor(Executor exec) {
		executor = exec;
	}

	@Override
	public Executor getExecutor() {
		if (executor==null) executor = Executors.newCachedThreadPool();
		return executor;
	}

	@Override
	public IRemoteData createRemoteData(String serverName, int port) {
		return new RemoteData(this, serverName, port, getExecutor());
	}
}
