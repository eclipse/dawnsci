package org.eclipse.dawnsci.json.test.testobject;

public class TestTypeNonRegisteredImpl implements ITestTypeNonRegistered {

	String str;

	public TestTypeNonRegisteredImpl() {
		this.str = "";
	}

	public TestTypeNonRegisteredImpl(String something) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((str == null) ? 0 : str.hashCode());
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
		TestTypeNonRegisteredImpl other = (TestTypeNonRegisteredImpl) obj;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		return true;
	}

}
