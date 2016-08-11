/*-
 * Copyright 2015, 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.january.metadata;

import java.util.List;

public class DynamicMetadataUtils {

	public static int[] refreshDynamicAxesMetadata(List<AxesMetadata> axm, int[] shape){
		
		int[] maxShape = shape.clone();
		if (axm == null) return maxShape;
		
		for (AxesMetadata a : axm) {
			AxesMetadata ai = a;
			int[] s = ai.refresh(shape);
			for (int i = 0; i < s.length; i++) {
				if (maxShape[i] > s[i]) maxShape[i] = s[i];
 			}
		}
		
		return maxShape;	
	}
	
}
