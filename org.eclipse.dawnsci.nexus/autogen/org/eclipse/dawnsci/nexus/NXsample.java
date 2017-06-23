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

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

/**
 * Any information on the sample.
 * This could include scanned variables that
 * are associated with one of the data dimensions, e.g. the magnetic field, or
 * logged data, e.g. monitored temperature vs elapsed time.
 * <p><b>Symbols:</b> 
 * symbolic array lengths to be coordinated between various fields<ul>
 * <li><b>n_comp</b> 
 * number of compositions</li>
 * <li><b>n_Temp</b> 
 * number of temperatures</li>
 * <li><b>n_eField</b> 
 * number of values in applied electric field</li>
 * <li><b>n_mField</b> 
 * number of values in applied magnetic field</li>
 * <li><b>n_pField</b> 
 * number of values in applied pressure field</li>
 * <li><b>n_sField</b> 
 * number of values in applied stress field</li></ul></p>
 * 
 * @version 1.0
 */
public interface NXsample extends NXobject {

	public static final String NX_NAME = "name";
	public static final String NX_CHEMICAL_FORMULA = "chemical_formula";
	public static final String NX_TEMPERATURE = "temperature";
	public static final String NX_ELECTRIC_FIELD = "electric_field";
	public static final String NX_ELECTRIC_FIELD_ATTRIBUTE_DIRECTION = "direction";
	public static final String NX_MAGNETIC_FIELD = "magnetic_field";
	public static final String NX_MAGNETIC_FIELD_ATTRIBUTE_DIRECTION = "direction";
	public static final String NX_STRESS_FIELD = "stress_field";
	public static final String NX_STRESS_FIELD_ATTRIBUTE_DIRECTION = "direction";
	public static final String NX_PRESSURE = "pressure";
	public static final String NX_CHANGER_POSITION = "changer_position";
	public static final String NX_UNIT_CELL_ABC = "unit_cell_abc";
	public static final String NX_UNIT_CELL_ALPHABETAGAMMA = "unit_cell_alphabetagamma";
	public static final String NX_UNIT_CELL = "unit_cell";
	public static final String NX_UNIT_CELL_VOLUME = "unit_cell_volume";
	public static final String NX_SAMPLE_ORIENTATION = "sample_orientation";
	public static final String NX_ORIENTATION_MATRIX = "orientation_matrix";
	public static final String NX_UB_MATRIX = "ub_matrix";
	public static final String NX_MASS = "mass";
	public static final String NX_DENSITY = "density";
	public static final String NX_RELATIVE_MOLECULAR_MASS = "relative_molecular_mass";
	public static final String NX_TYPE = "type";
	public static final String NX_SITUATION = "situation";
	public static final String NX_DESCRIPTION = "description";
	public static final String NX_PREPARATION_DATE = "preparation_date";
	public static final String NX_COMPONENT = "component";
	public static final String NX_SAMPLE_COMPONENT = "sample_component";
	public static final String NX_CONCENTRATION = "concentration";
	public static final String NX_VOLUME_FRACTION = "volume_fraction";
	public static final String NX_SCATTERING_LENGTH_DENSITY = "scattering_length_density";
	public static final String NX_UNIT_CELL_CLASS = "unit_cell_class";
	public static final String NX_SPACE_GROUP = "space_group";
	public static final String NX_POINT_GROUP = "point_group";
	public static final String NX_PATH_LENGTH = "path_length";
	public static final String NX_PATH_LENGTH_WINDOW = "path_length_window";
	public static final String NX_THICKNESS = "thickness";
	public static final String NX_EXTERNAL_DAC = "external_DAC";
	public static final String NX_SHORT_TITLE = "short_title";
	public static final String NX_ROTATION_ANGLE = "rotation_angle";
	public static final String NX_X_TRANSLATION = "x_translation";
	public static final String NX_DISTANCE = "distance";
	/**
	 * Descriptive name of sample
	 * 
	 * @return  the value.
	 */
	public IDataset getName();
	
	/**
	 * Descriptive name of sample
	 * 
	 * @param name the name
	 */
	public DataNode setName(IDataset name);

	/**
	 * Descriptive name of sample
	 * 
	 * @return  the value.
	 */
	public String getNameScalar();

	/**
	 * Descriptive name of sample
	 * 
	 * @param name the name
	 */
	public DataNode setNameScalar(String name);

	/**
	 * The chemical formula specified using CIF conventions.
	 * Abbreviated version of CIF standard:
	 * * Only recognized element symbols may be used.
	 * * Each element symbol is followed by a 'count' number. A count of '1' may be omitted.
	 * * A space or parenthesis must separate each cluster of (element symbol + count).
	 * * Where a group of elements is enclosed in parentheses, the multiplier for the
	 * group must follow the closing parentheses. That is, all element and group
	 * multipliers are assumed to be printed as subscripted numbers.
	 * * Unless the elements are ordered in a manner that corresponds to their chemical
	 * structure, the order of the elements within any group or moiety depends on
	 * whether or not carbon is present.
	 * * If carbon is present, the order should be:
	 * - C, then H, then the other elements in alphabetical order of their symbol.
	 * - If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @return  the value.
	 */
	public IDataset getChemical_formula();
	
