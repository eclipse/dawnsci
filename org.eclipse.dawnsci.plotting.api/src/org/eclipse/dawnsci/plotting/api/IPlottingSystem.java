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

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.annotation.IAnnotationSystem;
import org.eclipse.dawnsci.plotting.api.axis.IAxisSystem;
import org.eclipse.dawnsci.plotting.api.region.IRegionSystem;
import org.eclipse.dawnsci.plotting.api.trace.ColorOption;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.plotting.api.trace.ITraceSystem;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;


/**
 * Represents a bridge to the plotting system.
 * 
 * To get your plotting system use the class PlottingFactory. This
 * will return the user preferred plotting.
 * 
 * To use the plotting system follow this design (similar to RCP parts in 3.x):
 * <code>
 * IPlottingSystem system = PlottingFactory.createPlottingSystem(); // reads user preference if there are alternatives.
 * 
 * In UI code
 * system.createPlotPart(...); // Note that the title here is the key used to retrieve the plotter in future.
 * 
 * Create some 1D plots in one go
 * system.createPlot1D(...); // Does not have to be in the UI thread.
 * 
 * Configure a plot in detail, does have to be in UI thread
 * ILineTrace trace = system.createLineTrace(...);
 * trace.setTraceColor(...)
 * trace.setLineWidth(...)
 * trace.setXXX(...)
 * 
 * system.addTrace(trace);
 * 
 * This is true for any trace, the paradigm is 'create, modify, add' in the trace lifecycle.
 * 
 * 
 * The plotting system uses listeners extensively to notify of the user doing things. It is likely that the
 * events in future will increase as more features become available.
 * 
 * At the end:
 * system.dispose(); // This should be called to clean up UI. Do not forget to remove listeners in the 
 *                   // dispose as well.
 *                   
 * </code>
 * 
 * @author gerring
 *
 */
public interface IPlottingSystem<T> extends IAdaptable, ITraceSystem, IRegionSystem, IAxisSystem, IAnnotationSystem, IPrintablePlotting{

	public final static String RMI_PREFIX = "PlottingSystem:"; // Hard coded into jyplottingsystem.py as well.
	public final static String RESCALE_ID = "org.dawb.common.ui.plot.rescale";
	
	public static final int NORMAL_CURSOR = 0;
	public static final int CROSS_CURSOR  = 1;
	public static final int WAIT_CURSOR   = 2;

	/**
	 * Get the current title being used for the plot.
	 * @return
	 */
	public String getTitle();
	
	/**
	 * Call to set the plot title. NOTE The title may be used for keys used to determine
	 * if traces are unique and should be set to a meaningful string, i.e. not "Plot".
	 * @param title
	 */
	public void setTitle(final String title);
	
	/**
	 * Call to set the plot title color. 
	 * 
	 * @param title
	 */
	public void setTitleColor(final Color color);
	
	/**
	 * Set the back ground color of the plot.
	 * 
	 * @param title
	 */
	public void setBackgroundColor(final Color color);

	/**
	 * Call to create the UI component dealing with the plotting.
	 * @param parent
	 * @param plotName
	 * @param bars - may be null
	 * @param hint
	 * @param part - may be null
	 */
	public void createPlotPart(T              parent,
			                   String         plotName,
			                   IActionBars    bars,
			                   PlotType       hint,
			                   IWorkbenchPart part);
	

	/**
	 * The plot name corresponding to the name used in the PlottingFactory.
	 * For file plots in editors this will be the file name and for views such
	 * as 'Plot 1' etc it will be the view name (Plot 1).
	 * @return
	 */
	public String getPlotName();
	
