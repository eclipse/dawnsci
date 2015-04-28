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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClientProvider {
	private static final Logger logger = LoggerFactory.getLogger(AbstractClientProvider.class);

	private int port = 0;

	protected AbstractClientProvider() {
	}

	/**
	 * Provide a port number to use. Allows overriding the default, particularly useful if multiple instances of SDA are
	 * controlled from the same Jython/Java JVM.
	 * 
	 * @param portNumber
	 *            new port number, or 0 to use default port resolution mechanism
	 * @throws IllegalArgumentException
	 *             if the port number is < 0
	 */
	public void setPort(int portNumber) throws IllegalArgumentException {
		if (portNumber < 0)
			throw new IllegalArgumentException("Port number must be >= 0");

		port = portNumber;
	}

	/**
	 * Return Port number in use
	 * 
	 * @return port number
	 * @throws IllegalStateException
	 *             if the remote port cannot be determined
	 */
	public int getPort() throws IllegalStateException {
		if (port == 0) {
			String portString = System.getenv(getEnvName());
			if (portString != null) {
				try {
					port = Integer.parseInt(portString);
				} catch (NumberFormatException e) {
					port = 0;
				}
			}
			// It isn't going to work with a port of 0, so throw the error now
			if (port == 0) {
				// Check if this is GDA if the gda.mode property is present
				boolean isGda = System.getProperty("gda.mode") != null ? true : false;
				// If not GDA show the error, for GDA it will be suppressed
				if (!isGda) {
					String msg = "Failed to determine suitable port from " + getEnvName() + ", value was '"
							+ portString + "'";
					logger.error(msg);
					throw new IllegalStateException(msg);
				}
			}
		}

		return port;
	}

	/**
	 * Return name of environment variable to initialise port from.
	 * 
	 * @return env variable name
	 */
	protected abstract String getEnvName();
}
