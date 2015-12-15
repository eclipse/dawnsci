/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_DATA);

	protected NXmoderatorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXmoderatorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXmoderator.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_MODERATOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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

	@Override
	public double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	public DataNode setDistanceScalar(double distance) {
		return setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
	}

	@Override
	public IDataset getPoison_depth() {
		return getDataset(NX_POISON_DEPTH);
	}

	@Override
	public double getPoison_depthScalar() {
		return getDouble(NX_POISON_DEPTH);
	}

	public DataNode setPoison_depth(IDataset poison_depth) {
		return setDataset(NX_POISON_DEPTH, poison_depth);
	}

	public DataNode setPoison_depthScalar(double poison_depth) {
		return setField(NX_POISON_DEPTH, poison_depth);
	}

	@Override
	public IDataset getCoupled() {
		return getDataset(NX_COUPLED);
	}

	@Override
	public boolean getCoupledScalar() {
		return getBoolean(NX_COUPLED);
	}

	public DataNode setCoupled(IDataset coupled) {
		return setDataset(NX_COUPLED, coupled);
	}

	public DataNode setCoupledScalar(boolean coupled) {
		return setField(NX_COUPLED, coupled);
	}

	@Override
	public IDataset getCoupling_material() {
		return getDataset(NX_COUPLING_MATERIAL);
	}

	@Override
	public String getCoupling_materialScalar() {
		return getString(NX_COUPLING_MATERIAL);
	}

	public DataNode setCoupling_material(IDataset coupling_material) {
		return setDataset(NX_COUPLING_MATERIAL, coupling_material);
	}

	public DataNode setCoupling_materialScalar(String coupling_material) {
		return setString(NX_COUPLING_MATERIAL, coupling_material);
	}

	@Override
	public IDataset getPoison_material() {
		return getDataset(NX_POISON_MATERIAL);
	}

	@Override
	public String getPoison_materialScalar() {
		return getString(NX_POISON_MATERIAL);
	}

	public DataNode setPoison_material(IDataset poison_material) {
		return setDataset(NX_POISON_MATERIAL, poison_material);
	}

	public DataNode setPoison_materialScalar(String poison_material) {
		return setString(NX_POISON_MATERIAL, poison_material);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	@Override
	public double getTemperatureScalar() {
		return getDouble(NX_TEMPERATURE);
	}

	public DataNode setTemperature(IDataset temperature) {
		return setDataset(NX_TEMPERATURE, temperature);
	}

	public DataNode setTemperatureScalar(double temperature) {
		return setField(NX_TEMPERATURE, temperature);
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
