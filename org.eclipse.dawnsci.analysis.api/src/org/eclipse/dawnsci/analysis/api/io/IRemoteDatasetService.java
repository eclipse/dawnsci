/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.io;

import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;

/**
 * A service used to create connections to remote datasets.
 */
public interface IRemoteDatasetService {

	/**
		<usage><code>
		final IRemoteDatasetService service = ...
		final IRemoteDataset data = service.createRemoteDataset("localhost", 8080);<br>
		data.setPath(h5File.getAbsolutePath());
		data.setDataset("image"); // We just get the first image in the PNG file.
		data.connect();
		
		try {
		    // Use it the same way as ILazyDataset
		} finally {
		    data.disconnect();
		}
		</code></usage>
	 * @param serverName
	 * @param port
	 * @return dataset
	 */
	public IRemoteDataset createRemoteDataset(String serverName, int port);
}
