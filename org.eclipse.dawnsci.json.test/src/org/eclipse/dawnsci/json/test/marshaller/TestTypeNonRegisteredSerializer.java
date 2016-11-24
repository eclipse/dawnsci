package org.eclipse.dawnsci.json.test.marshaller;

import java.io.IOException;

import org.eclipse.dawnsci.json.test.testobject.ITestTypeNonRegistered;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class TestTypeNonRegisteredSerializer extends JsonSerializer<ITestTypeNonRegistered> {

	@Override
	public void serialize(ITestTypeNonRegistered pos, JsonGenerator gen, SerializerProvider prov) throws IOException, JsonProcessingException {

		try {
			gen.writeString("TestTypeNonRegisteredSerializer used.");
		} catch (Throwable ne) {
			ne.printStackTrace();
			throw ne;
		}
	}

	@Override
	public void serializeWithType(ITestTypeNonRegistered pos, JsonGenerator gen, SerializerProvider prov, TypeSerializer typeSer)
			throws IOException, JsonProcessingException {
		serialize(pos, gen, prov);
	}
}
