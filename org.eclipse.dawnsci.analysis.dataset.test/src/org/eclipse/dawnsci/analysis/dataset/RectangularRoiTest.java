/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.junit.Test;

/**
 * Tests for the RectangularROI class.
 * @author Matt Taylor
 *
 */
public class RectangularRoiTest {
		
	private final double TOLERANCE = 0.0001;
	
		@Test
		public void RectRoiBounds_Nested_PPPPPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			IRectangularROI r2 = new RectangularROI(5, 6, 1, 1, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(2, s0, TOLERANCE);
			assertEquals(3, s1, TOLERANCE);
			assertEquals(6, l0, TOLERANCE);
			assertEquals(7, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Nested_NNPPNNPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(-12, -7, 4, 4, 0);
			IRectangularROI r2 = new RectangularROI(-11, -6, 2, 2, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(-12, s0, TOLERANCE);
			assertEquals(-7, s1, TOLERANCE);
			assertEquals(4, l0, TOLERANCE);
			assertEquals(4, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_NegativeWidth() throws Exception {
			try {
				new RectangularROI(6, 6, -1, 1, 0);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_NegativeHeight() throws Exception {
			try {
				new RectangularROI(6, 6, 1, -1, 0);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative height", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_NegativeWidthHeight() throws Exception {
			try {
				new RectangularROI(6, 6, -1, -1, 0);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetNegativeWidth() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.setLengths(new double[]{-1,2});
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetNegativeHeight() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.setLengths(new double[]{1,-2});
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative height", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetNegativeWidthHeight() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.setLengths(new double[]{-1,-2});
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetNegativeWidthMajor() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.setLengths(-1, 4);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetNegativeHeightMinor() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.setLengths(1,-2);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative height", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetNegativeWidthHeightMajorMinor() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.setLengths(-1,-2);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_AddNegativeWidthOK() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			r1.addToLengths(-1, 4);
			assertEquals(r1.getLength(0), 5, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_AddNegativeWidthFail() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.addToLengths(-7, 4);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative width", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_AddNegativeHeightOK() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			r1.addToLengths(2, -3);
			assertEquals(r1.getLength(1), 4, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_AddNegativeHeightFail() throws Exception {
			RectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			try {
				r1.addToLengths(5, -9);
				fail("No Exception thrown");
			} catch (IllegalArgumentException ex) {
				assertEquals("RectangularROI cannot have negative height", ex.getMessage());
			}
		}
		
		@Test
		public void RectRoiBounds_SetEndPointBeforeStart() throws Exception {
			RectangularROI r1 = new RectangularROI(4, 6, 7, 8, 0);
			r1.setEndPoint(new double[]{1,2});
			assertEquals(1, r1.getPoint()[0], TOLERANCE);
			assertEquals(2, r1.getPoint()[1], TOLERANCE);
			assertEquals(3, r1.getLength(0), TOLERANCE);
			assertEquals(4, r1.getLength(1), TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Nested_PPPPPPPPsecBigger() throws Exception {
			IRectangularROI r1 = new RectangularROI(5, 6, 1, 1, 0);
			IRectangularROI r2 = new RectangularROI(2, 3, 6, 7, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(2, s0, TOLERANCE);
			assertEquals(3, s1, TOLERANCE);
			assertEquals(6, l0, TOLERANCE);
			assertEquals(7, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Separate_PPPPPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			IRectangularROI r2 = new RectangularROI(10, 12, 1, 1, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(2, s0, TOLERANCE);
			assertEquals(3, s1, TOLERANCE);
			assertEquals(9, l0, TOLERANCE);
			assertEquals(10, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Separate_NNPPNNPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(-12, -7, 4, 4, 0);
			IRectangularROI r2 = new RectangularROI(-15, -11, 2, 2, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(-15, s0, TOLERANCE);
			assertEquals(-11, s1, TOLERANCE);
			assertEquals(7, l0, TOLERANCE);
			assertEquals(8, l1, TOLERANCE);
		}
	}
