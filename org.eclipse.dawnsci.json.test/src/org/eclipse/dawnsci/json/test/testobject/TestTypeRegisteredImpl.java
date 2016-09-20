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

public class TestTypeRegisteredImpl implements ITestTypeRegistered {

	static {
		ClassRegistry.register("jsontest.testtyperegistered", TestTypeRegisteredImpl.class);
	}

	String str;

	public TestTypeRegisteredImpl() {
		this.str = "";
	}

	public TestTypeRegisteredImpl(String something) {
		this.str = something;
	}

	@Override
	public void setString(String str) {
		this.str = str;
	}

	@Override
	public String getString() {
		return this.str;
	}

}
