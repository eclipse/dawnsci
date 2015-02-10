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

package org.eclipse.dawnsci.plotting.api.preferences;


public class BasePlottingConstants {

	/**
	 * 
	 */
	public final static String XY_SHOWLEGEND = "org.csstudio.swt.xygraph.preferences.show.legend";

	/**
	 * Used to store palette preference
	 */
	public static final String COLOUR_SCHEME = "org.dawb.plotting.system.colourSchemeName";
	/**
	 *  This String is used to retrieve the plot view colour map preference which is in analysis.rcp
	 *  Make sure to search for the string if modifying it
	 */
	public static final String PLOT_VIEW_PLOT2D_COLOURMAP = "plotView.plot2DcolourMap";

	/**
	 * Used to store origin preference, do not change string
	 */
	public static final String ORIGIN_PREF = "org.dawb.plotting.system.originChoice";

	/**
	 * true when the image should keep aspect ratio, otherwise it will stretch to the available area.
	 */
	public static final String ASPECT = "org.dawb.plotting.system.aspectRatio";

	/**
	 * true when zooming should rehistogram, also rehistograms when pressed.
	 */
	public static final String HISTO = "org.dawb.plotting.system.rehistogram";

	/**
	 * Used to store histo preference
	 */
	public static final String HISTO_PREF = "org.dawb.plotting.system.histogram.type";

	/**
	 * 
	 */
	public static final String HISTO_LO = "org.dawb.plotting.system.histogram.lo";
	
	/**
	 * 
	 */
	public static final String HISTO_HI = "org.dawb.plotting.system.histogram.hi";

	/**
	 * Used to store downsample preference
	 */
	public static final String DOWNSAMPLE_PREF = "org.dawb.plotting.system.downsample.type";
	
	
	/**
	 * Used for cut pixels
	 */
	public static final String MIN_CUT = "org.dawb.plotting.system.histogram.min.cut";
	public static final String MAX_CUT = "org.dawb.plotting.system.histogram.max.cut";
	public static final String NAN_CUT = "org.dawb.plotting.system.histogram.nan.cut";

	/**
	 * Used to identify the config setting action in the toolbar
	 */
	public static final String CONFIG_SETTINGS = "org.dawnsci.plotting.config.settings";

	/**
	 * Used to identify the rescale action
	 */
	public static final String RESCALE = "org.dawb.workbench.plotting.rescale";

	/**
	 * Used to identify the action to plot data as separate plots
	 */
	public static final String PLOT_INDEX = "org.dawb.workbench.plotting.plotIndex";

	/**
	 * Used to identify the action: "Plot using first data set as x-axis"
	 */
	public static final String PLOT_X_AXIS = "org.dawb.workbench.plotting.plotX";

	/**
	 * Used to identify the add region action
	 */
	public static final String ADD_REGION = "org.dawb.workbench.ui.editors.plotting.swtxy.addRegions";

	/**
	 * Used to identify the remove region action
	 */
	public static final String REMOVE_REGION = "org.dawb.workbench.ui.editors.plotting.swtxy.removeRegions";

	/**
	 * Used to identify the autoscale action
	 */
	public static final String AUTO_SCALE = "org.csstudio.swt.xygraph.autoscale";

	/**
	 * Property to say if we use the functions with a 24 bit image or not.
	 */
	public static final String USE_PALETTE_FUNCTIONS = "org.dawnsci.plotting.use.paletteFunctions";
}