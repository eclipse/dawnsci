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

import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.analysis.dataset.impl.function.Sum;
import org.junit.Test;

/**
 *
 */
public class SumTest {

	/**
	 * 
	 */
	@Test
	public void testExecute() {
		double[] x = {1., 2., 3., 4., 5.};
		Dataset d = DatasetFactory.createFromObject(x);
		Sum s = new Sum();
		List<Number> dsets = s.value(d);

		assertEquals(15., dsets.get(0).doubleValue(), 1e-8);
	}

}
