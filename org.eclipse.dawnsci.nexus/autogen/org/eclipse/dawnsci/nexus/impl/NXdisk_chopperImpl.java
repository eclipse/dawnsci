/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * A device blocking the beam in a temporal periodic pattern.
 * TODO: need documentation
 * 
 * @version 1.0
 */
public class NXdisk_chopperImpl extends NXobjectImpl implements NXdisk_chopper {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY);

	public NXdisk_chopperImpl() {
		super();
	}

	public NXdisk_chopperImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXdisk_chopper.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_DISK_CHOPPER;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	@Override
	public String getTypeScalar() {
		return getString(NX_TYPE);
	}

	@Override
	public DataNode setType(IDataset type) {
		return setDataset(NX_TYPE, type);
	}

	@Override
	public DataNode setTypeScalar(String type) {
		return setString(NX_TYPE, type);
	}

	@Override
	public IDataset getRotation_speed() {
		return getDataset(NX_ROTATION_SPEED);
	}

	@Override
	public Double getRotation_speedScalar() {
		return getDouble(NX_ROTATION_SPEED);
	}

	@Override
	public DataNode setRotation_speed(IDataset rotation_speed) {
		return setDataset(NX_ROTATION_SPEED, rotation_speed);
	}

	@Override
	public DataNode setRotation_speedScalar(Double rotation_speed) {
		return setField(NX_ROTATION_SPEED, rotation_speed);
	}

	@Override
	public IDataset getSlits() {
		return getDataset(NX_SLITS);
	}

	@Override
	public Long getSlitsScalar() {
		return getLong(NX_SLITS);
	}

	@Override
	public DataNode setSlits(IDataset slits) {
		return setDataset(NX_SLITS, slits);
	}

	@Override
	public DataNode setSlitsScalar(Long slits) {
		return setField(NX_SLITS, slits);
	}

	@Override
	public IDataset getSlit_angle() {
		return getDataset(NX_SLIT_ANGLE);
	}

	@Override
	public Double getSlit_angleScalar() {
		return getDouble(NX_SLIT_ANGLE);
	}

	@Override
	public DataNode setSlit_angle(IDataset slit_angle) {
		return setDataset(NX_SLIT_ANGLE, slit_angle);
	}

	@Override
	public DataNode setSlit_angleScalar(Double slit_angle) {
		return setField(NX_SLIT_ANGLE, slit_angle);
	}

	@Override
	public IDataset getPair_separation() {
		return getDataset(NX_PAIR_SEPARATION);
	}

	@Override
	public Double getPair_separationScalar() {
		return getDouble(NX_PAIR_SEPARATION);
	}

	@Override
	public DataNode setPair_separation(IDataset pair_separation) {
		return setDataset(NX_PAIR_SEPARATION, pair_separation);
	}

	@Override
	public DataNode setPair_separationScalar(Double pair_separation) {
		return setField(NX_PAIR_SEPARATION, pair_separation);
	}

	@Override
	public IDataset getRadius() {
		return getDataset(NX_RADIUS);
	}

	@Override
	public Double getRadiusScalar() {
		return getDouble(NX_RADIUS);
	}

	@Override
	public DataNode setRadius(IDataset radius) {
		return setDataset(NX_RADIUS, radius);
	}

	@Override
	public DataNode setRadiusScalar(Double radius) {
		return setField(NX_RADIUS, radius);
	}

	@Override
	public IDataset getSlit_height() {
		return getDataset(NX_SLIT_HEIGHT);
	}

	@Override
	public Double getSlit_heightScalar() {
		return getDouble(NX_SLIT_HEIGHT);
	}

	@Override
	public DataNode setSlit_height(IDataset slit_height) {
		return setDataset(NX_SLIT_HEIGHT, slit_height);
	}

	@Override
	public DataNode setSlit_heightScalar(Double slit_height) {
		return setField(NX_SLIT_HEIGHT, slit_height);
	}

	@Override
	public IDataset getPhase() {
		return getDataset(NX_PHASE);
	}

	@Override
	public Double getPhaseScalar() {
		return getDouble(NX_PHASE);
	}

	@Override
	public DataNode setPhase(IDataset phase) {
		return setDataset(NX_PHASE, phase);
	}

	@Override
	public DataNode setPhaseScalar(Double phase) {
		return setField(NX_PHASE, phase);
	}

	@Override
	public IDataset getRatio() {
		return getDataset(NX_RATIO);
	}

	@Override
	public Long getRatioScalar() {
		return getLong(NX_RATIO);
	}

	@Override
	public DataNode setRatio(IDataset ratio) {
		return setDataset(NX_RATIO, ratio);
	}

	@Override
	public DataNode setRatioScalar(Long ratio) {
		return setField(NX_RATIO, ratio);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public Double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	@Override
	public DataNode setDistance(IDataset distance) {
		return setDataset(NX_DISTANCE, distance);
	}

	@Override
	public DataNode setDistanceScalar(Double distance) {
		return setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getWavelength_range() {
		return getDataset(NX_WAVELENGTH_RANGE);
	}

	@Override
	public Double getWavelength_rangeScalar() {
		return getDouble(NX_WAVELENGTH_RANGE);
	}

	@Override
	public DataNode setWavelength_range(IDataset wavelength_range) {
		return setDataset(NX_WAVELENGTH_RANGE, wavelength_range);
	}

	@Override
	public DataNode setWavelength_rangeScalar(Double wavelength_range) {
		return setField(NX_WAVELENGTH_RANGE, wavelength_range);
	}

	@Override
	public NXgeometry getGeometry() {
		return getChild("geometry", NXgeometry.class);
	}

	@Override
	public void setGeometry(NXgeometry geometry) {
		putChild("geometry", geometry);
	}

	@Override
	public NXgeometry getGeometry(String name) {
		return getChild(name, NXgeometry.class);
	}

	@Override
	public void setGeometry(String name, NXgeometry geometry) {
		putChild(name, geometry);
	}

	@Override
	public Map<String, NXgeometry> getAllGeometry() {
		return getChildren(NXgeometry.class);
	}
	
	@Override
	public void setAllGeometry(Map<String, NXgeometry> geometry) {
		setChildren(geometry);
	}

}
