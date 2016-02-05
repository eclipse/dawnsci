/*******************************************************************************
 * Copyright (c) 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.histogram;

/**
 * Enum used to list all colormaps categories
 */
public enum HistoCategory {
		ALL("All", "List of all colormaps"),
		SEQUENTIAL1 ("Sequential 1", "Approximately monochromatic colormaps varying smoothly between two color tones---usually from low saturation (e.g. white) to high saturation (e.g. a bright blue)"), 
		SEQUENTIAL2 ("Sequential 2", "Approximately monochromatic colormaps varying smoothly between two-three color tones"), 
		UNIFORM ("Uniform", "Perceptually Uniform sequential colormaps"),
		MISCELLANEOUS ("Miscellaneous", "Colormaps that don't fit into other categories"),
		DIVERGENT ("Divergent", "Colour maps with median value (usually light in color) and which vary smoothly to two different color tones at high and low values"), 
		QUALITATIVE ("Qualitative", "Colormaps which vary rapidly in color; useful for choosing a set of discrete colors");

		private final String name;
		private final String description;

		private HistoCategory(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public static String[] names() {
			HistoCategory[] states = values();
			String[] names = new String[states.length];
			for (int i = 0; i < states.length; i++) {
				names[i] = states[i].name();
			}
			return names;
		}

		public static HistoCategory getCategory(String category) {
			HistoCategory[] states = values();
			for (int i = 0; i < states.length; i++) {
				if (states[i].name().equals(category))
					return states[i];
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
	}