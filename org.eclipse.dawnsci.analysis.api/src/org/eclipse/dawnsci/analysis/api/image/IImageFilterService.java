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
package org.eclipse.dawnsci.analysis.api.image;

import org.eclipse.january.dataset.IDataset;

/**
 * This service can be called to process IDataset using BoofCV filtering algorithms
 * 
 * @author Baha El Kassaby
 *
 */
public interface IImageFilterService {

	/**
	 * Applies a median filter
	 * 
	 * @param input
	 * @param radius
	 * @return output filtered image
	 */
	public IDataset filterMedian(IDataset input, int radius);

	/**
	 * Applies a mean box filter
	 * 
	 * @param input
	 * @param radius
	 * @return output filtered image
	 */
	public IDataset filterMean(IDataset input, int radius);

	/**
	 * Applies a min filter
	 * 
	 * @param input
	 * @param radius
	 * @return output filtered image
	 */
	public IDataset filterMin(IDataset input, int radius);

	/**
	 * Applies a max filter
	 * 
	 * @param input
	 * @param radius
	 * @return output filtered image
	 */
	public IDataset filterMax(IDataset input, int radius);

	/**
	 * Applies Gaussian blur.
	 *
	 * @param input Input image.  Not modified.
	 * @param sigma Gaussian distribution's sigma.  If <= 0 then will be selected based on radius.
	 * @param radius Radius of the Gaussian blur function. If <= 0 then radius will be determined by sigma.
	 * @return Output blurred image.
	 */
	public IDataset filterGaussianBlur(IDataset input, double sigma, int radius);

	/**
	 * Computes the derivative in the X and Y direction using an integer Sobel edge detector.
	 *
	 * @param orig
	 *            Input image. Not modified.
	 * @param isXaxis
	 *            if true the image derivative will be along the XAxis, if false, along the yaxis
	 * @return the image derivative along the x-axis or y-axis
	 */
	public IDataset filterDerivativeSobel(IDataset orig, boolean isXaxis);

	/**
	 * <p>
	 * Erodes an image according to a 8-neighborhood.  Unless a pixel is connected to all its neighbors its value
	 * is set to zero.
	 * </p>
	 *
	 * @param input  Input image. Not modified.
	 * @param isBinary if true will convert to a binary image
	 * @return Output image.
	 */
	public IDataset filterErode(IDataset input, boolean isBinary);

	/**
	 * <p>
	 * Dilates an image according to a 8-neighborhood.  If a pixel is connected to any other pixel then its output
	 * value will be one.
	 * </p>
	 *
	 * @param input  Input image. Not modified.
	 * @param isBinary if true will convert to a binary image
	 * @return Output image.
	 */
	public IDataset filterDilate(IDataset input, boolean isBinary);

	/**
	 * <p>
	 * Erodes and dilates an image. See filterErode and filetDilate
	 * </p>
	 *
	 * @param input  Input image. Not modified.
 	 * @param isBinary if true will convert to a binary image
	 * @return Output image.
	 */
	public IDataset filterErodeAndDilate(IDataset input, boolean isBinary);

	/**
	 * <p>
	 * Given a binary image, connect together pixels to form blobs/clusters using the specified connectivity rule.
	 * The found blobs will be labeled in an output image and also described as a set of contours.  Pixels
	 * in the contours are consecutive order in a clockwise or counter-clockwise direction, depending on the
	 * implementation.
	 * </p>
	 * <p>
	 * The returned contours are traces of the object.  The trace of an object can be found by marking a point
	 * with a pen and then marking every point on the contour without removing the pen.  It is possible to have
	 * the same point multiple times in the contour.
	 * </p>
	 *
	 * @param input Input binary image.  Not modified.
	 * @param rule Connectivity rule.  Can be 4 or 8.  8 is more commonly used.
	 * @return Dataset Output labeled image.
	 * @throws Exception 
	 */
	public IDataset extractBlob(IDataset input, int rule) throws Exception;

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
