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
import org.eclipse.swt.graphics.PaletteData;

/**
 * A vector trace can be added to a 1D or 2D plot to show a vector map over
 * the top. It requires a 3D dataset where the first two coordinates are the
 * position in x and y and the third coordinate is a size two argument. This
 * represents a magnitude and angle (anti-clockwise from 12 O'Clock) in radians
 * for the vector arrow.
 *
 * @author Matthew Gerring
 *
 */
public interface IVectorTrace extends ITrace {

	public enum VectorNormalization {
		LINEAR,
		LOGARITHMIC;
	};

	public enum ArrowConfiguration {
		THROUGH_CENTER,
		TO_CENTER,
		TO_CENTER_WITH_CIRCLE;
	};

	public enum ArrowHistogram {
		FIXED_COLOR,
		COLOR_BY_MAGNITUDE,
		COLOR_BY_ANGLE;
	};
	/**
	 * Set the way the arrow pointing should be done.
	 * @param arrowPosition
	 */
	public void setArrowConfiguration(ArrowConfiguration arrowPosition);

	/**
	 * Get the way the arrow pointing should be done.
	 * @param arrowPosition
	 */
	public ArrowConfiguration getArrowConfiguration();

	/**
	 * The maximum size of the arrow in screen pixels. Default is 20.
	 * @param screenPixels
	 */
	public void setMaximumArrowSize(int screenPixels);

	/**
	 * The maximum size of the arrow in screen pixels. Default is 20.
	 * @param screenPixels
	 */
	public int getMaximumArrowSize();

	/**
	 * Set the rgb values for the arrow color. By default the arrow is drawn black.
	 * @param rgb
	 */
	public void setArrowColor(int... rgb);

	/**
	 * Get the rgb values for the arrow color. By default the arrow is drawn black.
	 * @param rgb
	 */
	public int[] getArrowColor();

    /**
     * Instead of a fixed color the arrow may use a palette. If the palette is
     * set the arrowColor is ignored providing the ArrowHistogram has been set
     * to a non-fixed type.
     *
     * @param jet
     */
	public void setArrowPalette(PaletteData jet);

    /**
     * Instead of a fixed color the arrow may use a palette. If the palette is
     * set the arrowColor is ignored providing the ArrowHistogram has been set
     * to a non-fixed type.
     *
     */
	public PaletteData getArrowPalette();

	/**
	 * Set the rgb values for the circle color. By default the circle is drawn black.
	 * @param rgb
	 */
	public void setCircleColor(int... rgb);

	/**
	 * Get the rgb values for the circle color. By default the circle is drawn black.
	 * @param rgb
	 */
	public int[] getCircleColor();

	/**
	 * Call to set image data
	 * @param vectors a 3D dataset [x,y,2] where the first two coordinates are the
     * position in x and y and the third coordinate is a size two argument. This
     * represents a magnitude and angle for the vector arrow.
     * @param axes or null.
	 * @return false if could not set data
	 */
	public boolean setData(final IDataset vectors, final List<IDataset> axes);

	/**
	 *
	 * @return the axes used to look up the position which the vector should be drawn.
	 */
	public List<IDataset> getAxes();

	/**
	 * Call to redraw the image, normally the same as repaint on Figure.
	 */
	public void repaint();


	/**
	 * Used when the values of the vectors are normalized to the maximum
	 * arrow size.
	 * @param type
	 */
	public void setVectorNormalization(VectorNormalization type);

	/**
	 * Used when the values of the vectors are normalized to the maximum
	 * arrow size.
	 * @return VectorNormalizationType
	 */
	public VectorNormalization getVectorNormalization();

	/**
	 * How to color the arrows
	 * @return
	 */
	public ArrowHistogram getArrowHistogram();

	/**
	 * How to color the arrows
	 * @return
	 */
	public void setArrowHistogram(ArrowHistogram arrowHistogram);


}
