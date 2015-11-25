/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.slicer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyDynamicDataset;

/**
 * Methods for slicing data using visit patterns.
 * This is Jakes algorithm moved out of the conversion API to make more use of it.
 */
public class Slicer {
	
	public static void visit(ISliceViewIterator iterator, SliceVisitor visitor) throws Exception {
		
		while (iterator.hasNext()) {

			IDataset data = iterator.next().getSlice();
			visitor.visit(data);

			if (visitor.isCancelled()) break;
		}
		
	}

	public static void visitParallel(ISliceViewIterator iterator, final SliceVisitor visitor) throws Exception {

		//Can't just farm out each slice to a separate thread, need to block when thread pool full,
		//other wise there is the potential run out of memory from loading all the data before any is processed
		
		//use one less thread than processors, as we are using one for the rejectedhandler
		int nProcessors = Math.max(Runtime.getRuntime().availableProcessors()-1,1);
		BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(nProcessors);
	    RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
	    final ExecutorService pool =  new ThreadPoolExecutor(nProcessors, nProcessors, 
	        0L, TimeUnit.MILLISECONDS, blockingQueue, rejectedExecutionHandler);
	    
		final SliceVisitor parallel = new SliceVisitor() {

			@Override
			public void visit(final IDataset slice) throws Exception {
				
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							
						    visitor.visit(slice);
						    
						    
						} catch (Throwable ne) {
							ne.printStackTrace();
							// TODO Fix me - should runtime exception really be thrown back to Fork/Join?
							//throw new RuntimeException(ne.getMessage(), ne);
						}
					}
				});
			}

			@Override
			public boolean isCancelled() {
				return visitor.isCancelled();
			}
		};
		
		Slicer.visit(iterator, parallel);
		
		pool.shutdown();
		
		while (!pool.awaitTermination(200, TimeUnit.MILLISECONDS)){
			if (visitor.isCancelled()) break;
		}

	}
	
	public static IDataset getFirstSlice(ILazyDataset lz, Map<Integer, String> sliceDimensions) {
		
		SliceND sampling = getSliceNDFromSliceDimensions(sliceDimensions, lz.getShape());
		int[] axes = getDataDimensions(lz.getShape(), sliceDimensions);
		SliceViewIterator generator = new SliceViewIterator(lz, sampling, axes);
		if (generator.hasNext()) return generator.next().getSlice();
		
        return null;
	}
	
	public static IDataset getDynamicFirstSlice(ILazyDataset lz, ILazyDataset key) {
		

		GrowingSliceViewIterator generator = new GrowingSliceViewIterator((LazyDynamicDataset)lz, (LazyDynamicDataset)key);
		if (generator.hasNext()) return generator.next().getSlice();
		
        return null;
	}
	
	public static SliceViewIterator getSliceViewGenerator(ILazyDataset lz, Map<Integer, String> sliceDimensions){
		SliceND sampling = getSliceNDFromSliceDimensions(sliceDimensions, lz.getShape());
		int[] axes = getDataDimensions(lz.getShape(), sliceDimensions);
		return new SliceViewIterator(lz, sampling, axes);
	}
	
	/**
	 * 
	 * @param lz
	 * @param sliceDimensions
	 * @return size of expected iteration, slightly faster than calling <code>getSlices(...).size()</code>
	 */
	public static int getSize(ILazyDataset lz, Map<Integer, String> sliceDimensions) {

		SliceND sampling = getSliceNDFromSliceDimensions(sliceDimensions, lz.getShape());
		int[] axes = getDataDimensions(lz.getShape(), sliceDimensions);
		SliceViewIterator generator = new SliceViewIterator(lz, sampling, axes);
		return generator.getTotal();

	}
	
	public static int[] getDataDimensions(int[] shape, Map<Integer, String> sliceDimensions) {

		//assume single image/line
		if (sliceDimensions == null) {
			int[] dd = new int[shape.length];
			for (int i = 0; i < shape.length; i++) dd[i] = i;
			return dd;
		}
		
		//create array of ignored axes values
		Set<Integer> axesSet = new HashSet<Integer>();
		for (int i = 0; i < shape.length; i++) {
			if (sliceDimensions.containsKey(i)) {
				if (!isAxis(sliceDimensions.get(i))) continue;
			}
			axesSet.add(i);
		}
		
		int[] axes = new int[axesSet.size()];
		int count = 0;
		Iterator<Integer> iter = axesSet.iterator();
		while (iter.hasNext()) axes[count++] = iter.next();

		return axes;
	}
	
	public static Slice[] getSliceArrayFromSliceDimensions(Map<Integer, String> sliceDimensions, int[] shape) {
		
		//Construct Slice String
		StringBuilder sb = new StringBuilder();
		if (sliceDimensions == null) sliceDimensions = new HashMap<Integer, String>();
		
		for (int i = 0; i < shape.length; i++) {
			if (sliceDimensions.containsKey(i)) {
				String s = sliceDimensions.get(i);
				if (isFullDim(s)) s = ":";
				sb.append(s);
				sb.append(",");
			} else {
				sb.append(":,");
			}
		}
		
		sb.deleteCharAt(sb.length()-1);
		return Slice.convertFromString(sb.toString());
		
	}
	
	public static SliceND getSliceNDFromSliceDimensions(Map<Integer, String> sliceDimensions, int[] shape){
		return new SliceND(shape, getSliceArrayFromSliceDimensions(sliceDimensions, shape));
	}

	private static boolean isFullDim(String s) {
		if (s==null)         return false;
		if ("all".equals(s)) return true;
		if (isAxis(s))       return true;
		return false;
	}
	private static boolean isAxis(String s) {
		if (s==null) return false;
		// TODO Other axis strings a problem still...
		if ("X".equals(s)) return true;
		if ("Y".equals(s)) return true;
		if ("Z".equals(s))  return true;
		return false;
	}

}
