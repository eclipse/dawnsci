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


/**
 * Signal processing class
 */
public class Signal {

	private static int[] paddedShape(final int[] ashape, final int[] bshape, final int[] axes) {
		int[] s = ashape.clone();
		if (axes == null) {
			 // pad all axes
			for (int i = 0; i < s.length; i++) {
				s[i] += bshape[i] - 1; // pad 
			}
		} else {
			 // pad chosen axes
			for (int i = 0; i < s.length; i++) {
				int j = 0;
				for (; j < axes.length; j++) {
					if (i == axes[j])
						break;
				}
				if (j < axes.length) {
					s[i] += bshape[i] - 1;
				}
			}
		}
		return s;
	}

	/**
	 * @param c
	 * @param a
	 * @param b
	 * @return slice of c that has shape a but is offset by middle of shape b
	 */
	private static Dataset getSame(Dataset c, int[] a, int[] b) {
		int rank = a.length;
		int[] start = new int[rank];
		int[] stop = new int[rank];
		for (int i = 0; i < start.length; i++) {
			start[i] = Math.min(a[i], b[i]) /2;
			stop[i] = Math.max(a[i], b[i]) + start[i];
		}
		return c.getSlice(start, stop, null);
	}

	/**
	 * @param c
	 * @param a
	 * @param b
	 * @return slice of c that is the overlapping portion of shapes a and b
	 */
	private static Dataset getValid(Dataset c, int[] a, int[] b) {
		int rank = a.length;
		int[] start = new int[rank];
		int[] stop = new int[rank];
		for (int i = 0; i < start.length; i++) {
			int l = Math.max(a[i], b[i]) - Math.min(a[i], b[i]) + 1;
			start[i] = (c.getShapeRef()[i] - l)/2;
			stop[i] = start[i] + l;
		}
		return c.getSlice(start, stop, null);
	}

	/**
	 * Perform a linear convolution of two input datasets 
	 * @param f
	 * @param g
	 * @param axes
	 * @return linear convolution
	 */
	public static Dataset convolve(final Dataset f, final Dataset g, final int[] axes) {
		// compute using circular (DFT) algorithm
		// to get a linear version, need to pad out axes to f-axes + g-axes - 1 before DFTs
		
		if (f.getRank() != g.getRank()) {
			f.checkCompatibility(g);
		}

		Dataset c = null, d = null;
		int[] s = paddedShape(f.getShapeRef(), g.getShapeRef(), axes);
		c = FFT.fftn(f, s, axes);
		d = FFT.fftn(g, s, axes);
		c = Maths.multiply(c, d);

		Dataset conv = FFT.ifftn(c, s, axes);
		if (f.isComplex() || g.isComplex())
			return conv;
		return conv.real();
	}

	/**
	 * Perform a linear convolution of two input datasets 
	 * @param f
	 * @param g
	 * @param axes
	 * @return central portion of linear convolution with same shape as f
	 */
	public static Dataset convolveToSameShape(final Dataset f, final Dataset g, final int[] axes) {
		return getSame(convolve(f, g, axes), f.getShapeRef(), g.getShapeRef());
	}

	/**
	 * Perform a linear convolution of two input datasets 
	 * @param f
	 * @param g
	 * @param axes
	 * @return overlapping portion of linear convolution
	 */
	public static Dataset convolveForOverlap(final Dataset f, final Dataset g, final int[] axes) {
		return getValid(convolve(f, g, axes), f.getShapeRef(), g.getShapeRef());
	}

	/**
	 * Perform a linear auto-correlation on a dataset
	 * @param f
	 * @param axes
	 * @return linear auto-correlation
	 */
	public static Dataset correlate(final Dataset f, final int[] axes) {
		return correlate(f, f, axes);
	}

	/**
	 * Perform a linear cross-correlation on two input datasets
	 * @param f
	 * @param g
	 * @param axes
	 * @return linear cross-correlation (centre-shifted)
	 */
	public static Dataset correlate(final Dataset f, final Dataset g, int[] axes) {
		if (f.getRank() != g.getRank()) {
			f.checkCompatibility(g);
		}

		Dataset c = null, d = null;
		int[] s = paddedShape(f.getShapeRef(), g.getShapeRef(), axes);
		
		c = FFT.fftn(f, s, axes);
		d = FFT.fftn(g, s, axes);
		c = Maths.multiply(c, Maths.conjugate(d));

		Dataset corr = FFT.ifftn(c, s, axes);
		if (!f.isComplex() && !g.isComplex())
			corr = corr.real();

		int rank = s.length;
		int alen;
		if (axes == null) {
			alen = rank;
			axes = new int[alen];
			for (int i = 0; i < alen; i++)
				axes[i] = i;
		} else {
			alen = axes.length;
			for (int i = 0; i < alen; i++) {
				int a = axes[i];
				if (a < 0)
					a += rank;
				if (a < 0 || a >= rank)
					throw new IndexOutOfBoundsException("Axis " + a + " given is out of range [0, " + rank + ")");

				axes[i] = a;
			}
		}
		for (int a : axes) {
			int l = Math.min(f.getShapeRef()[a], g.getShapeRef()[a]);
			if (l == f.getShapeRef()[a]) {
				l = -l + 1;
			}
			corr = DatasetUtils.roll(corr, l-1, a);
		}
		
		return corr;
	}

