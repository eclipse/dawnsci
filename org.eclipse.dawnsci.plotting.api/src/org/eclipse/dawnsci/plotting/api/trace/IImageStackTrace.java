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

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;


/**
 * Class which extends IImageTrace and allows a stack
 * of images to be plotted in 2D with a scale widget.
 * 
 * The scale appears in the plot and can be moved by the user.
 *  
 * @author Matthew Gerring
 *
 */
public interface IImageStackTrace extends IImageTrace {
	
	/**
	 * Set the ILazyDataset to use for the stack.
	 * Normally 3D where the first index is the stack the 
	 * other two are the 
	 * @see ImageStackLoader
	 * @param stack
	 */
	public void setStack(ILazyDataset stack);
		
	/**
	 * The size of the stack we are editing or -1 if something went
	 * wrong loading the stack.
	 * 
	 * @return
	 */
	public int getStackSize();
	
	/**
	 * The current location out of the stack which 
	 * we are plotting.
	 * @return
	 */
	public int getStackIndex();
	
	/**
	 * Set the location in the stack which we plot.
	 * Updates the plot to the new index.
	 * 
	 * @param index
	 */
	public void setStackIndex(int index);

	/**
	 * Notifies of stack position.
	 * @param l
	 */
	public void addStackPositionListener(IStackPositionListener l);
	
	
	/**
	 * Removes notification of stack position.
	 * @param l
	 */
	public void removeStackPositionListener(IStackPositionListener l);

}
