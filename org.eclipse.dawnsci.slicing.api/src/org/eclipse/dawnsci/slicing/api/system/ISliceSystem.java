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

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.io.SliceObject;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.slicing.api.tool.ISlicingTool;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * A generic interface for slicing data. The system must be able 
 * to create a UI for slicing which is added to the SWT part. It is
 * provided from the loader with an ILazyDataset of the whole data
 * and provides a mechanism for slicing the lazy dataset.
 * 
 * The inspector may be extended to have additional tools. When chosen these
 * tools may replace the original plotting system with a custom one for more
 * flexible slicing.
 * 
 * <code>
 * ISliceComponent slicer = SlicerFactory.createSliceComponent();
 * //Optional
 * slicer.setPlottingSystem(...); // The plotting system which you will plot slices on to.
 * sliceComponent.addCustomAction(...);
 * 
 * // Essential
 * slicer.createPartControl(...)
 * 
 * // Essential
 * SliceSource source = new SliceSource(...)
 * slicer.setData(source);
 * 
 * 
 * //UI created and can now slice things.
 * 
 * </code>
 * 
 * @author Matthew Gerring
 *
 */
public interface ISliceSystem {
	
	/**
	 * Main method for creating a part that can slice data.
	 * Once called the UI for slicing is created.
	 * @param parent
	 * @return
	 */
	public Control createPartControl(Composite parent);
	
	/**
	 * Set the data in the form of a source which provides the ILazyDataset
	 * @param source
	 */
	public void setData(SliceSource source);
	
	/**
	 * 
	 * @param system
	 */
	public void setPlottingSystem(IPlottingSystem<Composite> system);
	
	/**
	 * 
	 * @return system
	 */
	public IPlottingSystem<Composite> getPlottingSystem();
	
	/**
	 * 
	 * @return the current data which we are slicing.
	 */
	public SliceSource getData();
	
	/**
	 * Set the way that the slicing should be set up in the slice system.
	 */
	public void setDimsDataList(DimsDataList sliceSetup);

	/**
	 * 
	 * @return an object which provides information about how the user
	 * set up the slice in the UI.
	 */
	public DimsDataList getDimsDataList();
	
	/**
	 * A name describing the current slice 
	 * @return
	 */
	public String getSliceName();


	/**
	 * To change the slice value by data index and optionally replot a slice
	 * call this method.
	 * 
	 * @param dimension to slice (if slicing it already)
	 * @param index
	 * @param doReslice - true to replot the slice straight away.
	 * @throws IndexOutOfBoundsException if the dimension is not already being
	 *         sliced or the index is outside the size of the dimension.
	 */
	public void setSliceIndex(int dimension, int index, boolean doReslice);

	/**
	 * The slicer should be disposed when finished with and once this is called it
	 * will no longer be usable. 
	 */
	public void dispose();

	/**
	 * Call to reprocess a slice where the slice system
	 * is slicing using a standard tool like line or image.
	 * @param disable - true to grey out the slicing while it changes mode.
	 */
	public void update(boolean disable);

	/**
	 * Refresh the widget showing the slice setup.
	 * Can be used after editing the dimensional data list
	 * to define the slice.
	 */
	public void refresh();

	/**
	 * Set the id of the IViewPart which implements ISliceGallery to be used
	 * as a gallery view of the dataset which is being sliced.
	 * 
	 * @param sliceGalleryId
	 */
	public void setSliceGalleryId(String sliceGalleryId);

	/**
	 * 
	 * @param false cancels any slice and sets this component invisible.
	 */
	public void setVisible(boolean vis);

	/**
	 * Turn the toolbar on or off.
	 * @param vis
	 */
	public void setToolbarVisible(boolean vis);
	
	/**
	 * If the slice component has specialist slice actions in a toolbar
	 * or tabbed panes. This will disable or enable the actions.
	 * @param b
	 */
	public void setSliceActionsEnabled(boolean enabled);
	
