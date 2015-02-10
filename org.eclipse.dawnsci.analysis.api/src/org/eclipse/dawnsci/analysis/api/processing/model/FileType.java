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

package org.eclipse.dawnsci.analysis.api.processing.model;

/**
 * An enum used to mark options on a OperationModelField as being 
 * editable using 
 */
public enum FileType {

	NONE(false, true), 
	EXISTING_FILE(false, false), 
	EXISTING_FOLDER(true, false), 
	NEW_FILE(false, true), 
	NEW_FOLDER(true, true);
	
	private final boolean isDirectory;
	private final boolean isNewFile;

	FileType(boolean isDirectory, boolean isNewFile) {
		this.isDirectory = isDirectory;
		this.isNewFile   = isNewFile;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public boolean isNewFile() {
		return isNewFile;
	}
}
