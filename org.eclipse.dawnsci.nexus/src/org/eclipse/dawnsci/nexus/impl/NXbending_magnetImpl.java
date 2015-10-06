/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * description for a bending magnet
 * 
 * @version 1.0
 */
public class NXbending_magnetImpl extends NXobjectImpl implements NXbending_magnet {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_CRITICAL_ENERGY = "critical_energy";
	public static final String NX_BENDING_RADIUS = "bending_radius";
	public static final String NX_MAGNETIC_FIELD = "magnetic_field";
	public static final String NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE = "accepted_photon_beam_divergence";
	public static final String NX_SOURCE_DISTANCE_X = "source_distance_x";
	public static final String NX_SOURCE_DISTANCE_Y = "source_distance_y";
	public static final String NX_DIVERGENCE_X_PLUS = "divergence_x_plus";
	public static final String NX_DIVERGENCE_X_MINUS = "divergence_x_minus";
	public static final String NX_DIVERGENCE_Y_PLUS = "divergence_y_plus";
	public static final String NX_DIVERGENCE_Y_MINUS = "divergence_y_minus";

	protected NXbending_magnetImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXbending_magnet.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_BENDING_MAGNET;
	}

	@Override
	public IDataset getCritical_energy() {
		return getDataset(NX_CRITICAL_ENERGY);
	}

	public void setCritical_energy(IDataset critical_energy) {
		setDataset(NX_CRITICAL_ENERGY, critical_energy);
	}

	@Override
	public IDataset getBending_radius() {
		return getDataset(NX_BENDING_RADIUS);
	}

	public void setBending_radius(IDataset bending_radius) {
		setDataset(NX_BENDING_RADIUS, bending_radius);
	}

	@Override
	public IDataset getMagnetic_field() {
		return getDataset(NX_MAGNETIC_FIELD);
	}

	public void setMagnetic_field(IDataset magnetic_field) {
		setDataset(NX_MAGNETIC_FIELD, magnetic_field);
	}

	@Override
	public IDataset getAccepted_photon_beam_divergence() {
		return getDataset(NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE);
	}

	public void setAccepted_photon_beam_divergence(IDataset accepted_photon_beam_divergence) {
		setDataset(NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE, accepted_photon_beam_divergence);
	}

	@Override
	public IDataset getSource_distance_x() {
		return getDataset(NX_SOURCE_DISTANCE_X);
	}

	public void setSource_distance_x(IDataset source_distance_x) {
		setDataset(NX_SOURCE_DISTANCE_X, source_distance_x);
	}

	@Override
	public IDataset getSource_distance_y() {
		return getDataset(NX_SOURCE_DISTANCE_Y);
	}

	public void setSource_distance_y(IDataset source_distance_y) {
		setDataset(NX_SOURCE_DISTANCE_Y, source_distance_y);
	}

	@Override
	public IDataset getDivergence_x_plus() {
		return getDataset(NX_DIVERGENCE_X_PLUS);
	}

	public void setDivergence_x_plus(IDataset divergence_x_plus) {
		setDataset(NX_DIVERGENCE_X_PLUS, divergence_x_plus);
	}

	@Override
	public IDataset getDivergence_x_minus() {
		return getDataset(NX_DIVERGENCE_X_MINUS);
	}

	public void setDivergence_x_minus(IDataset divergence_x_minus) {
		setDataset(NX_DIVERGENCE_X_MINUS, divergence_x_minus);
	}

	@Override
	public IDataset getDivergence_y_plus() {
		return getDataset(NX_DIVERGENCE_Y_PLUS);
	}

	public void setDivergence_y_plus(IDataset divergence_y_plus) {
		setDataset(NX_DIVERGENCE_Y_PLUS, divergence_y_plus);
	}

	@Override
	public IDataset getDivergence_y_minus() {
		return getDataset(NX_DIVERGENCE_Y_MINUS);
	}

	public void setDivergence_y_minus(IDataset divergence_y_minus) {
		setDataset(NX_DIVERGENCE_Y_MINUS, divergence_y_minus);
	}

	@Override
	public NXdata getSpectrum() {
		return getChild("spectrum", NXdata.class);
	}

	public void setSpectrum(NXdata spectrum) {
		putChild("spectrum", spectrum);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}

	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

}
