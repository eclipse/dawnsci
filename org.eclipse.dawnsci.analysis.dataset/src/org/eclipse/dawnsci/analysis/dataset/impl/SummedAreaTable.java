/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.impl;

import java.io.Serializable;
import java.text.Format;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.dataset.SliceND;
import org.eclipse.dawnsci.analysis.api.metadata.IMetadata;
import org.eclipse.dawnsci.analysis.api.metadata.MetadataType;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;

/**
 * This code is based on:
 * http://en.wikipedia.org/wiki/Summed_area_table
 * 
 * This class can calculate the sum of a box region very fast
 * compared to ROIProfile. Therefore for instance if calculating
 * means of ROIs within a given plot, create a SummedAreaTable and
 * do the calculation through that.
 * 
 * 	I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
 * 
 *  getDouble(...) and
 *  set(Object, ...)
 *  
 *  On Dataset are slow. Therefore we have replaced these with absolute index.
 *
 * @author Matthew Gerring
 */
public class SummedAreaTable implements IDataset {
	
	private int[]         shape; // We cache shape for speed reasons (it is cloned in the dataset on getShape())
	private Dataset       image;
	private DoubleDataset sum, sum2; // Use concrete because use setAbs for speed up.

	/**
	 * Calls SummedAreaTable(Image, false)
	 * @param image
	 * @throws Exception
	 */
	public SummedAreaTable(Dataset image) throws Exception {
		this(image, false);
	}

	/**
	 * Constructs the summed table.
	 * @param image
	 * @param willRequireVariance set to true if you know that you need fano or variance
	 * @throws Exception
	 */
	public SummedAreaTable(Dataset image, boolean willRequireVariance) throws Exception {
		this.image = image;
		this.shape = image.getShape();
		if (image.getRank()!=2) throw new Exception("You may only get sum table of 2D data!");
		createSummedTable(image, willRequireVariance);
	}
	
	/**
	 * We mess about here with creating the sums in one pass for speed reasons.
	 * The test SummedAreaTableTest should check if logic remains correct.
	 * @param image
	 * @param requireSum2
	 * @throws Exception
	 */
	private void createSummedTable(Dataset image, boolean requireSum2) throws Exception {
		
		if (image.getRank()!=2) throw new Exception("You may only compute the summed image table of 2D data!");
	    
		if (sum!=null && sum2!=null)   return;
		if (sum!=null && !requireSum2) return;
		
	    //Create integral
		boolean requireSum = false;
		if (sum == null) {
			sum  = new DoubleDataset(shape);
			requireSum = true;
		}
		
		if (requireSum2 && sum2==null) {
			sum2  = new DoubleDataset(shape);
		}
	    
	    // Create a position iterator
	    final PositionIterator it = new PositionIterator(shape);
	    while(it.hasNext()) {
	    	final int[]  pos   = it.getPos();	    	
	    	final double value = image.getElementDoubleAbs(get1DIndex(pos[0], pos[1], shape));
	        if (requireSum)  fill(value,  sum,  pos, shape);
	        if (requireSum2) fill(Math.pow(value, 2d), sum2, pos, shape);
	    }
	}

