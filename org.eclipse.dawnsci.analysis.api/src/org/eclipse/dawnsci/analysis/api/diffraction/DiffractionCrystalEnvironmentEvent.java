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

public class DiffractionCrystalEnvironmentEvent extends EventObject {
	private static final long serialVersionUID = -6642603662682717892L;

	static enum EventType {
		WAVELENGTH,
	}

	private EventType type;

	public DiffractionCrystalEnvironmentEvent(Object source, EventType propertyType) {
		super(source);
		type = propertyType;
	}

	public boolean hasWavelengthChanged() {
		return type == EventType.WAVELENGTH;
	}

}
