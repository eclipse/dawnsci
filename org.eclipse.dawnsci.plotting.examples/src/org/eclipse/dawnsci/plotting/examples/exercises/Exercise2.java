/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.examples.exercises;

import org.eclipse.dawnsci.analysis.dataset.impl.BooleanDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.PositionIterator;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceListener;
import org.eclipse.dawnsci.plotting.api.trace.TraceEvent;
import org.eclipse.swt.widgets.Composite;

public class Exercise2 extends Exercise1 {
	
	private ITraceListener traceListener;
	private BooleanDataset mask;

	@Override
	public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        
        traceListener = createTraceListener();
        system.addTraceListener(traceListener);
	}
	
	protected ITraceListener createTraceListener() {
		return new ITraceListener.Stub() {
			@Override
			public void traceUpdated(TraceEvent evt) {
				IImageTrace trace = (IImageTrace)evt.getSource();
				createThreasholdMask(trace);
 			}
		};
	}

	protected void createThreasholdMask(IImageTrace trace) {
		// Lets do some masking...
		if (mask==null) mask = new BooleanDataset(trace.getData().getShape());

		// Start off with everything true (true = not-masked!)
		mask.fill(true);

		// Iterate everything - yes this is slowish now. In Java8 we are
		// implementing parallel streams with Datasets but this was not available
		// when these examples were being written.
		PositionIterator it = new PositionIterator(mask.getShape());
		while(it.hasNext()) {
			int[] pos = it.getPos();
			if (trace.getData().getInt(pos)<=-1) mask.set(false, pos);
		}

		trace.setMask(mask);
	}

	@Override
	public void dispose() {
		system.removeTraceListener(traceListener); // Not necessary but good practice since we added one...
		super.dispose();
	}
}
