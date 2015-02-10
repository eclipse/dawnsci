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

package org.eclipse.dawnsci.plotting.api;

/**
 * Export / save tools used to export / save a plotting system
 * 
 * @author Baha El Kassaby
 *
 */
public interface IPrintablePlotting {

	/**
	 * Prints out the plotting in a scaled way
	 */
	public void printScaledPlotting();
	
	/**
	 * Print/preview the plotting
	 */
	public void printPlotting();

	/**
	 * Copy plotting to clip board of the OS
	 */
	public void copyPlotting();

	/**
	 * Export/Save the plotting to an image format (PostScript, JPEG, PNG) through a dialog box
	 * 
	 * @param filename
	 */
	public String savePlotting(String filename)  throws Exception;

	/**
	 * Export/Save the plotting to an image format without a dialog box
	 * 
	 * @param filename
	 */
	public void savePlotting(String filename, String filetype)  throws Exception;
}
