/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Colin Palmer - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.json.mixin.roi;

import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class IROIMixIn {

	@JsonProperty abstract void setPoint(double[] point);

	// TODO do these need to be explicitly ignored?
//	public void setPoint(int[] point);
//	public void setPoint(int x, int y);
//	public void setPoint(double x, double y);

	@JsonIgnore abstract double[] getPointRef();

	@JsonProperty abstract double[] getPoint();

	@JsonIgnore abstract double getPointX();

	@JsonIgnore abstract double getPointY();

	@JsonIgnore abstract int[] getIntPoint();

	@JsonIgnore abstract void setPlot(boolean require);

	@JsonIgnore abstract boolean isPlot();

	@JsonIgnore abstract IRectangularROI getBounds();

	@JsonIgnore abstract boolean isNearOutline(double x, double y, double distance);
}
