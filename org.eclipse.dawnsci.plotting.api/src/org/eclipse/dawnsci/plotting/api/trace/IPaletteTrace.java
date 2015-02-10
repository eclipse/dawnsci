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

import org.eclipse.dawnsci.plotting.api.histogram.HistogramBound;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.swt.graphics.PaletteData;

public interface IPaletteTrace extends IAxesTrace {

	
	/**
	 * PaletteData for creating the image/surface from the Dataset
	 * @return
	 */
	public PaletteData getPaletteData();
	
	/**
	 * Setting palette data causes the image to redraw with the new palette.
	 * @param paletteData
	 */
	public void setPaletteData(PaletteData paletteData);

	/**
	 * PaletteName for creating the image/surface from the Dataset
	 * @return
	 */
	public String getPaletteName();
	
	/**
	 * Setting palette name
	 * @param paletteName
	 */
	public void setPaletteName(String paletteName);

	/**
	 * Setting palette name and looks up this palette in the IPaletteService and applies
	 * the palette data for it to the trace as well.
	 * @param paletteName
	 */
	public void setPalette(String paletteName);

	/**
	 * Returns the last image service bean sent to the service for getting
	 * the image.
	 * 
	 * @return
	 */
	public ImageServiceBean getImageServiceBean();
	
	/**
	 * Call to add a palette listener
	 * @param pl
	 */
	public void addPaletteListener(IPaletteListener pl);
	
	
	/**
	 * Call to remove a palette listener
	 * @param pl
	 */
	public void removePaletteListener(IPaletteListener pl);

	/**
	 * The min intensity for generating the image
	 * @return
	 */
	public Number getMin();

	/**
	 * The max intensity for generating the image
	 * @return
	 */
	public Number getMax();

	/**
	 * Gets the Nan cut
	 * @return
	 */
	public HistogramBound getNanBound();

	/**
	 * Gets the min cut, a RGB and a bound.
	 * @return
	 */
	public HistogramBound getMinCut();

	/**
	 * Gets the max cut, a RGB and a bound.
	 * @return
	 */
	public HistogramBound getMaxCut();

	/**
	 * Sets the Nan cut
	 * @return
	 */
	public void setNanBound(HistogramBound bound);

	/**
	 * Gets the min cut, a RGB and a bound.
	 * @return
	 */
	public void setMinCut(HistogramBound bound);

	/**
	 * Gets the min cut, a RGB and a bound.
	 * @return
	 */
	public void setMaxCut(HistogramBound bound);

	/**
	 * The min intensity for generating the image/surface trace
	 * @return
	 */
	public void setMin(Number min);
	
	/**
	 * The max intensity for generating the image/surface trace
	 * @return
	 */
	public void setMax(Number max);

	/**
	 * Set wheter an update to the image trace triggers a rehistogram
	 * @param rescaleHistogram
	 */
	public void setRescaleHistogram(boolean rescaleHistogram);

	/**
	 * Is the imagetrace set to rescale the histogram when a new image update occurs
	 * @return the current state.
	 */
	public boolean isRescaleHistogram();
}
