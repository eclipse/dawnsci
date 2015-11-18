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
package org.eclipse.dawnsci.plotting.api.region;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.axis.ICoordinateSystem;
import org.eclipse.swt.graphics.Color;

/**
 * A selection region must conform to this interface. You can set its position, colour and transparency settings.
 * 
 * @author Matthew Gerring
 */
public interface IRegion {
	
	/**
	 * 
	 */
	public void repaint();

	/**
	 * @return the name of the region
	 */
	public String getName();

	/**
	 * The name of the region. Warning: as region names must be unique and are coordinated in a plotting system,
	 * you probably want to use {@link IPlottingSystem<Composite>#renameRegion(IRegion, String)}
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return the label of the region
	 */
	public String getLabel();

	/**
	 * The label of the region
	 * 
	 * @param label
	 */
	public void setLabel(String label);

	/**
	 * @return the plot type in which the region is created
	 */
	public PlotType getPlotType();

	/**
	 * The plot type in which the region is created
	 * @param type
	 */
	public void setPlotType(PlotType type);

	/**
	 * @return the colour of the region
	 */
	public Color getRegionColor();

	/**
	 * The colour of the region
	 * 
	 * @param regionColor
	 */
	public void setRegionColor(Color regionColor);

	/**
	 * @return if true, position information should be shown in the region.
	 */
	public boolean isShowPosition();

	/**
	 * If position information should be shown in the region.
	 * 
	 * @param showPosition
	 */
	public void setShowPosition(boolean showPosition);

	/**
	 * Alpha transparency 0-255, 0-transparent, 255-opaque
	 * 
	 * @return
	 */
	public int getAlpha();

	/**
	 * Alpha transparency 0-255, 0-transparent, 255-opaque
	 * 
	 * @param alpha
	 */
	public void setAlpha(int alpha);

	/**
	 * @return true if visible
	 */
	public boolean isVisible();

	/**
	 * Visibility
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible);

	/**
	 * @return true if filled
	 */
	public boolean isFill();

	/**
	 * Fill body
	 * 
	 * @param fill
	 */
	public void setFill(boolean fill);

	/**
	 * @return true if moveable
	 */
	public boolean isMobile();

	/**
	 * Moveable or not
	 * 
	 * @param mobile
	 */
	public void setMobile(boolean mobile);

	/**
	 * Get the region of interest (in coordinate frame of the axis that region is added to)
	 */
	public IROI getROI();

	/**
	 * Set the region of interest (in coordinate frame of the axis that region is added to)
	 */
	public void setROI(IROI roi);

	/**
	 * Returns whether the region is active or not
	 */
	public boolean isActive();

	/**
	 * Set whether the region is active or not
	 * @param b
	 */
	public void setActive(boolean b);

	/**
	 * Add a listener which is notified when this region is resized or moved.
	 * 
	 * @param l
	 */
	public boolean addROIListener(final IROIListener l);

	/**
	 * Remove a ROIListener
	 * 
	 * @param l
	 */
	public boolean removeROIListener(final IROIListener l);

	/**
	 * Will be called to remove the region and clean up resources when the user
	 * calls the removeRegion(...) method.
	 */
	public void remove();

	// some missing colours from draw2d
	static final Color darkYellow  = new Color(null, 128, 128, 0);
	static final Color darkMagenta = new Color(null, 128, 0, 128);
	static final Color darkCyan    = new Color(null, 0, 128, 128);

	/**
	 * Class packages types of regions, their default names, colours and indices.
	 * @author Matthew Gerring
	 * 
	 * Used to be:
	 * 
	 * 		
	 *
	 */
	public enum RegionType {
		POINT("Point",             darkMagenta),
		LINE("Line",               ColorConstants.cyan),
		BOX("Box",                 ColorConstants.green),
		POLYLINE("Polyline",       ColorConstants.cyan),
		POLYGON("Polygon",         ColorConstants.cyan),
		PERIMETERBOX("Perimeter box", ColorConstants.gray),
		GRID("Grid",               ColorConstants.lightGray),
		CIRCLE("Circle",           darkYellow),
		CIRCLEFIT("Circle fit",    darkYellow),
		SECTOR("Sector",           ColorConstants.red),
		ELLIPSE("Ellipse",         ColorConstants.lightGreen),
		ELLIPSEFIT("Ellipse fit",  ColorConstants.lightGreen),
		PARABOLA("Parabola",       ColorConstants.orange),
		HYPERBOLA("Hyperbola",     ColorConstants.lightBlue),
		RING("Ring",               darkYellow),
		XAXIS("X-Axis",            ColorConstants.blue),
		YAXIS("Y-Axis",            ColorConstants.blue),
		XAXIS_LINE("X-Axis Line",  ColorConstants.blue),
		YAXIS_LINE("Y-Axis Line",  ColorConstants.blue),
		FREE_DRAW("Free draw",     darkYellow);

