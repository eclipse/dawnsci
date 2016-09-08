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

import java.util.HashMap;
import java.util.Map.Entry;

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

	private static HashMap<String, Class<?>> idToClassMap = new HashMap<String, Class<?>>();
	static {
		idToClassMap.put("roi.circularfit", CircularFitROI.class);
		idToClassMap.put("roi.circular", CircularROI.class);
		idToClassMap.put("roi.ellipticalfit", EllipticalFitROI.class);
		idToClassMap.put("roi.elliptical", EllipticalROI.class);
		idToClassMap.put("roi.freedraw", FreeDrawROI.class);
		idToClassMap.put("roi.grid", GridROI.class);
		idToClassMap.put("roi.hyperbolic", HyperbolicROI.class);
		idToClassMap.put("roi.linear", LinearROI.class);
		idToClassMap.put("roi.parabolic", ParabolicROI.class);
		idToClassMap.put("roi.perimeterbox", PerimeterBoxROI.class);
		idToClassMap.put("roi.point", PointROI.class);
		idToClassMap.put("roi.polygonal", PolygonalROI.class);
		idToClassMap.put("roi.polyline", PolylineROI.class);
		idToClassMap.put("roi.rectangular", RectangularROI.class);
		idToClassMap.put("roi.ring", RingROI.class);
		idToClassMap.put("roi.sector", SectorROI.class);
		idToClassMap.put("roi.xaxisbox", XAxisBoxROI.class);
		idToClassMap.put("roi.yaxisbox", YAxisBoxROI.class);
	}

	@Override
	public Class<?> getClassFromId(String id) {
		return idToClassMap.get(id);
	}

	@Override
	public String getIdFromClass(Class<?> clazz) {
		for (Entry<String, Class<?>> entry : idToClassMap.entrySet()) {
			if (entry.getValue().equals(clazz)) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public Boolean hasId(String id) {
		return idToClassMap.containsKey(id);
	}

	@Override
	public Boolean hasClass(Class<?> clazz) {
		return idToClassMap.containsValue(clazz);
	}
}
