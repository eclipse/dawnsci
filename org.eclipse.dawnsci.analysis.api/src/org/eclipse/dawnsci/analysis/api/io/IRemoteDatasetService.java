/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.io;

import java.net.URL;

import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;

/**
 * A service used to create connections to remote datasets.
 * The Remote Datasets created will be in connection with the DataServer running in the acquisition server.
 * There is also a facility to create a DynamicDataset directly. In this case a connection is
 * made to an MJPG stream at a given URL.
 * 
 * 		<usage><code>
		final IRemoteDatasetService service = ...
		final IRemoteDataset data = service.createRemoteDataset("localhost", 8080);
		data.setPath(h5File.getAbsolutePath());
		data.setDataset("image"); // We just get the first image in the PNG file.
		data.connect();
		
		try {
		    // Use it the same way as ILazyDataset
		} finally {
		    data.disconnect();
		}
		</code></usage>
 * 
 */
public interface IRemoteDatasetService {

	/**
     * 
	 * @param serverName
	 * @param port
	 * @return dataset
	 */
	public IRemoteDataset createRemoteDataset(String serverName, int port);

	
	/**
	 * Create an MJPG dataset at the given stream. The Dataset returned will be updated
	 * as the stream changes. The Dataset will be an RBG Image and implements IDynamicDataset and IRemoteDataset
	 * You must cast the IDataset to IRemoteDataset and then call connect() to start the monitor thread.
     *
	 * 
	 * @param url to MJPG stream for instance http://ws157.diamond.ac.uk:8080/ADSIM.mjpg.mjpg
	 * @param sleepTime - time to sleep between image reads, we don't want to use all CPU
	 * @param cacheSize - size of image cache. If image cache grows too large, they are DROPPED.
	 * 
	 * @return A DynamicDataset for instance one looking at a changing data source like
	 * an MJPG stream. This dataset is loaded data unlike the one above. It may be cast with impunity to IDataset
	 */
	public IRemoteDataset createMJPGDataset(URL url, long sleepTime, int cacheSize) throws Exception;


	/**
	 * Create a gray scale MJPG dataset at the given stream. The Dataset returned will be updated
	 * as the stream changes. The Dataset will be a gray scale Image and implements IDynamicDataset and IRemoteDataset
	 * You must cast the IDataset to IRemoteDataset and then call connect() to start the monitor thread.
     *
	 * 
	 * @param url to MJPG stream for instance http://ws157.diamond.ac.uk:8080/ADSIM.mjpg.mjpg
	 * @param sleepTime - time to sleep between image reads, we don't want to use all CPU
	 * @param cacheSize - size of image cache. If image cache grows too large, they are DROPPED.
	 * 
	 * @return A DynamicDataset for instance one looking at a changing data source like
	 * an MJPG stream. This dataset is loaded data unlike the one above. It may be cast with impunity to IDataset
	 */
	public IRemoteDataset createGrayScaleMJPGDataset(URL url, long sleepTime, int cacheSize) throws Exception;
}