		private String name;
		private Color defaultColor;
		
		public static final List<RegionType> ALL_TYPES;
		static {
			List<RegionType> tmp = new ArrayList<RegionType>(12);
			for (RegionType t : EnumSet.allOf(RegionType.class)) tmp.add(t);
			ALL_TYPES = Collections.unmodifiableList(tmp);
		}

		RegionType(String name, Color defaultColor) {
			this.name = name;
			this.defaultColor = defaultColor;
		}

		public int getIndex() {
			return ALL_TYPES.indexOf(this);
		}

		public String getName() {
			return name;
		}

		public Color getDefaultColor() {
			return defaultColor;
		}

		public static RegionType getRegion(int index) {
			return ALL_TYPES.get(index);
		}

		public String getId() {
			return IRegion.class.getPackage().getName()+"."+toString();
		}

	}

	/**
	 * return the line width used for drawing any lines (if any are drawn, otherwise 0).
	 * @return
	 */
	public int getLineWidth();
	
	/**
	 * set the line width used for drawing any lines (if any are drawn, otherwise does nothing).
	 * @return
	 */
	public void setLineWidth(int i);

	/**
	 * The type of this region
	 * @return
	 */
	public RegionType getRegionType();
	
	/**
	 * return true if the mouse should be tracked. The region will mouse with this tracking.
	 * WARNING Most regions will not respond to this setting.
	 * 
	 * @return
	 */
	public boolean isTrackMouse();
	
	/**
	 * return true if the mouse should be tracked.
	 * WARNING Most regions will not respond to this setting. AxisSelection does.
     *
	 * @return
	 */
	public void setTrackMouse(boolean trackMouse);

	/**
	 * 
	 * @return true if user region. If not a user region the region has been created programmatically
	 * and has been marked as not editable to the user.
	 */
	public boolean isUserRegion();

	/**
	 *  If not a user region the region has been created programmatically
	 * and has been marked as not editable to the user.
	 * @param userRegion
	 */
	public void setUserRegion(boolean userRegion);
	
	/**
	 * Add Mouse listener to the region if it supports it and if it is a draw2d region.
	 */
	public void addMouseListener(MouseListener l);
	
	
	/**
	 * Remove Mouse listener to the region if it supports it and if it is a draw2d region.
	 */
	public void removeMouseListener(MouseListener l);

	
	/**
	 * Add Mouse motion listener to the region if it supports it and if it is a draw2d region.
	 */
	public void addMouseMotionListener(MouseMotionListener l);
	
	/**
	 * Remove Mouse motion listener to the region if it supports it and if it is a draw2d region.
	 */
	public void removeMouseMotionListener(MouseMotionListener l);
	
	/**
	 * This method will send the figure back to the start of its
	 * parents child list. This results in it being underneath the other children.
	 */
	public void toBack();

	
	/**
	 * This method will send the figure to the end of its
	 * parents child list. This results in it being above the other children.
	 */
	public void toFront();

	/**
	 *
	 * @return true if the region should be used in a mask operation.
	 */
	public boolean isMaskRegion();
	
	/**
	 * Set the mask boolean. By default this will be false but when true the region
	 * is marked as being part of mask operations.
	 * 
	 * @param isMaskRegion
	 */
	public void setMaskRegion(boolean isMaskRegion);

	/**
	 * The coordinate system for the region (this allows for the image to be in 
	 * any orientation).
	 * @return
	 */
	public ICoordinateSystem getCoordinateSystem();
	
	/**
	 * 
	 * @return last object
	 */
	public Object setUserObject(Object object);
	
	/**
	 * 
	 * @return object
	 */
	public Object getUserObject();

	/**
	 * Sets wether the label should be shown or not.
	 * @param b
	 */
	public void setShowLabel(boolean b);
	
	/**
	 * 
	 * @return true if the selection region only draws an outline.
	 */
	public boolean isOutlineOnly();
	
	/**
	 * Set if the region should draw in outline only mode. If
	 * outline only is not available for this selection region, this
	 * method will throw a RuntimeException.
	 */
	public void setOutlineOnly(boolean outlineOnly);

	/**
	 * 
	 * @return true if Region update is from server
	 */
	public boolean fromServer();

	/**
	 * Set if the Region is being updated from server or not
	 * @param isFromServer
	 *         set to false by default
	 */
	public void setFromServer(boolean isFromServer);

	/**
	 * Snaps the region to Grid (Pixel values)
	 */
	public void snapToGrid();
}
