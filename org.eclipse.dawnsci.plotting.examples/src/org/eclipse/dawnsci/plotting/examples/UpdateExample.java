/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * View which creates a sector selection region
 * and listens to that region moving.
 * 
 * @author fcp94556
 *
 */
public class UpdateExample extends PlotExample {
	
	private boolean buttonPressed = false;
	
	public void createExampleContent(Composite parent) {
		
		parent.setLayout(new GridLayout(1, false));
		try {
			final Composite buttons = new Composite(parent, SWT.NONE);
			buttons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			buttons.setLayout(new GridLayout(2, false));
			
			// We create a button which when pressed, does updates
			final Button updates = new Button(buttons, SWT.CHECK);
			updates.setText("Append data every 200ms");
			updates.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
			
			final Button clear = new Button(buttons, SWT.PUSH);
			clear.setText("Reset");
			clear.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

			// We create a basic plot
			system.createPlotPart(parent, "XY Example", getViewSite().getActionBars(), PlotType.XY, this);
			system.getPlotComposite().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
			// Make 3x100 values of first order 
			List<IDataset> ys = getInitialData();
			
		    // Plot them
			system.createPlot1D(null, ys, new NullProgressMonitor());
			
			// Add a listener to append data
			updates.addSelectionListener(new SelectionAdapter() {
				
				public void widgetSelected(SelectionEvent e){
					
					buttonPressed = updates.getSelection();
					if (!updates.getSelection()) return;
					
					// Or better with an Eclipse job...
					createUpdateThread();
				}
			});
			
			clear.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e){
					buttonPressed = false;
					updates.setSelection(false);
					
					// Removed the last updated plots
					system.clear();
					system.createPlot1D(null, getInitialData(), new NullProgressMonitor());
				}
			});

		} catch (Throwable ne) {
			ne.printStackTrace(); // Or your favourite logging.
		}

    }

	protected void createUpdateThread() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while(buttonPressed) {
					
					try {
						// Thread safe update of y
						for (int n=2; n<5; ++n) {
							int size = system.getTrace("y"+n).getData().getSize();
						    system.append("y"+n, size, Math.pow(size*n, 2), new NullProgressMonitor());
						}
						system.repaint(true);
						
					} catch (Exception e) {
						e.printStackTrace(); // Or your favourite logging.
					} finally {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace(); // Or your favourite logging.
						}
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	private List<IDataset> getInitialData() {
		List<IDataset> ys = new ArrayList<IDataset>(3);
		for (int n=2; n<5; ++n) {
			double[] data = new double[100];
			for (int i = 0; i < data.length; i++) data[i] = Math.pow(i*n, 2);
			IDataset y = new DoubleDataset(data, data.length);
			y.setName("y"+n);
			ys.add(y);
		}
		return ys;
	}

	@Override
	protected String getFileName() {
		return null; // Not file data
	}

}
