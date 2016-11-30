package org.eclipse.dawnsci.json.test.testobject;

public class TestTypeRegisteredAlternativeImpl implements ITestTypeRegistered {

	String str;

	public TestTypeRegisteredAlternativeImpl() {
		this.str = "Alternative";
	}

	public TestTypeRegisteredAlternativeImpl(String something) {
		this.str = "Alternative " + something;
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
		TestTypeRegisteredImpl other = (TestTypeRegisteredImpl) obj;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		return true;
	}

}
