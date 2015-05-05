/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.dawb.common.services.conversion;

/**
 * This service is available as an OSGI service similar to IPersistenceService.
 * 
 * An OSGI Service can be used from anywhere in an OSG environment such as vergil
 * or equinox.
 * 
 * Usage something like:
 * <code>
 * IConversionService service = (IConversionService)ServiceManager.getService(IConversionService.class);
 * IConversionContext context = service.open("/dls/path_to_some_hdf5_file.nxs"); // regex allowed
 * context.setDatasetName("/entry1/signal/some_data"); // regex allowed
 * context.setOutputPath("/dls/some_place_I_want_my_data");
 * context.setConversionScheme(IConversionContext.ConversionScheme.ASCII_FROM_2D);
 * service.process(context);
 * </code>
 * 
 * The core service supports looping, slicing and dicing etc.
 * 
 * Two methods for adding your own conversion:
 * 
 * 1. 
 * 1.1 Extend the enum ConversionScheme to have your new conversion.
 * 1.2 Add an implementation of it in org.dawnsci.conversion.internal
 * 1.3 Change method process(...) in ConversionServiceImpl to use this new scheme and class.
 * 
 * 2.
 * 2.1 Create a new class implementing IConversionVisitor
 * 2.2 Set this visitor on the context using IConversionContext.setConversionVisitor(...)
 * 2.3 Call conversion as normal, each slice will be sent for the visitor to do the writing.
 * 
 * 
 * TODO FIXME Move this service to org.eclipse.dawnsci.conversion with few dependencies.
 */
public interface IConversionService {

	/**
	 * Call to open a conversion context on a given path.
	 * The context returned should be configured and then the 'convert'
	 * method called to run a conversion.
	 * 
	 * @param filePathRegEx may be a folder path(s), or a regex(s). 
	 * 
	 * If a regex all matching files in the
	 * directory will be converted, for instance "C:/tmp/(.+).h5". The path separator must always
	 * be "/" even on windows. The regex is done after the last / the path preceeding the last /
	 * is the directory path and does not support regex. If the path ends with a / then all
	 * files in the directory will be processes. Sub-folders will not be traversed.
	 * 
	 * @return context to use as conversion
	 */
	public IConversionContext open(String... filePathRegEx);
	
	/**
	 * Call to run the conversion. Process all files matching the filePathRegEx
	 * @param context
	 * @throws Exception if problem processing the conversion.
	 */
	public void process(IConversionContext context) throws Exception;
	
}
