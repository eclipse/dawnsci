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
package org.eclipse.dawnsci.remotedataset.test.server;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.dawnsci.slicing.api.data.ITransferableDataManager;
import org.eclipse.dawnsci.slicing.api.data.TransferableLazyDataset;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
import org.eclipse.january.dataset.IDatasetConnector;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.junit.Test;


/**
 * This test attempts open some GUI with a remote dataset that is being written too.
 * 
 * The 
 * 
 * @author Matthew Gerring
 *
 */
public class RemoteDatasetPluginTest extends DataServerTest {

	@Test
	public void testHDF5FileMonitoring() throws Exception {
		
		try {
			testIsRunning = true;
			final File h5File = startHDF5WritingThread(); // This is an HDF5 file which is growing as a thread writes it.
			Thread.sleep(2000);
			
			IRemoteDatasetService service = new RemoteDatasetServiceImpl();
			final IDatasetConnector data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDatasetName("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			try {
				// We open a random part then 
				// 1. copy in the remote dataset which we are currently writing to
				// 2. plot data from it
                IEditorPart  editor = TestUtils.openExternalEditor(testDir+"/export.h5");
                ITransferableDataManager man = (ITransferableDataManager)editor.getAdapter(ITransferableDataManager.class);
                
				TestUtils.delay(1000); // Wait while plot sorts itself out

				TransferableLazyDataset trans = new TransferableLazyDataset(data.getDataset());
				trans.setChecked(true);
                man.addData(trans);
				
				final ISliceSystem system = (ISliceSystem)editor.getAdapter(ISliceSystem.class);
				
				for (int i = 0; i < 20; i++) {
					TestUtils.delay(1000);

					// We set the slice to the current end, so that the image refreshes.
					Display.getDefault().syncExec(new Runnable() {					
						@Override
						public void run() {
							system.setSliceIndex(0, data.getDataset().getShape()[0]-1, true);
						}
					});
				}

				
			} finally {
				data.disconnect();
			}
			
		} finally {
			testIsRunning = false;
		}
	}

}
