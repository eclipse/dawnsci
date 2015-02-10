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

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * This trace is currently only available with Java8 and there is
 * no DAWN executable based on Java8 available on the website.
 * 
 * Probably DAWN 2.0 in 2015 will be Java8 based.
 * 
 * @author Matthew Gerring
 *
 */
public interface IIsosurfaceTrace extends IImage3DTrace {

	
	/**
	 * Plot an isosurface on the plotting system.
	 * 
	 * Thread safe
	 * 
	 * @param points      (float)
	 * @param textCoords  (float)
	 * @param faces       (int)
	 * @param axes        May be null.
	 */
	public void setData(final IDataset points, 
			            final IDataset textCoords, 
			            final IDataset faces, 
                        final List<? extends IDataset> axes);


	/**
	 * Default is CullFace.NONE
	 * @return
	 */
	public CullFace getCullFace();
	
	/**
	 * Default is CullFace.NONE
	 * @param culling
	 */
	public void setCullFace(CullFace culling);
	
	/**
	 * The RGB components of the material
	 * @return
	 */
	public int[] getMaterialRBG();
	
	/**
	 * The Opacity component of the material
	 * @return
	 */
	public double getMaterialOpacity();
	
	/**
     * @param red the red component, in the range {@code 0-255}
     * @param green the green component, in the range {@code 0-255}
     * @param blue the blue component, in the range {@code 0-255}
     * @param opacity the opacity component, in the range {@code 0.0-1.0}
     * @return the {@code Color}
     * @throws IllegalArgumentException if any value is out of range
	 */
	public void setMaterial(int red, int green, int blue, double opacity);
	
	public enum CullFace {

	    /**
	     * Do not perform any face culling.
	     */
	    NONE,

	    /**
	     * Cull all back-facing polygons. BACK is defined as clockwise winding
	     * order.
	     */
	    BACK,

	    /**
	     * Cull all front-facing polygons. FRONT is defined as counter-clockwise
	     * winding order.
	     */
	    FRONT;
	}
}
