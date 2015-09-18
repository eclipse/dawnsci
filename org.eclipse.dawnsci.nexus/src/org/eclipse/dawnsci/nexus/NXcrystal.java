/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-18T11:52:16.514+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of a crystal monochromator or analyzer. Permits double bent
 * monochromator comprised of multiple segments with anisotropic
 * Gaussian mosaic.
 * If curvatures are set to zero or are absent, array
 * is considered to be flat.
 * Scattering vector is perpendicular to surface. Crystal is oriented
 * parallel to beam incident on crystal before rotation, and lies in
 * vertical plane.
 * <p><b>Symbols:</b> 
 * These symbols will be used below to coordinate dimensions with the same lengths.<ul>
 * <li><b>n_comp</b> 
 * number of different unit cells to be described</li>
 * <li><b>i</b> 
 * number of wavelengths</li></ul></p>
 * 
 * @version 1.0
 */
public interface NXcrystal extends NXobject {

	/**
	 * Position of crystal
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Position of crystal</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public NXgeometry getGeometry(String name);
	
	/**
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li>
	 * Position of crystal</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * How this crystal is used. Choices are in the list.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Bragg</b> 
	 * reflection geometry</li>
	 * <li><b>Laue</b> 
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
	 * C, then H, then the other elements in alphabetical order of their symbol.
	 * If carbon is not present, the elements are listed purely in alphabetic
	 * order of their symbol.
	 * This is the *Hill* system used by Chemical Abstracts.
	 * See, for example:
	 * http://www.iucr.org/__data/iucr/cif/standard/cifstd15.html,
	 * http://www.cas.org/training/stneasytips/subinforformula1.html,
	 * or
	 * http://www.indiana.edu/~cheminfo/courses/471cnfs.html.</li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUsage();

	/**
	 * Type or material of monochromating substance.
	 * Chemical formula can be specified separately.
	 * Use the "reflection" field to indicate the (hkl) orientation.
	 * Use the "d_spacing" field to record the lattice plane spacing.
	 * This field was changed (2010-11-17) from an enumeration to
	 * a string since common usage showed a wider variety of use
	 * than a simple list. These are the items in the list at
	 * the time of the change: PG (Highly Oriented Pyrolytic Graphite) |
	 * Ge | Si | Cu | Fe3Si | CoFe | Cu2MnAl (Heusler) | Multilayer |
	 * Diamond.
	 * 
	 * @return  the value.
	 */
	public IDataset getType();

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
	 * C, then H, then the other elements in alphabetical order of their symbol.
	 * If carbon is not present, the elements are listed purely in alphabetic
	 * order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @return  the value.
	 */
	public IDataset getChemical_formula();

	/**
	 * A number which describes if this is the first, second,..
	 * :math:`n^{th}` crystal in a multi crystal monochromator
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOrder_no();

	/**
	 * Cut angle of reflecting Bragg plane and plane of crystal surface
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCut_angle();

	/**
	 * Space group of crystal structure
	 * 
	 * @return  the value.
	 */
	public IDataset getSpace_group();

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
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getUnit_cell_volume();

	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: 3; 2: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOrientation_matrix();

	/**
	 * Optimum diffracted wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength();

	/**
	 * spacing between crystal planes of the reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getD_spacing();

	/**
	 * Scattering vector, Q, of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVENUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getScattering_vector();

	/**
	 * Miller indices (hkl) values of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getReflection();

	/**
	 * Thickness of the crystal. (Required for Laue orientations - see "usage" field)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThickness();

	/**
	 * mass density of the crystal
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDensity();

	/**
	 * Horizontal width of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSegment_width();

	/**
	 * Vertical height of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSegment_height();

	/**
	 * Thickness of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSegment_thickness();

	/**
	 * Typical gap between adjacent segments
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSegment_gap();

	/**
	 * number of segment columns in horizontal direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSegment_columns();

	/**
	 * number of segment rows in vertical direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSegment_rows();

	/**
	 * horizontal mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMosaic_horizontal();

	/**
	 * vertical mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMosaic_vertical();

	/**
	 * Horizontal curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCurvature_horizontal();

	/**
	 * Vertical curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCurvature_vertical();

	/**
	 * Is this crystal bent cylindrically?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getIs_cylindrical();

	/**
	 * If cylindrical: cylinder orientation angle
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCylindrical_orientation_angle();

	/**
	 * Polar (scattering) angle at which crystal assembly is positioned.
	 * Note: some instrument geometries call this term 2theta.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPolar_angle();

	/**
	 * Azimuthal angle at which crystal assembly is positioned
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAzimuthal_angle();

	/**
	 * Bragg angle of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBragg_angle();

	/**
	 * average/nominal crystal temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTemperature();

	/**
	 * how lattice parameter changes with temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTemperature_coefficient();

	/**
	 * log file of crystal temperature
	 * 
	 * @return  the value.
	 */
	public NXlog getTemperature_log();

	/**
	 * crystal reflectivity versus wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getReflectivity();

	/**
	 * crystal transmission versus wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getTransmission();

	/**
	 * A NXshape group describing the shape of the crystal arrangement
	 * 
	 * @return  the value.
	 */
	public NXshape getShape();

}
