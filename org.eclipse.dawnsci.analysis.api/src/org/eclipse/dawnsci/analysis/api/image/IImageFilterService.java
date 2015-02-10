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

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * This service can be called to process IDataset using BoofCV filtering algorithms
 * 
 * @author wqk87977
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
	 * @param orig   Input image.  Not modified.
	 * @return Output list containing the image derivative along the x-axis and y-axis 
	 */
	public List<IDataset> filterDerivativeSobel(IDataset orig);

	/**
	 * Applies a global threshold across the whole image.  If 'down' is true, then pixels with values <=
	 * to 'threshold' are set to 1 and the others set to 0.  If 'down' is false, then pixels with values >=
	 * to 'threshold' are set to 1 and the others set to 0.
	 *
	 * @param input Input image. Not modified.
	 * @param threshold threshold value.
	 * @param down If true then the inequality <= is used, otherwise if false then >= is used.
	 * @param isBinary if true will convert to a binary image
	 * @return Output image.
	 */
	public IDataset filterThreshold(IDataset input, float threshold , boolean down, boolean isBinary);

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
	 * The input data is converted to a binary image first then the contour algorithm is applied to it.
	 * <p>
	 * The returned contours are traces of the object.  The trace of an object can be found by marking a point
	 * with a pen and then marking every point on the contour without removing the pen.  It is possible to have
	 * the same point multiple times in the contour.
	 * </p>
	 *
	 * @param input Input binary image.  Not modified.
	 * @param rule Connectivity rule.  Can be 4 or 8.  8 is more commonly used.
	 * @param colorExternal RGB color
	 * @param colorInternal RGB color
	 * @return Dataset contours for each blob.
	 * @throws Exception 
	 */
	public IDataset filterContour(IDataset input, int rule, int colorExternal, int colorInternal) throws Exception;
}
