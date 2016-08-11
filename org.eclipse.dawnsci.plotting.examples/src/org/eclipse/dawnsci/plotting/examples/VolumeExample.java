/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.examples;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.IVolumeRenderTrace;
import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.swt.widgets.Composite;

public class VolumeExample extends PlotExample {

	@Override
	protected void createExampleContent(Composite parent) {

		try {
			// We create a basic plot
			system.createPlotPart(parent, "Volume Example", getViewSite()
					.getActionBars(), PlotType.VOLUME, this);
			system.setPlotType(PlotType.VOLUME);
			File loc = new File(
					BundleUtils.getBundleLocation(Activator.PLUGIN_ID),
					getFileName());

			String fp = loc.getAbsolutePath();
			IDataHolder dh = service.getData(fp, true, null);

			ILazyDataset lazyDataset = dh.getLazyDataset("/entry/result/data");

			final IVolumeRenderTrace volume = system
					.createVolumeRenderTrace("Volume1");

			final List<? extends IDataset> axes = generateAxes(lazyDataset);
			
			volume.setData(
					lazyDataset.getShape(),
					lazyDataset.getSlice(
							new int[] { 0, 0, 0 },
							lazyDataset.getShape(), 
							new int[] { 2, 2, 2 }),
							0.42, 
							0.61, 
							new double[] { 0.2, 8.0 }, 
							new double[] { 0.1, 10000 },
							axes
					);

			system.addTrace(volume);

		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}

	}
	
	
	private List<IDataset> generateAxes(ILazyDataset dataset) {
		return Stream.of(0,1,2).map(i -> {		
			return generateIndexAxis(dataset.getShape()[i]);	
		}).collect(Collectors.toList());
	}
	private IDataset generateIndexAxis(int max) {
		double[] axis = IntStream.range(0, max).mapToDouble(i -> i).toArray();
		return DatasetFactory.createFromObject(axis);
	}
	
	protected String getFileName() {
		return "volumeExample.nxs";
	}

}