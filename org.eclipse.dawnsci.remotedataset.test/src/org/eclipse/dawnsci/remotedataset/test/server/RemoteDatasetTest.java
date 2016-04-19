package org.eclipse.dawnsci.remotedataset.test.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test which writes to a local file random data every timeinterval
 * and checks that the DataServer sents events.
 * 
 * @author Matthew Gerring
 *
 */
public class RemoteDatasetTest extends DataServerTest {
	
	@Test
	public void testHDF5FileMonitoring() throws Exception {
		
		IRemoteDataset data = null;
		try {
			testIsRunning = true;
			final File h5File = startHDF5WritingThread(100);
			Thread.sleep(400);
			
			IRemoteDatasetService service = new RemoteDatasetServiceImpl();
			data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			checkAndWait(data, 2000, 100, 2); // This one is unreliable so we reduced the required events.
			
			System.out.println("> testHDF5FileMonitoring ok");
		} finally {
			testIsRunning = false;
			if (data!=null) data.disconnect();
		}
	}
	
	//@Test
	public void testRemoteSlicingUsingSliceND() throws Exception {
		
		IRemoteDataset data = null;
		try {
			testIsRunning = true;
			
			final long freq = 100;
			final File h5File = startHDF5WritingThread(freq);
			Thread.sleep(4*freq); // Let it get going
			
			IRemoteDatasetService service = new RemoteDatasetServiceImpl();
			data = service.createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
			
			Thread.sleep(2*freq); // Let it get going
			
			for (int i = 0; i < 10; i++) {
				
				System.out.println("Shape is "+Arrays.toString(data.getShape()));
				System.out.println("i = "+i);
				SliceND sliceND = SliceND.createSlice(data, new int[]{i,0,0}, new int[]{i+1,1024,1024},new int[]{1,1,1});
				IDataset slice = data.getSlice(sliceND);
				if (slice == null) throw new Exception("Unable to get slice from "+data.getName()+". Index is "+i);
                if (!Arrays.equals(slice.getShape(), new int[]{1,1024,1024})) {
                	throw new Exception("Incorrect remote slice! "+Arrays.toString(slice.getShape()));
                }
    			Thread.sleep(freq);
			}
			System.out.println("> testRemoteSlicingUsingSliceND ok");
			
		} finally {
			testIsRunning = false;
			if (data!=null) data.disconnect();
		}
	}

	@Test
	public void testDirectoryMonitoring() throws Exception {
		
		IRemoteDataset data = null;
		try {
			testIsRunning = true;
			final File dir = startFileWritingThread(100, true);
			Thread.sleep(1000);
			
			// Set the into, then call connect().
			IRemoteDatasetService service = new RemoteDatasetServiceImpl();
		    data =service.createRemoteDataset("localhost", 8080);
			data.setPath(dir.getAbsolutePath());
			data.setDataset("Image Stack"); // We just get the first image in the PNG file.
			data.connect();
			
			checkAndWait(data, 1000, 100);
			System.out.println("> testDirectoryMonitoring ok");

		} finally {
			testIsRunning = false;
			data.disconnect();
		}
	}
	
	@Test
	public void testImageFileMonitoring() throws Exception {
		
		IRemoteDataset data = null;
		try {
			testIsRunning = true;
			final File tmpData = startFileWritingThread(500, false);
			Thread.sleep(1000);
					
			// Set the into, then call connect().
			IRemoteDatasetService service = new RemoteDatasetServiceImpl();
			data =service.createRemoteDataset("localhost", 8080);
			data.setPath(tmpData.getAbsolutePath());
			data.setDataset(null); // Should get the dataset at the position of 0
			data.connect();
			
			// Check that we got the 1024x1024 as expected
			if (!Arrays.equals(data.getShape(), new int[]{1024,1024})) throw new Exception("Incorrect remote dataset size!");
			
			checkAndWait(data, 5000, 500);
			System.out.println("> testImageFileMonitoring ok");
			
		} finally {
			testIsRunning = false;
			data.disconnect();
		}
	}

	private void checkAndWait(final IRemoteDataset data, long time, long imageTime) throws Exception {
		final int count = (int)time/(int)imageTime;
		checkAndWait(data, time, imageTime, count-6);
	}
	
	private void checkAndWait(final IRemoteDataset data, long time, long imageTime, int min) throws Exception {
		
		final int count = (int)time/(int)imageTime;
		try {
			final List<DataEvent> events = new ArrayList<DataEvent>(count);
			
			// Check that we get events about the image changing.			
			data.addDataListener(new IDataListener() {
				@Override
				public void dataChangePerformed(DataEvent evt) {
					try {
						System.out.println("Data changed, shape is "+Arrays.toString(evt.getShape()));
						if (!Arrays.equals(evt.getShape(), data.getShape())) {
							throw new Exception("Data shape and event shape are not the same!");
						}
						events.add(evt);
					} catch (Exception ne) {
						ne.printStackTrace();
					}
				}
			});
	
			Thread.sleep(time);
			
			if (events.size() < min) throw new Exception("Less data events than expected! Event count was "+events.size()+" Min expected was "+min);
		
		} finally {
			data.disconnect();
		}
	}


}
