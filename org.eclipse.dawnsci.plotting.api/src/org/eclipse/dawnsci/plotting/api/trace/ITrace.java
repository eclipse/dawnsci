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


import org.eclipse.dawnsci.analysis.api.INameable;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * A representation of a plotted data set.
 * 
 * 
 * 
 * @author Matthew Gerring
 *
 */
public interface ITrace extends INameable {
		
	/**
	 * The name of the original data that this trace was plotted from or null
	 * @return
	 */
	public String getDataName();
	/**
	 * The name of the original data that this trace was plotted from or null
	 * @return data name
	 */
	public void setDataName(String name);

	/**
	 * Call this method to return a plotted data set by this trace.
	 * Different trace types will return different ranks from getData().
	 * For ILineTrace it is 1D line data
	 * For IImageTrace it is a 2D image
	 * etc.
	 */
	public IDataset getData();
	
	/**
	 * True if visible
	 * @return
	 */
	public boolean isVisible();

	/**
	 * True if visible
	 * @return
	 */
	public void setVisible(boolean isVisible);
	
	/**
	 * True if user trace (normally is)
	 * @return
	 */
	public boolean isUserTrace();

	/**
	 * True if visible
	 * @return
	 */
	public void setUserTrace(boolean isUserTrace);

	/**
	 * An object which may be set by API users to record information
	 * about the plot. Ideally avoid objects containing large data
	 * in this method.
	 * 
	 * @return
	 */
	public Object getUserObject();
	
	/**
	 * An object which may be set by API users to record information
	 * about the plot. Ideally avoid objects containing large data
	 * in this method.
	 * 
	 * @return
	 */
	public void setUserObject(Object userObject);
	
	/**
	 * @return true if trace is plotted using a 3D viewer.
	 * @return
	 */
	public boolean is3DTrace();

	/**
	 * Called to release system resources.
	 */
	public void dispose();
	
	/**
	 * The rank of data plotted, 1 for XY, 2 for Image and surfaces, 3 for volumes.
	 * @return
	 */
	public int getRank();
}
