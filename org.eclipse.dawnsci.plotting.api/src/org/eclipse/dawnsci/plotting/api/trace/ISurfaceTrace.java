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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.roi.IROI;

/**
 *
 * This interface is ready for use from now onwards and is to be used for
 * 3D plotting operations. Use IImageTrace normally for images.
 *
 *
 * @author Matthew Gerring
 *
 */
public interface ISurfaceTrace extends IImage3DTrace, IWindowTrace {

	/**
	 * Set the data of the plot, will replot if called on an active plot.
	 * @param data
	 * @param axes
	 * @return true if data has been successfully set
	 * @throws Exception
	 */
	public boolean setData(final IDataset data, final List<IDataset> axes);

	/**
	 *
	 * @return the region of the window, usually a SurfacePlotROI or a RectangularROI
	 */
	public IROI getWindow();

	/**
	 * Set the window to be used as a SurfacePlotROI or RectangularROI
	 * @param window
	 * @param updateClipping
	 * @param monitor
	 * @return status
	 */
	public IStatus setWindow(IROI window, boolean updateClipping, IProgressMonitor monitor);

}
