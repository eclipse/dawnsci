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

import java.util.Arrays;

import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.fft.DoubleFFT_2D;
import org.jtransforms.fft.DoubleFFT_3D;
import org.jtransforms.fft.FloatFFT_1D;
import org.jtransforms.fft.FloatFFT_2D;
import org.jtransforms.fft.FloatFFT_3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO fast path for defaults?
//      axes and shape correspondence

/**
 * Class to hold methods to compute discrete Fourier transforms
 * 
 * Emulates numpy.fft
 */
public class FFT {
	/**
	 * Setup the logging facilities
	 */
	protected static final Logger logger = LoggerFactory.getLogger(FFT.class);

	/**
	 * forward 1D fast Fourier transform
	 * @param a dataset
	 * @return new dataset holding transform
	 */
	public static Dataset fft(final Dataset a) {
		return fft(a, a.getShapeRef()[a.getRank() - 1], -1);
	}

	/**
	 * forward 1D fast Fourier transform
	 * @param a dataset
	 * @param n number of points
	 * @param axis (negative numbers refer to axes from end, eg. -1 is last axis)
	 * @return new dataset holding transform
	 */
	public static Dataset fft(final Dataset a, final int n, int axis) {
		if (n <= 0) {
			logger.error("number of points should be greater than zero");
			throw new IllegalArgumentException("number of points should be greater than zero");
		}
		axis = a.checkAxis(axis);

		return fft1d(a, n, axis);
	}

	/**
	 * forward 2D fast Fourier transform
	 * @param a dataset
	 * @param s shape of FFT dataset (if null, use whole dataset)
	 * @param axes for FFT (if null, default as [-2,-1])
	 * @return new dataset holding transform
	 */
	public static Dataset fft2(final Dataset a, int[] s, int[] axes) {
		int rank = a.getRank();
		if (rank < 2) {
			logger.error("dataset should be at least 2 dimensional");
			throw new IllegalArgumentException("dataset should be at least 2 dimensional");
		}
		if (axes == null) {
			axes = new int[] {rank-2, rank-1};
		} else if (axes.length != 2) {
			logger.error("axes should have two entries");
			throw new IllegalArgumentException("axes should have two entries");
		}
		if (s == null) {
			s = new int[2];
			int[] shape = a.getShapeRef();
			for (int i = 0; i < 2; i++) {
				axes[i] = a.checkAxis(axes[i]);

				s[i] = shape[axes[i]];
			}
		} else if (s.length < 2) {
			logger.error("shape should not have more than 2 dimensions");
			throw new IllegalArgumentException("shape should not have more than 2 dimensions");
		}
		return fftn(a, s, axes);
	}

	/**
	 * forward nD fast Fourier transform
	 * @param a dataset
	 * @param s shape of FFT dataset (if null, use whole dataset)
	 * @param axes for FFT (if null, default as [..., -1])
	 * @return new dataset holding transform
	 */
	public static Dataset fftn(final Dataset a, int[] s, int[] axes) {
		int[] shape = a.getShape();
		int rank = shape.length;
		Dataset result = null;

		if (s == null) {
			if (axes == null) {
				s = shape;
				axes = new int[rank];
				for (int i = 0; i < rank; i++)
					axes[i] = i;
			} else {
				s = new int[axes.length];
				Arrays.sort(axes);
				for (int i = 0; i < axes.length; i++) {
					axes[i] = a.checkAxis(axes[i]);

					s[i] = shape[axes[i]];
				}
			}
		} else {
			if (s.length > rank) {
				logger.error("shape of FFT should not have more dimensions than dataset");
				throw new IllegalArgumentException("shape of FFT should not have more dimensions than dataset");
			}
			if (axes == null) {
				axes = new int[s.length];
				for (int i = 0; i < s.length; i++)
					axes[i] = rank - s.length + i;
			} else {
				if (s.length != axes.length) {
					logger.error("shape of FFT should have same rank as axes");
					throw new IllegalArgumentException("shape of FFT should have same rank as axes");
				}
			}
		}

		if (s.length > 3) {
			logger.error("Fourier transform across more than 3 dimensions are not supported");
			throw new IllegalArgumentException("Fourier transform across more than 3 dimensions are not supported");
		}

		for (int i = 0; i < axes.length; i++) {
			if (s[i] <= 0) {
				logger.error("dimensions should be greater than zero");
				throw new IllegalArgumentException("dimensions should be greater than zero");
			}
			axes[i] = a.checkAxis(axes[i]);
		}

		switch (s.length) {
		case 1:
			result = fft1d(a, s[0], axes[0]);
			break;
		case 2:
			result = fft2d(a, s, axes);
			break;
		case 3:
			result = fft3d(a, s, axes);
			break;
		}

		return result;
	}

