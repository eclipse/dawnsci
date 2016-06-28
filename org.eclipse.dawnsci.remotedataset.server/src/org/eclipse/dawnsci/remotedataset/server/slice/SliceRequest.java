/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.server.slice;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.metadata.OriginMetadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.plotting.api.histogram.HistogramBound;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.HistoType;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.ImageOrigin;
import org.eclipse.dawnsci.remotedataset.Constants;
import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
/**
 * There are one of these objects per session.
 * 
 * So a user only blocks their own session if they
 * do an unfriendly slice.
 * 
 * Parameters which may be set in the request:
 *    Essential
 *    =========
 *    path`   - path to file or directory for loader factory to use.
 *    
 *    Optional
 *    ========
 *    dataset - dataset name, by default the dataset at position 0 will be returned
 *    
 *    slice`  - Provides the slice in the form of that required org.eclipse.dawnsci.analysis.api.dataset.Slice.convertFromString(...)
 *              for example: [0,:1024,:1024]. If left unset and data not too large, will send while dataset, no slice.
 *              
 *    bin`     - downsample  As in Downsample.encode(...) / Downsample.decode(...) ; examples: 'MEAN:2x3', 'MAXIMUM:2x2' 
 *              by default no downsampling is done
 *              
 *    format` - One of Format.values():
 *              DATA - Serialized slice, binary (default)
 *              JPG  - JPG made using IImageService to make the image
 *              PNG  - PNG made using IImageService to make the image
 *              MJPG:<dim> e.g. MJPG:0 to send the first dimension as slices in a series. NOTE slice mist be set in this case.
 *              MDATA:<dim> e.g. MDATA:0 to send the first dimension as slices in a series as IDatasets. NOTE slice mist be set in this case.
 *              
 *    histo`  - Encoding of histo to the rules of ImageServiceBean.encode(...) / ImageServiceBean.decode(...)
 *              Example: "MEAN", "OUTLIER_VALUES:5-95"
 *              Only used when an actual image is requested.
 *    
 *    sleep   - Time to sleep between sending images, default 100ms.
 * 
 *    `URL encoded.
 *    
 *    
 *     * Example in GET format (POST is also ok):
 * 
 *      http://localhost:8080/slice/?path=c%3A/Work/results/TomographyDataSet.hdf5&dataset=/entry/exchange/data&slice=[0,%3A1024,%3A1024]
 *      
 *      Or in a browser:
 *      http://localhost:8080/slice/?path=c%3A/Work/results/TomographyDataSet.hdf5&dataset=/entry/exchange/data&slice=[0,%3A1024,%3A1024]&bin=MAXIMUM:2x2&format=JPG
 *      http://localhost:8080/slice/?path=c%3A/Work/results/TomographyDataSet.hdf5&dataset=/entry/exchange/data&slice=[0,%3A1024,%3A1024]&bin=MAXIMUM:2x2&format=MJPG:0
 *      
 * @author Matthew Gerring
 *
 */
class SliceRequest implements HttpSessionBindingListener {	
	
	private ReentrantLock lock;
	private String sessionId;


	// Actually the SliceRequest
	SliceRequest(String sessionId) {
    	this.lock = new ReentrantLock();
    	this.sessionId = sessionId;
	}
	
	public void slice(HttpServletRequest  request,
					  HttpServletResponse response) throws Exception {
		try {
		    lock.lock(); // Blocks so that one thread at a time does the slice for a given session.
		    doSlice(request, response);
		} finally {
			lock.unlock();
		}
	}

	protected void doSlice(HttpServletRequest  request,
						   HttpServletResponse response) throws Exception {

		final String  path    = decode(request.getParameter("path"));
		final String  dataset = decode(request.getParameter("dataset"));
		ILazyDataset lz       = getLazyDataset(path, dataset);
		lz.clearMetadata(null);
	    
		final String slice  = decode(request.getParameter("slice"));		
		final Slice[] slices    = slice!=null ? Slice.convertFromString(slice) : null;
		
		Format format = Format.getFormat(decode(request.getParameter("format")));
		String bin    = decode(request.getParameter("bin"));
				
		// We set the meta data as header an
		switch(format) {
		case DATA:
			sendObject(getData(lz, slices, bin), response);
			break;
		
		case JPG:
		case PNG:
			sendImage(getData(lz, slices, bin), request, response, format);
			break;
			
		case MJPG:  // In the case of MJPG, we loop over doSlice(...)
		case MDATA: // In the case of MDATA, we loop over doSlice(...)
			sendImages(lz, slices, request, response, format);
			break;
			
	    default:
	    	throw new Exception("Cannot process format: "+format);
		}

	}
	
