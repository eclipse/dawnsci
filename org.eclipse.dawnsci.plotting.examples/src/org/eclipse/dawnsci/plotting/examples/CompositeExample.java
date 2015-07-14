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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.ICompositeTrace;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.swt.widgets.Composite;

/**
 * A basic view which plots image (2D) data.
 * 
 * This view uses the services available from plotting.api and 
 * analysis.io
 * 
 * @author fcp94556
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
			
			ICompositeTrace comp = system.createCompositeTrace("composite1");
			IImageTrace     back = system.createImageTrace(getFileName());
			back.setData(image, null, false);
			
			comp.add(back, 0);
			
			// Now we overlay a random image at a location
			final IDataset rand  = Random.rand(0, 100, new int[]{500,500});
			IImageTrace    front = system.createImageTrace("rand");
			front.setData(rand, null, false);
			front.setAlpha(100);
			
			comp.add(front, 1);
		
			system.addTrace(comp);
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "pow_M99S5_1_0001.cbf";
	}

}
