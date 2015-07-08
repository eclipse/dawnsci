package org.eclipse.dawnsci.data.server.test;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.data.server.ServiceHolder;
import org.eclipse.dawnsci.slicing.api.data.ITransferableDataManager;
import org.eclipse.dawnsci.slicing.api.data.TransferableLazyDataset;
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
			
			IRemoteDatasetService service = ServiceHolder.getRemoteDatasetService();
			final IRemoteDataset data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			try {
				// We open a random part then 
				// 1. copy in the remote dataset which we are currently writing to
				// 2. plot data from it
                IEditorPart  editor = TestUtils.openExternalEditor(testDir+"/export.h5");
                ITransferableDataManager man = (ITransferableDataManager)editor.getAdapter(ITransferableDataManager.class);
                
				TestUtils.delay(1000); // Wait while plot sorts itself out

                man.addData(new TransferableLazyDataset(data));
				
				TestUtils.delay(20000);
				
			} finally {
				data.disconnect();
			}
			
		} finally {
			testIsRunning = false;
		}
	}

}
