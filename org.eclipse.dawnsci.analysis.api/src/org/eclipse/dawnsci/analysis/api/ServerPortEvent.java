/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.analysis.api;

import java.util.EventObject;

public class ServerPortEvent extends EventObject {

	private int port;
	private boolean volatilePort;

	public ServerPortEvent(Object arg0, int port, boolean volatilePort) {
		super(arg0);
		this.port = port;
		this.volatilePort = volatilePort;
	}

	public boolean isVolatilePort() {
		return volatilePort;
	}

	public void setVolatilePort(boolean volatilePort) {
		this.volatilePort = volatilePort;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
