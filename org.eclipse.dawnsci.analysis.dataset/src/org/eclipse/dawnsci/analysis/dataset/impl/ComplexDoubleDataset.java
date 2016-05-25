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

// GEN_COMMENT

package org.eclipse.dawnsci.analysis.dataset.impl;


import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;


/**
 * Extend compound dataset to hold complex double values // PRIM_TYPE
 */
public class ComplexDoubleDataset extends CompoundDoubleDataset { // CLASS_TYPE
	// pin UID to base class
	private static final long serialVersionUID = Dataset.serialVersionUID;

	private static final int ISIZE = 2; // number of elements per item

	@Override
	public int getDtype() {
		return Dataset.COMPLEX128; // DATA_TYPE
	}

	public ComplexDoubleDataset() {
		super(ISIZE);
	}

	/**
	 * Create a zero-filled dataset of given shape
	 * @param shape
	 */
	public ComplexDoubleDataset(final int... shape) {
		super(ISIZE, shape);
	}

	/**
	 * Create a dataset using given data (real and imaginary parts are grouped in pairs)
	 * @param data
	 * @param shape (can be null to create 1D dataset)
	 */
	public ComplexDoubleDataset(final double[] data, final int... shape) { // PRIM_TYPE
		super(ISIZE, data, shape);
	}

	/**
	 * Copy a dataset
	 * @param dataset
	 */
	public ComplexDoubleDataset(final ComplexDoubleDataset dataset) {
		super(dataset);
	}

	/**
	 * Create a dataset using given data (real and imaginary parts are given separately)
	 * @param realData
	 * @param imagData
	 * @param shape (can be null to create 1D dataset)
	 */
	public ComplexDoubleDataset(final double[] realData, final double[] imagData, int... shape) { // PRIM_TYPE
		if (realData == null || imagData == null) {
			throw new IllegalArgumentException("Data must not be null");
		}
		int dsize = realData.length > imagData.length ? imagData.length : realData.length;
		if (shape == null || shape.length == 0) {
			shape = new int[] {dsize};
		}
		isize = ISIZE;
		size = calcSize(shape);
		if (size != dsize) {
			throw new IllegalArgumentException(String.format("Shape %s is not compatible with size of data array, %d",
					Arrays.toString(shape), dsize));
		}
		this.shape = shape.clone();

		try {
			odata = data = createArray(size);
		} catch (Throwable t) {
			logger.error("Could not create a dataset of shape {}", Arrays.toString(shape), t);
			throw new IllegalArgumentException(t);
		}

		for (int i = 0, n = 0; i < size; i++) {
			data[n++] = realData[i];
			data[n++] = imagData[i];
		}
	}

	/**
	 * Create a dataset using given data (real and imaginary parts are given separately)
	 * @param real
	 * @param imag
	 */
	public ComplexDoubleDataset(final Dataset real, final Dataset imag) {
		super(ISIZE, real.getShapeRef());
		real.checkCompatibility(imag);

		IndexIterator riter = real.getIterator();
		IndexIterator iiter = imag.getIterator();

		for (int i = 0; riter.hasNext() && iiter.hasNext();) {
			data[i++] = real.getElementDoubleAbs(riter.index); // ADD_CAST
			data[i++] = imag.getElementDoubleAbs(iiter.index); // ADD_CAST
		}
	}

	/**
	 * Cast a dataset to this complex type
	 * @param dataset
	 */
	public ComplexDoubleDataset(final Dataset dataset) {
		super(ISIZE, dataset.getShapeRef());
		copyToView(dataset, this, true, false);
		offset = 0;
		stride = null;
		base = null;

		IndexIterator iter = dataset.getIterator();
		int disize = dataset.getElementsPerItem();
		if (disize == 1) {
			for (int i = 0; iter.hasNext(); i += isize) {
				data[i] = dataset.getElementDoubleAbs(iter.index); // ADD_CAST
			}
		} else {
			for (int i = 0; iter.hasNext(); i += isize) {
				data[i] = dataset.getElementDoubleAbs(iter.index); // ADD_CAST
				data[i+1] = dataset.getElementDoubleAbs(iter.index+1); // ADD_CAST
			}
		}
	}

