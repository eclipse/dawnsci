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
package org.eclipse.dawnsci.plotting.api.region;

/**
 * An interface to provide a region from an object which may be containing it.
 * 
 * For instance children of a selection region and/or the selection region itself may be.
 * 
 * @author Matthew Gerring
 *
 */
public interface IRegionContainer {

	public IRegion getRegion();

	public void setRegion(IRegion region);
}