	private static int[] newShape(final int[] shape, final int[] s, final int[] axes) {
		int[] nshape = shape.clone();

		for (int i = 0; i < s.length; i++) {
			nshape[axes[i]] = s[i];
		}
		return nshape;
	}

	private static Dataset fft1d(final Dataset a, final int n, final int axis) {
		Dataset result = null;
		Dataset dest = null;

		int[] shape;
		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
		case Dataset.COMPLEX64:
			FloatFFT_1D ffft = new FloatFFT_1D(n);
			shape = a.getShape();
			shape[axis] = n;
			result = new ComplexFloatDataset(shape);
			dest = new ComplexFloatDataset(new int[] {2*n});
			float[] fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.complexForward(fdata);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
		case Dataset.COMPLEX128:
			DoubleFFT_1D dfft = new DoubleFFT_1D(n);
			shape = a.getShape();
			shape[axis] = n;
			result = new ComplexDoubleDataset(shape);
			dest = new ComplexDoubleDataset(new int[] {2*n});
			double[] ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.complexForward(ddata);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset fft2d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
		case Dataset.COMPLEX64:
			FloatFFT_2D ffft = new FloatFFT_2D(s[0], s[1]);
			float[] fdata = null;
			result = new ComplexFloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexFloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.complexForward(fdata);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
		case Dataset.COMPLEX128:
			DoubleFFT_2D dfft = new DoubleFFT_2D(s[0], s[1]);
			double[] ddata = null;
			result = new ComplexDoubleDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexDoubleDataset(s);
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.complexForward(ddata);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset fft3d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
		case Dataset.COMPLEX64:
			FloatFFT_3D ffft = new FloatFFT_3D(s[0], s[1], s[2]);

			float[] fdata = null;
			result = new ComplexFloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexFloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.complexForward(fdata);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
		case Dataset.COMPLEX128:
			DoubleFFT_3D dfft = new DoubleFFT_3D(s[0], s[1], s[2]);

			double[] ddata = null;
			result = new ComplexDoubleDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexDoubleDataset(s);
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.complexForward(ddata);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	/**
	 * inverse 1D fast Fourier transform
	 * @param a dataset
	 * @return new dataset holding transform
	 */
	public static Dataset ifft(final Dataset a) {
		return ifft(a, a.getShapeRef()[a.getRank() - 1], -1);
	}

	/**
	 * inverse 1D fast Fourier transform
	 * @param a dataset
	 * @param n number of points
	 * @param axis (negative numbers refer to axes from end, eg. -1 is last axis)
	 * @return new dataset holding transform
	 */
	public static Dataset ifft(final Dataset a, final int n, int axis) {
		if (n <= 0) {
			logger.error("number of points should be greater than zero");
			throw new IllegalArgumentException("number of points should be greater than zero");
		}
		axis = a.checkAxis(axis);

		return ifft1d(a, n, axis);
	}

	/**
	 * inverse 2D fast Fourier transform
	 * @param a dataset
	 * @param s shape of FFT dataset (if null, use whole dataset)
	 * @param axes for FFT (default as [-2,-1])
	 * @return new dataset holding transform
	 */
	public static Dataset ifft2(final Dataset a, int[] s, int[] axes) {
		int rank = a.getRank();
		if (rank < 2) {
			logger.error("dataset should be at least 2 dimensional");
			throw new IllegalArgumentException("dataset should be at least 2 dimensional");
		}
		if (axes == null) {
			axes = new int[] {rank-2, rank-1};
		} else if (axes.length != 2) {
			logger.error("axes should have two entries");
			throw new IllegalArgumentException("axes should have two entries");
		}
		if (s == null) {
			s = new int[2];
			int[] shape = a.getShapeRef();
			for (int i = 0; i < 2; i++) {
				axes[i] = a.checkAxis(axes[i]);
				s[i] = shape[axes[i]];
			}
		} else if (s.length < 2) {
			logger.error("shape should not have more than 2 dimensions");
			throw new IllegalArgumentException("shape should not have more than 2 dimensions");
		}
		return ifftn(a, s, axes);
	}

	/**
	 * inverse nD fast Fourier transform
	 * @param a dataset
	 * @param s shape of FFT dataset (if null, use whole dataset)
	 * @param axes for FFT (if null, default as [..., -1])
	 * @return new dataset holding transform
	 */
	public static Dataset ifftn(final Dataset a, int[] s, int[] axes) {
		int[] shape = a.getShape();
		int rank = shape.length;
		Dataset result = null;

		if (s == null) {
			if (axes == null) {
				s = shape;
				axes = new int[rank];
				for (int i = 0; i < rank; i++)
					axes[i] = i;
			} else {
				s = new int[axes.length];
				Arrays.sort(axes);
				for (int i = 0; i < axes.length; i++) {
					axes[i] = a.checkAxis(axes[i]);
					s[i] = shape[axes[i]];
				}
			}
		} else {
			if (s.length > rank) {
				logger.error("shape of FFT should not have more dimensions than dataset");
				throw new IllegalArgumentException("shape of FFT should not have more dimensions than dataset");
			}
			if (axes == null) {
				axes = new int[s.length];
				for (int i = 0; i < s.length; i++)
					axes[i] = rank - s.length + i;
			} else {
				if (s.length != axes.length) {
					logger.error("shape of FFT should have same rank as axes");
					throw new IllegalArgumentException("shape of FFT should have same rank as axes");
				}
			}
		}

		if (s.length > 3) {
			logger.error("Fourier transform across more than 3 dimensions are not supported");
			throw new IllegalArgumentException("Fourier transform across more than 3 dimensions are not supported");
		}

		for (int i = 0; i < axes.length; i++) {
			if (s[i] <= 0) {
				logger.error("dimensions should be greater than zero");
				throw new IllegalArgumentException("dimensions should be greater than zero");
			}
			axes[i] = a.checkAxis(axes[i]);
		}

		switch (s.length) {
		case 1:
			result = ifft1d(a, s[0], axes[0]);
			break;
		case 2:
			result = ifft2d(a, s, axes);
			break;
		case 3:
			result = ifft3d(a, s, axes);
			break;
		}

		return result;
	}

	private static Dataset ifft1d(final Dataset a, final int n, final int axis) {
		Dataset result = null;
		Dataset dest = null;

		int[] shape;
		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
		case Dataset.COMPLEX64:
			FloatFFT_1D ffft = new FloatFFT_1D(n);
			float[] fdata = null;
			shape = a.getShape();
			shape[axis] = n;
			result = new ComplexFloatDataset(shape);
			dest = new ComplexFloatDataset(new int[] {n});
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.complexInverse(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
		case Dataset.COMPLEX128:
			DoubleFFT_1D dfft = new DoubleFFT_1D(n);
			double[] ddata = null;
			shape = a.getShape();
			shape[axis] = n;
			result = new ComplexDoubleDataset(shape);
			dest = new ComplexDoubleDataset(new int[] {n});
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.complexInverse(ddata, true);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset ifft2d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
		case Dataset.COMPLEX64:
			FloatFFT_2D ffft = new FloatFFT_2D(s[0], s[1]);
			float[] fdata = null;
			result = new ComplexFloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexFloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.complexInverse(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
		case Dataset.COMPLEX128:
			DoubleFFT_2D dfft = new DoubleFFT_2D(s[0], s[1]);
			double[] ddata = null;
			result = new ComplexDoubleDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexDoubleDataset(s);
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.complexInverse(ddata, true);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset ifft3d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
		case Dataset.COMPLEX64:
			FloatFFT_3D ffft = new FloatFFT_3D(s[0], s[1], s[2]);
			float[] fdata = null;
			result = new ComplexFloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new ComplexFloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.complexInverse(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
		case Dataset.COMPLEX128:
				DoubleFFT_3D dfft = new DoubleFFT_3D(s[0], s[1], s[2]);
				double[] ddata = null;
				result = new ComplexDoubleDataset(newShape(a.getShapeRef(), s, axes));
				dest = new ComplexDoubleDataset(s);
				ddata = (double[]) dest.getBuffer();
				pi = a.getPositionIterator(axes);
				pos = pi.getPos();
				hit = pi.getOmit();
				while (pi.hasNext()) {
					Arrays.fill(ddata, 0.);
					a.copyItemsFromAxes(pos, hit, dest);
					dfft.complexInverse(ddata, true);
					result.setItemsOnAxes(pos, hit, ddata);
				}
				break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	/**
	 * Shift zero-frequency component to centre of dataset
	 * @param a
	 * @param axes (if null, then shift all axes)
	 * @return shifted dataset
	 */
	public static Dataset fftshift(final Dataset a, int[] axes) {
		int alen;
		if (axes == null) {
			alen = a.getRank();
			axes = new int[alen];
			for (int i = 0; i < alen; i++)
				axes[i] = i;
		} else {
			alen = axes.length;
			for (int i = 0; i < alen; i++)
				axes[i] = a.checkAxis(axes[i]);
		}

		Dataset result = a;
		int[] indices;
		for (int i = 0; i < alen; i++) {
			int axis = axes[i];
			int n = a.getShapeRef()[axis];
			int p = (n+1)/2;
			logger.info("Shift {} by {}", axis, p);
			indices = new int[n];
			for (int j = 0; j < n; j++) {
				if (j < n - p)
					indices[j] = p + j;
				else
					indices[j] = j - n + p;
			}

			result = DatasetUtils.take(result, indices, axis);
		}

		return result;
	}

	/**
	 * Shift zero-frequency component to centre of dataset
	 * @param a
	 * @param axes (if null, then shift all axes)
	 * @return shifted dataset
	 */
	public static Dataset ifftshift(final Dataset a, int[] axes) {
		int alen;
		if (axes == null) {
			alen = a.getRank();
			axes = new int[alen];
			for (int i = 0; i < alen; i++)
				axes[i] = i;
		} else {
			alen = axes.length;
			for (int i = 0; i < alen; i++)
				axes[i] = a.checkAxis(axes[i]);
		}

		Dataset result = a;
		int[] indices;
		for (int i = 0; i < alen; i++) {
			int axis = axes[i];
			int n = a.getShapeRef()[axis];
			int p = n - (n+1)/2;
			logger.info("Shift {} by {}", axis, p);
			indices = new int[n];
			for (int j = 0; j < n; j++) {
				if (j < n - p)
					indices[j] = p + j;
				else
					indices[j] = j - n + p;
			}

			result = DatasetUtils.take(result, indices, axis);
		}

		return result;
	}

	/**
	 * Discrete FFT sample frequencies
	 * @param n number of samples
	 * @param d sample spacing
	 * @return frequencies
	 */
	public static Dataset sampleFrequencies(int n, double d) {
		int hn = n/2;
		return DatasetUtils.roll(DoubleDataset.createRange(n).isubtract(hn).imultiply(1/(d*n)), n - hn, null);
	}
}
