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

import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PolylineROIMixIn {

	@JsonIgnore abstract void setPoint(PointROI point);

	@JsonIgnore abstract int getSides();

	@JsonIgnore abstract int getNumberOfPoints();

	@JsonProperty abstract List<IROI> getPoints();

	@JsonProperty abstract void setPoints(List<IROI> points);
}
