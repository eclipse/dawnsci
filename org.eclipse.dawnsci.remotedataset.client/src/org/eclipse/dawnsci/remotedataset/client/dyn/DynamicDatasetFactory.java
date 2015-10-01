package org.eclipse.dawnsci.remotedataset.client.dyn;

import java.awt.image.BufferedImage;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;

/**
 * DO NOT USE THIS CLASS FOR API. 
 * 
 * Instead if you need an MJPG Dataset use:
 * 
 * IRemoteDatasetService service = ... // OSGi
 * IDataset rgb = service.createMJPGDataset(...)
 * 
 * then plot RGB
 * 
 * @author fcp94556
 *
 */
public class DynamicDatasetFactory {

	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDynamicMonitorDataset createGreyScaleImage(SliceClient<BufferedImage> client, int... shape) {
		return new DynamicGreyScaleImage(client, shape);
	}
	
	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDynamicMonitorDataset createRGBImage(SliceClient<BufferedImage> client, int... shape) {
		return new DynamicRGBImage(client, shape);
	}
}
