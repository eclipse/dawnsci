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

import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RectangularROIMixIn {

	@JsonIgnore abstract void setClippingCompensation(boolean clippingCompensation);

	@JsonIgnore abstract boolean isClippingCompensation();

	@JsonProperty abstract void setLengths(double len[]);

	@JsonIgnore abstract void setLengths(double major, double minor);

	@JsonIgnore abstract double[] getEndPoint();

	@JsonIgnore abstract double[] getMidPoint();

	@JsonIgnore abstract void setMidPoint(double[] mpt);

	@JsonProperty abstract double[] getLengths();

	@JsonIgnore abstract void setEndPointKeepLengths(double[] pt);

	@JsonIgnore abstract void setEndPoint(int[] pt);

	@JsonIgnore abstract void setEndPoint(double[] pt);

	@JsonIgnore abstract void setEndPoint(int[] pt, boolean moveX, boolean moveY);

	@JsonIgnore abstract void setEndPoint(double[] pt, boolean moveX, boolean moveY);

	@JsonIgnore abstract void setPointKeepEndPoint(int[] dpt, boolean moveX, boolean moveY);

	@JsonIgnore abstract void setPointKeepEndPoint(double[] dpt, boolean moveX, boolean moveY);

	@JsonIgnore abstract RectangularROI getBounds();
}
