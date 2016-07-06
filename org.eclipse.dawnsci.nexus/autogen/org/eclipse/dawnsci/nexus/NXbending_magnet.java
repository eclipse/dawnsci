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
 * A bending magnet
 * 
 * @version 1.0
 */
public interface NXbending_magnet extends NXobject {

	public static final String NX_CRITICAL_ENERGY = "critical_energy";
	public static final String NX_BENDING_RADIUS = "bending_radius";
	public static final String NX_MAGNETIC_FIELD = "magnetic_field";
	public static final String NX_ACCEPTED_PHOTON_BEAM_DIVERGENCE = "accepted_photon_beam_divergence";
	public static final String NX_SOURCE_DISTANCE_X = "source_distance_x";
	public static final String NX_SOURCE_DISTANCE_Y = "source_distance_y";
	public static final String NX_DIVERGENCE_X_PLUS = "divergence_x_plus";
	public static final String NX_DIVERGENCE_X_MINUS = "divergence_x_minus";
	public static final String NX_DIVERGENCE_Y_PLUS = "divergence_y_plus";
	public static final String NX_DIVERGENCE_Y_MINUS = "divergence_y_minus";
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getCritical_energy();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param critical_energy the critical_energy
	 */
	public DataNode setCritical_energy(IDataset critical_energy);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getCritical_energyScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ENERGY
	 * </p>
	 * 
	 * @param critical_energy the critical_energy
	 */
	public DataNode setCritical_energyScalar(double critical_energy);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getBending_radius();
	
	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param bending_radius the bending_radius
	 */
	public DataNode setBending_radius(IDataset bending_radius);

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getBending_radiusScalar();

	/**
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param bending_radius the bending_radius
	 */
	public DataNode setBending_radiusScalar(double bending_radius);

	/**
	 * strength of magnetic field of dipole magnets
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getMagnetic_field();
	
	/**
	 * strength of magnetic field of dipole magnets
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param magnetic_field the magnetic_field
	 */
	public DataNode setMagnetic_field(IDataset magnetic_field);

	/**
	 * strength of magnetic field of dipole magnets
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getMagnetic_fieldScalar();

	/**
	 * strength of magnetic field of dipole magnets
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_CURRENT
	 * </p>
	 * 
	 * @param magnetic_field the magnetic_field
	 */
	public DataNode setMagnetic_fieldScalar(double magnetic_field);

	/**
	 * An array of four numbers giving X+, X-, Y+ and Y- half divergence
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAccepted_photon_beam_divergence();
	
	/**
	 * An array of four numbers giving X+, X-, Y+ and Y- half divergence
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param accepted_photon_beam_divergence the accepted_photon_beam_divergence
	 */
	public DataNode setAccepted_photon_beam_divergence(IDataset accepted_photon_beam_divergence);

	/**
	 * An array of four numbers giving X+, X-, Y+ and Y- half divergence
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getAccepted_photon_beam_divergenceScalar();

	/**
	 * An array of four numbers giving X+, X-, Y+ and Y- half divergence
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param accepted_photon_beam_divergence the accepted_photon_beam_divergence
	 */
	public DataNode setAccepted_photon_beam_divergenceScalar(double accepted_photon_beam_divergence);

	/**
	 * Distance of source point from particle beam waist in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSource_distance_x();
	
	/**
	 * Distance of source point from particle beam waist in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param source_distance_x the source_distance_x
	 */
	public DataNode setSource_distance_x(IDataset source_distance_x);

	/**
	 * Distance of source point from particle beam waist in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSource_distance_xScalar();

	/**
	 * Distance of source point from particle beam waist in X (horizontal) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param source_distance_x the source_distance_x
	 */
	public DataNode setSource_distance_xScalar(double source_distance_x);

	/**
	 * Distance of source point from particle beam waist in Y (vertical) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getSource_distance_y();
	
	/**
	 * Distance of source point from particle beam waist in Y (vertical) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param source_distance_y the source_distance_y
	 */
	public DataNode setSource_distance_y(IDataset source_distance_y);

