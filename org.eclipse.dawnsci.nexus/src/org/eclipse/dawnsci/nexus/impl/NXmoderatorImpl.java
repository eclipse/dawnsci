/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This is the description for a general moderator
 * 
 * @version 1.0
 */
public class NXmoderatorImpl extends NXobjectImpl implements NXmoderator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DISTANCE = "distance";
	public static final String NX_TYPE = "type";
	public static final String NX_POISON_DEPTH = "poison_depth";
	public static final String NX_COUPLED = "coupled";
	public static final String NX_COUPLING_MATERIAL = "coupling_material";
	public static final String NX_POISON_MATERIAL = "poison_material";
	public static final String NX_TEMPERATURE = "temperature";

	protected NXmoderatorImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmoderator.class;
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

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getPoison_depth() {
		return getDataset(NX_POISON_DEPTH);
	}

	public void setPoison_depth(IDataset poison_depth) {
		setDataset(NX_POISON_DEPTH, poison_depth);
	}

	@Override
	public IDataset getCoupled() {
		return getDataset(NX_COUPLED);
	}

	public void setCoupled(IDataset coupled) {
		setDataset(NX_COUPLED, coupled);
	}

	@Override
	public IDataset getCoupling_material() {
		return getDataset(NX_COUPLING_MATERIAL);
	}

	public void setCoupling_material(IDataset coupling_material) {
		setDataset(NX_COUPLING_MATERIAL, coupling_material);
	}

	@Override
	public IDataset getPoison_material() {
		return getDataset(NX_POISON_MATERIAL);
	}

	public void setPoison_material(IDataset poison_material) {
		setDataset(NX_POISON_MATERIAL, poison_material);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	public void setTemperature(IDataset temperature) {
		setDataset(NX_TEMPERATURE, temperature);
	}

	@Override
	public NXlog getTemperature_log() {
		return getChild("temperature_log", NXlog.class);
	}

	public void setTemperature_log(NXlog temperature_log) {
		putChild("temperature_log", temperature_log);
	}

	@Override
	public NXdata getPulse_shape() {
		return getChild("pulse_shape", NXdata.class);
	}

	public void setPulse_shape(NXdata pulse_shape) {
		putChild("pulse_shape", pulse_shape);
	}

}
