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

package org.eclipse.dawnsci.plotting.api.histogram.functions;

/**
 *
 */
public class UserCustomFunction extends AbstractMapFunction {

	double[] mapTable;
	String funcname;
	
	public UserCustomFunction(String funcname, int tableSize) {
		mapTable = new double[tableSize];
		this.funcname = funcname;
	}
	@Override
	public String getMapFunctionName() {
		return funcname;
	}

	public void setValues(double[] values) {
		mapTable = values.clone();
	}
	
	public void setValue(int pos, double value) {
		if (pos >= 0 && pos < mapTable.length)
			mapTable[pos] = value;
	}
	
	@Override
	public double mapFunction(double input) {
		int lowMapEntry = (int)(input * mapTable.length);
		int upMapEntry = (int)Math.ceil(input * mapTable.length);
		
		if (upMapEntry > mapTable.length-1)
			upMapEntry = mapTable.length-1;
		
		double a = input * mapTable.length - lowMapEntry;
		// linear interpolation
		return mapTable[lowMapEntry] * (1.0 - a) +  a * mapTable[upMapEntry];
	}

}
