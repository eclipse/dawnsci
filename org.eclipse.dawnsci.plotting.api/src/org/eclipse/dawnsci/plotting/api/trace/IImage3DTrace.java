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

/**
 * Interface for plotting images as surfaces or stack of images.
 * 
 * For plotting a stack of images in 2d or surface of image in 3D , staggered in Z.
 * This interface is for Multi 2d and surface data and 
 * is more limited because of the features available in the 3D viewer.
 * 
 * If all the tools are required, use createPlot2D(x, manyYs, ...)
 * where manyYs is a stack of 1D data. This will all be plotted in an
 * over layered manner but allow all tools to be used.
 * 
 *
 */
public interface IImage3DTrace extends IPaletteTrace {

	/**
	 * 
	 * @param axesNames
	 */
	public void setAxesNames(List<String> axesNames);

}
