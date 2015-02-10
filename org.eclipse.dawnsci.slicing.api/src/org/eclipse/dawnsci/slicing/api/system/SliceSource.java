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
package org.eclipse.dawnsci.slicing.api.system;

import java.util.Arrays;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.plotting.api.expressions.IVariableManager;

/**
 * This class represents information which configures a slice component
 * to 
 * 
 * @author Matthew Gerring
 *
 */
public class SliceSource {

	private IVariableManager variableManager;
	private ILazyDataset     lazySet; 
	private String           dataName; 
	private String           filePath;
	private boolean          isExpression;
	
	public SliceSource() {
		
	}
	public SliceSource(IVariableManager man, ILazyDataset l, String name, String path, boolean isExpr) {
		setVariableManager(man);
		setLazySet(l);
		setDataName(name);
		setFilePath(path);
		setExpression(isExpr);
	}
	public SliceSource(IDataHolder man, ILazyDataset l, String name, String path, boolean isExpr) {
		setVariableManager(createDataView(man));
		setLazySet(l);
		setDataName(name);
		setFilePath(path);
		setExpression(isExpr);
	}
	
	private IVariableManager createDataView(final IDataHolder holder) {
		return new IVariableManager.Stub() {

			@Override
			public List<String> getDataNames() {
				return Arrays.asList(holder.getNames());
			}

			@Override
			public List<String> getVariableNames() {
				return Arrays.asList(holder.getNames());
			}

			@Override
			public boolean isVariableName(String name, IMonitor monitor) {
				return holder.contains(name);
			}

			@Override
			public IDataset getVariableValue(String name, IMonitor monitor) {
				return holder.getDataset(name);
			}

			@Override
			public ILazyDataset getLazyValue(String variableName, IMonitor monitor) {
				return holder.getLazyDataset(variableName);
			}		
			
			@Override
			public ILazyDataset getDataValue(String dataName, IMonitor monitor) {
				return holder.getLazyDataset(dataName);
			}

			@Override
			public boolean isDataName(String dataName, IMonitor monitor) {
			    return holder.contains(dataName);
			}			
		};
	}
	public ILazyDataset getLazySet() {
		return lazySet;
	}
	public void setLazySet(ILazyDataset lazySet) {
		this.lazySet = lazySet;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isExpression() {
		return isExpression;
	}
	public void setExpression(boolean isExpression) {
		this.isExpression = isExpression;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataName == null) ? 0 : dataName.hashCode());
		result = prime * result
				+ ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + (isExpression ? 1231 : 1237);
		result = prime * result + ((lazySet == null) ? 0 : lazySet.hashCode());
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
		SliceSource other = (SliceSource) obj;
		if (dataName == null) {
			if (other.dataName != null)
				return false;
		} else if (!dataName.equals(other.dataName))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (isExpression != other.isExpression)
			return false;
		if (lazySet == null) {
			if (other.lazySet != null)
				return false;
		} else if (!lazySet.equals(other.lazySet))
			return false;
		return true;
	}
	public IVariableManager getVariableManager() {
		return variableManager;
	}
	public void setVariableManager(IVariableManager variableManager) {
		this.variableManager = variableManager;
	}
	
}
