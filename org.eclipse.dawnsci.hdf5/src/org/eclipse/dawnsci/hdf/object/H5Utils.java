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
package org.eclipse.dawnsci.hdf.object;

import org.eclipse.january.dataset.DTypeUtils;
import org.eclipse.january.dataset.Dataset;
import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DatasetUtils;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;

import hdf.object.Datatype;
import hdf.object.h5.H5Datatype;

public class H5Utils {

	public static int getDataType(Datatype datatype) throws Exception {
		

		final int type = datatype.getDatatypeClass();
		if (type == Datatype.CLASS_ARRAY) throw new Exception("Cannot read array type data sets!");
			
		final int size = datatype.getDatatypeSize()*4;
		
		switch (size) {
		case 8:
			return Dataset.INT8;
			
		case 16:
			return Dataset.INT16;
			
		case 32:
			if (type==Datatype.CLASS_INTEGER) return Dataset.INT32;
			if (type==Datatype.CLASS_FLOAT)   return Dataset.FLOAT32;
			//$FALL-THROUGH$
		case 64:
			if (type==Datatype.CLASS_INTEGER) return Dataset.INT64;
			if (type==Datatype.CLASS_FLOAT)   return Dataset.FLOAT64;
		}
		
		return Dataset.FLOAT;
	}
	
	public static Dataset getSet(final IHierarchicalDataFile file, String fullPath) throws Exception {
		@SuppressWarnings("deprecation") // We are allowed to use this method internally.
		hdf.object.Dataset dataset = (hdf.object.Dataset) file.getData(fullPath);
		return H5Utils.getSet(dataset.getData(), dataset);
	}


	/**
	 * Gets a dataset from the complete dims.
	 * @param val
	 * @param set
	 * @return dataset
	 * @throws Exception
	 */
	public static Dataset getSet(final Object  val, final hdf.object.Dataset set) throws Exception {
	    return H5Utils.getSet(val, set.getDims(), set);
	}

	/**
	 * Used when dims are not the same as the entire set, for instance when doing a slice.
	 * @param val
	 * @param longShape
	 * @param set
	 * @return dataset
	 * @throws Exception
	 */
	public static Dataset getSet(final Object  val, final long[] longShape, final hdf.object.Dataset set) throws Exception {
		final int[] intShape  = getInt(longShape);
         
		Dataset ret = DatasetFactory.createFromObject(val);
		ret.setShape(intShape);
        
		if (set.getDatatype().isUnsigned()) {
			ret = DatasetUtils.makeUnsigned(ret);
		}

        return ret;
	}

	/**
	 * Get a int[] from a long[]
	 * @param longShape
	 * @return int shape
	 */
	public static int[] getInt(long[] longShape) {
		final int[] intShape  = new int[longShape.length];
		for (int i = 0; i < intShape.length; i++) intShape[i] = (int)longShape[i];
		return intShape;
	}

	/**
	 * Get a long[] from a int[]
	 * @param intShape
	 * @return long shape
	 */
	public static long[] getLong(int[] intShape) {
		final long[] longShape  = new long[intShape.length];
		for (int i = 0; i < intShape.length; i++) longShape[i] = intShape[i];
		return longShape;
	}
	/**
	 * Determines the HDF5 Datatype for an abstract dataset.
	 * @param a
	 * @return data type
	 */
	public static Datatype getDatatype(IDataset a) throws Exception {
		
		// There is a smarter way of doing this, but am in a hurry...
		try {
		    return getDatatype(DTypeUtils.getDType(a));
		} catch (Exception ne) {
			throw new Exception("Cannot deal with data in form "+a.getClass().getName());
		}
	}
	
	public static Datatype getDatatype(int dType) throws Exception {
		
		return getDatatype(dType, -1);
	}

    /**
     * 
     * @param dType
     * @param size - used if the dType is a String only
     * @return
     */
	public static Datatype getDatatype(int dType, int size)  throws Exception {
		
		// There is a smarter way of doing this, but am in a hurry...
		if (dType==Dataset.INT8 || dType==Dataset.BOOL) {
         	return new H5Datatype(Datatype.CLASS_INTEGER, 8/8, Datatype.NATIVE, Datatype.SIGN_NONE);
         	
        } else if (dType==Dataset.INT16) {
        	return new H5Datatype(Datatype.CLASS_INTEGER, 16/8, Datatype.NATIVE, Datatype.NATIVE); 
        	
        } else if (dType==Dataset.INT32) {
        	return new H5Datatype(Datatype.CLASS_INTEGER, 32/8, Datatype.NATIVE, Datatype.NATIVE); 
       	
        } else if (dType==Dataset.INT64) {
        	return new H5Datatype(Datatype.CLASS_INTEGER, 64/8, Datatype.NATIVE, Datatype.NATIVE); 
        	
        } else if (dType==Dataset.FLOAT32) {
        	return new H5Datatype(Datatype.CLASS_FLOAT, 32/8, Datatype.NATIVE, Datatype.NATIVE); 
        	
        } else if (dType==Dataset.FLOAT64) {
        	return new H5Datatype(Datatype.CLASS_FLOAT, 64/8, Datatype.NATIVE, Datatype.NATIVE); 
      	    
        } else if (dType == Dataset.STRING) {
        	if (size<0) size = 64;
        	return new H5Datatype(Datatype.CLASS_STRING, size, Datatype.NATIVE, Datatype.NATIVE);
        }
          
        throw new Exception("Cannot deal with data type "+dType);
	}

	/**
	 * Appends a to a dataset of the same name as a and parent Group of parent.
	 * 
	 * @param file
	 * @param parent
	 * @param a
	 * @throws Exception
	 */
	public static void appendDataset(IHierarchicalDataFile file, String parent, IDataset a) throws Exception {

		String s = file.appendDataset(a.getName(), a, parent);

		file.setNexusAttribute(s, Nexus.SDS);
	}
	
	public static void insertDataset(IHierarchicalDataFile file, String parent, IDataset a, Slice[] slice, long[] finalShape) throws Exception {
		int[] fShape = H5Utils.getInt(finalShape);
		file.insertSlice(a.getName(), a, parent, new SliceND(a.getShape(), fShape, slice));
	}
}