	/**
	 * private static final so that compiler will inline it
	 * @param value
	 * @param sum
	 * @param pos
	 * @param shape
	 */
	private static final void fill(double value, DoubleDataset sum, int[] pos, int[] shape) {
    	
		int x = pos[0];
    	int y = pos[1];
    	
        // I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
        //Calculate coefficients
        double sxm  = (x > 0)          ? sum.getElementDoubleAbs(get1DIndex(x-1,y,shape))   : 0;
        double sym  = (y > 0)          ? sum.getElementDoubleAbs(get1DIndex(x,y-1,shape))   : 0;
        double sxym = (x > 0 && y > 0) ? sum.getElementDoubleAbs(get1DIndex(x-1,y-1,shape)) : 0;
        
        double val = value + sxm + sym - sxym;
        sum.setAbs(get1DIndex(x, y, shape), val);  // Fast
 	}

	
	/**
	 * Creates a fano image where each pixel is the fano factor
	 * for a give box surrounding it.
	 * 
	 * This operation has improved speed because it uses the summed area table
	 * to compute a fast mean and variance for a given box.
	 * 
	 * @param box
	 * @return fano factor image using box passed in.
	 * @throws Exception
	 */
	public Dataset getFanoImage(int... box) throws Exception {
		
		if (box[0] % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box[1] % 2 == 0) throw new Exception("Box second dim is not odd!");

		final DoubleDataset fano = new DoubleDataset(shape);
		
		// Compute some things to save FPOs
		int n = box[0]*box[1]; // Save a FPO inside loop.
		
		int r1 = (int)Math.floor(box[0]/2d); // for instance 3->1, 5->2, 7->3 
		int r2 = (int)Math.floor(box[1]/2d); // for instance 3->1, 5->2, 7->3 
        int[] radii = new int[]{r1, r2};


		if (sum2==null) createSummedTable(image, true);

		final PositionIterator it = new PositionIterator(shape);
		while(it.hasNext()) {
			
			// Point from the iterator
			int[] point   = it.getPos();
			
			// Save FPO by calculating coords once per pixel and
			// passing to getBoxVarianceInternal and getBoxMeanInternal
			int [] coords = createCoordsInternal(point, radii);
			
			// These save some floating points
			double vari = getBoxVarianceInternal(coords, n);
			double mean = getBoxMeanInternal(coords, n);
			
			// Use concrete because fast
			fano.setAbs(get1DIndex(point[0], point[1], shape), vari/mean);
		}
		return fano;
	}

	/**
	 * Give a point point, this will return the sum of a box around it.
	 * The box should really be an odd number such that the point is in the center
	 * @param box 
	 * @return the sum of a box around point of shape box
	 * @throws Exception
	 */
	public double getBoxSum(IRectangularROI box) throws Exception {

		if (box.getIntLength(0) % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box.getIntLength(1) % 2 == 0) throw new Exception("Box second dim is not odd!");

		return getBoxSumInternal(sum, createCoords(box), shape);

	}
	
	/**
	 * 
	 * @param box
	 * @return mean from the summed area table
	 */
	public double getBoxMean(IRectangularROI box)  throws Exception {
		if (box.getIntLength(0) % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box.getIntLength(1) % 2 == 0) throw new Exception("Box second dim is not odd!");
		int[] coords = createCoords(box);
		int[] bx     = getBox(coords);
        return getBoxSumInternal(sum, coords, shape) / (bx[0]*bx[1]);
	}

	/**
	 * private static final so that compiler will inline it
	 * @param coords
	 * @return box
	 */
	private static final int[] getBox(int... coords) {
		int minx = coords[0];
		int miny = coords[1];
		int maxx = coords[2];
		int maxy = coords[3];
		
		int w = maxx-minx+1;
		int h = maxy-miny+1;
		return new int[]{w,h};
	}


	/**
	 * 
	 * @param point
	 * @param box
	 * @return sum of box
	 * @throws Exception
	 */
	public double getBoxSum(int[] point, int... box) throws Exception {
		if (box[0] % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box[1] % 2 == 0) throw new Exception("Box second dim is not odd!");
	    return getBoxSumInternal(sum, createCoords(point, box), shape);
	}
	
	/**
	 * 
	 * @param box
	 * @return mean from the summed area table
	 * @throws Exception
	 */
	public double getBoxMean(int[] point, int... box) throws Exception {
		if (box[0] % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box[1] % 2 == 0) throw new Exception("Box second dim is not odd!");
		int[] coords = createCoords(point, box);
        return getBoxMeanInternal(coords, box[0]*box[1]);
	}
	private double getBoxMeanInternal(int[] coords, int n) {
       return getBoxSumInternal(sum, coords, shape) / n;
	}

	/**
	 * private static final so that compiler will inline it
	 * 
	 * @param coords Coordinates of box: x1,y1,x2,y2
	 * @return the sum of a region
	 */
	private static final double getBoxSumInternal(Dataset sum, int[] coords, int[] shape) {

		int minx = coords[0];
		int miny = coords[1];
		int maxx = coords[2];
		int maxy = coords[3];
		
		double A = (minx > 0 && miny > 0) ? sum.getElementDoubleAbs(get1DIndex(minx-1, miny-1, shape)) : 0d;
		double B = (miny > 0)             ? sum.getElementDoubleAbs(get1DIndex(maxx, miny-1, shape))   : 0d;
		double C = (minx > 0)             ? sum.getElementDoubleAbs(get1DIndex(minx-1, maxy, shape))   : 0d;
		double D = sum.getElementDoubleAbs(get1DIndex(maxx, maxy, shape));
		
		return (D+A-B-C);
	}
	
