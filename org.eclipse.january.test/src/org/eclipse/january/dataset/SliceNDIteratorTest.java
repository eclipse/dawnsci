/*-
 * Copyright (c) 2015, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.january.dataset;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.eclipse.january.asserts.TestUtils;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;
import org.eclipse.january.dataset.SliceNDIterator;
import org.junit.Test;

/**
 *
 */
public class SliceNDIteratorTest {
	/**
	 * 
	 */
	@Test
	public void testIterations() {
		SliceND sa = new SliceND(new int[] {4, 5, 6, 7}, new Slice(1, 5, 2), null, null, null);

		SliceNDIterator it = new SliceNDIterator(sa, 1);

		assertArrayEquals(new int[]{2, 1, 6, 7}, it.getShape());
		myAssertEquals(new SliceND(new int[] {5}), it.getOmittedSlice());

		int size = 0;
		while (it.hasNext()) {
			TestUtils.verbosePrintf(size + ": " + Arrays.toString(it.getPos()) + " or " + Arrays.toString(it.getUsedPos()));
			TestUtils.verbosePrintf("         " + it.getOutputSlice());
			if (size == (6*7 + 3*7 + 5)) {
				assertArrayEquals(new int[]{3, 0, 3, 5}, it.getPos());
				assertArrayEquals(new int[]{3, 3, 5}, it.getUsedPos());
				myAssertEquals(new SliceND(new int[] {2, 5, 6, 7}, new Slice(1, 2), null, new Slice(3, 4), new Slice(5, 6)), it.getOutputSlice());
			}
			size++;
		}
		assertEquals(2*6*7, size);
	}

	private void myAssertEquals(SliceND a, SliceND b) {
		assertArrayEquals(a.getSourceShape(), b.getSourceShape());
		assertArrayEquals(a.getShape(), b.getShape());
	}
}
