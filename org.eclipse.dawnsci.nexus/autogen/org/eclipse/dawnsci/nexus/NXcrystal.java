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
 * A crystal monochromator or analyzer.
 * Permits double bent
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
	/**
	 * Position of crystal
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * Position of crystal
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
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
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Position of crystal</li>
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
	 * Position of crystal</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * Position of crystal</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

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
	 * @param usage the usage
	 */
	public DataNode setUsage(IDataset usage);

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
	public String getUsageScalar();

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
	 * @param usage the usage
	 */
	public DataNode setUsageScalar(String usage);

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
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

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
	public String getTypeScalar();

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
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

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
	 * C, then H, then the other elements in alphabetical order of their symbol.
	 * If carbon is not present, the elements are listed purely in alphabetic
	 * order of their symbol.
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
	 * C, then H, then the other elements in alphabetical order of their symbol.
	 * If carbon is not present, the elements are listed purely in alphabetic
	 * order of their symbol.
	 * * This is the *Hill* system used by Chemical Abstracts.
	 * 
	 * @param chemical_formula the chemical_formula
	 */
	public DataNode setChemical_formulaScalar(String chemical_formula);

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
	 * A number which describes if this is the first, second,..
	 * :math:`n^{th}` crystal in a multi crystal monochromator
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param order_no the order_no
	 */
	public DataNode setOrder_no(IDataset order_no);

	/**
	 * A number which describes if this is the first, second,..
	 * :math:`n^{th}` crystal in a multi crystal monochromator
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getOrder_noScalar();

	/**
	 * A number which describes if this is the first, second,..
	 * :math:`n^{th}` crystal in a multi crystal monochromator
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param order_no the order_no
	 */
	public DataNode setOrder_noScalar(long order_no);

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
	 * Cut angle of reflecting Bragg plane and plane of crystal surface
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param cut_angle the cut_angle
	 */
	public DataNode setCut_angle(IDataset cut_angle);

	/**
	 * Cut angle of reflecting Bragg plane and plane of crystal surface
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCut_angleScalar();

	/**
	 * Cut angle of reflecting Bragg plane and plane of crystal surface
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param cut_angle the cut_angle
	 */
	public DataNode setCut_angleScalar(double cut_angle);

	/**
	 * Space group of crystal structure
	 * 
	 * @return  the value.
	 */
	public IDataset getSpace_group();
	
	/**
	 * Space group of crystal structure
	 * 
	 * @param space_group the space_group
	 */
	public DataNode setSpace_group(IDataset space_group);

	/**
	 * Space group of crystal structure
	 * 
	 * @return  the value.
	 */
	public String getSpace_groupScalar();

	/**
	 * Space group of crystal structure
	 * 
	 * @param space_group the space_group
	 */
	public DataNode setSpace_groupScalar(String space_group);

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
	public double getUnit_cellScalar();

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
	public DataNode setUnit_cellScalar(double unit_cell);

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
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
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
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getUnit_cell_volumeScalar();

	/**
	 * Volume of the unit cell
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_VOLUME
	 * </p>
	 * 
	 * @param unit_cell_volume the unit_cell_volume
	 */
	public DataNode setUnit_cell_volumeScalar(double unit_cell_volume);

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
	 * Orientation matrix of single crystal sample using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: 3; 2: 3;
	 * </p>
	 * 
	 * @param orientation_matrix the orientation_matrix
	 */
	public DataNode setOrientation_matrix(IDataset orientation_matrix);

	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: 3; 2: 3;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getOrientation_matrixScalar();

	/**
	 * Orientation matrix of single crystal sample using Busing-Levy convention
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: 3; 2: 3;
	 * </p>
	 * 
	 * @param orientation_matrix the orientation_matrix
	 */
	public DataNode setOrientation_matrixScalar(double orientation_matrix);

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
	 * Optimum diffracted wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelength(IDataset wavelength);

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
	public double getWavelengthScalar();

	/**
	 * Optimum diffracted wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelengthScalar(double wavelength);

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
	 * spacing between crystal planes of the reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param d_spacing the d_spacing
	 */
	public DataNode setD_spacing(IDataset d_spacing);

	/**
	 * spacing between crystal planes of the reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getD_spacingScalar();

	/**
	 * spacing between crystal planes of the reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param d_spacing the d_spacing
	 */
	public DataNode setD_spacingScalar(double d_spacing);

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
	 * Scattering vector, Q, of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVENUMBER
	 * </p>
	 * 
	 * @param scattering_vector the scattering_vector
	 */
	public DataNode setScattering_vector(IDataset scattering_vector);

	/**
	 * Scattering vector, Q, of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVENUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getScattering_vectorScalar();

	/**
	 * Scattering vector, Q, of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVENUMBER
	 * </p>
	 * 
	 * @param scattering_vector the scattering_vector
	 */
	public DataNode setScattering_vectorScalar(double scattering_vector);

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
	 * Miller indices (hkl) values of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param reflection the reflection
	 */
	public DataNode setReflection(IDataset reflection);

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
	public long getReflectionScalar();

	/**
	 * Miller indices (hkl) values of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * <b>Dimensions:</b> 1: 3;
	 * </p>
	 * 
	 * @param reflection the reflection
	 */
	public DataNode setReflectionScalar(long reflection);

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
	 * Thickness of the crystal. (Required for Laue orientations - see "usage" field)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThickness(IDataset thickness);

	/**
	 * Thickness of the crystal. (Required for Laue orientations - see "usage" field)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getThicknessScalar();

	/**
	 * Thickness of the crystal. (Required for Laue orientations - see "usage" field)
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param thickness the thickness
	 */
	public DataNode setThicknessScalar(double thickness);

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
	 * mass density of the crystal
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @param density the density
	 */
	public DataNode setDensity(IDataset density);

	/**
	 * mass density of the crystal
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getDensityScalar();

	/**
	 * mass density of the crystal
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_MASS_DENSITY
	 * </p>
	 * 
	 * @param density the density
	 */
	public DataNode setDensityScalar(Number density);

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
	 * Horizontal width of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_width the segment_width
	 */
	public DataNode setSegment_width(IDataset segment_width);

	/**
	 * Horizontal width of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSegment_widthScalar();

	/**
	 * Horizontal width of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_width the segment_width
	 */
	public DataNode setSegment_widthScalar(double segment_width);

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
	 * Vertical height of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_height the segment_height
	 */
	public DataNode setSegment_height(IDataset segment_height);

	/**
	 * Vertical height of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSegment_heightScalar();

	/**
	 * Vertical height of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_height the segment_height
	 */
	public DataNode setSegment_heightScalar(double segment_height);

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
	 * Thickness of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_thickness the segment_thickness
	 */
	public DataNode setSegment_thickness(IDataset segment_thickness);

	/**
	 * Thickness of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSegment_thicknessScalar();

	/**
	 * Thickness of individual segment
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_thickness the segment_thickness
	 */
	public DataNode setSegment_thicknessScalar(double segment_thickness);

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
	 * Typical gap between adjacent segments
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_gap the segment_gap
	 */
	public DataNode setSegment_gap(IDataset segment_gap);

	/**
	 * Typical gap between adjacent segments
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSegment_gapScalar();

	/**
	 * Typical gap between adjacent segments
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_gap the segment_gap
	 */
	public DataNode setSegment_gapScalar(double segment_gap);

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
	 * number of segment columns in horizontal direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_columns the segment_columns
	 */
	public DataNode setSegment_columns(IDataset segment_columns);

	/**
	 * number of segment columns in horizontal direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSegment_columnsScalar();

	/**
	 * number of segment columns in horizontal direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_columns the segment_columns
	 */
	public DataNode setSegment_columnsScalar(double segment_columns);

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
	 * number of segment rows in vertical direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_rows the segment_rows
	 */
	public DataNode setSegment_rows(IDataset segment_rows);

	/**
	 * number of segment rows in vertical direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSegment_rowsScalar();

	/**
	 * number of segment rows in vertical direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param segment_rows the segment_rows
	 */
	public DataNode setSegment_rowsScalar(double segment_rows);

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
	 * horizontal mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param mosaic_horizontal the mosaic_horizontal
	 */
	public DataNode setMosaic_horizontal(IDataset mosaic_horizontal);

	/**
	 * horizontal mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMosaic_horizontalScalar();

	/**
	 * horizontal mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param mosaic_horizontal the mosaic_horizontal
	 */
	public DataNode setMosaic_horizontalScalar(double mosaic_horizontal);

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
	 * vertical mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param mosaic_vertical the mosaic_vertical
	 */
	public DataNode setMosaic_vertical(IDataset mosaic_vertical);

	/**
	 * vertical mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMosaic_verticalScalar();

	/**
	 * vertical mosaic Full Width Half Maximum
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param mosaic_vertical the mosaic_vertical
	 */
	public DataNode setMosaic_verticalScalar(double mosaic_vertical);

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
	 * Horizontal curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param curvature_horizontal the curvature_horizontal
	 */
	public DataNode setCurvature_horizontal(IDataset curvature_horizontal);

	/**
	 * Horizontal curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCurvature_horizontalScalar();

	/**
	 * Horizontal curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param curvature_horizontal the curvature_horizontal
	 */
	public DataNode setCurvature_horizontalScalar(double curvature_horizontal);

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
	 * Vertical curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param curvature_vertical the curvature_vertical
	 */
	public DataNode setCurvature_vertical(IDataset curvature_vertical);

	/**
	 * Vertical curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCurvature_verticalScalar();

	/**
	 * Vertical curvature of focusing crystal
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param curvature_vertical the curvature_vertical
	 */
	public DataNode setCurvature_verticalScalar(double curvature_vertical);

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
	 * Is this crystal bent cylindrically?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param is_cylindrical the is_cylindrical
	 */
	public DataNode setIs_cylindrical(IDataset is_cylindrical);

	/**
	 * Is this crystal bent cylindrically?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public boolean getIs_cylindricalScalar();

	/**
	 * Is this crystal bent cylindrically?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param is_cylindrical the is_cylindrical
	 */
	public DataNode setIs_cylindricalScalar(boolean is_cylindrical);

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
	 * If cylindrical: cylinder orientation angle
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param cylindrical_orientation_angle the cylindrical_orientation_angle
	 */
	public DataNode setCylindrical_orientation_angle(IDataset cylindrical_orientation_angle);

	/**
	 * If cylindrical: cylinder orientation angle
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getCylindrical_orientation_angleScalar();

	/**
	 * If cylindrical: cylinder orientation angle
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param cylindrical_orientation_angle the cylindrical_orientation_angle
	 */
	public DataNode setCylindrical_orientation_angleScalar(Number cylindrical_orientation_angle);

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
	 * Polar (scattering) angle at which crystal assembly is positioned.
	 * Note: some instrument geometries call this term 2theta.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param polar_angle the polar_angle
	 */
	public DataNode setPolar_angle(IDataset polar_angle);

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
	public double getPolar_angleScalar();

	/**
	 * Polar (scattering) angle at which crystal assembly is positioned.
	 * Note: some instrument geometries call this term 2theta.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param polar_angle the polar_angle
	 */
	public DataNode setPolar_angleScalar(double polar_angle);

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
	 * Azimuthal angle at which crystal assembly is positioned
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param azimuthal_angle the azimuthal_angle
	 */
	public DataNode setAzimuthal_angle(IDataset azimuthal_angle);

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
	public double getAzimuthal_angleScalar();

	/**
	 * Azimuthal angle at which crystal assembly is positioned
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param azimuthal_angle the azimuthal_angle
	 */
	public DataNode setAzimuthal_angleScalar(double azimuthal_angle);

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
	 * Bragg angle of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param bragg_angle the bragg_angle
	 */
	public DataNode setBragg_angle(IDataset bragg_angle);

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
	public double getBragg_angleScalar();

	/**
	 * Bragg angle of nominal reflection
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: i;
	 * </p>
	 * 
	 * @param bragg_angle the bragg_angle
	 */
	public DataNode setBragg_angleScalar(double bragg_angle);

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
	 * average/nominal crystal temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @param temperature the temperature
	 */
	public DataNode setTemperature(IDataset temperature);

	/**
	 * average/nominal crystal temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTemperatureScalar();

	/**
	 * average/nominal crystal temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TEMPERATURE
	 * </p>
	 * 
	 * @param temperature the temperature
	 */
	public DataNode setTemperatureScalar(double temperature);

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
	 * how lattice parameter changes with temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param temperature_coefficient the temperature_coefficient
	 */
	public DataNode setTemperature_coefficient(IDataset temperature_coefficient);

	/**
	 * how lattice parameter changes with temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTemperature_coefficientScalar();

	/**
	 * how lattice parameter changes with temperature
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * </p>
	 * 
	 * @param temperature_coefficient the temperature_coefficient
	 */
	public DataNode setTemperature_coefficientScalar(double temperature_coefficient);

	/**
	 * log file of crystal temperature
	 * 
	 * @return  the value.
	 */
	public NXlog getTemperature_log();
	
	/**
	 * log file of crystal temperature
	 * 
	 * @param temperature_log the temperature_log
	 */
	public void setTemperature_log(NXlog temperature_log);

	/**
	 * crystal reflectivity versus wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getReflectivity();
	
	/**
	 * crystal reflectivity versus wavelength
	 * 
	 * @param reflectivity the reflectivity
	 */
	public void setReflectivity(NXdata reflectivity);

	/**
	 * crystal transmission versus wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getTransmission();
	
	/**
	 * crystal transmission versus wavelength
	 * 
	 * @param transmission the transmission
	 */
	public void setTransmission(NXdata transmission);

	/**
	 * A NXshape group describing the shape of the crystal arrangement
	 * 
	 * @return  the value.
	 */
	public NXshape getShape();
	
	/**
	 * A NXshape group describing the shape of the crystal arrangement
	 * 
	 * @param shape the shape
	 */
	public void setShape(NXshape shape);

}
