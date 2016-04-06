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

package org.eclipse.dawnsci.json.test;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonUtils {

	/**
	 * Compare two JSON strings for equality, ignoring whitespace between entries and the order of an object's
	 * properties.
	 *
	 * @param expected JSON string for the expected object
	 * @param actual JSON string for the actual object
	 * @throws Exception if there is a problem reading the strings as JSON
	 */
	public static void assertJsonEquals(String expected, String actual) throws Exception {
		ObjectMapper jsonMapper = new ObjectMapper();
		JsonNode actualTree = jsonMapper.readTree(actual);
		JsonNode expectedTree = jsonMapper.readTree(expected);
		assertEquals("JSON trees are different.", expectedTree, actualTree);
	}
}