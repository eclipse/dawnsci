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
package org.eclipse.dawnsci.slicing.api.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDatasetMathsService;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.slicing.api.Activator;
import org.eclipse.dawnsci.slicing.api.system.DimsData;
import org.eclipse.dawnsci.slicing.api.system.DimsDataList;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
import org.eclipse.dawnsci.slicing.api.util.SliceUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;

/**
 * Convenience class for extending to provide a tool.
 * 
 * @author Matthew Gerring
 *
 */
public abstract class AbstractSlicingTool implements ISlicingTool {

	protected ISliceSystem slicingSystem;
	protected String       toolId;

	/**
	 * Does nothing by default.
	 */
	@Override
	public void createToolComponent(Composite parent) {
		
	}
	
	/**
	 * Does nothing but demilitarize() unless overridden.
	 */
	@Override
	public void dispose() {
		demilitarize();
	}

	@Override
	public String getToolId() {
		return toolId;
	}

	@Override
	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	@Override
	public ISliceSystem getSlicingSystem() {
		return slicingSystem;
	}

	@Override
	public void setSlicingSystem(ISliceSystem slicingSystem) {
		this.slicingSystem = slicingSystem;
	}
	
	
	/**
	 * Does nothing unless overridden.
	 */
	@Override
	public void demilitarize() {
		
	}

	@Override
	public Object getAdapter(Class clazz) {
        return null;
	}
	
	
	/**
	 * May be null. Returns the axes in dimensional order.
	 * @return
	 */
	protected List<IDataset> getNexusAxes() throws Exception {
		
		final Map<Integer, String> names = getSlicingSystem().getAxesNames();
		final DimsDataList           ddl = getSlicingSystem().getDimsDataList();
		final int[]            dataShape = getSlicingSystem().getData().getLazySet().getShape();
		
		final List<IDataset>         ret = new ArrayList<IDataset>(3);
		for (DimsData dd : ddl.iterable()) {
			
			IDataset axis = null;
			try {
				final String name = names.get(dd.getDimension()+1);
				axis = SliceUtils.getAxis(getSlicingSystem().getCurrentSlice(), getSlicingSystem().getData().getVariableManager(), name, false, null);
			} catch (Throwable e) {
				ret.add(null);
				continue;
			}
            if (axis==null) {
            	final IDatasetMathsService service = (IDatasetMathsService)Activator.getService(IDatasetMathsService.class);
            	axis = service.createRange(dataShape[dd.getDimension()], IDatasetMathsService.INT);
            }
            ret.add(axis);
		}
		return ret;
	}
	
	/**
	 * The action to be used for the tool. In this case we return
	 * null and an action is created from the extension point.
	 * @return
	 */
	public IAction createAction() {
		return null;
	}

	@Override
	public boolean isSliceRequired() {
		return true;
	}
	@Override
	public boolean isAdvancedSupported() {
		return true;
	}

	protected Slice[] getSlices() {
		
		int[] dataShape         = getSlicingSystem().getData().getLazySet().getShape();
		final DimsDataList dims = getSlicingSystem().getDimsDataList();
		
        return dims.toSliceArray(dataShape);
	}

	
	private boolean             on = true;

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}
    
}
