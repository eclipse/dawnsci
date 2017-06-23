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
import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * One group like this per component can be recorded For a sample consisting of multiple components.
 * 
 * @version 1.0
 */
public class NXsample_componentImpl extends NXobjectImpl implements NXsample_component {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_DATA);

	public NXsample_componentImpl() {
		super();
	}

	public NXsample_componentImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXsample_component.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_SAMPLE_COMPONENT;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getName() {
		return getDataset(NX_NAME);
	}

	@Override
	public String getNameScalar() {
		return getString(NX_NAME);
	}

	@Override
	public DataNode setName(IDataset name) {
		return setDataset(NX_NAME, name);
	}

	@Override
	public DataNode setNameScalar(String name) {
		return setString(NX_NAME, name);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	@Override
	public String getChemical_formulaScalar() {
		return getString(NX_CHEMICAL_FORMULA);
	}

	@Override
	public DataNode setChemical_formula(IDataset chemical_formula) {
		return setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public DataNode setChemical_formulaScalar(String chemical_formula) {
		return setString(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public IDataset getUnit_cell_abc() {
		return getDataset(NX_UNIT_CELL_ABC);
	}

	@Override
	public Double getUnit_cell_abcScalar() {
		return getDouble(NX_UNIT_CELL_ABC);
	}

	@Override
	public DataNode setUnit_cell_abc(IDataset unit_cell_abc) {
		return setDataset(NX_UNIT_CELL_ABC, unit_cell_abc);
	}

	@Override
	public DataNode setUnit_cell_abcScalar(Double unit_cell_abc) {
		return setField(NX_UNIT_CELL_ABC, unit_cell_abc);
	}

	@Override
	public IDataset getUnit_cell_alphabetagamma() {
		return getDataset(NX_UNIT_CELL_ALPHABETAGAMMA);
	}

	@Override
	public Double getUnit_cell_alphabetagammaScalar() {
		return getDouble(NX_UNIT_CELL_ALPHABETAGAMMA);
	}

	@Override
	public DataNode setUnit_cell_alphabetagamma(IDataset unit_cell_alphabetagamma) {
		return setDataset(NX_UNIT_CELL_ALPHABETAGAMMA, unit_cell_alphabetagamma);
	}

	@Override
	public DataNode setUnit_cell_alphabetagammaScalar(Double unit_cell_alphabetagamma) {
		return setField(NX_UNIT_CELL_ALPHABETAGAMMA, unit_cell_alphabetagamma);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public Double getUnit_cell_volumeScalar() {
		return getDouble(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public DataNode setUnit_cell_volume(IDataset unit_cell_volume) {
		return setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public DataNode setUnit_cell_volumeScalar(Double unit_cell_volume) {
		return setField(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getSample_orientation() {
		return getDataset(NX_SAMPLE_ORIENTATION);
	}

	@Override
	public Double getSample_orientationScalar() {
		return getDouble(NX_SAMPLE_ORIENTATION);
	}

	@Override
	public DataNode setSample_orientation(IDataset sample_orientation) {
		return setDataset(NX_SAMPLE_ORIENTATION, sample_orientation);
	}

	@Override
	public DataNode setSample_orientationScalar(Double sample_orientation) {
		return setField(NX_SAMPLE_ORIENTATION, sample_orientation);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	@Override
	public Double getOrientation_matrixScalar() {
		return getDouble(NX_ORIENTATION_MATRIX);
	}

	@Override
	public DataNode setOrientation_matrix(IDataset orientation_matrix) {
		return setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public DataNode setOrientation_matrixScalar(Double orientation_matrix) {
		return setField(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public IDataset getMass() {
		return getDataset(NX_MASS);
	}

	@Override
	public Double getMassScalar() {
		return getDouble(NX_MASS);
	}

	@Override
	public DataNode setMass(IDataset mass) {
		return setDataset(NX_MASS, mass);
	}

	@Override
	public DataNode setMassScalar(Double mass) {
		return setField(NX_MASS, mass);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public Double getDensityScalar() {
		return getDouble(NX_DENSITY);
	}

	@Override
	public DataNode setDensity(IDataset density) {
		return setDataset(NX_DENSITY, density);
	}

	@Override
	public DataNode setDensityScalar(Double density) {
		return setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getRelative_molecular_mass() {
		return getDataset(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public Double getRelative_molecular_massScalar() {
		return getDouble(NX_RELATIVE_MOLECULAR_MASS);
	}

	@Override
	public DataNode setRelative_molecular_mass(IDataset relative_molecular_mass) {
		return setDataset(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public DataNode setRelative_molecular_massScalar(Double relative_molecular_mass) {
		return setField(NX_RELATIVE_MOLECULAR_MASS, relative_molecular_mass);
	}

	@Override
	public IDataset getDescription() {
		return getDataset(NX_DESCRIPTION);
	}

	@Override
	public String getDescriptionScalar() {
		return getString(NX_DESCRIPTION);
	}

	@Override
	public DataNode setDescription(IDataset description) {
		return setDataset(NX_DESCRIPTION, description);
	}

	@Override
	public DataNode setDescriptionScalar(String description) {
		return setString(NX_DESCRIPTION, description);
	}

	@Override
	public IDataset getVolume_fraction() {
		return getDataset(NX_VOLUME_FRACTION);
	}

	@Override
	public Double getVolume_fractionScalar() {
		return getDouble(NX_VOLUME_FRACTION);
	}

	@Override
	public DataNode setVolume_fraction(IDataset volume_fraction) {
		return setDataset(NX_VOLUME_FRACTION, volume_fraction);
	}

	@Override
	public DataNode setVolume_fractionScalar(Double volume_fraction) {
		return setField(NX_VOLUME_FRACTION, volume_fraction);
	}

	@Override
	public IDataset getScattering_length_density() {
		return getDataset(NX_SCATTERING_LENGTH_DENSITY);
	}

	@Override
	public Double getScattering_length_densityScalar() {
		return getDouble(NX_SCATTERING_LENGTH_DENSITY);
	}

	@Override
	public DataNode setScattering_length_density(IDataset scattering_length_density) {
		return setDataset(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	@Override
	public DataNode setScattering_length_densityScalar(Double scattering_length_density) {
		return setField(NX_SCATTERING_LENGTH_DENSITY, scattering_length_density);
	}

	@Override
	public IDataset getUnit_cell_class() {
		return getDataset(NX_UNIT_CELL_CLASS);
	}

	@Override
	public String getUnit_cell_classScalar() {
		return getString(NX_UNIT_CELL_CLASS);
	}

	@Override
	public DataNode setUnit_cell_class(IDataset unit_cell_class) {
		return setDataset(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	@Override
	public DataNode setUnit_cell_classScalar(String unit_cell_class) {
		return setString(NX_UNIT_CELL_CLASS, unit_cell_class);
	}

	@Override
	public IDataset getSpace_group() {
		return getDataset(NX_SPACE_GROUP);
	}

	@Override
	public String getSpace_groupScalar() {
		return getString(NX_SPACE_GROUP);
	}

	@Override
	public DataNode setSpace_group(IDataset space_group) {
		return setDataset(NX_SPACE_GROUP, space_group);
	}

	@Override
	public DataNode setSpace_groupScalar(String space_group) {
		return setString(NX_SPACE_GROUP, space_group);
	}

	@Override
	public IDataset getPoint_group() {
		return getDataset(NX_POINT_GROUP);
	}

	@Override
	public String getPoint_groupScalar() {
		return getString(NX_POINT_GROUP);
	}

	@Override
	public DataNode setPoint_group(IDataset point_group) {
		return setDataset(NX_POINT_GROUP, point_group);
	}

	@Override
	public DataNode setPoint_groupScalar(String point_group) {
		return setString(NX_POINT_GROUP, point_group);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	@Override
	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
	}

}
