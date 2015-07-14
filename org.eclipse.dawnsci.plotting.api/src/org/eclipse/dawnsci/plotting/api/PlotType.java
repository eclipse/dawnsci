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

public enum PlotType {

	IMAGE(2, 2),        // dimensions=2 X and Y
	
	COMPOSITE_IMAGE(2, 2),        // dimensions=2 X and Y

	SURFACE(3, 2),      // dimensions=2 X and Y

	ISOSURFACE(3, 3),   // dimensions=2 X, Y, Z

	XY(1, 1),           // dimensions=1 X

	XY_STACKED(1, 2),   // dimensions=2 X and Many Y

	XY_STACKED_3D(3, 2),// dimensions=2 X and Many Y

	XY_SCATTER_3D(3, 1),// dimensions=1 X and Many Y

	MULTI_IMAGE(4, 2);  // dimensions=2 X and Many Y
	


	private final int rank;
	private final int dimensions;

	/**
	 * 
	 * @param rank
	 * @param dimensions
	 */
	private PlotType(int rank, int dimensions) {
	   	this.rank       = rank;
	   	this.dimensions = dimensions;
	}
	
	public static PlotType forSliceIndex(int type) {
		switch(type) {
		case 0:
			return IMAGE;
		case 1:
			return SURFACE;
		}
		return null;
	}

	public boolean is1D() {
		return rank==1;
	}
	public boolean is2D() {
		return rank==2;
	}
	public boolean is3D() {
		return rank==3 || isMulti2D();
	}
	public boolean is1Dor2D() {
		return is1D()||is2D();
	}
	public boolean isStacked3D() {
		return rank == 3 && dimensions == 2;
	}
	public boolean isScatter3D() {
		return rank == 3 && dimensions == 1;
	}
	public boolean isMulti2D() {
		return rank == 4 && dimensions == 2;
	}
	
	/**
	 * This method is called by reflection to determine the 
	 * number of non-slice dimensions in the ISliceSystem.
	 * @return
	 */
	public int getDimensions() {
		return dimensions;
	}
}
