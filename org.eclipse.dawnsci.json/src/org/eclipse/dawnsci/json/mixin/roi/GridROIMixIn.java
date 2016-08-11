/*-
 *******************************************************************************
 * Copyright (c) 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Baha El Kassaby - Added support for extra ROIS
 *******************************************************************************/

package org.eclipse.dawnsci.json.mixin.roi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GridROIMixIn extends RectangularROIMixIn {

	@JsonProperty abstract double[] getSpacing();

	@JsonIgnore abstract int[] getIntSpacing();

	@JsonProperty abstract double[][] getGridPoints();

	@JsonProperty abstract double[][] getGridLines();

	@JsonProperty abstract double getxSpacing();

	@JsonProperty abstract double getySpacing();

	@JsonIgnore abstract void setxySpacing(double xSpacing, double ySpacing);

	@JsonProperty abstract void setxSpacing(double xSpacing);

	@JsonProperty abstract void setySpacing(double ySpacing);

	@JsonIgnore abstract boolean isMidPointOn();

	@JsonIgnore abstract void setMidPointOn(boolean midPointOn);

	@JsonIgnore abstract boolean isGridLineOn();

	@JsonIgnore abstract void setGridLineOn(boolean gridLinesOn);

	@JsonProperty abstract int getNumberOfPoints();

	@JsonProperty abstract int[] getDimensions();

	@JsonIgnore abstract double[][] getPhysicalGridPoints();

	@JsonProperty abstract double[] getBeamCentre();

	@JsonProperty abstract double[] getPixelSizeM();
}
