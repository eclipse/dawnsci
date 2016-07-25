package org.eclipse.dawnsci.remotedataset.client.dyn;

import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IDynamicDataset;
import org.eclipse.january.dataset.IRemoteDataset;

/**
 * NON-API DO NOT USE OUTSIDE THIS BUNDLE
 * 
 * Instead if you need an MJPG Dataset use:
 * 
 * IRemoteDatasetService service = ... // OSGi
 * IDataset rgb = service.createMJPGDataset(...)
 * 
 * then plot RGB

 * 
 * @author Matthew Gerring
 *
 */
public interface IDynamicMonitorDataset extends IDataset, IDynamicDataset, IRemoteDataset {
	/**
	 * Internal use only. When the dataset changes, the internal API may update the 
	 * underlying data which the dataset is linked to.
	 * 
	 * @param newData
	 */
	public void setData(IDataset newData);
	/**
	 * Starts notifying the IDataListener's with the current 
	 * thread, blocking until there are no more images.
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception;
	/**
	 * Starts notifying the IDataListener's with the current 
	 * thread, blocking until there are no more images or if
	 * maxImages>0 until that number of images has been received.
	 * 
	 * @throws Exception
	 */
	public void start(int maxImages) throws Exception;
}
