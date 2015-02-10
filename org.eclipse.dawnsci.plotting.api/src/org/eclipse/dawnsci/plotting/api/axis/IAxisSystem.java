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
package org.eclipse.dawnsci.plotting.api.axis;

import java.util.List;

/**
 * No methods in this interface are thread safe.
 * 
 * @author Matthew Gerring
 *
 */
public interface IAxisSystem {
	
	/**
	 * Called to reset the axes
	 */
	public void resetAxes();

	/**
	 * Use this method to create axes other than the default y and x axes.
	 * @param title
	 * @param isYAxis, normally it is.
	 * @param side - either SWT.LEFT, SWT.RIGHT, SWT.TOP, SWT.BOTTOM
	 * @return
	 */
	public IAxis createAxis(final String title, final boolean isYAxis, final int side);
	
	/**
	 * Use this method to delete any extra axes which have been created.
	 * 
	 * You may not delete the primary axes using this method, only those which 
	 * you have created using createAxis(...)
	 * 
	 * @param title
	 * @return
	 */
	public IAxis removeAxis(final IAxis axis);
	
	/**
	 * Get the list of all axes
	 * @return
	 */
	public List<IAxis> getAxes();
	
	/**
	 * Look up the axis from the list of axes by the name used when createAxis(...) was called.
	 * @param name
	 * @return
	 */
	public IAxis getAxis(String name);

	/**
	 * The current y axis to plot to. Intended for 1D plotting with multiple axes.
	 * @return
	 */
	public IAxis getSelectedYAxis();
	
	/**
	 * Set the current plotting yAxis. Intended for 1D plotting with multiple axes.
	 * May be called with null to reset to the primary axis.
	 * @param yAxis
	 */
	public void setSelectedYAxis(IAxis yAxis);
	
	/**
	 * The current x axis to plot to. Intended for 1D plotting with multiple axes.
	 * @return
	 */
	public IAxis getSelectedXAxis();
	
	/**
	 * Set the current plotting xAxis. Intended for 1D plotting with multiple axes.
	 * May be called with null to reset to the primary axis.
	 * 
	 * @param xAxis
	 */
	public void setSelectedXAxis(IAxis xAxis);
	

	/**
	 * Call to rescale the axes, for instance after changing data of a few axes you want the
	 * data to be scaled properly. Not thread safe.
	 */
	public void autoscaleAxes();
	
	
	/**
	 * A listener to be notified of mouse position.
	 * @param l
	 */
	public void addPositionListener(IPositionListener l);
	
	
	/**
	 * A listener to stop being notified of mouse position.
	 * @param l
	 */
	public void removePositionListener(IPositionListener l);
	
	/**
	 * A listener to be notified of mouse position.
	 * @param l
	 */
	public void addClickListener(IClickListener l);
	
	
	/**
	 * A listener to stop being notified of mouse position.
	 * @param l
	 */
	public void removeClickListener(IClickListener l);

}
