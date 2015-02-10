/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.dataset.roi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.dawnsci.analysis.dataset.coords.SectorCoords;

/**
 * Class for sector region of interest
 */
public class SectorROI extends RingROI implements Serializable {
	protected double ang[]; // angles in radians
	protected boolean combineSymmetry; // combine symmetry option for profile (where appropriate)

	protected int symmetry; // symmetry

	/**
	 * No operation
	 */
	public static final int NONE = 0;
	/**
	 * Full circular sector
	 */
	public static final int FULL = 1;
	/**
	 * Reflect sector in y-axis (left/right)
	 */
	public static final int XREFLECT = 2;
	/**
	 * Reflect sector in x-axis (up/down)
	 */
	public static final int YREFLECT = 3;
	/**
	 * Rotate sector by +90 degrees
	 */
	public static final int CNINETY = 4;
	/**
	 * Rotate sector by -90 degrees
	 */
	public static final int ACNINETY = 5;
	/**
	 * Invert sector through centre
	 */
	public static final int INVERT = 6;

	private static Map<Integer, String> symmetryText = new HashMap<Integer, String>();
	static {
		symmetryText.put(SectorROI.NONE,     "None");
		symmetryText.put(SectorROI.FULL,     "Full");
		symmetryText.put(SectorROI.XREFLECT, "L/R");
		symmetryText.put(SectorROI.YREFLECT, "U/D");
		symmetryText.put(SectorROI.CNINETY,  "+90");
		symmetryText.put(SectorROI.ACNINETY, "-90");
		symmetryText.put(SectorROI.INVERT,   "Invert");
	}
	public static Map<Integer, String> getSymmetriesPossible() {
		return symmetryText;
	}

	/**
	 * @param symmetry The symmetry to set.
	 */
	public void setSymmetry(int symmetry) {
		this.symmetry = symmetry;
		setDirty();
	}

	@Override
	protected void setDirty() {
		super.setDirty();
		symAng = null;
	}

	/**
	 * @param symmetry The symmetry to set.
	 */
	public static int getSymmetry(String symmetry) {
		if (symmetryText.containsValue(symmetry))
			for (int key : symmetryText.keySet())
				if (symmetry.equals(symmetryText.get(key))) {
					return key;
				}
		
		return SectorROI.NONE;
	}
	
	/**
	 * @return Returns the symmetry.
	 */
	public int getSymmetry() {
		return symmetry;
	}

	/**
	 * 
	 */
	public SectorROI() {
		this(30., 120., Math.PI*0.25, Math.PI*2./3.);
	}

	private final static double TWO_PI = 2.0 * Math.PI;
	private final static double HALF_PI = 0.5 * Math.PI;

	/**
	 * Create an annulus
	 * @param sr 
	 * @param er
	 */
	public SectorROI(double sr, double er) {
		this(0., 0., sr, er, 0, TWO_PI, 1.0, false, SectorROI.FULL);
	}