	@Override
	public ComplexDoubleDataset clone() {
		return new ComplexDoubleDataset(this);
	}

	/**
	 * Create a dataset from an object which could be a Java list, array (of arrays...)
	 * or Number. Ragged sequences or arrays are padded with zeros.
	 * 
	 * @param obj
	 * @return dataset with contents given by input
	 */
	public static ComplexDoubleDataset createFromObject(final Object obj) {
		ComplexDoubleDataset result = new ComplexDoubleDataset();

		result.shape = getShapeFromObject(obj);
		result.size = calcSize(result.shape);

		try {
			result.odata = result.data = result.createArray(result.size);
		} catch (Throwable t) {
			logger.error("Could not create a dataset of shape {}", Arrays.toString(result.shape), t);
			throw new IllegalArgumentException(t);
		}

		int[] pos = new int[result.shape.length];
		result.fillData(obj, 0, pos);
		return result;
	}

	/**
	 * @param stop
	 * @return a new 1D dataset, filled with values determined by parameters
	 * @deprecated Use {@link #createRange(double)}
	 */
	@Deprecated
	public static ComplexDoubleDataset arange(final double stop) {
		return createRange(0, stop, 1);
	}

	/**
	 * @param start
	 * @param stop
	 * @param step
	 * @return a new 1D dataset, filled with values determined by parameters
	 * @deprecated Use {@link #createRange(double, double, double)}
	 */
	@Deprecated
	public static ComplexDoubleDataset arange(final double start, final double stop, final double step) {
		return createRange(start, stop, step);
	}

	/**
	 * @param stop
	 * @return a new 1D dataset, filled with values determined by parameters
	 */
	public static ComplexDoubleDataset createRange(final double stop) {
		return createRange(0, stop, 1);
	}

	/**
	 * @param start
	 * @param stop
	 * @param step
	 * @return a new 1D dataset, filled with values determined by parameters
	 */
	public static ComplexDoubleDataset createRange(final double start, final double stop, final double step) {
		int size = calcSteps(start, stop, step);
		ComplexDoubleDataset result = new ComplexDoubleDataset(size);
		for (int i = 0; i < size; i ++) {
			result.data[i*ISIZE] = (start + i*step); // ADD_CAST
		}
		return result;
	}

	/**
	 * @param shape
	 * @return a dataset filled with ones
	 */
	public static ComplexDoubleDataset ones(final int... shape) {
		return new ComplexDoubleDataset(shape).fill(1);
	}

	@Override
	public ComplexDoubleDataset fill(final Object obj) {
		double vr = toReal(obj); // PRIM_TYPE // ADD_CAST
		double vi = toImag(obj); // PRIM_TYPE // ADD_CAST
		IndexIterator iter = getIterator();

		while (iter.hasNext()) {
			data[iter.index] = vr;
			data[iter.index+1] = vi;
		}

		setDirty();
		return this;
	}

	@Override
	public ComplexDoubleDataset getView() {
		ComplexDoubleDataset view = new ComplexDoubleDataset();
		copyToView(this, view, true, true);
		view.data = data;
		return view;
	}

	/**
	 * Get complex value at absolute index in the internal array.
	 * 
	 * This is an internal method with no checks so can be dangerous. Use with care or ideally with an iterator.
	 *
	 * @param index absolute index
	 * @return value
	 */
	public Complex getComplexAbs(final int index) {
		return new Complex(data[index], data[index+1]);
	}

	@Override
	public Object getObjectAbs(final int index) {
		return new Complex(data[index], data[index+1]);
	}

	@Override
	public String getStringAbs(final int index) {
		double di = data[index + 1]; // PRIM_TYPE
		if (stringFormat == null) {
			return di >= 0 ? String.format("%.8g + %.8gj", data[index], di) : // FORMAT_STRING
				String.format("%.8g - %.8gj", data[index], -di); // FORMAT_STRING
		}
		StringBuilder s = new StringBuilder();
		s.append(stringFormat.format(data[index]));
		if (di >= 0) {
			s.append(" + ");
			s.append(stringFormat.format(di));
		} else {
			s.append(" - ");
			s.append(stringFormat.format(-di));
		}
		s.append('j');
		return s.toString();
	}

