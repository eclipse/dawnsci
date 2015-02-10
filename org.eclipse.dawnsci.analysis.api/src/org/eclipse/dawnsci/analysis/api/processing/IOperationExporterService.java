/*-
 * Copyright 2014 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

public interface IOperationExporterService {

	/**
	 * Get an exporter for a given ExecutionType
	 * @param type
	 * @return export or null
	 * @throws Exception
	 */
	public IOperationExporter getExporter(ExecutionType type) throws Exception;
}
