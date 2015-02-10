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

import java.io.Serializable;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageBookViewPage;

/**
 * This class represents a page in a page book view which
 * is associated with a specific tool in the plotting system. The tool
 * is a 1D tool meaning it is linked to a 1D plot. It itself does
 * not show a 1D plot normally and if it does, no tools menu will be
 * shown. This prevents recursion which it was decided is too complex
 * for users.
 * 
 * The tool this page connects to currently has some limitations which
 * should be adhered to for the over all UI design to be coherent:
 * 
 * 1. Implement getToolRole() to provide information as to where the tool
 *    page should appear. For instance ToolPageRole.ROLE_2D would have a 1D plot
 *    with tool pages available if their type is ToolPageRole.ROLE_1D, normally.
 *    
 * 2. Do not plot things in an IToolPage with a 1D role directly. These are 1D
 * tools and should plot back to the original 1D view if they have 1D data to plot.
 * This avoids recursion.
 * 
 * If you have to plot on a 1D tool, by default the tools menu will not be shown so you cannot
 * do recursive tools. (This can be overridden by implementing getAdpater(...) for
 * IToolPageSystem if you really need to, but you are breaking the rules a little and
 * some things may not work.)
 * 
 * 
 *
 * 
 * @author Matthew Gerring
 *
 */
public interface IToolPage extends IPageBookViewPage, IAdaptable {
	
	public enum ToolPageRole {
		ROLE_2D("org.dawb.common.ui.plot.tool.ROLE_2D", false, true, "icons/plot-tool-any.png", "Image tools", "Image tools used to profile and inspect images."),  // For instance LineProfile, Profile
		ROLE_1D("org.dawb.common.ui.plot.tool.ROLE_1D", true, false, "icons/plot-tool-any.png", "XY plotting", "XY plotting tools"),  // 1D only
		
		/**
		 * @Deprecated Workaround: Use two extension point declarations and in getToolPageRole() test toolid
		 * to return the correct role.
		 */
		@Deprecated
		ROLE_1D_AND_2D("org.dawb.common.ui.plot.tool.ROLE_1D_AND_2D", true, true, "icons/plot-tool-any.png", "Plotting tools", "Plotting tools (used both for images and XY plots)"), // Measure, derivative, peak fitting
		ROLE_3D("org.dawb.common.ui.plot.tool.ROLE_3D", false, false, "icons/plot-tool-any.png", "3D tools", "3D plotting tools"); // SubSurface
		
		private boolean is1D;
		private boolean is2D;
		private String  id;
		private String imagePath;
		private String label;
		private String tooltip;

		ToolPageRole(String id, boolean is1D, boolean is2D, String imagePath, String label, String tooltip) {
			this.id   = id;
			this.is1D = is1D;
			this.is2D = is2D;
			this.imagePath = imagePath;
			this.label = label;
			this.tooltip = tooltip;
		}
		
		public boolean is1D() {
			return is1D;
		}
		public boolean is2D() {
			return is2D;
		}
		public boolean is3D() {
			return !is1D()&&!is2D();
		}
		/**
		 * Either 1,2 or 3. A tool cannot currently act on
		 * more dimensions and will return -1 if 
		 * it cannot determine rank.
		 * 
		 * @return
		 */
		public int getPreferredRank() {
			if (is1D()) return 1;
			if (is2D()) return 2;
			if (is3D()) return 3;
			return -1;
		}

		public String getId() {
			return id;
		}

		public String getImagePath() {
			return imagePath;
		}

		public String getLabel() {
			return label;
		}

		public String getTooltip() {
			return tooltip;
		}

		public boolean isCompatible(ToolPageRole role) {
			if (role==this) return true;
			if (ToolPageRole.ROLE_1D_AND_2D!=this) return false;
			if (role==ROLE_1D || role==ROLE_2D) return true;
			return false;
		}
	}
	
	public ToolPageRole getToolPageRole();

	/**
	 * the title for the tool.
	 * @return
	 */
	public String getTitle();
	
	/**
	 * This title will be show in the part displaying this tool
	 * @param title
	 */
	public void setTitle(final String title);
	
	/**
	 * Called when the tool is read from extension for a given 
	 * plotting system instance, used internally.
	 * 
	 * @param system
	 */
	public void setPlottingSystem(IPlottingSystem system);
	
