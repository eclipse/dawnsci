package org.eclipse.dawnsci.nexus.validation;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.measure.unit.Unit;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.metadata.MetadataType;
import org.eclipse.dawnsci.analysis.api.metadata.UnitMetadata;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.hdf5.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXtransformations;

/**
 * Abstract superclass for Nexus application definition validators.
 *
 */
public abstract class AbstractNXValidator implements NXApplicationValidator {
	
	private Map<String, Integer> globalDimensionPlaceholderValues = new HashMap<>();
	
	private Map<String, Integer> localGroupDimensionPlaceholderValues = new HashMap<>();
	
	/**
	 * Throw an {@link NexusValidationException} with the given message.
	 * @param message message
	 * @throws NexusValidationException always
	 */
	protected void failValidation(final String message) throws NexusValidationException {
		if (message == null) {
			throw new NexusValidationException(null);
		} else {
			throw new NexusValidationException(message);
		}
	}
	
	/**
	 * Validates that the given condition holds, throwing an {@link NexusValidationException} with the given message otherwise
	 * @param message message
	 * @param condition condition to check
	 * @throws NexusValidationException if the condition does not hold
	 */
	protected void validate(String message, boolean condition) throws NexusValidationException {
		if (!condition) {
			failValidation(message);
		}
	}
	
	/**
	 * Validates that the given object is not <code>null</code>.
	 * @param message message
	 * @param object object to check for <code>null</code>
	 * @throws NexusValidationException if the given object is <code>null</code>
	 */
	protected void validateNotNull(String message, Object object) throws NexusValidationException {
		validate(message, object != null);
	}
	
	/**
	 * Validates that the given group is not null.
	 * @param groupName name of group
	 * @param type type of group
	 * @param groupNode group node
	 * @throws NexusValidationException
	 */
	protected void validateGroupNotNull(String groupName, Class<? extends NXobject> type, GroupNode groupNode) throws NexusValidationException {
		validateNotNull((groupName == null ? "The unnamed group " : "The group '" + groupName + "' ") + "of type " + type.getSimpleName() + " must not be null", groupNode);
	}
	
	/**
	 * Validates that the given field value is not <code>null</code>.
	 * @param fieldName name of field
	 * @param dataset the field value, an {@link IDataset}
	 * @throws NexusValidationException if the field is <code>null</code>
	 */
	protected void validateFieldNotNull(String fieldName, IDataset dataset) throws NexusValidationException {
		validateNotNull("The field " + fieldName + " must be set", dataset);
	}
	
	/**
	 * Validates that the given attribute node is not <code>null</code>.
	 * @param attributeName name of attribute
	 * @param attribute attribute 
	 * @throws NexusValidationException if the attribute is <code>null</code>
	 */
	protected void validateAttributeNotNull(String attributeName, Attribute attribute) throws NexusValidationException {
		validateNotNull("The attribute " + attributeName + " must be set", attribute);
		validateNotNull("The dataset for the attribute " + attributeName + " must be set", attribute);
	}
	
