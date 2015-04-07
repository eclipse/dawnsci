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
package org.eclipse.dawnsci.data.client;

import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.analysis.api.IClassLoaderService;
import org.eclipse.dawnsci.data.client.streamer.IStreamer;
import org.eclipse.dawnsci.data.client.streamer.StreamerFactory;
import org.eclipse.dawnsci.data.server.Format;
/**
 *   
 *    Class to look after making a connection to the HTTP Data slice server.
 *    Basically it encodes the parameters if a GET is used or if a POST is used,
 *    it deals with that. There is no need when using the client to know how the 
 *    HTTP connection is managed.
 *   
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
 *    bin     - downsample  As in Downsample.fromString(...) ; examples: 'MEAN:2x3', 'MAXIMUM:2x2' 
 *              by default no downsampling is done
 *              
 *    format` - One of Format.values():
 *              DATA - zipped slice, binary (default)
 *              JPG  - JPG made using IImageService to make the image
 *              PNG  - PNG made using IImageService to make the image
 *              MJPG:<dim> e.g. MJPG:0 to send the first dimension as slices in a series as JPGs. NOTE slice mist be set in this case.
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
 *    Usage:
 *    Normally we simply use getImage() or get data but for a stream, the formula is:

    <code>
    	try {
	    	while(!client.isFinished()) {
	
	    		final BufferedImage image = client.take(); // Blocks
	    		if (image==null) break;
	    		
	    		// Do what we want with the image, may cause exception
	    		//...
	    	}
    	} catch (Exception ne) {
    		client.setFinished(true);
    		throw ne;
    	}
    </code>

 * @author fcp94556
 *
 */
public class DataClient<T> {

	private static IClassLoaderService classService;
	public static void setClassLoaderService(IClassLoaderService service) {
		classService = service;
	}
	public DataClient() {
		// OSGi
	}

	private String     base;
	private String     path;
	private String     dataset;
	private String     slice;
	private String     bin;
	private Format     format;
	private String     histo;
	private long       sleep=100;
	private int        imageCache=10;
	private boolean    isFinished;
	private boolean    get = true;
	
	// Private data, not getter/setter
	private IStreamer<T> streamer;
	

	public DataClient(String base) {
		this.base = base;
	}
    
	/**
	 * Call to take the next image for a stream (MJPG). Blocking call.
	 * If in JPG or PNG mode, this is the same as getImage().
	 * @return
	 * @throws Exception
	 */
	public T take() throws Exception {
		
		if (format!=Format.MJPG && format.isImage()) {
			return get();
		} else if (format!=Format.MDATA && (format==null || format==Format.DATA)) {
			return get();
		}
		
		if (isFinished()) throw new Exception("Client has infinished reading images!");
		if (streamer==null) {
			this.isFinished = false;
	        this.streamer = (IStreamer<T>)StreamerFactory.getStreamer(new URL(getURLString()), getSleep(), imageCache, format);
	        streamer.start(); // Runs thread to add to queue
		}
		
		T image = streamer.take();
		if (image == null) {
			isFinished = true;
			streamer = null; // A null image means that the connection is down.
		}
        return image;
	}

	public long getDroppedImageCount() {
		return streamer.getDroppedImageCount();
	}
	public long getReceivedImageCount() {
		return streamer.getReceivedImageCount();
	}
	
	public T get() throws Exception {
		
		if (format==null) format = Format.DATA;
		switch(format) {
		case DATA:
			return getData();
		case JPG:
		case PNG:
			return getImage();
		}
		throw new Exception("Format '"+format+"' cannot be used with get()");
	}


	private T getImage() throws Exception {
		
		isFinished = false;
		try {
			if (!format.isImage()) {
				throw new Exception("Cannot get image with format set to "+format);
			}

			final URL url = new URL(getURLString());
			URLConnection  conn = url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);

			return (T)ImageIO.read(url.openStream());
		} finally {
			isFinished = true;
		}
	}

	
	private T getData() throws Exception {
		isFinished = false;
		try {
			// We are getting into serializing and deserializing IDataset which
			// might come with some fruity 
			if (classService!=null) classService.setDataAnalysisClassLoaderActive(true);

			if (format!=null && format!=Format.DATA) {
				throw new Exception("Cannot get data with format set to "+format);
			}
			
			final URL url = new URL(getURLString());
			URLConnection  conn = url.openConnection();
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	
	        ObjectInputStream oin=null;
			try {
		        oin  = new ObjectInputStream(url.openStream());
				return (T)oin.readObject();
				
			} finally {
				if (oin!=null) oin.close();
	 		}
		} finally {
			if (classService!=null) classService.setDataAnalysisClassLoaderActive(false);
			isFinished = true;
		}

	}
	
	
	private String getURLString() throws Exception {
		final StringBuilder buf = new StringBuilder();
		buf.append(base);
		if (isGet()) { // Add params
			buf.append("?");
			append(buf, "path",    path);
			append(buf, "dataset", dataset);
			append(buf, "slice",   slice);
			append(buf, "bin",     bin);
		    append(buf, "format",  format);
		    append(buf, "histo",   histo);
		    append(buf, "sleep",   sleep);
		}
		return buf.toString();
	}

	private void append(StringBuilder buf, String name, Object object) throws UnsupportedEncodingException {
		if (object==null || "".equals(object)) return;
		
		String value = object.toString();
		buf.append(name);
		buf.append("=");
		buf.append(URLEncoder.encode(value, "UTF-8"));
		buf.append("&");
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	public String getSlice() {
		return slice;
	}
	public void setSlice(String slice) {
		this.slice = slice;
	}
	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((bin == null) ? 0 : bin.hashCode());
		result = prime * result + ((dataset == null) ? 0 : dataset.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + (get ? 1231 : 1237);
		result = prime * result + ((histo == null) ? 0 : histo.hashCode());
		result = prime * result + imageCache;
		result = prime * result + (isFinished ? 1231 : 1237);
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + (int) (sleep ^ (sleep >>> 32));
		result = prime * result + ((slice == null) ? 0 : slice.hashCode());
		result = prime * result
				+ ((streamer == null) ? 0 : streamer.hashCode());
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
		DataClient other = (DataClient) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (bin == null) {
			if (other.bin != null)
				return false;
		} else if (!bin.equals(other.bin))
			return false;
		if (dataset == null) {
			if (other.dataset != null)
				return false;
		} else if (!dataset.equals(other.dataset))
			return false;
		if (format != other.format)
			return false;
		if (get != other.get)
			return false;
		if (histo == null) {
			if (other.histo != null)
				return false;
		} else if (!histo.equals(other.histo))
			return false;
		if (imageCache != other.imageCache)
			return false;
		if (isFinished != other.isFinished)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (sleep != other.sleep)
			return false;
		if (slice == null) {
			if (other.slice != null)
				return false;
		} else if (!slice.equals(other.slice))
			return false;
		if (streamer == null) {
			if (other.streamer != null)
				return false;
		} else if (!streamer.equals(other.streamer))
			return false;
		return true;
	}
	
	public String getHisto() {
		return histo;
	}

	public void setHisto(String histo) {
		this.histo = histo;
	}

	public long getSleep() {
		return sleep;
	}

	public void setSleep(long sleep) {
		this.sleep = sleep;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean b) {
		isFinished = b;
		if (streamer!=null) streamer.setFinished(b);
	}

	public int getImageCache() {
		return imageCache;
	}

	public void setImageCache(int imageCache) {
		this.imageCache = imageCache;
	}
	public boolean isGet() {
		return get;
	}
	public void setGet(boolean get) {
		this.get = get;
	}

}
