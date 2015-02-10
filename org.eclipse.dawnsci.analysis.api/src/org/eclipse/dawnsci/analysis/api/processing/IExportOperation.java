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

package org.eclipse.dawnsci.analysis.api.processing;

/**
 * Marker interface for the Operation processing system.
 * 
 * Used to flag a special set of operations, those that do not process data, only store the data they receive.
 * 
 * Allows operations that write out files to be recognised and not run when not appropriate (i.e. dynamic viewing of results in UI)
 */
public interface IExportOperation {

}
