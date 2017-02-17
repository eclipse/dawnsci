/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.nexus.builder.data;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;

/**
 * Represents a device which can be added to an {@link NexusDataBuilder} to build an
 * {@link NXdata} group. It may wrap on otherwise be constructed from an instance of a
 * sub-interface of {@link NXobject}. The method {@link #getFieldNode(String)} should return
 * the node with the given name from the.
 * <p>
 * This is an abstract interface. Any class which implements this interface should also implement
 * either {@link PrimaryDataDevice} (which sh
 * 
 * @param <N> the sub-interface of {@link NXobject} that the nexus object was created from 
 * 
 * @author Matthew Dickie
 */
public interface DataDevice<N extends NXobject> {

	/**
	 * Returns whether this is the primary device for the {@link NXdata} group being built.
	 * This primary device is the device which contains the <code>@signal</code> field for
	 * the {@link NXdata} group.
	 * @return <code>true</code> if this is the primary device, <code>false</code> otherwise
	 */
	public boolean isPrimary();
	
	/**
	 * Returns the nexus object for this data device.
	 * @return nexus object
	 */
	public N getNexusObject();
	
	/**
	 * Returns the list of the axis field names.
	 * @return axis field names
	 */
	public List<String> getAxisFieldNames();
	
	/**
	 * Returns the {@link Node} for the field with the given name in the {@link NXobject}
	 * that this object was created from. In most cases this will be
	 * a {@link DataNode}, but may be a {@link SymbolicNode} in the case of an external link
	 * @param sourceFieldName field name in the existing {@link NXobject}
	 * @return the node with the given name
	 */
	public Node getFieldNode(String sourceFieldName);
	
	/**
	 * Returns the rank of the source field with the given name
	 * @param sourceFieldName field name in the existing {@link NXobject}
	 * @return rank of the field with the given name
	 */
	public int getFieldRank(String sourceFieldName);
	
	/**
	 * Returns the name that the field with the given name in the {@link NXobject} should be
	 * given in the {@link NXdata} group when added (i.e. linked to).
	 * @param sourceFieldName field name in the existing {@link NXobject}
	 * @return destination name
	 */
	public String getDestinationFieldName(String sourceFieldName);
	
	/**
	 * Returns the dimension mappings for the field with the given name. This is an array of
	 * integers each of which specifies the index of the <code>@signal</code> field that
	 * the dimension of the dataset for this field name with the corresponding index maps to.
	 * @param sourceFieldName field name in the existing {@link NXobject}
	 * @return dimension mappings between the field with the given name and the 
	 *    <code>@signal</code> field of the {@link NXdata} group
	 */
	public int[] getDimensionMappings(String sourceFieldName);
	
	/**
	 * Returns the index of the dimension of the <code>@signal</code> field of the {@link NXdata}
	 * group the field of the {@link NXobject} with the given name is a default axis for, or
	 * <code>null</code> if this field is not a default axis of the <code>@signal</code> field.
	 * @param sourceFieldName field name in the existing {@link NXobject}
	 * @return dimension of the signal field that this field is a default axis for,
	 *    or <code>null</code> 
	 */
	public Integer getDefaultAxisDimension(String sourceFieldName);

}
