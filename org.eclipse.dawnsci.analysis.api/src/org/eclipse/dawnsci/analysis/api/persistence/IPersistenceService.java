/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.persistence;


/**
 * This service can be called to save and/or load data from an hdf5 file.
 * 
 * @author wqk87977
 *
 */
public interface IPersistenceService {

	/**
	 * Method to read / load an IPersistentFile
	 *  
	 * @param filePath
	 * @return IPersistentFile
	 */
	public IPersistentFile getPersistentFile(String filePath) throws Exception;

	/**
	 * Method to save an IPersistentFile
	 * 
	 * @param filePath
	 * @return IPersistentFile
	 */
	public IPersistentFile createPersistentFile(String filePath) throws Exception;

	/**
	 * 
	 * @param file - currently must be the IHierarchicalDataFile or a class cast exception is thrown 
	 * @return file
	 * @throws Exception
	 */
	public IPersistentFile createPersistentFile(Object file) throws Exception;
	
	/**
	 * Returns an object bean given a JSon String
	 * @param json
	 * @return obj
	 */
	public Object unmarshal(String json) throws Exception;

	/**
	 * Returns an object of class clazz given a JSon String and the objects class
	 * @param json
	 * @param clazz
	 * @return T
	 */
	public <T extends Object> T unmarshal(String json, Class<T> clazz) throws Exception;
	
	/**
	 * Returns a JSON string given an Object bean
	 * @param obj
	 * @return string
	 */
	public String marshal(Object obj) throws Exception;

}
