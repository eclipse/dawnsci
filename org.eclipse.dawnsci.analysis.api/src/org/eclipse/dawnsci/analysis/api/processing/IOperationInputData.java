package org.eclipse.dawnsci.analysis.api.processing;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.january.dataset.IDataset;

public interface IOperationInputData {

	public IDataset getInputData();
	
	@SuppressWarnings("rawtypes")
	public List<IOperation> getCurrentOperations();
	
}
