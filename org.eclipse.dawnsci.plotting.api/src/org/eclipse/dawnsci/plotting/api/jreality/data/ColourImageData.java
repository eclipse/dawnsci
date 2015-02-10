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

package org.eclipse.dawnsci.plotting.api.jreality.data;

/**
 * A small image data container with the minimum set of functionality
 */
public class ColourImageData {
	
	private int imgWidth;
	private int imgHeight;
	private int[] dataContainer;
	
	public ColourImageData(final int width, final int height) {
		imgWidth = width;
		imgHeight = height;
		dataContainer = new int[width * height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) dataContainer[x+y*width] = 0;
	}

	public void set(int value, int pos) {
		if (pos < imgWidth * imgHeight && pos >= 0)
			dataContainer[pos] = value;
	}
	
	public void set(int value, int x, int y) {
		if (x >= 0 && x < imgWidth && y >=0 && y < imgHeight) {
			dataContainer[x+y*imgWidth] = value;
		}
	}
	
	public int getHeight() {
		return imgHeight;
	}
	
	public int getWidth() {
		return imgWidth;
	}
	
	public int get(int pos) {
		if (pos >= 0 && pos < imgWidth * imgHeight)
			return dataContainer[pos];
		return -1;
	}

	public int get(int x, int y) {
		return get(x+y*imgWidth);
	}
}
