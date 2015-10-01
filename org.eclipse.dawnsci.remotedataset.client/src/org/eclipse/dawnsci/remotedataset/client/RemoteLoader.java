package org.eclipse.dawnsci.remotedataset.client;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.ILazyLoader;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;

class RemoteLoader implements ILazyLoader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4031650917756857882L;
	
	private final URLBuilder urlBuilder;
		
	public RemoteLoader(URLBuilder urlBuilder) {
		this.urlBuilder = urlBuilder;
	}

	@Override
	public boolean isFileReadable() {
		return true;
	}

	@Override
	public IDataset getDataset(IMonitor unused, SliceND slice) throws Exception {
		urlBuilder.setSlice(slice);
		final SliceClient<IDataset> client = new SliceClient<IDataset>(urlBuilder);
		IDataset ret = client.get();
		return ret;
	}

}
