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
package org.eclipse.dawnsci.plotting.api.views;

import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.PlottingFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

/**
 * This view shows a list of registered plotting systems
 * using their registered name.
 * @author Matthew Gerring
 *
 */
public class PlottingSystemsView extends ViewPart {


	public static final String ID = "org.eclipse.dawnsci.plotting.api.views.PlottingSystemsView"; //$NON-NLS-1$

	private TableViewer viewer;
	
	public PlottingSystemsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new GridLayout(1, false));
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		TableColumnLayout layout = new TableColumnLayout();
		container.setLayout(layout);
		
		this.viewer = new TableViewer(container, SWT.FULL_SELECTION | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setHeaderVisible(true);
		createColumns(viewer, layout);
		
		viewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public void dispose() {
			}
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

			@Override
			public Object[] getElements(Object inputElement) {
				final IPlottingSystem[] ps = PlottingFactory.getPlottingSystems();
				return ps!=null ? ps : new IPlottingSystem[]{};
			}
		});
		viewer.setInput(new Object());

		// Used by squish tests - do not change!
		RefreshButton button = new RefreshButton(parent, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.refresh();
			}
		});
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	private void createColumns(TableViewer viewer, TableColumnLayout layout) {
		
       
		final TableViewerColumn system   = new TableViewerColumn(viewer, SWT.LEFT, 0);
		system.getColumn().setText("Registered Name");
		layout.setColumnData(system.getColumn(), new ColumnWeightData(100));
		system.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
	            return ((IPlottingSystem)element).getPlotName();
			}
		});
		
		final TableViewerColumn part   = new TableViewerColumn(viewer, SWT.LEFT, 1);
		part.getColumn().setText("Part Name");
		layout.setColumnData(part.getColumn(), new ColumnWeightData(100));
		part.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				IWorkbenchPart part = ((IPlottingSystem)element).getPart();
				if (part!=null) return part.getTitle();
				return null;
			}
		});


	}


	/**
	 * Create the actions.
	 */
	private void createActions() {
		getViewSite().getActionBars().getToolBarManager().add(new Action("Refresh") {
			public void run() {
				viewer.refresh();
			}
		});
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		@SuppressWarnings("unused")
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		viewer.getTable().setFocus();
	}

}
