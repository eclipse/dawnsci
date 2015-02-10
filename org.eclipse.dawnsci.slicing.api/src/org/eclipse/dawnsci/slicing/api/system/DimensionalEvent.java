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
package org.eclipse.dawnsci.slicing.api.system;

import java.util.EventObject;

public class DimensionalEvent extends EventObject {

	private DimsDataList dimsList;

	public DimensionalEvent(Object source, DimsDataList dl) {
		super(source);
		this.dimsList = dl;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4461117258433723938L;

	public DimsDataList getDimsList() {
		return dimsList;
	}

	public void setDimsList(DimsDataList dimsList) {
		this.dimsList = dimsList;
	}

}
