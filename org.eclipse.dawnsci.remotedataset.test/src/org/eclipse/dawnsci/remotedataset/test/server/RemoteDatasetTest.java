package org.eclipse.dawnsci.remotedataset.test.server;

import java.io.File;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.hdf5.HDF5FileFactory;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IRemoteDataset;
import org.eclipse.january.dataset.SliceND;
import org.junit.Before;
import org.junit.Test;

/**
 * Test which writes to a local file random data every timeinterval
 * and checks that the DataServer sents events.
 * 
 * @author Matthew Gerring
 *
 */
public class RemoteDatasetTest extends DataServerTest {
	
	private IRemoteDatasetService service;
	
	@Before
	public void before() {
		service = new RemoteDatasetServiceImpl(); // Instead of OSGi - do not copy!
	}

	
	@Test
	public void testHDF5FileMonitoring() throws Exception {
		
		IRemoteDataset data = null;
		File h5File=null;
		try {
			System.out.println("> testHDF5FileMonitoring start");
			System.out.flush();
			testIsRunning = true;
			h5File = startHDF5WritingThread(100);
			Thread.sleep(400);
			
			data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			checkAndWait(data, 2000, 100, 2); // This one is unreliable so we reduced the required events.
			
			System.out.println("> testHDF5FileMonitoring ok");
		} finally {
			testIsRunning = false;
			if (data!=null) data.disconnect();
			if (h5File!=null) h5File.delete();
		}
	}

	//@Test
	public void testRemoteSlicingUsingSliceND() throws Exception {
		IRemoteDataset data = null;
		File h5File = null;
		try {
			HDF5FileFactory.setVerbose(true);
			System.out.println("> testRemoteSlicingUsingSliceND start");
			System.out.flush();
			testIsRunning = true;
			
			final long freq = 100;
		    h5File = startHDF5WritingThread(freq);
			Thread.sleep(4*freq); // Let it get going
			
			data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
			
			Thread.sleep(2*freq); // Let it get going
			
			for (int i = 0; i < 10; i++) {
				
				System.err.println("Shape is "+Arrays.toString(data.getShape()));
				System.err.println("i = "+i);
				SliceND sliceND = SliceND.createSlice(data, new int[]{i,0,0}, new int[]{i+1,64,64},new int[]{1,1,1});
				IDataset slice = data.getSlice(sliceND);
				if (slice == null) throw new Exception("Unable to get slice from "+data.getName()+". Index is "+i);
				if (!Arrays.equals(slice.getShape(), new int[] { 1, 64, 64 })) {
					throw new Exception("Incorrect remote slice! " + Arrays.toString(slice.getShape()));
				}
				Thread.sleep(freq);
			}
			System.out.println("> testRemoteSlicingUsingSliceND ok");
			
		} finally {
			testIsRunning = false;
			if (data!=null) data.disconnect();
			if (h5File!=null)h5File.delete();
			HDF5FileFactory.setVerbose(false);
		}
	}

	@Test
	public void testDirectoryMonitoring() throws Exception {
		
		IRemoteDataset data = null;
		File dir=null;
		try {
			System.out.println("> testDirectoryMonitoring start");
			System.out.flush();
			testIsRunning = true;
			dir = startFileWritingThread(100, true);
			Thread.sleep(1000);
			
			// Set the into, then call connect().
			data =service.createRemoteDataset("localhost", 8080);
			data.setPath(dir.getAbsolutePath());
			data.setDataset("Image Stack"); // We just get the first image in the PNG file.
			data.connect();
			
			checkAndWait(data, 1000, 100);
			System.out.println("> testDirectoryMonitoring ok");

		} finally {
			testIsRunning = false;
			data.disconnect();
			if (dir!=null) TestUtils.recursiveDelete(dir);
		}
	}
	
	@Test
	public void testImageFileMonitoring() throws Exception {
		
		IRemoteDataset data = null;
		File tmpData =null;
		try {
			System.out.println("> testImageFileMonitoring start");
			System.out.flush();
			testIsRunning = true;
			tmpData = startFileWritingThread(500, false);
			Thread.sleep(1000);

			// Set the into, then call connect().
			data =service.createRemoteDataset("localhost", 8080);
			data.setPath(tmpData.getAbsolutePath());
			data.setDataset(null); // Should get the dataset at the position of 0
			data.connect();
			
			// Check that we got the 64x64 as expected
			if (!Arrays.equals(data.getShape(), new int[]{64,64})) throw new Exception("Incorrect remote dataset size!");
			
			checkAndWait(data, 5000, 500);
			System.out.println("> testImageFileMonitoring ok");
			
		} finally {
			testIsRunning = false;
			data.disconnect();
			if (tmpData!=null) tmpData.delete();
		}
	}
}
