/*-
 * Copyright 2015, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.january.dataset;

import java.util.Arrays;

/**
 * Checks whether shape has changed between waits. Useful for where datasets are being extended.
 * Note, {@link LazyDynamicDataset} already alerts its listeners when its shape is resized
 */
public class ShapeChangeChecker implements IDatasetChangeChecker {
	
	private ILazyDataset lazy;
	private int[] shape;

	@Override
	public void setDataset(ILazyDataset dataset) {
		if (dataset == null) {
			throw new IllegalArgumentException("Dataset must not be null");
		}
		lazy = dataset;
		shape = lazy.getShape();
	}

	@Override
	public boolean check() {
		int[] cshape = lazy.getShape();
		if (Arrays.equals(shape, cshape)) {
			return false;
		}
		shape = cshape;
		return true;
	}
}
