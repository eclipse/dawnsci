package org.eclipse.dawnsci.json.test.marshaller;

import java.io.IOException;

import org.eclipse.dawnsci.json.test.testobject.ITestTypeNonRegistered;
import org.eclipse.dawnsci.json.test.testobject.TestTypeNonRegisteredImpl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

public class TestTypeNonRegisteredDeserializer extends JsonDeserializer<ITestTypeNonRegistered> {

	@Override
	public ITestTypeNonRegistered deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {

		return new TestTypeNonRegisteredImpl("TestTypeNonRegisteredDeserializer used.");
	}

	@Override
	public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer)
			throws IOException, JsonProcessingException {

		return deserialize(parser, context);
	}
}
