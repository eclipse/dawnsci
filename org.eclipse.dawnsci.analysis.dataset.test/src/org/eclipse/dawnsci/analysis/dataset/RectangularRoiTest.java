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
		public void RectRoiBounds_Nested_PPPPPPNP() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			IRectangularROI r2 = new RectangularROI(6, 6, -1, 1, 0);
			
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
		public void RectRoiBounds_Nested_PPPPPPPN() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			IRectangularROI r2 = new RectangularROI(5, 7, 1, -1, 0);
			
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
		public void RectRoiBounds_Nested_PPNPPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(8, 3, -6, 7, 0);
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
		public void RectRoiBounds_Nested_PPNPPPNP() throws Exception {
			IRectangularROI r1 = new RectangularROI(8, 3, -6, 7, 0);
			IRectangularROI r2 = new RectangularROI(6, 6, -1, 1, 0);
			
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
		public void RectRoiBounds_Nested_PPNPPPPPSecBigger() throws Exception {
			IRectangularROI r1 = new RectangularROI(5, 6, 1, 1, 0);
			IRectangularROI r2 = new RectangularROI(8, 3, -6, 7, 0);
			
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
		public void RectRoiBounds_Nested_NNPNNNPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(-12, -3, 4, -4, 0);
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
		public void RectRoiBounds_Nested_NNNNNNNN() throws Exception {
			IRectangularROI r1 = new RectangularROI(-8, -3, -4, -4, 0);
			IRectangularROI r2 = new RectangularROI(-9, -4, -2, -2, 0);
			
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
		public void RectRoiBounds_Separate_PPNPPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(8, 3, -6, 7, 0);
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
		
		@Test
		public void RectRoiBounds_Separate_NNNNNNNN() throws Exception {
			IRectangularROI r1 = new RectangularROI(-8, -3, -4, -4, 0);
			IRectangularROI r2 = new RectangularROI(-13, -9, -2, -2, 0);
			
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
		
		@Test
		public void RectRoiBounds_Separate_PPPNPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 10, 6, -7, 0);
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
		public void RectRoiBounds_Separate_PPPNPPPN() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 10, 6, -7, 0);
			IRectangularROI r2 = new RectangularROI(10, 13, 1, -1, 0);
			
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
		public void RectRoiBounds_Overlap_PPPPPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			IRectangularROI r2 = new RectangularROI(7, 6, 2, 2, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(2, s0, TOLERANCE);
			assertEquals(3, s1, TOLERANCE);
			assertEquals(7, l0, TOLERANCE);
			assertEquals(7, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Overlap_NNNNNNNN() throws Exception {
			IRectangularROI r1 = new RectangularROI(-2, -3, -6, -7, 0);
			IRectangularROI r2 = new RectangularROI(-7, -6, -2, -2, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(-9, s0, TOLERANCE);
			assertEquals(-10, s1, TOLERANCE);
			assertEquals(7, l0, TOLERANCE);
			assertEquals(7, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Overlap_PPPNPPPP() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 10, 6, -7, 0);
			IRectangularROI r2 = new RectangularROI(7, 6, 2, 2, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(2, s0, TOLERANCE);
			assertEquals(3, s1, TOLERANCE);
			assertEquals(7, l0, TOLERANCE);
			assertEquals(7, l1, TOLERANCE);
		}
		
		@Test
		public void RectRoiBounds_Overlap_PPPPPPPN() throws Exception {
			IRectangularROI r1 = new RectangularROI(2, 3, 6, 7, 0);
			IRectangularROI r2 = new RectangularROI(7, 11, 4, -2, 0);
			
			r1 = r1.bounds(r2);
			
			double s0 = r1.getPoint()[0];
			double s1 = r1.getPoint()[1];
			double l0 = r1.getLength(0);
			double l1 = r1.getLength(1);
			
			assertEquals(2, s0, TOLERANCE);
			assertEquals(3, s1, TOLERANCE);
			assertEquals(9, l0, TOLERANCE);
			assertEquals(8, l1, TOLERANCE);
		}
	}
