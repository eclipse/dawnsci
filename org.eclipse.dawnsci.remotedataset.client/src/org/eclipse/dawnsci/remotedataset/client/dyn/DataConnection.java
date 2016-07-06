package org.eclipse.dawnsci.remotedataset.client.dyn;

import java.awt.image.BufferedImage;

import org.eclipse.dawnsci.plotting.api.image.IPlotImageService;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.january.dataset.DataEvent;
import org.eclipse.january.dataset.DataListenerDelegate;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataListener;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IDynamicDataset;
import org.eclipse.january.dataset.RGBDataset;

class DataConnection<T extends IDataset> {
	

	public DataConnection() {
		// OSGi
	}
	
	private SliceClient<BufferedImage>   client;
	private DataListenerDelegate        delegate;
	private IDynamicMonitorDataset      dataset;
	private int dType;
	private boolean greyScale;
	
	public DataConnection(int dType, boolean greyScale) {
		this.delegate = new DataListenerDelegate();
		this.dType    = dType; // Should match parameterized type
		this.greyScale= greyScale; 
	}

	public void start(int maxImages) throws Exception {

		int count = 0;
		while(!client.isFinished()) {

			final BufferedImage image = client.take();
			if (image==null) break;

			IPlotImageService plotImageService = ServiceHolder.getPlotImageService();
			if (plotImageService == null) {
				throw new NullPointerException("Plot image service not set");
			}
			Dataset rgb = DatasetUtils.convertToDataset(plotImageService.createDataset(image));
			if (greyScale) rgb = ((RGBDataset)rgb).getRedView();

			IDataset set = rgb.cast(dType);
			dataset.setData(set);
			delegate.fire(new DataEvent(set.getName(), set.getShape()));

			++count;
			if (count>maxImages && maxImages>-1) return;
			
			if (client.getSleep()>-1) {
				delay(client.getSleep());
			}
		}
	}
	public SliceClient<BufferedImage> getClient() {
		return client;
	}
	public void setClient(SliceClient<BufferedImage> client) {
		this.client = client;
	}

	public static void delay(long waitTimeMillis) throws InterruptedException {
		Thread.sleep(waitTimeMillis);
	}

	public void addDataListener(IDataListener l) {
		delegate.addDataListener(l);
	}

	public void removeDataListener(IDataListener l) {
		delegate.removeDataListener(l);
	}


	public IDynamicDataset getDataset() {
		return dataset;
	}


	public void setDataset(IDynamicMonitorDataset dataset) {
		this.dataset = dataset;
	}
	

}
