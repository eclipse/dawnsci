/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.remotedataset.test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.persistence.IMarshallerService;
import org.eclipse.dawnsci.remotedataset.XMLMarshallerService;
import org.junit.Before;
import org.junit.Test;

public class XMLMarshallerTest {

	private IMarshallerService marshaller;

	@Before
	public void setUp() throws Exception {

		marshaller = new XMLMarshallerService();
	}


	@Test
	public void testTreeSerializationSimple() throws Exception {

		Map<String, Object> map = new HashMap<>(1);
		map.put("/", new HashMap<>(1));
		Map<String, Object> pam = testMap(map);
      	assertEquals(map, pam);
	}

	@Test
	public void testTreeSerializationSimpleNonHashMap() throws Exception {

		Map<String, Object> map = new HashMap<>(1);
		map.put("/", new Hashtable<>(1));
		Map<String, Object> pam = testMap(map);
      	assertEquals(map, pam);
	}


	/**
	 * We cannot do this one yet, we still test it but we expect the
	 * maps not to be equal.
	 * @throws Exception
	 */
	@Test
	public void testTreeSerializationComplex() throws Exception {

		final ObjectInputStream in = new ObjectInputStream(getClass().getResourceAsStream("treetomap.ser"));
		Map<String, Object> map = (Map<String, Object>)in.readObject();
		in.close();
		Map<String, Object> pam = testMap(map);

        String[] maxes = (String[])((Map)((Map)((Map)map.get("/")).get("entry")).get("mandelbrot_spectrum")).get("axes");
        String[] paxes = (String[])((Map)((Map)((Map)pam.get("/")).get("entry")).get("mandelbrot_spectrum")).get("axes");

        assertEquals(maxes, paxes);
	}


	private Map<String, Object> testMap(Map<String, Object> map) throws Exception {

		assertTrue(!map.isEmpty());

		String xml = marshaller.marshal(map, false);
       	assertTrue(xml.indexOf('\n')<0);

       	return marshaller.unmarshal(xml, Map.class);
	}

}
