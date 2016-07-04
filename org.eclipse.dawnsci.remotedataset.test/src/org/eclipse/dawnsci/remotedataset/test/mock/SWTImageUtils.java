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
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.IndexIterator;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.ITransferFunction;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

/**
 * Helper methods to convert to SWT images from datasets
 */
class SWTImageUtils {

	static private ImageData createImageFromRGBADataset(RGBDataset rgbdata, long minv, long maxv)
	{
		ImageData img;
		final IndexIterator iter = rgbdata.getIterator(true);
		final int[] pos = iter.getPos();
		final int[] shape = rgbdata.getShape();
		final int height = shape[0];
		final int width = shape.length == 1 ? 1 : shape[1]; // allow 1D datasets to be saved
		long delta = maxv - minv;
		short off = (short) minv;

		short[] data = rgbdata.getData();
		if (delta < 32) { // 555
			img = new ImageData(width, height, 16, new PaletteData(0x7c00, 0x03e0, 0x001f));
			while (iter.hasNext()) {
				final int n = iter.index;
				final int rgb = (((data[n] - off) & 0x1f) << 10) | (((data[n + 1] - off) & 0x1f) << 5) | ((data[n + 2] - off)& 0x1f);
				img.setPixel(pos[1], pos[0], rgb);
			}
		} else if (delta < 64) { // 565
			img = new ImageData(width, height, 16, new PaletteData(0xf800, 0x07e0, 0x001f));

			while (iter.hasNext()) {
				final int n = iter.index;
				final int rgb = ((((data[n] - off) >> 1) & 0x1f) << 10) | (((data[n + 1] - off) & 0x3f) << 5) | (((data[n + 2] - off) >> 1) & 0x1f);
				img.setPixel(pos[1], pos[0], rgb);
			}
		} else if (delta < 256) { // 888
			img = new ImageData(width, height, 24, new PaletteData(0xff0000, 0x00ff00, 0x0000ff));

			while (iter.hasNext()) {
				final int n = iter.index;
				final int rgb = (((data[n] - off) & 0xff) << 16) | (((data[n + 1] - off) & 0xff) << 8) | ((data[n + 2] - off) & 0xff);
				img.setPixel(pos[1], pos[0], rgb);
			}
		} else {
			int shift = 0;
			while (delta >= 256) {
				shift++;
				delta >>= 1;
			}

			img = new ImageData(width, height, 24, new PaletteData(0xff0000, 0x00ff00, 0x0000ff));

			while (iter.hasNext()) {
				final int n = iter.index;
				final int rgb = ((((data[n] - off) >> shift) & 0xff) << 16) | ((((data[n + 1] - off) >> shift) & 0xff) << 8) | (((data[n + 2] - off) >> shift) & 0xff);
				img.setPixel(pos[1], pos[0], rgb);
			}
		}
		return img;
	}
	
	static private ImageData createImageFromDataset(Dataset a,
													double minv,
													double maxv,
													ITransferFunction redFunc,
													ITransferFunction greenFunc,
													ITransferFunction blueFunc,
													boolean inverseRed,
													boolean inverseGreen,
													boolean inverseBlue)  {
		final int[] shape = a.getShape();
		final int height = shape[0];
		final int width = shape.length == 1 ? 1 : shape[1]; // allow 1D datasets to be saved
		ImageData img;
		final IndexIterator iter = a.getIterator(true);
		final int[] pos = iter.getPos();
		img = new ImageData(width, height, 24, new PaletteData(0xff0000, 0x00ff00, 0x0000ff));
		double delta = maxv - minv;
		while (iter.hasNext()) {
			double value = (a.getElementDoubleAbs(iter.index) - minv)/delta;
			final int red = (inverseRed ? (255-redFunc.mapToByte(value)) : redFunc.mapToByte(value));
			final int green = (inverseGreen ? (255-greenFunc.mapToByte(value)) : greenFunc.mapToByte(value));
			final int blue = (inverseBlue ? (255-blueFunc.mapToByte(value)) : blueFunc.mapToByte(value));
			final int rgb = (red << 16) | green << 8 | blue; 		
			img.setPixel(pos[1], pos[0],rgb); 
		}
		return img;
	}

	static private ImageData createImageFromDataset(Dataset a, PaletteData paletteData) throws Exception {
		final IImageService iservice = (IImageService)PlatformUI.getWorkbench().getService(IImageService.class);
		ImageServiceBean ibean = new ImageServiceBean();
		ibean.setImage(a);
		ibean.setPalette(paletteData);
		return iservice.getImageData(ibean);
	}

	/**
	 * Create SWT ImageData from a dataset
	 * <p>
	 * The input dataset can be a RGB dataset in which case the mapping functions
	 * and inversion flags are ignored.
	 * @param a dataset
	 * @param max maximum value of dataset
	 * @param redFunc
	 * @param greenFunc
	 * @param blueFunc
	 * @param inverseRed
	 * @param inverseGreen
	 * @param inverseBlue
	 * @return an ImageData object for SWT
	 */
	static public ImageData createImageData(Dataset a, Number max,
											ITransferFunction redFunc,
											ITransferFunction greenFunc,
											ITransferFunction blueFunc,
											boolean inverseRed,
											boolean inverseGreen,
											boolean inverseBlue) {
		return createImageData(a, 0, max, redFunc, greenFunc, blueFunc, inverseRed, inverseGreen, inverseBlue);
	}

