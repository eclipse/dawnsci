/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-12T11:55:04.232+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * ``NXguide`` is used by neutron instruments to describe
 * a guide consists of several mirrors building a shape through which
 * neutrons can be guided or directed. The simplest such form is box shaped
 * although elliptical guides are gaining in popularity.
 * The individual parts of a guide usually have common characteristics
 * but there are cases where they are different.
 * For example, a neutron guide might consist of 2 or 4 coated walls or
 * a supermirror bender with multiple, coated vanes.
 * To describe polarizing supermirrors such as used in neutron reflection,
 * it may be necessary to revise this definition of ``NXguide``
 * to include ``NXpolarizer`` and/or ``NXmirror``.
 * When even greater complexity exists in the definition of what
 * constitutes a *guide*, it has been suggested that ``NXguide``
 * be redefined as a ``NXcollection`` of
 * ``NXmirrors`` each having their own
 * ``NXgeometries`` describing their location(s).
 * For the more general case when describing mirrors, consider using
 * ``NXmirror``.
 * NOTE: The NeXus International Advisory Committee welcomes
 * comments for revision and improvement of
 * this definition of ``NXguide``.
 * 
 * @version 1.0
 */
public class NXguideImpl extends NXobjectImpl implements NXguide {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_INCIDENT_ANGLE = "incident_angle";
	public static final String NX_BEND_ANGLE_X = "bend_angle_x";
	public static final String NX_BEND_ANGLE_Y = "bend_angle_y";
	public static final String NX_INTERIOR_ATMOSPHERE = "interior_atmosphere";
	public static final String NX_EXTERNAL_MATERIAL = "external_material";
	public static final String NX_M_VALUE = "m_value";
	public static final String NX_SUBSTRATE_MATERIAL = "substrate_material";
	public static final String NX_SUBSTRATE_THICKNESS = "substrate_thickness";
	public static final String NX_COATING_MATERIAL = "coating_material";
	public static final String NX_SUBSTRATE_ROUGHNESS = "substrate_roughness";
	public static final String NX_COATING_ROUGHNESS = "coating_roughness";
	public static final String NX_NUMBER_SECTIONS = "number_sections";

	protected NXguideImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXguideImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXguide.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_GUIDE;
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
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getScalarDescription() {
		return getString(NX_DESCRIPTION);
	}

	public void setDescription(IDataset description) {
		setDataset(NX_DESCRIPTION, description);
	}

