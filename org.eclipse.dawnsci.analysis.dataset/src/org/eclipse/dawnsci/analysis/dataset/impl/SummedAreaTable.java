/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
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
public class SummedAreaTable {
	
	private IDataset image;
	private IDataset sum;

	/**
	 * Constructs the summed table.
	 * @param image
	 * @throws Exception
	 */
	public SummedAreaTable(IDataset image) throws Exception {
		this.image = image;
		createSummedTable();
	}
	
	private void createSummedTable() throws Exception {
		
		if (image.getRank()!=2) throw new Exception("You may only compute the summed image table of 2D data!");
	    
	    //Create integral
	    sum = new DoubleDataset(image.getShape());
	    
	    // Create a position iterator
	    final PositionIterator it = new PositionIterator(image.getShape());
	    while(it.hasNext()) {
	    	
	    	final int[] pos = it.getPos();
	    	int x = pos[0];
	    	int y = pos[1];
	    	
	        // I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
	        //Calculate coefficients
	        double sxm  = (x > 0)          ? sum.getDouble(x-1,y)   : 0;
	        double sym  = (y > 0)          ? sum.getDouble(x,y-1)   : 0;
	        double sxym = (x > 0 && y > 0) ? sum.getDouble(x-1,y-1) : 0;
	        
	        double val = image.getDouble(x,y) + sxm + sym - sxym;
	        sum.set(val, pos);
	    }

	}

	/**
	 * Calculate a summed area table  I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
	 * @see "http://en.wikipedia.org/wiki/Summed_area_table"
	 * 
	 * @return  I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
	 * @throws Exception
	 */
	public IDataset getSummedTable() throws Exception {
		if (sum == null) createSummedTable();    
	    //Return the sum
	    return sum;
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

		return getBoxSum(box.getIntPoint()[0], 
	               box.getIntPoint()[1], 
	               box.getIntPoint()[0]+box.getIntLength(0), 
	               box.getIntPoint()[1]+box.getIntLength(1));

	}
	
	/**
	 * 
	 * @param point
	 * @param box
	 * @return sum of box
	 * @throws Exception
	 */
	public double getBoxSum(int[] point, int... box) throws Exception {

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
		if (maxx>=sum.getShape()[0]) maxx = sum.getShape()[0]-1;
		
		int miny = y-r2;
		if (miny<0) miny=0;		
		int maxy = y+r2;
		if (maxy>=sum.getShape()[1]) maxy = sum.getShape()[1]-1;
	
	    return getBoxSum(minx, miny, maxx, maxy);
	}

	/**
	 * 
	 * @param coords Coordinates of box: x1,y1,x2,y2
	 * @return the sum of a region
	 */
	private double getBoxSum(int... coords) {

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

	public int[] getShape() {
		return sum.getShape();
	}

	public double getDouble(int x, int y) {
		return sum.getDouble(x,y);
	}
}
