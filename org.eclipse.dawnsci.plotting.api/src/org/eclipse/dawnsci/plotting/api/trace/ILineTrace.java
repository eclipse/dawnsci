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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.axis.IAxis;
import org.eclipse.swt.graphics.Color;

/**
 * Line trace is used for all 1D trace data. So as well as lines,
 * it is used for histogram data. You can transform lines to histograms
 * using the setTraceType method.
 * 
 * @author Matthew Gerring
 *
 */
public interface ILineTrace extends ITrace {

	public enum TraceType {
		/** Solid Line */
		SOLID_LINE("Solid Line", true),
		
		/** Dash Line */
		DASH_LINE("Dash Line", true),
		
		/** Only draw point whose style is defined by pointStyle. 
		 * Its size is defined by pointSize. */
		POINT("Point", true),
		
		/** Draw each data point as a bar whose width is defined by lineWidth.
		 *  The data point is in the middle of the bar on X direction. 
		 *  The bottom of the bar depends on the baseline.
		 *  The alpha of the bar is defined by areaAlpha. */
		HISTO("Histogram", true),
		
		/** Fill the area under the trace.  
		 * The bottom of the filled area depends on the baseline.
		 *  The alpha of the filled area is defined by areaAlpha. */ 
		AREA("Area", true),
		/**
		 * Solid line in step. It looks like the y value(on vertical direction) changed firstly.
		 */
		STEP_VERTICALLY("Step Vertically", true),
		
		/**
		 * Solid line in step. It looks like the x value(on horizontal direction) changed firstly.
		 */
		STEP_HORIZONTALLY("Step Horizontally", true);
		
				
		private boolean is1D;
		public static final List<TraceType> ALL;
		static {
			List<ILineTrace.TraceType> tmp = new ArrayList<ILineTrace.TraceType>();
			tmp.add(SOLID_LINE);
			tmp.add(DASH_LINE);
			tmp.add(POINT);
			tmp.add(HISTO);
			tmp.add(AREA);
			tmp.add(STEP_VERTICALLY);
			tmp.add(STEP_HORIZONTALLY);
			ALL = Collections.unmodifiableList(tmp);
		}
	
		private TraceType(String description, boolean is1D) {
			 this.description = description;
			 this.is1D        = is1D;
		}
		private String description;
		
		@Override
		public String toString() {
			return description;
		}
		/**
		 * defines if the TraceType is applicable to
		 * 1D only.
		 * @return
		 */
		public boolean is1D() {
			return is1D;
		}
	}

	public enum PointStyle{
		NONE("None"),
		POINT("point(" + (char)7 + ")"),
		CIRCLE("Circle(o)"),
		FILLED_CIRCLE("Filled Circle"),
		TRIANGLE("Triangle"),
		FILLED_TRIANGLE("Filled Triangle"),
		SQUARE("Square"),
		FILLED_SQUARE("Filled Square"),
		DIAMOND("Diamond"),
		FILLED_DIAMOND("Filled Diamond"),
		XCROSS("XCross(x)"),
		CROSS("Cross(+)"),
		BAR("Bar(|)");
		
		public static final List<PointStyle> ALL;
		static {
			List<PointStyle> tmp = new ArrayList<ILineTrace.PointStyle>();
			tmp.add(NONE);
			tmp.add(POINT);
			tmp.add(CIRCLE);
			tmp.add(FILLED_CIRCLE);
			tmp.add(TRIANGLE);
			tmp.add(FILLED_TRIANGLE);
			tmp.add(SQUARE);
			tmp.add(FILLED_SQUARE);
			tmp.add(DIAMOND);
			tmp.add(FILLED_DIAMOND);
			tmp.add(XCROSS);
			tmp.add(CROSS);
			tmp.add(BAR);
			ALL = Collections.unmodifiableList(tmp);
		}
		
		private PointStyle(String description) {
			 this.description = description;
		}
		private String description;
		
		@Override
		public String toString() {
			return description;
		}
	}

