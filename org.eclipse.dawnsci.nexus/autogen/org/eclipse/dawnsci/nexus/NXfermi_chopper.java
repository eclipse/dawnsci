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
 * A Fermi chopper, possibly with curved slits.
 * 
 * @version 1.0
 */
public interface NXfermi_chopper extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_ROTATION_SPEED = "rotation_speed";
	public static final String NX_RADIUS = "radius";
	public static final String NX_SLIT = "slit";
	public static final String NX_R_SLIT = "r_slit";
	public static final String NX_NUMBER = "number";
	public static final String NX_HEIGHT = "height";
	public static final String NX_WIDTH = "width";
	public static final String NX_DISTANCE = "distance";
	public static final String NX_WAVELENGTH = "wavelength";
	public static final String NX_ENERGY = "energy";
	public static final String NX_ABSORBING_MATERIAL = "absorbing_material";
	public static final String NX_TRANSMITTING_MATERIAL = "transmitting_material";
	/**
	 * Fermi chopper type
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * Fermi chopper type
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * Fermi chopper type
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * Fermi chopper type
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
	 * radius of chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRadius();
	
	/**
	 * radius of chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param radius the radius
	 */
	public DataNode setRadius(IDataset radius);

	/**
	 * radius of chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getRadiusScalar();

	/**
	 * radius of chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param radius the radius
	 */
	public DataNode setRadiusScalar(double radius);

	/**
	 * width of an individual slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSlit();
	
	/**
	 * width of an individual slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param slit the slit
	 */
	public DataNode setSlit(IDataset slit);

	/**
	 * width of an individual slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSlitScalar();

	/**
	 * width of an individual slit
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param slit the slit
	 */
	public DataNode setSlitScalar(double slit);

	/**
	 * radius of curvature of slits
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getR_slit();
	
	/**
	 * radius of curvature of slits
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param r_slit the r_slit
	 */
	public DataNode setR_slit(IDataset r_slit);

	/**
	 * radius of curvature of slits
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getR_slitScalar();

	/**
	 * radius of curvature of slits
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param r_slit the r_slit
	 */
	public DataNode setR_slitScalar(double r_slit);

	/**
	 * number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNumber();
	
	/**
	 * number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param number the number
	 */
	public DataNode setNumber(IDataset number);

	/**
	 * number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getNumberScalar();

	/**
	 * number of slits
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param number the number
	 */
	public DataNode setNumberScalar(long number);

	/**
	 * input beam height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getHeight();
	
	/**
	 * input beam height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param height the height
	 */
	public DataNode setHeight(IDataset height);

	/**
	 * input beam height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getHeightScalar();

	/**
	 * input beam height
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param height the height
	 */
	public DataNode setHeightScalar(double height);

	/**
	 * input beam width
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWidth();
	
	/**
	 * input beam width
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param width the width
	 */
	public DataNode setWidth(IDataset width);

	/**
	 * input beam width
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWidthScalar();

	/**
	 * input beam width
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param width the width
	 */
	public DataNode setWidthScalar(double width);

	/**
	 * distance
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDistance();
	
	/**
	 * distance
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistance(IDataset distance);

	/**
	 * distance
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDistanceScalar();

	/**
	 * distance
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param distance the distance
	 */
	public DataNode setDistanceScalar(double distance);

	/**
	 * Wavelength transmitted by chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength();
	
	/**
	 * Wavelength transmitted by chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelength(IDataset wavelength);

	/**
	 * Wavelength transmitted by chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWavelengthScalar();

	/**
	 * Wavelength transmitted by chopper
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelengthScalar(double wavelength);

	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getEnergy();
	
	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergy(IDataset energy);

	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getEnergyScalar();

	/**
	 * energy selected
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param energy the energy
	 */
	public DataNode setEnergyScalar(double energy);

	/**
	 * geometry of the fermi chopper
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * geometry of the fermi chopper
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * geometry of the fermi chopper</li>
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
	 * geometry of the fermi chopper</li>
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
	 * geometry of the fermi chopper</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * geometry of the fermi chopper</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

	/**
	 * absorbing material
	 * 
	 * @return  the value.
	 */
	public IDataset getAbsorbing_material();
	
	/**
	 * absorbing material
	 * 
	 * @param absorbing_material the absorbing_material
	 */
	public DataNode setAbsorbing_material(IDataset absorbing_material);

	/**
	 * absorbing material
	 * 
	 * @return  the value.
	 */
	public String getAbsorbing_materialScalar();

	/**
	 * absorbing material
	 * 
	 * @param absorbing_material the absorbing_material
	 */
	public DataNode setAbsorbing_materialScalar(String absorbing_material);

	/**
	 * transmitting material
	 * 
	 * @return  the value.
	 */
	public IDataset getTransmitting_material();
	
	/**
	 * transmitting material
	 * 
	 * @param transmitting_material the transmitting_material
	 */
	public DataNode setTransmitting_material(IDataset transmitting_material);

	/**
	 * transmitting material
	 * 
	 * @return  the value.
	 */
	public String getTransmitting_materialScalar();

	/**
	 * transmitting material
	 * 
	 * @param transmitting_material the transmitting_material
	 */
	public DataNode setTransmitting_materialScalar(String transmitting_material);

}
