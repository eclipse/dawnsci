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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * The handler for incoming requests. No work should be done here
 * the request should be delegated down to the backing LoaderFactory instance
 * using the current session.
 * 
 * In this way if someone does a slice which is time consuming, another request coming
 * in will not be blocked while it is done.
 * 
 * 
 * @author fcp94556
 *
 */
public class DataHandler extends AbstractHandler {
	
	private static final Object LOCK=new Object();

	/**
	 * Remember with servlets each of these is done on a thread from the
	 * pool. Therefore it should filter down giving each session its
	 * own object with which to slice. In this way if a user decides to
	 * do a long running slice, they only block themselves.
	 * 
	 * TODO User should be able to cancel slice...
	 * 
	 */
	public void handle( String              target,
						Request             baseRequest,
						HttpServletRequest  request,
						HttpServletResponse response)  throws IOException, ServletException {
		
		HttpSession sess = request.getSession();
		
		SliceRequest slicer=null;
		
		// Assignment of slicer is synched
		synchronized(LOCK) {
			slicer = (SliceRequest)sess.getAttribute("slicer");
			if (slicer==null) {
				slicer = new SliceRequest(sess.getId());
				sess.setAttribute("slicer", slicer);
			}
		}
		
		try {
		    slicer.slice(target, baseRequest, request, response);
		    
		} catch (Exception ne) {
			ne.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			baseRequest.setHandled(true);
			response.getWriter().println("<h1>"+ne.getMessage()+"</h1>");
		}
	}

 }