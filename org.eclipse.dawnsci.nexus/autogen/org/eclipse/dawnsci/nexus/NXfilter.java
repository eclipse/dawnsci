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

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * For band pass beam filters.
 * If uncertain whether to use :ref:`NXfilter` (band-pass filter)
 * or :ref:`NXattenuator` (reduces beam intensity), then use
 * :ref:`NXattenuator`.
 * 
 * @version 1.0
 */
public interface NXfilter extends NXobject {

	public static final String NX_DESCRIPTION = "description";
	public static final String NX_STATUS = "status";
	public static final String NX_TEMPERATURE = "temperature";
	public static final String NX_THICKNESS = "thickness";
	public static final String NX_DENSITY = "density";
	public static final String NX_CHEMICAL_FORMULA = "chemical_formula";
	public static final String NX_UNIT_CELL_A = "unit_cell_a";
	public static final String NX_UNIT_CELL_B = "unit_cell_b";
	public static final String NX_UNIT_CELL_C = "unit_cell_c";
	public static final String NX_UNIT_CELL_ALPHA = "unit_cell_alpha";
	public static final String NX_UNIT_CELL_BETA = "unit_cell_beta";
	public static final String NX_UNIT_CELL_GAMMA = "unit_cell_gamma";
	public static final String NX_UNIT_CELL_VOLUME = "unit_cell_volume";
	public static final String NX_ORIENTATION_MATRIX = "orientation_matrix";
	public static final String NX_M_VALUE = "m_value";
	public static final String NX_SUBSTRATE_MATERIAL = "substrate_material";
	public static final String NX_SUBSTRATE_THICKNESS = "substrate_thickness";
	public static final String NX_COATING_MATERIAL = "coating_material";
	public static final String NX_SUBSTRATE_ROUGHNESS = "substrate_roughness";
	public static final String NX_COATING_ROUGHNESS = "coating_roughness";
	/**
	 * Geometry of the filter
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * Geometry of the filter
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Geometry of the filter</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public NXgeometry getGeometry(String name);
	
	/**
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Geometry of the filter</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param geometry the value to set
	 */
	public void setGeometry(String name, NXgeometry geometry);
	
	/**
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li>
	 * Geometry of the filter</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Geometry of the filter</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

	/**
	 * Composition of the filter. Chemical formula can be specified separately.
	 * This field was changed (2010-11-17) from an enumeration to
	 * a string since common usage showed a wider variety of use
	 * than a simple list. These are the items in the list at
	 * the time of the change: Beryllium | Pyrolytic Graphite |
	 * Graphite | Sapphire | Silicon | Supermirror.
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();
	
	/**
	 * Composition of the filter. Chemical formula can be specified separately.
	 * This field was changed (2010-11-17) from an enumeration to
	 * a string since common usage showed a wider variety of use
	 * than a simple list. These are the items in the list at
	 * the time of the change: Beryllium | Pyrolytic Graphite |
	 * Graphite | Sapphire | Silicon | Supermirror.
	 * 
	 * @param description the description
	 */
	public DataNode setDescription(IDataset description);

	/**
	 * Composition of the filter. Chemical formula can be specified separately.
	 * This field was changed (2010-11-17) from an enumeration to
	 * a string since common usage showed a wider variety of use
	 * than a simple list. These are the items in the list at
	 * the time of the change: Beryllium | Pyrolytic Graphite |
	 * Graphite | Sapphire | Silicon | Supermirror.
	 * 
	 * @return  the value.
	 */
	public String getDescriptionScalar();

	/**
	 * Composition of the filter. Chemical formula can be specified separately.
	 * This field was changed (2010-11-17) from an enumeration to
	 * a string since common usage showed a wider variety of use
	 * than a simple list. These are the items in the list at
	 * the time of the change: Beryllium | Pyrolytic Graphite |
	 * Graphite | Sapphire | Silicon | Supermirror.
	 * 
	 * @param description the description
	 */
	public DataNode setDescriptionScalar(String description);

	/**
	 * position with respect to in or out of the beam (choice of only "in" or "out")
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>in</b> 
	 * in the beam</li>
	 * <li><b>out</b> 
	 * out of the beam</li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getStatus();
	
	/**
	 * position with respect to in or out of the beam (choice of only "in" or "out")
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>in</b> 
	 * in the beam</li>
	 * <li><b>out</b> 
	 * out of the beam</li></ul></p>
	 * </p>
	 * 
	 * @param status the status
	 */
	public DataNode setStatus(IDataset status);

	/**
	 * position with respect to in or out of the beam (choice of only "in" or "out")
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>in</b> 
	 * in the beam</li>
	 * <li><b>out</b> 
	 * out of the beam</li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getStatusScalar();

	/**
	 * position with respect to in or out of the beam (choice of only "in" or "out")
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>in</b> 
	 * in the beam</li>
	 * <li><b>out</b> 
	 * out of the beam</li></ul></p>
	 * </p>
	 * 
	 * @param status the status
	 */
	public DataNode setStatusScalar(String status);

