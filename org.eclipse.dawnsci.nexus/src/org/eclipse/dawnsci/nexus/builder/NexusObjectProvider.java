/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.builder;

import java.util.List;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;

/**
 * Defines the interface for a class that can create a a NeXus object of a particular type.
 * (i.e. an instance of a NeXus base class).
 * For example, this interface can be implemented by classes representing devices such
 * as positioners or detectors; alternatively a wrapper class implementing this class
 * could contain objects representing such devices, this allows for greater decoupling. 
 * 
 * @param <N> a subinterface of {@link NXobject} that an object 
 */
public interface NexusObjectProvider<N extends NXobject> extends NexusEntryModification {

	/**
	 * Get the name of the provider. This is used as the name of the 
	 * NeXus object (i.e. group) in the parent group to which it is added.
	 * @return name of base
	 */
	public String getName();

	/**
	 * Return the NeXus base class enum value for the type NeXus object this
	 * provider creates.
	 * @return the {@link NexusBaseClass} for this object provider
	 */
	public NexusBaseClass getNexusBaseClass();

	/**
	 * Creates and returns the nexus object for this provider.
	 * @param nodeFactory node factory node factory to use to create the nexus object
	 * @return new nexus object
	 * @throws NexusException if the nexus object could not be created for any reason 
	 */
	public N createNexusObject(NexusNodeFactory nodeFactory) throws NexusException;
	
	/**
	 * Returns the nexus object, creating it if necessary
	 * @param nodeFactory node factory to create the nexus object
	 * @param createIfNecessary <code>true</code> to create the nexus object if necessary,
	 *    <code>false</code> otherwise
	 * @return nexus object
	 * @throws NexusException if the nexus object could not be created for any reason 
	 */
	public N getNexusObject(NexusNodeFactory nodeFactory, boolean createIfNecessary) throws NexusException;
	
	/**
	 * If {@link #createNexusObject(NexusNodeFactory)} has previously been invoked
	 * on this object, returns the NeXus object that was returned by that method,
	 * otherwise returns <code>null</code>. This method should <em>not</em> return a new
	 * NeXus object each time.
	 * @return NeXus object or <code>null</code>
	 */
	public N getNexusObject();

	/**
	 * Returns the category for this {@link NexusObjectProvider}. When adding
	 * a nexus object to a {@link NexusEntryBuilder}, the nexus object will be added
	 * to a group of this type, if one exists in the skeleton tree. For example
	 * a {@link NexusObjectProvider} that provides an {@link NXpositioner} would normally
	 * be added to the {@link NXinstrument} group, but if
	 * this method returns {@link NexusBaseClass#NX_SAMPLE}, then it will instead be added
	 * to the {@link NXsample} group.
	 * @return category for this object
	 */
	public NexusBaseClass getCategory();

	/**
	 * Returns the name of the external HDF5 file that this device writes
	 * its data to, or <code>null</code> if none
	 * @return name of external file, or <code>null</code>
	 */
	public String getExternalFileName();
	
	/**
	 * Returns the rank of the external dataset with the given field name.
	 * @param fieldName field name
	 * @return rank of external dataset with given field name
	 * @throws IllegalArgumentException if no such field exists
	 */
	public int getExternalDatasetRank(String fieldName);

	// methods below this line are relevant only when adding this object to a NexusDataBuilder 
	
	/**
	 * Returns the axis data field names for this object. These are the fields
	 * that will be linked to when this this object is added to
	 * an {@link NexusDataBuilder} to construct an {@link NXdata} group.
	 * 
	 * This method should not return the names of primary data fields, nor should it
	 * return the names of data fields which should only be added to the {@link NXdata} groups
	 * for a particular primary data field (an {@link NXdata} group is added to the scan for
	 * each primary data field, as returned by {@link #getPrimaryDataFieldName()} and
	 * {@link #getAdditionalPrimaryDataFieldNames()}). The primary data field will also be linked
	 * to in an {@link NXdata} group, except where this device is the primary device for the scan
	 * where it is not linked to in the {@link NXdata} groups for additional primary data fields.
	 *  
	 * @return name of data fields for this object
	 */
	public List<String> getAxisDataFieldNames();
	
