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

/**

The doe package in concerned with Design of Experiment logic used to create ranges of experiments.

It is designed to later be extended to deal with creating true DOE studies over parameters identified by the user
which should be varied in the experiment.

It currently deals with RANGE (start, stop, increment) and LIST (comma separated values).

Here is what to do to start using ranges:
-----------------------------------------

1. Change a widget to be a range widget (extending IRangeWidget). For instance simply swap ScaleBox for RangeBox in your UI.

2. Change the field in the corresponding bean to be a string, ranges are defined as string. For instance change 'Double temperature' to 'String temperature'
   and mark this field with the @DOEField annotation. For instance:
   <code>
   @DOEField(DOEField.DEFAULT_LEVEL)
   private String temperature;
   </code>
   The level argument to DOEField is explained in that class and defines at what level the parameter should be operated when expanding
   the experiments.
   
   NOTE: When changing a field to be a string the castor mapping and schema have to be updated.
   
4. Use <code>DOEUtils.expand(Object bean)</code> the bean objects to a list of experiment definitions.

5. The annotation <code> @DOEControl </code> can be used where one field value means that other fields are not to be read.
**/
package org.eclipse.dawnsci.doe;

