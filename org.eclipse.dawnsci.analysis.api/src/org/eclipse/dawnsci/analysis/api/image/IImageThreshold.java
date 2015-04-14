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

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * This service can be called to process IDataset using BoofCV threshold algorithms
 * 
 * @author Baha El Kassaby
 *
 */
public interface IImageThreshold {

	/**
	 * Applies a global threshold across the whole image. If 'down' is true, then pixels with values <= to 'threshold'
	 * are set to 1 and the others set to 0. If 'down' is false, then pixels with values >= to 'threshold' are set to 1
	 * and the others set to 0.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param threshold
	 *            threshold value.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return Output image.
	 */
	public IDataset globalThreshold(IDataset input, float threshold, boolean down, boolean isBinary);

	/**
	 * Applies a global mean threshold across the whole image with the mean pixel intensity value as a threshold value
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return output image
	 */
	public IDataset globalMeanThreshold(IDataset input, boolean down, boolean isBinary);

	/**
	 * Applies a global mean threshold across the whole image with the variance based threshold using Otsu's method.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return output image
	 */
	public IDataset globalOtsuThreshold(IDataset input, boolean down, boolean isBinary);

	/**
	 * Applies a global mean threshold across the whole image with the threshold which maximizes the entropy between the
	 * foreground and background regions.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return output image
	 */
	public IDataset globalEntropyThreshold(IDataset input, boolean down, boolean isBinary);

	/**
	 * Thresholds the image using an adaptive threshold that is computed using a local square region centered on each
	 * pixel. The threshold is equal to the average value of the surrounding pixels plus the bias. If down is true then
	 * b(x,y) = I(x,y) <= T(x,y) + bias ? 1 : 0. Otherwise b(x,y) = I(x,y) >= T(x,y) + bias ? 0 : 1
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param radius
	 *            Radius of square region.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return output image
	 */
	public IDataset adaptiveSquareThreshold(IDataset input, int radius, boolean down, boolean isBinary);

	/**
	 * Thresholds the image using an adaptive threshold that is computed using a local square region centered on each
	 * pixel. The threshold is equal to the gaussian weighted sum of the surrounding pixels plus the bias. If down is
	 * true then b(x,y) = I(x,y) <= T(x,y) + bias ? 1 : 0. Otherwise b(x,y) = I(x,y) >= T(x,y) + bias ? 0 : 1
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param radius
	 *            Radius of square region.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return output image
	 */
	public IDataset adaptiveGaussianThreshold(IDataset input, int radius, boolean down, boolean isBinary);

	/**
	 * Applies Sauvola thresholding to the input image. Intended for use with text image.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param radius
	 *            Radius of square region.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary
	 *            if true will convert to a binary image
	 * @return output image
	 */
	public IDataset adaptiveSauvolaThreshold(IDataset input, int radius, boolean down, boolean isBinary);

}
