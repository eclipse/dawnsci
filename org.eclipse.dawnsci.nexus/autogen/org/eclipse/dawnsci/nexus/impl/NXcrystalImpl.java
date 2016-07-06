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
 * A crystal monochromator or analyzer.
 * Permits double bent
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


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.of(
		NexusBaseClass.NX_GEOMETRY,
		NexusBaseClass.NX_LOG,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_DATA,
		NexusBaseClass.NX_SHAPE);

	public NXcrystalImpl() {
		super();
	}

	public NXcrystalImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXcrystal.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_CRYSTAL;
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
	public IDataset getUsage() {
		return getDataset(NX_USAGE);
	}

	@Override
	public String getUsageScalar() {
		return getString(NX_USAGE);
	}

	@Override
	public DataNode setUsage(IDataset usage) {
		return setDataset(NX_USAGE, usage);
	}

	@Override
	public DataNode setUsageScalar(String usage) {
		return setString(NX_USAGE, usage);
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
	public IDataset getOrder_no() {
		return getDataset(NX_ORDER_NO);
	}

	@Override
	public long getOrder_noScalar() {
		return getLong(NX_ORDER_NO);
	}

	@Override
	public DataNode setOrder_no(IDataset order_no) {
		return setDataset(NX_ORDER_NO, order_no);
	}

	@Override
	public DataNode setOrder_noScalar(long order_no) {
		return setField(NX_ORDER_NO, order_no);
	}

	@Override
	public IDataset getCut_angle() {
		return getDataset(NX_CUT_ANGLE);
	}

	@Override
	public double getCut_angleScalar() {
		return getDouble(NX_CUT_ANGLE);
	}

	@Override
	public DataNode setCut_angle(IDataset cut_angle) {
		return setDataset(NX_CUT_ANGLE, cut_angle);
	}

	@Override
	public DataNode setCut_angleScalar(double cut_angle) {
		return setField(NX_CUT_ANGLE, cut_angle);
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
	public IDataset getUnit_cell() {
		return getDataset(NX_UNIT_CELL);
	}

	@Override
	public double getUnit_cellScalar() {
		return getDouble(NX_UNIT_CELL);
	}

	@Override
	public DataNode setUnit_cell(IDataset unit_cell) {
		return setDataset(NX_UNIT_CELL, unit_cell);
	}

	@Override
	public DataNode setUnit_cellScalar(double unit_cell) {
		return setField(NX_UNIT_CELL, unit_cell);
	}

	@Override
	public IDataset getUnit_cell_a() {
		return getDataset(NX_UNIT_CELL_A);
	}

	@Override
	public double getUnit_cell_aScalar() {
		return getDouble(NX_UNIT_CELL_A);
	}

	@Override
	public DataNode setUnit_cell_a(IDataset unit_cell_a) {
		return setDataset(NX_UNIT_CELL_A, unit_cell_a);
	}

	@Override
	public DataNode setUnit_cell_aScalar(double unit_cell_a) {
		return setField(NX_UNIT_CELL_A, unit_cell_a);
	}

	@Override
	public IDataset getUnit_cell_b() {
		return getDataset(NX_UNIT_CELL_B);
	}

	@Override
	public double getUnit_cell_bScalar() {
		return getDouble(NX_UNIT_CELL_B);
	}

	@Override
	public DataNode setUnit_cell_b(IDataset unit_cell_b) {
		return setDataset(NX_UNIT_CELL_B, unit_cell_b);
	}

	@Override
	public DataNode setUnit_cell_bScalar(double unit_cell_b) {
		return setField(NX_UNIT_CELL_B, unit_cell_b);
	}

	@Override
	public IDataset getUnit_cell_c() {
		return getDataset(NX_UNIT_CELL_C);
	}

	@Override
	public double getUnit_cell_cScalar() {
		return getDouble(NX_UNIT_CELL_C);
	}

	@Override
	public DataNode setUnit_cell_c(IDataset unit_cell_c) {
		return setDataset(NX_UNIT_CELL_C, unit_cell_c);
	}

	@Override
	public DataNode setUnit_cell_cScalar(double unit_cell_c) {
		return setField(NX_UNIT_CELL_C, unit_cell_c);
	}

	@Override
	public IDataset getUnit_cell_alpha() {
		return getDataset(NX_UNIT_CELL_ALPHA);
	}

	@Override
	public double getUnit_cell_alphaScalar() {
		return getDouble(NX_UNIT_CELL_ALPHA);
	}

	@Override
	public DataNode setUnit_cell_alpha(IDataset unit_cell_alpha) {
		return setDataset(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	@Override
	public DataNode setUnit_cell_alphaScalar(double unit_cell_alpha) {
		return setField(NX_UNIT_CELL_ALPHA, unit_cell_alpha);
	}

	@Override
	public IDataset getUnit_cell_beta() {
		return getDataset(NX_UNIT_CELL_BETA);
	}

	@Override
	public double getUnit_cell_betaScalar() {
		return getDouble(NX_UNIT_CELL_BETA);
	}

	@Override
	public DataNode setUnit_cell_beta(IDataset unit_cell_beta) {
		return setDataset(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	@Override
	public DataNode setUnit_cell_betaScalar(double unit_cell_beta) {
		return setField(NX_UNIT_CELL_BETA, unit_cell_beta);
	}

	@Override
	public IDataset getUnit_cell_gamma() {
		return getDataset(NX_UNIT_CELL_GAMMA);
	}

	@Override
	public double getUnit_cell_gammaScalar() {
		return getDouble(NX_UNIT_CELL_GAMMA);
	}

	@Override
	public DataNode setUnit_cell_gamma(IDataset unit_cell_gamma) {
		return setDataset(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	@Override
	public DataNode setUnit_cell_gammaScalar(double unit_cell_gamma) {
		return setField(NX_UNIT_CELL_GAMMA, unit_cell_gamma);
	}

	@Override
	public IDataset getUnit_cell_volume() {
		return getDataset(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public double getUnit_cell_volumeScalar() {
		return getDouble(NX_UNIT_CELL_VOLUME);
	}

	@Override
	public DataNode setUnit_cell_volume(IDataset unit_cell_volume) {
		return setDataset(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public DataNode setUnit_cell_volumeScalar(double unit_cell_volume) {
		return setField(NX_UNIT_CELL_VOLUME, unit_cell_volume);
	}

	@Override
	public IDataset getOrientation_matrix() {
		return getDataset(NX_ORIENTATION_MATRIX);
	}

	@Override
	public double getOrientation_matrixScalar() {
		return getDouble(NX_ORIENTATION_MATRIX);
	}

	@Override
	public DataNode setOrientation_matrix(IDataset orientation_matrix) {
		return setDataset(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public DataNode setOrientation_matrixScalar(double orientation_matrix) {
		return setField(NX_ORIENTATION_MATRIX, orientation_matrix);
	}

	@Override
	public IDataset getWavelength() {
		return getDataset(NX_WAVELENGTH);
	}

	@Override
	public double getWavelengthScalar() {
		return getDouble(NX_WAVELENGTH);
	}

	@Override
	public DataNode setWavelength(IDataset wavelength) {
		return setDataset(NX_WAVELENGTH, wavelength);
	}

	@Override
	public DataNode setWavelengthScalar(double wavelength) {
		return setField(NX_WAVELENGTH, wavelength);
	}

	@Override
	public IDataset getD_spacing() {
		return getDataset(NX_D_SPACING);
	}

	@Override
	public double getD_spacingScalar() {
		return getDouble(NX_D_SPACING);
	}

	@Override
	public DataNode setD_spacing(IDataset d_spacing) {
		return setDataset(NX_D_SPACING, d_spacing);
	}

	@Override
	public DataNode setD_spacingScalar(double d_spacing) {
		return setField(NX_D_SPACING, d_spacing);
	}

	@Override
	public IDataset getScattering_vector() {
		return getDataset(NX_SCATTERING_VECTOR);
	}

	@Override
	public double getScattering_vectorScalar() {
		return getDouble(NX_SCATTERING_VECTOR);
	}

	@Override
	public DataNode setScattering_vector(IDataset scattering_vector) {
		return setDataset(NX_SCATTERING_VECTOR, scattering_vector);
	}

	@Override
	public DataNode setScattering_vectorScalar(double scattering_vector) {
		return setField(NX_SCATTERING_VECTOR, scattering_vector);
	}

	@Override
	public IDataset getReflection() {
		return getDataset(NX_REFLECTION);
	}

	@Override
	public long getReflectionScalar() {
		return getLong(NX_REFLECTION);
	}

	@Override
	public DataNode setReflection(IDataset reflection) {
		return setDataset(NX_REFLECTION, reflection);
	}

	@Override
	public DataNode setReflectionScalar(long reflection) {
		return setField(NX_REFLECTION, reflection);
	}

	@Override
	public IDataset getThickness() {
		return getDataset(NX_THICKNESS);
	}

	@Override
	public double getThicknessScalar() {
		return getDouble(NX_THICKNESS);
	}

	@Override
	public DataNode setThickness(IDataset thickness) {
		return setDataset(NX_THICKNESS, thickness);
	}

	@Override
	public DataNode setThicknessScalar(double thickness) {
		return setField(NX_THICKNESS, thickness);
	}

	@Override
	public IDataset getDensity() {
		return getDataset(NX_DENSITY);
	}

	@Override
	public Number getDensityScalar() {
		return getNumber(NX_DENSITY);
	}

	@Override
	public DataNode setDensity(IDataset density) {
		return setDataset(NX_DENSITY, density);
	}

	@Override
	public DataNode setDensityScalar(Number density) {
		return setField(NX_DENSITY, density);
	}

	@Override
	public IDataset getSegment_width() {
		return getDataset(NX_SEGMENT_WIDTH);
	}

	@Override
	public double getSegment_widthScalar() {
		return getDouble(NX_SEGMENT_WIDTH);
	}

	@Override
	public DataNode setSegment_width(IDataset segment_width) {
		return setDataset(NX_SEGMENT_WIDTH, segment_width);
	}

	@Override
	public DataNode setSegment_widthScalar(double segment_width) {
		return setField(NX_SEGMENT_WIDTH, segment_width);
	}

	@Override
	public IDataset getSegment_height() {
		return getDataset(NX_SEGMENT_HEIGHT);
	}

	@Override
	public double getSegment_heightScalar() {
		return getDouble(NX_SEGMENT_HEIGHT);
	}

	@Override
	public DataNode setSegment_height(IDataset segment_height) {
		return setDataset(NX_SEGMENT_HEIGHT, segment_height);
	}

	@Override
	public DataNode setSegment_heightScalar(double segment_height) {
		return setField(NX_SEGMENT_HEIGHT, segment_height);
	}

	@Override
	public IDataset getSegment_thickness() {
		return getDataset(NX_SEGMENT_THICKNESS);
	}

	@Override
	public double getSegment_thicknessScalar() {
		return getDouble(NX_SEGMENT_THICKNESS);
	}

	@Override
	public DataNode setSegment_thickness(IDataset segment_thickness) {
		return setDataset(NX_SEGMENT_THICKNESS, segment_thickness);
	}

	@Override
	public DataNode setSegment_thicknessScalar(double segment_thickness) {
		return setField(NX_SEGMENT_THICKNESS, segment_thickness);
	}

	@Override
	public IDataset getSegment_gap() {
		return getDataset(NX_SEGMENT_GAP);
	}

	@Override
	public double getSegment_gapScalar() {
		return getDouble(NX_SEGMENT_GAP);
	}

	@Override
	public DataNode setSegment_gap(IDataset segment_gap) {
		return setDataset(NX_SEGMENT_GAP, segment_gap);
	}

	@Override
	public DataNode setSegment_gapScalar(double segment_gap) {
		return setField(NX_SEGMENT_GAP, segment_gap);
	}

	@Override
	public IDataset getSegment_columns() {
		return getDataset(NX_SEGMENT_COLUMNS);
	}

	@Override
	public double getSegment_columnsScalar() {
		return getDouble(NX_SEGMENT_COLUMNS);
	}

	@Override
	public DataNode setSegment_columns(IDataset segment_columns) {
		return setDataset(NX_SEGMENT_COLUMNS, segment_columns);
	}

	@Override
	public DataNode setSegment_columnsScalar(double segment_columns) {
		return setField(NX_SEGMENT_COLUMNS, segment_columns);
	}

	@Override
	public IDataset getSegment_rows() {
		return getDataset(NX_SEGMENT_ROWS);
	}

	@Override
	public double getSegment_rowsScalar() {
		return getDouble(NX_SEGMENT_ROWS);
	}

	@Override
	public DataNode setSegment_rows(IDataset segment_rows) {
		return setDataset(NX_SEGMENT_ROWS, segment_rows);
	}

	@Override
	public DataNode setSegment_rowsScalar(double segment_rows) {
		return setField(NX_SEGMENT_ROWS, segment_rows);
	}

	@Override
	public IDataset getMosaic_horizontal() {
		return getDataset(NX_MOSAIC_HORIZONTAL);
	}

	@Override
	public double getMosaic_horizontalScalar() {
		return getDouble(NX_MOSAIC_HORIZONTAL);
	}

	@Override
	public DataNode setMosaic_horizontal(IDataset mosaic_horizontal) {
		return setDataset(NX_MOSAIC_HORIZONTAL, mosaic_horizontal);
	}

	@Override
	public DataNode setMosaic_horizontalScalar(double mosaic_horizontal) {
		return setField(NX_MOSAIC_HORIZONTAL, mosaic_horizontal);
	}

	@Override
	public IDataset getMosaic_vertical() {
		return getDataset(NX_MOSAIC_VERTICAL);
	}

	@Override
	public double getMosaic_verticalScalar() {
		return getDouble(NX_MOSAIC_VERTICAL);
	}

	@Override
	public DataNode setMosaic_vertical(IDataset mosaic_vertical) {
		return setDataset(NX_MOSAIC_VERTICAL, mosaic_vertical);
	}

	@Override
	public DataNode setMosaic_verticalScalar(double mosaic_vertical) {
		return setField(NX_MOSAIC_VERTICAL, mosaic_vertical);
	}

	@Override
	public IDataset getCurvature_horizontal() {
		return getDataset(NX_CURVATURE_HORIZONTAL);
	}

	@Override
	public double getCurvature_horizontalScalar() {
		return getDouble(NX_CURVATURE_HORIZONTAL);
	}

	@Override
	public DataNode setCurvature_horizontal(IDataset curvature_horizontal) {
		return setDataset(NX_CURVATURE_HORIZONTAL, curvature_horizontal);
	}

	@Override
	public DataNode setCurvature_horizontalScalar(double curvature_horizontal) {
		return setField(NX_CURVATURE_HORIZONTAL, curvature_horizontal);
	}

	@Override
	public IDataset getCurvature_vertical() {
		return getDataset(NX_CURVATURE_VERTICAL);
	}

	@Override
	public double getCurvature_verticalScalar() {
		return getDouble(NX_CURVATURE_VERTICAL);
	}

	@Override
	public DataNode setCurvature_vertical(IDataset curvature_vertical) {
		return setDataset(NX_CURVATURE_VERTICAL, curvature_vertical);
	}

	@Override
	public DataNode setCurvature_verticalScalar(double curvature_vertical) {
		return setField(NX_CURVATURE_VERTICAL, curvature_vertical);
	}

	@Override
	public IDataset getIs_cylindrical() {
		return getDataset(NX_IS_CYLINDRICAL);
	}

	@Override
	public boolean getIs_cylindricalScalar() {
		return getBoolean(NX_IS_CYLINDRICAL);
	}

	@Override
	public DataNode setIs_cylindrical(IDataset is_cylindrical) {
		return setDataset(NX_IS_CYLINDRICAL, is_cylindrical);
	}

	@Override
	public DataNode setIs_cylindricalScalar(boolean is_cylindrical) {
		return setField(NX_IS_CYLINDRICAL, is_cylindrical);
	}

	@Override
	public IDataset getCylindrical_orientation_angle() {
		return getDataset(NX_CYLINDRICAL_ORIENTATION_ANGLE);
	}

	@Override
	public Number getCylindrical_orientation_angleScalar() {
		return getNumber(NX_CYLINDRICAL_ORIENTATION_ANGLE);
	}

	@Override
	public DataNode setCylindrical_orientation_angle(IDataset cylindrical_orientation_angle) {
		return setDataset(NX_CYLINDRICAL_ORIENTATION_ANGLE, cylindrical_orientation_angle);
	}

	@Override
	public DataNode setCylindrical_orientation_angleScalar(Number cylindrical_orientation_angle) {
		return setField(NX_CYLINDRICAL_ORIENTATION_ANGLE, cylindrical_orientation_angle);
	}

	@Override
	public IDataset getPolar_angle() {
		return getDataset(NX_POLAR_ANGLE);
	}

	@Override
	public double getPolar_angleScalar() {
		return getDouble(NX_POLAR_ANGLE);
	}

	@Override
	public DataNode setPolar_angle(IDataset polar_angle) {
		return setDataset(NX_POLAR_ANGLE, polar_angle);
	}

	@Override
	public DataNode setPolar_angleScalar(double polar_angle) {
		return setField(NX_POLAR_ANGLE, polar_angle);
	}

	@Override
	public IDataset getAzimuthal_angle() {
		return getDataset(NX_AZIMUTHAL_ANGLE);
	}

	@Override
	public double getAzimuthal_angleScalar() {
		return getDouble(NX_AZIMUTHAL_ANGLE);
	}

	@Override
	public DataNode setAzimuthal_angle(IDataset azimuthal_angle) {
		return setDataset(NX_AZIMUTHAL_ANGLE, azimuthal_angle);
	}

	@Override
	public DataNode setAzimuthal_angleScalar(double azimuthal_angle) {
		return setField(NX_AZIMUTHAL_ANGLE, azimuthal_angle);
	}

	@Override
	public IDataset getBragg_angle() {
		return getDataset(NX_BRAGG_ANGLE);
	}

	@Override
	public double getBragg_angleScalar() {
		return getDouble(NX_BRAGG_ANGLE);
	}

	@Override
	public DataNode setBragg_angle(IDataset bragg_angle) {
		return setDataset(NX_BRAGG_ANGLE, bragg_angle);
	}

	@Override
	public DataNode setBragg_angleScalar(double bragg_angle) {
		return setField(NX_BRAGG_ANGLE, bragg_angle);
	}

	@Override
	public IDataset getTemperature() {
		return getDataset(NX_TEMPERATURE);
	}

	@Override
	public double getTemperatureScalar() {
		return getDouble(NX_TEMPERATURE);
	}

	@Override
	public DataNode setTemperature(IDataset temperature) {
		return setDataset(NX_TEMPERATURE, temperature);
	}

	@Override
	public DataNode setTemperatureScalar(double temperature) {
		return setField(NX_TEMPERATURE, temperature);
	}

	@Override
	public IDataset getTemperature_coefficient() {
		return getDataset(NX_TEMPERATURE_COEFFICIENT);
	}

	@Override
	public double getTemperature_coefficientScalar() {
		return getDouble(NX_TEMPERATURE_COEFFICIENT);
	}

	@Override
	public DataNode setTemperature_coefficient(IDataset temperature_coefficient) {
		return setDataset(NX_TEMPERATURE_COEFFICIENT, temperature_coefficient);
	}

	@Override
	public DataNode setTemperature_coefficientScalar(double temperature_coefficient) {
		return setField(NX_TEMPERATURE_COEFFICIENT, temperature_coefficient);
	}

	@Override
	public NXlog getTemperature_log() {
		return getChild("temperature_log", NXlog.class);
	}

	@Override
	public void setTemperature_log(NXlog temperature_log) {
		putChild("temperature_log", temperature_log);
	}

	@Override
	public NXdata getReflectivity() {
		return getChild("reflectivity", NXdata.class);
	}

	@Override
	public void setReflectivity(NXdata reflectivity) {
		putChild("reflectivity", reflectivity);
	}

	@Override
	public NXdata getTransmission() {
		return getChild("transmission", NXdata.class);
	}

	@Override
	public void setTransmission(NXdata transmission) {
		putChild("transmission", transmission);
	}

	@Override
	public NXshape getShape() {
		return getChild("shape", NXshape.class);
	}

	@Override
	public void setShape(NXshape shape) {
		putChild("shape", shape);
	}

}
