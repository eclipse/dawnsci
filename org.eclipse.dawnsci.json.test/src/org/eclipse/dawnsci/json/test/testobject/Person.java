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
package org.eclipse.dawnsci.json.test.testobject;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Person {

	static {
		PersonClassRegistry.register("jsontest.person", Person.class);
	}

	public static final String BUNDLE_NAME_FOR_TESTING = "uk.ac.diamond.daq.test.example";
	public static final String BUNDLE_VERSION_FOR_TESTING = "1.2.0.test";

	String name;
	Animal pet;
	Collection<Object> objList;
	Map<String, Object> objMap;
	Object[] objArr;
	Object bob;
	ITestTypeNonRegistered ttNonReg;
	ITestTypeRegistered ttReg;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Animal getPet() {
		return pet;
	}
	public void setPet(Animal pet) {
		this.pet = pet;
	}
	public Object getBob() {
		return bob;
	}
	public void setBob(Object bob) {
		this.bob = bob;
	}

	public Collection<Object> getObjList() {
		return objList;
	}

	public void setObjList(Collection<Object> objList) {
		this.objList = objList;
	}
	public void addObj(Object obj) {
		if (objList==null) objList = new LinkedHashSet<Object>(7);
		objList.add(obj);
	}

	public Map<String, Object> getObjMap() {
		return objMap;
	}

	public void setObjMap(Map<String, Object> objMap) {
		this.objMap = objMap;
	}
	public void addItem(String key, Object obj) {
		if (objMap==null) objMap = new HashMap<String, Object>(7);
		objMap.put(key, obj);
	}

	public Object[] getObjArray() {
		return objArr;
	}

	public void setObjArray(Object[] objArr) {
		this.objArr = objArr;
	}
	public void setIndex(Integer index, Object obj) {
		if (objArr==null) objArr = new Object[10];
		objArr[index] = obj;
	}

	public ITestTypeNonRegistered getTTNonReg() {
		return ttNonReg;
	}
	public void setTTNonReg(ITestTypeNonRegistered ttNonReg) {
		this.ttNonReg = ttNonReg;
	}
	public ITestTypeRegistered getTTReg() {
		return ttReg;
	}
	public void setTTReg(ITestTypeRegistered ttReg) {
		this.ttReg = ttReg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pet == null) ? 0 : pet.hashCode());
		result = prime * result + ((objList == null) ? 0 : objList.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pet == null) {
			if (other.pet != null)
				return false;
		} else if (!pet.equals(other.pet))
			return false;
		if (objList == null) {
			if (other.objList != null)
				return false;
		} else if (!objList.equals(other.objList))
			return false;
		return true;
	}
}