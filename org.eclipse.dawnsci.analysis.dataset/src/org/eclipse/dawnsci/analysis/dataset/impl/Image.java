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
package org.eclipse.dawnsci.analysis.dataset.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.image.IImageFilterService;
import org.eclipse.dawnsci.analysis.api.image.IImageTransform;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;
import org.eclipse.dawnsci.analysis.dataset.impl.function.MapToRotatedCartesian;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Image processing package
 */
public class Image {

	/**
	 * Setup the logging facilities
	 */
	protected static final Logger logger = LoggerFactory.getLogger(Image.class);

	private static IImageFilterService filterService;
	private static IImageTransform transformService;

	public static void setImageFilterService(IImageFilterService ifs) {
		filterService = ifs;
	}

	public static void setImageTransformService(IImageTransform its) {
		transformService = its;
	}

	/**
	 * Find translation shift between two 2D datasets
	 * @param ia
	 * @param ib
	 * @param r rectangular region of interest to use for alignment
	 * @return a vector containing the translation needed to be applied to dataset b to align with dataset a
	 */
	public static double[] findTranslation2D(IDataset ia, IDataset ib, IRectangularROI r) {
		
		Dataset a = DatasetUtils.convertToDataset(ia);
		Dataset b = DatasetUtils.convertToDataset(ib);
		
		if (a.getRank() != 2 || b.getRank() != 2) {
			logger.error("Both datasets should be two-dimensional");
			throw new IllegalArgumentException("Both datasets should be two-dimensional");
		}
		Dataset f, g;
		if (r == null) {
			f = a;
			g = b;
		} else {
			MapToRotatedCartesian rcmap = new MapToRotatedCartesian(r);
			f = rcmap.value(a).get(0);
			g = rcmap.value(b).get(0);
		}

//		logger.info("f {} {}", f.shape, f.getElementDoubleAbs(0));
//		logger.info("g {} {}", g.shape, g.getElementDoubleAbs(0));

		// subtract mean before correlating
		List<? extends Dataset> corrs = Signal.phaseCorrelate(Maths.subtract(f, f.mean()), Maths.subtract(g, g.mean()), null, true);
		Dataset pcorr = corrs.get(0);
		int[] maxpos = pcorr.maxPos(); // peak pos
		int[] xshape = pcorr.getShapeRef();
		double mvalue = pcorr.max().doubleValue();

		logger.info("Max at {} is {}", maxpos, mvalue);
		double[] shift = new double[2];

		// Refine shift using inverse of cross-power spectrum
		// Foroosh et al, "Extension of Phase Correlation to Subpixel Registration",
		// IEEE Trans. Image Processing, v11n3, 188-200 (2002)
		Dataset xcorr = corrs.get(1);

		double c0 = xcorr.getDouble(maxpos);

		for (int i = 0; i < 2; i++) {
			maxpos[i]++;
			if (maxpos[i] < xshape[i]) {
				final double c1 = xcorr.getDouble(maxpos);
				shift[i] = c1/(c1-c0);
				if (Math.abs(shift[i]) > 1)
					shift[i] = c1/(c1+c0);
			} 
			maxpos[i]--;
		}
		logger.info("Partial shift is {}", shift);

		for (int i = 0; i < 2; i++) {
			shift[i] += maxpos[i];
			if (shift[i] > xshape[i]/2) {
				shift[i] -= xshape[i];
				logger.info("  Unwrapped position to {}", shift[i] );
			}
		}
		logger.info("Shift is {}", shift);

		return shift;
	}

