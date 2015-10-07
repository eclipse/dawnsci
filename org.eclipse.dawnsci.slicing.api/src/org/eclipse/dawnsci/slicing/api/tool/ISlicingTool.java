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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;

/**
 * A tool which integrates to the slicing system to 
 * provide difference kinds of slices. For instance 1D, 2D Surface, Hyper 3D.
 * 
 * Normally a tool is added by extending AbstractSlicingTool
 * 
 * @author Matthew Gerring
 *
 */
public interface ISlicingTool extends IAdaptable {
	
	/**
	 * You may optionally create some UI. If you do, this
	 * should be setVisible(false) when created a then true
	 * on militarize and false again on demilitarize;
	 * @param parent
	 */
	public void createToolComponent(Composite parent);
	
	/**
	 * Called when the tool changes the slicing and
	 * potentially does the first slice in the new 
	 * format. It will also make any UI changes so 
	 * that the UI is set up for this slicing methodology.
	 * 
	 * @param if the tool is being militarized after a new slice has been done.
	 */
	public void militarize(boolean newSlice);
	
	
	/**
	 * Called on a militarized tool to demilitarize it and
	 * remove it from active service.
	 */
	public void demilitarize();


	
	/**
	 * The preferred plot type which the tool is active with.
	 * 
	 * Normally one of the enum PlotType however may be a custom
	 * objects because some plots can provide their own custom UI
	 * for slicing. For instance Hyper3D does this.
	 * 
	 * @return the plot type which will be some kind of enum but 
	 *         can be a custom defined one.
	 */
	public Enum<?> getSliceType();
	
	/**
	 * The id of this tool as set in the extension point
	 * @return
	 */
	public String getToolId();
	
	/**
	 * The id of this tool as set in the extension point
	 * @param toolId
	 */
	public void setToolId(String toolId);

	/**
	 * Called internally to ensure that there is an active slicing system available.
	 * @param system
	 */
	public void setSlicingSystem(ISliceSystem system);
	
	/**
	 * 
	 * @return system -  the Slicing System which the tool is registered with.
	 */
	public ISliceSystem getSlicingSystem();

	/**
	 * Called to dispose of tool
	 */
	public void dispose();


	/**
	 * This method may be used to provide an alternative action for the
	 * too. If used, the tool will not have the icon defined by the extension point
	 * set and instead return a custom defined action by the tool.
	 * 
	 * This can be used where a slice tool has several modes which should be chosen from
	 * the toolbar. For instance the hyper 3D tool which can several options for slicing.
	 * 
	 * @return the action to be used for this tool, may be null.
	 */
	public IAction createAction();
	
	/**
	 * If the tool requires the slice system to do a slice, return true.
	 * If the tool does its own custom slice, return false.
	 * @return
	 */
	public boolean isSliceRequired();
	
	/**
	 * Defines if the user may use this tool with the advanced action.
	 * @return
	 */
	public boolean isAdvancedSupported();

}