	private final static Pattern RANDOM = Pattern.compile("RANDOM\\:(\\d)+x(\\d)+");
	
	private ILazyDataset getLazyDataset(String path, String dataset) throws Exception {
		
		Matcher m = RANDOM.matcher(path);
		if (m.matches()) {
			String[] ints = path.split("\\:")[1].split("x");
			int[]   shape = new int[ints.length];
			for (int i = 0; i < ints.length; i++) shape[i] = Integer.parseInt(ints[i]);
			return Random.lazyRand("Test random data", shape);
		}
		
		final File   file = new File(path); // Can we see the file using the local file system?
		if (!file.exists()) throw new IOException("Path '"+path+"' does not exist!");
		ServiceHolder.getLoaderService().clearSoftReferenceCache();
		final IDataHolder holder = ServiceHolder.getLoaderService().getData(path, new IMonitor.Stub()); // TOOD Make it cancellable?
		
		final ILazyDataset lz = dataset!=null 
				              ? holder.getLazyDataset(dataset)
				              : holder.getLazyDataset(0);
		
	    // In order to pass RemoteDataset.testRemoteSlicingUsingSliceND() this is required.
	    if (lz instanceof IDynamicDataset) {
	    	((IDynamicDataset)lz).refreshShape();
	    }

	    if (dataset!=null && lz==null) throw new Exception("Dataset '"+dataset+"' not found in data holder!");
	    
	    return lz;
	}

	private IDataset getData(ILazyDataset lz, Slice[] slices, String bin) throws Exception {
		
		IDataset data = slices!=null ? lz.getSlice(slices) : null;

		// We might load all the data if it is not too large
		if (data==null && lz.getRank()<3) data = lz.getSlice(); // Loads all data

		if (data==null) throw new Exception("Cannot get slice of data for '"+lz+"'");

		data = data.squeeze();

		// We downsample if there was one
		if (bin!=null) {
			data = ServiceHolder.getDownService().downsample(bin, data).get(0);
		}
        return data;
	}

    
	private void sendImages(ILazyDataset        lz, 
			                Slice[]             slices,
							HttpServletRequest  request,
							HttpServletResponse response, 
							Format              format) throws Exception {


		response.setStatus(HttpServletResponse.SC_OK);
		
		String delemeter_str = getClass().getName()+Long.toHexString(System.currentTimeMillis());
		response.setContentType(Constants.MCONTENT_TYPE+";boundary=" + delemeter_str);

		ImageServiceBean bean = createImageServiceBean();

		// Override histo if they set it.
		String histo = decode(request.getParameter("histo"));
		if (histo!=null) bean.decode(histo);

		String bin      = decode(request.getParameter("bin"));
		String sleepStr = decode(request.getParameter("sleep"));
		if (sleepStr == null|| "".equals(sleepStr)) sleepStr = "100"; // Traditional GDA sleep 100!
		int sleep = Integer.parseInt(sleepStr);

		OutputStream out    = new BufferedOutputStream(response.getOutputStream(), 100000);

        byte[] mcontent_type  = ("Content-Type: "+Constants.MCONTENT_TYPE+";boundary="+delemeter_str).getBytes("UTF-8");
		byte[] delimiter      = ("--"+delemeter_str).getBytes("UTF-8");
		final String mimeType = format==Format.MJPG ? Constants.JPG_TYPE : Constants.OBJECT_TYPE;
		byte[] content_type   = ("Content-Type: "+mimeType).getBytes("UTF-8");
		try {

			if (isIE(request)) {
				out.write(mcontent_type);
		        out.write(Constants.CRLF);
	            out.write(Constants.CRLF);
	            out.write(Constants.CRLF);
			}

			final int size = slices!=null ? lz.getShape()[format.getDimension()] : Integer.MAX_VALUE;
			final int from = slices!=null ? slices[format.getDimension()].getStart() : 0; // If no slice, stream forever.
			for (int i = from; i < size; i++) {
				if (slices!=null){
					slices[format.getDimension()].setStart(i);
					slices[format.getDimension()].setStop(i+1);
				}
				IDataset data = getData(lz, slices, bin);
						
				if (data.getRank()!=2 && data.getRank()!=1) {
					throw new Exception("The data used to make an image must either be 1D or 2D!"); 
				}
				
				final byte[]       frame  = getFrame(data, bean, format);
				byte[] content_length = ("Content-Length: " + frame.length).getBytes("UTF-8");
				
				out.write(delimiter);
                out.write(Constants.CRLF);
                out.write(content_type);
                out.write(Constants.CRLF);
                out.write(content_length);
                out.write(Constants.CRLF);
                out.write(Constants.CRLF);
                out.write(frame);
                out.write(Constants.CRLF);
                out.write(Constants.CRLF);
                out.flush();
                 
                TimeUnit.MILLISECONDS.sleep(sleep);
			}

		} catch (Exception ne) {
			ne.printStackTrace();
			throw ne;
			
		} finally {
			if (out!=null)    out.close();
		}
	}

