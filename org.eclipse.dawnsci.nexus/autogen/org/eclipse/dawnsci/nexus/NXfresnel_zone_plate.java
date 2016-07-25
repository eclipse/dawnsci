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
 * A fresnel zone plate
 * 
 * @version 1.0
 */
public interface NXfresnel_zone_plate extends NXobject {

	public static final String NX_FOCUS_PARAMETERS = "focus_parameters";
	public static final String NX_OUTER_DIAMETER = "outer_diameter";
	public static final String NX_OUTERMOST_ZONE_WIDTH = "outermost_zone_width";
	public static final String NX_CENTRAL_STOP_DIAMETER = "central_stop_diameter";
	public static final String NX_FABRICATION = "fabrication";
	public static final String NX_ZONE_HEIGHT = "zone_height";
	public static final String NX_ZONE_MATERIAL = "zone_material";
	public static final String NX_ZONE_SUPPORT_MATERIAL = "zone_support_material";
	public static final String NX_CENTRAL_STOP_MATERIAL = "central_stop_material";
	public static final String NX_CENTRAL_STOP_THICKNESS = "central_stop_thickness";
	public static final String NX_MASK_THICKNESS = "mask_thickness";
	public static final String NX_MASK_MATERIAL = "mask_material";
	public static final String NX_SUPPORT_MEMBRANE_MATERIAL = "support_membrane_material";
	public static final String NX_SUPPORT_MEMBRANE_THICKNESS = "support_membrane_thickness";
	/**
	 * list of polynomial coefficients describing the focal length of the zone plate, in increasing powers of photon energy,
	 * that describes the focal length of the zone plate (in microns) at an X-ray photon energy (in electron volts).
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFocus_parameters();
	
	/**
	 * list of polynomial coefficients describing the focal length of the zone plate, in increasing powers of photon energy,
	 * that describes the focal length of the zone plate (in microns) at an X-ray photon energy (in electron volts).
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b>
	 * </p>
	 * 
	 * @param focus_parameters the focus_parameters
	 */
	public DataNode setFocus_parameters(IDataset focus_parameters);

	/**
	 * list of polynomial coefficients describing the focal length of the zone plate, in increasing powers of photon energy,
	 * that describes the focal length of the zone plate (in microns) at an X-ray photon energy (in electron volts).
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFocus_parametersScalar();

	/**
	 * list of polynomial coefficients describing the focal length of the zone plate, in increasing powers of photon energy,
	 * that describes the focal length of the zone plate (in microns) at an X-ray photon energy (in electron volts).
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b>
	 * </p>
	 * 
	 * @param focus_parameters the focus_parameters
	 */
	public DataNode setFocus_parametersScalar(double focus_parameters);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOuter_diameter();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param outer_diameter the outer_diameter
	 */
	public DataNode setOuter_diameter(IDataset outer_diameter);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getOuter_diameterScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param outer_diameter the outer_diameter
	 */
	public DataNode setOuter_diameterScalar(double outer_diameter);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOutermost_zone_width();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param outermost_zone_width the outermost_zone_width
	 */
	public DataNode setOutermost_zone_width(IDataset outermost_zone_width);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getOutermost_zone_widthScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param outermost_zone_width the outermost_zone_width
	 */
	public DataNode setOutermost_zone_widthScalar(double outermost_zone_width);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCentral_stop_diameter();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param central_stop_diameter the central_stop_diameter
	 */
	public DataNode setCentral_stop_diameter(IDataset central_stop_diameter);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCentral_stop_diameterScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param central_stop_diameter the central_stop_diameter
	 */
	public DataNode setCentral_stop_diameterScalar(double central_stop_diameter);

	/**
	 * how the zone plate was manufactured
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>etched</b> </li>
	 * <li><b>plated</b> </li>
	 * <li><b>zone doubled</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFabrication();
	
	/**
	 * how the zone plate was manufactured
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>etched</b> </li>
	 * <li><b>plated</b> </li>
	 * <li><b>zone doubled</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @param fabrication the fabrication
	 */
	public DataNode setFabrication(IDataset fabrication);

	/**
	 * how the zone plate was manufactured
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>etched</b> </li>
	 * <li><b>plated</b> </li>
	 * <li><b>zone doubled</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getFabricationScalar();

	/**
	 * how the zone plate was manufactured
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>etched</b> </li>
	 * <li><b>plated</b> </li>
	 * <li><b>zone doubled</b> </li>
	 * <li><b>other</b> </li></ul></p>
	 * </p>
	 * 
	 * @param fabrication the fabrication
	 */
	public DataNode setFabricationScalar(String fabrication);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getZone_height();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param zone_height the zone_height
	 */
	public DataNode setZone_height(IDataset zone_height);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getZone_heightScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param zone_height the zone_height
	 */
	public DataNode setZone_heightScalar(double zone_height);

	/**
	 * Material of the zones themselves
	 * 
	 * @return  the value.
	 */
	public IDataset getZone_material();
	
	/**
	 * Material of the zones themselves
	 * 
	 * @param zone_material the zone_material
	 */
	public DataNode setZone_material(IDataset zone_material);

