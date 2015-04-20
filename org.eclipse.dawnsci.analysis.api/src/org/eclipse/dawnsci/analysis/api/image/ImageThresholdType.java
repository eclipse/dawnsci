/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.dawnsci.analysis.api.image;

/**
 * Thresholding filters
 * 
 * @author Baha El Kassaby
 */
public enum ImageThresholdType {
	GLOBAL_CUSTOM,
	GLOBAL_MEAN,
	GLOBAL_OTSU,
	GLOBAL_ENTROPY,
	ADAPTIVE_SQUARE,
	ADAPTIVE_GAUSSIAN,
	ADAPTIVE_SAUVOLA;
}