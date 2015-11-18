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
package org.eclipse.dawnsci.plotting.api.filter;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.TraceWillPlotEvent;

/**
 * Convenience class for filtering plots based on name. If the
 * name passed in matches the plot data name then the filter will be active.
 * 
 * If 
 * 
 * If a filter has been already done on a trace with this name,
 * it will also not be done again.
 * 
 * @author Matthew Gerring
 *
 */
public abstract class UniqueNamedPlottingFilter extends AbstractPlottingFilter {

	protected String  dataName;

	/**
	 * Only traces with this name will be filtered.
	 * @param name
	 */
	public UniqueNamedPlottingFilter(String dataName) {
		this.dataName = dataName;
	}
	
	public int getRank() {
		return -1; // Not used!
	}
	
	@Override
	public void filter(final IPlottingSystem<?> system, final TraceWillPlotEvent evt) throws Exception {
		
		
		final ITrace trace = (ITrace)evt.getSource();
		final String dName = trace.getDataName() != null ? trace.getDataName() : null; 
		if (!dataName.equals(dName)) return;
				
		cache.add(new OriginalData(evt));
		
		if (trace.getRank()==1) {
			IDataset[] filtered = filter(evt.getXData(), evt.getYData());
			if (filtered!=null) evt.setLineData(filtered[0]!=null?filtered[0]:evt.getXData(), filtered[1]);
		} else if (trace.getRank()==2) {
			Object[] filtered = filter(evt.getImage(), evt.getAxes());
			if (filtered!=null) evt.setImageData((IDataset)filtered[0], (List<IDataset>)filtered[1]);
		}
		
	}

}
