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

import org.jtransforms.dct.DoubleDCT_1D;
import org.jtransforms.dct.DoubleDCT_2D;
import org.jtransforms.dct.DoubleDCT_3D;
import org.jtransforms.dct.FloatDCT_1D;
import org.jtransforms.dct.FloatDCT_2D;
import org.jtransforms.dct.FloatDCT_3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO fast path for defaults?
//      axes and shape correspondence

/**
 * Class to hold methods to compute Discrete Cosine Transforms (DCT)
 * 
 */
public class DCT {
	/**
	 * Setup the logging facilities
	 */
	protected static final Logger logger = LoggerFactory.getLogger(DCT.class);

	/**
	 * forward 1D Discrete Cosine Transform (DCT-II)
	 * @param a dataset
	 * @return new dataset holding transform
	 */
	public static Dataset dct(final Dataset a) {
		return dct(a, a.getShapeRef()[a.getRank() - 1], -1);
	}

	/**
	 * forward 1D Discrete Cosine Transform (DCT-II)
	 * @param a dataset
	 * @param n number of points
	 * @param axis (negative numbers refer to axes from end, eg. -1 is last axis)
	 * @return new dataset holding transform
	 */
	public static Dataset dct(final Dataset a, final int n, int axis) {
		if (n <= 0) {
			logger.error("number of points should be greater than zero");
			throw new IllegalArgumentException("number of points should be greater than zero");
		}
		axis = a.checkAxis(axis);

		return dct1d(a, n, axis);
	}

