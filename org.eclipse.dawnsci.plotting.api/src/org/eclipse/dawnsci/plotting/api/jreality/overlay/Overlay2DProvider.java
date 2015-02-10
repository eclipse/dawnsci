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

import org.eclipse.swt.graphics.Image;

/**
 * A 2D Overlay provider
 */
public interface Overlay2DProvider extends OverlayProvider {


	/**
	 * Draw a line primitive
	 * @param primID id of the primitive
	 * @param sx start x position
	 * @param sy start y position
	 * @param ex end x position
	 * @param ey end y position
	 */
	
	public void drawLine(int primID, double sx, double sy, double ex, double ey);
	
	/**
	 * Draw a box primitive
	 * @param primID id of the primitive
	 * @param lux left upper x coordinate
	 * @param luy left upper y coordinate
	 * @param rlx right lower x coordinate
	 * @param rly right lower y coordinate
	 */
	public void drawBox(int primID, double lux, double luy, double rlx, double rly);
	
	/**
	 * Draw an arrow primitive
	 * @param primID id of the primitive
	 * @param sx start x position
	 * @param sy start y position
	 * @param ex end x position
	 * @param ey end y position
	 */
	
	public void drawArrow(int primID, double sx, double sy, double ex, double ey);

	/**
	 * Draw an arrow primitive
	 * @param primID id of the primitive
	 * @param sx start x position
	 * @param sy start y position
	 * @param ex end x position
	 * @param ey end y position
	 * @param arrowPos relative position of the arrow on the line (0.0 = startPoint, 1.0 = endPoint)
	 */
	
	public void drawArrow(int primID, double sx, double sy, double ex, double ey, double arrowPos);
	
	/**
	 * Draw a circle primitive
	 * @param primID id of the primitive
	 * @param cx x position of the centre of the circle
	 * @param cy y position of the centre of the circle
	 * @param radius of the circle
	 */
	public void drawCircle(int primID, double cx, double cy, double radius);
	
	/**
	 * Draw a triangle primitive
	 * @param primID id of the primitive
	 * @param x1 x coordinate of the first point
	 * @param y1 y coordinate of the first point
	 * @param x2 x coordinate of the second point
	 * @param y2 y coordinate of the second point
	 * @param x3 x coordinate of the third point
	 * @param y3 y coordinate of the third point
	 */
	public void drawTriangle(int primID, double x1, double y1, double x2, double y2, double x3, double y3);
	
	/**
	 * Draw a circle sector primitive
	 * @param primID id of the primitive
	 * @param cx x coordinate of the centre point
	 * @param cy y coordinate of the centre point
	 * @param inRadius inner radius size
	 * @param outRadius outer radius size
	 * @param startAngle starting angle in degrees
	 * @param endAngle end angle in degrees
	 */
	public void drawSector(int primID, double cx, double cy, double inRadius, double outRadius,
			               double startAngle, double endAngle);

	
	/**
	 * Draw a label primitive 
	 * @param primID id of the primitive
	 * @param lx x coordinate of the label
	 * @param ly y coordinate of the label
	 */
	
	public void drawLabel(int primID, double lx, double ly);
	
	/**
	 * Draw a point primitive 
	 * @param primID id of the primitive
	 * @param px x coordinate of the point
	 * @param py y coordinate of the point
	 */	
	
	public void drawPoint(int primID, double px, double py);

	/**
	 * Set points in a point or pointlist primitive to thick or not
	 * @param primID id of the primitive
	 * @param on set the points to be thick true otherwise false
	 */	
	
	public void setThickPoints(int primID, boolean on);
	

	/**
	 * Draw a point list primitive 
	 * @param primID id of the primitive
	 * @param px array of x coordinate of the points
	 * @param py array of y coordinate of the points (# elements should match px)
	 */		
	public void drawPoints(int primID, double[] px, double[] py);

	/**
	 * Draw a Ring primitive 
	 * @param primID id of the primitive
	 * @param cx x coordinate of the centre point
	 * @param cy y coordinate of the centre point
	 * @param inRadius inner radius size
	 * @param outRadius outer radius size
	 */		
	
	public void drawRing(int primID, double cx, double cy, double inRadius, double outRadius);
	
	/**
	 * Draw a general ellipse primitive 
	 * @param primID id of the primitive
	 * @param cx x coordinate of the centre point
	 * @param cy y coordinate of the centre point
	 * @param a radius a
	 * @param b radius b
	 * @param omega off-axis rotation in radiant
	 */		
	
	public void drawEllipse(int primID, double cx, double cy, double a, double b, double omega);
	
	/**
	 * Draw an image
	 * @param imageID id of the image
	 * @param lux left upper x coordinate
	 * @param luy left upper y coordinate
	 * @param rlx right lower x coordinate
	 * @param rly right lower y coordinate
	 */
	public void drawImage(int imageID, Image image, double lux, double luy, double rlx, double rly);

	/**
	 * Whether objects will be too small to draw (< 1 pixel in either height or width)
	 * @param xSize
	 * @param ySize
	 * @return drawable
	 */
	boolean isDrawable(double xSize, double ySize);
}
