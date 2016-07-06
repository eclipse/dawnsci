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

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.january.dataset.IDataset;

/**
 * An X-ray lens, typically at a synchrotron X-ray beam line.
 * Based on information provided by Gerd Wellenreuther (DESY).
 * 
 * @version 1.0
 */
public interface NXxraylens extends NXobject {

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
	/**
	 * Geometry of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>paraboloid</b> </li>
	 * <li><b>spherical</b> </li>
	 * <li><b>elliptical</b> </li>
	 * <li><b>hyperbolical</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_geometry();
	
	/**
	 * Geometry of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>paraboloid</b> </li>
	 * <li><b>spherical</b> </li>
	 * <li><b>elliptical</b> </li>
	 * <li><b>hyperbolical</b> </li></ul></p>
	 * </p>
	 * 
	 * @param lens_geometry the lens_geometry
	 */
	public DataNode setLens_geometry(IDataset lens_geometry);

	/**
	 * Geometry of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>paraboloid</b> </li>
	 * <li><b>spherical</b> </li>
	 * <li><b>elliptical</b> </li>
	 * <li><b>hyperbolical</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getLens_geometryScalar();

	/**
	 * Geometry of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>paraboloid</b> </li>
	 * <li><b>spherical</b> </li>
	 * <li><b>elliptical</b> </li>
	 * <li><b>hyperbolical</b> </li></ul></p>
	 * </p>
	 * 
	 * @param lens_geometry the lens_geometry
	 */
	public DataNode setLens_geometryScalar(String lens_geometry);

	/**
	 * Is the device symmetric?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSymmetric();
	
	/**
	 * Is the device symmetric?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param symmetric the symmetric
	 */
	public DataNode setSymmetric(IDataset symmetric);

	/**
	 * Is the device symmetric?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public boolean getSymmetricScalar();

	/**
	 * Is the device symmetric?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param symmetric the symmetric
	 */
	public DataNode setSymmetricScalar(boolean symmetric);

	/**
	 * Is the device cylindrical?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCylindrical();
	
	/**
	 * Is the device cylindrical?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param cylindrical the cylindrical
	 */
	public DataNode setCylindrical(IDataset cylindrical);

	/**
	 * Is the device cylindrical?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public boolean getCylindricalScalar();

	/**
	 * Is the device cylindrical?
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @param cylindrical the cylindrical
	 */
	public DataNode setCylindricalScalar(boolean cylindrical);

	/**
	 * Orientation of the cylinder axis.
	 * 
	 * @return  the value.
	 */
	public NXnote getCylinder_orientation();
	
	/**
	 * Orientation of the cylinder axis.
	 * 
	 * @param cylinder_orientation the cylinder_orientation
	 */
	public void setCylinder_orientation(NXnote cylinder_orientation);

	/**
	 * The type of focus of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>line</b> </li>
	 * <li><b>point</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFocus_type();
	
	/**
	 * The type of focus of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>line</b> </li>
	 * <li><b>point</b> </li></ul></p>
	 * </p>
	 * 
	 * @param focus_type the focus_type
	 */
	public DataNode setFocus_type(IDataset focus_type);

	/**
	 * The type of focus of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>line</b> </li>
	 * <li><b>point</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getFocus_typeScalar();

	/**
	 * The type of focus of the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>line</b> </li>
	 * <li><b>point</b> </li></ul></p>
	 * </p>
	 * 
	 * @param focus_type the focus_type
	 */
	public DataNode setFocus_typeScalar(String focus_type);

	/**
	 * Thickness of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_thickness();
	
	/**
	 * Thickness of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param lens_thickness the lens_thickness
	 */
	public DataNode setLens_thickness(IDataset lens_thickness);

	/**
	 * Thickness of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getLens_thicknessScalar();

	/**
	 * Thickness of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param lens_thickness the lens_thickness
	 */
	public DataNode setLens_thicknessScalar(double lens_thickness);

	/**
	 * Length of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_length();
	
	/**
	 * Length of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param lens_length the lens_length
	 */
	public DataNode setLens_length(IDataset lens_length);

	/**
	 * Length of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getLens_lengthScalar();

	/**
	 * Length of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param lens_length the lens_length
	 */
	public DataNode setLens_lengthScalar(double lens_length);

	/**
	 * Radius of the curvature as measured in the middle of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCurvature();
	
	/**
	 * Radius of the curvature as measured in the middle of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param curvature the curvature
	 */
	public DataNode setCurvature(IDataset curvature);

	/**
	 * Radius of the curvature as measured in the middle of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCurvatureScalar();

	/**
	 * Radius of the curvature as measured in the middle of the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param curvature the curvature
	 */
	public DataNode setCurvatureScalar(double curvature);

	/**
	 * Diameter of the lens.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAperture();
	
	/**
	 * Diameter of the lens.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param aperture the aperture
	 */
	public DataNode setAperture(IDataset aperture);

	/**
	 * Diameter of the lens.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getApertureScalar();

	/**
	 * Diameter of the lens.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param aperture the aperture
	 */
	public DataNode setApertureScalar(double aperture);

	/**
	 * Number of lenses that make up the compound lens.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNumber_of_lenses();
	
	/**
	 * Number of lenses that make up the compound lens.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param number_of_lenses the number_of_lenses
	 */
	public DataNode setNumber_of_lenses(IDataset number_of_lenses);

	/**
	 * Number of lenses that make up the compound lens.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getNumber_of_lensesScalar();

	/**
	 * Number of lenses that make up the compound lens.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param number_of_lenses the number_of_lenses
	 */
	public DataNode setNumber_of_lensesScalar(long number_of_lenses);

	/**
	 * Material used to make the lens.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLens_material();
	
	/**
	 * Material used to make the lens.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param lens_material the lens_material
	 */
	public DataNode setLens_material(IDataset lens_material);

	/**
	 * Material used to make the lens.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getLens_materialScalar();

	/**
	 * Material used to make the lens.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param lens_material the lens_material
	 */
	public DataNode setLens_materialScalar(String lens_material);

	/**
	 * Gas used to fill the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGas();
	
	/**
	 * Gas used to fill the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param gas the gas
	 */
	public DataNode setGas(IDataset gas);

	/**
	 * Gas used to fill the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getGasScalar();

	/**
	 * Gas used to fill the lens
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param gas the gas
	 */
	public DataNode setGasScalar(String gas);

	/**
	 * Gas pressure in the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGas_pressure();
	
	/**
	 * Gas pressure in the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * </p>
	 * 
	 * @param gas_pressure the gas_pressure
	 */
	public DataNode setGas_pressure(IDataset gas_pressure);

	/**
	 * Gas pressure in the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getGas_pressureScalar();

	/**
	 * Gas pressure in the lens
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * </p>
	 * 
	 * @param gas_pressure the gas_pressure
	 */
	public DataNode setGas_pressureScalar(double gas_pressure);

}