	/**
	 * Distance of source point from particle beam waist in Y (vertical) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getSource_distance_yScalar();

	/**
	 * Distance of source point from particle beam waist in Y (vertical) direction.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param source_distance_y the source_distance_y
	 */
	public DataNode setSource_distance_yScalar(double source_distance_y);

	/**
	 * Accepted photon beam divergence in X+ (horizontal outboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_x_plus();
	
	/**
	 * Accepted photon beam divergence in X+ (horizontal outboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_x_plus the divergence_x_plus
	 */
	public DataNode setDivergence_x_plus(IDataset divergence_x_plus);

	/**
	 * Accepted photon beam divergence in X+ (horizontal outboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDivergence_x_plusScalar();

	/**
	 * Accepted photon beam divergence in X+ (horizontal outboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_x_plus the divergence_x_plus
	 */
	public DataNode setDivergence_x_plusScalar(double divergence_x_plus);

	/**
	 * Accepted photon beam divergence in X- (horizontal inboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_x_minus();
	
	/**
	 * Accepted photon beam divergence in X- (horizontal inboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_x_minus the divergence_x_minus
	 */
	public DataNode setDivergence_x_minus(IDataset divergence_x_minus);

	/**
	 * Accepted photon beam divergence in X- (horizontal inboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDivergence_x_minusScalar();

	/**
	 * Accepted photon beam divergence in X- (horizontal inboard) direction.
	 * Note that divergence_x_plus+divergence_x_minus is the total horizontal beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_x_minus the divergence_x_minus
	 */
	public DataNode setDivergence_x_minusScalar(double divergence_x_minus);

	/**
	 * Accepted photon beam divergence in Y+ (vertical upward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_y_plus();
	
	/**
	 * Accepted photon beam divergence in Y+ (vertical upward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_y_plus the divergence_y_plus
	 */
	public DataNode setDivergence_y_plus(IDataset divergence_y_plus);

	/**
	 * Accepted photon beam divergence in Y+ (vertical upward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDivergence_y_plusScalar();

	/**
	 * Accepted photon beam divergence in Y+ (vertical upward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_y_plus the divergence_y_plus
	 */
	public DataNode setDivergence_y_plusScalar(double divergence_y_plus);

	/**
	 * Accepted photon beam divergence in Y- (vertical downward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDivergence_y_minus();
	
	/**
	 * Accepted photon beam divergence in Y- (vertical downward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_y_minus the divergence_y_minus
	 */
	public DataNode setDivergence_y_minus(IDataset divergence_y_minus);

	/**
	 * Accepted photon beam divergence in Y- (vertical downward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @return  the value.
	 */
	public double getDivergence_y_minusScalar();

	/**
	 * Accepted photon beam divergence in Y- (vertical downward) direction.
	 * Note that divergence_y_plus+divergence_y_minus is the total vertical beam divergence.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANGLE
	 * </p>
	 * 
	 * @param divergence_y_minus the divergence_y_minus
	 */
	public DataNode setDivergence_y_minusScalar(double divergence_y_minus);

	/**
	 * bending magnet spectrum
	 * 
	 * @return  the value.
	 */
	public NXdata getSpectrum();
	
	/**
	 * bending magnet spectrum
	 * 
	 * @param spectrum the spectrum
	 */
	public void setSpectrum(NXdata spectrum);

	/**
	 * "Engineering" position of bending magnet
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();
	
	/**
	 * "Engineering" position of bending magnet
	 * 
	 * @param geometry the geometry
	 */
	public void setGeometry(NXgeometry geometry);
  
	/**
	 * Get a NXgeometry node by name:
	 * <ul>
	 * <li>
	 * "Engineering" position of bending magnet</li>
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
	 * "Engineering" position of bending magnet</li>
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
	 * "Engineering" position of bending magnet</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * <ul>
	 * <li>
	 * "Engineering" position of bending magnet</li>
	 * </ul>
	 * 
	 * @param geometry the child nodes to add 
	 */
	
	public void setAllGeometry(Map<String, NXgeometry> geometry);
	

}