	/**
	 * @param sr 
	 * @param er
	 * @param sp
	 * @param ep
	 */
	public SectorROI(double sr, double er, double sp, double ep) {
		this(0., 0., sr, er, sp, ep);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param sr
	 * @param er
	 * @param sp
	 * @param ep
	 */
	public SectorROI(double ptx, double pty, double sr, double er, double sp, double ep) {
		this(ptx, pty, sr, er, sp, ep, 1.0, true);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param sr
	 * @param er
	 * @param sp
	 * @param ep
	 * @param dpp
	 * @param clip 
	 */
	public SectorROI(double ptx, double pty, double sr, double er, double sp, double ep, double dpp, boolean clip) {
		this(ptx, pty, sr, er, sp, ep, dpp, clip, SectorROI.NONE);
	}

	/**
	 * @param ptx
	 * @param pty
	 * @param sr
	 * @param er
	 * @param sp
	 * @param ep
	 * @param dpp
	 * @param clip 
	 * @param sym 
	 */
	public SectorROI(double ptx, double pty, double sr, double er, double sp, double ep, double dpp, boolean clip, int sym) {
		super(ptx, pty, sr, er, dpp, clip);
		ang = new double[] {sp, ep};
		symmetry = sym;
		combineSymmetry = false;
		checkAngles(ang);
	}

	/**
	 * Set angles
	 * @param startAngle
	 * @param endAngle
	 */
	public void setAngles(double startAngle, double endAngle) {
		ang[0] = startAngle;
		ang[1] = endAngle;
		checkAngles(ang);
		setDirty();
	}

	/**
	 * @param angles The angles to set
	 */
	public void setAngles(double angles[]) {
		setAngles(angles[0], angles[1]);
	}

	/**
	 * @return Returns the angles in degrees
	 */
	public double[] getAnglesDegrees() {
		double[] angles = new double[] { Math.toDegrees(ang[0]), Math.toDegrees(ang[1]) };
		return angles;
	}

	/**
	 * @param index 
	 * @return Returns the angles in degrees
	 */
	public double getAngleDegrees(int index) {
		return Math.toDegrees(ang[index]);
	}

	/**
	 * @param angles The angles in degrees to set
	 */
	public void setAnglesDegrees(double angles[]) {
		setAnglesDegrees(angles[0], angles[1]);
	}

	/**
	 * For Jython
	 * @param angles The angles in degrees to set
	 */
	public void setAnglesdegrees(double[] angles) {
		setAnglesDegrees(angles);
	}

	/**
	 * @param startAngle in degrees
	 * @param endAngle in degrees
	 */
	public void setAnglesDegrees(double startAngle, double endAngle) {
		setAngles(Math.toRadians(startAngle), Math.toRadians(endAngle));
	}

	/**
	 * @return Returns reference to the angles
	 */
	public double[] getAngles() {
		return ang;
	}

	/**
	 * @param index 
	 * @return Returns the angles
	 */
	public double getAngle(int index) {
		return ang[index];
	}

	/**
	 * Add an offset to both angle
	 * 
	 * @param angle
	 */
	public void addAngles(double angle) {
		for (int i = 0; i < 2; i++) {
			ang[i] += angle;
		}
		if (ang[0] > TWO_PI) {
			ang[0] -= TWO_PI;
			ang[1] -= TWO_PI;
		}
		if (ang[0] < 0) {
			ang[0] += TWO_PI;
			ang[1] += TWO_PI;
		}
		setDirty();
	}

	/**
	 * Add an offset to an angle
	 * @param index 
	 * @param angle
	 */
	public void addAngle(int index, double angle) {
		ang[index] += angle;
		checkAngles(ang);
		setDirty();
	}

	/**
	 * Make sure angles lie in permitted ranges:
	 *  0 <= ang0 <= 2*pi, 0 <= ang1 <= 4*pi
	 *  0 <= ang1 - ang0 <= 2*pi
	 */
	protected static void checkAngles(double[] angles) {
		// sort out relative values
		double a = angles[0];
		while (a >= angles[1]) {
			angles[1] += TWO_PI;
		}

		a += TWO_PI;
		while (a < angles[1]) {
			angles[1] -= TWO_PI;
		}
		
		// place correctly in absolute terms
		while (angles[0] < 0) {
			angles[0] += TWO_PI;
			angles[1] += TWO_PI;
		}
		
		while (angles[0] > TWO_PI) {
			angles[0] -= TWO_PI;
			angles[1] -= TWO_PI;
		}
	}

	private static boolean isAngleInSector(double[] limits, double angle) {
		if (angle < 0) {
			angle += TWO_PI;
		}
		if (limits[0] <= angle) {
			return angle <= limits[1];
		}

		return angle + TWO_PI <= limits[1];
	}

	@Override
	public SectorROI copy() {
		SectorROI c = new SectorROI(spt[0], spt[1], rad[0], rad[1], ang[0], ang[1], dpp, clippingCompensation, symmetry);
		c.setCombineSymmetry(combineSymmetry);
		c.name = name;
		c.plot = plot;
		return c;
	}
	
	/**
	 * @param sym 
	 * @return true if given symmetry is okay with angles
	 */
	public boolean checkSymmetry(int sym) {
		boolean isOK = false;

		switch(sym) {
		case NONE: case FULL:
			isOK = true;
			break;
		case XREFLECT:
			if (0 <= ang[0] && ang[0] < HALF_PI && ang[0] <= ang[1] && ang[1] <= HALF_PI)
				isOK = true;
			else if (HALF_PI <= ang[0] && ang[0] <= 3*HALF_PI && ang[0] <= ang[1] && ang[1] <= 3*HALF_PI)
				isOK = true;
			else if (3*HALF_PI <= ang[0] && ang[0] <= TWO_PI && ang[0] <= ang[1] && ang[1] <= 5*HALF_PI)
				isOK = true;
			break;
		case YREFLECT:
			if (0 <= ang[0] && ang[0] < Math.PI && ang[0] <= ang[1] && ang[1] <= Math.PI)
				isOK = true;
			else if (Math.PI <= ang[0] && ang[0] <= TWO_PI && ang[0] <= ang[1] && ang[1] <= TWO_PI)
				isOK = true;
			break;
		case CNINETY:
			if (ang[1] <= ang[0] + HALF_PI)
				isOK = true;
			break;
		case ACNINETY:
			if (ang[0] >= ang[1] - HALF_PI)
				isOK = true;
			break;
		case INVERT:
			if (ang[1] <= ang[0] + HALF_PI)
				isOK = true;
			break;
		}
		return isOK;
	}

	/**
	 * @return angles from symmetry operations
	 */
	public double[] getSymmetryAngles() {
		double[] nang = new double[] {0, TWO_PI};

		switch (symmetry) {
		case XREFLECT:
			// add in x reflected integral
			nang[0] = Math.PI - ang[1];
			nang[1] = Math.PI - ang[0];
			break;
		case YREFLECT:
			// add in y reflected integral
			nang[0] = TWO_PI - ang[1];
			nang[1] = TWO_PI - ang[0];
			break;
		case CNINETY:
			// add in +90 rotated integral
			nang[0] = ang[0] + HALF_PI;
			nang[1] = ang[1] + HALF_PI;
			break;
		case ACNINETY:
			// add in -90 rotated integral
			nang[0] = ang[0] - HALF_PI;
			nang[1] = ang[1] - HALF_PI;
			break;
		case INVERT:
			// add in inverted integral
			nang[0] = ang[0] + Math.PI;
			nang[1] = ang[1] + Math.PI;
			break;
		case FULL:
			break;
		default:
		case NONE:
			return null;
		}

		return nang;
	}

	/**
	 * @return text for symmetry setting
	 */
	public String getSymmetryText() {
		return getSymmetryText(symmetry);
	}

	/**
	 * @return text for symmetry setting
	 */
	public static String getSymmetryText(int sym) {
		if (symmetryText.containsKey(sym))
			return symmetryText.get(sym);

		return "N";
	}

	/**
	 * @param combineSymmetry The combineSymmetry to set.
	 */
	public void setCombineSymmetry(boolean combineSymmetry) {
		this.combineSymmetry = combineSymmetry;
	}

	/**
	 * @return Returns the combineSymmetry.
	 */
	public boolean isCombineSymmetry() {
		return combineSymmetry;
	}

	/**
	 * @return Returns true if ROI has separate regions (determined by symmetry and combine flag)
	 */
	public boolean hasSeparateRegions() {
		return !(symmetry == NONE || symmetry == FULL || combineSymmetry); 
	}

	@Override
	public RectangularROI getBounds() {
		if (bounds == null) {
			double[] max = SectorCoords.convertFromPolarRadians(rad[0], ang[0]);
			double[] min = max.clone();

			ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[0], ang[1]));

			ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[1], ang[1]));

			ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[1], ang[0]));

			int beg = (int) Math.ceil(ang[0] / HALF_PI);
			int end = (int) Math.floor(ang[1] / HALF_PI);
			for (; beg <= end; beg++) { // angle range spans multiples of pi/2
				ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[1], beg * HALF_PI));
			}

			symAng = getSymmetryAngles();
			if (symAng != null) {
				if (symAng[0] > symAng[1]) { // angles may not be ordered
					double t = symAng[1];
					symAng[1] = symAng[0];
					symAng[0] = t;
				}
				checkAngles(symAng); // make angles lie in [0, 2pi)

				ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[0], symAng[0]));

				ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[0], symAng[1]));

				ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[1], symAng[1]));

				ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[1], symAng[0]));

				beg = (int) Math.ceil(symAng[0] / HALF_PI);
				end = (int) Math.floor(symAng[1] / HALF_PI);
				for (; beg <= end; beg++) { // angle range spans multiples of pi/2
					ROIUtils.updateMaxMin(max, min, SectorCoords.convertFromPolarRadians(rad[1], beg * HALF_PI));
				}
			}

			bounds = new RectangularROI();
			bounds.setPoint(min[0] + spt[0], min[1] + spt[1]);
			bounds.setLengths(max[0] - min[0], max[1] - min[1]);
		}
		return bounds;
	}

	private transient double[] symAng = null;

	@Override
	public boolean containsPoint(double x, double y) {
		x -= spt[0];
		y -= spt[1];

		double[] pol = SectorCoords.convertFromCartesianToPolarRadians(x, y);
		double r = pol[0];
		if (r < rad[0] || r > rad[1])
			return false;

		double p = pol[1];
		if (p >= ang[0] && p <= ang[1])
			return true;
		if (ang[1] > TWO_PI && p + TWO_PI < ang[1]) // angle domain straddles branch cut
			return true;

		if (bounds == null) { // calculate symmetry angles
			getBounds();
		}
		if (symAng == null)
			return false;

		if (symAng[0] > symAng[1]) { // angles may not be ordered
			double t = symAng[1];
			symAng[1] = symAng[0];
			symAng[0] = t;
		}
		checkAngles(symAng); // make angles lie in [0, 2pi)
		if (p >= symAng[0] && p <= symAng[1])
			return true;
		return symAng[1] > TWO_PI && p + TWO_PI < symAng[1];
	}

	@Override
	public boolean isNearOutline(double x, double y, double distance) {
		x -= spt[0];
		y -= spt[1];

		double[] pol = SectorCoords.convertFromCartesianToPolarRadians(x, y);
		double r = pol[0];
		double p = pol[1];
		if (p >= ang[0] && p <= ang[1]) { // near arcs
			if (Math.abs(r - rad[0]) <= distance || Math.abs(r - rad[1]) <= distance)
				return true;
		}

		// check radials
		double[] pt = SectorCoords.convertFromPolarRadians(rad[0],  ang[0]);
		double px = pt[0];
		double py = pt[1];
		pt = SectorCoords.convertFromPolarRadians(rad[1],  ang[0]);
		if (ROIUtils.isNearSegment(pt[0] - px, pt[1] - py, x - px, y - py, distance))
			return true;

		pt = SectorCoords.convertFromPolarRadians(rad[0],  ang[1]);
		px = pt[0];
		py = pt[1];
		pt = SectorCoords.convertFromPolarRadians(rad[1],  ang[1]);
		return ROIUtils.isNearSegment(pt[0] - px, pt[1] - py, x - px, y - py, distance);
	}

	@Override
	public String toString() {
		return super.toString() + String.format("point=%s, radii=%s, angles=[%g, %g], symmetry=%s", Arrays.toString(spt), Arrays.toString(rad), getAngleDegrees(0), getAngleDegrees(1), getSymmetryText());
	}

	protected static double[] calculateArcIntersections(final double[] angles, final double xc, final double yc, final double r) {
		if (yc < -r || yc > r)
			return null;

		if (yc == -r) {
			return isAngleInSector(angles, 1.5*Math.PI) ? new double[] {xc} : null;
		} else if (yc == r) {
			return isAngleInSector(angles, HALF_PI) ? new double[] {xc} : null;
		}

		double x = Math.sqrt(r*r - yc*yc);
		double a = Math.atan2(yc, x);
		if (isAngleInSector(angles, a)) {
			return isAngleInSector(angles, Math.PI - a) ? new double[] {xc - x, xc + x} : new double[] {xc + x};
		}

		return isAngleInSector(angles, Math.PI - a) ? new double[] {xc - x} : null;
	}

	/**
	 * Get relative point on circle at given angle
	 * @param rad
	 * @param angle in radians
	 * @return point with relative y-values
	 */
	double[] getRelativePoint(double rad, double angle) {
		return new double[] { spt[0] + rad*Math.cos(angle), 
				rad*Math.sin(angle) };
	}

	private void findHorizontalIntersections(Set<Double> values, double[] angles, double y) {
		double[] xi;

		xi = calculateArcIntersections(angles, spt[0], y, rad[1]);
		if (xi != null) {
			for (double x : xi)
				values.add(x);
		}

		xi = calculateArcIntersections(angles, spt[0], y, rad[0]);
		if (xi != null) {
			for (double x : xi)
				values.add(x);
		}

		if (angles[1] - angles[0] != TWO_PI) {
			xi = ROIUtils.findYIntersection(getRelativePoint(rad[0],  angles[0]), getRelativePoint(rad[1],  angles[0]), y);
			if (xi != null) {
				for (double x : xi)
					values.add(x);
			}
	
			xi = ROIUtils.findYIntersection(getRelativePoint(rad[0],  angles[1]), getRelativePoint(rad[1],  angles[1]), y);
			if (xi != null) {
				for (double x : xi)
					values.add(x);
			}
		}
		
	}

	@Override
	public double[] findHorizontalIntersections(double y) {
		y -= spt[1];

		double[] xi;
		Set<Double> values = new TreeSet<Double>();
		if (symmetry != FULL)
			findHorizontalIntersections(values, ang, y);

		if (symmetry != NONE) {
			if (symAng == null) {
				symAng = getSymmetryAngles();
			}
			findHorizontalIntersections(values, symAng, y);
		}

		if (values.size() == 0)
			return null;

		xi = new double[values.size()];
		int i = 0;
		for (Double d : values) {
			xi[i++] = d;
		}

		return xi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(ang);
		result = prime * result + (combineSymmetry ? 1231 : 1237);
		result = prime * result + symmetry;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;

		SectorROI other = (SectorROI) obj;
		if (!Arrays.equals(ang, other.ang))
			return false;
		if (combineSymmetry != other.combineSymmetry)
			return false;
		if (symmetry != other.symmetry)
			return false;
		return true;
	}
}
