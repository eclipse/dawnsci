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
 * A simple slit.
 * For more complex geometries, :ref:`NXaperture` should be used.
 * 
 * @version 1.0
 */
public interface NXslit extends NXobject {

	public static final String NX_DEPENDS_ON = "depends_on";
	public static final String NX_X_GAP = "x_gap";
	public static final String NX_Y_GAP = "y_gap";
	/**
	 * Points to the path of the last element in the geometry chain that places
	 * this object in space.
	 * When followed through that chain is supposed to end at an element depending
	 * on "." i.e. the origin of the coordinate system.
	 * If desired the location of the slit can also be described relative to
	 * an NXbeam, which will allow a simple description of a non-centred slit.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getDepends_on();
	
	/**
	 * Points to the path of the last element in the geometry chain that places
	 * this object in space.
	 * When followed through that chain is supposed to end at an element depending
	 * on "." i.e. the origin of the coordinate system.
	 * If desired the location of the slit can also be described relative to
	 * an NXbeam, which will allow a simple description of a non-centred slit.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param depends_on the depends_on
	 */
	public DataNode setDepends_on(IDataset depends_on);

	/**
	 * Points to the path of the last element in the geometry chain that places
	 * this object in space.
	 * When followed through that chain is supposed to end at an element depending
	 * on "." i.e. the origin of the coordinate system.
	 * If desired the location of the slit can also be described relative to
	 * an NXbeam, which will allow a simple description of a non-centred slit.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getDepends_onScalar();

	/**
	 * Points to the path of the last element in the geometry chain that places
	 * this object in space.
	 * When followed through that chain is supposed to end at an element depending
	 * on "." i.e. the origin of the coordinate system.
	 * If desired the location of the slit can also be described relative to
	 * an NXbeam, which will allow a simple description of a non-centred slit.
	 * <p>
	 * <b>Type:</b> NX_CHAR
	 * </p>
	 * 
	 * @param depends_on the depends_on
	 */
	public DataNode setDepends_onScalar(String depends_on);

	/**
	 * Size of the gap opening in the first dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getX_gap();
	
	/**
	 * Size of the gap opening in the first dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param x_gap the x_gap
	 */
	public DataNode setX_gap(IDataset x_gap);

	/**
	 * Size of the gap opening in the first dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getX_gapScalar();

	/**
	 * Size of the gap opening in the first dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param x_gap the x_gap
	 */
	public DataNode setX_gapScalar(Number x_gap);

	/**
	 * Size of the gap opening in the second dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getY_gap();
	
	/**
	 * Size of the gap opening in the second dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param y_gap the y_gap
	 */
	public DataNode setY_gap(IDataset y_gap);

	/**
	 * Size of the gap opening in the second dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getY_gapScalar();

	/**
	 * Size of the gap opening in the second dimension of the local
	 * coordinate system.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Units:</b> NX_LENGTH
	 * </p>
	 * 
	 * @param y_gap the y_gap
	 */
	public DataNode setY_gapScalar(Number y_gap);

}