	/**
	 * Set an action visiable by enum value.
	 * @param xy
	 * @param b
	 */
	@SuppressWarnings("rawtypes")
	public void setSliceActionEnabled(Enum sliceType, boolean b);

	/**
	 * 
	 * @return true if advanced slicing such as MEAN, MEDIAN and SUM are allowed
	 */
	public boolean isAdvanced();
	
	
	/**
	 * Sets if slicing is allowed in the slice widget. If set to false
	 * the slice widget (e.g. a table) will become deactivated.
	 * @param enabled
	 */
	public void setSlicingEnabled(boolean enabled);

	/**
	 * Sets a string to be used as explaination text somewhere in the system depending
	 * on implementation.
	 * 
	 * @param string
	 */
	public void setLabel(String string);

	/**
	 * Sets wether the slice component should show axes or not.
	 * @param b
	 */
	public void setAxesVisible(boolean vis);

	/**
	 * Add extra action(s) which should appear on the toolbar of the slicer. 
	 * @param action
	 */
	public void addCustomAction(IAction action);

	/**
	 * Add a listener to be notified when the dimensions change.
	 * @param l
	 */
	public void addDimensionalListener(DimensionalListener l);
	
	/**
	 * Remove a DimensionalListener
	 * @param l
	 */
	public void removeDimensionalListener(DimensionalListener l);
	
	/**
	 * Add a listener to be notified when the dimensions change.
	 * @param l
	 */
	public void addAxisChoiceListener(AxisChoiceListener l);
	
	/**
	 * Remove a DimensionalListener
	 * @param l
	 */
	public void removeAxisChoiceListener(AxisChoiceListener l);


	/**
	 * The names (nexus path to axis normally) of the axis by dimension
	 * number.
	 * 
	 * @return map of axes.
	 */
	public Map<Integer, String> getAxesNames();
	
	/**
	 * 
	 * @return the slice object used to produce the current slice.
	 */
	public SliceObject getCurrentSlice();

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Enum getSliceType();

	/**
	 * Normally one of the PlotType enums
	 * @param plotType
	 */
	@SuppressWarnings("rawtypes")
	public void setSliceType(Enum type);
	
	/**
	 * Provide information as to the current slice methodology so that
	 * the component can show which tool is currently active.
	 * 
	 * Optional to use this method
	 * 
	 * @param label
	 * @param icon - may be null, if null and sliceType!=null the image descriptor
	 *        of the action for this sliceType will be used.
	 */
	public void setSliceTypeInfo(String label, ImageDescriptor icon);

	
	/**
	 * Demilitarizes the current tool (if different) and miliarizes this tool.
	 * 
	 * @param tool
	 */
	public void militarize(ISlicingTool tool);

	/**
	 * Greys out the table and actions. Should normally be called within a try finally.
	 * @param b
	 */
	public void setEnabled(boolean b);

	/**
	 * true if enabled
	 * @return
	 */
	public boolean isEnabled();

    /**
     * Set the mode with which to slice the data. 
     * @param mode
     */
	public void setRangeMode(RangeMode mode);
	
    /**
     * Set the mode with which to slice the data. 
     * @param mode
     */
	public RangeMode getRangeMode();

	/**
	 * 
	 * @return the current slice tool which the user has chosen, or null
	 */
	public ISlicingTool getActiveTool();
	
	/**
	 * The metadata of the current slice, if any
	 * @return
	 */
	public IMetadata getSliceMetadata();
	
	/**
	 * The metadata of the current slice, if any
	 * @return
	 */
	public void setSliceMetadata(IMetadata sliceMeta);

	/**
	 * May be null
	 * @return
	 */
	public ISelectionProvider getSelectionProvider();
	
	/**
	 * Current slice
	 * @return
	 */
	public IDataset getSlice();
	
	/**
	 * 
	 * @param slice
	 */
	public void setSlice(IDataset slice);
}
