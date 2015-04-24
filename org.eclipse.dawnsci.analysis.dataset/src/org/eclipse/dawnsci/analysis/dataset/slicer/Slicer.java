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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.dataset.impl.PositionIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Methods for slicing data using visit patterns.
 * This is Jakes algorithm moved out of the conversion API to make more use of it.
 */
public class Slicer {
	
	private static final Logger logger = LoggerFactory.getLogger(Slicer.class);

	public static IDataset getFirstSlice(ILazyDataset lz, Map<Integer, String> sliceDimensions) throws Exception {
		
		SliceND sampling = getSliceNDFromSliceDimensions(sliceDimensions, lz.getShape());
		int[] axes = getDataDimensions(lz.getShape(), sliceDimensions);
		SliceViewIterator generator = new SliceViewIterator(lz, sampling, axes);
		if (generator.hasNext()) return generator.getCurrentView().getSlice();
		
        return null;
	}
	
	public static SliceViewIterator getSliceViewGenerator(ILazyDataset lz, Map<Integer, String> sliceDimensions){
		SliceND sampling = getSliceNDFromSliceDimensions(sliceDimensions, lz.getShape());
		int[] axes = getDataDimensions(lz.getShape(), sliceDimensions);
		return new SliceViewIterator(lz, sampling, axes);
	}

	/**
	 * This method provides a way to slice over a lazy dataset providing the values
	 * in each dimension for the slice using a visit pattern.
	 * 
	 * @param lz
	 * @param sliceDimensions
	 * @param visitor
	 * @throws Exception 
	 */
	public static void visitAll(ILazyDataset lz, Map<Integer, String> sliceDimensions, SliceVisitor visitor) throws Exception {
		visitAll(lz, sliceDimensions, null, visitor);
	}

	/**
	 * This method provides a way to slice over a lazy dataset providing the values
	 * in each dimension for the slice using a visit pattern.
	 * 
	 * Block until complete.
	 * 
	 * @param lz
	 * @param sliceDimensions
	 * @param nameFragment may be null
	 * @param visitor
	 * @throws Exception 
	 */
	public static void visitAll(ILazyDataset lz, Map<Integer, String> sliceDimensions, String nameFragment, SliceVisitor visitor) throws Exception {
		visit(lz, sliceDimensions, nameFragment, visitor);
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
	
	/**
	 * 
	 * @param lz
	 * @param sliceDimensions
	 * @param nameFragment
	 * @param visitor - if null just returns the first slice
	 * @return null if visitor != null otherwise returns first slice.
	 * @throws Exception
	 */
	private static IDataset visit(ILazyDataset lz, Map<Integer, String> sliceDimensions, String nameFragment, SliceVisitor visitor) throws Exception {
		
		SliceND sampling = getSliceNDFromSliceDimensions(sliceDimensions, lz.getShape());
		int[] axes = getDataDimensions(lz.getShape(), sliceDimensions);
		SliceViewIterator generator = new SliceViewIterator(lz, sampling, axes);
		
		while (generator.hasNext()) {
			long start = System.currentTimeMillis();
			IDataset data = generator.getCurrentView().getSlice();
			
			logger.debug("Data loaded in: " +(System.currentTimeMillis()-start)/1000. + " s");
			
			if (visitor!=null) {
			    visitor.visit(data, generator.getOutputSlice().convertToSlice(), generator.getOutputShape());
			} else {
				return data;
			}
			
			if (visitor.isCancelled()) break;
		}

		return null;
	}
	

	/**
	 * This method provides a way to slice over a lazy dataset providing the values
	 * in each dimension for the slice using a visit pattern. The call on to the 
	 * SliceVisitor is done in a parallel way by delegating the calling of the visit method
	 * to a thread pool.
	 * 
	 * Blocks until complete or timeout of 5s is reached.
     *
	 * @param lz
	 * @param sliceDimensions
	 * @param nameFragment may be null
	 * @param visitor - if used with visitAllParallel, visit should not normally throw
	 * an exception or if it does it will stop the execution but not throw back to the
	 * calling code. Instead an internal RuntimeException is thrown back to the fork/join API.
	 * @throws Exception 
	 */
	public static void visitAllParallel(ILazyDataset lz, Map<Integer, String> sliceDimensions, String nameFragment, final SliceVisitor visitor) throws Exception {
        visitAllParallel(lz, sliceDimensions, nameFragment, visitor, 5000);
	}

	/**
	 * This method provides a way to slice over a lazy dataset providing the values
	 * in each dimension for the slice using a visit pattern. The call on to the 
	 * SliceVisitor is done in a parallel way by delegating the calling of the visit method
	 * to a thread pool.
	 * 
	 * Blocks until complete or timeout is reached.
	 * 
	 * @param lz
	 * @param sliceDimensions
	 * @param nameFragment may be null
	 * @param visitor - if used with visitAllParallel, visit should not normally throw
	 * an exception or if it does it will stop the execution but not throw back to the
	 * calling code. Instead an internal RuntimeException is thrown back to the fork/join API.
	 * @param timeout in ms.
	 * @throws Exception 
	 */
	public static void visitAllParallel(ILazyDataset lz, Map<Integer, String> sliceDimensions, String nameFragment, final SliceVisitor visitor, long timeout) throws Exception {

		// Just farm out each slice to a different runnable.
		final ForkJoinPool pool = new ForkJoinPool();
		
		final SliceVisitor parallel = new SliceVisitor() {

			@Override
			public void visit(final IDataset slice, final Slice[] slices, final int[] shape) throws Exception {
				
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
						    visitor.visit(slice, slices, shape);
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
		
		Slicer.visitAll(lz, sliceDimensions, nameFragment, parallel);
		
		pool.shutdown();
		
		boolean allDone = pool.awaitTermination(timeout, TimeUnit.MILLISECONDS);
		if (!allDone) throw new TimeoutException("The timeout of "+timeout+" was exceeded for parallel run, please increase it!");
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
