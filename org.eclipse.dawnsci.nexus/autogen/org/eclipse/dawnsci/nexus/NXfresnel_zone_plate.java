/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-12-14T18:05:35.255Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * description for a fresnel zone plate
 * 
 * @version 1.0
 */
public interface NXfresnel_zone_plate extends NXobject {

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
	 * @return  the value
	 */
	 public double getFocus_parametersScalar();

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
	 * @return  the value
	 */
	 public double getOuter_diameterScalar();

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
	 * @return  the value
	 */
	 public double getOutermost_zone_widthScalar();

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
	 * @return  the value
	 */
	 public double getCentral_stop_diameterScalar();

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
	 * @return  the value
	 */
	 public String getFabricationScalar();

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
	 * @return  the value
	 */
	 public double getZone_heightScalar();

	/**
	 * Material of the zones themselves
	 * 
	 * @return  the value.
	 */
	public IDataset getZone_material();	

	/**
	 * Material of the zones themselves
	 * 
	 * @return  the value
	 */
	 public String getZone_materialScalar();

	/**
	 * Material present between the zones. This is usually only present for the "zone doubled" fabrication process
	 * 
	 * @return  the value.
	 */
	public IDataset getZone_support_material();	

	/**
	 * Material present between the zones. This is usually only present for the "zone doubled" fabrication process
	 * 
	 * @return  the value
	 */
	 public String getZone_support_materialScalar();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getCentral_stop_material();	

	/**
	 * 
	 * @return  the value
	 */
	 public String getCentral_stop_materialScalar();

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
	 * @return  the value
	 */
	 public double getCentral_stop_thicknessScalar();

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
	 * @return  the value
	 */
	 public double getMask_thicknessScalar();

	/**
	 * If no mask is present, set mask_thickness to 0 and omit the mask_material field
	 * 
	 * @return  the value.
	 */
	public IDataset getMask_material();	

	/**
	 * If no mask is present, set mask_thickness to 0 and omit the mask_material field
	 * 
	 * @return  the value
	 */
	 public String getMask_materialScalar();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getSupport_membrane_material();	

	/**
	 * 
	 * @return  the value
	 */
	 public String getSupport_membrane_materialScalar();

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
	 * @return  the value
	 */
	 public double getSupport_membrane_thicknessScalar();

	/**
	 * "Engineering" position of the fresnel zone plate
	 * 
	 * @return  the value.
	 */
	public NXtransformations getTransformations();	
  
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
	 * Get all NXtransformations nodes:
	 * <ul>
	 * <li>
	 * "Engineering" position of the fresnel zone plate</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXtransformations for that node.
	 */
	public Map<String, NXtransformations> getAllTransformations();

}
