/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-10-13T13:58:10.369+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Date;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * Template of a detector, detector bank, or multidetector.
 * <p><b>Symbols:</b> 
 * These symbols will be used below to coordinate datasets with the same shape.<ul>
 * <li><b>np</b> 
 * number of scan points (only present in scanning measurements)</li>
 * <li><b>i</b> 
 * number of detector pixels in the first (X, slowest) direction</li>
 * <li><b>j</b> 
 * number of detector pixels in the second (Y, faster) direction</li>
 * <li><b>k</b> 
 * number of detector pixels in the third (Z, if necessary, fastest) direction</li>
 * <li><b>tof</b> 
 * number of bins in the time-of-flight histogram</li></ul></p>
 * 
 * @version 1.1
 */
public interface NXdetector extends NXobject {

	/**
	 * Total time of flight
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: tof+1;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTime_of_flight();	

	/**
	 * Total time of flight
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME_OF_FLIGHT
	 * <b>Dimensions:</b> 1: tof+1;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getTime_of_flightScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>3</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getTime_of_flightAttributeAxis();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>1</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getTime_of_flightAttributePrimary();	

	/**
	 * Axis label
	 * 
	 * @return  the value.
	 */
	public String getTime_of_flightAttributeLong_name();	

	/**
	 * In DAQ clock pulses
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_PULSES
	 * <b>Dimensions:</b> 1: tof+1;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRaw_time_of_flight();	

	/**
	 * In DAQ clock pulses
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_PULSES
	 * <b>Dimensions:</b> 1: tof+1;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getRaw_time_of_flightScalar();

	/**
	 * Clock frequency in Hz
	 * 
	 * @return  the value.
	 */
	public Number getRaw_time_of_flightAttributeFrequency();	

	/**
	 * Identifier for detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDetector_number();	

	/**
	 * Identifier for detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getDetector_numberScalar();

	/**
	 * Data values from the detector.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j; 4: tof;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData();	

	/**
	 * Data values from the detector.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j; 4: tof;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getDataScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>1</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getDataAttributeSignal();	

	/**
	 * ``[number of scan points,x_offset?,y_offset?,time_of_flight?]``
	 * 
	 * @return  the value.
	 */
	public String getDataAttributeAxes();	

	/**
	 * Title of measurement
	 * 
	 * @return  the value.
	 */
	public String getDataAttributeLong_name();	

	/**
	 * Integral of data as check of data integrity
	 * 
	 * @return  the value.
	 */
	public long getDataAttributeCheck_sum();	

	/**
	 * The best estimate of the uncertainty in the data value. Where
	 * possible, this should be the standard deviation, which has the same units
	 * as the data.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j; 4: tof;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData_error();	

	/**
	 * The best estimate of the uncertainty in the data value. Where
	 * possible, this should be the standard deviation, which has the same units
	 * as the data.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j; 4: tof;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getData_errorScalar();

	/**
	 * Offset from the detector center in x-direction.
	 * Can be multidimensional when needed.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getX_pixel_offset();	

	/**
	 * Offset from the detector center in x-direction.
	 * Can be multidimensional when needed.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getX_pixel_offsetScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>1</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getX_pixel_offsetAttributeAxis();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>1</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getX_pixel_offsetAttributePrimary();	

	/**
	 * Axis label
	 * 
	 * @return  the value.
	 */
	public String getX_pixel_offsetAttributeLong_name();	

	/**
	 * Offset from the detector center in the y-direction.
	 * Can be multidimensional when different values are required for each pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getY_pixel_offset();	

	/**
	 * Offset from the detector center in the y-direction.
	 * Can be multidimensional when different values are required for each pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getY_pixel_offsetScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>2</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getY_pixel_offsetAttributeAxis();	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>1</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getY_pixel_offsetAttributePrimary();	

	/**
	 * Axis label
	 * 
	 * @return  the value.
	 */
	public String getY_pixel_offsetAttributeLong_name();	

