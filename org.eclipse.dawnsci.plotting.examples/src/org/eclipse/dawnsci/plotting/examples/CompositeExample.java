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
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Maths;
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
			// We create a basic plot
			system.createPlotPart(parent, "Composite Example", getViewSite().getActionBars(), PlotType.IMAGE, this);

			// We read an image
			final File loc = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), getFileName());
			final IDataset image = service.getDataset(loc.getAbsolutePath(), new IMonitor.Stub());
			// NOTE IMonitor is an alternative to IProgressMonitor which cannot be seen in the data layer.
			
			final IDataset sub = image.getSlice(null,null,new int[]{4,4});
			
			ICompositeTrace comp = system.createCompositeTrace("composite1");
			IImageTrace     back = system.createImageTrace(getFileName());
			
			IDataset x = Maths.add(AbstractDataset.arange(sub.getShape()[1], AbstractDataset.FLOAT), 0).imultiply(4).iadd(100);
			IDataset y = Maths.add(AbstractDataset.arange(sub.getShape()[0], AbstractDataset.FLOAT), 0).imultiply(4).iadd(100);
			
			back.setData(sub, Arrays.asList(x,y), false);
			
			comp.add(back, 0);
			
			IDataset med = image.getSlice(new int[]{424,494},new int[]{1357,1084},new int[]{2,2});
			x = AbstractDataset.arange(med.getShape()[1], AbstractDataset.FLOAT).imultiply(2).iadd(494).iadd(100);
			y = AbstractDataset.arange(med.getShape()[0], AbstractDataset.FLOAT).imultiply(2).iadd(424).iadd(100);
			IImageTrace    mid = system.createImageTrace("mid");
			mid.setData(med, Arrays.asList(x,y), false);
			mid.setAlpha(255);
			comp.add(mid, 1);
			
			
			// Now we overlay a random image at a location
			IDataset small = image.getSlice(new int[]{424,494},new int[]{831,1475},new int[]{1,1});
//			final IDataset small = image.getSlice(new int[]{494,424},new int[]{1475,831},new int[]{1,1});
			IImageTrace    front = system.createImageTrace("rand");
			x = AbstractDataset.arange(small.getShape()[1], AbstractDataset.FLOAT).iadd(494).iadd(100);
			y = AbstractDataset.arange(small.getShape()[0], AbstractDataset.FLOAT).iadd(424).iadd(100);
			front.setData(small, Arrays.asList(x,y), false);
			front.setAlpha(255);
			comp.add(front, 2);
			
			// Add Duke
//			final File loc1 = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), "duke_football.jpg");
//			final IDataset dduke = service.getDataset(loc1.getAbsolutePath(), new IMonitor.Stub());
//
//			IImageTrace    duke = system.createImageTrace("duke");
//			IDataset x = Maths.add(AbstractDataset.arange(dduke.getShape()[1], AbstractDataset.FLOAT), 100);
//			IDataset y = Maths.add(AbstractDataset.arange(dduke.getShape()[0], AbstractDataset.FLOAT), 200);
//			duke.setData(dduke, Arrays.asList(x,y), false);
//			duke.setAlpha(120);
//			comp.add(duke, 2);
			
		
			system.addTrace(comp);
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "pow_M99S5_1_0001.cbf";
	}

}
