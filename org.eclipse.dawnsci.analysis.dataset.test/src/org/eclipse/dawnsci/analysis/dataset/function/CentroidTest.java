/*-
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.function;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.dawnsci.analysis.dataset.impl.function.Centroid;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IntegerDataset;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class CentroidTest {
	Dataset d;

	@Before
	public void setUp() {
		d = DatasetFactory.zeros(IntegerDataset.class, 100,60);
		d.fill(1);
	}

	/**
	 * 
	 */
	@Test
	public void testCentroid() {
		Centroid cen = new Centroid();
		List<Double> csets = cen.value(d);
		assertEquals("Centroid test, y", 50., csets.get(0), 1e-8);
		assertEquals("Centroid test, x", 30., csets.get(1), 1e-8);
	}
}

	
