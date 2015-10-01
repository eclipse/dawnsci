package org.eclipse.dawnsci.remotedataset.test.client.dyn;

import java.awt.image.BufferedImage;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.DataListenerDelegate;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.plotting.api.image.IPlotImageService;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.dawnsci.remotedataset.server.ServiceHolder;
import org.eclipse.swt.widgets.Display;

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
			
			Dataset rgb = (Dataset)ServiceHolder.getPlotImageService().createDataset(image);
			if (greyScale) rgb = ((RGBDataset)rgb).getRedView();
			
			IDataset set = rgb.cast(dType);
			dataset.setData((T)set);
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

	public static void delay(long waitTimeMillis) {
		
		if (Display.getDefault() != null) {

			Display display = Display.getDefault();

			// If this is the UI thread,
			// then process input.
			long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis) {
				try {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				} catch (Exception ne) {
					try {
						Thread.sleep(waitTimeMillis);
					} catch (InterruptedException e) {
						// Ignored
					}
					break;
				}
			}
			display.update();
			
		} else {
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// Ignored.
			}
		}
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
