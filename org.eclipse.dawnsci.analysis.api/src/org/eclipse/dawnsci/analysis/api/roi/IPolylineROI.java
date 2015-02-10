/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.roi;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Interface for a polyline ROI
 */
public interface IPolylineROI extends IROI, Iterable<IROI> {

	/**
	 * @return number of points
	 */
	public int getNumberOfPoints();

	/**
	 * @param i
	 * @return i-th point as point ROI
	 */
	public IROI getPoint(int i);

	/**
	 * @return two datasets with x and y coordinates 
	 */
	public IDataset[] makeCoordinateDatasets();

	/**
	 * Add point to polyline
	 * @param point
	 */
	public void insertPoint(IROI point);

	/**
	 * Set point of polyline at index
	 * @param i index
	 * @param point
	 */
	public void setPoint(int i, IROI point);

	/**
	 * Remove all points
	 */
	public void removeAllPoints();

	@Override
	public IPolylineROI copy();
}
