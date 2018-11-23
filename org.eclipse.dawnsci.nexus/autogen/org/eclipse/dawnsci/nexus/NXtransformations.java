/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus;

import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

/**
 * Collection of axis-based translations and rotations to describe a geometry.
 * May also contain axes that do not move and therefore do not have a transformation
 * type specified, but are useful in understanding coordinate frames within which
 * transformations are done, or in documenting important directions, such as the
 * direction of gravity.
 * A nested sequence of transformations lists the offset and rotation steps
 * needed to describe the position and orientation of any movable or fixed device.
 * There will be one or more transformations (axes) defined by one or more fields
 * for each transformation. The all-caps name ``AXISNAME`` designates the
 * particular axis generating a transformation (e.g. a rotation axis or a translation
 * axis or a general axis). The attribute ``units="NX_TRANSFORMATION"`` designates the
 * units will be appropriate to the ``transformation_type`` attribute:
 * * ``NX_LENGTH`` for ``translation``
 * * ``NX_ANGLE`` for ``rotation``
 * * ``NX_UNITLESS`` for axes for which no transformation type is specified
 * This class will usually contain all axes of a sample stage or goniometer or
 * a detector. The NeXus default McSTAS coordinate frame is assumed, but additional
 * useful coordinate axes may be defined by using axes for which no transformation
 * type has been specified.
 * The entry point (``depends_on``) will be outside of this class and point to a
 * field in here. Following the chain may also require following ``depends_on``
 * links to transformations outside, for example to a common base table. If
 * a relative path is given, it is relative to the group enclosing the ``depends_on``
 * specification.
 * For a chain of three transformations, where :math:`T_1` depends on :math:`T_2`
 * and that in turn depends on :math:`T_3`, the final transformation :math:`T_f` is
 * .. math::
 * T_f = T_3 T_2 T_1
 * In explicit terms, the transformations are a subset of affine transformations
 * expressed as 4x4 matrices that act on homogeneous coordinates, :math:`w=(x,y,z,1)^T`.
 * For rotation and translation,
 * .. math::
 * T_r &= \left( \begin{matrix} R & o \\
 * 0_3 & 1 \end{matrix} \right) \\
 * T_t &= \left( \begin{matrix} I_3 & t + o \\
 * 0_3 & 1 \end{matrix} \right)
 * where :math:`R` is the usual 3x3 rotation matrix, :math:`o` is an offset vector,
 * :math:`0_3` is a row of 3 zeros, :math:`I_3` is the 3x3 identity matrix and
 * :math:`t` is the translation vector.
 * :math:`o` is given the ``offset`` attribute, :math:`t` is given by the ``vector``
 * attribute multiplied by the field value, and :math:`R` is defined as a rotation
 * about an axis in the direction of ``vector``, of angle of the field value.
 * 
 * @version 1.1
 */
public interface NXtransformations extends NXobject {

	public static final String NX_AXISNAME = "AXISNAME";
	public static final String NX_AXISNAME_ATTRIBUTE_TRANSFORMATION_TYPE = "transformation_type";
	public static final String NX_AXISNAME_ATTRIBUTE_VECTOR = "vector";
	public static final String NX_AXISNAME_ATTRIBUTE_OFFSET = "offset";
	public static final String NX_AXISNAME_ATTRIBUTE_OFFSET_UNITS = "offset_units";
	public static final String NX_AXISNAME_ATTRIBUTE_DEPENDS_ON = "depends_on";
	public static final String NX_AXISNAME_END = "AXISNAME_end";
	public static final String NX_AXISNAME_INCREMENT_SET = "AXISNAME_increment_set";
	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not forced. The user is free to use any name
	 * that does not cause confusion. When using more than one ``AXISNAME`` field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * The values given should be the start points of exposures for the corresponding
	 * frames. The end points should be given in ``AXISNAME_end``.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAXISNAME();
	
	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not forced. The user is free to use any name
	 * that does not cause confusion. When using more than one ``AXISNAME`` field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * The values given should be the start points of exposures for the corresponding
	 * frames. The end points should be given in ``AXISNAME_end``.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param AXISNAME the AXISNAME
	 */
	public DataNode setAXISNAME(IDataset AXISNAME);

	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not forced. The user is free to use any name
	 * that does not cause confusion. When using more than one ``AXISNAME`` field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * The values given should be the start points of exposures for the corresponding
	 * frames. The end points should be given in ``AXISNAME_end``.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAXISNAMEScalar();

