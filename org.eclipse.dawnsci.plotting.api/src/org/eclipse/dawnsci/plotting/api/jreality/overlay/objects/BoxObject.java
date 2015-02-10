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

import org.eclipse.dawnsci.plotting.api.jreality.overlay.Overlay1DProvider;
import org.eclipse.dawnsci.plotting.api.jreality.overlay.Overlay2DProvider;
import org.eclipse.dawnsci.plotting.api.jreality.overlay.OverlayProvider;

/**
 *
 */
public class BoxObject extends OverlayObject {

	private double lux,luy,rlx,rly;
	
	public BoxObject(int primID, OverlayProvider provider) 
	{
		super(primID, provider);
	}

	public void setBoxPoints(double lux, double luy, double rlx, double rly) 
	{
		this.lux = lux;
		this.luy = luy;
		this.rlx = rlx;
		this.rly = rly;
	}
	
	public void setBoxUpperLeftPoint(double lux, double luy)  
	{
		this.lux = lux;
		this.luy = luy;
	}
	
	public void setBoxBottomRightPoint(double rlx, double rly) 
	{
		this.rlx = rlx;
		this.rly = rly;
	}
	
	public void setBoxPosition(double lux, double luy) 
	{
		this.lux = lux;
		this.luy = luy;
	}
	
	public void setBoxWidth(double width) 
	{
		this.rlx = lux + width;
	}
	
	public void setBoxHeight(double height) 
	{
		this.rly = luy + height;
	}
	
	@Override
	public void draw() {
		if (provider instanceof Overlay1DProvider) {
			((Overlay1DProvider)provider).drawBox(primID, lux, luy, rlx, rly);
		} else if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawBox(primID, lux, luy, rlx, rly);
	}	
	
}
