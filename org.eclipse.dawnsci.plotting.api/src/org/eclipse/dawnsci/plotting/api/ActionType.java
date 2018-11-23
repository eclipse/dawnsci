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

public enum ActionType {

	XY, IMAGE, XYANDIMAGE, THREED, SURFACE, SCATTER3D, MULTIIMAGE, ALL, FX3D, DATA, JZY3D_COLOR;

	public boolean isCompatible(PlotType type) {

		if (this == ALL)
			return true;
		if (type == PlotType.DATA && this == ActionType.DATA)
			return true;
		else if (type == PlotType.DATA)
			return false;
		
		if (type == PlotType.JZY3D_COLOR && this == ActionType.JZY3D_COLOR) {
			return true;
		} else if (type == PlotType.JZY3D_COLOR){
			return false;
		}
		
		if (type.is1D() && this == XY) {
			return true;
		}
		if (type.is2D() && this == IMAGE) {
			return true;
		}
		if ((type.is2D() || type.is1D()) && this == XYANDIMAGE) {
			return true;
		}
		if (type.equals(PlotType.SURFACE) && this == SURFACE) {
			return true;
		}
		if (type.equals(PlotType.XY_SCATTER_3D) && this == SCATTER3D) {
			return true;
		}
		if (type.is3D() && this == THREED && type != PlotType.ISOSURFACE && type != PlotType.VOLUME) {
			return true;
		}
		if ((type==PlotType.ISOSURFACE || type==PlotType.VOLUME) && this == FX3D) {
			return true;
		}
		if (type.isMulti2D() && this == MULTIIMAGE) {
			return true;
		}
		
			
		return false;
	}
}
