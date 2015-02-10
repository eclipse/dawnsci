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
package org.eclipse.dawnsci.slicing.api;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.slicing.api.system.DimsDataList;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
import org.eclipse.dawnsci.slicing.api.system.RangeMode;
import org.eclipse.dawnsci.slicing.api.system.SliceSource;
import org.eclipse.dawnsci.slicing.api.util.ProgressMonitorWrapper;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A dialog for holding a component used to slice the data.
 * 
 * @author Matthew Gerring
 *
 */
public class SliceDialog extends Dialog {
	
	private static Logger logger = LoggerFactory.getLogger(SliceDialog.class);
	
	private DimsDataList   dimsDataList;
	private ISliceSystem   sliceComponent;

	private boolean showAxes;
	
	public SliceDialog(Shell parentShell, boolean showAxes) {
		super(parentShell);
		setShellStyle(SWT.RESIZE|SWT.DIALOG_TRIM);
		this.showAxes = showAxes;
	}
	
	public Control createDialogArea(Composite parent) {
		
		try {
			this.sliceComponent = SlicingFactory.createSliceSystem("org.dawb.workbench.views.h5GalleryView");
		} catch (Exception e) {
			logger.error("Cannot create a slice system!", e);
			return null;
		}
		sliceComponent.setAxesVisible(showAxes);
		final Control slicer = sliceComponent.createPartControl(parent);
		slicer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		sliceComponent.setAxesVisible(false);

		sliceComponent.setVisible(true);
		
		return parent;
	}

	/**
	 * Sets the data which the parameter should slice.
	 * @param dataSetName
	 * @param filePath
	 * @throws Exception 
	 */
	public void setData(final String   dataSetName,
			            final String   filePath,
			            final IProgressMonitor monitor) throws Throwable {
		
		final ILoaderService service = (ILoaderService)Activator.getService(ILoaderService.class);
		final IDataHolder holder = service.getData(filePath, new ProgressMonitorWrapper(monitor));
		ILazyDataset lazy  = holder.getLazyDataset(dataSetName);
		if (lazy==null) lazy = holder.getLazyDataset(0);
		
		sliceComponent.setData(new SliceSource(holder, lazy, dataSetName, filePath, false));        
	}

	public DimsDataList getDimsDataList() {
		return dimsDataList;
	}

	/**
	 * Examples "[0, X, Y]",   "[0, 0, X]",  "[1;10;1, 5, X]"
	 * @param persistedString
	 */
	public void setDimsDataList(final DimsDataList dimsDataList) {
		
		if (dimsDataList==null)    return;
		if (dimsDataList.size()<1) return;
		try {
			sliceComponent.setDimsDataList(dimsDataList);
		} catch (Exception e) {
			logger.error("Cannot set persisted string in slice component!", e);
		}
	}

	protected void okPressed() {
		try {
			this.dimsDataList = sliceComponent.getDimsDataList();
		} catch (Exception e) {
			logger.error("Cannot get persisted string in slice component!", e);
		}
		super.okPressed();
	}

	public void setRangeMode(RangeMode mode) {
		sliceComponent.setRangeMode(mode);
	}
	
	public void setSliceActionsEnabled(boolean enabled) {
		sliceComponent.setSliceActionsEnabled(enabled);
	}

}
