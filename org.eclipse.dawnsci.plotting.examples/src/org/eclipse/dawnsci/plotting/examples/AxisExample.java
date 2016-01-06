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

import java.util.Arrays;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDatasetMathsService;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * View which creates a sector selection region
 * and listens to that region moving.
 * 
 * @author Matthew Gerring
 *
 */
public class AxisExample extends XYExample {
	

	public AxisExample() {
		super();			
	}
	
	public void createPartControl(Composite parent) {
		
		super.createPartControl(parent); // plots an image for us
		
		try {
			final IDatasetMathsService mservice = Examples.getCurrent().getDatasetMathsService();
			
			// Create a 1D dataset programmatically. Can also use 
			final IDataset set = mservice.createRange(0, 100000, 1000, IDatasetMathsService.INT);
			set.setName("Different scale data");
			
			final IAxis otherX = system.createAxis("top", false, SWT.TOP);
			final IAxis otherY = system.createAxis("right", true, SWT.RIGHT);
		    
			system.setSelectedXAxis(otherX);
			system.setSelectedYAxis(otherY);
			
			system.createPlot1D(null, Arrays.asList(set), new NullProgressMonitor());
			
			system.setTitle("Axis Example");

		} catch (Exception e) {
  		    e.printStackTrace(); // Or your favourite logging
		}
		
		
    }
	
	protected String getImageName() {
		return "duke_football.jpg";
	}

}
