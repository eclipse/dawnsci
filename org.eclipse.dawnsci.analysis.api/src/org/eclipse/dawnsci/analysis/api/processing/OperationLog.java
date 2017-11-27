/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.processing;

/**
 * Log of what happened during an operation
 */
public class OperationLog {
	private static final String NEWLINE = "\n";
	StringBuilder log = new StringBuilder();

	/**
	 * Format a string like in {@link String#format(String, Object...)} and append it to the log
	 * @param format
	 * @param objs
	 */
	public void append(String format, Object... objs) {
		String s = String.format(format, objs);
		log.append(s);
		log.append(NEWLINE);
	}

	@Override
	public String toString() {
		return log.toString();
	}

	/**
	 * Clear contents of log
	 */
	public void clear() {
		log.setLength(0);
	}
}
