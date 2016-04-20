package org.eclipse.dawnsci.remotedataset.test.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyWriteableDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.dataset.function.Downsample;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyWriteableDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.hdf5.nexus.NexusFileFactoryHDF5;
import org.eclipse.dawnsci.nexus.INexusFileFactory;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.server.DataServer;
import org.eclipse.dawnsci.remotedataset.test.mock.ImageServiceMock;
import org.eclipse.dawnsci.remotedataset.test.mock.LoaderServiceMock;
import org.eclipse.dawnsci.remotedataset.test.mock.PlotImageServiceMock;
import org.eclipse.swt.graphics.ImageData;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * 
 * <pre>
 * To run this test from the IDE or it's subsclasses:
 * o Run as junit test (will fail)
 * o Add all org.eclipse.jetty and javax.serlvet to classpath
 * o Set LD_LIBRARY_PATH(linux) or PATH(windows) to:
 *     ${project_loc:uk.ac.diamond.CBFlib}/lib/${target.os}-${target.arch};${project_loc:ncsa.hdf}/lib/${target.os}-${target.arch}
 * </pre>
 * 
 * @author Matthew Gerring
 *
 */
public class DataServerTest {

	protected static INexusFileFactory   factory;
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
		
		factory = new NexusFileFactoryHDF5();
		
		// Sorry but the concrete classes for these services are not part of an eclipse project.
		// To get these concrete services go to dawnsci.org and follow the instructions for
		// setting up dawnsci to run in your application.
		ServiceHolder.setDownService(new Downsample());
		ServiceHolder.setImageService(new ImageServiceMock());
		ServiceHolder.setPlotImageService(new PlotImageServiceMock());
	
        // Start the DataServer
		port   = TestUtils.getFreePort(8080);
		
		server = new DataServer();
		server.setPort(port);
		server.start();
		
		System.out.println("Started DataServer on port "+port);
		
		File pluginDir = new File((new File("")).getAbsolutePath()); // Assuming test run in test plugin
		testDir = (new File(pluginDir, "testfiles")).getAbsolutePath();
	}
	
	@Before
	public void setLoader() {
		ServiceHolder.setLoaderService(new LoaderServiceMock(factory, "/entry/data/image"));
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
        		
        		NexusFile file=null;
        		try {
        			file = factory.newNexusFile(ret.getAbsolutePath(), false);  // DO NOT COPY!
        			file.openToWrite(true); // DO NOT COPY!

        			GroupNode par = file.getGroup("/entry/data", true); // DO NOT COPY!
        			
        			final int[] shape = new int[]{1, 1024, 1024};
        			final int[] max   = new int[]{-1, 1024, 1024};
        			ILazyWriteableDataset writer = new LazyWriteableDataset("image", Dataset.FLOAT, shape, max, shape, null); // DO NOT COPY!
        			file.createData(par, writer); 

        			int index = 0;
        			while(testIsRunning) {

        				int[] start = {index, 0, 0};
        				int[] stop  = {index+1, 1024, 1024};
        				index++;
        				if (index>23) index = 23; // Stall on the last image to avoid writing massive stacks
        				
        				IDataset       rimage   = Random.rand(new int[]{1, 1024, 1024});
        				rimage.setName("image");
       				    writer.setSlice(new IMonitor.Stub(), rimage, start, stop, null);
       				    file.flush();
       				    
       				    Thread.sleep(sleepTime);
        				System.out.println("> HDF5 wrote image to "+ret);
        				System.out.println("> New shape "+getShape(ret, "/entry/data/image"));
        			}
       			
        		} catch (Exception ne) {
        			ne.printStackTrace();
        			
				} finally {
					try {
						file.close();
					} catch (NexusException e) {
						e.printStackTrace();
					}
				}
        	}
        });
        runner.setPriority(Thread.MIN_PRIORITY);
        runner.setDaemon(true);
        runner.start();
        
		// Wait for a bit to ensure file is being written
		Thread.sleep(2*sleepTime);

        return ret;
	}

	protected String getShape(File ret, String path) throws Exception {
		final ILoaderService lservice = ServiceHolder.getLoaderService();
		IDataHolder holder = lservice.getData(ret.getAbsolutePath(), new IMonitor.Stub());
		return Arrays.toString(holder.getLazyDataset(path).getShape());
	}

	protected File startFileWritingThread(final long waitTime, final boolean dir) throws IOException, InterruptedException {
		
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
	        			
	        			Thread.sleep(waitTime);
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
		Thread.sleep(2*waitTime);

        return ret;
	}

	

	protected void checkAndWait(final IRemoteDataset data, long time, long imageTime) throws Exception {
		final int count = (int)time/(int)imageTime;
		checkAndWait(data, time, imageTime, count-6);
	}
	
	protected void checkAndWait(final IRemoteDataset data, long time, long imageTime, int min) throws Exception {
		
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
