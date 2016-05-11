package org.eclipse.dawnsci.nexus.builder.data.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.data.DataDevice;
import org.eclipse.dawnsci.nexus.builder.data.NexusDataBuilder;

/**
 * A object of this class wraps an {@link NexusObjectProvider} and contains
 * additional information that can be used by a {@link NexusDataBuilder}
 * describing how to add the fields for this device to an {@link NXdata}
 * group when its added, either as a primary data device (by calling
 * {@link NexusDataBuilder#setPrimaryDevice(org.eclipse.dawnsci.nexus.builder.data.PrimaryDataDevice)})
 * or as an axis device (by calling {@link NexusDataBuilder#addAxisDevice(org.eclipse.dawnsci.nexus.builder.data.AxisDataDevice)}
 * 
 * @author Matthew Dickie
 * @param <N> subinterface of {@link NXobject} created by the
 *   wrapped {@link NexusObjectProvider}
 */
public abstract class DataDeviceImpl<N extends NXobject> implements DataDevice<N> {

	private final Map<String, AxisFieldModel> axisFields;
	
	private final N nexusObject;
	
	public DataDeviceImpl(N nexusObject) {
		this.nexusObject = nexusObject;
		this.axisFields = new LinkedHashMap<>();
	}
	
	public void addAxisField(AxisFieldModel axisFieldModel) {
		axisFields.put(axisFieldModel.getSourceFieldName(), axisFieldModel);
	}
	
	private AxisFieldModel getAxisDataFieldModel(String sourceFieldName) {
		AxisFieldModel axisDataFieldModel = axisFields.get(sourceFieldName);
		if (axisDataFieldModel == null) {
			throw new IllegalArgumentException("No such axis field: " + sourceFieldName);
		}
		
		return axisDataFieldModel;
	}

	@Override
	public final N getNexusObject() {
		return nexusObject;
	}

	public List<String> getAxisFieldNames() {
		return new ArrayList<>(axisFields.keySet());
	}
	
	public int[] getDimensionMappings(String sourceFieldName) {
		return getAxisDataFieldModel(sourceFieldName).getDimensionMappings();
	}
	
	public Integer getDefaultAxisDimension(String sourceFieldName) {
		return getAxisDataFieldModel(sourceFieldName).getDefaultAxisDimension();
	}
	
	public String getDestinationFieldName(String sourceFieldName) {
		return getAxisDataFieldModel(sourceFieldName).getDestinationFieldName();
	}
	
	@Override
	public Node getFieldNode(String sourceFieldName) {
		return nexusObject.getNode(sourceFieldName);
	}

	@Override
	public int getFieldRank(String sourceFieldName) {
		return getAxisDataFieldModel(sourceFieldName).getFieldRank();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("nexusObject=" + nexusObject);
		appendFields(sb);
		sb.append("]");
		
		return sb.toString();
	}
	
	protected void appendFields(StringBuilder sb) {
		sb.append(", axisFields={");
		sb.append("\n");
		for (Map.Entry<String, AxisFieldModel> entry : axisFields.entrySet()) {
			sb.append("  ");
			sb.append(entry.getKey());
			sb.append(": ");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		sb.append("}");
	}

}
