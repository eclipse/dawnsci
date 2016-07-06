package org.eclipse.dawnsci.remotedataset.client.dyn;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.january.dataset.DatasetException;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataListener;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IDatasetChangeChecker;
import org.eclipse.january.dataset.ShortDataset;

/**
 * Used for streaming an image into the plotting system.
 * @author Matthew Gerring
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


	private Thread imageMonitor;
	
	/**
	 * 
	 * @param client the client used to create the connection, for instance MJPG
	 * @param shape the shape of the data if known, or do not set it if not.
	 */
	public DynamicGreyScaleImage(SliceClient<BufferedImage> client, int... shape) {
		super(shape == null || shape.length<1 ? new int[]{1,1} : shape);
		this.connection= new DataConnection<ShortDataset>(getDType(), true);
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
		
		Serializable buffer = DatasetUtils.convertToDataset(sdata).getBuffer();	
		
		odata = buffer;
		setData();
		if (dynamicShape) {
		    shape = sdata.getShape();
		    size = calcSize(shape);
		} else {
			this.transShape = sdata.getShape();
		}
	}
	
	public void setShapeDynamic(boolean isDyn) {
		dynamicShape  = isDyn;
		if (dynamicShape && transShape!=null) {
		    shape = transShape;
		    size = calcSize(shape);
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

	public void setMaxShape(int... maxShape) {
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
	public String connect() throws DatasetException {
		return connect(500, TimeUnit.MILLISECONDS);
	}
		
	@Override
	public String connect(long time, TimeUnit unit) throws DatasetException {

		if (imageMonitor!=null) throw new DatasetException("Cannot reconnect to already running dataset!");
		
		// Might be a bit overkill for this task
        final BlockingQueue<Exception> queue = new LinkedBlockingDeque<Exception>(1);
		this.imageMonitor = new Thread(new Runnable() {
			public void run() {
				try {
					start(); // Just keep going until we are interrupted...
				} catch (Exception e) {
					queue.add(e);
				}
			}
		});
		imageMonitor.setName("Monitor "+getName());
		imageMonitor.setDaemon(true);
		imageMonitor.setPriority(Thread.MIN_PRIORITY); // TODO Is that right?
		imageMonitor.start();
		
		Exception e = null;
		try {
			e = queue.poll(time, unit);
		} catch (InterruptedException e1) {
			e = e1;
		}
		if (e!=null) throw new DatasetException(e);
		
		return imageMonitor.getName(); // So that you can know if the runner is going.
	}

	@Override
	public void disconnect() throws DatasetException {
		connection.getClient().setFinished(true);
		imageMonitor = null;
	}

	@Override
	public void refreshShape() {
		//does nothing
		
	}
	public boolean isWritingExpected() {
		return connection.getClient().isWritingExpected();
	}

	public void setWritingExpected(boolean writingExpected) {
		connection.getClient().setWritingExpected(writingExpected);
	}

}
