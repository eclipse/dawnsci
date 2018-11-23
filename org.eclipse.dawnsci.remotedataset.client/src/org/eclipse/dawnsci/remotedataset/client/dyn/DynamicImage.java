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

package org.eclipse.dawnsci.remotedataset.client.dyn;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.eclipse.dawnsci.plotting.api.image.IPlotImageService;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.DataEvent;
import org.eclipse.january.dataset.DataListenerDelegate;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataListener;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IDatasetChangeChecker;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.RGBDataset;
import org.eclipse.january.dataset.ShortDataset;

/**
 * Used for streaming an image into the plotting system.
 * @author Matthew Gerring
 *
 */
class DynamicImage implements IDynamicMonitorDatasetHolder {
	
 	private boolean dynamicShape=true;
	private int[] transShape;
	private int[] maxShape;

	private Dataset dataset;

	private Thread imageMonitor;

	private boolean greyScale;

	private DataListenerDelegate delegate;
	private SliceClient<BufferedImage> client;

	/**
	 * @param isRGB
	 * @param client the client used to create the connection, for instance MJPG
	 * @param shape the shape of the data if known, or do not set it if not.
	 */
	public DynamicImage(boolean isRGB, SliceClient<BufferedImage> client, int... shape) {
		greyScale = !isRGB;
		dataset = greyScale ? DatasetFactory.zeros(ShortDataset.class, shape) : DatasetFactory.zeros(RGBDataset.class, shape);
		delegate = new DataListenerDelegate();
		this.client = client;
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

		int count = 0;
		while(!client.isFinished()) {

			final BufferedImage image = client.take();
			if (image==null) break;

			IPlotImageService plotImageService = ServiceHolder.getPlotImageService();
			if (plotImageService == null) {
				throw new NullPointerException("Plot image service not set");
			}
			Dataset im = DatasetUtils.convertToDataset(plotImageService.createDataset(image));
			if (greyScale && im instanceof RGBDataset) im = ((RGBDataset)im).getRedView();

			setDataset(im);

			delegate.fire(new DataEvent(im.getName(), im.getShape()));

			++count;
			if (count>maxImages && maxImages>-1) return;
			
			if (client.getSleep()>-1) {
				delay(client.getSleep());
			}
		}
	}

	public static void delay(long waitTimeMillis) throws InterruptedException {
		Thread.sleep(waitTimeMillis);
	}

	@Override
	public void setDataset(IDataset sdata) {
		Dataset data = greyScale ? DatasetUtils.cast(ShortDataset.class, sdata) : DatasetUtils.cast(RGBDataset.class, sdata);
		Serializable buffer = data.getBuffer();
		
		int[] shape = sdata.getShape();
		if (dynamicShape) {
			dataset.overrideInternal(buffer, shape);
		} else {
			dataset.overrideInternal(buffer, null);
			this.transShape = shape;
		}
	}

	@Override
	public ILazyDataset getDataset() {
		return dataset;
	}

	@Override
	public boolean resize(int... newShape) {
		return true;
	}

	public void setShapeDynamic(boolean isDyn) {
		dynamicShape  = isDyn;
		if (dynamicShape && transShape!=null) {
			dataset.overrideInternal(null, transShape);
		    transShape = null;
		}
	}

	@Override
	public void addDataListener(IDataListener l) {
		delegate.addDataListener(l);
	}

	@Override
	public void removeDataListener(IDataListener l) {
		delegate.removeDataListener(l);
	}

	@Override
	public void fireDataListeners() {
		// TODO add method to DataConnection
	}

	public int[] getMaxShape() {
		if (maxShape==null) return dataset.getShape();
		return maxShape;
	}

	public void setMaxShape(int... maxShape) {
		this.maxShape = maxShape;
	}

	@Override
	public void startUpdateChecker(int milliseconds, IDatasetChangeChecker checker) {
		throw new IllegalArgumentException("Method not implemented. Use connect() instread!");
	}

	@Override
	public String getPath() {
		return client.getPath();
	}

	@Override
	public void setPath(String path) {
		client.setPath(path);
	}

	@Override
	public String getDatasetName() {
		return client.getDataset();
	}

	@Override
	public void setDatasetName(String dataset) {
		client.setDataset(dataset);
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
		imageMonitor.setName("Monitor "+ dataset.getName());
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
		client.setFinished(true);
		imageMonitor = null;
	}

	@Override
	public boolean refreshShape() {
		return true;
	}

	public boolean isWritingExpected() {
		return client.isWritingExpected();
	}

	public void setWritingExpected(boolean writingExpected) {
		client.setWritingExpected(writingExpected);
	}
}