	public static Dataset regrid_kabsch(Dataset data, Dataset x, Dataset y, Dataset gridX, Dataset gridY) {
		// create the output array
		DoubleDataset result = new DoubleDataset(gridY.getShapeRef()[0]+1, gridX.getShapeRef()[0]+1);
		IntegerDataset count = new IntegerDataset(gridY.getShapeRef()[0]+1, gridX.getShapeRef()[0]+1);

		IndexIterator it = data.getIterator();
		while(it.hasNext()){
			double xpos = x.getElementDoubleAbs(it.index);
			double ypos = y.getElementDoubleAbs(it.index);
			double dvalue = data.getElementDoubleAbs(it.index);
			int xind = getLowerIndex(xpos,gridX);
			int yind = getLowerIndex(ypos,gridY);
			
			if (xind >= 0 && xind < gridX.getShape()[0]-1 && yind >= 0 && yind < gridY.getShape()[0]-1) {
			
				double x1 = gridX.getDouble(xind+1);
				double x0 = gridX.getDouble(xind);
				double dx = Math.abs(x1 - x0);
				double y1 = gridY.getDouble(yind+1);
				double y0 = gridY.getDouble(yind);
				double dy = Math.abs(y1 - y0);
				
				// now work out the 4 weightings
				double ux0 = Math.abs(dx - Math.abs(xpos-x0));
				double uy0 = Math.abs(dy - Math.abs(ypos-y0));
				double ux1 = Math.abs(dx - Math.abs(xpos-x1));
				double uy1 = Math.abs(dy - Math.abs(ypos-y1));
				
				double area = dx*dy;
				
				double w00 = ((ux0*uy0)/area);
				double w01 = ((ux0*uy1)/area);
				double w10 = ((ux1*uy0)/area);
				double w11 = ((ux1*uy1)/area);
				
				if (Math.abs(w00+w10+w01+w11 -1.0) > 0.000001) {
					System.out.println(w00+w10+w01+w11);
				}
				
				double new00 = result.getDouble(yind,xind)+(w00*dvalue);
				result.set(new00, yind, xind);
				count.set(count.get(yind, xind)+1, yind, xind);
				double new01 = result.getDouble(yind,xind+1)+(w01*dvalue);
				result.set(new01, yind, xind+1);
				count.set(count.get(yind, xind+1)+1, yind, xind+1);
				double new10 = result.getDouble(yind+1,xind)+(w10*dvalue);
				result.set(new10, yind+1, xind);
				count.set(count.get(yind+1, xind)+1, yind+1, xind);
				double new11 = result.getDouble(yind+1,xind+1)+(w11*dvalue);
				result.set(new11, yind+1, xind+1);
				count.set(count.get(yind+1, xind+1)+1, yind+1, xind+1);
			}
		}
		
		return result;
	}
	
	private static int getLowerIndex(double point, Dataset axis) {
		Dataset mins = Maths.abs(Maths.subtract(axis, point));
		return mins.minPos()[0];
		
	}
	
