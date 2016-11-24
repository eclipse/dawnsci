/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test.testobject;

import java.util.Collection;
import java.util.Map;

public class ContainerBean {

	private Collection<Object> objList;
	private Map<String, Object> objMap;
	private Object[] objArr;

	public Collection<Object> getObjList() {
		return objList;
	}

	public void setObjList(Collection<Object> objList) {
		this.objList = objList;
	}

	public Map<String, Object> getObjMap() {
		return objMap;
	}

	public void setObjMap(Map<String, Object> objMap) {
		this.objMap = objMap;
	}

	public Object[] getObjArray() {
		return objArr;
	}

	public void setObjArray(Object[] objArr) {
		this.objArr = objArr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objList == null) ? 0 : objList.hashCode());
		result = prime * result + ((objMap == null) ? 0 : objMap.hashCode());
		result = prime * result + ((objArr == null) ? 0 : objArr.hashCode());
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
		ContainerBean other = (ContainerBean) obj;
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
		return true;
	}
}
