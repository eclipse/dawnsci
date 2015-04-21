/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.expressions;

import java.util.EventObject;

public class ExpressionEngineEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object result;
	private String expression;
	
	public ExpressionEngineEvent(Object source, Object result, String expression) {
		super(source);
		this.result = result;
		this.expression = expression;
	}

	public Object getResult() {
		return result;
	}

	public String getExpression() {
		return expression;
	}
}
