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

import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EllipticalROIMixIn {

	@JsonIgnore abstract void downsample(double subFactor);

	@JsonProperty abstract double[] getSemiAxes();

	@JsonIgnore abstract double getSemiAxis(int index);

	@JsonProperty abstract void setSemiAxes(double[] semiaxis);

	@JsonIgnore abstract void setSemiaxes(double[] semiaxis);

	@JsonIgnore abstract void setSemiAxis(int index, double semiaxis);

	@JsonProperty abstract void setAngledegrees(double angle);

	@JsonIgnore abstract boolean isCircular();

	@JsonIgnore abstract double getAspectRatio();

	@JsonProperty abstract double[] getPoint(double angle);

	@JsonIgnore abstract double[] getRelativePoint(double angle);

	@JsonIgnore abstract double[] getPointDegrees(double angle);

	@JsonIgnore abstract double getDistance(double angle);

	@JsonIgnore abstract RectangularROI getBounds();

	@JsonIgnore abstract double getAngleRelative(double x, double y);

	@JsonIgnore abstract boolean containsPoint(double x, double y);

	@JsonIgnore abstract boolean isNearOutline(double x, double y, double distance);

	@JsonIgnore abstract boolean isContainedBy(RectangularROI rect);

	@JsonIgnore abstract void setDirty();

	@JsonIgnore abstract double[] getVerticalIntersectionParameters(double x);

	@JsonIgnore abstract double[] getHorizontalIntersectionParameters(double y);

	@JsonIgnore abstract double getStartParameter(double d);

	@JsonIgnore abstract double getEndParameter(double d);

	@JsonIgnore abstract void updateQValues();

	@JsonIgnore abstract double[] findHorizontalIntersections(double y);

}
