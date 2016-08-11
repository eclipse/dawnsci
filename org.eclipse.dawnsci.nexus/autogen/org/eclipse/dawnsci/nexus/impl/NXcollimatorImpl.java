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
 * A beamline collimator.
 * 
 * @version 1.0
 */
public class NXcollimatorImpl extends NXobjectImpl implements NXcollimator {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_LOG);

	public NXcollimatorImpl() {
		super();
	}

	public NXcollimatorImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcollimator.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_COLLIMATOR;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	public IDataset getSoller_angle() {
		return getDataset(NX_SOLLER_ANGLE);
	}

	@Override
	public double getSoller_angleScalar() {
		return getDouble(NX_SOLLER_ANGLE);
	}

	@Override
	public DataNode setSoller_angle(IDataset soller_angle) {
		return setDataset(NX_SOLLER_ANGLE, soller_angle);
	}

	@Override
	public DataNode setSoller_angleScalar(double soller_angle) {
		return setField(NX_SOLLER_ANGLE, soller_angle);
	}

	@Override
	public IDataset getDivergence_x() {
		return getDataset(NX_DIVERGENCE_X);
	}

	@Override
	public double getDivergence_xScalar() {
		return getDouble(NX_DIVERGENCE_X);
	}

	@Override
	public DataNode setDivergence_x(IDataset divergence_x) {
		return setDataset(NX_DIVERGENCE_X, divergence_x);
	}

	@Override
	public DataNode setDivergence_xScalar(double divergence_x) {
		return setField(NX_DIVERGENCE_X, divergence_x);
	}

	@Override
	public IDataset getDivergence_y() {
		return getDataset(NX_DIVERGENCE_Y);
	}

	@Override
	public double getDivergence_yScalar() {
		return getDouble(NX_DIVERGENCE_Y);
	}

	@Override
	public DataNode setDivergence_y(IDataset divergence_y) {
		return setDataset(NX_DIVERGENCE_Y, divergence_y);
	}

	@Override
	public DataNode setDivergence_yScalar(double divergence_y) {
		return setField(NX_DIVERGENCE_Y, divergence_y);
	}

	@Override
	public IDataset getFrequency() {
		return getDataset(NX_FREQUENCY);
	}

	@Override
	public double getFrequencyScalar() {
		return getDouble(NX_FREQUENCY);
	}

	@Override
	public DataNode setFrequency(IDataset frequency) {
		return setDataset(NX_FREQUENCY, frequency);
	}

	@Override
	public DataNode setFrequencyScalar(double frequency) {
		return setField(NX_FREQUENCY, frequency);
	}

	@Override
	public NXlog getFrequency_log() {
		return getChild("frequency_log", NXlog.class);
	}

	@Override
	public void setFrequency_log(NXlog frequency_log) {
		putChild("frequency_log", frequency_log);
	}

	@Override
	public IDataset getBlade_thickness() {
		return getDataset(NX_BLADE_THICKNESS);
	}

	@Override
	public double getBlade_thicknessScalar() {
		return getDouble(NX_BLADE_THICKNESS);
	}

	@Override
	public DataNode setBlade_thickness(IDataset blade_thickness) {
		return setDataset(NX_BLADE_THICKNESS, blade_thickness);
	}

	@Override
	public DataNode setBlade_thicknessScalar(double blade_thickness) {
		return setField(NX_BLADE_THICKNESS, blade_thickness);
	}

	@Override
	public IDataset getBlade_spacing() {
		return getDataset(NX_BLADE_SPACING);
	}

	@Override
	public double getBlade_spacingScalar() {
		return getDouble(NX_BLADE_SPACING);
	}

	@Override
	public DataNode setBlade_spacing(IDataset blade_spacing) {
		return setDataset(NX_BLADE_SPACING, blade_spacing);
	}

	@Override
	public DataNode setBlade_spacingScalar(double blade_spacing) {
		return setField(NX_BLADE_SPACING, blade_spacing);
	}

	@Override
	public IDataset getAbsorbing_material() {
		return getDataset(NX_ABSORBING_MATERIAL);
	}

	@Override
	public String getAbsorbing_materialScalar() {
		return getString(NX_ABSORBING_MATERIAL);
	}

	@Override
	public DataNode setAbsorbing_material(IDataset absorbing_material) {
		return setDataset(NX_ABSORBING_MATERIAL, absorbing_material);
	}

	@Override
	public DataNode setAbsorbing_materialScalar(String absorbing_material) {
		return setString(NX_ABSORBING_MATERIAL, absorbing_material);
	}

	@Override
	public IDataset getTransmitting_material() {
		return getDataset(NX_TRANSMITTING_MATERIAL);
	}

	@Override
	public String getTransmitting_materialScalar() {
		return getString(NX_TRANSMITTING_MATERIAL);
	}

	@Override
	public DataNode setTransmitting_material(IDataset transmitting_material) {
		return setDataset(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

	@Override
	public DataNode setTransmitting_materialScalar(String transmitting_material) {
		return setString(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

}
