/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.examples;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.IPlane3DTrace;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.january.dataset.Slice;
import org.eclipse.swt.widgets.Composite;

public class Plane3DExample extends PlotExample {

	@Override
	protected void createExampleContent(Composite parent) {

		try {
			// We create a basic plot
			system.createPlotPart(parent, "Plane 3D Example", getViewSite()
					.getActionBars(), PlotType.PLANE3D, this);
			system.setPlotType(PlotType.PLANE3D);
			File loc = new File(
					BundleUtils.getBundleLocation(Activator.PLUGIN_ID),
					getFileName());

			String fp = loc.getAbsolutePath();
			IDataHolder dh = service.getData(fp, true, null);

			ILazyDataset data = dh.getLazyDataset(0);
			int l = 700;
			List<? extends IDataset> axes = generateAxes(data, l, 500, 600, 700);

			IPlane3DTrace plane1 = system.createPlane3DTrace("Plane1");
			plane1.setData(data, new double[] {300, 600}, new double[] {0,0,0}, new double[] {0,0,1}, axes);
			system.addTrace(plane1);

			IPlane3DTrace plane2 = system.createPlane3DTrace("Plane2");
			plane2.setOpacity(0.85); // FIXME does not work

			// FIXME plane normal does not change orientation in anti-z direction
			double c = Math.cos(Math.toRadians(10));
			double s = Math.sin(Math.toRadians(10));
			plane2.setData(data.getSliceView(new Slice(null, null, -1)), new double[] {300, 600}, new double[] {300,0,300}, new double[] {s,0,-c}, axes);
			system.addTrace(plane2);

		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}
	}

	private List<? extends IDataset> generateAxes(ILazyDataset data, int length, double... axesDims) {
		// FIXME for some reason axes have to be the same length
		List<IDataset> axes = new ArrayList<>();
		Dataset x = DatasetFactory.createLinearSpace(0, axesDims[0], length, Dataset.FLOAT64);
		x.setName("x");
		axes.add(x);
		Dataset y = DatasetFactory.createLinearSpace(0, axesDims[1], (int) (length*1.5), Dataset.FLOAT64);
		y.setName("y");
		axes.add(y);
		Dataset z = DatasetFactory.createLinearSpace(0, axesDims[2], length * 2, Dataset.FLOAT64);
		z.setName("z");
		axes.add(z);
		return axes;
	}

	protected String getFileName() {
		return "test.png";
	}

}