	/**
	 * forward 2D Discrete Cosine Transform (DCT-II)
	 * @param a dataset
	 * @param s shape of DCT dataset (if null, use whole dataset)
	 * @param axes for DCT (if null, default as [-2,-1])
	 * @return new dataset holding transform
	 */
	public static Dataset dct2(final Dataset a, int[] s, int[] axes) {
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
			int[] shape = a.getShape();
			s[0] = shape[axes[0]];
			s[1] = shape[axes[1]];
		} else if (s.length < 2) {
			logger.error("shape should not have more than 2 dimensions");
			throw new IllegalArgumentException("shape should not have more than 2 dimensions");
		}
		return dctn(a, s, axes);
	}

	/**
	 * forward nD Discrete Cosine Transform (DCT-II)
	 * @param a dataset
	 * @param s shape of DCT dataset (if null, use whole dataset)
	 * @param axes for DCT (if null, default as [..., -1])
	 * @return new dataset holding transform
	 */
	public static Dataset dctn(final Dataset a, int[] s, int[] axes) {
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
				logger.error("shape of DCT should not have more dimensions than dataset");
				throw new IllegalArgumentException("shape of DCT should not have more dimensions than dataset");
			}
			if (axes == null) {
				axes = new int[s.length];
				for (int i = 0; i < s.length; i++)
					axes[i] = rank - s.length + i;
			} else {
				if (s.length != axes.length) {
					logger.error("shape of DCT should have same rank as axes");
					throw new IllegalArgumentException("shape of DCT should have same rank as axes");
				}
			}
		}

		if (s.length > 3) {
			logger.error("DCT across more than 3 dimensions are not supported");
			throw new IllegalArgumentException("DCT across more than 3 dimensions are not supported");
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
			result = dct1d(a, s[0], axes[0]);
			break;
		case 2:
			result = dct2d(a, s, axes);
			break;
		case 3:
			result = dct3d(a, s, axes);
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

	private static Dataset dct1d(final Dataset a, final int n, final int axis) {
		Dataset result = null;
		Dataset dest = null;

		int[] shape;
		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
			FloatDCT_1D ffft = new FloatDCT_1D(n);
			shape = a.getShape().clone();
			shape[axis] = n;
			result = new FloatDataset(shape);
			dest = new FloatDataset(new int[] {n});
			float[] fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.forward(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
			DoubleDCT_1D dfft = new DoubleDCT_1D(n);
			shape = a.getShape().clone();
			shape[axis] = n;
			result = new DoubleDataset(shape);
			dest = new DoubleDataset(new int[] {n});
			double[] ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.forward(ddata, true);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset dct2d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
			FloatDCT_2D ffft = new FloatDCT_2D(s[0], s[1]);
			float[] fdata = null;
			result = new FloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new FloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.forward(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
			DoubleDCT_2D dfft = new DoubleDCT_2D(s[0], s[1]);
			double[] ddata = null;
			result = new DoubleDataset(newShape(a.getShapeRef(), s, axes));
			dest = new DoubleDataset(s);
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.forward(ddata, true);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-float dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset dct3d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
			FloatDCT_3D ffft = new FloatDCT_3D(s[0], s[1], s[2]);

			float[] fdata = null;
			result = new FloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new FloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.forward(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
			DoubleDCT_3D dfft = new DoubleDCT_3D(s[0], s[1], s[2]);

			double[] ddata = null;
			result = new DoubleDataset(newShape(a.getShapeRef(), s, axes));
			dest = new DoubleDataset(s);
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.forward(ddata, true);
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
	 * inverse 1D Discrete Cosine Transform (DCT-III)
	 * @param a dataset
	 * @return new dataset holding transform
	 */
	public static Dataset idct(final Dataset a) {
		return idct(a, a.getShapeRef()[a.getRank() - 1], -1);
	}

	/**
	 * inverse 1D Discrete Cosine Transform (DCT-III)
	 * @param a dataset
	 * @param n number of points
	 * @param axis (negative numbers refer to axes from end, eg. -1 is last axis)
	 * @return new dataset holding transform
	 */
	public static Dataset idct(final Dataset a, final int n, int axis) {
		if (n <= 0) {
			logger.error("number of points should be greater than zero");
			throw new IllegalArgumentException("number of points should be greater than zero");
		}
		axis = a.checkAxis(axis);

		return idct1d(a, n, axis);
	}

	/**
	 * inverse 2D Discrete Cosine Transform (DCT-III)
	 * @param a dataset
	 * @param s shape of DCT dataset (if null, use whole dataset)
	 * @param axes for DCT (default as [-2,-1])
	 * @return new dataset holding transform
	 */
	public static Dataset idct2(final Dataset a, int[] s, int[] axes) {
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
			int[] shape = a.getShape();
			s[0] = shape[axes[0]];
			s[1] = shape[axes[1]];
		} else if (s.length < 2) {
			logger.error("shape should not have more than 2 dimensions");
			throw new IllegalArgumentException("shape should not have more than 2 dimensions");
		}
		return idctn(a, s, axes);
	}

	/**
	 * inverse nD Discrete Cosine Transform (DCT-III)
	 * @param a dataset
	 * @param s shape of DCT dataset (if null, use whole dataset)
	 * @param axes for DCT (if null, default as [..., -1])
	 * @return new dataset holding transform
	 */
	public static Dataset idctn(final Dataset a, int[] s, int[] axes) {
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
				logger.error("shape of DCT should not have more dimensions than dataset");
				throw new IllegalArgumentException("shape of DCT should not have more dimensions than dataset");
			}
			if (axes == null) {
				axes = new int[s.length];
				for (int i = 0; i < s.length; i++)
					axes[i] = rank - s.length + i;
			} else {
				if (s.length != axes.length) {
					logger.error("shape of DCT should have same rank as axes");
					throw new IllegalArgumentException("shape of DCT should have same rank as axes");
				}
			}
		}

		if (s.length > 3) {
			logger.error("DCT across more than 3 dimensions are not supported");
			throw new IllegalArgumentException("DCT across more than 3 dimensions are not supported");
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
			result = idct1d(a, s[0], axes[0]);
			break;
		case 2:
			result = idct2d(a, s, axes);
			break;
		case 3:
			result = idct3d(a, s, axes);
			break;
		}

		return result;
	}

	private static Dataset idct1d(final Dataset a, final int n, final int axis) {
		Dataset result = null;
		Dataset dest = null;

		int[] shape;
		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
			FloatDCT_1D ffft = new FloatDCT_1D(n);
			float[] fdata = null;
			shape = a.getShape();
			shape[axis] = n;
			result = new FloatDataset(shape);
			dest = new FloatDataset(new int[] {n});
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.inverse(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
			DoubleDCT_1D dfft = new DoubleDCT_1D(n);
			double[] ddata = null;
			shape = a.getShape();
			shape[axis] = n;
			result = new DoubleDataset(shape);
			dest = new DoubleDataset(new int[] {n});
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axis);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.inverse(ddata, true);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-complex dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset idct2d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
			FloatDCT_2D ffft = new FloatDCT_2D(s[0], s[1]);
			float[] fdata = null;
			result = new FloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new FloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.inverse(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
			DoubleDCT_2D dfft = new DoubleDCT_2D(s[0], s[1]);
			double[] ddata = null;
			result = new DoubleDataset(newShape(a.getShapeRef(), s, axes));
			dest = new DoubleDataset(s);
			ddata = (double[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(ddata, 0.);
				a.copyItemsFromAxes(pos, hit, dest);
				dfft.inverse(ddata, true);
				result.setItemsOnAxes(pos, hit, ddata);
			}
			break;
		default:
			logger.warn("Non-complex dataset not yet supported");
			break;
		}

		return result;
	}

	private static Dataset idct3d(final Dataset a, final int[] s, final int[] axes) {
		Dataset result = null;
		Dataset dest = null;

		PositionIterator pi;
		int[] pos;
		boolean[] hit;

		switch (a.getDtype()) {
		case Dataset.FLOAT32:
			FloatDCT_3D ffft = new FloatDCT_3D(s[0], s[1], s[2]);
			float[] fdata = null;
			result = new FloatDataset(newShape(a.getShapeRef(), s, axes));
			dest = new FloatDataset(s);
			fdata = (float[]) dest.getBuffer();
			pi = a.getPositionIterator(axes);
			pos = pi.getPos();
			hit = pi.getOmit();
			while (pi.hasNext()) {
				Arrays.fill(fdata, 0.f);
				a.copyItemsFromAxes(pos, hit, dest);
				ffft.inverse(fdata, true);
				result.setItemsOnAxes(pos, hit, fdata);
			}
			break;
		case Dataset.FLOAT64:
				DoubleDCT_3D dfft = new DoubleDCT_3D(s[0], s[1], s[2]);
				double[] ddata = null;
				result = new DoubleDataset(newShape(a.getShapeRef(), s, axes));
				dest = new DoubleDataset(s);
				ddata = (double[]) dest.getBuffer();
				pi = a.getPositionIterator(axes);
				pos = pi.getPos();
				hit = pi.getOmit();
				while (pi.hasNext()) {
					Arrays.fill(ddata, 0.);
					a.copyItemsFromAxes(pos, hit, dest);
					dfft.inverse(ddata, true);
					result.setItemsOnAxes(pos, hit, ddata);
				}
				break;
		default:
			logger.warn("Non-complex dataset not yet supported");
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
	public static Dataset dctshift(final Dataset a, int[] axes) {
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
	public static Dataset idctshift(final Dataset a, int[] axes) {
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

}
