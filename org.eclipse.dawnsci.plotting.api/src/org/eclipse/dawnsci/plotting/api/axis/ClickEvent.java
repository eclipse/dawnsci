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

public class ClickEvent extends EventObject {

	@Override
	public String toString() {
		return "ClickEvent [xAxis=" + xAxis + ", yAxis=" + yAxis + ", xValue="
				+ xValue + ", yValue=" + yValue + ", shiftDown=" + shiftDown
				+ ", controlDown=" + controlDown + ", keyCode=" + keyCode
				+ ", stateMask=" + stateMask + ", character=" + character + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + character;
		result = prime * result + (controlDown ? 1231 : 1237);
		result = prime * result + keyCode;
		result = prime * result + (shiftDown ? 1231 : 1237);
		result = prime * result + stateMask;
		result = prime * result + ((xAxis == null) ? 0 : xAxis.hashCode());
		long temp;
		temp = Double.doubleToLongBits(xValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((yAxis == null) ? 0 : yAxis.hashCode());
		temp = Double.doubleToLongBits(yValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ClickEvent other = (ClickEvent) obj;
		if (character != other.character)
			return false;
		if (controlDown != other.controlDown)
			return false;
		if (keyCode != other.keyCode)
			return false;
		if (shiftDown != other.shiftDown)
			return false;
		if (stateMask != other.stateMask)
			return false;
		if (xAxis == null) {
			if (other.xAxis != null)
				return false;
		} else if (!xAxis.equals(other.xAxis))
			return false;
		if (Double.doubleToLongBits(xValue) != Double
				.doubleToLongBits(other.xValue))
			return false;
		if (yAxis == null) {
			if (other.yAxis != null)
				return false;
		} else if (!yAxis.equals(other.yAxis))
			return false;
		if (Double.doubleToLongBits(yValue) != Double
				.doubleToLongBits(other.yValue))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6102271125408437641L;

	private final IAxis   xAxis;
	private final IAxis   yAxis;
	private final double  xValue;
	private final double  yValue;
	private final boolean shiftDown;
	private final boolean controlDown;
	private final int     keyCode;
	private final int     stateMask;
	private final char    character;
	
	public ClickEvent(Object source, 
			           IAxis xAxis, 
			           IAxis yAxis,
                       double xValue, 
                       double yValue, 
                       boolean shiftDown, 
                       boolean controlDown, 
                       int keyCode, 
                       int stateMask, 
                       char character) {
		
		super(source);
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.xValue = xValue;
		this.yValue = yValue;
		this.shiftDown = shiftDown;
		this.controlDown = controlDown;
		this.keyCode = keyCode;
		this.stateMask = stateMask;
		this.character = character;
	}

	public IAxis getxAxis() {
		return xAxis;
	}

	public IAxis getyAxis() {
		return yAxis;
	}

	public double getxValue() {
		return xValue;
	}

	public double getyValue() {
		return yValue;
	}

	public boolean isShiftDown() {
		return shiftDown;
	}

	public boolean isControlDown() {
		return controlDown;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getStateMask() {
		return stateMask;
	}

	public char getCharacter() {
		return character;
	}

}
