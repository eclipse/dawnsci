/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.image;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.ui.services.IDisposable;

public class PlotImageData {

	public static enum PlotImageType {
		XY_PLOT, IMAGE_ONLY, IMAGE_PLOT, SURFACE_PLOT;
	}
	
	private String   plotTitle;
	private IDataset data;
	private int      width;
	private int      height;
	private PlotImageType type=PlotImageType.IMAGE_ONLY;
	private boolean constantRange=false;
	
	private double yLower, yUpper, xLower, xUpper, zUpper, zLower;
	
	private Object imageServiceBean;
	
	/**
	 * An object which may be used to cache the plotting
	 * system when doing images in 1D or surfaces. This 
	 * cache makes drawing off screen more efficient.
	 */
	private IDisposable   disposable;
	
	public PlotImageData() {
		
	}
	
	public PlotImageData(IDataset data, int width, int height) {
		this.data   = data;
		this.width  = width;
		this.height = height;
	}
	
	public IDataset getData() {
		return data;
	}
	public void setData(IDataset data) {
		this.data = data;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public PlotImageType getType() {
		return type;
	}

	public void setType(PlotImageType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (constantRange ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((disposable == null) ? 0 : disposable.hashCode());
		result = prime * result + height;
		result = prime * result
				+ ((plotTitle == null) ? 0 : plotTitle.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + width;
		long temp;
		temp = Double.doubleToLongBits(xLower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(xUpper);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yLower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yUpper);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(zLower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(zUpper);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlotImageData other = (PlotImageData) obj;
		if (constantRange != other.constantRange)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (disposable == null) {
			if (other.disposable != null)
				return false;
		} else if (!disposable.equals(other.disposable))
			return false;
		if (height != other.height)
			return false;
		if (plotTitle == null) {
			if (other.plotTitle != null)
				return false;
		} else if (!plotTitle.equals(other.plotTitle))
			return false;
		if (type != other.type)
			return false;
		if (width != other.width)
			return false;
		if (Double.doubleToLongBits(xLower) != Double
				.doubleToLongBits(other.xLower))
			return false;
		if (Double.doubleToLongBits(xUpper) != Double
				.doubleToLongBits(other.xUpper))
			return false;
		if (Double.doubleToLongBits(yLower) != Double
				.doubleToLongBits(other.yLower))
			return false;
		if (Double.doubleToLongBits(yUpper) != Double
				.doubleToLongBits(other.yUpper))
			return false;
		if (Double.doubleToLongBits(zLower) != Double
				.doubleToLongBits(other.zLower))
			return false;
		if (Double.doubleToLongBits(zUpper) != Double
				.doubleToLongBits(other.zUpper))
			return false;
		return true;
	}

	public IDisposable getDisposable() {
		return disposable;
	}

	public void setDisposible(IDisposable disposable) {
		this.disposable = disposable;
	}

	public String getPlotTitle() {
		return plotTitle;
	}

	public void setPlotTitle(String plotTitle) {
		this.plotTitle = plotTitle;
	}

	public double getyLower() {
		return yLower;
	}

	public void setyLower(double yLower) {
		this.yLower = yLower;
	}

	public double getyUpper() {
		return yUpper;
	}

	public void setyUpper(double yUpper) {
		this.yUpper = yUpper;
	}

	public double getxLower() {
		return xLower;
	}

	public void setxLower(double xLower) {
		this.xLower = xLower;
	}

	public double getxUpper() {
		return xUpper;
	}

	public void setxUpper(double xUpper) {
		this.xUpper = xUpper;
	}

	public boolean isConstantRange() {
		return constantRange;
	}

	public void setConstantRange(boolean constantRange) {
		this.constantRange = constantRange;
	}

	public double getzUpper() {
		return zUpper;
	}

	public void setzUpper(double zUpper) {
		this.zUpper = zUpper;
	}

	public double getzLower() {
		return zLower;
	}

	public void setzLower(double zLower) {
		this.zLower = zLower;
	}

	public Object getImageServiceBean() {
		return imageServiceBean;
	}

	public void setImageServiceBean(Object imageServiceBean) {
		this.imageServiceBean = imageServiceBean;
	}
	
}
