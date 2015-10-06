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

import java.net.URL;

import org.eclipse.dawnsci.remotedataset.Format;

public class StreamerFactory {

	public static IStreamer<?> getStreamer(URL url, long sleepTime, int cacheSize, Format format) throws Exception{
		
		if (format == Format.MJPG) {
			return new MJPGStreamer(url, sleepTime, cacheSize);
		} else if (format == Format.MDATA) {
			return new DataStreamer(url, sleepTime, cacheSize);
		}
		throw new Exception("No streamer for format "+format);
	}
}
