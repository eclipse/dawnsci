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
package org.eclipse.dawnsci.analysis.examples.pipelines;

import org.eclipse.dawnsci.analysis.api.dataset.Slice;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.analysis.api.processing.ExecutionType;
import org.eclipse.dawnsci.analysis.api.processing.IExecutionVisitor;
import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.processing.IOperationContext;
import org.eclipse.dawnsci.analysis.api.processing.IOperationService;
import org.eclipse.dawnsci.analysis.api.processing.OperationData;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.dawnsci.analysis.dataset.impl.Random;
import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;
import org.junit.Test;


/**
 * Examples for running pipelines
 * 
 * @author Matthew Gerring
 *
 */
public class PipelineExamples {

	private static IOperationService service;
	
	public static void setOperationService(IOperationService s) {
		service = s;
	}
	
	/**
	 * Operations are contributed by extension point
	 * @throws Exception 
	 */
	@Test
	public void listOperations() throws Exception {
		
		System.out.println("-------------------------------");
		System.out.println("List of available operations:");
		for(String id : service.getRegisteredOperations()) {
			final IOperation op = service.create(id);
			System.out.println("'"+id+"' \t\t is called: "+op.getName());
		}
		System.out.println("-------------------------------");
	}
	
	/**
	 * In this example we do an azimuthal integration over 24 images in a LazyDataset
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void simplePipelineExample() throws Exception {
				
		// Create a Rich dataset without the extra information and a random array.
		final IOperationContext context = service.createContext();
		context.setData(Random.lazyRand(24, 1000, 1000)); // Simplest possible 
		context.setSlicing("all"); // All 24 images in first dimension.
		
		// We access the model in a generic way because this is example code unable to see
		// the internals of pipelines.
		final IOperation<IOperationModel, OperationData> azi = (IOperation<IOperationModel, OperationData>) service.findFirst("uk.ac.diamond.scisoft.analysis.processing.operations.azimuthalIntegration");
		IOperationModel model = service.getModelClass(azi.getId()).newInstance();
		model.set("region", new SectorROI(500.0, 500.0, 20.0, 300.0,  Math.toRadians(90.0), Math.toRadians(180.0)));
		azi.setModel(model);
		
		// We do an azimuthal integration on each of the 24 images, we do not do anything with the integration
		// The series is executed in one thread, in order.
		context.setSeries(azi);
		service.execute(context);
		
		// We execute using fork/join in Java7
		context.setExecutionType(ExecutionType.PARALLEL);
		service.execute(context);

	}
		
	@Test
	public void simplePipelineExampleUsingMonitor() throws Exception {
		
		// Create a Rich dataset without the extra information and a random array.
		final IOperationContext context = service.createContext();
		context.setData(Random.lazyRand(24, 1000, 1000)); // Simplest possible 
		context.setSlicing("all"); // All 24 images in first dimension.
		
		// We access the model in a generic way because this is example code unable to see
		// the internals of pipelines.
		final IOperation<IOperationModel, OperationData> azi = (IOperation<IOperationModel, OperationData>) service.findFirst("uk.ac.diamond.scisoft.analysis.processing.operations.azimuthalIntegration");
		IOperationModel model = service.getModelClass(azi.getId()).newInstance();
		model.set("region", new SectorROI(500.0, 500.0, 20.0, 300.0,  Math.toRadians(90.0), Math.toRadians(180.0)));
		azi.setModel(model);
		
		// We do not use IProgressMonitor because it would introduce an eclipse
		// dependency on the core mathematics.
		final IMonitor monitor = new IMonitor.Stub() {
			int total = 0;
			@Override
			public void worked(int amount) {
				total = total+amount;
				System.out.println("This amount worked "+total);
			}
		};
		context.setMonitor(monitor);
		
		
		// We do an azimuthal integration on each of the 24 images, we do not do anything with the integration
		// The series is executed in one thread, in order.
		context.setSeries(azi);
		service.execute(context);
		
		// We execute using fork/join in Java7
		context.setExecutionType(ExecutionType.PARALLEL);
		service.execute(context);
	}

	
	@Test
	public void simplePipelineExampleUsingVisitor() throws Exception {
		
		// Create a Rich dataset without the extra information and a random array.
		final IOperationContext context = service.createContext();
		context.setData(Random.lazyRand(24, 1000, 1000)); // Simplest possible 
		context.setSlicing("all"); // All 24 images in first dimension.
		
		// We access the model in a generic way because this is example code unable to see
		// the internals of pipelines.
		final IOperation<IOperationModel, OperationData> azi = (IOperation<IOperationModel, OperationData>) service.findFirst("uk.ac.diamond.scisoft.analysis.processing.operations.azimuthalIntegration");
		IOperationModel model = service.getModelClass(azi.getId()).newInstance();
		model.set("region", new SectorROI(500.0, 500.0, 20.0, 300.0,  Math.toRadians(90.0), Math.toRadians(180.0)));
		azi.setModel(model);
		
		// We do not use IProgressMonitor because it would introduce an eclipse
		// dependency on the core mathematics.
		final IExecutionVisitor visitor = new IExecutionVisitor.Stub() {
			@Override
			public void notify(IOperation<? extends IOperationModel, ? extends OperationData> intermediateData, OperationData data) {
				System.out.println("Did operation "+intermediateData.getName());
			}
		};
		context.setVisitor(visitor);
		
		
		// We do an azimuthal integration on each of the 24 images, we do not do anything with the integration
		// The series is executed in one thread, in order.
		context.setSeries(azi);
		service.execute(context);
	
	}

}
