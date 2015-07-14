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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.dawnsci.plotting.api.histogram.HistogramBound;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.HistoType;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.ImageOrigin;
import org.eclipse.dawnsci.plotting.api.trace.ICompositeTrace;
import org.eclipse.dawnsci.plotting.api.trace.IDownSampleListener;
import org.eclipse.dawnsci.plotting.api.trace.IImageStackTrace;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.IIsosurfaceTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineStackTrace;
import org.eclipse.dawnsci.plotting.api.trace.ILineTrace;
import org.eclipse.dawnsci.plotting.api.trace.IMulti2DTrace;
import org.eclipse.dawnsci.plotting.api.trace.IPaletteListener;
import org.eclipse.dawnsci.plotting.api.trace.IScatter3DTrace;
import org.eclipse.dawnsci.plotting.api.trace.IStackPositionListener;
import org.eclipse.dawnsci.plotting.api.trace.ISurfaceTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.IVectorTrace;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.PaletteData;

/**
 * A class to wrap any trace in a single object which is exposed to python.
 * If the user tries to call a method that does not reflect into python, they
 * will get a stack trace saying that the method does not exist.
 */
class ThreadSafeTrace extends ThreadSafeObject implements ITrace,
                                                          ILineTrace,
                                                          IImageTrace,
                                                          ICompositeTrace,
                                                          IVectorTrace,
                                                          ISurfaceTrace,
                                                          IIsosurfaceTrace,
                                                          IMulti2DTrace,
                                                          ILineStackTrace,
                                                          IScatter3DTrace,
                                                          IImageStackTrace,
                                                          Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2704054519627538121L;

	private ITrace trace;

	public ThreadSafeTrace(ITrace delegate) {
		super(delegate);
		this.trace = delegate;
	}

	public String getName() {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setName(String name) {
		call(getMethodName(Thread.currentThread().getStackTrace()), name);
	}

	public String getDataName() {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setDataName(String name) {
		call(getMethodName(Thread.currentThread().getStackTrace()), name);
	}

	public IDataset getData() {
		return trace.getData();
	}

	public boolean isVisible() {
		return trace.isVisible();
	}

	public void setVisible(boolean isVisible) {
		call(getMethodName(Thread.currentThread().getStackTrace()), isVisible);
	}

	public boolean isUserTrace() {
		return trace.isUserTrace();
	}

	public void setUserTrace(boolean isUserTrace) {
		call(getMethodName(Thread.currentThread().getStackTrace()), isUserTrace);
	}

	public Object getUserObject() {
		return trace.getUserObject();
	}

	public void setUserObject(Object userObject) {
		call(getMethodName(Thread.currentThread().getStackTrace()), userObject);
	}

	public boolean is3DTrace() {
		return trace.is3DTrace();
	}

	public void dispose() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public int getRank() {
		return trace.getRank();
	}

	ITrace getDelegate() {
		return trace;
	}


	//////////////////////////////////////////////////////////////////////////
	// LineTrace stuff
	//////////////////////////////////////////////////////////////////////////

	public int getErrorBarWidth() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setErrorBarWidth(int errorBarCapWidth) {
		call(getMethodName(Thread.currentThread().getStackTrace()), errorBarCapWidth);
	}

	public Color getTraceColor() {
		return (Color)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setTraceColor(Color traceColor) {
		call(getMethodName(Thread.currentThread().getStackTrace()), traceColor);
	}

	public TraceType getTraceType() {
		return (TraceType)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setTraceType(TraceType traceType) {
		call(getMethodName(Thread.currentThread().getStackTrace()), traceType);
	}

	public PointStyle getPointStyle() {
		return (PointStyle)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setPointStyle(PointStyle pointStyle) {
		call(getMethodName(Thread.currentThread().getStackTrace()), pointStyle);
	}

	public int getLineWidth() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setLineWidth(int lineWidth) {
		call(getMethodName(Thread.currentThread().getStackTrace()), lineWidth);
	}

	public int getPointSize() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setPointSize(int pointSize) {
		call(getMethodName(Thread.currentThread().getStackTrace()), pointSize);
	}

	public int getAreaAlpha() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setAreaAlpha(int areaAlpha) {
		call(getMethodName(Thread.currentThread().getStackTrace()), areaAlpha);
	}

	public boolean isAntiAliasing() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setAntiAliasing(boolean antiAliasing) {
		call(getMethodName(Thread.currentThread().getStackTrace()), antiAliasing);
	}

	public boolean isErrorBarEnabled() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setErrorBarEnabled(boolean errorBarEnabled) {
		call(getMethodName(Thread.currentThread().getStackTrace()), errorBarEnabled);
	}

	public ErrorBarType getYErrorBarType() {
		return (ErrorBarType)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setYErrorBarType(ErrorBarType errorBarType) {
		call(getMethodName(Thread.currentThread().getStackTrace()), errorBarType);
	}

	public ErrorBarType getXErrorBarType() {
		return (ErrorBarType)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setXErrorBarType(ErrorBarType errorBarType) {
		call(getMethodName(Thread.currentThread().getStackTrace()), errorBarType);
	}

	public Color getErrorBarColor() {
		return (Color)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setErrorBarColor(Color errorBarColor) {
		call(getMethodName(Thread.currentThread().getStackTrace()), errorBarColor);
	}

	public IDataset getYData() {
		return (IDataset)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public IDataset getXData() {
		return (IDataset)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public void setData(IDataset xData, IDataset yData) {
		call(getMethodName(Thread.currentThread().getStackTrace()), xData, yData);
	}

	public void repaint() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	public IAxis getXAxis() {
		return new ThreadSafeAxis(((ILineTrace)trace).getXAxis());
	}

	public IAxis getYAxis() {
		return new ThreadSafeAxis(((ILineTrace)trace).getYAxis());
	}


	//////////////////////////////////////////////////////////////////////////
	// IVectorTrace stuff
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void setArrowConfiguration(ArrowConfiguration arrowPosition) {
		call(getMethodName(Thread.currentThread().getStackTrace()), arrowPosition);
	}

	@Override
	public ArrowConfiguration getArrowConfiguration() {
		return (ArrowConfiguration)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setMaximumArrowSize(int screenPixels) {
		call(getMethodName(Thread.currentThread().getStackTrace()), screenPixels);
	}

	@Override
	public int getMaximumArrowSize() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setArrowColor(int... rgb) {
		call(getMethodName(Thread.currentThread().getStackTrace()), rgb);
	}

	@Override
	public int[] getArrowColor() {
		return (int[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setArrowPalette(PaletteData jet) {
		call(getMethodName(Thread.currentThread().getStackTrace()), jet);
	}

	@Override
	public PaletteData getArrowPalette() {
		return (PaletteData)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setCircleColor(int... rgb) {
		call(getMethodName(Thread.currentThread().getStackTrace()), rgb);
	}

	@Override
	public int[] getCircleColor() {
		return (int[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public boolean setData(IDataset vectors, List<IDataset> axes) {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public List<IDataset> getAxes() {
		return (List<IDataset>) call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setVectorNormalization(VectorNormalization type) {
		call(getMethodName(Thread.currentThread().getStackTrace()), type);
	}

	@Override
	public VectorNormalization getVectorNormalization() {
		return (VectorNormalization)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public ArrowHistogram getArrowHistogram() {
		return (ArrowHistogram)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	//////////////////////////////////////////////////////////////////////////
	// ISurfaceTrace stuff
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void setArrowHistogram(ArrowHistogram arrowHistogram) {
		call(getMethodName(Thread.currentThread().getStackTrace()), arrowHistogram);
	}

	@Override
	public void setAxesNames(List<String> axesNames) {
		call(getMethodName(Thread.currentThread().getStackTrace()), axesNames);
	}

	@Override
	public PaletteData getPaletteData() {
		return (PaletteData)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setPaletteData(PaletteData paletteData) {
		call(getMethodName(Thread.currentThread().getStackTrace()), paletteData);
	}

	@Override
	public String getPaletteName() {
		return (String)call(getMethodName(Thread.currentThread().getStackTrace()));
	}


	@Override
	public void setPaletteName(String paletteName) {
		call(getMethodName(Thread.currentThread().getStackTrace()), paletteName);
	}

	@Override
	public void setPalette(String paletteName) {
		call(getMethodName(Thread.currentThread().getStackTrace()), paletteName);
	}

	@Override
	public ImageServiceBean getImageServiceBean() {
		return (ImageServiceBean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void addPaletteListener(IPaletteListener pl) {
		call(getMethodName(Thread.currentThread().getStackTrace()), pl);
	}

	@Override
	public void removePaletteListener(IPaletteListener pl) {
		call(getMethodName(Thread.currentThread().getStackTrace()), pl);
	}

	@Override
	public Number getMin() {
		return (Number)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public Number getMax() {
		return (Number)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public HistogramBound getNanBound() {
		return (HistogramBound)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public HistogramBound getMinCut() {
		return (HistogramBound)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public HistogramBound getMaxCut() {
		return (HistogramBound)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setNanBound(HistogramBound bound) {
		call(getMethodName(Thread.currentThread().getStackTrace()), bound);
	}

	@Override
	public void setMinCut(HistogramBound bound) {
		call(getMethodName(Thread.currentThread().getStackTrace()), bound);

	}

	@Override
	public void setMaxCut(HistogramBound bound) {
		call(getMethodName(Thread.currentThread().getStackTrace()), bound);
	}

	@Override
	public void setMin(Number min) {
		call(getMethodName(Thread.currentThread().getStackTrace()), min);
	}

	@Override
	public void setMax(Number max) {
		call(getMethodName(Thread.currentThread().getStackTrace()), max);
	}

	@Override
	public void setRescaleHistogram(boolean rescaleHistogram) {
		call(getMethodName(Thread.currentThread().getStackTrace()), rescaleHistogram);
	}

	@Override
	public boolean isRescaleHistogram() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public boolean isActive() {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public List<String> getAxesNames() {
		return (List<String>)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public IStatus setWindow(IROI roi, IProgressMonitor monitor) {
		return (IStatus)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	//////////////////////////////////////////////////////////////////////////
	// IIsosurfaceTrace stuff
	//////////////////////////////////////////////////////////////////////////

//	@Override
//	public void setData(IDataset data, List<? extends IDataset> axes) {
//		call(getMethodName(Thread.currentThread().getStackTrace()), axes);
//
//	}

	@Override
	public IROI getWindow() {
		return (IROI)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public IStatus setWindow(IROI window, boolean updateClipping, IProgressMonitor monitor) {
		return (IStatus)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setData(IDataset points, IDataset textCoords, IDataset faces,
			List<? extends IDataset> axes) {
		call(getMethodName(Thread.currentThread().getStackTrace()), points, textCoords, faces, axes);

	}

	@Override
	public CullFace getCullFace() {
		return (CullFace)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setCullFace(CullFace culling) {
		call(getMethodName(Thread.currentThread().getStackTrace()), culling);

	}

	@Override
	public int[] getMaterialRBG() {
		return (int[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public double getMaterialOpacity() {
		return (Double)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setMaterial(int red, int green, int blue, double opacity) {
		call(getMethodName(Thread.currentThread().getStackTrace()), red, green, blue, opacity);
	}
	//////////////////////////////////////////////////////////////////////////
	// IMulti2DTrace stuff
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void setData(List<? extends IDataset> axes, IDataset... s) {
		call(getMethodName(Thread.currentThread().getStackTrace()), axes, s);

	}

	@Override
	public IDataset[] getMulti2D() {
		return (IDataset[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}
	//////////////////////////////////////////////////////////////////////////
	// ILineStackTrace stuff
	//////////////////////////////////////////////////////////////////////////

	@Override
	public IDataset[] getStack() {
		return (IDataset[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setWindow(IROI window) {
		call(getMethodName(Thread.currentThread().getStackTrace()), window);

	}

	@Override
	public IDataset getRGBData() {
		return (IDataset)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public ImageOrigin getImageOrigin() {
		return (ImageOrigin)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setImageOrigin(ImageOrigin origin) {
		call(getMethodName(Thread.currentThread().getStackTrace()), origin);
	}
	//////////////////////////////////////////////////////////////////////////
	// IImageStackTrace stuff
	//////////////////////////////////////////////////////////////////////////

	@Override
	public boolean setData(IDataset image, List<? extends IDataset> axes,
			boolean performAutoScale) {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()));

	}

	@Override
	public void setAxes(List<? extends IDataset> axes, boolean performAutoScale) {
		call(getMethodName(Thread.currentThread().getStackTrace()), axes, performAutoScale);

	}

	@Override
	public DownsampleType getDownsampleType() {
		return (DownsampleType)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setDownsampleType(DownsampleType type) {
		call(getMethodName(Thread.currentThread().getStackTrace()), type);
	}

	@Override
	public void rehistogram() {
		call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public HistoType getHistoType() {
		return (HistoType)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public boolean setHistoType(HistoType type) {
		return (Boolean)call(getMethodName(Thread.currentThread().getStackTrace()), type);
	}

	@Override
	public void setImageUpdateActive(boolean b) {
		call(getMethodName(Thread.currentThread().getStackTrace()), b);

	}

	@Override
	public IDataset getDownsampled() {
		return (IDataset)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public IDataset getDownsampledMask() {
		return (IDataset)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public int getDownsampleBin() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public IDataset getMask() {
		return (IDataset)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setMask(IDataset bd) {
		call(getMethodName(Thread.currentThread().getStackTrace()), bd);
	}

	@Override
	public void sleep() {
		call(getMethodName(Thread.currentThread().getStackTrace()));

	}

	@Override
	public int getBin() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void addDownsampleListener(IDownSampleListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), l);

	}

	@Override
	public void removeDownsampleListener(IDownSampleListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), l);
	}

	@Override
	public IROI getRegionInAxisCoordinates(IROI roi) throws Exception {
		return (IROI)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public double[] getPointInAxisCoordinates(double[] point) throws Exception {
		return (double[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public double[] getPointInImageCoordinates(double[] axisLocation)
			throws Exception {
		return (double[])call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setStack(ILazyDataset stack) {
		call(getMethodName(Thread.currentThread().getStackTrace()), stack);

	}

	@Override
	public int getStackSize() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public int getStackIndex() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void setStackIndex(int index) {
		call(getMethodName(Thread.currentThread().getStackTrace()), index);
	}

	@Override
	public void addStackPositionListener(IStackPositionListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), l);
	}

	@Override
	public void removeStackPositionListener(IStackPositionListener l) {
		call(getMethodName(Thread.currentThread().getStackTrace()), l);
	}

	@Override
	public void setAlpha(int alpha) {
		call(getMethodName(Thread.currentThread().getStackTrace()), alpha);
	}

	@Override
	public int getAlpha() {
		return (Integer)call(getMethodName(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void add(ITrace trace, int index) {
		call(getMethodName(Thread.currentThread().getStackTrace()), trace, index);
	}

	@Override
	public void removeImage(String name) {
		call(getMethodName(Thread.currentThread().getStackTrace()), name);
	}

}
