/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.roi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.dawnsci.analysis.api.roi.IParametricROI;
import org.eclipse.dawnsci.analysis.api.roi.IRectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.HyperbolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ParabolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolylineROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ROIUtils;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RingROI;
import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;
import org.junit.Test;

public class ROITest {
	private static final double ABS_TOL = 1e-14;

	@Test
	public void testPointROI() {
		PointROI p = new PointROI(10.0, 20.5);

		IRectangularROI b = p.getBounds();
		assertEquals(b.getPointX(), p.getPointX(), ABS_TOL);
		assertEquals(b.getPointY(), p.getPointY(), ABS_TOL);
		assertEquals(b.getLength(0), 0, ABS_TOL);
		assertEquals(b.getLength(1), 0, ABS_TOL);
		assertTrue(p.containsPoint(p.getPointX(), p.getPointY()));
		assertFalse(p.containsPoint(0, p.getPointY()));
		assertTrue(p.isNearOutline(p.getPointX(), p.getPointY(), 2.5));
		assertTrue(p.isNearOutline(p.getPointX()-1, p.getPointY(), 2.5));
		assertFalse(p.isNearOutline(p.getPointX()-1, p.getPointY()+3, 2.5));

		assertNull(p.findHorizontalIntersections(0));
		assertNull(p.findHorizontalIntersections(30));
		double[] xi = p.findHorizontalIntersections(20.5);
		assertArrayEquals(new double[]{10}, xi, ABS_TOL);
	}

	private static final int POINTS = 200;

	@Test
	public void testLinearROI() {
		LinearROI l = new LinearROI();
		l.setLength(10);

		IRectangularROI b = l.getBounds();
		assertEquals(b.getPointX(), l.getPointX(), ABS_TOL);
		assertEquals(b.getPointY(), l.getPointY(), ABS_TOL);
		assertEquals(b.getLength(0), 10, ABS_TOL);
		assertEquals(b.getLength(1), 0, ABS_TOL);
		assertTrue(l.containsPoint(l.getPointX(), l.getPointY()));
		assertFalse(l.containsPoint(-2, l.getPointY()));
		assertTrue(l.isNearOutline(l.getPointX(), l.getPointY(), 2.5));
		assertTrue(l.isNearOutline(l.getPointX(), l.getPointY()-1, 2.5));
		assertFalse(l.isNearOutline(l.getPointX()-1, l.getPointY()+3, 2.5));

		l.setAngleDegrees(45.);
		assertTrue(l.containsPoint(l.getPointX(), l.getPointY()));
		assertTrue(l.containsPoint(l.getPointX() + 2, l.getPointY() + 2));
		assertFalse(l.containsPoint(2, l.getPointY()));
		assertFalse(l.containsPoint(-2, l.getPointY()));

		LinearROI sl = l.copy();
		sl.setPoint(-0.1, 0);
		LinearROI ll = l.copy();
		ll.setPoint(0.1, 0);
		for (int i = 20; i < POINTS-2; i++) {
			double a = i / (POINTS - 1.0);

			double[] ps = sl.getPoint(a);
			assertTrue(l.isNearOutline(ps, 1));
			assertFalse(l.isNearOutline(ps, 0.01));

			double[] pl = ll.getPoint(a);
			assertTrue(l.isNearOutline(pl, 1));
			assertFalse(l.isNearOutline(pl, 0.01));

			double[] p = l.getPoint(a);
			assertTrue(l.containsPoint(p));
			checkPoint(l.getVerticalIntersectionParameters(p[0]), p, 0, l);
			checkPoint(l.getHorizontalIntersectionParameters(p[1]), p, 1, l);
		}

		assertTrue(l.getVerticalIntersectionParameters(l.getBounds().getEndPoint()[0]+0.1) == null);
		assertTrue(l.getVerticalIntersectionParameters(l.getBounds().getPointRef()[0]-0.1) == null);
		assertTrue(l.getHorizontalIntersectionParameters(l.getBounds().getEndPoint()[1]+0.1) == null);
		assertTrue(l.getHorizontalIntersectionParameters(l.getBounds().getPointRef()[1]-0.1) == null);

		l.setAngleDegrees(0);
		assertTrue(l.getVerticalIntersectionParameters(10.1) == null);
		assertTrue(l.getVerticalIntersectionParameters(-0.1) == null);
		assertTrue(l.getHorizontalIntersectionParameters(10.1) == null);
		assertTrue(l.getHorizontalIntersectionParameters(-0.1) == null);
		assertTrue(Double.isNaN(l.getHorizontalIntersectionParameters(0)[0]));

		assertNull(l.findHorizontalIntersections(-1));
		assertNull(l.findHorizontalIntersections(1));
		double[] xi = l.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{0, 10}, xi, ABS_TOL);

