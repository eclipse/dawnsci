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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace.PointStyle;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * A basic view which plots xy (1D) data.
 * 
 * This view uses the services available from plotting.api and 
 * analysis.io
 * 
 * @author fcp94556
 *
 */
public class XYExample extends PlotExample {
	
	
	public void createExampleContent(Composite parent) {
		try {
			// We create a basic plot
			system.createPlotPart(parent, "XY Example", getViewSite().getActionBars(), PlotType.XY, this);

			// We read an image
			final File        loc        = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), getFileName());
			final IDataHolder dataHolder = service.getData(loc.getAbsolutePath(), new IMonitor.Stub());
			// NOTE IMonitor is an alternative to IProgressMonitor which cannot be seen in the data layer.
			
			// Plot some data
			
			// Thread safe convenience method
			system.createPlot1D(null, Arrays.asList(dataHolder.getDataset(0), dataHolder.getDataset(1)), new NullProgressMonitor());
			system.setXFirst(false); // Set the face that 
			
			// Create, configure, add method
			// Another way of adding traces is like this:
			IDataset   data  = dataHolder.getDataset(2); // Get some data out of the CSV file
			data.setName("Frederick");
			ILineTrace trace = system.createLineTrace(data.getName());
			
			// We make some indices (null would also suffice because the trace can make the x values
			Dataset   indices = DatasetFactory.createRange(data.getSize(), Dataset.INT32);
			
			// Transpose the x position a little for fun
			indices.iadd(5);
			
			// Configure the trace
			trace.setData(indices, data);
			trace.setTraceColor(Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED));
			trace.setLineWidth(2);
			trace.setPointStyle(PointStyle.XCROSS);
			trace.setPointSize(8);
			
			// The trace does not get plotted until we add it
			system.addTrace(trace);
			
			system.setTitle("XY Example");
			
			
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }
	
	protected String getFileName() {
		return "metalmix.csv";
	}


}
