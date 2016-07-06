package org.eclipse.dawnsci.remotedataset.test.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.analysis.api.io.IRemoteDatasetService;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.nexus.NexusFile;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.RemoteDatasetServiceImpl;
import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyWriteableDataset;
import org.eclipse.january.dataset.IRemoteDataset;
import org.eclipse.january.dataset.LazyWriteableDataset;
import org.eclipse.january.dataset.Random;
import org.eclipse.january.dataset.Slice;
import org.eclipse.swt.graphics.ImageData;
import org.junit.Test;

public class RemoteDatasetSliceTest extends DataServerTest {

	// For some reason this test intermittently crashes the test VM on travis.
	//@Test
	public void testRemoteSliceDirectory() throws Exception {
		System.out.println("> testRemoteSliceDirectory start");
		System.out.flush();

		final File dir = createSomeDirectoryData(10, 64, 64);
		
		IRemoteDatasetService service = new RemoteDatasetServiceImpl();
		final IRemoteDataset data = service.createRemoteDataset("localhost", 8080);
		data.setPath(dir.getAbsolutePath());
		data.setDataset("Image Stack"); // We get the stack.
		data.connect();
		
        checkSlices(data);
		System.out.println("> testRemoteSliceDirectory ok");
	}
	


	private File createSomeDirectoryData(final int nimages, final int... shapeImage) throws Exception {

		final File ret = new File(File.createTempFile("temp_transient_file", ".png").getParentFile(), "test");
		ret.deleteOnExit();

		if (ret.exists()) TestUtils.recursiveDelete(ret.toPath());
		ret.mkdir();

		for (int i = 0; i < nimages; i++) {

			IDataset       rimage   = Random.rand(new int[]{64, 64});
			IImageService  iservice = ServiceHolder.getImageService();
			ImageServiceBean bean   = iservice.createBeanFromPreferences();
			bean.setImage(rimage);
			final ImageData   data  = iservice.getImageData(bean);
			final BufferedImage bi  = iservice.getBufferedImage(data);
			if (bi==null) continue;

			File file = new File(ret, "image_"+i+".png");
			file.getParentFile().mkdirs();
			file.createNewFile();
			file.deleteOnExit();

			ImageIO.write(bi, "PNG", file);
            System.out.println("> Wrote image "+file.getName());
		}

		return ret;
	}

	@Test
	public void testRemoteSliceH5() throws Exception {
		System.out.println("> testRemoteSliceH5 start");
		System.out.flush();
		final File h5File = createSomeH5Data(10, 64, 64);
		
		IRemoteDatasetService service = new RemoteDatasetServiceImpl();
		final IRemoteDataset data = service.createRemoteDataset("localhost", 8080);
		data.setPath(h5File.getAbsolutePath());
		data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
		data.connect();
		
		checkSlices(data);
		System.out.println("> testRemoteSliceH5 ok");
	}
	
	
	private void checkSlices(IRemoteDataset data) throws Exception {
		try { // New we have the opportunity to slice this remote blighter as much as we like...
			IDataset slice = data.getSlice(new Slice(0,1,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{1,64, 64})) throw new Exception("Wrong shape of remote data!");
			
			slice = data.getSlice(new Slice(0,5,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{5,64, 64})) throw new Exception("Wrong shape of remote data!");
			
			slice = data.getSlice(new Slice(0,1,1), new Slice(48,49,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{1,1,64})) throw new Exception("Wrong shape of remote data!");
			
			slice = data.getSlice(new Slice(0,5,1), new Slice(58,63,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{5,5,64})) throw new Exception("Wrong shape of remote data!");			
			
		} finally {
			data.disconnect();
		}		
	}

	private File createSomeH5Data(final int nimages, final int... shapeImage) throws Exception {

		final File ret = File.createTempFile("temp_transient_file", ".h5");
		ret.deleteOnExit();

		NexusFile file=null;
		try {
			file = factory.newNexusFile(ret.getAbsolutePath(), false);  // DO NOT COPY!
			file.openToWrite(true); // DO NOT COPY!

			GroupNode par = file.getGroup("/entry/data", true); // DO NOT COPY!
			
			final int[] shape = new int[]{1, shapeImage[0], shapeImage[1]};
			final int[] max   = new int[]{-1, shapeImage[0], shapeImage[1]};
			ILazyWriteableDataset writer = new LazyWriteableDataset("image", Dataset.FLOAT, shape, max, shape, null); // DO NOT COPY!
			file.createData(par, writer); 
			
			int index = 0;
			while(index<nimages) {

				int[] start = {index, 0, 0};
				int[] stop  = {index+1, 64, 64};
				index++;
				if (index>23) index = 23; // Stall on the last image to avoid writing massive stacks
				
				IDataset       rimage   = Random.rand(new int[]{1, 64, 64});
				rimage.setName("image");
				writer.setSlice(new IMonitor.Stub(), rimage, start, stop, null);
				file.flush();
				++index;
			}
		}  finally {
			try {
				if (file!=null) file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
       
        return ret;
	}

}
