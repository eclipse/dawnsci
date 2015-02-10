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
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.histogram.IPaletteService;
import org.eclipse.dawnsci.plotting.api.trace.ISurfaceTrace;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Composite;

public class SurfaceExample extends PlotExample {

	public void createExampleContent(Composite parent) {
		try {
			// We create a basic plot
			system.createPlotPart(parent, "Image Example", getViewSite().getActionBars(), PlotType.IMAGE, this);

			// We read an image
			final File loc = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), getFileName());
			final IDataset image = service.getDataset(loc.getAbsolutePath(), new IMonitor.Stub());
			// NOTE IMonitor is an alternative to IProgressMonitor which cannot be seen in the data layer.
			
			// We plot the image
			system.setPlotType(PlotType.SURFACE); // Set to a 3D plot type.
			
			ISurfaceTrace surface = system.createSurfaceTrace("Example surface");
			surface.setData(image, null);
			// NOTE We are viewing a window of the data. The user can 
			// open a tool from the toolbar to move around this window.
			surface.setWindow(new RectangularROI(300,300,600,600,0), false, null);
			
			// Let's make it something colorful!
			final IPaletteService pservice = Examples.getCurrent().getPaletteService();
			final PaletteData     pData    = pservice.getDirectPaletteData("NCD");
			surface.setPaletteData(pData); 
			surface.setMax(10);
			surface.setMin(0);
			
			system.addTrace(surface);
			
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "pow_M99S5_1_0001.cbf";
	}
}