	/**
	 * This is the distance to the previous component in the instrument; most often the sample. The usage depends on the
	 * nature of the detector: Most often it is the distance of the detector assembly. But there are irregular detectors. In this
	 * case the distance must be specified for each detector pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();	

	/**
	 * This is the distance to the previous component in the instrument; most often the sample. The usage depends on the
	 * nature of the detector: Most often it is the distance of the detector assembly. But there are irregular detectors. In this
	 * case the distance must be specified for each detector pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDistanceScalar();

	/**
	 * This is the polar angle of the detector towards the previous component in the instrument; most often the sample. The usage depends on the
	 * nature of the detector: Most often it is the polar_angle of the detector assembly. But there are irregular detectors. In this
	 * case the polar_angle must be specified for each detector pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPolar_angle();	

	/**
	 * This is the polar angle of the detector towards the previous component in the instrument; most often the sample. The usage depends on the
	 * nature of the detector: Most often it is the polar_angle of the detector assembly. But there are irregular detectors. In this
	 * case the polar_angle must be specified for each detector pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getPolar_angleScalar();

	/**
	 * This is the azimuthal angle angle of the detector towards the previous component in the instrument; most often the sample. The usage depends on the
	 * nature of the detector: Most often it is the azimuthal_angle of the detector assembly. But there are irregular detectors. In this
	 * case the azimuthal_angle must be specified for each detector pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAzimuthal_angle();	

	/**
	 * This is the azimuthal angle angle of the detector towards the previous component in the instrument; most often the sample. The usage depends on the
	 * nature of the detector: Most often it is the azimuthal_angle of the detector assembly. But there are irregular detectors. In this
	 * case the azimuthal_angle must be specified for each detector pixel.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getAzimuthal_angleScalar();

	/**
	 * name/manufacturer/model/etc. information
	 * 
	 * @return  the value.
	 */
	public IDataset getDescription();	

	/**
	 * name/manufacturer/model/etc. information
	 * 
	 * @return  the value
	 */
	 public String getDescriptionScalar();

	/**
	 * Local name for the detector
	 * 
	 * @return  the value.
	 */
	public IDataset getLocal_name();	

	/**
	 * Local name for the detector
	 * 
	 * @return  the value
	 */
	 public String getLocal_nameScalar();

	/**
	 * Position and orientation of detector
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * Position and orientation of detector</li>
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
	 * Position and orientation of detector</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

	/**
	 * Solid angle subtended by the detector at the sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_SOLID_ANGLE
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSolid_angle();	

	/**
	 * Solid angle subtended by the detector at the sample
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_SOLID_ANGLE
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSolid_angleScalar();

	/**
	 * Size of each detector pixel. If it is scalar all pixels are the same size.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getX_pixel_size();	

	/**
	 * Size of each detector pixel. If it is scalar all pixels are the same size.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getX_pixel_sizeScalar();

	/**
	 * Size of each detector pixel. If it is scalar all pixels are the same size
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getY_pixel_size();	

	/**
	 * Size of each detector pixel. If it is scalar all pixels are the same size
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getY_pixel_sizeScalar();

	/**
	 * Detector dead time
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDead_time();	

	/**
	 * Detector dead time
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: np; 2: i; 3: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDead_timeScalar();

	/**
	 * Detector gas pressure
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGas_pressure();	

	/**
	 * Detector gas pressure
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_PRESSURE
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getGas_pressureScalar();

	/**
	 * maximum drift space dimension
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDetection_gas_path();	

	/**
	 * maximum drift space dimension
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDetection_gas_pathScalar();

	/**
	 * Crate number of detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCrate();	

	/**
	 * Crate number of detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getCrateScalar();

	/**
	 * Equivalent local term
	 * 
	 * @return  the value.
	 */
	public String getCrateAttributeLocal_name();	

	/**
	 * Slot number of detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSlot();	

	/**
	 * Slot number of detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getSlotScalar();

	/**
	 * Equivalent local term
	 * 
	 * @return  the value.
	 */
	public String getSlotAttributeLocal_name();	

	/**
	 * Input number of detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getInput();	

	/**
	 * Input number of detector
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getInputScalar();

	/**
	 * Equivalent local term
	 * 
	 * @return  the value.
	 */
	public String getInputAttributeLocal_name();	

	/**
	 * Description of type such as He3 gas cylinder, He3 PSD, scintillator,
	 * fission chamber, proportion counter, ion chamber, ccd, pixel, image plate,
	 * CMOS, ...
	 * 
	 * @return  the value.
	 */
	public IDataset getType();	

	/**
	 * Description of type such as He3 gas cylinder, He3 PSD, scintillator,
	 * fission chamber, proportion counter, ion chamber, ccd, pixel, image plate,
	 * CMOS, ...
	 * 
	 * @return  the value
	 */
	 public String getTypeScalar();

	/**
	 * Spectral efficiency of detector with respect to e.g. wavelength
	 * 
	 * @return  the value.
	 */
	public NXdata getEfficiency();	

	/**
	 * date of last calibration (geometry and/or efficiency) measurements
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCalibration_date();	

	/**
	 * date of last calibration (geometry and/or efficiency) measurements
	 * <p>
	 * <b>Type:</b> NX_DATE_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Date getCalibration_dateScalar();

	/**
	 * summary of conversion of array data to pixels (e.g. polynomial
	 * approximations) and location of details of the calibrations
	 * 
	 * @return  the value.
	 */
	public NXnote getCalibration_method();	

