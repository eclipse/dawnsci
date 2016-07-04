/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.examples;

import java.io.File;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.swt.widgets.Composite;

/**
 * A view which plots several image (2D) data together on the same pliotting system.
 * 
 * This view uses the services available from plotting.api and 
 * analysis.io
 * 
 * @author Matthew Gerring
 *
 */
public class CompositeExample extends PlotExample {
	
	
	public void createExampleContent(Composite parent) {
		try {
			
//			 We create a basic plot
			system.createPlotPart(parent, "Composite Example", getViewSite().getActionBars(), PlotType.IMAGE, this);

			final File loc = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), getFileName());

			String fp = loc.getAbsolutePath();
			IDataHolder dh = service.getData(fp, true, null);
			Dataset micro = DatasetUtils.sliceAndConvertLazyDataset(dh.getLazyDataset("/microscope1/image/data"));
			Dataset microx = DatasetUtils.sliceAndConvertLazyDataset(dh.getLazyDataset("/microscope1/image/x"));
			Dataset microy = DatasetUtils.sliceAndConvertLazyDataset(dh.getLazyDataset("/microscope1/image/y"));
			RGBDataset microrgb = (RGBDataset) DatasetUtils.createCompoundDataset(Dataset.RGB, micro.getSlice(new Slice(0,1),null,null).squeeze(),
												 micro.getSlice(new Slice(1,2),null,null).squeeze(),
												 micro.getSlice(new Slice(2,3),null,null).squeeze());

			Dataset map = DatasetUtils.sliceAndConvertLazyDataset(dh.getLazyDataset("/map1/map/data"));
			Dataset mapx = DatasetUtils.sliceAndConvertLazyDataset(dh.getLazyDataset("/map1/map/x"));
			Dataset mapy = DatasetUtils.sliceAndConvertLazyDataset(dh.getLazyDataset("/map1/map/y"));
			
			//Nudge co-ordinates
			mapx.iadd(5);
			mapy.isubtract(20);

			//Make the composite trace to hold all the images
			IImageTrace     back = system.createImageTrace(getFileName());

			double[] globalRange = new double[4];
			globalRange[0] =microx.min().doubleValue();
			globalRange[1] =microx.max().doubleValue();
			globalRange[2] =microy.min().doubleValue();
			globalRange[3] =microy.max().doubleValue();
			
			//Set RGB as background
			back.setData(microrgb, Arrays.asList(microx,microy), false);
			back.setGlobalRange(globalRange);

			//Make a low resolution image by slicing out every other point
			Dataset lowMap = map.getSlice(null,null,new int[]{2,2});
			Dataset lowx = mapx.getSlice(null,null,new int[]{2});
			Dataset lowy = mapy.getSlice(null,null,new int[]{2});
			IImageTrace    mid = system.createImageTrace("mid");
			mid.setData(lowMap, Arrays.asList(lowx,lowy), false);
			mid.setAlpha(90);
			mid.setGlobalRange(globalRange);
			
			//Make a partial high resolution area of the map by taking one 64*64 block
			Dataset highMap = map.getSlice(new int[]{64,0},new int[]{128,64} ,null);
			Dataset highx = mapx.getSlice(new int[]{0},new int[]{64},null);
			Dataset highy = mapy.getSlice(new int[]{64},new int[]{128},null);
			IImageTrace    top = system.createImageTrace("top");
			top.setData(highMap, Arrays.asList(highx,highy), false);
			top.setAlpha(150);
			top.setGlobalRange(globalRange);
			
			system.addTrace(back);
			system.addTrace(mid);
			system.addTrace(top);			
			system.autoscaleAxes();
			
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "composite.nxs";
	}

}
