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
package org.eclipse.dawnsci.plotting.api.region;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.region.IRegion.RegionType;

/**
 * Service for managing IROI (data region) and IRegion
 * object for managing user interface
 * 
 * @author Matthew Gerring
 *
 */
public interface IRegionService {
	
	
    /**
     * Create a region for a given IROI. An IRegion is the user interface
     * object for a selection region.
     * 
     * @param system
     * @param roi
     * @param roiName
     * @return
     */
	public IRegion createRegion(IPlottingSystem<?> system, IROI roi, String roiName) throws Exception;

	/**
	 * Create a region for a given region type
	 * @return
	 */
	//public Class<? extends IROI> createRegion();

	/**
	 * Get the UI RegionType for a given IROI 
	 * @param iroi
	 * @return
	 */
	public RegionType forROI(IROI iroi);
	
	/**
	 * Get the UI RegionType which best fits the class of IROI
	 * @param clazz
	 * @return
	 */
	public RegionType getRegion(Class<? extends IROI> clazz);

}
