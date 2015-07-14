/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.examples;

import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.plotting.api.trace.IVectorTrace;
import org.eclipse.dawnsci.plotting.api.trace.IVectorTrace.ArrowConfiguration;
import org.eclipse.swt.widgets.Composite;

public class VectorExample extends PlotExample {

	@Override
	protected String getFileName() {
		return "Vector";
	}

	@Override
	protected void createExampleContent(Composite parent) {
		
		// We create a basic plot
		system.createPlotPart(parent, "Vector Example", getViewSite().getActionBars(), PlotType.IMAGE, this);
		
		Dataset vectors = DatasetFactory.zeros(new int[]{20, 20, 2}, Dataset.FLOAT32);
		
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++) {
				vectors.set(x*100, x, y, 0); // This gets normalized later
				vectors.set(2*Math.PI*((double)x/(20d)), x, y, 1);
			}
		}
		
		final IDataset xAxis = DatasetFactory.zeros(new int[]{20}, Dataset.FLOAT32);
		final IDataset yAxis = DatasetFactory.zeros(new int[]{20}, Dataset.FLOAT32);
		for (int i = 0; i < 20; i++) {
			xAxis.set(i*5, i);
			yAxis.set(i*5, i);
		}
		
		final IVectorTrace vector = system.createVectorTrace("vector1");
		vector.setData(vectors, Arrays.asList(xAxis, yAxis));
		vector.setArrowColor(200, 0, 0);
		vector.setArrowConfiguration(ArrowConfiguration.TO_CENTER_WITH_CIRCLE);
		vector.setCircleColor(0,200,0);
		//vector.setVectorNormalizationType(VectorNormalizationType.LOGARITHMIC);
		system.addTrace(vector);
	}

}
