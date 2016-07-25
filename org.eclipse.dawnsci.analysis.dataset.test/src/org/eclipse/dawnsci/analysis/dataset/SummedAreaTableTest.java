/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset;

import java.util.Arrays;

import org.eclipse.dawnsci.analysis.dataset.impl.SummedAreaTable;
import org.eclipse.january.asserts.TestUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.IntegerDataset;
import org.eclipse.january.dataset.Maths;
import org.eclipse.january.dataset.Random;
import org.junit.Ignore;
import org.junit.Test;

public class SummedAreaTableTest {

	private enum TestType {
		VALUE, MEAN, VARIANCE, FANO;
	}

	private static final int[] smallShape = new int[] { 10, 10 };
	private static final int[] largeShape = new int[] { 256, 256};

	@Test
	public void testSmallDiagonal() throws Exception {

		final Dataset image = Random.rand(smallShape);
		final SummedAreaTable sum = new SummedAreaTable(image);
		testDiagonal(image, sum, TestType.VALUE);
	}

	@Test
	public void testLargeDiagonal() throws Exception {

		long start = System.currentTimeMillis();
		final Dataset image = Random.rand(largeShape);
		final SummedAreaTable sum = new SummedAreaTable(image);
		long end = System.currentTimeMillis();

		// Check time
		long delta = end - start;
		if (delta > 1000)
			throw new Exception(
					"Unexpected long sum table generation! As a guide, it should take less than 400ms on I7 but took longer than 1000ms");

		// Long time, no caching done!
		testDiagonal(image, sum, TestType.VALUE);
	}

	@Test
	public void testSmallMeanDiagonal() throws Exception {

		final Dataset image = Random.rand(smallShape);
		final SummedAreaTable sum = new SummedAreaTable(image);
		testDiagonal(image, sum, TestType.MEAN, 3, 3);
	}

	@Test
	public void testLargeMeanDiagonal() throws Exception {

		long start = System.currentTimeMillis();
		final Dataset image = Random.rand(largeShape);
		final SummedAreaTable sum = new SummedAreaTable(image);
		long end = System.currentTimeMillis();

		// Check time
		long delta = end - start;
		if (delta > 1000)
			throw new Exception(
					"Unexpected long sum table generation! As a guide, it should take less than 400ms on I7 but took longer than 1000ms");

		// Long time, no caching done!
		testDiagonal(image, sum, TestType.MEAN, 5, 5);
	}

	@Test
	public void testSmallVarianceDiagonal() throws Exception {

		final Dataset image = Maths.multiply(Random.rand(smallShape), 100);
		final SummedAreaTable sum = new SummedAreaTable(image);
		testDiagonal(image, sum, TestType.VARIANCE, 3, 3);
	}

	@Test
	public void testLargeVarianceDiagonal() throws Exception {

		long start = System.currentTimeMillis();
		final Dataset image = Maths.multiply(Random.rand(largeShape), 100);
		final SummedAreaTable sum = new SummedAreaTable(image);
		long end = System.currentTimeMillis();

		// Check time
		long delta = end - start;
		if (delta > 1000)
			throw new Exception(
					"Unexpected long sum table generation! As a guide, it should take less than 400ms on I7 but took longer than 1000ms");

		// Long time, no caching done!
		testDiagonal(image, sum, TestType.VARIANCE, 5, 5);
	}

	@Test
	public void testSmallFanoDiagonal() throws Exception {

		final Dataset image = Maths.multiply(Random.rand(smallShape), 100);
		final SummedAreaTable sum = new SummedAreaTable(image, true);
		testDiagonal(image, sum, TestType.FANO, 3, 3);
	}

	@Test
	public void testLargeFanoDiagonal() throws Exception {

		long start = System.currentTimeMillis();
		final Dataset image = Maths.multiply(Random.rand(largeShape), 100);
		final SummedAreaTable sum = new SummedAreaTable(image, true);
		long end = System.currentTimeMillis();

		// Check time
		long delta = end - start;
		if (delta > 1000)
			throw new Exception(
					"Unexpected long sum table generation! As a guide, it should take less than 400ms on I7 but took longer than 1000ms");

		// Long time, no caching done!
		testDiagonal(image, sum, TestType.FANO, 5, 5);
	}

