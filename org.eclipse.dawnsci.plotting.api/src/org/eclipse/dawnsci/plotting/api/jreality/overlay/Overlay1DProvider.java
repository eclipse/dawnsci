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

package org.eclipse.dawnsci.plotting.api.jreality.overlay;

/**
 * 1D Overlay provider
 */
public interface Overlay1DProvider extends OverlayProvider {

	/**
	 * Draw a line primitive
	 * @param primID id of the primitive
	 * @param sx start x position
	 * @param sy start y position
	 * @param ex end x position
	 * @param ey end y position
	 * @return if the draw was successful (true) otherwise (false)
	 */
	
	public boolean drawLine(int primID, double sx,  double sy, double ex, double ey);
	
	/**
	 * Draw a box primitive
	 * @param primID id of the primitive
	 * @param lux left upper x coordinate
	 * @param luy left upper y coordinate
	 * @param rlx right lower x coordinate
	 * @param rly right lower y coordinate
	 * @return if the draw was successful (true) otherwise (false)
	 */
	public boolean drawBox(int primID, double lux, double luy, double rlx, double rly);
	
	
	/**
	 * Draw a label primitive 
	 * @param primID id of the primitive
	 * @param sx x coordinate of the label
	 * @param sy y coordinate of the label
	 * @return if the translation was successful (true) otherwise (false)
	 */
	
	public boolean drawLabel(int primID, double sx, double sy);
	
	
}
