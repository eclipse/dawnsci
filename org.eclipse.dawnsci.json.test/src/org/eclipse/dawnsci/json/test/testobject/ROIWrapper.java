package org.eclipse.dawnsci.json.test.testobject;

import org.eclipse.dawnsci.analysis.api.roi.IROI;

public class ROIWrapper extends ObjectWrapper<IROI> {

	public ROIWrapper() {
	}
	public ROIWrapper(IROI roi) {
		this.object = roi;
	}
}
