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
package org.eclipse.dawnsci.plotting.api;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;


public class PlottingSelectionProvider implements ISelectionProvider {

	private Set<ISelectionChangedListener> listeners;
	private ISelection currentSelection = new StructuredSelection();
	private BlockingDeque<ISelection> selectionQueue;
	private Thread                    selectionJob;
	private boolean isDisposed;
	
	public PlottingSelectionProvider(String plotSystemName) {
		listeners      = new HashSet<ISelectionChangedListener>(11);
		selectionQueue = new LinkedBlockingDeque<ISelection>(1);
		
		selectionJob   = new Thread("Plot selection thread '"+plotSystemName+"'") {		
			@Override
			public void run() {
				
				while(!isDisposed && listeners!=null && selectionQueue!=null) {
					try {
						currentSelection = selectionQueue.take();
						if (currentSelection instanceof DoneSelection) return;
 						
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								SelectionChangedEvent e = new SelectionChangedEvent(PlottingSelectionProvider.this, currentSelection);
								for (ISelectionChangedListener s : listeners) s.selectionChanged(e);
							}
						});
						Thread.sleep(100);
						
					} catch (InterruptedException e1) {
						continue;
					}
				}
			}
		};
		selectionJob.setDaemon(true);
		selectionJob.setPriority(Thread.MIN_PRIORITY);
		selectionJob.start();
	}
	
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		listeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return currentSelection;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Method calls listener in background thread mto make frequent updates possible.
	 */
	@Override
	public void setSelection(ISelection selection) {
		
		selectionQueue.clear();
		selectionQueue.add(selection);
		
	}

	public void clear() {
		if (listeners!=null) listeners.clear();
		selectionQueue.clear();
		selectionQueue.add(new DoneSelection());
	}
	
	private final class DoneSelection extends StructuredSelection {
		
	}
	

	public void dispose() {
		clear();
		this.isDisposed = true;
	}


}
