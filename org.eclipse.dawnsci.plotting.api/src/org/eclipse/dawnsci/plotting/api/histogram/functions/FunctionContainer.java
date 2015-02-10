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
package org.eclipse.dawnsci.plotting.api.histogram.functions;

import org.eclipse.dawnsci.plotting.api.histogram.ITransferFunction;


/**
 * To be used as a function object in ImageServiceBean to override 
 * the normal 8-bit mapping and provide functions for use with SWTImageUtils
 * 
 * @author Matthew Gerring
 *
 */
public class FunctionContainer {
	
	private ITransferFunction redFunc;
	private ITransferFunction greenFunc;
	private ITransferFunction blueFunc;
	private ITransferFunction alphaFunc;
	private boolean inverseRed;
	private boolean inverseGreen;
	private boolean inverseBlue;
	private boolean inverseAlpha;

	public FunctionContainer(ITransferFunction redFunc,
			ITransferFunction greenFunc, ITransferFunction blueFunc,
			ITransferFunction alphaFunc, boolean inverseRed,
			boolean inverseGreen, boolean inverseBlue, boolean inverseAlpha) {
		super();
		this.redFunc = redFunc;
		this.greenFunc = greenFunc;
		this.blueFunc = blueFunc;
		this.alphaFunc = alphaFunc;
		this.inverseRed = inverseRed;
		this.inverseGreen = inverseGreen;
		this.inverseBlue = inverseBlue;
		this.inverseAlpha = inverseAlpha;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alphaFunc == null) ? 0 : alphaFunc.hashCode());
		result = prime * result
				+ ((blueFunc == null) ? 0 : blueFunc.hashCode());
		result = prime * result
				+ ((greenFunc == null) ? 0 : greenFunc.hashCode());
		result = prime * result + (inverseAlpha ? 1231 : 1237);
		result = prime * result + (inverseBlue ? 1231 : 1237);
		result = prime * result + (inverseGreen ? 1231 : 1237);
		result = prime * result + (inverseRed ? 1231 : 1237);
		result = prime * result + ((redFunc == null) ? 0 : redFunc.hashCode());
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
		FunctionContainer other = (FunctionContainer) obj;
		if (alphaFunc == null) {
			if (other.alphaFunc != null)
				return false;
		} else if (!alphaFunc.equals(other.alphaFunc))
			return false;
		if (blueFunc == null) {
			if (other.blueFunc != null)
				return false;
		} else if (!blueFunc.equals(other.blueFunc))
			return false;
		if (greenFunc == null) {
			if (other.greenFunc != null)
				return false;
		} else if (!greenFunc.equals(other.greenFunc))
			return false;
		if (inverseAlpha != other.inverseAlpha)
			return false;
		if (inverseBlue != other.inverseBlue)
			return false;
		if (inverseGreen != other.inverseGreen)
			return false;
		if (inverseRed != other.inverseRed)
			return false;
		if (redFunc == null) {
			if (other.redFunc != null)
				return false;
		} else if (!redFunc.equals(other.redFunc))
			return false;
		return true;
	}
	public ITransferFunction getAlphaFunc() {
		return alphaFunc;
	}
	public void setAlphaFunc(ITransferFunction alphaFunc) {
		this.alphaFunc = alphaFunc;
	}
	public boolean isInverseAlpha() {
		return inverseAlpha;
	}
	public void setInverseAlpha(boolean inverseAlpha) {
		this.inverseAlpha = inverseAlpha;
	}
	public ITransferFunction getRedFunc() {
		return redFunc;
	}
	public void setRedFunc(ITransferFunction redFunc) {
		this.redFunc = redFunc;
	}
	public ITransferFunction getGreenFunc() {
		return greenFunc;
	}
	public void setGreenFunc(ITransferFunction greenFunc) {
		this.greenFunc = greenFunc;
	}
	public ITransferFunction getBlueFunc() {
		return blueFunc;
	}
	public void setBlueFunc(ITransferFunction blueFunc) {
		this.blueFunc = blueFunc;
	}
	public boolean isInverseRed() {
		return inverseRed;
	}
	public void setInverseRed(boolean inverseRed) {
		this.inverseRed = inverseRed;
	}
	public boolean isInverseGreen() {
		return inverseGreen;
	}
	public void setInverseGreen(boolean inverseGreen) {
		this.inverseGreen = inverseGreen;
	}
	public boolean isInverseBlue() {
		return inverseBlue;
	}
	public void setInverseBlue(boolean inverseBlue) {
		this.inverseBlue = inverseBlue;
	}

}
