package org.eclipse.dawnsci.remotedataset.test.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.remotedataset.server.ServiceHolder;
import org.eclipse.swt.graphics.ImageData;
import org.junit.Test;

public class RemoteDatasetSliceTest extends DataServerTest {

	@Test
	public void testRemoteSliceDirectory() throws Exception {
		
		final File h5File = createSomeDirectoryData(10, 1024, 1024);
		
		final IRemoteDataset data = ServiceHolder.getRemoteDatasetService().createRemoteDataset("localhost", 8080);
		data.setPath(h5File.getAbsolutePath());
		data.setDataset("image_"); // We just get the first image in the PNG file.
		data.connect();
		
        checkSlices(data);
	}
	


	private File createSomeDirectoryData(final int nimages, final int... shapeImage) throws Exception {

		final File ret = new File(File.createTempFile("temp_transient_file", ".png").getParentFile(), "test");
		ret.deleteOnExit();

		if (ret.exists()) TestUtils.recursiveDelete(ret.toPath());
		ret.mkdir();

		for (int i = 0; i < nimages; i++) {

			IDataset       rimage   = Random.rand(new int[]{1024, 1024});
			IImageService  iservice = ServiceHolder.getImageService();
			ImageServiceBean bean   = iservice.createBeanFromPreferences();
			bean.setImage(rimage);
			final ImageData   data  = iservice.getImageData(bean);
			final BufferedImage bi  = iservice.getBufferedImage(data);

			File file = new File(ret, "image_"+i+".png");
			file.deleteOnExit();

			ImageIO.write(bi, "PNG", file);

		}

		return ret;
	}

	@Test
	public void testRemoteSliceH5() throws Exception {
		
		final File h5File = createSomeH5Data(10, 1024, 1024);
		
		final IRemoteDataset data = ServiceHolder.getRemoteDatasetService().createRemoteDataset("localhost", 8080);
		data.setPath(h5File.getAbsolutePath());
		data.setDataset("/entry/data/image"); // We just get the first image in the PNG file.
		data.connect();
		
		checkSlices(data);
	}
	
	
	private void checkSlices(IRemoteDataset data) throws Exception {
		try { // New we have the opportunity to slice this remote blighter as much as we like...
			IDataset slice = data.getSlice(new Slice(0,1,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{1,1024, 1024})) throw new Exception("Wrong shape of remote data!");
			
			slice = data.getSlice(new Slice(0,5,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{5,1024, 1024})) throw new Exception("Wrong shape of remote data!");
			
			slice = data.getSlice(new Slice(0,1,1), new Slice(100,101,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{1,1,1024})) throw new Exception("Wrong shape of remote data!");
			
			slice = data.getSlice(new Slice(0,5,1), new Slice(100,105,1)); 
			if (!Arrays.equals(slice.getShape(), new int[]{5,5,1024})) throw new Exception("Wrong shape of remote data!");			
			
		} finally {
			data.disconnect();
		}		
	}

	private File createSomeH5Data(final int nimages, final int... shapeImage) throws Exception {

		final File ret = File.createTempFile("temp_transient_file", ".h5");
		ret.deleteOnExit();

		IHierarchicalDataFile file=null;
		try {
			file = HierarchicalDataFactory.getWriter(ret.getAbsolutePath());
			for (int i = 0; i < nimages; i++) {

				IDataset       rimage   = Random.rand(shapeImage);
				rimage.setName("image");

				file.group("/entry");
				file.group("/entry/data");
				file.appendDataset(rimage.getName(), rimage, "/entry/data");
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
