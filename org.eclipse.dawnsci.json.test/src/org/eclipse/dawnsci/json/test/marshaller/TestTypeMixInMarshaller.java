package org.eclipse.dawnsci.json.test.marshaller;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshaller;
import org.eclipse.dawnsci.json.test.testobject.TestTypeNonRegisteredImpl;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;

public class TestTypeMixInMarshaller implements IMarshaller {

	@Override
	public Class<?> getObjectClass() {
		return null;
	}

	@Override
	public Class<?> getSerializerClass() {
		return null;
	}

	@Override
	public Class<?> getDeserializerClass() {
		return null;
	}

	@Override
	public Class<TestTypeNonRegisteredImpl> getMixinAnnotationType() {
		return TestTypeNonRegisteredImpl.class;
	}

	@Override
	public Class<TestTypeRegisteredImpl> getMixinAnnotationClass() {
		return TestTypeRegisteredImpl.class;
	}

}
