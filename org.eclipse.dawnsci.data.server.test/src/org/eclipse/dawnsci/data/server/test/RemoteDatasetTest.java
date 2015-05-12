package org.eclipse.dawnsci.data.server.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.data.client.RemoteDataset;
import org.eclipse.dawnsci.data.server.ServiceHolder;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.swt.graphics.ImageData;
import org.junit.Test;

/**
 * Test which writes to a local file random data every timeinterval
 * and checks that the DataServer sents events.
 * 
 * @author fcp94556
 *
 */
public class RemoteDatasetTest extends DataServerTest {
	
	
	private volatile boolean testIsRunning = false;

	@Test
	public void testDirectoryMonitoring() throws Exception {
		try {
			testIsRunning = true;
			final File dir = startFileWritingThread(true);
			
			// Set the into, then call connect().
			IRemoteDataset data = new RemoteDataset("localhost", 8080);
			data.setPath(dir.getAbsolutePath());
			data.setDataset("image"); // We just get the first image in the PNG file.
			data.connect();
						
			// Check that we get events about the image changing.			
			data.addDataListener(new IDataListener() {
				@Override
				public void dataChangePerformed(DataEvent evt) {
					try {
					    System.out.println("Data changed, shape is "+Arrays.toString(evt.getShape()));
					} catch (Exception ne) {
						ne.printStackTrace();
					}
				}
			});

			Thread.sleep(20000);
			
			data.disconnect();

		} finally {
			testIsRunning = false;
		}
	}
	
	@Test
	public void testFileMonitoring() throws Exception {
		
		try {
			testIsRunning = true;
			final File tmpData = startFileWritingThread(false);
						
			// Set the into, then call connect().
			IRemoteDataset data = new RemoteDataset("localhost", 8080);
			data.setPath(tmpData.getAbsolutePath());
			data.setDataset(null); // We just get the first image in the PNG file.
			data.connect();
			
			// Check that we got the 1024x1024 as expected
			if (!Arrays.equals(data.getShape(), new int[]{1024,1024})) throw new Exception("Incorrect remote dataset size!");
			
			// Check that we get events about the image changing.			
			data.addDataListener(new IDataListener() {
				@Override
				public void dataChangePerformed(DataEvent evt) {
					try {
					    System.out.println("Data changed, shape is "+Arrays.toString(evt.getShape()));
					} catch (Exception ne) {
						ne.printStackTrace();
					}
				}
			});

			Thread.sleep(10000);
			
			data.disconnect();
			
		} finally {
			testIsRunning = false;
		}
	}

	private File startFileWritingThread(final boolean dir) throws IOException, InterruptedException {
		
        final File ret = dir
        		       ? new File(File.createTempFile("temp_transient_file", ".png").getParentFile(), "test")
        		       : File.createTempFile("temp_transient_file", ".png");
        ret.deleteOnExit();
        
        if (dir) {
        	if (ret.exists()) TestUtils.recursiveDelete(ret.toPath());
        	ret.mkdir();
        }
        
        final Thread runner = new Thread(new Runnable() {
        	public void run() {
        		
        		int index = 0;
        		while(testIsRunning) {
        			
        			try {
	        			IDataset       rimage   = Random.rand(new int[]{1024, 1024});
	        			IImageService  iservice = ServiceHolder.getImageService();
	        			ImageServiceBean bean   = iservice.createBeanFromPreferences();
	        			bean.setImage(rimage);
	        			final ImageData   data  = iservice.getImageData(bean);
	        			final BufferedImage bi  = iservice.getBufferedImage(data);
	        			
	        			File file = dir
	        					  ? new File(ret, "image_"+index+".png")
	        				      : ret;
	        			file.deleteOnExit();
	        			index++;
	        			
	        			ImageIO.write(bi, "PNG", file);
	        			
	        			Thread.sleep(1000);
	        			System.out.println(">> Thread wrote "+file.getAbsolutePath());
	        			
        			} catch (Exception ne) {
        				ne.printStackTrace();
        				break;
        			}
        			
        		}
        	}
        });
        runner.setPriority(Thread.MIN_PRIORITY);
        runner.setDaemon(true);
        runner.start();
        
		// Wait for a bit to ensure file is being written
		Thread.sleep(2000);

        return ret;
	}
}
