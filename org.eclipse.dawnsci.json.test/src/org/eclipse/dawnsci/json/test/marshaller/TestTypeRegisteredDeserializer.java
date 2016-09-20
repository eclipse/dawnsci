/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Colin Palmer - initial API and implementation and/or initial documentation
 *******************************************************************************/
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

		//TypeReference<LinkedHashMap<String, Object>> linkedHashMap = new TypeReference<LinkedHashMap<String, Object>>() {};
//		TestBean bean = parser.readValueAs(TestBean.class);
//		return bean.toPosition();
		return new TestTypeRegisteredImpl("Registered. Deserialized.");
	}

	@Override
	public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer)
			throws IOException, JsonProcessingException {

		return deserialize(parser, context);
	}
}
