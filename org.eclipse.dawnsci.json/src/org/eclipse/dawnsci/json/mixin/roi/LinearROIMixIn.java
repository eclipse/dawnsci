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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class LinearROIMixIn {

	@JsonIgnore abstract void setPointKeepEndPoint(int[] pt);

	@JsonIgnore abstract void setPointKeepEndPoint(double[] pt);

	@JsonIgnore abstract double[] getEndPoint();

	@JsonIgnore abstract int[] getIntEndPoint();

	@JsonIgnore abstract void setEndPoint(double eptx, double epty);

	@JsonIgnore abstract void setEndPoint(double[] ept);

	@JsonIgnore abstract void setEndPoint(int[] ept);

	@JsonIgnore abstract double[] getMidPoint();

	@JsonIgnore abstract void setMidPoint(double[] mpt);

	@JsonProperty abstract void setLength(double len);

	@JsonProperty abstract double getLength();

	@JsonIgnore abstract double[] getPoint(double f);

	@JsonIgnore abstract void setCrossHair(boolean crossHair);

	@JsonIgnore abstract boolean isCrossHair();
}
