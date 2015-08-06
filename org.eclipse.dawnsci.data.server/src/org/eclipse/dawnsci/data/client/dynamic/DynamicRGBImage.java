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
package org.eclipse.dawnsci.data.client.dynamic;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDatasetChangeChecker;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.data.client.slice.SliceClient;

/**
 * Class used to get a streaming image into the plotting system.
 * 
 * @author fcp94556
 *
 */
class DynamicRGBImage extends RGBDataset implements IDynamicMonitorDataset {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2512465878034055747L;

	private DataConnection<RGBDataset>            connection;
	
	private boolean dynamicShape=true;
	private int[] transShape;
	private int[] maxShape;

	/**
	 * 
	 * @param client the client used to create the connection, for instance MJPG
	 * @param shape the shape of the data if known, or do not set it if not.
	 */
	public DynamicRGBImage(SliceClient<BufferedImage> client, int... shape) {
		super(shape == null || shape.length<1 ? new int[]{1,1} : shape);
		this.connection= new DataConnection<RGBDataset>(getDtype(), false);
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
	public void setData(IDataset newData) {
		
		Serializable buffer = ((Dataset)newData).getBuffer();	
		odata = buffer;
		setData();
		if (dynamicShape) {
		    this.shape = newData.getShape();
		} else {
			this.transShape = newData.getShape();
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
	}

}
