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
package org.eclipse.dawnsci.remotedataset.client.slice;

import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;
import org.eclipse.dawnsci.remotedataset.client.URLBuilder;
import org.eclipse.dawnsci.remotedataset.client.streamer.IStreamer;
import org.eclipse.dawnsci.remotedataset.client.streamer.StreamerFactory;
/**
 *    <pre>
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
 *              MDATA:<dim> e.g. MDATA:0 to send the first dimension as slices in a series as IDatasets. NOTE slice must be set in this case.
 *
 *    histo`  - Encoding of histo to the rules of ImageServiceBean.encode(...) / ImageServiceBean.decode(...)
 *              Example: "MEAN", "OUTLIER_VALUES:5-95"
 *              Only used when an actual image is requested.
 *    
 *    sleep   - Time to sleep between sending images, default 100ms.
 *    
 *    writingExpected - If you know that the remote dataset it likely to be written to, set this flag to ensure
 *                      that limitations with SWMR datestamping and cached writable lazy datasets, do not cause the
 *                      dataset to be incorrectly cached by the server.
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
   </pre>
 * @author Matthew Gerring
 *
 */
public class SliceClient<T> {

	public SliceClient() {

	}

	private int        imageCache=10;
	private boolean    isFinished;
	
	// Private data, not getter/setter
	private IStreamer<T> streamer;
	
    private URLBuilder urlBuilder;

    /**
     * Used for DataServer connections.
     * 
     * @param serverName
     * @param port
     */
	public SliceClient(String serverName, int port) {
		this.urlBuilder = new URLBuilder(serverName, port);
	}
	
	public SliceClient(URLBuilder urlBuilder) {
		this.urlBuilder = urlBuilder;
	}
	
	/**
	 * Used for MJPG streams
	 * @param url
	 */
	public SliceClient(URL url) {
		this.urlBuilder = new URLBuilder(url);
	}

	/**
	 * Call to take the next image for a stream (MJPG). Blocking call.
	 * If in JPG or PNG mode, this is the same as getImage().
	 * @return
	 * @throws Exception
	 */
	public T take() throws Exception {
		
		Format format = urlBuilder.getFormat();
		if (format!=Format.MJPG && format.isImage()) {
			return get();
		} else if (format!=Format.MDATA && (format==null || format==Format.DATA)) {
			return get();
		}
		
		if (isFinished()) throw new Exception("Client has infinished reading images!");
		if (streamer==null) {
			this.isFinished = false;
	        this.streamer = (IStreamer<T>)StreamerFactory.getStreamer(urlBuilder.getSliceURL(), getSleep(), imageCache, format);
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
	
	/**
	 * Gets a remote, non-dynamic view of the data.
	 * If format is DATA, loads data and returns it
	 * If format is an Image returns Buffered Image
	 * If format is MONITOR an IRemoteDataset is returned.
	 * 
	 * @return
	 * @throws Exception
	 */
	public T get() throws Exception {
		
		Format format = urlBuilder.getFormat();
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
			Format format = urlBuilder.getFormat();
			if (!format.isImage()) {
				throw new Exception("Cannot get image with format set to "+format);
			}

			final URL url = urlBuilder.getSliceURL();
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
			// might come with some fruity dependencies
			if (ServiceHolder.getClassLoaderService()!=null) ServiceHolder.getClassLoaderService().setDataAnalysisClassLoaderActive(true);

			Format format = urlBuilder.getFormat();
			if (format!=null && format!=Format.DATA) {
				throw new Exception("Cannot get data with format set to "+format);
			}
			
			final URL url = urlBuilder.getSliceURL();
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
			if (ServiceHolder.getClassLoaderService()!=null) ServiceHolder.getClassLoaderService().setDataAnalysisClassLoaderActive(false);
			isFinished = true;
		}

	}

	public String getPath() {
		return urlBuilder.getPath();
	}

	public void setPath(String path) {
		urlBuilder.setPath(path);
	}

	public String getDataset() {
		return urlBuilder.getDataset();
	}

	public void setDataset(String dataset) {
		urlBuilder.setDataset(dataset);
	}

	public String getSlice() {
		return urlBuilder.getSlice();
	}
	public void setSlice(String slice) {
		urlBuilder.setSlice(slice);
	}
	public String getBin() {
		return urlBuilder.getBin();
	}

	public void setBin(String bin) {
		urlBuilder.setBin(bin);
	}

	public Format getFormat() {
		return urlBuilder.getFormat();
	}
	public void setFormat(Format format) {
		urlBuilder.setFormat(format);
	}
	
	public String getHisto() {
		return urlBuilder.getHisto();
	}

	public void setHisto(String histo) {
		urlBuilder.setHisto(histo);
	}

	public long getSleep() {
		return urlBuilder.getSleep();
	}

	public void setSleep(long sleep) {
		urlBuilder.setSleep(sleep);
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isFinished ? 1231 : 1237);
		result = prime * result
				+ ((urlBuilder == null) ? 0 : urlBuilder.hashCode());
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
		SliceClient other = (SliceClient) obj;
		if (isFinished != other.isFinished)
			return false;
		if (urlBuilder == null) {
			if (other.urlBuilder != null)
				return false;
		} else if (!urlBuilder.equals(other.urlBuilder))
			return false;
		return true;
	}
	
	public void setGet(boolean get) {
		urlBuilder.setGet(get);
	}

	public boolean isWritingExpected() {
		return urlBuilder.isWritingExpected();
	}
	
	public void setWritingExpected(boolean expected) {
		urlBuilder.setWritingExpected(expected);
	}
}
