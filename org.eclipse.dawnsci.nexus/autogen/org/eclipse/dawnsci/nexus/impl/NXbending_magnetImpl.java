/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2016-06-07T11:32:35.177+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Set;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * A bending magnet
 * 
 * @version 1.0
 */
public class NXbending_magnetImpl extends NXobjectImpl implements NXbending_magnet {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_GEOMETRY);

	public NXbending_magnetImpl() {
		super();
	}

	public NXbending_magnetImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXbending_magnet.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_BENDING_MAGNET;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getCritical_energy() {
		return getDataset(NX_CRITICAL_ENERGY);
	}

	@Override
	public double getCritical_energyScalar() {
		return getDouble(NX_CRITICAL_ENERGY);
	}

	@Override
	public DataNode setCritical_energy(IDataset critical_energy) {
		return setDataset(NX_CRITICAL_ENERGY, critical_energy);
	}

	@Override
	public DataNode setCritical_energyScalar(double critical_energy) {
		return setField(NX_CRITICAL_ENERGY, critical_energy);
	}

	@Override
	public IDataset getBending_radius() {
		return getDataset(NX_BENDING_RADIUS);
	}

	@Override
	public double getBending_radiusScalar() {
		return getDouble(NX_BENDING_RADIUS);
	}

	@Override
	public DataNode setBending_radius(IDataset bending_radius) {
		return setDataset(NX_BENDING_RADIUS, bending_radius);
	}

	@Override
	public DataNode setBending_radiusScalar(double bending_radius) {
		return setField(NX_BENDING_RADIUS, bending_radius);
	}

	@Override
	public IDataset getMagnetic_field() {
		return getDataset(NX_MAGNETIC_FIELD);
	}

	@Override
	public double getMagnetic_fieldScalar() {
		return getDouble(NX_MAGNETIC_FIELD);
	}

	@Override
	public DataNode setMagnetic_field(IDataset magnetic_field) {
		return setDataset(NX_MAGNETIC_FIELD, magnetic_field);
	}

	@Override
	public DataNode setMagnetic_fieldScalar(double magnetic_field) {
		return setField(NX_MAGNETIC_FIELD, magnetic_field);
	}

	@Override
	public IDataset getAccepted_photon_beam_divergence() {
		return getDataset(NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE);
	}

	@Override
	public double getAccepted_photon_beam_divergenceScalar() {
		return getDouble(NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE);
	}

	@Override
	public DataNode setAccepted_photon_beam_divergence(IDataset accepted_photon_beam_divergence) {
		return setDataset(NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE, accepted_photon_beam_divergence);
	}

	@Override
	public DataNode setAccepted_photon_beam_divergenceScalar(double accepted_photon_beam_divergence) {
		return setField(NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE, accepted_photon_beam_divergence);
	}

	@Override
	public IDataset getSource_distance_x() {
		return getDataset(NX_SOURCE_DISTANCE_X);
	}

	@Override
	public double getSource_distance_xScalar() {
		return getDouble(NX_SOURCE_DISTANCE_X);
	}

	@Override
	public DataNode setSource_distance_x(IDataset source_distance_x) {
		return setDataset(NX_SOURCE_DISTANCE_X, source_distance_x);
	}

	@Override
	public DataNode setSource_distance_xScalar(double source_distance_x) {
		return setField(NX_SOURCE_DISTANCE_X, source_distance_x);
	}

	@Override
	public IDataset getSource_distance_y() {
		return getDataset(NX_SOURCE_DISTANCE_Y);
	}

	@Override
	public double getSource_distance_yScalar() {
		return getDouble(NX_SOURCE_DISTANCE_Y);
	}

	@Override
	public DataNode setSource_distance_y(IDataset source_distance_y) {
		return setDataset(NX_SOURCE_DISTANCE_Y, source_distance_y);
	}

	@Override
	public DataNode setSource_distance_yScalar(double source_distance_y) {
		return setField(NX_SOURCE_DISTANCE_Y, source_distance_y);
	}

	@Override
	public IDataset getDivergence_x_plus() {
		return getDataset(NX_DIVERGENCE_X_PLUS);
	}

	@Override
	public double getDivergence_x_plusScalar() {
		return getDouble(NX_DIVERGENCE_X_PLUS);
	}

	@Override
	public DataNode setDivergence_x_plus(IDataset divergence_x_plus) {
		return setDataset(NX_DIVERGENCE_X_PLUS, divergence_x_plus);
	}

	@Override
	public DataNode setDivergence_x_plusScalar(double divergence_x_plus) {
		return setField(NX_DIVERGENCE_X_PLUS, divergence_x_plus);
	}

	@Override
	public IDataset getDivergence_x_minus() {
		return getDataset(NX_DIVERGENCE_X_MINUS);
	}

	@Override
	public double getDivergence_x_minusScalar() {
		return getDouble(NX_DIVERGENCE_X_MINUS);
	}

	@Override
	public DataNode setDivergence_x_minus(IDataset divergence_x_minus) {
		return setDataset(NX_DIVERGENCE_X_MINUS, divergence_x_minus);
	}

	@Override
	public DataNode setDivergence_x_minusScalar(double divergence_x_minus) {
		return setField(NX_DIVERGENCE_X_MINUS, divergence_x_minus);
	}

	@Override
	public IDataset getDivergence_y_plus() {
		return getDataset(NX_DIVERGENCE_Y_PLUS);
	}

	@Override
	public double getDivergence_y_plusScalar() {
		return getDouble(NX_DIVERGENCE_Y_PLUS);
	}

	@Override
	public DataNode setDivergence_y_plus(IDataset divergence_y_plus) {
		return setDataset(NX_DIVERGENCE_Y_PLUS, divergence_y_plus);
	}

	@Override
	public DataNode setDivergence_y_plusScalar(double divergence_y_plus) {
		return setField(NX_DIVERGENCE_Y_PLUS, divergence_y_plus);
	}

	@Override
	public IDataset getDivergence_y_minus() {
		return getDataset(NX_DIVERGENCE_Y_MINUS);
	}

	@Override
	public double getDivergence_y_minusScalar() {
		return getDouble(NX_DIVERGENCE_Y_MINUS);
	}

	@Override
	public DataNode setDivergence_y_minus(IDataset divergence_y_minus) {
		return setDataset(NX_DIVERGENCE_Y_MINUS, divergence_y_minus);
	}

	@Override
	public DataNode setDivergence_y_minusScalar(double divergence_y_minus) {
		return setField(NX_DIVERGENCE_Y_MINUS, divergence_y_minus);
	}

	@Override
	public NXdata getSpectrum() {
		return getChild("spectrum", NXdata.class);
	}

	@Override
	public void setSpectrum(NXdata spectrum) {
		putChild("spectrum", spectrum);
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
