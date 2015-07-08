/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.slicing.api.data;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDynamicDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.plotting.api.expressions.IExpressionObject;
import org.eclipse.dawnsci.plotting.api.expressions.IExpressionObjectService;
import org.eclipse.dawnsci.plotting.api.expressions.IVariableManager;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractTransferableDataObject implements ITransferableDataObject {

	protected IExpressionObjectService service;
	/**
	 * If it is a transient data set it will have been copied from 
	 * one data view to another and the user may want to delete it.
	 */
	protected boolean           transientData=false;
	
	protected AbstractTransferableDataObject() {
		this.service  = (IExpressionObjectService)PlatformUI.getWorkbench().getService(IExpressionObjectService.class);
	}

	public AbstractTransferableDataObject(String name) {
		this();
		this.name = name;
	}

	private int yaxis = 1;
	private String            variable;
	
	private IExpressionObject expression;
	@Override
	public IExpressionObject getExpression() {
		return expression;
	}

	@Override
	public void setExpression(IExpressionObject expression) {
		this.expression = expression;
	}

    @Override
	public boolean isExpression() {
    	return expression!=null;
    }


	@Override
	public String toString() {
		if (expression!=null) return expression.toString();
		return name;
	}
	
	protected String            name;

	@Override
	public String getName() {
		if (expression!=null) return expression.getExpressionString();
		return name;
	}

	/**
	 * Data *must* have been cloned before doing this.
	 * @param name
	 */
	@Override
	public void setName(String name) {
        this.name = name;
	}
	

	@Override
	public void setVariable(String variable) {
		this.variable = variable;
		if (expression!=null) {
			expression.setExpressionName(variable);
		}
	}
	


	@Override
	public int getYaxis() {
		return yaxis;
	}

	@Override
	public void setYaxis(int yaxis) {
		this.yaxis = yaxis;
	}

	/**
	 * Please override this method.
	 */
	public ITransferableDataObject clone() {
        return this;
	}
	
	@Override
	public String getAxis(List<ITransferableDataObject> selections, boolean is2D, boolean isXFirst) {
		
		if (is2D) return isChecked() ? "-" : "";
		int axis = getAxisIndex(selections, isXFirst);
		if (axis<0) return "";
		if (axis==0) {
			return isXFirst ? "X" : "Y1";
		}
		return "Y"+axis;
	}

	@Override
	public int getAxisIndex(List<ITransferableDataObject> selections, boolean isXFirst) {
		if (selections!=null&&!selections.isEmpty()) {
			if (selections.size()>1) {
				if (selections.contains(this)) {
					if (selections.indexOf(this)==0) {
						return isXFirst ? 0 : 1;
					}
					return yaxis;
				}
			} if (selections.size()==1 && selections.contains(this)) {
				return yaxis;
			}
		}
		
        return -1;
    }

	public String getVariable() {
		return variable;
	}


    private static final String DELIMITER = "£";

	@Override
	public String getMemento() {
		return variable+DELIMITER+getName();
	}
	
	private String            mementoKey;

	@Override
	public String getMementoKey() {
		if (mementoKey==null) mementoKey = generateMementoKey();
		return mementoKey;
	}

	@Override
	public void setMementoKey(String mementoKey) {
		this.mementoKey = mementoKey;
	}

	private String generateMementoKey() {
		return "CheckableObject$"+System.currentTimeMillis()+"$"+getVariable();
	}

	public static String getVariable(String memento) {
		final String[] parts = memento.split(DELIMITER);
		return parts[0];
	}

	public static String getName(String memento) {
		final String[] parts = memento.split(DELIMITER);
		return parts[1];
	}

	
	@Override
	public void createExpression(IVariableManager psData, String mementoKey, String memento) {
		final String[] parts = memento.split(DELIMITER);
		setVariable(parts[0]);
		setExpression(service.createExpressionObject(psData, getVariable(), parts[1]));
	}


	private boolean checked = false;
	@Override
	public boolean isChecked() {
		return checked;
	}

	@Override
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result
				+ ((filterFile == null) ? 0 : filterFile.hashCode());
		result = prime * result
				+ ((mementoKey == null) ? 0 : mementoKey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		result = prime * result + yaxis;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTransferableDataObject other = (AbstractTransferableDataObject) obj;
		if (checked != other.checked)
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (filterFile == null) {
			if (other.filterFile != null)
				return false;
		} else if (!filterFile.equals(other.filterFile))
			return false;
		if (mementoKey == null) {
			if (other.mementoKey != null)
				return false;
		} else if (!mementoKey.equals(other.mementoKey))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		if (yaxis != other.yaxis)
			return false;
		return true;
	}
	
	private String filterFile;
	

	public String getFilterPath() {
		return filterFile;
	}

	public void setFilterPath(String filterFile) {
		this.filterFile = filterFile;
	}

	public boolean isTransientData() {
		return transientData;
	}

	@Override
	public boolean isDynamic() {
		if (isExpression()) return false;
		ILazyDataset lz = getLazyData(new IMonitor.Stub());
		if (lz==null) return false;
		if (lz instanceof IDynamicDataset) return true;
		return false;
	}
	
	@Override
	public void addDataListener(IDataListener l) {
		if (!isDynamic()) return;
		IDynamicDataset dy = (IDynamicDataset)getLazyData(new IMonitor.Stub());
		if (dy==null) return;
		dy.addDataListener(l);
	}

	@Override
	public void removeDataListener(IDataListener l){
	    if (!isDynamic()) return;
		IDynamicDataset dy = (IDynamicDataset)getLazyData(new IMonitor.Stub());
		if (dy==null) return;
		dy.removeDataListener(l);
	}

}