	/**
	 * How the detector is represented
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>point</b> </li>
	 * <li><b>linear</b> </li>
	 * <li><b>area</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLayout();	

	/**
	 * How the detector is represented
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>point</b> </li>
	 * <li><b>linear</b> </li>
	 * <li><b>area</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getLayoutScalar();

	/**
	 * Elapsed actual counting time
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: np;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCount_time();	

	/**
	 * Elapsed actual counting time
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: np;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public Number getCount_timeScalar();

	/**
	 * 
	 * @return  the value.
	 */
	public NXnote getData_file();	

	/**
	 * :see: https://github.com/nexusformat/definitions/issues/177
	 * 
	 * @deprecated use :ref:`NXcollection` instead
	 * @return  the value.
	 */
	@Deprecated
	public NXcharacterization getCharacterization();	
  
	/**
	 * Get a NXcharacterization node by name:
	 * <ul>
	 * <li>
	 * :see: https://github.com/nexusformat/definitions/issues/177</li>
	 * </ul>
	 * 
	 * @deprecated use :ref:`NXcollection` instead
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcharacterization for that node.
	 */
	@Deprecated
	public NXcharacterization getCharacterization(String name);
	
	/**
	 * Get all NXcharacterization nodes:
	 * <ul>
	 * <li>
	 * :see: https://github.com/nexusformat/definitions/issues/177</li>
	 * </ul>
	 * 
	 * @deprecated use :ref:`NXcollection` instead
	 * @return  a map from node names to the NXcharacterization for that node.
	 */
	@Deprecated
	public Map<String, NXcharacterization> getAllCharacterization();

	/**
	 * Use this group to provide other data related to this NXdetector group.
	 * 
	 * @return  the value.
	 */
	public NXcollection getCollection();	
  
	/**
	 * Get a NXcollection node by name:
	 * <ul>
	 * <li>
	 * Use this group to provide other data related to this NXdetector group.</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXcollection for that node.
	 */
	public NXcollection getCollection(String name);
	
	/**
	 * Get all NXcollection nodes:
	 * <ul>
	 * <li>
	 * Use this group to provide other data related to this NXdetector group.</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXcollection for that node.
	 */
	public Map<String, NXcollection> getAllCollection();

