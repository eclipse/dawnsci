package org.eclipse.dawnsci.json.test;

import java.util.UUID;


public class TestIdBean {
	
	private String    uniqueId;         // Unique id for each object.
	private boolean   explicitlySetId;
	
	public TestIdBean() {
		uniqueId = UUID.randomUUID().toString(); // Normally overridden
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId        = uniqueId;
		this.explicitlySetId = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((uniqueId == null) ? 0 : uniqueId.hashCode());
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
		TestIdBean other = (TestIdBean) obj;
		if (explicitlySetId) {
			if (uniqueId == null) {
				if (other.uniqueId != null)
					return false;
			} else if (!uniqueId.equals(other.uniqueId))
				return false;
		}
		return true;
	}

	/**
	 * Subclasses must override this method calling super.merge(...)
	 * 
	 * @param with
	 */
	public <T extends TestIdBean> void merge(T with) {
		this.uniqueId = with.getUniqueId();
	}

}