	/**
	 * The chemical formula specified using CIF conventions.
	 * Abbreviated version of CIF standard:
	 * * Only recognized element symbols may be used.
	 * * Each element symbol is followed by a 'count' number. A count of '1' may be omitted.
	 * * A space or parenthesis must separate each cluster of (element symbol + count).
	 * * Where a group of elements is enclosed in parentheses, the multiplier for the
	 * group must follow the closing parentheses. That is, all element and group
	 * multipliers are assumed to be printed as subscripted numbers.
	 * * Unless the elements are ordered in a manner that corresponds to their chemical
	 * structure, the order of the elements within any group or moiety depends on
	 * whether or not carbon is present.
	 * * If carbon is present, the order should be:
	 * - C, then H, then the other elements in alphabetical order of their symbol.
	 * - If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @param chemical_formula the chemical_formula
	 */
	public DataNode setChemical_formula(IDataset chemical_formula);

	/**
	 * The chemical formula specified using CIF conventions.
	 * Abbreviated version of CIF standard:
	 * * Only recognized element symbols may be used.
	 * * Each element symbol is followed by a 'count' number. A count of '1' may be omitted.
	 * * A space or parenthesis must separate each cluster of (element symbol + count).
	 * * Where a group of elements is enclosed in parentheses, the multiplier for the
	 * group must follow the closing parentheses. That is, all element and group
	 * multipliers are assumed to be printed as subscripted numbers.
	 * * Unless the elements are ordered in a manner that corresponds to their chemical
	 * structure, the order of the elements within any group or moiety depends on
	 * whether or not carbon is present.
	 * * If carbon is present, the order should be:
	 * - C, then H, then the other elements in alphabetical order of their symbol.
	 * - If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @return  the value.
	 */
	public String getChemical_formulaScalar();

	/**
	 * The chemical formula specified using CIF conventions.
	 * Abbreviated version of CIF standard:
	 * * Only recognized element symbols may be used.
	 * * Each element symbol is followed by a 'count' number. A count of '1' may be omitted.
	 * * A space or parenthesis must separate each cluster of (element symbol + count).
	 * * Where a group of elements is enclosed in parentheses, the multiplier for the
	 * group must follow the closing parentheses. That is, all element and group
	 * multipliers are assumed to be printed as subscripted numbers.
	 * * Unless the elements are ordered in a manner that corresponds to their chemical
	 * structure, the order of the elements within any group or moiety depends on
	 * whether or not carbon is present.
	 * * If carbon is present, the order should be:
	 * - C, then H, then the other elements in alphabetical order of their symbol.
	 * - If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @param chemical_formula the chemical_formula
	 */
	public DataNode setChemical_formulaScalar(String chemical_formula);

	/**
	 * Sample temperature. This could be a scanned variable
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * <b>Dimensions:</b> 1: n_Temp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTemperature();
	
	/**
	 * Sample temperature. This could be a scanned variable
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * <b>Dimensions:</b> 1: n_Temp;
	 * </p>
	 * 
	 * @param temperature the temperature
	 */
	public DataNode setTemperature(IDataset temperature);

	/**
	 * Sample temperature. This could be a scanned variable
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * <b>Dimensions:</b> 1: n_Temp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getTemperatureScalar();

	/**
	 * Sample temperature. This could be a scanned variable
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * <b>Dimensions:</b> 1: n_Temp;
	 * </p>
	 * 
	 * @param temperature the temperature
	 */
	public DataNode setTemperatureScalar(Double temperature);

	/**
	 * Applied electric field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * <b>Dimensions:</b> 1: n_eField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getElectric_field();
	
	/**
	 * Applied electric field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * <b>Dimensions:</b> 1: n_eField;
	 * </p>
	 * 
	 * @param electric_field the electric_field
	 */
	public DataNode setElectric_field(IDataset electric_field);

	/**
	 * Applied electric field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * <b>Dimensions:</b> 1: n_eField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getElectric_fieldScalar();

	/**
	 * Applied electric field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLTAGE
	 * <b>Dimensions:</b> 1: n_eField;
	 * </p>
	 * 
	 * @param electric_field the electric_field
	 */
	public DataNode setElectric_fieldScalar(Double electric_field);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>x</b> </li>
	 * <li><b>y</b> </li>
	 * <li><b>z</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getElectric_fieldAttributeDirection();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>x</b> </li>
	 * <li><b>y</b> </li>
	 * <li><b>z</b> </li></ul></p>
	 * </p>
	 * 
	 * @param direction the direction
	 */
	public void setElectric_fieldAttributeDirection(String direction);

