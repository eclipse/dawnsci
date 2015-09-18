/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Use ``NXcollection`` to gather together any set of terms.
 * The original suggestion is to use this as a container
 * class for the description of a beamline.
 * For NeXus validation, ``NXcollection`` will always generate
 * a warning since it is always an optional group.
 * Anything (groups, fields, or attributes) placed in
 * an ``NXcollection`` group will not be validated.
 * 
 * @version 1.0
 */
public interface NXcollection extends NXobject {

	/**
	 * name of the beamline for this collection
	 * 
	 * @return  the value.
	 */
	public IDataset getBeamline();

}
