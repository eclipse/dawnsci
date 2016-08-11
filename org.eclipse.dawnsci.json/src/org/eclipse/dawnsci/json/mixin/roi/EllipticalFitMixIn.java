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

import org.eclipse.dawnsci.analysis.api.fitting.IConicSectionFitter;
import org.eclipse.dawnsci.analysis.api.roi.IPolylineROI;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EllipticalFitMixIn extends EllipticalROIMixIn {

	@JsonProperty abstract IConicSectionFitter fit(IPolylineROI polyline, final boolean fitCircle);

	@JsonProperty abstract void setPoints(IPolylineROI points);

	@JsonProperty abstract double getRMS();

	@JsonProperty abstract IConicSectionFitter getFitter();

	@JsonProperty abstract IPolylineROI getPoints();

}
