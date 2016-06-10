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
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;

/**
 * This class loads a TIFF image file
 */
public class MockImageLoader extends MockJavaImageLoader {

	protected Map<String, Serializable> metadataMap = null;
	private boolean loadData = true;
	private int height = -1;
	private int width = -1;
	
	public MockImageLoader() {
		this(null, false);
	}
	
	/**
	 * @param FileName
	 */
	public MockImageLoader(String FileName) {
		this(FileName, false);
	}

	/**
	 * @param FileName
	 * @param convertToGrey
	 */
	public MockImageLoader(String FileName, boolean convertToGrey) {
		super(FileName, "tiff", convertToGrey);
	}

	/**
	 * @param FileName
	 * @param convertToGrey
	 * @param keepBitWidth
	 */
	public MockImageLoader(String FileName, boolean convertToGrey, boolean keepBitWidth) {
		super(FileName, "tiff", convertToGrey, keepBitWidth);
	}

	@Override
	public IDataHolder loadFile() throws ScanFileHolderException {
		File f = null;

		// Check for file
		f = new File(fileName);
		if (!f.exists()) {
			logger.warn("File, {}, did not exist. Now trying to replace suffix", fileName);
			f = findCorrectSuffix();
		}

		// TODO cope with multiple images (tiff)
		IDataHolder output = new MockDataHolder(null, fileName);
		ImageReader reader = null;
		try {
			int count = 0;
			try {
				BufferedImage image = ImageIO.read(f);
				IDataset set = SWTImageUtils.convertToRGBDataset(image);
				set.setName("image");
				output.addDataset("image", set);
			} catch (Exception ne) {
				if (count>10) throw ne;
				count++;
			}
		} catch (IllegalArgumentException e) {
			throw new ScanFileHolderException("IllegalArgumentException interpreting file '" + fileName + "'", e);
		} catch (NullPointerException e) {
			throw new ScanFileHolderException("NullPointerException interpreting file '" + fileName + "'", e);
		} catch (Exception other) {
			System.out.println("> Cannot read "+fileName);
			other.printStackTrace();
			throw new ScanFileHolderException("> Cannot read "+fileName, other);
		} finally {
			if (reader != null)
				reader.dispose();
		}

		if (!loadData) {
			return null;
		}

		return output;
	}

	
	private void readImages(IDataHolder output, ImageReader reader) throws IOException, ScanFileHolderException {
		int n = reader.getNumImages(true);

		if (n == 0) {
			return;
		}

		if (!loadData)
			return;

		boolean allSame = true;
		if (height < 0 || width < 0) {
			height = reader.getHeight(0); // this can throw NPE when using 12-bit reader
			width = reader.getWidth(0);
			for (int i = 1; i < n; i++) {
				if (height != reader.getHeight(i) || width != reader.getWidth(i)) {
					allSame = false;
					break;
				}
			}
		}

		final ImageTypeSpecifier its = reader.getRawImageType(0); // this raises an exception for 12-bit images when using standard reader
		if (allSame) {
			for (int i = 1; i < n; i++) {
				if (!its.equals(reader.getRawImageType(i))) {
					throw new ScanFileHolderException("Type of image in stack does not match first");
				}
			}
		}
		int dtype = AWTImageUtils.getDTypeFromImage(its.getSampleModel(), keepBitWidth)[0];
		if (n == 1) {
			ILazyDataset image;
			if (loadLazily) {
				image = createLazyDataset(dtype, height, width);
			} else {
				image = createDataset(reader.read(0));
			}
			image.setName(DEF_IMAGE_NAME);
			image.setMetadata(metadata);
			output.addDataset(DEF_IMAGE_NAME, image);
		} else if (allSame) {
			ILazyDataset ld = createLazyDataset(dtype, n, height, width);
			ld.setMetadata(metadata);
			output.addDataset(STACK_NAME, ld);
		} else {
			createLazyDatasets(output, reader);
		}

		if (loadMetadata) {
			createMetadata(output, reader);
			metadata.setMetadata(metadataMap);
			output.setMetadata(metadata);
		}
	}

	private ILazyDataset createLazyDataset(final int dtype, final int... trueShape) {
		LazyLoaderStub l = new LazyLoaderStub() {
			@Override
			public IDataset getDataset(IMonitor mon, SliceND slice) throws IOException {
				int[] lstart = slice.getStart();
				int[] lstep  = slice.getStep();
				int[] newShape = slice.getShape();
				int[] shape = slice.getSourceShape();
				final int rank = shape.length;

				IDataset d = null;
				if (!Arrays.equals(trueShape, shape)) {
					final int trank = trueShape.length;
					int[] tstart = new int[trank];
					int[] tsize = new int[trank];
					int[] tstep = new int[trank];

					if (rank > trank) { // shape was extended (from left) then need to translate to true slice
						int j = 0;
						for (int i = 0; i < trank; i++) {
							if (trueShape[i] == 1) {
								tstart[i] = 0;
								tsize[i] = 1;
								tstep[i] = 1;
							} else {
								while (shape[j] == 1 && (rank - j) > (trank - i))
									j++;

								tstart[i] = lstart[j];
								tsize[i] = newShape[j];
								tstep[i] = lstep[j];
								j++;
							}
						}
					} else { // shape was squeezed then need to translate to true slice
						int j = 0;
						for (int i = 0; i < trank; i++) {
							if (trueShape[i] == 1) {
								tstart[i] = 0;
								tsize[i] = 1;
								tstep[i] = 1;
							} else {
								tstart[i] = lstart[j];
								tsize[i] = newShape[j];
								tstep[i] = lstep[j];
								j++;
							}
						}
					}

					d = loadData(mon, fileName, asGrey, keepBitWidth, dtype, shape, tstart, tsize, tstep);
					d.setShape(newShape); // squeeze shape back
				} else {
					d = loadData(mon, fileName, asGrey, keepBitWidth, dtype, shape, lstart, newShape, lstep);
				}
				return d;
			}

		};

		return createLazyDataset(STACK_NAME, dtype, trueShape.clone(), l);
	}

	private static IDataset loadData(IMonitor mon, String filename, boolean asGrey, boolean keepBitWidth,
			int dtype, int[] oshape, int[] start, int[] count, int[] step) throws IOException {

		// test to see if the filename passed will load
		BufferedImage image = ImageIO.read(new File(filename));
		IDataset set = SWTImageUtils.convertToRGBDataset(image);
		return set;
	}


	@Override
	protected void clearMetadata() {
		super.clearMetadata();
		metadataMap.clear();
	}
}
