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

public abstract class HyperbolicROIMixIn {

	@JsonIgnore abstract void downsample(double subFactor);

	@JsonProperty abstract double getSemilatusRectum();

	@JsonIgnore abstract void setSemilatusRectum(double semi);

	@JsonProperty abstract double getEccentricity();

	@JsonProperty abstract void setEccentricity(double eccentricity);

	@JsonProperty abstract double[] getPoint(double angle);

	@JsonIgnore abstract double[] getRelativePoint(double angle);

	@JsonProperty abstract double getAsymptoteAngle();

	@JsonIgnore abstract double[] getPointDegrees(double angle);

	@JsonIgnore abstract double getDistance(double angle);

	@JsonIgnore abstract boolean containsPoint(double x, double y);

	@JsonIgnore abstract boolean isNearOutline(double x, double y, double distance);

	@JsonIgnore abstract double[] getVerticalIntersectionParameters(double x);

	@JsonIgnore abstract double[] getHorizontalIntersectionParameters(double y);

	@JsonIgnore abstract double getStartParameter(double d);

	@JsonIgnore abstract double getEndParameter(double d);
}
