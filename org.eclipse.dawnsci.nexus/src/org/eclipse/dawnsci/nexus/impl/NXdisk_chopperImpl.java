/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * TODO: need documentation
 * 
 * @version 1.0
 */
public class NXdisk_chopperImpl extends NXobjectImpl implements NXdisk_chopper {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_TYPE = "type";
	public static final String NX_ROTATION_SPEED = "rotation_speed";
	public static final String NX_SLITS = "slits";
	public static final String NX_SLIT_ANGLE = "slit_angle";
	public static final String NX_PAIR_SEPARATION = "pair_separation";
	public static final String NX_RADIUS = "radius";
	public static final String NX_SLIT_HEIGHT = "slit_height";
	public static final String NX_PHASE = "phase";
	public static final String NX_RATIO = "ratio";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_WAVELENGTH_RANGE = "wavelength_range";

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY);

	protected NXdisk_chopperImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXdisk_chopperImpl(final long oid) {
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

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	public void setTypeScalar(String type) {
		setString(NX_TYPE, type);
	}

	@Override
	public IDataset getRotation_speed() {
		return getDataset(NX_ROTATION_SPEED);
	}

	@Override
	public double getRotation_speedScalar() {
		return getDouble(NX_ROTATION_SPEED);
	}

	public void setRotation_speed(IDataset rotation_speed) {
		setDataset(NX_ROTATION_SPEED, rotation_speed);
	}

	public void setRotation_speedScalar(double rotation_speed) {
		setField(NX_ROTATION_SPEED, rotation_speed);
	}

	@Override
	public IDataset getSlits() {
		return getDataset(NX_SLITS);
	}

	@Override
	public long getSlitsScalar() {
		return getLong(NX_SLITS);
	}

	public void setSlits(IDataset slits) {
		setDataset(NX_SLITS, slits);
	}

	public void setSlitsScalar(long slits) {
		setField(NX_SLITS, slits);
	}

	@Override
	public IDataset getSlit_angle() {
		return getDataset(NX_SLIT_ANGLE);
	}

	@Override
	public double getSlit_angleScalar() {
		return getDouble(NX_SLIT_ANGLE);
	}

	public void setSlit_angle(IDataset slit_angle) {
		setDataset(NX_SLIT_ANGLE, slit_angle);
	}

	public void setSlit_angleScalar(double slit_angle) {
		setField(NX_SLIT_ANGLE, slit_angle);
	}

	@Override
	public IDataset getPair_separation() {
		return getDataset(NX_PAIR_SEPARATION);
	}

	@Override
	public double getPair_separationScalar() {
		return getDouble(NX_PAIR_SEPARATION);
	}

	public void setPair_separation(IDataset pair_separation) {
		setDataset(NX_PAIR_SEPARATION, pair_separation);
	}

	public void setPair_separationScalar(double pair_separation) {
		setField(NX_PAIR_SEPARATION, pair_separation);
	}

	@Override
	public IDataset getRadius() {
		return getDataset(NX_RADIUS);
	}

	@Override
	public double getRadiusScalar() {
		return getDouble(NX_RADIUS);
	}

	public void setRadius(IDataset radius) {
		setDataset(NX_RADIUS, radius);
	}

	public void setRadiusScalar(double radius) {
		setField(NX_RADIUS, radius);
	}

	@Override
	public IDataset getSlit_height() {
		return getDataset(NX_SLIT_HEIGHT);
	}

	@Override
	public double getSlit_heightScalar() {
		return getDouble(NX_SLIT_HEIGHT);
	}

	public void setSlit_height(IDataset slit_height) {
		setDataset(NX_SLIT_HEIGHT, slit_height);
	}

	public void setSlit_heightScalar(double slit_height) {
		setField(NX_SLIT_HEIGHT, slit_height);
	}

	@Override
	public IDataset getPhase() {
		return getDataset(NX_PHASE);
	}

	@Override
	public double getPhaseScalar() {
		return getDouble(NX_PHASE);
	}

	public void setPhase(IDataset phase) {
		setDataset(NX_PHASE, phase);
	}

	public void setPhaseScalar(double phase) {
		setField(NX_PHASE, phase);
	}

	@Override
	public IDataset getRatio() {
		return getDataset(NX_RATIO);
	}

	@Override
	public long getRatioScalar() {
		return getLong(NX_RATIO);
	}

	public void setRatio(IDataset ratio) {
		setDataset(NX_RATIO, ratio);
	}

	public void setRatioScalar(long ratio) {
		setField(NX_RATIO, ratio);
	}

	@Override
	public IDataset getDistance() {
		return getDataset(NX_DISTANCE);
	}

	@Override
	public double getDistanceScalar() {
		return getDouble(NX_DISTANCE);
	}

	public void setDistance(IDataset distance) {
		setDataset(NX_DISTANCE, distance);
	}

	public void setDistanceScalar(double distance) {
		setField(NX_DISTANCE, distance);
	}

	@Override
	public IDataset getWavelength_range() {
		return getDataset(NX_WAVELENGTH_RANGE);
	}

	@Override
	public double getWavelength_rangeScalar() {
		return getDouble(NX_WAVELENGTH_RANGE);
	}

	public void setWavelength_range(IDataset wavelength_range) {
		setDataset(NX_WAVELENGTH_RANGE, wavelength_range);
	}

	public void setWavelength_rangeScalar(double wavelength_range) {
		setField(NX_WAVELENGTH_RANGE, wavelength_range);
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