	/**
	 * Applied magnetic field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_mField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMagnetic_field();
	
	/**
	 * Applied magnetic field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_mField;
	 * </p>
	 * 
	 * @param magnetic_field the magnetic_field
	 */
	public DataNode setMagnetic_field(IDataset magnetic_field);

	/**
	 * Applied magnetic field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_mField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getMagnetic_fieldScalar();

	/**
	 * Applied magnetic field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_mField;
	 * </p>
	 * 
	 * @param magnetic_field the magnetic_field
	 */
	public DataNode setMagnetic_fieldScalar(Double magnetic_field);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>x</b> </li>
	 * <li><b>y</b> </li>
	 * <li><b>z</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getMagnetic_fieldAttributeDirection();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>x</b> </li>
	 * <li><b>y</b> </li>
	 * <li><b>z</b> </li></ul></p>
	 * </p>
	 * 
	 * @param direction the direction
	 */
	public void setMagnetic_fieldAttributeDirection(String direction);

	/**
	 * Applied external stress field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_sField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getStress_field();
	
	/**
	 * Applied external stress field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_sField;
	 * </p>
	 * 
	 * @param stress_field the stress_field
	 */
	public DataNode setStress_field(IDataset stress_field);

	/**
	 * Applied external stress field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_sField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getStress_fieldScalar();

	/**
	 * Applied external stress field
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: n_sField;
	 * </p>
	 * 
	 * @param stress_field the stress_field
	 */
	public DataNode setStress_fieldScalar(Double stress_field);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>x</b> </li>
	 * <li><b>y</b> </li>
	 * <li><b>z</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getStress_fieldAttributeDirection();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>x</b> </li>
	 * <li><b>y</b> </li>
	 * <li><b>z</b> </li></ul></p>
	 * </p>
	 * 
	 * @param direction the direction
	 */
	public void setStress_fieldAttributeDirection(String direction);

	/**
	 * Applied pressure
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * <b>Dimensions:</b> 1: n_pField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPressure();
	
	/**
	 * Applied pressure
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * <b>Dimensions:</b> 1: n_pField;
	 * </p>
	 * 
	 * @param pressure the pressure
	 */
	public DataNode setPressure(IDataset pressure);

	/**
	 * Applied pressure
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * <b>Dimensions:</b> 1: n_pField;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getPressureScalar();

	/**
	 * Applied pressure
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * <b>Dimensions:</b> 1: n_pField;
	 * </p>
	 * 
	 * @param pressure the pressure
	 */
	public DataNode setPressureScalar(Double pressure);

	/**
	 * Sample changer position
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getChanger_position();
	
	/**
	 * Sample changer position
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param changer_position the changer_position
	 */
	public DataNode setChanger_position(IDataset changer_position);

	/**
	 * Sample changer position
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Long getChanger_positionScalar();

	/**
	 * Sample changer position
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param changer_position the changer_position
	 */
	public DataNode setChanger_positionScalar(Long changer_position);

	/**
	 * Crystallography unit cell parameters a, b, and c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_abc();
	
	/**
	 * Crystallography unit cell parameters a, b, and c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param unit_cell_abc the unit_cell_abc
	 */
	public DataNode setUnit_cell_abc(IDataset unit_cell_abc);

	/**
	 * Crystallography unit cell parameters a, b, and c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getUnit_cell_abcScalar();

	/**
	 * Crystallography unit cell parameters a, b, and c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param unit_cell_abc the unit_cell_abc
	 */
	public DataNode setUnit_cell_abcScalar(Double unit_cell_abc);

	/**
	 * Crystallography unit cell parameters alpha, beta, and gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_alphabetagamma();
	
	/**
	 * Crystallography unit cell parameters alpha, beta, and gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param unit_cell_alphabetagamma the unit_cell_alphabetagamma
	 */
	public DataNode setUnit_cell_alphabetagamma(IDataset unit_cell_alphabetagamma);

	/**
	 * Crystallography unit cell parameters alpha, beta, and gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getUnit_cell_alphabetagammaScalar();

	/**
	 * Crystallography unit cell parameters alpha, beta, and gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param unit_cell_alphabetagamma the unit_cell_alphabetagamma
	 */
	public DataNode setUnit_cell_alphabetagammaScalar(Double unit_cell_alphabetagamma);

	/**
	 * Unit cell parameters (lengths and angles)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: n_comp; 2: 6;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell();
	
	/**
	 * Unit cell parameters (lengths and angles)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: n_comp; 2: 6;
	 * </p>
	 * 
	 * @param unit_cell the unit_cell
	 */
	public DataNode setUnit_cell(IDataset unit_cell);

