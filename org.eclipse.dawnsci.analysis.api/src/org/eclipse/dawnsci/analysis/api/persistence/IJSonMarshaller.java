/*
 * Copyright (c) 2012-2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.persistence;

/**
 * Interface used to marshall from ROIBean/FunctionBean to JSON
 * and unmarshall from JSON to ROIBean/FunctionBean. <br>
 * Use {@link IMarshallerService} for serializing/deserializing ROIs.
 *
 */
public interface IJSonMarshaller {

	/**
	 * Returns a JSON String given an object
	 * @param obj
	 * @return json string
	 * @throws Exception 
	 */
	public String marshal(Object obj) throws Exception;

	/**
	 * Returns an object given a JSON String
	 * @param json
	 * @return Object
	 * @throws Exception
	 */
	public Object unmarshal(String json) throws Exception;
}
