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
package org.eclipse.dawnsci.plotting.api.annotation;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public interface IAnnotation {

	
	public enum LineStyle {
		NONE(0, "None"),
		UP_DOWN(1, "Up&Down"),
		LEFT_RIGHT(2, "Left&Right"),
		FOUR_DIRECTIONS(3, "Four Directions");		
		
		private LineStyle(int index, String description) {
			 this.index = index;
			 this.description = description;
		}
		private int index;
		private String description;
		
		@Override
		public String toString() {
			return description;
		}
		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}
		public String getDescription() {
			return description;
		}
	}

	public String getName();
	
	public void setName(String name);
	
	public boolean isShowName();
	/**
	 * @param showName the showName to set
	 */
	public void setShowName(boolean showName);
	
	public boolean isShowInfo();
	/**
	 * @param setInfo the setInfo to set
	 */
	public void setShowInfo(boolean setInfo);
	
	public boolean isShowPosition();
	/**
	 * @param showPosition the showPosition to set
	 */
	public void setShowPosition(boolean showPosition);
	
	public Color getAnnotationColor();
	/**
	 * @param annotationColor the annotationColor to set
	 */
	public void setAnnotationColor(Color annotationColor);
	

	public Font getAnnotationFont();
	/**
	 * @param annotationFont the annotationFont to set
	 */
	public void setAnnotationFont(Font annotationFont);

	public LineStyle getLineStyle();
	/**
	 * @param cursorLineStyle the cursorLineStyle to set
	 */
	public void setLineStyle(LineStyle cursorLineStyle);
	
	
	/**
	 * Call to set location in x and y
	 */
	public void setLocation(double x, double y);
	
	/**
	 * visibility
	 * @return
	 */
	public boolean isVisible();
	
	/**
	 * visibility
	 * @return
	 */
	public void setVisible(boolean isVis);

}
