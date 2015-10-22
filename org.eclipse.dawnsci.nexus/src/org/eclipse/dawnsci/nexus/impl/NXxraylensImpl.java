/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-13T13:58:10.369+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * This is a dictionary of field names to use for
 * describing a X-ray lens as used at
 * synchrotron beam lines.
 * Based on information provided by Gerd Wellenreuther.
 * 
 * @version 1.0
 */
public class NXxraylensImpl extends NXobjectImpl implements NXxraylens {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_LENS_GEOMETRY = "lens_geometry";
	public static final String NX_SYMMETRIC = "symmetric";
	public static final String NX_CYLINDRICAL = "cylindrical";
	public static final String NX_FOCUS_TYPE = "focus_type";
	public static final String NX_LENS_THICKNESS = "lens_thickness";
	public static final String NX_LENS_LENGTH = "lens_length";
	public static final String NX_CURVATURE = "curvature";
	public static final String NX_APERTURE = "aperture";
	public static final String NX_NUMBER_OF_LENSES = "number_of_lenses";
	public static final String NX_LENS_MATERIAL = "lens_material";
	public static final String NX_GAS = "gas";
	public static final String NX_GAS_PRESSURE = "gas_pressure";

	protected NXxraylensImpl(final NexusNodeFactory nodeFactory) {
		super(nodeFactory);
	}

	protected NXxraylensImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXxraylens.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.NX_XRAYLENS;
	}

	@Override
	public IDataset getLens_geometry() {
		return getDataset(NX_LENS_GEOMETRY);
	}

	@Override
	public String getLens_geometryScalar() {
		return getString(NX_LENS_GEOMETRY);
	}

	public void setLens_geometry(IDataset lens_geometry) {
		setDataset(NX_LENS_GEOMETRY, lens_geometry);
	}

	public void setLens_geometryScalar(String lens_geometry) {
		setString(NX_LENS_GEOMETRY, lens_geometry);
	}

	@Override
	public IDataset getSymmetric() {
		return getDataset(NX_SYMMETRIC);
	}

	@Override
	public boolean getSymmetricScalar() {
		return getBoolean(NX_SYMMETRIC);
	}

	public void setSymmetric(IDataset symmetric) {
		setDataset(NX_SYMMETRIC, symmetric);
	}

	public void setSymmetricScalar(boolean symmetric) {
		setField(NX_SYMMETRIC, symmetric);
	}

	@Override
	public IDataset getCylindrical() {
		return getDataset(NX_CYLINDRICAL);
	}

	@Override
	public boolean getCylindricalScalar() {
		return getBoolean(NX_CYLINDRICAL);
	}

	public void setCylindrical(IDataset cylindrical) {
		setDataset(NX_CYLINDRICAL, cylindrical);
	}

	public void setCylindricalScalar(boolean cylindrical) {
		setField(NX_CYLINDRICAL, cylindrical);
	}

	@Override
	public NXnote getCylinder_orientation() {
		return getChild("cylinder_orientation", NXnote.class);
	}

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

	public void setFocus_type(IDataset focus_type) {
		setDataset(NX_FOCUS_TYPE, focus_type);
	}

	public void setFocus_typeScalar(String focus_type) {
		setString(NX_FOCUS_TYPE, focus_type);
	}

	@Override
	public IDataset getLens_thickness() {
		return getDataset(NX_LENS_THICKNESS);
	}

	@Override
	public double getLens_thicknessScalar() {
		return getDouble(NX_LENS_THICKNESS);
	}

	public void setLens_thickness(IDataset lens_thickness) {
		setDataset(NX_LENS_THICKNESS, lens_thickness);
	}

	public void setLens_thicknessScalar(double lens_thickness) {
		setField(NX_LENS_THICKNESS, lens_thickness);
	}

	@Override
	public IDataset getLens_length() {
		return getDataset(NX_LENS_LENGTH);
	}

	@Override
	public double getLens_lengthScalar() {
		return getDouble(NX_LENS_LENGTH);
	}

	public void setLens_length(IDataset lens_length) {
		setDataset(NX_LENS_LENGTH, lens_length);
	}

	public void setLens_lengthScalar(double lens_length) {
		setField(NX_LENS_LENGTH, lens_length);
	}

	@Override
	public IDataset getCurvature() {
		return getDataset(NX_CURVATURE);
	}

	@Override
	public double getCurvatureScalar() {
		return getDouble(NX_CURVATURE);
	}

	public void setCurvature(IDataset curvature) {
		setDataset(NX_CURVATURE, curvature);
	}

	public void setCurvatureScalar(double curvature) {
		setField(NX_CURVATURE, curvature);
	}

	@Override
	public IDataset getAperture() {
		return getDataset(NX_APERTURE);
	}

	@Override
	public double getApertureScalar() {
		return getDouble(NX_APERTURE);
	}

	public void setAperture(IDataset aperture) {
		setDataset(NX_APERTURE, aperture);
	}

	public void setApertureScalar(double aperture) {
		setField(NX_APERTURE, aperture);
	}

	@Override
	public IDataset getNumber_of_lenses() {
		return getDataset(NX_NUMBER_OF_LENSES);
	}

	@Override
	public long getNumber_of_lensesScalar() {
		return getLong(NX_NUMBER_OF_LENSES);
	}

	public void setNumber_of_lenses(IDataset number_of_lenses) {
		setDataset(NX_NUMBER_OF_LENSES, number_of_lenses);
	}

	public void setNumber_of_lensesScalar(long number_of_lenses) {
		setField(NX_NUMBER_OF_LENSES, number_of_lenses);
	}

	@Override
	public IDataset getLens_material() {
		return getDataset(NX_LENS_MATERIAL);
	}

	@Override
	public String getLens_materialScalar() {
		return getString(NX_LENS_MATERIAL);
	}

	public void setLens_material(IDataset lens_material) {
		setDataset(NX_LENS_MATERIAL, lens_material);
	}

	public void setLens_materialScalar(String lens_material) {
		setString(NX_LENS_MATERIAL, lens_material);
	}

	@Override
	public IDataset getGas() {
		return getDataset(NX_GAS);
	}

	@Override
	public String getGasScalar() {
		return getString(NX_GAS);
	}

	public void setGas(IDataset gas) {
		setDataset(NX_GAS, gas);
	}

	public void setGasScalar(String gas) {
		setString(NX_GAS, gas);
	}

	@Override
	public IDataset getGas_pressure() {
		return getDataset(NX_GAS_PRESSURE);
	}

	@Override
	public double getGas_pressureScalar() {
		return getDouble(NX_GAS_PRESSURE);
	}

	public void setGas_pressure(IDataset gas_pressure) {
		setDataset(NX_GAS_PRESSURE, gas_pressure);
	}

	public void setGas_pressureScalar(double gas_pressure) {
		setField(NX_GAS_PRESSURE, gas_pressure);
	}

}
