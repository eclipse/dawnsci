/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.remote;

import java.rmi.RemoteException;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.dawnsci.plotting.api.axis.IAxisListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * Boilder plate delegation
 */
public class RemoteAxis implements IRemoteAxis {
	
	private IAxis delegate;
	public RemoteAxis(IAxis d) throws RemoteException {
		delegate = d;
	}

	public String getTitle() throws RemoteException {
		return delegate.getTitle();
	}
	public void setTitle(String title) throws RemoteException {
		delegate.setTitle(title);
	}
	public void setTitleFont(Font titleFont) throws RemoteException {
		delegate.setTitleFont(titleFont);
	}
	public Font getTitleFont() throws RemoteException {
		return delegate.getTitleFont();
	}
	public boolean isPrimaryAxis() throws RemoteException {
		return delegate.isPrimaryAxis();
	}
	public boolean isLog10() throws RemoteException {
		return delegate.isLog10();
	}
	public void setLog10(boolean isLog10) throws RemoteException {
		delegate.setLog10(isLog10);
	}
	public void setForegroundColor(Color color) throws RemoteException {
		delegate.setForegroundColor(color);
	}
	public Color getForegroundColor() throws RemoteException {
		return delegate.getForegroundColor();
	}
	public void setBackgroundColor(Color color) throws RemoteException {
		delegate.setBackgroundColor(color);
	}
	public Color getBackgroundColor() throws RemoteException {
		return delegate.getBackgroundColor();
	}
	public boolean isShowMajorGrid() throws RemoteException {
		return delegate.isShowMajorGrid();
	}
	public void setShowMajorGrid(boolean showMajorGrid) throws RemoteException {
		delegate.setShowMajorGrid(showMajorGrid);
	}
	public boolean isShowMinorGrid() throws RemoteException {
		return delegate.isShowMinorGrid();
	}
	public void setShowMinorGrid(boolean showMinorGrid) throws RemoteException {
		delegate.setShowMinorGrid(showMinorGrid);
	}
	public Color getMajorGridColor() throws RemoteException {
		return delegate.getMajorGridColor();
	}
	public void setMajorGridColor(Color majorGridColor) throws RemoteException {
		delegate.setMajorGridColor(majorGridColor);
	}
	public Color getMinorGridColor() throws RemoteException {
		return delegate.getMinorGridColor();
	}
	public void setMinorGridColor(Color minorGridColor) throws RemoteException {
		delegate.setMinorGridColor(minorGridColor);
	}
	public String getFormatPattern() throws RemoteException {
		return delegate.getFormatPattern();
	}
	public void setFormatPattern(String formatPattern) throws RemoteException {
		delegate.setFormatPattern(formatPattern);
	}
	public boolean isYAxis() throws RemoteException {
		return delegate.isYAxis();
	}
	public void setYAxis(boolean isYAxis) throws RemoteException {
		delegate.setYAxis(isYAxis);
	}
	public void setVisible(boolean b) throws RemoteException {
		delegate.setVisible(b);
	}
	public boolean isVisible() throws RemoteException {
		return delegate.isVisible();
	}
	public double getUpper() throws RemoteException {
		return delegate.getUpper();
	}
	public double getLower() throws RemoteException {
		return delegate.getLower();
	}
	public void setRange(double start, double end) throws RemoteException {
		delegate.setRange(start, end);
	}
	public void addAxisListener(IAxisListener listener) throws RemoteException {
		delegate.addAxisListener(listener);
	}
	public void removeAxisListener(IAxisListener listener) throws RemoteException {
		delegate.removeAxisListener(listener);
	}
	public void setDateFormatEnabled(boolean dateEnabled) throws RemoteException {
		delegate.setDateFormatEnabled(dateEnabled);
	}
	public boolean isDateFormatEnabled() throws RemoteException {
		return delegate.isDateFormatEnabled();
	}
	public int getValuePosition(double value) throws RemoteException {
		return delegate.getValuePosition(value);
	}
	public double getPositionValue(int position) throws RemoteException {
		return delegate.getPositionValue(position);
	}
	public double getValueFromPosition(double position) throws RemoteException {
		return delegate.getValueFromPosition(position);
	}
	public double getPositionFromValue(double value) throws RemoteException {
		return delegate.getPositionFromValue(value);
	}
	public void setLabelDataAndTitle(IDataset labels) throws RemoteException {
		delegate.setLabelDataAndTitle(labels);
	}
	public void setMaximumRange(double lower, double upper) throws RemoteException {
		delegate.setMaximumRange(lower, upper);
	}
	public String format(Object value) throws RemoteException {
		return delegate.format(value);
	}
	public double getScaling() throws RemoteException {
		return delegate.getScaling();
	}
	public void setAutoFormat(boolean autoFormat) throws RemoteException {
		delegate.setAutoFormat(autoFormat);
	}
	public boolean isAutoFormat() throws RemoteException {
		return delegate.isAutoFormat();
	}
	public void setAxisAutoscaleTight(boolean axisTight) throws RemoteException {
		delegate.setAxisAutoscaleTight(axisTight);
	}
	public boolean isAxisAutoscaleTight() throws RemoteException {
		return delegate.isAxisAutoscaleTight();
	}

	@Override
	public IAxis getDelegate() {
		return delegate;
	}

  
}
