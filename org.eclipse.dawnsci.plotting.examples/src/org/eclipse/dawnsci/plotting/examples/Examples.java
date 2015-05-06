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
package org.eclipse.dawnsci.plotting.examples;

import org.eclipse.dawnsci.analysis.api.conversion.IConversionService;
import org.eclipse.dawnsci.analysis.api.dataset.IDatasetMathsService;
import org.eclipse.dawnsci.analysis.api.fitting.functions.IDownsampleService;
import org.eclipse.dawnsci.analysis.api.io.ILoaderService;
import org.eclipse.dawnsci.plotting.api.IPlottingService;
import org.eclipse.dawnsci.plotting.api.histogram.IImageService;
import org.eclipse.dawnsci.plotting.api.histogram.IPaletteService;
import org.osgi.service.component.ComponentContext;
/**
 * Class to inject OSGI services to.
 * @author fcp94556
 *
 */
public class Examples {
	
	private IDatasetMathsService datasetMathsService;
	private ILoaderService       loaderService;
	private IPlottingService     plottingService;
	private IPaletteService      paletteService;
	private IImageService        imageService;
	private IConversionService   conversionService;
	private IDownsampleService   downsampleService;
	
	public IDownsampleService getDownsampleService() {
		return downsampleService;
	}
	public void setDownsampleService(IDownsampleService downsampleService) {
		this.downsampleService = downsampleService;
	}
	public IConversionService getConversionService() {
		return conversionService;
	}
	public void setConversionService(IConversionService conversionService) {
		this.conversionService = conversionService;
	}
	public IImageService getImageService() {
		return imageService;
	}
	public void setImageService(IImageService imageService) {
		this.imageService = imageService;
	}
	public IPaletteService getPaletteService() {
		return paletteService;
	}
	public void setPaletteService(IPaletteService paletteService) {
		this.paletteService = paletteService;
	}
	public IDatasetMathsService getDatasetMathsService() {
		return datasetMathsService;
	}
	public void setDatasetMathsService(IDatasetMathsService datasetMathsService) {
		this.datasetMathsService = datasetMathsService;
	}
	public ILoaderService getLoaderService() {
		return loaderService;
	}
	public void setLoaderService(ILoaderService loaderService) {
		this.loaderService = loaderService;
	}
	public IPlottingService getPlottingService() {
		return plottingService;
	}
	public void setPlottingService(IPlottingService plottingService) {
		this.plottingService = plottingService;
	}
	
	private static Examples current;

	public void start(ComponentContext context) {
		current = this;
	}
	
	public void stop() {
		current = null;
	}

	public static Examples getCurrent() {
		return current;
	}
}
