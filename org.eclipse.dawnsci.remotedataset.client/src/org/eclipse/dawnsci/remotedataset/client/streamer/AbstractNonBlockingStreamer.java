/*-
 *******************************************************************************
 * Copyright (c) 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial version 'AbstractStreamer' on which this class is based
 *    Matthew Taylor  - modified to be non-blocking, constantly updating the latest data
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.client.streamer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.dawnsci.remotedataset.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractNonBlockingStreamer<T> implements IStreamer<T>, Runnable {
		
	protected static final Logger logger = LoggerFactory.getLogger(AbstractNonBlockingStreamer.class);

	private InputStream      in;
	private long             sleepTime;
	private long             receivedImages = 0;
	private boolean          isFinished;
	private byte[] latestBytes = null;
	
	private String             delimiter;

	/**
	 * Initialises the connection
	 * @param url
	 * @param sleepTime
	 * @return
	 * @throws Exception
	 */
	protected URLConnection init(URL url, long sleepTime) throws Exception {

		URLConnection  conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);

        String contentType = conn.getContentType();
        if (!contentType.startsWith(Constants.MCONTENT_TYPE)) throw new Exception("getImages() may only be used with "+Constants.MCONTENT_TYPE+", not "+contentType);

        this.delimiter  = contentType.split("boundary=")[1];
		this.in         = new BufferedInputStream(conn.getInputStream());
		this.sleepTime  = sleepTime;

        return conn;
	}
	
	/**
	 * Runs until finished or the stream is closed, continuously parsing the data in the stream to form an image
	 */
	public void run() {
		
		isFinished = false;
		try {
			final StringBuilder buf = new StringBuilder();

			int c       = -1;
			boolean foundImage = false;
			
			while(!isFinished && (c=in.read())> -1 ) {
				
				buf.append((char)c);
				if (buf.length()>0 && buf.charAt(buf.length()-1)  == '\n') { // Line found
					
					final String line = buf.toString().trim();
					if (line.equals("--"+delimiter)) { // We found a new image
						foundImage = true;
					}
					if (foundImage && line.startsWith("Content-Length: ")) {
						int clength = Integer.parseInt(line.split("\\:")[1].trim());
						readImage(in, clength);
						if (isFinished) return;
						
						foundImage = false;
						
						Thread.sleep(sleepTime); // We don't want to use all the CPU!
					}
					
					buf.delete(0, buf.length());
					continue;
				}
			}
			
		} catch (Exception ne) {
			setFinished(true);
			logger.error("Cannot read input stream in "+getClass().getSimpleName(), ne);
			
		} finally {
			setFinished(true);
			try {
				in.close();
			} catch (Exception ne) {
				logger.error("Cannot close connection!", ne);
			}
		}
	}

	/**
	 * Reads an image from the stream, populating the array with the latest image bytes
	 * @param in
	 * @param clength
	 * @throws Exception
	 */
	private void readImage(InputStream in, int clength) throws Exception {
		
		int c= -1;
		// Scoot down until no more new lines (this loses first character of JPG)
		while((c=in.read())> -1) {
			if (c=='\r') continue;
			if (c=='\n') continue;
			break;
		}
			
		byte[] imageBytes = new byte[clength + 1];

		imageBytes[0] = (byte)c; // We took one
		int offset    = 1;
		int numRead   = 0;
		while (!isFinished && offset < imageBytes.length && (numRead=in.read(imageBytes, offset, imageBytes.length-offset)) >= 0) {
			offset += numRead;
		}       

		if (isFinished) return;
		
		latestBytes = imageBytes;
	}

	/**
	 * Implement to turn the raw stream bytes into type T
	 * @param bais
	 * @return
	 * @throws Exception
	 */
	protected abstract T getFromStream(ByteArrayInputStream bais) throws Exception;


	/**
	 * Blocks until image added, after that will take the latest data. Once null is added, we are done.
	 * @return Image or null when finished.
	 * 
	 * @throws InterruptedException
	 */
	public T take() throws InterruptedException {
		while (latestBytes == null) {
			Thread.sleep(sleepTime);
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(latestBytes);	
		T bi = null;
		try {
			bi = getFromStream(bais);
			if (isFinished || bi == getQueueEndObject()) {
				setFinished(true);
				return null;
			}
			receivedImages++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bi;
	}

	/**
	 * Implement to designate an object of tpye T as the end of queue object
	 * @return
	 */
	protected abstract T getQueueEndObject();

	/**
	 * Gets the dropped image count. Note, this does not apply to this streamer, so will return 0
	 */
	public long getDroppedImageCount() {
		return 0;
	}
	
	/**
	 * Gets the received image count
	 */
	public long getReceivedImageCount() {
		return receivedImages;
	}

	/**
	 * Starts the thread running
	 */
	public void start() {
		Thread thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.setDaemon(true);
		thread.setName("MJPG Streamer");
		thread.start();
	}

	/**
	 * Call to tell the streamer to stop adding images to its queue.
	 * @param b
	 */
	public void setFinished(boolean b) {
		this.isFinished = b;
	}


}