package org.eclipse.dawnsci.remotedataset.test.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.dawnsci.plotting.services.ImageService;
import org.dawnsci.plotting.services.PlotImageService;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.dawnsci.remotedataset.server.DataServer;
import org.eclipse.swt.graphics.ImageData;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import uk.ac.diamond.scisoft.analysis.dataset.function.Downsample;
import uk.ac.diamond.scisoft.analysis.osgi.LoaderServiceImpl;

public class DataServerTest {

	protected static DataServer server;
	protected static String     testDir;
	protected static int        port;

	/**
	 * Programmatically start the DataServer OSGi application which runs
	 * under Jetty and starts jetty itself.
	 * @throws Exception 
	 */
	@BeforeClass
	public static void startDataServer() throws Exception {
		
		// Sorry but the concrete classes for these services are not part of an eclipse project.
		// To get these concrete services go to dawnsci.org and follow the instructions for
		// setting up dawnsci to run in your application.
		ServiceHolder.setDownService(new Downsample());
		ServiceHolder.setImageService(new ImageService());
		ServiceHolder.setLoaderService(new LoaderServiceImpl());
		ServiceHolder.setPlotImageService(new PlotImageService());
		ServiceHolder.setRemoteDatasetService(new RemoteDatasetServiceImpl());
	
        // Start the DataServer
		port   = TestUtils.getFreePort(8080);
		server = new DataServer();
		server.setPort(port);
		server.start(false);
		
		System.out.println("Started DataServer on port "+port);
		
		File pluginDir = new File((new File("")).getAbsolutePath()); // Assuming test run in test plugin
		testDir = (new File(pluginDir, "testfiles")).getAbsolutePath();
	}
	
	@AfterClass
	public static void stop() {
		server.stop();
	}

	
	protected volatile boolean testIsRunning = false;

	protected File startHDF5WritingThread() throws IOException, InterruptedException {
		return startHDF5WritingThread(1000L);
	}

	protected File startHDF5WritingThread(final long sleepTime) throws IOException, InterruptedException {
		
        final File ret = File.createTempFile("temp_transient_file", ".h5");
        ret.deleteOnExit();
         
        final Thread runner = new Thread(new Runnable() {
        	public void run() {
        		
        		try {
         			while(testIsRunning) {

         				IHierarchicalDataFile file=null;
         				try {
                			file = HierarchicalDataFactory.getWriter(ret.getAbsolutePath());
               			 
       					    IDataset       rimage   = Random.rand(new int[]{1024, 1024});
        					rimage.setName("image");
        					
        					file.group("/entry");
        					file.group("/entry/data");
        					String path = file.appendDataset(rimage.getName(), rimage, "/entry/data");

        					Thread.sleep(sleepTime);
        					System.out.println(">> HDF5 wrote image to "+path);

        				} catch (Exception ne) {
        					ne.printStackTrace();
        					break;
        				} finally {
                			try {
        						if (file!=null) file.close();
        					} catch (Exception e) {
        						e.printStackTrace();
        					}
                		}
        			}
        			
        		} catch (Exception ne) {
        			ne.printStackTrace();
        			
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

	protected File startFileWritingThread(final boolean dir) throws IOException, InterruptedException {
		
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
