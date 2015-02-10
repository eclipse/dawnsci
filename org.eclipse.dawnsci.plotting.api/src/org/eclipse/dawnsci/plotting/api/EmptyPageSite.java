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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPageSite;

public class EmptyPageSite implements IPageSite {

	private Shell          shell;
	private IActionBars    actionBars;

	public EmptyPageSite(Shell shell, IActionBars actionBars) {
		this.shell      = shell;
		this.actionBars = actionBars;
	}

	@Override
	public IWorkbenchPage getPage() {
		return getActivePage();
	}

	@Override
	public Shell getShell() {
		return shell;
	}

	@Override
	public IWorkbenchWindow getWorkbenchWindow() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	@Override
	public void setSelectionProvider(ISelectionProvider provider) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getService(Class api) {
		return PlatformUI.getWorkbench().getService(api);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean hasService(Class api) {
		return  PlatformUI.getWorkbench().hasService(api);
	}

	@Override
	public void registerContextMenu(String menuId, MenuManager menuManager,
			ISelectionProvider selectionProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IActionBars getActionBars() {
		return actionBars;
	}
	
	@Override
	public ISelectionProvider getSelectionProvider() {
		return new ISelectionProvider() {

			@Override
			public void addSelectionChangedListener(
					ISelectionChangedListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public ISelection getSelection() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setSelection(ISelection selection) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	/**
	 * @return IWorkbenchPage
	 */
	private static final IWorkbenchPage getActivePage() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		if (bench==null) return null;
		final IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
		if (window==null) return null;
		return window.getActivePage();
	}

}
