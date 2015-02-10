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

package org.eclipse.dawnsci.plotting.api.jreality.impl;


import java.io.Serializable;

import org.eclipse.dawnsci.plotting.api.jreality.legend.TableCanvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

/**
 * Plot1DAppearance encapsulates all the different appearance on of a 1D Plot
 */
public class Plot1DAppearance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.awt.Color plotColour;
	private Plot1DStyles plotStyle;
	private int lineWidth;
	private String plotName;
	private boolean visible;
	
	/**
	 * Generate a new Plot1DAppearance
	 * @param colour of the plot
	 * @param style of the plot
	 * @param lineWidth of the plot
	 * @param name of the plot
	 * @param isVisible is the plot visible?
	 */
	public Plot1DAppearance(java.awt.Color colour,
							Plot1DStyles style,
							int lineWidth,
							String name,
							boolean isVisible)
	{
		this.plotColour = colour;
		this.plotStyle = style;
		this.lineWidth = lineWidth;
		this.plotName = name;
		this.visible = isVisible;
	}
	
	/**
	 * Generate a new Plot1DAppearance
	 * @param colour of the plot
	 * @param style of the plot
	 * @param lineWidth of the plot
	 * @param name of the plot
	 */

	public Plot1DAppearance(java.awt.Color colour,
							Plot1DStyles style,
							int lineWidth,
							String name)
	{
		this(colour,style,lineWidth,name,true);
	}
	
	/**
	 * Generate a new Plot1DAppearance
	 * @param colour of the plot
	 * @param style of the plot
	 * @param name of the plot
	 */
	public Plot1DAppearance(java.awt.Color colour,
							Plot1DStyles style,
							String name)
	{
		this(colour,style,2,name,true);
	}
	
	/**
	 * Generate a new Plot1DAppearance
	 * @param colour of the plot
	 * @param name of the plot
	 */
	
	public Plot1DAppearance(java.awt.Color colour, String name)
	{
		this(colour,Plot1DStyles.SOLID,1,name,true);
	}
	
	/**
	 * Get the current colour of the plot
	 * @return the colour of the plot
	 */
	public java.awt.Color getColour()
	{
		return plotColour;
	}
	
	/**
	 * Set the colour of the plot
	 * @param colour new colour of the plot
	 */
	public void setColour(java.awt.Color colour)
	{
		this.plotColour = colour;
	}
	
	/**
	 * Get the style of the plot
	 * @return the style of the plot
	 */
	public Plot1DStyles getStyle()
	{
		return plotStyle;
	}
	
	/**
	 * Set the style of the plot
	 * @param newStyle the new style of the plot
	 */
	public void setStyle(Plot1DStyles newStyle)
	{
		this.plotStyle = newStyle;
	}
	
	/**
	 * Get the line width of the plot
	 * @return the line width of the plot
	 */
	public int getLineWidth()
	{
		return lineWidth;
	}
	
	/**
	 * Get the name of the plot
	 * @return the name of the plot
	 */
	public String getName() {
		return plotName;
	}
	
	/**
	 * Set the name of the plot
	 * @param newName the name of the plot
	 */
	
	public void setName(String newName) {
		this.plotName = newName;
	}
	
	/**
	 * Set the line width of the plot
	 * @param newWidth the new line width of the plot
	 */
	public void setLineWidth(int newWidth)
	{
		lineWidth = newWidth;
	}
	
	/**
	 * Is the plot visible
	 * @return if the plot is visible (true) or not (false)
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Set the plot to being visible
	 * @param newVisible new visibility status
	 */
	public void setVisible(boolean newVisible) {
		visible = newVisible;
	}
	
	
	
	/**
	 * Update the SWT canvas according to the appearance properties
	 * @param canvas SWT canvas that should be updated
	 */
	public void updateCanvas(TableCanvas canvas)
	{
		org.eclipse.swt.graphics.RGB rgb = new org.eclipse.swt.graphics.RGB(plotColour.getRed(),
																		plotColour.getGreen(),
																		plotColour.getBlue());
		org.eclipse.swt.graphics.Color color = new
			org.eclipse.swt.graphics.Color(canvas.getDisplay(),rgb);
		canvas.setStyle(plotStyle);
		canvas.setColour(color);
	}
	
	public void drawApp(int xpos, int ypos, GC gc, Display display, boolean rotated) 
	{
		org.eclipse.swt.graphics.RGB rgb = new org.eclipse.swt.graphics.RGB(plotColour.getRed(),
				plotColour.getGreen(),
				plotColour.getBlue());
		org.eclipse.swt.graphics.Color color = new
			org.eclipse.swt.graphics.Color(display,rgb);
		gc.setBackground(color);
	    switch (plotStyle)
		{
			case SOLID:
			case SOLID_POINT:
				if (rotated)
					gc.fillRectangle(xpos,ypos, 2,30);
				else
					gc.fillRectangle(xpos,ypos,30,2);
			break;
			case DASHED:
			case DASHED_POINT:
			{
				int dashSize = 30 >> 3;
				for (int i = 0; i < 4; i++)
				{
					if (rotated) 
						gc.fillRectangle(xpos,ypos+i*2*dashSize,2,dashSize);
					else
						gc.fillRectangle(xpos+i*2*dashSize,ypos,dashSize,2);						
				}
			}
			break;
			case POINT:
				int dashSize = 30 >> 3;
				for (int i = 0; i < 4; i++)
				{
					if (rotated)
						gc.fillOval(xpos,ypos+i*2*dashSize,(dashSize >> 1),(dashSize >> 1));
					else
						gc.fillOval(xpos+i*2*dashSize,ypos,(dashSize >> 1),(dashSize >> 1));						
				}
			break;
		}
	    if (rotated) {
	  	    gc.setAdvanced(true);
			color.dispose();
		    Transform t = new Transform(display);
		    t.rotate(90);
		    t.translate(ypos+35, -xpos-10);	    
		    gc.setTransform(t);
			gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.drawText(plotName.substring(0,Math.min(15,plotName.length())), 0,0);
		    t.translate(-ypos-35, xpos+10);	
			t.rotate(-90);
		    gc.setTransform(t);
			t.dispose();
	    } else {
			gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.drawText(plotName.substring(0,Math.min(15,plotName.length())), xpos+35,ypos-10);	    	
	    }
	}
	
	public void drawApp(int xpos, int ypos, GC gc, Display display, boolean rotated, int scaleFactor) 
	{
		org.eclipse.swt.graphics.RGB rgb = new org.eclipse.swt.graphics.RGB(plotColour.getRed(),
				plotColour.getGreen(),
				plotColour.getBlue());
		org.eclipse.swt.graphics.Color color = new
			org.eclipse.swt.graphics.Color(display,rgb);
		gc.setBackground(color);
	    switch (plotStyle)
		{
			case SOLID:
			case SOLID_POINT:
				if (rotated)
					gc.fillRectangle(xpos,ypos, 2*scaleFactor,30*scaleFactor);
				else
					gc.fillRectangle(xpos,ypos,30*scaleFactor,2*scaleFactor);
				break;
			case DASHED:
			case DASHED_POINT:
			{
				int dashSize = 30 >> 3;
				for (int i = 0; i < 4; i++)
				{
					if (rotated)
						gc.fillRectangle(xpos,ypos+i*2*dashSize,2*scaleFactor,dashSize*scaleFactor);
					else
						gc.fillRectangle(xpos+i*2*dashSize,ypos,dashSize*scaleFactor,2*scaleFactor);
				}
			}
			break;
			case POINT:
				int dashSize = 30 >> 3;
				for (int i = 0; i < 4; i++)
				{
					if (rotated)
						gc.fillOval(xpos,ypos+i*2*dashSize,(dashSize >> 1)*scaleFactor,(dashSize >> 1)*scaleFactor);
					else
						gc.fillOval(xpos+i*2*dashSize,ypos,(dashSize >> 1)*scaleFactor,(dashSize >> 1)*scaleFactor);
				}
			break;
		}
	    if (rotated) {
	  	    gc.setAdvanced(true);
			color.dispose();
		    Transform t = new Transform(display);
		    t.rotate(90);
		    t.translate(ypos+35, -xpos-75);	    
		    t.scale(scaleFactor, scaleFactor);
		    gc.setTransform(t);
			gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.drawText(plotName.substring(0,Math.min(15,plotName.length())), 0,0);
		    t.translate(-ypos-35, xpos+10);	
			t.rotate(-90);
		    gc.setTransform(t);
			t.dispose();
	    } else {
	    	gc.setAdvanced(true);
			color.dispose();
		    Transform t = new Transform(display);
		    t.translate(xpos+75,ypos-25);	    
		    t.scale(scaleFactor, scaleFactor);
		    gc.setTransform(t);
			gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.drawText(plotName.substring(0,Math.min(15,plotName.length())), 0,0);
		    gc.setTransform(t);
			t.dispose();
	    }
	}
}
