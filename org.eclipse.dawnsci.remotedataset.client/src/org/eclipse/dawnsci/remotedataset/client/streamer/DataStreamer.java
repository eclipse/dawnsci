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

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ShortDataset;

class DataStreamer extends AbstractStreamer<IDataset> {
	
	/**
	 * 
	 * @param url - URL to read from
	 * @param sleepTime - time to sleep between image reads, we don't want to use all CPU
	 * @param cacheSize - size of image cache. If image cache grows too large, they are DROPPED.
	 * @throws Exception
	 */
	public DataStreamer(URL url, long sleepTime, int cacheSize) throws Exception {
		init(url, sleepTime, cacheSize);
	}
	
	private static IDataset QUEUE_END = DatasetFactory.zeros(ShortDataset.class, null);
	
	@Override
	protected IDataset getQueueEndObject() {
		return QUEUE_END;
	}

	@Override
	protected IDataset getFromStream(ByteArrayInputStream bais) throws Exception {
		ObjectInputStream oin = new ObjectInputStream(bais);
		try {
			return (IDataset)oin.readObject();
		} finally {
			oin.close();
		}
	}


}
