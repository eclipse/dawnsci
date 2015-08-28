/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-08-28T15:05:14.853+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a beamline collimator.
 * 
 * @version 1.0
 */
public class NXcollimatorImpl extends NXobjectImpl implements NXcollimator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_SOLLER_ANGLE = "soller_angle";
	public static final String NX_DIVERGENCE_X = "divergence_x";
	public static final String NX_DIVERGENCE_Y = "divergence_y";
	public static final String NX_FREQUENCY = "frequency";
	public static final String NX_BLADE_THICKNESS = "blade_thickness";
	public static final String NX_BLADE_SPACING = "blade_spacing";
	public static final String NX_ABSORBING_MATERIAL = "absorbing_material";
	public static final String NX_TRANSMITTING_MATERIAL = "transmitting_material";

	protected NXcollimatorImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcollimator.class;
	}

	@Override
	public NXgeometry getGeometry() {
		return getFirstChild(NXgeometry.class);
	}

	public void setGeometry(NXgeometry geometry) {
		putChild(geometry);
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

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getSoller_angle() {
		return getDataset(NX_SOLLER_ANGLE);
	}

	public void setSoller_angle(IDataset soller_angle) {
		setDataset(NX_SOLLER_ANGLE, soller_angle);
	}

	@Override
	public IDataset getDivergence_x() {
		return getDataset(NX_DIVERGENCE_X);
	}

	public void setDivergence_x(IDataset divergence_x) {
		setDataset(NX_DIVERGENCE_X, divergence_x);
	}

	@Override
	public IDataset getDivergence_y() {
		return getDataset(NX_DIVERGENCE_Y);
	}

	public void setDivergence_y(IDataset divergence_y) {
		setDataset(NX_DIVERGENCE_Y, divergence_y);
	}

	@Override
	public IDataset getFrequency() {
		return getDataset(NX_FREQUENCY);
	}

	public void setFrequency(IDataset frequency) {
		setDataset(NX_FREQUENCY, frequency);
	}

	@Override
	public NXlog getFrequency_log() {
		return getFirstChild(NXlog.class);
	}

	public void setFrequency_log(NXlog frequency_log) {
		putChild(frequency_log);
	}

	@Override
	public IDataset getBlade_thickness() {
		return getDataset(NX_BLADE_THICKNESS);
	}

	public void setBlade_thickness(IDataset blade_thickness) {
		setDataset(NX_BLADE_THICKNESS, blade_thickness);
	}

	@Override
	public IDataset getBlade_spacing() {
		return getDataset(NX_BLADE_SPACING);
	}

	public void setBlade_spacing(IDataset blade_spacing) {
		setDataset(NX_BLADE_SPACING, blade_spacing);
	}

	@Override
	public IDataset getAbsorbing_material() {
		return getDataset(NX_ABSORBING_MATERIAL);
	}

	public void setAbsorbing_material(IDataset absorbing_material) {
		setDataset(NX_ABSORBING_MATERIAL, absorbing_material);
	}

	@Override
	public IDataset getTransmitting_material() {
		return getDataset(NX_TRANSMITTING_MATERIAL);
	}

	public void setTransmitting_material(IDataset transmitting_material) {
		setDataset(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

}
