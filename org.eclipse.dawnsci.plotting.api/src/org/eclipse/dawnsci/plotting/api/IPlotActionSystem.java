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
package org.eclipse.dawnsci.plotting.api;

import org.eclipse.dawnsci.plotting.api.tool.IToolPage.ToolPageRole;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;

/**
 * Interface for giving access to filling custom actions used in the plotting.
 * 
 * The action filled will be exactly the same one as the plotting uses. Therefore
 * visible, enabled settings will take effect across all IContributionManagers
 * 
 * @author Matthew Gerring
 *
 */
public interface IPlotActionSystem extends ITraceActionProvider{
	
	/**
	 * Removes this id from the action bars and from the caches of actions by plot type.
	 * 
	 * Effectively permanently removes this id.
	 * 
	 * @param id
	 */
	public void remove(String id);

	/**
	 * Gets the zoom actions and fills them into this contribution manager.
	 * @param man
	 */
	public void fillZoomActions(IContributionManager man);
	

	/**
	 * Fill for region actions.
	 * @param man
	 */
	public void fillRegionActions(IContributionManager man);

	
	/**
	 * Fill with undo/redo actions
	 * @param man
	 */
	public void fillUndoActions(IContributionManager man);

	
	/**
	 * Fill with print actions.
	 * @param man
	 */
	public void fillPrintActions(IContributionManager man);
	
	/**
	 * Fill with annotation actions
	 * @param man
	 */
	public void fillAnnotationActions(IContributionManager man);
	
	/**
	 * Fill the action(s) for a given tool type
	 * @param man
	 */
	public void fillToolActions(IContributionManager man, ToolPageRole role);

	
	/**
	 * Create tool actions for a given role.
	 * @param role3d
	 * @param string
	 */
	public void createToolDimensionalActions(ToolPageRole role3d, String viewId);

	/**
	 * Creates a group, generally do this before registerAction(...)
	 * 
	 * @param groupName
	 * @param type
	 */
	public void registerGroup(String groupName, ManagerType type);
	
	/**
	 * 
	 * @param groupName
	 * @param action
	 * @param actionType
	 * @param manType
	 */
	public void registerAction(String groupName, IAction action, ActionType actionType, ManagerType manType);
}
