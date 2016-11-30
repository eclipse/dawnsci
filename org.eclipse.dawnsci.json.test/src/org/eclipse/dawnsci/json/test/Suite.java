package org.eclipse.dawnsci.json.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({

	JsonMarshallerInbuiltTypesTest.class,
	JsonMarshallerCustomClassesTest.class,
	JsonMarshallerROITypesTest.class,
	JsonMarshallerSerializerTest.class,
	JsonMarshallerClassRegistryTest.class,

})
public class Suite {
}