	public static Dataset regrid(Dataset data, Dataset x, Dataset y, Dataset gridX, Dataset gridY) {
		// return regrid_kabsch(data, x, y, gridX, gridY);

		try {
			return InterpolatorUtils.regrid(data, x, y, gridX, gridY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("TODO put description of error here", e);
		}
		return null;
	}

	public static enum FilterType {
		MEDIAN, MIN, MAX, MEAN
	}

	/**
	 * Applies a minimum filter (slower)
	 * 
	 * @param input
	 * @param kernel
	 * @return filtered data
	 */
	public static Dataset minFilter(Dataset input, int[] kernel) {
		return filter(input, kernel, FilterType.MIN);
	}

	/**
	 * Applies a maximum filter (slower)
	 * 
	 * @param input
	 * @param kernel
	 * @return filtered data
	 */
	public static Dataset maxFilter(Dataset input, int[] kernel) {
		return filter(input, kernel, FilterType.MAX);
	}

	/**
	 * Applies a median filter (slower)
	 * 
	 * @param input
	 * @param kernel
	 * @return filtered data
	 */
	public static Dataset medianFilter(Dataset input, int[] kernel) {
		return filter(input, kernel, FilterType.MEDIAN);
	}

	/**
	 * Applies a mean Filter (slower)
	 * 
	 * @param input
	 * @param kernel
	 * @return filtered data
	 */
	public static Dataset meanFilter(Dataset input, int[] kernel) {
		return filter(input, kernel, FilterType.MEAN);
	}

	/**
	 * Applies a median filter (faster)
	 * 
	 * @param input
	 * @param radius
	 * @return filtered data
	 */
	public static Dataset medianFilter(Dataset input, int radius) {
		return filter(input, radius, FilterType.MEDIAN);
	}

	/**
	 * Applies a mean filter (faster)
	 * 
	 * @param input
	 * @param radius
	 * @return filtered data
	 */
	public static Dataset meanFilter(Dataset input, int radius) {
		return filter(input, radius, FilterType.MEAN);
	}

	private static Dataset filter(Dataset input, int radius, FilterType type) {
		if (type == FilterType.MEDIAN) {
			return DatasetUtils.convertToDataset(filterService.filterMedian(input, radius));
		} else if (type == FilterType.MIN) {
			return DatasetUtils.convertToDataset(filterService.filterMin(input, radius));
		} else if (type == FilterType.MAX) {
			return DatasetUtils.convertToDataset(filterService.filterMax(input, radius));
		} else if (type == FilterType.MEAN) {
			return DatasetUtils.convertToDataset(filterService.filterMean(input, radius));
		}
		return null;
	}

	private static Dataset filter(Dataset input, int[] kernel, FilterType type) {
		input.squeeze();
		// check to see if the kernel shape in the correct dimensionality.
		int[] shape = input.getShape();
		if (kernel.length != shape.length)
			throw new IllegalArgumentException("Kernel shape must be the same shape as the input dataset");

		Dataset result = input.clone();
		int[] offset = kernel.clone();
		for (int i = 0; i < offset.length; i++) {
			offset[i] = -kernel[i] / 2;
		}
		IndexIterator iter = input.getIterator(true);
		int[] pos = iter.getPos();
		int[] start = new int[pos.length];
		int[] stop = new int[pos.length];
		while (iter.hasNext()) {
			for (int i = 0; i < pos.length; i++) {
				start[i] = pos[i] + offset[i];
				stop[i] = start[i] + kernel[i];
				if (start[i] < 0)
					start[i] = 0;
				if (stop[i] >= shape[i])
					stop[i] = shape[i];
			}
			Dataset slice = input.getSlice(start, stop, null);
			if (type == FilterType.MEDIAN) {
				result.set(Stats.median(slice), pos);
			} else if (type == FilterType.MIN) {
				result.set(slice.min(), pos);
			} else if (type == FilterType.MAX) {
				result.set(slice.max(), pos);
			} else if (type == FilterType.MEAN) {
				result.set(slice.mean(), pos);
			}
		}
		return result;
	}

	/**
	 * Applies a background filter by first applying a median filter of radius 1, then a minimum and maximum filter and
	 * finally another median filter with the given radius
	 * 
	 * @param input
	 * @param radius
	 * @return filtered data
	 */
	public static Dataset backgroundFilter(Dataset input, int radius) {
		input.squeeze();
		Dataset median1 = filter(input, 1, FilterType.MEDIAN);
		Dataset min = filter(median1, new int[] {radius*2 + 1, radius * 2 + 1}, FilterType.MIN);
		Dataset max = filter(min, new int[] {radius*2 + 1, radius*2 + 1}, FilterType.MAX);
		Dataset median2 = filter(max, radius, FilterType.MEDIAN);
		return Maths.subtract(input, median2);
	}

	public static Dataset convolutionFilter(Dataset input, Dataset kernel) {
		input.squeeze();
		// check to see if the kernel shape in the correct dimensionality.
		int[] shape = input.getShape();
		int[] kShape = kernel.getShape();
		if (kShape.length != shape.length)
			throw new IllegalArgumentException("Kernel shape must be the same shape as the input dataset");

		Dataset result = input.clone();
		int[] offset = kShape.clone();
		for (int i = 0; i < offset.length; i++) {
			offset[i] = -kShape[i] / 2;
		}
		IndexIterator iter = input.getIterator(true);
		int[] pos = iter.getPos();
		int[] start = new int[pos.length];
		int[] stop = new int[pos.length];
		int[] kStart = new int[pos.length];
		int[] kStop = kShape.clone();
		while (iter.hasNext()) {
			boolean kClipped = false;
			for (int i = 0; i < pos.length; i++) {
				start[i] = pos[i] + offset[i];
				stop[i] = start[i] + kShape[i];
				kStart[i] = 0;
				if (start[i] < 0) {
					kStart[i] = -start[i];
					start[i] = 0;
					kClipped = true;
				}
				kStop[i] = kShape[i];
				if (stop[i] >= shape[i]) {
					kStop[i] = kStop[i] - (stop[i] - shape[i]);
					stop[i] = shape[i];
					kClipped = true;
				}
			}
			Dataset tempKernel = kernel;
			if (kClipped)
				tempKernel = kernel.getSlice(kStart, kStop, null);
			Dataset slice = input.getSlice(start, stop, null);
			slice.imultiply(tempKernel);
			result.set(slice.sum(), pos);
		}

		return result;
	}
	
	public static Dataset sobelFilter(Dataset input) {
		input.squeeze();
		//TODO should be extended for Nd but 2D is all that is required for now.
		if(input.getShape().length != 2) throw new IllegalArgumentException("The sobel filter only works on 2D datasets");
		DoubleDataset kernel = new DoubleDataset(new double[] {-1,0,1,-2,0,2,-1,0,1}, 3 ,3);
		Dataset result = convolutionFilter(input, kernel);
		kernel = new DoubleDataset(new double[] {-1,-2,-1,0,0,0,1,2,1}, 3 ,3);
		result.iadd(convolutionFilter(input, kernel));
		return result;
	}
	
	/**
	 * Compute the fano factor for a given box size
	 * @param input
	 * @param width  - must be odd number
	 * @param height - must be odd number
	 * @return fano factor image
	 * @throws Exception
	 */
	public static Dataset fanoFilter(Dataset input, int width, int height) throws Exception {
		final SummedAreaTable table = new SummedAreaTable(input, true);
		return table.getFanoImage(width, height);
	}

	public static Dataset flip(Dataset input, boolean vertical) {
		Dataset ret;
		if (vertical) {
			ret = input.getSlice(null, null, new int[] { -1, 1 });
		} else {
			ret = input.getSlice(null, null, new int[] { 1, -1 });
		}
		return ret;
	}

	/**
	 * Rotates @input by @angle degrees around its centre
	 * 
	 * @param input
	 *            input image
	 * @param angle
	 *            rotation angle in degrees
	 * @param keepShape
	 *            if true the resulting image shape will be the same as the original, if false the bounding box is
	 *            resized to display the image in its entirety
	 * @return rotated image
	 * @throws Exception
	 */
	public static Dataset rotate(Dataset input, double angle, boolean keepShape) throws Exception {
		if (input.getRank() != 2)
			throw new Exception("Error: input dataset rank expected is 2");
		IDataset ret = transformService.rotate(input.cast(input.getDtype()), angle, keepShape);
		Dataset result = DatasetUtils.cast(ret, input.getDtype());
		return result;
	}

	/**
	 * Aligns an image stack with shifted features using Hessian transformation
	 * 
	 * @param input
	 *            input image stack
	 * @return aligned images
	 * @throws Exception
	 */
	public static Dataset align(Dataset input) throws Exception {
		if (input.getRank() != 3)
			throw new Exception("Error: input dataset rank expected is 3");
		int[] size = input.getShape();
		List<IDataset> images = new ArrayList<IDataset>(size[0]);

		for (int i = 0; i < size[0]; i ++) {
			IDataset data = input.getSlice(new Slice(i, size[0], size[1]));
			images.add(data.squeeze());
		}
		List<IDataset> aligned = transformService.align(images, new IMonitor.Stub());
		Dataset[] alignedData = new Dataset[aligned.size()];
		for (int i = 0; i < aligned.size(); i ++) {
			IDataset dat = aligned.get(i);
			dat.resize(new int[]{1, size[1], size[2]});
			alignedData[i] = DatasetUtils.cast(dat, input.getDtype());
		}
		Dataset result = DatasetUtils.concatenate(alignedData, 0);
		return result;
	}

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
	 * @return Output image.
	 */
	public static IDataset globalThreshold(IDataset input, float threshold, boolean down) {
		return filterService.globalThreshold(input, threshold, down, false);
	}

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
	public static IDataset globalThreshold(IDataset input, float threshold , boolean down, boolean isBinary) {
		return filterService.globalThreshold(input, threshold, down, isBinary);
	}

	/**
	 * Applies a global mean threshold across the whole image with the mean pixel intensity value as a threshold value
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @return output image
	 */
	public static IDataset globalMeanThreshold(IDataset input, boolean down) {
		return filterService.globalMeanThreshold(input, down, true);
	}

	/**
	 * Applies a global mean threshold across the whole image with the variance based threshold using Otsu's method.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @return output image
	 */
	public static IDataset globalOtsuThreshold(IDataset input, boolean down) {
		return filterService.globalOtsuThreshold(input, down, true);
	}

	/**
	 * Applies a global mean threshold across the whole image with the threshold which maximizes the entropy between the
	 * foreground and background regions.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @return output image
	 */
	public static IDataset globalEntropyThreshold(IDataset input, boolean down) {
		return filterService.globalEntropyThreshold(input, down, true);
	}

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
	 * @return output image
	 */
	public static IDataset adaptiveSquareThreshold(IDataset input, int radius, boolean down) {
		return filterService.adaptiveSquareThreshold(input, radius, down, true);
	}

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
	 * @return output image
	 */
	public static IDataset adaptiveGaussianThreshold(IDataset input, int radius, boolean down) {
		return filterService.adaptiveGaussianThreshold(input, radius, down, true);
	}

	/**
	 * Applies Sauvola thresholding to the input image. Intended for use with text image.
	 * 
	 * @param input
	 *            Input image. Not modified.
	 * @param radius
	 *            Radius of square region.
	 * @param down
	 *            If true then the inequality <= is used, otherwise if false then >= is used.
	 * @return output image
	 */
	public static IDataset adaptiveSauvolaThreshold(IDataset input, int radius, boolean down) {
		return filterService.adaptiveSauvolaThreshold(input, radius, down, true);
	}

	/**
	 * <p>
	 * Given a binary image, connect together pixels to form blobs/clusters using the specified connectivity rule. The
	 * found blobs will be labeled in an output image and also described as a set of contours. Pixels in the contours
	 * are consecutive order in a clockwise or counter-clockwise direction, depending on the implementation.
	 * </p>
	 * <p>
	 * The returned contours are traces of the object. The trace of an object can be found by marking a point with a pen
	 * and then marking every point on the contour without removing the pen. It is possible to have the same point
	 * multiple times in the contour.
	 * </p>
	 * 
	 * @param input
	 *            Input binary image. Not modified.
	 * @param rule
	 *            Connectivity rule. Can be 4 or 8. 8 is more commonly used.
	 * @return Dataset Output labeled image.
	 * @throws Exception
	 */
	public static IDataset extractBlob(IDataset input, int rule) throws Exception {
		return filterService.extractBlob(input, rule);
	}

	/**
	 * Image cropped is a rectangle inside of the given ellipse
	 * 
	 * @param image
	 * @param roi
	 * @return IDataset
	 */
	public static IDataset maxRectangleFromEllipticalImage(IDataset image, IROI roi) {
		// TODO make it work for ellipses with non-null angle
		EllipticalROI myEllipse;
		if (roi instanceof EllipticalROI) {
			myEllipse = (EllipticalROI) roi;
		} else if (roi instanceof CircularROI) {
			myEllipse = new EllipticalROI((CircularROI)roi);
		} else {
			throw new IllegalArgumentException("This method takes either an EllipticalROI or a CircularROI");
		}
		RectangularROI boundingBox = myEllipse.getBounds();
		double width = boundingBox.getLength(0);
		double height = boundingBox.getLength(1);
		int[] center = myEllipse.getIntPoint();
		double majorSemiAxis = myEllipse.getSemiAxis(0);
		double minorSemiAxis = myEllipse.getSemiAxis(1);
		int x = center[0];
		int y = center[1];
		if (width >= height) {
			return maxRectangleFromEllipticalImage(image, majorSemiAxis * 2, minorSemiAxis *2, (int)(x - majorSemiAxis), (int)(y - minorSemiAxis));
		}
		return maxRectangleFromEllipticalImage(image, minorSemiAxis * 2, majorSemiAxis * 2, (int)(x - minorSemiAxis), (int)(y - majorSemiAxis));
	}

	/**
	 * Image cropped is a rectangle inside of the circular image if centre of ellipse is (0, 0) then (x, y) =
	 * (a/sqrt(2), b/sqrt(2)) where a = xdiameter/2 and b = ydiameter/2<br>
	 * With origin of image being top left of the image, (x, y) the top left corner of the rectangle becomes (buffer +
	 * (a * (sqrt(2)-1)/sqrt(2) , buffer + (b * (sqrt(2)-1)/sqrt(2)) and width = 2*a/sqrt(2) and height = 2*b/sqrt(2)
	 * 
	 * @param image
	 * @param xdiameter
	 * @param ydiameter
	 * @param xbuffer
	 * @param ybuffer
	 * @return IDataset
	 */
	public static IDataset maxRectangleFromEllipticalImage(IDataset image, double xdiameter, double ydiameter, int xbuffer, int ybuffer) {
		// maximum rectangle dimension
		double a = xdiameter / 2;
		double b = ydiameter / 2;
		int width = (int) (xdiameter / Math.sqrt(2));
		int height = (int) (ydiameter / Math.sqrt(2));

		// find the top left corner of the largest square within the circle
		int cornerx = (int) (xbuffer + (a * (Math.sqrt(2)-1)/Math.sqrt(2)));
		int cornery = (int) (ybuffer + (b * (Math.sqrt(2)-1)/Math.sqrt(2)));

		IDataset cropped = image.getSlice(new int[] { cornerx, cornery}, new int[] { cornerx + width, cornery + height}, null);

		return cropped;
	}
}
