/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-17T13:07:37.011Z
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

	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_LOG);

	protected NXcollimatorImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXcollimatorImpl(final long oid) {
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
	public IDataset getSoller_angle() {
		return getDataset(NX_SOLLER_ANGLE);
	}

	@Override
	public double getSoller_angleScalar() {
		return getDouble(NX_SOLLER_ANGLE);
	}

	public DataNode setSoller_angle(IDataset soller_angle) {
		return setDataset(NX_SOLLER_ANGLE, soller_angle);
	}

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

	public DataNode setDivergence_x(IDataset divergence_x) {
		return setDataset(NX_DIVERGENCE_X, divergence_x);
	}

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

	public DataNode setDivergence_y(IDataset divergence_y) {
		return setDataset(NX_DIVERGENCE_Y, divergence_y);
	}

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

	public DataNode setFrequency(IDataset frequency) {
		return setDataset(NX_FREQUENCY, frequency);
	}

	public DataNode setFrequencyScalar(double frequency) {
		return setField(NX_FREQUENCY, frequency);
	}

	@Override
	public NXlog getFrequency_log() {
		return getChild("frequency_log", NXlog.class);
	}

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

	public DataNode setBlade_thickness(IDataset blade_thickness) {
		return setDataset(NX_BLADE_THICKNESS, blade_thickness);
	}

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

	public DataNode setBlade_spacing(IDataset blade_spacing) {
		return setDataset(NX_BLADE_SPACING, blade_spacing);
	}

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

	public DataNode setAbsorbing_material(IDataset absorbing_material) {
		return setDataset(NX_ABSORBING_MATERIAL, absorbing_material);
	}

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

	public DataNode setTransmitting_material(IDataset transmitting_material) {
		return setDataset(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

	public DataNode setTransmitting_materialScalar(String transmitting_material) {
		return setString(NX_TRANSMITTING_MATERIAL, transmitting_material);
	}

}
