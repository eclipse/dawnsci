package org.eclipse.dawnsci.nexus.builder.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Models the mappings for a primary data field from the other data fields within the same device. 
 * @author Matthew Dickie
 */
public class PrimaryDataFieldModel {
	
	private Map<String, DataFieldDimensionModel> dataFieldDimensionModels = new HashMap<>(4);

	public PrimaryDataFieldModel() {
		// nothing to do
	}
	
	public void addDataField(String dataFieldName, Integer defaultAxisDimension) {
		DataFieldDimensionModel fieldDimModel = new DataFieldDimensionModel(defaultAxisDimension);
		dataFieldDimensionModels.put(dataFieldName, fieldDimModel);
	}
	
	public void addDataField(String dataFieldName, Integer defaultAxisDimension, int[] dimensionMappings) {
		DataFieldDimensionModel fieldDimModel = new DataFieldDimensionModel(defaultAxisDimension,
				dimensionMappings);
		dataFieldDimensionModels.put(dataFieldName, fieldDimModel);
	}
	
	public Integer getDefaultAxisDimension(String dataFieldName) {
		DataFieldDimensionModel fieldDimModel = dataFieldDimensionModels.get(dataFieldName);
		if (fieldDimModel != null) {
			return fieldDimModel.getDefaultAxisDimension();
		}
		
		return null;
	}
	
	public int[] getDimensionMappings(String dataFieldName) {
		DataFieldDimensionModel fieldDimModel = dataFieldDimensionModels.get(dataFieldName);
		if (fieldDimModel != null) {
			return fieldDimModel.getDimensionMappings();
		}
		
		return null;
	}
	
}
