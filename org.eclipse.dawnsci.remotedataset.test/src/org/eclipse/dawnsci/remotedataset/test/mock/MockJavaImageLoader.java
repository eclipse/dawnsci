/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.remotedataset.test.mock;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.SampleModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ScanFileHolderException;
import org.eclipse.dawnsci.analysis.api.metadata.Metadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.RGBDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that loads in data from an image file using native Java ImageIO
 * library with built-in image reader/writer.
 * <p>
 * A Raster object comprises a SampleModel and a DataBuffer where we have the
 * model of an image comprising bands of samples so that each pixel is a tuple
 * of samples (e.g. R, G, B) and the SampleModel maps to/from a sample (of a
 * pixel) to information held by the DataBuffer. A BufferedImage object
 * comprises a Raster and a ColorModel. The reader/writer handles BufferImages
 * so access the image data via the BufferedImage's Raster attribute.
 */
public class MockJavaImageLoader extends MockAbstractFileLoader {
	protected static final Logger logger = LoggerFactory.getLogger(MockJavaImageLoader.class);

	private String fileType = "";
	protected boolean asGrey;
	protected boolean keepBitWidth = false;

	/**
	 * @return true if loader keeps bit width of pixels
	 */
	public boolean isKeepBitWidth() {
		return keepBitWidth;
	}

	/**
	 * set loader to keep bit width of pixels
	 * @param keepBitWidth
	 */
	public void setKeepBitWidth(boolean keepBitWidth) {
		this.keepBitWidth = keepBitWidth;
	}

	/**
	 * @param FileName
	 *            which is the name of the file being passed to this class.
	 * @param FileType
	 *            which is the type of image being passed to the class
	 */
	public MockJavaImageLoader(String FileName, String FileType) {
		this(FileName, FileType, false);
	}

	/**
	 * @param FileName
	 *            which is the name of the file being passed to this class.
	 * @param FileType
	 *            which is the type of image being passed to the class
	 * @param convertToGrey
	 *            interpret colour image as a greyscale image (rather than taking the first colour channel)
	 */
	public MockJavaImageLoader(String FileName, String FileType, boolean convertToGrey) {
		this(FileName, FileType, convertToGrey, false);
	}

	/**
	 * @param FileName
	 *            which is the name of the file being passed to this class.
	 * @param FileType
	 *            which is the type of image being passed to the class
	 * @param convertToGrey
	 *            interpret colour image as a greyscale image (rather than taking the first colour channel)
	 * @param keepBitWidth
	 *            true if loader keeps bit width of pixels
	 */
	public MockJavaImageLoader(String FileName, String FileType, boolean convertToGrey, boolean keepBitWidth) {
		fileName = FileName;
		fileType = FileType; // format name
		asGrey = convertToGrey;
		this.keepBitWidth = keepBitWidth;
	}

	@Override
	protected void clearMetadata() {
		metadata = null;
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

		IDataHolder output = new MockDataHolder(null, fileName);
		// test to see if the filename passed will load
		f = new File(fileName);

		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(f);
		} catch (Exception e) {
			logger.error("Problem creating input stream for file " + fileName, e);
			throw new ScanFileHolderException("Problem creating input stream for file " + fileName, e);
		}
		if (iis == null) {
			logger.error("File format in '{}' cannot be read", fileName);
			throw new ScanFileHolderException("File format in '" + fileName + "' cannot be read");
		}
		Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
		boolean loaded = false;
		while (it.hasNext()) {
			ImageReader reader = it.next();
			reader.setInput(iis, false, loadMetadata);
			if (loadLazily) {
				loaded = createLazyDatasets(output, reader);
			} else {
				loaded = createDatasets(output, reader);
			}
			if (loaded) {
				if (loadMetadata) {
					createMetadata(output, reader);
				}
				break;
			}
		}
		if (!loaded) {
			logger.error("File format in '{}' cannot be read", fileName);
			throw new ScanFileHolderException("File format in '" + fileName + "' cannot be read");
		}

