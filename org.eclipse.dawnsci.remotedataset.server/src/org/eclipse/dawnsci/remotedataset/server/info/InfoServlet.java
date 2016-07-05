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
package org.eclipse.dawnsci.remotedataset.server.info;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.analysis.api.metadata.DimensionMetadata;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.dataset.impl.DTypeUtils;
import org.eclipse.dawnsci.remotedataset.ServiceHolder;

/**
 * The handler for incoming requests. No work should be done here
 * the request should be delegated down to the backing LoaderFactory instance
 * using the current session.
 * 
 * In this way if someone does a slice which is time consuming, another request coming
 * in will not be blocked while it is done.
 * 
 * Gives info about a given ILazyDataset, specifically:
 * name
 * shape
 * dType
 * elements per item
 * 
 * For example: http://localhost:8080/info/?path=c%3A/Work/results/TomographyDataSet.hdf5
 * Gives:
 * 
dark_data
[37, 1024, 1024]
2
1
  
 * Or: http://localhost:8080/info/?path=c%3A/Work/results/TomographyDataSet.hdf5&dataset=/entry/exchange/data
 * Gives:
data
[720, 1024, 1024]
2
1
 * 
 * @author Matthew Gerring
 *
 */
public class InfoServlet extends HttpServlet {
			
    /**
	 * 
	 */
	private static final long serialVersionUID = 371241872556958998L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doHandle(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doHandle(req, resp);
    }
    
	/**
	 * Remember with servlets each of these is done on a thread from the
	 * pool. Therefore it should filter down giving each session its
	 * own object with which to slice. In this way if a user decides to
	 * do a long running slice, they only block themselves.
	 * 
	 * TODO User should be able to cancel slice...
	 * 
	 */
	private void doHandle(HttpServletRequest  request,
						  HttpServletResponse response)  throws IOException, ServletException {
				
		final String path    = request.getParameter("path");
		final String dataset = request.getParameter("dataset");
		
		try {
			final ILoaderService lservice = ServiceHolder.getLoaderService();
			final IDataHolder holder = lservice.getData(path, true, new IMonitor.Stub());
			final ILazyDataset lz    = dataset!=null && !"".equals(dataset)
					                 ? holder.getLazyDataset(dataset)
					                 : holder.getLazyDataset(0);
					                 
			response.getWriter().println(lz.getName());
			response.getWriter().println(Arrays.toString(lz.getShape()));
			response.getWriter().println(DTypeUtils.getDTypeFromClass(lz.getElementClass()));
			response.getWriter().println(lz.getElementsPerItem()); // Probably 1
			List<DimensionMetadata> dmds = lz.getMetadata(DimensionMetadata.class);
			if (dmds != null && dmds.size() > 0) {
				DimensionMetadata dmd = dmds.get(0);
				response.getWriter().println(Arrays.toString(dmd.getDataMaxDimensions()));
				response.getWriter().println(Arrays.toString(dmd.getDataChunkDimensions()));
			}
		   
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().println("<h1>"+e.getMessage()+"</h1>");
		}
		
		 
	}

 }