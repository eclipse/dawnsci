package org.eclipse.dawnsci.remotedataset.client.slice;

import java.awt.image.BufferedImage;

import org.eclipse.january.metadata.DynamicConnectionInfo;

/**
 * Not for use outside package. Bridges SliceClient to DynamicConnectionInfo
 * 
 * @author Matthew Gerring
 *
 */
public class DynamicConnectionInfoExt extends DynamicConnectionInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8129875989888252784L;
	
	private transient SliceClient<BufferedImage> client;

	public DynamicConnectionInfoExt(SliceClient<BufferedImage> client) {
		this.client = client;
	}
	public boolean isConnected() {
		return !client.isFinished();
	}

	public long getReceivedCount() {
		return client.getReceivedImageCount();
	}

	public long getDroppedCount() {
		return client.getDroppedImageCount();
	}

}