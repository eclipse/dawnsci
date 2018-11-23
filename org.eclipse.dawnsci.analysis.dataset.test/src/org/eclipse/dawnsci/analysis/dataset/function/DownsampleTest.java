/*-
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.function;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.downsample.DownsampleMode;
import org.eclipse.dawnsci.analysis.dataset.function.Downsample;
import org.eclipse.january.asserts.TestUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Test down-sampling class
 */
public class DownsampleTest {
	Dataset d;

	@Before
	public void setUp() {
		d = DatasetFactory.createRange(24);
		d.setShape(new int[] {4, 6});
	}

	@Test
	public void testDownsamplePoint() {
		Downsample ds = new Downsample(DownsampleMode.POINT, new int[] {2, 3});

		List<Dataset> dsets = ds.value(d);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] {0, 3, 12, 15}).reshape(2, 2), dsets.get(0));
	}

	@Test
	public void testDownsampleMean() {
		Downsample ds = new Downsample(DownsampleMode.MEAN, new int[] {2, 3});

		List<Dataset> dsets = ds.value(d);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] {4, 7, 16, 19}).reshape(2, 2), dsets.get(0));
	}

	@Test
	public void testDownsampleMax() {
		Downsample ds = new Downsample(DownsampleMode.MAXIMUM, new int[] {2, 3});

		List<Dataset> dsets = ds.value(d);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] {8, 11, 20, 23}).reshape(2, 2), dsets.get(0));
	}

	@Test
	public void testDownsampleMin() {
		Downsample ds = new Downsample(DownsampleMode.MINIMUM, new int[] {2, 3});

		List<Dataset> dsets = ds.value(d);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] {0, 3, 12, 15}).reshape(2, 2), dsets.get(0));
	}

	@Test
	public void testDownsampleSum() {
		Downsample ds = new Downsample(DownsampleMode.SUM, new int[] {2, 3});

		List<Dataset> dsets = ds.value(d);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(new double[] {24, 42, 96, 114}).reshape(2, 2), dsets.get(0));
	}

	@Test
	public void testBreak() {
		Downsample ds = new Downsample(DownsampleMode.MEAN, new int[] {5, 7, 2});

		List<Dataset> dsets = ds.value(d);
		TestUtils.assertDatasetEquals(DatasetFactory.createFromObject(11.5).reshape(1, 1), dsets.get(0));
	}
}
