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
package org.eclipse.dawnsci.plotting.api.axis;

import java.util.EventObject;


/**
 * Event fired for notifying position of the mouse
 * in data coordinates.
 * 
 * @author Matthew Gerring
 *
 */
public class PositionEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4667006704544719932L;
	
	private final IAxis xAxis, yAxis;
	private final int xPos,yPos;
	
	/**
	 * The data value in the primary axis,
	 * other axes may be used by using the 
	 * xPos and the getPositionValue(..) method
	 * of the other axis.
	 */
	public final double x;
	
	/**
	 * The data value in the primary axis,
	 * other axes may be used by using the 
	 * xPos and the getPositionValue(..) method
	 * of the other axis.
	 */
	public final double y;

	public PositionEvent(Object source, IAxis xAxis, IAxis yAxis, int xpos, int ypos) {
		super(source);
		this.xPos  = xpos;
		this.yPos  = ypos;
		this.x     = xAxis.getPositionValue(xpos);
		this.y     = yAxis.getPositionValue(ypos);
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((xAxis == null) ? 0 : xAxis.hashCode());
		result = prime * result + xPos;
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((yAxis == null) ? 0 : yAxis.hashCode());
		result = prime * result + yPos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionEvent other = (PositionEvent) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (xAxis == null) {
			if (other.xAxis != null)
				return false;
		} else if (!xAxis.equals(other.xAxis))
			return false;
		if (xPos != other.xPos)
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (yAxis == null) {
			if (other.yAxis != null)
				return false;
		} else if (!yAxis.equals(other.yAxis))
			return false;
		if (yPos != other.yPos)
			return false;
		return true;
	}

	public IAxis getxAxis() {
		return xAxis;
	}

	public IAxis getyAxis() {
		return yAxis;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

}