	/**
	 * Unit cell parameters (lengths and angles)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: n_comp; 2: 6;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getUnit_cellScalar();

	/**
	 * Unit cell parameters (lengths and angles)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: n_comp; 2: 6;
	 * </p>
	 * 
	 * @param unit_cell the unit_cell
	 */
	public DataNode setUnit_cellScalar(Double unit_cell);

	/**
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_volume();
	
	/**
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param unit_cell_volume the unit_cell_volume
	 */
	public DataNode setUnit_cell_volume(IDataset unit_cell_volume);

	/**
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getUnit_cell_volumeScalar();

	/**
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param unit_cell_volume the unit_cell_volume
	 */
	public DataNode setUnit_cell_volumeScalar(Double unit_cell_volume);

	/**
	 * This will follow the Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSample_orientation();
	
	/**
	 * This will follow the Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param sample_orientation the sample_orientation
	 */
	public DataNode setSample_orientation(IDataset sample_orientation);

	/**
	 * This will follow the Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getSample_orientationScalar();

	/**
	 * This will follow the Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param sample_orientation the sample_orientation
	 */
	public DataNode setSample_orientationScalar(Double sample_orientation);

	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOrientation_matrix();
	
	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @param orientation_matrix the orientation_matrix
	 */
	public DataNode setOrientation_matrix(IDataset orientation_matrix);

	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getOrientation_matrixScalar();

	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @param orientation_matrix the orientation_matrix
	 */
	public DataNode setOrientation_matrixScalar(Double orientation_matrix);

	/**
	 * UB matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464. This is
	 * the multiplication of the orientation_matrix, given above,
	 * with the :math:`B` matrix which
	 * can be derived from the lattice constants.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUb_matrix();
	
	/**
	 * UB matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464. This is
	 * the multiplication of the orientation_matrix, given above,
	 * with the :math:`B` matrix which
	 * can be derived from the lattice constants.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @param ub_matrix the ub_matrix
	 */
	public DataNode setUb_matrix(IDataset ub_matrix);

	/**
	 * UB matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464. This is
	 * the multiplication of the orientation_matrix, given above,
	 * with the :math:`B` matrix which
	 * can be derived from the lattice constants.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getUb_matrixScalar();

	/**
	 * UB matrix of single crystal sample using Busing-Levy convention:
	 * W. R. Busing and H. A. Levy (1967). Acta Cryst. 22, 457-464. This is
	 * the multiplication of the orientation_matrix, given above,
	 * with the :math:`B` matrix which
	 * can be derived from the lattice constants.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @param ub_matrix the ub_matrix
	 */
	public DataNode setUb_matrixScalar(Double ub_matrix);

	/**
	 * Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMass();
	
	/**
	 * Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param mass the mass
	 */
	public DataNode setMass(IDataset mass);

	/**
	 * Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getMassScalar();

	/**
	 * Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param mass the mass
	 */
	public DataNode setMassScalar(Double mass);

	/**
	 * Density of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDensity();
	
	/**
	 * Density of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param density the density
	 */
	public DataNode setDensity(IDataset density);

	/**
	 * Density of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getDensityScalar();

	/**
	 * Density of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param density the density
	 */
	public DataNode setDensityScalar(Double density);

	/**
	 * Relative Molecular Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRelative_molecular_mass();
	
	/**
	 * Relative Molecular Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param relative_molecular_mass the relative_molecular_mass
	 */
	public DataNode setRelative_molecular_mass(IDataset relative_molecular_mass);

	/**
	 * Relative Molecular Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getRelative_molecular_massScalar();

	/**
	 * Relative Molecular Mass of sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param relative_molecular_mass the relative_molecular_mass
	 */
	public DataNode setRelative_molecular_massScalar(Double relative_molecular_mass);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>sample+can</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>sample+buffer</b> </li>
	 * <li><b>buffer</b> </li>
	 * <li><b>calibration sample</b> </li>
	 * <li><b>normalisation sample</b> </li>
	 * <li><b>simulated data</b> </li>
	 * <li><b>none</b> </li>
	 * <li><b>sample environment</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>sample+can</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>sample+buffer</b> </li>
	 * <li><b>buffer</b> </li>
	 * <li><b>calibration sample</b> </li>
	 * <li><b>normalisation sample</b> </li>
	 * <li><b>simulated data</b> </li>
	 * <li><b>none</b> </li>
	 * <li><b>sample environment</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>sample+can</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>sample+buffer</b> </li>
	 * <li><b>buffer</b> </li>
	 * <li><b>calibration sample</b> </li>
	 * <li><b>normalisation sample</b> </li>
	 * <li><b>simulated data</b> </li>
	 * <li><b>none</b> </li>
	 * <li><b>sample environment</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>sample+can</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>sample+buffer</b> </li>
	 * <li><b>buffer</b> </li>
	 * <li><b>calibration sample</b> </li>
	 * <li><b>normalisation sample</b> </li>
	 * <li><b>simulated data</b> </li>
	 * <li><b>none</b> </li>
	 * <li><b>sample environment</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * The atmosphere will be one of the components, which is where
	 * its details will be stored; the relevant components will be
	 * indicated by the entry in the sample_component member.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>air</b> </li>
	 * <li><b>vacuum</b> </li>
	 * <li><b>inert atmosphere</b> </li>
	 * <li><b>oxidising atmosphere</b> </li>
	 * <li><b>reducing atmosphere</b> </li>
	 * <li><b>sealed can</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSituation();
	
