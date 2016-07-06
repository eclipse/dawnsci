/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.downsample.DownsampleMode;
import org.eclipse.dawnsci.analysis.api.downsample.IDownsampleService;
import org.eclipse.dawnsci.analysis.dataset.impl.function.DatasetToDatasetFunction;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.eclipse.january.dataset.RGBDataset;
import org.eclipse.january.dataset.SliceIterator;

/**
 * Down-sample a dataset by a given bin. Also is the implementation of a service
 * so instead of using this class directly, one can consume the IDownsampleService
 * from OSGi thus not making a hard dependency on uk.ac.diamond.scisoft.analysis
 */
public class Downsample implements DatasetToDatasetFunction, IDownsampleService {
	
	private final DownsampleMode mode;
	private final int[] bshape; // bin shape 

	/**
	 * Creates a default Downsample set to MAXIMUM with a 2x2 bin.
	 * Called by OSGi when contributing the IDownsampleService
	 */
	public Downsample() {
		this(DownsampleMode.MAXIMUM, 2, 2);
	}
	
	/**
	 * This class down-samples (or subsamples) datasets according to given mode and sample bin shape
	 * 
	 * {@code mode} can be POINT, MEAN, MAXIMUM
	 * @param mode
	 * @param binShape
	 */
	public Downsample(DownsampleMode mode, int... binShape) {
		if (binShape.length == 0) {
			throw new IllegalArgumentException("Zero-dimensional bin not allowed");
		}
		for (int b : binShape) {
			if (b <= 0)
				throw new IllegalArgumentException("Bin side must be greater than zero");
		}
		bshape = binShape;
		this.mode = mode;
	}

	@Override
	public List<Dataset> downsample(String enc, IDataset... data) throws Exception {
		Downsample d = Downsample.decode(enc);
		return d.value(data);
	}