	/**
	 * Returns the name of the default data field to write to within the nexus object.
	 * If this object is added as the primary device to an {@link NXdata} group, then
	 * this is the field name of the default field, i.e. the field referred to by
	 * the <code>@signal</code> attribute.
	 * <p>
	 * If additional {@link NXdata} groups should be created for other fields in this scan,
	 * then the names of these fields should be returned by {@link #getAdditionalPrimaryDataFieldNames()}.
	 *
	 * @return default data field name, this cannot be <code>null</code>
	 */
	public String getPrimaryDataFieldName();
	
	/**
	 * Returns the names of any additional primary data fields for this device.
	 * This method indicates that if this device is to be used to create an
	 * {@link NXdata} with the field {@link #getPrimaryDataFieldName()}
	 * as the default (signal field), then additional {@link NXdata} groups
	 * should be created for each of these fields.
	 * 
	 * @return additional primary data field names
	 */
	public List<String> getAdditionalPrimaryDataFieldNames();
	
	/**
	 * Returns the names of any data fields that are axes for the primary data field with
	 * the given name. These data fields are those that should be added to the {@link NXdata}
	 * group for the primary data field with the given name (and not those for other primary
	 * data fields), in addition to the data fields returned by {@link #getAxisDataFieldNames()}
	 * @param primaryDataFieldName primary data field name
	 * @return names of data fields
	 */
	public List<String> getAxisDataFieldsForPrimaryDataField(String primaryDataFieldName);
	
	/**
	 * Returns the name of the default axis field for this nexus object, if any.
	 * If this object is added as a device to an {@link NXdata} then this
	 * is the field that will be added as a default axis of the <code>@signal</code> field,
	 * for example for a positioner this may be the demand field.
	 * @return name of demand field, or <code>null</code> if none.
	 */
	public String getDefaultAxisDataFieldName();
	
	/**
	 * Returns the dimension of the given primary data field for which the data field with the
	 * given name is a default axis, or <code>null</code> if this field does
	 * not provide a default axis to the default data field.
	 * This method is required only when this device provides the default data field
	 * of an {@link NXdata} group (i.e. that referred to by the <code>@signal</code> attribute),
	 * and additional data fields within this device provide default axis for that data field
	 * @param primaryDataFieldName name of primary data field
	 * @param dataFieldName data field
	 * @return dimension of the default data field for which the field with the
	 *   given name provides a default axis, or <code>null</code> if none
	 */
	public Integer getDefaultAxisDimension(String primaryDataFieldName, String dataFieldName);

	/**
	 * Returns the dimension mappings between the data field and
	 * the primary data field with the given names.
	 * This method is required only when this device provides the default data
	 * field of an {@link NXdata} group (i.e. that referred to by the <code>signal</code>
	 * attribute), and additional data fields within that 
	 * and the default data field of this device.
	 * @param primaryDataFieldName field name
	 * @param dataFieldName data field name
	 * @return dimension mappings between the field with the given name and the
	 *    default data field
	 */
	public int[] getDimensionMappings(String primaryDataFieldName, String dataFieldName);
	
	/**
	 * Returns whether the names of the fields within the nexus object should be prefixed with the
	 * device name when linked to from an {@link NXdata} group. If this method returns
	 * <code>true</code> and just one data field
	 * from this device is added to the {@link NXdata}, then the device name will be used as the
	 * name of the field.
	 * @return <code>true</code> to use the device name when linking fields in an {@link NXdata}
	 *     group, <code>false</code> to not use the device name, <code>null</code> unspecified  
	 */
	public Boolean getUseDeviceNameInNXdata();
	
}
