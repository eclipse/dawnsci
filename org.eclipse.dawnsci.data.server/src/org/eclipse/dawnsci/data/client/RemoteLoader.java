package org.eclipse.dawnsci.data.client;

import java.net.URI;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.ILazyLoader;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

public class RemoteLoader implements ILazyLoader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4031650917756857882L;
	
	private URI uri;

	public RemoteLoader(URI uri) {
		this.uri = uri;
	}

	@Override
	public boolean isFileReadable() {
		return true;
	}

	@Override
	public IDataset getDataset(IMonitor unused, SliceND slice) throws Exception {
		// TODO Make a SliceND implementation on the slice servlet
		return null;
	}

}