	@Override
	public List<Dataset> downsample(DownsampleMode mode, int[] binShape, IDataset... data) throws Exception{
	    Downsample d = new Downsample(mode, binShape);
		return d.value(data);
	}
	/**
	 * The class implements down-sampling of datasets
	 * 
	 * @param datasets
	 * @return a list of down-sampled datasets
	 */
	@Override
	public List<Dataset> value(IDataset... datasets) {
		if (datasets.length == 0)
			return null;

		List<Dataset> result = new ArrayList<Dataset>();
		int brank = bshape.length;

		for (IDataset idataset : datasets) {
			Dataset dataset = DatasetUtils.convertToDataset(idataset);
			final int[] dshape = dataset.getShape();
			final int drank = dshape.length;
			final int[] lbshape = new int[drank];
			for (int i = 0; i < drank; i++) {
				lbshape[i] = i < brank ? bshape[i] : 1; // adjust binning to dataset shape
			}
			final int[] shape = new int[drank];
			for (int i = 0; i < drank; i++) {
				shape[i] = (dshape[i] + bshape[i] - 1)/bshape[i];
			}

			final Dataset binned;
			DownsampleMode m = mode;
			if (dataset instanceof RGBDataset) {
				binned = DatasetFactory.zeros(RGBDataset.class, shape);
				// set the mode to point whenever RGB datasets are involved for the moment.
				m = DownsampleMode.POINT;
			} else {
				binned = DatasetFactory.zeros(dataset.getElementsPerItem(), shape, dataset.getDType());
			}

			final IndexIterator biter = binned.getIterator(true);
			final int[] bpos = biter.getPos();
			final int[] spos = new int[drank];
			final int[] epos = new int[drank];
			final int isize = binned.getElementsPerItem();

			
			// TODO In Java8 switch these loops to using ParallelStreams
			switch (m) {
			case POINT:
				while (biter.hasNext()) {
					for (int i = 0; i < drank; i++) {
						spos[i] = bshape[i]*bpos[i];
					}

					binned.setObjectAbs(biter.index, dataset.getObject(spos));
				}
				break;
			case MEAN:
				if (isize == 1) {
					while (biter.hasNext()) {
						for (int i = 0; i < drank; i++) {
							spos[i] = bshape[i] * bpos[i];
							epos[i] = spos[i] + bshape[i];
							if (epos[i] > dshape[i]) // ensure bin is within dataset
								epos[i] = dshape[i];
						}
						SliceIterator siter = (SliceIterator) dataset.getSliceIterator(spos, epos, null);

						double mean = 0;
						int num = 0;
						while (siter.hasNext()) {
							final double val = dataset.getElementDoubleAbs(siter.index);
							if (Double.isInfinite(val) || Double.isNaN(val))
								continue;
							num++;
							final double delta = val - mean;
							mean += delta / num;
						}
						binned.setObjectAbs(biter.index, mean);
					}
				} else {
					while (biter.hasNext()) {
						for (int i = 0; i < drank; i++) {
							spos[i] = bshape[i] * bpos[i];
							epos[i] = spos[i] + bshape[i];
							if (epos[i] > dshape[i])
								epos[i] = dshape[i];
						}
						SliceIterator siter = (SliceIterator) dataset.getSliceIterator(spos, epos, null);

						final double[] mean = new double[isize];
						int num = 0;
						while (siter.hasNext()) {
							for (int i = 0; i < isize; i++) {
								final double val = dataset.getElementDoubleAbs(siter.index + i);
								if (Double.isInfinite(val) || Double.isNaN(val))
									continue;
								num++;
								final double delta = val - mean[i];
								mean[i] += delta / num;
							}
						}
						binned.setObjectAbs(biter.index, mean);
					}
				}
				break;
			case MAXIMUM:
				if (isize == 1) {
					while (biter.hasNext()) {
						for (int i = 0; i < drank; i++) {
							spos[i] = bshape[i] * bpos[i];
							epos[i] = spos[i] + bshape[i];
							if (epos[i] > dshape[i])
								epos[i] = dshape[i];
						}
						SliceIterator siter = (SliceIterator) dataset.getSliceIterator(spos, epos, null);

						double max = Double.NEGATIVE_INFINITY;
						while (siter.hasNext()) {
							final double val = dataset.getElementDoubleAbs(siter.index);
							if (Double.isInfinite(val) || Double.isNaN(val))
								continue;
							if (val > max)
								max = val;
						}
						binned.setObjectAbs(biter.index, max);
					}
				} else {
					while (biter.hasNext()) {
						for (int i = 0; i < drank; i++) {
							spos[i] = bshape[i] * bpos[i];
							epos[i] = spos[i] + bshape[i];
							if (epos[i] > dshape[i])
								epos[i] = dshape[i];
						}
						SliceIterator siter = (SliceIterator) dataset.getSliceIterator(spos, epos, null);

						final double[] max = new double[isize];
						for (int i = 0; i < isize; i++)
							max[i] = Double.NEGATIVE_INFINITY;
						while (siter.hasNext()) {
							for (int i = 0; i < isize; i++) {
								final double val = dataset.getElementDoubleAbs(siter.index + i);
								if (Double.isInfinite(val) || Double.isNaN(val))
									continue;
								if (val > max[i])
									max[i] = val;
							}
						}
						binned.setObjectAbs(biter.index, max);
					}
				}
				break;
			case MINIMUM:
				if (isize == 1) {
					while (biter.hasNext()) {
						for (int i = 0; i < drank; i++) {
							spos[i] = bshape[i] * bpos[i];
							epos[i] = spos[i] + bshape[i];
							if (epos[i] > dshape[i])
								epos[i] = dshape[i];
						}
						SliceIterator siter = (SliceIterator) dataset.getSliceIterator(spos, epos, null);

						double min = Double.POSITIVE_INFINITY;
						while (siter.hasNext()) {
							final double val = dataset.getElementDoubleAbs(siter.index);
							if (Double.isInfinite(val) || Double.isNaN(val))
								continue;
							if (val < min)
								min = val;
						}
						binned.setObjectAbs(biter.index, min);
					}
				} else {
					while (biter.hasNext()) {
						for (int i = 0; i < drank; i++) {
							spos[i] = bshape[i] * bpos[i];
							epos[i] = spos[i] + bshape[i];
							if (epos[i] > dshape[i])
								epos[i] = dshape[i];
						}
						SliceIterator siter = (SliceIterator) dataset.getSliceIterator(spos, epos, null);

						final double[] min = new double[isize];
						for (int i = 0; i < isize; i++)
							min[i] = Double.POSITIVE_INFINITY;
						while (siter.hasNext()) {
							for (int i = 0; i < isize; i++) {
								final double val = dataset.getElementDoubleAbs(siter.index + i);
								if (Double.isInfinite(val) || Double.isNaN(val))
									continue;
								if (val < min[i])
									min[i] = val;
							}
						}
						binned.setObjectAbs(biter.index, min);
					}
				}
				break;
			}
			result.add(binned);
		}
		return result;
	}
	
	public DownsampleMode getMode() {
		return mode;
	}

	public int[] getBshape() {
		return bshape;
	}

	public static String encode(Downsample s) {
		
		StringBuilder buf = new StringBuilder();
		buf.append(s.mode.name());
		buf.append(":");
		for (int i = 0; i < s.bshape.length; i++) {
			buf.append(s.bshape[i]);
			if (i<s.bshape.length-1) buf.append("x");
		}
        return buf.toString();
	}
	
	public static Downsample decode(String enc) {
		
		final String[] sa = enc.split("\\:");
		DownsampleMode dt    = DownsampleMode.valueOf(sa[0]);
		final String[]    vals   = sa[1].split("x");
        final int[]       bshape = new int[vals.length];
        for (int i = 0; i < vals.length; i++) bshape[i] = Integer.parseInt(vals[i]);
        return new Downsample(dt, bshape);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bshape);
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Downsample other = (Downsample) obj;
		if (!Arrays.equals(bshape, other.bshape))
			return false;
		if (mode != other.mode)
			return false;
		return true;
	}


}
