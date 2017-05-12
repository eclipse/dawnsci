/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.plotting.api.area;

/**
 * The possible type of zoom of an {@link IPlotArea}
 *
 */
public enum ZoomOption {
	/** Interactive Rubberband zoom */
	RUBBERBAND_ZOOM,

	/** Interactive Dynamic zoom */
	DYNAMIC_ZOOM,

	/** Zoom via 'cursors' for horizontal start/end position */
	HORIZONTAL_ZOOM,

	/** Zoom via 'cursors' for vertical start/end position */
	VERTICAL_ZOOM,

	/** Zoom 'in' around mouse pointer */
	ZOOM_IN,

	/** Zoom 'out' around mouse pointer */
	ZOOM_OUT,

	/** Zoom 'in' around mouse pointer along horizontal axis */
	ZOOM_IN_HORIZONTALLY,

	/** Zoom 'out' around mouse pointer along horizontal axis */
	ZOOM_OUT_HORIZONTALLY,

	/** Zoom 'in' around mouse pointer along vertical axis */
	ZOOM_IN_VERTICALLY,

	/** Zoom 'out' around mouse pointer along vertical axes */
	ZOOM_OUT_VERTICALLY,

	/** Zoom 'out' around mouse pointer */
	PANNING,

	/** Disarm zoom behavior */
	NONE;

}
