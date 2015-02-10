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

package org.eclipse.dawnsci.plotting.api.jreality.overlay.objects;

import org.eclipse.dawnsci.plotting.api.jreality.overlay.Overlay2DProvider;
import org.eclipse.dawnsci.plotting.api.jreality.overlay.OverlayProvider;

/**
 *
 */
public class PointListObject extends OverlayObject {

	private double px[];
	private double py[];
	
	public PointListObject(int primID, OverlayProvider provider) {
		super(primID, provider);
	}
	
	public void setPointPositions(double px[], double py[]) {
		this.px = px;
		this.py = py;
	}

	public void setThick(boolean isThick) {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).setThickPoints(primID, isThick);		
	}
	
	@Override
	public void draw() {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawPoints(primID, px, py);
	}			
}
