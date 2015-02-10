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

import static org.eclipse.dawnsci.hdf5.HierarchicalDataUtils.compareScalarToString;
import static org.eclipse.dawnsci.hdf5.HierarchicalDataUtils.compareScalars;
import static org.eclipse.dawnsci.hdf5.HierarchicalDataUtils.extractScalar;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

public class HierarchicalDataUtilsTest {

	@Test
	public void testExtractScalar() {
		assertEquals(null, extractScalar(null));

		assertEquals((short) 1, extractScalar(new short[] { 1 }));
		assertEquals(1, extractScalar(new int[] { 1 }));
		assertEquals(10000000000l, extractScalar(new long[] { 10000000000l }));
		assertEquals('a', extractScalar(new char[] { 'a' }));
		assertEquals((float) 1, extractScalar(new float[] { 1 }));
		assertEquals((double) 1, extractScalar(new double[] { 1 }));
		assertEquals(true, extractScalar(new boolean[] { true }));
		assertEquals((byte) 1, extractScalar(new byte[] { 1 }));
		assertEquals("1", extractScalar(new String[] { "1" }));
		assertEquals((Integer) 1, extractScalar(new Integer[] { 1 }));

		assertEquals(null, extractScalar("Hello"));
		assertEquals(null, extractScalar(123));
		assertEquals(null, extractScalar(new int[] {}));
		assertEquals(null, extractScalar(new int[] { 1, 2 }));
		assertEquals(null, extractScalar(new int[][] { new int[] { 1 } }));
		assertEquals(null, extractScalar(new Object[] { 1 }));
	}

	@Test
	public void testCompareScalars() {
		assertTrue(compareScalars(1, 2) < 0);
		assertTrue(compareScalars(2, 1) > 0);
		assertTrue(compareScalars(1, 1) == 0);

		// fall back to .toString comparison because they are
		// different types (but both comparable)
		assertTrue(compareScalars((short) 1, (int) 1) == 0);
		assertTrue(compareScalars("1", (int) 1) == 0);

		// not both comparable
		assertTrue(compareScalars(Arrays.asList(1), "[1]") == 0);
		assertTrue(compareScalars("[1]", Arrays.asList(1)) == 0);
		assertTrue(compareScalars(Arrays.asList(1), Arrays.asList(1)) == 0);

		// Special cases dealing with NaN
		// Because we are doing compare, NaN has a sort order (See Float/Double.compareTo)
		assertEquals(0, compareScalars(Float.NaN, Float.NaN));
		assertEquals(false, Float.NaN == Float.NaN);
		assertEquals(0, compareScalars(Double.NaN, Double.NaN));
		assertEquals(false, Double.NaN == Double.NaN);
	}

