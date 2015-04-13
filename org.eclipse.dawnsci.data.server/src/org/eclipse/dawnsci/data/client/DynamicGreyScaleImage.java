package org.eclipse.dawnsci.data.client;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.ShortDataset;

/**
 * Used for streaming an image into the plotting system.
 * @author fcp94556
 *
 */
public class DynamicGreyScaleImage extends ShortDataset implements IDynamicDataset<RGBDataset> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1581983742203718163L;

	
	private DataConnection            connection;
	
	/**
	 * 
	 * @param client the client used to create the connection, for instance MJPG
	 * @param shape the shape of the data if known, or do not set it if not.
	 */
	public DynamicGreyScaleImage(DataClient<BufferedImage> client, int... shape) {
		super(shape == null || shape.length<1 ? new int[]{1,1} : shape);
		this.connection= new DataConnection();
		connection.setClient(client);
		connection.setDataset(this);
	}
	
	/**
	 * Starts notifying the IDataListener's with the current 
	 * thread, blocking until there are no more images.
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		start(-1);
	}
	/**
	 * Starts notifying the IDataListener's with the current 
	 * thread, blocking until there are no more images or if
	 * maxImages>0 until that number of images has been received.
	 * 
	 * @throws Exception
	 */
	public void start(int maxImages) throws Exception {
		connection.start(maxImages);
	}

	@Override
	public void setData(RGBDataset newData) {
		
		ShortDataset sdata = ((RGBDataset)newData).getRedView();
		Serializable buffer = sdata.getBuffer();
		
		odata = buffer;
		setData();
		this.shape = sdata.getShape();
	}
	
	@Override
	public void addDataListener(IDataListener l) {
		connection.addDataListener(l);
	}

	@Override
	public void removeDataListener(IDataListener l) {
		connection.removeDataListener(l);
	}
	
}
