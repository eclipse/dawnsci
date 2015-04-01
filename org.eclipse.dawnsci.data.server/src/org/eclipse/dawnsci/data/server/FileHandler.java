/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.data.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class FileHandler extends AbstractHandler {
	
	private String resourceName;

	public FileHandler(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public void handle(String              target, 
			           Request             baseRequest,
			           HttpServletRequest  request, 
			           HttpServletResponse response) throws IOException, ServletException {

		InputStream source = null;
		OutputStream destination = null;
		try {

			byte[] buffer = new byte[4096];
			source        = new BufferedInputStream(getClass().getResourceAsStream(resourceName));
			destination   = new BufferedOutputStream(response.getOutputStream());
			int bytes_read;
			while (true) {
				bytes_read = source.read(buffer);
				if (bytes_read == -1) {
					break;
				}
				destination.write(buffer, 0, bytes_read);
			}

		} finally {
			source.close();
			destination.close();
		}

	}

}
