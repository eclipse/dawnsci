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
package org.eclipse.dawnsci.macro.api;

import java.util.EventListener;

public interface IMacroEventListener extends EventListener {

	/**
	 * Called when a macro event happened which could be recorded
	 * in the console or macro editor.
	 * 
	 * @param mevt
	 */
	void macroChangePerformed(MacroEventObject mevt);
	
	/**
	 * 
	 * @return true if the listener isDisposed.
	 * In theory listeners should remove themselves when they are disposed but
	 * just in case we force listeners to have a method to do this.
	 */
	boolean isDisposed();
}
