/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.json.test.testobject;

public class TestTypeBean {

	private ITestTypeNonRegistered ttNonReg;
	private ITestTypeRegistered ttReg;

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
		TestTypeBean other = (TestTypeBean) obj;
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
