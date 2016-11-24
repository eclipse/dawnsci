/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test.testobject;

import org.eclipse.dawnsci.analysis.api.roi.IROI;

public class ROIWrapper extends ObjectWrapper<IROI> {

	public ROIWrapper() {
	}
	public ROIWrapper(IROI roi) {
		this.object = roi;
	}
}