	/**
	 * This method is Thread safe - so no need to call from UI thread!
	 * 
	 * See also ITraceSystem for flexible trace manipulation.
	 * 
	 * For 1D - x is the x axis, ys is the y traces. X may be null when plotting with indices.
	 * 
	 * This call will also plot in 3D if the plotting mode is setting to PlotType.XY_STACKED_3D first.
	 * A 3D plot will be produced staggering the 1D data in Z.
	 * 
	 * NOTE: The call createPlot1D(y, null, monitor) is no longer allowed. It should be:
	 * createPlot1D(null, Arrays.asList(y), monitor).
	 * 
	 * NOTE: Using this option plots everything on the current x and y axes. These are the default axes,
	 * to change axes, use createAxis(...) then setActiveAxis(...). Then subsequent plotting will plot
	 * to these active axes.
	 * 
	 * Does not have to be called in the UI thread - any thread will do. Should be called to switch the entire
	 * plot contents.
	 * 
	 * There is append for 1D plotting used for single points. @see IPlottingSystem.append(...)
	 * 
	 * Each call to createPlot1D(...) adds to the plot and the current selected axes, use reset() to clear the plot.
	 * 
	 * If a IDataset passed into the ys array is an instance of IErrorDataset and the getError() is non-null, error
	 * bars will be switched on automatically.
	 * 
	 * @param x - may be null
	 * @param ys - may not be null or empty
	 * @param mode
	 * @param monitor
	 * @return List of ITrace objects plotted, may be null. Normally you can cast these to ILineTrace as all 1D 
	 *         plotting systems will wholly or partially support ILineTrace. If plotting mode is XY_STACKED_3D then 
	 *         will return a list of size 1 with the trace of type IStackTrace.
	 */
	public List<ITrace> createPlot1D(IDataset                 x, 
							         List<? extends IDataset> ys,
							         IProgressMonitor         monitor);

	/**
	 * @see IPlottingSystem.createPlot1D(x, ys, monitor);
	 * 
	 * This method is Thread safe - so no need to call from UI thread!
	 * @see createPlot1D(IDataset, List<IDataset>, IProgressMonitor)
	 * @param x
	 * @param ys
	 * @param title - specifies the title instead of creating one.
	 * @param monitor
	 * @return
	 */
	public List<ITrace> createPlot1D(IDataset              x, 
							         List<? extends IDataset> ys,
							         String                title,
							         IProgressMonitor      monitor);

	/**
	 * @see IPlottingSystem.createPlot1D(x, ys, monitor);
	 * 
	 * This method is Thread safe - so no need to call from UI thread!
	 * @see createPlot1D(IDataset, List<IDataset>, IProgressMonitor)
	 * @param x
	 * @param ys
	 * @param dataNames - If the data plotted has names which will be used in Filters these can be set here.
	 * @param title - specifies the title instead of creating one.
	 * @param monitor
	 * @return
	 */
	public List<ITrace> createPlot1D(IDataset              x, 
							         List<? extends IDataset> ys,
							         List<String>          dataNames,
							         String                title,
							         IProgressMonitor      monitor);

	/**
	 * This method is Thread safe - so no need to call from UI thread!
	 * 
	 * Attempts to update any ILineTraces with the same name as the ys pass in, otherwise
	 * will call createPlot1D(...)
	 * 
	 * NOTE This will not update the title at the moment if the traces exist.
	 * 
	 * 	
	 * If a IDataset passed into the ys array is an instance of IErrorDataset and the getError() is non-null, error
	 * bars will be switched on automatically [UNLESS the trace exists and  setErrorBarEnabled(false) has been called
	 * on the ILineTrace already].
     *
	 * @param x  - may be null, if null indices of y are used
	 * @param ys -  must not be null
	 * @param monitor
	 * @return
	 */
	public List<ITrace> updatePlot1D(IDataset                 x, 
							         List<? extends IDataset> ys,
							         IProgressMonitor         monitor);

	/**
	 * @see IPlottingSystem.updatePlot1D(x,ys,monitor);
     *
	 * @param x  - may be null, if null indices of y are used
	 * @param ys -  must not be null
	 * @param dataNames - If the data plotted has names which will be used in Filters these can be set here.
	 * @param monitor
	 * @return
	 */
	public List<ITrace> updatePlot1D(IDataset                 x, 
							         List<? extends IDataset> ys,
							         List<String>             dataNames,
							         IProgressMonitor         monitor);

	/**
	 * @see IPlottingSystem.updatePlot1D(x,ys,monitor);
     *
	 * @param x  - may be null, if null indices of y are used
	 * @param ys -  must not be null
	 * @param plotTitle - The name to use as the title of the plot, providing something new is plotted, otherwise leaves title unchanged.
	 * @param monitor
	 * @return
	 */
	public List<ITrace> updatePlot1D(IDataset                 x, 
							         List<? extends IDataset> ys,
							         final String             plotTitle,
							         IProgressMonitor         monitor);

