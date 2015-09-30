/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-29T13:43:53.722+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of the state of the sample. This could include scanned variables that
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

	/**
	 * Descriptive name of sample
	 * 
	 * @return  the value.
	 */
	public IDataset getName();

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
	 * This will follow the Busing and Levy convention from Acta.Crysta v22, p457 (1967)
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
	 * Orientation matrix of single crystal sample.
	 * This will follow the Busing and Levy convention from Acta.Crysta v22, p457 (1967)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOrientation_matrix();

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
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>sample</b> </li>
	 * <li><b>sample+can</b> </li>
	 * <li><b>can</b> </li>
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
	 * Description of the sample
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();

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
	 * The position and orientation of the center of mass of the sample
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();

	/**
	 * Details of beam incident on sample - used to calculate sample/beam interaction point
	 * 
	 * @return  the value.
	 */
	public NXbeam getBeam();
  
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
	 * Details of the component of the sample and/or can
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getComponent();

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
	 * In case it is all we know and we want to record/document it
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>cubic</b> </li>
	 * <li><b>tetragonal</b> </li>
	 * <li><b>orthorhombic</b> </li>
	 * <li><b>monoclinic</b> </li>
	 * <li><b>triclinic</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_class();

	/**
	 * Crystallographic point or space group
	 * <p>
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_group();

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
	 * As a function of Wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getTransmission();

	/**
	 * temperature_log.value is a link to e.g. temperature_env.sensor1.value_log.value
	 * 
	 * @return  the value.
	 */
	public NXlog getTemperature_log();

	/**
	 * Additional sample temperature environment information
	 * 
	 * @return  the value.
	 */
	public NXenvironment getTemperature_env();

	/**
	 * magnetic_field_log.value is a link to e.g. magnetic_field_env.sensor1.value_log.value
	 * 
	 * @return  the value.
	 */
	public NXlog getMagnetic_field_log();

	/**
	 * Additional sample magnetic environment information
	 * 
	 * @return  the value.
	 */
	public NXenvironment getMagnetic_field_env();

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
	 * logged value (or logic state) read from user's setup
	 * 
	 * @return  the value.
	 */
	public NXlog getExternal_ADC();

	/**
	 * 20 character fixed length sample description for legends
	 * 
	 * @return  the value.
	 */
	public IDataset getShort_title();

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
	 * Any positioner (motor, PZT, ...) used to locate the sample
	 * 
	 * @return  the value.
	 */
	public NXpositioner getPositioner();
  
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
	 * Get all NXpositioner nodes:
	 * <ul>
	 * <li>
	 * Any positioner (motor, PZT, ...) used to locate the sample</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXpositioner for that node.
	 */
	public Map<String, NXpositioner> getAllPositioner();

}
