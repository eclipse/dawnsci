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
import org.eclipse.dawnsci.nexus.NexusNodeFactory;

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
	 * @return
	 */
	public NexusBaseClass getNexusBaseClass();

	/**
	 * Creates and returns the nexus object for this provider.
	 * @param nodeFactory node factory node factory to use to create the nexus object
	 * @return new nexus object
	 */
	public N createNexusObject(NexusNodeFactory nodeFactory);
	
	/**
	 * Returns the nexus object, creating it if necessary
	 * @param createIfNecessary
	 * @return nexus object
	 */
	public N getNexusObject(NexusNodeFactory nodeFactory, boolean createIfNecessary);
	
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
	 * Returns the data field names for this object. These are the fields
	 * that will be linked to when this object is added to a {@link NexusDataBuilder}.
	 * @return
	 */
	public List<String> getDataFieldNames();
	
	/**
	 * Returns the name of the default data field to write to within the nexus object.
	 * If this object is added as the primary device to an {@link NXdata} group, then
	 * this is the field name of the default field, i.e. the field referred to by
	 * the <code>@signal</code> attribute.
	 *
	 * @return default data field name
	 */
	public String getDefaultWritableDataFieldName();
	
	/**
	 * Returns the name of the demand field for this nexus object, if any.
	 * If this object is added as a device to an {@link NXdata}, then this
	 * is the field that will be added as an axis of the default dataset.
	 * @return name of demand field, or <code>null</code> if none.
	 */
	public String getDemandDataFieldName();
	
	/**
	 * Returns the dimension of the default data field for which the field with the
	 * given name is a default axis, or <code>null</code> if this field does
	 * not provide a default axis to the default data field.
	 * This method is required only when this device provides the default data field
	 * of an {@link NXdata} group (i.e. that referred to by the <code>@signal</code> attribute),
	 * and additional data fields within this device provide default axis for that data field
	 * @return dimension of the default data field for which the field with the
	 *   given name provides a default axis, or <code>null</code> if none
	 */
	public Integer getDefaultAxisDimension(String fieldName);
	
	/**
	 * Returns the dimension mappings between the field with the given name and
	 * the default data field of this device.
	 * This method is required only when this device provides the default data
	 * field of an {@link NXdata} group (i.e. that referred to by the <code>signal</code>
	 * attribute), and additional data fields within that 
	 * and the default data field of this device.
	 * @param fieldName field name
	 * @return dimension mappings between the field with the given name and the
	 *    default data field
	 */
	public int[] getDimensionMappings(String fieldName);
	
}
