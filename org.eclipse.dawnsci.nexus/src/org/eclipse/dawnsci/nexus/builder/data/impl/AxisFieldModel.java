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
package org.eclipse.dawnsci.nexus.builder.data.impl;

import java.util.Arrays;

import org.eclipse.dawnsci.nexus.NXdata;

/**
 * A model for how the dimensions of a data field correspond to the dimensions
 * of the primary data field (i.e. the <code>@signal</code> field ) of an {@link NXdata} group.
 */
public class AxisFieldModel extends DataFieldModel {
	
	private Integer defaultAxisDimension = null;
	
	private int[] dimensionMappings = null;
	
	public AxisFieldModel(String sourceFieldName, int fieldRank) {
		super(sourceFieldName, fieldRank);
	}
	
	/**
	 * Sets the default axis dimension for this data field to the given value.
	 * This is the dimension of the default data field of the {@link NXdata} group
	 * for which this field provides a default axis when plotting the data.
	 * @param defaultAxisDimension default axis dimension index
	 */
	public void setDefaultAxisDimension(Integer defaultAxisDimension) {
		this.defaultAxisDimension = defaultAxisDimension;
	}

	public Integer getDefaultAxisDimension() {
		return defaultAxisDimension;
	}

	/**
	 * Sets the dimension mappings for the given field to the given value.
	 * This is the mapping from the dimensions of this field to the dimensions
	 * of the default data field of the {@link NXdata} group.
	 * @param dimensionMappings dimension mappings
	 */
	public void setDimensionMappings(int... dimensionMappings) {
		this.dimensionMappings = dimensionMappings;
	}

	public int[] getDimensionMappings() {
		return dimensionMappings;
	}

	@Override
	protected void appendMemberFields(StringBuilder sb) {
		super.appendMemberFields(sb);
		sb.append(", defaultAxisDimension = " + defaultAxisDimension);
		sb.append(", dimensionMappings = " + Arrays.toString(dimensionMappings));
	}
	
}