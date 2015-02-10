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
import org.eclipse.swt.graphics.Image;

public class ImageObject extends OverlayObject {

	private Image image;
	private double lux, luy, rlx, rly;
	
	public ImageObject(int primID, OverlayProvider provider) {
		super(primID, provider);
	}

	public void setUpperLeftPoint(double lux, double luy) {
		this.lux = lux;
		this.luy = luy;
	}
	
	public void setBottomRightPoint(double rlx, double rly) {
		this.rlx = rlx;
		this.rly = rly;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	@Override
	public void draw() {
		if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawImage(primID, image, lux, luy, rlx, rly);
	}		
}