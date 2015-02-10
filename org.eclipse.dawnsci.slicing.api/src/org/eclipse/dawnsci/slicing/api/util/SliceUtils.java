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
package org.eclipse.dawnsci.slicing.api.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.IDatasetMathsService;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.io.SliceObject;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.doe.DOEUtils;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.dawnsci.plotting.api.IPlottingSystem;
import org.eclipse.dawnsci.plotting.api.expressions.IVariableManager;
import org.eclipse.dawnsci.plotting.api.trace.IImageTrace;
import org.eclipse.dawnsci.plotting.api.trace.ITrace;
import org.eclipse.dawnsci.slicing.api.Activator;
import org.eclipse.dawnsci.slicing.api.system.AxisType;
import org.eclipse.dawnsci.slicing.api.system.DimsData;
import org.eclipse.dawnsci.slicing.api.system.DimsDataList;
import org.eclipse.dawnsci.slicing.api.system.ISliceRangeSubstituter;
import org.eclipse.dawnsci.slicing.api.system.SliceSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SliceUtils {

    private static Logger logger = LoggerFactory.getLogger(SliceUtils.class);

	private static NumberFormat format = DecimalFormat.getNumberInstance();
    /**
     * Generates a list of slice information for a given set of dimensional data.
     * 
     * The data may have fields which use DOE annotations and hence can be expanded.
     * This allows slices to be ranges in one or more dimensions which is a simple
     * form of summing sub-sets of data.
     * 
     * @param dimsDataHolder
     * @param dataShape
     * @param sliceObject
     * @return a list of slices
     * @throws Exception 
     */
    public static SliceObject createSliceObject(final DimsDataList dimsDataHolder,
    		                                    final SliceSource  data,
     		                                    final SliceObject  sliceObject) throws Exception  {

        final int[]        dataShape = data.getLazySet().getShape();

    	if (dimsDataHolder.size()!=dataShape.length) throw new RuntimeException("The dims data and the data shape are not equal!");
    	
    	final SliceObject currentSlice = sliceObject!=null ? sliceObject.clone() : new SliceObject();

    	// This ugly code results from the ugly API to the slicing.
    	final int[] start  = new int[dimsDataHolder.size()];
    	final int[] stop   = new int[dimsDataHolder.size()];
    	final int[] step   = new int[dimsDataHolder.size()];
    	IDataset x  = null;
    	IDataset y  = null;
    	final StringBuilder buf = new StringBuilder();

     	//buf.append("\n"); // New graphing can deal with long titles.
    	for (int i = 0; i < dimsDataHolder.size(); i++) {

    		final DimsData dimsData = dimsDataHolder.getDimsData(i);
    		
    		start[i] = getStart(dimsData);
    		stop[i]  = getStop(dimsData,dataShape[i]);
    		step[i]  = getStep(dimsData);

    		if (dimsData.getPlotAxis().hasValue()) {
    			String nexusAxisName = getAxisName(currentSlice, dimsData, " (Dim "+(dimsData.getDimension()+1)); 
    			final int sliceIndex = dimsData.getSlice();
    			if (sliceIndex>-1) {
    				String formatValue   = String.valueOf(sliceIndex);
        			try {
        			    formatValue = format.format(getAxisValue(sliceObject, data.getVariableManager(), dimsData, dimsData.getSlice(), null));
        			} catch (Throwable ne) {
        				formatValue   = String.valueOf(dimsData.getSlice());
        			}
        			buf.append("("+nexusAxisName+" = "+(dimsData.getSliceRange()!=null?dimsData.getSliceRange():formatValue)+")");
    			}
    		}

    		final IDatasetMathsService service = (IDatasetMathsService)Activator.getService(IDatasetMathsService.class);
    		if (dimsData.getPlotAxis()==AxisType.X) {
    			x = service.createRange(dataShape[i], IDatasetMathsService.INT);
    			x.setName("Dimension "+(dimsData.getDimension()+1));
    			currentSlice.setX(dimsData.getDimension());
    			currentSlice.setxSize(x.getSize());
    		}
    		if (dimsData.getPlotAxis()==AxisType.Y || dimsData.getPlotAxis()==AxisType.Y_MANY) {
       			y = service.createRange(dataShape[i], IDatasetMathsService.INT);
    			y.setName("Dimension "+(dimsData.getDimension()+1));
    			currentSlice.setY(dimsData.getDimension());
    			final int count = DOEUtils.getSize(dimsData.getSliceRange(true), null);
    			currentSlice.setySize(count>-1?count:y.getSize());
    		}
    		
        	if (dimsData.getSliceRange()!=null&&!dimsData.getPlotAxis().hasValue()) {
        		currentSlice.setRange(true);
        	}

    	}
    	currentSlice.setDimensionalData(dimsDataHolder);

    	if (x==null || x.getSize()<2) { // Nothing to plot
    		throw new Exception("Cannot slice into image, one of the axes is size 1");
     	}
    	
    	if (y!=null) {
    	    currentSlice.setSlicedShape(new int[]{currentSlice.getxSize(),currentSlice.getySize()});
        	currentSlice.setAxes(Arrays.asList(new IDataset[]{x,y}));
    	} else {
    		currentSlice.setSlicedShape(new int[]{currentSlice.getxSize()});
        	currentSlice.setAxes(Arrays.asList(new IDataset[]{x}));
   	    }
    	
    	currentSlice.setSliceStart(start);
    	currentSlice.setSliceStop(stop);
    	currentSlice.setSliceStep(step);
    	currentSlice.setShapeMessage(buf.toString());

    	return currentSlice;
	}
  
    /**
     * 
     * @param sliceObject
     * @param data
     * @return
     * @throws Exception
     */
    public static String getAxisLabel(SliceObject sliceObject, DimsData data) {
    	
    	final String axisName = getAxisName(sliceObject, data, "indices");
    	return getAxisLabel(sliceObject, axisName);
    }
    
    /**
     * 
     * @param sliceObject
     * @param axisName
     * @return
     */
    private static String getAxisLabel(SliceObject sliceObject, String axisName) {

    	final String dataName = sliceObject.getName();
    	String axisLabel = axisName;
    	try {
    	   final String parentName = dataName.substring(0,dataName.lastIndexOf('/'));
    	   if (axisLabel.startsWith(parentName)) return axisLabel.substring(parentName.length()+1);
    	} catch (Throwable ne) {
    		return axisLabel;
    	}
    	return axisLabel;
   }
    /**
     * 
     * @param sliceObject
     * @param data
     * @return
     * @throws Exception
     */
    public static String getAxisName(SliceObject sliceObject, DimsData data, String alternateName) {
    	
    	try {
			Map<Integer,String> dims = sliceObject.getAxisNames();
			String axisName = dims.get(data.getDimension()+1); // The data used for this axis
			if (axisName==null || "".equals(axisName)) return alternateName;
			return axisName;
    	} catch (Throwable ne) {
    		return alternateName;
    	}
    }
    /**
     * 
     * @param sliceObject
     * @param data
     * @param value
     * @param monitor
     * @return the nexus axis value or the index if no nexus axis can be found.
     * @throws Throwable 
     */
	public static Number getAxisValue(SliceObject sliceObject, IVariableManager varMan, DimsData data, int value, IProgressMonitor monitor) throws Throwable {
        IDataset axis = getAxis(sliceObject, varMan, data, monitor);
        
        try {
            return axis!=null ? axis.getDouble(value)  :  value;
        } catch (Throwable ne) {
        	return value;
        }

	}

	/**
	 * 
	 * @param sliceObject
	 * @param varMan
	 * @param data
	 * @param monitor
	 * @return
	 * @throws Throwable
	 */
	public static IDataset getAxis(SliceObject sliceObject,
									IVariableManager varMan, 
									DimsData data, 
									IProgressMonitor monitor) throws Throwable {
		
		IDataset axis = null;
        try {
        	final String axisName = getAxisLabel(sliceObject, data);
        	if (varMan!=null) {
        		ILazyDataset la = varMan.getDataValue(axisName, null);
        		if (la != null)
        			axis = la instanceof IDataset ? (IDataset)la : la.getSlice();
        	}
        	if (axis==null) {
                axis = SliceUtils.getAxis(sliceObject, varMan, axisName, false, monitor);
        	}
        } catch (Exception ne) {
        	axis = null;
        }
        return axis;
	}

	private static int getStart(DimsData dimsData) {
		if (dimsData.isTextRange()) {
			final double[] exp = DOEUtils.getRange(dimsData.getSliceRange(true), null);
			return exp!=null ? (int)exp[0] : 0;
			
		} else if (!dimsData.getPlotAxis().hasValue()) {
			return 0;
		} 
		return dimsData.getSlice();
	}
	
	private static int getStop(DimsData dimsData, final int size) {
		if (dimsData.isTextRange()) {
			final double[] exp = DOEUtils.getRange(dimsData.getSliceRange(true), null);
			return exp!=null ? (int)exp[1] : size;
		} else if (!dimsData.getPlotAxis().hasValue()) {
			return size;
			
		}  else if (dimsData.getPlotAxis().isAdvanced()) {
			int ispan = dimsData.getSlice()+dimsData.getSliceSpan();
			if (ispan>size) ispan=size;
			return ispan;
		}
		return dimsData.getSlice()+1;
	}

	private static int getStep(DimsData dimsData) {
		if (dimsData.isTextRange()) {
			final double[] exp = DOEUtils.getRange(dimsData.getSliceRange(true), null);
			return exp!=null ? (int)exp[2] : 1; 
		} else if (!dimsData.getPlotAxis().hasValue()) {
			return 1;
			
		}
		return 1;
	}


	/**
	 * this method gives access to the image trace plotted in the
	 * main plotter or null if one is not plotted.
	 * @return
	 */
	public static IImageTrace getImageTrace(IPlottingSystem plotting) {
		if (plotting == null) return null;

		final Collection<ITrace> traces = plotting.getTraces(IImageTrace.class);
		if (traces==null || traces.size()==0) return null;
		final ITrace trace = traces.iterator().next();
		return trace instanceof IImageTrace ? (IImageTrace)trace : null;
	}


	/**
	 * 
	 * @param currentSlice
	 * @param length of axis
	 * @param iAxis  dimension (starting with 1 as in nexus)
	 * @param requireIndicesOnError
	 * @param monitor
	 * @return
	 * @throws Exception
	 */
	public static IDataset getAxis(final SliceObject      currentSlice, 
			                        final IVariableManager varMan, 
			                        int                    length, 
			                        int                    iAxis, 
			                        boolean                requireIndicesOnError, 
			                        final IProgressMonitor monitor) throws Exception {
		
		
		String axisName = currentSlice.getAxisName(iAxis);
		final IDatasetMathsService service = (IDatasetMathsService)Activator.getService(IDatasetMathsService.class);
		if ("indices".equals(axisName) || axisName==null) {
			if (requireIndicesOnError) {
				IDataset indices = service.createRange(length, IDatasetMathsService.INT); // Save time
				indices.setName("");
				return indices;
			} else {
				return null;
			}
		}
		
		if (axisName.endsWith("[Expression]")) {
			final IDataset set = currentSlice.getExpressionAxis(axisName);
			return service.convertToDataset(set);
		}
		
		try {
			IDataset x = getAxis(currentSlice, varMan, axisName, true, monitor);
			
			// Hack to make EDXD file work in DAWN 1.7.1
			// TODO FIXME Axes should come from metadata eventually, although
			// since expressions may be axes, there will still need to be some
			// manipulation. 
			// Example file is 40788
			try {
				if (x!=null&& x.getRank()>1) {
					final int[] dataShape = currentSlice.getFullShape();
				    if (dataShape!=null && x.getShape()[0]==dataShape[currentSlice.getX()-1]) {
						int dim  = currentSlice.getX()-1;
						int from = currentSlice.getSliceStart()[dim];
						int to   = currentSlice.getSliceStop()[dim];
						x = x.getSliceView(new Slice(from, to), null);
						x = x.squeeze();
						x.setName(axisName);
				    }
				}
			} catch (Exception ignored) {
				// This is a late on fix, if we cannot get the axes, we set no axis.
				x = service.createRange(length, IDatasetMathsService.INT); // Save time
				x.setName("Indices");
			}
			if (x.getRank()==2) {
				x = ((Dataset)x).mean(0).squeeze();
				x.setName("Average '"+axisName+"'");
			}

			if (x.getRank()!=1) {
				x = service.createRange(length, IDatasetMathsService.INT); // Save time
				x.setName("Indices");
			}
            return x;
			
		} catch (Throwable ne) {
			logger.error("Cannot get nexus axis during slice!", ne);
			if (requireIndicesOnError) {
				IDataset indices = service.createRange(length, IDatasetMathsService.INT); // Save time
				indices.setName("");
				return indices;

			} else {
				return null;
			}
		}
	}
	
	/**
	 * 
	 * @param currentSlice
	 * @param axisName, full path and then optionally a : and the dimension which the axis is for.
	 * @param requireUnit - if true will get unit but will be slower.
	 * @param requireIndicesOnError
	 * @param monitor
	 * @return
	 */
	public static IDataset getAxis(final SliceObject currentSlice, 
			                       final IVariableManager varMan,
										 String      origName, 
								   final boolean     requireUnit,
								   final IProgressMonitor  monitor) throws Throwable {
		
		int dimension = -1;
		String axisName = origName;
		if (axisName.contains(":")) {
			final String[] sa = axisName.split(":");
			axisName  = sa[0];
			dimension = Integer.parseInt(sa[1])-1;
		}
		
    	if (varMan!=null && varMan.isDataName(axisName, null)) {
    		ILazyDataset la = varMan.getDataValue(axisName, null);
    		IDataset da;
    		if (dimension<0) {
    			da = la instanceof IDataset ? (IDataset)la : la.getSlice();
    		} else {
    			da = sliceDimension(la, dimension);
    		}
            da.setName(getAxisLabel(currentSlice, origName));
            return da;
    	}

		IDataset axis = null;
		final String dataPath = currentSlice.getName();
		if (dataPath.endsWith("[Expression]")) {
			final IDataset set = currentSlice.getExpressionAxis(dataPath);
			final IDatasetMathsService service = (IDatasetMathsService)Activator.getService(IDatasetMathsService.class);
			return service.convertToDataset(set);
		}
		
		if (requireUnit) { // Slower
			IHierarchicalDataFile file = null;
			try {
				file = HierarchicalDataFactory.getReader(currentSlice.getPath());
				final String  group    = file.getParent(currentSlice.getName());
				
				final String fullName = group+"/"+axisName;
				
				final ILoaderService service = (ILoaderService)Activator.getService(ILoaderService.class);
				axis = service.getDataset(currentSlice.getPath(), fullName, new ProgressMonitorWrapper(monitor));
				if (axis == null) return null;
				axis = axis.squeeze();
				
				final String unit = file.getAttributeValue(fullName+"@unit");
				if (unit!=null) origName = origName+" "+unit;
			    
			} finally {
				if (file!=null) file.close();
			}
		} else { // Faster
			final File file = new File(dataPath);
			final String parent = file.getParent();
			final String fullName = parent == null ? axisName : parent.replace('\\','/')+"/"+axisName;
			final ILoaderService service = (ILoaderService)Activator.getService(ILoaderService.class);
			axis = service.getDataset(currentSlice.getPath(), fullName, new ProgressMonitorWrapper(monitor));
			if (axis == null) return null;
			axis = axis.squeeze();
		
		}

		// TODO Should really be averaging not using first index.
		if (dimension>-1) {
			axis = sliceDimension(axis, dimension);
		}
		
		axis.setName(getAxisLabel(currentSlice, origName));
	    return axis;

	}


	private static IDataset sliceDimension(ILazyDataset axis, int dimension) {
		
		final int[] shape = axis.getShape();
		final int[] start = new int[shape.length];
		final int[] stop  = new int[shape.length];
		final int[] step  = new int[shape.length];
		for (int i = 0; i < shape.length; i++) {
			start[i] = 0;
			step[i]  = 1;
			if (i==dimension) {
				stop[i] = shape[i];
			} else {
				stop[i] = 1;
			}
		}
		IDataset iaxis = axis.getSlice(start, stop, step);
		if (iaxis == null) return null;
		iaxis = iaxis.squeeze();
		return iaxis;
	}

	/**
	 * Get a slice from a ILazyDataset using a SliceObject which may contain
	 * information about slicing and operations to apply on the slice.
	 * 
	 * @param ld
	 * @param currentSlice
	 * @param monitor
	 * @return
	 * @throws Exception
	 */
	public static IDataset getSlice(final ILazyDataset      ld,
									final SliceObject       currentSlice,
									final IProgressMonitor  monitor) throws Exception {
		
		final int[] dataShape = currentSlice.getFullShape();
		
		if (monitor!=null&&monitor.isCanceled()) return null;

		// This is the bit that takes the time. 
		// *DO NOT CANCEL MONITOR* if we get this far
		IDataset slice = (IDataset)ld.getSlice(currentSlice.getSliceStart(), currentSlice.getSliceStop(), currentSlice.getSliceStep());
		slice.setName("Slice of "+currentSlice.getName()+" "+currentSlice.getShapeMessage());
		
		final DimsDataList ddl = (DimsDataList)currentSlice.getDimensionalData();
		
		final IDatasetMathsService service = (IDatasetMathsService)Activator.getService(IDatasetMathsService.class);
		if (currentSlice.isRange()) {
			// We sum the data in the dimensions that are not axes
			IDataset sum    = slice;
			final int       len    = dataShape.length;
			for (int i = len-1; i >= 0; i--) {
				if (!currentSlice.isAxis(i) && dataShape[i]>1) sum = service.sum(sum, i);
			}

			if (currentSlice.getX() > currentSlice.getY()) sum = service.transpose(sum);
			
			sum = sum.squeeze();
			sum.setName(slice.getName());
			slice = sum;
			
		} else {
			if (ddl!=null && !ddl.isEmpty()) {	
				final int       len    = dataShape.length;
				String title = slice.getName();
				
				int[] ss = currentSlice.getSlicedShape();
				if (ss.length<slice.getRank()) {
					for (int i = len-1; i >= 0; i--) {
						final DimsData dd = ddl.getDimsData(i);
						if (dd.getPlotAxis().isAdvanced()) {
							slice = dd.getPlotAxis().process(slice,i);
							try {
							    title = dd.getPlotAxis().getName()+" of "+currentSlice.getName()+" range "+getRange(ld, currentSlice, dd);
						
							} catch (Throwable ne) {
								logger.error("Cannot get title for operation "+dd.getPlotAxis(), ne);
								title = slice.getName();
							}
						}
					}
					slice.setName(title);
				}
			} 
			
			final String name = slice.getName();
			slice = slice.squeeze();		
			if (currentSlice.getX() > currentSlice.getY() && slice.getShape().length==2) {
				// transpose clobbers name
				slice = service.transpose(slice);
				if (name!=null) slice.setName(name);
			}
		}
		
		return slice;
	}

	private static String getRange(ILazyDataset ld, 
									SliceObject  currentSlice,
									DimsData     data) {
		
		String from = getFormatValue(ld, currentSlice, data, data.getSlice());
		String to   = getFormatValue(ld, currentSlice, data, data.getSlice()+data.getSliceSpan());
		return from+":"+to;
	}

	private static String getFormatValue(ILazyDataset      ld, 
			                             SliceObject       currentSlice,
			                             DimsData          data, 
			                             int               index) {
		
		int max = ld.getShape()[data.getDimension()];
		if (index>=max) index = max-1;
		String formatValue = String.valueOf(index);
		try {
			Number value = SliceUtils.getAxisValue(currentSlice, null, data, index, null);
			formatValue = format.format(value);
		} catch (Throwable ne) {
			formatValue = String.valueOf(index);
		}
		return formatValue;
	}


	/**
     * Transforms a SliceComponent defined slice into an expanded set
     * of slice objects so that the data can be sliced out of the h5 file.
     * 
     * @param fullShape
     * @param dimsDataList
     * @return
     * @throws Exception 
     */
	public static List<SliceObject> getExpandedSlices(final SliceSource data,
			                                          final DimsDataList ddl) throws Exception {	

		return getExpandedSlices(data, ddl, null);
	}

    /**
     * Transforms a SliceComponent defined slice into an expanded set
     * of slice objects so that the data can be sliced out of the h5 file.
     * 
     * @param fullShape
     * @param dimsDataList
     * @return
     * @throws Exception 
     */
	public static List<SliceObject> getExpandedSlices(final SliceSource data,
			                                          final DimsDataList ddl,
			                                          final ISliceRangeSubstituter substituter) throws Exception {	

		final List<SliceObject> obs = new ArrayList<SliceObject>(89);
		createExpandedSlices(data, ddl, 0, new ArrayList<DimsData>(ddl.size()), obs, substituter);
		return obs;
	}

	private static void createExpandedSlices(final SliceSource       data,
			                                 final DimsDataList      ddl,
			                                 final int               index,
			                                 final List<DimsData>    chunk,
			                                 final List<SliceObject> obs,
			                                 final ISliceRangeSubstituter substituter) throws Exception {
		
		final int[]    fullShape = data.getLazySet().getShape();
		final DimsData       dat = ddl.getDimsData(index);
		final List<DimsData> exp = dat.expand(fullShape[index], substituter);
		
		for (DimsData d : exp) {
			
			chunk.add(d);
			if (index==ddl.size()-1) { // Reached end
				SliceObject ob = new SliceObject();
				ob.setFullShape(fullShape);
				ob = SliceUtils.createSliceObject(new DimsDataList(chunk), data, ob);
				obs.add(ob);
				chunk.clear();
			} else {
				createExpandedSlices(data, ddl, index+1, chunk, obs, substituter);
			}
			
		}
	}
	
	/**
	 * Deals with loaders which provide data names of size > 1
	 * 
	 * 
	 * @param meta
	 * @return
	 */
	public static final List<String> getSlicableNames(IDataHolder holder) {

		return getSlicableNames(holder, 2, String.class);
	}
	

	/**
	 * Deals with loaders which provide data names of size 1
	 * 
	 * 
	 * @param meta
	 * @param minSize - min size of any one dimension
	 * @param the list of dTypes which are not slicable data or now required in the list of names.
	 * @return
	 */
	public static final List<String> getSlicableNames(IDataHolder holder, int minSize, Class<?>... elementClasses) {
				
		if (minSize<=0) minSize = 2;
		
		Collection<String> names = Arrays.asList(holder.getNames());
		if (names==null||names.isEmpty()) return null;
		
		List<Class<?>> restrictions = new ArrayList<Class<?>>(10);
		if (elementClasses!=null) for (Class<?> clazz : elementClasses) restrictions.add(clazz);
		
		boolean isH5 = isH5(holder.getFilePath());
		
		List<String> ret   = new ArrayList<String>(names.size());
		for (String name : names) {
			
			// Some funny keys are put in data holders with the 
			// 'local_name' attribute in nexus.
			if (isH5 && !name.startsWith("/")) continue;
			
			ILazyDataset ls = holder.getLazyDataset(name);
			if (restrictions.contains(ls.elementClass())) continue;
			int[] shape = ls!=null ? ls.getShape() : null;
			if (shape==null) continue;
			
			boolean foundDims = false;
			if (minSize==1 && shape.length==0) {
				foundDims = true;
			} else {
				for (int i = 0; i < shape.length; i++) {
					if (shape[i]>=minSize) {
						foundDims = true;
						break;
					}
				}
			}
			if (!foundDims) continue;
			ret.add(name);
		}
		return ret;
	}


	public final static List<String> EXT;
	static {
		List<String> tmp = new ArrayList<String>(7);
		tmp.add("h5");
		tmp.add("nxs");
		tmp.add("hd5");
		tmp.add("hdf5");
		tmp.add("hdf");
		tmp.add("nexus");
		EXT = Collections.unmodifiableList(tmp);
	}	

	private static boolean isH5(final String filePath) {
		if (filePath == null) { return false; }
		final String ext = getFileExtension(filePath);
		if (ext == null) { return false; }
		return EXT.contains(ext.toLowerCase());
	}
	private static String getFileExtension(String fileName) {
		int posExt = fileName.lastIndexOf(".");
		// No File Extension
		return posExt == -1 ? "" : fileName.substring(posExt + 1);
	}

}
