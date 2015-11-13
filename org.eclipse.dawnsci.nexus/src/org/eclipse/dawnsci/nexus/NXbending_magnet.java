/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-11-11T16:27:56.219Z
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * description for a bending magnet
 * 
 * @version 1.0
 */
public interface NXbending_magnet extends NXobject {

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
	 * @return  the value
	 */
	 public double getCritical_energyScalar();

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
	 * @return  the value
	 */
	 public double getBending_radiusScalar();

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
	 * @return  the value
	 */
	 public double getMagnetic_fieldScalar();

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
	 * @return  the value
	 */
	 public double getAccepted_photon_beam_divergenceScalar();

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
	 * @return  the value
	 */
	 public double getSource_distance_xScalar();

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
	 * @return  the value
	 */
	 public double getSource_distance_yScalar();

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
	 * @return  the value
	 */
	 public double getDivergence_x_plusScalar();

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
	 * @return  the value
	 */
	 public double getDivergence_x_minusScalar();

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
	 * @return  the value
	 */
	 public double getDivergence_y_plusScalar();

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
	 * @return  the value
	 */
	 public double getDivergence_y_minusScalar();

	/**
	 * bending magnet spectrum
	 * 
	 * @return  the value.
	 */
	public NXdata getSpectrum();	

	/**
	 * "Engineering" position of bending magnet
	 * 
	 * @return  the value.
	 */
	public NXgeometry getGeometry();	
  
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
	 * Get all NXgeometry nodes:
	 * <ul>
	 * <li>
	 * "Engineering" position of bending magnet</li>
	 * </ul>
	 * 
	 * @return  a map from node names to the NXgeometry for that node.
	 */
	public Map<String, NXgeometry> getAllGeometry();

}
