/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.dataset;

public class DType {

	/**
	 * Boolean
	 */
	public static final int BOOL = 0;

	/**
	 * Signed 8-bit integer
	 */
	public static final int INT8 = 1;

	/**
	 * Signed 16-bit integer
	 */
	public static final int INT16 = 2;

	/**
	 * Signed 32-bit integer
	 */
	public static final int INT32 = 3;
	/**
	 * Integer (same as signed 32-bit integer)
	 */
	public static final int INT = INT32;

	/**
	 * Signed 64-bit integer
	 */
	public static final int INT64 = 4;

	/**
	 * 32-bit floating point
	 */
	public static final int FLOAT32 = 5;

	/**
	 * 64-bit floating point
	 */
	public static final int FLOAT64 = 6;

	/**
	 * Floating point (same as 64-bit floating point)
	 */
	public static final int FLOAT = FLOAT64;

	/**
	 * 64-bit complex floating point (real and imaginary parts are 32-bit floats)
	 */
	public static final int COMPLEX64 = 7;

	/**
	 * 128-bit complex floating point (real and imaginary parts are 64-bit floats)
	 */
	public static final int COMPLEX128 = 8;

	/**
	 * Complex floating point (same as 64-bit floating point)
	 */
	public static final int COMPLEX = COMPLEX128;

	/**
	 * String
	 */
	public static final int STRING = 9;
	
	/**
	 * Object
	 */
	public static final int OBJECT = 10;

	/**
	 * Date
	 */
	public static final int DATE = 11;

	static final int ARRAYMUL = 100;

	/**
	 * Array of signed 8-bit integers
	 */
	public static final int ARRAYINT8 = ARRAYMUL * INT8;

	/**
	 * Array of signed 16-bit integers
	 */
	public static final int ARRAYINT16 = ARRAYMUL * INT16;

	/**
	 * Array of three signed 16-bit integers for RGB values
	 */
	public static final int RGB = ARRAYINT16 + 3;

	/**
	 * Array of signed 32-bit integers
	 */
	public static final int ARRAYINT32 = ARRAYMUL * INT32;

	/**
	 * Array of signed 64-bit integers
	 */
	public static final int ARRAYINT64 = ARRAYMUL * INT64;

	/**
	 * Array of 32-bit floating points
	 */
	public static final int ARRAYFLOAT32 = ARRAYMUL * FLOAT32;

	/**
	 * Array of 64-bit floating points
	 */
	public static final int ARRAYFLOAT64 = ARRAYMUL * FLOAT64;

}
