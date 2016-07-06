package org.eclipse.dawnsci.remotedataset.test.server;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.dawnsci.remotedataset.server.DataServerMode;
import org.eclipse.dawnsci.remotedataset.server.DiagnosticInfo;
import org.eclipse.january.dataset.IRemoteDataset;
import org.junit.Test;

/**
 * 
 * This class attempts to find out how many threads and file events
 * are generated when listening to a file and check that there are not
 * too many.
 * 
 * @author Matthew Gerring
 * 
 *
 */
public class FileMonitoringTest extends DataServerTest {

	@Test
	public void testHDF5FileConnections() throws Exception {
		System.out.println("> testHDF5FileConnections start");
		System.out.flush();
		// We force the DataServer into diagnostic mode.
		server.setMode(DataServerMode.DIAGNOSTIC);
		
		// Connect to five different chaning files and ensure that only one thread is there.
		for (int i = 0; i < 5; i++) {
			doConnectionAndDisconnect(i, true);
		}
		
		Thread.sleep(1000); // Give it a chance to close out.
		
		DiagnosticInfo info = server.getDiagnosticInfo();
		assertTrue("The started thread count was "+info.getCount("Start Thread")+" and should have been 5 or less", info.getCount("Start Thread")<=5);
		assertTrue("The closed thread count was "+info.getCount("Close Thread")+" and should have been 5 or less", info.getCount("Close Thread")<=5);
		System.out.println("> testHDF5FileConnections ok");
	}
	
	@Test
	public void testHDF5FileConnectionsNoListener() throws Exception {
		System.out.println("> testHDF5FileConnectionsNoListener start");
		System.out.flush();
		// We force the DataServer into diagnostic mode.
		server.setMode(DataServerMode.DIAGNOSTIC);
		
		// Connect to five different chaining files and ensure that only one thread is there.
		doConnectionAndDisconnect(0, false);

		Thread.sleep(1000); // Give it a chance to close out.

		DiagnosticInfo info = server.getDiagnosticInfo();
		assertTrue("There should be no file monitor threads started", info.getCount("Start Thread")==0);
		assertTrue("There should be no file monitor threads closed", info.getCount("Close Thread")==0);
		System.out.println("> testHDF5FileConnectionsNoListener ok");
	}


	private void doConnectionAndDisconnect(int index, boolean checkListen) throws Exception {
		
		IRemoteDataset data = null;
		File h5File = null;
		try {
			testIsRunning = true;
			h5File = startHDF5WritingThread(100);
			Thread.sleep(400);
			
			IRemoteDatasetService service = new RemoteDatasetServiceImpl();
			data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			if (checkListen) {
				checkAndWait(data, 2000, 100, 1); // This one is unreliable so we reduced the required events.
			} else {
				Thread.sleep(2000);
			}
			
			DiagnosticInfo info = server.getDiagnosticInfo();
			
			if (checkListen) {
				assertTrue("The started thread count is "+info.getCount("Start Thread")+" and the closed is "+info.getCount("Close Thread"), 
						(info.getCount("Start Thread")-info.getCount("Close Thread"))<=1); // One file changing, there should be one thread.
				assertTrue("The closed thread count "+info.getCount("Close Thread")+" is greater than the maximum allowed; "+index, info.getCount("Close Thread")<=index+1); // Ensure all closed picked up.
			} 
			System.out.println(info);

		} finally {
			testIsRunning = false;
			if (data!=null) data.disconnect();
			if (h5File!=null) h5File.delete();
		}
	}

}
