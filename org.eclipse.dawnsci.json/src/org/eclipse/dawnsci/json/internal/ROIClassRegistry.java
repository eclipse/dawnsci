/*-
 * Copyright © 2016 Diamond Light Source Ltd.
 *
 * This file is part of GDA.
 *
 * GDA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License version 3 as published by the Free
 * Software Foundation.
 *
 * GDA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with GDA. If not, see <http://www.gnu.org/licenses/>.
 */

package org.eclipse.dawnsci.json.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.persistence.IClassRegistry;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularFitROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalFitROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.FreeDrawROI;
import org.eclipse.dawnsci.analysis.dataset.roi.GridROI;
import org.eclipse.dawnsci.analysis.dataset.roi.HyperbolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ParabolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PerimeterBoxROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolylineROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RingROI;
import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;
import org.eclipse.dawnsci.analysis.dataset.roi.XAxisBoxROI;
import org.eclipse.dawnsci.analysis.dataset.roi.YAxisBoxROI;

public class ROIClassRegistry implements IClassRegistry {

	private static final Map<String, Class<?>> idToClassMap;
	static {
		Map<String, Class<?>> tmp = new HashMap<String, Class<?>>();

		tmp.put("roi.circularfit", CircularFitROI.class);
		tmp.put("roi.circular", CircularROI.class);
		tmp.put("roi.ellipticalfit", EllipticalFitROI.class);
		tmp.put("roi.elliptical", EllipticalROI.class);
		tmp.put("roi.freedraw", FreeDrawROI.class);
		tmp.put("roi.grid", GridROI.class);
		tmp.put("roi.hyperbolic", HyperbolicROI.class);
		tmp.put("roi.linear", LinearROI.class);
		tmp.put("roi.parabolic", ParabolicROI.class);
		tmp.put("roi.perimeterbox", PerimeterBoxROI.class);
		tmp.put("roi.point", PointROI.class);
		tmp.put("roi.polygonal", PolygonalROI.class);
		tmp.put("roi.polyline", PolylineROI.class);
		tmp.put("roi.rectangular", RectangularROI.class);
		tmp.put("roi.ring", RingROI.class);
		tmp.put("roi.sector", SectorROI.class);
		tmp.put("roi.xaxisbox", XAxisBoxROI.class);
		tmp.put("roi.yaxisbox", YAxisBoxROI.class);

		idToClassMap = Collections.unmodifiableMap(tmp);
	}

	@Override
	public Map<String, Class<?>> getIdToClassMap() {
		return idToClassMap;
	}
}
