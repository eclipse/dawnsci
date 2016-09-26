package org.eclipse.dawnsci.json.test.marshaller;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshaller;
import org.eclipse.dawnsci.json.test.testobject.ITestTypeRegistered;

public class TestTypeRegisteredMarshaller implements IMarshaller {

	@Override
	public Class<ITestTypeRegistered> getObjectClass() {
		return ITestTypeRegistered.class;
	}

	@Override
	public Class<TestTypeRegisteredSerializer> getSerializerClass() {
		return TestTypeRegisteredSerializer.class;
	}

	@Override
	public Class<TestTypeRegisteredDeserializer> getDeserializerClass() {
		return TestTypeRegisteredDeserializer.class;
	}

}