	/**
	 * Set values at absolute index in the internal array.
	 * 
	 * This is an internal method with no checks so can be dangerous. Use with care or ideally with an iterator.
	 * @param index absolute index
	 * @param val new values
	 */
	@SuppressWarnings("cast")
	public void setAbs(final int index, final Complex val) {
		setAbs(index, (double) val.getReal(), (double) val.getImaginary()); // PRIM_TYPE
	}

	@SuppressWarnings("cast")
	@Override
	public void setObjectAbs(final int index, final Object obj) {
		setAbs(index, (double) toReal(obj), (double) toImag(obj)); // PRIM_TYPE
	}

	/**
	 * Set item at index to complex value given by real and imaginary parts 
	 * @param index absolute index
	 * @param real
	 * @param imag
	 */
	public void setAbs(final int index, final double real, final double imag) { // PRIM_TYPE
		data[index] = real;
		data[index+1] = imag;
		setDirty();
	}

	/**
	 * @param i
	 * @return item in given position
	 */
	public Complex get(final int i) {
		int n = get1DIndex(i);
		Complex z = new Complex(data[n], data[n+1]);
		return z;
	}

	/**
	 * @param i
	 * @param j
	 * @return item in given position
	 */
	public Complex get(final int i, final int j) {
		int n = get1DIndex(i, j);
		Complex z = new Complex(data[n], data[n+1]);
		return z;
	}

	/**
	 * @param pos
	 * @return item in given position
	 */
	public Complex get(final int... pos) {
		int n = get1DIndex(pos);
		Complex z = new Complex(data[n], data[n+1]);
		return z;
	}

	@Override
	public Object getObject(final int i) {
		return getComplex(i);
	}

	@Override
	public Object getObject(final int i, final int j) {
		return getComplex(i, j);
	}

	@Override
	public Object getObject(final int... pos) {
		return getComplex(pos);
	}

	/**
	 * @param i
	 * @return item in given position
	 */
	@SuppressWarnings("cast")
	public double getReal(final int i) { // PRIM_TYPE
		return (double) getFirstValue(i); // PRIM_TYPE
	}

	/**
	 * @param i
	 * @param j
	 * @return item in given position
	 */
	@SuppressWarnings("cast")
	public double getReal(final int i, final int j) { // PRIM_TYPE
		return (double) getFirstValue(i, j); // PRIM_TYPE
	}

	/**
	 * @param pos
	 * @return item in given position
	 */
	@SuppressWarnings("cast")
	public double getReal(final int... pos) { // PRIM_TYPE
		return (double) getFirstValue(pos); // PRIM_TYPE
	}

	/**
	 * @param i
	 * @return item in given position
	 */
	public double getImag(final int i) { // PRIM_TYPE
		return data[get1DIndex(i) + 1];
	}

	/**
	 * @param i
	 * @param j
	 * @return item in given position
	 */
	public double getImag(final int i, final int j) { // PRIM_TYPE
		return data[get1DIndex(i, j) + 1];
	}

	/**
	 * @param pos
	 * @return item in given position
	 */
	public double getImag(final int... pos) { // PRIM_TYPE
		return data[get1DIndex(pos) + 1];
	}

	/**
	 * @param i
	 * @return item in given position
	 */
	public Complex getComplex(final int i) {
		return get(i);
	}

	/**
	 * @param i
	 * @param j
	 * @return item in given position
	 */
	public Complex getComplex(final int i, final int j) {
		return get(i, j);
	}

	/**
	 * @param pos
	 * @return item in given position
	 */
	public Complex getComplex(final int... pos) {
		return get(pos);
	}

	@SuppressWarnings("cast")
	@Override
	public void set(final Object obj, final int i) {
		setItem(new double[] {(double) toReal(obj), (double) toImag(obj)}, i); // PRIM_TYPE
	}

