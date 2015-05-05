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
package org.eclipse.dawnsci.plotting.examples.exercises;

import java.io.File;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetUtils;
import org.eclipse.dawnsci.plotting.api.IPlottingService;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.examples.Activator;
import org.eclipse.dawnsci.plotting.examples.Examples;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * A view which plots an image and has an action which
 * can be pressed and when running, updates the image by 
 * removing the last 10 rows and placing them at the front.
 * 
 * This example is not declared as an IViewPart extension by 
 * default. You will need to do this to see the example run.
 * 
 * @author Matthew Gerring
 *
 */
public class Exercise1 extends ViewPart {
	
	protected ILoaderService  service;
	protected IPlottingSystem system;
	private   boolean         updating = false;

	public Exercise1() {
		
		// A service for loading data from any data file format.
		service = Examples.getCurrent().getLoaderService();
		
		final IPlottingService pservice = Examples.getCurrent().getPlottingService();
		try {
			this.system = pservice.createPlottingSystem();
		} catch (Exception ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
			
	}

	@Override
	public void createPartControl(Composite parent) {
		try {
			// We create toggle for running the thread which changes the image
			// There are many other ways of adding a button to do something in eclipse
			// Use your favourite.
			IAction runReorder = new Action("Run Reordering", IAction.AS_CHECK_BOX) {
				public void run() {
					updating = isChecked();
					if (updating) startReorderThread();
				}
			};	
			getViewSite().getActionBars().getToolBarManager().add(runReorder);
			getViewSite().getActionBars().getToolBarManager().add(new Separator());
			
			// We create a basic plot
			system.createPlotPart(parent, "Exercise1", getViewSite().getActionBars(), PlotType.IMAGE, this);

			// We read an image
			final File loc = new File(BundleUtils.getBundleLocation(Activator.PLUGIN_ID), "pow_M99S5_1_0001.cbf");
			final IDataset image = service.getDataset(loc.getAbsolutePath(), new IMonitor.Stub());
			image.setName("Image");
			// NOTE IMonitor is an alternative to IProgressMonitor which cannot be seen in the data layer.
			
			// We plot the initial image
			system.createPlot2D(image, null, new NullProgressMonitor());
			
		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
    }

	protected void startReorderThread() {
		final Thread doReordering = new Thread(new Runnable() {
			public void run() {
				while(updating) {
					
					final IImageTrace trace = (IImageTrace)system.getTrace("Image");
					final IDataset image = trace.getData();
					final IDataset first = image.getSliceView(new Slice(0,image.getShape()[0]-10), null);
					final IDataset last  = image.getSliceView(new Slice(-10,null));

					final IDataset nimage = DatasetUtils.concatenate(new IDataset[]{last,  first}, 0);
					nimage.setName("Image"); // Important
					
				    // Convenience method for thread safe update
					// Could use IITmageTrace.setData(...) but must be from UI thread ie needing a syncExec
					// or similar, therefore use convenience method
					system.updatePlot2D(nimage, trace.getAxes(), new NullProgressMonitor());
					
					try {
						Thread.sleep(100);// Not too fast...
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		});
		doReordering.setDaemon(true);
		doReordering.start();
	}

	@Override
	public void dispose() {
		super.dispose();
		system.dispose();
	}
	
	@Override
	public Object getAdapter(Class clazz) {
		if (system.getAdapter(clazz)!=null) return system.getAdapter(clazz);
		return super.getAdapter(clazz);
	}

	@Override
	public void setFocus() {
		system.setFocus();
	}

}
