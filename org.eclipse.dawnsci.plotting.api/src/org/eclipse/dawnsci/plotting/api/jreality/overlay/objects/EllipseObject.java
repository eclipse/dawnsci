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
public class EllipseObject extends OverlayObject {

	private double cx, cy;
	private double a = 1.0;
	private double b = 1.0;
	private double omega = 0.0;
	
	public EllipseObject(int primID, OverlayProvider provider) {
		super(primID, provider);
	}

	public void setEllipsePoint(double cx, double cy) {
		this.cx = cx;
		this.cy = cy;
	}
	
	public void setEllipseParams(double a, double b, double omega) {
		this.a = a;
		this.b = b;
		this.omega = omega;
	}
	
	@Override
	public void draw() {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawEllipse(primID, cx, cy, a, b, omega);
	}		
}
