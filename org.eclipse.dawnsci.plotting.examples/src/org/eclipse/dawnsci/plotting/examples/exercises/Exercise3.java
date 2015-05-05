/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.examples.exercises;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;
import org.eclipse.dawnsci.plotting.api.trace.TraceEvent;
import org.eclipse.dawnsci.plotting.examples.Examples;
import org.eclipse.swt.graphics.ImageData;

public class Exercise3 extends Exercise2 {
	
	private static final String EXPORT_DIR = "C:/tmp/Export_";
	private int count;
	private static int buttonPressedCount;
	
	public Exercise3() {
		super();
	}
	
	protected void startReorderThread() {
		count = 0;
		
		final File olddir = new File(EXPORT_DIR+buttonPressedCount);
        if (olddir.exists()) recursiveDelete(olddir);
		
		buttonPressedCount++;
		
		final File dir = new File(EXPORT_DIR+buttonPressedCount);
	    if (dir.exists()) recursiveDelete(dir);

		super.startReorderThread();
	}
	
	protected ITraceListener createTraceListener() {
		return new ITraceListener.Stub() {
			@Override
			public void traceUpdated(TraceEvent evt) {
				// In reality use a Job queue to do this or the UI goes slow...
				IImageTrace trace = (IImageTrace)evt.getSource();
				createThreasholdMask(trace);
				createImageFile(trace);
			}
		};
	}

	protected void createImageFile(IImageTrace trace) {
		try {
			final IImageService iservice = Examples.getCurrent().getImageService();
			final ImageServiceBean  bean = trace.getImageServiceBean().clone();
			
			// Full image and full mask
			bean.setImage(trace.getData());
			bean.setMask(trace.getMask());
			
			final ImageData    imdata = iservice.getImageData(bean);
			final BufferedImage image = iservice.getBufferedImage(imdata);
 			
			count++;
			
			final File dir       = new File(EXPORT_DIR+buttonPressedCount);
			if (!dir.exists()) dir.mkdirs();
			
            final File imageFile = new File(dir, "Image_"+buttonPressedCount+"_"+count+".png");
            
			boolean ok = ImageIO.write(image, "png", imageFile);
			if (ok) {
				System.out.println("Written: "+imageFile.getAbsolutePath());
			} else {
				System.out.println("Failed writing: "+imageFile.getAbsolutePath());
			}
			
		} catch (Exception ne) {
			ne.printStackTrace();
		}
	}
	
	static protected final boolean recursiveDelete(File parent) {

		if (parent.exists()) {
			if (parent.isDirectory()) {

				File[] files = parent.listFiles();
				for (int ifile = 0; ifile < files.length; ++ifile) {
					if (files[ifile].isDirectory()) {
						recursiveDelete(files[ifile]);
					}
					if (files[ifile].exists()) {
						files[ifile].delete();
					}
				}
			}
			return parent.delete();
		}
		return false;
	}

}