	public void setScalarDescription(String description) {
		setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getIncident_angle() {
		return getDataset(NX_INCIDENT_ANGLE);
	}

	@Override
	public double getScalarIncident_angle() {
		return getDouble(NX_INCIDENT_ANGLE);
	}

	public void setIncident_angle(IDataset incident_angle) {
		setDataset(NX_INCIDENT_ANGLE, incident_angle);
	}

	public void setScalarIncident_angle(double incident_angle) {
		setField(NX_INCIDENT_ANGLE, incident_angle);
	}

	@Override
	public NXdata getReflectivity() {
		return getChild("reflectivity", NXdata.class);
	}

	public void setReflectivity(NXdata reflectivity) {
		putChild("reflectivity", reflectivity);
	}

	@Override
	public IDataset getBend_angle_x() {
		return getDataset(NX_BEND_ANGLE_X);
	}

	@Override
	public double getScalarBend_angle_x() {
		return getDouble(NX_BEND_ANGLE_X);
	}

	public void setBend_angle_x(IDataset bend_angle_x) {
		setDataset(NX_BEND_ANGLE_X, bend_angle_x);
	}

	public void setScalarBend_angle_x(double bend_angle_x) {
		setField(NX_BEND_ANGLE_X, bend_angle_x);
	}

	@Override
	public IDataset getBend_angle_y() {
		return getDataset(NX_BEND_ANGLE_Y);
	}

	@Override
	public double getScalarBend_angle_y() {
		return getDouble(NX_BEND_ANGLE_Y);
	}

	public void setBend_angle_y(IDataset bend_angle_y) {
		setDataset(NX_BEND_ANGLE_Y, bend_angle_y);
	}

	public void setScalarBend_angle_y(double bend_angle_y) {
		setField(NX_BEND_ANGLE_Y, bend_angle_y);
	}

	@Override
	public IDataset getInterior_atmosphere() {
		return getDataset(NX_INTERIOR_ATMOSPHERE);
	}

	@Override
	public String getScalarInterior_atmosphere() {
		return getString(NX_INTERIOR_ATMOSPHERE);
	}

	public void setInterior_atmosphere(IDataset interior_atmosphere) {
		setDataset(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	public void setScalarInterior_atmosphere(String interior_atmosphere) {
		setString(NX_INTERIOR_ATMOSPHERE, interior_atmosphere);
	}

	@Override
	public IDataset getExternal_material() {
		return getDataset(NX_EXTERNAL_MATERIAL);
	}

	@Override
	public String getScalarExternal_material() {
		return getString(NX_EXTERNAL_MATERIAL);
	}

	public void setExternal_material(IDataset external_material) {
		setDataset(NX_EXTERNAL_MATERIAL, external_material);
	}

	public void setScalarExternal_material(String external_material) {
		setString(NX_EXTERNAL_MATERIAL, external_material);
	}

	@Override
	public IDataset getM_value() {
		return getDataset(NX_M_VALUE);
	}

	@Override
	public double getScalarM_value() {
		return getDouble(NX_M_VALUE);
	}

	public void setM_value(IDataset m_value) {
		setDataset(NX_M_VALUE, m_value);
	}

	public void setScalarM_value(double m_value) {
		setField(NX_M_VALUE, m_value);
	}

	@Override
	public IDataset getSubstrate_material() {
		return getDataset(NX_SUBSTRATE_MATERIAL);
	}

	@Override
	public double getScalarSubstrate_material() {
		return getDouble(NX_SUBSTRATE_MATERIAL);
	}

	public void setSubstrate_material(IDataset substrate_material) {
		setDataset(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	public void setScalarSubstrate_material(double substrate_material) {
		setField(NX_SUBSTRATE_MATERIAL, substrate_material);
	}

	@Override
	public IDataset getSubstrate_thickness() {
		return getDataset(NX_SUBSTRATE_THICKNESS);
	}

	@Override
	public double getScalarSubstrate_thickness() {
		return getDouble(NX_SUBSTRATE_THICKNESS);
	}

	public void setSubstrate_thickness(IDataset substrate_thickness) {
		setDataset(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	public void setScalarSubstrate_thickness(double substrate_thickness) {
		setField(NX_SUBSTRATE_THICKNESS, substrate_thickness);
	}

	@Override
	public IDataset getCoating_material() {
		return getDataset(NX_COATING_MATERIAL);
	}

	@Override
	public double getScalarCoating_material() {
		return getDouble(NX_COATING_MATERIAL);
	}

	public void setCoating_material(IDataset coating_material) {
		setDataset(NX_COATING_MATERIAL, coating_material);
	}

	public void setScalarCoating_material(double coating_material) {
		setField(NX_COATING_MATERIAL, coating_material);
	}

	@Override
	public IDataset getSubstrate_roughness() {
		return getDataset(NX_SUBSTRATE_ROUGHNESS);
	}

	@Override
	public double getScalarSubstrate_roughness() {
		return getDouble(NX_SUBSTRATE_ROUGHNESS);
	}

	public void setSubstrate_roughness(IDataset substrate_roughness) {
		setDataset(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	public void setScalarSubstrate_roughness(double substrate_roughness) {
		setField(NX_SUBSTRATE_ROUGHNESS, substrate_roughness);
	}

	@Override
	public IDataset getCoating_roughness() {
		return getDataset(NX_COATING_ROUGHNESS);
	}

	@Override
	public double getScalarCoating_roughness() {
		return getDouble(NX_COATING_ROUGHNESS);
	}

	public void setCoating_roughness(IDataset coating_roughness) {
		setDataset(NX_COATING_ROUGHNESS, coating_roughness);
	}

	public void setScalarCoating_roughness(double coating_roughness) {
		setField(NX_COATING_ROUGHNESS, coating_roughness);
	}

	@Override
	public IDataset getNumber_sections() {
		return getDataset(NX_NUMBER_SECTIONS);
	}

	@Override
	public long getScalarNumber_sections() {
		return getLong(NX_NUMBER_SECTIONS);
	}

	public void setNumber_sections(IDataset number_sections) {
		setDataset(NX_NUMBER_SECTIONS, number_sections);
	}

	public void setScalarNumber_sections(long number_sections) {
		setField(NX_NUMBER_SECTIONS, number_sections);
	}

}
