package org.eclipse.dawnsci.remotedataset.client;

import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;

public class RemoteDatasetServiceImpl implements IRemoteDatasetService {

	static {
		System.out.println("Starting remote dataset service.");
	}
	@Override
	public IRemoteDataset createRemoteDataset(String serverName, int port) {
    	return new RemoteDataset(serverName, port);
	}

}
