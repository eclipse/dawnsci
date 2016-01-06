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
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
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
	public void testRemoteSlicingUsingSliceND() throws Exception {
		
		try {
			testIsRunning = true;
			final File h5File = startHDF5WritingThread(1000);
			
			final IRemoteDataset data = ServiceHolder.getRemoteDatasetService().createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();

			Thread.sleep(2000); // Let it get going
			
			for (int i = 0; i < 10; i++) {
				
				SliceND sliceND = SliceND.createSlice(data, new int[]{i,0,0}, new int[]{i+1,1024,1024},new int[]{1,1,1});
				IDataset slice = data.getSlice(sliceND);
                if (!Arrays.equals(slice.getShape(), new int[]{1,1024,1024})) {
                	throw new Exception("Incorrect remote slice! "+Arrays.toString(slice.getShape()));
                }
    			Thread.sleep(1000);
			}
			
		} finally {
			testIsRunning = false;
		}
	}

	@Test
	public void testHDF5FileMonitoring() throws Exception {
		
		try {
			testIsRunning = true;
			final File h5File = startHDF5WritingThread();
			
			final IRemoteDataset data = ServiceHolder.getRemoteDatasetService().createRemoteDataset("localhost", 8080);
			data.setPath(h5File.getAbsolutePath());
			data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
			data.connect();
		
			checkAndWait(data, 20000);
			
		} finally {
			testIsRunning = false;
		}
	}

	@Test
	public void testDirectoryMonitoring() throws Exception {
		try {
			testIsRunning = true;
			final File dir = startFileWritingThread(true);
			
			// Set the into, then call connect().
			final IRemoteDataset data = ServiceHolder.getRemoteDatasetService().createRemoteDataset("localhost", 8080);
			data.setPath(dir.getAbsolutePath());
			data.setDataset("image"); // We just get the first image in the PNG file.
			data.connect();
			
			checkAndWait(data, 20000);

		} finally {
			testIsRunning = false;
		}
	}
	
	@Test
	public void testImageFileMonitoring() throws Exception {
		
		try {
			testIsRunning = true;
			final File tmpData = startFileWritingThread(false);
						
			// Set the into, then call connect().
			IRemoteDataset data = ServiceHolder.getRemoteDatasetService().createRemoteDataset("localhost", 8080);
			data.setPath(tmpData.getAbsolutePath());
			data.setDataset(null); // We just get the first image in the PNG file.
			data.connect();
			
			// Check that we got the 1024x1024 as expected
			if (!Arrays.equals(data.getShape(), new int[]{1024,1024})) throw new Exception("Incorrect remote dataset size!");
			
			checkAndWait(data, 10000);
			
		} finally {
			testIsRunning = false;
		}
	}

	
	private void checkAndWait(final IRemoteDataset data, long time) throws Exception {
		
		try {
			final List<DataEvent> events = new ArrayList<DataEvent>((int)time/1000);
			
			// Check that we get events about the image changing.			
			data.addDataListener(new IDataListener() {
				@Override
				public void dataChangePerformed(DataEvent evt) {
					try {
						if (!Arrays.equals(evt.getShape(), data.getShape())) throw new Exception("Data shape and event shape are not the same!");
						System.out.println("Data changed, shape is "+Arrays.toString(evt.getShape()));
						events.add(evt);
					} catch (Exception ne) {
						ne.printStackTrace();
					}
				}
			});
	
			Thread.sleep(time);
			
			if (events.isEmpty()) throw new Exception("No data events returned while thread writing to file!");
			if (events.size() < ((time/1000)-5)) throw new Exception("Less data events than expected! Event count was "+events.size());
		
		} finally {
			data.disconnect();
		}
	}


}
