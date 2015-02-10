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

import java.util.Collection;
import java.util.HashSet;

public class ServerProvider {


	
	private Collection<ServerPortListener> portListeners;
	
	public void addPortListener(ServerPortListener l) {
		if (portListeners==null) portListeners = new HashSet<ServerPortListener>();
		portListeners.add(l);
	}
	public void removePortListener(ServerPortListener l) {
		if (portListeners==null) return;
		portListeners.remove(l);
	}
	protected void firePortListeners(int port, boolean volatilePort) {
		if (portListeners==null) return;
		final ServerPortEvent evt = new ServerPortEvent(this, port, volatilePort);
		for (ServerPortListener l : portListeners) {
			l.portAssigned(evt);
		}
	}

}
