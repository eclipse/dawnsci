/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.analysis.api.image;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * This service can be called to process IDataset using BoofCV stitching algorithms
 * 
 * @author Baha El Kassaby
 *
 */
public interface IImageStitchingProcess {

	/**
	 * Stitches a list of images with a default matrix size and angle
	 * 
	 * @param input
	 * @return output stitched image
	 */
	public IDataset stitch(List<IDataset> input);

	/**
	 * Stitches a list of images
	 * 
	 * @param input
	 * @param rows
	 * @param columns
	 * @return output stitched image
	 */
	public IDataset stitch(List<IDataset> input, int rows, int columns);

	/**
	 * Crops a list of images given a ROI then stitches them together
	 * 
	 * @param input
	 * @param rows
	 * @param columns
	 * @param fieldOfView
	 * @return output stitched image
	 */
	public IDataset stitch(List<IDataset> input, int rows, int columns, double fieldOfView);

	/**
	 * Stiches a list of images previously cropped with the given roi. Use the theoretical image position from the
	 * metadata to stitch the images.
	 * 
	 * @param input
	 * @param rows
	 * @param columns
	 * @param fieldOfView
	 *            in microns
	 * @param translations
	 *            X/Y expected translations in microns (mainly used if no feature association)
	 * @param hasFeatureAssociation
	 *            if True then feature association will be used to find the translation coordinates<br>
	 * @return output stitched image
	 */
	public IDataset stitch(List<IDataset> input, int rows, int columns, double fieldOfView, List<double[]> translations, 
			boolean hasFeatureAssociation);

	/**
	 * Stiches a list of images previously cropped with the given roi. Use the theoretical image position from the
	 * metadata to stitch the images.
	 * 
	 * @param input
	 * @param rows
	 * @param columns
	 * @param fieldOfView
	 *            in microns
	 * @param translations
	 *            X/Y expected translations in microns (mainly used if no feature association)
	 * @param hasFeatureAssociation
	 *            if True then feature association will be used to find the translation coordinates<br>
	 * @param originalShape
	 *            if input data is cropped, original shape of data
	 * @return output stitched image
	 */
	public IDataset stitch(List<IDataset> input, int rows, int columns, double fieldOfView,
			List<double[]> translations, boolean hasFeatureAssociation, int[] originalShape);

}
