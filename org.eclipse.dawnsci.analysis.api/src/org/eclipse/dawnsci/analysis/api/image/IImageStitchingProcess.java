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

import org.eclipse.january.IMonitor;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;

/**
 * This service can be called to process IDataset using BoofCV stitching algorithms
 * 
 * @author Baha El Kassaby
 *
 */
public interface IImageStitchingProcess {

	/**
	 * Stitches a list of images with a default matrix size and angle.
	 * Use {@link #stitch(ILazyDataset, IMonitor)} instead.
	 * @param input
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	@Deprecated
	public IDataset stitch(List<IDataset> input, IMonitor monitor) throws Exception;

	/**
	 * Stitches a lazy list of images with a default matrix size and angle
	 * 
	 * @param input
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	public IDataset stitch(ILazyDataset input, IMonitor monitor) throws Exception;

	/**
	 * Stitches a list of images.
	 * Use {@link #stitch(ILazyDataset, int, int, IMonitor)} instead.
	 * @param input
	 * @param rows
	 * @param columns
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	@Deprecated
	public IDataset stitch(List<IDataset> input, int rows, int columns, IMonitor monitor) throws Exception;

	/**
	 * Stitches a lazy list of images
	 * 
	 * @param input
	 * @param rows
	 * @param columns
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	public IDataset stitch(ILazyDataset input, int rows, int columns, IMonitor monitor) throws Exception;

	/**
	 * Crops a list of images given a ROI then stitches them together
	 * Use {@link #stitch(ILazyDataset, int, int, double, IMonitor)} instead.
	 * @param input
	 * @param rows
	 * @param columns
	 * @param fieldOfView
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	@Deprecated
	public IDataset stitch(List<IDataset> input, int rows, int columns, double fieldOfView, IMonitor monitor) throws Exception;

	/**
	 * Crops a lazy list of images given a ROI then stitches them together.
	 * 
	 * @param input
	 * @param rows
	 * @param columns
	 * @param fieldOfView
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	public IDataset stitch(ILazyDataset input, int rows, int columns, double fieldOfView, IMonitor monitor) throws Exception;

	/**
	 * Stiches a list of images previously cropped with the given roi. Use the theoretical image position from the
	 * metadata to stitch the images.
	 * Use {@link #stitch(ILazyDataset, int, int, double, double[][][], boolean, int[], IMonitor)} instead.
	 * @param input
	 * @param rows
	 * @param columns
	 * @param fieldOfView
	 *            in microns
	 * @param translations
	 *            X/Y expected translations in microns (mainly used if no feature association)
	 * @param hasFeatureAssociation
	 *            if True then feature association will be used to find the translation coordinates<br>
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	@Deprecated
	public IDataset stitch(List<IDataset> input, int rows, int columns, double fieldOfView, List<double[]> translations, 
			boolean hasFeatureAssociation, IMonitor monitor) throws Exception;

	/**
	 * Stiches a list of images previously cropped with the given roi. Use the theoretical image position from the
	 * metadata to stitch the images.
	 * Use {@link #stitch(ILazyDataset, int, int, double, double[][][], boolean, int[], IMonitor)} instead.
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
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	@Deprecated
	public IDataset stitch(List<IDataset> input, int rows, int columns, double fieldOfView,
			List<double[]> translations, boolean hasFeatureAssociation, int[] originalShape, IMonitor monitor) throws Exception;

	/**
	 * Stiches a list of images loaded lazily previously cropped with the given roi. Use the theoretical image position from the
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
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	@Deprecated
	IDataset stitch(ILazyDataset input, int rows, int columns, double fieldOfView, List<double[]> translations,
			boolean hasFeatureAssociation, int[] originalShape, IMonitor monitor) throws Exception;

	/**
	 * Stiches a list of images loaded lazily previously cropped with the given roi. Use the theoretical image position from the
	 * metadata to stitch the images. Translations are in an array.
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
	 * @param monitor
	 *            To monitor progress
	 * @return output stitched image
	 * @throws Exception 
	 */
	IDataset stitch(ILazyDataset input, int rows, int columns, double fieldOfView, double[][][] translations,
			boolean hasFeatureAssociation, int[] originalShape, IMonitor monitor) throws Exception;

	/**
	 * Stiches two images together given x and y translations
	 * 
	 * @param imageA
	 * @param imageB
	 * @param translations
	 *            where translations[0] is for the Y axes and translations[1] is for the X axes
	 * @return stitched image of imageA and imageB
	 * @throws Exception
	 */
	IDataset stitch(IDataset imageA, IDataset imageB, double[] translations) throws Exception;
}