	/**
	 * Wavelength transmission profile of filter
	 * 
	 * @return  the value.
	 */
	public NXdata getTransmission();
	
	/**
	 * Wavelength transmission profile of filter
	 * 
	 * @param transmission the transmission
	 */
	public void setTransmission(NXdata transmission);

	/**
	 * average/nominal filter temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTemperature();
	
	/**
	 * average/nominal filter temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @param temperature the temperature
	 */
	public DataNode setTemperature(IDataset temperature);

	/**
	 * average/nominal filter temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTemperatureScalar();

	/**
	 * average/nominal filter temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @param temperature the temperature
	 */
	public DataNode setTemperatureScalar(double temperature);

	/**
	 * Linked temperature_log for the filter
	 * 
	 * @return  the value.
	 */
	public NXlog getTemperature_log();
	
	/**
	 * Linked temperature_log for the filter
	 * 
	 * @param temperature_log the temperature_log
	 */
	public void setTemperature_log(NXlog temperature_log);

	/**
	 * Thickness of the filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThickness();
	
	/**
	 * Thickness of the filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThickness(IDataset thickness);

	/**
	 * Thickness of the filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getThicknessScalar();

	/**
	 * Thickness of the filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThicknessScalar(double thickness);

	/**
	 * mass density of the filter
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDensity();
	
	/**
	 * mass density of the filter
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @param density the density
	 */
	public DataNode setDensity(IDataset density);

	/**
	 * mass density of the filter
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getDensityScalar();

	/**
	 * mass density of the filter
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @param density the density
	 */
	public DataNode setDensityScalar(Number density);

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
	 * * C, then H, then the other elements in alphabetical order of their symbol.
	 * * If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
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
	 * * C, then H, then the other elements in alphabetical order of their symbol.
	 * * If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
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
	 * * C, then H, then the other elements in alphabetical order of their symbol.
	 * * If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
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
	 * * C, then H, then the other elements in alphabetical order of their symbol.
	 * * If carbon is not present, the elements are listed purely in alphabetic order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @param chemical_formula the chemical_formula
	 */
	public DataNode setChemical_formulaScalar(String chemical_formula);

	/**
	 * Sensor(s)used to monitor the filter temperature
	 * 
	 * @return  the value.
	 */
	public NXsensor getSensor_type();
	
	/**
	 * Sensor(s)used to monitor the filter temperature
	 * 
	 * @param sensor_type the sensor_type
	 */
	public void setSensor_type(NXsensor sensor_type);

	/**
	 * Unit cell lattice parameter: length of side a
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_a();
	
	/**
	 * Unit cell lattice parameter: length of side a
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param unit_cell_a the unit_cell_a
	 */
	public DataNode setUnit_cell_a(IDataset unit_cell_a);

	/**
	 * Unit cell lattice parameter: length of side a
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_aScalar();

	/**
	 * Unit cell lattice parameter: length of side a
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param unit_cell_a the unit_cell_a
	 */
	public DataNode setUnit_cell_aScalar(double unit_cell_a);

	/**
	 * Unit cell lattice parameter: length of side b
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_b();
	
	/**
	 * Unit cell lattice parameter: length of side b
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param unit_cell_b the unit_cell_b
	 */
	public DataNode setUnit_cell_b(IDataset unit_cell_b);

	/**
	 * Unit cell lattice parameter: length of side b
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_bScalar();

	/**
	 * Unit cell lattice parameter: length of side b
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param unit_cell_b the unit_cell_b
	 */
	public DataNode setUnit_cell_bScalar(double unit_cell_b);

	/**
	 * Unit cell lattice parameter: length of side c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_c();
	
	/**
	 * Unit cell lattice parameter: length of side c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param unit_cell_c the unit_cell_c
	 */
	public DataNode setUnit_cell_c(IDataset unit_cell_c);

	/**
	 * Unit cell lattice parameter: length of side c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_cScalar();

	/**
	 * Unit cell lattice parameter: length of side c
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param unit_cell_c the unit_cell_c
	 */
	public DataNode setUnit_cell_cScalar(double unit_cell_c);

	/**
	 * Unit cell lattice parameter: angle alpha
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_alpha();
	
	/**
	 * Unit cell lattice parameter: angle alpha
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param unit_cell_alpha the unit_cell_alpha
	 */
	public DataNode setUnit_cell_alpha(IDataset unit_cell_alpha);

	/**
	 * Unit cell lattice parameter: angle alpha
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_alphaScalar();

	/**
	 * Unit cell lattice parameter: angle alpha
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param unit_cell_alpha the unit_cell_alpha
	 */
	public DataNode setUnit_cell_alphaScalar(double unit_cell_alpha);

	/**
	 * Unit cell lattice parameter: angle beta
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_beta();
	
	/**
	 * Unit cell lattice parameter: angle beta
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param unit_cell_beta the unit_cell_beta
	 */
	public DataNode setUnit_cell_beta(IDataset unit_cell_beta);

