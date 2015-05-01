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

import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;
import org.eclipse.dawnsci.plotting.api.region.IRegion;
import org.eclipse.dawnsci.plotting.api.region.IRegion.RegionType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * View which creates a sector selection region
 * and listens to that region moving.
 * 
 * @author fcp94556
 *
 */
public class SectorExample extends ImageExample {
	

	public SectorExample() {
		super();			
	}
	
	public void createPartControl(Composite parent) {
		
		super.createPartControl(parent); // plots an image for us
		
		try {
			final IRegion sector = system.createRegion("Sector 1", RegionType.SECTOR);
			sector.setROI(new SectorROI(10, 10, 0, 100, 0, Math.PI/3));
			sector.setRegionColor(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
			system.addRegion(sector);
			
			// NOTE there are clases (like ROIProfile.sector(...)) which can do maths based
			// on where a user moves a sector.
			
			system.setTitle("Sector Example");
			
		} catch (Exception e) {
  		    e.printStackTrace(); // Or your favourite logging
		}
		
		
    }
	
	protected String getFileName() {
		return "duke_football.jpg";
	}

}
