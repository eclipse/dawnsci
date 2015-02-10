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

package org.eclipse.dawnsci.plotting.api.jreality.overlay;

/**
 * Overlay image object
 */
public class OverlayImage {

	private byte[] data;
	private int width;
	private int height;
	private boolean isDirty;
	
	public OverlayImage(int width, int height) {
		data = new byte[width*height*4];
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public byte[] getImageData() {
		return data;
	}
	
	public void zap() {
		data = new byte[width*height*4];
		isDirty = true;
	}
	
	public void clear(short red, short green, short blue, short alpha) {
		for (int y = 0; y < height; y++)
			for (int x = 0; x <width; x++) {
				data[(x+y*width)*4] = (byte)red;
				data[(x+y*width)*4+1] = (byte)green;
				data[(x+y*width)*4+2] = (byte)blue;
				data[(x+y*width)*4+3] = (byte)alpha;
			}
		isDirty = true;
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void putPixel(int x, int y,
			             short red, 
			             short green, 
			             short blue, 
			             short alpha) {
		
		if (x >= 0 && x < width &&
			y >= 0 && y < height) {
			data[(x+y*width)*4] = (byte)red;
			data[(x+y*width)*4+1] = (byte)green;
			data[(x+y*width)*4+2] = (byte)blue;
			data[(x+y*width)*4+3] = (byte)alpha;
		}
		isDirty = true;
	}
	
	public void clean() {
		isDirty = false;
	}

	public int[] getShape() {
		return new int[] { height, width };
	}
}
