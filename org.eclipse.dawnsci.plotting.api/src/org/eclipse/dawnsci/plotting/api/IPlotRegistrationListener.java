/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.plotting.api;

public interface IPlotRegistrationListener {
	
	/**
	 * If interested in a specific name only, implement this method to return non-null
	 * @return
	 */
	String getPlottingSystemName();

	/**
	 * Called when system registered. NOTE system exists at this point but you cannot plot to 
	 * it or 
	 * @param evt
	 */
	void plottingSystemRegistered(PlotRegistrationEvent evt);
	
	/**
	 * Called when system unregistered.
	 * @param evt
	 */
	void plottingSystemUnregistered(PlotRegistrationEvent evt);

	/**
	 * Called when the plotting system exists, you can add listeners to it and
	 * do plots etc.
	 * 
	 * @param plotRegistrationEvent
	 */
	void plottingSystemCreated(PlotRegistrationEvent evt);
	
	
	public class Stub implements IPlotRegistrationListener {

		@Override
		public String getPlottingSystemName() {
			return null;
		}

		@Override
		public void plottingSystemRegistered(PlotRegistrationEvent evt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void plottingSystemUnregistered(PlotRegistrationEvent evt) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void plottingSystemCreated(PlotRegistrationEvent evt) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
