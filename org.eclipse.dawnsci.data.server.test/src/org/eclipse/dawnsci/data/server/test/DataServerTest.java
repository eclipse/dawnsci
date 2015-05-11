package org.eclipse.dawnsci.data.server.test;

import java.io.File;

import org.dawnsci.plotting.services.ImageService;
import org.eclipse.dawnsci.data.server.DataServer;
import org.eclipse.dawnsci.data.server.ServiceHolder;
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

}