	/**
	 * This method is Thread safe - so no need to call from UI thread!
	 * 
	 * See also ITraceSystem for flexible trace manipulation.
     *
	 * For 2D - x is the image dataset, ys is the axes. It will also plot in 3D
	 * if the plotting mode is setting to PlotType.SURFACE first.
	 * 
	 * Does not have to be called in the UI thread. Should be called to switch the entire
	 * plot contents. 
	 * 
	 * @param image
	 * @param axes - may be null for pixels only. They must be data sets of the same size
	 *               as the image. The first dataset is the x-axis in standard orientation,
	 *               the second is the y-axis.
	 * @param mode
	 * @param monitor
	 * @return Image trace plotted. You can normally cast this trace to an IImageTrace for
	 *         PlotType.IMAGE and and ISurfaceTrace for PlotType.SURFACE. You can
	 *         use any image methods offered by these interface.
	 */
	public ITrace createPlot2D(IDataset                 image, 
							   List<? extends IDataset> axes,
							   IProgressMonitor         monitor);
	
	/**
	 * @see IPlottingSystem.createPlot2D(image, axes, monitor);
	 * 
	 * @param image
	 * @param axes
	 * @param dataName - If the data plotted has a name which will be used in Filters this can be set here.
	 * @param monitor
	 * @return
	 */
	public ITrace createPlot2D(IDataset                 image, 
							   List<? extends IDataset> axes,
							   String                   dataName,
							   IProgressMonitor         monitor);

	/**
	 * This method is Thread safe - so no need to call from UI thread!
	 * 
	 * This method is similar to createPlot2D(...) however calling this method swaps the image data for
	 * a plot - keeping zoom level intact. It can be used for a live update of an image plot for instance.
	 * If there is no image to update, createPlot2D(...) will be called instead automatically.
	 * 
	 * @param image
	 * @param axes
	 * @param monitor
	 * @return
	 */
	public ITrace updatePlot2D(IDataset                 image, 
							   List<? extends IDataset> axes,
							   IProgressMonitor         monitor);
	
	/**
	 * 	@see IPlottingSystem.updatePlot2D(image, axes, monitor);

	 * @param image
	 * @param axes
	 * @param dataName - If the data plotted has a name which will be used in Filters this can be set here.
	 * @param monitor
	 * @return
	 */
	public ITrace updatePlot2D(IDataset                 image, 
							   List<? extends IDataset> axes,
							   String                   dataName,
							   IProgressMonitor         monitor);

	/**
	 * Set the plot type. For instance if requiring a 3D surface plot of an image
	 * Call setPlotType(PlotType.SURFACE) followed by createPlot2D(...)
	 * 
	 * Do not call before createPlotPart(...)
	 * 
	 * THREAD SAFE
	 * 
	 * @param plotType
	 */
	public void setPlotType(PlotType plotType);

	/**
	 * This method can be used to add a single plot data point to 
	 * an individual 1D plot already created in createPlot(...). The dataSetName
	 * argument is the same as the name of the original data set plotted,
	 * the data is added to this plot efficiently.
	 * 
	 * Example of starting a plot with nothing and then adding points:
	 * 
	 * final Dataset y = new DoubleDataset(new double[]{}, 0}
	 * y.setName("y")
	 * 
	 * plottingSystem.createPlot(y, null, PlotType.PT1D, mon);
	 * 
	 * ...Update, x value indices in this case
	 * plottingSystem.append("y", y.getSize()+1, 10, mon);
	 * 
	 * Call this update method in any thread, it will do the update in the UI thread
	 * if not already called in the UI thread.
	 * 
	 * @param dataSetName
	 * @param xValue - may be null if using indices
	 * @param yValue
	 * @param monitor - often null, this will be a fast operation.
	 * @throws Exception
	 */
	public void append(final String           dataSetName, 
			           final Number           xValue,
					   final Number           yValue,
					   final IProgressMonitor monitor) throws Exception ;

	/**
	 * Call to tell the plot to plot nothing and reset axes. Thread safe.
	 */
	public void reset();

	/**
	 * Call to tell the plot to reset axes. Thread safe.
	 */
	public void resetAxes();

