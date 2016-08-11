/*-
 *******************************************************************************
 * Copyright (c) 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.trace;

import java.util.List;

import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;

/**
 * This trace is currently only available with Java8 and there is
 * no DAWN executable based on Java8 available on the website.
 * 
 * Probably DAWN 2.0 in 2015 will be Java8 based.
 * 
 * @author Joel Ogden
 *
 */
public interface IPlane3DTrace extends IImage3DTrace {

	/**
	 * Plot a volume on the plotting system.
	 * 
	 * Requires the images for each plane in the volume
	 * 
	 * Thread safe
	 * 
	 * @param data
	 * @param size extent of image rectangle
	 * @param offset top-left corner of rectangle
	 * @param planeNormal direction of plane normal TODO missing is rotation, i.e. this should be orientation
	 * @param axes
	 */
	public void setData(final ILazyDataset data, final double[] size, final double[] offset, final double[] planeNormal, final List<? extends IDataset> axes);

	/**
	 * sets the new opacity
	 * @param opacity - between 0.0 - 1.0
	 */
	public void setOpacity(double opacity);
}