	@SuppressWarnings("cast")
	@Override
	public void set(final Object obj, final int i, final int j) {
		setItem(new double[] {(double) toReal(obj), (double) toImag(obj)}, i, j); // PRIM_TYPE
	}

	@SuppressWarnings("cast")
	@Override
	public void set(final Object obj, int... pos) {
		if (pos == null || (pos.length == 0 && shape.length > 0)) {
			pos = new int[shape.length];
		}

		setItem(new double[] {(double) toReal(obj), (double) toImag(obj)}, pos); // PRIM_TYPE
	}

	/**
	 * Set real and imaginary values at given position
	 * @param dr
	 * @param di
	 * @param i
	 */
	public void set(final double dr, final double di, final int i) { // PRIM_TYPE
		setItem(new double[] {dr, di}, i); // PRIM_TYPE
	}

	/**
	 * Set real and imaginary values at given position
	 * @param dr
	 * @param di
	 * @param i
	 * @param j
	 */
	public void set(final double dr, final double di, final int i, final int j) { // PRIM_TYPE
		setItem(new double[] {dr, di}, i, j); // PRIM_TYPE
	}

	/**
	 * Set real and imaginary values at given position
	 * @param dr
	 * @param di
	 * @param pos
	 */
	public void set(final double dr, final double di, final int... pos) { // PRIM_TYPE
		setItem(new double[] {dr, di}, pos); // PRIM_TYPE
	}

	public DoubleDataset imag() { // CLASS_TYPE
		DoubleDataset rdataset = new DoubleDataset(shape); // CLASS_TYPE
		IndexIterator iter = getIterator();
		IndexIterator riter = rdataset.getIterator();

		double[] rdata = rdataset.data; // PRIM_TYPE
		while (iter.hasNext() && riter.hasNext())
			rdata[riter.index] = data[iter.index + 1];

		return rdataset;
	}

	/**
	 * @return view of imaginary values
	 */
	public DoubleDataset imagView() { // CLASS_TYPE
		return getElementsView(1);
	}

	@Override
	public Number max(boolean... switches) {
		throw new UnsupportedOperationException("Cannot compare complex numbers");
	}

	@Override
	public Number min(boolean... switches) {
		throw new UnsupportedOperationException("Cannot compare complex numbers");
	}

	@Override
	public Object sum() {
		final String n = storeName(false, STORE_STATS_ITEM_NAME);
		if (storedValues == null || storedValues.isEmpty()) {
			calculateSummaryStats(false, false, n);
		}

		final SummaryStatistics rstats = (SummaryStatistics) storedValues.get(n + "0");
		final SummaryStatistics istats = (SummaryStatistics) storedValues.get(n + "1");
		return new Complex(rstats.getSum(), istats.getSum());
	}

	@Override
	public Object mean(boolean... switches) {
		final String n = storeName(false, STORE_STATS_ITEM_NAME);
		if (storedValues == null || storedValues.isEmpty()) {
			calculateSummaryStats(false, false, n);
		}

		final SummaryStatistics rstats = (SummaryStatistics) storedValues.get(n + "0");
		final SummaryStatistics istats = (SummaryStatistics) storedValues.get(n + "1");
		return new Complex(rstats.getMean(), istats.getMean());
	}

	@Override
	public int[] maxPos(boolean ignoreNaNs) {
		throw new UnsupportedOperationException("Cannot compare complex numbers");
	}

	@Override
	public int[] minPos(boolean ignoreNaNs) {
		throw new UnsupportedOperationException("Cannot compare complex numbers");
	}

	@Override
	public ComplexDoubleDataset getSlice(final SliceIterator siter) {
		ComplexDoubleDataset result = new ComplexDoubleDataset(siter.getShape());
		double[] rdata = result.data; // PRIM_TYPE
		IndexIterator riter = result.getIterator();

		while (siter.hasNext() && riter.hasNext()) {
			rdata[riter.index] = data[siter.index];
			rdata[riter.index+1] = data[siter.index+1];
		}

		result.setName(name + BLOCK_OPEN + Slice.createString(siter.shape, siter.start, siter.stop, siter.step) + BLOCK_CLOSE);
		return result;
	}

