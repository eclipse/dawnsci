/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.diffraction;

import java.util.EventObject;

public class DetectorPropertyEvent extends EventObject {
	private static final long serialVersionUID = -4124035392214967002L;

	public static enum EventType {
		/**
		 * change in origin
		 */
		ORIGIN,
		/**
		 * change in beam centre
		 */
		BEAM_CENTRE,
		/**
		 * change in horizontal pixel size
		 */
		HPXSIZE,
		/**
		 * change in vertical pixel size
		 */
		VPXSIZE,
		/**
		 * change in normal direction
		 */
		NORMAL,
		/**
		 * change in geometry
		 */
		GEOMETRY,
	}

	private EventType type;

	public DetectorPropertyEvent(Object source, EventType propertyType) {
		super(source);
		type = propertyType;
	}

	public EventType getType() {
		return type;
	}

	public boolean hasOriginChanged() {
		return type == EventType.ORIGIN || type == EventType.NORMAL || type == EventType.GEOMETRY;
	}

	public boolean hasBeamCentreChanged() {
		return type == EventType.BEAM_CENTRE || type == EventType.GEOMETRY;
	}

	public boolean hasHPxSizeChanged() {
		return type == EventType.HPXSIZE;
	}

	public boolean hasVPxSizeChanged() {
		return type == EventType.VPXSIZE;
	}

	public boolean hasNormalChanged() {
		return type == EventType.NORMAL || type == EventType.GEOMETRY;
	}
}
