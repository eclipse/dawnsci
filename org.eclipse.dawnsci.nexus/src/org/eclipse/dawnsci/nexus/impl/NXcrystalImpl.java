/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * Template of a crystal monochromator or analyzer. Permits double bent
 * monochromator comprised of multiple segments with anisotropic
 * Gaussian mosaic.
 * If curvatures are set to zero or are absent, array
 * is considered to be flat.
 * Scattering vector is perpendicular to surface. Crystal is oriented
 * parallel to beam incident on crystal before rotation, and lies in
 * vertical plane.
 * 
 * @version 1.0
 */
public class NXcrystalImpl extends NXobjectImpl implements NXcrystal {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible

	public static final String NX_USAGE = "usage";
	public static final String NX_TYPE = "type";
	public static final String NX_CHEMICAL_FORMULA = "chemical_formula";
	public static final String NX_ORDER_NO = "order_no";
	public static final String NX_CUT_ANGLE = "cut_angle";
	public static final String NX_SPACE_GROUP = "space_group";
	public static final String NX_UNIT_CELL = "unit_cell";
	public static final String NX_UNIT_CELL_A = "unit_cell_a";
	public static final String NX_UNIT_CELL_B = "unit_cell_b";
	public static final String NX_UNIT_CELL_C = "unit_cell_c";
	public static final String NX_UNIT_CELL_ALPHA = "unit_cell_alpha";
	public static final String NX_UNIT_CELL_BETA = "unit_cell_beta";
	public static final String NX_UNIT_CELL_GAMMA = "unit_cell_gamma";
	public static final String NX_UNIT_CELL_VOLUME = "unit_cell_volume";
	public static final String NX_ORIENTATION_MATRIX = "orientation_matrix";
	public static final String NX_WAVELENGTH = "wavelength";
	public static final String NX_D_SPACING = "d_spacing";
	public static final String NX_SCATTERING_VECTOR = "scattering_vector";
	public static final String NX_REFLECTION = "reflection";
	public static final String NX_THICKNESS = "thickness";
	public static final String NX_DENSITY = "density";
	public static final String NX_SEGMENT_WIDTH = "segment_width";
	public static final String NX_SEGMENT_HEIGHT = "segment_height";
	public static final String NX_SEGMENT_THICKNESS = "segment_thickness";
	public static final String NX_SEGMENT_GAP = "segment_gap";
	public static final String NX_SEGMENT_COLUMNS = "segment_columns";
	public static final String NX_SEGMENT_ROWS = "segment_rows";
	public static final String NX_MOSAIC_HORIZONTAL = "mosaic_horizontal";
	public static final String NX_MOSAIC_VERTICAL = "mosaic_vertical";
	public static final String NX_CURVATURE_HORIZONTAL = "curvature_horizontal";
	public static final String NX_CURVATURE_VERTICAL = "curvature_vertical";
	public static final String NX_IS_CYLINDRICAL = "is_cylindrical";
	public static final String NX_CYLINDRICAL_ORIENTATION_ANGLE = "cylindrical_orientation_angle";
	public static final String NX_POLAR_ANGLE = "polar_angle";
	public static final String NX_AZIMUTHAL_ANGLE = "azimuthal_angle";
	public static final String NX_BRAGG_ANGLE = "bragg_angle";
	public static final String NX_TEMPERATURE = "temperature";
	public static final String NX_TEMPERATURE_COEFFICIENT = "temperature_coefficient";

	protected NXcrystalImpl(long oid) {
		super(oid);
	}

	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcrystal.class;
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
	public IDataset getUsage() {
		return getDataset(NX_USAGE);
	}

	public void setUsage(IDataset usage) {
		setDataset(NX_USAGE, usage);
	}

	@Override
	public IDataset getType() {
		return getDataset(NX_TYPE);
	}

	public void setType(IDataset type) {
		setDataset(NX_TYPE, type);
	}

	@Override
	public IDataset getChemical_formula() {
		return getDataset(NX_CHEMICAL_FORMULA);
	}

	public void setChemical_formula(IDataset chemical_formula) {
		setDataset(NX_CHEMICAL_FORMULA, chemical_formula);
	}

	@Override
	public IDataset getOrder_no() {
		return getDataset(NX_ORDER_NO);
	}

	public void setOrder_no(IDataset order_no) {
		setDataset(NX_ORDER_NO, order_no);
	}

