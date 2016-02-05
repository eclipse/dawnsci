package org.eclipse.dawnsci.nexus.builder;

import org.eclipse.dawnsci.nexus.NXdata;

/**
 * A model for how the dimensions of a field correspond to the dimensions
 * of the default dataset of an {@link NXdata} group.
 */
class FieldDimensionModel {
	
	private Integer defaultAxisDimension = null;
	
	private int[] dimensionMappings = null;
	
	public FieldDimensionModel() {
		// do nothing
	}
	
	public FieldDimensionModel(Integer defaultAxisDimension, int[] dimensionMappings) {
		this.defaultAxisDimension = defaultAxisDimension;
		this.dimensionMappings = dimensionMappings;
	}

	public Integer getDefaultAxisDimension() {
		return defaultAxisDimension;
	}

	/**
	 * Sets the default axis dimension for this data field to the given value.
	 * This is the dimension of the default data field of the {@link NXdata} group
	 * for which this field provides a default axis when plotting the data.
	 * @param defaultAxisDimension default axis dimension index
	 */
	public void setDefaultAxisDimension(Integer defaultAxisDimension) {
		this.defaultAxisDimension = defaultAxisDimension;
	}

	public int[] getDimensionMappings() {
		return dimensionMappings;
	}

	/**
	 * Sets the dimension mappings for the given field to the given value.
	 * This is the mapping from the dimensions of this field to the dimensions
	 * of the default data field of the {@link NXdata} group.
	 * @param dimensionMappings dimension mappings
	 */
	public void setDimensionMappings(int... dimensionMappings) {
		this.dimensionMappings = dimensionMappings;
	}
	
}