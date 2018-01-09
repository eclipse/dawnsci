/*-
 * Copyright 2018 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.trace;

import org.eclipse.january.dataset.IDataset;

public interface IWaterfallTrace extends ITrace {
	
	public void setData(IDataset data, IDataset[] axes);

}