	@Override
	public IDataset getCut_angle() {
		return getDataset(NX_CUT_ANGLE);
	}

	public void setCut_angle(IDataset cut_angle) {
		setDataset(NX_CUT_ANGLE, cut_angle);
	}

	@Override
	public IDataset getSpace_group() {
		return getDataset(NX_SPACE_GROUP);
	}

	public void setSpace_group(IDataset space_group) {
		setDataset(NX_SPACE_GROUP, space_group);
	}

	@Override
	public IDataset getUnit_cell() {
		return getDataset(NX_UNIT_CELL);
	}

	public void setUnit_cell(IDataset unit_cell) {
		setDataset(NX_UNIT_CELL, unit_cell);
	}

	@Override
	public IDataset getUnit_cell_a() {
		return getDataset(NX_UNIT_CELL_A);
	}

	public void setUnit_cell_a(IDataset unit_cell_a) {
		setDataset(NX_UNIT_CELL_A, unit_cell_a);
	}

	@Override
	public IDataset getUnit_cell_b() {
		return getDataset(NX_UNIT_CELL_B);
	}

	public void setUnit_cell_b(IDataset unit_cell_b) {
		setDataset(NX_UNIT_CELL_B, unit_cell_b);
	}

	@Override
	public IDataset getUnit_cell_c() {
		return getDataset(NX_UNIT_CELL_C);
	}

	public void setUnit_cell_c(IDataset unit_cell_c) {
		setDataset(NX_UNIT_CELL_C, unit_cell_c);
	}

	@Override
	public IDataset getUnit_cell_alpha() {
		return getDataset(NX_UNIT_CELL_ALPHA);
	}