	/**
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not forced. The user is free to use any name
	 * that does not cause confusion. When using more than one ``AXISNAME`` field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * The values given should be the start points of exposures for the corresponding
	 * frames. The end points should be given in ``AXISNAME_end``.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param AXISNAME the AXISNAME
	 */
	public DataNode setAXISNAMEScalar(Number AXISNAME);
  
	/**
	 * Get all AXISNAME fields:
	 *
	 * Units need to be appropriate for translation or rotation
	 * The name of this field is not forced. The user is free to use any name
	 * that does not cause confusion. When using more than one ``AXISNAME`` field,
	 * make sure that each field name is unique in the same group, as required
	 * by HDF5.
	 * The values given should be the start points of exposures for the corresponding
	 * frames. The end points should be given in ``AXISNAME_end``.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  a map from node names to the ? extends IDataset for that node.
	 */
	public Map<String, ? extends IDataset> getAllAXISNAME();

	/**
	 * The transformation_type may be ``translation``, in which case the
	 * values are linear displacements along the axis, ``rotation``,
	 * in which case the values are angular rotations around the axis.
	 * If this attribute is omitted, this is an axis for which there
	 * is no motion to be specifies, such as the direction of gravity,
	 * or the direction to the source, or a basis vector of a
	 * coordinate frame.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @return  the value.
	 */
	public String getAXISNAMEAttributeTransformation_type();
	
	/**
	 * The transformation_type may be ``translation``, in which case the
	 * values are linear displacements along the axis, ``rotation``,
	 * in which case the values are angular rotations around the axis.
	 * If this attribute is omitted, this is an axis for which there
	 * is no motion to be specifies, such as the direction of gravity,
	 * or the direction to the source, or a basis vector of a
	 * coordinate frame.
	 * <p>
	 * <p><b>Enumeration:</b><ul>
	 * <li><b>translation</b> </li>
	 * <li><b>rotation</b> </li></ul></p>
	 * </p>
	 * 
	 * @param transformation_type the transformation_type
	 */
	public void setAXISNAMEAttributeTransformation_type(String transformation_type);

	/**
	 * Three values that define the axis for this transformation.
	 * The axis should be normalized to unit length, making it
	 * dimensionless. For ``rotation`` axes, the direction should be
	 * chosen for a right-handed rotation with increasing angle.
	 * For ``translation`` axes the direction should be chosen for
	 * increasing displacement.
	 * <p>
				 1: 3;
			
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAXISNAMEAttributeVector();
	
	/**
	 * Three values that define the axis for this transformation.
	 * The axis should be normalized to unit length, making it
	 * dimensionless. For ``rotation`` axes, the direction should be
	 * chosen for a right-handed rotation with increasing angle.
	 * For ``translation`` axes the direction should be chosen for
	 * increasing displacement.
	 * <p>
				 1: 3;
			
	 * </p>
	 * 
	 * @param vector the vector
	 */
	public void setAXISNAMEAttributeVector(Number vector);

	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * <p>
				 1: 3;
			
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAXISNAMEAttributeOffset();
	
	/**
	 * A fixed offset applied before the transformation (three vector components).
	 * <p>
				 1: 3;
			
	 * </p>
	 * 
	 * @param offset the offset
	 */
	public void setAXISNAMEAttributeOffset(Number offset);

	/**
	 * Units of the offset. Values should be consistent with NX_LENGTH.
	 * 
	 * @return  the value.
	 */
	public String getAXISNAMEAttributeOffset_units();
	
	/**
	 * Units of the offset. Values should be consistent with NX_LENGTH.
	 * 
	 * @param offset_units the offset_units
	 */
	public void setAXISNAMEAttributeOffset_units(String offset_units);

	/**
	 * Points to the path to a field defining the axis on which this
	 * depends or the string ".".
	 * 
	 * @return  the value.
	 */
	public String getAXISNAMEAttributeDepends_on();
	
	/**
	 * Points to the path to a field defining the axis on which this
	 * depends or the string ".".
	 * 
	 * @param depends_on the depends_on
	 */
	public void setAXISNAMEAttributeDepends_on(String depends_on);

