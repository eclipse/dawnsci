package org.eclipse.dawnsci.nexus.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;

/**
 * A object of this class wraps an {@link NexusObjectProvider} and contains
 * additional information that can be used by a {@link NexusDataBuilder}
 * describing how to add the fields for this device to an {@link NXdata}
 * group when its {@link NexusDataBuilder#addDevice(DataDevice)} method
 * is called.
 * 
 * @author Matthew Dickie
 * @param <N> subinterface of {@link NXobject} created by the
 *   wrapped {@link NexusObjectProvider}
 */
public class DataDevice<N extends NXobject> {
	
	private final NexusObjectProvider<N> nexusObjectProvider;
	
	private int[] deviceDefaultDimensionMappings = null;
	
	private boolean useDeviceName;
	
	private String primaryDataSourceFieldName;
	
	private Map<String, FieldDimensionModel> sourceFields = new LinkedHashMap<>();
	
	private Map<String, String> fieldNameMappings = new HashMap<>();
	
	private String defaultAxisSourceFieldName;
	
	private Integer defaultAxisDimension = null;
	
	public DataDevice(NexusObjectProvider<N> nexusObjectProvider) {
		this(nexusObjectProvider, true, null);
	}
	
	public DataDevice(NexusObjectProvider<N> nexusObjectProvider, boolean useDeviceName) {
		this(nexusObjectProvider, useDeviceName, null);
	}
	
	public DataDevice(NexusObjectProvider<N> nexusObjectProvider,
			Integer defaultAxisDimension, int... dimensionMappings) {
		this(nexusObjectProvider, true, defaultAxisDimension, dimensionMappings);
	}
	
	public DataDevice(NexusObjectProvider<N> nexusObjectProvider, boolean useDeviceName,
			Integer defaultAxisDimension, int... dimensionMappings) {
		this(nexusObjectProvider, useDeviceName, null, defaultAxisDimension, dimensionMappings);
	}
	
	public DataDevice(NexusObjectProvider<N> nexusObjectProvider, boolean useDeviceName,
			String axisFieldName, Integer defaultAxisDimension, int... dimensionMappings) {  
		this.nexusObjectProvider = nexusObjectProvider;
		this.deviceDefaultDimensionMappings = dimensionMappings;
		this.useDeviceName = useDeviceName;
		this.defaultAxisSourceFieldName = axisFieldName;
		this.defaultAxisDimension = defaultAxisDimension;
		
		if (axisFieldName == null && defaultAxisDimension != null) {
			// The default axis dimension applies to the demand field, if there is one
			// otherwise it applies to the the default writable data field
			String demandDataFieldName = nexusObjectProvider.getDemandDataFieldName();
			if (demandDataFieldName != null) {
				this.defaultAxisSourceFieldName = demandDataFieldName;
			} else {
				this.defaultAxisSourceFieldName = nexusObjectProvider.getPrimaryDataFieldName();
			}
		}
		
		List<String> dataFieldNames = nexusObjectProvider.getDataFieldNames();
		for (String dataFieldName : dataFieldNames) {
			sourceFields.put(dataFieldName, null);
		}
	}
	
	public NexusObjectProvider<N> getNexusObjectProvider() {
		return nexusObjectProvider;
	}

	public int[] getDefaultDimensionMappings() {
		return deviceDefaultDimensionMappings;
	}

	/**
	 * Set the default dimension mappings for this device.
	 * @param defaultDimensionMappings
	 * @return
	 */
	public DataDevice<N> setDefaultDimensionMappings(int... defaultDimensionMappings) {
		this.deviceDefaultDimensionMappings = defaultDimensionMappings;
		return this;
	}

	public List<String> getSourceFieldNames() {
		return new ArrayList<>(sourceFields.keySet());
	}
	
	public DataDevice<N> clearSourceFields() {
		sourceFields.clear();
		fieldNameMappings.clear();
		return this;
	}

