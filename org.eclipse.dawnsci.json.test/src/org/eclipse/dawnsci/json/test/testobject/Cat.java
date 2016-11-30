package org.eclipse.dawnsci.json.test.testobject;

public class Cat extends Animal {

	String whiskers;

	public String getWhiskers() {
		return whiskers;
	}
	public void setWhiskers(String whiskers) {
		this.whiskers = whiskers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((whiskers == null) ? 0 : whiskers.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (whiskers == null) {
			if (other.whiskers != null)
				return false;
		} else if (!whiskers.equals(other.whiskers))
			return false;
		return true;
	}
}