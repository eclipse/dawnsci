/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.hdf5.nexus;

public class NexusException extends Exception {
	
	private static final long serialVersionUID = -113774429307299466L;

	public NexusException(Throwable t) {
		super(t);
	}

	public NexusException(String message) {
		super(message);
	}

	public NexusException(String message, Throwable t) {
		super(message, t);
	}
}
