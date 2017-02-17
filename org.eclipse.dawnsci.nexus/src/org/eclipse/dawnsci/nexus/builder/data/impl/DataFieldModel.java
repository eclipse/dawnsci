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

import java.util.Objects;

import org.eclipse.dawnsci.nexus.NXdata;

/**
 * A model of how a field in a nexus object should be mapped when it is added
 * (i.e. linked to) in an {@link NXdata} group.
 * {@link AxisFieldModel} extends this class for axis fields (which include all
 * fields except the signal field).
 */
public class DataFieldModel {
	
	private final String sourceFieldName;
	
	private final int fieldRank;
	
	private String destinationFieldName;
	
	public DataFieldModel(String sourceFieldName, int fieldRank) {
		this(sourceFieldName, sourceFieldName, fieldRank);
	}
	
	public DataFieldModel(String sourceFieldName, String destinationFieldName, int fieldRank) {
		Objects.requireNonNull(sourceFieldName);
		Objects.requireNonNull(destinationFieldName);
//		if (fieldRank < 1) {
//			throw new IllegalArgumentException(MessageFormat.format(
//					"Dataset rank must be at least 1, was {0}: {1}", fieldRank, sourceFieldName));
//		}
		this.sourceFieldName = sourceFieldName;
		this.destinationFieldName = destinationFieldName;
		this.fieldRank = fieldRank;
	}

	public String getDestinationFieldName() {
		return destinationFieldName;
	}

	public void setDestinationFieldName(String destinationFieldName) {
		this.destinationFieldName = destinationFieldName;
	}

	public String getSourceFieldName() {
		return sourceFieldName;
	}
	
	public int getFieldRank() {
		return fieldRank;
	}
	
	protected void appendMemberFields(StringBuilder sb) {
		sb.append(getClass().getSimpleName());
		sb.append("sourceFieldName=" + sourceFieldName);
		sb.append(", fieldRank=" + fieldRank);
		sb.append(", destinationFieldName=" + destinationFieldName);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" [");
		appendMemberFields(sb);
		sb.append("]");
		
		return sb.toString();
	}
	
}
