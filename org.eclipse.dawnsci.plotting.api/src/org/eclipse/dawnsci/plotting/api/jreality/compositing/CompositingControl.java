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

package org.eclipse.dawnsci.plotting.api.jreality.compositing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 *
 */
public class CompositingControl extends Composite implements SelectionListener {

	private Table tblComposits;
	private List<CompositeTableRow> rows = new ArrayList<CompositeTableRow>();
	private List<SelectionListener> listeners = new ArrayList<SelectionListener>();
	
	public CompositingControl(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		tblComposits = new Table(this, SWT.DOUBLE_BUFFERED );
		tblComposits.setHeaderVisible(true);
		tblComposits.setLinesVisible(true);
		tblComposits.setEnabled(true);
		{
			TableColumn tblclmnActive = new TableColumn(tblComposits,SWT.NONE);
			tblclmnActive.setWidth(70);
			tblclmnActive.setResizable(false);
			tblclmnActive.setText("Active");
		}
		{
			TableColumn tblclmnColour = new TableColumn(tblComposits, SWT.NONE);
			tblclmnColour.setWidth(330);
			tblclmnColour.setResizable(true);
			tblclmnColour.setText("Layer");
		}
		{
			TableColumn tblclmnDescription = new TableColumn(tblComposits, SWT.NONE);
			tblclmnDescription.setWidth(190);
			tblclmnDescription.setResizable(false);
			tblclmnDescription.setText("Weight (%)");
		}
		{
			TableColumn tblclmnVisible = new TableColumn(tblComposits, SWT.NONE);
			tblclmnVisible.setWidth(120);
			tblclmnVisible.setResizable(false);
			tblclmnVisible.setText("Operation");
		}	
		{
			TableColumn tblclmnRed = new TableColumn(tblComposits, SWT.NONE);
			tblclmnRed.setWidth(40);
			tblclmnRed.setResizable(false);
			tblclmnRed.setText("R");
			
		}
		{
			TableColumn tblclmnGreen = new TableColumn(tblComposits, SWT.NONE);
			tblclmnGreen.setWidth(40);
			tblclmnGreen.setResizable(false);
			tblclmnGreen.setText("G");	
		}
		{
			TableColumn tblclmnBlue = new TableColumn(tblComposits, SWT.NONE);
			tblclmnBlue.setWidth(40);
			tblclmnBlue.setResizable(false);
			tblclmnBlue.setText("B");	
		}		
	}

		
	private void clearRows() {
		Iterator<CompositeTableRow> rowIter = rows.iterator();
		while (rowIter.hasNext()) {
			rowIter.next().dispose();
		}
		tblComposits.removeAll();
		rows.clear();
	}
	/**
	 * Update the legend table
	 * @param table
	 */
	public void updateTable(final List<CompositeEntry> table)
	{
		final CompositingControl control = this;
		this.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				clearRows();
				for (int i = 0; i < table.size(); i++)
				{
					
					CompositeEntry entry = table.get(i);
					CompositeTableRow row = new
						CompositeTableRow(entry,tblComposits,control,(i == 0));
					rows.add(row);
				}
			}
		});
	}


	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// Nothing to do
	}


	@Override
	public void widgetSelected(SelectionEvent e) {
		List<CompositeEntry> entries = new ArrayList<CompositeEntry>();
		Iterator<CompositeTableRow> rowIter = rows.iterator();
		while (rowIter.hasNext()) {
			CompositeTableRow row = rowIter.next();
			entries.add(row.convertToCompositeEntry());
		}
		e.data = entries;
		notifySelectionListeners(e);
	}
	
	private void notifySelectionListeners(SelectionEvent e) {
		Iterator<SelectionListener> iter = listeners.iterator();
		while (iter.hasNext()) {
			iter.next().widgetSelected(e);
		}
	}
	
	public void addSelectionListener(SelectionListener listener) {
		listeners.add(listener);
	}
	
	public void removeSelectionListener(SelectionListener listener) {
		listeners.remove(listener);
	}
	
}
