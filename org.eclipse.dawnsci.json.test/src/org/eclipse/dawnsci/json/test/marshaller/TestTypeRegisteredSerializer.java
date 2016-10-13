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

import org.eclipse.dawnsci.json.test.testobject.ITestTypeRegistered;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class TestTypeRegisteredSerializer extends JsonSerializer<ITestTypeRegistered> {

	@Override
	public void serialize(ITestTypeRegistered pos, JsonGenerator gen, SerializerProvider prov) throws IOException, JsonProcessingException {

		try {
			gen.writeString("TestTypeRegisteredSerializer used.");
		} catch (Throwable ne) {
			ne.printStackTrace();
			throw ne;
		}
	}

	@Override
	public void serializeWithType(ITestTypeRegistered pos, JsonGenerator gen, SerializerProvider prov, TypeSerializer typeSer)
			throws IOException, JsonProcessingException {
		serialize(pos, gen, prov);
	}
}
