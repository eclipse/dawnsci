package org.eclipse.dawnsci.remotedataset.client.dyn;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDatasetChangeChecker;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.ShortDataset;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;

/**
 * Used for streaming an image into the plotting system.
 * @author fcp94556
 *
 */
class DynamicGreyScaleImage extends ShortDataset implements IDynamicMonitorDataset {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1581983742203718163L;

	
	private DataConnection<ShortDataset>            connection;


	private boolean dynamicShape=true;
	private int[] transShape;
	private int[] maxShape;
	
	/**
	 * 
	 * @param client the client used to create the connection, for instance MJPG
	 * @param shape the shape of the data if known, or do not set it if not.
	 */
	public DynamicGreyScaleImage(SliceClient<BufferedImage> client, int... shape) {
		super(shape == null || shape.length<1 ? new int[]{1,1} : shape);
		this.connection= new DataConnection<ShortDataset>(getDtype(), true);
		connection.setClient(client);
		connection.setDataset(this);
	}
	
	/**
	 * Starts notifying the IDataListener's with the current 
	 * thread, blocking until there are no more images.
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		start(-1);
	}
	/**
	 * Starts notifying the IDataListener's with the current 
	 * thread, blocking until there are no more images or if
	 * maxImages>0 until that number of images has been received.
	 * 
	 * @throws Exception
	 */
	public void start(int maxImages) throws Exception {
		connection.start(maxImages);
	}

	@Override
	public void setData(IDataset sdata) {
		
		Serializable buffer = ((Dataset)sdata).getBuffer();	
		
		odata = buffer;
		setData();
		if (dynamicShape) {
		    this.shape = sdata.getShape();
		} else {
			this.transShape = sdata.getShape();
		}
	}
	
	public void setShapeDynamic(boolean isDyn) {
		dynamicShape  = isDyn;
		if (dynamicShape && transShape!=null) {
		    this.shape = transShape;
		    transShape = null;
		}
	}
	
	@Override
	public void addDataListener(IDataListener l) {
		connection.addDataListener(l);
	}

	@Override
	public void removeDataListener(IDataListener l) {
		connection.removeDataListener(l);
	}

	public int[] getMaxShape() {
		if (maxShape==null) return getShape();
		return maxShape;
	}

	public void setMaxShape(int[] maxShape) {
		this.maxShape = maxShape;
	}

	@Override
	public void fireDataListeners() {
		// TODO add method to DataConnection
	}

	@Override
	public void startUpdateChecker(int milliseconds, IDatasetChangeChecker checker) {
		throw new IllegalArgumentException("Method not implemented. Use connect() instread!");
	}

	@Override
	public String getPath() {
		return connection.getClient().getPath();
	}

	@Override
	public void setPath(String path) {
		connection.getClient().setPath(path);
	}

	@Override
	public String getDataset() {
		return 	connection.getClient().getDataset();
	}

	@Override
	public void setDataset(String dataset) {
		connection.getClient().setDataset(dataset);
	}

	@Override
	public void connect() throws Exception {
		
		final Thread imageMonitor = new Thread(new Runnable() {
			public void run() {
				try {
					start(); // Just keep going until we are interrupted...
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		imageMonitor.setName("Monitor "+getName());
		imageMonitor.setDaemon(true);
		imageMonitor.setPriority(Thread.MIN_PRIORITY); // TODO Is that right?
		imageMonitor.start();
	}

	@Override
	public void disconnect() throws Exception {
		connection.getClient().setFinished(true);
	}
}