		return output;
	}

	private boolean createDatasets(IDataHolder output, ImageReader reader) {
		int j = 1; // start at 1
		BufferedImage input = null;
		for (int i = 0; true; i++) {
			try {
				String name = String.format(IMAGE_NAME_FORMAT, j);
				input = reader.read(i);
				Dataset data = createDataset(input);
				data.setName(name);
				output.addDataset(name, data);
			} catch (IOException e) {
				return false;
			} catch (IndexOutOfBoundsException e) {
				break;
			} catch (ScanFileHolderException e) {
				logger.error("Problem with creating dataset from image", e);
				return false;
			}
			j++;
		}
		return true;
	}

	protected boolean createLazyDatasets(IDataHolder output, ImageReader reader) {
		int j = 1; // start at 1
		for (int i = 0; true; i++) {
			try {
				int[] shape = new int[] {reader.getHeight(i), reader.getWidth(i)};
				Iterator<ImageTypeSpecifier> it = reader.getImageTypes(i);
				SampleModel sm = it.next().getSampleModel();
				int dtype = AWTImageUtils.getDTypeFromImage(sm, keepBitWidth)[0];
				final String name = String.format(IMAGE_NAME_FORMAT, j);
				LazyDataset lazy = createLazyDataset(name, dtype, shape, new LazyLoaderStub() {
					@Override
					public IDataset getDataset(IMonitor mon, SliceND slice) throws IOException {
						Dataset data = loadDataset(fileName, name, asGrey, keepBitWidth);
						return data == null ? null : data.getSliceView(slice);
					}
				});
				output.addDataset(name, lazy);
//				IIOMetadata imd = reader.getImageMetadata(i);
//				imd.getAsTree(imd.getNativeMetadataFormatName()).toString();
			} catch (IndexOutOfBoundsException e) {
				break;
			} catch (Exception e) {
				logger.warn("Could not get height or width for image {}", j);
				continue;
			}
			j++;
		}
		return output.getNames().length > 0;
	}

	private static Dataset loadDataset(String path, String name, boolean asGrey, boolean keepBitWidth) throws IOException {
		if (!name.startsWith(IMAGE_NAME_PREFIX)) {
			throw new IOException("Dataset of name '" + name + "' does not contain prefix " + IMAGE_NAME_PREFIX);
		}
		String number = name.substring(IMAGE_NAME_PREFIX.length());
		int num = -1;
		try {
			num = Integer.parseInt(number) - 1;
		} catch (NumberFormatException e) {
		}
		if (num < 0) {
			throw new IOException("Dataset of name '" + name + "' does not contain image number");
		}

		File f = new File(path);

		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(f);
		} catch (Exception e) {
			logger.error("Problem creating input stream for file " + path, e);
			throw new IOException("Problem creating input stream for file " + path, e);
		}
		if (iis == null) {
			logger.error("File format in '{}' cannot be read", path);
			throw new IOException("File format in '" + path + "' cannot be read");
		}
		Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
		while (it.hasNext()) {
			ImageReader reader = it.next();
			reader.setInput(iis, false, true);
			Dataset data;
			try {
				data = createDataset(reader.read(num), asGrey, keepBitWidth);
				data.setName(name);
				IDataHolder holder = new MockDataHolder(null, path);
				holder.setLoaderClass(MockJavaImageLoader.class);
				holder.setFilePath(path);
				holder.addDataset(name, data);
				return data;
			} catch (IndexOutOfBoundsException e) {
				throw new IOException("Image number is incorrect");
			} catch (IOException e) {
				logger.error("Problem reading file", e);
				throw e;
			} catch (ScanFileHolderException e) {
				logger.error("Problem creating dataset", e);
				throw new IOException(e);
			}
		}

		return null;
	}

	protected void createMetadata(IDataHolder output, @SuppressWarnings("unused") ImageReader reader) {
		metadata = new Metadata();
		metadata.setFilePath(fileName);
		for (String n : output.getNames()) {
			ILazyDataset lazy = output.getLazyDataset(n);
			metadata.addDataInfo(n, lazy.getShape());
		}
	}

	protected Dataset createDataset(BufferedImage input) throws ScanFileHolderException {
		return createDataset(input, asGrey, keepBitWidth);
	}

	protected static Dataset createDataset(BufferedImage input, boolean asGrey, boolean keepBitWidth) throws ScanFileHolderException {
		Dataset data = null;
		try {
			Dataset[] channels = AWTImageUtils.makeDatasets(input, keepBitWidth);
//			final int bands = input.getData().getNumBands();
			final int bands = channels.length;
			if (bands == 1) {
				data = channels[0];
			} else {
				if (input.getColorModel().getColorSpace().getType() != ColorSpace.TYPE_RGB) {
					throw new ScanFileHolderException("File does not contain RGB data");					
				}
				if (bands < 3) {
					throw new ScanFileHolderException("Number of colour channels is less than three so cannot load and convert");
				}

				data = new RGBDataset(channels[0], channels[1], channels[2]);

				if (asGrey)
					data = ((RGBDataset) data).createGreyDataset(channels[0].getDtype());
			}
		} catch (Exception e) {
			throw new ScanFileHolderException("There was a problem loading the image", e);
		}
		return data;
	}

	protected File findCorrectSuffix() throws ScanFileHolderException {
		String[] suffixes = ImageIO.getReaderFileSuffixes();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

		File f = null;
		testforsuffix: {
			if (!extension.equals(fileName)) { // there is a suffix
				for (String s : suffixes) {
					if (extension.equalsIgnoreCase(s)) {
						break testforsuffix;
					}
				}
			}
			// try standard suffix first then all supported suffixes
			String name = fileName + "." + fileType;
			f = new File(name);
			if (f.exists()) {
				fileName = name;
				break testforsuffix;
			}
			for (String s : suffixes) {
				name = fileName + "." + s;
				f = new File(name);
				if (f.exists()) {
					fileName = name;
					break testforsuffix;
				}
			}
		}
		if (f == null || !f.exists()) {
			throw new ScanFileHolderException("Does not exist",
					new FileNotFoundException(fileName));
		}
		return f;
	}
}
