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
package org.eclipse.dawnsci.plotting.api.tool;

import org.eclipse.dawnsci.plotting.api.region.IRegion;
import org.eclipse.dawnsci.plotting.api.tool.IToolPage;

/**
 * Interface designed to hide special tool pages.
 * @author Matthew Gerring
 *
 */
public interface IProfileToolPage extends IToolPage {

	/**
	 * Line type for Box Line Profiles
	 * @param vertical true for vertical, else false for horizontal
	 */
	void setLineOrientation(boolean vertical);

	/**
	 * Set the plotAverageProfile flag for BoxLineProfile
	 * @param b
	 */
	void setPlotAverageProfile(boolean b);

	/**
	 * Set the plotEdgeProfile flag for BoxLineProfile
	 * @param b
	 */
	void setPlotEdgeProfile(boolean b);

	/**
	 * Set the XAxis ROI visible flag for BoxLineProfile
	 * @param b
	 */
	void setXAxisROIVisible(boolean b);

	/**
	 * Update Profile given an IRegion
	 * @param region
	 */
	void update(IRegion region);
}