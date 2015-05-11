package org.eclipse.dawnsci.data.client.dynamic;

import java.awt.image.BufferedImage;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.data.client.slice.SliceClient;

public class DynamicDatasetFactory {

	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDataset createGreyScaleImage(SliceClient<BufferedImage> client, int... shape) {
		return new DynamicGreyScaleImage(client, shape);
	}
	
	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDataset createRGBImage(SliceClient<BufferedImage> client, int... shape) {
		return new DynamicRGBImage(client, shape);
	}

	/**
	 * Listens to the image source for a finite number of images.
	 * @param grey
	 * @param i either not set or set to a single number (> 0) that is the maximum image count which should be received.
	 */
	public static void start(IDataset data, int... i) throws Exception {
		if (i==null) {
			((IDynamicMonitorDataset)data).start();
		} else {
			((IDynamicMonitorDataset)data).start(i[0]);
		}
	}
}
