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
public class CircleObject extends OverlayObject {

	private double cx,cy,radius = 1.0;
	
	public CircleObject(int primID, OverlayProvider provider) {
		super(primID, provider);
	}

	public void setCirclePoint(double cx, double cy) 
	{
		this.cx = cx;
		this.cy = cy;
	}
	
	public void setRadius(double rad) 
	{
		this.radius = rad;
	}
	
	@Override
	public void draw() {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawCircle(primID, cx, cy, radius);
	}		
	
}
