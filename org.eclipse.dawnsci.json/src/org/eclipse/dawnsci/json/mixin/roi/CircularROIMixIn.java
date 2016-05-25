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

public abstract class CircularROIMixIn {

	@JsonProperty abstract double getRadius();

	@JsonProperty abstract void setRadius(double radius);

	@JsonIgnore abstract double[] getCentre();

	@JsonIgnore abstract void setCentre(double... centre);
}
