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

package org.eclipse.dawnsci.plotting.api.jreality.tool;

public interface IDataPositionEvent {

	/**
	 * Describes the various states a data position event can be in:
	 * <li/> START - position event has been initiated
	 * <li/> DRAG - position is changing
	 * <li/> END - position event is complete
	 */
	public enum Mode {START, DRAG, END}
	
	/**
	 * Get the current mode
	 * @return current mode
	 */
	public abstract Mode getMode();

	/**
	 * Get the position in texture coordinates
	 * @return texture coordinates
	 */
	public abstract double[] getPosition();

}
