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
package org.eclipse.dawnsci.hdf5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Datatype;
import ncsa.hdf.object.HObject;

public class HierarchicalDataUtils {


	/**
	 * Overwrites destination_file if it exists, creates new if not.
	 *
	 * @param source_file
	 * @param destination_file
	 * @throws IOException
	 */
	public final static void copy(final File source_file, final File destination_file) throws IOException {
		HierarchicalDataUtils.copy(source_file, destination_file, new byte[4096]);
	}

	/**
	 * Overwrites destination_file if it exists, creates new if not.
	 *
	 * @param source_file
	 * @param destination_file
	 * @param buffer
	 * @throws IOException
	 */
	public final static void copy(final File source_file, final File destination_file, final byte[] buffer) throws IOException {

		if (!source_file.exists()) {
			return;
		}

		final File parTo = destination_file.getParentFile();
		if (!parTo.exists()) {
			parTo.mkdirs();
		}
		if (!destination_file.exists()) {
			destination_file.createNewFile();
		}

		InputStream source = null;
		OutputStream destination = null;
		try {

			source = new BufferedInputStream(new FileInputStream(source_file));
			destination = new BufferedOutputStream(new FileOutputStream(destination_file));
			int bytes_read;
			while (true) {
				bytes_read = source.read(buffer);
				if (bytes_read == -1) {
					break;
				}
				destination.write(buffer, 0, bytes_read);
			}

		} finally {
			source.close();
			destination.close();
		}
	}


	/**
	 * @throws Exception
	 * @throws OutOfMemoryError
	 *
	 */
	public static boolean isDataType(Dataset set, int requiredType) throws OutOfMemoryError, Exception {

		if (requiredType<0) return true; // Numbers less than 0 are any dataset

        final int   type  = set.getDatatype().getDatatypeClass();
        if (type==Datatype.CLASS_FLOAT || type==Datatype.CLASS_INTEGER || type==Datatype.CLASS_CHAR) {
        	if (IHierarchicalDataFile.NUMBER_ARRAY==requiredType) {
        		long[]shape = getDims(set);
                if (shape==null) return true;
        		return shape.length>1 || shape[0]>1;
        	} else if (IHierarchicalDataFile.SCALAR==requiredType) {
        		long[]shape = getDims(set);
                if (shape==null) return true;
        		return shape.length==1 && shape[0]==1;
        	}
        }

        if (type==Datatype.CLASS_STRING) {
        	if (IHierarchicalDataFile.TEXT==requiredType) {
        		return true;
        	} else if (IHierarchicalDataFile.SCALAR==requiredType) {
        		long[]shape = getDims(set);
                if (shape==null) return true;
        		return shape.length==1 && shape[0]==1;
        	}
        }

        return requiredType==type;
	}


	/**
	 * Returns the link in another file if this is a linked file.
	 * @param set
	 * @param original - may be null
	 * @return
	 * @throws OutOfMemoryError
	 * @throws Exception
	 */
	public static HObject getDataLink(HObject set, HierarchicalDataFile original) {

		try {
			// To make things faster, we only check for Datasets
			if (!(set instanceof Dataset)) return null;

			List<?> attributes = set.getMetadata(); // Appears in stack traces of VM exists
			if (attributes==null || attributes.isEmpty()) return null;

			for (Object attribute : attributes) {
				if (attribute instanceof Attribute) {
					Attribute a = (Attribute)attribute;
					if (a.getName().equals(HierarchicalInfo.NAPIMOUNT)) {
						return  getDataLink(set, a, original);
					}
				}
			}
		} catch (Throwable ne) {
			return null;
		}

		return null;
	}

	private static final Pattern LINK_ATTR = Pattern.compile("nxfile\\:\\/\\/(.+)\\#(entry.+)");

	private static final HObject getDataLink(HObject set, Attribute a, HierarchicalDataFile original) throws Exception {

		final String[] vals = (String[])a.getValue();
		final Matcher matcher = LINK_ATTR.matcher(vals[0]);
		if (matcher.matches()) {

			final String path     = matcher.group(1);
			final String fullPath = matcher.group(2);

			File file = new File(path);
			if (!file.exists()) {
				// Look in same directory.
				final File parent = set.getFileFormat().getAbsoluteFile().getParentFile();
				file = new File(parent, file.getName());
			}

			if (!file.exists()) return null;

			@SuppressWarnings("resource")
			IHierarchicalDataFile hFile = original!=null
					                    ? original.getLinkedFile(file.getAbsolutePath())
					                    // Causes a memory leak I think:
					                    : HierarchicalDataFactory.getReader(file.getAbsolutePath());
			HObject link = (HObject)hFile.getData(fullPath);
			link.getMetadata(); // Ensure that meta data is read.
			return link;
		}

		return null;
	}

	public static long[] getDims(final IHierarchicalDataFile file, String setPath) throws Exception {

		HObject obj = (HObject)file.getData(setPath);
		if (!(obj instanceof Dataset)) return null;
		Dataset set = (Dataset)obj;
        if (set.getDims()==null) {
        	set.getMetadata();
        }
        return set.getDims();
	}

