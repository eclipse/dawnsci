package org.eclipse.dawnsci.nexus.builder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;

/**
 * A object of this class wraps an {@link NexusObjectProvider} and contains
 * additional information that can be used by a {@link NexusDataBuilder}
 * describing how to add the fields for this device to an {@link NXdata}
 * group when its {@link NexusDataBuilder#addAxisDevice(AxisDevice)} method
 * is called.
 * 
 * @author Matthew Dickie
 * @param <N>
 */
public class AxisDevice<N extends NXobject> {
	
	private static class FieldInfo {
		
		private String destinationFieldName;
		
		private int[] dimensionMapping;

		public FieldInfo(String destinationFieldName, int[] dimensionMapping) {
			this.destinationFieldName = destinationFieldName;
			this.dimensionMapping = dimensionMapping;
		}
		
		public String getDestinationName() {
			return destinationFieldName;
		}
		
		public int[] getDimensionMappings() {
			return dimensionMapping;
		}
		
	}
	
	private final NexusObjectProvider<N> nexusObjectProvider;
	
	private int[] dimensionMappings = null;
	
	private Integer defaultAxisDimension = null;
	
	private boolean useDeviceName = true;
	
	private String defaultAxisSourceFieldName = null;
	
	private Map<String, FieldInfo> sourceFields = new LinkedHashMap<>();
	
	public AxisDevice(NexusObjectProvider<N> nexusObjectProvider) {
		this(nexusObjectProvider, null);
	}
	
	public AxisDevice(NexusObjectProvider<N> nexusObjectProvider, int[] dimensionMappings) {
		this(nexusObjectProvider, null, dimensionMappings);
	}
	
	public AxisDevice(NexusObjectProvider<N> nexusObjectProvider, int defaultDimension) {
		this(nexusObjectProvider, defaultDimension, null);
	}

	public AxisDevice(NexusObjectProvider<N> nexusObjectProvider, Integer defaultDimension, int[] dimensionMappings) {
		this(nexusObjectProvider, defaultDimension, null, dimensionMappings);
	}
	
	public AxisDevice(NexusObjectProvider<N> nexusObjectProvider, Integer defaultDimension,
			String defaultAxisSourceFieldName ,int[] dimensionMappings) {
		this.nexusObjectProvider = nexusObjectProvider;
		this.dimensionMappings = dimensionMappings;
		this.defaultAxisDimension = defaultDimension;
		this.dimensionMappings = dimensionMappings;
		this.defaultAxisSourceFieldName = defaultAxisSourceFieldName;
		
		// add the fields for this device
		if (nexusObjectProvider.getDemandFieldName() != null) {
			sourceFields.put(nexusObjectProvider.getDemandFieldName(), null);
		}
		for (String sourceFieldName : nexusObjectProvider.getDataFieldNames()) {
			sourceFields.put(sourceFieldName, null);
		}
	}
	
	public NexusObjectProvider<N> getNexusObjectProvider() {
		return nexusObjectProvider;
	}

	public int[] getDimensionMappings() {
		return dimensionMappings;
	}

	public AxisDevice<N> setDimensionMappings(int... dimensionMappings) {
		this.dimensionMappings = dimensionMappings;
		return this;
	}

	public Integer getDefaultAxisDimension() {
		return defaultAxisDimension;
	}

	public AxisDevice<N> setDefaultAxisDimension(int defaultAxisDimension) {
		this.defaultAxisDimension = defaultAxisDimension;
		return this;
	}

	public List<String> getSourceFieldNames() {
		return new ArrayList<>(sourceFields.keySet());
	}
	
	public AxisDevice<N> clearSourceFields() {
		this.sourceFields.clear();
		return this;
	}

	public AxisDevice<N> setSourceFields(String... sourceFieldNames) {
		this.sourceFields.clear();
		for (String sourceField : sourceFieldNames) {
			this.sourceFields.put(sourceField, null);
		}
		return this;
	}
	
	public AxisDevice<N> addSourceField(String sourceFieldName) {
		this.sourceFields.put(sourceFieldName, null);
		return this;
	}
	
	public AxisDevice<N> addSourceFields(String... sourceFieldNames) {
		for (String sourceFieldName : sourceFieldNames) {
			this.sourceFields.put(sourceFieldName, null);
		}
		return this;
	}
	
	public AxisDevice<N> addSourceField(String sourceFieldName, String destinationFieldName) {
		sourceFields.put(sourceFieldName, new FieldInfo(destinationFieldName, null));
		return this;
	}
	
	public AxisDevice<N> addSourceField(String sourceFieldName, int... dimensionMapping) {
		sourceFields.put(sourceFieldName, new FieldInfo(null, dimensionMapping));
		return this;
	}
	
	public AxisDevice<N> addSourceField(String sourceFieldName, String destinationFieldName, int... dimensionMapping) {
		sourceFields.put(sourceFieldName, new FieldInfo(destinationFieldName, dimensionMapping));
		return this;
	}
	
	/**
	 * Returns the name of the field within the nexus object that 
	 * provides a default axis for a dimension of the main dataset of the {@link NXdata}
	 * if any.
	 * <ol>
	 *   <li>The value set by {@link #setDefaultAxisSourceFieldName(String)},
	 *   	if not <code>null</code>;</li>
	 *   <li>otherwise the value of the demand field as returned by
	 *   {@link NexusObjectProvider#getDemandFieldName()}, if not <code>null</code>;</li>
	 *   <li>the default data field, as retuned by {@link NexusObjectProvider#getDefaultDataFieldName()},
	 *   if not <code>null</code></li>
	 *   <li>otherwise <code>null</code>.
	 * </ol>
	 * 
	 * @return name of source field to use as a default axis of the main dataset
	 *   of the {@link NXdata}, or <code>null</code>
	 */
	public String getDefaultAxisSourceFieldName() {
		if (getDefaultAxisDimension() != null) {
			if (defaultAxisSourceFieldName != null) {
				return defaultAxisSourceFieldName;
			}
			
			if (nexusObjectProvider.getDemandFieldName() != null) {
				return nexusObjectProvider.getDemandFieldName();
			}
			
			return nexusObjectProvider.getDefaultDataFieldName();
		}
		
		return null;
	}
	
	public AxisDevice<N> setDefaultAxisSourceFieldName(String defaultAxisSourceFieldName) {
		this.defaultAxisSourceFieldName = defaultAxisSourceFieldName;
		return this;
	}
	
	public boolean getUseDeviceName() {
		return useDeviceName;
	}
	
	public AxisDevice<N> setUseDeviceName(boolean useDeviceName) {
		this.useDeviceName = useDeviceName;
		return this;
	}
	
	public int[] getDimensionMapping(String sourceFieldName) {
		FieldInfo fieldInfo = sourceFields.get(sourceFieldName);
		if (fieldInfo != null) {
			return fieldInfo.getDimensionMappings();
		}
		
		// use the default dimension mapping for this device
		return dimensionMappings;
	}
	
	public String getDestinationFieldName(String sourceFieldName) {
		String destinationFieldName = null;
		FieldInfo fieldInfo = sourceFields.get(sourceFieldName);
		if (fieldInfo != null) {
			destinationFieldName = fieldInfo.getDestinationName();
			if (destinationFieldName != null) {
				return destinationFieldName;
			}
		}
		
		if (useDeviceName) {
			if (sourceFields.size() == 1) {
				return nexusObjectProvider.getName();
			} else {
				return nexusObjectProvider.getName() + "_" + sourceFieldName;
			}
		}
		
		return sourceFieldName;
	}
	
}
