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

package org.eclipse.dawnsci.plotting.api.jreality.legend;

import java.util.EventObject;

/**
 *
 */
public class LegendChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int legendEntryNr;
	
	public LegendChangeEvent(Object source, int entryNr) {
		super(source);
		this.legendEntryNr = entryNr;
	}

	public int getEntryNr() {
		return legendEntryNr;
	}


}