	/**
	 * private static final so that compiler will inline it
	 * @param i
	 * @param j
	 * @param shape
	 * @return index
	 */
	private final static int get1DIndex(int i, int j, int[]shape) {
		if (i < 0) {
			i += shape[0];
		}
		if (i < 0 || i >= shape[0]) {
			throw new ArrayIndexOutOfBoundsException(i);
		}
		if (j < 0) {
			j += shape[1];
		}
		if (j < 0 || j >= shape[1]) {
			throw new ArrayIndexOutOfBoundsException(j);
		}
		return i*shape[1] + j;
	}

	
	/**
	 * Get the variance for a given box.
	 * 
	 *  (1/n)(S2 - S1^2/n)
     * 
     * Where:
     * S1 is sum of box ( D+A-B-C of sum )
     * S2 is sum^2 of box ( D+A-B-C of sum )
     * n  is number of pixels box covers
     * 
	 * @param box
	 * @return variance
	 * @throws Exception
	 */
	public double getBoxVariance(IRectangularROI box) throws Exception {

		if (box.getIntLength(0) % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box.getIntLength(1) % 2 == 0) throw new Exception("Box second dim is not odd!");

		if (sum2==null) createSummedTable(image, true);
		int[] coords = createCoords(box);
		return getBoxVariance(getBox(coords), coords);

	}

	/**
	 * Get the variance for a given box.
	 * 
	 *  (1/n)(S2 - S1^2/n)
     * 
     * Where:
     * S1 is sum of box ( D+A-B-C of sum )
     * S2 is sum^2 of box ( D+A-B-C of sum2 )
     * n  is number of pixels box covers
     * @throws Exception 
     * 
    **/
	public double getBoxVariance(int[] point, int... box) throws Exception {
		if (sum2==null) createSummedTable(image, true);
		int [] coords = createCoords(point, box);
		return getBoxVarianceInternal(coords, box[0]*box[1]);
	}
	
	private double getBoxVarianceInternal(int[] coords, int n) {

		double s1 = getBoxSumInternal(sum,  coords, shape);		
		double s2 = getBoxSumInternal(sum2, coords, shape);
		
		return (1d/n)*(s2 - (Math.pow(s1, 2d)/n));
	}

	@Override
	public int[] getShape() {
		return shape;
	}

	@Override
	public String getName() {
		return sum.getName();
	}

	@Override
	public void setName(String name) {
		sum.setName(name);
	}

	@Override
	public void setStringFormat(Format format) {
		sum.setStringFormat(format);
	}

	@Override
	public int getItemsize() {
		return sum.getItemsize();
	}

	@Override
	public <T extends MetadataType> List<T> getMetadata(Class<T> clazz) throws Exception {
		return sum.getMetadata(clazz);
	}

	@Override
	public Object getObject(int... pos) {
		return sum.getObject(pos);
	}

	@Override
	public String getString(int... pos) {
		return sum.getString(pos);
	}

	@Override
	public Class<?> elementClass() {
		return sum.elementClass();
	}

	@Override
	public int getElementsPerItem() {
		return sum.getElementsPerItem();
	}

	@Override
	public double getDouble(int... pos) {
		return sum.getDouble(pos);
	}

	@Override
	public int getSize() {
		return sum.getSize();
	}

	@Override
	public long getLong(int... pos) {
		return sum.getLong(pos);
	}

	@Override
	public float getFloat(int... pos) {
		return sum.getFloat(pos);
	}

	@Override
	public int getInt(int... pos) {
		return sum.getInt(pos);
	}

	@Override
	public void setShape(int... shape) {
		sum.setShape(shape);
	}

	@Override
	public short getShort(int... pos) {
		return sum.getShort(pos);
	}

	@Override
	public byte getByte(int... pos) {
		return sum.getByte(pos);
	}

	@Override
	public int getRank() {
		return sum.getRank();
	}

	@Override
	public boolean getBoolean(int... pos) {
		return sum.getBoolean(pos);
	}

	@Override
	public void set(Object obj, int... pos) {
		sum.set(obj, pos);
	}

