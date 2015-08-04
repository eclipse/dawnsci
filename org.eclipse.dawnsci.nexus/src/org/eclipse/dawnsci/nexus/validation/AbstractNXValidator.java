package org.eclipse.dawnsci.nexus.validation;

import java.util.List;

import javax.measure.unit.Unit;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.metadata.MetadataType;
import org.eclipse.dawnsci.analysis.api.metadata.UnitMetadata;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.nexus.NXentry;

public abstract class AbstractNXValidator {
	
	protected void failValidation(final String message) throws NexusValidationException {
		if (message == null) {
			throw new NexusValidationException(message);
		}
	}
	
	public void validate(String message, boolean condition) throws NexusValidationException {
		if (!condition) {
			failValidation(message);
		}
	}
	
	
	public void validateNotNull(String message, Object object) throws NexusValidationException {
		validate(message, object != null);
	}
	
	public void validateGroupNotNull(String groupName, String typeName, GroupNode groupNode) throws NexusValidationException {
		validateNotNull((groupName == null ? "The unnamed group " : "The group '" + groupName + "' ") + "of type " + typeName + " must not be null", groupNode);
	}
	
	public void validateDatasetFieldNotNull(String fieldName, IDataset dataset) throws NexusValidationException {
		validateNotNull("The field " + fieldName + " must be set", dataset);
	}
	
	public void validateAttributeNotNull(String attributeName, Node node) throws NexusValidationException {
		Attribute attribute = node.getAttribute(attributeName);
		validateNotNull("The attribute " + attributeName + " must be set", attribute);
		// TODO check the line below: does the dataset have to be non-null?
		validateNotNull("The dataset for the attribute " + attributeName + " must be set", attribute);
	}
	
	public void validateEnumerationField(String fieldName, IDataset dataset, String... permittedValues) throws NexusValidationException {
		// a field with an enumeration cannot be null
		validateDatasetFieldNotNull(fieldName, dataset);
		// TODO: we assume that enumerations must be strings: is this correct?
		validateDatasetFieldType(fieldName, dataset, NexusDataType.NX_CHAR);
		
		if (dataset.getRank() > 0) {
			failValidation("The enumeration field " + fieldName + " must have a rank of 0");
		}
		
		// the size of the field must be 1
		if (dataset.getSize() != 1) {
			failValidation("The enumeration field " + fieldName + " must have a size of 1");
		}
		
		String value = dataset.getString(0);
		validateNotNull("The value of the enumeration field " + fieldName + " cannot be null.", value);
		
		boolean valuePermitted = false;
		for (String permittedValue : permittedValues) {
			if (value.equals(permittedValue)) {
				valuePermitted = true;
				break;
			}
		}
		
		if (!valuePermitted) {
			failValidation("The value of the field " + fieldName + " must be one of the enumerated values.");
		}
	}
	
	public void validateDatasetField(final String fieldName, IDataset dataset, final NexusDataType type) throws NexusValidationException {
		validateDatasetFieldNotNull(fieldName, dataset);
		validateDatasetFieldType(fieldName, dataset, type);
	}
	
	public void validateDatasetField(final String fieldName, IDataset dataset, final NexusDataType type, final NexusUnitCategory unitCategory) throws Exception {
		validateDatasetFieldNotNull(fieldName, dataset);
		validateDatasetFieldType(fieldName, dataset, type);
		validateDatasetFieldUnit(fieldName, dataset, unitCategory);
	}
	
	public void validateDatasetField(final String fieldName, IDataset dataset, final NexusUnitCategory unitCategory) throws Exception {
		validateDatasetFieldNotNull(fieldName, dataset);
		validateDatasetFieldUnit(fieldName, dataset, unitCategory);
	}
	
	public void validateDatasetField(final String fieldName, IDataset dataset, final NexusDataType type, final NexusUnitCategory unitCategory, int rank) throws Exception {
		validateDatasetField(fieldName, dataset, type, unitCategory);
		validateRank(fieldName, dataset, rank);
	}

	private void validateRank(final String fieldName, IDataset dataset, int rank)
			throws NexusValidationException {
		if (dataset.getRank() != rank) {
			throw new NexusValidationException("The field " + fieldName + " has a rank of " + dataset.getRank() + ", expected " + rank); 
		}
	}
	
	private void validateDatasetFieldType(final String fieldName, final IDataset dataset, final NexusDataType type) throws NexusValidationException {
		type.validate(fieldName, dataset);
	}

	private void validateDatasetFieldUnit(final String fieldName, IDataset dataset,
			final NexusUnitCategory unitCategory) throws Exception,
			NexusValidationException {
		List<? extends MetadataType> metadata = dataset.getMetadata(UnitMetadata.class);
		// TODO why does getMetadata return a list? Can I assume I'm only interested in the first element>
		if (metadata == null || metadata.isEmpty() || !metadata.get(0).getClass().equals(UnitMetadata.class)) {
			failValidation("No unit metadata for field '" + fieldName + "', expected " + unitCategory);
		}
		
		if (metadata.size() > 1) {
			failValidation("Multiple unit metadata items found for field '" + fieldName + "'");
		}
		
		Unit<?> unit = ((UnitMetadata) metadata.get(0)).getUnit();
		if (!unitCategory.isCompatible(unit)) {
			failValidation("Unit " + unit + " is not compatible with the unit category " + unitCategory);
		}
	}
	

}
