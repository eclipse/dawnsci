/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * This is the description for the general spatial location
 * of a component - it is used by the NXgeometry class
 * 
 * @version 1.0
 */
public interface NXtranslation extends NXobject {

	/**
	 * Link to other object if we are relative, else absent
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();

	/**
	 * (x,y,z)
	 * This field describes the lateral movement of a component.
	 * The pair of groups NXtranslation and NXorientation together
	 * describe the position of a component.
	 * For absolute position, the origin is the scattering center (where a perfectly
	 * aligned sample would be) with the z-axis pointing downstream and the y-axis
	 * pointing gravitationally up. For a relative position the NXtranslation is
	 * taken into account before the NXorientation. The axes are right-handed and
	 * orthonormal.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: numobj; 2: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistances();

}
