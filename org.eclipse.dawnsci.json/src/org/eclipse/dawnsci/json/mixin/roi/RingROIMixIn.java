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

public abstract class RingROIMixIn {

	@JsonProperty abstract double[] getRadii();

	@JsonIgnore abstract double getRadius(int index);

	@JsonIgnore abstract int[] getIntRadii();

	@JsonIgnore abstract int getIntRadius(int index);

	@JsonProperty abstract void setRadii(double radius[]);

	@JsonProperty abstract void setRadii(double startRadius, double endRadius);

	@JsonIgnore abstract void addRadii(double radius);

	@JsonIgnore abstract void addRadius(int index, double radius);

	@JsonProperty abstract void setClippingCompensation(boolean clippingCompensation);

	@JsonIgnore abstract boolean isClippingCompensation();

	@JsonProperty abstract double getDpp();

	@JsonProperty abstract void setDpp(double dpp);

	@JsonProperty abstract boolean isAverageArea();

	@JsonProperty abstract void setAverageArea(boolean averageArea);
}
