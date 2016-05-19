package org.eclipse.dawnsci.nexus.builder.data.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Models the mappings for a primary data field from the other data fields within the same device. 
 * @author Matthew Dickie
 */
public class PrimaryDataFieldModel {
	
	private static final class AxisFieldDimensionModel {
		
		private final Integer defaultAxisDimension;
		private final int[] dimensionMappings;
		
		AxisFieldDimensionModel(Integer defaultAxisDimension, int[] dimensionMappings) {
			this.defaultAxisDimension = defaultAxisDimension;
			this.dimensionMappings = dimensionMappings;
		}

		public Integer getDefaultAxisDimension() {
			return defaultAxisDimension;
		}

		public int[] getDimensionMappings() {
			return dimensionMappings;
		}
		
	}
	
	private final Map<String, AxisFieldDimensionModel> axisFieldDimensionModels = new LinkedHashMap<>();

	public void addAxisField(String axisFieldName, Integer defaultAxisDimension,
			int[] dimensionMappings) {
		axisFieldDimensionModels.put(axisFieldName,
				new AxisFieldDimensionModel(defaultAxisDimension, dimensionMappings));
	}
	
	public List<String> getAxisFieldNames() {
		return new ArrayList<>(axisFieldDimensionModels.keySet());
	}
	
	public Integer getDefaultAxisDimension(String dataFieldName) {
		AxisFieldDimensionModel axisFieldDimensionModel = axisFieldDimensionModels.get(dataFieldName);
		if (axisFieldDimensionModel != null) {
			return axisFieldDimensionModel.getDefaultAxisDimension();
		}
		
		return null;
	}
	
	public int[] getDimensionMappings(String dataFieldName) {
		AxisFieldDimensionModel axisFieldDimensionModel = axisFieldDimensionModels.get(dataFieldName);
		if (axisFieldDimensionModel != null) {
			return axisFieldDimensionModel.getDimensionMappings();
		}
		
		return null;
	}
	
}
