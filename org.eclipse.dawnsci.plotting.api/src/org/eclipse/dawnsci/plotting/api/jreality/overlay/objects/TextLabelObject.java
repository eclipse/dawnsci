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
import org.eclipse.dawnsci.plotting.api.jreality.overlay.enums.LabelOrientation;

/**
 *
 */
public class TextLabelObject extends OverlayObject {

	private double cx,cy;
	
	public TextLabelObject(int primID, OverlayProvider provider) {
		super(primID, provider);
	}
	
	public void setTextPosition(double cx, double cy) {
		this.cx = cx;
		this.cy = cy;
	}

	public void setTextOrientation(LabelOrientation orient) {
		if (provider != null && primID != -1)
			provider.setLabelOrientation(primID, orient);
	}
	
	public void setText(String text, int alignment) {
		if (provider != null && primID != -1)
			provider.setLabelText(primID, text, alignment);
	}
	
	public void setTextFont(java.awt.Font font) {
		if (provider != null && primID != -1)
			provider.setLabelFont(primID, font);
	}

	@Override
	public void draw() {
		if (provider instanceof Overlay1DProvider)
			((Overlay1DProvider)provider).drawLabel(primID, cx, cy);
		else if (provider instanceof Overlay2DProvider)
			((Overlay2DProvider)provider).drawLabel(primID, cx, cy);
	}		
}

