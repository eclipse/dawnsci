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

package org.eclipse.dawnsci.plotting.api.jreality.tool;

import java.util.List;

public interface IImagePositionEvent extends IDataPositionEvent{

	/**
	 * Bit mask for left mouse button
	 */
	public static final short LEFTMOUSEBUTTON = 1;

	/**
	 * Bit mask for right mouse button
	 */
	public static final short RIGHTMOUSEBUTTON = 2;

	/**
	 * Bit mask for the control key
	 */
	public static final short CTRLKEY = 4;

	/**
	 * Bit mask for the shift key
	 */
	public static final short SHIFTKEY = 8;

	/**
	 * Get the id of the overlay primitive that the mouse is currently on
	 * @return -1 if there is no overlay primitive otherwise its id
	 */
	public abstract int getPrimitiveID();

	/**
	 * Get the ids of the overlay primitive that the mouse is currently on
	 * @return an array, possibly empty, of the primitives that were hit
	 */
	public abstract List<Integer> getPrimitiveIDs();

	/**
	 * // TODO This really should return a double, ie if you can draw on a double (See OverlayProviders) 
	 * // you can also also click on a double
	 * Get the position in the image in pixel coordinates
	 * @return image position
	 */
	public abstract int[] getImagePosition();

	/**
	 * Get the specific bit flags
	 * @return the bit flags
	 */
	public abstract short getFlags();

}
