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

import javax.imageio.ImageIO;

import org.dawb.common.services.conversion.IConversionContext;
import org.dawb.common.services.conversion.IConversionContext.ConversionScheme;
import org.dawb.common.services.conversion.IConversionService;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.dawnsci.analysis.api.fitting.functions.IDownsampleService;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;
import org.eclipse.dawnsci.plotting.api.trace.TraceEvent;
import org.eclipse.dawnsci.plotting.examples.Examples;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;

/**
 * This is a minimal code 
 */
public class Exercise3 extends Exercise2 {
	
	private static final String EXPORT_DIR = "C:/tmp/Export_";
	private int count;
	private static int buttonPressedCount;
	
	public Exercise3() {
		super();
	}
	
	protected void stopReorderThread() {
        
		super.stopReorderThread();
        
		final File dir = new File(EXPORT_DIR+buttonPressedCount);
	    if (!dir.exists()) return;
	    
	    // This does everything in the GUI thread - use a Job in the final solution!
	    convertH5(dir);
	}
	
	private void convertH5(File dir) {
		try {
		    final File output = new File(dir, "output.h5");
		    
	        // We now use the conversion service to convert the files to nexus
	        IConversionService cservice = Examples.getCurrent().getConversionService();
	        IConversionContext context  = cservice.open(dir.getAbsolutePath()+"/.+png");
	        context.setConversionScheme(ConversionScheme.H5_FROM_IMAGEDIR);
	        context.setOutputPath(output.getAbsolutePath());
	        context.setDatasetName("/entry/data"); // With this conversion dataset is the OUTPUT
	        cservice.process(context);
	        
	        openExternalEditor(output.getAbsolutePath());
	        
		} catch (Exception ne) {
			ne.printStackTrace();
		}
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
			IDownsampleService dservice = Examples.getCurrent().getDownsampleService();
			bean.setImage(dservice.downsample("POINT:2x2", trace.getData()).get(0));
			bean.setMask(dservice.downsample("POINT:2x2", trace.getMask()).get(0));
			
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

	

	/**
	 * Opens an external editor on a file path
	 * @param filename
	 * @throws PartInitException
	 */
	private static IEditorPart openExternalEditor(String filename) throws PartInitException {
		return openExternalEditor(getExternalFileStoreEditorInput(filename), filename);
	}
	private static IEditorInput getExternalFileStoreEditorInput(String filename) {
		final IFileStore externalFile = EFS.getLocalFileSystem().fromLocalFile(new File(filename));
		return new FileStoreEditorInput(externalFile);
	}
	private static IEditorPart openExternalEditor(IEditorInput editorInput, String filePath) throws PartInitException {
		//TODO Maybe this method could be improved by omitting filepath which comes from editorInput, but "how?" should be defined here
		final IWorkbenchPage page = getActivePage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(filePath);
		if (desc == null) desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(filePath+".txt");
		return page.openEditor(editorInput, desc.getId());
	}
	private static IWorkbenchPage getActivePage() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		if (bench==null) return null;
		final IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
		if (window==null) return null;
		return window.getActivePage();
	}

}
