/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.persistence;

import org.eclipse.dawnsci.analysis.api.diffraction.IPowderCalibrationInfo;
import org.eclipse.dawnsci.analysis.api.metadata.IDiffractionMetadata;
import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.metadata.OriginMetadata;

public interface IPersistentNodeFactory {

	/**
	 * Read Operations from a tree file
	 * 
	 * @param tree
	 * @return operations
	 * @throws Exception
	 */
	public IOperation[] readOperationsFromTree(Tree tree) throws Exception;
	
	/**
	 * Write operations to in memory tree
	 * 
	 * @param operations
	 * @return node
	 */
	public GroupNode writeOperationsToGroup(IOperation[] operations);
	
	/**
	 * Write processing origin information to in memory tree
	 * 
	 * @param origin
	 * @return node
	 */
	public GroupNode writeOriginalDataInformation(OriginMetadata origin);
	
	/**
	 * Write powder diffraction calibration to Nexus Standard formated nodes
	 * 
	 * @param metadata
	 * @param calibrationImage
	 * @param info
	 * @return node
	 */
	public GroupNode writePowderCalibrationToFile(IDiffractionMetadata metadata, IDataset calibrationImage, IPowderCalibrationInfo info);
}
