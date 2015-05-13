package org.eclipse.dawnsci.data.server.test;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.data.client.RemoteDataset;
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
			
			final IRemoteDataset data = new RemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			try {
				Thread.sleep(10000);
				
			} finally {
				data.disconnect();
			}
			
		} finally {
			testIsRunning = false;
		}
	}

}
