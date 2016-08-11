/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */ 
package org.eclipse.dawnsci.remotedataset.test.mock;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.dawnsci.plotting.api.histogram.HistogramBound;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.IPaletteService;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.HistoType;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.ImageOrigin;
import org.eclipse.dawnsci.plotting.api.histogram.functions.FunctionContainer;
import org.eclipse.dawnsci.plotting.api.preferences.BasePlottingConstants;
import org.eclipse.january.dataset.BooleanDataset;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.Maths;
import org.eclipse.january.dataset.RGBDataset;
import org.eclipse.january.dataset.Stats;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

/**
 * 
 
   Histogramming Explanation
   ---------------------------
   Image intensity distribution:

                ++----------------------**---------------
                +                      *+ *              
                ++                    *    *             
                |                     *    *             
                ++                    *     *            
                *                    *       *            
                +*                   *       *            
                |*                  *        *            
                +*                  *        *           
                |                  *          *         
                ++                 *          *          
                |                  *           *        
                ++                 *           *        
                |                 *            *        
                ++                *            *       
                                 *              *      
        Min Cut           Min    *              *      Max                     Max cut
 Red <- |   (min colour)  |    (color range, palette)  |      (max color)      | -> Blue
                                *                 *  
                |              *        +         *  
----------------++------------**---------+----------**----+---------------**+---------------++

 
 * @author Matthew Gerring
 *
 */
public class ImageServiceMock extends AbstractServiceFactory implements IImageService {
	

	static {
		System.out.println("Starting image service");
	}
	public ImageServiceMock() {
		// Important do nothing here, OSGI may start the service more than once.
	}
	
	/**
	 * This method is not thread safe
	 */
	public Image getImage(ImageServiceBean bean) {
		final ImageData data = getImageData(bean);
		return new Image(Display.getCurrent(), data);
	}
	
	private static final int MIN_PIX_INDEX = 253;
	private static final int NAN_PIX_INDEX = 254;
	private static final int MAX_PIX_INDEX = 255;
	
	private static final byte MIN_PIX_BYTE = (byte)(MIN_PIX_INDEX & 0xFF);
	private static final byte NAN_PIX_BYTE = (byte)(NAN_PIX_INDEX & 0xFF);
	private static final byte MAX_PIX_BYTE = (byte)(MAX_PIX_INDEX & 0xFF);
	
