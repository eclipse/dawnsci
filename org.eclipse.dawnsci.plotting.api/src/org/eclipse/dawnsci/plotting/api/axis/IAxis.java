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
package org.eclipse.dawnsci.plotting.api.axis;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * Interface used to define an axis on the graph (multiple x and y are allowed).
 * 
 * You can get an IAxis by using IPlottingSystem.createAxis(...) then you can configure the
 * axis as desired, for instance setLog10(...)
 * 
 * @author Matthew Gerring
 *
 */
public interface IAxis {

	/**
	 * 
	 * @return
	 */
	public String getTitle();
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title);
	
	/**
	 * 
	 * @param titleFont
	 */
	public void setTitleFont(final Font titleFont);

	/**
	 * 
	 * @return
	 */
	public Font getTitleFont();
	
	/**
	 * 
	 * @return true if axis is a primary one that cannot be deleted.
	 */
	public boolean isPrimaryAxis();
	
	/**
	 * 
	 * @return
	 */
	public boolean isLog10();
	
	/**
	 * 
	 * @param isLog10
	 */
	public void setLog10(final boolean isLog10);
	
	/**
	 * 
	 * @param color
	 */
	public void setForegroundColor(final Color color);
	
	/**
	 * 
	 * @return
	 */
	public Color getForegroundColor();
	
	/**
	 * 
	 * @param color
	 */
	public void setBackgroundColor(final Color color);
	
	/**
	 * 
	 * @return
	 */
	public Color getBackgroundColor();

	/**
	 * 
	 * @return
	 */
	public boolean isShowMajorGrid();

	/**
	 * 
	 * @param showMajorGrid
	 */
	public void setShowMajorGrid(final boolean showMajorGrid);

	/**
	 * 
	 * @return
	 */
	public boolean isShowMinorGrid();

	/**
	 * 
	 * @param showMinorGrid
	 */
	public void setShowMinorGrid(final boolean showMinorGrid);

	/**
	 * 
	 * @return
	 */
	public Color getMajorGridColor();

	/**
	 * 
	 * @param majorGridColor
	 */
	public void setMajorGridColor(final Color majorGridColor);

	/**
	 * 
	 * @return
	 */
	public Color getMinorGridColor();

	/**
	 * 
	 * @param minorGridColor
	 */
	public void setMinorGridColor(final Color minorGridColor);
	
	/**
	 * NumberFormat pattern for axis
	 * @return
	 */
	public String getFormatPattern();
	
	/**
	 * NumberFormat pattern for axis
	 * @param formatPattern
	 */
	public void setFormatPattern(String formatPattern);

	/**
	 * 
	 * @return
	 */
	public boolean isYAxis();
	
	/**
	 * 
	 * @param isYAxis
	 */
	public void setYAxis(final boolean isYAxis);

	/**
	 * 
	 * @param b
	 */
	public void setVisible(boolean b);
	
	/**
	 * 
	 * @return
	 */
	public boolean isVisible();
	
	/**
	 * returns the upper bound in the units of the axis (not pixels)
	 */
	public double getUpper();
	
	/**
	 * returns the lower bound in the units of the axis (not pixels)
	 */
	public double getLower();

	/**
	 * Sets the range of the axis, start maybe > end for reversed axes.
	 * @param start
	 * @param end
	 */
	public void setRange(double start, double end);
	
	/**
	 * Add a listener notified when the axis changes.
	 * @param listener
	 */
	public void addAxisListener(IAxisListener listener);
	
	/**
	 * Add a listener notified when the axis changes.
	 * @param listener
	 */
	public void removeAxisListener(IAxisListener listener);

	/**
	 * Sets if the date format should be formatting this axis.
	 * @param dateEnabled
	 */
	public void setDateFormatEnabled(boolean dateEnabled);

	/**
	 * @return true if in date/time mode, false normally.
	 */
	public boolean isDateFormatEnabled();
	
	/**
	 * The position in pixels of a given value.
	 * @param value
	 * @return
	 */
	public int getValuePosition(double value);
	
	/**
	 * The value for a position in pixels.
	 * @param value
	 * @return
	 */
	public double getPositionValue(int position);

	/**
	 * The value for a position in pixels.
	 * @param position
	 * @return
	 */
	public double getValueFromPosition(double position);
	
	/**
	 * The position in pixels of a given value.
	 * @param value
	 * @return
	 */
	public double getPositionFromValue(double value);

	/**
	 * Sets an alternative dataset to use for the labels and the title
	 * @param labels
	 */
	public void setLabelDataAndTitle(IDataset labels);
	
	/**
	 * Call to set a maximum range for the axis 
	 */
	public void setMaximumRange(double lower, double upper);

	/**
	 * 
	 * @return the number (usually Double) formatted to the current format
	 */
	public String format(Object value);

	/**
	 * @return scaling of axis in terms of pixels/unit
	 */
	public double getScaling();
	
	/**
	 * @param set whether axis label is automatically formatted or set by the user
	 */
	public void setAutoFormat(boolean autoFormat);
	
	/**
	 * @return true if axis format is done automatically, false if user format is to be used
	 */
	public boolean isAutoFormat();
	
	/**
	 * @param set whether autoscale sets axis range tight to the data or the end of axis is set to the nearest tickmark
	 */
	public void setAxisAutoscaleTight(boolean axisTight);
	
	/**
	 * @return true if autoscaling axis is tight to displayed data
	 */
	public boolean isAxisAutoscaleTight();
}
