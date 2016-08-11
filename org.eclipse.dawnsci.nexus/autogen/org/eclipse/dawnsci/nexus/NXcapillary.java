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
 * A capillary lens to focus the X-ray beam.
 * Based on information provided by Gerd Wellenreuther (DESY).
 * 
 * @version 1.0
 */
public interface NXcapillary extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_MANUFACTURER = "manufacturer";
	public static final String NX_MAXIMUM_INCIDENT_ANGLE = "maximum_incident_angle";
	public static final String NX_ACCEPTING_APERTURE = "accepting_aperture";
	public static final String NX_WORKING_DISTANCE = "working_distance";
	public static final String NX_FOCAL_SIZE = "focal_size";
	/**
	 * Type of the capillary
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>single_bounce</b> </li>
	 * <li><b>polycapillary</b> </li>
	 * <li><b>conical_capillary</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * Type of the capillary
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>single_bounce</b> </li>
	 * <li><b>polycapillary</b> </li>
	 * <li><b>conical_capillary</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * Type of the capillary
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>single_bounce</b> </li>
	 * <li><b>polycapillary</b> </li>
	 * <li><b>conical_capillary</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * Type of the capillary
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>single_bounce</b> </li>
	 * <li><b>polycapillary</b> </li>
	 * <li><b>conical_capillary</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * The manufacturer of the capillary. This is actually important as
	 * it may have an impact on performance.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getManufacturer();
	
	/**
	 * The manufacturer of the capillary. This is actually important as
	 * it may have an impact on performance.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param manufacturer the manufacturer
	 */
	public DataNode setManufacturer(IDataset manufacturer);

	/**
	 * The manufacturer of the capillary. This is actually important as
	 * it may have an impact on performance.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getManufacturerScalar();

	/**
	 * The manufacturer of the capillary. This is actually important as
	 * it may have an impact on performance.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param manufacturer the manufacturer
	 */
	public DataNode setManufacturerScalar(String manufacturer);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMaximum_incident_angle();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param maximum_incident_angle the maximum_incident_angle
	 */
	public DataNode setMaximum_incident_angle(IDataset maximum_incident_angle);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMaximum_incident_angleScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param maximum_incident_angle the maximum_incident_angle
	 */
	public DataNode setMaximum_incident_angleScalar(double maximum_incident_angle);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAccepting_aperture();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param accepting_aperture the accepting_aperture
	 */
	public DataNode setAccepting_aperture(IDataset accepting_aperture);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getAccepting_apertureScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param accepting_aperture the accepting_aperture
	 */
	public DataNode setAccepting_apertureScalar(double accepting_aperture);

	/**
	 * The gain of the capillary as a function of energy
	 * 
	 * @return  the value.
	 */
	public NXdata getGain();
	
	/**
	 * The gain of the capillary as a function of energy
	 * 
	 * @param gain the gain
	 */
	public void setGain(NXdata gain);

	/**
	 * The transmission of the capillary as a function of energy
	 * 
	 * @return  the value.
	 */
	public NXdata getTransmission();
	
	/**
	 * The transmission of the capillary as a function of energy
	 * 
	 * @param transmission the transmission
	 */
	public void setTransmission(NXdata transmission);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWorking_distance();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param working_distance the working_distance
	 */
	public DataNode setWorking_distance(IDataset working_distance);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWorking_distanceScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param working_distance the working_distance
	 */
	public DataNode setWorking_distanceScalar(double working_distance);

	/**
	 * The focal size in FWHM
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFocal_size();
	
	/**
	 * The focal size in FWHM
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * </p>
	 * 
	 * @param focal_size the focal_size
	 */
	public DataNode setFocal_size(IDataset focal_size);

	/**
	 * The focal size in FWHM
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFocal_sizeScalar();

	/**
	 * The focal size in FWHM
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * </p>
	 * 
	 * @param focal_size the focal_size
	 */
	public DataNode setFocal_sizeScalar(double focal_size);

}
