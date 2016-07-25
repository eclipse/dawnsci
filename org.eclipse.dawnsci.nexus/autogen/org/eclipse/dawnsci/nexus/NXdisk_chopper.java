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
 * A device blocking the beam in a temporal periodic pattern.
 * TODO: need documentation
 * 
 * @version 1.0
 */
public interface NXdisk_chopper extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_ROTATION_SPEED = "rotation_speed";
	public static final String NX_SLITS = "slits";
	public static final String NX_SLIT_ANGLE = "slit_angle";
	public static final String NX_PAIR_SEPARATION = "pair_separation";
	public static final String NX_RADIUS = "radius";
	public static final String NX_SLIT_HEIGHT = "slit_height";
	public static final String NX_PHASE = "phase";
	public static final String NX_RATIO = "ratio";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_WAVELENGTH_RANGE = "wavelength_range";
	/**
	 * Type of the disk-chopper: only one from the enumerated list (match text exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Chopper type single</b> </li>
	 * <li><b>contra_rotating_pair</b> </li>
	 * <li><b>synchro_pair</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * Type of the disk-chopper: only one from the enumerated list (match text exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Chopper type single</b> </li>
	 * <li><b>contra_rotating_pair</b> </li>
	 * <li><b>synchro_pair</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * Type of the disk-chopper: only one from the enumerated list (match text exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Chopper type single</b> </li>
	 * <li><b>contra_rotating_pair</b> </li>
	 * <li><b>synchro_pair</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * Type of the disk-chopper: only one from the enumerated list (match text exactly)
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Chopper type single</b> </li>
	 * <li><b>contra_rotating_pair</b> </li>
	 * <li><b>synchro_pair</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * chopper rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRotation_speed();
	
	/**
	 * chopper rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param rotation_speed the rotation_speed
	 */
	public DataNode setRotation_speed(IDataset rotation_speed);

	/**
	 * chopper rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getRotation_speedScalar();

	/**
	 * chopper rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param rotation_speed the rotation_speed
	 */
	public DataNode setRotation_speedScalar(double rotation_speed);

	/**
	 * Number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSlits();
	
	/**
	 * Number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param slits the slits
	 */
	public DataNode setSlits(IDataset slits);

	/**
	 * Number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getSlitsScalar();

	/**
	 * Number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param slits the slits
	 */
	public DataNode setSlitsScalar(long slits);

	/**
	 * angular opening
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSlit_angle();
	
	/**
	 * angular opening
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param slit_angle the slit_angle
	 */
	public DataNode setSlit_angle(IDataset slit_angle);

	/**
	 * angular opening
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSlit_angleScalar();

	/**
	 * angular opening
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param slit_angle the slit_angle
	 */
	public DataNode setSlit_angleScalar(double slit_angle);

	/**
	 * disc spacing in direction of beam
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPair_separation();
	
	/**
	 * disc spacing in direction of beam
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param pair_separation the pair_separation
	 */
	public DataNode setPair_separation(IDataset pair_separation);

	/**
	 * disc spacing in direction of beam
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPair_separationScalar();

	/**
	 * disc spacing in direction of beam
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param pair_separation the pair_separation
	 */
	public DataNode setPair_separationScalar(double pair_separation);

	/**
	 * radius to centre of slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRadius();
	
	/**
	 * radius to centre of slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param radius the radius
	 */
	public DataNode setRadius(IDataset radius);

	/**
	 * radius to centre of slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getRadiusScalar();

	/**
	 * radius to centre of slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param radius the radius
	 */
	public DataNode setRadiusScalar(double radius);

	/**
	 * total slit height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSlit_height();
	
	/**
	 * total slit height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param slit_height the slit_height
	 */
	public DataNode setSlit_height(IDataset slit_height);

	/**
	 * total slit height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSlit_heightScalar();

	/**
	 * total slit height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param slit_height the slit_height
	 */
	public DataNode setSlit_heightScalar(double slit_height);

	/**
	 * chopper phase angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getPhase();
	
	/**
	 * chopper phase angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param phase the phase
	 */
	public DataNode setPhase(IDataset phase);

	/**
	 * chopper phase angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getPhaseScalar();

	/**
	 * chopper phase angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param phase the phase
	 */
	public DataNode setPhaseScalar(double phase);

	/**
	 * pulse reduction factor of this chopper in relation to other
	 * choppers/fastest pulse in the instrument
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRatio();
	
	/**
	 * pulse reduction factor of this chopper in relation to other
	 * choppers/fastest pulse in the instrument
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param ratio the ratio
	 */
	public DataNode setRatio(IDataset ratio);

	/**
	 * pulse reduction factor of this chopper in relation to other
	 * choppers/fastest pulse in the instrument
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getRatioScalar();

	/**
	 * pulse reduction factor of this chopper in relation to other
	 * choppers/fastest pulse in the instrument
	 * <p>
	 * <b>Type:</b> NX_INT
	 * </p>
	 * 
	 * @param ratio the ratio
	 */
	public DataNode setRatioScalar(long ratio);

	/**
	 * Effective distance to the origin
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();
	
	/**
	 * Effective distance to the origin
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistance(IDataset distance);

	/**
	 * Effective distance to the origin
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDistanceScalar();

	/**
	 * Effective distance to the origin
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistanceScalar(double distance);

	/**
	 * low and high values of wavelength range transmitted
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength_range();
	
	/**
	 * low and high values of wavelength range transmitted
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @param wavelength_range the wavelength_range
	 */
	public DataNode setWavelength_range(IDataset wavelength_range);

	/**
	 * low and high values of wavelength range transmitted
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWavelength_rangeScalar();

	/**
	 * low and high values of wavelength range transmitted
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * <b>Dimensions:</b> 1: 2;
	 * </p>
	 * 
	 * @param wavelength_range the wavelength_range
	 */
	public DataNode setWavelength_rangeScalar(double wavelength_range);

	/**
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name  the name of the node.
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public NXgeometry getGeometry(String name);
	
	/**
	 * Set a NXgeometry node by name:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param name the name of the node
	 * @param geometry the value to set
	 */
	public void setGeometry(String name, NXgeometry geometry);
	
	/**
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li></li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

}