	/**
	 * In order to properly sort the order of the images taken in (for
	 * example) a tomography experiment, a sequence number is stored with each
	 * image.
	 * <p>
	 * <b>Dimensions:</b> 1: nBrightFrames;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSequence_number();	

	/**
	 * In order to properly sort the order of the images taken in (for
	 * example) a tomography experiment, a sequence number is stored with each
	 * image.
	 * <p>
	 * <b>Dimensions:</b> 1: nBrightFrames;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getSequence_numberScalar();

	/**
	 * This is the x position where the direct beam would hit the detector.
	 * This is a length, not a pixel position, and can be outside of the actual
	 * detector.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBeam_center_x();	

	/**
	 * This is the x position where the direct beam would hit the detector.
	 * This is a length, not a pixel position, and can be outside of the actual
	 * detector.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBeam_center_xScalar();

	/**
	 * This is the y position where the direct beam would hit the detector.
	 * This is a length, not a pixel position, and can be outside of the actual
	 * detector.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBeam_center_y();	

	/**
	 * This is the y position where the direct beam would hit the detector.
	 * This is a length, not a pixel position, and can be outside of the actual
	 * detector.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getBeam_center_yScalar();

	/**
	 * This is the start number of the first frame of a scan. In PX one
	 * often scans a couple of frames on a give sample, then does something else,
	 * then returns to the same sample and scans some more frames. Each time with
	 * a new data file. This number helps concatenating such measurements.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFrame_start_number();	

	/**
	 * This is the start number of the first frame of a scan. In PX one
	 * often scans a couple of frames on a give sample, then does something else,
	 * then returns to the same sample and scans some more frames. Each time with
	 * a new data file. This number helps concatenating such measurements.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getFrame_start_numberScalar();

	/**
	 * The diameter of a cylindrical detector
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDiameter();	

	/**
	 * The diameter of a cylindrical detector
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDiameterScalar();

	/**
	 * The acquisition mode of the detector.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>gated</b> </li>
	 * <li><b>triggered</b> </li>
	 * <li><b>summed</b> </li>
	 * <li><b>event</b> </li>
	 * <li><b>histogrammed</b> </li>
	 * <li><b>decimated</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAcquisition_mode();	

	/**
	 * The acquisition mode of the detector.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>gated</b> </li>
	 * <li><b>triggered</b> </li>
	 * <li><b>summed</b> </li>
	 * <li><b>event</b> </li>
	 * <li><b>histogrammed</b> </li>
	 * <li><b>decimated</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getAcquisition_modeScalar();

	/**
	 * True when the angular calibration has been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAngular_calibration_applied();	

	/**
	 * True when the angular calibration has been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getAngular_calibration_appliedScalar();

	/**
	 * Angular calibration data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAngular_calibration();	

	/**
	 * Angular calibration data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getAngular_calibrationScalar();

	/**
	 * True when the flat field correction has been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlatfield_applied();	

	/**
	 * True when the flat field correction has been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getFlatfield_appliedScalar();

	/**
	 * Flat field correction data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlatfield();	

	/**
	 * Flat field correction data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFlatfieldScalar();

	/**
	 * Errors of the flat field correction data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFlatfield_error();	

	/**
	 * Errors of the flat field correction data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFlatfield_errorScalar();

	/**
	 * True when the pixel mask correction has been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPixel_mask_applied();	

	/**
	 * True when the pixel mask correction has been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getPixel_mask_appliedScalar();

	/**
	 * The 32-bit pixel mask for the detector.
	 * Contains a bit field for each pixel to signal dead,
	 * blind or high or otherwise unwanted or undesirable pixels.
	 * They have the following meaning:
	 * .. can't make a table here, a bullet list will have to do for now
	 * * bit 0: gap (pixel with no sensor)
	 * * bit 1: dead
	 * * bit 2: under responding
	 * * bit 3: over responding
	 * * bit 4: noisy
	 * * bit 5: -undefined-
	 * * bit 6: pixel is part of a cluster of problematic pixels (bit set in addition to others)
	 * * bit 7: -undefined-
	 * * bit 8: user defined mask (e.g. around beamstop)
	 * * bits 9-30: -undefined-
	 * * bit 31: virtual pixel (corner pixel with interpolated value)
	 * The normal data analysis software would not take pixels into account when a bit in (mask & 0x00FF) is set.
	 * Tag bit in the upper two bytes would indicate special pixel properties that normally would not be a sole reason to
	 * reject the intensity value (unless lower bits are set as well of course).
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPixel_mask();	

	/**
	 * The 32-bit pixel mask for the detector.
	 * Contains a bit field for each pixel to signal dead,
	 * blind or high or otherwise unwanted or undesirable pixels.
	 * They have the following meaning:
	 * .. can't make a table here, a bullet list will have to do for now
	 * * bit 0: gap (pixel with no sensor)
	 * * bit 1: dead
	 * * bit 2: under responding
	 * * bit 3: over responding
	 * * bit 4: noisy
	 * * bit 5: -undefined-
	 * * bit 6: pixel is part of a cluster of problematic pixels (bit set in addition to others)
	 * * bit 7: -undefined-
	 * * bit 8: user defined mask (e.g. around beamstop)
	 * * bits 9-30: -undefined-
	 * * bit 31: virtual pixel (corner pixel with interpolated value)
	 * The normal data analysis software would not take pixels into account when a bit in (mask & 0x00FF) is set.
	 * Tag bit in the upper two bytes would indicate special pixel properties that normally would not be a sole reason to
	 * reject the intensity value (unless lower bits are set as well of course).
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Dimensions:</b> 1: i; 2: j;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getPixel_maskScalar();

	/**
	 * True when a count-rate correction has already been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCountrate_correction__applied();	

	/**
	 * True when a count-rate correction has already been applied in the
	 * electronics, false otherwise.
	 * <p>
	 * <b>Type:</b> NX_BOOLEAN
	 * </p>
	 * 
	 * @return  the value
	 */
	 public boolean getCountrate_correction__appliedScalar();

