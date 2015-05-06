/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.downsample;

/**
 * Enumerations for down-sample modes
 */
public enum DownsampleMode {
	POINT,  // select corner point of bin
	MEAN,   // mean average over bin
	MAXIMUM, // use maximum value in bin
	MINIMUM // use minimum value in bin
}
