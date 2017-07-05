/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.conversion;

import java.io.File;
import java.util.List;

public interface IConversion {

	public void process(IConversionContext context) throws Exception;

	public void close(IConversionContext context) throws Exception;

	public List<String> getData(File path, String datasetName) throws Exception;

	public List<String> getDataNames(File ioFile) throws Exception;

	public List<File> expand(String path);
}