	/**
	 * ``AXISNAME_end`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_end`` has been appended.
	 * The values in this field are the end points of the motions that start
	 * at the corresponding positions given in the ``AXISNAME`` field.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAXISNAME_end();
	
	/**
	 * ``AXISNAME_end`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_end`` has been appended.
	 * The values in this field are the end points of the motions that start
	 * at the corresponding positions given in the ``AXISNAME`` field.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param AXISNAME_end the AXISNAME_end
	 */
	public DataNode setAXISNAME_end(IDataset AXISNAME_end);

	/**
	 * ``AXISNAME_end`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_end`` has been appended.
	 * The values in this field are the end points of the motions that start
	 * at the corresponding positions given in the ``AXISNAME`` field.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAXISNAME_endScalar();

	/**
	 * ``AXISNAME_end`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_end`` has been appended.
	 * The values in this field are the end points of the motions that start
	 * at the corresponding positions given in the ``AXISNAME`` field.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param AXISNAME_end the AXISNAME_end
	 */
	public DataNode setAXISNAME_endScalar(Number AXISNAME_end);
  
	/**
	 * Get all AXISNAME_end fields:
	 *
	 * ``AXISNAME_end`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_end`` has been appended.
	 * The values in this field are the end points of the motions that start
	 * at the corresponding positions given in the ``AXISNAME`` field.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  a map from node names to the ? extends IDataset for that node.
	 */
	public Map<String, ? extends IDataset> getAllAXISNAME_end();

	/**
	 * ``AXISNAME_increment_set`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_increment_set`` has been appended.
	 * The value of this optional field is the intended average range through which
	 * the corresponding axis moves during the exposure of a frame. Ideally, the
	 * value of this field added to each value of ``AXISNAME`` would agree with the
	 * corresponding values of ``AXISNAME_end``, but there is a possibility of significant
	 * differences. Use of ``AXISNAME_end`` is recommended.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public IDataset getAXISNAME_increment_set();
	
	/**
	 * ``AXISNAME_increment_set`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_increment_set`` has been appended.
	 * The value of this optional field is the intended average range through which
	 * the corresponding axis moves during the exposure of a frame. Ideally, the
	 * value of this field added to each value of ``AXISNAME`` would agree with the
	 * corresponding values of ``AXISNAME_end``, but there is a possibility of significant
	 * differences. Use of ``AXISNAME_end`` is recommended.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param AXISNAME_increment_set the AXISNAME_increment_set
	 */
	public DataNode setAXISNAME_increment_set(IDataset AXISNAME_increment_set);

	/**
	 * ``AXISNAME_increment_set`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_increment_set`` has been appended.
	 * The value of this optional field is the intended average range through which
	 * the corresponding axis moves during the exposure of a frame. Ideally, the
	 * value of this field added to each value of ``AXISNAME`` would agree with the
	 * corresponding values of ``AXISNAME_end``, but there is a possibility of significant
	 * differences. Use of ``AXISNAME_end`` is recommended.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  the value.
	 */
	public Number getAXISNAME_increment_setScalar();

	/**
	 * ``AXISNAME_increment_set`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_increment_set`` has been appended.
	 * The value of this optional field is the intended average range through which
	 * the corresponding axis moves during the exposure of a frame. Ideally, the
	 * value of this field added to each value of ``AXISNAME`` would agree with the
	 * corresponding values of ``AXISNAME_end``, but there is a possibility of significant
	 * differences. Use of ``AXISNAME_end`` is recommended.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @param AXISNAME_increment_set the AXISNAME_increment_set
	 */
	public DataNode setAXISNAME_increment_setScalar(Number AXISNAME_increment_set);
  
	/**
	 * Get all AXISNAME_increment_set fields:
	 *
	 * ``AXISNAME_increment_set`` is a placeholder for a name constructed from the actual
	 * name of an axis to which ``_increment_set`` has been appended.
	 * The value of this optional field is the intended average range through which
	 * the corresponding axis moves during the exposure of a frame. Ideally, the
	 * value of this field added to each value of ``AXISNAME`` would agree with the
	 * corresponding values of ``AXISNAME_end``, but there is a possibility of significant
	 * differences. Use of ``AXISNAME_end`` is recommended.
	 * <p>
	 * <b>Units:</b> NX_TRANSFORMATION
	 * <b>Type:</b> NX_NUMBER
	 * </p>
	 * 
	 * @return  a map from node names to the ? extends IDataset for that node.
	 */
	public Map<String, ? extends IDataset> getAllAXISNAME_increment_set();

}
