/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api.filter;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.TraceWillPlotEvent;

/**
 * Uses a job to process the filter which might take a second or two
 * to run and therefore should be run in a job.
 * 
 * Uses the name of the original data to update the value, therefore the
 * filtered data ends up getting the same name.
 */
public abstract class AbstractDelayedFilter extends AbstractPlottingFilter  {
	
	private FilterJob       job;
	private IPlottingSystem<?> system;
	private String          filterName;
	private TraceWillPlotEvent evt;
	private boolean         isOn=true;

	@Override
	public void filter(IPlottingSystem<?> system, TraceWillPlotEvent evt) throws Exception {

		if (job == null)
			this.job = new FilterJob(filterName);

		if (!job.getName().equals(filterName))
			this.job = new FilterJob(filterName);
		this.system = system;
		final ITrace trace = (ITrace)evt.getSource();
		if (trace.getRank()!=getRank()) {
			if (getRank()>0) return;
		}
		
		this.evt = evt;
		if (!isOn) return;
		cache.add(new OriginalData(evt));
		
		system.setEnabled(false);
		job.schedule();
	}
	
	private class FilterJob extends Job {
				
		public FilterJob(String filterName) {
			super(filterName);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			try {
				isOn = false;
				if (getRank()==1) {
					IDataset[] filtered = filter(evt.getXData(), evt.getYData());
					system.updatePlot1D(filtered[0]!=null?filtered[0]:evt.getXData(), Arrays.asList(filtered[1]), monitor);
				} else {
					Object[] filtered = filter(evt.getImage(), evt.getAxes());
					system.updatePlot2D((IDataset)filtered[0], (List<IDataset>)filtered[1], getName(), monitor);
				}
				fireFilterApplied(new FilterEvent(this));

			} catch (Exception ne) {
				ne.printStackTrace();
				return Status.CANCEL_STATUS;
			} finally {
				isOn = true;
				system.setEnabled(true);
			}

			return Status.OK_STATUS;
		}
	}

	public boolean isFilterOn() {
		return isOn;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
