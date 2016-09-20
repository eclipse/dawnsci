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

package org.eclipse.dawnsci.json.test.testobject;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class TestTypeContainerBean {

	static {
		ClassRegistry.register("jsontest.testtypecontainerbean", TestTypeContainerBean.class);
	}

	private Collection<Object> objList;
	private Map<String, Object> objMap;
	private Object[] objArr;
	private Object objAttr;
	private ITestTypeNonRegistered ttNonReg;
	private ITestTypeRegistered ttReg;

	public Object getObj() {
		return objAttr;
	}
	public void setObj(Object bob) {
		this.objAttr = bob;
	}

	public Collection<Object> getObjList() {
		return objList;
	}

	public void setObjList(Collection<Object> objList) {
		this.objList = objList;
	}
	public void addToObjList(Object obj) {
		if (objList==null) objList = new LinkedHashSet<Object>(7);
		objList.add(obj);
	}

	public Map<String, Object> getObjMap() {
		return objMap;
	}

	public void setObjMap(Map<String, Object> objMap) {
		this.objMap = objMap;
	}
	public void addObjMapItem(String key, Object obj) {
		if (objMap==null) objMap = new HashMap<String, Object>(7);
		objMap.put(key, obj);
	}

	public Object[] getObjArray() {
		return objArr;
	}

	public void setObjArray(Object[] objArr) {
		this.objArr = objArr;
	}
	public void setObjArrayAtIndex(Integer index, Object obj) {
		// As this is just for tests, I don't care about wrong indices!
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
		result = prime * result + ((objList == null) ? 0 : objList.hashCode());
		result = prime * result + ((objMap == null) ? 0 : objMap.hashCode());
		result = prime * result + ((objArr == null) ? 0 : objArr.hashCode());
		result = prime * result + ((objAttr == null) ? 0 : objAttr.hashCode());
		result = prime * result + ((ttNonReg == null) ? 0 : ttNonReg.hashCode());
		result = prime * result + ((ttReg == null) ? 0 : ttReg.hashCode());
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
		TestTypeContainerBean other = (TestTypeContainerBean) obj;
		if (objList == null) {
			if (other.objList != null)
				return false;
		} else if (!objList.equals(other.objList))
			return false;
		if (objMap == null) {
			if (other.objMap != null)
				return false;
		} else if (!objMap.equals(other.objMap))
			return false;
		if (objArr == null) {
			if (other.objArr != null)
				return false;
		} else if (!objArr.equals(other.objArr))
			return false;
		if (objAttr == null) {
			if (other.objAttr != null)
				return false;
		} else if (!objAttr.equals(other.objAttr))
			return false;
		if (ttNonReg == null) {
			if (other.ttNonReg != null)
				return false;
		} else if (!ttNonReg.equals(other.ttNonReg))
			return false;
		if (ttReg == null) {
			if (other.ttReg != null)
				return false;
		} else if (!ttReg.equals(other.ttReg))
			return false;
		return true;
	}
}
