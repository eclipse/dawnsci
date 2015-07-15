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
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
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
			
			ICompositeTrace comp = system.createCompositeTrace("composite1");
			IImageTrace     back = system.createImageTrace(getFileName());
			back.setData(image, null, false);
			
			comp.add(back, 0);
			
			// Now we overlay a random image at a location
			final IDataset rand  = Random.rand(0, 100, new int[]{500,500});
			IImageTrace    front = system.createImageTrace("rand");
			IDataset x = Maths.add(AbstractDataset.arange(rand.getShape()[1], AbstractDataset.FLOAT), 0);
			IDataset y = Maths.add(AbstractDataset.arange(rand.getShape()[0], AbstractDataset.FLOAT), 0);
			front.setData(rand, Arrays.asList(x,y), false);
			front.setAlpha(100);
			comp.add(front, 1);
			
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
