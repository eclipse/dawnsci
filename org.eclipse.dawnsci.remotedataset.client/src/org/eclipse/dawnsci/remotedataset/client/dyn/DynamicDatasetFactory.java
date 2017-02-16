/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
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

import org.eclipse.dawnsci.remotedataset.client.slice.DynamicConnectionInfoExt;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;

/**
 * DO NOT USE THIS CLASS FOR API. 
 * 
 * Instead if you need an MJPG Dataset use:
 * 
 * IRemoteDatasetService service = ... // OSGi
 * IRemoteDataset rgb = service.createMJPGDataset(...)
 * 
 * then plot RGB
 * 
 * @author Matthew Gerring
 *
 */
public class DynamicDatasetFactory {

	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDynamicMonitorDatasetHolder createGreyScaleImage(SliceClient<BufferedImage> client, int... shape) {
		DynamicImage ret = new DynamicImage(false, client, shape);
		prepare(ret, client);
		return ret;
	}
	
	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDynamicMonitorDatasetHolder createRGBImage(SliceClient<BufferedImage> client, int... shape) {
		DynamicImage ret = new DynamicImage(true, client, shape);
		prepare(ret, client);
		return ret;
	}

	private static void prepare(IDynamicMonitorDatasetHolder ret, SliceClient<BufferedImage> client) {
		if (ret.getDataset() != null) {
			ret.getDataset().addMetadata(new DynamicConnectionInfoExt(client));
		}
	}
}

