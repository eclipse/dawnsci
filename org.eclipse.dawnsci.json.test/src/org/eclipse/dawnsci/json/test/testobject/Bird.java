package org.eclipse.dawnsci.json.test.testobject;

public class Bird extends Animal {

	String feathers;

	public String getFeathers() {
		return feathers;
	}
	public void setFeathers(String feathers) {
		this.feathers = feathers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((feathers == null) ? 0 : feathers.hashCode());
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
		Bird other = (Bird) obj;
		if (feathers == null) {
			if (other.feathers != null)
				return false;
		} else if (!feathers.equals(other.feathers))
			return false;
		return true;
	}
}