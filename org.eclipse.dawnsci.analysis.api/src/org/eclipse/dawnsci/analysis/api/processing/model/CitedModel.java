/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api.processing.model;

import org.eclipse.dawnsci.analysis.api.processing.NXCite;

public class CitedModel extends AbstractOperationModel {

	@OperationModelField(editable=false, visible=true) // They can see it not change it in the UI
    private NXCite citation;
    
	public NXCite getCitation() {
		return citation;
	}

	public void setCitation(NXCite citation) {
		this.citation = citation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citation == null) ? 0 : citation.hashCode());
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
		CitedModel other = (CitedModel) obj;
		if (citation == null) {
			if (other.citation != null)
				return false;
		} else if (!citation.equals(other.citation))
			return false;
		return true;
	}

}
