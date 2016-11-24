package org.eclipse.dawnsci.json.test.marshaller;

import java.io.IOException;

import org.eclipse.dawnsci.json.test.testobject.ITestTypeRegistered;
import org.eclipse.dawnsci.json.test.testobject.TestTypeRegisteredImpl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public class TestTypeRegisteredDeserializer extends JsonDeserializer<ITestTypeRegistered> {

	@Override
	public ITestTypeRegistered deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {

		return new TestTypeRegisteredImpl("TestTypeRegisteredDeserializer used.");
	}

	@Override
	public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer)
			throws IOException, JsonProcessingException {

		return deserialize(parser, context);
	}
}
