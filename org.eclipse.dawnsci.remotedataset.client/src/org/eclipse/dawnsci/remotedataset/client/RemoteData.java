/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.Executor;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.remotedataset.XMLMarshallerService;
import org.eclipse.january.dataset.IDatasetConnector;
import org.eclipse.january.dataset.IRemoteData;

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
			String xml = reader.readLine();
			IMarshallerService marshaller = new XMLMarshallerService();
			return (Map<String, Object>)marshaller.unmarshal(xml, Map.class);

		} finally {
			reader.close();
		}
	}

	@Override
	public IDatasetConnector createRemoteDataset(String datasetPath) throws Exception {
		IDatasetConnector set = service.createRemoteDataset(urlBuilder.getServerName(), urlBuilder.getPort());
		set.setDatasetName(datasetPath);
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
