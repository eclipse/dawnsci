/*-
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset;

import java.util.List;

import org.eclipse.dawnsci.analysis.dataset.impl.BooleanDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Comparisons;
import org.eclipse.dawnsci.analysis.dataset.impl.Comparisons.Monotonicity;
import org.eclipse.dawnsci.analysis.dataset.impl.ComplexDoubleDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.IntegerDataset;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComparisonsTest {
	Dataset a, b, z;

	@Before
	public void setUpClass() {
		a = new DoubleDataset(new double[] { 0, 1, 3, 5, -7, -9 });
		b = new DoubleDataset(new double[] { 0.01, 1.2, 2.9, 5, -7.1, -9 });
		z = new ComplexDoubleDataset(new double[] { 0.01, 1.2, 2.5, 5, -7.1, -9, 2.5, 0 });
	}

	@Test
	public void testEqualTo() {
		BooleanDataset c = Comparisons.equalTo(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {false, false, false, true, false, true});
		AbstractDatasetTest.checkDatasets(c, d);

		AbstractDatasetTest.checkDatasets(Comparisons.equalTo(3, a), new BooleanDataset(
				new boolean[] {false, false, true, false, false, false}));

		DoubleDataset ta = new DoubleDataset(new int [] {20, 10});
		ta.fill(Double.NaN);
		DoubleDataset tb = new DoubleDataset(new int [] {20, 10});
		tb.fill(Double.NaN);
		
		BooleanDataset bd = new BooleanDataset(ta.getShape());
		bd.fill(Boolean.FALSE);
		AbstractDatasetTest.checkDatasets(Comparisons.equalTo(ta, tb), bd);
		ta.fill(Double.POSITIVE_INFINITY);
		tb.fill(Double.POSITIVE_INFINITY);
		bd.fill(Boolean.TRUE);
		AbstractDatasetTest.checkDatasets(Comparisons.equalTo(ta, tb), bd);

		c = Comparisons.equalTo(new DoubleDataset(new int[]{}).fill(1), 1);
		AbstractDatasetTest.checkDatasets(c, new BooleanDataset(new boolean[] {true}));

		c = Comparisons.equalTo(a, 3);
		AbstractDatasetTest.checkDatasets(c, new BooleanDataset(new boolean[] {false, false, true, false, false, false}));
		c = Comparisons.equalTo(3, a);
		AbstractDatasetTest.checkDatasets(c, new BooleanDataset(new boolean[] {false, false, true, false, false, false}));

		c = Comparisons.equalTo(z, 2.5);
		AbstractDatasetTest.checkDatasets(c, new BooleanDataset(new boolean[] {false, false, false, true}));
		c = Comparisons.equalTo(2.5, z);
		AbstractDatasetTest.checkDatasets(c, new BooleanDataset(new boolean[] {false, false, false, true}));
	}

	@Test
	public void testAlmostEqualTo() {
		BooleanDataset c = Comparisons.almostEqualTo(a, b, 0.1, 1e-3);
		BooleanDataset d = new BooleanDataset(new boolean[] {false, false, true, true, true, true});
		AbstractDatasetTest.checkDatasets(c, d);

		AbstractDatasetTest.checkDatasets(Comparisons.almostEqualTo(a, 3, 0.1, 1e-3), new BooleanDataset(
				new boolean[] {false, false, true, false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.almostEqualTo(3, a, 0.1, 1e-3), new BooleanDataset(
				new boolean[] {false, false, true, false, false, false}));

		c = Comparisons.almostEqualTo(z, 2.5, 0.1, 1e-3);
		AbstractDatasetTest.checkDatasets(c, new BooleanDataset(new boolean[] {false, false, false, true}));
		c = Comparisons.almostEqualTo(2.5, z, 0.1, 1e-3);
	}

	@Test
	public void testAllCloseTo() {
		Assert.assertFalse(Comparisons.allCloseTo(a, b, 0.1, 1e-3));

		Assert.assertTrue(Comparisons.allCloseTo(a, b, 0.1, 2e-1));

		Assert.assertFalse(Comparisons.allCloseTo(z, 2.5, 0.1, 1e-3));
		Assert.assertFalse(Comparisons.allCloseTo(2.5, z, 0.1, 1e-3));
	}

	@Test
	public void testGreaterThan() {
		BooleanDataset c = Comparisons.greaterThan(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {false, false, true, false, true, false});
		AbstractDatasetTest.checkDatasets(c, d);

		AbstractDatasetTest.checkDatasets(Comparisons.greaterThan(3, a), new BooleanDataset(
				new boolean[] {true, true, false, false, true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.greaterThan(a, 3), new BooleanDataset(
				new boolean[] {false, false, false, true, false, false}));
	}

	@Test
	public void testGreaterThanOrEqualTo() {
		BooleanDataset c = Comparisons.greaterThanOrEqualTo(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {false, false, true, true, true, true});
		AbstractDatasetTest.checkDatasets(c, d);

		AbstractDatasetTest.checkDatasets(Comparisons.greaterThanOrEqualTo(3, a), new BooleanDataset(
				new boolean[] {true, true, true, false, true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.greaterThanOrEqualTo(a, 3), new BooleanDataset(
				new boolean[] {false, false, true, true, false, false}));
	}

	@Test
	public void testLessThan() {
		BooleanDataset c = Comparisons.lessThan(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {true, true, false, false, false, false});
		AbstractDatasetTest.checkDatasets(c, d);

		AbstractDatasetTest.checkDatasets(Comparisons.lessThan(3, a), new BooleanDataset(
				new boolean[] {false, false, false, true, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.lessThan(a, 3), new BooleanDataset(
				new boolean[] {true, true, false, false, true, true}));
	}

	@Test
	public void testLessThanOrEqualTo() {
		BooleanDataset c = Comparisons.lessThanOrEqualTo(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {true, true, false, true, false, true});
		AbstractDatasetTest.checkDatasets(c, d);

		AbstractDatasetTest.checkDatasets(Comparisons.lessThanOrEqualTo(3, a), new BooleanDataset(
				new boolean[] {false, false, true, true, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.lessThanOrEqualTo(a, 3), new BooleanDataset(
				new boolean[] {true, true, true, false, true, true}));
	}

	@Test
	public void testWithinRange() {
		BooleanDataset c = Comparisons.withinRange(b, -8, 2);
		BooleanDataset d = new BooleanDataset(new boolean[] {true, true, false, false, true, false});
		AbstractDatasetTest.checkDatasets(c, d);
	}

	@Test
	public void testAllTrue() {
		Assert.assertFalse(Comparisons.allTrue(a));
		Assert.assertTrue(Comparisons.allTrue(b));
		Dataset c = a.clone().reshape(2, 3);
		AbstractDatasetTest.checkDatasets(Comparisons.allTrue(c, 0), new BooleanDataset(new boolean[] {false, true, true}));
		Dataset d = b.clone().reshape(2, 3);
		AbstractDatasetTest.checkDatasets(Comparisons.allTrue(d, 1), new BooleanDataset(new boolean[] {true, true}));
	}

	@Test
	public void testAnyTrue() {
		Assert.assertTrue(Comparisons.anyTrue(a));
		Assert.assertTrue(Comparisons.anyTrue(b));
		Assert.assertFalse(Comparisons.anyTrue(new DoubleDataset(new double[] {0, 0})));
		Dataset c = a.clone().reshape(2, 3);
		AbstractDatasetTest.checkDatasets(Comparisons.anyTrue(c, 0), new BooleanDataset(new boolean[] {true, true, true}));
		Dataset d = b.clone().reshape(2, 3);
		AbstractDatasetTest.checkDatasets(Comparisons.anyTrue(d, 1), new BooleanDataset(new boolean[] {true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.anyTrue(new DoubleDataset(new double[] {0, 0, 0, 1}).reshape(2,2), 1),
				new BooleanDataset(new boolean[] {false, true}));
	}

	@Test
	public void testNot() {
		AbstractDatasetTest.checkDatasets(Comparisons.logicalNot(a), new BooleanDataset(
				new boolean[] {true, false, false, false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.logicalNot(b), new BooleanDataset(
				new boolean[] {false, false, false, false, false, false}));
	}

	@Test
	public void testAnd() {
		BooleanDataset c = Comparisons.logicalAnd(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {false, true, true, true, true, true});
		AbstractDatasetTest.checkDatasets(c, d);
	}

	@Test
	public void testOr() {
		BooleanDataset c = Comparisons.logicalOr(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {true, true, true, true, true, true});
		AbstractDatasetTest.checkDatasets(c, d);
	}

	@Test
	public void testXor() {
		BooleanDataset c = Comparisons.logicalXor(a, b);
		BooleanDataset d = new BooleanDataset(new boolean[] {true, false, false, false, false, false});
		AbstractDatasetTest.checkDatasets(c, d);
	}

	@Test
	public void testNonZero() {
		Dataset c = a.clone().reshape(2, 3);
		List<IntegerDataset> e = Comparisons.nonZero(c);
		AbstractDatasetTest.checkDatasets(e.get(0), new IntegerDataset(new int[] {0, 0, 1, 1, 1}, null));
		AbstractDatasetTest.checkDatasets(e.get(1), new IntegerDataset(new int[] {1, 2, 0, 1, 2}, null));
	}

	@Test
	public void testFlags() {
		Dataset c;

		c = DatasetFactory.createFromObject(new int[] {0, -1, 1});
		AbstractDatasetTest.checkDatasets(Comparisons.isFinite(c), new BooleanDataset(new boolean[] {true, true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.isInfinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isPositiveInfinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNegativeInfinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNaN(c), new BooleanDataset(new boolean[] {false, false, false}));

		c = DatasetFactory.createFromObject(new double[] {0, -1, 1});
		AbstractDatasetTest.checkDatasets(Comparisons.isFinite(c), new BooleanDataset(new boolean[] {true, true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.isInfinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isPositiveInfinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNegativeInfinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNaN(c), new BooleanDataset(new boolean[] {false, false, false}));

		c = DatasetFactory.createFromObject(new double[] {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY});
		AbstractDatasetTest.checkDatasets(Comparisons.isFinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isInfinite(c), new BooleanDataset(new boolean[] {false, true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.isPositiveInfinite(c), new BooleanDataset(new boolean[] {false, false, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNegativeInfinite(c), new BooleanDataset(new boolean[] {false, true, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNaN(c), new BooleanDataset(new boolean[] {true, false, false}));

		c = DatasetFactory.createFromObject(new double[] {Double.NaN, -Double.POSITIVE_INFINITY, -Double.NEGATIVE_INFINITY});
		AbstractDatasetTest.checkDatasets(Comparisons.isFinite(c), new BooleanDataset(new boolean[] {false, false, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isInfinite(c), new BooleanDataset(new boolean[] {false, true, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.isPositiveInfinite(c), new BooleanDataset(new boolean[] {false, false, true}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNegativeInfinite(c), new BooleanDataset(new boolean[] {false, true, false}));
		AbstractDatasetTest.checkDatasets(Comparisons.isNaN(c), new BooleanDataset(new boolean[] {true, false, false}));
	}

	@Test
	public void testNans() {
		double n = Double.NaN;
		double[] a = {-4.34, -1.34, 21.34};
		double l = -2.;
		double h = 15.4;
		for (double x : a) {
			System.err.println((x >= l && x <= h) + "\t" + (x <= h) + " = " + ((Double.isNaN(n) || x >= n) && x <= h) + "\t" +  (x >= l) + " = " + (x >= l && (Double.isNaN(n) || x <= n)) + "\t" + (x >= n && x <= n));
		}

		System.err.println(Double.isNaN(n));
		System.err.println(Float.isNaN((float) n));
		float f = Float.NaN;
		System.err.println(Double.isNaN(f));
	}

	@Test
	public void testIsMonotonic() {
		Assert.assertTrue(Comparisons.isMonotonic(1));
		Assert.assertFalse(Comparisons.isMonotonic(Double.NaN));
		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY));

		Assert.assertFalse(Comparisons.isMonotonic(new double[] {Double.NaN, Double.NaN}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}));

		Assert.assertFalse(Comparisons.isMonotonic(new double[] {Double.NaN, Double.NaN, Double.NaN}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NaN, Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}));

		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.NaN, Double.NaN}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}));
		Assert.assertFalse(Comparisons.isMonotonic(new double[] {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY}));

		Assert.assertTrue(Comparisons.isMonotonic(new double[] {0, 1, 1, 2, 6, Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isMonotonic(new double[] {0, -1, -1, -2, -6, Double.NaN, Double.NEGATIVE_INFINITY}));

		Assert.assertFalse(Comparisons.isMonotonic(new double[] {0, 1, 0.5, 2, 6, Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertFalse(Comparisons.isMonotonic(new double[] {0, 0, -1, -0.2, -6, Double.NaN, Double.NEGATIVE_INFINITY}));

		Assert.assertFalse(Comparisons.isStrictlyMonotonic(new double[] {0, 1, 1, 2, 6, Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertFalse(Comparisons.isStrictlyMonotonic(new double[] {0, 0, -1, -2, -6, Double.NaN, Double.NEGATIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isStrictlyMonotonic(new double[] {0, 1, 1.5, 2, 6, Double.NaN, Double.POSITIVE_INFINITY}));
		Assert.assertTrue(Comparisons.isStrictlyMonotonic(new double[] {0.5, 0, -1, -2, -6, Double.NaN, Double.NEGATIVE_INFINITY}));

		Assert.assertTrue(Comparisons.isMonotonic(1, Monotonicity.NOT_ORDERED));
		Assert.assertTrue(Comparisons.isMonotonic(1, Monotonicity.ALL_EQUAL));
		Assert.assertTrue(Comparisons.isMonotonic(1, Monotonicity.STRICTLY_DECREASING));
		Assert.assertTrue(Comparisons.isMonotonic(1, Monotonicity.NONINCREASING));
		Assert.assertTrue(Comparisons.isMonotonic(1, Monotonicity.NONDECREASING));
		Assert.assertTrue(Comparisons.isMonotonic(1, Monotonicity.STRICTLY_INCREASING));

		Assert.assertTrue(Comparisons.isMonotonic(Double.NaN, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(Double.NaN, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(Double.NaN, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(Double.NaN, Monotonicity.NONINCREASING));
		Assert.assertFalse(Comparisons.isMonotonic(Double.NaN, Monotonicity.NONDECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(Double.NaN, Monotonicity.STRICTLY_INCREASING));

		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY, Monotonicity.NOT_ORDERED));
		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY, Monotonicity.ALL_EQUAL));
		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY, Monotonicity.STRICTLY_DECREASING));
		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY, Monotonicity.NONINCREASING));
		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY, Monotonicity.NONDECREASING));
		Assert.assertTrue(Comparisons.isMonotonic(Double.POSITIVE_INFINITY, Monotonicity.STRICTLY_INCREASING));

		double[] x;

		x = new double[] {0, 1, 1.5, 2, 6, Double.NaN, Double.POSITIVE_INFINITY};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));

		x = new double[] {0, 1, 1, 2, 6, Double.NaN, Double.POSITIVE_INFINITY};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));

		x = new double[] {0, -1, -1, -2, -6, Double.NaN, Double.NEGATIVE_INFINITY};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));

		x = new double[] {0, -1, -1.5, -2, -6, Double.NaN, Double.NEGATIVE_INFINITY};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));

		x = new double[] {0, 1, 0.5, 2, 6, Double.NaN, Double.POSITIVE_INFINITY};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));

		x = new double[] {0, -1, 1.5, -2, -6, Double.NaN, Double.NEGATIVE_INFINITY};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));

		x = new double[] {1, 1, Double.NaN};
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.ALL_EQUAL));
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NONINCREASING));
		Assert.assertTrue(Comparisons.isMonotonic(x, Monotonicity.NONDECREASING));

		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(x, Monotonicity.STRICTLY_INCREASING));

		Dataset d = DatasetFactory.createRange(5, Dataset.INT32);
		Assert.assertTrue(Comparisons.isMonotonic(d, Monotonicity.NONDECREASING));
		Assert.assertTrue(Comparisons.isMonotonic(d, Monotonicity.STRICTLY_INCREASING));

		Assert.assertFalse(Comparisons.isMonotonic(d, Monotonicity.NOT_ORDERED));
		Assert.assertFalse(Comparisons.isMonotonic(d, Monotonicity.ALL_EQUAL));
		Assert.assertFalse(Comparisons.isMonotonic(d, Monotonicity.STRICTLY_DECREASING));
		Assert.assertFalse(Comparisons.isMonotonic(d, Monotonicity.NONINCREASING));
	}
}