	@Test
	public void testCompareScalarToString() {
		assertTrue(compareScalarToString(new Short((short) 2), "1") > 0);
		assertTrue(compareScalarToString(new Short((short) 2), "2") == 0);
		assertTrue(compareScalarToString(new Short((short) 2), "3") < 0);

		assertTrue(compareScalarToString(new Integer(2), "1") > 0);
		assertTrue(compareScalarToString(new Integer(2), "2") == 0);
		assertTrue(compareScalarToString(new Integer(2), "3") < 0);

		assertTrue(compareScalarToString(new Long(2), "1") > 0);
		assertTrue(compareScalarToString(new Long(2), "2") == 0);
		assertTrue(compareScalarToString(new Long(2), "3") < 0);

		assertTrue(compareScalarToString(new Character('b'), "a") > 0);
		assertTrue(compareScalarToString(new Character('b'), "b") == 0);
		assertTrue(compareScalarToString(new Character('b'), "c") < 0);
		assertTrue(compareScalarToString(new Character('b'), "") > 0);
		assertTrue(compareScalarToString(new Character('b'), "bb") < 0);

		assertTrue(compareScalarToString(new Float(2), "1.0") > 0);
		assertTrue(compareScalarToString(new Float(2), "2.0") == 0);
		assertTrue(compareScalarToString(new Float(2), "3.0") < 0);
		assertTrue(compareScalarToString(new Float(2), "1") > 0);
		assertTrue(compareScalarToString(new Float(2), "2") == 0);
		assertTrue(compareScalarToString(new Float(2), "3") < 0);
		assertTrue(compareScalarToString(new Float(Float.NaN), "NaN") == 0);
		assertTrue(compareScalarToString(new Float(2), "NaN") < 0);
		assertTrue(compareScalarToString(new Float(2), "Infinity") < 0);
		assertTrue(compareScalarToString(new Float(2), "-Infinity") > 0);
		assertTrue(compareScalarToString(new Float(Float.MAX_VALUE), Double.toString(Double.MAX_VALUE)) < 0);

		assertTrue(compareScalarToString(new Double(2), "1.0") > 0);
		assertTrue(compareScalarToString(new Double(2), "2.0") == 0);
		assertTrue(compareScalarToString(new Double(2), "3.0") < 0);
		assertTrue(compareScalarToString(new Double(2), "1") > 0);
		assertTrue(compareScalarToString(new Double(2), "2") == 0);
		assertTrue(compareScalarToString(new Double(2), "3") < 0);
		assertTrue(compareScalarToString(new Double(Double.NaN), "NaN") == 0);
		assertTrue(compareScalarToString(new Double(2), "NaN") < 0);
		assertTrue(compareScalarToString(new Double(2), "Infinity") < 0);
		assertTrue(compareScalarToString(new Double(2), "-Infinity") > 0);
		assertTrue(compareScalarToString(new Double(Double.MAX_VALUE), Double.toString(Double.MAX_VALUE)) == 0);
		assertTrue(compareScalarToString(new Double(Double.MAX_VALUE), "1" + Double.toString(Double.MAX_VALUE)) < 0);

		assertTrue(compareScalarToString(Boolean.TRUE, "True") == 0);
		assertTrue(compareScalarToString(Boolean.TRUE, "true") == 0);
		assertTrue(compareScalarToString(Boolean.TRUE, "TRUE") == 0);
		assertTrue(compareScalarToString(Boolean.TRUE, "False") > 0);
		assertTrue(compareScalarToString(Boolean.TRUE, "false") > 0);
		assertTrue(compareScalarToString(Boolean.TRUE, "FALSE") > 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "False") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "false") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "FALSE") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "True") < 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "true") < 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "TRUE") < 0);

		// Typos on purpose, these strings are all false as Boolean.parseBoolean
		// does not raise exception ever
		assertTrue(compareScalarToString(Boolean.FALSE, "on") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "yes") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "truu") == 0);
		assertTrue(compareScalarToString(Boolean.FALSE, "blah") == 0);

		assertTrue(compareScalarToString(new Byte((byte) 2), "1") > 0);
		assertTrue(compareScalarToString(new Byte((byte) 2), "2") == 0);
		assertTrue(compareScalarToString(new Byte((byte) 2), "3") < 0);

		assertTrue(compareScalarToString("b", "a") > 0);
		assertTrue(compareScalarToString("b", "b") == 0);
		assertTrue(compareScalarToString("b", "c") < 0);

		// Negative tests, lhs == null
		checkNumberFormatExceptionThrown(null, "");
		checkNumberFormatExceptionThrown(null, "1");
		checkNumberFormatExceptionThrown(null, "1.0");

		// Negative tests, lhs is unsupported type
		checkNumberFormatExceptionThrown(new Object(), "1");
		checkNumberFormatExceptionThrown(new BigDecimal(1), "1");
		checkNumberFormatExceptionThrown(new BigInteger("1"), "1");

		// Negative tests, rhs cannot be converted to lhs type
		checkNumberFormatExceptionThrown(new Short((short) 2), "a");
		checkNumberFormatExceptionThrown(new Integer(2), "a");
		checkNumberFormatExceptionThrown(new Long((long)2), "a");
		// there is no negative test when lhs is Character
		checkNumberFormatExceptionThrown(new Float((float)2.0), "a");
		checkNumberFormatExceptionThrown(new Double(2.0), "a");
		// there is no negative test when lhs is Boolean
		checkNumberFormatExceptionThrown(new Byte((byte)2), "a");
		// there is no negative test when lhs is String

		// Negative tests, rhs cannot be converted to lhs type
		checkNumberFormatExceptionThrown(new Short((short) 2), "2a");
		checkNumberFormatExceptionThrown(new Integer(2), "2a");
		checkNumberFormatExceptionThrown(new Long((long)2), "2a");
		// there is no negative test when lhs is Character
		checkNumberFormatExceptionThrown(new Float((float)2.0), "2.0a");
		checkNumberFormatExceptionThrown(new Double(2.0), "2.0a");
		// there is no negative test when lhs is Boolean
		checkNumberFormatExceptionThrown(new Byte((byte)2), "2a");
		// there is no negative test when lhs is String

		// Negative tests, rhs is out of range supported by lhs type
		checkNumberFormatExceptionThrown(new Short((short) 2), Long.toString(Long.MAX_VALUE));
		checkNumberFormatExceptionThrown(new Integer(2), Long.toString(Long.MAX_VALUE));
		checkNumberFormatExceptionThrown(new Long((long)2), Long.toString(Long.MAX_VALUE) + "1");
		// there is no negative test when lhs is Character
		// there is no negative test when lhs is Float or Double and rhs is some kind of number, values
		//  greater than what can be represented are still handled
		// there is no negative test when lhs is Boolean
		checkNumberFormatExceptionThrown(new Byte((byte)2), Long.toString(Long.MAX_VALUE));
		// there is no negative test when lhs is String

		// Special cases dealing with NaN
		// Because we are doing compare, NaN has a sort order (See Float/Double.compareTo)
		assertEquals(0, compareScalarToString(new Float(Float.NaN), "NaN"));
		assertEquals(false, Float.NaN == Float.NaN);
		assertEquals(0, compareScalarToString(new Double(Double.NaN), "NaN"));
		assertEquals(false, Double.NaN == Double.NaN);
	}

	private void checkNumberFormatExceptionThrown(Object a, String b) {
		try{
			compareScalarToString(a, b);
			fail("NumberFormatException not raised");
		} catch (NumberFormatException nfe) {
			// expected/required
		}
	}


}
