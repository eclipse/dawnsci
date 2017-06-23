/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;


/**
 * An unvalidated set of terms, such as the description of a beam line.
 * Use :ref:`NXcollection` to gather together any set of terms.
 * The original suggestion is to use this as a container
 * class for the description of a beamline.
 * For NeXus validation, :ref:`NXcollection` will always generate
 * a warning since it is always an optional group.
 * Anything (groups, fields, or attributes) placed in
 * an :ref:`NXcollection` group will not be validated.
 * 
 * @version 1.0
 */
public interface NXcollection extends NXobject {

}