	public void setUnit_cell_alpha(IDataset unit_cell_alpha) {
		setDataset(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	@Override
	public IDataset getUnit_cell_beta() {
		return getDataset(NX_UNIT_CELL_BETA);
	}

	public void setUnit_cell_beta(IDataset unit_cell_beta) {
		setDataset(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	@Override
	public IDataset getUnit_cell_gamma() {
		return getDataset(NX_UNIT_CELL_GAMMA);
	}

	public void setUnit_cell_gamma(IDataset unit_cell_gamma) {
		setDataset(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	public void setUnit_cell_volume(IDataset unit_cell_volume) {
		setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	public void setOrientation_matrix(IDataset orientation_matrix) {
		setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public IDataset getWavelength() {
		return getDataset(NX_WAVELENGTH);
	}

	public void setWavelength(IDataset wavelength) {
		setDataset(NX_WAVELENGTH, wavelength);
	}

	@Override
	public IDataset getD_spacing() {
		return getDataset(NX_D_SPACING);
	}

	public void setD_spacing(IDataset d_spacing) {
		setDataset(NX_D_SPACING, d_spacing);
	}

	@Override
	public IDataset getScattering_vector() {
		return getDataset(NX_SCATTERING_VECTOR);
	}

	public void setScattering_vector(IDataset scattering_vector) {
		setDataset(NX_SCATTERING_VECTOR, scattering_vector);
	}

	@Override
	public IDataset getReflection() {
		return getDataset(NX_REFLECTION);
	}

	public void setReflection(IDataset reflection) {
		setDataset(NX_REFLECTION, reflection);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	public void setThickness(IDataset thickness) {
		setDataset(NX_THICKNESS, thickness);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	public void setDensity(IDataset density) {
		setDataset(NX_DENSITY, density);
	}

	@Override
	public IDataset getSegment_width() {
		return getDataset(NX_SEGMENT_WIDTH);
	}

	public void setSegment_width(IDataset segment_width) {
		setDataset(NX_SEGMENT_WIDTH, segment_width);
	}

	@Override
	public IDataset getSegment_height() {
		return getDataset(NX_SEGMENT_HEIGHT);
	}

	public void setSegment_height(IDataset segment_height) {
		setDataset(NX_SEGMENT_HEIGHT, segment_height);
	}

	@Override
	public IDataset getSegment_thickness() {
		return getDataset(NX_SEGMENT_THICKNESS);
	}

	public void setSegment_thickness(IDataset segment_thickness) {
		setDataset(NX_SEGMENT_THICKNESS, segment_thickness);
	}

	@Override
	public IDataset getSegment_gap() {
		return getDataset(NX_SEGMENT_GAP);
	}

	public void setSegment_gap(IDataset segment_gap) {
		setDataset(NX_SEGMENT_GAP, segment_gap);
	}

	@Override
	public IDataset getSegment_columns() {
		return getDataset(NX_SEGMENT_COLUMNS);
	}

	public void setSegment_columns(IDataset segment_columns) {
		setDataset(NX_SEGMENT_COLUMNS, segment_columns);
	}

	@Override
	public IDataset getSegment_rows() {
		return getDataset(NX_SEGMENT_ROWS);
	}

	public void setSegment_rows(IDataset segment_rows) {
		setDataset(NX_SEGMENT_ROWS, segment_rows);
	}

	@Override
	public IDataset getMosaic_horizontal() {
		return getDataset(NX_MOSAIC_HORIZONTAL);
	}

	public void setMosaic_horizontal(IDataset mosaic_horizontal) {
		setDataset(NX_MOSAIC_HORIZONTAL, mosaic_horizontal);
	}

	@Override
	public IDataset getMosaic_vertical() {
		return getDataset(NX_MOSAIC_VERTICAL);
	}

	public void setMosaic_vertical(IDataset mosaic_vertical) {
		setDataset(NX_MOSAIC_VERTICAL, mosaic_vertical);
	}

	@Override
	public IDataset getCurvature_horizontal() {
		return getDataset(NX_CURVATURE_HORIZONTAL);
	}

	public void setCurvature_horizontal(IDataset curvature_horizontal) {
		setDataset(NX_CURVATURE_HORIZONTAL, curvature_horizontal);
	}

	@Override
	public IDataset getCurvature_vertical() {
		return getDataset(NX_CURVATURE_VERTICAL);
	}

	public void setCurvature_vertical(IDataset curvature_vertical) {
		setDataset(NX_CURVATURE_VERTICAL, curvature_vertical);
	}

	@Override
	public IDataset getIs_cylindrical() {
		return getDataset(NX_IS_CYLINDRICAL);
	}

	public void setIs_cylindrical(IDataset is_cylindrical) {
		setDataset(NX_IS_CYLINDRICAL, is_cylindrical);
	}

	@Override
	public IDataset getCylindrical_orientation_angle() {
		return getDataset(NX_CYLINDRICAL_ORIENTATION_ANGLE);
	}

	public void setCylindrical_orientation_angle(IDataset cylindrical_orientation_angle) {
		setDataset(NX_CYLINDRICAL_ORIENTATION_ANGLE, cylindrical_orientation_angle);
	}

	@Override
	public IDataset getPolar_angle() {
		return getDataset(NX_POLAR_ANGLE);
	}

	public void setPolar_angle(IDataset polar_angle) {
		setDataset(NX_POLAR_ANGLE, polar_angle);
	}

	@Override
	public IDataset getAzimuthal_angle() {
		return getDataset(NX_AZIMUTHAL_ANGLE);
	}

	public void setAzimuthal_angle(IDataset azimuthal_angle) {
		setDataset(NX_AZIMUTHAL_ANGLE, azimuthal_angle);
	}

	@Override
	public IDataset getBragg_angle() {
		return getDataset(NX_BRAGG_ANGLE);
	}

	public void setBragg_angle(IDataset bragg_angle) {
		setDataset(NX_BRAGG_ANGLE, bragg_angle);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	public void setTemperature(IDataset temperature) {
		setDataset(NX_TEMPERATURE, temperature);
	}

	@Override
	public IDataset getTemperature_coefficient() {
		return getDataset(NX_TEMPERATURE_COEFFICIENT);
	}

	public void setTemperature_coefficient(IDataset temperature_coefficient) {
		setDataset(NX_TEMPERATURE_COEFFICIENT, temperature_coefficient);
	}

	@Override
	public NXlog getTemperature_log() {
		return getChild("temperature_log", NXlog.class);
	}

	public void setTemperature_log(NXlog temperature_log) {
		putChild("temperature_log", temperature_log);
	}

	@Override
	public NXdata getReflectivity() {
		return getChild("reflectivity", NXdata.class);
	}

	public void setReflectivity(NXdata reflectivity) {
		putChild("reflectivity", reflectivity);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
	}

	@Override
	public NXshape getShape() {
		return getChild("shape", NXshape.class);
	}

	public void setShape(NXshape shape) {
		putChild("shape", shape);
	}

}
