package org.eclipse.dawnsci.json.test.marshaller;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshaller;
import org.eclipse.dawnsci.json.test.testobject.ITestTypeNonRegistered;

public class TestTypeNonRegisteredMarshaller implements IMarshaller {

	@Override
	public Class<ITestTypeNonRegistered> getObjectClass() {
		return ITestTypeNonRegistered.class;
	}

	@Override
	public Class<TestTypeNonRegisteredSerializer> getSerializerClass() {
		return TestTypeNonRegisteredSerializer.class;
	}

	@Override
	public Class<TestTypeNonRegisteredDeserializer> getDeserializerClass() {
		return TestTypeNonRegisteredDeserializer.class;
	}
}