	/**
	 * How many bits the electronics reads per pixel.
	 * With CCD's and single photon counting detectors,
	 * this must not align with traditional integer sizes.
	 * This can be 4, 8, 12, 14, 16, ...
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBit_depth_readout();	

	/**
	 * How many bits the electronics reads per pixel.
	 * With CCD's and single photon counting detectors,
	 * this must not align with traditional integer sizes.
	 * This can be 4, 8, 12, 14, 16, ...
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getBit_depth_readoutScalar();

	/**
	 * Time it takes to read the detector (typically milliseconds).
	 * This is important to know for time resolved experiments.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDetector_readout_time();	

	/**
	 * Time it takes to read the detector (typically milliseconds).
	 * This is important to know for time resolved experiments.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getDetector_readout_timeScalar();

	/**
	 * Time it takes to start exposure after a trigger signal has been received.
	 * This is important to know for time resolved experiments.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTrigger_delay_time();	

	/**
	 * Time it takes to start exposure after a trigger signal has been received.
	 * This is important to know for time resolved experiments.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getTrigger_delay_timeScalar();

	/**
	 * Time during which no new trigger signal can be accepted. Typically this is the
	 * trigger_delay_time + exposure_time + readout_time.
	 * This is important to know for time resolved experiments.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTrigger_dead_time();	

	/**
	 * Time during which no new trigger signal can be accepted. Typically this is the
	 * trigger_delay_time + exposure_time + readout_time.
	 * This is important to know for time resolved experiments.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getTrigger_dead_timeScalar();

	/**
	 * This is time for each frame. This is exposure_time + readout time.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: NP;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFrame_time();	

	/**
	 * This is time for each frame. This is exposure_time + readout time.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_TIME
	 * <b>Dimensions:</b> 1: NP;
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getFrame_timeScalar();

	/**
	 * The gain setting of the detector. This influences background etc.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>high</b> </li>
	 * <li><b>standard</b> </li>
	 * <li><b>fast</b> </li>
	 * <li><b>auto</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getGain_setting();	

	/**
	 * The gain setting of the detector. This influences background etc.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>high</b> </li>
	 * <li><b>standard</b> </li>
	 * <li><b>fast</b> </li>
	 * <li><b>auto</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getGain_settingScalar();

	/**
	 * The value at which the detector goes into saturation.
	 * Especially common to CCD detectors, the data is known to be invalid above this value.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSaturation_value();	

	/**
	 * The value at which the detector goes into saturation.
	 * Especially common to CCD detectors, the data is known to be invalid above this value.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getSaturation_valueScalar();

	/**
	 * CCD images are sometimes constructed by summing together multiple short exposures in the
	 * electronics. This reduces background etc. This is the number of short exposures used to sum
	 * images for an image.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNumber_of_cycles();	

	/**
	 * CCD images are sometimes constructed by summing together multiple short exposures in the
	 * electronics. This reduces background etc. This is the number of short exposures used to sum
	 * images for an image.
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value
	 */
	 public long getNumber_of_cyclesScalar();

	/**
	 * At times, radiation is not directly sensed by the detector.
	 * Rather, the detector might sense the output from some
	 * converter like a scintillator.
	 * This is the name of this converter material.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSensor_material();	

	/**
	 * At times, radiation is not directly sensed by the detector.
	 * Rather, the detector might sense the output from some
	 * converter like a scintillator.
	 * This is the name of this converter material.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value
	 */
	 public String getSensor_materialScalar();

	/**
	 * At times, radiation is not directly sensed by the detector.
	 * Rather, the detector might sense the output from some
	 * converter like a scintillator.
	 * This is the thickness of this converter material.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSensor_thickness();	

	/**
	 * At times, radiation is not directly sensed by the detector.
	 * Rather, the detector might sense the output from some
	 * converter like a scintillator.
	 * This is the thickness of this converter material.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getSensor_thicknessScalar();

	/**
	 * Single photon counter detectors can be adjusted for a certain energy range in which they
	 * work optimally. This is the energy setting for this.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getThreshold_energy();	

	/**
	 * Single photon counter detectors can be adjusted for a certain energy range in which they
	 * work optimally. This is the energy setting for this.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value
	 */
	 public double getThreshold_energyScalar();

	/**
	 * For use in special cases where the data in NXdetector
	 * is represented in several parts, each with a separate geometry.
	 * Use one or more instances of the NXdetector_module
	 * group to declare regions of interest or some other
	 * subdivision of a detector.
	 * 
	 * @return  the value.
	 */
	public NXdetector_module getDetector_module();	
  
	/**
	 * Get a NXdetector_module node by name:
	 * <ul>
	 * <li>
	 * For use in special cases where the data in NXdetector
	 * is represented in several parts, each with a separate geometry.
	 * Use one or more instances of the NXdetector_module
	 * group to declare regions of interest or some other
	 * subdivision of a detector.</li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXdetector_module for that node.
	 */
	public NXdetector_module getDetector_module(String name);
	
	/**
	 * Get all NXdetector_module nodes:
	 * <ul>
	 * <li>
	 * For use in special cases where the data in NXdetector
	 * is represented in several parts, each with a separate geometry.
	 * Use one or more instances of the NXdetector_module
	 * group to declare regions of interest or some other
	 * subdivision of a detector.</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXdetector_module for that node.
	 */
	public Map<String, NXdetector_module> getAllDetector_module();

}
