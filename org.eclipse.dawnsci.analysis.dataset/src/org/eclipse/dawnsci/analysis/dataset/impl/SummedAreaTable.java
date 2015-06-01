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

/**
 * This code is based on:
 * http://en.wikipedia.org/wiki/Summed_area_table
 * 
 * @author Matthew Gerring
 */
public class SummedAreaTable {

	/**
	 * Calculate a summed area table  I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
	 * @see "http://en.wikipedia.org/wiki/Summed_area_table"
	 * 
	 * @param image
	 * @return  I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
	 * @throws Exception
	 */
	public static IDataset getSummedTable(IDataset image) throws Exception {
		
		if (image.getRank()!=2) throw new Exception("You may only compute the summed image table of 2D data!");
			    
	    //Create integral
	    IDataset sum = new DoubleDataset(image.getShape());
	    
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
	    
	    //Return the sum
	    return sum;
	}
}
