package org.eclipse.dawnsci.remotedataset.client;

import java.io.IOException;

import org.eclipse.dawnsci.remotedataset.client.slice.SliceClient;
import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.SliceND;
import org.eclipse.january.io.ILazyLoader;

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
	public IDataset getDataset(IMonitor unused, SliceND slice) throws IOException {
		urlBuilder.setSlice(slice);
		final SliceClient<IDataset> client = new SliceClient<IDataset>(urlBuilder);
		IDataset ret;
		try {
			ret = client.get();
		} catch (Exception e) {
			throw new IOException(e);
		}
		return ret;
	}

}
