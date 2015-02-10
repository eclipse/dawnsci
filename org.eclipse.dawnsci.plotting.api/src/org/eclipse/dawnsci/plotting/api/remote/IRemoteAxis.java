/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.dawnsci.plotting.api.axis.IAxisListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public interface IRemoteAxis extends Remote {

	/**
	 * 
	 * @return
	 */
	public String getTitle() throws RemoteException;
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) throws RemoteException;
	
	/**
	 * 
	 * @param titleFont
	 */
	public void setTitleFont(final Font titleFont) throws RemoteException;

	/**
	 * 
	 * @return
	 */
	public Font getTitleFont() throws RemoteException;
	
	/**
	 * 
	 * @return true if axis is a primary one that cannot be deleted.
	 */
	public boolean isPrimaryAxis() throws RemoteException;
	
	/**
	 * 
	 * @return
	 */
	public boolean isLog10() throws RemoteException;
	
	/**
	 * 
	 * @param isLog10
	 */
	public void setLog10(final boolean isLog10) throws RemoteException;
	
	/**
	 * 
	 * @param color
	 */
	public void setForegroundColor(final Color color) throws RemoteException;
	
	/**
	 * 
	 * @return
	 */
	public Color getForegroundColor() throws RemoteException;
	
	/**
	 * 
	 * @param color
	 */
	public void setBackgroundColor(final Color color) throws RemoteException;
	
	/**
	 * 
	 * @return
	 */
	public Color getBackgroundColor() throws RemoteException;

	/**
	 * 
	 * @return
	 */
	public boolean isShowMajorGrid() throws RemoteException;

	/**
	 * 
	 * @param showMajorGrid
	 */
	public void setShowMajorGrid(final boolean showMajorGrid) throws RemoteException;

	/**
	 * 
	 * @return
	 */
	public boolean isShowMinorGrid() throws RemoteException;

	/**
	 * 
	 * @param showMinorGrid
	 */
	public void setShowMinorGrid(final boolean showMinorGrid) throws RemoteException;

	/**
	 * 
	 * @return
	 */
	public Color getMajorGridColor() throws RemoteException;

	/**
	 * 
	 * @param majorGridColor
	 */
	public void setMajorGridColor(final Color majorGridColor) throws RemoteException;

	/**
	 * 
	 * @return
	 */
	public Color getMinorGridColor() throws RemoteException;

	/**
	 * 
	 * @param minorGridColor
	 */
	public void setMinorGridColor(final Color minorGridColor) throws RemoteException;
	
	/**
	 * NumberFormat pattern for axis
	 * @return
	 */
	public String getFormatPattern() throws RemoteException;
	
	/**
	 * NumberFormat pattern for axis
	 * @param formatPattern
	 */
	public void setFormatPattern(String formatPattern) throws RemoteException;

	/**
	 * 
	 * @return
	 */
	public boolean isYAxis() throws RemoteException;
	
	/**
	 * 
	 * @param isYAxis
	 */
	public void setYAxis(final boolean isYAxis) throws RemoteException;

	/**
	 * 
	 * @param b
	 */
	public void setVisible(boolean b) throws RemoteException;
	
	/**
	 * 
	 * @return
	 */
	public boolean isVisible() throws RemoteException;
	
	/**
	 * returns the upper bound in the units of the axis (not pixels)
	 */
	public double getUpper() throws RemoteException;
	
	/**
	 * returns the lower bound in the units of the axis (not pixels)
	 */
	public double getLower() throws RemoteException;

	/**
	 * Sets the range of the axis, start maybe > end for reversed axes.
	 * @param start
	 * @param end
	 */
	public void setRange(double start, double end) throws RemoteException;
	
	/**
	 * Add a listener notified when the axis changes.
	 * @param listener
	 */
	public void addAxisListener(IAxisListener listener) throws RemoteException;
	
	/**
	 * Add a listener notified when the axis changes.
	 * @param listener
	 */
	public void removeAxisListener(IAxisListener listener) throws RemoteException;

	/**
	 * Sets if the date format should be formatting this axis.
	 * @param dateEnabled
	 */
	public void setDateFormatEnabled(boolean dateEnabled) throws RemoteException;

	/**
	 * @return true if in date/time mode, false normally.
	 */
	public boolean isDateFormatEnabled() throws RemoteException;
	
	/**
	 * The position in pixels of a given value.
	 * @param value
	 * @return
	 */
	public int getValuePosition(double value) throws RemoteException;
	
	/**
	 * The value for a position in pixels.
	 * @param value
	 * @return
	 */
	public double getPositionValue(int position) throws RemoteException;

	/**
	 * The value for a position in pixels.
	 * @param position
	 * @return
	 */
	public double getValueFromPosition(double position) throws RemoteException;
	
	/**
	 * The position in pixels of a given value.
	 * @param value
	 * @return
	 */
	public double getPositionFromValue(double value) throws RemoteException;

	/**
	 * Sets an alternative dataset to use for the labels and the title
	 * @param labels
	 */
	public void setLabelDataAndTitle(IDataset labels) throws RemoteException;
	
	/**
	 * Call to set a maximum range for the axis 
	 */
	public void setMaximumRange(double lower, double upper) throws RemoteException;

	/**
	 * 
	 * @return the number (usually Double) formatted to the current format
	 */
	public String format(Object value) throws RemoteException;

	/**
	 * @return scaling of axis in terms of pixels/unit
	 */
	public double getScaling() throws RemoteException;
	
	/**
	 * @param set whether axis label is automatically formatted or set by the user
	 */
	public void setAutoFormat(boolean autoFormat) throws RemoteException;
	
	/**
	 * @return true if axis format is done automatically, false if user format is to be used
	 */
	public boolean isAutoFormat() throws RemoteException;
	
	/**
	 * @param set whether autoscale sets axis range tight to the data or the end of axis is set to the nearest tickmark
	 */
	public void setAxisAutoscaleTight(boolean axisTight) throws RemoteException;
	
	/**
	 * @return true if autoscaling axis is tight to displayed data
	 */
	public boolean isAxisAutoscaleTight() throws RemoteException;

	public IAxis getDelegate() throws RemoteException;

}
