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

/**
 * Interface for plotting stack of images.
 * 
 * For plotting a stack of images in 2D, staggered in Z.
 * This interface is for Multi 2d data trace and is more limited because of the
 * features available in the 3D viewer.
 * 
 * If all the tools are required, use createPlot2D(x, manyYs, ...)
 * where manyYs is a stack of 1D data. This will all be plotted in an
 * overlayed manner but allow all tools to be used.
 * 
 *
 */
public interface IMulti2DTrace extends IImage3DTrace {

	/**
	 * 
	 * @param axes
	 * @param data
	 */
	public void setData(List<? extends IDataset> axes, IDataset... s);

	/**
	 * The multi 2d data that is being plotted.
	 * @return
	 */
	public IDataset[] getMulti2D();

}
