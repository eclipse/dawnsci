/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.Iterator;

import org.eclipse.january.dataset.ILazyDataset;

public interface ISliceViewIterator extends Iterator<ILazyDataset> {
	
	public int[] getShape();
	
}
