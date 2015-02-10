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
package org.eclipse.dawnsci.plotting.api.tool;

import org.eclipse.dawnsci.plotting.api.tool.IToolPage.ToolPageRole;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This system allows one to get the page
 * which corresponds to the tool the user would
 * like to use with their plotting system. The
 * page may contain its own plotting or it may
 * create selection regions on the main IPlottingSystem.
 * 
 * @author Matthew Gerring
 *
 */
public interface IToolPageSystem {

	/**
	 * Convenience method for getCurrentToolPage(...). Checks if 1D or 2D
	 * is plotted and asks for IToolPage using getCurrentToolPage(...).
	 * 
	 * @return the active tool page
	 */
	public IToolPage getActiveTool();
	
	/**
	 * Get the current tool page that the user would like to use.
	 * Fitting, profile, derivative etc. Null if no selection has been made.
	 * @param role, may be null to get last used page.
	 * @return tool page with may be EmptyTool or null
	 */
	public IToolPage getCurrentToolPage(ToolPageRole role);
	
	/**
	 * Add a tool change listener. If the user changes preferred tool
	 * this listener will be called so that any views showing the current
	 * tool are updated. This method is always implemented as a HashSet
	 * to avoid duplicates being added.
	 * 
	 * @param l
	 */
	public void addToolChangeListener(IToolChangeListener l);
	
	/**
	 * Remove a tool change listener if one has been addded.
	 * @param l
	 */
	public void removeToolChangeListener(IToolChangeListener l);

	/**
	 * Get a tool page by id for this tool page system.
	 * @param toolId
	 * @return
	 */
	public IToolPage getToolPage(String toolId);
	
	/**
	 * The tool system keeps a reference to all tools.
	 * 
	 * Calling this method removes this tool from the cache of tools
	 * (and leaves a new stub in its place). It then
	 * disposes the UI of the tool, if one has been created. The dispose()
	 * method of the tool will also be called.
	 */
	public void disposeToolPage(String id);
	
	/**
	 * Creates a new tool page using the id.
	 * @param toolId
	 * @return
	 */
	public IToolPage createToolPage(String toolId) throws Exception;

	/**
	 * Clears any cached tools, can be used during dispose methods.
	 */
	public void clearCachedTools();

	/**
	 * If the system is visible to the user and active,
	 * it will return true here.
	 * @param active the active part that this system may be active inside.
	 * @return
	 */
	public boolean isActive(IWorkbenchPart active);
	
	/**
	 * Tools appear in views on pages normally. Sometimes it is required to define
	 * a special Composite for the tool to be shown on. For instance when showing a 
	 * Dialog to the user which contains a plotting system and a tool.
	 * 
	 * Once set the tools for this plotting system are locked to this composite. Calling
	 * again with composite=null may cause undesirable results.
	 * 
	 * @param composite
	 */
	public void setToolComposite(Composite composite);

	/**
	 * 
	 * @param toolId
	 * @param role
	 * @param viewId
	 * @return
	 * @throws Exception
	 */
	public boolean setToolVisible(final String toolId, final ToolPageRole role, final String viewId) throws Exception;
}
