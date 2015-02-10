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
package org.eclipse.dawnsci.slicing.api;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.io.SliceObject;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.doe.DOEUtils;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.PlotType;
import org.eclipse.dawnsci.slicing.api.system.AxisChoiceEvent;
import org.eclipse.dawnsci.slicing.api.system.AxisChoiceListener;
import org.eclipse.dawnsci.slicing.api.system.AxisType;
import org.eclipse.dawnsci.slicing.api.system.DimensionalEvent;
import org.eclipse.dawnsci.slicing.api.system.DimensionalListener;
import org.eclipse.dawnsci.slicing.api.system.DimsData;
import org.eclipse.dawnsci.slicing.api.system.DimsDataList;
import org.eclipse.dawnsci.slicing.api.system.ISliceGallery;
import org.eclipse.dawnsci.slicing.api.system.ISliceSystem;
import org.eclipse.dawnsci.slicing.api.system.RangeMode;
import org.eclipse.dawnsci.slicing.api.tool.ISlicingTool;
import org.eclipse.dawnsci.slicing.api.util.SliceUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Do not expose this class to copying. Instead use ISliceSystem
 * @author Matthew Gerring
 * @internal
 */
public abstract class AbstractSliceSystem implements ISliceSystem {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractSliceSystem.class);

	protected DimsDataList    dimsDataList;
	protected IPlottingSystem plottingSystem;
	protected String          sliceReceiverId;
	private List<IAction>     customActions;
	protected SliceObject     sliceObject;
	protected RangeMode       rangeMode=RangeMode.NO_RANGES;
	protected Action          advanced;
	
	protected Enum        sliceType=PlotType.IMAGE;
	protected IToolBarManager sliceToolbar;
	
	@Override
	public void setPlottingSystem(IPlottingSystem system) {
		this.plottingSystem = system;
	}

	@Override
	public IPlottingSystem getPlottingSystem() {
		return plottingSystem;
	}
	public SliceObject getCurrentSlice() {
		return sliceObject;
	}

	@Override
	public void setDimsDataList(DimsDataList sliceSetup) {
		this.dimsDataList = sliceSetup;
	}

	@Override
	public DimsDataList getDimsDataList() {
		return dimsDataList;
	}
	
	/**
	 * May be implemented to save the current slice set up.
	 */
	protected abstract void saveSliceSettings();
	
	private ISlicingTool activeTool;

	protected Map<String, ISlicingTool> sliceTools;

	/**
	 * Creates the slice tools by reading extension points
	 * for the slice tools.
	 * 
	 * @return
	 */
	protected IToolBarManager createSliceTools() {
				
		final ToolBarManager man = new ToolBarManager(SWT.FLAT|SWT.RIGHT|SWT.WRAP);
		man.add(new Separator("sliceTools"));
		return createSliceTools(man);
	}

	/**
	 * Creates the slice tools by reading extension points
	 * for the slice tools.
	 * 
	 * @return
	 */
	protected IToolBarManager createSliceTools(final ToolBarManager man) {
				
		final IConfigurationElement[] eles = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.slicing.api.slicingTool");

  		plotTypeActions= new HashMap<Enum, IAction>();
  		this.sliceTools= new LinkedHashMap<String, ISlicingTool>(17);

		for (IConfigurationElement e : eles) {
			
			final ISlicingTool slicingTool = createSliceTool(e);
			
			final String requireSep = e.getAttribute("separator");
			if ("true".equals(requireSep)) man.add(new Separator());
			
			IAction action = slicingTool.createAction();
			if (action==null) action = createSliceToolAction(e, slicingTool);
			man.add(action);
			plotTypeActions.put(slicingTool.getSliceType(), action);

			sliceTools.put(slicingTool.getToolId(), slicingTool);
		}
								
		return man;
	}
	
	private IAction createSliceToolAction(IConfigurationElement e, final ISlicingTool slicingTool) {
		
		String toolTip = e.getAttribute("tooltip");
		if (toolTip==null) toolTip = slicingTool.getToolId();

		final Action action = new Action(toolTip, IAction.AS_CHECK_BOX) {
        	public void run() {
        		militarize(slicingTool);
        	}
        };
        
    	final String   icon  = e.getAttribute("icon");
    	if (icon!=null) {
	    	final String   id    = e.getContributor().getName();
	    	final Bundle   bundle= Platform.getBundle(id);
	    	final URL      entry = bundle.getEntry(icon);
	    	final ImageDescriptor des = ImageDescriptor.createFromURL(entry);
	    	action.setImageDescriptor(des);
    	}

		action.setId(slicingTool.getToolId());	
	    return action;	
	}
	
	/**
	 * Disarms the current tool (if different) and arms this tool.
	 * 
	 * @param tool
	 */
	@Override
	public void militarize(ISlicingTool slicingTool) {
		saveSliceSettings();
		if (activeTool!=null && slicingTool!=activeTool) {
			activeTool.demilitarize();
		}
		
		// If we don't support advanced slicing (averaging etc.) then disable
		if (advanced!=null) advanced.setEnabled(slicingTool.isAdvancedSupported());
		if (!slicingTool.isAdvancedSupported()) {
            DimsDataList list = getDimsDataList();
            for (DimsData dd : list.iterable()) {
            	if (dd.getPlotAxis().isAdvanced()) {
            		dd.setSliceRange(null);
            		dd.setPlotAxis(AxisType.SLICE);
            	}
			}
		}

		slicingTool.militarize(false);
		activeTool = slicingTool;
		
		// check the correct actions
		for (Enum key : plotTypeActions.keySet()) {
			final IAction action = plotTypeActions.get(key);
			action.setChecked(key==sliceType);
		}
		
	}
	
	/**
	 * 
	 * @return null if ok, error message if errors.
	 */
	protected String checkErrors() {
		
		boolean isX = false;
		for (int i = 0; i < dimsDataList.size(); i++) {
			if (dimsDataList.getDimsData(i).getPlotAxis()==AxisType.X) isX = true;
		}
		boolean isY = false;
		for (int i = 0; i < dimsDataList.size(); i++) {
			if (dimsDataList.getDimsData(i).getPlotAxis()==AxisType.Y) isY = true;
		}
		boolean isYMany = false;
		for (int i = 0; i < dimsDataList.size(); i++) {
			if (dimsDataList.getDimsData(i).getPlotAxis()==AxisType.Y_MANY) isYMany = true;
		}
		boolean isZ = false;
		for (int i = 0; i < dimsDataList.size(); i++) {
			if (dimsDataList.getDimsData(i).getPlotAxis()==AxisType.Z) isZ = true;
		}

		String errorMessage = "";
		boolean ok = false;
		
		int dimCount = getDimensions(getSliceType());

		if (dimCount==1) {
			ok = isX;
			errorMessage = "Please set an X axis.";
		} else if (dimCount==2){
			ok = (isX&&isY&&getSliceType()!=PlotType.XY_STACKED)     ||
				 (isX&&isYMany&&getSliceType()==PlotType.XY_STACKED) ;
			
			errorMessage = !isYMany
					     ? "Please set an X and Y (Many) axis or switch to 'Slice as line plot'."
					     : "Please set an X and Y axis or switch to 'Slice as line plot'.";
		} else if (dimCount==3){
			ok = isX&&isY&&isZ;
			errorMessage = "Please set an X, Y and Z axis or switch to 'Slice as image plot'.";
		}
		
		if (ok) { // Check size of ranges.
			try {
				for (DimsData dd : dimsDataList.iterable()) {
					if (dd.isTextRange()) {
						if (dd.getSliceRange(true)==null) { // Set a range over all the data
							if (rangeMode == RangeMode.MULTI_RANGE) {
								dd.setSliceRange("all");
							} else {
								int max = Math.min(25, getData().getLazySet().getShape()[dd.getDimension()]-1);
								dd.setSliceRange("0:"+max);
							}
							
						}
						final int size = DOEUtils.getSize(dd.getSliceRange(true), null);
						if (size>=getData().getLazySet().getShape()[dd.getDimension()] || size<1) {
							errorMessage = "The slice '"+dd.getSliceRange(true)+"' does not fit the data.";
							ok = false;
							break;
						}
					}
				}
			} catch (Exception ignored) {
				// ignore problem 
			}
		}

		return ok ? null : errorMessage;
	}


	protected int getDimensions(Enum st)  {
		
		try {
			final Method dimCountMethod = st.getClass().getMethod("getDimensions");
			final int dimCount = (Integer)dimCountMethod.invoke(st);
			return dimCount;
		} catch (Exception ne) {
			logger.error("Slice type "+st+" must define a method called 'getDimensions'!", ne);
			return 0;
		}
	}

	private  Map<Enum, IAction> plotTypeActions;
	protected IAction getActionByPlotType(Object plotType) {
		if (plotTypeActions==null) return null;
		return plotTypeActions.get(plotType);
	}


	
	/**
	 * 
	 * @param e
	 * @return
	 */
	private ISlicingTool createSliceTool(IConfigurationElement e) {
    	
		ISlicingTool tool = null;
    	try {
    		tool  = (ISlicingTool)e.createExecutableExtension("class");
    	} catch (Throwable ne) {
    		logger.error("Cannot create tool page "+e.getAttribute("class"), ne);
    		return null;
    	}
    	tool.setToolId(e.getAttribute("id"));	       	
    	tool.setSlicingSystem(this);
    	
    	// TODO Provide the tool with a reference to the part with the
    	// slice will end up being showed in?
    	
    	return tool;
	}


	@Override
	public void dispose() {
		if (dimensionalListeners!=null) dimensionalListeners.clear();
		dimensionalListeners = null;
	}

	@Override
	public void setSliceGalleryId(String id) {
		this.sliceReceiverId = id;
	}
	
	protected void openGallery() {
		
		if (sliceReceiverId==null) return;
		SliceObject cs;
		try {
			final SliceObject current = getCurrentSlice();
			cs = SliceUtils.createSliceObject(dimsDataList, getData(), current);
		} catch (Exception e1) {
			logger.error("Cannot create a slice!");
			return;
		}
		
		IViewPart view;
		try {
			view = getActivePage().showView(sliceReceiverId);
		} catch (PartInitException e) {
			logger.error("Cannot find view "+sliceReceiverId);
			return;
		}
		if (view instanceof ISliceGallery) {
			((ISliceGallery)view).updateSlice(getData().getLazySet(), cs);
		}
		
	}
	private static IWorkbenchPage getActivePage() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		if (bench == null)
			return null;
		final IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
		if (window == null)
			return null;
		return window.getActivePage();
	}

	public void addCustomAction(IAction customAction) {
		if (customActions == null)customActions = new ArrayList<IAction>();
		customActions.add(customAction);
	}
	
	protected void createCustomActions(IContributionManager man) {
		if (customActions!=null) {
			man.add(new Separator("group5"));
			for (IAction action : customActions) man.add(action);
		}
	}


	private Collection<DimensionalListener> dimensionalListeners;
	@Override
	public void addDimensionalListener(DimensionalListener l) {
		if (dimensionalListeners==null) dimensionalListeners= new HashSet<DimensionalListener>(7);
		dimensionalListeners.add(l);
	}
	
	@Override
	public void removeDimensionalListener(DimensionalListener l) {
		if (dimensionalListeners==null) return;
		dimensionalListeners.remove(l);
	}
	
	protected void fireDimensionalListeners() {
		if (dimensionalListeners==null) return;
		final DimensionalEvent evt = new DimensionalEvent(this, dimsDataList);
		for (DimensionalListener l : dimensionalListeners) {
			l.dimensionsChanged(evt);
		}
	}
	
	private Collection<AxisChoiceListener> axisChoiceListeners;
	@Override
	public void addAxisChoiceListener(AxisChoiceListener l) {
		if (axisChoiceListeners==null) axisChoiceListeners= new HashSet<AxisChoiceListener>(7);
		axisChoiceListeners.add(l);
	}
	
	@Override
	public void removeAxisChoiceListener(AxisChoiceListener l) {
		if (axisChoiceListeners==null) return;
		axisChoiceListeners.remove(l);
	}
	
	protected void fireAxisChoiceListeners(AxisChoiceEvent evt) {
		if (axisChoiceListeners==null) return;
		for (AxisChoiceListener l : axisChoiceListeners) {
			l.axisChoicePerformed(evt);
		}
	}


	@Override
	public Enum getSliceType() {
		return sliceType;
	}

	@Override
	public void setSliceType(Enum plotType) {
		this.sliceType = plotType;
		setSliceTypeInfo(null, null);
		checkToolDimenionsOk();
	}
	
	/**
	 * Checks the tools and disables any which require mre dimensions 
	 * than we have
	 */
	protected void checkToolDimenionsOk() {
		
		final int rank = getData().getLazySet().getRank();
        for (Enum type : plotTypeActions.keySet()) {
        	
        	int dims = getDimensions(type);
        	
        	boolean enabled = dims<=rank;
        	if (enabled && sliceActionEnabledMap!=null && sliceActionEnabledMap.containsKey(type)) {
        		if (!sliceActionEnabledMap.get(type)) enabled = false;
        	}
        	plotTypeActions.get(type).setEnabled(enabled);
        }
	}
	

	public void setSliceActionsEnabled(boolean enabled) {
		
		if (sliceToolbar==null) return;
		final IContributionItem[] items = sliceToolbar.getItems();
		for (IContributionItem toolItem : items) {
			if (toolItem instanceof ActionContributionItem) {
				((ActionContributionItem)toolItem).getAction().setEnabled(enabled);
			}
		}
		sliceToolbar.update(true);
		
		if (plotTypeActions!=null) {
			if (sliceActionEnabledMap==null) sliceActionEnabledMap = new HashMap<Enum, Boolean>();
			for (Enum type : plotTypeActions.keySet()) sliceActionEnabledMap.put(type, false);
		}

	}
	
	private Map<Enum, Boolean> sliceActionEnabledMap;
	/**
	 * Throws an NPE if the action is not there.
	 */
	public void setSliceActionEnabled(Enum type, boolean enabled) {
		final IAction action = getActionByPlotType(type);
		action.setEnabled(enabled);
		if (sliceToolbar!=null) sliceToolbar.update(true);
		if (sliceActionEnabledMap==null) sliceActionEnabledMap = new HashMap<Enum, Boolean>();
		sliceActionEnabledMap.put(type, enabled);
	}


	/**
	 * Does nothing by default.
	 */
	@Override
	public void setSliceTypeInfo(String label, ImageDescriptor icon) {
		
	}
	
	/**
	 * 
	 * @return true if the current slice type is a 3D one.
	 */
	public boolean is3D() {
		return sliceType instanceof PlotType && ((PlotType)sliceType).is3D();
	}

	@Override
	public ISlicingTool getActiveTool() {
		return activeTool;
	}
	
	private static final String ADVANCED = "org.dawb.workbench.slicing.component.advanced";

	public boolean isAdvanced() {
		return Activator.getDefault().getPreferenceStore().getBoolean(ADVANCED);
	}
	protected void setAdvanced(boolean advanced) {
		Activator.getDefault().getPreferenceStore().setValue(ADVANCED, advanced);
	}

	public RangeMode getRangeMode() {
		return rangeMode;
	}

	public void setRangeMode(RangeMode rm) {
		this.rangeMode = rm;
	}
	
	private IMetadata sliceMetadata;
	/**
	 * The metadata of the current slice, if any
	 * @return
	 */
	public IMetadata getSliceMetadata() {
		return sliceMetadata;
	}
	
	/**
	 * The metadata of the current slice, if any
	 * @return
	 */
	public void setSliceMetadata(IMetadata sliceMeta) {
		this.sliceMetadata = sliceMeta;
	}

	private IDataset slice;

	public IDataset getSlice() {
		return slice;
	}

	public void setSlice(IDataset slice) {
		this.slice = slice;
	}
	
}