	@Test
	public void testSmallFano() throws Exception {
		typeLoop(new int[] { 10, 10 });
	}

	@Test
	public void testMediumFano() throws Exception {
		typeLoop(new int[] { 378, 517 });
	}

	@Ignore
	@Test
	public void test6MillFanoTableTime() throws Exception {
		long start = System.currentTimeMillis();
		final Dataset image = Random.rand(new int[] { 2000, 3000 });
		final SummedAreaTable sum = new SummedAreaTable(image, true);
		long postTable = System.currentTimeMillis();
		System.out.println("Calculated summed area table of size " + Arrays.toString(new int[] { 2000, 3000 }) + " in "
				+ (postTable - start) + "ms");
		@SuppressWarnings("unused")
		final IDataset fano = sum.getFanoImage(new int[] { 5, 5 });
		long end = System.currentTimeMillis();
		System.out.println("Fano loop after table creation of size " + Arrays.toString(new int[] { 2000, 3000 })
				+ " in " + (end - postTable) + "ms");
		System.out.println("Total fano image of size " + Arrays.toString(new int[] { 2000, 3000 }) + " in "
				+ (end - start) + "ms");
		if ((end - start) > 5000)
			throw new Exception("Rather long time take to compute fano factor image!");
	}

	@Test
	public void testNullImage() throws Exception {
		try {
			new SummedAreaTable(null);
		} catch (Exception required) {
			return;
		}
		throw new Exception("Null image worked!");
	}

	@Test
	public void testEmptyImage() throws Exception {
		try {
			new SummedAreaTable(DatasetFactory.zeros(IntegerDataset.class, new int[] { 0, 0 }));
		} catch (Exception required) {
			return;
		}
		throw new Exception("Empty image worked!");
	}

	@Test
	public void testZeroBox() throws Exception {
		try {
			SummedAreaTable table = new SummedAreaTable(Random.rand(smallShape), true);
			table.getFanoImage(0, 0);
		} catch (Exception required) {
			return;
		}
		throw new Exception("Empty box worked!");
	}

	@Test
	public void testEvenBox() throws Exception {
		try {
			SummedAreaTable table = new SummedAreaTable(Random.rand(smallShape), true);
			table.getFanoImage(2, 2);
		} catch (Exception required) {
			return;
		}
		throw new Exception("Even box worked!");
	}

	private void typeLoop(int[] size) throws Exception {
		for (int i : new int[] { 1, 5, 9 }) {
			testFano(DatasetUtils.cast(Maths.multiply(Random.rand(size), 100), Dataset.INT16), i, i);
			testFano(DatasetUtils.cast(Maths.multiply(Random.rand(size), 100), Dataset.INT32), i, i);
			testFano(DatasetUtils.cast(Maths.multiply(Random.rand(size), 100), Dataset.INT64), i, i);
			testFano(DatasetUtils.cast(Maths.multiply(Random.rand(size), 100), Dataset.FLOAT32), i, i);
			testFano(DatasetUtils.cast(Maths.multiply(Random.rand(size), 100), Dataset.FLOAT64), i, i);
		}
	}

	private void testFano(Dataset image, int... box) throws Exception {

		long start = System.currentTimeMillis();
		final SummedAreaTable sum = new SummedAreaTable(image);
		final Dataset fano = sum.getFanoImage(box);
		long end = System.currentTimeMillis();

		if (!Arrays.equals(fano.getShape(), image.getShape()))
			throw new Exception("Fano image changed shape!");

		TestUtils.verbosePrintf("Calculated fano of size %s with box %s in %dms\n", Arrays.toString(fano.getShape()),
				Arrays.toString(box), end - start);

	}