	/**
	 * getImageData(...) provides an image in a given palette data and origin.
	 * Faster than getting a resolved image
	 * 
	 * This method should be thread safe.
	 */
	public ImageData getImageData(ImageServiceBean bean) {
		Dataset oImage    = DatasetUtils.convertToDataset(bean.getImage());
		Dataset image    = oImage;
		ImageOrigin     origin   = bean.getOrigin();
		if (origin==null) origin = ImageOrigin.TOP_LEFT;
		PaletteData     palette  = bean.getPalette();

		if (image instanceof RGBDataset) {
			switch (origin) {
			case TOP_LEFT:
				break;
			case TOP_RIGHT:
				image = DatasetUtils.transpose(image);
				image = image.getSlice(null, null, new int[] {1,-1});
				break;
			case BOTTOM_LEFT:
				image = DatasetUtils.transpose(image);
				image = image.getSlice(null, null, new int[] {-1,1});
				break;
			case BOTTOM_RIGHT:
				image = image.getSlice(null, null, new int[] {-1,-1});
				break;
			}
			RGBDataset rgbImage = (RGBDataset) image;
			return SWTImageUtils.createImageData(rgbImage, 0, 255, null, null, null, false, false, false);
		}

		int depth = bean.getDepth();
		final int size  = (int)Math.round(Math.pow(2, depth));

		createMaxMin(bean);
		double max = getMax(bean);
		double min = getMin(bean);

		double maxCut = getMaxCut(bean);
		double minCut = getMinCut(bean);

		// now deal with the log if needed
		if (bean.isLogColorScale()) {
			image = getImageLoggedData(bean);
			max = Math.log10(max);
			// note createMaxMin() -> getFastStatistics() -> getImageLogged() which ensures min >= 0 
			min = Math.log10(min);
			maxCut = Math.log10(maxCut);
			// no guarantees for minCut though
			minCut = minCut <= 0 ? Double.NEGATIVE_INFINITY : Math.log10(minCut);
		}

		if (oImage.isComplex()) { // handle complex datasets by creating RGB dataset
			Dataset hue = Maths.angle(oImage, true);
			Dataset value = getImageLoggedData(bean);
			double maxmax = Math.max(Math.abs(max), Math.abs(min));
			if (max - min > Math.ulp(maxmax)) {
				value.isubtract(min);
				value.imultiply(1./(max - min));
			} else {
				value.imultiply(1./maxmax);
			}
			image = RGBDataset.createFromHSV(hue, null, value);
			return SWTImageUtils.createImageData(image, 0, 255, null, null, null, false, false, false);
		}

		if (bean.getFunctionObject()!=null && bean.getFunctionObject() instanceof FunctionContainer) {
			final FunctionContainer fc = (FunctionContainer)bean.getFunctionObject();
			// TODO This does not support masking or cut bounds for zingers and dead pixels.
			return SWTImageUtils.createImageData(image, min, max, fc.getRedFunc(), 
																  fc.getGreenFunc(), 
																  fc.getBlueFunc(), 
																  fc.isInverseRed(), 
																  fc.isInverseGreen(), 
																  fc.isInverseBlue());
		}

		if (depth>8) { // Depth > 8 will not work properly at the moment.
			throw new RuntimeException(getClass().getSimpleName()+" only supports 8-bit images unless a FunctionContainer has been set!");
			//if (depth == 16) palette = new PaletteData(0x7C00, 0x3E0, 0x1F);
			//if (depth == 24) palette = new PaletteData(0xFF, 0xFF00, 0xFF0000);
			//if (depth == 32) palette = new PaletteData(0xFF00, 0xFF0000, 0xFF000000);
		}
		
		final int[]   shape = image.getShape();
		if (bean.isCancelled()) return null;	
				
		int len = image.getSize();
		if (len == 0) return null;

		// The last three indices of the palette are always taken up with bound colours
		createCutColours(bean); // Modifies the palette data and sets the withheld indices
		
		double scale;
		double maxPixel;
		if (max > min) {
			// 4 because 1 less than size and then 1 for each bound colour is lost.
			scale = (size - 4) / (max - min);
			maxPixel = max - min;
		} else {
			scale = 1;
			maxPixel = 0xFF;
		}
		if (bean.isCancelled()) return null;
		
		BooleanDataset mask = bean.getMask()!=null
							? (BooleanDataset)DatasetUtils.cast(bean.getMask(), Dataset.BOOL)
							: null;

		ImageData imageData = null;

		// We use a byte array directly as this is faster than using setPixel(...)
		// on image data. Set pixel does extra floating point operations. The downside
		// is that by doing this we certainly have to have 8 bit as getPixelColorIndex(...)
		// forces the use of on byte.
		final byte[] scaledImageAsByte = new byte[len];

		if (origin==ImageOrigin.TOP_LEFT) { 
			
			int index = 0;
			// This loop is usually the same as the image is read in but not always depending on loader.
			for (int i = 0; i<shape[0]; ++i) {
				if (bean.isCancelled()) return null;				
				for (int j = 0; j<shape[1]; ++j) {
					
					// This saves a value lookup when the pixel is certainly masked.
					scaledImageAsByte[index] = mask==null || mask.getBoolean(i,j)
									? getPixelColorIndex(image.getDouble(i,j), min, max, scale, maxPixel, minCut, maxCut)
									: NAN_PIX_BYTE;
					++index;
				}
			}
			imageData = new ImageData(shape[1], shape[0], 8, palette, 1, scaledImageAsByte);
	
		} else if (origin==ImageOrigin.BOTTOM_LEFT) {

			int index = 0;
			// This loop is slower than looping over all data and using image.getElementDoubleAbs(...)
			// However it reorders data for the axes
			for (int i = shape[1]-1; i>=0; --i) {
				if (bean.isCancelled()) return null;
				for (int j = 0; j<shape[0]; ++j) {
					
					// This saves a value lookup when the pixel is certainly masked.
					scaledImageAsByte[index]  = mask==null || mask.getBoolean(j,i)
									? getPixelColorIndex(image.getDouble(j,i), min, max, scale, maxPixel, minCut, maxCut)
									: NAN_PIX_BYTE;
					index++;
				}
			}
			imageData = new ImageData(shape[0], shape[1], 8, palette, 1, scaledImageAsByte);
			
		} else if (origin==ImageOrigin.BOTTOM_RIGHT) {

			int index = 0;
			// This loop is slower than looping over all data and using image.getElementDoubleAbs(...)
			// However it reorders data for the axes
			for (int i = shape[0]-1; i>=0; --i) {
				if (bean.isCancelled()) return null;
				for (int j = shape[1]-1; j>=0; --j) {

					// This saves a value lookup when the pixel is certainly masked.
					scaledImageAsByte[index] = mask==null || mask.getBoolean(i,j)
									? getPixelColorIndex(image.getDouble(i,j), min, max, scale, maxPixel, minCut, maxCut)
									: NAN_PIX_BYTE;
						index++;
				}
			}
			imageData = new ImageData(shape[1], shape[0], 8, palette, 1, scaledImageAsByte);
			
		} else if (origin==ImageOrigin.TOP_RIGHT) {

			int index = 0;
			// This loop is slower than looping over all data and using image.getElementDoubleAbs(...)
			// However it reorders data for the axes
			for (int i = 0; i<shape[1]; ++i) {
				if (bean.isCancelled()) return null;
				for (int j = shape[0]-1; j>=0; --j) {
					scaledImageAsByte[index]  = mask==null || mask.getBoolean(j,i)
									? getPixelColorIndex(image.getDouble(j, i), min, max, scale, maxPixel, minCut, maxCut)
									: NAN_PIX_BYTE;
					index++;
				}
			}
			imageData = new ImageData(shape[0], shape[1], 8, palette, 1, scaledImageAsByte);
		}

		imageData.alpha = bean.getAlpha();
		return imageData;
	}

