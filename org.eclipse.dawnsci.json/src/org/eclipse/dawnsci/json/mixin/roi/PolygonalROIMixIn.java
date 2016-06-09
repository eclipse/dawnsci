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

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PolygonalROIMixIn extends PolylineROIMixIn {

	@JsonProperty abstract boolean containsPoint(double x, double y);

	@JsonProperty abstract boolean isNearOutline(double x, double y, double distance);

	@JsonProperty abstract double[] findHorizontalIntersections(double y);
}