	/**
	 * returns the main plotting system that the tool is
	 * acting on - not the plotting system that this tool
	 * may be showing.
	 * 
	 * @return
	 */
	public IPlottingSystem getPlottingSystem();
	
	/**
	 * The tool system that this page is active within.
	 * @return
	 */
	public IToolPageSystem getToolSystem();
	
	/**
	 * Set the IToolPageSystem, used internally
	 * @param system
	 */
	public void setToolSystem(IToolPageSystem system);
	
	/**
	 * Return true if this is the active tool.
	 * @return
	 */
	public boolean isActive();
	
	/**
	 * Called if the tool is chosen when it already exists.
	 * Please use this method to connection all your listeners on the plotting system
	 * of the parent part.
	 */
	public void activate();
	
	/**
	 * Called if the tool swapped for another but is not disposed.
	 * Please use this method to disconnect all your listeners on the plotting system
	 * of the parent part.
	 */
	public void deactivate();

	/**
	 * Normally the page containing the
	 * plotting system which originated the data. For instance if the data
	 * is from an ImageEditor then this will be the part. If the tool is on a view
	 * and then a sub-tool is opened the part will still be the original image part.
	 * However the method getViewPart can be used to determine the view part which
	 * we are running the tool on in this case.
	 * 
	 * @return
	 */
	public IWorkbenchPart getPart();
	
	/**
	 * Sets the part this page is linked to.
	 * @param part
	 */
	public void setPart(IWorkbenchPart part);

	/**
	 * The ImageDescriptor for the page
	 * @param des
	 */
	public void setImageDescriptor(ImageDescriptor des);
	
	/**
	 * The ImageDescriptor for the page
	 * @return
	 */
	public ImageDescriptor getImageDescriptor();
	
	/**
	 * 
	 * @return IViewPart the view that the tool is being shown on, null if not on a view part.
	 */
	public IViewPart getViewPart();
	
	/**
	 * Called by the view part using the tool to declare the parent part.
	 * @param viewPart
	 */
	public void setViewPart(IViewPart viewPart);
	
	/**
	 * The unique id of the tool, used for opening it in a static page.
	 * @return
	 */
	public String getToolId();
	
	/**
	 * Designed to be used when the tool is created only.
	 * @return
	 */
	public void setToolId(String id);

	/**
	 * Used to clone a tool when the tool is opened in its own view.
	 * @return
	 */
	public IToolPage cloneTool()  throws Exception;

	/**
	 * 
	 * @return true if this one got disposed.
	 */
	public boolean isDisposed();
	
	/**
	 * May be null
	 * @return
	 */
	public String getCheatSheetId();
	
	/**
	 * May be null
	 * @return
	 */
	public void setCheatSheetId(String cheatId);

	/**
	 * Override in your tool page. If the page is opened in a dedicated view,
	 * a new version of the tool is created. However there may be data in the original
	 * linked tool that you wish to sync. In this case override sync and a reference
	 * to the original tool will be provided when it is opened in a dedicated view.
	 * 
	 * @param with
	 */
	public void sync(IToolPage with);

	/**
	 * @return true if the tool should be opened in a dedicated view always
	 * @return
	 */
	public boolean isAlwaysSeparateView();
	
	/**
	 * The PlottingSystem for the tool if there is one, otherwise null.
	 * 
	 * @return
	 */
	public IPlottingSystem getToolPlottingSystem();

	/**
	 * When a tool is opened in a dedicated view, it can enter a static mode.
	 * In this mode the tool is the same for all parts. By default this will return
	 * false and tools wishing to be static should override it. 
	 * 
	 * They will also need to override setPlottingSystem(...) and other configuration methods
	 * in order to work properly.
	 * 
	 * @return
	 */
	public boolean isStaticTool();
	
	/**
	 * This method will be implemented to initiate the tool from existing data. For instance
	 * the UserPlotBeam may be passed into this call to set up a tool from the workflows.
	 * 
	 * By default the AbstractToolPage will do nothing
	 * many tools will return null unless they have been specifically altered to encapsulate
	 * and return their data.
	 * 
	 * @return data specific to tool
	 */
	public void setToolData(Serializable data);

	/**
	 * This method will be implemented to return any data which is defined by the tool
	 * and may be used elsewhere. By default the AbstractToolPage will return null and
	 * many tools will return null unless they have been specifically altered to encapsulate
	 * and return their data.
	 * 
	 * @return data specific to tool
	 */
	public Serializable getToolData();

}