	/**
	 * Create SWT ImageData from a dataset
	 * <p>
	 * The input dataset can be a RGB dataset in which case the mapping functions
	 * and inversion flags are ignored.
	 * @param a dataset
	 * @param min minimum value of dataset
	 * @param max maximum value of dataset
	 * @param redFunc
	 * @param greenFunc
	 * @param blueFunc
	 * @param inverseRed
	 * @param inverseGreen
	 * @param inverseBlue
	 * @return an ImageData object for SWT
	 */
	static public ImageData createImageData(Dataset a, Number min, Number max,
			                                ITransferFunction redFunc,
											ITransferFunction greenFunc,
											ITransferFunction blueFunc,
											boolean inverseRed,
											boolean inverseGreen,
											boolean inverseBlue) {
		ImageData img;

		if (a instanceof RGBDataset) {
			img = createImageFromRGBADataset((RGBDataset)a, min.longValue(), max.longValue());
		} else {
			img = createImageFromDataset(a, min.doubleValue(), max.doubleValue(),redFunc,greenFunc,blueFunc,
										 inverseRed,inverseGreen,inverseBlue);
		}

		return img;
	}

	/**
	 * Create SWT ImageData from a dataset given a palette data
	 * <p>
	 * The input dataset can be a RGB dataset in which case the mapping functions
	 * and inversion flags are ignored.
	 * @param a dataset
	 * @param min minimum value of dataset
	 * @param max maximum value of dataset
	 * @param paletteData
	 * @return an ImageData object for SWT
	 * @throws Exception
	 */
	static public ImageData createImageData(Dataset a, Number min, Number max,
											PaletteData paletteData) throws Exception {
		ImageData img;
		if (a instanceof RGBDataset) {
			img = createImageFromRGBADataset((RGBDataset)a, min.longValue(), max.longValue());
		} else {
			img = createImageFromDataset(a, paletteData);
		}
		return img;
	}

	/**
	 * Create RGB dataset from an SWT image
	 * @param image
	 * @return a RGB dataset
	 */
	static public RGBDataset createRGBDataset(final ImageData image) {
		final int[] data = new int[image.width];
		final RGBDataset rgb = DatasetFactory.zeros(RGBDataset.class, image.height, image.width);
		final short[] p = new short[3];
		final PaletteData palette = image.palette;
		if (palette.isDirect) {
			for (int i = 0; i < image.height; i++) {
				image.getPixels(0, i, image.width, data, 0);
				for (int j = 0; j < image.width; j++) {
					int value = data[j];
					p[0] = palette.redShift >= 0 ? (short) ((value & palette.redMask) << palette.redShift) :
						(short) ((value & palette.redMask) >>> -palette.redShift);
					p[1] = palette.greenShift >= 0 ? (short) ((value & palette.greenMask) << palette.greenShift) :
						(short) ((value & palette.greenMask) >>> -palette.greenShift);
					p[2] = palette.blueShift >= 0 ? (short) ((value & palette.blueMask) << palette.blueShift) :
						(short) ((value & palette.blueMask) >>> -palette.blueShift);
					rgb.setItem(p, i, j);
				}
			}
		} else {
			final RGB[] table = palette.getRGBs();
			for (int i = 0; i < image.height; i++) {
				image.getPixels(0, i, image.width, data, 0);
				for (int j = 0; j < image.width; j++) {
					RGB value = table[data[j]];
					p[0] = (short) value.red;
					p[1] = (short) value.green;
					p[2] = (short) value.blue;
					rgb.setItem(p, i, j);
				}
			}
		}

		return rgb;
	}
    static RGBDataset convertToRGBDataset(BufferedImage bufferedImage) {
    	
        RGBDataset data = DatasetFactory.zeros(RGBDataset.class, bufferedImage.getHeight(), bufferedImage.getWidth());
       
        if (bufferedImage.getColorModel() instanceof DirectColorModel) {
            DirectColorModel colorModel = (DirectColorModel)bufferedImage.getColorModel();
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    for (int x = 0; x < bufferedImage.getWidth(); x++) {
                            int value = bufferedImage.getRGB(x, y);
                            RGB rgb = new RGB((value >> 16) & 0xFF, (value >> 8) & 0xFF, value & 0xFF); 
                            data.set(new short[]{(short)rgb.red, (short)rgb.green, (short)rgb.blue}, y, x);
                            if (colorModel.hasAlpha()) {
                            	// TODO
                                    //data.set(x, y, (rgb >> 24) & 0xFF);
                            }
                    }
            }
            return data;
            
        } else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
        	
            IndexColorModel colorModel = (IndexColorModel)bufferedImage.getColorModel();
            int size = colorModel.getMapSize();
            byte[] reds = new byte[size];
            byte[] greens = new byte[size];
            byte[] blues = new byte[size];
            colorModel.getReds(reds);
            colorModel.getGreens(greens);
            colorModel.getBlues(blues);
            RGB[] rgbs = new RGB[size];
            for (int i = 0; i < rgbs.length; i++) {
                    rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
            }
            PaletteData palette = new PaletteData(rgbs);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[1];
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
            	for (int x = 0; x < bufferedImage.getWidth(); x++) {
            		raster.getPixel(x, y, pixelArray);
            		RGB rgb = palette.getRGB(pixelArray[0]);
            		data.set(new short[]{(short)rgb.red, (short)rgb.green, (short)rgb.blue}, y, x);
            	}
            }
        } else {

        	WritableRaster raster = bufferedImage.getRaster();
        	int[] pixelArray = new int[3];
        	for (int y = 0; y < bufferedImage.getHeight(); y++) {
        		for (int x = 0; x < bufferedImage.getWidth(); x++) {
        			raster.getPixel(x, y, pixelArray);
        			data.set(new short[]{
        					(short)pixelArray[0], 
        					(short)pixelArray[1], 
        					(short)pixelArray[2]},
        					y, x);
        		}
        	}  
        }
       return data;
    }

}