	/**
	 * Unit cell lattice parameter: angle beta
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_betaScalar();

	/**
	 * Unit cell lattice parameter: angle beta
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param unit_cell_beta the unit_cell_beta
	 */
	public DataNode setUnit_cell_betaScalar(double unit_cell_beta);

	/**
	 * Unit cell lattice parameter: angle gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_gamma();
	
	/**
	 * Unit cell lattice parameter: angle gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param unit_cell_gamma the unit_cell_gamma
	 */
	public DataNode setUnit_cell_gamma(IDataset unit_cell_gamma);

	/**
	 * Unit cell lattice parameter: angle gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_gammaScalar();

	/**
	 * Unit cell lattice parameter: angle gamma
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param unit_cell_gamma the unit_cell_gamma
	 */
	public DataNode setUnit_cell_gammaScalar(double unit_cell_gamma);

	/**
	 * Unit cell
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
	 * Unit cell
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
	 * Unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_volumeScalar();

	/**
	 * Unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * <b>Dimensions:</b> 1: n_comp;
	 * </p>
	 * 
	 * @param unit_cell_volume the unit_cell_volume
	 */
	public DataNode setUnit_cell_volumeScalar(double unit_cell_volume);

	/**
	 * Orientation matrix of single crystal filter using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOrientation_matrix();
	
	/**
	 * Orientation matrix of single crystal filter using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @param orientation_matrix the orientation_matrix
	 */
	public DataNode setOrientation_matrix(IDataset orientation_matrix);

	/**
	 * Orientation matrix of single crystal filter using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getOrientation_matrixScalar();

	/**
	 * Orientation matrix of single crystal filter using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: n_comp; 2: 3; 3: 3;
	 * </p>
	 * 
	 * @param orientation_matrix the orientation_matrix
	 */
	public DataNode setOrientation_matrixScalar(double orientation_matrix);

	/**
	 * m value of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getM_value();
	
	/**
	 * m value of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @param m_value the m_value
	 */
	public DataNode setM_value(IDataset m_value);

	/**
	 * m value of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getM_valueScalar();

	/**
	 * m value of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_DIMENSIONLESS
	 * </p>
	 * 
	 * @param m_value the m_value
	 */
	public DataNode setM_valueScalar(double m_value);

	/**
	 * substrate material of supermirror filter
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_material();
	
	/**
	 * substrate material of supermirror filter
	 * 
	 * @param substrate_material the substrate_material
	 */
	public DataNode setSubstrate_material(IDataset substrate_material);

	/**
	 * substrate material of supermirror filter
	 * 
	 * @return  the value.
	 */
	public String getSubstrate_materialScalar();

	/**
	 * substrate material of supermirror filter
	 * 
	 * @param substrate_material the substrate_material
	 */
	public DataNode setSubstrate_materialScalar(String substrate_material);

	/**
	 * substrate thickness of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_thickness();
	
	/**
	 * substrate thickness of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param substrate_thickness the substrate_thickness
	 */
	public DataNode setSubstrate_thickness(IDataset substrate_thickness);

	/**
	 * substrate thickness of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSubstrate_thicknessScalar();

	/**
	 * substrate thickness of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param substrate_thickness the substrate_thickness
	 */
	public DataNode setSubstrate_thicknessScalar(double substrate_thickness);

	/**
	 * coating material of supermirror filter
	 * 
	 * @return  the value.
	 */
	public IDataset getCoating_material();
	
	/**
	 * coating material of supermirror filter
	 * 
	 * @param coating_material the coating_material
	 */
	public DataNode setCoating_material(IDataset coating_material);

	/**
	 * coating material of supermirror filter
	 * 
	 * @return  the value.
	 */
	public String getCoating_materialScalar();

	/**
	 * coating material of supermirror filter
	 * 
	 * @param coating_material the coating_material
	 */
	public DataNode setCoating_materialScalar(String coating_material);

	/**
	 * substrate roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSubstrate_roughness();
	
	/**
	 * substrate roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param substrate_roughness the substrate_roughness
	 */
	public DataNode setSubstrate_roughness(IDataset substrate_roughness);

	/**
	 * substrate roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSubstrate_roughnessScalar();

	/**
	 * substrate roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param substrate_roughness the substrate_roughness
	 */
	public DataNode setSubstrate_roughnessScalar(double substrate_roughness);

	/**
	 * coating roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCoating_roughness();
	
	/**
	 * coating roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param coating_roughness the coating_roughness
	 */
	public DataNode setCoating_roughness(IDataset coating_roughness);

	/**
	 * coating roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCoating_roughnessScalar();

	/**
	 * coating roughness (RMS) of supermirror filter
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: nsurf;
	 * </p>
	 * 
	 * @param coating_roughness the coating_roughness
	 */
	public DataNode setCoating_roughnessScalar(double coating_roughness);

}