	private boolean isIE(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		return !userAgent.toLowerCase().contains("firefox") &&
			   !userAgent.toLowerCase().contains("chrome");
	}

	private byte[] getFrame(IDataset data, ImageServiceBean bean, Format format) throws Exception {
		
		ByteArrayOutputStream stream = null;
		if (format == Format.MJPG) {
			bean.setImage(data);

			IImageService service = ServiceHolder.getImageService();

			if (service == null) {
				throw new NullPointerException("Image service not set");
			}

			final ImageData imdata = service.getImageData(bean);
			final BufferedImage image = service.getBufferedImage(imdata);

			stream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", stream);

		} else if (format == Format.MDATA) {
			
			stream = new ByteArrayOutputStream();
			final ObjectOutputStream    oout   = new ObjectOutputStream(stream);
			try {
				oout.writeObject(data);
			} finally {
				oout.close();
			}
		}
		
        return stream.toByteArray();
	}

	private void sendImage(IDataset            data, 
						   HttpServletRequest  request,
			               HttpServletResponse response, 
			               Format              format) throws Exception {
		
		if (data.getRank()!=2 && data.getRank()!=1) {
			throw new Exception("The data used to make an image must either be 1D or 2D!"); 
		}
			
		response.setContentType("image/jpeg");
		response.setStatus(HttpServletResponse.SC_OK);

		ImageServiceBean bean = createImageServiceBean();
		bean.setImage(data);
		
		// Override histo if they set it.
		String histo = decode(request.getParameter("histo"));
		if (histo!=null) bean.decode(histo);

		IImageService service = ServiceHolder.getImageService();

		if (service == null) {
			throw new NullPointerException("Image service not set");
		}

		final ImageData    imdata = service.getImageData(bean);
		final BufferedImage image = service.getBufferedImage(imdata);
		
		ImageIO.write(image, format.getImageIOString(), response.getOutputStream());
	}
	
	
	private void sendObject(IDataset            data, 
							HttpServletResponse response) throws Exception {

		response.setContentType("application/zip");
		response.setStatus(HttpServletResponse.SC_OK);

		response.setHeader("elementClass", data.getElementClass().toString());

		final ObjectOutputStream ostream = new ObjectOutputStream(response.getOutputStream());
		try {
			// We remove the origin metadata because the reference
			// to the original dataset is not desirable.
			data.clearMetadata(null);
			ostream.writeObject(data);
			
		} catch (Exception ne) {
			ne.printStackTrace();
			throw ne;
		} finally {
			ostream.flush();
		}
	}
	
	private ImageServiceBean createImageServiceBean() {
		ImageServiceBean imageServiceBean = new ImageServiceBean();
		imageServiceBean.setPalette(makeGrayScalePalette());
		imageServiceBean.setOrigin(ImageOrigin.TOP_LEFT);
		imageServiceBean.setMinimumCutBound(HistogramBound.DEFAULT_MINIMUM);
		imageServiceBean.setMaximumCutBound(HistogramBound.DEFAULT_MAXIMUM);
		imageServiceBean.setNanBound(HistogramBound.DEFAULT_NAN);
		
		imageServiceBean.setHistogramType(HistoType.OUTLIER_VALUES);
		imageServiceBean.setLo(5);
		imageServiceBean.setHi(95);		
		
		return imageServiceBean;
	}
	/**
	 * Make 256 level grayscale palette.
	 */
	public static PaletteData makeGrayScalePalette() {
		RGB grayscale[] = new RGB[256];
		for (int i = 0; i < 256; i++) {
			grayscale[i] = new RGB(i, i, i);
		}
		return new PaletteData(grayscale);
	}


	private String decode(String value) throws UnsupportedEncodingException {
		if (value==null) return null;
		return URLDecoder.decode(value, "UTF-8");
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SliceRequest other = (SliceRequest) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

	public void start() {
		System.out.println(">>>>>> Slice Request Started");
	}
	public void stop() {
		System.out.println(">>>>>> Slice Request Stopped");
	}
}
