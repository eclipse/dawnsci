package org.eclipse.dawnsci.data.client.dataset;

import java.awt.image.BufferedImage;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.DataListenerDelegate;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.data.client.DataClient;
import org.eclipse.dawnsci.plotting.api.image.IPlotImageService;
import org.eclipse.swt.widgets.Display;

class DataConnection<T extends IDataset> {
	
	private static IPlotImageService plotService;
	public static void setPlotImageService(IPlotImageService service) {
		plotService = service;
	}
	public DataConnection() {
		// OSGi
	}
	
	private DataClient<BufferedImage>   client;
	private DataListenerDelegate        delegate;
	private IDynamicDataset             dataset;
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
			
			Dataset rgb = (Dataset)plotService.createDataset(image);
			if (greyScale) rgb = ((RGBDataset)rgb).getRedView();
			
			IDataset set = rgb.cast(dType);
			dataset.setData((T)set);
			delegate.fire(new DataEvent(set));
			
			++count;
			if (count>maxImages) return;
			
			if (client.getSleep()>-1) {
				delay(client.getSleep());
			}
		}
	}
	public DataClient<BufferedImage> getClient() {
		return client;
	}
	public void setClient(DataClient<BufferedImage> client) {
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


	public void setDataset(IDynamicDataset dataset) {
		this.dataset = dataset;
	}
	

}
