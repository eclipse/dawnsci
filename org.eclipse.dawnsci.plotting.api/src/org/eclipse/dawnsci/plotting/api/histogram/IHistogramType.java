/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 */

package org.eclipse.dawnsci.plotting.api.histogram;

/**
 * Interface used to store the colour map types
 */
public interface IHistogramType {
	enum HistoType {
		ALL,
		SEQUENTIAL1, 
		SEQUENTIAL2, 
		UNIFORM,
		MISCELLANEOUS,
		DIVERGENT, 
		QUALITATIVE;

		public static String[] names() {
			HistoType[] states = values();
			String[] names = new String[states.length];
			for (int i = 0; i < states.length; i++) {
				names[i] = states[i].name();
			}
			return names;
		}

		public static HistoType getType(String sType) {
			HistoType[] states = values();
			for (int i = 0; i < states.length; i++) {
				if (states[i].name().equals(sType))
					return states[i];
			}
			return null;
		}
		
	}

	public HistoType getType();
}
