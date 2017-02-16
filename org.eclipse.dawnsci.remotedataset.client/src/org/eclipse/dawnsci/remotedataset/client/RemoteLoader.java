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
