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

package org.eclipse.dawnsci.plotting.api.jreality.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.plotting.api.jreality.impl.Plot1DStyles;

/**
 * Class encapsulates generating unique colours for plots.
 */
public class PlotColorUtility {
	
	// NOTE Color choice used to be 9, however 16 is a better choice for scan plots as there
	// are regularly more than nine plots in a single scan.
	static Color[] GRAPH_DEFAULT_COLORS = { new Color(0, 0, 0) ,new Color(0, 0, 255),new Color(255, 0, 0),
		new Color(204, 0, 204), new Color(204, 0, 0), new Color(0, 153, 51), new Color(102, 0, 102),
		new Color(255, 102, 255), new Color(255, 155, 0), new Color(204, 255, 0), new Color(51, 255, 51),
		new Color(102, 255, 255), new Color(102, 102, 255), new Color(153, 153, 0),
		new Color(204, 204, 205), new Color(255, 204, 204)};

	private static final  Plot1DStyles [] GRAPH_DEFAULT_STYLES;
	private static final  int[]           GRAPH_DEFAULT_LINEWIDTHS;
	
	static {
		GRAPH_DEFAULT_STYLES = new  Plot1DStyles [GRAPH_DEFAULT_COLORS.length];
		GRAPH_DEFAULT_LINEWIDTHS = new  int [GRAPH_DEFAULT_COLORS.length];
		for (int i = 0; i < GRAPH_DEFAULT_COLORS.length; i++) {
			GRAPH_DEFAULT_STYLES[i]     = Plot1DStyles.SOLID;
			GRAPH_DEFAULT_LINEWIDTHS[i] = 1; // NOTE: Changed from 2 to 1, walkthrough with Joachim
		}
	}
	
		
	/**
	 * Get the size of the colour table
	 * @return size of the colour table
	 */
	
	public static int getSize()
	{
		return GRAPH_DEFAULT_COLORS.length;
	}
	
	/**
	 * Get the colour in the colour table
	 * @param nr entry number in the colour table
	 * @return colour entry in the table as AWT colour
	 */
	public static final Color getDefaultColour(int nr) {
		return GRAPH_DEFAULT_COLORS[nr%GRAPH_DEFAULT_COLORS.length];
	}
	
	/**
	 * Get the style in the colour table
	 * @param nr entry number in the style table
	 * @return Plot1DStyle entry in the table
	 */
	public static final Plot1DStyles getDefaultStyle(int nr) {
		return GRAPH_DEFAULT_STYLES[nr%GRAPH_DEFAULT_STYLES.length];
	}
	
	/**
	 * Get the line width in the colour table
	 * @param nr entry number in the line width table
	 * @return line width entry in the table
	 */
	public static final int getDefaultLineWidth(int nr) {
		return GRAPH_DEFAULT_LINEWIDTHS[nr%GRAPH_DEFAULT_LINEWIDTHS.length];
	}
	
	private static Map<String,Color> nameCache;
	/**
	 * 
	 * @param iplot
	 * @param name
	 * @return colour
	 */
	public static final Color getDefaultColour(final int iplot, final String name) {
		
		if (nameCache == null) nameCache = new HashMap<String,Color>(1000);
		if (nameCache.containsKey(name)) return nameCache.get(name);
		
		final Color color = getDefaultColour(iplot);
		nameCache.put(name, color);
		
		// More than 1000 names of different plots are less likely
		if (nameCache.size()>1000) nameCache.clear();
		
		return color;
	}
	
	/**
	 * Set a new default colour for example from the preferences
	 * @param nr number in the table that should be replaced
	 * @param color new colour
	 */
	public static final void setDefaultColour(int nr, java.awt.Color color) {
		if (nr < GRAPH_DEFAULT_COLORS.length) {
			GRAPH_DEFAULT_COLORS[nr] = color;
		}
	}
	
	/**
	 * Set a new default style for example from the preferences
	 * @param nr number in the table that should be replaced
	 * @param newStyle new style
	 */
	public static final void setDefaultStyle(int nr, Plot1DStyles newStyle) {
		if (nr < GRAPH_DEFAULT_STYLES.length) {
			GRAPH_DEFAULT_STYLES[nr] = newStyle;
		}
	}
	
	/**
	 * Set a new default line width for example from the preferences
	 * @param nr number in the table that should be replaced
	 * @param newLineWidth new line width
	 */
	public static final void setDefaultLineWidth(int nr, int newLineWidth) {
		if (nr < GRAPH_DEFAULT_LINEWIDTHS.length) {
			GRAPH_DEFAULT_LINEWIDTHS[nr] = newLineWidth;
		}
	}

}
