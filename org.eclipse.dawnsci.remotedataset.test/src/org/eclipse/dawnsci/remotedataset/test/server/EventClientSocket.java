/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.test.server;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;


public class EventClientSocket extends WebSocketAdapter {

	@Override
	public void onWebSocketText(String message) {
	    System.out.println("Message from server "+message);
    }
	
}