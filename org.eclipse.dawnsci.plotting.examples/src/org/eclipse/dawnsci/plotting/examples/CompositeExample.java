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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.ICompositeTrace;
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
			IDataset micro = dh.getLazyDataset("/microscope1/image/data").getSlice();
			IDataset microx = dh.getLazyDataset("/microscope1/image/x").getSlice();
			IDataset microy = dh.getLazyDataset("/microscope1/image/y").getSlice();
			RGBDataset microrgb = new RGBDataset((Dataset)micro.getSlice(new Slice(0,1),null,null).squeeze(),
												 (Dataset)micro.getSlice(new Slice(1,2),null,null).squeeze(),
												 (Dataset)micro.getSlice(new Slice(2,3),null,null).squeeze());

			IDataset map = dh.getLazyDataset("/map1/map/data").getSlice();
			IDataset mapx = dh.getLazyDataset("/map1/map/x").getSlice();
			IDataset mapy = dh.getLazyDataset("/map1/map/y").getSlice();
			
			//Nudge co-ordinates
			((Dataset)mapx).iadd(5);
			((Dataset)mapy).isubtract(20);

			//Make the composite trace to hold all the images
			ICompositeTrace comp = system.createCompositeTrace("composite1");
			IImageTrace     back = system.createImageTrace(getFileName());

			//Set RGB as background
			back.setData(microrgb, Arrays.asList(microx,((Dataset)microy)), false);
			comp.add(back, 0);

			//Make a low resolution image by slicing out every other point
			IDataset lowMap = map.getSlice(null,null,new int[]{2,2});
			IDataset lowx = mapx.getSlice(null,null,new int[]{2});
			IDataset lowy = mapy.getSlice(null,null,new int[]{2});
			IImageTrace    mid = system.createImageTrace("mid");
			mid.setData(lowMap, Arrays.asList(((Dataset)lowx),((Dataset)lowy)), false);
			mid.setAlpha(90);
			comp.add(mid,1);
			
			//Make a partial high resolution area of the map by taking one 64*64 block
			IDataset highMap = map.getSlice(new int[]{64,0},new int[]{128,64} ,null);
			IDataset highx = mapx.getSlice(new int[]{0},new int[]{64},null);
			IDataset highy = mapy.getSlice(new int[]{64},new int[]{128},null);
			IImageTrace    top = system.createImageTrace("top");
			top.setData(highMap, Arrays.asList(highx,highy), false);
			top.setAlpha(150);
			
			comp.add(top, 2);

			system.addTrace(comp);
			
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "composite.nxs";
	}

}
