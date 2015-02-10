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
package org.eclipse.dawnsci.plotting.api.trace;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

public interface IAxesTrace extends ITrace {

	
	/**
	 * The set of axes, either a list of size 2 for a 2D trace or a list of size 3 for a 3D one.
	 * May contain nulls (z is often null for intensity).
	 * @return
	 */
	public List<IDataset> getAxes();
	
	
	/**
	 * 
	 * @return true if plot is currently plotting.
	 */
	public boolean isActive();

	
	/**
	 * Labels for the axes. The data set name of the axis used if not set.
	 * Should be size 3 but may have nulls.
	 * @return
	 */
	public List<String> getAxesNames();

}
