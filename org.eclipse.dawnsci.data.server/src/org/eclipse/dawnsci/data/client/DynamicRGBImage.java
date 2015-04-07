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
package org.eclipse.dawnsci.data.client;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.DataListenerDelegate;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.plotting.api.image.IPlotImageService;
import org.eclipse.swt.widgets.Display;

/**
 * Class used to get a streaming image into the plotting system.
 * 
 * @author fcp94556
 *
 */
public class DynamicRGBImage extends RGBDataset implements IDynamicDataset {
	
	private static IPlotImageService plotService;
	public static void setPlotImageService(IPlotImageService service) {
		plotService = service;
	}
	public DynamicRGBImage() {
		// OSGi
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2512465878034055747L;

	private DataListenerDelegate      delegate;
	private DataClient<BufferedImage> client;
	
	/**
	 * 
	 * @param client the client used to create the connection, for instance MJPG
	 * @param shape the shape of the data if known, or do not set it if not.
	 */
	public DynamicRGBImage(DataClient<BufferedImage> client, int... shape) {
		super(shape == null || shape.length<1 ? new int[]{1,1} : shape);
		this.delegate = new DataListenerDelegate();
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
    		
    		final RGBDataset set = (RGBDataset)plotService.createDataset(image);
    		setBuffer(set.getBuffer());
    		setData();
    		shape = set.getShape();
    		delegate.fire(new DataEvent(set));
    		
    		++count;
    		if (count>maxImages) return;
    		
    		if (client.getSleep()>-1) {
    			delay(client.getSleep());
    		}
    	}

	}

	private void setBuffer(Serializable buffer) {
		odata = buffer;
	}
	
	@Override
	public void addDataListener(IDataListener l) {
		delegate.addDataListener(l);
	}

	@Override
	public void removeDataListener(IDataListener l) {
		delegate.removeDataListener(l);
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


}
