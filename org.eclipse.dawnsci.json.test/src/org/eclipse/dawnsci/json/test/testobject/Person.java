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

public class Person {

	// TODO @Martin: Need to delete these for all testing classes, as they are unnecessary.
	public static final String BUNDLE_NAME_FOR_TESTING = "uk.ac.diamond.daq.test.example";
	public static final String BUNDLE_VERSION_FOR_TESTING = "1.2.0.test";

	String name;
	Animal pet;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pet == null) ? 0 : pet.hashCode());
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
		return true;
	}
}