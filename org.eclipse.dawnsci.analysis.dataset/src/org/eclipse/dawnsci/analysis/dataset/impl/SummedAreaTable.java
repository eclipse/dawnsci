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
 * @author Matthew Gerring
 */
public class SummedAreaTable implements IDataset {
	
	private int[] shape; // We cache shape for speed reasons (it is cloned in the dataset on getShape())
	private IDataset image;
	private IDataset sum, sum2;

	/**
	 * Calls SummedAreaTable(Image, false)
	 * @param image
	 * @throws Exception
	 */
	public SummedAreaTable(IDataset image) throws Exception {
		this(image, false);
	}

	/**
	 * Constructs the summed table.
	 * @param image
	 * @param willRequireVariance set to true if you know that you need fano or variance
	 * @throws Exception
	 */
	public SummedAreaTable(IDataset image, boolean willRequireVariance) throws Exception {
		this.image = image;
		this.shape = image.getShape();
		createSummedTable(image, willRequireVariance);
	}
	
	/**
	 * We mess about here with creating the sums in one pass for speed reasons.
	 * The test SummedAreaTableTest should check if logic remains correct.
	 * @param image
	 * @param requireSum2
	 * @throws Exception
	 */
	private void createSummedTable(IDataset image, boolean requireSum2) throws Exception {
		
		if (image.getRank()!=2) throw new Exception("You may only compute the summed image table of 2D data!");
	    
		if (sum!=null && sum2!=null)   return;
		if (sum!=null && !requireSum2) return;
		
	    //Create integral
		boolean requireSum = false;
		if (sum == null) {
			sum  = new DoubleDataset(image.getShape());
			requireSum = true;
		}
		
		IDataset image2 = null;
		if (requireSum2 && sum2==null) {
			image2 = Maths.power(image, 2d);
			sum2 = new DoubleDataset(image.getShape());
		}
	    
	    // Create a position iterator
	    final PositionIterator it = new PositionIterator(image.getShape());
	    while(it.hasNext()) {
	    	final int[] pos = it.getPos();
	        if (requireSum)  fill(image,  sum,  pos);
	        if (requireSum2) fill(image2, sum2, pos);
	    }
	}

	private static final void fill(IDataset image, IDataset sum, int[] pos) {
    	
		int x = pos[0];
    	int y = pos[1];
    	
        // I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
        //Calculate coefficients
        double sxm  = (x > 0)          ? sum.getDouble(x-1,y)   : 0;
        double sym  = (y > 0)          ? sum.getDouble(x,y-1)   : 0;
        double sxym = (x > 0 && y > 0) ? sum.getDouble(x-1,y-1) : 0;
        
        double val = image.getDouble(pos) + sxm + sym - sxym;
        sum.set(val, pos);
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
	public IDataset getFanoImage(int... box) throws Exception {
		
		IDataset fano = DatasetFactory.zeros((Dataset)image);
		final PositionIterator it = new PositionIterator(shape);
		
		int n = box[0]*box[1];

		while(it.hasNext()) {
			
			int[] point   = it.getPos();
			int [] coords = createCoords(point, box);
			
			// These save some floating points
			double vari = getBoxVarianceInternal(coords, n);
			double mean = getBoxMeanInternal(coords, n);
			fano.set(vari/mean, point);
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

		if (sum.getRank()!=2) throw new Exception("You may only get sum of 2D data!");
		if (box.getIntLength(0) % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box.getIntLength(1) % 2 == 0) throw new Exception("Box second dim is not odd!");

		return getBoxSum(sum, createCoords(box));

	}
	
	/**
	 * 
	 * @param box
	 * @return mean from the summed area table
	 */
	public double getBoxMean(IRectangularROI box) {
		int[] coords = createCoords(box);
		int[] bx     = getBox(coords);
        return getBoxSum(sum, coords) / (bx[0]*bx[1]);
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

		if (sum.getRank()!=2) throw new Exception("You may only get sum of 2D data!");
		if (box.getIntLength(0) % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box.getIntLength(1) % 2 == 0) throw new Exception("Box second dim is not odd!");

		int[] coords = createCoords(box);
		return getBoxVariance(getBox(coords), coords);

	}

	private int[] getBox(int... coords) {
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
	    return getBoxSum(sum, createCoords(point, box));
	}
	
	/**
	 * 
	 * @param box
	 * @return mean from the summed area table
	 * @throws Exception
	 */
	public double getBoxMean(int[] point, int... box) throws Exception {
		int[] coords = createCoords(point, box);
        return getBoxMeanInternal(coords, box[0]*box[1]);
	}
	private double getBoxMeanInternal(int[] coords, int n) throws Exception {
       return getBoxSum(sum, coords) / n;
	}

	/**
	 * 
	 * @param coords Coordinates of box: x1,y1,x2,y2
	 * @return the sum of a region
	 */
	private static double getBoxSum(IDataset sum, int... coords) {

		int minx = coords[0];
		int miny = coords[1];
		int maxx = coords[2];
		int maxy = coords[3];
		
		double A = (minx > 0 && miny > 0) ? sum.getDouble(minx-1, miny-1) : 0;
		double B = (miny > 0)             ? sum.getDouble(maxx, miny-1)   : 0;
		double C = (minx > 0)             ? sum.getDouble(minx-1, maxy)   : 0;
		double D = sum.getDouble(maxx, maxy);
		
		return (D+A-B-C);
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
		int [] coords = createCoords(point, box);
		return getBoxVarianceInternal(coords, box[0]*box[1]);
	}
	
	private double getBoxVarianceInternal(int[] coords, int n) throws Exception {

		
		double s1 = getBoxSum(sum, coords);
		
		if (sum2==null) createSummedTable(image, true);
		
		double s2 = getBoxSum(sum2, coords);
		
		return (1/n)*(s2 - (Math.pow(s1, 2d)/n));
	}

	@Override
	public int[] getShape() {
		return sum.getShape();
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
	
	
	
	private int[] createCoords(IRectangularROI box) {
		return new int[]{box.getIntPoint()[0], 
				         box.getIntPoint()[1], 
				         box.getIntPoint()[0]+box.getIntLength(0), 
				         box.getIntPoint()[1]+box.getIntLength(1)};
	}

	private int[] createCoords(int[] point, int[] box) throws Exception  {
		
		if (sum.getRank()!=2) throw new Exception("You may only get sum of 2D data!");
		if (box[0] % 2 == 0) throw new Exception("Box first dim is not odd!");
		if (box[1] % 2 == 0) throw new Exception("Box second dim is not odd!");
		int x = point[0];
		int y = point[1];
		int w = box[0];
		int h = box[1];
		
		int r1 = (int)Math.floor(w/2d); // for instance 3->1, 5->2, 7->3 
		int r2 = (int)Math.floor(h/2d); // for instance 3->1, 5->2, 7->3 
		
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
