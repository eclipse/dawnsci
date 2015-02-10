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

package org.eclipse.dawnsci.analysis.api.diffraction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.dawnsci.analysis.api.diffraction.DiffractionCrystalEnvironmentEvent.EventType;

/**
 * Container for parameters that a crystal is subject to in a diffraction experiment
 */
public class DiffractionCrystalEnvironment implements Serializable, Cloneable {
	/**
	 * Update this when there are any serious changes to API
	 */
	static final long serialVersionUID = 4306363319254548387L;
	//  TODO add transmission

	private double wavelength;   // in Angstroms
	private double phiStart;     // in degrees
	private double phiRange;     // in degrees
	private double exposureTime; // in seconds
	private double oscGap;       // in degrees
	private boolean fire = true;

	// TODO move controller away from model?
	private transient Set<IDiffractionCrystalEnvironmentListener> diffCrystEnvListeners; 

	/**
	 * @param wavelength in Angstroms
	 */
	public DiffractionCrystalEnvironment(double wavelength) {
		this(wavelength, Double.NaN, Double.NaN, Double.NaN, Double.NaN);
	}

	/**
	 * @param wavelength in Angstroms
	 * @param phiStart in degrees
	 * @param phiRange in degrees
	 * @param exposureTime in seconds
	 */
	public DiffractionCrystalEnvironment(double wavelength, double phiStart, double phiRange, double exposureTime) {
		this(wavelength, phiStart, phiRange, exposureTime, Double.NaN);
	}

	/**
	 * @param wavelength in Angstroms
	 * @param phiStart in degrees
	 * @param phiRange in degrees
	 * @param exposureTime in seconds
	 * @param oscGap in degrees
	 */
	public DiffractionCrystalEnvironment(double wavelength, double phiStart, double phiRange, double exposureTime, double oscGap) {
		this.wavelength = wavelength;
		this.phiStart = phiStart;
		this.phiRange = phiRange;
		this.exposureTime = exposureTime;
		this.oscGap = oscGap;
	}

	/**
	 * null constructor
	 */
	public DiffractionCrystalEnvironment() {
	}

	/*
	 *  @param diffenv The DiffractionCrystalEnvironment to copy
	 */
	public DiffractionCrystalEnvironment(DiffractionCrystalEnvironment diffenv) {
		wavelength = diffenv.getWavelength();
		phiStart = diffenv.getPhiStart();
		phiRange = diffenv.getPhiRange();
		exposureTime = diffenv.getExposureTime();
	}

	public static DiffractionCrystalEnvironment getDefaultDiffractionCrystalEnvironment() {
		double lambda = 0.9;
		double startOmega = 0.0;
		double rangeOmega = 1.0;
		double exposureTime = 1.0;
		double oscGap = 0;
		
		return new DiffractionCrystalEnvironment(lambda, startOmega, rangeOmega, exposureTime, oscGap);
	}
	
	@Override
	public DiffractionCrystalEnvironment clone() {
		return new DiffractionCrystalEnvironment(wavelength, phiStart, phiRange, exposureTime);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(exposureTime);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(phiRange);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(phiStart);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wavelength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(oscGap);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	private boolean equalOrBothNaNs(double a, double b) {
		if (Double.isNaN(a) && Double.isNaN(b))
			return true;
		return a == b;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiffractionCrystalEnvironment other = (DiffractionCrystalEnvironment) obj;
		if (!equalOrBothNaNs(exposureTime, other.exposureTime))
			return false;
		if (!equalOrBothNaNs(phiRange, other.phiRange))
			return false;
		if (!equalOrBothNaNs(phiStart, other.phiStart))
			return false;
		if (!equalOrBothNaNs(wavelength, other.wavelength))
			return false;
		if (!equalOrBothNaNs(oscGap, other.oscGap))
			return false;
		return true;
	}

	/**
	 * @return wavelength in Angstroms
	 */
	public double getWavelength() {
		return wavelength;
	}
	
	/**
	 * Sets the wavelength from energy in keV
	 * @param keV energy
	 */
	public void setWavelengthFromEnergykeV(double keV) {
		// lambda(A) = 10^7 * (h*c/e) / energy(keV)
		this.wavelength = 1./(0.0806554465*keV); // constant from NIST CODATA 2006
		// Tell listeners
		fireDiffractionCrystalEnvironmentListeners(new DiffractionCrystalEnvironmentEvent(this, EventType.WAVELENGTH));
	}

	/**
	 * Set wavelength in Angstroms
	 * @param wavelength
	 */
	public void setWavelength(double wavelength) {
		this.wavelength = wavelength;
		fireDiffractionCrystalEnvironmentListeners(new DiffractionCrystalEnvironmentEvent(this, EventType.WAVELENGTH));
	}
	
	/**
	 * @return the phi value for the start of the image in degrees
	 */
	public double getPhiStart() {
		return phiStart;
	}

	/**
	 * Set the phi value at start of the diffraction image in degrees
	 * @param phiStart 
	 */
	public void setPhiStart(double phiStart) {
		this.phiStart = phiStart;
	}

	/**
	 * @return the phi range of the image in degrees
	 */
	public double getPhiRange() {
		return phiRange;
	}

	/**
	 * Set the phi range in degrees
	 * @param phiRange 
	 */
	public void setPhiRange(double phiRange) {
		this.phiRange = phiRange;
	}
	
	/**
	 * @return Exposure time in seconds
	 */
	public double getExposureTime() {
		return exposureTime;
	}

	/**
	 * Set the exposure time in seconds
	 * @param exposureTime
	 */
	public void setExposureTime(double exposureTime) {
		this.exposureTime = exposureTime;
	}
	
	/**
	 * @return the oscillation gap value of diffraction image in degrees
	 */
	public double getOscGap() {
		return oscGap;
	}

	/**
	 * Set the oscillation gap value of diffraction image in degrees
	 * @param oscGap
	 */
	public void setOscGap(double oscGap) {
		this.oscGap = oscGap;
	}

	public void addDiffractionCrystalEnvironmentListener(IDiffractionCrystalEnvironmentListener l) {
		if (diffCrystEnvListeners==null) 
			diffCrystEnvListeners = new HashSet<IDiffractionCrystalEnvironmentListener>(5);
		diffCrystEnvListeners.add(l);
	}
	/**
	 * Call from dispose of part listening to diffraction crystal environment changing
	 * @param l
	 */
	public void removeDiffractionCrystalEnvironmentListener(IDiffractionCrystalEnvironmentListener l) {
		if (diffCrystEnvListeners==null) 
			return;
		diffCrystEnvListeners.remove(l);
	}
	
	protected void fireDiffractionCrystalEnvironmentListeners(DiffractionCrystalEnvironmentEvent evt) {
		if (diffCrystEnvListeners==null || !fire) 
			return;
		for (IDiffractionCrystalEnvironmentListener l : diffCrystEnvListeners) {
			l.diffractionCrystalEnvironmentChanged(evt);
		}
	}

	public void restore(DiffractionCrystalEnvironment original) {
		fire = false;
		setExposureTime(original.getExposureTime());
		setOscGap(original.getOscGap());
		setPhiRange(original.getPhiRange());
		setPhiStart(original.getPhiStart());
		setWavelength(original.getWavelength());
		fire = true;
	}

	@Override
	public String toString() {
		return "CE: " + wavelength;
	}
}
