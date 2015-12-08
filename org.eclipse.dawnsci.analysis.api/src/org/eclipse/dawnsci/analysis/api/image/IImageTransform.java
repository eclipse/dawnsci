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
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * This service can be called to process IDataset using BoofCV transform algorithms
 * 
 * @author Baha El Kassaby
 *
 */
public interface IImageTransform {

	/**
	 * Rotates a 2d dataset by n degrees
	 * 
	 * @param data
	 *            dataset to be rotated
	 * @param angle
	 *            in degrees
	 * @return rotated image
	 */
	public IDataset rotate(IDataset data, double angle) throws Exception;

	/**
	 * Rotates a 2d dataset by n degrees
	 * 
	 * @param data
	 *            dataset to be rotated
	 * @param angle
	 *            in degrees
	 * @param keepShape
	 *            if true, the resulting image will have the same shape as the original
	 * @return rotated image
	 * @throws Exception
	 */
	public IDataset rotate(IDataset data, double angle, boolean keepShape) throws Exception;

	/**
	 * Aligns a stack of images using feature association
	 * 
	 * @param images
	 *            stack of images
	 * @param monitor
	 *            progress monitor
	 * @return aligned stack of images
	 * @throws Exception
	 */
	public List<IDataset> align(List<IDataset> images, IMonitor monitor) throws Exception;

	/**
	 * <p>
	 * Applies an affine transformation from the input image to the output image.
	 * </p>
	 *
	 * <p>
	 * Input coordinates (x,y) to output coordinate (x',y')<br>
	 * x' = a11*x + a12*y + dx<br>
	 * y' = a21*x + a22*y + dy
	 * </p>
	 * @param input Which which is being rotated.
	 *
	 */
	public IDataset affineTransform(IDataset input, double a11, double a12, double a21, double a22, double dx, double dy);

}
