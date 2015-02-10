/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.remote;

import java.io.Serializable;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.dawnsci.plotting.api.axis.IAxisListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

class ThreadSafeAxis extends ThreadSafeObject implements IAxis, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295646820477687920L;
	
	private IAxis delegate;

	public ThreadSafeAxis(IAxis axis) {
		super(axis);
		this.delegate = axis;
	}

	@Override
	public String getTitle() {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setTitle(String title) {
		call(getMethodName(Thread.currentThread().getStackTrace()), title);
	}

	@Override
	public void setTitleFont(Font titleFont) {
		call(getMethodName(Thread.currentThread().getStackTrace()), titleFont);
	}

	@Override
	public Font getTitleFont() {
		return (Font)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public boolean isPrimaryAxis() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public boolean isLog10() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setLog10(boolean isLog10) {
		call(getMethodName(Thread.currentThread().getStackTrace()), isLog10);
	}

	@Override
	public void setForegroundColor(Color color) {
		call(getMethodName(Thread.currentThread().getStackTrace()), color);
	}

	@Override
	public Color getForegroundColor() {
		return (Color)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setBackgroundColor(Color color) {
		call(getMethodName(Thread.currentThread().getStackTrace()), color);
	}

	@Override
	public Color getBackgroundColor() {
		return (Color)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public boolean isShowMajorGrid() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setShowMajorGrid(boolean showMajorGrid) {
		call(getMethodName(Thread.currentThread().getStackTrace()), showMajorGrid);
	}

	@Override
	public boolean isShowMinorGrid() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setShowMinorGrid(boolean showMinorGrid) {
		call(getMethodName(Thread.currentThread().getStackTrace()), showMinorGrid);
	}

	@Override
	public Color getMajorGridColor() {
		return (Color)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setMajorGridColor(Color majorGridColor) {
		call(getMethodName(Thread.currentThread().getStackTrace()), majorGridColor);
	}

	@Override
	public Color getMinorGridColor() {
		return (Color)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setMinorGridColor(Color minorGridColor) {
		call(getMethodName(Thread.currentThread().getStackTrace()), minorGridColor);
	}

	@Override
	public String getFormatPattern() {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setFormatPattern(String formatPattern) {
		call(getMethodName(Thread.currentThread().getStackTrace()), formatPattern);
	}

	@Override
	public boolean isYAxis() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setYAxis(boolean isYAxis) {
		call(getMethodName(Thread.currentThread().getStackTrace()), isYAxis);
	}

	@Override
	public void setVisible(boolean b) {
		call(getMethodName(Thread.currentThread().getStackTrace()), b);
	}

	@Override
	public boolean isVisible() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public double getUpper() {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public double getLower() {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setRange(double start, double end) {
		call(getMethodName(Thread.currentThread().getStackTrace()), start, end);
	}

	@Override
	public void addAxisListener(IAxisListener listener) {
		delegate.addAxisListener(listener);
	}

	@Override
	public void removeAxisListener(IAxisListener listener) {
		delegate.removeAxisListener(listener);
	}

	@Override
	public void setDateFormatEnabled(boolean dateEnabled) {
		call(getMethodName(Thread.currentThread().getStackTrace()), dateEnabled);
	}

	@Override
	public boolean isDateFormatEnabled() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public int getValuePosition(double value) {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public double getPositionValue(int position) {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()), position);
	}

	@Override
	public double getValueFromPosition(double position) {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()), position);
	}

	@Override
	public double getPositionFromValue(double value) {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()), value);
	}

	@Override
	public void setLabelDataAndTitle(IDataset labels) {
		call(getMethodName(Thread.currentThread().getStackTrace()), labels);
	}

	@Override
	public void setMaximumRange(double lower, double upper) {
		call(getMethodName(Thread.currentThread().getStackTrace()), lower, upper);
	}

	@Override
	public String format(Object value) {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()), value);
	}

	@Override
	public double getScaling() {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setAutoFormat(boolean autoFormat) {
		call(getMethodName(Thread.currentThread().getStackTrace()), autoFormat);
	}

	@Override
	public boolean isAutoFormat() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setAxisAutoscaleTight(boolean axisTight) {
		call(getMethodName(Thread.currentThread().getStackTrace()), axisTight);

	}

	@Override
	public boolean isAxisAutoscaleTight() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	IAxis getDelegate() {
		return delegate;
	}

}
