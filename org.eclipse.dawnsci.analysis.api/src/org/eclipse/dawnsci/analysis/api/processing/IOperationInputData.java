package org.eclipse.dawnsci.analysis.api.processing;

import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.processing.OperationData;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.january.dataset.IDataset;

public interface IOperationInputData {

	public IDataset getInputData();
	
	public IOperation<? extends IOperationModel, ? extends OperationData> getCurrentOperation();
	
}
