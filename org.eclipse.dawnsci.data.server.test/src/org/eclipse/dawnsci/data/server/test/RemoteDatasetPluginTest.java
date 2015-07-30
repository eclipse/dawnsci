package org.eclipse.dawnsci.data.server.test;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.data.server.ServiceHolder;
import org.eclipse.dawnsci.slicing.api.data.ITransferableDataManager;
import org.eclipse.dawnsci.slicing.api.data.TransferableLazyDataset;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
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

				TransferableLazyDataset trans = new TransferableLazyDataset(data);
				trans.setChecked(true);
                man.addData(trans);
				
				final ISliceSystem system = (ISliceSystem)editor.getAdapter(ISliceSystem.class);
				
				for (int i = 0; i < 20; i++) {
					TestUtils.delay(1000);

					// We set the slice to the current end, so that the image refreshes.
					Display.getDefault().syncExec(new Runnable() {					
						@Override
						public void run() {
							system.setSliceIndex(0, data.getShape()[0]-1, true);
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
