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
package org.eclipse.dawnsci.remotedataset.client.streamer;

/**
 * Interface used to stream either JPG or IDataset from
 * server stream.
 * 
 * @author Matthew Gerring
 *
 * @param <T>
 */
public interface IStreamer<T> {

	/**
	 * Start the streamer
	 */
	void start();

	/**
	 * Gets the image
	 * @return
	 */
	T take()  throws InterruptedException ;

	/**
	 * Gets the count of dropped images from this stream
	 * @return
	 */
	long getDroppedImageCount();

	/**
	 * Gets the count of received images from this stream
	 * @return
	 */
	long getReceivedImageCount();

	/**
	 * Call to stop streamer from streaming.
	 * @param b
	 */
	void setFinished(boolean b);

}
