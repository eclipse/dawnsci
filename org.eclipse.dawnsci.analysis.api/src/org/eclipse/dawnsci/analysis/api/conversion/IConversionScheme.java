/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.conversion;

public interface IConversionScheme {

	public String getUiLabel();

	public int[] getPreferredRanks();

	public boolean isRankSupported(int rank);

	public boolean isUserVisible();

	public boolean isNexusOnly();

	public boolean isNexusSourceAllowed();

	public String getDescription();

	public Class<? extends IConversion> getConversion();
}