	/**
	 * Call to tell the plot to remove data and leave axes unchanged. Thread safe.
	 */
	public void clear();

	/**
	 * Call to mark widgets and plotting as no longer required.
	 * 
	 * This will be called when the part is disposed.
	 */
	public void dispose();


	/**
	 * Redraws all the data. Calls repaint(isRescale())
	 * Thread safe.
	 */
	public void repaint();
	
	/**
	 * Repaint with rescale of autoScale is true.
	 * Thread safe.
	 * 
	 * @param autoScale
	 */
	public void repaint(final boolean autoScale);

	
    /**
     * The plot composite which plots are being drawn on.
     * 
     * Use this method with caution
     * 
     * @return
     */
	public T getPlotComposite();
		
	/**
	 * This ISelectionProvider will provide StructuredSelections which have been
	 * made in the graph. It may be registered as a selection provider for the part
	 * using this IPlottingSystem. The StructuredSelection will contain objects such
	 * as 'LinerROI' for the selection inside the graph.
	 * 
	 * @return
	 */
	public ISelectionProvider getSelectionProvider();
	
	/**
	 * Will return the current plot type 1D, 2D etc.
	 */
	public PlotType getPlotType();
	/**
	 * 
	 * @return true if some or all of the plotted data is 2D or images.
	 */
	public boolean is2D();
	
	/**
	 * @return the action bars containing the graph actions.
	 */
	public IActionBars getActionBars();

    /**
     * Gives access to the action manager for removing and filling different actions.
     * @return
     */
	public IPlotActionSystem getPlotActionSystem();
	
	/**
	 * The cursor type used normally when no zoom or other cursor is active.
	 * NORMAL is the arrow, CROSS is a cross
	 */
	public void setDefaultCursor(int cursorType);

	/**
	 * Set the plotting System aspect ratio on/off
	 * @param b
	 */
	public void setKeepAspect(boolean b);
	
	/**
	 * Can be used to grey out plotting component during updates.
	 * Thread safe, can be called from any thread.
	 * @param enabled
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * returns if the plotting system is 
	 * @return
	 */
	public boolean isEnabled();

	/**
	 * 
	 * @return true if intensity is shown for images.
	 */
	public boolean isShowIntensity();
	
	/**
	 * Shows or not the intensity scale
	 * The intensity scale is displayed (set to True) every time there is an image update
	 * If you want to hide it (set to False), make sure this method is called after each update.
	 * @param b
	 */
	public void setShowIntensity(boolean b);
	
	/**
	 * 
	 * @return true if values will be shown when the user zooms in far enough
	 */
	public boolean isShowValueLabels();
	
	/**
	 * Shows or not the values on high zoom
	 * @param b
	 */
	public void setShowValueLabels(boolean b);


	/**
	 * Sets the legends setting on/off
	 * @param b
	 */
	public void setShowLegend(boolean b);

	/**
	 * Returns whether the IPlottingSystem is disposed
	 * @return
	 */
	public boolean isDisposed();

	/**
	 * Sets the Colour option
	 * @param colorOption
	 */
	public void setColorOption(ColorOption colorOption);

	/**
	 * Returns whether the plot should rescale when replotted.
	 * @return rescale
	 */
	public boolean isRescale();

	/**
	 * Sets whether the the plot should rescale when replotted
	 * @param rescale
	 */
	public void setRescale(boolean rescale);
	
	/**
	 * May be called to ensure the widget doing the plotting has focus.
	 */
	public void setFocus();
	
	/**
	 * The underlying IWorkbenchPart, if any
	 * @return IWorkbenchPart, may be null
	 */
	public IWorkbenchPart getPart();

	
	/**
	 * Expert use only: This method replaces the plotting system control, for
	 * instance the light weight plot viewer with an alternative widget. It
	 * returns the widget that was replaced in the plotting system. For instance
	 * the light weight canvas if in light weight mode. This can then be called
	 * using setControl(original) when the alternative UI for editing the plot
	 * is finished with.
	 * 
	 * An example of doing this is replacing the plot with a custom slicing part 
	 * like the Hyper3D slicing tool.
	 * 
	 * @param alternative
	 * @return
	 */
	public Control setControl(Control alternative, boolean showPlotToolbar);

}
