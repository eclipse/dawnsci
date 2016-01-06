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
	public static IDynamicMonitorDataset createGreyScaleImage(SliceClient<BufferedImage> client, int... shape) {
		DynamicGreyScaleImage ret = new DynamicGreyScaleImage(client, shape);
		prepare(ret, client);
		return ret;
	}
	
	/**
	 * 
	 * @param client
	 * @param shape
	 * @return
	 */
	public static IDynamicMonitorDataset createRGBImage(SliceClient<BufferedImage> client, int... shape) {
		DynamicRGBImage ret = new DynamicRGBImage(client, shape);
		prepare(ret, client);
		return ret;
	}

	private static void prepare(IDynamicMonitorDataset ret, SliceClient<BufferedImage> client) {
		ret.addMetadata(new DynamicConnectionInfoExt(client));
	}

}

