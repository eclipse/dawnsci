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

package org.eclipse.dawnsci.json.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.json.MarshallerService;
import org.eclipse.dawnsci.json.test.classregistry.TestObjectClassRegistry;
import org.junit.Before;
import org.junit.Test;

public class XMLMarshallerTest {

	private IMarshallerService marshaller;

	@Before
	public void setUp() throws Exception {

		marshaller = new MarshallerService(new TestObjectClassRegistry());
	}


	@Test
	public void testTreeSerializationSimple() throws Exception {

		Map<String, Object> map = new HashMap<>(1);
		map.put("/", new HashMap<>(1));
		testMap(map);
	}

	@Test
	public void testTreeSerializationSimpleNonHashMap() throws Exception {

		Map<String, Object> map = new HashMap<>(1);
		map.put("/", new Hashtable<>(1));
		testMap(map);
	}


	/**
	 * We cannot do this one yet, we still test it but we expect the
	 * maps not to be equal.
	 * @throws Exception
	 */
	@Test(expected=AssertionError.class)
	public void testTreeSerializationComplex() throws Exception {

		final ObjectInputStream in = new ObjectInputStream(getClass().getResourceAsStream("treetomap.ser"));
		Map<String, Object> map = (Map<String, Object>)in.readObject();
		in.close();
		testMap(map);
	}


	private Map<String, Object> testMap(Map<String, Object> map) throws Exception {

		assertTrue(!map.isEmpty());

		String xml = marshaller.marshal(map, false);
       	Map<String, Object> pam = marshaller.unmarshal(xml, Map.class);

       	assertEquals(map, pam);
       	return pam;
	}

}
