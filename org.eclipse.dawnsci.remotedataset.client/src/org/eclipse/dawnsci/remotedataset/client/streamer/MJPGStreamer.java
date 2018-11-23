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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;

import javax.imageio.ImageIO;

class MJPGStreamer extends AbstractNonCachingStreamer<BufferedImage> {

	/**
	 * 
	 * @param url - URL to read from
	 * @param sleepTime - time to sleep between image reads, we don't want to use all CPU
	 * @throws Exception
	 */
	public MJPGStreamer(URL url, long sleepTime) throws Exception {
		init(url, sleepTime);
	}
	
	private static BufferedImage QUEUE_END = new BufferedImage(1, 1, 1);
	
	@Override
	protected BufferedImage getQueueEndObject() {
		return QUEUE_END;
	}

	@Override
	protected BufferedImage getFromStream(ByteArrayInputStream bais) throws Exception {
		return ImageIO.read(bais);
	}
}