package org.eclipse.dawnsci.remotedataset.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.Executor;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.january.dataset.IRemoteData;
import org.eclipse.january.dataset.IRemoteDataset;

class RemoteData implements IRemoteData {

	private String path;
	private URLBuilder urlBuilder;
	private Executor exec;
	private IRemoteDatasetService service;

	public RemoteData(IRemoteDatasetService service, String serverName, int port, Executor exec) {
		this.urlBuilder = new URLBuilder(serverName, port);
		urlBuilder.setWritingExpected(true);
		this.exec       = exec;
		this.service    = service;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTree() throws Exception {
		
		urlBuilder.setGet(true);
		final URL url = new URL(urlBuilder.getTreeURL());
		URLConnection  conn = url.openConnection();

		final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		try {
			String json = reader.readLine();
			IMarshallerService marshaller = RemoteDatasetServiceImpl.getMarshallerService();
			return (Map<String, Object>)marshaller.unmarshal(json, Map.class);

		} finally {
			reader.close();
		}
	}

	@Override
	public IRemoteDataset createRemoteDataset(String datasetPath) throws Exception {
		IRemoteDataset set = service.createRemoteDataset(urlBuilder.getServerName(), urlBuilder.getPort());
		set.setDataset(datasetPath);
		set.setPath(getPath());
		return set;
	}

	public String getPath() {
		return urlBuilder.getPath();
	}

	public void setPath(String path) {
		urlBuilder.setPath(path);
	}

}