	/**
	 * The atmosphere will be one of the components, which is where
	 * its details will be stored; the relevant components will be
	 * indicated by the entry in the sample_component member.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>air</b> </li>
	 * <li><b>vacuum</b> </li>
	 * <li><b>inert atmosphere</b> </li>
	 * <li><b>oxidising atmosphere</b> </li>
	 * <li><b>reducing atmosphere</b> </li>
	 * <li><b>sealed can</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @param situation the situation
	 */
	public DataNode setSituation(IDataset situation);

	/**
	 * The atmosphere will be one of the components, which is where
	 * its details will be stored; the relevant components will be
	 * indicated by the entry in the sample_component member.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>air</b> </li>
	 * <li><b>vacuum</b> </li>
	 * <li><b>inert atmosphere</b> </li>
	 * <li><b>oxidising atmosphere</b> </li>
	 * <li><b>reducing atmosphere</b> </li>
	 * <li><b>sealed can</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getSituationScalar();

	/**
	 * The atmosphere will be one of the components, which is where
	 * its details will be stored; the relevant components will be
	 * indicated by the entry in the sample_component member.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>air</b> </li>
	 * <li><b>vacuum</b> </li>
	 * <li><b>inert atmosphere</b> </li>
	 * <li><b>oxidising atmosphere</b> </li>
	 * <li><b>reducing atmosphere</b> </li>
	 * <li><b>sealed can</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @param situation the situation
	 */
	public DataNode setSituationScalar(String situation);

	/**
	 * Description of the sample
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * Description of the sample
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * Description of the sample
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * Description of the sample
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * Date of preparation of the sample
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPreparation_date();
	
	/**
	 * Date of preparation of the sample
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param preparation_date the preparation_date
	 */
	public DataNode setPreparation_date(IDataset preparation_date);

	/**
	 * Date of preparation of the sample
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Date getPreparation_dateScalar();

	/**
	 * Date of preparation of the sample
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @param preparation_date the preparation_date
	 */
	public DataNode setPreparation_dateScalar(Date preparation_date);

	/**
	 * The position and orientation of the center of mass of the sample
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * The position and orientation of the center of mass of the sample
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);

	/**
	 * Details of beam incident on sample - used to calculate sample/beam interaction point
	 * 
	 * @return  the value.
	 */
	public NXbeam getBeam();
	
	/**
	 * Details of beam incident on sample - used to calculate sample/beam interaction point
	 * 
	 * @param beam the beam
	 */
	public void setBeam(NXbeam beam);
  
	/**
	 * Get a NXbeam node by name:
	 * <ul>
	 * <li>
	 * Details of beam incident on sample - used to calculate sample/beam interaction point</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXbeam for that node.
	 */
	public NXbeam getBeam(String name);
	
	/**
	 * Set a NXbeam node by name:
	 * <ul>
	 * <li>
	 * Details of beam incident on sample - used to calculate sample/beam interaction point</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param beam the value to set
	 */
	public void setBeam(String name, NXbeam beam);
	
	/**
	 * Get all NXbeam nodes:
	 * <ul>
	 * <li>
	 * Details of beam incident on sample - used to calculate sample/beam interaction point</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXbeam for that node.
	 */
	public Map<String, NXbeam> getAllBeam();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Details of beam incident on sample - used to calculate sample/beam interaction point</li>
	 * </ul>
	 * 
	 * @param beam the child nodes to add 
	 */
	
	public void setAllBeam(Map<String, NXbeam> beam);
	

	/**
	 * One group per sample component
	 * This is the perferred way of recording per component information over the n_comp arrays
	 * 
	 * @return  the value.
	 */
	public NXsample_component getSample_componentGroup();
	
	/**
	 * One group per sample component
	 * This is the perferred way of recording per component information over the n_comp arrays
	 * 
	 * @param sample_componentGroup the sample_componentGroup
	 */
	public void setSample_componentGroup(NXsample_component sample_componentGroup);
  