	public enum ErrorBarType{
		NONE,
		PLUS,		
		MINUS,
		BOTH;
		
		
		public static final List<ErrorBarType> ALL;
		static {
			List<ErrorBarType> tmp = new ArrayList<ErrorBarType>(4);
			tmp.add(NONE);
			tmp.add(PLUS);
			tmp.add(MINUS);
			tmp.add(BOTH);
			ALL = Collections.unmodifiableList(tmp);
		}
	}
	public int getErrorBarWidth();
	/**
	 * The size of the error bar in pixels not real coordinates.
	 * @param errorBarCapWidth
	 */
	public void setErrorBarWidth(int errorBarCapWidth);
	
	public Color getTraceColor();
	/**
	 * The Color of the trace
	 * @param traceColor
	 */
	public void setTraceColor(final Color traceColor);
	
	public ILineTrace.TraceType getTraceType();
	/**
	 * Set TraceType. For instance change to a bar plot by calling this.
	 * @param traceType
	 */
	public void setTraceType(ILineTrace.TraceType traceType);
	
	public ILineTrace.PointStyle getPointStyle();
	/**
	 * The style of points if plotted by the trace.
	 * @param pointStyle
	 */
	public void setPointStyle(ILineTrace.PointStyle pointStyle);
	
	public int getLineWidth();
	/**
	 * The width of line plotted, if trace is a line plot.
	 * @param lineWidth
	 */
	public void setLineWidth(int lineWidth);

	public int getPointSize();
	/**
	 * The size of points plotted if Trace is a Line type.
	 * @param pointSize
	 */
	public void setPointSize(int pointSize);
	
	public int getAreaAlpha();
	/**
	 * The transparency of areas draw by the trace (for instance 
	 * in HISTO trace type or FILL trace type).
	 * @param areaAlpha
	 */
	public void setAreaAlpha(int areaAlpha);
	
	public boolean isAntiAliasing();
	/**
	 * Turn on anti-aliasing of 1D plots here. No effect on images.
	 * @param antiAliasing
	 */
	public void setAntiAliasing(boolean antiAliasing) ;
	
	public boolean isErrorBarEnabled();
	/**
	 * For 1D traces
	 * @param errorBarEnabled
	 */
	public void setErrorBarEnabled(boolean errorBarEnabled);
	
	public ILineTrace.ErrorBarType getYErrorBarType();
	/**
	 * If showing error bars, sets the y error bar type. Use setErrorBarEnabled(...)
	 * to start showing the error bars.
	 * 
	 * @param errorBarType
	 */
	public void setYErrorBarType(ILineTrace.ErrorBarType errorBarType);
	
	public ILineTrace.ErrorBarType getXErrorBarType();
	/**
	 * If showing error bars, sets the y error bar type. Use setErrorBarEnabled(...)
	 * to start showing the error bars.
	 * 
	 * @param errorBarType
	 */
	public void setXErrorBarType(ILineTrace.ErrorBarType errorBarType) ;
	
	public Color getErrorBarColor();
	/**
	 * Color of error bars.
	 * @param errorBarColor
	 */
	public void setErrorBarColor(final Color errorBarColor);

	
	/**
	 * The Y data of the plot, considered as the main data as the x-data
	 * might often be indices of y. 
	 * @return
	 */
	public IDataset getYData();
	
	
	/**
	 * The x data of the plot - might often be indices of y. 
	 * @return
	 */
	public IDataset getXData();

	/**
	 * Set the yData for the trace, replaces old yData
	 * Repaint may be required as the system does not assume you want to repaint right away.
	 * 
	 * If the yData is an instance of IErrorDataset the method getError will be called.
	 * If this is non-null then error bars will be switched on (unless they have been explicitly 
	 * turned off with setErrorBarEnabled(false)) 
	 * 
	 * @param yData
	 */
	public void setData(IDataset xData, IDataset yData);
	
	/**
	 * May be called to refresh the whole graph, for instance if you change trace
	 * color, you may need to repaint the legend.
	 */
	public void repaint();
	
	/**
	 * 
	 * @return The x-axis which this trace is being drawn with
	 */
	public IAxis getXAxis();
	/**
	 * 
	 * @return The y-axis which this trace is being drawn with
	 */
	public IAxis getYAxis();
	
}
