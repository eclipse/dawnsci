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

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace.TraceType;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.swt.widgets.Composite;

/**
 * A basic view which plots xy (1D) data.
 * 
 * This view uses the services available from plotting.api and 
 * analysis.io
 * 
 * @author Matthew Gerring
 *
 */
public class BarExample extends PlotExample {
	
	
	public void createExampleContent(Composite parent) {
		try {
			// We create a basic plot
			system.createPlotPart(parent, "Bar Example", getViewSite().getActionBars(), PlotType.XY, this);

			// We read an image
			final File        loc     = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), getFileName());
			final IDataHolder allData = service.getData(loc.getAbsolutePath(), new IMonitor.Stub());
			// NOTE IMonitor is an alternative to IProgressMonitor which cannot be seen in the data layer.
			
			// We plot the data
			ILineTrace bar = system.createLineTrace(allData.getDataset(0).getName());
			bar.setData(null, allData.getDataset(0));
			bar.setTraceType(TraceType.HISTO);
			system.addTrace(bar);
		
			ILineTrace area = system.createLineTrace(allData.getDataset(10).getName());
			area.setData(null, allData.getDataset(10));
			area.setTraceType(TraceType.AREA);
			system.addTrace(area);
			
			system.autoscaleAxes();
			system.setTitle("Area and Bar Example");
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "metalmix.csv";
	}


}
