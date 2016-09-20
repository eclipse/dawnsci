/*-
 * Copyright © 2016 Diamond Light Source Ltd.
 *
 * This file is part of GDA.
 *
 * GDA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License version 3 as published by the Free
 * Software Foundation.
 *
 * GDA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with GDA. If not, see <http://www.gnu.org/licenses/>.
 */

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
