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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.plotting.api.PlotType;
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
public class ImageExample extends PlotExample {
	
	
	public void createExampleContent(Composite parent) {
		try {
			// We create a basic plot
			system.createPlotPart(parent, "Image Example", getViewSite().getActionBars(), PlotType.IMAGE, this);

			// We read an image
			final File loc = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), getFileName());
			final IDataset image = service.getDataset(loc.getAbsolutePath(), new IMonitor.Stub());
			// NOTE IMonitor is an alternative to IProgressMonitor which cannot be seen in the data layer.
			
			// We plot the image
			system.createPlot2D(image, null, new NullProgressMonitor());
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "pow_M99S5_1_0001.cbf";
	}

}
