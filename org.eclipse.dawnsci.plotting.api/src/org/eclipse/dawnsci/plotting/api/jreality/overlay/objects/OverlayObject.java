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

import java.awt.Color;

import org.eclipse.dawnsci.plotting.api.jreality.overlay.OverlayProvider;
import org.eclipse.dawnsci.plotting.api.jreality.overlay.VectorOverlayStyles;

/**
 *
 */
public class OverlayObject {

	protected int primID = -1;
	protected OverlayProvider provider;
	
	public void draw() {
		// Leave this to sub classes to get this right
	}
	
	public OverlayObject(int primID, OverlayProvider provider) 
	{
		this.provider = provider;
		this.primID = primID;
	}
	
	public void setPrimID(int primID) 
	{
		this.primID = primID;
	}
	
	public int getPrimID() 
	{
		return primID;
	}
	
	public void translate(double tx, double ty) 
	{
		if (provider != null)
			provider.translatePrimitive(primID, tx, ty);
	}
	
	public void rotate(double angle, double rcx, double rcy) 
	{
		if (provider != null)
			provider.rotatePrimitive(primID, angle, rcx, rcy);
	}
	
	public void setOutlineColour(Color colour) 
	{
		if (provider != null)
			provider.setOutlineColour(primID, colour);
	}
	
	public void setColour(Color colour) 
	{
		if (provider != null)
			provider.setColour(primID,colour);
	}
	
	public void setTransparency(double transparency) 
	{
		if (provider != null)
			provider.setTransparency(primID, transparency);
	}
	
	public void setStyle(VectorOverlayStyles newStyle) 
	{
		if (provider != null)
			provider.setStyle(primID, newStyle);
	}
	
	public void setAnchorPoints(double x, double y) 
	{
		if (provider != null)
			provider.setAnchorPoints(primID, x, y);
	}
	
	public void setVisible(boolean visible) {
		if (provider != null)
			provider.setPrimitiveVisible(primID, visible);
	}
	
	public void dispose() {
		if (provider != null)
			provider.unregisterPrimitive(primID);
		primID = -1;
	}
	
	@Override
	public void finalize() {
		if (provider != null && primID != -1) {
			provider.unregisterPrimitive(primID);
		}
	}
}
