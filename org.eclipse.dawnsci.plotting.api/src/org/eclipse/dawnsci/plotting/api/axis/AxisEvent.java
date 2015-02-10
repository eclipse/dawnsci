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

public class AxisEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3528839612994566339L;
	private double oldLower;
	private double oldUpper;
	private double newLower;
	private double newUpper;

	
	public AxisEvent(IAxis axis) {
		super(axis);
	}

	public AxisEvent(IAxis axis, double oldLower, double oldUpper,
			                     double newLower, double newUpper) {
		super(axis);
		this.oldLower = oldLower;
		this.oldUpper = oldUpper;
		this.newLower = newLower;
		this.newUpper = newUpper;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(newLower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(newUpper);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(oldLower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(oldUpper);
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
		AxisEvent other = (AxisEvent) obj;
		if (Double.doubleToLongBits(newLower) != Double
				.doubleToLongBits(other.newLower))
			return false;
		if (Double.doubleToLongBits(newUpper) != Double
				.doubleToLongBits(other.newUpper))
			return false;
		if (Double.doubleToLongBits(oldLower) != Double
				.doubleToLongBits(other.oldLower))
			return false;
		if (Double.doubleToLongBits(oldUpper) != Double
				.doubleToLongBits(other.oldUpper))
			return false;
		return true;
	}

	public double getOldLower() {
		return oldLower;
	}

	public void setOldLower(double oldLower) {
		this.oldLower = oldLower;
	}

	public double getOldUpper() {
		return oldUpper;
	}

	public void setOldUpper(double oldUpper) {
		this.oldUpper = oldUpper;
	}

	public double getNewLower() {
		return newLower;
	}

	public void setNewLower(double newLower) {
		this.newLower = newLower;
	}

	public double getNewUpper() {
		return newUpper;
	}

	public void setNewUpper(double newUpper) {
		this.newUpper = newUpper;
	}

	public IAxis getAxis() {
		return (IAxis)getSource();
	}
	
	
}