		l.setAngleDegrees(90);
		assertTrue(l.getVerticalIntersectionParameters(10.1) == null);
		assertTrue(l.getVerticalIntersectionParameters(-0.1) == null);
		assertTrue(Double.isNaN(l.getVerticalIntersectionParameters(0)[0]));
		assertTrue(l.getHorizontalIntersectionParameters(10.1) == null);
		assertTrue(l.getHorizontalIntersectionParameters(-0.1) == null);

		assertNull(l.findHorizontalIntersections(-1));
		assertNull(l.findHorizontalIntersections(11));
		xi = l.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);

		l.setAngleDegrees(45);
		assertNull(l.findHorizontalIntersections(-1));
		assertNull(l.findHorizontalIntersections(15));
		xi = l.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);
		xi = l.findHorizontalIntersections(5);
		assertArrayEquals(new double[]{5}, xi, ABS_TOL);

		l.setAngleDegrees(90);
		l.setPoint(0.1, 0);
		assertTrue(Double.isNaN(l.getVerticalIntersectionParameters(0.1)[0]));
	}

	@Test
	public void testPolygonalBounds() {
		PolygonalROI diamond = new PolygonalROI(new double[] { 1.5, 0 });
		diamond.insertPoint(new double[] { 3, 1.5 });
		diamond.insertPoint(new double[] { 1.5, 3 });
		diamond.insertPoint(new double[] { 0, 1.5 });

		IRectangularROI roi = diamond.getBounds();
		assertTrue(roi.getLength(0) > 0);

		PolygonalROI empty = new PolygonalROI();
		roi = empty.getBounds();
		assertEquals(0, roi.getLength(0), 1e-8);
		assertEquals(0, roi.getLength(1), 1e-8);
		assertTrue(Double.isNaN(roi.getPointX()));
		assertTrue(Double.isNaN(roi.getPointY()));

		PolygonalROI single = new PolygonalROI(new double[] { 1.5, -2.2 });
		roi = single.getBounds();
		assertEquals(0, roi.getLength(0), 1e-8);
		assertEquals(0, roi.getLength(1), 1e-8);
		assertEquals(1.5, roi.getPointX(), 1e-8);
		assertEquals(-2.2, roi.getPointY(), 1e-8);
	}

	@Test
	public void testPolygonalContains() {
		PolygonalROI diamond = new PolygonalROI(new double[] { 1.5, 0 });
		diamond.insertPoint(new double[] { 3, 1.5 });
		diamond.insertPoint(new double[] { 1.5, 3 });
		diamond.insertPoint(new double[] { 0, 1.5 });

		assertTrue(diamond.containsPoint(1.5, 1.5));
		assertFalse(diamond.containsPoint(3, 3));
		assertFalse(diamond.containsPoint(0, 0));
	}

	@Test
	public void testRectangularROI() {
		RectangularROI r = new RectangularROI();
		r.setPoint(0, 0);
		r.setLengths(10.5, 23);

		IRectangularROI b = r.getBounds();
		assertEquals(boundingBox(r), b);
		assertEquals(b.getPointX(), r.getPointX(), ABS_TOL);
		assertEquals(b.getPointY(), r.getPointY(), ABS_TOL);
		assertEquals(b.getLength(0), 10.5, ABS_TOL);
		assertEquals(b.getLength(1), 23, ABS_TOL);
		assertTrue(r.containsPoint(r.getPointX(), r.getPointY()));
		assertFalse(r.containsPoint(-2, r.getPointY()));
		assertTrue(r.isNearOutline(r.getPointX(), r.getPointY(), 2.5));
		assertTrue(r.isNearOutline(r.getPointX()-1, r.getPointY(), 2.5));
		assertFalse(r.isNearOutline(r.getPointX()-1, r.getPointY()-3, 2.5));

		assertNull(r.findHorizontalIntersections(-1));
		assertNull(r.findHorizontalIntersections(24));
		double[] xi = r.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{0, 10.5}, xi, ABS_TOL);

		xi = r.findHorizontalIntersections(23);
		assertArrayEquals(new double[]{0, 10.5}, xi, ABS_TOL);

		r.setAngleDegrees(35.);
		assertEquals(boundingBox(r), r.getBounds());
		assertTrue(r.containsPoint(r.getPointX(), r.getPointY()));
		assertFalse(r.containsPoint(2, r.getPointY()));
		assertFalse(r.containsPoint(-2, r.getPointY()));

		r.setAngleDegrees(45.);
		assertNull(r.findHorizontalIntersections(-1));
		assertNull(r.findHorizontalIntersections(45));
		xi = r.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);
		xi = r.findHorizontalIntersections(5);
		assertArrayEquals(new double[]{-5, 5}, xi, ABS_TOL);

		r.setAngleDegrees(145.);
		assertEquals(boundingBox(r), r.getBounds());
		assertTrue(r.containsPoint(r.getPointX(), r.getPointY()));
		assertFalse(r.containsPoint(2, r.getPointY()));
		assertTrue(r.containsPoint(-2, r.getPointY()));

		r.setAngleDegrees(260.);
		assertEquals(boundingBox(r), r.getBounds());
		assertTrue(r.containsPoint(r.getPointX(), r.getPointY()));
		assertFalse(r.containsPoint(2, r.getPointY()));
		assertFalse(r.containsPoint(-2, r.getPointY()));

		r.setAngleDegrees(342.);
		assertEquals(boundingBox(r), r.getBounds());
		assertTrue(r.containsPoint(r.getPointX(), r.getPointY()));
		assertTrue(r.containsPoint(2, r.getPointY()));
		assertFalse(r.containsPoint(-2, r.getPointY()));
	}

	public RectangularROI boundingBox(RectangularROI r) {
		double[] p = r.getPointRef();
		double[] max = p.clone();
		double[] min = max.clone();

		p = r.getPoint(1, 0);
		ROIUtils.updateMaxMin(max, min, p);

		p = r.getPoint(1, 1);
		ROIUtils.updateMaxMin(max, min, p);

		p = r.getPoint(0, 1);
		ROIUtils.updateMaxMin(max, min, p);

		RectangularROI b = new RectangularROI();
		b.setPoint(min);
		max[0] -= min[0];
		max[1] -= min[1];
		b.setLengths(max);
		return b;
	}

	@Test
	public void testCircularROI() {
		CircularROI c = new CircularROI(10);

		IRectangularROI b = c.getBounds();
		assertEquals(b.getPointX() + 10, c.getPointX(), ABS_TOL);
		assertEquals(b.getPointY() + 10, c.getPointY(), ABS_TOL);
		assertEquals(b.getLength(0), 20, ABS_TOL);
		assertEquals(b.getLength(1), 20, ABS_TOL);
		assertTrue(c.containsPoint(c.getPointX(), c.getPointY()));
		assertFalse(c.containsPoint(-20, c.getPointY()));
		CircularROI sc = new CircularROI(9.9);
		CircularROI lc = new CircularROI(10.1);
		for (int i = 0; i < POINTS; i++) {
			double a = (i * Math.PI)/POINTS;
			assertTrue(c.containsPoint(sc.getPoint(a)));
			assertTrue(c.isNearOutline(sc.getPoint(a), 1));
			assertFalse(c.isNearOutline(sc.getPoint(a), 0.01));
			assertFalse(c.containsPoint(lc.getPoint(a)));
			assertTrue(c.isNearOutline(lc.getPoint(a), 1));
			assertFalse(c.isNearOutline(lc.getPoint(a), 0.01));
		}
		assertTrue(c.isNearOutline(c.getPointX()+10, c.getPointY(), 2.5));
		assertTrue(c.isNearOutline(c.getPointX()+10, c.getPointY()-1, 2.5));
		assertFalse(c.isNearOutline(c.getPointX()+9, c.getPointY()+9, 2.5));

		assertNull(c.findHorizontalIntersections(-11));
		assertNull(c.findHorizontalIntersections(11));
		double[] xi = c.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-10, 10}, xi, ABS_TOL);
		xi = c.findHorizontalIntersections(10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);
		xi = c.findHorizontalIntersections(8);
		assertArrayEquals(new double[]{-6, 6}, xi, ABS_TOL);
	}

	
	@Test
	public void testEllipticalROI() {
		EllipticalROI e = new EllipticalROI(10, 5, Math.PI/4., 0, 0);
		assertTrue(e.containsPoint(3,3));
		assertTrue(e.containsPoint(7,6));
		assertTrue(e.containsPoint(7,7));
		assertFalse(e.containsPoint(10.0001,10));
		assertFalse(e.containsPoint(9,9));

		IRectangularROI b = e.getBounds();
		double side = 15.811388300841898;
		assertEquals(b.getPointX() + side/2, e.getPointX(), ABS_TOL);
		assertEquals(b.getPointY() + side/2, e.getPointY(), ABS_TOL);
		assertTrue(e.containsPoint(e.getPointX(), e.getPointY()));
		assertFalse(e.containsPoint(-20, e.getPointY()));

		EllipticalROI se = new EllipticalROI(9.9, 4.9, Math.PI/4., 0, 0);
		EllipticalROI le = new EllipticalROI(10.1, 5.1, Math.PI/4., 0, 0);
		for (int i = 0; i < POINTS; i++) {
			double a = (i * Math.PI)/POINTS;
			double[] ps = se.getPoint(a);
			assertTrue(e.containsPoint(ps));
			assertTrue(e.isNearOutline(ps, 1));
			assertFalse(e.isNearOutline(ps, 0.01));

			double[] pl = le.getPoint(a);
			assertFalse(e.containsPoint(pl));
			assertTrue(e.isNearOutline(pl, 1));
			assertFalse(e.isNearOutline(pl, 0.01));

			double[] p = e.getPoint(a);
			checkPoint(e.getVerticalIntersectionParameters(p[0]), p, 0, e);
			checkPoint(e.getHorizontalIntersectionParameters(p[1]), p, 1, e);
		}

		assertTrue(e.getVerticalIntersectionParameters(e.getBounds().getEndPoint()[0]+0.1) == null);
		assertTrue(e.getVerticalIntersectionParameters(e.getBounds().getPointRef()[0]-0.1) == null);
		assertTrue(e.getHorizontalIntersectionParameters(e.getBounds().getEndPoint()[1]+0.1) == null);
		assertTrue(e.getHorizontalIntersectionParameters(e.getBounds().getPointRef()[1]-0.1) == null);

		// check a particular line through origin
		double[] p = e.getHorizontalIntersectionParameters(0);
		assertTrue(p != null);
		double ymin = -2e-15;
		assertTrue(e.getPoint(p[0])[1] >= ymin);
		assertTrue(e.getPoint(p[1])[1] >= ymin);
		assertFalse(e.getPoint(p[0]*0.99)[1] >= ymin);
		assertTrue(e.getPoint(p[0]*1.01)[1] >= ymin);
		assertTrue(e.getPoint(p[1]*0.99)[1] >= ymin);
		assertFalse(e.getPoint(p[1]*1.01)[1] >= ymin);

		RectangularROI rect = new RectangularROI(side, 0);
		assertFalse(e.isContainedBy(rect));

		rect.setPoint(-side/2, -side/2);
		assertTrue(e.isContainedBy(rect));
		rect.setLengths(20, 10);
		assertFalse(e.isContainedBy(rect));

		rect.setPoint(-10, -5);
		e.setSemiAxis(1, 5);
		e.setAngleDegrees(0);
		assertTrue(e.isContainedBy(rect));

		e.setAngleDegrees(90);
		assertFalse(e.isContainedBy(rect));

		rect.setPoint(-5, -10);
		assertFalse(e.isContainedBy(rect));

		rect.setLengths(10, 20);
		assertTrue(e.isContainedBy(rect));

		e.setAngleDegrees(45);
		assertFalse(e.isContainedBy(rect));

		rect.setPoint(-10, -10);
		rect.setLengths(20, 20);
		assertTrue(e.isContainedBy(rect));

		double d = Math.sqrt(0.5*(10*10 + 5*5));
		rect.setPoint(-d, -d);
		rect.setLengths(2*d, 2*d);
		assertTrue(e.isContainedBy(rect));

		d *= 0.99;
		rect.setPoint(-d, -d);
		rect.setLengths(2*d, 2*d);
		assertFalse(e.isContainedBy(rect));

		e = new EllipticalROI(10, 5, 0, 0, 0);
		System.out.println("Bounds: " + e.getBounds());
		assertNull(e.findHorizontalIntersections(-6));
		assertNull(e.findHorizontalIntersections(6));
		double[] xi = e.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-10, 10}, xi, ABS_TOL);
		xi = e.findHorizontalIntersections(5);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);

		e.setAngleDegrees(90);
		System.out.println("Bounds: " + e.getBounds());
		assertNull(e.findHorizontalIntersections(-11));
		assertNull(e.findHorizontalIntersections(11));
		xi = e.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-5, 5}, xi, ABS_TOL);
		xi = e.findHorizontalIntersections(10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);

		e.setAngleDegrees(45);
		System.out.println("Bounds: " + e.getBounds());
		assertNull(e.findHorizontalIntersections(-8));
		assertNull(e.findHorizontalIntersections(8));
		xi = e.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-2*Math.sqrt(10), 2*Math.sqrt(10)}, xi, ABS_TOL);

		double y = Math.sqrt(160./3);
		xi = e.findHorizontalIntersections(y*(1 - 1e-15));
		assertArrayEquals(new double[]{y/2, y/2}, xi, 1e-6);
	}

	public void checkPoint(double[] t, double[] p, int i, IParametricROI e) {
		if (t.length == 1)
			assertTrue(Math.abs(e.getPoint(t[0])[i] - p[i]) < 1e-8);
		else
			assertTrue(Math.abs(e.getPoint(t[0])[i] - p[i]) < 1e-8 || Math.abs(e.getPoint(t[1])[i] - p[i]) < 1e-8);
	}

	@Test
	public void testRingROI() {
		RingROI r = new RingROI(0, 0, 5, 10);

		IRectangularROI b = r.getBounds();
		assertTrue(b.containsPoint(r.getPointX(), r.getPointY()));
		assertTrue(b.containsPoint(8, 8));
		assertTrue(b.containsPoint(4, 0));
		
		assertFalse(r.containsPoint(r.getPointX(), r.getPointY()));
		assertFalse(r.containsPoint(8, 8));
		assertTrue(r.containsPoint(4, 4));

		CircularROI sc = new CircularROI(9.9);
		sc.setPoint(r.getPoint());
		CircularROI lc = new CircularROI(10.1);
		lc.setPoint(r.getPoint());
		double ar = Math.PI*2;
		for (int i = 0; i < POINTS; i++) {
			double a = (i * ar)/(POINTS - 1);
			assertTrue(r.containsPoint(sc.getPoint(a)));
			assertTrue(r.isNearOutline(sc.getPoint(a), 1));
			assertFalse(r.isNearOutline(sc.getPoint(a), 0.01));
			assertFalse(r.containsPoint(lc.getPoint(a)));
			assertTrue(r.isNearOutline(lc.getPoint(a), 1));
			assertFalse(r.isNearOutline(lc.getPoint(a), 0.01));
		}

		assertNull(r.findHorizontalIntersections(-11));
		assertNull(r.findHorizontalIntersections(11));
		double[] xi = r.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-10, -5, 5, 10}, xi, ABS_TOL);
		xi = r.findHorizontalIntersections(3);
		assertArrayEquals(new double[]{-Math.sqrt(91), -4, 4, Math.sqrt(91)}, xi, ABS_TOL);
		xi = r.findHorizontalIntersections(5);
		assertArrayEquals(new double[]{-Math.sqrt(75), 0, Math.sqrt(75)}, xi, ABS_TOL);
		xi = r.findHorizontalIntersections(7);
		assertArrayEquals(new double[]{-Math.sqrt(51), Math.sqrt(51)}, xi, ABS_TOL);
		xi = r.findHorizontalIntersections(10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);
	}

	@Test
	public void testSectorROI() {
		SectorROI s = new SectorROI();
		s.setPoint(3,  3);
		s.setRadii(5, 10);
		s.setAnglesDegrees(23, 69);

		IRectangularROI b = s.getBounds();
		assertFalse(b.containsPoint(s.getPointX(), s.getPointY()));
		assertTrue(b.containsPoint(8, 8));

		CircularROI sc = new CircularROI(9.9);
		sc.setPoint(s.getPoint());
		CircularROI lc = new CircularROI(10.1);
		lc.setPoint(s.getPoint());
		double ar = s.getAngle(1) - s.getAngle(0);
		for (int i = 0; i < POINTS; i++) {
			double a = s.getAngle(0) + (i * ar)/(POINTS - 1);
			assertTrue(s.containsPoint(sc.getPoint(a)));
			assertTrue(s.isNearOutline(sc.getPoint(a), 1));
			boolean f = s.isNearOutline(sc.getPoint(a), 0.01);
			if (i > 0 && i < POINTS-1)
				assertFalse(f);
			else
				assertTrue(f);
			assertFalse(s.containsPoint(lc.getPoint(a)));
			assertTrue(s.isNearOutline(lc.getPoint(a), 1));
			assertFalse(s.isNearOutline(lc.getPoint(a), 0.01));
		}

		s = new SectorROI(0, 0, 5, 10, 0, Math.PI);
		assertNull(s.findHorizontalIntersections(-11));
		assertNull(s.findHorizontalIntersections(11));
		double[] xi = s.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-10, -5, 5, 10}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);

		s.setAngles(0.25*Math.PI, 0.75*Math.PI);
		assertNull(s.findHorizontalIntersections(0));
		assertNull(s.findHorizontalIntersections(3));
		xi = s.findHorizontalIntersections(4);
		assertArrayEquals(new double[]{-4, -3, 3, 4}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(6);
		assertArrayEquals(new double[]{-6, 6}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(8);
		assertArrayEquals(new double[]{-6, 6}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);
		assertNull(s.findHorizontalIntersections(-3));
		assertNull(s.findHorizontalIntersections(-4));

		s.setSymmetry(SectorROI.INVERT);
		assertNull(s.findHorizontalIntersections(-3));
		xi = s.findHorizontalIntersections(-4);
		assertArrayEquals(new double[]{-4, -3, 3, 4}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(-6);
		assertArrayEquals(new double[]{-6, 6}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(-8);
		assertArrayEquals(new double[]{-6, 6}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(-10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);

		s.setSymmetry(SectorROI.FULL);
		xi = s.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-10, -5, 5, 10}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(3);
		assertArrayEquals(new double[]{-Math.sqrt(91), -4, 4, Math.sqrt(91)}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(4);
		assertArrayEquals(new double[]{-Math.sqrt(84), -3, 3, Math.sqrt(84)}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(6);
		assertArrayEquals(new double[]{-8, 8}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(8);
		assertArrayEquals(new double[]{-6, 6}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(10);
		assertArrayEquals(new double[]{0}, xi, ABS_TOL);

		s.setSymmetry(SectorROI.NONE);
		s.setAngles(0.75*Math.PI, 2.25*Math.PI);
		xi = s.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-10, -5, 5, 10}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(3);
		assertArrayEquals(new double[]{-Math.sqrt(91), -4, 4, Math.sqrt(91)}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(4);
		assertArrayEquals(new double[]{-Math.sqrt(84), -4, 4, Math.sqrt(84)}, xi, ABS_TOL);
		xi = s.findHorizontalIntersections(6);
		assertArrayEquals(new double[]{-8, -6, 6, 8}, xi, ABS_TOL);
		assertNull(s.findHorizontalIntersections(8));
		assertNull(s.findHorizontalIntersections(10));

	}

	private static final int SIDES = 24;

	@Test
	public void testPolylineROI() {
		PolylineROI p = new PolylineROI();
		double r = 20;
		for (int i = 0; i < SIDES; i++) {
			double a = (2 * i * Math.PI) / SIDES;
			p.insertPoint(r*Math.cos(a), r*Math.sin(a));
		}

		IRectangularROI b = p.getBounds();
		assertEquals(b.getPointX(), -r, ABS_TOL);
		assertEquals(b.getPointY(), -r, ABS_TOL);
		assertEquals(b.getLength(0), 2*r, ABS_TOL);
		assertEquals(b.getLength(1), 2*r, ABS_TOL);

		assertTrue(p.containsPoint(p.getPointX(), p.getPointY()));
		assertFalse(p.containsPoint(0, 0));

		assertTrue(p.isNearOutline(p.getPointRef(), 0.01));
		assertTrue(p.isNearOutline(p.getPointX() - 0.5, p.getPointY() + 1, 2.5));
		assertFalse(p.isNearOutline(p.getPointX() + 2.5, p.getPointY() + 4, 2.5));
	}

	@Test
	public void testPolygonalROI() {
		PolygonalROI p = new PolygonalROI();
		double r = 20;
		for (int i = 0; i < SIDES; i++) {
			double a = (2 * i * Math.PI) / SIDES;
			p.insertPoint(r*Math.cos(a), r*Math.sin(a));
		}

		IRectangularROI b = p.getBounds();
		assertEquals(b.getPointX(), -r, ABS_TOL);
		assertEquals(b.getPointY(), -r, ABS_TOL);
		assertEquals(b.getLength(0), 2*r, ABS_TOL);
		assertEquals(b.getLength(1), 2*r, ABS_TOL);

		assertTrue(p.containsPoint(p.getPointX(), p.getPointY()));
		assertTrue(p.containsPoint(0, 0));
		assertFalse(p.containsPoint(1, r));

		assertTrue(p.isNearOutline(p.getPointRef(), 0.01));
		assertTrue(p.isNearOutline(p.getPointX() - 0.5, p.getPointY() - 1, 2.5));
		assertFalse(p.isNearOutline(p.getPointX() + 2.5, p.getPointY() + 4, 2.5));

		p = new PolygonalROI();
		for (int i = 0; i < 4; i++) {
			double a = (2 * i * Math.PI) / 4;
			p.insertPoint(r*Math.cos(a), r*Math.sin(a));
		}
		assertNull(p.findHorizontalIntersections(-21));
		assertNull(p.findHorizontalIntersections(21));
		double[] xi = p.findHorizontalIntersections(0);
		assertArrayEquals(new double[]{-20, 20}, xi, ABS_TOL);
	}

	@Test
	public void testPolylineROIEqualsCopy() {
		PolylineROI polylineROI = new PolylineROI();
		polylineROI.insertPoint(0, 0);
		polylineROI.equals(polylineROI.copy());
	}

	@Test
	public void testPolylineROIEqualsOtherWithSameStartPoint() {
		PolylineROI polylineROI = new PolylineROI();
		polylineROI.insertPoint(0, 0);
		PolylineROI other = new PolylineROI();
		other.insertPoint(0, 0);
		polylineROI.equals(other);
	}

	@Test
	public void testParabolicROI() {
		ParabolicROI p = new ParabolicROI(3, 0, 0, 0);

		double distance = 5.5;
		double limit = p.getStartParameter(distance);
		for (int i = 0; i < SIDES; i++) {
			double a = (2 * i * Math.PI) / SIDES;
			double[] pt = p.getPoint(a);
			if (a > limit && a < 2* Math.PI - limit) {
				assertTrue(Math.hypot(pt[0], pt[1]) <= distance);
				checkPoint(p.getVerticalIntersectionParameters(pt[0]), pt, 0, p);
				checkPoint(p.getHorizontalIntersectionParameters(pt[1]), pt, 1, p);
			}
		}
	}

	@Test
	public void testHyperbolicROI() {
		HyperbolicROI h = new HyperbolicROI(3, 2, 0, 0, 0);

		double distance = 5.5;
		double limit = h.getStartParameter(distance);
		for (int i = 0; i < SIDES; i++) {
			double a = (2 * i * Math.PI) / SIDES;
			double[] pt = h.getPoint(a);
			if (a > limit && a < 2* Math.PI - limit) {
				assertTrue(Math.hypot(pt[0], pt[1]) <= distance);
				checkPoint(h.getVerticalIntersectionParameters(pt[0]), pt, 0, h);
				checkPoint(h.getHorizontalIntersectionParameters(pt[1]), pt, 1, h);
			}
		}
	}
}
