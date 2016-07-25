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
 * A neutron velocity selector
 * 
 * @version 1.0
 */
public interface NXvelocity_selector extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_ROTATION_SPEED = "rotation_speed";
	public static final String NX_RADIUS = "radius";
	public static final String NX_SPWIDTH = "spwidth";
	public static final String NX_LENGTH = "length";
	public static final String NX_NUM = "num";
	public static final String NX_TWIST = "twist";
	public static final String NX_TABLE = "table";
	public static final String NX_HEIGHT = "height";
	public static final String NX_WIDTH = "width";
	public static final String NX_WAVELENGTH = "wavelength";
	public static final String NX_WAVELENGTH_SPREAD = "wavelength_spread";
	/**
	 * velocity selector type
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * velocity selector type
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * velocity selector type
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * velocity selector type
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * velocity selector rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRotation_speed();
	
	/**
	 * velocity selector rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param rotation_speed the rotation_speed
	 */
	public DataNode setRotation_speed(IDataset rotation_speed);

	/**
	 * velocity selector rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getRotation_speedScalar();

	/**
	 * velocity selector rotation speed
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param rotation_speed the rotation_speed
	 */
	public DataNode setRotation_speedScalar(double rotation_speed);

	/**
	 * radius at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getRadius();
	
	/**
	 * radius at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param radius the radius
	 */
	public DataNode setRadius(IDataset radius);

	/**
	 * radius at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getRadiusScalar();

	/**
	 * radius at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param radius the radius
	 */
	public DataNode setRadiusScalar(double radius);

	/**
	 * spoke width at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSpwidth();
	
	/**
	 * spoke width at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param spwidth the spwidth
	 */
	public DataNode setSpwidth(IDataset spwidth);

	/**
	 * spoke width at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSpwidthScalar();

	/**
	 * spoke width at beam centre
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param spwidth the spwidth
	 */
	public DataNode setSpwidthScalar(double spwidth);

	/**
	 * rotor length
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getLength();
	
	/**
	 * rotor length
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param length the length
	 */
	public DataNode setLength(IDataset length);

	/**
	 * rotor length
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getLengthScalar();

	/**
	 * rotor length
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param length the length
	 */
	public DataNode setLengthScalar(double length);

	/**
	 * number of spokes/lamella
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getNum();
	
	/**
	 * number of spokes/lamella
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param num the num
	 */
	public DataNode setNum(IDataset num);

	/**
	 * number of spokes/lamella
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @return  the value.
	 */
	public long getNumScalar();

	/**
	 * number of spokes/lamella
	 * <p>
	 * <b>Type:</b> NX_INT
	 * <b>Units:</b> NX_UNITLESS
	 * </p>
	 * 
	 * @param num the num
	 */
	public DataNode setNumScalar(long num);

	/**
	 * twist angle along axis
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTwist();
	
	/**
	 * twist angle along axis
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param twist the twist
	 */
	public DataNode setTwist(IDataset twist);

	/**
	 * twist angle along axis
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTwistScalar();

	/**
	 * twist angle along axis
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param twist the twist
	 */
	public DataNode setTwistScalar(double twist);

	/**
	 * offset vertical angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getTable();
	
	/**
	 * offset vertical angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param table the table
	 */
	public DataNode setTable(IDataset table);

	/**
	 * offset vertical angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getTableScalar();

	/**
	 * offset vertical angle
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param table the table
	 */
	public DataNode setTableScalar(double table);

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
	 * wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength();
	
	/**
	 * wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelength(IDataset wavelength);

	/**
	 * wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWavelengthScalar();

	/**
	 * wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength the wavelength
	 */
	public DataNode setWavelengthScalar(double wavelength);

	/**
	 * deviation FWHM /Wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getWavelength_spread();
	
	/**
	 * deviation FWHM /Wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength_spread the wavelength_spread
	 */
	public DataNode setWavelength_spread(IDataset wavelength_spread);

	/**
	 * deviation FWHM /Wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getWavelength_spreadScalar();

	/**
	 * deviation FWHM /Wavelength
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_WAVELENGTH
	 * </p>
	 * 
	 * @param wavelength_spread the wavelength_spread
	 */
	public DataNode setWavelength_spreadScalar(double wavelength_spread);

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

}
