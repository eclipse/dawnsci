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
 * 
 * This region allows control over which parts of the region
 * can be moved. The alternative would be an API which allows one to 
 * can and set handle properties by ID. This currently is more complex
 * than is required so a more high level design is implemented.
 * 
 * If the method count here grows above 6, consider replacing ILockableRegion
 * with a more flexible and abstract API, like getting and setting handle
 * properties via abstract IDs.
 * 
 * @author Matthew Gerring
 *
 */
public interface ILockableRegion extends IRegion {

	/**
	 * This may not be implemented depending on the region.
	 * @return true if it is movable
	 */
	public boolean isCentreMovable();

	/**
	 * This may not be implemented depending on the region.
	 * 
	 * Set if the sector centre should be locked or not.
	 * @param isCentreMovable
	 */
	public void setCentreMovable(boolean isCentreMovable);
	
	/**
	 * This may not be implemented depending on the region.
	 * For instance Ellipse implements this but Sector does not.
	 * @return true if it is movable
	 */
	public boolean isOuterMovable();

	/**
	 * This may not be implemented depending on the region.
	 * For instance Ellipse implements this but Sector does not.
	 * 
	 * Set if the ellipse outer handles should be locked or not.
	 * @param isOuterMovable
	 */
	public void setOuterMovable(boolean isOuterMovable);
}
