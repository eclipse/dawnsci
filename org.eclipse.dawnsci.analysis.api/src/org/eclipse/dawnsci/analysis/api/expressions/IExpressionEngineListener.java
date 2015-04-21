/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.expressions;

import java.util.EventListener;

public interface IExpressionEngineListener extends EventListener {
	/**
	 * Fired when calculation is completed.
	 * @param evt
	 */
	public void calculationDone(ExpressionEngineEvent evt);

}
