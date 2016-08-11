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
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.*;
import org.eclipse.january.dataset.IDataset;

/**
 * An X-ray lens, typically at a synchrotron X-ray beam line.
 * Based on information provided by Gerd Wellenreuther (DESY).
 * 
 * @version 1.0
 */
public class NXxraylensImpl extends NXobjectImpl implements NXxraylens {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_NOTE);

	public NXxraylensImpl() {
		super();
	}

	public NXxraylensImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXxraylens.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_XRAYLENS;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getLens_geometry() {
		return getDataset(NX_LENS_GEOMETRY);
	}

	@Override
	public String getLens_geometryScalar() {
		return getString(NX_LENS_GEOMETRY);
	}

	@Override
	public DataNode setLens_geometry(IDataset lens_geometry) {
		return setDataset(NX_LENS_GEOMETRY, lens_geometry);
	}

	@Override
	public DataNode setLens_geometryScalar(String lens_geometry) {
		return setString(NX_LENS_GEOMETRY, lens_geometry);
	}

	@Override
	public IDataset getSymmetric() {
		return getDataset(NX_SYMMETRIC);
	}

	@Override
	public boolean getSymmetricScalar() {
		return getBoolean(NX_SYMMETRIC);
	}

	@Override
	public DataNode setSymmetric(IDataset symmetric) {
		return setDataset(NX_SYMMETRIC, symmetric);
	}

	@Override
	public DataNode setSymmetricScalar(boolean symmetric) {
		return setField(NX_SYMMETRIC, symmetric);
	}

	@Override
	public IDataset getCylindrical() {
		return getDataset(NX_CYLINDRICAL);
	}

	@Override
	public boolean getCylindricalScalar() {
		return getBoolean(NX_CYLINDRICAL);
	}

	@Override
	public DataNode setCylindrical(IDataset cylindrical) {
		return setDataset(NX_CYLINDRICAL, cylindrical);
	}

	@Override
	public DataNode setCylindricalScalar(boolean cylindrical) {
		return setField(NX_CYLINDRICAL, cylindrical);
	}

	@Override
	public NXnote getCylinder_orientation() {
		return getChild("cylinder_orientation", NXnote.class);
	}

	@Override
	public void setCylinder_orientation(NXnote cylinder_orientation) {
		putChild("cylinder_orientation", cylinder_orientation);
	}

	@Override
	public IDataset getFocus_type() {
		return getDataset(NX_FOCUS_TYPE);
	}

	@Override
	public String getFocus_typeScalar() {
		return getString(NX_FOCUS_TYPE);
	}

	@Override
	public DataNode setFocus_type(IDataset focus_type) {
		return setDataset(NX_FOCUS_TYPE, focus_type);
	}

	@Override
	public DataNode setFocus_typeScalar(String focus_type) {
		return setString(NX_FOCUS_TYPE, focus_type);
	}

	@Override
	public IDataset getLens_thickness() {
		return getDataset(NX_LENS_THICKNESS);
	}

	@Override
	public double getLens_thicknessScalar() {
		return getDouble(NX_LENS_THICKNESS);
	}

	@Override
	public DataNode setLens_thickness(IDataset lens_thickness) {
		return setDataset(NX_LENS_THICKNESS, lens_thickness);
	}

	@Override
	public DataNode setLens_thicknessScalar(double lens_thickness) {
		return setField(NX_LENS_THICKNESS, lens_thickness);
	}

	@Override
	public IDataset getLens_length() {
		return getDataset(NX_LENS_LENGTH);
	}

	@Override
	public double getLens_lengthScalar() {
		return getDouble(NX_LENS_LENGTH);
	}

	@Override
	public DataNode setLens_length(IDataset lens_length) {
		return setDataset(NX_LENS_LENGTH, lens_length);
	}

	@Override
	public DataNode setLens_lengthScalar(double lens_length) {
		return setField(NX_LENS_LENGTH, lens_length);
	}

	@Override
	public IDataset getCurvature() {
		return getDataset(NX_CURVATURE);
	}

	@Override
	public double getCurvatureScalar() {
		return getDouble(NX_CURVATURE);
	}

	@Override
	public DataNode setCurvature(IDataset curvature) {
		return setDataset(NX_CURVATURE, curvature);
	}

	@Override
	public DataNode setCurvatureScalar(double curvature) {
		return setField(NX_CURVATURE, curvature);
	}

	@Override
	public IDataset getAperture() {
		return getDataset(NX_APERTURE);
	}

	@Override
	public double getApertureScalar() {
		return getDouble(NX_APERTURE);
	}

	@Override
	public DataNode setAperture(IDataset aperture) {
		return setDataset(NX_APERTURE, aperture);
	}

	@Override
	public DataNode setApertureScalar(double aperture) {
		return setField(NX_APERTURE, aperture);
	}

	@Override
	public IDataset getNumber_of_lenses() {
		return getDataset(NX_NUMBER_OF_LENSES);
	}

	@Override
	public long getNumber_of_lensesScalar() {
		return getLong(NX_NUMBER_OF_LENSES);
	}

	@Override
	public DataNode setNumber_of_lenses(IDataset number_of_lenses) {
		return setDataset(NX_NUMBER_OF_LENSES, number_of_lenses);
	}

	@Override
	public DataNode setNumber_of_lensesScalar(long number_of_lenses) {
		return setField(NX_NUMBER_OF_LENSES, number_of_lenses);
	}

	@Override
	public IDataset getLens_material() {
		return getDataset(NX_LENS_MATERIAL);
	}

	@Override
	public String getLens_materialScalar() {
		return getString(NX_LENS_MATERIAL);
	}

	@Override
	public DataNode setLens_material(IDataset lens_material) {
		return setDataset(NX_LENS_MATERIAL, lens_material);
	}

	@Override
	public DataNode setLens_materialScalar(String lens_material) {
		return setString(NX_LENS_MATERIAL, lens_material);
	}

	@Override
	public IDataset getGas() {
		return getDataset(NX_GAS);
	}

	@Override
	public String getGasScalar() {
		return getString(NX_GAS);
	}

	@Override
	public DataNode setGas(IDataset gas) {
		return setDataset(NX_GAS, gas);
	}

	@Override
	public DataNode setGasScalar(String gas) {
		return setString(NX_GAS, gas);
	}

	@Override
	public IDataset getGas_pressure() {
		return getDataset(NX_GAS_PRESSURE);
	}

	@Override
	public double getGas_pressureScalar() {
		return getDouble(NX_GAS_PRESSURE);
	}

	@Override
	public DataNode setGas_pressure(IDataset gas_pressure) {
		return setDataset(NX_GAS_PRESSURE, gas_pressure);
	}

	@Override
	public DataNode setGas_pressureScalar(double gas_pressure) {
		return setField(NX_GAS_PRESSURE, gas_pressure);
	}

}
