/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.roi;

public interface IRectangularROI extends IOrientableROI {
	@Override
	public IRectangularROI copy();

	/**
	 * @param i
	 * @return length in given dimension
	 */
	public double getLength(int i);

	/**
	 * End point of rectangle
	 * @return end
	 */
	public double[] getEndPoint();
}