	@Override
	ComplexDoubleDataset setSlicedView(Dataset view, Dataset d) {
		final BroadcastSelfIterator it = BroadcastSelfIterator.createIterator(view, d);

		if (d instanceof ComplexFloatDataset || d instanceof ComplexDoubleDataset) {
			while (it.hasNext()) {
				data[it.aIndex] = it.bDouble; // BCAST_WITH_CAST d.getElementDoubleAbs(it.bIndex);
				data[it.aIndex + 1] = d.getElementDoubleAbs(it.bIndex + 1); // GET_ELEMENT_WITH_CAST
			}
		} else {
			while (it.hasNext()) {
				data[it.aIndex] = it.bDouble; // BCAST_WITH_CAST d.getElementDoubleAbs(it.bIndex);
				data[it.aIndex + 1] = 0;
			}
		}
		return this;
	}

	@Override
	public ComplexDoubleDataset setSlice(final Object o, final IndexIterator siter) {
		if (o instanceof ComplexFloatDataset) {
			ComplexFloatDataset zds = (ComplexFloatDataset) o;

			if (!areShapesCompatible(siter.getShape(), zds.shape)) {
				throw new IllegalArgumentException(String.format(
						"Input dataset is not compatible with slice: %s cf %s", Arrays.toString(zds.shape),
						Arrays.toString(siter.getShape())));
			}

			IndexIterator oiter = zds.getIterator();
			float[] odata = zds.data;

			while (siter.hasNext() && oiter.hasNext()) {
				data[siter.index] = odata[oiter.index];
				data[siter.index+1] = odata[oiter.index+1];
			}
		} else if (o instanceof ComplexDoubleDataset) { // IGNORE_CLASS
			ComplexDoubleDataset zds = (ComplexDoubleDataset) o; // IGNORE_CLASS

			if (!areShapesCompatible(siter.getShape(), zds.shape)) {
				throw new IllegalArgumentException(String.format(
						"Input dataset is not compatible with slice: %s cf %s", Arrays.toString(zds.shape),
						Arrays.toString(siter.getShape())));
			}

			IndexIterator oiter = zds.getIterator();
			double[] odata = zds.data;

			while (siter.hasNext() && oiter.hasNext()) {
				data[siter.index] = odata[oiter.index]; // PRIM_TYPE // ADD_CAST
				data[siter.index+1] = odata[oiter.index+1]; // PRIM_TYPE // ADD_CAST
			}
		} else if (o instanceof IDataset) {
			super.setSlice(o, siter);
		} else {
			try {
				double vr = toReal(o); // PRIM_TYPE // ADD_CAST
				double vi = toImag(o); // PRIM_TYPE // ADD_CAST

				while (siter.hasNext()) {
					data[siter.index]     = vr;
					data[siter.index + 1] = vi;
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Object for setting slice is not a dataset or number");
			}
		}
		setDirty();
		return this;
	}

	@Override
	public ComplexDoubleDataset iadd(final Object b) {
		Dataset bds = b instanceof Dataset ? (Dataset) b : DatasetFactory.createFromObject(b);
		boolean useLong = bds.elementClass().equals(Long.class);
		if (bds.getSize() == 1) {
			final IndexIterator it = getIterator();
			if (useLong) { // note no complex longs
				final long lb = bds.getElementLongAbs(0);
				while (it.hasNext()) {
					data[it.index] += lb;
				}
			} else {
				final double db = bds.getElementDoubleAbs(0);
				if (!bds.isComplex() || bds.getElementDoubleAbs(1) == 0) {
					while (it.hasNext()) {
						data[it.index] += db;
					}
				} else {
					final double vi = bds.getElementDoubleAbs(1);
					while (it.hasNext()) {
						data[it.index]     += db;
						data[it.index + 1] += vi;
					}
				}
			}
		} else {
			final BroadcastSelfIterator it = BroadcastSelfIterator.createIterator(this, bds);
			it.setOutputDouble(!useLong);
			if (useLong) { // note no complex longs
				while (it.hasNext()) {
					data[it.aIndex] += it.bLong;
				}
			} else {
				if (bds.isComplex()) {
					while (it.hasNext()) {
						data[it.aIndex]     += it.bDouble;
						data[it.aIndex + 1] += bds.getElementDoubleAbs(it.bIndex + 1);
					}
				} else {
					while (it.hasNext()) {
						data[it.aIndex] += it.bDouble;
					}
				}
			}
		}
		setDirty();
		return this;
	}

	@Override
	public ComplexDoubleDataset isubtract(final Object b) {
		Dataset bds = b instanceof Dataset ? (Dataset) b : DatasetFactory.createFromObject(b);
		boolean useLong = bds.elementClass().equals(Long.class);
		if (bds.getSize() == 1) {
			final IndexIterator it = getIterator();
			if (useLong) { // note no complex longs
				final long lb = bds.getElementLongAbs(0);
				while (it.hasNext()) {
					data[it.index] -= lb;
				}
			} else {
				final double db = bds.getElementDoubleAbs(0);
				if (!bds.isComplex() || bds.getElementDoubleAbs(1) == 0) {
					while (it.hasNext()) {
						data[it.index] -= db;
					}
				} else {
					final double vi = bds.getElementDoubleAbs(1);
					while (it.hasNext()) {
						data[it.index]     -= db;
						data[it.index + 1] -= vi;
					}
				}
			}
		} else {
			final BroadcastSelfIterator it = BroadcastSelfIterator.createIterator(this, bds);
			it.setOutputDouble(!useLong);
			if (useLong) { // note no complex longs
				while (it.hasNext()) {
					data[it.aIndex] -= it.bLong;
				}
			} else {
				if (bds.isComplex()) {
					while (it.hasNext()) {
						data[it.aIndex]     -= it.bDouble;
						data[it.aIndex + 1] -= bds.getElementDoubleAbs(it.bIndex + 1);
					}
				} else {
					while (it.hasNext()) {
						data[it.aIndex] -= it.bDouble;
					}
				}
			}
		}
		setDirty();
		return this;
	}

	@Override
	public ComplexDoubleDataset imultiply(final Object b) {
		Dataset bds = b instanceof Dataset ? (Dataset) b : DatasetFactory.createFromObject(b);
		boolean useLong = bds.elementClass().equals(Long.class);
		if (bds.getSize() == 1) {
			final IndexIterator it = getIterator();
			if (useLong) { // note no complex longs
				final long r2 = bds.getElementLongAbs(0);
				while (it.hasNext()) {
					data[it.index]     *= r2;
					data[it.index + 1] *= r2;
				}
			} else {
				final double r2 = bds.getElementDoubleAbs(0);
				if (!bds.isComplex() || bds.getElementDoubleAbs(1) == 0) {
					while (it.hasNext()) {
						data[it.index]     *= r2;
						data[it.index + 1] *= r2;
					}
				} else {
					final double i2 = bds.getElementDoubleAbs(1);
					while (it.hasNext()) {
						double r1 = data[it.index];
						double i1 = data[it.index + 1];
						data[it.index]     = (r1*r2 - i1*i2); // ADD_CAST
						data[it.index + 1] = (r1*i2 + i1*r2); // ADD_CAST
					}
				}
			}
		} else {
			final BroadcastIterator it = BroadcastIterator.createIterator(this, bds);
			it.setOutputDouble(!useLong);
			if (useLong) { // note no complex longs
				while (it.hasNext()) {
					data[it.aIndex]     *= it.bDouble;
					data[it.aIndex + 1] *= it.bDouble;
				}
			} else {
				if (bds.isComplex()) {
					while (it.hasNext()) {
						double r1 = it.aDouble;
						double r2 = it.bDouble;
						double i1 = data[it.aIndex + 1];
						double i2 = bds.getElementDoubleAbs(it.bIndex + 1);
						data[it.aIndex]     = (r1*r2 - i1*i2); // ADD_CAST
						data[it.aIndex + 1] = (r1*i2 + i1*r2); // ADD_CAST
					}
				} else {
					while (it.hasNext()) {
						data[it.aIndex]     *= it.bDouble;
						data[it.aIndex + 1] *= it.bDouble;
					}
				}
			}
		}
		setDirty();
		return this;
	}

	@Override
	public ComplexDoubleDataset idivide(final Object b) {
		Dataset bds = b instanceof Dataset ? (Dataset) b : DatasetFactory.createFromObject(b);
		boolean useLong = bds.elementClass().equals(Long.class);
		if (bds.getSize() == 1) {
			final IndexIterator it = getIterator();
			if (useLong) { // note no complex longs
				final long r2 = bds.getElementLongAbs(0);
				while (it.hasNext()) {
					data[it.index]     /= r2;
					data[it.index + 1] /= r2;
				}
			} else {
				final double r2 = bds.getElementDoubleAbs(0);
				if (!bds.isComplex() || bds.getElementDoubleAbs(1) == 0) {
					while (it.hasNext()) {
						data[it.index]     /= r2;
						data[it.index + 1] /= r2;
					}
				} else {
					final double i2 = bds.getElementDoubleAbs(1);
					if (Math.abs(r2) < Math.abs(i2)) {
						double q = r2/i2;
						double den = r2*q + i2;
						while (it.hasNext()) {
							double r1 = data[it.index];
							double i1 = data[it.index + 1];
							data[it.index]     = ((r1*q + i1) / den); // ADD_CAST
							data[it.index + 1] = ((i1*q - r1) / den); // ADD_CAST
						}
					} else {
						double q = i2/r2;
						double den = i2*q + r2;
						if (den == 0) {
							while (it.hasNext()) {
								data[it.index]     = Double.NaN; // CLASS_TYPE
								data[it.index + 1] = Double.NaN; // CLASS_TYPE
							}
						} else {
							while (it.hasNext()) {
								double r1 = data[it.index];
								double i1 = data[it.index + 1];
								data[it.index]     = ((i1 * q + r1) / den); // ADD_CAST
								data[it.index + 1] = ((i1 - r1 * q) / den); // ADD_CAST
							}
						}
					}
				}
			}
		} else {
			final BroadcastIterator it = BroadcastIterator.createIterator(this, bds);
			it.setOutputDouble(!useLong);
			if (useLong) {
				while (it.hasNext()) {
					data[it.aIndex]     /= it.bLong;
					data[it.aIndex + 1] /= it.bLong;
				}
			} else {
				if (bds.isComplex()) {
					while (it.hasNext()) {
						double r1 = it.aDouble;
						double r2 = it.bDouble;
						double i1 = data[it.aIndex + 1];
						double i2 = bds.getElementDoubleAbs(it.bIndex + 1);
						if (Math.abs(r2) < Math.abs(i2)) {
							double q = r2/i2;
							double den = r2*q + i2;
							data[it.aIndex]     = ((r1*q + i1) / den); // ADD_CAST
							data[it.aIndex + 1] = ((i1*q - r1) / den); // ADD_CAST
						} else {
							double q = i2/r2;
							double den = i2*q + r2;
							if (den == 0) {
								data[it.aIndex]     = Double.NaN; // CLASS_TYPE
								data[it.aIndex + 1] = Double.NaN; // CLASS_TYPE
							} else {
								data[it.aIndex]     = ((i1 * q + r1) / den); // ADD_CAST
								data[it.aIndex + 1] = ((i1 - r1 * q) / den); // ADD_CAST
							}
						}
					}
				} else {
					while (it.hasNext()) {
						data[it.aIndex]     /= it.bDouble;
						data[it.aIndex + 1] /= it.bDouble;
					}
				}
			}
		}
		setDirty();
		return this;
	}

	@Override
	public ComplexDoubleDataset iremainder(final Object b) {
		throw new UnsupportedOperationException("Unsupported method for class");
	}

	@Override
	public ComplexDoubleDataset ipower(final Object b) {
		Dataset bds = b instanceof Dataset ? (Dataset) b : DatasetFactory.createFromObject(b);
		if (bds.getSize() == 1) {
			final IndexIterator it = getIterator();
			final double r2 = bds.getElementDoubleAbs(0);
			if (!bds.isComplex() || bds.getElementDoubleAbs(1) == 0) {
				while (it.hasNext()) {
					final Complex zd = new Complex(data[it.index], data[it.index + 1]).pow(r2);
					data[it.index]     = zd.getReal(); // ADD_CAST
					data[it.index + 1] = zd.getImaginary(); // ADD_CAST
				}
			} else {
				final Complex zv = new Complex(r2, bds.getElementDoubleAbs(1));
				while (it.hasNext()) {
					final Complex zd = new Complex(data[it.index], data[it.index + 1]).pow(zv);
					data[it.index]     = zd.getReal(); // ADD_CAST
					data[it.index + 1] = zd.getImaginary(); // ADD_CAST
				}
			}
		} else {
			final BroadcastIterator it = BroadcastIterator.createIterator(this, bds);
			it.setOutputDouble(true);
			if (bds.isComplex()) {
				while (it.hasNext()) {
					final Complex zv = new Complex(it.bDouble, bds.getElementDoubleAbs(it.bIndex + 1));
					final Complex zd = new Complex(it.aDouble, data[it.aIndex + 1]).pow(zv);
					data[it.aIndex]     = zd.getReal(); // ADD_CAST
					data[it.aIndex + 1] = zd.getImaginary(); // ADD_CAST
				}
			} else {
				while (it.hasNext()) {
					final Complex zd = new Complex(it.aDouble, data[it.aIndex + 1]).pow(it.bDouble);
					data[it.aIndex]     = zd.getReal(); // ADD_CAST
					data[it.aIndex + 1] = zd.getImaginary(); // ADD_CAST
				}
			}
		}
		setDirty();
		return this;
	}

	@Override
	public double residual(final Object b, Dataset w, boolean ignoreNaNs) {
		Dataset bds = b instanceof Dataset ? (Dataset) b : DatasetFactory.createFromObject(b);
		final BroadcastIterator it = BroadcastIterator.createIterator(this, bds);
		it.setOutputDouble(true);
		double sum = 0;
		double comp = 0;
		final int bis = bds.getElementsPerItem();

		if (bis == 1) {
			if (w == null) {
				while (it.hasNext()) {
					double diffr = it.aDouble - it.bDouble;
					double diffi = data[it.aIndex + 1];
					if (ignoreNaNs && (Double.isNaN(diffr) || Double.isNaN(diffi))) {
						continue;
					}
					double err = diffr * diffr - comp;
					double temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;

					err = diffi * diffi - comp;
					temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;
				}
			} else {
				IndexIterator itw = w.getIterator();
				while (it.hasNext() && itw.hasNext()) {
					final double dw = w.getElementDoubleAbs(itw.index);
					double diffr = it.aDouble - it.bDouble;
					double diffi = data[it.aIndex + 1];
					if (ignoreNaNs && (Double.isNaN(diffr) || Double.isNaN(diffi))) {
						continue;
					}
					double err = diffr * diffr * dw - comp;
					double temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;

					err = diffi * diffi * dw - comp;
					temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;
				}
			}
		} else {
			if (w == null) {
				while (it.hasNext()) {
					double diffr = it.aDouble - it.bDouble;
					double diffi = data[it.aIndex] - bds.getElementDoubleAbs(it.bIndex + 1);
					if (ignoreNaNs && (Double.isNaN(diffr) || Double.isNaN(diffi))) {
						continue;
					}
					double err = diffr * diffr - comp;
					double temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;

					err = diffi * diffi - comp;
					temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;
				}
			} else {
				IndexIterator itw = w.getIterator();
				while (it.hasNext() && itw.hasNext()) {
					final double dw = w.getElementDoubleAbs(itw.index);
					double diffr = it.aDouble - it.bDouble;
					double diffi = data[it.aIndex] - bds.getElementDoubleAbs(it.bIndex + 1);
					if (ignoreNaNs && (Double.isNaN(diffr) || Double.isNaN(diffi))) {
						continue;
					}
					double err = diffr * diffr * dw - comp;
					double temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;

					err = diffi * diffi * dw - comp;
					temp = sum + err;
					comp = (temp - sum) - err;
					sum = temp;
				}
			}
		}
		return sum;
	}
}
