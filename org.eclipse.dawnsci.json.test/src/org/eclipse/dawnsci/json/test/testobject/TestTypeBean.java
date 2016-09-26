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