	@Override
	public IDataset getSlice(int[] start, int[] stop, int[] step) {
		return sum.getSlice(start, stop, step);
	}

	@Override
	public void resize(int... newShape) {
		sum.resize(newShape);
	}

	@Override
	public IDataset squeezeEnds() {
		return sum.squeezeEnds();
	}

	@Override
	public IDataset squeeze() {
		return sum.squeeze();
	}

	@Override
	public IDataset squeeze(boolean onlyFromEnds) {
		return sum.squeeze(onlyFromEnds);
	}

	@Override
	public Object mean(boolean... switches) {
		return sum.mean(switches);
	}

	@Override
	public IDataset getSlice(IMonitor monitor, int[] start, int[] stop, int[] step) throws Exception {
		return sum.getSlice(monitor, start, stop, step);
	}

	@Override
	public Number min(boolean... switches) {
		return sum.min(switches);
	}

	@Override
	public IDataset getSlice(Slice... slice) {
		return sum.getSlice(slice);
	}

	
	@Override public Number max(boolean... switches) {
		return sum.max(switches);
	}

	@Override public IDataset getSlice(SliceND slice) {
		return sum.getSlice(slice);
	}

	@Override public IDataset getSlice(IMonitor monitor, Slice... slice) throws Exception {
		return sum.getSlice(monitor, slice);
	}

	@Override public int[] minPos() {
		return sum.minPos();
	}

	@Override public int[] maxPos() {
		return sum.maxPos();
	}

	@Override public IDataset clone() {
		return sum.clone();
	}

	@Override public IDataset getSlice(IMonitor monitor, SliceND slice) throws Exception {
		return sum.getSlice(monitor, slice);
	}

	@Override public IMetadata getMetadata() {
		return sum.getMetadata();
	}

	@Override public IDataset getSliceView(int[] start, int[] stop, int[] step) {
		return sum.getSliceView(start, stop, step);
	}

	@Override public IDataset getSliceView(Slice... slice) {
		return sum.getSliceView(slice);
	}

	@Override public IDataset getSliceView(SliceND slice) {
		return sum.getSliceView(slice);
	}

	@Override public ILazyDataset getTransposedView(int... axes) {
		return sum.getTransposedView(axes);
	}

	@Override public <T extends MetadataType> void addMetadata(T metadata) {
		sum.addMetadata(metadata);
	}

	@Override public <T extends MetadataType> void setMetadata(T metadata) {
		sum.setMetadata(metadata);
	}

	@Override public <T extends MetadataType> void clearMetadata(Class<T> clazz) {
		sum.clearMetadata(clazz);
	}

	@Override public void setError(Serializable errors) {
		sum.setError(errors);
	}

	@Override public ILazyDataset getError() {
		return sum.getError();
	}
	
	
	/**
	 * private static final so that compiler will inline it
	 * @param box
	 * @return coords
	 */
	private static final int[] createCoords(IRectangularROI box) {
		return new int[]{box.getIntPoint()[0], 
				         box.getIntPoint()[1], 
				         box.getIntPoint()[0]+box.getIntLength(0), 
				         box.getIntPoint()[1]+box.getIntLength(1)};
	}

	private final int[] createCoords(int[] point, int[] box)  {
		
		int w = box[0];
		int h = box[1];
		int r1 = (int)Math.floor(w/2d); // for instance 3->1, 5->2, 7->3 
		int r2 = (int)Math.floor(h/2d); // for instance 3->1, 5->2, 7->3 
	    return createCoordsInternal(point, new int[]{r1, r2});
	}
	
	private final int[] createCoordsInternal(int[] point, int[] radii)  {

		int x = point[0];
		int y = point[1];
		
		int r1 = radii[0]; // for instance 3->1, 5->2, 7->3 
		int r2 = radii[1]; // for instance 3->1, 5->2, 7->3 
		
		int minx = x-r1;
		if (minx<0) minx=0;		
		int maxx = x+r1;
		if (maxx>=shape[0]) maxx = shape[0]-1;
		
		int miny = y-r2;
		if (miny<0) miny=0;		
		int maxy = y+r2;
		if (maxy>=shape[1]) maxy = shape[1]-1;
		
		return new int[]{minx, miny, maxx, maxy};
	}

}
