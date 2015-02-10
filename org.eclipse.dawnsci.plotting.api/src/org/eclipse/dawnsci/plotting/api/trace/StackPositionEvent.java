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
package org.eclipse.dawnsci.plotting.api.trace;

import java.util.EventObject;

/**
 * Event fired to notify of the stack position changing.
 * @author Matthew Gerring
 *
 */
public class StackPositionEvent extends EventObject {

	private int position;

	public StackPositionEvent(Object arg0, int position) {
		super(arg0);
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8818838204678926655L;

}
