/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-12T11:55:04.232+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

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
	 public double getScalarFocus_parameters();

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
	 public double getScalarOuter_diameter();

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
	 public double getScalarOutermost_zone_width();

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
	 public double getScalarCentral_stop_diameter();

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
	 public String getScalarFabrication();

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
	 public double getScalarZone_height();

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
	 public String getScalarZone_material();

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
	 public String getScalarZone_support_material();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getCentral_stop_material();	

	/**
	 * 
	 * @return  the value
	 */
	 public String getScalarCentral_stop_material();

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
	 public double getScalarCentral_stop_thickness();

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
	 public double getScalarMask_thickness();

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
	 public String getScalarMask_material();

	/**
	 * 
	 * @return  the value.
	 */
	public IDataset getSupport_membrane_material();	

	/**
	 * 
	 * @return  the value
	 */
	 public String getScalarSupport_membrane_material();

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
	 public double getScalarSupport_membrane_thickness();

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
