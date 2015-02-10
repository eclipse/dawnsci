/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.processing;

public enum OperationRank {

	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	ANY(-1),
	NONE(-2), 
	SAME(-3); // Denotes that this is the same as the input
	
	private final int rank;
	
	OperationRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}
	
	public String getLabel() {
		if (this==ANY)  return "Any";
		if (this==SAME) return "Same as input";
		if (this==NONE) return "None";
		return String.valueOf(rank);
	}

	public static OperationRank get(int rank) {
		for (OperationRank or : values()) {
			if (or.rank == rank) return or;
		}
		return null;
	}

	public boolean isDiscrete() {
		return this!=ANY && this!=NONE;
	}

	public boolean isCompatibleWith(OperationRank with) {
		
		if (this==NONE || with==NONE) return false;
		if (this.isDiscrete() && with.isDiscrete()) return this.rank==with.rank;
		if (ANY==with || this==ANY) return true;
		
		return false;
	}

}