	private void testDiagonal(IDataset image, SummedAreaTable sum, TestType type, int... box) throws Exception {

		if (!Arrays.equals(sum.getShape(), image.getShape()))
			throw new Exception("Shape not the same! sum is " + Arrays.toString(sum.getShape()));

		int x = 0, y = 0;
		while (x < image.getShape()[0] && y < image.getShape()[1]) {

			double a = 0d, b = 0d;
			if (type == TestType.VALUE) {
				a = sum.getDouble(x, y);
				b = getSum(image, x, y);

			} else if (type == TestType.MEAN) {
				a = sum.getBoxMean(new int[] { x, y }, box);
				b = getBoxMean(image, new int[] { x, y }, box);

			} else if (type == TestType.VARIANCE) {
				a = sum.getBoxVariance(new int[] { x, y }, box);
				b = getBoxVariance(image, new int[] { x, y }, box);

			} else if (type == TestType.FANO) {
				a = sum.getBoxFanoFactor(new int[] { x, y }, box);
				double mean = getBoxMean(image, new int[] { x, y }, box);
				double variance = getBoxVariance(image, new int[] { x, y }, box);
				b = variance / mean;
			}

			TestUtils.assertEquals("a does not equal b", a, b, 1e-6, 1e-6);
			x++;
			y++;
		}
	}

	/**
	 * At one point during development the dataset variance was wrong.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDatasetVariance() throws Exception {

		final Dataset image = Maths.multiply(Random.rand(smallShape), 100);
		double mean = ((Number) image.mean()).doubleValue();
		Dataset minus = Maths.subtract(image, mean);
		Dataset square = Maths.square(minus);
		double var = ((Number) square.mean()).doubleValue();

		TestUtils.assertEquals("Variance does not equal image variance", var, image.variance(true).doubleValue(), 1e-6,
				1e-6);
	}

	private double getBoxVariance(IDataset image, int[] point, int[] box) {
		Dataset subsetNoSlice = createDataset(image, point, box);
		return subsetNoSlice.variance(true).doubleValue();
	}

	private double getBoxMean(IDataset image, int[] point, int... box) {
		Dataset subsetNoSlice = createDataset(image, point, box);
		return ((Number) subsetNoSlice.mean()).doubleValue();
	}

	private Dataset createDataset(IDataset image, int[] point, int[] box) {
		int[] coords = createCoords(image, point, box);

		double[] subset = new double[box[0] * box[1]];

		int count = 0;
		for (int ix = coords[0]; ix <= coords[2]; ix++) {
			for (int iy = coords[1]; iy <= coords[3]; iy++) {
				subset[count] = image.getDouble(ix, iy);
				++count;
			}
		}

		return DatasetFactory.createFromObject(subset, box);
	}

	private int[] createCoords(IDataset image, int[] point, int[] box) {

		int x = point[0];
		int y = point[1];
		int r1 = (int) Math.floor(box[0] / 2d); // for instance 3->1, 5->2, 7->3
		int r2 = (int) Math.floor(box[1] / 2d); // for instance 3->1, 5->2, 7->3

		int minx = x - r1;
		if (minx < 0)
			minx = 0;
		int maxx = x + r1;
		if (maxx >= image.getShape()[0])
			maxx = image.getShape()[0] - 1;

		int miny = y - r2;
		if (miny < 0)
			miny = 0;
		int maxy = y + r2;
		if (maxy >= image.getShape()[1])
			maxy = image.getShape()[1] - 1;

		return new int[] { minx, miny, maxx, maxy };
	}

	/**
	 * The summed area table is just the sum of all the pixels above and to the
	 * left of (x, y)
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @return The summed area table is just the sum of all the pixels above and
	 *         to the left of (x, y)
	 */
	private double getSum(IDataset image, int x, int y) {

		double sum = 0d;
		for (int ix = 0; ix <= x; ix++) {
			for (int iy = 0; iy <= y; iy++) {
				sum += image.getDouble(ix, iy);
			}
		}
		return sum;
	}

}
