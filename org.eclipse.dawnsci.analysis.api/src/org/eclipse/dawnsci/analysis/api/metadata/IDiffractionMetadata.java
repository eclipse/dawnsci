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

package org.eclipse.dawnsci.analysis.api.metadata;

import org.eclipse.dawnsci.analysis.api.diffraction.DetectorProperties;
import org.eclipse.dawnsci.analysis.api.diffraction.DiffractionCrystalEnvironment;

/**
 * This interface is used to mark meta data which conforms to the diffraction meta
 * data specification. The following keys will exist in the IMetadata and must return
 * meaningful values.
 */
public interface IDiffractionMetadata extends IMetadata {

	public DetectorProperties getDetector2DProperties();
	
	public DiffractionCrystalEnvironment getDiffractionCrystalEnvironment();
	
	public DetectorProperties getOriginalDetector2DProperties();

	public DiffractionCrystalEnvironment getOriginalDiffractionCrystalEnvironment();
	
	@Override
	public IDiffractionMetadata clone();
}