	/**
	 * Material of the zones themselves
	 * 
	 * @return  the value.
	 */
	public String getZone_materialScalar();

	/**
	 * Material of the zones themselves
	 * 
	 * @param zone_material the zone_material
	 */
	public DataNode setZone_materialScalar(String zone_material);

	/**
	 * Material present between the zones. This is usually only present for the "zone doubled" fabrication process
	 * 
	 * @return  the value.
	 */
	public IDataset getZone_support_material();
	
	/**
	 * Material present between the zones. This is usually only present for the "zone doubled" fabrication process
	 * 
	 * @param zone_support_material the zone_support_material
	 */
	public DataNode setZone_support_material(IDataset zone_support_material);

	/**
	 * Material present between the zones. This is usually only present for the "zone doubled" fabrication process
	 * 
	 * @return  the value.
	 */
	public String getZone_support_materialScalar();

	/**
	 * Material present between the zones. This is usually only present for the "zone doubled" fabrication process
	 * 
	 * @param zone_support_material the zone_support_material
	 */
	public DataNode setZone_support_materialScalar(String zone_support_material);

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getCentral_stop_material();
	
	/**
	 * 
	 * @param central_stop_material the central_stop_material
	 */
	public DataNode setCentral_stop_material(IDataset central_stop_material);

	/**
	 * 
	 * @return  the value.
	 */
	public String getCentral_stop_materialScalar();

	/**
	 * 
	 * @param central_stop_material the central_stop_material
	 */
	public DataNode setCentral_stop_materialScalar(String central_stop_material);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCentral_stop_thickness();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param central_stop_thickness the central_stop_thickness
	 */
	public DataNode setCentral_stop_thickness(IDataset central_stop_thickness);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCentral_stop_thicknessScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param central_stop_thickness the central_stop_thickness
	 */
	public DataNode setCentral_stop_thicknessScalar(double central_stop_thickness);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMask_thickness();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param mask_thickness the mask_thickness
	 */
	public DataNode setMask_thickness(IDataset mask_thickness);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMask_thicknessScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param mask_thickness the mask_thickness
	 */
	public DataNode setMask_thicknessScalar(double mask_thickness);

	/**
	 * If no mask is present, set mask_thickness to 0 and omit the mask_material field
	 * 
	 * @return  the value.
	 */
	public IDataset getMask_material();
	
	/**
	 * If no mask is present, set mask_thickness to 0 and omit the mask_material field
	 * 
	 * @param mask_material the mask_material
	 */
	public DataNode setMask_material(IDataset mask_material);

	/**
	 * If no mask is present, set mask_thickness to 0 and omit the mask_material field
	 * 
	 * @return  the value.
	 */
	public String getMask_materialScalar();

	/**
	 * If no mask is present, set mask_thickness to 0 and omit the mask_material field
	 * 
	 * @param mask_material the mask_material
	 */
	public DataNode setMask_materialScalar(String mask_material);

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getSupport_membrane_material();
	
	/**
	 * 
	 * @param support_membrane_material the support_membrane_material
	 */
	public DataNode setSupport_membrane_material(IDataset support_membrane_material);

	/**
	 * 
	 * @return  the value.
	 */
	public String getSupport_membrane_materialScalar();

	/**
	 * 
	 * @param support_membrane_material the support_membrane_material
	 */
	public DataNode setSupport_membrane_materialScalar(String support_membrane_material);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSupport_membrane_thickness();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param support_membrane_thickness the support_membrane_thickness
	 */
	public DataNode setSupport_membrane_thickness(IDataset support_membrane_thickness);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSupport_membrane_thicknessScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param support_membrane_thickness the support_membrane_thickness
	 */
	public DataNode setSupport_membrane_thicknessScalar(double support_membrane_thickness);

	/**
	 * "Engineering" position of the fresnel zone plate
	 * 
	 * @return  the value.
	 */
	public NXtransformations getTransformations();
	
	/**
	 * "Engineering" position of the fresnel zone plate
	 * 
	 * @param transformations the transformations
	 */
	public void setTransformations(NXtransformations transformations);
  
	/**
	 * Get a NXtransformations node by name:
	 * <ul>
	 * <li>
	 * "Engineering" position of the fresnel zone plate</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXtransformations for that node.
	 */
	public NXtransformations getTransformations(String name);
	
	/**
	 * Set a NXtransformations node by name:
	 * <ul>
	 * <li>
	 * "Engineering" position of the fresnel zone plate</li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param transformations the value to set
	 */
	public void setTransformations(String name, NXtransformations transformations);
	
	/**
	 * Get all NXtransformations nodes:
	 * <ul>
	 * <li>
	 * "Engineering" position of the fresnel zone plate</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXtransformations for that node.
	 */
	public Map<String, NXtransformations> getAllTransformations();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * "Engineering" position of the fresnel zone plate</li>
	 * </ul>
	 * 
	 * @param transformations the child nodes to add 
	 */
	
	public void setAllTransformations(Map<String, NXtransformations> transformations);
	

}
