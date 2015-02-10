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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * Stores information from a powder calibration run for persistence into a NeXus file
 */
public interface IPowderCalibrationInfo {

	/**
	 * Get the name of the calibration standard used
	 * @return name
	 */
	public String getCalibrantName();

	/**
	 * Get the path to file containing the calibration image
	 * @return imagePath
	 */
	public String getCalibrationImagePath();

	/**
	 * Get the name of the detector calibrated
	 * @return detectorName
	 */
	public String getDetectorName();

	/**
	 * Get a string description of the calibration method
	 * @return method
	 */
	public String getMethodDescription();

	/**
	 * Get the d-space valued of the standard used in calibration
	 * @return dSpace
	 */
	public IDataset getCalibrantDSpaceValues();

	/**
	 * Get the indicies of the d-space values used in the calibration
	 * @return indicies
	 */
	public IDataset getUsedDSpaceIndexValues();
	
	/**
	 * Get the residual from the calibration process
	 * @return residual
	 */
	public double getResidual();
	
	/**
	 * For NXcite class
	 * [0] = description
	 * [1] = doi
	 * [2] = endnote
	 * [3] = bibtex
	 * 
	 * @return citeArray
	 */
	public String[] getCitationInformation();
}
