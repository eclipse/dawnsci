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
 * A beamline collimator.
 * 
 * @version 1.0
 */
public interface NXcollimator extends NXobject {

	public static final String NX_TYPE = "type";
	public static final String NX_SOLLER_ANGLE = "soller_angle";
	public static final String NX_DIVERGENCE_X = "divergence_x";
	public static final String NX_DIVERGENCE_Y = "divergence_y";
	public static final String NX_FREQUENCY = "frequency";
	public static final String NX_BLADE_THICKNESS = "blade_thickness";
	public static final String NX_BLADE_SPACING = "blade_spacing";
	public static final String NX_ABSORBING_MATERIAL = "absorbing_material";
	public static final String NX_TRANSMITTING_MATERIAL = "transmitting_material";
	/**
	 * position, shape and size
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * position, shape and size
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * position, shape and size</li>
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
	 * position, shape and size</li>
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
	 * position, shape and size</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * position, shape and size</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Soller</b> </li>
	 * <li><b>radial</b> </li>
	 * <li><b>oscillating</b> </li>
	 * <li><b>honeycomb</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getType();
	
	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Soller</b> </li>
	 * <li><b>radial</b> </li>
	 * <li><b>oscillating</b> </li>
	 * <li><b>honeycomb</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setType(IDataset type);

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Soller</b> </li>
	 * <li><b>radial</b> </li>
	 * <li><b>oscillating</b> </li>
	 * <li><b>honeycomb</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getTypeScalar();

	/**
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>Soller</b> </li>
	 * <li><b>radial</b> </li>
	 * <li><b>oscillating</b> </li>
	 * <li><b>honeycomb</b> </li></ul></p>
	 * </p>
	 * 
	 * @param type the type
	 */
	public DataNode setTypeScalar(String type);

	/**
	 * Angular divergence of Soller collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSoller_angle();
	
	/**
	 * Angular divergence of Soller collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param soller_angle the soller_angle
	 */
	public DataNode setSoller_angle(IDataset soller_angle);

	/**
	 * Angular divergence of Soller collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSoller_angleScalar();

	/**
	 * Angular divergence of Soller collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param soller_angle the soller_angle
	 */
	public DataNode setSoller_angleScalar(double soller_angle);

	/**
	 * divergence of collimator in local x direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_x();
	
	/**
	 * divergence of collimator in local x direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_x the divergence_x
	 */
	public DataNode setDivergence_x(IDataset divergence_x);

	/**
	 * divergence of collimator in local x direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDivergence_xScalar();

	/**
	 * divergence of collimator in local x direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_x the divergence_x
	 */
	public DataNode setDivergence_xScalar(double divergence_x);

	/**
	 * divergence of collimator in local y direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_y();
	
	/**
	 * divergence of collimator in local y direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_y the divergence_y
	 */
	public DataNode setDivergence_y(IDataset divergence_y);

	/**
	 * divergence of collimator in local y direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDivergence_yScalar();

	/**
	 * divergence of collimator in local y direction
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_y the divergence_y
	 */
	public DataNode setDivergence_yScalar(double divergence_y);

	/**
	 * Frequency of oscillating collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getFrequency();
	
	/**
	 * Frequency of oscillating collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param frequency the frequency
	 */
	public DataNode setFrequency(IDataset frequency);

	/**
	 * Frequency of oscillating collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getFrequencyScalar();

	/**
	 * Frequency of oscillating collimator
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_FREQUENCY
	 * </p>
	 * 
	 * @param frequency the frequency
	 */
	public DataNode setFrequencyScalar(double frequency);

	/**
	 * Log of frequency
	 * 
	 * @return  the value.
	 */
	public NXlog getFrequency_log();
	
	/**
	 * Log of frequency
	 * 
	 * @param frequency_log the frequency_log
	 */
	public void setFrequency_log(NXlog frequency_log);

	/**
	 * blade thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBlade_thickness();
	
	/**
	 * blade thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param blade_thickness the blade_thickness
	 */
	public DataNode setBlade_thickness(IDataset blade_thickness);

	/**
	 * blade thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBlade_thicknessScalar();

	/**
	 * blade thickness
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param blade_thickness the blade_thickness
	 */
	public DataNode setBlade_thicknessScalar(double blade_thickness);

	/**
	 * blade spacing
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBlade_spacing();
	
	/**
	 * blade spacing
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param blade_spacing the blade_spacing
	 */
	public DataNode setBlade_spacing(IDataset blade_spacing);

	/**
	 * blade spacing
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBlade_spacingScalar();

	/**
	 * blade spacing
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param blade_spacing the blade_spacing
	 */
	public DataNode setBlade_spacingScalar(double blade_spacing);

	/**
	 * name of absorbing material
	 * 
	 * @return  the value.
	 */
	public IDataset getAbsorbing_material();
	
	/**
	 * name of absorbing material
	 * 
	 * @param absorbing_material the absorbing_material
	 */
	public DataNode setAbsorbing_material(IDataset absorbing_material);

	/**
	 * name of absorbing material
	 * 
	 * @return  the value.
	 */
	public String getAbsorbing_materialScalar();

	/**
	 * name of absorbing material
	 * 
	 * @param absorbing_material the absorbing_material
	 */
	public DataNode setAbsorbing_materialScalar(String absorbing_material);

	/**
	 * name of transmitting material
	 * 
	 * @return  the value.
	 */
	public IDataset getTransmitting_material();
	
	/**
	 * name of transmitting material
	 * 
	 * @param transmitting_material the transmitting_material
	 */
	public DataNode setTransmitting_material(IDataset transmitting_material);

	/**
	 * name of transmitting material
	 * 
	 * @return  the value.
	 */
	public String getTransmitting_materialScalar();

	/**
	 * name of transmitting material
	 * 
	 * @param transmitting_material the transmitting_material
	 */
	public DataNode setTransmitting_materialScalar(String transmitting_material);

}
