/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.january.dataset;

/**
 * Class to run over a pair of contiguous datasets
 */
public class ContiguousPairIterator extends BroadcastIterator {
	private final int aMax; // maximum index in array
	private final int aStep; // step over items
	private final int bMax; // maximum index in array
	private final int bStep;
	private final int oStep;

	public ContiguousPairIterator(Dataset a, Dataset b, Dataset o, boolean createIfNull) {
		super(a, b, o);
		aStep = a.getElementsPerItem();
		aMax = a.getSize() * aStep;
		bStep = b.getElementsPerItem();
		bMax = b.getSize() * bStep;
		if (outputA) {
			oStep = aStep;
		} else if (outputB) {
			oStep = bStep;
		} else if (o != null) {
			oStep = o.getElementsPerItem();
		} else if (createIfNull) {
			oDataset = BroadcastUtils.createDataset(a, b, a.getShapeRef());
			oStep = oDataset.getElementsPerItem();
		} else {
			oStep = 1;
		}
		maxShape = a.getShape();
		reset();
	}

	@Override
	public boolean hasNext() {
		aIndex += aStep;
		bIndex += bStep;

		if (outputA) {
			oIndex = aIndex;
		} else if (outputB) {
			oIndex = bIndex;
		} else {
			oIndex += oStep;
		}

		if (aIndex >= aMax || bIndex >= bMax) {
			return false;
		}
		if (read) {
			if (asDouble) {
				aDouble = aDataset.getElementDoubleAbs(aIndex);
				bDouble = bDataset.getElementDoubleAbs(bIndex);
			} else {
				aLong = aDataset.getElementLongAbs(aIndex);
				bLong = bDataset.getElementLongAbs(bIndex);
			}
		}
		return true;
	}

	@Override
	public int[] getPos() {
		return null;
	}

	@Override
	public void reset() {
		aIndex = -aStep;
		bIndex = -bStep;
		oIndex = -oStep;
		if (read) {
			storeCurrentValues();
		}
	}
}
