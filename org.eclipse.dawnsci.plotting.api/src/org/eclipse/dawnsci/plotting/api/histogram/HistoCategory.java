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
		SEQUENTIAL1 ("Sequential 1 (approximately monochromatic with variation between two colours)", "Approximately monochromatic colormaps varying smoothly between two color tones---usually from low saturation (e.g. white) to high saturation (e.g. a bright blue)"), 
		SEQUENTIAL2 ("Sequential 2 (variation between two or more colours)", "Approximately monochromatic colormaps varying smoothly between two-three color tones"), 
		UNIFORM ("Uniform (perceptually uniform sequential)", "Perceptually Uniform sequential colormaps"),
		DIVERGENT ("Divergent (with median value)", "Colour maps with median value (usually light in color) and which vary smoothly to two different color tones at high and low values"), 
		QUALITATIVE ("Qualitative (rapid variation in colours)", "Colormaps which vary rapidly in color; useful for choosing a set of discrete colors"),
		MISCELLANEOUS ("Miscellaneous", "Colormaps that don't fit into other categories");

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
				names[i] = states[i].getName();
			}
			return names;
		}

		public static HistoCategory getCategory(String category) {
			HistoCategory[] states = values();
			for (int i = 0; i < states.length; i++) {
				if (states[i].getName().equals(category))
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