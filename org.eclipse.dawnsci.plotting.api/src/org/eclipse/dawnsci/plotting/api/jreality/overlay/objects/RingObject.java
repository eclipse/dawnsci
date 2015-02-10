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
 * This is pretty useless but Alun wanted it! Gwyndaf's comment on this was:
 * "What the hell is this?"
 */
public class RingObject extends OverlayObject {

	private double rx,ry;
	private double inRadius = 1.0, outRadius = 2.0;
	
	public RingObject(int primID, OverlayProvider provider) {
		super(primID, provider);
	}

	public void setRingPosition(double rx, double ry) {
		this.rx = rx;
		this.ry = ry;
	}
	
	public void setRingRadii(double innerRadius, double outerRadius) {
		this.inRadius = innerRadius;
		this.outRadius = outerRadius;
	}
	
	@Override
	public void draw() {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawRing(primID, rx, ry, inRadius, outRadius);
	}	
	
}
