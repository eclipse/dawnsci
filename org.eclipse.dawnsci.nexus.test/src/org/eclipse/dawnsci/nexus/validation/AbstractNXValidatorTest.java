package org.eclipse.dawnsci.nexus.validation;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.IntegerDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.StringDataset;
import org.eclipse.dawnsci.analysis.tree.impl.AttributeImpl;
import org.eclipse.dawnsci.analysis.tree.impl.GroupNodeImpl;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXtransformations;
import org.eclipse.dawnsci.nexus.impl.NXobjectFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractNXValidatorTest {
	
	/**
	 * The validator to test. We're testing the method defined in the abstract superclass
	 * of the validators created for each NeXus base class.
	 */
	private AbstractNXValidator validator;
	
	@Before
	public void setUp() {
		validator = new AbstractNXValidator() {
		};
	}
	
	@After
	public void tearDown() {
		validator = null;
	}
	
	@Test
	public void testFailValidation() throws Exception {
		final String message = "message";
		try {
			validator.failValidation(message);
			fail(); // exception expected to be thrown
		} catch (NexusValidationException e) {
			Assert.assertEquals(message, e.getMessage());
		}
	}
	
	@Test
	public void testValidateNotNull_notNull() throws Exception {
		validator.validateNotNull("", new Object());
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateNotNull_null() throws Exception {
		validator.validateNotNull("", null);
	}
	
	@Test
	public void testValidateGroupNotNull_nonNullGroup() throws Exception {
		final GroupNode group = new GroupNodeImpl(1);
		validator.validateGroupNotNull("groupName", NXsample.class, group);
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateGroupNotNull_nullGroup() throws Exception {
		validator.validateGroupNotNull("groupName", NXsample.class, null);
	}
	
	@Test
	public void testValidateFieldNotNull_nonNullField() throws Exception {
		IDataset dataset = new IntegerDataset();
		validator.validateFieldNotNull("fieldName", dataset);
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateFieldNotNull_nullField() throws Exception {
		validator.validateFieldNotNull("fieldName", null);
	}
	
	@Test
	public void testValidateAttributeNotNull_notNull() throws Exception {
		Attribute attribute = new AttributeImpl("attributeName");
		validator.validateAttributeNotNull("attributeName", attribute);
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateAttributeNotNull_null() throws Exception {
		validator.validateAttributeNotNull("attributeName", null);
	}
	
	@Test
	public void testValidateFieldEnumeration_ok() throws Exception {
		StringDataset dataset = new StringDataset(1);
		dataset.set("foo", 0);
		validator.validateFieldEnumeration("enumField", dataset, "foo", "bar");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateFieldEnumeration_illegalRank() throws Exception {
		StringDataset dataset = new StringDataset(3, 3);
		dataset.set("foo", 0);
		validator.validateFieldEnumeration("enumField", dataset, "foo", "bar");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateFieldEnumeration_illegalSize() throws Exception {
		StringDataset dataset = new StringDataset(2);
		dataset.set("foo", 0);
		validator.validateFieldEnumeration("enumField", dataset, "foo", "bar");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateFieldEnumeration_illegalValue() throws Exception {
		StringDataset dataset = new StringDataset(2);
		dataset.set("banana", 0);
		validator.validateFieldEnumeration("enumField", dataset, "foo", "bar");
	}
	
	@Test
	public void testValidateFieldType_ok() throws Exception {
		IDataset dataset = new DoubleDataset(10);
		validator.validateFieldType("doubleField", dataset, NexusDataType.NX_FLOAT);
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateFieldType_incompatibleType() throws Exception {
		IDataset dataset = new DoubleDataset(10);
		validator.validateFieldType("doubleField", dataset, NexusDataType.NX_CHAR);
	}
	
	@Test
	public void testValidateFieldUnits_ok() throws Exception {
//		DoubleDataset dataset = new DoubleDataset();
		// TODO: how to create unit metadata
//		validator.validateFieldUnits("unitsField", dataset, NexusUnitCategory.NX_CURRENT);

		// TODO
		Assert.fail("Test not yet written.");
	}
	
	@Test
	public void testValidateFieldUnits_incompatibleUnits() throws Exception {
		// TODO
		Assert.fail("Test not yet written.");
	}
	
	@Test
	public void testValidateRank_ok() throws Exception {
		IDataset dataset = new DoubleDataset(10, 20, 30);
		validator.validateFieldRank("rankField", dataset, 3); 
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateRank_incorrectRank() throws Exception {
		IDataset dataset = new DoubleDataset(10, 20);
		validator.validateFieldRank("rankField", dataset, 3); 
	}
	
	@Test
	public void testValidateDimensions_integers_ok() throws Exception {
		IDataset dataset = new DoubleDataset(10, 20, 30);
		validator.validateFieldDimensions("rankField", dataset, null, 10, 20, 30);
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateDimensions_integers_incorrect() throws Exception {
		IDataset dataset = new DoubleDataset(10, 20, 30);
		validator.validateFieldDimensions("rankField", dataset, null, 10, 20, 40);
	}
	
	@Test
	public void testValidateDimensionsPlaceholders_oneDataset_ok() throws Exception {
		int size = 10;
		
		IDataset dataset = new DoubleDataset(size, size);
		validator.validateFieldDimensions("field", dataset, null, "size", "size");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateDimensionsPlaceholders_oneDataset_yDimensionWrongSize() throws Exception {
		int size = 10;
		
		IDataset dataset = new DoubleDataset(size, size + 1);
		validator.validateFieldDimensions("field", dataset, null, "size", "size");
	}
	
	@Test
	public void testValidateDimensions_multipleDatasets_ok() throws Exception {
		int xDim = 10;
		int yDim = 20;
		IDataset dataset1 = new DoubleDataset(xDim, yDim);
		validator.validateFieldDimensions("field1", dataset1, null, "xDim", "yDim"); 
		IDataset dataset2 = new DoubleDataset(xDim, yDim);
		validator.validateFieldDimensions("field2", dataset2, null, "xDim", "yDim"); 
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateDimensions_multipleDatasets_incorrect() throws Exception {
		int xDim = 10;
		int yDim = 20;
		IDataset dataset1 = new DoubleDataset(xDim, yDim);
		validator.validateFieldDimensions("field1", dataset1, null, "xDim", "yDim"); 
		IDataset dataset2 = new DoubleDataset(xDim, yDim + 1);
		validator.validateFieldDimensions("field2", dataset2, null, "xDim", "yDim"); 
	}
	
	@Test
	public void testValidateDimensions_localGroup() throws Exception {
		int xDim = 10;
		int yDim = 20;
		IDataset dataset1 = new DoubleDataset(xDim, yDim);
		validator.validateFieldDimensions("field1", dataset1, "group1", "xDim", "yDim"); 
		IDataset dataset2 = new DoubleDataset(xDim, yDim);
		validator.validateFieldDimensions("field2", dataset2, "group1", "xDim", "yDim");
		
		// clear the group placeholders
		validator.clearLocalGroupDimensionPlaceholderValues();
		IDataset newGroupDataset = new DoubleDataset(3, 4);
		validator.validateFieldDimensions("newGroupField", newGroupDataset, "group2", "xDim", "yDim");
	}
	
	@Test
	public void testValidateTransformations() throws Exception {
		final Map<String, NXtransformations> transformations = new HashMap<>();
		
		NXobjectFactory nxObjectFactory = new NXobjectFactory();
		transformations.put("one", nxObjectFactory.createNXtransformations());
		transformations.put("two", nxObjectFactory.createNXtransformations());
		transformations.put("three", nxObjectFactory.createNXtransformations());
		
		transformations.get("one").addAttribute(new AttributeImpl("depends_on", "two"));
		transformations.get("two").addAttribute(new AttributeImpl("depends_on", "three"));
		transformations.get("three").addAttribute(new AttributeImpl("depends_on", "."));
		
		validator.validateTransformations(transformations, "one");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateTransformations_missingFirstTransformation() throws Exception {
		final Map<String, NXtransformations> transformations = new HashMap<>();
		
		NXobjectFactory nxObjectFactory = new NXobjectFactory();
		transformations.put("one", nxObjectFactory.createNXtransformations());
		transformations.put("two", nxObjectFactory.createNXtransformations());
		transformations.put("three", nxObjectFactory.createNXtransformations());
		
		transformations.get("one").addAttribute(new AttributeImpl("depends_on", "two"));
		transformations.get("two").addAttribute(new AttributeImpl("depends_on", "three"));
		transformations.get("three").addAttribute(new AttributeImpl("depends_on", "."));
		
		validator.validateTransformations(transformations, "four");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateTransformations_missingTransformation() throws Exception {
		final Map<String, NXtransformations> transformations = new HashMap<>();
		
		NXobjectFactory nxObjectFactory = new NXobjectFactory();
		transformations.put("one", nxObjectFactory.createNXtransformations());
		transformations.put("two", nxObjectFactory.createNXtransformations());
		transformations.put("three", nxObjectFactory.createNXtransformations());
		
		transformations.get("one").addAttribute(new AttributeImpl("depends_on", "two"));
		transformations.get("two").addAttribute(new AttributeImpl("depends_on", "three"));
		transformations.get("three").addAttribute(new AttributeImpl("depends_on", "four"));
		
		validator.validateTransformations(transformations, "one");
	}
	
	@Test(expected = NexusValidationException.class)
	public void testValidateTransformations_circularDependency() throws Exception {
		final Map<String, NXtransformations> transformations = new HashMap<>();
		
		NXobjectFactory nxObjectFactory = new NXobjectFactory();
		transformations.put("one", nxObjectFactory.createNXtransformations());
		transformations.put("two", nxObjectFactory.createNXtransformations());
		transformations.put("three", nxObjectFactory.createNXtransformations());
		
		transformations.get("one").addAttribute(new AttributeImpl("depends_on", "two"));
		transformations.get("two").addAttribute(new AttributeImpl("depends_on", "three"));
		transformations.get("three").addAttribute(new AttributeImpl("depends_on", "one"));
		
		validator.validateTransformations(transformations, "one");
	}

}
