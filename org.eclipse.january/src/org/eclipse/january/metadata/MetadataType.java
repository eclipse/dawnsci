/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.january.metadata;

import java.io.Serializable;

/**
 * This is a marker interface for all metadata items which can be associated with a dataset.
 * <p>
 * All sub-interfaces must have an initialize method and all implementations must have a null constructor.
 * 
 */
public interface MetadataType extends Serializable, Cloneable {

	/**
	 * Make a deep copy of metadata
	 * @return clone
	 */
	public MetadataType clone();
}
