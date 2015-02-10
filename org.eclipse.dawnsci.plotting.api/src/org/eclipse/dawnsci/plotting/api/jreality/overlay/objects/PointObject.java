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
public class PointObject extends OverlayObject {

	private double px, py;
	
	public PointObject(int primID, OverlayProvider provider) 
	{
		super(primID, provider);
	}

	public void setPointPosition(double px, double py) 
	{
		this.px = px;
		this.py = py;
	}
	
	@Override
	public void draw() {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawPoint(primID, px, py);
	}		
	
}
