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
package org.eclipse.dawnsci.slicing.api.data;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.plotting.api.expressions.IExpressionObject;
import org.eclipse.dawnsci.plotting.api.expressions.IVariableManager;

public interface ITransferableDataObject {
	
	/**
	 * Get the actual data, evaluating expressions where necessary.
	 * @param monitor
	 * @return
	 */
	public IDataset getData(IMonitor monitor);

	/**
	 * Get the lazy data if possible. Some image loaders always load the entire data
	 * therefore the lazy data CANNOT be guaranteed to be lazy at the moment.
	 * @param monitor
	 * @return
	 */
	public ILazyDataset getLazyData(IMonitor monitor);
	
	/**
	 * The original file name that this data came from.
	 * @return
	 */
	public String getFileName();

	/**
	 * The shape of the data. If the data is an image, will attempt to 
	 * get the shape WITHOUT reading all the data as many image loaders do.
	 * 
	 * If the data is an expression, will attempt to get the shape without
	 * evaluating the expression if it has not already been evaluated (=faster)
	 * 
	 * @param force -  if forced expressions will be evaluated and datasets squeezed.
	 * @return
	 */
	public int[] getShape(boolean force);

	/**
	 * Name of transferable.
	 * @return
	 */
	public String getName();

	/**
	 * This method should only be called once the object has been cloned.
	 * This is because it puts a different record inside the IDataHolder for
	 * this dataset.
	 * 
	 * If this is an expression an IllegalArgumentException is thrown.
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * If this is an expression, returns a reference to the expression serivce
	 * object used to evaluate the expression.
	 * @return
	 */
	public IExpressionObject getExpression();

	/**
	 * Set this object as an expression.
	 * @param expression
	 */
	public void setExpression(IExpressionObject expression);

	/**
	 * 
	 * @return if is expression.
	 */
	public boolean isExpression();

	/**
	 * If the data is ticked for plotting
	 * @return
	 */
	public boolean isChecked();

	/**
	 * 
	 * @param checked
	 */
	public void setChecked(boolean checked);

	/**
	 * The file path of the original data.
	 * @return
	 */
	public String getPath();

	/**
	 * Get the axis, X, Y1..Y4
	 * 
	 * If this object is not in 
	 * 
	 * @param selections
	 * @return
	 */
	public String getAxis(List<ITransferableDataObject> selections, boolean is2D, boolean isXFirst);

	public int getAxisIndex(List<ITransferableDataObject> selections, boolean isXFirst);

	public int getYaxis();

	public void setYaxis(int yaxis);

	/**
	 * The variable used to provide the value of this data
	 * in expressions.
	 * 
	 * @return
	 */
	public String getVariable();

	/**
	 * The variable name used for expressions by expressions of data.
	 * @param variable
	 */
	public void setVariable(String variable);

	/**
	 * Internally creates an expression of data with the user can interact with.
	 * @param psData
	 * @param mementoKey
	 * @param memento
	 */
	public void createExpression(IVariableManager psData, String mementoKey, String memento);

	/**
	 * Memento to remember this data (primarily for expressions)
	 * @return
	 */
	public String getMemento();

	/**
	 * @return Returns the mementoKey.
	 */
	public String getMementoKey();

	/**
	 * @param mementoKey The mementoKey to set.
	 */
	public void setMementoKey(String mementoKey);

	/**
	 * It is possible to have an alternative name displayed in the table of data.
	 * @param rootName
	 * @return
	 */
	public String getDisplayName(String rootName);

	/**
	 * Shallow copy of object.
	 * @return
	 */
	public ITransferableDataObject clone();
	
	/**
	 * 
	 * @return true if the data has been pasted in and therefore is
	 * not currently remembered if the file in closed and reopened
	 * (unlike expressions which are).
	 */
	public boolean isTransientData();

	/**
	 * Called to clean up any references to data which may be large.
	 */
	public void dispose();
	
	
	/**
	 * The data may be filtered using an jython script at this location.
	 * If so an IFilterDecorator is created on the plotting system and 
	 * set active for the data plotted by this filter.
	 * 
	 * @return path to jython file to use as a plot filter
	 */
	public String getFilterPath();
	
	/**
	 * A file which is used to filter this data.
	 * @param filterFile
	 */
	public void setFilterPath(String filterFile);

	/**
	 * The path to the data file (if any) from which the data came.
	 * @return file path to data file, may be null.
	 */
	public String getFilePath();

	/**
	 * 
	 * @return true if the dataset behind this transferable is dynamic
	 */
	public boolean isDynamic();
	
	/**
	 * Add a data listener (if this is not a dynamic transferable, does nothing)
	 * @param l
	 */
	public void addDataListener(IDataListener l);

	/**
	 * Add a data listener (if this is not a dynamic transferable, does nothing)
	 * @param l
	 */
	public void removeDataListener(IDataListener l);

}