	/**
	 * Get a NXsample_component node by name:
	 * <ul>
	 * <li>
	 * One group per sample component
	 * This is the perferred way of recording per component information over the n_comp arrays</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXsample_component for that node.
	 */
	public NXsample_component getSample_componentGroup(String name);
	
	/**
	 * Set a NXsample_component node by name:
	 * <ul>
	 * <li>
	 * One group per sample component
	 * This is the perferred way of recording per component information over the n_comp arrays</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param sample_componentGroup the value to set
	 */
	public void setSample_componentGroup(String name, NXsample_component sample_componentGroup);
	
	/**
	 * Get all NXsample_component nodes:
	 * <ul>
	 * <li>
	 * One group per sample component
	 * This is the perferred way of recording per component information over the n_comp arrays</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXsample_component for that node.
	 */
	public Map<String, NXsample_component> getAllSample_componentGroup();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * One group per sample component
	 * This is the perferred way of recording per component information over the n_comp arrays</li>
	 * </ul>
	 * 
	 * @param sample_componentGroup the child nodes to add 
	 */
	
	public void setAllSample_componentGroup(Map<String, NXsample_component> sample_componentGroup);
	

	/**
	 * Details of the component of the sample and/or can
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getComponent();
	
	/**
	 * Details of the component of the sample and/or can
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param component the component
	 */
	public DataNode setComponent(IDataset component);

	/**
	 * Details of the component of the sample and/or can
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getComponentScalar();

	/**
	 * Details of the component of the sample and/or can
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param component the component
	 */
	public DataNode setComponentScalar(String component);

	/**
	 * Type of component
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>atmosphere</b> </li>
	 * <li><b>kit</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSample_component();
	
	/**
	 * Type of component
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>atmosphere</b> </li>
	 * <li><b>kit</b> </li></ul></p>
	 * </p>
	 * 
	 * @param sample_component the sample_component
	 */
	public DataNode setSample_component(IDataset sample_component);

	/**
	 * Type of component
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>atmosphere</b> </li>
	 * <li><b>kit</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getSample_componentScalar();

	/**
	 * Type of component
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>can</b> </li>
	 * <li><b>atmosphere</b> </li>
	 * <li><b>kit</b> </li></ul></p>
	 * </p>
	 * 
	 * @param sample_component the sample_component
	 */
	public DataNode setSample_componentScalar(String sample_component);

	/**
	 * Concentration of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getConcentration();
	
	/**
	 * Concentration of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param concentration the concentration
	 */
	public DataNode setConcentration(IDataset concentration);

	/**
	 * Concentration of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getConcentrationScalar();

	/**
	 * Concentration of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_MASS_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param concentration the concentration
	 */
	public DataNode setConcentrationScalar(Double concentration);

	/**
	 * Volume fraction of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getVolume_fraction();
	
	/**
	 * Volume fraction of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param volume_fraction the volume_fraction
	 */
	public DataNode setVolume_fraction(IDataset volume_fraction);

	/**
	 * Volume fraction of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getVolume_fractionScalar();

	/**
	 * Volume fraction of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param volume_fraction the volume_fraction
	 */
	public DataNode setVolume_fractionScalar(Double volume_fraction);

	/**
	 * Scattering length density of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_SCATTERING_LENGTH_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getScattering_length_density();
	
	/**
	 * Scattering length density of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_SCATTERING_LENGTH_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param scattering_length_density the scattering_length_density
	 */
	public DataNode setScattering_length_density(IDataset scattering_length_density);

	/**
	 * Scattering length density of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_SCATTERING_LENGTH_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getScattering_length_densityScalar();

	/**
	 * Scattering length density of each component
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_SCATTERING_LENGTH_DENSITY
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param scattering_length_density the scattering_length_density
	 */
	public DataNode setScattering_length_densityScalar(Double scattering_length_density);

	/**
	 * In case it is all we know and we want to record/document it
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>triclinic</b> </li>
	 * <li><b>monoclinic</b> </li>
	 * <li><b>orthorhombic</b> </li>
	 * <li><b>tetragonal</b> </li>
	 * <li><b>rhombohedral</b> </li>
	 * <li><b>hexagonal</b> </li>
	 * <li><b>cubic</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_class();
	
	/**
	 * In case it is all we know and we want to record/document it
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>triclinic</b> </li>
	 * <li><b>monoclinic</b> </li>
	 * <li><b>orthorhombic</b> </li>
	 * <li><b>tetragonal</b> </li>
	 * <li><b>rhombohedral</b> </li>
	 * <li><b>hexagonal</b> </li>
	 * <li><b>cubic</b> </li></ul></p>
	 * </p>
	 * 
	 * @param unit_cell_class the unit_cell_class
	 */
	public DataNode setUnit_cell_class(IDataset unit_cell_class);