	private double getMax(ImageServiceBean bean) {
		if (bean.getMaximumCutBound()==null || bean.getMaximumCutBound().getBound()==null) {
			return bean.getMax().doubleValue();
		}
		return Math.min(bean.getMax().doubleValue(), bean.getMaximumCutBound().getBound().doubleValue());
	}
	
	private double getMin(ImageServiceBean bean) {
		if (bean.getMinimumCutBound()==null || bean.getMinimumCutBound().getBound()==null) {
			return bean.getMin().doubleValue();
		}
		return Math.max(bean.getMin().doubleValue(), bean.getMinimumCutBound().getBound().doubleValue());
	}
	
	private double getMaxCut(ImageServiceBean bean) {
		if (bean.getMaximumCutBound()==null || bean.getMaximumCutBound().getBound()==null) {
			return Double.POSITIVE_INFINITY;
		}
		return bean.getMaximumCutBound().getBound().doubleValue();
	}
	
	private double getMinCut(ImageServiceBean bean) {
		if (bean.getMinimumCutBound()==null || bean.getMinimumCutBound().getBound()==null) {
			return Double.NEGATIVE_INFINITY;
		}
		return bean.getMinimumCutBound().getBound().doubleValue();
	}

	/**
	 * Calling this wipes out the last three RGBs. Even if you set max
	 * @param bean
	 */
	private void createCutColours(ImageServiceBean bean) {
		
		// We *DO NOT* copy the palette here so up to 3 of the original
		// colours can be changed. Instead whenever a palette is given to an
		// ImageService bean it should be original.
		if (bean.getPalette()==null) {
			try {
				final IPaletteService service = (IPaletteService)PlatformUI.getWorkbench().getService(IPaletteService.class);
				bean.setPalette(service.getDirectPaletteData("Gray Scale"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		// We have three special values, those which are greater than the max cut,
		// less than the min cut and the NaN number. For these we use special pixel
		// values in the palette as defined by the cut bound if it is set.
		if (bean.getMinimumCutBound()!=null && bean.getMinimumCutBound().getColor()!=null) {
			int[] ia = bean.getMinimumCutBound().getColor();
			bean.getPalette().colors[MIN_PIX_INDEX] = new RGB(ia[0], ia[1], ia[2]);
		}
		
		if (bean.getNanBound()!=null && bean.getNanBound().getColor()!=null) {
			int[] ia = bean.getNanBound().getColor();
			bean.getPalette().colors[NAN_PIX_INDEX] = new RGB(ia[0], ia[1], ia[2]);
		}
		
		if (bean.getMaximumCutBound()!=null && bean.getMaximumCutBound().getColor()!=null) {
			int[] ia = bean.getMaximumCutBound().getColor();
			bean.getPalette().colors[MAX_PIX_INDEX] = new RGB(ia[0], ia[1], ia[2]);
		}
		
	}

	private void createMaxMin(ImageServiceBean bean) {
		
		double[] stats  = null;
		if (bean.getMin()==null) {
			if (stats==null) stats = getFastStatistics(bean); // do not get unless have to
			bean.setMin(stats[0]);
		}
		
		if (bean.getMax()==null) {
			if (stats==null) stats = getFastStatistics(bean); // do not get unless have to
		    bean.setMax(stats[1]);
		}		
	}

	/**
	 * private finals inline well by the compiler.
	 * @param val
	 * @param min
	 * @param max
	 * @param scale
	 * @param maxPixel
	 * @param scaledImageAsByte
	 */
	private final static byte getPixelColorIndex(final double  val, 
												 final double  min, 
												 final double  max, 
												 final double  scale, 
												 final double  maxPixel,
												 final double  minCut,
												 final double  maxCut) {

		// Deal with bounds
		if (Double.isNaN(val)) return NAN_PIX_BYTE;

		if (val<=minCut) return MIN_PIX_BYTE;
		if (val>=maxCut) return MAX_PIX_BYTE;

		// If the pixel is within the bounds
		double scaled_pixel;
		if (val < min) {
			scaled_pixel = 0;
		} else if (val >= max) {
			scaled_pixel = maxPixel;
		} else {
			scaled_pixel = val - min;
		}
		scaled_pixel = scaled_pixel * scale;

		return (byte) (0x000000FF & ((int) scaled_pixel));
	}

	/**
	 * Get the logged image value and cache the result.
	 * 
	 * @param bean
	 * @return a dataset that can be absolute, if complex, and also be logged according to bean
	 * Package private for testing
	 */
	/* package */ Dataset getImageLoggedData(ImageServiceBean bean) {
		Dataset ret = DatasetUtils.convertToDataset(bean.getImageValue());
		if (ret == null) {
			ret = getImageLoggedDataCalc(bean);
			bean.setImageValue(ret);
		}
		return ret;
	}
	/**
	 * Get the logged image value.
	 * 
	 * @param bean
	 * @return a dataset that can be absolute, if complex, and also be logged according to bean
	 * Package private for testing
	 */
	/* package */ Dataset getImageLoggedDataCalc(ImageServiceBean bean) {
		Dataset ret = DatasetUtils.convertToDataset(bean.getImage());

		if (ret.isComplex()) {
			ret = Maths.abs(ret);
		}
		if (bean.isLogColorScale()) {
			double offset = bean.getLogOffset();
			if (!Double.isNaN(offset) &&
				!Double.isInfinite(offset)) {
				ret = Maths.subtract(ret, offset);
			}
			ret = Maths.log10(ret);
		}
		return ret;
	}

	/**
	 * Fast statistics as a rough guide - this is faster than Dataset.getMin()
	 * and getMax() which may cache but slows the opening of images too much.
	 * The return array[2] was added in "Updated for Diffraction Tool." commit,
	 * but no trace of such usage. However it should not be removed, because
	 * it is useful as return array[3].
	 * 
	 * @param bean
	 * @return [0] = min [1] = max(=mean*constant) [2] = mean [3] max
	 */
	public double[] getFastStatistics(ImageServiceBean bean) {
		
		Dataset image    = getImageLoggedData(bean);
		
		if (bean.getHistogramType()==HistoType.OUTLIER_VALUES && !bean.isLogColorScale()) {

			double[] ret = null;
			try {
			    double[] stats = Stats.outlierValues(image, bean.getLo(), bean.getHi(), -1);
			    ret = new double[]{stats[0], stats[1], -1};
			} catch (IllegalArgumentException iae) {
				bean.setLo(10);
				bean.setHi(90);
			    double[] stats = Stats.outlierValues(image, bean.getLo(), bean.getHi(), -1);
			    ret = new double[]{stats[0], stats[1], -1};
			}

		    if (bean.isLogColorScale() && ret!=null) {
		    	ret = new double[]{Math.pow(10, ret[0]), Math.pow(10, ret[1]), -1};
			}

			return ret;
		}
		
		double min = Double.MAX_VALUE;
		double max = -Double.MAX_VALUE;
		double sum = 0.0;
		int size = 0;
		
		BooleanDataset mask = bean.getMask()!=null
	                        ? (BooleanDataset) DatasetUtils.cast(bean.getMask(), Dataset.BOOL)
	                        : null;

	    // Big loop warning:
	    final IndexIterator it = image.getIterator();
	    final IndexIterator mit = mask == null ? null : mask.getIterator();
		while (it.hasNext()) {
			
			final double val = image.getElementDoubleAbs(it.index);
			if (mit != null && mit.hasNext()) {
				if (!mask.getElementBooleanAbs(mit.index)) {
					continue; // Masked!
			    }
			}

			if (Double.isNaN(val))      continue;
			if (!bean.isInBounds(val))  continue;

			sum += val;
			if (val < min) min = val;
			if (val > max) max = val;
			size++;
		}
		
		double retMax = Double.NaN;
		double retExtra = Double.NaN;
		
		if (bean.getHistogramType()==HistoType.MEDIAN) { 
			
			double median = Double.NaN;
			try {
				median = ((Number)Stats.median(image)).doubleValue(); // SLOW
			} catch (Exception ne) {
				median = ((Number)Stats.median(image.cast(Dataset.INT16))).doubleValue();// SLOWER
			}
			retMax = 2 * median;
			retExtra=median;
			
		} else { // Use mean based histo
			double mean = sum / size;
			retMax = (Math.E)*mean; // Not statistical, E seems to be better than 3...
			retExtra=mean;

		}
		
		if (retMax > max) retMax = max;
		
		if (bean.isLogColorScale()) {
			return new double[]{Math.pow(10, min), Math.pow(10, retMax), Math.pow(10, retExtra)};
		}

		return new double[]{min, retMax, retExtra, max};
	}

	@Override
	public Object create(@SuppressWarnings("rawtypes") Class serviceInterface, IServiceLocator parentLocator, IServiceLocator locator) {
		
		if (serviceInterface==IImageService.class) {
			return new ImageServiceMock();
		} 
		return null;
	}
	
	public static final class SDAFunctionBean {
		
	}

	
	/**
	 * Converts an SWT ImageData to an AWT BufferedImage.
	 * 
	 * @param bufferedImage
	 * @return
	 */
	@Override
	public BufferedImage getBufferedImage(ImageData data) {
		ColorModel colorModel = null;
		PaletteData palette = data.palette;
		if (palette.isDirect) {
			colorModel = new DirectColorModel(data.depth, palette.redMask,
					palette.greenMask, palette.blueMask);
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width,
							data.height), false, null);
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					RGB rgb = palette.getRGB(pixel);
					bufferedImage.setRGB(x, y, rgb.red << 16 | rgb.green << 8
							| rgb.blue);
				}
			}
			return bufferedImage;
		} else {
			RGB[] rgbs = palette.getRGBs();
			byte[] red = new byte[rgbs.length];
			byte[] green = new byte[rgbs.length];
			byte[] blue = new byte[rgbs.length];
			for (int i = 0; i < rgbs.length; i++) {
				RGB rgb = rgbs[i];
				red[i] = (byte) rgb.red;
				green[i] = (byte) rgb.green;
				blue[i] = (byte) rgb.blue;
			}
			if (data.transparentPixel != -1) {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red,
						green, blue, data.transparentPixel);
			} else {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red,
						green, blue);
			}
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width,
							data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					pixelArray[0] = pixel;
					raster.setPixel(x, y, pixelArray);
				}
			}
			return bufferedImage;
		}
	}

	@Override
	public ImageServiceBean createBeanFromPreferences() {
		
		ImageServiceBean imageServiceBean = new ImageServiceBean();
		
		if (Platform.getPreferencesService() != null) { // Normally
			IPreferenceStore store            = new ScopedPreferenceStore(InstanceScope.INSTANCE, "org.dawnsci.plotting");
			imageServiceBean.setOrigin(ImageOrigin.forLabel(store.getString(BasePlottingConstants.ORIGIN_PREF)));
			imageServiceBean.setHistogramType(HistoType.forLabel(store.getString(BasePlottingConstants.HISTO_PREF)));
			imageServiceBean.setMinimumCutBound(HistogramBound.fromString(store.getString(BasePlottingConstants.MIN_CUT)));
			imageServiceBean.setMaximumCutBound(HistogramBound.fromString(store.getString(BasePlottingConstants.MAX_CUT)));
			imageServiceBean.setNanBound(HistogramBound.fromString(store.getString(BasePlottingConstants.NAN_CUT)));
			imageServiceBean.setLo(store.getDouble(BasePlottingConstants.HISTO_LO));
			imageServiceBean.setHi(store.getDouble(BasePlottingConstants.HISTO_HI));		
			
			try {
				
				imageServiceBean.setPalette(makeJetPalette());

			} catch (Exception e) {
				// Ignored
			}
			
		} else { // Hard code something
			
			imageServiceBean.setOrigin(ImageOrigin.TOP_LEFT);
			imageServiceBean.setHistogramType(HistoType.OUTLIER_VALUES);
			imageServiceBean.setMinimumCutBound(HistogramBound.DEFAULT_MINIMUM);
			imageServiceBean.setMaximumCutBound(HistogramBound.DEFAULT_MAXIMUM);
			imageServiceBean.setNanBound(HistogramBound.DEFAULT_NAN);
			imageServiceBean.setLo(00.01);
			imageServiceBean.setHi(99.99);		
			imageServiceBean.setPalette(makeJetPalette());
		}
	
		return imageServiceBean;
	}

	
    static PaletteData makeJetPalette() {
		RGB jet[] = new RGB[256];
		
		int nb = 256;

		for (int i = 0; i < nb; i++) {
			
			double value = (double)i/(double)255;

			double outBlue = 0;
			if (value <= 0.1) {outBlue  =  5*value + 0.5;}
			if (value > 0.1 && value <= 1.0/3.0 ) {outBlue  =  1;}
			if (value >1.0/3.0 && value <= 1.0/2.0) {outBlue  =  -6*value +3;}
			
			double outGreen = 0;
			if (value > 1.0/3.0 && value < 2.0/3.0  ) {outGreen = 1;}
			if (value <= 1.0/3.0 && value >= 1.0/8.0) {outGreen = 24.0/5*value - 0.6;}
			if (value >= 2.0/3.0 && value <= 7.0/8.0) {outGreen = -24.0/5*value + 4.2;}
			
			double outRed = 0;
			if (value >= 0.9) {outRed = -5*value +5.5;}
			if (value > 2.0/3.0 && value <= 0.9 ) {outRed = 1;}
			if (value >=1.0/2.0 && value <= 2.0/3.0 ) {outRed = 6*value -3;}
			
			jet[i] = new RGB((int)(outRed*255),
					(int)(outGreen*255),
					(int)(outBlue*255));

		}
		return new PaletteData(jet);
	}
	

}
