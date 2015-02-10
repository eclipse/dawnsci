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

public class InvalidRankException extends OperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3684722892559338586L;
	private int rank;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public InvalidRankException(IOperation<?, ?> operation, String reason) {
		super(operation, reason);
	}

	public InvalidRankException(IOperation<?, ?> operation, Throwable cause) {
		super(operation, cause);
	}

	public InvalidRankException(IOperation<?, ?> operation) {
		super(operation);
	}
}
