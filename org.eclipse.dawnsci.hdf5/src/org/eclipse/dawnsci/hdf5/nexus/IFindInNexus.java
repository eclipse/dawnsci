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
package org.eclipse.dawnsci.hdf5.nexus;


/**
 * This interface describes a single method that returns a boolean when given a
 * Hierarchical object. This allows classes to be created specifically to test
 * the type/name/contents of a Hierarchical object, and return whether the object
 * matches or not. To be used with methods that iterate over Hierarchical files to 
 * locate specific objects.
 */
public interface IFindInNexus
{
    boolean inNexus(String nexusObjectPath);
    
}