	/**
	 * Perform a linear cross-correlation on two input datasets
	 * @param f
	 * @param g
	 * @param axes
	 * @return central portion of linear cross-correlation with same shape as f
	 */
	public static Dataset correlateToSameShape(final Dataset f, final Dataset g, final int[] axes) {
		return getSame(correlate(f, g, axes), f.getShapeRef(), g.getShapeRef());
	}

	/**
	 * Perform a linear cross-correlation on two input datasets
	 * @param f
	 * @param g
	 * @param axes
	 * @return overlapping portion of linear cross-correlation
	 */
	public static Dataset correlateForOverlap(final Dataset f, final Dataset g, final int[] axes) {
		return getValid(correlate(f, g, axes), f.getShapeRef(), g.getShapeRef());
	}

	/**
	 * Perform a linear phase cross-correlation on two input datasets
	 * 
	 * The inverse of the normalized cross-power spectrum is {@code IFFT(F/G)}
	 * 
	 * @param f
	 * @param g
	 * @param axes
	 * @param includeInverse 
	 * @return linear phase cross-correlation and inverse of the normalized cross-power spectrum
	 */
	public static List<Dataset> phaseCorrelate(final Dataset f, final Dataset g, final int[] axes, boolean includeInverse) {
		Dataset c = null, d = null;
		int[] s = paddedShape(f.getShapeRef(), g.getShapeRef(), axes);
		c = FFT.fftn(f, s, axes);
		d = FFT.fftn(g, s, axes);
		c.idivide(d);

		Dataset corr;
		List<Dataset> results = new ArrayList<Dataset>();

		d = Maths.phaseAsComplexNumber(c, true);

		corr = FFT.ifftn(d, s, axes);
		if (f.isComplex() || g.isComplex())
			results.add(corr);
		else
			results.add(corr.real());

		if (includeInverse) {
			corr = FFT.ifftn(c, s, axes);
			if (f.isComplex() || g.isComplex())
				results.add(corr);
			else
				results.add(corr.real());
		}

		return results;
	}

	/**
	 * A rectangular (boxcar or Dirichlet) window
	 * @param n
	 * @return window
	 */
	public static Dataset rectangularWindow(int n) {
		return DatasetFactory.ones(new int[] {n}, Dataset.FLOAT64);
	}

	/**
	 * A triangular window
	 * @param n
	 * @return window
	 */
	public static Dataset triangularWindow(int n) {
		DoubleDataset w = new DoubleDataset(n);
		double f = 2./(n+1);
		double o = f*(n-1)*0.5;
		for (int i = 0; i < n; i++) {
			w.setAbs(i, 1 - f*Math.abs(i-o));
		}
		return w;
	}

	/**
	 * A Bartlett window
	 * @param n
	 * @return window
	 */
	public static Dataset bartlettWindow(int n) {
		DoubleDataset w = new DoubleDataset(n);
		double f = 2./(n-1);
		double o = f*(n-1)*0.5;
		for (int i = 0; i < n; i++) {
			w.setAbs(i, 1 - f*Math.abs(i-o));
		}
		return w;
	}

	/**
	 * A Hann window
	 * @param n
	 * @return window
	 */
	public static Dataset hannWindow(int n) {
		double a = 0.5;
		return hammingWindow(n, a, 1-a);
	}

	/**
	 * A Hamming window
	 * @param n
	 * @return window
	 */
	public static Dataset hammingWindow(int n) {
		double a = 0.54;
		return hammingWindow(n, a, 1-a);
	}

	/**
	 * A generalized Hamming window
	 * @param n
	 * @param a
	 * @param b
	 * @return window
	 */
	public static Dataset hammingWindow(int n, double a, double b) {
		DoubleDataset w = new DoubleDataset(n);
		double f = 2*Math.PI/(n-1);
		for (int i = 0; i < n; i++) {
			w.setAbs(i, a - b*Math.cos(i*f));
		}
		return w;
	}
}