	/**
	 * In case it is all we know and we want to record/document it
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>triclinic</b> </li>
	 * <li><b>monoclinic</b> </li>
	 * <li><b>orthorhombic</b> </li>
	 * <li><b>tetragonal</b> </li>
	 * <li><b>rhombohedral</b> </li>
	 * <li><b>hexagonal</b> </li>
	 * <li><b>cubic</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getUnit_cell_classScalar();

	/**
	 * In case it is all we know and we want to record/document it
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>triclinic</b> </li>
	 * <li><b>monoclinic</b> </li>
	 * <li><b>orthorhombic</b> </li>
	 * <li><b>tetragonal</b> </li>
	 * <li><b>rhombohedral</b> </li>
	 * <li><b>hexagonal</b> </li>
	 * <li><b>cubic</b> </li></ul></p>
	 * </p>
	 * 
	 * @param unit_cell_class the unit_cell_class
	 */
	public DataNode setUnit_cell_classScalar(String unit_cell_class);

	/**
	 * Crystallographic space group
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSpace_group();
	
	/**
	 * Crystallographic space group
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param space_group the space_group
	 */
	public DataNode setSpace_group(IDataset space_group);

	/**
	 * Crystallographic space group
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getSpace_groupScalar();

	/**
	 * Crystallographic space group
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param space_group the space_group
	 */
	public DataNode setSpace_groupScalar(String space_group);

	/**
	 * Crystallographic point group, deprecated if space_group present
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPoint_group();
	
	/**
	 * Crystallographic point group, deprecated if space_group present
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param point_group the point_group
	 */
	public DataNode setPoint_group(IDataset point_group);

	/**
	 * Crystallographic point group, deprecated if space_group present
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getPoint_groupScalar();

	/**
	 * Crystallographic point group, deprecated if space_group present
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param point_group the point_group
	 */
	public DataNode setPoint_groupScalar(String point_group);

	/**
	 * Path length through sample/can for simple case when
	 * it does not vary with scattering direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPath_length();
	
	/**
	 * Path length through sample/can for simple case when
	 * it does not vary with scattering direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param path_length the path_length
	 */
	public DataNode setPath_length(IDataset path_length);

	/**
	 * Path length through sample/can for simple case when
	 * it does not vary with scattering direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getPath_lengthScalar();

	/**
	 * Path length through sample/can for simple case when
	 * it does not vary with scattering direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param path_length the path_length
	 */
	public DataNode setPath_lengthScalar(Double path_length);

	/**
	 * Thickness of a beam entry/exit window on the can (mm)
	 * - assumed same for entry and exit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPath_length_window();
	
	/**
	 * Thickness of a beam entry/exit window on the can (mm)
	 * - assumed same for entry and exit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param path_length_window the path_length_window
	 */
	public DataNode setPath_length_window(IDataset path_length_window);

	/**
	 * Thickness of a beam entry/exit window on the can (mm)
	 * - assumed same for entry and exit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getPath_length_windowScalar();

	/**
	 * Thickness of a beam entry/exit window on the can (mm)
	 * - assumed same for entry and exit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param path_length_window the path_length_window
	 */
	public DataNode setPath_length_windowScalar(Double path_length_window);

	/**
	 * sample thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThickness();
	
	/**
	 * sample thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThickness(IDataset thickness);

	/**
	 * sample thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getThicknessScalar();

	/**
	 * sample thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThicknessScalar(Double thickness);

	/**
	 * As a function of Wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getTransmission();
	
	/**
	 * As a function of Wavelength
	 * 
	 * @param transmission the transmission
	 */
	public void setTransmission(NXdata transmission);

	/**
	 * temperature_log.value is a link to e.g. temperature_env.sensor1.value_log.value
	 * 
	 * @return  the value.
	 */
	public NXlog getTemperature_log();
	
	/**
	 * temperature_log.value is a link to e.g. temperature_env.sensor1.value_log.value
	 * 
	 * @param temperature_log the temperature_log
	 */
	public void setTemperature_log(NXlog temperature_log);

	/**
	 * Additional sample temperature environment information
	 * 
	 * @return  the value.
	 */
	public NXenvironment getTemperature_env();
	
	/**
	 * Additional sample temperature environment information
	 * 
	 * @param temperature_env the temperature_env
	 */
	public void setTemperature_env(NXenvironment temperature_env);

	/**
	 * magnetic_field_log.value is a link to e.g. magnetic_field_env.sensor1.value_log.value
	 * 
	 * @return  the value.
	 */
	public NXlog getMagnetic_field_log();
	
	/**
	 * magnetic_field_log.value is a link to e.g. magnetic_field_env.sensor1.value_log.value
	 * 
	 * @param magnetic_field_log the magnetic_field_log
	 */
	public void setMagnetic_field_log(NXlog magnetic_field_log);