	/**
	 * Validates that an enumeration field has one of the given permitted values.
	 * @param fieldName name of the field
	 * @param dataset the field value, a {@link Dataset}
	 * @param permittedValues the permitted values
	 * @throws NexusValidationException if the value of the field is not one of the permitted values
	 */
	protected void validateFieldEnumeration(String fieldName, IDataset dataset, String... permittedValues) throws NexusValidationException {
		// note: this method assumes that the enumeration values are always strings
		if (dataset.getRank() != 1) { // TODO confirm rank for enums: 0 or 1?
			failValidation("The enumeration field " + fieldName + " must have a rank of 1");
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
	
	/**
	 * Validates that the type of the given field is that given.
	 * @param fieldName field name
	 * @param dataset field value, an {@link IDataset}
	 * @param type expected type
	 * @throws NexusValidationException if the type of the field is not that given
	 */
	protected void validateFieldType(final String fieldName, final IDataset dataset, final NexusDataType type) throws NexusValidationException {
		type.validate(fieldName, dataset);
	}

	/**
	 * Validates that the given field has units consistent with the given unit category.
	 * 
	 * @param fieldName field name
	 * @param dataset field value, an {@link IDataset}
	 * @param unitCategory expected unit category
	 * @throws Exception if an unexpected exception occurs
	 * @throws NexusValidationException if the field's units are not consistent with the given unit category
	 */
	protected void validateFieldUnits(final String fieldName, final IDataset dataset,
			final NexusUnitCategory unitCategory) throws NexusValidationException {
		List<? extends MetadataType> metadata;
		try {
			metadata = dataset.getMetadata(UnitMetadata.class);
		} catch (Exception e) {
			throw new NexusValidationException("Could not get unit metadata for field '" + fieldName + "'", e);
		}
		// TODO why does getMetadata return a list? Can I assume I'm only interested in the first element?
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

	/**
	 * Validates that the given field has the expected rank.
	 * @param fieldName field name
	 * @param dataset field value, an {@link IDataset}
	 * @param rank expected rank
	 * @throws NexusValidationException if the field does not have the expected rank
	 */
	protected void validateFieldRank(final String fieldName, final IDataset dataset, final int rank)
			throws NexusValidationException {
		if (dataset.getRank() != rank) {
			failValidation("The field " + fieldName + " has a rank of " + dataset.getRank() + ", expected " + rank); 
		}
	}
	
	/**
	 * Validate the dimensions of the given field.
	 * @param fieldName field name
	 * @param dataset dataset to validate
	 * @param groupName the name of the group
	 * @param dimensions the dimensions, each value must be either an integer, interpreted as the expected size of
	 *    that dimension, or a placeholder string, in which case the size of this dimension will be validated
	 *    against any previous dimension with the same placeholder string
	 * @throws NexusValidationException if a dimension did not have the expected size
	 */
	protected void validateFieldDimensions(final String fieldName,
			final IDataset dataset, String groupName, Object... dimensions)
			throws NexusValidationException {
		final int[] shape = dataset.getShape();

		for (int i = 0; i < dimensions.length; i++) {
			if (dimensions[i] instanceof Integer) {
				// the dimension value to validate against in an integer specifying exactly the expected dimension size to check
				if (shape[i] != ((Integer) dimensions[i]).intValue()) {
					failValidation(MessageFormat.format("The dimension with index {0} of field ''{1}'' expected to have size {2} was {3}", 
							(i + 1), fieldName, dimensions[i], shape[i]));
				}
			} else if (dimensions[i] instanceof String) {
				// the dimension value to validate against is a string placeholder
				// if the name of the group is specified, then this is defined in the NXDL for the base class for that group type
				// otherwise the placeholder is global across the whole application
				
				// we need to check that all dimensions (across the group or application depending on whether there is a group name)
				// have the same size. To do this, if this is the first time we've seen this placeholder we store the size of the
				// current dimension. On subsequent encounters, we check that the current dimension has the same size as this
				// stored value
				final String dimensionPlaceholder = (String) dimensions[i];
				Integer expectedSize = getDimensionPlaceholderValue(dimensionPlaceholder, groupName != null, shape[i]);
				if (expectedSize != null && shape[i] != expectedSize.intValue()) {
					if (groupName != null) {
						failValidation(MessageFormat.format("The dimension with index {0} of field ''{1}'' expected to have size {2} according to symbol ''{3}'' within group {4}, was {5}",
								(i + 1), fieldName, expectedSize, dimensions[i], groupName, shape[i]));
					} else {
						failValidation(MessageFormat.format("The dimension with index {0} of field ''{1}'' expected to have size {2} according to symbol ''{3}'', was {4}",
								(i + 1), fieldName, expectedSize, dimensions[i], shape[i]));
					}
				}
			} else {
				failValidation("Dimension size value must be an Integer or String, was: " + dimensions[i].getClass().getName());
			}
		}
	}
	
	/**
	 * Validate the given transformations. Transformations have an order, whereby the initial dependsOnStr
	 * identifies the first transformation, and thereafter each transformation is identified by the
	 * value of the <code>depends_on</code> attribute of the previous transformation. The 
	 * final transformation is identified by having <code>"."</code> as the value of its depends_on attribute. 
	 * @param transformations transformations
	 * @param dependsOnStr the name of the first transformation
	 * @throws NexusValidationException if an expected transformation does not exist
	 */
	protected void validateTransformations(final Map<String, NXtransformations> transformations, String dependsOnStr) throws NexusValidationException {
		final Set<String> encounteredTransformationNames = new HashSet<String>();
		do {
			// get the tranformation with the given name
			final NXtransformations transformation = transformations.get(dependsOnStr);
			
			// check that the transformation exists
			if (transformation == null) {
				failValidation("No such transformation: " + dependsOnStr);
			}
			
			// check we haven't already encountered this transformation, if so the
			// transformations have a circular dependency
			if (encounteredTransformationNames.contains(dependsOnStr)) {
				failValidation("Circular dependency detected in transformations, transformation '" + dependsOnStr + "' encountered for second time.");
			}
			encounteredTransformationNames.add(dependsOnStr);
			Attribute dependsOnAttr = transformation.getAttribute("depends_on");
			dependsOnStr = (dependsOnAttr == null ? null : dependsOnAttr.getFirstElement());
		} while (dependsOnStr != null && !dependsOnStr.equals(".")); // "." marks the final transformation
	}
	
	/**
	 * A helper method to get the actual dimension size for the given placeholder string, if it exists.
	 * If this is the first occurrence of this placeholder, 
	 * @param placeholder
	 * @param local
	 * @param actualDimensionSize
	 * @return the dimension placeholder value
	 */
	private Integer getDimensionPlaceholderValue(String placeholder, boolean local, int actualDimensionSize) {
		final Integer dimensionPlaceholderValue;
		if (local) {
			dimensionPlaceholderValue = localGroupDimensionPlaceholderValues.get(placeholder);
			if (dimensionPlaceholderValue == null) {
				localGroupDimensionPlaceholderValues.put(placeholder, actualDimensionSize);
			} else {
				
			}
		} else {
			dimensionPlaceholderValue = globalDimensionPlaceholderValues.get(placeholder);
			if (dimensionPlaceholderValue == null) {
				globalDimensionPlaceholderValues.put(placeholder, actualDimensionSize);
			}
		}
		
		return dimensionPlaceholderValue;
	}
	
	
	/**
	 * Clears the map of values of dimension placeholders, as these are local only to the current group.
	 */
	protected void clearLocalGroupDimensionPlaceholderValues() {
		localGroupDimensionPlaceholderValues = new HashMap<String, Integer>();
	}
	
}
