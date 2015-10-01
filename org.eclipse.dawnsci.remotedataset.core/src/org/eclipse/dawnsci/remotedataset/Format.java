/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset;

public enum Format {

	/**
	 * Single data shot
	 */
	DATA, 
	
	/**
	 * Stream of IDataset
	 */
	MDATA(0), 
	
	/**
	 * Single Image
	 */
	JPG, PNG, 
	
	/**
	 * Stream of images
	 */
	MJPG(0); 
		
	/**
	 * The dimension to slice over when doing MJPG streams.
	 */
	private int dimension;

	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public boolean isImage() {
		return this==JPG || this==PNG;
	}
	
	Format() {
		this(0);
	}
	Format(int dimension) {
		this.dimension=dimension;
	}
	
	public static Format getFormat(String value) {
		if (value == null) return DATA;
		
		if (value.indexOf(':')>-1) {
			String[] sa = value.split("\\:");
			Format f = valueOf(sa[0]);
			f.setDimension(Integer.parseInt(sa[1]));
			return f;
		}
		return valueOf(value);
	}

	public String getImageIOString() {
		switch(this) {
		case JPG:
		case PNG:
			return toString().toLowerCase();
		case MJPG:
			return "jpg";
		default:
			throw new RuntimeException("ImageIO not supported with format: "+this);
		}
	}
}