	/**
	 * Additional sample magnetic environment information
	 * 
	 * @return  the value.
	 */
	public NXenvironment getMagnetic_field_env();
	
	/**
	 * Additional sample magnetic environment information
	 * 
	 * @param magnetic_field_env the magnetic_field_env
	 */
	public void setMagnetic_field_env(NXenvironment magnetic_field_env);

	/**
	 * value sent to user's sample setup
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getExternal_DAC();
	
	/**
	 * value sent to user's sample setup
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param external_DAC the external_DAC
	 */
	public DataNode setExternal_DAC(IDataset external_DAC);

	/**
	 * value sent to user's sample setup
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getExternal_DACScalar();

	/**
	 * value sent to user's sample setup
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param external_DAC the external_DAC
	 */
	public DataNode setExternal_DACScalar(Double external_DAC);

	/**
	 * logged value (or logic state) read from user's setup
	 * 
	 * @return  the value.
	 */
	public NXlog getExternal_ADC();
	
	/**
	 * logged value (or logic state) read from user's setup
	 * 
	 * @param external_ADC the external_ADC
	 */
	public void setExternal_ADC(NXlog external_ADC);

	/**
	 * 20 character fixed length sample description for legends
	 * 
	 * @return  the value.
	 */
	public IDataset getShort_title();
	
	/**
	 * 20 character fixed length sample description for legends
	 * 
	 * @param short_title the short_title
	 */
	public DataNode setShort_title(IDataset short_title);

	/**
	 * 20 character fixed length sample description for legends
	 * 
	 * @return  the value.
	 */
	public String getShort_titleScalar();

	/**
	 * 20 character fixed length sample description for legends
	 * 
	 * @param short_title the short_title
	 */
	public DataNode setShort_titleScalar(String short_title);

	/**
	 * Optional rotation angle for the case when the powder diagram has
	 * been obtained through an omega-2theta scan like from a traditional
	 * single detector powder diffractometer
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRotation_angle();
	
	/**
	 * Optional rotation angle for the case when the powder diagram has
	 * been obtained through an omega-2theta scan like from a traditional
	 * single detector powder diffractometer
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param rotation_angle the rotation_angle
	 */
	public DataNode setRotation_angle(IDataset rotation_angle);

	/**
	 * Optional rotation angle for the case when the powder diagram has
	 * been obtained through an omega-2theta scan like from a traditional
	 * single detector powder diffractometer
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getRotation_angleScalar();

	/**
	 * Optional rotation angle for the case when the powder diagram has
	 * been obtained through an omega-2theta scan like from a traditional
	 * single detector powder diffractometer
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param rotation_angle the rotation_angle
	 */
	public DataNode setRotation_angleScalar(Double rotation_angle);

	/**
	 * Translation of the sample along the X-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getX_translation();
	
	/**
	 * Translation of the sample along the X-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param x_translation the x_translation
	 */
	public DataNode setX_translation(IDataset x_translation);

	/**
	 * Translation of the sample along the X-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getX_translationScalar();

	/**
	 * Translation of the sample along the X-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param x_translation the x_translation
	 */
	public DataNode setX_translationScalar(Double x_translation);

	/**
	 * Translation of the sample along the Z-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();
	
	/**
	 * Translation of the sample along the Z-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistance(IDataset distance);

	/**
	 * Translation of the sample along the Z-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Double getDistanceScalar();

	/**
	 * Translation of the sample along the Z-direction of the laboratory coordinate system
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistanceScalar(Double distance);

	/**
	 * Any positioner (motor, PZT, ...) used to locate the sample
	 * 
	 * @return  the value.
	 */
	public NXpositioner getPositioner();
	
	/**
	 * Any positioner (motor, PZT, ...) used to locate the sample
	 * 
	 * @param positioner the positioner
	 */
	public void setPositioner(NXpositioner positioner);
  
	/**
	 * Get a NXpositioner node by name:
	 * <ul>
	 * <li>
	 * Any positioner (motor, PZT, ...) used to locate the sample</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXpositioner for that node.
	 */
	public NXpositioner getPositioner(String name);
	
	/**
	 * Set a NXpositioner node by name:
	 * <ul>
	 * <li>
	 * Any positioner (motor, PZT, ...) used to locate the sample</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param positioner the value to set
	 */
	public void setPositioner(String name, NXpositioner positioner);
	
	/**
	 * Get all NXpositioner nodes:
	 * <ul>
	 * <li>
	 * Any positioner (motor, PZT, ...) used to locate the sample</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXpositioner for that node.
	 */
	public Map<String, NXpositioner> getAllPositioner();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Any positioner (motor, PZT, ...) used to locate the sample</li>
	 * </ul>
	 * 
	 * @param positioner the child nodes to add 
	 */
	
	public void setAllPositioner(Map<String, NXpositioner> positioner);
	

}
