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

public abstract class SectorROIMixIn extends RingROIMixIn {

	@JsonProperty abstract void setSymmetry(int symmetry);

	@JsonIgnore abstract int getSymmetry(String symmetry);

	@JsonProperty abstract int getSymmetry();

	@JsonProperty abstract void setAngles(double startAngle, double endAngle);

	@JsonIgnore abstract void setAngles(double angles[]);

	@JsonIgnore abstract double[] getAnglesDegrees();

	@JsonIgnore abstract double getAngleDegrees(int index);

	@JsonProperty abstract void setAnglesDegrees(double angles[]);

	@JsonIgnore abstract void setAnglesdegrees(double[] angles);

	@JsonIgnore abstract void setAnglesDegrees(double startAngle, double endAngle);

	@JsonProperty abstract double[] getAngles();

	@JsonIgnore abstract double getAngle(int index);

	@JsonIgnore abstract void addAngles(double angle);

	@JsonIgnore abstract void addAngle(int index, double angle);

	@JsonProperty abstract boolean checkSymmetry(int sym);

	@JsonIgnore abstract double[] getSymmetryAngles();

	@JsonIgnore abstract String getSymmetryText();

	@JsonIgnore abstract String getSymmetryText(int sym);

	@JsonIgnore abstract void setCombineSymmetry(boolean combineSymmetry);

	@JsonIgnore abstract boolean isCombineSymmetry();

	@JsonIgnore abstract boolean hasSeparateRegions();

	@JsonIgnore abstract boolean containsPoint(double x, double y);

	@JsonIgnore abstract boolean isNearOutline(double x, double y, double distance);

	@JsonIgnore abstract double[] findHorizontalIntersections(double y);
}