	public static long[] getDims(final Dataset set) throws Exception {

        if (set.getDims()==null) {
        	set.getMetadata();
        }
        return set.getDims();
	}

	public static long getSize(final Dataset set) throws Exception {

		long[] shape;
		try {
			shape = HierarchicalDataUtils.getDims((Dataset)set);
		} catch (Exception e) {
			return -1;
		}
		if (shape==null) return -1;

		long size = shape[0];
		for (int i = 1; i < shape.length; i++) size*=shape[i];

		final int bpi  = set.getDatatype().getDatatypeSize();
        return  bpi*size;


	}

	/**
	 * Extract the value of an attribute.
	 * @param value
	 * @return
	 */
	public static String extractValue(Object value) {
		if (value == null) {
			return "";

		} else if (value.getClass().isArray()) {
			return toString(value);

		} else {
			return value.toString();
		}
	}

	/**
	 * Deals with primitive arrays
	 * @param value
	 */
	private static String toString(Object value) {

		if (value==null) return null;

        if (value instanceof short[]) {
        	return Arrays.toString((short[])value);

        } else if  (value instanceof int[]) {
        	return Arrays.toString((int[])value);

        } else if  (value instanceof long[]) {
        	return Arrays.toString((long[])value);

        } else if  (value instanceof char[]) {
        	return Arrays.toString((char[])value);

        } else if  (value instanceof float[]) {
        	return Arrays.toString((float[])value);

        } else if  (value instanceof double[]) {
        	return Arrays.toString((double[])value);

        } else if  (value instanceof boolean[]) {
        	return Arrays.toString((boolean[])value);

        } else if  (value instanceof byte[]) {
        	return Arrays.toString((byte[])value);

        } else if  (value instanceof Object[]) {
        	return Arrays.toString((Object[])value);
        }

        return value.toString();
	}

	/**
	 * Convert the size == 1 array to a scalar (extract value[0]). Works on
	 * primitive type arrays, String[] and Number[]
	 *
	 * @param value
	 *            a {@link Dataset} value (i.e. an array, the result of calling
	 *            {@link Dataset#read()})
	 * @return the scalar value, or <code>null</code> if no scalar can be
	 *         extracted
	 */
	public static Object extractScalar(Object value) {

		if (value == null)
			return null;

		if (value instanceof short[]) {
			short[] valueArray = (short[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof int[]) {
			int[] valueArray = (int[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof long[]) {
			long[] valueArray = (long[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof char[]) {
			char[] valueArray = (char[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof float[]) {
			float[] valueArray = (float[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof double[]) {
			double[] valueArray = (double[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof boolean[]) {
			boolean[] valueArray = (boolean[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof byte[]) {
			byte[] valueArray = (byte[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof String[]) {
			String[] valueArray = (String[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;

		} else if (value instanceof Number[]) {
			Number[] valueArray = (Number[]) value;
			if (valueArray.length == 1)
				return valueArray[0];
			return null;
		}

		return null;
	}

	/**
	 * Compares the two scalar objects if they are the same type and Comparable
	 * using their compareTo method, else compares the toString value.
	 *
	 * @see Comparable#compareTo(Object)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int compareScalars(Object a, Object b) {
		if (a instanceof Comparable && b instanceof Comparable) {
			Comparable ca = (Comparable) a;
			Comparable cb = (Comparable) b;
			if (a.getClass() == b.getClass())
				return ca.compareTo(cb);
		}
		return a.toString().compareTo(b.toString());
	}

	/**
	 * Compare object a as a scalar to String b by first converting b to a type
	 * that matches a.
	 *
	 * @see Comparable#compareTo(Object)
	 * @throws NumberFormatException
	 *             if b can not be converted to the same type as a
	 */
	public static int compareScalarToString(Object a, String b)
			throws NumberFormatException {
		if (a instanceof Short) {
			return ((Short) a).compareTo(Short.parseShort(b));
		}
		if (a instanceof Integer) {
			return ((Integer) a).compareTo(Integer.parseInt(b));
		}
		if (a instanceof Long) {
			return ((Long) a).compareTo(Long.parseLong(b));
		}
		if (a instanceof Character) {
			return ((Character) a).toString().compareTo(b);
		}
		if (a instanceof Float) {
			return ((Float) a).compareTo(Float.parseFloat(b));
		}
		if (a instanceof Double) {
			return ((Double) a).compareTo(Double.parseDouble(b));
		}
		if (a instanceof Boolean) {
			return ((Boolean) a).compareTo(Boolean.parseBoolean(b));
		}
		if (a instanceof Byte) {
			return ((Byte) a).compareTo(Byte.valueOf(b));
		}
		if (a instanceof String) {
			return ((String) a).compareTo(b);
		}
		String name;
		if (a == null) {
			name = "null";
		} else {
			name = "a.getClass().getName()";
		}
		throw new NumberFormatException("a has unknown type for conversion: "
				+ name);
	}
}
