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
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferUShort;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.RGBDataset;
import org.eclipse.january.metadata.Metadata;

/**
 * Helper methods to convert to/from AWT images and datasets
 */
public class AWTImageUtils {
	private AWTImageUtils() {
	}

	/**
	 * Create datasets from a Raster
	 * @param r raster
	 * @param data array to output datasets
	 * @param dtype dataset type
	 */
	static public void createDatasets(Raster r, Dataset[] data, final int dtype) {
		final int bands = data.length;
		final int height = r.getHeight();
		final int width = r.getWidth();
		Dataset tmp;

		for (int i = 0; i < bands; i++) {
			if (dtype == Dataset.FLOAT32) {
				tmp =  DatasetFactory.createFromObject(r.getSamples(0, 0, width, height, i, (float[]) null), height, width);
			} else if (dtype == Dataset.FLOAT64) {
				tmp = DatasetFactory.createFromObject(r.getSamples(0, 0, width, height, i, (double[]) null), height, width);
			} else if (dtype == Dataset.INT32) {
				tmp = DatasetFactory.createFromObject(r.getSamples(0, 0, width, height, i, (int[]) null), height, width);
			} else {
				tmp = DatasetFactory.createFromObject(dtype, r.getSamples(0, 0, width, height, i, (int[]) null), height, width);
			}
			data[i] = tmp;
		}
	}

	/**
	 * Get datasets from an image
	 * @param image
	 * @param keepBitWidth if true, then use signed primitives of same bit width for possibly unsigned data
	 * @return array of datasets
	 */
	static public Dataset[] makeDatasets(final BufferedImage image, boolean keepBitWidth) {
		// make raster from buffered image
		final Raster ras = image.getData();
		final SampleModel sm = ras.getSampleModel();
		int[] dtype = getDTypeFromImage(sm, keepBitWidth);

		final int bands = ras.getNumBands();
		Dataset[] data = new Dataset[bands];

		createDatasets(ras, data, dtype[0]);
		if (dtype[1] == 1) {
			for (int i = 0; i < bands; i++) {
				tagIntForShortDataset(data[i]);
			}
		}
		return data;
	}

	private static void tagIntForShortDataset(Dataset ret) {
		final Map<String,String> metadata = new HashMap<String, String>(1);
		metadata.put("unsigned.short.data", "true");
		ret.setMetadata(new Metadata(metadata));
	}

	static public int[] getDTypeFromImage(final SampleModel sm, boolean keepBitWidth) {
		int dbtype = sm.getDataType();
		final int bits = sm.getSampleSize(0);
		if (dbtype == DataBuffer.TYPE_INT) {
			if (bits <= 8) {
				dbtype = DataBuffer.TYPE_BYTE;
			} else if (bits <= 16) {
				dbtype = DataBuffer.TYPE_SHORT;
			}
		}
		if (dbtype == DataBuffer.TYPE_USHORT) {
			if (bits < 8) {
				dbtype = DataBuffer.TYPE_BYTE;
			} else if (bits < 16) {
				dbtype = DataBuffer.TYPE_SHORT;
			}
		}
		if (dbtype == DataBuffer.TYPE_SHORT) {
			if (bits <= 8) {
				dbtype = DataBuffer.TYPE_BYTE;
			}
		}
		int dtype = -1;
		switch (dbtype) {
		case DataBuffer.TYPE_BYTE:
			dtype = keepBitWidth ? Dataset.INT8 : Dataset.INT16;
			break;
		case DataBuffer.TYPE_SHORT:
			dtype = Dataset.INT16;
			break;
		case DataBuffer.TYPE_USHORT:
			dtype = keepBitWidth ? Dataset.INT16 : Dataset.INT32;
			break;
		case DataBuffer.TYPE_INT:
			dtype = Dataset.INT32;
			break;
		case DataBuffer.TYPE_DOUBLE:
			dtype = Dataset.FLOAT64;
			break;
		case DataBuffer.TYPE_FLOAT:
			dtype = Dataset.FLOAT32;
			break;
		}

		return new int[] {dtype, dbtype == DataBuffer.TYPE_USHORT && !keepBitWidth ? 1 : 0};
	}

	/**
	 * Get image from a dataset
	 * @param data
	 * @param bits number of bits (<=16 for non-RGB datasets)
	 * @return buffered image
	 */
	static public BufferedImage makeBufferedImage(final Dataset data, final int bits) {
		final int[] shape = data.getShape();
		if (shape.length > 2) {
			throw new IllegalArgumentException("Rank of data must be less than or equal to two");
		}

		final int height = shape[0];
		final int width = shape.length == 1 ? 1 : shape[1]; // allow 1D datasets to be saved

		final int size = data.getSize();
		BufferedImage image = null;

		if (data instanceof RGBDataset) {
			RGBDataset rgbds = (RGBDataset) data;

			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			short maxv = rgbds.max().shortValue();
			final IndexIterator iter = rgbds.getIterator(true);
			final int[] pos = iter.getPos();
			final short[] rgbdata = rgbds.getData();
			if (maxv < 256) { // 888
				while (iter.hasNext()) {
					final int n = iter.index;
					final int rgb = ((rgbdata[n] & 0xff) << 16) | ((rgbdata[n + 1] & 0xff) << 8) | (rgbdata[n + 2] & 0xff);
					image.setRGB(pos[1], pos[0], rgb);
				}			
			} else {
				int shift = 0;
				while (maxv >= 256) {
					shift++;
					maxv >>= 2;
				}

				while (iter.hasNext()) {
					final int n = iter.index;
					final int rgb = (((rgbdata[n] >> shift) & 0xff) << 16) | (((rgbdata[n + 1] >> shift) & 0xff) << 8) | ((rgbdata[n + 2] >> shift) & 0xff);
					image.setRGB(pos[1], pos[0], rgb);
				}			
			}
		} else {
			DataBuffer buffer = null;
			SampleModel sampleModel = null;
			// reconcile data with output format

			// populate data buffer using sample model
			IntegerDataset tmp = (IntegerDataset) DatasetUtils.cast(data, Dataset.INT32);

			if (bits <= 8) {
				buffer = new DataBufferByte(size);
				sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_BYTE, width, height, 1, width,
						new int[] { 0 });
				image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

				sampleModel.setPixels(0, 0, width, height, tmp.getData(), buffer);
			} else if (bits <= 16) {
				buffer = new DataBufferUShort(size);
				sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_USHORT, width, height, 1,
						width, new int[] { 0 });
				image = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY);

				sampleModel.setPixels(0, 0, width, height, tmp.getData(), buffer);
			} else {
				throw new IllegalArgumentException("Number of bits must be less than or equal to 16");
			}

			WritableRaster wRas = Raster.createWritableRaster(sampleModel, buffer, null);
			image.setData(wRas);
		}

		return image;
	}
	
}
