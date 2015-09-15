/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2015-09-09T16:50:16.8+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;

/**
 * .. index:: plotting
 * (**required**) ``NXdata`` is a template of
 * plottable data and their dimension scales.
 * It is mandatory that there is at least one ``NXdata`` group
 * in each NXentry group.
 * Note that the ``variable`` and ``data``
 * can be defined with different names.
 * The ``signal`` and ``axes`` attribute of the
 * ``data`` item define which items
 * are plottable data and which are *dimension scales*.
 * * Each ``NXdata`` group will consist of only one data set
 * containing plottable data and their standard deviations.
 * * This data set may be of arbitrary rank up to a maximum
 * of ``NX_MAXRANK=32``.
 * * The plottable data will be identified by the attribute:
 * ``signal=1``
 * * The plottable data will identify the *dimension scale*
 * specification(s) in the ``axes`` attribute.
 * If available, the standard deviations of the data are to be
 * stored in a data set of the same rank and dimensions, with the name ``errors``.
 * * For each data dimension, there should be a one-dimensional array
 * of the same length.
 * * These one-dimensional arrays are the *dimension scales* of the
 * data, *i.e*. the values of the independent variables at which the data
 * is measured, such as scattering angle or energy transfer.
 * .. index:: link
 * There are two methods of linking
 * each data dimension to its respective dimension scale.
 * .. index:: axes (attribute)
 * The preferred (and recommended) method uses the ``axes``
 * attribute to specify the names of each *dimension scale*.
 * The older method uses the ``axis`` attribute on each
 * *dimension scale*
 * to identify
 * with an integer the axis whose value is the number of the dimension.
 * ``NXdata`` is used to implement one of the basic motivations in NeXus,
 * to provide a default plot for the data of this ``NXentry``. The actual data
 * might be stored in another group and (hard) linked to the ``NXdata`` group.
 * <p><b>Symbols:</b> 
 * These symbols will be used below to coordinate datasets with the same shape.<ul>
 * <li><b>dataRank</b> 
 * rank of the ``data`` field</li>
 * <li><b>n</b> 
 * length of the ``variable`` field</li>
 * <li><b>nx</b> 
 * length of the ``x`` field</li>
 * <li><b>ny</b> 
 * length of the ``y`` field</li>
 * <li><b>nz</b> 
 * length of the ``z`` field</li></ul></p>
 * 
 * @version 1.0
 */
public interface NXdata extends NXobject {

	/**
	 * Dimension scale defining an axis of the data.
	 * Client is responsible for defining the dimensions of the data.
	 * The name of this field may be changed to fit the circumstances.
	 * Standard NeXus client tools will use the attributes to determine
	 * how to use this field.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getVariable();

	/**
	 * Axis label
	 * 
	 * @return  the value.
	 */
	public String getVariableAttributeLong_name();

	/**
	 * ``0|false``: single value,
	 * ``1|true``: multiple values
	 * 
	 * @return  the value.
	 */
	public boolean getVariableAttributeDistribution();

	/**
	 * Index of first good value
	 * 
	 * @return  the value.
	 */
	public long getVariableAttributeFirst_good();

	/**
	 * Index of last good value
	 * 
	 * @return  the value.
	 */
	public long getVariableAttributeLast_good();

	/**
	 * Index (positive integer) identifying this specific set of numbers.
	 * N.B. The ``axis`` attribute is the old way of designating a link.
	 * Do not use the ``axes`` attribute with the ``axis`` attribute.
	 * The ``axes`` attribute is now preferred.
	 * 
	 * @return  the value.
	 */
	public long getVariableAttributeAxis();

	/**
	 * Errors (uncertainties) associated with axis ``variable``
	 * Client is responsible for defining the dimensions of the data.
	 * The name of this field may be changed to fit the circumstances
	 * but is matched with the *variable*
	 * field with ``_errors`` appended.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Dimensions:</b> 1: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getVariable_errors();

	/**
	 * .. index:: plotting
	 * This field contains the data values to be used as the
	 * NeXus *plottable data*.
	 * Client is responsible for defining the dimensions of the data.
	 * The name of this field may be changed to fit the circumstances.
	 * Standard NeXus client tools will use the attributes to determine
	 * how to use this field.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getData();

	/**
	 * .. index:: plotting
	 * Plottable (independent) axis, indicate index number.
	 * Only one field in a ``NXdata`` group may have the
	 * ``signal=1`` attribute.
	 * Do not use the ``signal`` attribute with the ``axis`` attribute.
	 * 
	 * @return  the value.
	 */
	public long getDataAttributeSignal();

	/**
	 * Defines the names of the dimension scales
	 * (independent axes) for this data set
	 * as a colon-delimited array.
	 * NOTE: The ``axes`` attribute is the preferred
	 * method of designating a link.
	 * Do not use the ``axes`` attribute with the ``axis`` attribute.
	 * 
	 * @return  the value.
	 */
	public String getDataAttributeAxes();

	/**
	 * Specify the names of the errors (uncertainties)
	 * of the dependent axes as plottable data.
	 * NOTE: The ``errors`` attribute uses the same syntax
	 * as the ``axes`` attribute.
	 * 
	 * @return  the value.
	 */
	public String getDataAttributeUncertainties();

	/**
	 * data label
	 * 
	 * @return  the value.
	 */
	public String getDataAttributeLong_name();

	/**
	 * Standard deviations of data values -
	 * the data array is identified by the attribute ``signal=1``.
	 * The ``errors`` array must have the same dimensions as ``data``.
	 * Client is responsible for defining the dimensions of the data.
	 * <p>
	 * <b>Type:</b> NX_NUMBER
	 * <b>Dimensions:</b> 0: n;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getErrors();

	/**
	 * The elements in data are usually float values really. For
	 * efficiency reasons these are usually stored as integers
	 * after scaling with a scale factor. This value is the scale
	 * factor. It is required to get the actual physical value,
	 * when necessary.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getScaling_factor();

	/**
	 * An optional offset to apply to the values in data.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getOffset();

	/**
	 * This is an array holding the values to use for the x-axis of
	 * data. The units must be appropriate for the measurement.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: nx;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getX();

	/**
	 * This is an array holding the values to use for the y-axis of
	 * data. The units must be appropriate for the measurement.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: ny;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getY();

	/**
	 * This is an array holding the values to use for the z-axis of
	 * data. The units must be appropriate for the measurement.
	 * <p>
	 * <b>Type:</b> NX_FLOAT
	 * <b>Units:</b> NX_ANY
	 * <b>Dimensions:</b> 1: nz;
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getZ();

}