	public DataDevice<N> setSourceFields(String... sourceFieldNames) {
		this.sourceFields.clear();
		for (String sourceField : sourceFieldNames) {
			this.sourceFields.put(sourceField, null);
		}
		return this;
	}
	
	public DataDevice<N> addSourceField(String sourceFieldName) {
		this.sourceFields.put(sourceFieldName, null);
		return this;
	}
	
	public DataDevice<N> addSourceFields(String... sourceFieldNames) {
		for (String sourceFieldName : sourceFieldNames) {
			this.sourceFields.put(sourceFieldName, null);
		}
		return this;
	}
	
	public DataDevice<N> addSourceField(String sourceFieldName, String destinationFieldName) {
		sourceFields.put(sourceFieldName, null);
		fieldNameMappings.put(sourceFieldName, destinationFieldName);
		return this;
	}
	
	public DataDevice<N> addSourceField(String sourceFieldName, Integer defaultAxisDimension, 
			int... dimensionMappings) {
		if (dimensionMappings != null && dimensionMappings.length == 0) {
			dimensionMappings = null;
		}
		
		sourceFields.put(sourceFieldName,
				new FieldDimensionModel(defaultAxisDimension, dimensionMappings));
		return this;
	}
	
	public DataDevice<N> setDestinationFieldName(String sourceFieldName, String destinationFieldName) {
		if (!sourceFields.containsKey(sourceFieldName)) {
			throw new IllegalArgumentException("Unknown source field: " + sourceFieldName);
		}
		fieldNameMappings.put(sourceFieldName, destinationFieldName);
		return this;
	}
	
	public boolean getUseDeviceName() {
		return useDeviceName;
	}
	
	public DataDevice<N> setUseDeviceName(boolean useDeviceName) {
		this.useDeviceName = useDeviceName;
		return this;
	}
	
	public int[] getDimensionMappings(String sourceFieldName) {
		FieldDimensionModel fieldInfo = sourceFields.get(sourceFieldName);
		if (fieldInfo != null) {
			int[] dimensionMappings = fieldInfo.getDimensionMappings();
			if (dimensionMappings != null) {
				return dimensionMappings;
			}
		}
		
		// use the default dimension mapping for this device
		return deviceDefaultDimensionMappings;
	}
	
	public Integer getDefaultAxisDimension(String sourceFieldName) {
		FieldDimensionModel fieldInfo = sourceFields.get(sourceFieldName);
		if (fieldInfo != null) {
			return fieldInfo.getDefaultAxisDimension();
		}
		if (sourceFieldName.equals(defaultAxisSourceFieldName)) {
			return defaultAxisDimension;
		}
		
		return null;
	}
	
	public String getDestinationFieldName(String sourceFieldName) {
		String destinationName = fieldNameMappings.get(sourceFieldName);
		if (destinationName == null) {
			if (useDeviceName) {
				if (sourceFields.size() == 1) {
					destinationName = nexusObjectProvider.getName();
				} else {
					destinationName = nexusObjectProvider.getName() + "_" + sourceFieldName;
				}
			} else {
				destinationName = sourceFieldName;
			}
		}
		
		return destinationName;
	}

	public String getDefaultAxisSourceFieldName() {
		return defaultAxisSourceFieldName;
	}

	public void setDefaultAxisSourceFieldName(String defaultAxisSourceFieldName) {
		this.defaultAxisSourceFieldName = defaultAxisSourceFieldName;
	}

	public Integer getDefaultAxisDimension() {
		return defaultAxisDimension;
	}

	public void setDefaultAxisDimension(Integer defaultAxisDimension) {
		this.defaultAxisDimension = defaultAxisDimension;
	}

	public String getSignalDataSourceFieldName() {
		if (primaryDataSourceFieldName != null) {
			return primaryDataSourceFieldName;
		}
		
		// by default use default writable data field as primary (@signal) data field
		return nexusObjectProvider.getPrimaryDataFieldName();
	}

	public void setPrimaryDataSourceFieldName(String primaryDataSourceFieldName) {
		this.primaryDataSourceFieldName = primaryDataSourceFieldName;
	}
	
}
